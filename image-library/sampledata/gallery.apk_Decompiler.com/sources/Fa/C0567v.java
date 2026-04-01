package Fa;

import android.content.DialogInterface;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import java.util.Collection;
import java.util.stream.Collectors;

/* renamed from: Fa.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0567v implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryPreference e;

    public /* synthetic */ C0567v(GalleryPreference galleryPreference, int i2) {
        this.d = i2;
        this.e = galleryPreference;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        GalleryPreference galleryPreference = this.e;
        switch (i7) {
            case 0:
                galleryPreference.clear();
                return;
            case 1:
                galleryPreference.clear();
                return;
            case 2:
                galleryPreference.clear();
                return;
            case 3:
                galleryPreference.removeState((Collection<String>) (Collection) galleryPreference.getAll().keySet().stream().filter(new C0571z(1)).collect(Collectors.toList()));
                return;
            default:
                galleryPreference.clear();
                return;
        }
    }
}
