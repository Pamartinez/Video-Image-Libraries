package Fa;

import android.view.View;
import com.samsung.android.gallery.settings.ui.AboutFragment;

/* renamed from: Fa.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0547a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AboutFragment e;

    public /* synthetic */ C0547a(AboutFragment aboutFragment, int i2) {
        this.d = i2;
        this.e = aboutFragment;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        AboutFragment aboutFragment = this.e;
        switch (i2) {
            case 0:
                aboutFragment.onUpdateClicked(view);
                return;
            case 1:
                aboutFragment.lambda$bindViews$0(view);
                return;
            default:
                aboutFragment.lambda$bindViews$1(view);
                return;
        }
    }
}
