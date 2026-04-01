package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.format.UpdatableMediaFormat;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaBufferAllocator {
    private static final String TAG = Def.tagOf((Class<?>) MediaBufferAllocator.class);
    protected Align align;
    protected MediaFormat format;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Nothing {
    }

    public MediaBufferAllocator(MediaFormat mediaFormat) {
        this.format = mediaFormat;
        this.align = new Align(mediaFormat.getChannels() * mediaFormat.getCols(), mediaFormat.getRows());
    }

    public static MediaBufferAllocator of(MediaFormat mediaFormat) {
        if (mediaFormat instanceof MutableMediaFormat) {
            SLog.w(TAG, "mutable format converted as immutable");
            mediaFormat = ((MutableMediaFormat) mediaFormat).toMediaFormat();
        }
        if ((mediaFormat instanceof UpdatableMediaFormat) && mediaFormat.contains(UpdatableMediaFormat.UPDATE_AT_ALLOC)) {
            mediaFormat = ((UpdatableMediaFormat) mediaFormat).update();
        }
        return new StapleBufferAllocator(mediaFormat, Align.setByFormat(mediaFormat));
    }

    public abstract MediaBuffer allocate();

    public abstract MediaBuffer allocate(Align align2);

    public abstract MediaBuffer allocateShared();

    public abstract <T> MediaBuffer wrap(T t);

    public MediaBufferAllocator(MediaFormat mediaFormat, Align align2) {
        this.format = mediaFormat;
        this.align = align2;
    }

    public static MediaBufferAllocator of(MediaFormat mediaFormat, Align align2) {
        if (mediaFormat instanceof MutableMediaFormat) {
            SLog.w(TAG, "mutable format converted as immutable");
            mediaFormat = ((MutableMediaFormat) mediaFormat).toMediaFormat();
        }
        if (align2.getDimension() == 0) {
            align2 = Align.setByFormat(mediaFormat);
            align2.adjustAlign();
        }
        return new StapleBufferAllocator(mediaFormat, align2);
    }
}
