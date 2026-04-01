package com.samsung.android.gallery.app.ui.list.stories.highlight;

import A4.C0386v;
import C3.C0391a;
import G6.a;
import T8.C0578a;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.externals.StoryHighlightShareViaCmd;
import com.samsung.android.gallery.app.controller.story.ShareStoryToAlbumCmd;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.stories.headeritem.HeaderItem;
import com.samsung.android.gallery.app.ui.list.stories.headeritem.HeaderItemBuilder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.list.StoryHighlightMenuHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.Def;
import com.samsung.o3dp.lib3dphotography.i;
import g6.g;
import g6.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightPresenter<V extends IStoryHighlightView> extends MvpBasePresenter<V> {
    protected final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new h(StoryHighlightPresenter.this, 2));
        }
    };
    private final EventHandler mEventHandler;
    private final ExportHandler mExportHandler;
    private MediaData mMediaData;
    private final MenuUpdater mMenuUpdater;
    private final MusicPickerHandler mMusicPickerHandler;
    private HeaderItem mStoryHeaderItem;
    private final ToolbarUpdater mToolbarUpdater;

    public StoryHighlightPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        this.mMenuUpdater = new MenuUpdater(v);
        this.mToolbarUpdater = new ToolbarUpdater(v);
        this.mEventHandler = v.getEventHandler();
        this.mExportHandler = new ExportHandler(v, this);
        this.mMusicPickerHandler = new MusicPickerHandler(v);
    }

    private void closeStoryHeaderItem() {
        HeaderItem headerItem = this.mStoryHeaderItem;
        if (headerItem != null) {
            headerItem.close();
            this.mStoryHeaderItem = null;
        }
    }

    private void handleEventChangePlayStateExternal(EventMessage eventMessage) {
        Event event;
        Event event2;
        boolean booleanValue = ((Boolean) eventMessage.obj).booleanValue();
        EventHandler eventHandler = this.mEventHandler;
        if (booleanValue) {
            event = Event.PLAYER_RESUME;
        } else {
            event = Event.PLAYER_PAUSE;
        }
        eventHandler.lambda$postEvent$0(event, new Object[0]);
        EventHandler eventHandler2 = this.mEventHandler;
        if (booleanValue) {
            event2 = Event.BGM_RESUME;
        } else {
            event2 = Event.BGM_PAUSE;
        }
        eventHandler2.lambda$postEvent$0(event2, new Object[0]);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getAllItems$2(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChangedOnUi$0() {
        if (isDestroyed() || ((IStoryHighlightView) this.mView).isBackPressed()) {
            Log.d("StoryHighlightPresenter", "maybe finished");
            return;
        }
        ((IStoryHighlightView) this.mView).handlePostEvent(Event.FRAGMENT_RESET_TRANSITION_INFO, new Object[0]);
        getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateNewBadgeState$1(MediaItem mediaItem) {
        StoryHelper.updateStoryViewed(getContext(), mediaItem, false);
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(1077, Integer.valueOf(mediaItem.getAlbumID())));
    }

    /* access modifiers changed from: private */
    public MediaItem loadMediaItemForShare(int i2) {
        MediaItem readCache = this.mMediaData.readCache(i2);
        if (MediaItemStory.isLiveEffectItem(readCache)) {
            Optional.ofNullable(getContext()).ifPresent(new a(readCache, 12));
        }
        return readCache;
    }

    /* access modifiers changed from: private */
    public void onDataChangedOnUi() {
        MediaData mediaData = getMediaData();
        if (mediaData == null || mediaData.getCount() == 0) {
            int i2 = -1;
            if (((IStoryHighlightView) this.mView).getOptions().isFromExternal()) {
                if (mediaData != null) {
                    i2 = mediaData.getCount();
                }
                Log.d("StoryHighlightPresenter", "empty suicide", Integer.valueOf(i2));
                this.mBlackboard.post("command://request_suicide", (Object) null);
                return;
            }
            boolean isEnterTransitionFinished = SharedTransition.isEnterTransitionFinished(this.mBlackboard);
            if (mediaData != null) {
                i2 = mediaData.getCount();
            }
            Log.d("StoryHighlightPresenter", "empty finish", Integer.valueOf(i2), Boolean.valueOf(isEnterTransitionFinished));
            if (isEnterTransitionFinished) {
                ((IStoryHighlightView) this.mView).handlePostEvent(Event.FRAGMENT_RESET_TRANSITION_INFO, new Object[0]);
                getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
                return;
            }
            ThreadUtil.postOnUiThreadDelayed(new h(this, 1), 1000);
            return;
        }
        Log.d("StoryHighlightPresenter", "onDataChangedOnUi", Integer.valueOf(mediaData.getCount()));
        ((IStoryHighlightView) this.mView).onDataChangedOnUi();
    }

    /* access modifiers changed from: private */
    public void onHeaderUpdated(MediaItem mediaItem) {
        ((IStoryHighlightView) this.mView).handlePostEvent(Event.FRAGMENT_HEADER_UPDATE, mediaItem);
        ((IStoryHighlightView) this.mView).invalidateOptionsMenu();
        updateNewBadgeState(mediaItem);
    }

    /* access modifiers changed from: private */
    public void onThemeUpdatedFromOther(Object obj, Bundle bundle) {
        LaunchIntent launchIntent = (LaunchIntent) ((IStoryHighlightView) this.mView).getBlackboard().read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isViewStoryPicture()) {
            this.mEventHandler.postEvent(Event.CHECK_THEME_UPDATE, (Object[]) obj);
        }
    }

    private void onUpdatedFavorite() {
        V v = this.mView;
        if (v != null && ((IStoryHighlightView) v).getOptions().isFromTransitoryStory()) {
            int storyId = MediaItemStory.getStoryId(getHeaderItem());
            updateLocationKey(new UriBuilder(C0086a.i(storyId, "location://story/albums/storyHighlight/")).appendArg("id", storyId).appendArg("from_location", ArgumentsUtil.getArgValue(getLocationKey(), "from_location")).build());
            closeStoryHeaderItem();
            openStoryHeaderItem();
            ((IStoryHighlightView) this.mView).handlePostEvent(Event.FRAGMENT_TRANSITION_INFO_CHANGED, new Object[0]);
        }
    }

    private void openMediaData(String str) {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(str);
        this.mMediaData = open;
        open.register(this.mDataChangeListener);
    }

    private void openStoryHeaderItem() {
        if (this.mStoryHeaderItem == null) {
            this.mStoryHeaderItem = new HeaderItemBuilder(this.mView).setHeaderUpdateListener(new g(0, this)).build().open();
        }
    }

    private void prepareExtendedShareInternal() {
        if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET) {
            int realCount = this.mMediaData.getRealCount();
            ShareProvider.prepareExtendedShareList(getContext(), this.mBlackboard, new b(19, this), realCount, 0, realCount, (Runnable) null);
        }
    }

    private void reopenMediaData(MediaItem mediaItem) {
        String build = new StoryUriBuilder(getBlackboard(), mediaItem, false).withFavorite(ArgumentsUtil.getArgValue(getLocationKey(), "fromStoryFavorite", false)).withFromLocation(ArgumentsUtil.getArgValue(getLocationKey(), "from_location")).appendArg("story_trip_in_year", Boolean.valueOf(ArgumentsUtil.getArgValue(getLocationKey(), "story_trip_in_year", false))).build();
        closeMediaData();
        updateLocationKey(build);
        openMediaData(build);
        closeStoryHeaderItem();
        openStoryHeaderItem();
        updateNewBadgeState(mediaItem);
    }

    /* access modifiers changed from: private */
    public void saveHistory() {
        MediaItem headerItem;
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 && (headerItem = getHeaderItem()) != null && !isDestroyed()) {
            LocalProviderDebugHelper.addOperationHistory(HistoryTable.ActionKeyword.VIEW_STORY, Collections.singletonList(headerItem));
        }
    }

    private void updateLocationKey(String str) {
        ((IStoryHighlightView) this.mView).handlePostEvent(Event.FRAGMENT_UPDATE_LOCATION_KEY, str);
    }

    private void updateNewBadgeState(MediaItem mediaItem) {
        if (mediaItem != null && StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(mediaItem))) {
            MediaItemStory.setStoryNotifyStatus(mediaItem, 1);
            ThreadUtil.postOnBgThreadDelayed(new i(23, this, mediaItem), 1000);
        }
    }

    public void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://stories/defaultTheme/changed", new C0391a(26, this)));
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create(this.mBlackboard, (IStoryHighlightView) this.mView);
    }

    public MenuHandler createMenuHandler() {
        return new StoryHighlightMenuHandler();
    }

    public int findHeaderItemPosition() {
        HeaderItem headerItem = this.mStoryHeaderItem;
        if (headerItem != null) {
            return headerItem.positionInStories();
        }
        return -1;
    }

    public MediaItem[] getAllItems() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            return new MediaItem[0];
        }
        String str = hashTag() + ".getAllItems";
        try {
            this.mMediaData.acquireReadLock(str);
            IntStream range = IntStream.range(0, this.mMediaData.getCount());
            MediaData mediaData2 = this.mMediaData;
            Objects.requireNonNull(mediaData2);
            return (MediaItem[]) range.mapToObj(new C0386v(4, mediaData2)).toArray(new C0578a(17));
        } finally {
            this.mMediaData.releaseReadLock(str);
        }
    }

    public MediaItem getCurrentItem() {
        PreviewViewHolder currentViewHolder = this.mEventHandler.getCurrentViewHolder();
        if (currentViewHolder != null) {
            return currentViewHolder.getMediaItem();
        }
        return null;
    }

    public Object getEventContextData(String str) {
        PreviewViewHolder currentViewHolder;
        if (!"app_transition_view".equals(str) || (currentViewHolder = this.mEventHandler.getCurrentViewHolder()) == null) {
            return null;
        }
        return currentViewHolder.getImage();
    }

    public MediaItem getHeaderItem() {
        HeaderItem headerItem = this.mStoryHeaderItem;
        if (headerItem != null) {
            return headerItem.getHeaderItem();
        }
        return null;
    }

    public MediaData getMediaData() {
        return this.mMediaData;
    }

    public MediaItem[] getSelectedItems() {
        PreviewViewHolder currentViewHolder;
        if (isDestroyed() || (currentViewHolder = this.mEventHandler.getCurrentViewHolder()) == null) {
            return null;
        }
        return new MediaItem[]{currentViewHolder.getMediaItem()};
    }

    public MediaData getStoriesData() {
        HeaderItem headerItem = this.mStoryHeaderItem;
        if (headerItem != null) {
            return headerItem.getStoriesData();
        }
        return null;
    }

    public MediaItem getStoryAlbumById(int i2) {
        HeaderItem headerItem = this.mStoryHeaderItem;
        if (headerItem != null) {
            return headerItem.getStoryById(i2);
        }
        return null;
    }

    public ToolbarUpdater getToolbarUpdater() {
        return this.mToolbarUpdater;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (!this.mExportHandler.handleEvent(eventMessage) && !this.mMusicPickerHandler.handleEvent(eventMessage)) {
            int i2 = eventMessage.what;
            if (i2 == 1089) {
                onUpdatedFavorite();
            } else if (i2 == 1090) {
                prepareExtendedShare();
                if (SdkConfig.atLeast(SdkConfig.GED.V)) {
                    new StoryHighlightShareViaCmd().addParameter("story_theme", ((IStoryHighlightView) this.mView).getEventHandler().getEffectTheme().toString()).addParameter("story_filter", ((IStoryHighlightView) this.mView).getEventHandler().getFilter().toString()).addParameter("story_bgm", BgmExtraInfo.getBgmExtraInfoString(((IStoryHighlightView) this.mView).getEventHandler().getBgmExtraInfo())).execute(this, getAllItems(), null);
                } else {
                    new ShareViaCmd().execute(this, new MediaItem[]{getCurrentItem()}, null);
                }
                return true;
            } else if (i2 == 1093) {
                handleEventChangePlayStateExternal(eventMessage);
                return true;
            } else if (i2 == 1121) {
                this.mEventHandler.lambda$postEvent$0(Event.DOWNLOAD_ALL_BGM, new Object[0]);
                return true;
            } else if (i2 == 3023) {
                this.mExportHandler.resetPendingShare();
                return true;
            } else if (i2 == 1128) {
                new StoryToTimeline(this.mBlackboard).jump((IStoryHighlightView) this.mView);
                postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_VIEW_ORIGINAL_PICTURES);
                return true;
            } else if (i2 != 1129) {
                return super.handleEvent(eventMessage);
            } else {
                new ShareStoryToAlbumCmd().execute(this, this.mEventHandler.getEffectTheme().name(), this.mEventHandler.getBgmExtraInfo(), this.mEventHandler.getFilter().getRawName());
                return true;
            }
        }
        return true;
    }

    public void handlePostEvent(Event event, Object... objArr) {
        this.mToolbarUpdater.handleEvent(event, objArr);
        if (event == Event.BOTTOM_SHEET_STATE_CHANGED) {
            if (this.mEventHandler.isBottomSheetVisible() || this.mEventHandler.isBottomSheetHidden()) {
                ((IStoryHighlightView) this.mView).handlePostEvent(Event.FRAGMENT_UPDATE_KEEP_SCREEN_ON, new Object[0]);
            }
        } else if (event == Event.PLAYER_KEEP_PAUSE) {
            ((IStoryHighlightView) this.mView).handlePostEvent(Event.FRAGMENT_UPDATE_KEEP_SCREEN_ON, new Object[0]);
            ((IStoryHighlightView) this.mView).invalidateOptionsMenu();
        } else if (event == Event.CHANGE_STORY && objArr != null) {
            reopenMediaData(objArr[0]);
            ((IStoryHighlightView) this.mView).handlePostEvent(Event.FRAGMENT_STORY_CHANGED, new Object[0]);
            this.mExportHandler.reset();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        closeMediaData();
    }

    public void onViewAttach() {
        super.onViewAttach();
        openMediaData(getLocationKey());
        openStoryHeaderItem();
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mToolbarUpdater.handleEvent(Event.UPDATE_BGM_NAME, this.mEventHandler.getBgmExtraInfo());
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        closeStoryHeaderItem();
    }

    public void onViewResume() {
        super.onViewResume();
        this.mExportHandler.resume();
        ThreadUtil.postOnBgThreadDelayed(new h(this, 0), Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
    }

    public void prepareExtendedShare() {
        if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET && !isDestroyed()) {
            MediaData mediaData = this.mMediaData;
            if (mediaData == null || mediaData.getRealCount() <= 0) {
                ShareProvider.clearExtendedShareList(((IStoryHighlightView) this.mView).getActivity());
                Log.w("StoryHighlightPresenter", "prepareExtendedShare fail due to no share data");
                return;
            }
            prepareExtendedShareInternal();
            Log.w((CharSequence) "StoryHighlightPresenter", "prepareExtendedShare", Integer.valueOf(this.mMediaData.getRealCount()));
        }
    }

    public void prepareOptionsMenu(Menu menu) {
        this.mMenuUpdater.prepareOptionsMenu(menu, getMenuDataBinding());
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
