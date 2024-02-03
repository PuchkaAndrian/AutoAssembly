package com.unnkn0own1.autoassembly.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Серiалiзатор Gson для конвертацiї об'єктiв типу LocalDate у JSON-представлення.
 */
public class LocalDateSerializer implements JsonSerializer<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Серiалiзує об'єкт типу LocalDate у JSON-елемент.
     *
     * @param localDate                  Об'єкт типу LocalDate для серiалiзацiї.
     * @param type                       Тип об'єкта, з яким працює серiалiзатор.
     * @param jsonSerializationContext   Контекст серiалiзацiї Gson.
     * @return JSON-елемент, який представляє серiалiзоване значення LocalDate.
     */
    @Override
    public JsonElement serialize(LocalDate localDate, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}
