package com.samsung.android.gallery.app.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import h.C0199b;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameStoryDialog extends CreateStoryDialog {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postInitDialog$0() {
        setPositiveButtonEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postInitDialog$1(String str) {
        if (TextUtils.isEmpty(str) || isSameOrgName(str)) {
            clearError();
            ThreadUtil.postOnUiThread(new t(9, this));
        }
    }

    public String getDefaultName() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return "";
        }
        String string = arguments.getString("name");
        this.mOrgName = string;
        return string;
    }

    public int getPositiveButtonResource() {
        return R.string.done;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_EVENT_VIEW_SELECTION.toString();
    }

    public String getTitle() {
        return getResources().getString(R.string.change_story_title);
    }

    public boolean isSameOrgName(String str) {
        String str2 = this.mOrgName;
        if (str2 == null || !str2.equals(str)) {
            return false;
        }
        return true;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/StoryRename", (Object) null);
        super.onDismiss(dialogInterface);
    }

    public void postInitDialog() {
        ThreadUtil.postOnBgThread(new C0199b(23, this, getName()));
    }

    public void publishCancel() {
        postAnalyticsLog(AnalyticsEventId.EVENT_RENAME_STORY_CANCEL);
        getBlackboard().post("data://user/dialog/StoryRename", (Object) null);
    }

    public void publishData(String str) {
        postAnalyticsLog(AnalyticsEventId.EVENT_RENAME_STORY_RENAME);
        getBlackboard().post("data://user/dialog/StoryRename", str);
    }
}
