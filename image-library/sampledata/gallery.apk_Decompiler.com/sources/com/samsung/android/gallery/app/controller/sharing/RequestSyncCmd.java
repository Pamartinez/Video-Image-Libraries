package com.samsung.android.gallery.app.controller.sharing;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.sharing.request.RequestSync;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestSyncCmd extends BaseCommand {
    private String mLocationKey;

    private void connectSession() {
        MdeSharingService.getInstance().connectSessionAsync(1, new ConnectListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onSuccess$0() {
                new RequestSharedAlbumCmd().execute(RequestSyncCmd.this.getHandler(), RequestCmdType.REQUEST_SYNC, RequestSync.Types.Space);
                if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
                    new RequestSharedAlbumCmd().execute(RequestSyncCmd.this.getHandler(), RequestCmdType.REQUEST_INVITATION_SYNC);
                }
            }

            public void onFailure(int i2) {
                Log.she(RequestSyncCmd.this.TAG, "connectSessionAsync onFailure.");
                if (i2 == 1 && MdeAuthHelper.isAuthServiceEnabled()) {
                    RequestSyncCmd.this.notifyCompleteSession(false);
                }
            }

            public void onSuccess() {
                RequestSyncCmd.this.notifyCompleteSession(true);
                ThreadUtil.postOnUiThread(new a(this));
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifyCompleteSession(boolean z) {
        if (LocationKey.isSharings(this.mLocationKey)) {
            getBlackboard().publish("command://ConnectCompleteSession", Boolean.valueOf(z));
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mLocationKey = objArr[0];
        connectSession();
    }
}
