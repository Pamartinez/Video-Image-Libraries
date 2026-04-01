package ha;

import android.content.Context;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ Context d;
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;
    public final /* synthetic */ long g;

    public /* synthetic */ b(Context context, int i2, long j2, long j3) {
        this.d = context;
        this.e = i2;
        this.f = j2;
        this.g = j3;
    }

    public final void run() {
        SimilarPhotoHelper.lambda$deleteGroupContents$0(this.d, this.e, this.f, this.g);
    }
}
