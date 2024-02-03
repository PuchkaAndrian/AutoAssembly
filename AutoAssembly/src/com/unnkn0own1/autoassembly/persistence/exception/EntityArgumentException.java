package com.unnkn0own1.autoassembly.persistence.exception;

import java.util.List;

/**
 * Клас власної виняткової ситуацiї для обробки випадкiв, коли введенi аргументи полiв сутностi є
 * неправильними.
 * <p>
 * Цей виняток розширює клас {@link IllegalArgumentException} та включає список повiдомлень про помилки,
 * що вказують на конкретнi проблеми з валiдацiєю полiв сутностi.
 *
 * @author Puchka Andrian
 * @version 1.0
 */
public class EntityArgumentException extends IllegalArgumentException {

    /**
     * Список повiдомлень про помилки, що описують проблеми валiдацiї полiв сутностi.
     */
    private final List<String> errors;

    /**
     * Конструює новий об'єкт {@code EntityArgumentException} з вказаним списком повiдомлень про помилки.
     *
     * @param errors список повiдомлень про помилки, що вказують на проблеми валiдацiї полiв сутностi
     */
    public EntityArgumentException(List<String> errors) {
        this.errors = errors;
    }

    /**
     * Отримує список повiдомлень про помилки, пов'язаних з цим винятком.
     *
     * @return список повiдомлень про помилки
     */
    public List<String> getErrors() {
        return errors;
    }
}
