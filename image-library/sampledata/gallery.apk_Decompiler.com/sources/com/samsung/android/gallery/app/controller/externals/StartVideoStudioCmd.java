package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartVideoStudioCmd extends BaseCommand {
    private Object mCaller;

    public String getEventId() {
        if (this.mCaller instanceof CreateNewDialogCmd) {
            return AnalyticsEventId.EVENT_MENU_CREATE_VIDEO_STUDIO.toString();
        }
        return AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_VIDEO_STUDIO.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Object obj;
        if (guidePackageEnabling("com.sec.android.app.vepreload")) {
            Log.w(this.TAG, "goto settings due to package disabled");
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.sec.android.app.vepreload", "com.sec.android.app.vepreload.common.activity.ProjectPickerActivity"));
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("launch_from", "From_Gallery");
        try {
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage("com.sec.android.app.vepreload", StringResources.getVideoEditorAppName());
        } catch (Exception e) {
            a.s(e, new StringBuilder("startVideoStudio failed, e="), this.TAG);
        }
        if (objArr == null || objArr.length <= 0) {
            obj = null;
        } else {
            obj = objArr[0];
        }
        this.mCaller = obj;
    }
}
