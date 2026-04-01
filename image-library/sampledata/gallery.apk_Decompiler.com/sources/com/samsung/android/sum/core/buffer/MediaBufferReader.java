package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.types.MediaType;

@FunctionalInterface
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaBufferReader<T> {
    static MediaBufferReader<?> of(MediaBuffer mediaBuffer) {
        return new RawDataReader(mediaBuffer);
    }

    T get();

    static <V> MediaBufferReader<V> of(MediaBuffer mediaBuffer, Class<V> cls) {
        if (cls == Shape.class) {
            return new B(0, mediaBuffer);
        }
        if (Number.class.isAssignableFrom(cls)) {
            return of(mediaBuffer);
        }
        if (MediaType.class == cls) {
            return new B(1, mediaBuffer);
        }
        throw new UnsupportedOperationException("not supported type");
    }
}
