package com.samsung.android.gallery.app.ui.list.trash;

import A.a;
import A4.J;
import Ba.g;
import O3.o;
import T3.e;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.trash.ITrashView;
import com.samsung.android.gallery.app.ui.list.trash.TrashPresenter;
import com.samsung.android.gallery.app.ui.list.trash.container.ITrashContainerView;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.sum.solution.filter.UniImgp;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashFragment<V extends ITrashView, P extends TrashPresenter> extends PicturesFragment<V, P> implements ITrashView {
    private static final boolean SUPPORT_MULTI_TRASH = Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH);
    private boolean mForceEnterSelectionMode;
    private boolean mHasFamilyAlbum;
    private int mLayoutId = R.layout.fragment_trash_layout;

    private void changeViewDepth(GalleryListView galleryListView, int i2) {
        if (galleryListView != null && (galleryListView.getLayoutManager() instanceof GridLayoutManager)) {
            int depthFromGridSize = getDepthFromGridSize(i2);
            Log.d(this.TAG, "changeViewDepth", Integer.valueOf(i2), Integer.valueOf(depthFromGridSize));
            scrollListToPositionByDepth(depthFromGridSize);
            galleryListView.changeDepth((GridLayoutManager) galleryListView.getLayoutManager(), depthFromGridSize);
            galleryListView.onGridChanged();
        }
    }

    private View createHeaderView() {
        View inflate = getLayoutInflater().inflate(R.layout.trash_header_layout, getListView(), false);
        Context context = getContext();
        if (context != null) {
            ((TextView) inflate.findViewById(R.id.trash_description)).setText(getDescription(context));
        }
        return inflate;
    }

    private GalleryAppBarLayout getAppbarFromContainer() {
        MvpBaseFragment mvpBaseFragment = (MvpBaseFragment) getParentFragment();
        if (mvpBaseFragment instanceof ITrashContainerView) {
            return ((ITrashContainerView) mvpBaseFragment).getAppbarLayout();
        }
        return null;
    }

    private FloatingToolbarLayout getFloatingToolbarFromContainer() {
        MvpBaseFragment mvpBaseFragment = (MvpBaseFragment) getParentFragment();
        if (mvpBaseFragment instanceof ITrashContainerView) {
            return ((ITrashContainerView) mvpBaseFragment).getFloatingToolbarLayout();
        }
        return null;
    }

    private GalleryToolbar getToolbarFromContainer() {
        MvpBaseFragment mvpBaseFragment = (MvpBaseFragment) getParentFragment();
        if (mvpBaseFragment instanceof ITrashContainerView) {
            return ((ITrashContainerView) mvpBaseFragment).getToolbar();
        }
        return null;
    }

    private void initTrashOnButtonIfNeeded() {
        if (trashIsTurnedOffButEnteredState() && this.mEmptyView != null) {
            setEmptyViewLabel(R.string.trash_is_off);
            View findViewById = this.mEmptyView.findViewById(R.id.turn_on_trash);
            if (findViewById != null) {
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new g(10, this, findViewById));
            }
        }
    }

    private boolean isSDCardMounted(Context context) {
        if (Features.isEnabled(Features.IS_KNOX_MODE) || !Features.isEnabled(Features.SUPPORT_SD_CARD) || !FileUtils.isSdcardMounted(context)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initTrashOnButtonIfNeeded$1(View view, View view2) {
        SettingPreference.Trash.setAndNotifyIfChanged(getContext(), true);
        setEmptyViewLabel(R.string.empty_set_description_no_pictures_and_videos);
        view.setVisibility(8);
        postAnalyticsLog(AnalyticsEventId.EVENT_RECYCLE_BIN_TURN_ON_TRASH_BUTTON);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEmptyViewLabel$2(int i2, Object obj) {
        ((TextView) obj).setText(getString(i2));
    }

    private void setEmptyViewLabel(int i2) {
        View view = this.mEmptyView;
        if (view != null) {
            Optional.ofNullable(view.findViewById(R.id.label)).ifPresent(new J((Object) this, i2, 4));
        }
    }

    private boolean trashIsTurnedOffButEnteredState() {
        if (!isInMultiTrash() || PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash) || !this.mHasFamilyAlbum) {
            return false;
        }
        return true;
    }

    public void adjustAppbarHeightOffset(WindowInsets windowInsets) {
        if (!isInMultiTrash()) {
            super.adjustAppbarHeightOffset(windowInsets);
        }
    }

    public void adjustContentAreaMargin(View view, WindowInsets windowInsets, boolean z) {
        super.adjustContentAreaMargin(view, windowInsets, z);
        if (isInMultiTrash()) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin = 0;
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        if (this.mAppBarLayout == null && isInMultiTrash()) {
            this.mAppBarLayout = getAppbarFromContainer();
        }
        if (this.mToolbar == null && isInMultiTrash()) {
            this.mToolbar = getToolbarFromContainer();
        }
        if (this.mFloatingToolbarLayout == null && isInMultiTrash()) {
            this.mFloatingToolbarLayout = getFloatingToolbarFromContainer();
        }
    }

    public String getDescription(Context context) {
        int i2;
        int i7;
        if (!PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH || LocationKey.isFamilySharedTrash(getLocationKey())) {
            if (isSDCardMounted(context)) {
                i2 = R.string.trash_description_with_mounted_sd_card;
            } else {
                i2 = R.string.trash_description_without_sd_card;
            }
            return context.getString(i2);
        }
        if (isSDCardMounted(context)) {
            i7 = R.string.one_trash_description_with_sdcard;
        } else {
            i7 = R.string.one_trash_description_without_sdcard;
        }
        return context.getString(i7);
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new TrashViewDummyAdapter(getListView(), getColumnCount());
    }

    public int getLayoutId() {
        return this.mLayoutId;
    }

    public int getPreferenceDefault() {
        return getGridHelper().getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return getGridHelper().getPreferenceName();
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_RECYCLE_BIN_VIEW_EDIT.toString();
        }
        return AnalyticsScreenId.SCREEN_RECYCLE_BIN_VIEW_NORMAL.toString();
    }

    public int getStartPinchDepth() {
        return getPinchDepthRecoveredIfAbsent();
    }

    public GalleryToolbar getToolbar() {
        try {
            GalleryToolbar toolbar = super.getToolbar();
            if (toolbar != null || !isInMultiTrash()) {
                return toolbar;
            }
            return (GalleryToolbar) Optional.ofNullable((MvpBaseFragment) getParentFragment()).map(new o(17)).orElse((Object) null);
        } catch (StackOverflowError e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "getToolbar " + e.getMessage());
            return null;
        }
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        resetHeaderView();
    }

    public void initArguments(Bundle bundle) {
        super.initArguments(bundle);
        this.mForceEnterSelectionMode = ArgumentsUtil.getArgValue(getLocationKey(), "editMode", false);
    }

    public void initializeEmptyView() {
        super.initializeEmptyView();
        if (!LocationKey.isFamilySharedTrash(getLocationKey())) {
            initTrashOnButtonIfNeeded();
        }
    }

    public boolean isAutoDragPossible() {
        return false;
    }

    public boolean isInMultiTrash() {
        return SUPPORT_MULTI_TRASH;
    }

    public void loadArguments(Bundle bundle) {
        super.loadArguments(bundle);
        if (isInMultiTrash()) {
            this.mLayoutId = bundle.getInt("fragment-layout", R.layout.fragment_trash_layout);
            this.mHasFamilyAlbum = bundle.getBoolean("has-family-album", false);
        }
    }

    public boolean onBackPressed() {
        P p6;
        if (!isInMultiTrash() || (p6 = this.mPresenter) == null || !((TrashPresenter) p6).isEditMode() || isDrawerMode()) {
            return super.onBackPressed();
        }
        return false;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            this.mBlackboard.postEvent(EventMessage.obtain(1127));
        }
    }

    public void onCommitFromContainer() {
        initFloatingToolbarLayout();
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (this.mForceEnterSelectionMode && getDataCount() > 0) {
            enterSelectionMode(-1);
            this.mForceEnterSelectionMode = false;
        }
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            super.onGridChanged(i2, i7);
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (!isInMultiTrash() || eventMessage.what != 2011) {
            return super.onHandleEvent(eventMessage);
        }
        if (!isHidden()) {
            return false;
        }
        changeViewDepth(getListView(), eventMessage.arg1);
        return true;
    }

    public void onResume() {
        super.onResume();
        if (isSelectionMode() && getDataCount() == 0) {
            exitSelectionMode(false);
            invalidateToolbar();
        }
    }

    public void resetHeaderView() {
        try {
            PicturesViewAdapter adapter = getAdapter();
            if (adapter != null) {
                View headerView = adapter.getHeaderView();
                View createHeaderView = createHeaderView();
                if (headerView == null) {
                    adapter.setHeaderView(createHeaderView);
                    return;
                }
                TextView textView = (TextView) headerView.findViewById(R.id.trash_description);
                TextView textView2 = (TextView) createHeaderView.findViewById(R.id.trash_description);
                if (textView != null && textView2 != null && !Objects.equals(textView.getText(), textView2.getText())) {
                    adapter.setHeaderView(createHeaderView);
                }
            }
        } catch (Exception e) {
            a.r(e, new StringBuilder("unable to reset header view e="), this.TAG);
        }
    }

    public void savePinchDepth(String str, int i2) {
        Blackboard blackboard;
        super.savePinchDepth(str, i2);
        if (isInMultiTrash() && (blackboard = this.mBlackboard) != null) {
            blackboard.postEvent(EventMessage.obtain(UniImgp.OPTION_IMGP_TYPE_NAME, i2, 0, (Object) null));
        }
    }

    public void startPostponedEnterTransitionV2() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof MvpBaseFragment) {
            ((MvpBaseFragment) parentFragment).startPostponedEnterTransitionV2();
        } else {
            super.startPostponedEnterTransitionV2();
        }
    }

    public void startShrinkAnimation() {
        if (isDrawerMode()) {
            Optional.ofNullable(this.mBlackboard).ifPresent(new e(4));
        }
    }

    public boolean supportAutoDrag() {
        return false;
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportPivotOnFingerPos() {
        return false;
    }

    public void updateEmptyViewLayout(AppBarLayout appBarLayout, View view, int i2) {
        int i7;
        int i8;
        if (!isInMultiTrash() || !this.mHasFamilyAlbum) {
            i7 = ViewMarginUtils.getTopMargin(getView());
            i8 = -i7;
        } else {
            MvpBaseFragment mvpBaseFragment = (MvpBaseFragment) getParentFragment();
            if (mvpBaseFragment instanceof ITrashContainerView) {
                i7 = ((ITrashContainerView) mvpBaseFragment).getTabLayoutHeight();
                i8 = -i7;
            } else {
                i8 = 0;
            }
        }
        this.mSystemUi.updateEmptyViewLayout(appBarLayout, view, getUsableHeight(appBarLayout), i2, i8);
    }

    public TrashViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new TrashViewAdapter(this, getLocationKey(), createHeaderView(), supportRealRatio());
    }

    public TrashPresenter createPresenter(ITrashView iTrashView) {
        return new TrashPresenter(this.mBlackboard, iTrashView);
    }
}
