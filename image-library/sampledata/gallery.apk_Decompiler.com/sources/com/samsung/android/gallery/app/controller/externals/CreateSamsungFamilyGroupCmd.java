package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateSamsungFamilyGroupCmd extends BaseCommand {
    public void onExecute(EventContext eventContext, Object... objArr) {
        try {
            Intent intent = new Intent("com.samsung.android.samsungaccount.action.OPEN_FAMILY_GROUP_MAIN");
            intent.putExtra("launch_mode", "request_create_group");
            getActivity().startActivityForResult(intent, 301);
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("samsung account setting activity not found"), this.TAG);
        }
    }
}
