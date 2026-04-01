package com.samsung.android.gallery.app.controller.story;

import A4.C0369d;
import A6.a;
import Ab.b;
import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateStoryCmd extends BaseCommand {
    private void createStory(String str, MediaItem[] mediaItemArr) {
        Context context = getContext();
        int length = mediaItemArr.length;
        String str2 = this.TAG;
        Log.d(str2, "createStory selected=" + length);
        SimpleThreadPool.getInstance().execute(new a((Object) this, (Object) context, (Object) new ArrayList(Arrays.asList(mediaItemArr)), (Object) str, 22));
    }

    private MediaItem getStoryAlbumHeaderItem(String str, int i2) {
        Cursor query = DbCompat.query(DbKey.STORY_FILES, new C0369d(i2, 12));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    MediaItem create = MediaItemBuilder.create(query);
                    create.setTitle(str);
                    create.setCount(query.getCount());
                    query.close();
                    return create;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query == null) {
            return null;
        }
        query.close();
        return null;
        throw th;
    }

    private boolean isFromStories() {
        String str;
        if (getHandler() != null) {
            str = getHandler().getLocationKey();
        } else {
            str = null;
        }
        return LocationKey.isStories(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createStory$0(Context context, ArrayList arrayList, String str) {
        createStory(context, arrayList, str, 0, StoryType.MANUAL);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveTo$1(String str, int i2) {
        getBlackboard().post("command://MoveURL", new StoryUriBuilder(getBlackboard(), (ThumbnailInterface) getStoryAlbumHeaderItem(str, i2)).withStoryId(i2).appendArg("fromNobody", Boolean.TRUE).appendArg("returnTransition", Boolean.valueOf(isFromStories())).build());
    }

    private void moveTo(String str, int i2) {
        Log.d(this.TAG, "moveTo", Integer.valueOf(i2));
        ThreadUtil.postOnBgThread(new b((Object) this, (Object) str, i2, 27));
    }

    private static void updateUserCuration(int i2, ArrayList<FileItemInterface> arrayList) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            DbCompat.storyApi().updateUserCuration(i2, arrayList, 2);
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CREATE_STORY.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        createStory(objArr[0], objArr[1]);
    }

    public void createStory(Context context, ArrayList<FileItemInterface> arrayList, String str, int i2, StoryType storyType) {
        ArrayList<FileItemInterface> arrayList2 = arrayList;
        String str2 = str;
        int createStory = DbCompat.storyApi().createStory(arrayList2, str2, 0, StoryType.MANUAL, (String) null, arrayList.size());
        if (createStory >= 0) {
            updateUserCuration(createStory, arrayList2);
            StoryUpdateNotifier.getInstance().notifyStory(context, true);
            moveTo(str2, createStory);
            return;
        }
        String str3 = this.TAG;
        Log.e(str3, "createStory failed, selected=" + arrayList2.size());
    }
}
