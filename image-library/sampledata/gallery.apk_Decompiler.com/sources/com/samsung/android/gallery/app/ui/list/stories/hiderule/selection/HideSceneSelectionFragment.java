package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import c4.C0438h;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.HideSceneSelectionPresenter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.IHideSceneSelectionView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import e6.C0453a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionFragment<V extends IHideSceneSelectionView, P extends HideSceneSelectionPresenter> extends PicturesFragment<V, P> implements IHideSceneSelectionView {
    public HideSceneSelectionFragment() {
        setLocationKey("location://stories/hideSceneSelection");
    }

    private void notifyExitSelectMode() {
        Optional.ofNullable(getBlackboard()).ifPresent(new C0453a(0));
    }

    private void unregisterInsetListener() {
        Optional.ofNullable(getView()).ifPresent(new C0438h(29));
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new DummyDragAndDropDelegate();
    }

    public void finishSelf() {
        notifyExitSelectMode();
        finish();
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount()) {
            public PicturesViewHolderFactory createViewHolderFactory(Context context) {
                return new ViewHolderFactory(LayoutInflater.from(context).cloneInContext(context));
            }
        };
    }

    public MediaItem[] getHideItems() {
        if (getAdapter() != null) {
            return getAdapter().getHideItems();
        }
        return new MediaItem[0];
    }

    public int getHideRuleId(String str) {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((HideSceneSelectionPresenter) p6).getHideRuleId(str);
        }
        return -1;
    }

    public int getLayoutId() {
        return R.layout.fragment_hide_scene_selection_layout;
    }

    public int getPreferenceDefault() {
        return 2;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.HIDE_SCENE_SELECTION_GRID_SIZE;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_EVENT_HIDE_SCENE_SELECTION.toString();
    }

    public int getSelectedCountForToolbar(boolean z) {
        return getSelectedItemCount();
    }

    public int getSelectedItemCount() {
        P p6 = this.mPresenter;
        if (p6 == null) {
            return 0;
        }
        return (((HideSceneSelectionPresenter) p6).getHideSceneCacheCount() + getHideItems().length) - getUnHideItems().length;
    }

    public int getSelectionToolbarTitle() {
        return ((HideSceneSelectionPresenter) this.mPresenter).getTitle();
    }

    public int getStartPinchDepth() {
        return loadPinchDepth();
    }

    public MediaItem[] getUnHideItems() {
        if (getAdapter() != null) {
            return getAdapter().getUnHideItems();
        }
        return new MediaItem[0];
    }

    public int[] loadPinchColumnResource() {
        return getResources().getIntArray(R.array.stories_hide_scene_selection_column_count);
    }

    public boolean onBackPressed() {
        if (isEnterTransitionOnGoing()) {
            Log.d(this.TAG, "onBackPressed: during enter animation");
            return true;
        }
        finishSelf();
        return true;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.disableAppbarScroll();
        }
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (isSelectionMode() || getDataCount() <= 0) {
            updateToolbar();
        } else {
            enterSelectionMode(0);
        }
    }

    public void onDestroyView() {
        unregisterInsetListener();
        super.onDestroyView();
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        View view2 = this.mEmptyView;
        if (view2 != null && view2.getVisibility() == 0) {
            ViewUtils.setVisibleOrGone(this.mEmptyView.findViewById(R.id.create_button_layout), false);
            ViewUtils.setVisibleOrGone(this.mEmptyView.findViewById(R.id.empty_view_text), true);
        }
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            super.onGridChanged(i2, i7);
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public void updateToolbarStartInset(GalleryToolbar galleryToolbar) {
        galleryToolbar.setContentInset();
    }

    public HideSceneSelectionViewAdapter<IHideSceneSelectionView> createListViewAdapter(GalleryListView galleryListView) {
        return new HideSceneSelectionViewAdapter<>(this, getLocationKey(), false);
    }

    public HideSceneSelectionPresenter<IHideSceneSelectionView> createPresenter(IHideSceneSelectionView iHideSceneSelectionView) {
        return new HideSceneSelectionPresenter<>(this.mBlackboard, iHideSceneSelectionView);
    }

    public HideSceneSelectionViewAdapter<V> getAdapter() {
        return (HideSceneSelectionViewAdapter) this.mListAdapter;
    }
}
