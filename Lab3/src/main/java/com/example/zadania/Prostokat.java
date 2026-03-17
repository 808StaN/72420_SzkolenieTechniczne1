package com.example.zadania;

public class Prostokat extends Figura {
    private double a;
    private double b;

    public Prostokat(String nazwa, double a, double b) {
        super(nazwa);
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public void info() {
        System.out.println("Prostokat " + getNazwa() + " [a=" + a + ", b=" + b + "]");
    }

    @Override
    public double pole() {
        return a * b;
    }
}

