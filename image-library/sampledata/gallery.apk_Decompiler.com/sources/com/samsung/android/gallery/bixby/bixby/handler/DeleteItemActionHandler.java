package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteItemActionHandler extends GalleryActionHandler {
    private boolean operateDelete(TrashDeleteHelper trashDeleteHelper, long j2) {
        try {
            trashDeleteHelper.deleteItems(new long[]{j2}, true);
            trashDeleteHelper.done();
        } catch (Exception e) {
            Log.bxe(this.TAG, "unable to delete by bixby");
            e.printStackTrace();
        }
        return trashDeleteHelper.isSucceed();
    }

    public boolean isSupported() {
        return "DELETE_ITEM".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        String str;
        String str2 = this.TAG;
        Log.bx(str2, "found blackboard to start ACTION_DELETE_ITEM [" + blackboard.getName() + "]");
        long j2 = UnsafeCast.toLong(getValue("KEY_CONTENT_ID"), 0);
        if (j2 == 0) {
            Log.bxe(this.TAG, "delete item failed, invalid id={0}");
            return;
        }
        boolean operateDelete = operateDelete(TrashHelperFactory.getDeleteHelper(context), j2);
        String str3 = this.TAG;
        Log.bx(str3, "delete result [" + operateDelete + "]");
        if (operateDelete) {
            str = "success";
        } else {
            str = "fail";
        }
        sendCallback(getResultString(str));
    }
}
