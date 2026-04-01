package com.samsung.android.sum.core.types;

import B8.b;
import com.samsung.android.motionphoto.utils.v2.i;
import com.samsung.android.sum.core.Def;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MediaType implements NumericEnum {
    NONE(0),
    IMAGE(1),
    AUDIO(2),
    VIDEO(3),
    META(4),
    SCALA(5),
    _MAX_RANK_(16),
    RAW_IMAGE(typeValueOf(r0, 0)),
    RAW_AUDIO(typeValueOf(r1, 0)),
    RAW_VIDEO(typeValueOf(r4, 0)),
    COMPRESSED_IMAGE(typeValueOf(r0, 1)),
    COMPRESSED_AUDIO(typeValueOf(r1, 1)),
    COMPRESSED_VIDEO(typeValueOf(r4, 1)),
    VDIS_META(typeValueOf(r6, 2));
    
    public static final int FLAG_COMPRESSED = 1;
    private static final int MT_FLAG_SHIFT = 4;
    private static final int MT_RANK_MASK = 15;
    private static final int MT_RANK_MAX = 16;
    private final int value;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface Flag {
    }

    private MediaType(int i2) {
        this.value = i2;
    }

    private static MediaType of(int i2) {
        return (MediaType) Arrays.stream(values()).filter(new b(i2, 20)).findFirst().orElseThrow(new i(i2, 2));
    }

    private static int typeValueOf(MediaType mediaType, int i2) {
        boolean z;
        if (mediaType.getValue() <= _MAX_RANK_.getValue()) {
            z = true;
        } else {
            z = false;
        }
        Def.require(z, "1st argument is not depth: " + mediaType.getValue(), new Object[0]);
        return (mediaType.getValue() & 15) + (i2 << 4);
    }

    public int flag() {
        return this.value >> 4;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isAudio() {
        if (rank() == AUDIO) {
            return true;
        }
        return false;
    }

    public boolean isCompressed() {
        if ((flag() & 1) != 0) {
            return true;
        }
        return false;
    }

    public boolean isImage() {
        if (rank() == IMAGE) {
            return true;
        }
        return false;
    }

    public boolean isMetaData() {
        if (rank() == META) {
            return true;
        }
        return false;
    }

    public boolean isRaw() {
        if ((flag() & 1) == 0) {
            return true;
        }
        return false;
    }

    public boolean isScala() {
        if (rank() == SCALA) {
            return true;
        }
        return false;
    }

    public boolean isVideo() {
        if (rank() == VIDEO) {
            return true;
        }
        return false;
    }

    public MediaType rank() {
        return of(this.value & 15);
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }

    public static MediaType of(MediaType mediaType, int i2) {
        return of(typeValueOf(mediaType, i2));
    }
}
