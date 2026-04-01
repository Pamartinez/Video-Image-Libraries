package com.samsung.android.gallery.module.service.dialog;

import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.scsp.media.file.FileApiContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NormalDialogHelper extends DialogHelper {
    public void showDialog(String str, int i2, int i7, int i8, boolean z, boolean z3) {
        postEvent(new UriBuilder("command://ShowDialog").appendArg("target", "SimpleProgressDialog").appendArg("progress_title", str).appendArg("count", i2).appendArg("total", i7).appendArg("percent", i8).appendArg("support_pause", z).appendArg("support_back_key", z3).build());
    }

    public void showFileOperationDialog(String str, String str2, String str3) {
        postEvent(new UriBuilder("command://ShowDialog").appendArg("target", "FileOperationDialog").appendArg("title", str).appendArg(FileApiContract.Parameter.PATH, str2).appendArg("screenId", str3).build());
    }

    public void showRenameDialog(String str) {
        postEvent(new UriBuilder("command://ShowDialog").appendArg("target", "RenameFileDialog").appendArg(FileApiContract.Parameter.PATH, str).build());
    }

    public void updateDialog(int i2, int i7, int i8) {
        if (checkUpdateCondition(i2, i8)) {
            postEvent(new UriBuilder("command://UpdateProgress").appendArg("count", i2).appendArg("total", i7).appendArg("percent", i8).build());
        }
    }
}
