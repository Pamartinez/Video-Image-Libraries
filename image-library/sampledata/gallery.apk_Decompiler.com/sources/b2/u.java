package B2;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.ListPopupWindow;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements AdapterView.OnItemClickListener {
    public final /* synthetic */ w d;

    public u(w wVar) {
        this.d = wVar;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        Object obj;
        w wVar = this.d;
        ListPopupWindow listPopupWindow = wVar.d;
        if (i2 < 0) {
            obj = listPopupWindow.getSelectedItem();
        } else {
            obj = wVar.getAdapter().getItem(i2);
        }
        w.a(wVar, obj);
        AdapterView.OnItemClickListener onItemClickListener = wVar.getOnItemClickListener();
        if (onItemClickListener != null) {
            if (view == null || i2 < 0) {
                view = listPopupWindow.getSelectedView();
                i2 = listPopupWindow.getSelectedItemPosition();
                j2 = listPopupWindow.getSelectedItemId();
            }
            onItemClickListener.onItemClick(listPopupWindow.getListView(), view, i2, j2);
        }
        listPopupWindow.dismiss();
    }
}
