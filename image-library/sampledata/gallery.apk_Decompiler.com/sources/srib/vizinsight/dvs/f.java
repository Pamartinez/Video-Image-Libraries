package srib.vizinsight.dvs;

import android.util.Size;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements SemAsyncVideoFrameDecoder.OnDecodingCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SegmentAsyncTask f5128a;
    public final /* synthetic */ int[] b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ArrayList f5129c;
    public final /* synthetic */ CountDownLatch d;
    public final /* synthetic */ CountDownLatch e;
    public final /* synthetic */ Size f;
    public final /* synthetic */ long g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Stack f5130h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ ArrayList f5131i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Stack f5132j;
    public final /* synthetic */ File k;
    public final /* synthetic */ boolean l;
    public final /* synthetic */ MotionPhotoVideoUtils.VideoInfo m;

    public /* synthetic */ f(SegmentAsyncTask segmentAsyncTask, int[] iArr, ArrayList arrayList, CountDownLatch countDownLatch, CountDownLatch countDownLatch2, Size size, long j2, Stack stack, ArrayList arrayList2, Stack stack2, File file, boolean z, MotionPhotoVideoUtils.VideoInfo videoInfo) {
        this.f5128a = segmentAsyncTask;
        this.b = iArr;
        this.f5129c = arrayList;
        this.d = countDownLatch;
        this.e = countDownLatch2;
        this.f = size;
        this.g = j2;
        this.f5130h = stack;
        this.f5131i = arrayList2;
        this.f5132j = stack2;
        this.k = file;
        this.l = z;
        this.m = videoInfo;
    }

    public final void onDecodingCompleted(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, int i2) {
        SegmentAsyncTask segmentAsyncTask = this.f5128a;
        SegmentAsyncTask segmentAsyncTask2 = segmentAsyncTask;
        SegmentAsyncTask segmentAsyncTask3 = segmentAsyncTask2;
        SegmentAsyncTask segmentAsyncTask4 = segmentAsyncTask3;
        SegmentAsyncTask segmentAsyncTask5 = segmentAsyncTask4;
        SegmentAsyncTask segmentAsyncTask6 = segmentAsyncTask5;
        SegmentAsyncTask segmentAsyncTask7 = segmentAsyncTask6;
        SegmentAsyncTask segmentAsyncTask8 = segmentAsyncTask7;
        SegmentAsyncTask segmentAsyncTask9 = segmentAsyncTask8;
        SegmentAsyncTask segmentAsyncTask10 = segmentAsyncTask9;
        SegmentAsyncTask segmentAsyncTask11 = segmentAsyncTask10;
        SegmentAsyncTask segmentAsyncTask12 = segmentAsyncTask11;
        segmentAsyncTask12.lambda$doInBackground$8(this.b, this.f5129c, this.d, this.e, this.f, this.g, this.f5130h, this.f5131i, this.f5132j, this.k, this.l, this.m, semAsyncVideoFrameDecoder, i2);
    }
}
