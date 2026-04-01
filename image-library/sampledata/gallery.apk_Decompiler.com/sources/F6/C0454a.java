package f6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleViewHolder;

/* renamed from: f6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0454a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ HideRuleViewHolder e;

    public /* synthetic */ C0454a(HideRuleViewHolder hideRuleViewHolder, int i2) {
        this.d = i2;
        this.e = hideRuleViewHolder;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        HideRuleViewHolder hideRuleViewHolder = this.e;
        switch (i2) {
            case 0:
                hideRuleViewHolder.onClickAddHideRule(view);
                return;
            default:
                hideRuleViewHolder.onClickExecuteFacePicker(view);
                return;
        }
    }
}
