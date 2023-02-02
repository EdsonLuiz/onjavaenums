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

    enum StateDuration { TRANSIENT } // Tagging enum
    enum State {
        private boolean isTransient = false;
        State() {}
        State(StateDuration trans) {isTransient = true;}

    }
}
