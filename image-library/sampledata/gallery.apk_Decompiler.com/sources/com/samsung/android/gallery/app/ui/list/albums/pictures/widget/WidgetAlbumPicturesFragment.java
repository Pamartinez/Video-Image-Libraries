package com.samsung.android.gallery.app.ui.list.albums.pictures.widget;

import K5.a;
import O3.l;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.widget.WidgetAlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.WidgetPicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.foldable.FoldableScreen;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.animations.IThemeColor;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WidgetAlbumPicturesFragment<V extends IAlbumPicturesView, P extends WidgetAlbumPicturesPresenter<?>> extends AlbumPicturesFragment<V, P> {
    private final FoldStateManager.FoldChangedListener mFoldChangedListener = new FoldStateManager.FoldChangedListener() {
        public void onScreenChanged(FoldableScreen foldableScreen, FoldableScreen foldableScreen2) {
            if (FoldableScreen.FLIP_COVER.equals(foldableScreen) && FoldableScreen.MAIN.equals(foldableScreen2) && WidgetAlbumPicturesFragment.this.isViewActive()) {
                Log.d(WidgetAlbumPicturesFragment.this.TAG, "recreateActivity");
                Utils.recreateActivity(WidgetAlbumPicturesFragment.this.getActivity());
            }
        }
    };
    private FoldStateManager mFoldStateManager;
    private View mGradientView;
    private IThemeColor mThemeColor;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(FoldStateManager foldStateManager) {
        foldStateManager.register(this.mFoldChangedListener);
        this.mFoldStateManager = foldStateManager;
    }

    public void adjustEmptyViewButtonLayout(boolean z) {
        ViewUtils.setVisibleOrGone(this.mCreateButton, false);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mGradientView = view.findViewById(R.id.gradient_view);
    }

    public CustomCover createCustomCover(View view) {
        return null;
    }

    public GridHelper createGridHelper(String str) {
        if (isFromFlipCover()) {
            return new GridHelper.Builder(str).setSpanInfo(new int[]{1, 1, 1}).setDepthDefault(0).setResources(R.array.n_widget_column_count).build();
        }
        return super.createGridHelper(str);
    }

    public SimpleAutoScroller createSimpleAutoScroller(int i2) {
        return super.createSimpleAutoScroller(i2).setShrinkBlackBg(true);
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new WidgetPicturesViewDummyAdapter(getListView(), getColumnCount());
    }

    public int getGridSpacing(int i2) {
        return GridMarginHelper.getMarginInFlipCover(getListView(), i2);
    }

    public int getLayoutId() {
        return R.layout.fragment_widget_album_pictures_layout;
    }

    public int getStartPinchDepth() {
        return getGridHelper().getGridDepth();
    }

    public IThemeColor getThemeColor() {
        if (this.mThemeColor == null) {
            this.mThemeColor = new IThemeColor() {
            };
        }
        return this.mThemeColor;
    }

    public boolean isFromFlipCover() {
        if (!LaunchIntent.isFlipCoverWidgetTheme(this.mBlackboard) || !FoldUtils.isFlipCoverScreen(getActivity())) {
            return false;
        }
        return true;
    }

    public void loadArguments(Bundle bundle) {
        super.loadArguments(bundle);
        this.mIsSplitBlocked = true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        boolean z = false;
        this.mRecyclerView.setPadding(0, 0, 0, WindowUtils.getDisplayCutoutRect(windowInsets).bottom);
        View view2 = this.mGradientView;
        if (WindowUtils.getDisplayCutoutRect(windowInsets).bottom > 0) {
            z = true;
        }
        ViewUtils.setVisibleOrGone(view2, z);
        ViewMarginUtils.setTopMargin(getView(), WindowUtils.getDisplayCutoutRect(windowInsets).top);
        return windowInsets;
    }

    public boolean onBackPressed() {
        Optional.ofNullable(getActivity()).ifPresent(new l(18));
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Optional.ofNullable(FoldStateManager.getInstance(this.mBlackboard)).ifPresent(new a(26, this));
    }

    public void onDestroy() {
        super.onDestroy();
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.unregister(this.mFoldChangedListener);
            this.mFoldStateManager = null;
        }
    }

    public boolean supportSelection() {
        return false;
    }

    public boolean supportSplitFeature() {
        return false;
    }

    public WidgetAlbumPicturesViewAdapter<?> createListViewAdapter(GalleryListView galleryListView) {
        return new WidgetAlbumPicturesViewAdapter<>(this, getLocationKey(), (View) null, isRealRatioSupported(), isFromFlipCover());
    }

    public WidgetAlbumPicturesPresenter<?> createPresenter(IAlbumPicturesView iAlbumPicturesView) {
        return new WidgetAlbumPicturesPresenter<>(this.mBlackboard, iAlbumPicturesView);
    }

    public void savePinchDepth(String str, int i2) {
    }
}
