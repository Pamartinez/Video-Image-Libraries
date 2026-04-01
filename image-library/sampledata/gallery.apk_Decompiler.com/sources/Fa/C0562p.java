package Fa;

import com.samsung.android.gallery.app.controller.album.ChangeAlbumLevelCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* renamed from: Fa.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0562p implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2812a;
    public final /* synthetic */ ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ArrayList f2813c;

    public /* synthetic */ C0562p(ArrayList arrayList, ArrayList arrayList2, int i2) {
        this.f2812a = i2;
        this.b = arrayList;
        this.f2813c = arrayList2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2812a) {
            case 0:
                LabsDevManageFragment.lambda$buildGalleryPreferenceValues$53(this.b, this.f2813c, (String) obj, obj2);
                return;
            default:
                ChangeAlbumLevelCmd.lambda$changeAlbumLevel$2(this.b, this.f2813c, (MediaItem) obj, (Integer) obj2);
                return;
        }
    }
}
