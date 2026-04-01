package com.samsung.android.sum.core.filter.collection;

import A4.C0383s;
import android.util.Pair;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferGroup;
import com.samsung.android.sum.core.buffer.MediaBufferReader;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.SequentialDescriptor;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.filter.MediaFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SequentialPickerFilter extends SequentialFilter {
    private final List<Pair<Evaluator, MediaFilter>> evaluateFilters = new ArrayList();

    public SequentialPickerFilter(SequentialDescriptor sequentialDescriptor) {
        super(sequentialDescriptor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$0(int i2) {
        MediaFilter mediaFilter = this.filters.get(i2);
        mediaFilter.prepare();
        this.evaluateFilters.add(new Pair(this.descriptor.getEvaluators().get(i2), mediaFilter));
    }

    public void prepare() {
        boolean z;
        if (this.descriptor.getEvaluators().size() == this.filters.size()) {
            z = true;
        } else {
            z = false;
        }
        Def.require(z, "# of evaluator & filter are not matched", new Object[0]);
        IntStream.range(0, this.filters.size()).forEach(new C0383s(2, this));
    }

    public void release() {
        super.release();
        this.evaluateFilters.clear();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        for (Pair next : this.evaluateFilters) {
            if (((Evaluator) next.first).evaluate(MediaBufferReader.of(mediaBuffer, ((Evaluator) next.first).getValueType()).get())) {
                if (!(mediaBuffer instanceof MediaBufferGroup)) {
                    return ((MediaFilter) next.second).run(mediaBuffer, mutableMediaBuffer);
                }
                Stream<MediaBuffer> stream = mediaBuffer.stream();
                MediaFilter mediaFilter = (MediaFilter) next.second;
                Objects.requireNonNull(mediaFilter);
                List list = (List) stream.map(new b(13, mediaFilter)).collect(Collectors.toList());
                if (list.size() == 1) {
                    mutableMediaBuffer.put((MediaBuffer) list.get(0));
                } else {
                    mutableMediaBuffer.put(MediaBuffer.newGroupAlloc().setBuffers(list).allocate());
                }
            }
        }
        return mutableMediaBuffer;
    }
}
