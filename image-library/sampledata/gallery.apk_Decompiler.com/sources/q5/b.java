package q5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureNameFragment e;

    public /* synthetic */ b(EditCreatureNameFragment editCreatureNameFragment, int i2) {
        this.d = i2;
        this.e = editCreatureNameFragment;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        EditCreatureNameFragment editCreatureNameFragment = this.e;
        switch (i2) {
            case 0:
                editCreatureNameFragment.lambda$setRelationDeleteIconClickListener$7(view);
                return;
            case 1:
                editCreatureNameFragment.lambda$setRelationshipViewTouchAndOnClickListener$5(view);
                return;
            case 2:
                editCreatureNameFragment.lambda$updateContactLinkView$9(view);
                return;
            default:
                editCreatureNameFragment.lambda$setChangeCoverView$8(view);
                return;
        }
    }
}
