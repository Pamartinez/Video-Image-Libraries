package com.samsung.android.gallery.app.controller.delegate;

import android.content.Context;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedContentsPickChecker extends PickerSelectableChecker {
    public SharedContentsPickChecker(Blackboard blackboard) {
        super(blackboard);
    }

    public void showExceedMaxCountToast(Context context) {
        Utils.showToast(context, context.getResources().getQuantityString(R.plurals.cant_add_more_than_n_items_at_once, getMaxCount(), new Object[]{Integer.valueOf(getMaxCount())}));
    }
}
