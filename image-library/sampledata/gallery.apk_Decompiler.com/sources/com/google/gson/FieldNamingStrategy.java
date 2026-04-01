package com.google.gson;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface FieldNamingStrategy {
    List<String> alternateNames(Field field) {
        return Collections.EMPTY_LIST;
    }

    String translateName(Field field);
}
