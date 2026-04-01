package com.adobe.internal.xmp.options;

import com.adobe.internal.xmp.XMPException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PropertyOptions extends Options {
    public static final int ARRAY = 512;
    public static final int ARRAY_ALTERNATE = 2048;
    public static final int ARRAY_ALT_TEXT = 4096;
    public static final int ARRAY_ORDERED = 1024;
    public static final int DELETE_EXISTING = 536870912;
    public static final int HAS_LANGUAGE = 64;
    public static final int HAS_QUALIFIERS = 16;
    public static final int HAS_TYPE = 128;
    public static final int NO_OPTIONS = 0;
    public static final int QUALIFIER = 32;
    public static final int SCHEMA_NODE = Integer.MIN_VALUE;
    public static final int STRUCT = 256;
    public static final int URI = 2;
    private int arrayElementsLimit = -1;

    public PropertyOptions() {
    }

    public void assertConsistency(int i2) {
        if ((i2 & 256) > 0 && (i2 & 512) > 0) {
            throw new XMPException("IsStruct and IsArray options are mutually exclusive", 103);
        } else if ((i2 & 2) > 0 && (i2 & 768) > 0) {
            throw new XMPException("Structs and arrays can't have \"value\" options", 103);
        }
    }

    public String defineOptionName(int i2) {
        switch (i2) {
            case Integer.MIN_VALUE:
                return "SCHEMA_NODE";
            case 2:
                return "URI";
            case 16:
                return "HAS_QUALIFIER";
            case 32:
                return "QUALIFIER";
            case 64:
                return "HAS_LANGUAGE";
            case 128:
                return "HAS_TYPE";
            case 256:
                return "STRUCT";
            case 512:
                return "ARRAY";
            case 1024:
                return "ARRAY_ORDERED";
            case 2048:
                return "ARRAY_ALTERNATE";
            case 4096:
                return "ARRAY_ALT_TEXT";
            default:
                return null;
        }
    }

    public boolean equalArrayTypes(PropertyOptions propertyOptions) {
        if (isArray() == propertyOptions.isArray() && isArrayOrdered() == propertyOptions.isArrayOrdered() && isArrayAlternate() == propertyOptions.isArrayAlternate() && isArrayAltText() == propertyOptions.isArrayAltText()) {
            return true;
        }
        return false;
    }

    public int getArrayElementsLimit() {
        return this.arrayElementsLimit;
    }

    public boolean getHasLanguage() {
        return getOption(64);
    }

    public boolean getHasQualifiers() {
        return getOption(16);
    }

    public boolean getHasType() {
        return getOption(128);
    }

    public int getValidOptions() {
        return -1610604558;
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

    public boolean isArrayLimited() {
        if (this.arrayElementsLimit != -1) {
            return true;
        }
        return false;
    }

    public boolean isArrayOrdered() {
        return getOption(1024);
    }

    public boolean isCompositeProperty() {
        if ((getOptions() & 768) > 0) {
            return true;
        }
        return false;
    }

    public boolean isOnlyArrayOptions() {
        if ((getOptions() & -7681) == 0) {
            return true;
        }
        return false;
    }

    public boolean isQualifier() {
        return getOption(32);
    }

    public boolean isSchemaNode() {
        return getOption(Integer.MIN_VALUE);
    }

    public boolean isSimple() {
        if ((getOptions() & 768) == 0) {
            return true;
        }
        return false;
    }

    public boolean isStruct() {
        return getOption(256);
    }

    public boolean isURI() {
        return getOption(2);
    }

    public void mergeWith(PropertyOptions propertyOptions) {
        if (propertyOptions != null) {
            setOptions(propertyOptions.getOptions() | getOptions());
        }
    }

    public PropertyOptions setArray(boolean z) {
        setOption(512, z);
        return this;
    }

    public PropertyOptions setArrayAltText(boolean z) {
        setOption(4096, z);
        return this;
    }

    public PropertyOptions setArrayAlternate(boolean z) {
        setOption(2048, z);
        return this;
    }

    public PropertyOptions setArrayElementLimit(int i2) {
        this.arrayElementsLimit = i2;
        return this;
    }

    public PropertyOptions setArrayOrdered(boolean z) {
        setOption(1024, z);
        return this;
    }

    public PropertyOptions setHasLanguage(boolean z) {
        setOption(64, z);
        return this;
    }

    public PropertyOptions setHasQualifiers(boolean z) {
        setOption(16, z);
        return this;
    }

    public PropertyOptions setHasType(boolean z) {
        setOption(128, z);
        return this;
    }

    public PropertyOptions setQualifier(boolean z) {
        setOption(32, z);
        return this;
    }

    public PropertyOptions setSchemaNode(boolean z) {
        setOption(Integer.MIN_VALUE, z);
        return this;
    }

    public PropertyOptions setStruct(boolean z) {
        setOption(256, z);
        return this;
    }

    public PropertyOptions setURI(boolean z) {
        setOption(2, z);
        return this;
    }

    public PropertyOptions(int i2) {
        super(i2);
    }
}
