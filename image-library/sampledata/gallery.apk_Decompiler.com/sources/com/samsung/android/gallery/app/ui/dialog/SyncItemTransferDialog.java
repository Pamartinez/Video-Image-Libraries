package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SyncItemTransferDialog extends BaseDialog {
    private String getMessage(int i2) {
        int i7;
        Context context = getContext();
        if (context == null) {
            return null;
        }
        if (i2 == 1) {
            i7 = R.string.secure_knox_cloud_item_not_moved;
        } else {
            i7 = R.string.secure_knox_cloud_items_not_moved;
        }
        String cloudBrand = StringResources.getCloudBrand();
        return context.getString(i7, new Object[]{cloudBrand, cloudBrand});
    }

    private String getTitle(int i2, int i7, String str) {
        String str2;
        Context context = getContext();
        if (context == null) {
            return null;
        }
        if (i2 == 0) {
            str2 = context.getResources().getQuantityString(R.plurals.secure_folder_cloud_video_moved_title_plural, i7, new Object[]{Integer.valueOf(i7), str});
        } else if (i7 == 0) {
            str2 = context.getResources().getQuantityString(R.plurals.secure_folder_cloud_image_moved_title_plural, i2, new Object[]{Integer.valueOf(i2), str});
        } else {
            int i8 = i2 + i7;
            str2 = context.getResources().getQuantityString(R.plurals.secure_folder_cloud_item_moved_title_plural, i8, new Object[]{Integer.valueOf(i8), str});
        }
        return SeApiCompat.naturalizeText(str2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        publishInternal(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(DialogInterface dialogInterface, int i2) {
        publishInternal(true);
    }

    private void publishInternal(boolean z) {
        getBlackboard().post("data://user/dialog/SyncDataTransfer", Boolean.valueOf(z));
    }

    public Dialog createDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        int i2 = BundleWrapper.getInt(arguments, "imageCount", 0);
        int i7 = BundleWrapper.getInt(arguments, "videoCount", 0);
        String title = getTitle(i2, i7, arguments.getString("containerName"));
        return new AlertDialog.Builder(getContext()).setTitle((CharSequence) title).setMessage((CharSequence) getMessage(i2 + i7)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new B(this, 0)).setPositiveButton((int) R.string.move, (DialogInterface.OnClickListener) new B(this, 1)).create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/SyncDataTransfer", (Object) null);
    }
}
