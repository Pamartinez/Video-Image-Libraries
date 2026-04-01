package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.message.MessageProducer;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DecorateFilter extends MediaFilterBase implements MediaFilter {
    private static final String TAG = Def.tagOf((Class<?>) DecorateFilter.class);
    protected MessageProducer messageProducer;
    protected MediaFilter successor;

    public DecorateFilter(MediaFilter mediaFilter) {
        this.successor = mediaFilter;
    }

    public MFDescriptor getDescriptor() {
        return this.successor.getDescriptor();
    }

    public MediaFilter getEnclosedFilter() {
        MediaFilter mediaFilter = this.successor;
        if (mediaFilter instanceof DecorateFilter) {
            return ((DecorateFilter) mediaFilter).getEnclosedFilter();
        }
        return mediaFilter;
    }

    public MediaFilter getSuccessorFilter() {
        return this.successor;
    }

    public void prepare() {
        try {
            MediaFilter mediaFilter = this.successor;
            if (mediaFilter instanceof PlaceHolder) {
                MediaFilter mediaFilter2 = (MediaFilter) ((PlaceHolder) mediaFilter).reset();
                if (this.successor instanceof PlaceHolder) {
                    this.successor = mediaFilter2;
                }
            }
            this.successor.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void release() {
        this.successor.release();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return this.successor.run(mediaBuffer, mutableMediaBuffer);
    }

    public void setMessageProducer(MessageProducer messageProducer2) {
        this.messageProducer = messageProducer2;
    }

    public void setSuccessorFilter(MediaFilter mediaFilter) {
        this.successor = mediaFilter;
    }

    public Stream<MediaFilter> stream() {
        return this.successor.stream();
    }
}
