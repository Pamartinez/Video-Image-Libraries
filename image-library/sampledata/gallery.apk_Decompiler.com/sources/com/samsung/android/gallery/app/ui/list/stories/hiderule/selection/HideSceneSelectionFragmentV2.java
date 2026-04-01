package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.HideSceneSelectionPresenter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.IHideSceneSelectionView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionFragmentV2<V extends IHideSceneSelectionView, P extends HideSceneSelectionPresenter> extends HideSceneSelectionFragment implements IHideSceneSelectionView {
    public int getStartPinchDepth() {
        return 0;
    }

    public int[] loadPinchColumnResource() {
        return getResources().getIntArray(R.array.stories_hide_scene_selection_column_count_v2);
    }

    public void updateAppbarSelectionCount(int i2, int i7) {
        GalleryAppBarLayout galleryAppBarLayout;
        if (i2 != 0 || (galleryAppBarLayout = this.mAppBarLayout) == null) {
            super.updateAppbarSelectionCount(i2, i7);
        } else {
            galleryAppBarLayout.setTitle(getSelectionToolbarTitle());
        }
    }

    public PicturesLayoutManager createLayoutManager() {
        return new HideSceneSelectionLayoutManager(getContext(), getColumnCount());
    }

    public HideSceneSelectionViewAdapter<IHideSceneSelectionView> createListViewAdapter(GalleryListView galleryListView) {
        return new HideSceneSelectionViewAdapterV2(this, getLocationKey(), false);
    }
}
