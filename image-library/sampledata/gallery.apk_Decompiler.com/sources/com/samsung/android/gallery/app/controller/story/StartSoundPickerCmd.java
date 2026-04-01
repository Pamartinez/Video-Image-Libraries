package com.samsung.android.gallery.app.controller.story;

import A.a;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSoundPickerCmd extends BaseCommand {
    private Intent createIntent() {
        Intent intent = new Intent();
        intent.setPackage("com.samsung.android.app.soundpicker");
        intent.setType("vnd.android.cursor.dir/audio");
        intent.addFlags(603979776);
        return intent;
    }

    private void showErrorMessage(Exception exc) {
        showToast((int) R.string.no_app_for_action);
        a.s(exc, new StringBuilder("StartSoundPickerCmd failed e="), this.TAG);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_MENU_ADD_BACKGROUND_MUSIC.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        startMusicPicker(createIntent());
    }

    public void startMusicPicker(Intent intent) {
        try {
            getActivity().startActivityForResult(intent, 1800);
        } catch (ActivityNotFoundException | SecurityException e) {
            showErrorMessage(e);
        }
    }
}
