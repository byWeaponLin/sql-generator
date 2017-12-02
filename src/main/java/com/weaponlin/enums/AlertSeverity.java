package com.weaponlin.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum  AlertSeverity {
    WARNING("warning"), CRITICAL("critical");

    private String severity;
}
