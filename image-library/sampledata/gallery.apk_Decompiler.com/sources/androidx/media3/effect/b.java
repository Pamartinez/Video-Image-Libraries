package androidx.media3.effect;

import android.graphics.Bitmap;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements VideoFrameProcessingTaskExecutor.Task {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BitmapTextureManager f999a;
    public final /* synthetic */ Bitmap b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ FrameInfo f1000c;
    public final /* synthetic */ TimestampIterator d;

    public /* synthetic */ b(BitmapTextureManager bitmapTextureManager, Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) {
        this.f999a = bitmapTextureManager;
        this.b = bitmap;
        this.f1000c = frameInfo;
        this.d = timestampIterator;
    }

    public final void run() {
        this.f999a.lambda$queueInputBitmap$1(this.b, this.f1000c, this.d);
    }
}
