package com.samsung.android.gallery.app.ui.dialog;

import Fa.C0558l;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeAlbumCoverDialog extends BaseDialog {
    private boolean mIsCustomCover;
    private boolean mNeedHistoryMenu;

    private void finishDialog(int i2) {
        getBlackboard().post("data://user/dialog/ChangeAlbumCover", Integer.valueOf(getSelectedItem(i2)));
        dismiss();
    }

    private CharSequence[] getMenuItemList() {
        ArrayList arrayList = new ArrayList();
        if (this.mIsCustomCover) {
            arrayList.add(getString(R.string.use_most_recent_image));
        }
        arrayList.add(getString(R.string.select_an_image));
        if (this.mNeedHistoryMenu) {
            arrayList.add("Cover History");
        }
        return (CharSequence[]) arrayList.toArray(new CharSequence[0]);
    }

    private int getSelectedItem(int i2) {
        if (!this.mNeedHistoryMenu || this.mIsCustomCover) {
            return i2;
        }
        return i2 + 1;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        finishDialog(i2);
    }

    public Dialog createDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mNeedHistoryMenu = BundleWrapper.getBoolean(arguments, "need_history_menu", false);
            this.mIsCustomCover = BundleWrapper.getBoolean(arguments, "is_custom_cover", false);
            return new AlertDialog.Builder(getActivity()).setTitle((int) R.string.change_cover_image).setItems(getMenuItemList(), new C0558l(11, this)).create();
        }
        throw new IllegalArgumentException("bundle is null");
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/ChangeAlbumCover", (Object) null);
    }
}
