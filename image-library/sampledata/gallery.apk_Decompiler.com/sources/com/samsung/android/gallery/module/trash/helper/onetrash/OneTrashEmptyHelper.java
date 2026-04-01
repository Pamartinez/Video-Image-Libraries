package com.samsung.android.gallery.module.trash.helper.onetrash;

import N2.j;
import android.content.Context;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneTrashEmptyHelper extends TrashEmptyHelper {
    public OneTrashEmptyHelper(Context context, boolean z) {
        super(context, z);
    }

    public boolean deleteFromTrashDB(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            boolean z = false;
            if (this.mTrashProvider.deleteTrash("_data =? ", new String[]{str}, false) > 0) {
                z = true;
            }
            return z;
        } finally {
            j.m(currentTimeMillis, "]", this.TAG, new StringBuilder("deleted from Trash db ["));
        }
    }

    public String tag() {
        return "OneTrashEmptyHelper";
    }
}
