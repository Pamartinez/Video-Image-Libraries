package com.samsung.android.sum.core.format;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface UpdatableMediaFormat extends MediaFormat {
    public static final String UPDATE_AT_ALLOC = "update-at-alloc";

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface UpdatableAttribute {
    }

    static UpdatableMediaFormat of(MediaFormat mediaFormat) {
        return new StapleUpdatableMediaFormat(mediaFormat);
    }

    Shape getCroppedShape();

    UpdatableMediaFormat set(String str);

    UpdatableMediaFormat setUpdater(BiConsumer<MediaFormat, MutableMediaFormat> biConsumer);

    MediaFormat update();

    UpdatableMediaFormat with(MediaFormat mediaFormat);
}
