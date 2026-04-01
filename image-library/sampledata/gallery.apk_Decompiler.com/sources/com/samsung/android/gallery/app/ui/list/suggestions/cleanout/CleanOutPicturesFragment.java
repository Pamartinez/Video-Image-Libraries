package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import H7.A;
import M5.a;
import M9.o;
import android.content.Context;
import android.view.View;
import android.view.WindowInsets;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutPicturesFragment extends PicturesFragment<ICleanOutPicturesView, CleanOutPicturesPresenter> implements ICleanOutPicturesView {
    private static final boolean SUPPORT_PINCH_ZOOM = SdkConfig.atLeast(SdkConfig.SEM.T_MR5);
    private DuplicateHeader mHeader;

    private DuplicateHeader getHeader() {
        if (this.mHeader == null) {
            this.mHeader = new DuplicateHeader(getContext(), getListView(), new A(16, this));
        }
        return this.mHeader;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustEmptyViewMargin$1(FloatingToolbarLayout floatingToolbarLayout) {
        ViewMarginUtils.setBottomMargin(this.mEmptyView, Math.max(getBottomTabHeight(), floatingToolbarLayout.getHeight()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustEmptyViewMargin$2(FloatingToolbarLayout floatingToolbarLayout) {
        ViewUtils.post(floatingToolbarLayout, new a(27, this, floatingToolbarLayout));
    }

    /* access modifiers changed from: private */
    public void onHeaderButtonClicked(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_DELETE_DUPLICATE);
        enterSelectionMode(0);
        if (isCleanOutDuplicatedPictures()) {
            ((CleanOutDuplicatedPicturesViewAdapter) this.mListAdapter).selectDuplicatedItems();
        } else {
            ((CleanOutPicturesViewAdapter) this.mListAdapter).selectDuplicatedItems();
        }
    }

    /* access modifiers changed from: private */
    public boolean supportCleanOutPinch() {
        if (!SUPPORT_PINCH_ZOOM || isCleanOutDuplicatedPictures()) {
            return false;
        }
        return true;
    }

    private void updateHeader(boolean z) {
        Optional.ofNullable(this.mHeader).ifPresent(new b(z));
    }

    /* access modifiers changed from: private */
    public void updateHeaderText() {
        MediaData mediaData;
        DuplicateHeader duplicateHeader = this.mHeader;
        if (duplicateHeader != null && (mediaData = this.mMediaData) != null) {
            duplicateHeader.updateText(mediaData.getRealCount(), MediaItemSuggest.getTotalSize(this.mMediaData.read(0)));
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.function.Consumer, java.lang.Object] */
    private void updateLayout() {
        Optional.ofNullable(this.mHeader).ifPresent(new Object());
        ((CleanOutPicturesLayoutManager) getLayoutManager()).initDimens(getContext());
    }

    private void updateNoItemViewText(int i2, int i7) {
        View view = this.mEmptyView;
        if (view != null) {
            NoItemView noItemView = (NoItemView) view.findViewById(R.id.no_item_view);
            noItemView.setLabel(getResources().getString(i2));
            noItemView.setDescription(i7);
        }
    }

    public void adjustEmptyViewMargin(WindowInsets windowInsets) {
        Optional.ofNullable(this.mFloatingToolbarLayout).ifPresent(new K5.a(22, this));
    }

    public void changeViewDepthByWheelScroll(int i2) {
        if (supportCleanOutPinch()) {
            super.changeViewDepthByWheelScroll(i2);
        }
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new DummyDragAndDropDelegate();
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount()) {
            public PicturesViewHolderFactory createViewHolderFactory(Context context) {
                return CleanOutDelegate.createViewHolderFactory(context, CleanOutPicturesFragment.this.getLocationKey());
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_suggestion_pictures_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        if (!supportCleanOutPinch()) {
            return null;
        }
        return new CleanOutPinchAnimationManager((PinchLayoutManager) this.mLayoutManager, (GridInfo.ClusterInfo) null, true);
    }

    public int getPreferenceDefault() {
        return 0;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.SUGGESTION_CLEAN_OUT_PICTURES_GRID_SIZE;
    }

    public String getScreenId() {
        return CleanOutDelegate.getScreenId(this);
    }

    public int getStartPinchDepth() {
        if (supportCleanOutPinch()) {
            return loadPinchDepth();
        }
        return GridHelper.getInstance(getLocationKey()).getDefaultDepth();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        updateLayout();
    }

    public void initializeEmptyView() {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            updateNoItemViewText(R.string.no_duplicates, R.string.no_duplicates_description);
        } else if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            updateNoItemViewText(R.string.ocr_no_result, 0);
        }
        super.initializeEmptyView();
    }

    public boolean isCleanOutDuplicatedPictures() {
        if (LocationKey.isCleanOutDuplicatedPictures(getLocationKey()) || LocationKey.isCleanOutBurstSimilarPhoto(getLocationKey())) {
            return true;
        }
        return false;
    }

    public boolean isCleanOutExpiredPictures() {
        String locationKey = getLocationKey();
        if (!LocationKey.isCleanOutPictures(locationKey) || 1 != Integer.valueOf(ArgumentsUtil.getArgValue(locationKey, "delete_type")).intValue()) {
            return false;
        }
        return true;
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (!isDestroyed()) {
            if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                CleanOutDelegate.needFinish(this);
            }
            ThreadUtil.postOnUiThread(new o(16, this));
        }
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            super.onGridChanged(i2, i7);
        }
    }

    public void onSelectionModeChanged(boolean z) {
        super.onSelectionModeChanged(z);
        updateHeader(z);
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public PicturesLayoutManager createLayoutManager() {
        return new CleanOutPicturesLayoutManager(getListView(), getColumnCount(), isCleanOutDuplicatedPictures()) {
            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                if (CleanOutPicturesFragment.this.supportCleanOutPinch()) {
                    CleanOutPicturesFragment.this.onGridChanged(i2, spanCount);
                }
            }
        };
    }

    public BaseCleanOutPicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return CleanOutDelegate.createCleanOutPicturesViewAdapter(this, getLocationKey(), getHeader().getView());
    }

    public CleanOutPicturesPresenter createPresenter(ICleanOutPicturesView iCleanOutPicturesView) {
        return new CleanOutPicturesPresenter(this.mBlackboard, iCleanOutPicturesView);
    }

    public void updateAppbarSelectionCount(int i2, int i7) {
    }
}
