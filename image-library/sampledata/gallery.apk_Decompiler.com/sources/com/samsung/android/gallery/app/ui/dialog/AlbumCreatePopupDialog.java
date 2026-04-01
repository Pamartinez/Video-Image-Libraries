package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.module.album.AlbumCreatePopupType;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o5.C0496a;
import q4.C0504a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumCreatePopupDialog extends BaseDialog {
    private View inflateCreateViews(Context context) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.album_create_popup_dialog, (ViewGroup) null);
        String[] loadMenuFromBundle = loadMenuFromBundle();
        if (loadMenuFromBundle == null || loadMenuFromBundle.length <= 0) {
            Optional.ofNullable(viewGroup.findViewById(R.id.create_album)).ifPresent(new C0504a(this, 1));
            Optional.ofNullable(viewGroup.findViewById(R.id.create_group)).ifPresent(new C0504a(this, 2));
            if (loadEnableSharedFromBundle()) {
                View findViewById = viewGroup.findViewById(R.id.create_shared_album);
                findViewById.setOnClickListener(new C0496a(8, this));
                findViewById.setVisibility(0);
            }
            if (Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM)) {
                View findViewById2 = viewGroup.findViewById(R.id.create_auto_updating_album);
                findViewById2.setOnClickListener(new C0496a(8, this));
                if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                    ((TextView) viewGroup.findViewById(R.id.create_auto_updating_album_sub_title)).setText(R.string.album_create_popup_dialog_not_pet_auto_updating_album_title);
                }
                findViewById2.setVisibility(0);
            }
            return viewGroup;
        }
        for (String parseInt : loadMenuFromBundle) {
            Optional.ofNullable(viewGroup.findViewById(Integer.parseInt(parseInt))).ifPresent(new C0504a(this, 0));
        }
        if (Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM) && !Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            ((TextView) viewGroup.findViewById(R.id.create_auto_updating_album_sub_title)).setText(R.string.album_create_popup_dialog_not_pet_auto_updating_album_title);
        }
        return viewGroup;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateCreateViews$0(View view) {
        view.setOnClickListener(new C0496a(8, this));
        view.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateCreateViews$1(View view) {
        view.setOnClickListener(new C0496a(8, this));
        view.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateCreateViews$2(View view) {
        view.setOnClickListener(new C0496a(8, this));
        view.setVisibility(0);
    }

    private boolean loadEnableSharedFromBundle() {
        if (getArguments() == null || !BundleWrapper.getBoolean(getArguments(), "enable_create_shared", false)) {
            return false;
        }
        return true;
    }

    private String[] loadMenuFromBundle() {
        String str;
        if (getArguments() != null) {
            str = BundleWrapper.getString(getArguments(), "create_album_types", (String) null);
        } else {
            str = null;
        }
        if (str != null) {
            return str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onItemClicked(View view) {
        AlbumCreatePopupType albumCreatePopupType;
        if (view.getId() == R.id.create_album) {
            albumCreatePopupType = AlbumCreatePopupType.ALBUM;
        } else if (view.getId() == R.id.create_group) {
            albumCreatePopupType = AlbumCreatePopupType.GROUP;
        } else if (view.getId() == R.id.create_shared_album) {
            albumCreatePopupType = AlbumCreatePopupType.SHARED_ALBUM;
        } else if (view.getId() == R.id.create_auto_updating_album) {
            albumCreatePopupType = AlbumCreatePopupType.AUTO_UPDATING_ALBUM;
        } else if (view.getId() == R.id.create_family_shared_album) {
            albumCreatePopupType = AlbumCreatePopupType.FAMILY_SHARED_ALBUM;
        } else {
            albumCreatePopupType = AlbumCreatePopupType.ALBUM;
        }
        getBlackboard().post("data://user/dialog/AlbumCreatePopup", albumCreatePopupType);
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context != null) {
            return new AlertDialog.Builder(context).setView(inflateCreateViews(context)).setTitle((int) R.string.choose_what_to_create).create();
        }
        Log.e(this.TAG, "createDialog failed null context");
        return super.createDialog(bundle);
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/AlbumCreatePopup", (Object) null);
    }
}
