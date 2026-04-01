package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartMemorySaverCmd extends BaseCommand {
    public void onExecute(EventContext eventContext, Object... objArr) {
        try {
            Intent intent = new Intent();
            intent.setAction("samsung.intent.action.SIMILAR_Images");
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            showToast((int) R.string.activity_not_found, 1);
            j.p(e, new StringBuilder("StartMemorySaverCmd failed e="), this.TAG);
        }
    }
}
