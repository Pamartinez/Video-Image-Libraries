package com.samsung.android.gallery.app.controller.story;

import Fa.C0571z;
import O3.o;
import Qb.e;
import S3.d;
import T8.C0578a;
import U3.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.module.story.ondemand.StoryGenApi;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteStoryCmd extends BaseCommand {
    private FileItemInterface[] mItems;

    /* access modifiers changed from: private */
    public void deleteStories(EventContext eventContext, ArrayList<Object> arrayList) {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 && SdkConfig.atLeast(SdkConfig.GED.T)) {
            getBlackboard().postEvent(EventMessage.obtain(1093, Boolean.TRUE));
        }
        int i2 = 0;
        if (arrayList != null && arrayList.size() > 0) {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        if (i2 != 0) {
            ThreadUtil.postOnBgThread(new e(24, this));
        }
    }

    private String getTitleString(int i2) {
        if (i2 > 1) {
            return getContext().getString(R.string.n_stories_will_be_deleted, new Object[]{Integer.valueOf(i2)});
        }
        return getContext().getString(R.string.a_story_will_be_deleted);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer[] lambda$deleteStories$1(int i2) {
        return new Integer[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteStories$3() {
        boolean z;
        try {
            if (SdkConfig.atLeast(SdkConfig.GED.V)) {
                Integer[] numArr = (Integer[]) Arrays.stream(this.mItems).map(new o(20)).toArray(new C0578a(2));
                z = DbCompat.storyApi().updateStoryType(numArr, StoryType.DELETED_STORY.getValue());
                StoryGenApi.create().setBlockListAtTrip(new ArrayList(Arrays.asList(numArr)), (ArrayList<Long>) null);
            } else {
                z = DbCompat.storyApi().deleteStory(this.mItems, true);
            }
            if (z) {
                Arrays.stream(this.mItems).filter(new d(12)).forEach(new T3.e(9));
                StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
                getBlackboard().postEvent(EventMessage.obtain(1057));
                getBlackboard().postEvent(EventMessage.obtain(1003));
                getBlackboard().post("command://DeleteStories", (Object) null);
                if (PreferenceFeatures.OneUi30.MEMORIES) {
                    Blackboard.publishGlobal("data://user/storyUpdated", (Object) null);
                    return;
                }
                return;
            }
            Log.e(this.TAG, "delete story is failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ FileItemInterface[] lambda$onExecute$0(int i2) {
        return new FileItemInterface[i2];
    }

    public String getAnalyticsDetail() {
        MediaItem[] selectedItems;
        EventContext handler = getHandler();
        if (handler == null || (selectedItems = handler.getSelectedItems()) == null) {
            return "0";
        }
        return String.valueOf(selectedItems.length);
    }

    public String getEventId() {
        if (getHandler().isSelectionMode()) {
            return AnalyticsEventId.EVENT_MENU_BOTTOM_DELETE_STORY.toString();
        }
        return AnalyticsEventId.EVENT_MENU_DELETE_STORY.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        MediaItem[] mediaItemArr = objArr[0];
        this.mItems = (FileItemInterface[]) Arrays.stream(mediaItemArr).filter(new C0571z(4)).toArray(new C0578a(1));
        boolean z = true;
        if (mediaItemArr.length != 1 || mediaItemArr[0] == null) {
            z = false;
        }
        UriBuilder appendArg = new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", getTitleString(mediaItemArr.length)).appendArg("option1", getContext().getString(R.string.delete)).appendArg("screenId", AnalyticsScreenId.SCREEN_EVENT_VIEW_SELECTION.toString()).appendArg("option1EventId", AnalyticsEventId.EVENT_DELETE_STORY_DELETE.toString()).appendArg("cancelEventId", AnalyticsEventId.EVENT_DELETE_STORY_CANCEL.toString());
        if (z) {
            str = String.valueOf(MediaItemStory.getStorySaType(mediaItemArr[0]));
        } else {
            str = null;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(appendArg.appendArg("optionEventDetail", str).build()).setOnDataCompleteListener(new a(0, this)).start();
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 && SdkConfig.atLeast(SdkConfig.GED.T)) {
            getBlackboard().postEvent(EventMessage.obtain(1093, Boolean.FALSE));
        }
    }
}
