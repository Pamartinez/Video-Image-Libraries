package com.adobe.internal.xmp.options;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class IteratorOptions extends Options {
    public static final int JUST_CHILDREN = 256;
    public static final int JUST_LEAFNAME = 1024;
    public static final int JUST_LEAFNODES = 512;
    public static final int OMIT_QUALIFIERS = 4096;

    public String defineOptionName(int i2) {
        if (i2 == 256) {
            return "JUST_CHILDREN";
        }
        if (i2 == 512) {
            return "JUST_LEAFNODES";
        }
        if (i2 == 1024) {
            return "JUST_LEAFNAME";
        }
        if (i2 != 4096) {
            return null;
        }
        return "OMIT_QUALIFIERS";
    }

    public int getValidOptions() {
        return 5888;
    }

    public boolean isJustChildren() {
        return getOption(256);
    }

    public boolean isJustLeafname() {
        return getOption(1024);
    }

    public boolean isJustLeafnodes() {
        return getOption(512);
    }

    public boolean isOmitQualifiers() {
        return getOption(4096);
    }

    public IteratorOptions setJustChildren(boolean z) {
        setOption(256, z);
        return this;
    }

    public IteratorOptions setJustLeafname(boolean z) {
        setOption(1024, z);
        return this;
    }

    public IteratorOptions setJustLeafnodes(boolean z) {
        setOption(512, z);
        return this;
    }

    public IteratorOptions setOmitQualifiers(boolean z) {
        setOption(4096, z);
        return this;
    }
}
