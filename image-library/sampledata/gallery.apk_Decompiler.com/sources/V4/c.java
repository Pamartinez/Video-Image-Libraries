package v4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceDialogPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RelationshipChoiceDialogPresenter e;

    public /* synthetic */ c(RelationshipChoiceDialogPresenter relationshipChoiceDialogPresenter, int i2) {
        this.d = i2;
        this.e = relationshipChoiceDialogPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        RelationshipChoiceDialogPresenter relationshipChoiceDialogPresenter = this.e;
        switch (i2) {
            case 0:
                relationshipChoiceDialogPresenter.addCustomRelationship(obj, bundle);
                return;
            default:
                relationshipChoiceDialogPresenter.renameCustomRelationship(obj, bundle);
                return;
        }
    }
}
