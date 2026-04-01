package srib.vizinsight.dvs;

import android.util.Size;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements SemAsyncVideoFrameDecoder.OnDecodingCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SegmentAsyncTask f5120a;
    public final /* synthetic */ CountDownLatch b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ArrayList f5121c;
    public final /* synthetic */ CountDownLatch d;
    public final /* synthetic */ Size e;
    public final /* synthetic */ long f;
    public final /* synthetic */ Stack g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ File f5122h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ boolean f5123i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ MotionPhotoVideoUtils.VideoInfo f5124j;

    public /* synthetic */ c(SegmentAsyncTask segmentAsyncTask, CountDownLatch countDownLatch, ArrayList arrayList, CountDownLatch countDownLatch2, Size size, long j2, Stack stack, File file, boolean z, MotionPhotoVideoUtils.VideoInfo videoInfo) {
        this.f5120a = segmentAsyncTask;
        this.b = countDownLatch;
        this.f5121c = arrayList;
        this.d = countDownLatch2;
        this.e = size;
        this.f = j2;
        this.g = stack;
        this.f5122h = file;
        this.f5123i = z;
        this.f5124j = videoInfo;
    }

    public final void onDecodingCompleted(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, int i2) {
        this.f5120a.lambda$doInBackground$7(this.b, this.f5121c, this.d, this.e, this.f, this.g, this.f5122h, this.f5123i, this.f5124j, semAsyncVideoFrameDecoder, i2);
    }
}
