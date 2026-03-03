package com.example.kalkulator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class KalkulatorController {

    @FXML
    private TextField displayField;

    // obsługa przycisków

    @FXML
    protected void onBtn(ActionEvent e) {
        String val = ((Button) e.getSource()).getText();
        if (val.equals("×")) val = "*";
        if (val.equals("÷")) val = "/";
        appendToDisplay(val);
    }

    @FXML
    protected void onClear() {
        displayField.setText("");
    }

    @FXML
    protected void onBackspace() {
        String t = displayField.getText();
        if (!t.isEmpty()) displayField.setText(t.substring(0, t.length() - 1));
    }

    @FXML
    protected void onEquals() {
        calculate();
    }

    @FXML
    protected void onKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case ENTER -> calculate();
            case ESCAPE -> displayField.setText("");
            default -> { /* pozostałe klawisze działają normalnie */ }
        }
    }

    // logika – parsowanie i liczenie wyrażenia

    private void appendToDisplay(String s) {
        displayField.appendText(s);
    }

    private void calculate() {
        String expr = displayField.getText().trim();
        if (expr.isEmpty()) return;
        try {
            double result = evalExpression(expr);
            if (result == Math.floor(result) && !Double.isInfinite(result)) {
                displayField.setText(String.valueOf((long) result));
            } else {
                displayField.setText(String.valueOf(result));
            }
        } catch (ArithmeticException ex) {
            displayField.setText("Błąd: " + ex.getMessage());
        } catch (Exception ex) {
            displayField.setText("Błąd składni");
        }
    }

    // zamiana wyrażenia na wynik

    private double evalExpression(String expr) {
        List<String> tokens = tokenize(expr);
        List<String> rpn = toRPN(tokens);
        return evalRPN(rpn);
    }

    private List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (Character.isWhitespace(c)) { i++; continue; }
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                tokens.add(sb.toString());
            } else if (c == '-' && (tokens.isEmpty() || isOperator(tokens.getLast()) || tokens.getLast().equals("("))) {
                StringBuilder sb = new StringBuilder("-");
                i++;
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                if (sb.length() == 1) {
                    tokens.add("-");
                } else {
                    tokens.add(sb.toString());
                }
            } else {
                tokens.add(String.valueOf(c));
                i++;
            }
        }
        return tokens;
    }

    private boolean isOperator(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    private int precedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> 0;
        };
    }

    private List<String> toRPN(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> ops = new ArrayDeque<>();
        for (String t : tokens) {
            if (isNumber(t)) {
                output.add(t);
            } else if (isOperator(t)) {
                while (!ops.isEmpty() && isOperator(ops.peek())
                        && ops.peek() != null && precedence(ops.peek()) >= precedence(t)) {
                    output.add(ops.pop());
                }
                ops.push(t);
            } else if (t.equals("(")) {
                ops.push(t);
            } else if (t.equals(")")) {
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    output.add(ops.pop());
                }
                if (!ops.isEmpty()) ops.pop();
                else throw new IllegalArgumentException("Niezgodne nawiasy");
            }
        }
        while (!ops.isEmpty()) {
            String top = ops.pop();
            if (top.equals("(")) throw new IllegalArgumentException("Niezgodne nawiasy");
            output.add(top);
        }
        return output;
    }

    private boolean isNumber(String t) {
        try { Double.parseDouble(t); return true; }
        catch (NumberFormatException e) { return false; }
    }

    private double evalRPN(List<String> rpn) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String t : rpn) {
            if (isNumber(t)) {
                stack.push(Double.parseDouble(t));
            } else if (isOperator(t)) {
                if (stack.size() < 2) throw new IllegalArgumentException("Błąd wyrażenia");
                double b = stack.pop();
                double a = stack.pop();
                stack.push(switch (t) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> {
                        if (b == 0) throw new ArithmeticException("dzielenie przez zero");
                        yield a / b;
                    }
                    default -> throw new IllegalArgumentException("Nieznany operator: " + t);
                });
            }
        }
        if (stack.size() != 1) throw new IllegalArgumentException("Błąd wyrażenia");
        return stack.pop();
    }
}



