package com.example.zadania;

public class Kolo extends Figura {
    private double r;

    public Kolo(String nazwa, double r) {
        super(nazwa);
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public void info() {
        System.out.println("Kolo " + getNazwa() + " [r=" + r + "]");
    }

    @Override
    public double pole() {
        return Math.PI * r * r;
    }
}

