package q5;

import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureNamePresenter e;
    public final /* synthetic */ CreatureNameData f;

    public /* synthetic */ h(EditCreatureNamePresenter editCreatureNamePresenter, CreatureNameData creatureNameData, int i2) {
        this.d = i2;
        this.e = editCreatureNamePresenter;
        this.f = creatureNameData;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$checkContactLinked$15(this.f);
                return;
            default:
                this.e.lambda$checkContactLinked$14(this.f);
                return;
        }
    }
}
