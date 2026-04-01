package srib.vizinsight.dvs;

import android.util.Size;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements SemAsyncVideoFrameDecoder.OnInitCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SegmentAsyncTask f5125a;
    public final /* synthetic */ ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Size f5126c;
    public final /* synthetic */ long d;

    public /* synthetic */ d(SegmentAsyncTask segmentAsyncTask, ArrayList arrayList, Size size, long j2) {
        this.f5125a = segmentAsyncTask;
        this.b = arrayList;
        this.f5126c = size;
        this.d = j2;
    }

    public final void onInitCompleted(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        this.f5125a.lambda$doInBackground$0(this.b, this.f5126c, this.d, semAsyncVideoFrameDecoder);
    }
}
