package com.example.web_zaim.model.enums;

import lombok.Getter;

@Getter
public enum RatingPayments {
    ONE('1', 1),
    ZERO('0', 2),
    A('A', 3),
    TWO('2', 4),
    THREE('3', 5),
    X('X', 0);

    private final Character description;
    private final Integer value;

    RatingPayments(Character description, Integer value) {
        this.description = description;
        this.value = value;
    }

    public static RatingPayments getValueByCharacter(Character character) {
        for (RatingPayments s : values()) {
            if (s.getDescription().equals(character)) return s;
        }
        return RatingPayments.X;
    }
}
