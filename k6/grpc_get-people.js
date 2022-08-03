import grpc from 'k6/net/grpc';

const client = new grpc.Client();
client.load([], 'people.proto');

export const options = {
    stages: [
        { duration: '3m', target: 100 }, // simulate ramp-up of traffic from 1 to 100 users over 3 minute.
        { duration: '9m', target: 100 }, // stay at 100 users for 9 minutes
        { duration: '3m', target: 0 }, // ramp-down to 0 users
    ],
    insecureSkipTLSVerify: true,
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(95)', 'p(99)', 'count'],
};

const connectParams = {}
const emptyRequest = {};

export default () => {
    client.connect('localhost:8443', connectParams);
    client.invoke('com.example.grpc.SampleService/getPeople', emptyRequest);
    client.close();
};
