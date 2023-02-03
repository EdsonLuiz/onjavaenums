package org.example.enums;

import java.util.*;
import java.io.IOException;
import java.util.function.*;
import java.nio.file.*;
import java.util.stream.*;

enum Category {
    MONEY(Input.NICKEL, Input.DIME,
            Input.QUARTER, Input.DOLLAR),
    ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS,
            Input.SODA, Input.SOAP),
    QUIT_TRANSACTION(Input.ABORT_TRANSACTION),
    SHUT_DOWN(Input.STOP);

    private Input[] values;
    Category(Input... types) {values = types;}
    private static EnumMap<Input, Category> categories = new EnumMap<>(Input.class);
    static {
        for (Category c : Category.class.getEnumConstants()) {
            for (Input type : c.values) {
                categories.put(type, c);
            }
        }
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

public class VendingMachine {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static Input selection = null;

    enum StateDuration { TRANSIENT } // Tagging enum
    enum State {
        RESTING {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if(amount < selection.amount())
                            System.out.println("Insufficient money for " + selection);
                        else state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    default:
                }
            }
        },
        DISPENSING{},
        GIVING_CHANGE{},
        TERMINAL{};
        private boolean isTransient = false;
        State() {}
        State(StateDuration trans) {isTransient = true;}

        void next(Input input) {
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }
        void next() {
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            System.out.println(amount);
        }
    }

    static void run(Supplier<Input> gen) {
        while (state != State.TERMINAL) {
            state.next(gen.get());
            while (state.isTransient)
                state.next();
        }
    }
}
