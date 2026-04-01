package srib.vizinsight.dvs;

import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements SemAsyncVideoFrameDecoder.OnDecodingCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CountDownLatch f5136a;

    public /* synthetic */ i(CountDownLatch countDownLatch) {
        this.f5136a = countDownLatch;
    }

    public final void onDecodingCompleted(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, int i2) {
        SegmentAsyncTask.lambda$doInBackground$6(this.f5136a, semAsyncVideoFrameDecoder, i2);
    }
}
