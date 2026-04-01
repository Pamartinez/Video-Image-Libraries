package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.sdk.spage.card.event.Event;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import java.util.regex.Pattern;
import k7.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameFileDialog extends CreateNameDialog {
    private String mBaseFileFullPath;
    private final SubscriberListener mDismissListener = new j(5, this);

    private String getNewFileFullPath(String str) {
        return FileUtils.getDirectoryFromPath(this.mBaseFileFullPath, true) + str + FileUtils.getExtension(this.mBaseFileFullPath, true);
    }

    private void initBlackBoard() {
        if (getActivity() != null) {
            getBlackboard().subscribe("command://DismissDialog", this.mDismissListener);
            return;
        }
        throw new AssertionError("fail to refer blackboard");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        dismiss();
    }

    private void setEnableButton(int i2, boolean z) {
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null) {
            alertDialog.getButton(i2).setEnabled(z);
        }
    }

    public void afterTextChanged(Editable editable) {
        boolean exists = FileUtils.exists(FileUtils.getDirectoryFromPath(this.mBaseFileFullPath, true) + editable + FileUtils.getExtension(this.mBaseFileFullPath, true));
        if (exists) {
            setError((int) R.string.error_file_already_exists);
        }
        setEnableButton(-1, !exists);
    }

    public Dialog createDialog(Bundle bundle) {
        initBlackBoard();
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = BundleWrapper.getString(arguments, FileApiContract.Parameter.PATH, Event.DEFAULT_EVENT_TYPE);
            this.mBaseFileFullPath = string;
            if (string == null) {
                throw new RuntimeException("BundleKey.PATH => NULL");
            }
        }
        return super.createDialog(bundle);
    }

    public String getDefaultName() {
        return FileUtils.getBaseName(this.mBaseFileFullPath);
    }

    public int getPositiveButtonResource() {
        return R.string.rename;
    }

    public String getTitle() {
        return getString(R.string.rename_file);
    }

    public ErrorType isValid(String str) {
        if (isDottedText(str)) {
            return ErrorType.INVALID_CHARACTER;
        }
        return ErrorType.NONE;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        getBlackboard().unsubscribe("command://DismissDialog", this.mDismissListener);
        super.onDismiss(dialogInterface);
    }

    public void publishData(String str) {
        getBlackboard().post("FileOperationDialog", (Object) null);
        getBlackboard().post("command://RenameSelected", getNewFileFullPath(str));
    }

    public String replaceLastDots(String str) {
        String[] split = Pattern.compile("[.]+$").split(str);
        if (split.length >= 1) {
            return split[0];
        }
        return str;
    }

    public void publishCancel() {
    }
}
