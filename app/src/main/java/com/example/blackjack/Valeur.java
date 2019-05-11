package com.example.blackjack;

public enum Valeur {
    AS("a",1),
    DEUX("2", 2),
    TROIS("3", 3),
    QUATRE("4", 4),
    CINQ("5", 5),
    SIX("6", 6),
    SEPT("7", 7),
    HUIT("8", 8),
    NEUF("9", 9),
    DIX("10", 10),
    VALET("j", 10),
    DAME("q", 10),
    ROI("k", 10);

    private String stringValue;
    private int intValue;

    private Valeur(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public int getValue(){
        return intValue;
    }
}