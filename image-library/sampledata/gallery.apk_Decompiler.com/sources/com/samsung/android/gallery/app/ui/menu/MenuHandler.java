package com.samsung.android.gallery.app.ui.menu;

import android.text.TextUtils;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.utils.SystemUi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuHandler {
    protected final String TAG = getClass().getSimpleName();
    private MenuHandler mNextHandler;

    private void postAnalyticsLog(EventContext eventContext, String str) {
        AnalyticsEventId analyticsEventId;
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("location://albums/manage")) {
                analyticsEventId = AnalyticsEventId.EVENT_MENU_MANAGE_ALBUM;
            } else if (str.startsWith("location://albums/hide")) {
                analyticsEventId = AnalyticsEventId.EVENT_MENU_HIDE_ALBUM;
            } else if (str.startsWith("location://search")) {
                analyticsEventId = AnalyticsEventId.EVENT_MENU_SEARCH;
            } else if (str.startsWith("location://sharing/albums/invitations")) {
                analyticsEventId = AnalyticsEventId.EVENT_MENU_SHARING_INVITATION;
            } else if (str.startsWith("location://notices")) {
                analyticsEventId = AnalyticsEventId.EVENT_MENU_NOTICES;
            } else if (str.startsWith("location://stories/hideRule")) {
                analyticsEventId = AnalyticsEventId.EVENT_STORIES_HIDE_RULE;
            } else if (LocationKey.isStoriesFavorite(str)) {
                analyticsEventId = AnalyticsEventId.EVENT_STORIES_FAVORITES;
            } else {
                analyticsEventId = null;
            }
            if (analyticsEventId != null) {
                eventContext.postAnalyticsLog(analyticsEventId);
            }
        }
    }

    public final void moveTo(EventContext eventContext, String str) {
        eventContext.getBlackboard().post("command://MoveURL", str);
        postAnalyticsLog(eventContext, str);
    }

    public abstract boolean onItemSelected(EventContext eventContext, MenuItem menuItem);

    public final boolean onOptionsItemSelected(EventContext eventContext, MenuItem menuItem) {
        if (onItemSelected(eventContext, menuItem)) {
            return true;
        }
        MenuHandler menuHandler = this.mNextHandler;
        if (menuHandler == null || !menuHandler.onOptionsItemSelected(eventContext, menuItem)) {
            return false;
        }
        return true;
    }

    public final void postEvent(EventContext eventContext, EventMessage eventMessage) {
        eventContext.getBlackboard().postEvent(eventMessage);
    }

    public final void publishPopoverToolbarInfo(EventContext eventContext, int i2) {
        if (!SystemUi.supportPopoverUi(eventContext.getContext(), false)) {
            return;
        }
        if (eventContext.getToolbar() != null) {
            PopoverHelper.publishPopoverInfo(eventContext.getBlackboard(), eventContext.getToolbar(), 1, i2);
        } else {
            Log.w(this.TAG, "publishPopoverInfo fail#no toolbar ");
        }
    }

    public final MenuHandler setMenuHandler(MenuHandler menuHandler) {
        this.mNextHandler = menuHandler;
        return menuHandler;
    }
}
