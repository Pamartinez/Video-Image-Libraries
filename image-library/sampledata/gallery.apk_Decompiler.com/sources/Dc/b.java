package dc;

import android.view.View;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ PopupTipBuilder e;

    public /* synthetic */ b(PopupTipBuilder popupTipBuilder, int i2) {
        this.d = i2;
        this.e = popupTipBuilder;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        PopupTipBuilder popupTipBuilder = this.e;
        switch (i2) {
            case 0:
                popupTipBuilder.lambda$checkViewsDismissWhenClick$3(view);
                return;
            default:
                popupTipBuilder.lambda$checkViewsDismissWhenClick$4(view);
                return;
        }
    }
}
