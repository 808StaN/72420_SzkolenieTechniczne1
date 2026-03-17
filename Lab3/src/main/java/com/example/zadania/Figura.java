package com.example.zadania;

/**
 * Bazowa klasa figur geometrycznych.
 */
public abstract class Figura {
    private String nazwa;

    protected Figura(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Zwraca pole figury.
     */
    public abstract double pole();

    /**
     * Wypisuje opis figury.
     */
    public abstract void info();
}

