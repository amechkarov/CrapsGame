package com.gangames.crapsgame.utils;

public class EnumValuesValidator {

    public static <T extends Enum<T>> boolean isValidEnum(Class<T> enumClass, String value) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
