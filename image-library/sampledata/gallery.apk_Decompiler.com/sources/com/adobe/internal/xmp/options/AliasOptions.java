package com.adobe.internal.xmp.options;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AliasOptions extends Options {
    public static final int PROP_ARRAY = 512;
    public static final int PROP_ARRAY_ALTERNATE = 2048;
    public static final int PROP_ARRAY_ALT_TEXT = 4096;
    public static final int PROP_ARRAY_ORDERED = 1024;
    public static final int PROP_DIRECT = 0;

    public AliasOptions() {
    }

    public String defineOptionName(int i2) {
        if (i2 == 0) {
            return "PROP_DIRECT";
        }
        if (i2 == 512) {
            return "ARRAY";
        }
        if (i2 == 1024) {
            return "ARRAY_ORDERED";
        }
        if (i2 == 2048) {
            return "ARRAY_ALTERNATE";
        }
        if (i2 != 4096) {
            return null;
        }
        return "ARRAY_ALT_TEXT";
    }

    public int getValidOptions() {
        return 7680;
    }

    public boolean isArray() {
        return getOption(512);
    }

    public boolean isArrayAltText() {
        return getOption(4096);
    }

    public boolean isArrayAlternate() {
        return getOption(2048);
    }

    public boolean isArrayOrdered() {
        return getOption(1024);
    }

    public boolean isSimple() {
        if (getOptions() == 0) {
            return true;
        }
        return false;
    }

    public AliasOptions setArray(boolean z) {
        setOption(512, z);
        return this;
    }

    public AliasOptions setArrayAltText(boolean z) {
        setOption(7680, z);
        return this;
    }

    public AliasOptions setArrayAlternate(boolean z) {
        setOption(3584, z);
        return this;
    }

    public AliasOptions setArrayOrdered(boolean z) {
        setOption(1536, z);
        return this;
    }

    public PropertyOptions toPropertyOptions() {
        return new PropertyOptions(getOptions());
    }

    public AliasOptions(int i2) {
        super(i2);
    }
}
