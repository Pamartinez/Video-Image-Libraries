package com.samsung.android.gallery.app.ui.dialog;

import Fa.C0558l;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.SystemUi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerListChooserDialog extends BaseDialog {
    private final DialogInterface.OnClickListener mOnClickListener = new C0558l(19, this);

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(DialogInterface dialogInterface, int i2) {
        getBlackboard().post("data://user/dialog/ViewerListChooser", Integer.valueOf(i2));
    }

    private String[] loadItems() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return null;
        }
        String string = arguments.getString("list_chooser_items");
        if (string != null) {
            return string.split(";");
        }
        return new String[0];
    }

    private int loadTitle() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return Integer.parseInt(arguments.getString("list_chooser_title", "0"));
        }
        return 0;
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context != null) {
            return new AlertDialog.Builder(context).setTitle(loadTitle()).setItems(loadItems(), this.mOnClickListener).create();
        }
        Log.e(this.TAG, "createDialog failed null context");
        return super.createDialog(bundle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (SystemUi.supportPopoverUi(getContext(), false) && supportPopover()) {
            dismiss();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/ViewerListChooser", (Object) null);
        super.onDismiss(dialogInterface);
    }

    public boolean supportPopover() {
        return true;
    }
}
