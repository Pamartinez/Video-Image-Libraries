package com.samsung.android.gallery.app.controller.album;

import A.a;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartPrivateAlbumCmd extends BaseCommand {
    public String getEventId() {
        return AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_PRIVATE_ALBUM.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryPrivateActivity");
            getActivity().startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("start private album failed. e="), this.TAG);
        }
    }
}
