package com.samsung.android.gallery.app.controller.story;

import C4.c;
import Qb.e;
import S3.d;
import T8.C0578a;
import android.content.ContentProviderResult;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UpdateStoryFavoriteCmd extends BaseCommand {
    private int mType;
    private int mViewPosition;

    private void announceVoiceAssistant(boolean z) {
        int i2;
        Context context = getContext();
        Context context2 = getContext();
        if (z) {
            i2 = R.string.item_added_to_favorites;
        } else {
            i2 = R.string.item_removed_from_favorites;
        }
        SeApiCompat.announceVoiceAssistant(context, context2.getString(i2));
    }

    private boolean byMenuClick() {
        return LocationKey.isStoryHighlight(getHandler().getLocationKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0() {
        Utils.showToast((Context) getActivity(), (int) R.string.added_to_favorites);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(MediaItem[] mediaItemArr, boolean z, MediaItem mediaItem, String str) {
        int i2 = this.mType;
        boolean z3 = false;
        if (i2 == 1) {
            announceVoiceAssistant(false);
            if (needNotifyStory(updateStoryFavoriteInfo(mediaItemArr, false))) {
                for (MediaItem storyFavorite : mediaItemArr) {
                    MediaItemStory.setStoryFavorite(storyFavorite, 0);
                }
                StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
            }
            if (this.mViewPosition >= 0) {
                getBlackboard().postEvent(EventMessage.obtain(1057, new ArrayList(Collections.singletonList(Integer.valueOf(this.mViewPosition)))));
            }
            sendLoggingEvent(false, z, mediaItem);
        } else if (i2 == 0) {
            if (MediaItemStory.getStoryFavorite(mediaItemArr[0]) <= 0) {
                z3 = true;
            }
            announceVoiceAssistant(z3);
            updateInfo(mediaItemArr, z3);
            sendLoggingEvent(z3, z, mediaItem);
            if (z3 && z) {
                if (LocationKey.isStoriesMatch(str) || "location://search".equals(str)) {
                    ThreadUtil.postOnUiThread(new e(26, this));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ FileItemInterface[] lambda$updateStoryFavoriteInfo$2(int i2) {
        return new FileItemInterface[i2];
    }

    private boolean needNotifyStory(ContentProviderResult[] contentProviderResultArr) {
        if (contentProviderResultArr != null) {
            for (ContentProviderResult contentProviderResult : contentProviderResultArr) {
                if (contentProviderResult.count.intValue() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void sendLoggingEvent(boolean z, boolean z3, MediaItem mediaItem) {
        AnalyticsEventId analyticsEventId;
        String str;
        if (z3) {
            postAnalyticsLog(AnalyticsEventId.EVENT_STORIES_FAVORITE_ICON_SELECT, String.valueOf(MediaItemStory.getStorySaType(mediaItem)));
            return;
        }
        if (byMenuClick()) {
            analyticsEventId = AnalyticsEventId.EVENT_STORY_HIGHLIGHT_ADD_TO_FAVORITE;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_STORIES_FAVORITE_ICON_SELECT;
        }
        if (z) {
            str = AnalyticsDetail.STORY_FAVORITE_ICON_ADD.toString();
        } else {
            str = AnalyticsDetail.STORY_FAVORITE_ICON_EXCLUDE.toString();
        }
        postAnalyticsLog(analyticsEventId, str);
    }

    private void updateInfo(MediaItem[] mediaItemArr, boolean z) {
        long j2;
        if (needNotifyStory(updateStoryFavoriteInfo(mediaItemArr, z))) {
            if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
                getBlackboard().post("command:///RefreshWithoutDelay", (Object) null);
            }
            for (MediaItem mediaItem : mediaItemArr) {
                if (z) {
                    j2 = System.currentTimeMillis();
                } else {
                    j2 = 0;
                }
                MediaItemStory.setStoryFavorite(mediaItem, j2);
            }
            StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
        }
        getBlackboard().postEvent(EventMessage.obtain(1089, this.mViewPosition, this.mType, Boolean.valueOf(z)));
        getBlackboard().publish("command:///event_command", new Object[]{"update_favorite"});
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        int i2;
        MediaItem[] mediaItemArr = objArr[0];
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "Item is null");
            return;
        }
        this.mType = objArr[1].intValue();
        if (objArr.length > 2) {
            i2 = objArr[2].intValue();
        } else {
            i2 = -1;
        }
        this.mViewPosition = i2;
        MediaItem mediaItem = mediaItemArr[0];
        boolean isTransitoryType = StoryType.isTransitoryType(MediaItemStory.getStoryType(mediaItem));
        SimpleThreadPool.getInstance().execute(new c(1, this, mediaItemArr, mediaItem, ArgumentsUtil.removeArgs(eventContext.getLocationKey()), isTransitoryType));
    }

    public ContentProviderResult[] updateStoryFavoriteInfo(FileItemInterface[] fileItemInterfaceArr, boolean z) {
        FileItemInterface[] fileItemInterfaceArr2 = (FileItemInterface[]) Arrays.stream(fileItemInterfaceArr).filter(new d(5)).toArray(new C0578a(8));
        HashMap hashMap = new HashMap();
        for (FileItemInterface fileItemInterface : fileItemInterfaceArr2) {
            if (StoryType.isTransitoryType(MediaItemStory.getStoryType(fileItemInterface))) {
                hashMap.put(Integer.valueOf(MediaItemStory.getStoryId(fileItemInterface)), Integer.valueOf(MediaItemStory.getStoryType(fileItemInterface) - 30));
            }
        }
        return DbCompat.storyApi().updateStoryFavoriteInfo(fileItemInterfaceArr2, hashMap, z);
    }
}
