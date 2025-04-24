// 새 파일: rolling/src/main/java/com/rolling/model/enums/ColorType.java
package com.rolling.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ColorType {
    BEIGE("beige"), PURPLE("purple"), GREEN("green"), BLUE("blue");

    private String value;

    ColorType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ColorType fromValue(String value) {
        for (ColorType type : ColorType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return BEIGE; // 기본값으로 BEIGE 반환
    }
}
