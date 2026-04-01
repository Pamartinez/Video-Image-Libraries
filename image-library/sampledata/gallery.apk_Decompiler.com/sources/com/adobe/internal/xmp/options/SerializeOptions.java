package com.adobe.internal.xmp.options;

import com.adobe.internal.xmp.XMPException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SerializeOptions extends Options {
    public static final int ENCODE_UTF16BE = 2;
    public static final int ENCODE_UTF16LE = 3;
    public static final int ENCODE_UTF8 = 0;
    private static final int ENCODING_MASK = 3;
    public static final int EXACT_PACKET_LENGTH = 512;
    public static final int INCLUDE_THUMBNAIL_PAD = 256;
    private static final int LITTLEENDIAN_BIT = 1;
    public static final int OMIT_PACKET_WRAPPER = 16;
    public static final int OMIT_XMPMETA_ELEMENT = 4096;
    public static final int READONLY_PACKET = 32;
    public static final int SORT = 8192;
    public static final int USE_CANONICAL_FORMAT = 128;
    public static final int USE_COMPACT_FORMAT = 64;
    public static final int USE_PLAIN_XMP = 1024;
    private static final int UTF16_BIT = 2;
    private int baseIndent = 0;
    private String indent = "  ";
    private String newline = "\n";
    private boolean omitVersionAttribute = false;
    private int padding = 2048;

    public SerializeOptions() {
    }

    public Object clone() {
        try {
            SerializeOptions serializeOptions = new SerializeOptions(getOptions());
            serializeOptions.setBaseIndent(this.baseIndent);
            serializeOptions.setIndent(this.indent);
            serializeOptions.setNewline(this.newline);
            serializeOptions.setPadding(this.padding);
            return serializeOptions;
        } catch (XMPException unused) {
            return null;
        }
    }

    public String defineOptionName(int i2) {
        if (i2 == 16) {
            return "OMIT_PACKET_WRAPPER";
        }
        if (i2 == 32) {
            return "READONLY_PACKET";
        }
        if (i2 == 64) {
            return "USE_COMPACT_FORMAT";
        }
        if (i2 == 256) {
            return "INCLUDE_THUMBNAIL_PAD";
        }
        if (i2 == 512) {
            return "EXACT_PACKET_LENGTH";
        }
        if (i2 == 1024) {
            return "USE_PLAIN_XMP";
        }
        if (i2 == 4096) {
            return "OMIT_XMPMETA_ELEMENT";
        }
        if (i2 != 8192) {
            return null;
        }
        return "NORMALIZED";
    }

    public int getBaseIndent() {
        return this.baseIndent;
    }

    public boolean getEncodeUTF16BE() {
        if ((getOptions() & 3) == 2) {
            return true;
        }
        return false;
    }

    public boolean getEncodeUTF16LE() {
        if ((getOptions() & 3) == 3) {
            return true;
        }
        return false;
    }

    public String getEncoding() {
        if (getEncodeUTF16BE()) {
            return "UTF-16BE";
        }
        if (getEncodeUTF16LE()) {
            return "UTF-16LE";
        }
        return "UTF-8";
    }

    public boolean getExactPacketLength() {
        return getOption(512);
    }

    public boolean getIncludeThumbnailPad() {
        return getOption(256);
    }

    public String getIndent() {
        return this.indent;
    }

    public String getNewline() {
        return this.newline;
    }

    public boolean getOmitPacketWrapper() {
        return getOption(16);
    }

    public boolean getOmitVersionAttribute() {
        return this.omitVersionAttribute;
    }

    public boolean getOmitXmpMetaElement() {
        return getOption(4096);
    }

    public int getPadding() {
        return this.padding;
    }

    public boolean getReadOnlyPacket() {
        return getOption(32);
    }

    public boolean getSort() {
        return getOption(SORT);
    }

    public boolean getUseCanonicalFormat() {
        return getOption(128);
    }

    public boolean getUseCompactFormat() {
        return getOption(64);
    }

    public boolean getUsePlainXMP() {
        return getOption(1024);
    }

    public int getValidOptions() {
        return 14192;
    }

    public SerializeOptions setBaseIndent(int i2) {
        this.baseIndent = i2;
        return this;
    }

    public SerializeOptions setEncodeUTF16BE(boolean z) {
        setOption(3, false);
        setOption(2, z);
        return this;
    }

    public SerializeOptions setEncodeUTF16LE(boolean z) {
        setOption(3, false);
        setOption(3, z);
        return this;
    }

    public SerializeOptions setExactPacketLength(boolean z) {
        setOption(512, z);
        return this;
    }

    public SerializeOptions setIncludeThumbnailPad(boolean z) {
        setOption(256, z);
        return this;
    }

    public SerializeOptions setIndent(String str) {
        this.indent = str;
        return this;
    }

    public SerializeOptions setNewline(String str) {
        this.newline = str;
        return this;
    }

    public SerializeOptions setOmitPacketWrapper(boolean z) {
        setOption(16, z);
        return this;
    }

    public SerializeOptions setOmitXmpMetaElement(boolean z) {
        setOption(4096, z);
        return this;
    }

    public SerializeOptions setPadding(int i2) {
        this.padding = i2;
        return this;
    }

    public SerializeOptions setReadOnlyPacket(boolean z) {
        setOption(32, z);
        return this;
    }

    public SerializeOptions setSort(boolean z) {
        setOption(SORT, z);
        return this;
    }

    public SerializeOptions setUseCanonicalFormat(boolean z) {
        setOption(128, z);
        return this;
    }

    public SerializeOptions setUseCompactFormat(boolean z) {
        setOption(64, z);
        return this;
    }

    public SerializeOptions setUsePlainXMP(boolean z) {
        setOption(1024, z);
        return this;
    }

    public SerializeOptions(int i2) {
        super(i2);
    }
}
