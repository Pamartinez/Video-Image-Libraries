package com.samsung.android.gallery.app.ui.menu.list;

import android.content.Intent;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMenuHandler extends MenuHandler {
    private void sendSALogging(EventContext eventContext, AnalyticsEventId analyticsEventId) {
        eventContext.postAnalyticsLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_CATEGORY.toString(), analyticsEventId);
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_edit_quick_search) {
            moveTo(eventContext, new UriBuilder("location://search/fileList/Category/MyQuery").appendArg("searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).appendArg("editMode", true).build());
            sendSALogging(eventContext, AnalyticsEventId.EVENT_SEARCH_EDIT_SHORTCUTS_MENU);
        } else if (itemId != R.id.action_search_setting) {
            return false;
        } else {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.GenericSettingActivity");
            intent.putExtra("locationKey", "location://searchsettings");
            intent.putExtra("blackboard_name", eventContext.getBlackboard().getName());
            eventContext.getActivity().startActivity(intent);
            sendSALogging(eventContext, AnalyticsEventId.EVENT_SEARCH_SETTINGS_MENU);
        }
        return true;
    }
}
