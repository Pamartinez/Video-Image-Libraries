package com.samsung.android.motionphoto.utils.v2.video;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.functional.Operator;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Operator {
    public final /* synthetic */ ArrayList d;
    public final /* synthetic */ VideoTranscoder e;
    public final /* synthetic */ VideoTranscodingTask f;
    public final /* synthetic */ float g;

    public /* synthetic */ d(ArrayList arrayList, VideoTranscoder videoTranscoder, VideoTranscodingTask videoTranscodingTask, float f5) {
        this.d = arrayList;
        this.e = videoTranscoder;
        this.f = videoTranscodingTask;
        this.g = f5;
    }

    public final MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return VideoTranscoder.makeConverter$lambda$6(this.d, this.e, this.f, this.g, mediaBuffer, mutableMediaBuffer);
    }
}
