package Fa;

import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import java.util.function.BiConsumer;

/* renamed from: Fa.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0564s implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2815a;
    public final /* synthetic */ LabsDevManageFragment b;

    public /* synthetic */ C0564s(LabsDevManageFragment labsDevManageFragment, int i2) {
        this.f2815a = i2;
        this.b = labsDevManageFragment;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2815a;
        String str = (String) obj;
        String str2 = (String) obj2;
        LabsDevManageFragment labsDevManageFragment = this.b;
        switch (i2) {
            case 0:
                labsDevManageFragment.lambda$loadCacheInfo$59(str, str2);
                return;
            default:
                labsDevManageFragment.lambda$addCategoryPreferenceManager$8(str, str2);
                return;
        }
    }
}
