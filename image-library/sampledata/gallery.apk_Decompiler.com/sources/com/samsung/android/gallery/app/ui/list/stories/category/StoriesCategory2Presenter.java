package com.samsung.android.gallery.app.ui.list.stories.category;

import Qb.e;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.story.LaunchOnDemandStoryCmd;
import com.samsung.android.gallery.app.controller.story.SaveRecapVideoCmd;
import com.samsung.android.gallery.app.controller.story.SaveTransitoryToOthersCmd;
import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.category.IStoriesCategory2View;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.StoryLauncher;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCategory2Presenter<V extends IStoriesCategory2View> extends StoriesPinchViewPresenter<V> {
    private final StoriesCategoryPreviewDelegate mPreviewDelegate;
    private final TopColorEffectHandler mTopColorEffect;

    public StoriesCategory2Presenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        TopColorEffectHandler topColorEffectHandler;
        StoriesCategoryPreviewDelegate storiesCategoryPreviewDelegate = new StoriesCategoryPreviewDelegate();
        this.mPreviewDelegate = storiesCategoryPreviewDelegate;
        storiesCategoryPreviewDelegate.setPlayable(new b(this));
        OnDemandSuggestionDataManager.getInstance().init();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect)) {
            topColorEffectHandler = new TopColorEffectHandler(v);
        } else {
            topColorEffectHandler = null;
        }
        this.mTopColorEffect = topColorEffectHandler;
    }

    /* access modifiers changed from: private */
    public boolean isPreviewPlayable() {
        if (!((IStoriesCategory2View) this.mView).isViewResumed() || !((IStoriesCategory2View) this.mView).isViewActive()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$1(Object obj, Bundle bundle) {
        ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.REDUCED_TRANSPARENCY_CHANGED, new Object[0]);
    }

    private void launchDemandStory(ListViewHolder listViewHolder) {
        Object[] objArr;
        if (this.mView != null && (objArr = (Object[]) listViewHolder.itemView.getTag()) != null) {
            Integer num = (Integer) objArr[0];
            num.intValue();
            listViewHolder.itemView.setTag((Object) null);
            new LaunchOnDemandStoryCmd().execute(((IStoriesCategory2View) this.mView).getPresenter(), (String) objArr[1], num);
        }
    }

    /* access modifiers changed from: private */
    public void onDateTimeChanged(Object obj, Bundle bundle) {
        if (!isDestroyed()) {
            ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.ON_DATE_TIME_CHANGED, new Object[0]);
        }
    }

    private void scrollCompletelyVisible(IStoriesCategory2View iStoriesCategory2View, MediaItem mediaItem, int i2) {
        new InboundScroller(iStoriesCategory2View).setDataPosition(i2).setDataLocationKey(MediaItemStory.getStoryCategoryKey(mediaItem)).setHeader(((IStoriesCategory2View) this.mView).getHeader()).setDelay(500).start();
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://setting/system/reduce_transparency_and_blur", new k(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://event/dateTimeChanged", new k(this, 1)).setWorkingOnUI());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.samsung.android.gallery.support.blackboard.SubscriberListener, java.lang.Object] */
    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            arrayList.add(new SubscriberInfo("global://setting/advanced/LocationAuth", new Object()));
        }
    }

    public int getTitleStringResId() {
        String locationKey = getLocationKey();
        if (LocationKey.isStoriesCategory(locationKey)) {
            return StoryCategory.getCategoryTitle(locationKey);
        }
        return super.getTitleStringResId();
    }

    public boolean handleCategoryItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        String locationKey;
        boolean z;
        AnalyticsEventId analyticsEventId;
        if (mediaItem != null) {
            locationKey = MediaItemStory.getStoryCategoryKey(mediaItem);
        } else {
            locationKey = StoriesCatViewHolderFactory.getLocationKey(listViewHolder.getViewType());
        }
        String str = locationKey;
        StringCompat stringCompat = this.TAG;
        Integer valueOf = Integer.valueOf(listViewHolder.getViewType());
        Integer valueOf2 = Integer.valueOf(listViewHolder.getViewPosition());
        Integer valueOf3 = Integer.valueOf(i2);
        Integer valueOf4 = Integer.valueOf(i7);
        boolean z3 = false;
        if (mediaItem != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(stringCompat, "handleCategoryItemClick", valueOf, valueOf2, valueOf3, valueOf4, str, Boolean.valueOf(z));
        if (isSelectionMode()) {
            Optional.ofNullable(((IStoriesCategory2View) this.mView).getHeader()).ifPresent(new j(mediaItem, 1));
            ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.SELECT_ITEM, Integer.valueOf(i2));
            ((IStoriesCategory2View) this.mView).invalidateToolbar();
            return true;
        } else if (i7 == 1001) {
            StoryLauncher.moveToCategoryList(getBlackboard(), str);
            IStoriesCategory2View iStoriesCategory2View = (IStoriesCategory2View) this.mView;
            if ("location://stories/category/trip".equals(str)) {
                analyticsEventId = AnalyticsEventId.EVENT_STORIES_TRIP_VIEW_MORE;
            } else {
                analyticsEventId = AnalyticsEventId.EVENT_STORIES_CREATION_VIEW_MORE;
            }
            iStoriesCategory2View.postAnalyticsLog(analyticsEventId);
            return true;
        } else if (i7 == 1002) {
            if (MediaItemStory.isRecap(mediaItem)) {
                new SaveRecapVideoCmd().execute(this, mediaItem, Boolean.FALSE);
                return true;
            }
            new SaveTransitoryToOthersCmd().execute(this, mediaItem);
            return true;
        } else if (i7 == 6) {
            if (MediaItemStory.getStoryFavorite(mediaItem) <= 0) {
                z3 = true;
            }
            listViewHolder.updateDecoration(64, Boolean.valueOf(z3));
            ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.EVENT_FAVORITE, mediaItem, Integer.valueOf(i2), str);
            return true;
        } else {
            if (!"location://stories/category/more".equals(str)) {
                if (onListItemClick(listViewHolder, i2, mediaItem)) {
                    ((IStoriesCategory2View) this.mView).addSharedTransition(listViewHolder, mediaItem, i2, false);
                    return true;
                }
            } else if (i7 == 1003) {
                launchDemandStory(listViewHolder);
                return true;
            }
            return false;
        }
    }

    public boolean handleCategoryItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        String str;
        if (mediaItem != null) {
            str = MediaItemStory.getStoryCategoryKey(mediaItem);
        } else {
            str = StoriesCatViewHolderFactory.getLocationKey(listViewHolder.getViewType());
        }
        if (!"location://stories/category/trip".equals(str) && !"location://stories/category/creation".equals(str)) {
            return false;
        }
        if (!isSelectionMode()) {
            IStoriesCategory2View iStoriesCategory2View = (IStoriesCategory2View) this.mView;
            iStoriesCategory2View.postAnalyticsLog(iStoriesCategory2View.getScreenId(), AnalyticsEventId.EVENT_STORIES_ITEM_LONG_PRESS);
            Optional.ofNullable(((IStoriesCategory2View) this.mView).getAdapter()).ifPresent(new c(10));
            ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.SELECT_MODE_CHANGE, Boolean.TRUE);
        }
        Optional.ofNullable(((IStoriesCategory2View) this.mView).getHeader()).ifPresent(new j(mediaItem, 0));
        ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.SELECT_ITEM, Integer.valueOf(i2));
        ((IStoriesCategory2View) this.mView).updateSelectionToolBar();
        return true;
    }

    public void handleDensityChange() {
        refreshEffectView();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 102) {
            forceReloadData();
            return true;
        } else if (i2 == 1077) {
            ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.UPDATE_BADGE, new Object[0]);
            super.handleEvent(eventMessage);
            return true;
        } else if (i2 == 1143 || i2 == 1144 || i2 == 1145 || i2 == 8503) {
            LaunchOnDemandStoryCmd launchOnDemandStoryCmd = new LaunchOnDemandStoryCmd();
            EventContext eventContext = ((IStoriesCategory2View) this.mView).getEventContext();
            Object obj = eventMessage.obj;
            if (obj == null) {
                obj = "";
            }
            launchOnDemandStoryCmd.execute(eventContext, obj, Integer.valueOf(eventMessage.what));
            return true;
        } else if (i2 != 1141) {
            return super.handleEvent(eventMessage);
        } else {
            ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.ON_DEMAND_STORY_SAVED, new Object[0]);
            return true;
        }
    }

    public void handleMultiWindowModeChanged() {
        refreshEffectView();
    }

    public void handleOrientationChange() {
        refreshEffectView();
    }

    public void handleResolutionChange() {
        this.mPreviewDelegate.clear();
        refreshEffectView();
    }

    public void notifyDataChanged(MediaData mediaData) {
        super.notifyDataChanged(mediaData);
        TopColorEffectHandler topColorEffectHandler = this.mTopColorEffect;
        if (topColorEffectHandler != null) {
            ThreadUtil.runOnUiThread(new e(28, topColorEffectHandler));
        }
    }

    public void onAppbarVisibleRatio(float f) {
        Optional.ofNullable(this.mTopColorEffect).ifPresent(new T3.e(20));
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        this.mPreviewDelegate.requestPreview();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mPreviewDelegate.destroy();
        Optional.ofNullable(this.mTopColorEffect).ifPresent(new T3.e(19));
    }

    public boolean onHandleInternalEvent(InternalEvent internalEvent, Object... objArr) {
        String str;
        if (internalEvent == InternalEvent.EVENT_FAVORITE) {
            if (objArr != null && objArr.length > 0) {
                MediaItem mediaItem = objArr[0];
                if (objArr.length > 2) {
                    str = objArr[2];
                } else {
                    str = null;
                }
                new UpdateStoryFavoriteCmd().addParameter("dataKey", str).execute(this, new MediaItem[]{mediaItem}, 0);
            }
        } else if (internalEvent == InternalEvent.REQUEST_PREVIEW) {
            this.mPreviewDelegate.requestPreview();
        }
        TopColorEffectHandler topColorEffectHandler = this.mTopColorEffect;
        if (topColorEffectHandler == null || !topColorEffectHandler.handleInternalEvent(internalEvent, objArr)) {
            return false;
        }
        return true;
    }

    public void onHeaderCreated() {
        this.mPreviewDelegate.setHeader(((IStoriesCategory2View) this.mView).getHeader());
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.SHOW_SLIDESHOW, Boolean.valueOf(!z));
        ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.HIDDEN_CHANGE, Boolean.valueOf(z));
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect)) {
            Optional.ofNullable(this.mTopColorEffect).ifPresent(new f(z, 1));
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        boolean z = false;
        if (mediaItem == null || SharedTransition.isInReturnTransition(getBlackboard())) {
            StringCompat stringCompat = this.TAG;
            if (mediaItem == null) {
                z = true;
            }
            Log.w((CharSequence) stringCompat, "onListItemClickInternal ignore", Boolean.valueOf(z));
            return;
        }
        String storyCategoryKey = MediaItemStory.getStoryCategoryKey(mediaItem);
        if ("location://stories/category/more".equals(storyCategoryKey)) {
            storyCategoryKey = "location://story/albums";
        }
        StoryLauncher data = new StoryLauncher(this.mView).setData(mediaItem, i2);
        if (PreferenceFeatures.OneUi8x.SUPPORT_RECAP && MediaItemStory.isRecap(mediaItem)) {
            z = true;
        }
        data.setRecap(z).setFromLocation(storyCategoryKey).execute();
        removeOtherTabs(getLocationKey());
        scrollCompletelyVisible((IStoriesCategory2View) this.mView, mediaItem, i2);
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mPreviewDelegate.attach(((IStoriesCategory2View) this.mView).getListView());
        Optional.ofNullable(this.mTopColorEffect).ifPresent(new i(1, view));
    }

    public void onViewDestroy() {
        super.onViewDestroy();
    }

    public void onViewPause() {
        super.onViewPause();
        this.mPreviewDelegate.pause();
    }

    public void onViewResume() {
        super.onViewResume();
        this.mPreviewDelegate.resume();
    }

    public void onViewStop() {
        super.onViewStop();
        this.mPreviewDelegate.stop();
    }

    public void refreshEffectView() {
        if (this.mTopColorEffect != null && ((IStoriesCategory2View) this.mView).getView() != null) {
            this.mTopColorEffect.refreshView(((IStoriesCategory2View) this.mView).getView());
        }
    }

    public void requestPreview() {
        this.mPreviewDelegate.requestPreview();
    }

    public void selectOneItemOnEnterSelection() {
        if (((IStoriesCategory2View) this.mView).getTotalSelectableCount() == 1) {
            super.selectOneItemOnEnterSelection();
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            setNavigationUpButton(toolbar);
            toolbar.setTitle(getTitleStringResId());
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new StoriesPinchViewPresenter.StoriesPinchMenuUpdater(v, this) {
            private boolean hasChildData() {
                if (((IStoriesCategory2View) StoriesCategory2Presenter.this.mView).getMediaData("location://story/albums") == null || ((IStoriesCategory2View) StoriesCategory2Presenter.this.mView).getMediaData("location://story/albums").getChildCount() <= 0) {
                    return false;
                }
                return true;
            }

            private void setSelectMenuVisibility(Menu menu) {
                boolean z;
                boolean z3 = false;
                if (((IStoriesCategory2View) StoriesCategory2Presenter.this.mView).getDataCount() > 0) {
                    z = true;
                } else {
                    z = false;
                }
                StoriesCategory2Header header = ((IStoriesCategory2View) StoriesCategory2Presenter.this.mView).getHeader();
                if (header != null) {
                    if (z || header.getSelectableTotalItemCount() > 0) {
                        z3 = true;
                    }
                    z = z3;
                }
                setMenuVisibility(menu, (int) R.id.action_select, z);
            }

            public boolean hasData() {
                if (super.hasData() || hasChildData()) {
                    return true;
                }
                return false;
            }

            public boolean supportHeaderSelection(IStoriesPinchView iStoriesPinchView) {
                return true;
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                super.updateOptionsMenuAction(menu, selectionMode);
                if (MenuDataBinding.SelectionMode.NORMAL.equals(selectionMode)) {
                    setSelectMenuVisibility(menu);
                }
            }

            public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
                super.updatePopupMenuAction(menu, selectionMode, i2);
                if (MenuDataBinding.SelectionMode.NORMAL.equals(selectionMode)) {
                    setSelectMenuVisibility(menu);
                }
            }
        };
    }
}
