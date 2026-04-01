package c2;

import G0.e;
import Jd.b;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.SeslChipGroup;
import h2.g;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements ViewGroup.OnHierarchyChangeListener {
    public ViewGroup.OnHierarchyChangeListener d;
    public final /* synthetic */ SeslChipGroup e;

    public i(SeslChipGroup seslChipGroup) {
        this.e = seslChipGroup;
    }

    public final void onChildViewAdded(View view, View view2) {
        SeslChipGroup seslChipGroup = this.e;
        if (view == seslChipGroup && (view2 instanceof Chip)) {
            if (view2.getId() == -1) {
                view2.setId(ViewCompat.generateViewId());
            }
            b bVar = seslChipGroup.f1095j;
            Chip chip = (Chip) view2;
            ((HashMap) bVar.f3474c).put(Integer.valueOf(chip.getId()), chip);
            if (chip.isChecked()) {
                bVar.b(chip);
            }
            chip.setInternalOnCheckedChangeListener(new e((Object) bVar));
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.d;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }

    public final void onChildViewRemoved(View view, View view2) {
        SeslChipGroup seslChipGroup = this.e;
        if (view == seslChipGroup && (view2 instanceof Chip)) {
            b bVar = seslChipGroup.f1095j;
            Chip chip = (Chip) view2;
            bVar.getClass();
            chip.setInternalOnCheckedChangeListener((g) null);
            ((HashMap) bVar.f3474c).remove(Integer.valueOf(chip.getId()));
            ((HashSet) bVar.d).remove(Integer.valueOf(chip.getId()));
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.d;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
