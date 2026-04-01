package srib.vizinsight.dvs;

import android.graphics.Bitmap;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import java.util.Stack;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SemAsyncVideoFrameDecoder.OnVideoFrameListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Stack f5119a;
    public final /* synthetic */ long b;

    public /* synthetic */ b(Stack stack, long j2) {
        this.f5119a = stack;
        this.b = j2;
    }

    public final void onVideoFrame(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, Bitmap bitmap, int i2, int i7) {
        SegmentAsyncTask.lambda$doInBackground$3(this.f5119a, this.b, semAsyncVideoFrameDecoder, bitmap, i2, i7);
    }
}
