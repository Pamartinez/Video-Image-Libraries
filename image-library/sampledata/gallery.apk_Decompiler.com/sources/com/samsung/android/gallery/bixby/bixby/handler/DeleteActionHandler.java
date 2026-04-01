package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.bixby2.Sbixby;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteActionHandler extends GalleryActionHandler {
    private void handleExecutable(Context context, Blackboard blackboard, FileItemInterface fileItemInterface, String str) {
        boolean z;
        String str2;
        Log.bx(this.TAG, "delete start");
        boolean containsTrash = LocationKey.containsTrash(str);
        if (containsTrash) {
            z = operateEmpty(TrashHelperFactory.getEmptyHelper(context, false), fileItemInterface);
        } else {
            z = operateDelete(TrashHelperFactory.getDeleteHelper(context), fileItemInterface);
        }
        String str3 = this.TAG;
        Log.bx(str3, "delete result [" + z + "]");
        if (z) {
            str2 = "success";
        } else {
            str2 = "fail";
        }
        sendCallback(getResultString(str2));
        if (z) {
            blackboard.postEvent(EventMessage.obtain(3015));
            if (containsTrash) {
                Blackboard.postBroadcastEventGlobal(EventMessage.obtain(1029, 1, 0, (Object) null));
            } else if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                blackboard.publish("viewer_force_refresh", Boolean.TRUE);
            } else {
                BlackboardUtils.forceRefreshPicturesData(blackboard, false);
            }
        }
    }

    private boolean operateDelete(TrashDeleteHelper trashDeleteHelper, FileItemInterface fileItemInterface) {
        try {
            trashDeleteHelper.deleteItem(fileItemInterface);
            trashDeleteHelper.done();
        } catch (Exception e) {
            Log.bxe(this.TAG, "unable to delete by bixby");
            e.printStackTrace();
        }
        return trashDeleteHelper.isSucceed();
    }

    private boolean operateEmpty(TrashEmptyHelper trashEmptyHelper, FileItemInterface fileItemInterface) {
        try {
            trashEmptyHelper.emptyItem(fileItemInterface);
            trashEmptyHelper.done();
        } catch (Exception e) {
            Log.bxe(this.TAG, "unable to empty by bixby");
            e.printStackTrace();
        }
        return trashEmptyHelper.isSucceed();
    }

    public boolean isSupported() {
        return "DETAIL_VIEW_DELETE".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        String str = this.TAG;
        Log.bx(str, "found blackboard to start ACTION_VIEWER_DELETE [" + blackboard.getName() + "]");
        FileItemInterface fileItemInterface = (FileItemInterface) blackboard.pop("data://bixby_mediaItem");
        String str2 = (String) blackboard.read("location://variable/currentv1");
        if (isExecutableInViewer(fileItemInterface, str2, Sbixby.getStateHandler().getAppState(context, (Bundle) null), blackboard.getName())) {
            handleExecutable(context, blackboard, fileItemInterface, str2);
        }
    }
}
