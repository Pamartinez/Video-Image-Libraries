package com.samsung.android.sum.core.filter;

import c4.C0438h;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.message.MessageProducer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaFilterGroupBase extends MediaFilterBase implements MediaFilterGroup {
    protected Function<Integer, BufferChannel> channelSupplier;
    protected List<MediaFilter> filters = new ArrayList();

    public MediaFilterGroup addFilter(List<MediaFilter> list) {
        this.filters.addAll(list);
        return this;
    }

    public void prepare() {
        this.filters.forEach(new C0438h(11));
    }

    public void release() {
        this.filters.forEach(new C0438h(10));
        this.filters.clear();
    }

    public boolean removeFilter(MediaFilter... mediaFilterArr) {
        return this.filters.removeAll(Arrays.asList(mediaFilterArr));
    }

    public boolean replaceFilter(MediaFilter mediaFilter, MediaFilter mediaFilter2) {
        int indexOf = this.filters.indexOf(mediaFilter);
        if (indexOf < 0) {
            return false;
        }
        this.filters.set(indexOf, mediaFilter2);
        return true;
    }

    public void setMessageProducer(MessageProducer messageProducer) {
        this.filters.forEach(new b(3, messageProducer));
    }

    public Stream<MediaFilter> stream() {
        return this.filters.stream();
    }
}
