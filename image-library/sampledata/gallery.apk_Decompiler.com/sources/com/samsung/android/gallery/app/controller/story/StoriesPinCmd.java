package com.samsung.android.gallery.app.controller.story;

import B8.g;
import Fa.C0571z;
import O3.o;
import T8.C0578a;
import U3.f;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinCmd extends BaseCommand {
    protected boolean mIsPin;

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$onExecute$0(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$pinStory$1(MediaItem[] mediaItemArr, boolean z) {
        String str;
        ArrayList<MediaItem> updateStoryPin = updateStoryPin(mediaItemArr, z);
        if (!updateStoryPin.isEmpty()) {
            Blackboard blackboard = getBlackboard();
            if (z) {
                str = "command://StoriesFavoritePin";
            } else {
                str = "command://StoriesFavoriteUnpin";
            }
            blackboard.post(str, updateStoryPin);
            StoryUpdateNotifier.getInstance().notifyStory(getContext(), false);
        }
        pinCompleted(!updateStoryPin.isEmpty());
        if (getHandler().isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ long lambda$updateStoryPin$2(boolean z, int i2) {
        if (z) {
            return System.currentTimeMillis() + ((long) i2);
        }
        return -1;
    }

    private void pinStory(MediaItem[] mediaItemArr, boolean z) {
        ThreadUtil.postOnBgThread(new g((Object) this, (Object) mediaItemArr, z, 6));
    }

    private ArrayList<MediaItem> updateStoryPin(MediaItem[] mediaItemArr, boolean z) {
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            return new ArrayList<>();
        }
        List list = (List) IntStream.range(0, mediaItemArr.length).mapToLong(new f(z)).boxed().collect(Collectors.toList());
        ArrayList<MediaItem> updateStoryPin = DbCompat.storyApi().updateStoryPin(mediaItemArr, list);
        List list2 = (List) updateStoryPin.stream().map(new o(25)).collect(Collectors.toList());
        for (int i2 = 0; i2 < mediaItemArr.length; i2++) {
            if (list2.contains(Integer.valueOf(MediaItemStory.getStoryId(mediaItemArr[i2])))) {
                MediaItemStory.setStoryFavorite(mediaItemArr[i2], ((Long) list.get(i2)).longValue());
            }
        }
        return updateStoryPin;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_PICTURES_PIN.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        this.mIsPin = objArr[1].booleanValue();
        if (mediaItemArr != null && mediaItemArr.length > 0) {
            pinStory((MediaItem[]) Arrays.stream(mediaItemArr).filter(new C0571z(4)).toArray(new C0578a(7)), this.mIsPin);
        }
    }

    public void pinCompleted(boolean z) {
    }
}
