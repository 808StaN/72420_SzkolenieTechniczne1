package com.example.zadania;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FiguraTest {

    @Test
    void prostokatKonstruktorIProperty() {
        Prostokat p = new Prostokat("P", 2.0, 3.0);
        Assertions.assertEquals("P", p.getNazwa());
        Assertions.assertEquals(2.0, p.getA(), 0.0001);
        Assertions.assertEquals(3.0, p.getB(), 0.0001);

        p.setA(4.0);
        p.setB(5.0);
        Assertions.assertEquals(4.0, p.getA(), 0.0001);
        Assertions.assertEquals(5.0, p.getB(), 0.0001);
    }

    @Test
    void prostokatLiczyPole() {
        Prostokat p = new Prostokat("P", 2.0, 3.0);
        Assertions.assertEquals(6.0, p.pole(), 0.0001);
    }

    @Test
    void polimorfizmDlaRoznychFigur() {
        List<Figura> figury = new ArrayList<Figura>();
        figury.add(new Prostokat("P", 2.0, 5.0));
        figury.add(new Kolo("K", 1.0));

        double sumaPol = 0.0;
        for (Figura figura : figury) {
            sumaPol += figura.pole();
        }

        Assertions.assertEquals(10.0 + Math.PI, sumaPol, 0.0001);
    }

    @Test
    void wyszukiwaniePoWarunkuPola() {
        List<Figura> figury = new ArrayList<Figura>();
        figury.add(new Prostokat("MalyProstokat", 1.0, 2.0));
        figury.add(new Kolo("MaleKolo", 1.0));
        figury.add(new Prostokat("DuzyProstokat", 4.0, 5.0));

        List<Figura> znalezione = new ArrayList<Figura>();
        for (Figura figura : figury) {
            if (figura.pole() < 3.5) {
                znalezione.add(figura);
            }
        }

        Assertions.assertEquals(2, znalezione.size());
    }
}

