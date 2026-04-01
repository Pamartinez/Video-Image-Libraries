package Fa;

import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsDevManageFragment e;

    public /* synthetic */ r(LabsDevManageFragment labsDevManageFragment, int i2) {
        this.d = i2;
        this.e = labsDevManageFragment;
    }

    public final void run() {
        int i2 = this.d;
        LabsDevManageFragment labsDevManageFragment = this.e;
        switch (i2) {
            case 0:
                labsDevManageFragment.lambda$addCategoryPreferenceManager$3();
                return;
            case 1:
                labsDevManageFragment.lambda$addCategoryCacheManager$10();
                return;
            case 2:
                labsDevManageFragment.lambda$addCategoryCacheManager$16();
                return;
            case 3:
                labsDevManageFragment.lambda$addCategoryCacheManager$20();
                return;
            case 4:
                labsDevManageFragment.lambda$addCategoryCacheManager$18();
                return;
            case 5:
                labsDevManageFragment.lambda$addCategoryCacheManager$12();
                return;
            case 6:
                labsDevManageFragment.lambda$addCategoryCacheManager$14();
                return;
            default:
                labsDevManageFragment.lambda$addCategoryCacheManager$22();
                return;
        }
    }
}
