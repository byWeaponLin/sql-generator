package com.weaponlin.dsl.enums;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public enum LikeOption {
    NONE("%s"),
    LEFT("%%%s"),
    RIGHT("%s%%"),
    ALL("%%%s%%"),
    ;

    private String formatter;

    public String format(Object obj) {
        String nObj = Optional.ofNullable(obj)
                .map(Object::toString)
                .orElse("NULL");
        return String.format(this.formatter, nObj);
    }
}
