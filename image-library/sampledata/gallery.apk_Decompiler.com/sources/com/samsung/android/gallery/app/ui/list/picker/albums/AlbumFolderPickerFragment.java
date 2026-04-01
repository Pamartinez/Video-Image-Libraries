package com.samsung.android.gallery.app.ui.list.picker.albums;

import U9.b;
import W8.C0579a;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowInsets;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceFragment;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumFolderPickerPresenter;
import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumFolderPicker;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.foldable.FoldableScreen;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderPickerFragment<V extends IAlbumFolderPicker, P extends AlbumFolderPickerPresenter<V>> extends AlbumFolderChoiceFragment<V, P> implements IAlbumFolderPicker {
    protected View mAlbumsLayout;
    private final FoldStateManager.FoldChangedListener mFoldChangedListener = new FoldStateManager.FoldChangedListener() {
        private void updatePreselectedIds() {
            if (PreferenceFeatures.OneUi6x.SUPPORT_PICKER_PRESELECTED) {
                Blackboard.getApplicationInstance().publish("data://selected_albums", StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, Arrays.stream(AlbumFolderPickerFragment.this.getSelectedAlbumIds()).iterator()));
            }
        }

        public void onScreenChanged(FoldableScreen foldableScreen, FoldableScreen foldableScreen2) {
            if (FoldableScreen.FLIP_COVER.equals(foldableScreen) && FoldableScreen.MAIN.equals(foldableScreen2) && AlbumFolderPickerFragment.this.isViewActive()) {
                Log.d(AlbumFolderPickerFragment.this.TAG, "recreateActivity");
                updatePreselectedIds();
                Optional.ofNullable(AlbumFolderPickerFragment.this.getActivity()).ifPresent(new C0579a(5));
            }
        }
    };
    private FoldableScreen mFoldScreen = FoldableScreen.NONE;
    private FoldStateManager mFoldStateManager;
    private AtomicBoolean mIsFlipWidget;
    private GalleryToolbar mToolbar;

    private void disableAppbarLayout() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.setScrollEnable(false, getListView());
            this.mAppBarLayout.setExpanded(false);
        }
    }

    private void initToolbarPickerMode() {
        GalleryToolbar toolbar = getToolbar();
        if (toolbar != null) {
            Blackboard blackboard = this.mBlackboard;
            toolbar.enterPickerMode(blackboard, PickerUtil.getUsageTitle(blackboard), isFlipWidget());
            if (Clipboard.getInstance(this.mBlackboard).getTotalCount() == 0) {
                toolbar.setSelectedCountInfo(0, -1, PickerUtil.getMaxPickCount(this.mBlackboard));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(FoldStateManager foldStateManager) {
        foldStateManager.register(this.mFoldChangedListener);
        this.mFoldStateManager = foldStateManager;
    }

    public void adjustToolbarLayout(WindowInsets windowInsets) {
        super.adjustToolbarLayout(windowInsets);
        ViewMarginUtils.setTopPadding((View) this.mToolbar.getParent(), WindowUtils.getSystemInsetsTop(windowInsets));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mAlbumsLayout = view.findViewById(R.id.albums_picker_layout);
        this.mRecyclerView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.BOTTOM);
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumsViewDummyAdapter(getListView()) {
            public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
                return new AlbumFolderPickerViewHolderFactory(context) {
                    public boolean isFlipWidgetWithCoverScreen() {
                        return AlbumFolderPickerFragment.this.isFlipWidgetWithCoverScreen();
                    }
                };
            }
        };
    }

    public String getFragmentTag(String str) {
        return "AlbumFolderPickerFragment";
    }

    public int getLayoutId() {
        if (isFlipWidgetWithCoverScreen()) {
            return R.layout.fragment_widget_albums_picker_layout;
        }
        return R.layout.fragment_albums_picker_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return new AlbumFolderPickerPinchAnimationManager((PinchLayoutManager) this.mLayoutManager, this);
    }

    public int getSelectedCountForToolbar(boolean z) {
        return getSelectedItemCount();
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public boolean isFlipWidget() {
        if (this.mIsFlipWidget == null) {
            this.mIsFlipWidget = new AtomicBoolean(LaunchIntent.isFlipCoverWidgetTheme(this.mBlackboard));
        }
        return this.mIsFlipWidget.get();
    }

    public boolean isFlipWidgetWithCoverScreen() {
        if (FoldableScreen.NONE.equals(this.mFoldScreen)) {
            this.mFoldScreen = FoldUtils.getScreenType(getActivity());
        }
        if (!isFlipWidget() || !FoldableScreen.FLIP_COVER.equals(this.mFoldScreen)) {
            return false;
        }
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(view, windowInsets);
        getListView().updateGoToTopBottomPadding((float) getResources().getDimensionPixelOffset(R.dimen.gototop_adjust_bottom_padding));
        return onApplyWindowInsets;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        if (PickerUtil.isAlbumPickLaunchMode(getBlackboard()) || PickerUtil.isAlbumMultiPickLaunchMode(getBlackboard())) {
            Optional.ofNullable((ViewStub) view.findViewById(R.id.appbar_container)).ifPresent(new C0579a(4));
            this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isFlipWidget()) {
            Optional.ofNullable(FoldStateManager.getInstance(this.mBlackboard)).ifPresent(new b(16, this));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.unregister(this.mFoldChangedListener);
            this.mFoldStateManager = null;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        disableAppbarLayout();
        initToolbarPickerMode();
    }

    public void setScreenMode() {
        if (isFlipWidgetWithCoverScreen()) {
            enterFullListScreen(true);
        } else {
            super.setScreenMode();
        }
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public boolean supportNaviBackInSelectionMode() {
        return false;
    }

    public AlbumsBaseLayoutManager createLayoutManager() {
        return new AlbumFolderPickerLayoutManager(getContext(), getCurrentColCount()) {
            public int[] getItemPadding(int i2) {
                int albumHorizontalPadding = GridMarginHelper.getAlbumHorizontalPadding(AlbumFolderPickerFragment.this.mRecyclerView);
                int albumVerticalPadding = GridMarginHelper.getAlbumVerticalPadding(AlbumFolderPickerFragment.this.mRecyclerView);
                return new int[]{albumHorizontalPadding, albumVerticalPadding, albumHorizontalPadding, albumVerticalPadding};
            }

            public int getSpacing(int i2) {
                if (isListView(i2)) {
                    return 0;
                }
                return AlbumFolderPickerFragment.this.getGridSpacing();
            }
        };
    }

    public AlbumFolderPickerPresenter createPresenter(IAlbumFolderPicker iAlbumFolderPicker) {
        return new AlbumFolderPickerPresenter(this.mBlackboard, iAlbumFolderPicker);
    }

    public AlbumFolderPickerAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumFolderPickerAdapter(this, getLocationKey());
    }

    public void adjustAppbarHeightOffset(WindowInsets windowInsets) {
    }
}
