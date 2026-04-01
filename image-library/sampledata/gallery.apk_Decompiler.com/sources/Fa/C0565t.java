package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;

/* renamed from: Fa.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0565t implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsDevManageFragment e;

    public /* synthetic */ C0565t(LabsDevManageFragment labsDevManageFragment, int i2) {
        this.d = i2;
        this.e = labsDevManageFragment;
    }

    public final boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        LabsDevManageFragment labsDevManageFragment = this.e;
        switch (i2) {
            case 0:
                return labsDevManageFragment.lambda$addCategoryCacheManager$11(preference);
            case 1:
                return labsDevManageFragment.lambda$addCategoryCacheManager$13(preference);
            case 2:
                return labsDevManageFragment.lambda$addCategoryCacheManager$15(preference);
            case 3:
                return labsDevManageFragment.lambda$addCategoryCacheManager$17(preference);
            case 4:
                return labsDevManageFragment.lambda$addCategoryCacheManager$19(preference);
            case 5:
                return labsDevManageFragment.lambda$addCategoryCacheManager$21(preference);
            case 6:
                return labsDevManageFragment.lambda$addCategoryCacheManager$23(preference);
            case 7:
                return labsDevManageFragment.lambda$addCategoryPreferenceManager$0(preference);
            case 8:
                return labsDevManageFragment.lambda$addCategoryPreferenceManager$2(preference);
            case 9:
                return labsDevManageFragment.lambda$addCategoryPreferenceManager$6(preference);
            case 10:
                return labsDevManageFragment.lambda$addCategoryPreferenceManager$9(preference);
            case 11:
                return labsDevManageFragment.lambda$addCategoryBackupAndRestore$25(preference);
            case 12:
                return labsDevManageFragment.lambda$addCategoryBackupAndRestore$27(preference);
            case 13:
                return labsDevManageFragment.lambda$addCategoryStatus$29(preference);
            case 14:
                return labsDevManageFragment.lambda$addCategoryStatus$33(preference);
            case 15:
                return labsDevManageFragment.lambda$addCategoryStatus$35(preference);
            case 16:
                return labsDevManageFragment.lambda$addCategoryStatus$37(preference);
            default:
                return labsDevManageFragment.lambda$addCategoryStatus$39(preference);
        }
    }
}
