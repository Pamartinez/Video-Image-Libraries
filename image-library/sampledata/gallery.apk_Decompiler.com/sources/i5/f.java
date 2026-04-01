package I5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.RelationshipView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RelationshipView e;

    public /* synthetic */ f(RelationshipView relationshipView, int i2) {
        this.d = i2;
        this.e = relationshipView;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        RelationshipView relationshipView = this.e;
        switch (i2) {
            case 0:
                relationshipView.lambda$inflate$3(view);
                return;
            default:
                relationshipView.lambda$inflate$4(view);
                return;
        }
    }
}
