package com.samsung.android.gallery.app.ui.viewer2.details;

import C3.C0391a;
import H3.l;
import J6.c;
import K4.a;
import L7.e;
import L7.f;
import L7.g;
import L7.h;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.SubscriberListenerInfo;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.details.DetailsC2paLoader;
import com.samsung.android.gallery.module.details.DetailsDbQueryDataLoader;
import com.samsung.android.gallery.module.details.DetailsFileDataLoader;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsMapDataLoader;
import com.samsung.android.gallery.module.details.DetailsRelatedDataLoader;
import com.samsung.android.gallery.module.details.DetailsSpecialVideoLoader;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsLoadHandler extends ViewerObject implements FragmentLifeCycle {
    static final boolean SUPPORT_C2PA = Features.isEnabled(Features.SUPPORT_C2PA);
    private C2paWrapper mC2paWrapper;
    private View mDetailsView;
    /* access modifiers changed from: private */
    public View mMapFrameView;
    /* access modifiers changed from: private */
    public View mMapMarkerView;
    private SubscriberListenerInfo mStorySubscriber;
    private final View.OnLayoutChangeListener mapFrameLayoutListener = new View.OnLayoutChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onLayoutChange$0(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
            DetailsLoadHandler.this.onDetailsDataUpdated(mediaItem, detailsLoadResult);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onLayoutChange$1(Size size, Size size2) {
            DetailsMapDataLoader.loadMapSnap(DetailsLoadHandler.this.mModel.getMediaItem(), DetailsLoadHandler.this.mModel.getBlackboard(), size, size2, new b(this));
        }

        public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
            if (!(i13 - i11 == i8 - i2 && i14 - i12 == i10 - i7) && !BottomSheetState.Details.isClosed(DetailsLoadHandler.this.mModel.getContainerModel())) {
                DetailsLoadHandler.this.execLoad(new a(this, ViewUtils.getViewSize(DetailsLoadHandler.this.mMapFrameView), ViewUtils.getViewSize(DetailsLoadHandler.this.mMapMarkerView)));
            }
        }
    };
    private LoadState state = LoadState.UNLOADED;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LoadState {
        UNLOADED,
        INVALIDATED,
        LOADED
    }

    /* access modifiers changed from: private */
    public void execLoad(Runnable runnable) {
        SimpleThreadPool.getInstance().execute(runnable);
    }

    private void initOpenC2paWrapper() {
        if (SUPPORT_C2PA && this.mC2paWrapper == null) {
            C2paWrapper instance = C2paWrapper.getInstance();
            this.mC2paWrapper = instance;
            instance.open();
        }
    }

    private boolean isValid(MediaItem mediaItem) {
        return MediaItemUtil.equalsId(mediaItem, this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(MediaItem mediaItem) {
        onDetailsDataUpdated(mediaItem, new DetailsLoadResult(DetailsUpdateKey.FPS));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        Optional.ofNullable(this.mModel.getMediaItem()).ifPresent(new g(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mDetailsView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        this.state = LoadState.INVALIDATED;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$10(EventMessage eventMessage, MediaItem mediaItem) {
        Long l = (Long) eventMessage.obj;
        if (l.longValue() == mediaItem.getFileId()) {
            Log.d(this.TAG, "reloadRelated", l);
            DetailsRelatedDataLoader.reloadRelated(mediaItem, new a(5, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$7(MediaItem mediaItem, Size size, Size size2) {
        DetailsMapDataLoader.loadMapInfos(mediaItem, this.mModel.getBlackboard(), size, size2, new a(5, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$8(MediaItem mediaItem) {
        DetailsDbQueryDataLoader.loadCreatures(mediaItem, new a(5, this));
        DetailsRelatedDataLoader.loadRelated(mediaItem, new a(5, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$9(MediaItem mediaItem) {
        DetailsDbQueryDataLoader.loadTag(mediaItem, new a(5, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$11(MediaItem mediaItem) {
        DetailsFileDataLoader.loadFile(mediaItem, new a(5, this));
        DetailsFileDataLoader.loadClippedData(mediaItem, new a(5, this));
        DetailsFileDataLoader.loadDualPhotoManipulator(mediaItem, new a(5, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$12(MediaItem mediaItem, Size size, Size size2) {
        DetailsMapDataLoader.loadMapInfos(mediaItem, this.mModel.getBlackboard(), size, size2, new a(5, this));
        DetailsC2paLoader.loadC2pa(mediaItem, this.mC2paWrapper, new a(5, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$13(MediaItem mediaItem) {
        DetailsDbQueryDataLoader.loadCreatures(mediaItem, new a(5, this));
        DetailsRelatedDataLoader.loadRelated(mediaItem, new a(5, this));
        DetailsDbQueryDataLoader.loadTag(mediaItem, new a(5, this));
        DetailsDbQueryDataLoader.loadStory(mediaItem, new a(5, this));
        DetailsSpecialVideoLoader.loadSuperSlow(mediaItem, new a(5, this));
        DetailsSpecialVideoLoader.loadDynamicVideo(mediaItem, new a(5, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewRecycled$6(View view) {
        view.removeOnLayoutChangeListener(this.mapFrameLayoutListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preLoadMapInfos$14(View view) {
        view.addOnLayoutChangeListener(this.mapFrameLayoutListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerSubscriber$4() {
        if (BottomSheetState.Details.isExpanded(this.mModel)) {
            DetailsDbQueryDataLoader.loadStory(this.mModel.getMediaItem(), new a(5, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerSubscriber$5(Object obj, Bundle bundle) {
        execLoad(new l(24, this));
    }

    private void load(MediaItem mediaItem) {
        LoadState loadState = this.state;
        LoadState loadState2 = LoadState.LOADED;
        if (loadState != loadState2) {
            if (mediaItem == null) {
                Log.e(this.TAG, "load mediaItem is null");
                return;
            }
            this.state = loadState2;
            onDetailsDataUpdated(mediaItem, new DetailsLoadResult(DetailsUpdateKey.DEFAULT));
            preLoadMapInfos(mediaItem);
            execLoad(new f(this, mediaItem, 3));
            MediaItem mediaItem2 = mediaItem;
            execLoad(new e(this, mediaItem2, ViewUtils.getViewSize(this.mMapFrameView), ViewUtils.getViewSize(this.mMapMarkerView), 0));
            execLoad(new f(this, mediaItem2, 0));
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "load " + mediaItem2.getFileId());
        }
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (objArr[1].booleanValue() && !BottomSheetState.Details.isClosed(this.mModel)) {
            initOpenC2paWrapper();
            load(this.mModel.getMediaItem());
        }
    }

    /* access modifiers changed from: private */
    public void onDetailsDataUpdated(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        if (isValid(mediaItem)) {
            this.mActionInvoker.invoke(ViewerAction.EVENT_DETAILS_INFO_UPDATED, mediaItem, detailsLoadResult);
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "onDetailsDataUpdated skip" + Logger.v(this.mModel.getMediaItem(), mediaItem));
    }

    private void preLoadMapInfos(MediaItem mediaItem) {
        DetailsMapDataLoader.preLoadMapInfos(mediaItem, new a(5, this));
        this.mMapFrameView = this.mDetailsView.findViewById(R.id.moreinfo_map_frame);
        this.mMapMarkerView = this.mDetailsView.findViewById(R.id.map_view_marker_holder);
        Optional.ofNullable(this.mMapFrameView).ifPresent(new g(this, 1));
    }

    private void registerSubscriber() {
        if (this.mStorySubscriber == null) {
            this.mStorySubscriber = subscribe("data://user/storyUpdated", new C0391a(11, this));
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIDEO_PREPARED, new h(this, 0));
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new h(this, 1));
        this.mActionInvoker.add(ViewerAction.DETAILS_VIEW, new h(this, 2));
        this.mActionInvoker.add(ViewerAction.GROUP_CURRENT_ITEM_CHANGED, new h(this, 3));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (eventMessage == null || mediaItem == null) {
            return false;
        }
        int i2 = eventMessage.what;
        if (i2 == 103) {
            this.state = LoadState.INVALIDATED;
            load(mediaItem);
            return false;
        } else if (i2 == 1026) {
            preLoadMapInfos(mediaItem);
            execLoad(new e(this, mediaItem, ViewUtils.getViewSize(this.mMapFrameView), ViewUtils.getViewSize(this.mMapMarkerView), 1));
            return false;
        } else if (i2 == 3001) {
            execLoad(new f(this, mediaItem, 1));
            return false;
        } else if (i2 == 3028) {
            onDetailsDataUpdated(mediaItem, new DetailsLoadResult(DetailsUpdateKey.CAPTURED_URL));
            return false;
        } else if (i2 == 3052) {
            execLoad(new f(this, mediaItem, 2));
            return false;
        } else if (i2 != 3058) {
            return false;
        } else {
            execLoad(new c(this, eventMessage, mediaItem, 7));
            return false;
        }
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (MediaItemUtil.equals(mediaItem2, mediaItem)) {
            DetailsData.copy(mediaItem2, mediaItem);
            return;
        }
        this.state = LoadState.INVALIDATED;
        if (BottomSheetState.Details.isExpanded(this.mModel)) {
            load(mediaItem);
        }
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        this.state = LoadState.UNLOADED;
    }

    public void onFinalized() {
        C2paWrapper c2paWrapper;
        if (SUPPORT_C2PA && (c2paWrapper = this.mC2paWrapper) != null) {
            c2paWrapper.close();
            this.mC2paWrapper = null;
        }
    }

    public void onViewAttached() {
        if (BottomSheetState.Details.isExpanded(this.mModel.getContainerModel())) {
            initOpenC2paWrapper();
            load(this.mModel.getMediaItem());
        }
        registerSubscriber();
    }

    public void onViewDetached() {
        SubscriberListenerInfo subscriberListenerInfo = this.mStorySubscriber;
        if (subscriberListenerInfo != null) {
            unsubscribe(subscriberListenerInfo);
            this.mStorySubscriber = null;
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.state = LoadState.UNLOADED;
        Optional.ofNullable(this.mMapFrameView).ifPresent(new g(this, 0));
    }
}
