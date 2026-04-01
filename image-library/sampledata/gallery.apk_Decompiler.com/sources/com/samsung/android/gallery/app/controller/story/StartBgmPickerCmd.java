package com.samsung.android.gallery.app.controller.story;

import A.a;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartBgmPickerCmd extends BaseCommand {
    private Intent createIntent(String str) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setComponent(new ComponentName("com.sec.android.app.ve.vebgm", "com.sec.android.app.ve.vebgm.activity.VEBGMPickerActivity"));
        intent.setType("audio/*");
        intent.putExtra("SelectedBGM", str);
        intent.putExtra("RequestAPP", "GALLERY_STORY");
        return intent;
    }

    private void showErrorMessage(Exception exc) {
        showToast((int) R.string.no_app_for_action);
        a.s(exc, new StringBuilder("StartBgmPickerCmd failed e="), this.TAG);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        startBgmPicker(createIntent(objArr[0]));
    }

    public void startBgmPicker(Intent intent) {
        try {
            getActivity().startActivityForResult(intent, 1799);
        } catch (ActivityNotFoundException | SecurityException e) {
            showErrorMessage(e);
        }
    }
}
