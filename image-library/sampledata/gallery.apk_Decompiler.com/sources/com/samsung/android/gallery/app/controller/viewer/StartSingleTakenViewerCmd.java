package com.samsung.android.gallery.app.controller.viewer;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSingleTakenViewerCmd extends BaseCommand {
    private static String getLocationKey(String str) {
        if (LocationKey.isHighLightPictures(str)) {
            return "location://SingleTakenShotviewer/suggestionHighlight";
        }
        if (LocationKey.isHighlightGroupPanelView(str)) {
            return "location://SingleTakenShotviewer/highlight";
        }
        if (LocationKey.isSuperSlowGroupPanelView(str)) {
            return "location://SingleTakenShotviewer/superslow";
        }
        return "location://SingleTakenShotviewer";
    }

    private boolean isForcePlayVideoInGallery(String str) {
        if (!PreferenceFeatures.VideoPlayerFeature.SUPPORT_SETTING || !LocationKey.isHighlightGroupPanelView(str)) {
            return false;
        }
        return true;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Object obj;
        String locationKey = getLocationKey(eventContext.getLocationKey());
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
        int intValue = objArr[0].intValue();
        new VuLauncher(getBlackboard()).publishData().forcePlayVideoInGallery(isForcePlayVideoInGallery(locationKey)).launch(new UriBuilder(locationKey).appendArg("bestId", objArr[1].intValue()).appendArg("with_group", true).appendArg("burstId", eventContext.getCurrentItem().getGroupMediaId()).appendArg("from_viewer", true).build(), intValue, allItems);
    }
}
