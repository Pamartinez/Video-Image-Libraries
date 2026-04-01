package srib.vizinsight.dvs;

import android.graphics.Bitmap;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import java.util.concurrent.LinkedBlockingDeque;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements SemAsyncVideoFrameDecoder.OnVideoFrameListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LinkedBlockingDeque f5127a;
    public final /* synthetic */ long b;

    public /* synthetic */ e(LinkedBlockingDeque linkedBlockingDeque, long j2) {
        this.f5127a = linkedBlockingDeque;
        this.b = j2;
    }

    public final void onVideoFrame(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, Bitmap bitmap, int i2, int i7) {
        SegmentAsyncTask.lambda$doInBackground$1(this.f5127a, this.b, semAsyncVideoFrameDecoder, bitmap, i2, i7);
    }
}
