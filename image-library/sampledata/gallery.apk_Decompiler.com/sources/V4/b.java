package v4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceAdapter;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RelationshipChoiceAdapter e;
    public final /* synthetic */ RelationshipChoiceViewHolder f;

    public /* synthetic */ b(int i2, RelationshipChoiceAdapter relationshipChoiceAdapter, RelationshipChoiceViewHolder relationshipChoiceViewHolder) {
        this.d = i2;
        this.e = relationshipChoiceAdapter;
        this.f = relationshipChoiceViewHolder;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                this.e.lambda$onCreateViewHolder$4(this.f, view);
                return;
            default:
                this.e.lambda$onCreateViewHolder$5(this.f, view);
                return;
        }
    }
}
