package com.example.zadania;

import java.util.ArrayList;
import java.util.List;

public class FiguryDemo {
    public static void main(String[] args) {
        Prostokat test = new Prostokat("Test", 2.0, 3.0);
        test.setA(2.5);
        test.setB(4.0);
        System.out.println("Test konstruktora i wlasciwosci Prostokat:");
        test.info();
        System.out.println("Pole testowego prostokata=" + test.pole());

        List<Figura> figury = new ArrayList<>();
        figury.add(new Prostokat("P1", 4.0, 5.0));
        figury.add(new Kolo("K1", 3.0));
        figury.add(new Prostokat("P2", 1.0, 2.0));
        figury.add(new Kolo("K2", 1.0));

        System.out.println("\nPolimorfizm (kolekcja Figura):");
        for (Figura figura : figury) {
            figura.info();
            System.out.println("Pole=" + figura.pole());
        }

        System.out.println("\nFigury spelniajace warunek pole() < 3.5:");
        for (Figura figura : figury) {
            if (figura.pole() < 3.5) {
                figura.info();
                System.out.println("Pole=" + figura.pole());
            }
        }
    }
}

