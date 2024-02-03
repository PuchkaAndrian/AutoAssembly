package com.unnkn0own1.autoassembly.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Абстрактний клас, який представляє базовий клас для всiх сутностей в системi.
 */
public abstract class Entity {

    protected final UUID id;
    protected List<String> errors;
    protected boolean isValid;

    /**
     * Конструктор, який приймає унiкальний iдентифiкатор для сутностi.
     *
     * @param id Унiкальний iдентифiкатор сутностi.
     */
    protected Entity(UUID id) {
        errors = new ArrayList<>();
        this.id = id;
    }

    /**
     * Повертає унiкальний iдентифiкатор сутностi.
     *
     * @return Унiкальний iдентифiкатор сутностi.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Перевiряє, чи є сутнiсть валiдною (без помилок).
     *
     * @return {@code true}, якщо сутнiсть валiдна; iнакше {@code false}.
     */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     * Повертає список помилок, пов'язаних iз сутнiстю.
     *
     * @return Список помилок сутностi.
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Порiвнює поточний об'єкт iз iншим об'єктом.
     *
     * @param o iнший об'єкт для порiвняння.
     * @return {@code true}, якщо об'єкти рiвнi; iнакше {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    /**
     * Повертає хеш-код об'єкту.
     *
     * @return Хеш-код об'єкту.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
