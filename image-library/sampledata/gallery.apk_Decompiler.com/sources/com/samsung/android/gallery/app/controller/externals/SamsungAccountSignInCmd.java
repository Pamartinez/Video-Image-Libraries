package com.samsung.android.gallery.app.controller.externals;

import android.app.Activity;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungAccountSignInCmd extends BaseCommand {
    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        Activity activity = eventContext.getActivity();
        if (activity != null) {
            boolean z = false;
            if (objArr != null && objArr.length > 0) {
                z = objArr[0].booleanValue();
            }
            Intent intent = new Intent("com.msc.action.samsungaccount.SIGNIN_POPUP");
            intent.putExtra("mypackage", activity.getPackageName());
            if (z) {
                str = "2uzr3g12m3";
            } else {
                str = "22n6hzkam0";
            }
            intent.putExtra("client_id", str);
            intent.putExtra("OSP_VER", "OSP_02");
            intent.putExtra("MODE", "ADD_ACCOUNT");
            activity.startActivityForResult(intent, 799);
        }
    }
}
