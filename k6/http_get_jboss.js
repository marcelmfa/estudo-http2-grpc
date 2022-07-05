import http from 'k6/http';

export const options = {
    stages: [
        { duration: '3m', target: 100 }, // simulate ramp-up of traffic from 1 to 100 users over 3 minute.
        { duration: '9m', target: 100 }, // stay at 100 users for 9 minutes
        { duration: '3m', target: 0 }, // ramp-down to 0 users
    ],
    insecureSkipTLSVerify: true,
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(95)', 'p(99)', 'count'],
};

export default function () {
    http.get('https://localhost:8443/spring_jbosseap/ppl');
};