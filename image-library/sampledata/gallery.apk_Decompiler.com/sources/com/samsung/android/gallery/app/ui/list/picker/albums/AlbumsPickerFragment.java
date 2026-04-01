package com.samsung.android.gallery.app.ui.list.picker.albums;

import A4.C0371f;
import U9.b;
import W8.C0579a;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumsPickerPresenter;
import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumsPickerView;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.PickerViewUtil;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPickerFragment<V extends IAlbumsPickerView, P extends AlbumsPickerPresenter> extends AlbumsBaseFragment<V, P> implements IAlbumsPickerView {
    protected View mAlbumsLayout;
    ViewStub mSmartAlbumLayoutStub;
    private GalleryToolbar mToolbar;

    private void createToolbarSmartAlbumView() {
        if (supportSmartAlbum()) {
            SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
            if (smartAlbumHolder != null) {
                smartAlbumHolder.inflateSmartAlbumIfNecessary();
            }
            GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
            if (galleryAppBarLayout != null) {
                galleryAppBarLayout.setCollapsedHeightInPickMode(PickerUtil.getAppbarCollapsedHeight(galleryAppBarLayout.getContext()));
            }
        } else {
            GalleryAppBarLayout galleryAppBarLayout2 = this.mAppBarLayout;
            if (galleryAppBarLayout2 != null) {
                galleryAppBarLayout2.setScrollEnable(false, getListView());
                this.mAppBarLayout.setExpanded(false);
            }
        }
        if (this.mAppBarLayout != null && PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
            this.mAppBarLayout.setMultiplePickMode();
            GalleryAppBarLayout galleryAppBarLayout3 = this.mAppBarLayout;
            galleryAppBarLayout3.setTopOffset(PickerUtil.getAppbarTopOffsetInMultiPick(galleryAppBarLayout3.getContext()));
        }
    }

    private int getContentViewTopPadding(boolean z) {
        return PickerUtil.getContentViewTopPadding(this.mBlackboard, z);
    }

    private void handleCommonConfigurationChanged() {
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder != null) {
            smartAlbumHolder.updateLayout();
        }
        updatePadding();
        if (PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
            GalleryToolbar toolbar = getToolbar();
            if (toolbar != null) {
                Blackboard blackboard = this.mBlackboard;
                toolbar.enterPickerMode(blackboard, PickerUtil.getUsageTitle(blackboard));
            }
            updateToolbar();
            invalidateToolbar();
        }
    }

    private void initToolbarPickerMode() {
        GalleryToolbar toolbar = getToolbar();
        if (toolbar != null) {
            Blackboard blackboard = this.mBlackboard;
            toolbar.enterPickerMode(blackboard, PickerUtil.getUsageTitle(blackboard));
            if (PickerUtil.isMultiplePickLaunchMode(this.mBlackboard) && Clipboard.getInstance(this.mBlackboard).getTotalCount() == 0) {
                toolbar.setSelectedCountInfo(0, -1, PickerUtil.getMaxPickCount(this.mBlackboard));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onApplyWindowInsets$1(WindowInsets windowInsets, View view, WindowInsets windowInsets2) {
        PickerViewUtil.adjustViewAreaMargin(windowInsets, ((AlbumsPickerPresenter) this.mPresenter).getActivityToolBar(), ((AlbumsPickerPresenter) this.mPresenter).getClipboardView(), view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$0(FragmentActivity fragmentActivity) {
        this.mToolbar = (GalleryToolbar) fragmentActivity.findViewById(R.id.activity_toolbar);
    }

    private void updateContentViewPadding(boolean z, boolean z3) {
        if (this.mAlbumsLayout != null) {
            int contentViewTopPadding = getContentViewTopPadding(z);
            if (z3) {
                View view = this.mAlbumsLayout;
                PickerViewUtil.setContentViewTopPaddingWithAnimation(view, view.getPaddingTop(), contentViewTopPadding);
                return;
            }
            PickerViewUtil.setContentViewTopPadding(this.mAlbumsLayout, contentViewTopPadding);
        }
    }

    private void updatePadding() {
        updateToolbarBottomMargin();
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mAlbumsLayout = view.findViewById(R.id.albums_picker_layout);
        this.mSmartAlbumLayoutStub = (ViewStub) view.findViewById(R.id.smart_album_layout_stub);
    }

    public SmartAlbumHolder createSmartAlbumHolder() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH) {
            return null;
        }
        return new SmartAlbumHolder(this.mSmartAlbumLayoutStub, getBlackboard(), getLocationKey(), getAnalyticsScreenId(getScreenId()));
    }

    public String getFragmentTag(String str) {
        return "AlbumsPickerFragment";
    }

    public int getLayoutId() {
        return R.layout.fragment_albums_picker_layout;
    }

    public String getScreenId() {
        AnalyticsScreenId analyticsScreenId;
        if (this.mPresenter == null) {
            return null;
        }
        if (LocationKey.isFolder(getLocationKey())) {
            analyticsScreenId = AnalyticsScreenId.SCREEN_ALBUM_GROUP_NORMAL;
        } else {
            analyticsScreenId = AnalyticsScreenId.SCREEN_ALBUM_VIEW_PICK;
        }
        return analyticsScreenId.toString();
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        handleCommonConfigurationChanged();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        if (!isConfigStateChanged(2)) {
            handleCommonConfigurationChanged();
        }
    }

    public boolean isFadingEdgeExtended() {
        return false;
    }

    public boolean isItemEnabled(MediaItem mediaItem) {
        return ((AlbumsPickerPresenter) this.mPresenter).isItemEnabled(mediaItem);
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        Optional.ofNullable(view.getRootWindowInsets()).ifPresent(new C0371f((Object) this, (Object) windowInsets, (Object) view, 14));
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
        return windowInsets;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        Optional.ofNullable(getActivity()).ifPresent(new b(17, this));
        if (this.mToolbar == null) {
            Optional.ofNullable((ViewStub) view.findViewById(R.id.appbar_container)).ifPresent(new C0579a(4));
            this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        }
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        if (!supportSmartAlbum() && this.mAppBarLayout != null) {
            View view2 = this.mEmptyView;
            if (view2 == null || view2.getVisibility() != 0) {
                view = getListView();
            }
            this.mAppBarLayout.setScrollEnable(false, view);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        createToolbarSmartAlbumView();
        updatePadding();
        initToolbarPickerMode();
    }

    public void operateClipboard(boolean z) {
        updateContentViewPadding(z, true);
    }

    public void postAnalyticsLog() {
        super.postAnalyticsLog();
        if (((AlbumsPickerPresenter) this.mPresenter).getLaunchMode() == LaunchModeType.ACTION_MULTIPLE_PICK) {
            this.mBlackboard.postEvent(EventMessage.obtain(1028, getScreenId()));
        }
    }

    public void savePinchDepth(String str, int i2) {
        super.savePinchDepth(str, i2);
        if (this.mPresenter == null) {
            return;
        }
        if (LocationKey.isFolder(getLocationKey())) {
            getBlackboard().publish("command://FolderViewChanged", Integer.valueOf(((AlbumsPickerPresenter) this.mPresenter).getCurrentViewDepth()));
        } else {
            Blackboard.publishGlobal("command://AlbumsViewChanged", Integer.valueOf(((AlbumsPickerPresenter) this.mPresenter).getCurrentViewDepth()));
        }
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public boolean supportSelection() {
        return false;
    }

    public boolean supportSmartAlbum() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH || LocationKey.isFolder(getLocationKey()) || PickerUtil.isAlbumPickLaunchMode(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    public boolean supportTabLayout() {
        if (getParentFragment() != null) {
            return true;
        }
        return false;
    }

    public void updateToolbarBottomMargin() {
        GalleryToolbar toolbar = getToolbar();
        if (toolbar != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
            layoutParams.bottomMargin = 0;
            toolbar.setLayoutParams(layoutParams);
        }
    }

    public AlbumsBaseViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumsPickerAdapter(this, getLocationKey());
    }

    public AlbumsPickerPresenter createPresenter(IAlbumsPickerView iAlbumsPickerView) {
        return new AlbumsPickerPresenter(this.mBlackboard, iAlbumsPickerView);
    }

    public void adjustAppbarHeightOffset(WindowInsets windowInsets) {
    }

    public void setSmartAlbumEnabled(boolean z) {
    }
}
