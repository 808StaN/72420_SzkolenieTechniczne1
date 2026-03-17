package com.example.lab4;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> owoce = new LinkedHashMap<>();
        owoce.put("ananas", "img/ananas.png");
        owoce.put("banan", "img/banan.png");
        owoce.put("cytryna", "img/cytryna.png");
        owoce.put("daktyl", "img/daktyl.png");
        owoce.put("figa", "img/figa.png");
        owoce.put("gruszka", "img/gruszka.png");
        owoce.put("jablko", "img/jablko.png");

        System.out.println("1) Owoce na litery a-j:");
        Map<String, String> owoceAJ = owoce.entrySet().stream()
                .filter(e -> {
                    char first = e.getKey().toLowerCase().charAt(0);
                    return first >= 'a' && first <= 'j';
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (left, right) -> left,
                        LinkedHashMap::new
                ));
        owoceAJ.forEach((k, v) -> System.out.println("- " + k + " => " + v));
        System.out.println("Liczba owocow (a-j): " + owoceAJ.size());

        System.out.println("\n2) Dodaje 2 elementy (put):");
        owoce.put("kiwi", "img/kiwi.png");
        owoce.put("wisnia", "img/wisnia.png");
        System.out.println("Nowy rozmiar mapy: " + owoce.size());

        System.out.println("\n3) containsKey('wisnia'): " + owoce.containsKey("wisnia"));

        System.out.println("\n4) Wszystkie owoce:");
        owoce.forEach((k, v) -> System.out.println("- " + k + " => " + v));

        System.out.println("\n5) clear + isEmpty:");
        owoce.clear();
        System.out.println("Mapa pusta? " + owoce.isEmpty());
        System.out.println("Zawartosc po clear: " + owoce);
    }
}


