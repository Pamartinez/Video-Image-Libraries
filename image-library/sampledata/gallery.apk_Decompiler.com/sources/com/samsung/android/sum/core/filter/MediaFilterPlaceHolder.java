package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.functional.PlaceHolder;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaFilterPlaceHolder extends MediaFilterBase implements PlaceHolder<MediaFilter> {
    private static final String TAG = Def.tagOf((Class<?>) MediaFilterPlaceHolder.class);
    private final MFDescriptor descriptor;
    private MediaFilter mediaFilter;
    private Supplier<MediaFilter> mediaFilterProvider;
    private MediaFilterRetriever mediaFilterRetriever;
    private final List<Consumer<MediaFilter>> mediaFilterUpdaterList = new LinkedList();
    private WeakReference<MediaFilter> parent;

    public MediaFilterPlaceHolder(MFDescriptor mFDescriptor, Supplier<MediaFilter> supplier) {
        this.descriptor = mFDescriptor;
        this.mediaFilterProvider = supplier;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reset$0(Consumer consumer) {
        consumer.accept(this.mediaFilter);
    }

    public void accept(MediaFilterRetriever mediaFilterRetriever2, MediaFilter mediaFilter2) {
        this.mediaFilterRetriever = mediaFilterRetriever2;
        this.parent = new WeakReference<>(mediaFilter2);
        mediaFilterRetriever2.retrieve(this, mediaFilter2);
    }

    public MediaFilter get() {
        if (this.mediaFilter == null) {
            synchronized (this) {
                try {
                    Supplier<MediaFilter> supplier = this.mediaFilterProvider;
                    if (supplier != null) {
                        this.mediaFilter = supplier.get();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.mediaFilter;
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public boolean isEmpty() {
        if (this.mediaFilter == null) {
            return true;
        }
        return false;
    }

    public boolean isNotEmpty() {
        if (this.mediaFilter != null) {
            return true;
        }
        return false;
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        throw new UnsupportedOperationException("MediaFilterPlaceHolder not support this");
    }

    public void setMediaFilterUpdater(Consumer<MediaFilter> consumer) {
        this.mediaFilterUpdaterList.add(consumer);
    }

    public Stream<MediaFilter> stream() {
        throw new UnsupportedOperationException("MediaFilterPlaceHolder not support this");
    }

    public void put(MediaFilter mediaFilter2) {
        this.mediaFilter = mediaFilter2;
    }

    public MediaFilter reset() {
        if (this.mediaFilter == null) {
            Def.require(this.mediaFilterProvider != null, "duplicated replace call", new Object[0]);
            get();
        }
        this.mediaFilterUpdaterList.forEach(new b(4, this));
        MediaFilterRetriever mediaFilterRetriever2 = this.mediaFilterRetriever;
        if (mediaFilterRetriever2 != null) {
            this.mediaFilter.accept(mediaFilterRetriever2, this.parent.get());
            this.mediaFilterRetriever = null;
            this.parent = null;
        }
        return this.mediaFilter;
    }
}
