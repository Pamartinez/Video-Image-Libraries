package m3;

import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ExpandableCapsuleLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ExpandableCapsule e;
    public final /* synthetic */ String f;

    public /* synthetic */ a(ExpandableCapsule expandableCapsule, String str, int i2) {
        this.d = i2;
        this.e = expandableCapsule;
        this.f = str;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                ExpandableCapsuleLayout.bindSingleItemView$lambda$7$lambda$5(this.e, this.f, view);
                return;
            default:
                ExpandableCapsuleLayout.bindMultipleItemView$lambda$12$lambda$11$lambda$10(this.e, this.f, view);
                return;
        }
    }
}
