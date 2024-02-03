package com.unnkn0own1.autoassembly.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * Десерiалiзатор Gson для конвертацiї JSON-представлення об'єктiв типу LocalDate.
 */
public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

    /**
     * Десерiалiзує JSON-елемент у об'єкт типу LocalDate.
     *
     * @param json    JSON-елемент для десерiалiзацiї.
     * @param typeOfT Тип об'єкта, до якого вiдбувається десерiалiзацiя.
     * @param context Контекст десерiалiзацiї Gson.
     * @return Об'єкт LocalDate, який представляє десерiалiзоване значення.
     * @throws JsonParseException Виняток, який викидається у випадку помилки десерiалiзацiї.
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
    }
}
