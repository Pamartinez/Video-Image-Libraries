package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import A4.C0385u;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Options;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.ISlideshowV2View;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Presenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowV2Fragment<V extends ISlideshowV2View, P extends SlideshowV2Presenter<ISlideshowV2View>> extends MvpBaseFragment<V, P> implements ISlideshowV2View {
    private String mBgmName;
    private Delegate mDelegate;
    private final EventHandler mEventHandler = new EventHandler(this);
    private String mFilterName;
    private boolean mOnBackPressed;
    private Options mOptions = new Options();
    private View mToolbarParent;

    private void handlePostEventInternal(Event event, Object... objArr) {
        if (event == Event.FRAGMENT_NAVIGATION_VISIBLE) {
            boolean z = false;
            if (objArr != null && objArr.length > 0 && objArr[0].booleanValue()) {
                z = true;
            }
            setNavigationBarVisible(z);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getExportItems$0(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public boolean onTouchToolBar() {
        return false;
    }

    private void updateWindowInset() {
        int i2;
        View view = this.mToolbarParent;
        if (view != null) {
            if (!isInMultiWindowMode()) {
                i2 = WindowUtils.getSystemInsetsTop(this.mToolbarParent.getRootWindowInsets());
            } else {
                i2 = 0;
            }
            ViewMarginUtils.setTopPadding(view, i2);
        }
    }

    public void finish() {
        if (isFirstFragment()) {
            getBlackboard().post("command://request_suicide", (Object) null);
        } else {
            super.finish();
        }
    }

    public boolean fromSelection() {
        if (!PocFeatures.SLIDESHOW_SELECTED_ITEMS || !LocationKey.isSelectedItems(getLocationKey())) {
            return false;
        }
        return true;
    }

    public String getBgmName() {
        return this.mBgmName;
    }

    public EventHandler getEventHandler() {
        return this.mEventHandler;
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> getExportItems() {
        /*
            r4 = this;
            com.samsung.android.gallery.module.dataset.MediaData r0 = r4.getMediaData()
            r1 = 0
            com.samsung.android.gallery.module.data.MediaItem[] r2 = new com.samsung.android.gallery.module.data.MediaItem[r1]
            if (r0 == 0) goto L_0x0056
            int r3 = r0.getCount()
            if (r3 <= 0) goto L_0x0056
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r4.hashTag()
            r2.append(r4)
            java.lang.String r4 = ".getExportItems"
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            r0.acquireReadLock(r4)     // Catch:{ all -> 0x0051 }
            int r2 = r0.getCount()     // Catch:{ all -> 0x0051 }
            r3 = 100
            int r2 = java.lang.Math.min(r3, r2)     // Catch:{ all -> 0x0051 }
            java.util.stream.IntStream r1 = java.util.stream.IntStream.range(r1, r2)     // Catch:{ all -> 0x0051 }
            A4.v r2 = new A4.v     // Catch:{ all -> 0x0051 }
            r3 = 4
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0051 }
            java.util.stream.Stream r1 = r1.mapToObj(r2)     // Catch:{ all -> 0x0051 }
            A4.w r2 = new A4.w     // Catch:{ all -> 0x0051 }
            r3 = 17
            r2.<init>(r3)     // Catch:{ all -> 0x0051 }
            java.lang.Object[] r1 = r1.toArray(r2)     // Catch:{ all -> 0x0051 }
            r2 = r1
            com.samsung.android.gallery.module.data.MediaItem[] r2 = (com.samsung.android.gallery.module.data.MediaItem[]) r2     // Catch:{ all -> 0x0051 }
            r0.releaseReadLock(r4)
            goto L_0x0056
        L_0x0051:
            r1 = move-exception
            r0.releaseReadLock(r4)
            throw r1
        L_0x0056:
            java.util.stream.Stream r4 = java.util.Arrays.stream(r2)
            Ad.a r0 = new Ad.a
            r1 = 1
            r0.<init>(r1)
            java.util.stream.Collector r0 = java.util.stream.Collectors.toCollection(r0)
            java.lang.Object r4 = r4.collect(r0)
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Fragment.getExportItems():java.util.ArrayList");
    }

    public String getFilterName() {
        return this.mFilterName;
    }

    public int getLayoutId() {
        if (!PocFeatures.isEnabled(PocFeatures.Recap) || ArgumentsUtil.getArgValue(getLocationKey(), "slide_show_recap", 0) != 1) {
            return R.layout.fragment_story_highlight_layout;
        }
        return R.layout.fragment_recap_layout;
    }

    public MediaData getMediaData() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((SlideshowV2Presenter) p6).getMediaData();
        }
        return null;
    }

    public Options getOptions() {
        return this.mOptions;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SLIDESHOW.toString();
    }

    public Order getSortType() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((SlideshowV2Presenter) p6).getSortType();
        }
        return Order.TIME_DESC;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        this.mDelegate.handleOrientationChange(i2);
    }

    public void handlePostEvent(Event event, Object... objArr) {
        if (!isDestroyed()) {
            handlePostEventInternal(event, objArr);
            ((SlideshowV2Presenter) this.mPresenter).handlePostEvent(event, objArr);
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        Log.d(this.TAG, "handleResolutionChange", Integer.valueOf(i2));
        this.mDelegate.handleResolutionChange(i2);
        updateWindowInset();
    }

    public void initArguments(Bundle bundle) {
        super.initArguments(bundle);
        this.mFilterName = ArgumentsUtil.getArgValue(getLocationKey(), "filter_name", (String) null);
        this.mBgmName = ArgumentsUtil.getArgValue(getLocationKey(), "bgm_name", (String) null);
        this.mOptions = new SlideshowOptions(this);
    }

    public void initView(View view) {
        GalleryToolbar galleryToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mToolbar = galleryToolbar;
        galleryToolbar.setTouchBlocker(new C0385u(5, this));
        this.mDelegate.initView(view);
        this.mToolbarParent = (View) this.mToolbar.getParent();
        updateWindowInset();
        onEnterTransitionEndV2();
    }

    public boolean isBackPressed() {
        return this.mOnBackPressed;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.mDelegate.onApplyWindowInsets(view, windowInsets);
        return windowInsets;
    }

    public void onAttach(Context context) {
        Delegate create = DelegateFactory.create(this);
        this.mDelegate = create;
        this.mEventHandler.attach(create);
        super.onAttach(context);
        this.mDelegate.onAttach();
    }

    public boolean onBackPressed() {
        if (this.mDelegate.onBackPressed()) {
            return true;
        }
        this.mOnBackPressed = true;
        return super.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDelegate.onConfigurationChanged(configuration);
    }

    public void onDataChangedOnUi() {
        if (!isDestroyed()) {
            this.mDelegate.onDataChangedOnUi();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDelegate.onDestroy();
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setTouchBlocker((BooleanSupplier) null);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mDelegate.onDestroyView();
    }

    public void onEnterTransitionEndV2() {
        this.mEventHandler.lambda$postEvent$0(Event.ON_THEME_CHANGED, new Object[0]);
        this.mEventHandler.lambda$postEvent$0(Event.ENTER_TRANSITION_END, new Object[0]);
        this.mEventHandler.lambda$postEvent$0(Event.ENTER_TRANSITION_POST_END, new Object[0]);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return this.mDelegate.onKeyEvent(i2, keyEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        return this.mDelegate.onKeyEvent(i2, keyEvent);
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        this.mDelegate.onMultiWindowModeChanged(z);
        updateWindowInset();
    }

    public void onPause() {
        super.onPause();
        this.mDelegate.onPause();
        keepScreenOn(false);
    }

    public void onResume() {
        super.onResume();
        this.mDelegate.onResume();
        keepScreenOn(!((SlideshowV2Presenter) this.mPresenter).isKeepPaused());
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(getView());
    }

    public Object requestData(DataRequest dataRequest, Object... objArr) {
        if (dataRequest == DataRequest.FRAGMENT_REQ_ONGOING_ENTRY_ANIM) {
            return Boolean.FALSE;
        }
        return null;
    }

    public void setNavigationBarVisible(boolean z) {
        if (z) {
            showNavigationBar(false);
        } else {
            hideNavigationBar(false);
        }
    }

    public void setScreenMode() {
        setScreenViewerMode();
        this.mDelegate.setScreenMode();
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportPinchShrink() {
        return false;
    }

    public boolean supportReordering() {
        return false;
    }

    public SlideshowV2Presenter<ISlideshowV2View> createPresenter(ISlideshowV2View iSlideshowV2View) {
        return new SlideshowV2Presenter<>(this.mBlackboard, iSlideshowV2View);
    }

    public P getPresenter() {
        return (SlideshowV2Presenter) this.mPresenter;
    }

    public void onPrepareSharedTransitionV2() {
    }
}
