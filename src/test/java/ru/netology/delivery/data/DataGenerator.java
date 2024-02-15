package ru.netology.delivery.data;

import com.github.javafaker.Faker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataGenerator {
    static final String dateFormat = "dd.MM.yyyy";
    static List<String> cities = new ArrayList<>();
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        // логика генерации строки с датой
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern(dateFormat));
        return date;
    }

    public static void generateListCities() throws IOException{
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/test/resources/city.csv", StandardCharsets.UTF_8))){
            String line;
            while ((line = fileReader.readLine()) != null) {
                cities.add(line);
            }
        }
    }
    public static String generateCity(String locale) throws IOException {
        // генерация города их списка
        generateListCities();
        Random random = new Random();
        String city = cities.get(random.nextInt(cities.size()));
        return city;
    }

    public static String generateName(String locale) {
        // генерация фамилия + имя
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    public static String generatePhone(String locale) {
        // генерация номера телефона
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) throws IOException {
            UserInfo user = new UserInfo();
            user.city = generateCity(locale);
            user.name = generateName(locale);
            user.phone = generatePhone(locale);
            return user;
        }
    }

    public static class UserInfo {
        String city;
        String name;
        String phone;

        public String getCity() {
            return city;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }
    }

}