package gc;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode.TextActionModeCallback;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItemViewHolder;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                ((DebugSmartCropRectInfo) this.e).lambda$initThumbnail$4((ImageView) this.f, (Context) this.g, view);
                return;
            case 1:
                ((DrawerTabViewAdapter) this.e).lambda$setOnItemClickListener$5((DrawerTabItem) this.f, (DrawerTabItemViewHolder) this.g, view);
                return;
            default:
                TextActionModeCallback.createIntentOnClickListener$lambda$6((PendingIntent) this.e, (TextActionModeCallback) this.f, (Intent) this.g, view);
                return;
        }
    }
}
