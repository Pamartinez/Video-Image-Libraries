package com.samsung.android.gallery.app.controller.externals;

import android.app.Activity;
import android.content.Intent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayDualShotCmd extends AbstractPlayCmd {
    public static int MORE_OPTION = 1;
    public static int SEF_VIEWER = 0;
    public static int SUGGESTION = 2;
    protected int mPortraitMode = SEF_VIEWER;

    public Intent createIntent(MediaItem mediaItem) {
        String str;
        Intent intent = new Intent("com.samsung.android.app.dofviewer");
        intent.setFlags(67108864);
        if (this.mPortraitMode == SUGGESTION) {
            str = MediaItemSuggest.getOriginPath(mediaItem);
        } else {
            str = mediaItem.getPath();
        }
        intent.putExtra("IMAGE_FILE_PATH", str);
        intent.putExtra("Portrait", this.mPortraitMode);
        return intent;
    }

    public String getEventId() {
        int i2 = this.mPortraitMode;
        if (i2 == MORE_OPTION) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_ON_DEMAND_PORTRAIT_EFFECT.toString();
        }
        if (i2 == SUGGESTION) {
            return AnalyticsEventId.EVENT_SUGGEST_PORTRAIT_PICTURES_VIEW_DETAIL.toString();
        }
        return AnalyticsEventId.EVENT_DETAIL_VIEW_LIVE_FOCUS.toString();
    }

    public void init(Object... objArr) {
        super.init(objArr);
        if (objArr.length > 1) {
            this.mPortraitMode = objArr[1].intValue();
        }
    }

    public void startActivity(Intent intent) {
        int i2;
        int i7 = this.mPortraitMode;
        if (i7 == SEF_VIEWER || i7 == MORE_OPTION) {
            getBlackboard().post("command://event/DataDirty", (Object) null);
        }
        Activity activity = this.mActivity;
        if (this.mPortraitMode == SEF_VIEWER) {
            i2 = 788;
        } else {
            i2 = 2320;
        }
        activity.startActivityForResult(intent, i2);
    }
}
