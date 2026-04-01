package n5;

import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryFragment;

/* renamed from: n5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0492a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreatureCategoryFragment e;

    public /* synthetic */ C0492a(CreatureCategoryFragment creatureCategoryFragment, int i2) {
        this.d = i2;
        this.e = creatureCategoryFragment;
    }

    public final void run() {
        int i2 = this.d;
        CreatureCategoryFragment creatureCategoryFragment = this.e;
        switch (i2) {
            case 0:
                creatureCategoryFragment.lambda$editTop5$2();
                return;
            default:
                creatureCategoryFragment.invalidateOptionsMenu();
                return;
        }
    }
}
