package com.samsung.android.gallery.app.controller.sharing;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateSharedAlbumCmd extends BaseCommand {
    public String getEventId() {
        return AnalyticsEventId.EVENT_SHARED_VIEW_CREATE_SHARED_ALBUM.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!NetworkUtils.isNetworkConnected(getContext())) {
            showToast(getContext().getResources().getString(R.string.could_not_create_shared_album_network_status));
            return;
        }
        getBlackboard().post(CommandKey.DATA_REQUEST(new UriBuilder("data://user/dialog/SharedAlbumCreate").build()), eventContext);
    }
}
