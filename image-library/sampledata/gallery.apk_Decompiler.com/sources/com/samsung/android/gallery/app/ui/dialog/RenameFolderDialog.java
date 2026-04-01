package com.samsung.android.gallery.app.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameFolderDialog extends CreateFolderDialog {
    private void publishInternal(String str) {
        getBlackboard().post("data://user/dialog/FolderRename", new Object[]{str});
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
        return R.string.rename;
    }

    public String getTitle() {
        return getResources().getString(R.string.folder_rename);
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        getBlackboard().post("data://user/dialog/FolderRename", (Object) null);
    }

    public void onPostInit() {
        clearError();
    }

    public void publishCancel() {
        publishInternal((String) null);
    }

    public void publishData(String str) {
        publishInternal(str);
    }
}
