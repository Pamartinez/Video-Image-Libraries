package Fa;

import G0.e;
import Jd.b;
import android.widget.CompoundButton;
import com.google.android.material.chip.Chip;
import com.samsung.android.gallery.app.ui.dialog.FileOperationDialog;
import com.samsung.android.gallery.app.ui.dialog.switchable.FileOperation;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountSwitchHolder;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.CreatureCoverChoiceFragment;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.SelectModeDelegate;
import com.samsung.android.gallery.settings.ui.SearchCustomViewHolder;
import h2.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class K implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ K(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((SearchCustomViewHolder) obj).onCheckedChanged(compoundButton, z);
                return;
            case 1:
                ((AlbumTitleCountSwitchHolder) obj).onCheckedChanged(compoundButton, z);
                return;
            case 2:
                Chip chip = (Chip) obj;
                g gVar = chip.f1439i;
                if (gVar != null) {
                    b bVar = (b) ((e) gVar).d;
                    if (!z ? bVar.l(chip, bVar.b) : bVar.b(chip)) {
                        bVar.i();
                    }
                }
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener = chip.f1438h;
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(compoundButton, z);
                    return;
                }
                return;
            case 3:
                ((SelectModeDelegate) obj).lambda$initSelectView$2(compoundButton, z);
                return;
            case 4:
                ((CreatureCoverChoiceFragment) obj).lambda$initAutoSelect$3(compoundButton, z);
                return;
            case 5:
                ((FileOperationDialog) obj).onCheckedChanged(compoundButton, z);
                return;
            default:
                ((FileOperation) obj).onCheckedChanged(compoundButton, z);
                return;
        }
    }
}
