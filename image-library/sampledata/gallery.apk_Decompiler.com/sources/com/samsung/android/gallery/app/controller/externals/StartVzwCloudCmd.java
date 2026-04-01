package com.samsung.android.gallery.app.controller.externals;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartVzwCloudCmd extends BaseCommand {
    private Intent createIntent(Context context) {
        return new Intent("com.vcast.mediamanager.ACTION_PICTURES");
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Context context = getContext();
        try {
            context.startActivity(createIntent(context));
        } catch (ActivityNotFoundException unused) {
            Log.e(this.TAG, "StartVzwCloudCmd intent not found");
        }
    }
}
