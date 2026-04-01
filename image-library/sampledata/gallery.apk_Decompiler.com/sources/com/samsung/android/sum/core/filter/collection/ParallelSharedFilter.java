package com.samsung.android.sum.core.filter.collection;

import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.ParallelDescriptor;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.filter.AsyncFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterGroup;
import com.samsung.android.sum.core.filter.n;
import com.samsung.android.sum.core.functional.BufferComposer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ParallelSharedFilter extends ParallelFilter {
    List<BufferChannel> inChannels = new ArrayList();
    List<BufferChannel> outChannels = new ArrayList();

    public ParallelSharedFilter(ParallelDescriptor parallelDescriptor, Function<Integer, BufferChannel> function) {
        super(parallelDescriptor, function);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addFilter$0(MediaFilter mediaFilter) {
        BufferChannel apply = this.channelSupplier.apply(Integer.MAX_VALUE);
        BufferChannel apply2 = this.channelSupplier.apply(Integer.MAX_VALUE);
        this.inChannels.add(apply);
        this.outChannels.add(apply2);
        ((AsyncFilter) mediaFilter).addBufferChannels(apply, apply2);
    }

    public MediaFilterGroup addFilter(List<MediaFilter> list) {
        list.forEach(new b(9, this));
        return super.addFilter(list);
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        this.inChannels.forEach(new b(10, mediaBuffer));
        List list = (List) ((Stream) this.outChannels.stream().parallel()).map(new com.samsung.android.sum.core.channel.b(2)).collect(Collectors.toList());
        if (getDescriptor().getFilterOption().isUseExternalBufferComposer()) {
            mutableMediaBuffer.put(((BufferComposer) list.stream().filter(new a(11)).findFirst().map(new n(2)).orElseThrow(new u(4))).compose(list, (MediaFilter.Option) null));
            return mutableMediaBuffer;
        }
        mutableMediaBuffer.put(MediaBuffer.newGroupAlloc().setBuffers(list).allocate());
        return mutableMediaBuffer;
    }
}
