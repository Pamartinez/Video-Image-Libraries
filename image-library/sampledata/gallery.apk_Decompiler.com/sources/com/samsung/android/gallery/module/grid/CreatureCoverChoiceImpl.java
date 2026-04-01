package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCoverChoiceImpl extends GridHelper {
    public CreatureCoverChoiceImpl() {
        super("location://search/CreatureCoverChoice");
    }

    public int[] buildSpanInfo() {
        return new int[]{1};
    }

    public int getDefaultDepth() {
        return 0;
    }

    public int getGridArrayResource() {
        return R$array.creature_cover_choice_column_count;
    }

    public int getGridDepth() {
        return getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return null;
    }
}
