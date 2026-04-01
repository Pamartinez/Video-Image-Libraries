package srib.vizinsight.dvs;

import android.util.Size;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements SemAsyncVideoFrameDecoder.OnInitCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SegmentAsyncTask f5133a;
    public final /* synthetic */ ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Size f5134c;
    public final /* synthetic */ long d;

    public /* synthetic */ g(SegmentAsyncTask segmentAsyncTask, ArrayList arrayList, Size size, long j2) {
        this.f5133a = segmentAsyncTask;
        this.b = arrayList;
        this.f5134c = size;
        this.d = j2;
    }

    public final void onInitCompleted(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        this.f5133a.lambda$doInBackground$4(this.b, this.f5134c, this.d, semAsyncVideoFrameDecoder);
    }
}
