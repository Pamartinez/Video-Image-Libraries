package com.samsung.android.sum.core.filter;

import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferGroup;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SyncFilter extends DecorateFilter {
    private static final String TAG = Def.tagOf((Class<?>) SyncFilter.class);

    public SyncFilter(MediaFilter mediaFilter) {
        super(mediaFilter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$run$0(MediaBuffer mediaBuffer) {
        return super.run(mediaBuffer, MediaBuffer.mutableOf(new Object[0])).reset();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        if (mediaBuffer instanceof MediaBufferGroup) {
            MediaBuffer primaryBuffer = ((MediaBufferGroup) mediaBuffer).getPrimaryBuffer();
            if (primaryBuffer instanceof MediaBufferGroup) {
                primaryBuffer.addExtra(mediaBuffer.getExtra());
            }
        }
        List list = (List) mediaBuffer.stream().map(new b(12, this)).collect(Collectors.toList());
        if (list.size() == 1) {
            mutableMediaBuffer.put((MediaBuffer) list.get(0));
        } else {
            mutableMediaBuffer.put(MediaBuffer.newGroupAlloc().setBuffers(list).allocate());
        }
        mutableMediaBuffer.addExtra(mediaBuffer.getExtra());
        return mutableMediaBuffer;
    }
}
