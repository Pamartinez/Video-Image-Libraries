package srib.vizinsight.dvs;

import android.util.Size;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SemAsyncVideoFrameDecoder.OnInitCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SegmentAsyncTask f5117a;
    public final /* synthetic */ ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Size f5118c;
    public final /* synthetic */ long d;

    public /* synthetic */ a(SegmentAsyncTask segmentAsyncTask, ArrayList arrayList, Size size, long j2) {
        this.f5117a = segmentAsyncTask;
        this.b = arrayList;
        this.f5118c = size;
        this.d = j2;
    }

    public final void onInitCompleted(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        this.f5117a.lambda$doInBackground$2(this.b, this.f5118c, this.d, semAsyncVideoFrameDecoder);
    }
}
