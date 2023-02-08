package org.example.enums;

public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}
