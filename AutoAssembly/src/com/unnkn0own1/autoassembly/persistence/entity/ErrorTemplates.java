package com.unnkn0own1.autoassembly.persistence.entity;

/**
 * Перерахування, яке мiстить шаблони помилок для валiдацiї полiв сутностей.
 */
public enum ErrorTemplates {
    REQUIRED("Поле %s є обов'язковим до заповнення."),
    MIN_LENGTH("Поле %s не може бути меншим за %d симв."),
    MAX_LENGTH("Поле %s не може бути бiльшим за %d симв."),
    ONLY_LATIN("Поле %s лише латинськi символи."),
    INVALID_BIRTHDAY("Поле %s повинно мiстити дату у форматi рррр-мм-дд."),
    INVALID_EMAIL("Поле %s повинно мiстити латинськi символи та символ @."),

    PASSWORD(
            "Поле %s повинно мiстити латинськi лiтери, принаймнi одну велику та одну малу лiтеру, та принаймнi одну цифру.");

    private String template;

    /**
     * Конструктор з параметром, що встановлює шаблон помилки.
     *
     * @param template Шаблон помилки.
     */
    ErrorTemplates(String template) {
        this.template = template;
    }

    /**
     * Повертає текстовий шаблон помилки.
     *
     * @return Текстовий шаблон помилки.
     */
    public String getTemplate() {
        return template;
    }
}
