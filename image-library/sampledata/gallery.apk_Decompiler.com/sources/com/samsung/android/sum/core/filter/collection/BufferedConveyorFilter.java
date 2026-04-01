package com.samsung.android.sum.core.filter.collection;

import c0.C0086a;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.SequentialDescriptor;
import com.samsung.android.sum.core.filter.AsyncFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterGroup;
import com.samsung.android.sum.core.filter.MediaInputStreamFilter;
import com.samsung.android.sum.core.filter.MediaOutputStreamFilter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BufferedConveyorFilter extends SequentialFilter {
    private static final String TAG = SLog.tagOf(BufferedConveyorFilter.class);
    private final AtomicBoolean done = new AtomicBoolean(false);
    private BufferChannel inputChannel;
    private BufferChannel outputChannel;

    public BufferedConveyorFilter(SequentialDescriptor sequentialDescriptor) {
        super(sequentialDescriptor);
        String str = TAG;
        SLog.d(str, "BufferedConveyorFilter: desc=" + sequentialDescriptor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BufferChannel lambda$addFilter$0(Enum enumR) {
        return this.inputChannel;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BufferChannel lambda$addFilter$3(Enum enumR) {
        return this.outputChannel;
    }

    public MediaFilterGroup addFilter(List<MediaFilter> list) {
        String str = TAG;
        SLog.d(str, "addFilter: descriptor=" + this.descriptor + ", filters=" + list);
        if (this.descriptor.getInputChannel() != null) {
            this.inputChannel = this.descriptor.getInputChannel();
            ((AsyncFilter) list.get(0)).setInputChannel(this.inputChannel);
        } else {
            this.inputChannel = ((AsyncFilter) list.get(0)).getInputChannel();
        }
        if (this.descriptor.getOutputChannel() != null) {
            this.outputChannel = this.descriptor.getOutputChannel();
            SLog.d(str, "set external output channel: " + this.outputChannel);
            ((AsyncFilter) list.get(list.size() - 1)).setOutputChannel(this.outputChannel);
        } else {
            this.outputChannel = ((AsyncFilter) C0086a.f(1, list)).getOutputChannel();
        }
        SLog.d(str, "in-channel=" + this.inputChannel + ", out-channel=" + this.outputChannel);
        Iterator<MediaFilter> it = list.iterator();
        AsyncFilter asyncFilter = (AsyncFilter) it.next();
        MediaFilter successorFilter = asyncFilter.getSuccessorFilter();
        if (successorFilter instanceof MediaInputStreamFilter) {
            ((MediaInputStreamFilter) successorFilter).configInputChannel(new a(this, 0), 1);
        }
        AsyncFilter asyncFilter2 = null;
        while (it.hasNext()) {
            AsyncFilter asyncFilter3 = (AsyncFilter) it.next();
            if (asyncFilter2 != null && (asyncFilter instanceof MediaInputStreamFilter)) {
                ((MediaInputStreamFilter) asyncFilter).configInputChannel(new b(asyncFilter2, 0), 1);
            }
            if (asyncFilter.getSuccessorFilter() instanceof MediaOutputStreamFilter) {
                ((MediaOutputStreamFilter) asyncFilter.getSuccessorFilter()).configOutputChannel(new b(asyncFilter3, 1), 1);
            }
            asyncFilter2 = asyncFilter;
            asyncFilter = asyncFilter3;
            successorFilter = asyncFilter3.getSuccessorFilter();
        }
        if (successorFilter instanceof MediaOutputStreamFilter) {
            ((MediaOutputStreamFilter) successorFilter).configOutputChannel(new a(this, 1), 1);
        }
        return super.addFilter(list);
    }

    public void release() {
        this.done.set(true);
        super.release();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        this.inputChannel.send(mediaBuffer);
        return mutableMediaBuffer;
    }
}
