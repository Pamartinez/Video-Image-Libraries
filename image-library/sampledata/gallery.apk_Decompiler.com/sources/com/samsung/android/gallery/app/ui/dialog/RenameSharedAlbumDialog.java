package com.samsung.android.gallery.app.ui.dialog;

import android.os.Bundle;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameSharedAlbumDialog extends CreateNameDialog {
    private boolean isDuplicatedName(String str) {
        if (this.mPreviousName.equals(str)) {
            return false;
        }
        clearError();
        return false;
    }

    public String getDataRequestKey() {
        return "data://user/dialog/SharedAlbumRename";
    }

    public String getDefaultName() {
        String str;
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = arguments.getString("name");
        } else {
            str = "";
        }
        this.mOrgName = str;
        return str;
    }

    public int getPositiveButtonResource() {
        return R.string.rename;
    }

    public String getTitle() {
        return getResources().getString(R.string.rename_shared_album);
    }

    public ErrorType isValid(String str) {
        ErrorType errorType;
        if (isDuplicatedName(str)) {
            errorType = ErrorType.ALBUM_NAME_ALREADY_IN_USE;
        } else {
            errorType = ErrorType.NONE;
        }
        if (!errorType.isNone()) {
            return errorType;
        }
        if (isSameOrgName(str)) {
            return ErrorType.FILE_ALREADY_EXIST;
        }
        return ErrorType.NONE;
    }

    public void publishCancel() {
        getBlackboard().post(getDataRequestKey(), (Object) null);
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_VIEW_RENAME_DIALOG_CANCEL);
    }

    public void publishData(String str) {
        getBlackboard().post(getDataRequestKey(), str);
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_VIEW_RENAME_DIALOG_OK);
    }
}
