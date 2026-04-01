package com.adobe.internal.xmp.options;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TemplateOptions extends Options {
    public static final int ADD_NEW_PROPERTIES = 64;
    public static final int CLEAR_UNNAMED_PROPERTIES = 2;
    public static final int INCLUDE_INTERNAL_PROPERTIES = 32;
    public static final int REPLACE_EXISTING_PROPERTIES = 16;
    public static final int REPLACE_WITH_DELETE_EMPTY = 128;

    public TemplateOptions() {
    }

    public String defineOptionName(int i2) {
        if (i2 == 2) {
            return "CLEAR_UNNAMED_PROPERTIES";
        }
        if (i2 == 16) {
            return "REPLACE_EXISTING_PROPERTIES";
        }
        if (i2 == 32) {
            return "INCLUDE_INTERNAL_PROPERTIES";
        }
        if (i2 == 64) {
            return "ADD_NEW_PROPERTIES";
        }
        if (i2 != 128) {
            return null;
        }
        return "REPLACE_WITH_DELETE_EMPTY";
    }

    public int getValidOptions() {
        return 242;
    }

    public TemplateOptions(int i2) {
        super(i2);
    }
}
