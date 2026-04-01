package Ab;

import android.view.View;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.fastoption2.FastOptionMoreMenu;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements FastOptionItemView.ItemSelectedListener, FastOptionMoreMenu.MoreMenuItemSelectedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FastOptionView e;

    public /* synthetic */ f(FastOptionView fastOptionView, int i2) {
        this.d = i2;
        this.e = fastOptionView;
    }

    public void onItemSelected(int i2, View view) {
        int i7 = this.d;
        FastOptionView fastOptionView = this.e;
        switch (i7) {
            case 0:
                fastOptionView.lambda$new$0(i2, view);
                return;
            default:
                fastOptionView.lambda$addMoreMenuButton$3(i2, view);
                return;
        }
    }
}
