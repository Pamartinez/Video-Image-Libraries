package com.samsung.android.gallery.app.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o6.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateFolderDialog extends CreateNameDialog {
    private boolean mIsSuccess = false;

    private String loadTitle() {
        String string;
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString("error", (String) null)) == null) {
            return null;
        }
        String string2 = arguments.getString("title", (String) null);
        setError(string);
        return string2;
    }

    private void sendFolderErrorEvent() {
        getBlackboard().postEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_NOT_USER_OWNER, new Object[]{Boolean.FALSE}));
    }

    public String getDefaultName() {
        String loadTitle = loadTitle();
        if (loadTitle == null) {
            return getString(R.string.group_default, Integer.valueOf(PreferenceCache.FolderNameIndex.getInt()));
        }
        return loadTitle;
    }

    public String getHint() {
        return getString(R.string.enter_group_name);
    }

    public int getPositiveButtonResource() {
        return R.string.create_shared_album_dialog_positive_button;
    }

    public String getTitle() {
        return getString(R.string.create_album_group);
    }

    public ErrorType isValid(String str) {
        if (AlbumHelper.getInstance().checkDirectoriesFolderExist(str)) {
            return ErrorType.DUPLICATE_GROUP_NAME;
        }
        return ErrorType.NONE;
    }

    public void onDetach() {
        super.onDetach();
        Optional.ofNullable(getBlackboard()).ifPresent(new B(15));
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (!this.mIsSuccess) {
            sendFolderErrorEvent();
        }
    }

    public void publishCancel() {
        sendFolderErrorEvent();
        postAnalyticsLog(AnalyticsEventId.EVENT_MENU_CREATE_GROUP_CANCEL);
    }

    public void publishData(String str) {
        this.mIsSuccess = true;
        getBlackboard().post("data://user/dialog/FolderName", new Object[]{str});
        postAnalyticsLog(AnalyticsEventId.EVENT_MENU_CREATE_GROUP_CREATE);
    }
}
