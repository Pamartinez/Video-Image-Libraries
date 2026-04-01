package com.samsung.android.gallery.app.controller.story;

import R6.c;
import U3.a;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameStoryCmd extends BaseCommand {
    private MediaItem mItem;

    private boolean checkNoItems(EventContext eventContext, Object[] objArr) {
        if (eventContext.isSelectionMode()) {
            if (eventContext.getSelectedItems().length == 0) {
                return true;
            }
            return false;
        } else if (!isFloatingPopupMenu() && objArr.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    private MediaItem getSelectedItem(EventContext eventContext, Object... objArr) {
        if (!eventContext.isSelectionMode() && !isFloatingPopupMenu()) {
            return objArr[0];
        }
        if (eventContext.getSelectedItems().length > 0) {
            return eventContext.getSelectedItems()[0];
        }
        return null;
    }

    private boolean isFloatingPopupMenu() {
        return ((Boolean) getBlackboard().read("data://floating_pop_menu", Boolean.FALSE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renameStory$0(String str, EventContext eventContext) {
        Context applicationContext = getApplicationContext();
        if (DbCompat.storyApi().renameStory(str, this.mItem.getTitle(), this.mItem.getAlbumID())) {
            getBlackboard().post("command:///RefreshWithoutDelay", (Object) null);
            StoryUpdateNotifier.getInstance().notifyStory(applicationContext, true);
            Blackboard.publishGlobal("data://user/storyUpdated", (Object) null);
        } else {
            renameFail();
        }
        if (eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    private void renameFail() {
        Log.e(this.TAG, "Fail to update CMH during rename story.");
    }

    /* access modifiers changed from: private */
    public void renameStory(EventContext eventContext, ArrayList<Object> arrayList) {
        String str;
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 && SdkConfig.atLeast(SdkConfig.GED.T)) {
            getBlackboard().postEvent(EventMessage.obtain(1093, Boolean.TRUE));
        }
        if (arrayList == null) {
            Log.e(this.TAG, "data is null");
            return;
        }
        try {
            str = (String) arrayList.get(0);
        } catch (ClassCastException e) {
            Log.e(this.TAG, e.toString());
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e(this.TAG, "name is null or empty");
        } else {
            ThreadUtil.postOnBgThread(new c(this, str, eventContext, 11));
        }
    }

    public String getEventId() {
        if (getHandler().isSelectionMode()) {
            return AnalyticsEventId.EVENT_MENU_BOTTOM_RENAME_STORY.toString();
        }
        return AnalyticsEventId.EVENT_MENU_RENAME_STORY.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        if (checkNoItems(eventContext, objArr)) {
            Log.e(this.TAG, "array size is 0");
            return;
        }
        MediaItem selectedItem = getSelectedItem(eventContext, objArr);
        this.mItem = selectedItem;
        if (selectedItem == null) {
            Log.e(this.TAG, "mItem is null");
            return;
        }
        UriBuilder uriBuilder = new UriBuilder("data://user/dialog/StoryRename");
        if (this.mItem.getTitle() != null) {
            str = this.mItem.getTitle();
        } else {
            str = "story";
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(uriBuilder.appendArg("name", str).build()).setOnDataCompleteListener(new a(4, this)).start();
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 && SdkConfig.atLeast(SdkConfig.GED.T)) {
            getBlackboard().postEvent(EventMessage.obtain(1093, Boolean.FALSE));
        }
    }
}
