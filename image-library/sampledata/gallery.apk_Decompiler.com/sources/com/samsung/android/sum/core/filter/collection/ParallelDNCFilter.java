package com.samsung.android.sum.core.filter.collection;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferGroup;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.ParallelDescriptor;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.filter.AsyncFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterGroup;
import com.samsung.android.sum.core.filter.o;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ParallelDNCFilter extends ParallelFilter {
    private final BufferChannel inChannel;
    private final BufferChannel outChannel;

    public ParallelDNCFilter(ParallelDescriptor parallelDescriptor, Function<Integer, BufferChannel> function) {
        super(parallelDescriptor, function);
        this.inChannel = function.apply(Integer.MAX_VALUE);
        this.outChannel = function.apply(Integer.MAX_VALUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addFilter$0(MediaFilter mediaFilter) {
        ((AsyncFilter) mediaFilter).addBufferChannels(this.inChannel, this.outChannel);
    }

    public MediaFilterGroup addFilter(List<MediaFilter> list) {
        list.forEach(new b(8, this));
        return super.addFilter(list);
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        Def.require(mediaBuffer instanceof MediaBufferGroup);
        Stream<MediaBuffer> stream = mediaBuffer.stream();
        BufferChannel bufferChannel = this.inChannel;
        Objects.requireNonNull(bufferChannel);
        stream.forEach(new o(1, bufferChannel));
        ArrayList arrayList = new ArrayList();
        while (((long) arrayList.size()) != mediaBuffer.size()) {
            arrayList.add((MediaBuffer) this.outChannel.receive());
        }
        mutableMediaBuffer.put(MediaBuffer.newGroupAlloc().setBuffers(arrayList).allocate());
        return mutableMediaBuffer;
    }
}
