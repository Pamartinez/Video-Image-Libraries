package com.samsung.android.gallery.app.controller.viewer;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestBurstShotSelectModeCmd extends BaseCommand {
    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SELECT_BURST_SHOT.toString();
    }

    public String getLocationKey() {
        return "location://BurstShotSelectviewer";
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Object obj;
        Blackboard blackboard = getBlackboard();
        MediaItem[] allItems = eventContext.getAllItems();
        if (allItems == null || allItems.length <= 0) {
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("fail execute : ");
            if (allItems != null) {
                obj = Integer.valueOf(allItems.length);
            } else {
                obj = "0";
            }
            sb2.append(obj);
            Log.e(str, sb2.toString());
            return;
        }
        int intValue = objArr[1].intValue();
        long longValue = objArr[2].longValue();
        blackboard.publish(DataKey.DATA(getLocationKey()), allItems);
        blackboard.publish("command://MoveURL", new UriBuilder(getLocationKey()).appendArg(Message.KEY_POSITION, intValue).appendArg("bestId", longValue).appendArg("burstId", objArr[0].getGroupMediaId()).build());
    }
}
