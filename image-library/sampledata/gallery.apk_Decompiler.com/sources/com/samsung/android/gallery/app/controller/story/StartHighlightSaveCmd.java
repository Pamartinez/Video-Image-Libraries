package com.samsung.android.gallery.app.controller.story;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.StringResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartHighlightSaveCmd extends StartHighlightPlayerEditCmd {
    private int mRequestCode;

    private boolean isSave() {
        if (this.mRequestCode == 1796) {
            return true;
        }
        return false;
    }

    public Intent createIntent(EventContext eventContext, int i2) {
        String str;
        Intent createIntent = super.createIntent(eventContext, i2);
        createIntent.setAction("com.sec.android.app.vepreload.action.SAVE_HIGHLIGHT");
        if (isSave()) {
            str = "save";
        } else {
            str = "share";
        }
        createIntent.putExtra("action", str);
        return createIntent;
    }

    public String getEventId() {
        int i2 = this.mRequestCode;
        if (i2 == 1798) {
            return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_ADD_TO_SHARED_ALBUM.toString();
        }
        if (i2 == 1797) {
            return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SHARE.toString();
        }
        return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SAVE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mRequestCode = getExportOptions(objArr).getRequestCode();
        super.onExecute(eventContext, objArr);
    }

    public void startHighlightPlayer(Context context, Intent intent) {
        try {
            getActivity().startActivityForResult(intent, this.mRequestCode);
        } catch (ActivityNotFoundException e) {
            if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_STORY_VIDEO_EDITOR)) {
                guideDownloadPackage(StartHighlightPlayerCmd.PACKAGE_NAME_HV, StringResources.getVideoEditorAppName());
            } else {
                showErrorMessage(e);
            }
        } catch (SecurityException e7) {
            showErrorMessage(e7);
        }
    }
}
