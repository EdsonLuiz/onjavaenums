package org.example.enums;

import java.util.Random;

public enum Input {

    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        @Override
        int amount() {
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP { // this must be the last instance.

        @Override
        int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    int value; // In cents
    Input(int value) {this.value = value;}
    Input() {}
    int amount() {return value;} // In cents;

    static Random rand = new Random(47);

    public static Input randomSelection() {
        // Don't include STOP;
        return values()[rand.nextInt(values().length) -1];
    }
}
