package com.rolling.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ColorTypeConverter implements AttributeConverter<ColorType, String> {

    @Override
    public String convertToDatabaseColumn(ColorType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public ColorType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        //
        for (ColorType color : ColorType.values()) {
            if (color.getValue().equals(dbData)) {
                return color;
            }
        }

        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
