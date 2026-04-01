package com.samsung.android.gallery.app.controller.story;

import U3.a;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadAllBgmCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public void onOptionSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        String str;
        int i2 = 0;
        if (arrayList != null && arrayList.size() > 0) {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        String str2 = this.TAG;
        if (i2 == 1) {
            str = "download all";
        } else {
            str = Contract.COMMAND_ID_CANCEL;
        }
        Log.d(str2, "onOptionSelected", str);
        if (i2 == 1) {
            getBlackboard().postEvent(EventMessage.obtain(1121));
        }
    }

    private void showNetworkErrorToast() {
        Utils.showToast(AppResources.getAppContext(), (int) R.string.connect_network_to_see_music_suggestion);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_MENU_DOWNLOAD_ALL.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!NetworkUtils.isNetworkAvailable()) {
            showNetworkErrorToast();
            return;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirmProgress").appendArg("title", getContext().getString(R.string.download_all_background_music)).appendArg("option1", getContext().getString(R.string.download_all)).appendArg("dismissKey", "command://DownloadAllBgmComplete").appendArg("screenId", AnalyticsScreenId.SCREEN_STORY_HIGHLIGHT_BGM_PICKER.toString()).appendArg("option1EventId", AnalyticsEventId.EVENT_STORY_BGM_TIP_CARD_DOWNLOAD_ALL.toString()).appendArg("cancelEventId", AnalyticsEventId.EVENT_STORY_BGM_TIP_CARD_NOT_NOW.toString()).build()).setOnDataCompleteListener(new a(1, this)).start();
    }
}
