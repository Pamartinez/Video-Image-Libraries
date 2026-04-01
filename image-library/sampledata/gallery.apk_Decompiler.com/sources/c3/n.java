package C3;

import android.view.View;
import com.samsung.android.gallery.app.activity.GalleryPrivateActivity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements View.OnClickListener {
    public final /* synthetic */ int d;

    public /* synthetic */ n(int i2) {
        this.d = i2;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                GalleryPrivateActivity.lambda$onCreate$1(view);
                return;
            case 1:
                Blackboard.postEventGlobal(EventMessage.obtain(8019));
                return;
            default:
                PopupTipBuilder.lambda$checkIgnoreRootViewTouch$2(view);
                return;
        }
    }
}
