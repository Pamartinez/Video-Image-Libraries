package Ta;

import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.v11.media.Sem120MediaCaptureCompat;
import com.samsung.android.media.mediacapture.SemMediaCapture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SemMediaCapture.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Sem120MediaCaptureCompat f2878a;
    public final /* synthetic */ MediaCaptureCompat.OnErrorListener b;

    public /* synthetic */ b(Sem120MediaCaptureCompat sem120MediaCaptureCompat, MediaCaptureCompat.OnErrorListener onErrorListener) {
        this.f2878a = sem120MediaCaptureCompat;
        this.b = onErrorListener;
    }

    public final boolean onError(SemMediaCapture semMediaCapture, int i2, int i7) {
        return this.f2878a.lambda$setOnErrorListener$2(this.b, semMediaCapture, i2, i7);
    }
}
