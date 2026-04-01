package com.samsung.android.gallery.module.trash.expired;

import android.content.Context;
import com.samsung.android.gallery.module.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashExpiredDefault extends TrashExpiredBase {
    public int getDay() {
        return 31;
    }

    public String getNoItemDescription(Context context) {
        return context.getString(R$string.empty_set_description_no_trash_default);
    }

    public String getTrashSelection() {
        return getExpiryTimeCondition(getExpiredTime());
    }
}
