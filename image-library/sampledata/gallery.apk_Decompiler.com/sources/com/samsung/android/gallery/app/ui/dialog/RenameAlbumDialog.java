package com.samsung.android.gallery.app.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameAlbumDialog extends CreateAlbumDialog {
    private String mPath;

    private void publishInternal(String str) {
        getBlackboard().post("data://user/dialog/AlbumRename", new Object[]{str, this.mPath});
    }

    public String getDefaultName() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return "";
        }
        this.mPath = arguments.getString(FileApiContract.Parameter.PATH);
        String string = arguments.getString("name");
        this.mOrgName = string;
        return string;
    }

    public String getDefaultPath() {
        String str = this.mPath;
        if (str != null) {
            return str;
        }
        return this.DEFAULT_PATH;
    }

    public int getLayoutId() {
        return R.layout.alert_dialog_text_entry;
    }

    public int getPositiveButtonResource() {
        return R.string.rename;
    }

    public String getTitle() {
        return getResources().getString(R.string.rename_album);
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        getBlackboard().post("data://user/dialog/AlbumRename", (Object) null);
    }

    public void onPostInit() {
        clearError();
    }

    public void publishCancel() {
        publishInternal((String) null);
        postAnalyticsLog(AnalyticsEventId.EVENT_RENAME_ALBUM_CANCEL);
    }

    public void publishData(String str) {
        publishInternal(str);
        postAnalyticsLog(AnalyticsEventId.EVENT_RENAME_ALBUM_RENAME);
    }
}
