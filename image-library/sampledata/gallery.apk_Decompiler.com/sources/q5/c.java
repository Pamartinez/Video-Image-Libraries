package q5;

import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureNameFragment e;

    public /* synthetic */ c(EditCreatureNameFragment editCreatureNameFragment, int i2) {
        this.d = i2;
        this.e = editCreatureNameFragment;
    }

    public final void run() {
        int i2 = this.d;
        EditCreatureNameFragment editCreatureNameFragment = this.e;
        switch (i2) {
            case 0:
                editCreatureNameFragment.lambda$createSuggestionNameView$2();
                return;
            default:
                editCreatureNameFragment.lambda$onResume$10();
                return;
        }
    }
}
