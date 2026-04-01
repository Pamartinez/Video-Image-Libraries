package com.samsung.android.gallery.app.controller.album;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddShortcutCmd extends BaseCommand {
    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_SPLIT_ADD_SHORTCUT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ShortcutHelper.getInstance().setShortcut(getActivity(), objArr[0], ShortcutHelper.UseType.FOR_HOME_SCREEN);
    }
}
