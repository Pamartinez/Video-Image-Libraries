package m3;

import android.view.View;
import android.widget.LinearLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ExpandableCapsuleLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ExpandableCapsuleLayout e;
    public final /* synthetic */ LinearLayout f;
    public final /* synthetic */ ExpandableCapsule g;

    public /* synthetic */ b(ExpandableCapsuleLayout expandableCapsuleLayout, LinearLayout linearLayout, ExpandableCapsule expandableCapsule, int i2) {
        this.d = i2;
        this.e = expandableCapsuleLayout;
        this.f = linearLayout;
        this.g = expandableCapsule;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                ExpandableCapsuleLayout.bindSingleItemView$lambda$9$lambda$8(this.e, this.f, this.g, view);
                return;
            default:
                ExpandableCapsuleLayout.bindMultipleItemView$lambda$15$lambda$14(this.e, this.f, this.g, view);
                return;
        }
    }
}
