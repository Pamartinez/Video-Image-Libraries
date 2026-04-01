package Ab;

import android.view.View;
import android.widget.AdapterView;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;
import com.samsung.android.gallery.image360.widget.Image360FastOptionMoreMenu;
import com.samsung.android.gallery.widget.fastoption2.FastOptionMoreMenu;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements AdapterView.OnItemClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        int i7 = this.d;
        Object obj = this.e;
        switch (i7) {
            case 0:
                ((FastOptionMoreMenu) obj).lambda$initListPopupWindow$0(adapterView, view, i2, j2);
                return;
            case 1:
                ((EditCreatureNameFragment) obj).lambda$createEditTextView$0(adapterView, view, i2, j2);
                return;
            default:
                ((Image360FastOptionMoreMenu) obj).onListItemClick(adapterView, view, i2, j2);
                return;
        }
    }
}
