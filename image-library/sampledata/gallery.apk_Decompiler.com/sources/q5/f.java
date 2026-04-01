package q5;

import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureNamePresenter e;

    public /* synthetic */ f(EditCreatureNamePresenter editCreatureNamePresenter, int i2) {
        this.d = i2;
        this.e = editCreatureNamePresenter;
    }

    public final void run() {
        int i2 = this.d;
        EditCreatureNamePresenter editCreatureNamePresenter = this.e;
        switch (i2) {
            case 0:
                editCreatureNamePresenter.lambda$refreshContactLinkState$7();
                return;
            case 1:
                editCreatureNamePresenter.lambda$onContactLinkAction$8();
                return;
            case 2:
                editCreatureNamePresenter.lambda$checkMeTaggedItemMerge$16();
                return;
            case 3:
                editCreatureNamePresenter.finish();
                return;
            case 4:
                editCreatureNamePresenter.lambda$onPositiveClicked$11();
                return;
            case 5:
                editCreatureNamePresenter.lambda$onPositiveClicked$10();
                return;
            default:
                editCreatureNamePresenter.lambda$loadFaceImage$20();
                return;
        }
    }
}
