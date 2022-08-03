package com.example.grpc.server;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.grpc.lib.People;
import com.example.grpc.lib.People.Person;
import com.example.grpc.lib.SampleServiceGrpc.SampleServiceImplBase;
import com.github.javafaker.Faker;
import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class SampleService extends SampleServiceImplBase {

    private static final int DATA_SIZE = 100;

    public void getPeople(Empty emptyRequest, StreamObserver<People> responseObserver) {
        People response = People.newBuilder().addAllPpl(generateRandomPeople()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private List<Person> generateRandomPeople() {
        final Faker faker = new Faker();
        return Stream.generate(() -> Person.newBuilder()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setCellPhone(faker.phoneNumber().cellPhone())
                .setNickname(faker.funnyName().name())
                .setFavoriteBeer(faker.beer().name())
                .setAnimal(faker.animal().name())
                .setJobTitle(faker.job().title())
                .setCompany(faker.company().name())
                .setFavoriteDish(faker.food().dish())
                .setBirthday(faker.date().birthday().toString())
                .setBio(faker.lorem().sentence(50))
                .setUniversity(faker.university().name())
                .build())
                .limit(DATA_SIZE)
                .collect(Collectors.toList());
    }
}
