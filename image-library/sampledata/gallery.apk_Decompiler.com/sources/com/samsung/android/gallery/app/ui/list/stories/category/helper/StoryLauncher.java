package com.samsung.android.gallery.app.ui.list.stories.category.helper;

import U5.b;
import android.content.Context;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryLauncher {
    private final Blackboard mBlackboard;
    private final Context mContext;
    private HashMap<String, Object> mExtra;
    private String mFromLocation;
    private boolean mFromOnDemand;
    private boolean mIsRecap;
    private ThumbnailInterface mItem;
    private int mPosition;
    private String mScreenId;

    public StoryLauncher(IMvpBaseView iMvpBaseView) {
        this(iMvpBaseView.getContext(), iMvpBaseView.getBlackboard());
        setScreenId(iMvpBaseView.getScreenId());
    }

    private HashMap<String, Object> getExtra() {
        if (this.mExtra == null) {
            this.mExtra = new HashMap<>();
        }
        return this.mExtra;
    }

    private static int getTransitionViewRadius(Context context, String str) {
        return ItemProperty.getItemRadius(context, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchRecapViewer$1(StoryUriBuilder storyUriBuilder, String str) {
        storyUriBuilder.appendArg(str, this.mExtra.get(str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchStoryHighlight$0() {
        String str;
        updateNewStoryState(this.mBlackboard, this.mItem);
        this.mBlackboard.post("command://MoveURL", new StoryUriBuilder(this.mBlackboard, this.mItem).withPosition(this.mPosition).withBlurBitmap().withOnDemandVi(this.mFromOnDemand).withFromLocation(this.mFromLocation).build());
        this.mBlackboard.publish("data://story_transition_view_radius", new int[]{getTransitionViewRadius(this.mContext, MediaItemStory.getStoryCategoryKey(this.mItem)), 0});
        Integer valueOf = Integer.valueOf(MediaItemStory.getStoryId(this.mItem));
        Integer valueOf2 = Integer.valueOf(MediaItemStory.getStoryType(this.mItem));
        Integer valueOf3 = Integer.valueOf(MediaItemStory.getStorySaType(this.mItem));
        if (this.mFromOnDemand) {
            str = "O";
        } else {
            str = "o";
        }
        Log.d("LauncherHelper", "move to highlight id", valueOf, valueOf2, valueOf3, str, Integer.valueOf(this.mPosition));
    }

    private void launchRecapViewer() {
        if (this.mItem.isBroken()) {
            Log.e("LauncherHelper", "fail start");
            return;
        }
        updateNewStoryState(this.mBlackboard, this.mItem);
        StoryUriBuilder appendArg = new StoryUriBuilder(this.mBlackboard, this.mItem, true, "location://recap").withPosition(this.mPosition).withFromLocation(this.mFromLocation).appendArg("media_item", BlackboardUtils.publishMediaItem(this.mBlackboard, this.mItem));
        HashMap<String, Object> hashMap = this.mExtra;
        if (hashMap != null) {
            hashMap.keySet().forEach(new b(11, this, appendArg));
        }
        this.mBlackboard.post("command://MoveURL", appendArg.build());
    }

    private void launchStoryHighlight() {
        SimpleThreadPool.getInstance().execute(new V3.b(20, this));
    }

    public static void moveToCategoryList(Blackboard blackboard, String str) {
        blackboard.post("command://MoveURL", str);
        Log.d("LauncherHelper", "moveToCategoryList", str);
    }

    private void postAnalyticsLog() {
        if (!this.mFromOnDemand && this.mScreenId != null) {
            AnalyticsLogger.getInstance().postLog(this.mScreenId, AnalyticsEventId.EVENT_STORY_SELECT.toString(), (long) this.mItem.getCount(), String.valueOf(MediaItemStory.getStorySaType(this.mItem)));
        }
    }

    private static void updateNewStoryState(Blackboard blackboard, FileItemInterface fileItemInterface) {
        if (StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(fileItemInterface))) {
            MediaItemStory.setStoryNotifyStatus(fileItemInterface, 1);
            StoryHelper.updateStoryViewed(AppResources.getAppContext(), fileItemInterface, false);
            blackboard.postBroadcastEvent(EventMessage.obtain(1077, Integer.valueOf(fileItemInterface.getAlbumID())));
        }
    }

    public StoryLauncher appendArgs(String str, Object obj) {
        getExtra().put(str, obj);
        return this;
    }

    public void execute() {
        if (this.mIsRecap) {
            launchRecapViewer();
        } else {
            launchStoryHighlight();
        }
        postAnalyticsLog();
    }

    public StoryLauncher setData(ThumbnailInterface thumbnailInterface, int i2) {
        this.mItem = thumbnailInterface;
        this.mPosition = i2;
        return this;
    }

    public StoryLauncher setFromLocation(String str) {
        this.mFromLocation = str;
        return this;
    }

    public StoryLauncher setFromOnDemand(boolean z) {
        this.mFromOnDemand = z;
        return this;
    }

    public StoryLauncher setRecap(boolean z) {
        this.mIsRecap = z;
        return this;
    }

    public StoryLauncher setScreenId(String str) {
        this.mScreenId = str;
        return this;
    }

    public StoryLauncher(Context context, Blackboard blackboard) {
        this.mContext = context;
        this.mBlackboard = blackboard;
    }
}
