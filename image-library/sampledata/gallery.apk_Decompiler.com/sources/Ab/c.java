package Ab;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements PopupWindow.OnDismissListener {
    public final /* synthetic */ ViewGroup d;
    public final /* synthetic */ View e;

    public /* synthetic */ c(ViewGroup viewGroup, View view) {
        this.d = viewGroup;
        this.e = view;
    }

    public final void onDismiss() {
        ThreadUtil.postOnUiThread(new e(this.d, this.e));
    }
}
