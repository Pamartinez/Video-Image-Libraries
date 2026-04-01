package q5;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;
import com.samsung.android.gallery.module.creature.base.LoadFinishedListener;
import java.util.ArrayList;
import p2.C0262c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Toolbar.OnMenuItemClickListener, LoadFinishedListener, C0262c {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureNameFragment e;

    public /* synthetic */ a(EditCreatureNameFragment editCreatureNameFragment, int i2) {
        this.d = i2;
        this.e = editCreatureNameFragment;
    }

    public void onLoadFinished(ArrayList arrayList) {
        int i2 = this.d;
        EditCreatureNameFragment editCreatureNameFragment = this.e;
        switch (i2) {
            case 1:
                editCreatureNameFragment.updateAutoCompleteData(arrayList);
                return;
            default:
                editCreatureNameFragment.updateTaggedCreatureNameData(arrayList);
                return;
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        int i2 = this.d;
        EditCreatureNameFragment editCreatureNameFragment = this.e;
        switch (i2) {
            case 0:
                return editCreatureNameFragment.onOptionsMenuSelected(menuItem);
            default:
                return editCreatureNameFragment.onOptionsMenuSelected(menuItem);
        }
    }
}
