package q5;

import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureNamePresenter e;

    public /* synthetic */ d(EditCreatureNamePresenter editCreatureNamePresenter, int i2) {
        this.d = i2;
        this.e = editCreatureNamePresenter;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        EditCreatureNamePresenter editCreatureNamePresenter = this.e;
        switch (i2) {
            case 0:
                editCreatureNamePresenter.lambda$initContactLink$0(obj);
                return;
            case 1:
                editCreatureNamePresenter.publishData((CreatureNameData) obj);
                return;
            default:
                editCreatureNamePresenter.lambda$loadFaceImage$19((MediaItem) obj);
                return;
        }
    }
}
