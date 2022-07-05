package com.example.jboss;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.javafaker.Faker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private static final int DATA_SIZE = 100;

    @GetMapping("/ppl")
    public List<Person> randomPeople() {
        return generateRandomPeople();
    }

    private List<Person> generateRandomPeople() {
        final Faker faker = new Faker();
        return Stream.generate(() -> new Person(faker.name().firstName(),
                faker.name().lastName(),
                faker.phoneNumber().cellPhone(),
                faker.funnyName().name(),
                faker.beer().name(),
                faker.animal().name(),
                faker.job().title(),
                faker.company().name(),
                faker.food().dish(),
                faker.date().birthday(),
                faker.lorem().sentence(50),
                faker.university().name()))
                .limit(DATA_SIZE)
                .collect(Collectors.toList());
    }

    class Person {

        private String firstName;
        private String lastName;
        private String cellphone;
        private String nickname;
        private String favoriteBeer;
        private String animal;
        private String jobTitle;
        private String company;
        private String favoriteDish;
        private Date birthday;
        private String bio;
        private String univeristy;

        public Person(String firstName, String lastName, String cellphone, String nickname, String favoriteBeer,
                String animal, String jobTitle, String company, String favoriteDish, Date birthday, String bio,
                String univeristy) {

            this.firstName = firstName;
            this.lastName = lastName;
            this.cellphone = cellphone;
            this.nickname = nickname;
            this.favoriteBeer = favoriteBeer;
            this.animal = animal;
            this.jobTitle = jobTitle;
            this.company = company;
            this.favoriteDish = favoriteDish;
            this.birthday = birthday;
            this.bio = bio;
            this.univeristy = univeristy;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getCellphone() {
            return cellphone;
        }

        public String getNickname() {
            return nickname;
        }

        public String getFavoriteBeer() {
            return favoriteBeer;
        }

        public String getAnimal() {
            return animal;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public String getCompany() {
            return company;
        }

        public String getFavoriteDish() {
            return favoriteDish;
        }

        public Date getBirthday() {
            return birthday;
        }

        public String getBio() {
            return bio;
        }

        public String getUniveristy() {
            return univeristy;
        }

    }
}
