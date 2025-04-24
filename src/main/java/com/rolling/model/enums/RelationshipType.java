package com.rolling.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RelationshipType {
    FRIEND("친구"), FAMILY("가족"), COLLEAGUE("동료"), OTHER("지인");

    private final String value;

    RelationshipType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static RelationshipType fromValue(String value) {
        for (RelationshipType type : RelationshipType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return OTHER;
    }
}
