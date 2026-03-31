package com.example.lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class Zadania {

    @FunctionalInterface
    interface TextOperation {
        String apply(String value);
    }

    private Zadania() {
    }

    public static String buildReport() {
        List<Integer> numbers = Arrays.asList(12, 3, 8, 5, 20, 7, 14, 1, 18, 9, 22, 30);
        List<Osoba> people = Arrays.asList(
                new Osoba("Anna", "Nowak", 1998),
                new Osoba("Aneta", "Kowalska", 2001),
                new Osoba("Adam", "Kowalski", 1995),
                new Osoba("Alicja", "Kowalska", 2003),
                new Osoba("Piotr", "Zielinski", 1989),
                new Osoba("Pawel", "Adamski", 2004),
                new Osoba("Agnieszka", "Nowak", 1992),
                new Osoba("Marek", "Nowak", 2000)
        );

        StringBuilder sb = new StringBuilder();

        sb.append("1) Interfejs + lambda\n");
        TextOperation op = text -> "Lambda wykonana: " + text.toUpperCase();
        sb.append(op.apply("hello from lambda")).append("\n\n");

        sb.append("2) Elementy parzyste\n");
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        sb.append(evenNumbers).append("\n\n");

        sb.append("3) Najmniejsza wartosc parzysta\n");
        Optional<Integer> minEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .min(Integer::compareTo);
        sb.append(minEven.map(String::valueOf).orElse("Brak elementow parzystych")).append("\n\n");

        sb.append("4) Elementy z zakresu [5, 20]\n");
        List<Integer> inRange = numbers.stream()
                .filter(n -> n >= 5 && n <= 20)
                .collect(Collectors.toList());
        sb.append(inRange).append("\n\n");

        sb.append("5) Osoby urodzone po 1999\n");
        List<Osoba> bornAfter = people.stream()
                .filter(p -> p.getBirthYear() > 1999)
                .collect(Collectors.toList());
        sb.append(formatPeople(bornAfter)).append("\n\n");

        sb.append("6) Osoby, ktorych imie zaczyna sie od 'An', sortowanie po nazwisku\n");
        List<Osoba> namePrefixSorted = people.stream()
                .filter(p -> p.getFirstName().startsWith("An"))
                .sorted(Comparator.comparing(Osoba::getLastName))
                .collect(Collectors.toList());
        sb.append(formatPeople(namePrefixSorted)).append("\n\n");

        sb.append("7) Ile jest takich osob?\n");
        long countPrefix = people.stream()
                .filter(p -> p.getFirstName().startsWith("An"))
                .count();
        sb.append(countPrefix).append("\n\n");

        sb.append("8) Algorytm laczacy 2 kolekcje (zip + lambda)\n");
        List<Integer> numbers2 = Arrays.asList(1, 10, 100, 1000, 10000, 7, 9, 11, 13, 15, 17, 19);
        List<Integer> merged = zipWith(numbers, numbers2, Integer::sum);
        sb.append("Wejscie 1: ").append(numbers).append("\n");
        sb.append("Wejscie 2: ").append(numbers2).append("\n");
        sb.append("Wynik (suma): ").append(merged).append("\n\n");

        sb.append("9) Sortowanie po nazwisku, a potem po imieniu\n");
        List<Osoba> sortedByLastThenFirst = people.stream()
                .sorted(Comparator.comparing(Osoba::getLastName)
                        .thenComparing(Osoba::getFirstName))
                .collect(Collectors.toList());
        sb.append(formatPeople(sortedByLastThenFirst)).append("\n");

        return sb.toString();
    }

    public static <A, B, R> List<R> zipWith(List<A> first, List<B> second, BiFunction<A, B, R> operation) {
        int size = Math.min(first.size(), second.size());
        List<R> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(operation.apply(first.get(i), second.get(i)));
        }
        return result;
    }

    private static String formatPeople(List<Osoba> people) {
        return people.stream()
                .map(Osoba::toString)
                .collect(Collectors.joining(", "));
    }
}



