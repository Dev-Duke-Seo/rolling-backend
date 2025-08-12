package com.common.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Locale;

/**
 * Hibernate 물리적 명명 전략으로, 카멜 케이스(camelCase)의 필드 이름을 스네이크 케이스(snake_case)의 데이터베이스 컬럼 이름으로 변환합니다. 예:
 * 'userId' -> 'user_id'
 */
public class SnakeCasePhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return name == null ? null : toSnakeCase(name);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return name == null ? null : toSnakeCase(name);
    }

    private Identifier toSnakeCase(Identifier id) {
        String name = id.getText();
        String snakeName = camelToSnake(name);
        return snakeName.equals(name) ? id : Identifier.toIdentifier(snakeName, id.isQuoted());
    }

    private String camelToSnake(String name) {
        if (name == null) {
            return null;
        }

        StringBuilder result = new StringBuilder(name.length() * 2);
        boolean wasUpperCase = false;

        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            boolean isUpperCase = Character.isUpperCase(ch);

            if (isUpperCase) {
                if (!wasUpperCase && i > 0 && result.charAt(result.length() - 1) != '_') {
                    result.append('_');
                }
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }

            wasUpperCase = isUpperCase;
        }

        return result.toString().toLowerCase(Locale.ROOT);
    }
}
