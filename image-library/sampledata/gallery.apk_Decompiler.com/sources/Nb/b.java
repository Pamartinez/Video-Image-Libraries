package Nb;

import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.photoview.PhotoPreViewDebugDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ PhotoPreView d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;

    public /* synthetic */ b(PhotoPreView photoPreView, float f5, float f8) {
        this.d = photoPreView;
        this.e = f5;
        this.f = f8;
    }

    public final void accept(Object obj) {
        if (obj == null) {
            this.d.lambda$getDisplayMinScale$2(this.e, this.f, (PhotoPreViewDebugDelegate) null);
            return;
        }
        throw new ClassCastException();
    }
}
