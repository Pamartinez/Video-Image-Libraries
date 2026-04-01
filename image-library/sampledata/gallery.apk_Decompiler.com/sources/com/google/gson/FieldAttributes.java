package com.google.gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field field2) {
        Objects.requireNonNull(field2);
        this.field = field2;
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        return this.field.getAnnotation(cls);
    }

    public Collection<Annotation> getAnnotations() {
        return Arrays.asList(this.field.getAnnotations());
    }

    public Class<?> getDeclaredClass() {
        return this.field.getType();
    }

    public Type getDeclaredType() {
        return this.field.getGenericType();
    }

    public Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public String getName() {
        return this.field.getName();
    }

    public boolean hasModifier(int i2) {
        if ((this.field.getModifiers() & i2) != 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.field.toString();
    }
}
