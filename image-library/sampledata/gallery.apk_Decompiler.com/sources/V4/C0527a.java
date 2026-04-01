package v4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceAdapter;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceViewHolder;

/* renamed from: v4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0527a implements View.OnClickListener {
    public final /* synthetic */ RelationshipChoiceAdapter d;
    public final /* synthetic */ int e;
    public final /* synthetic */ RelationshipChoiceViewHolder f;

    public /* synthetic */ C0527a(int i2, RelationshipChoiceAdapter relationshipChoiceAdapter, RelationshipChoiceViewHolder relationshipChoiceViewHolder) {
        this.d = relationshipChoiceAdapter;
        this.e = i2;
        this.f = relationshipChoiceViewHolder;
    }

    public final void onClick(View view) {
        this.d.lambda$onCreateViewHolder$3(this.e, this.f, view);
    }
}
