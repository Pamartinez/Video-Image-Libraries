package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.suggestions.fixup.FixUpPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.suggestions.fixup.IFixUpPictures;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FixUpPicturesFragment<V extends IFixUpPictures, P extends FixUpPicturesPresenter> extends PicturesFragment<V, P> implements IFixUpPictures {
    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        if (isHighLightPictures()) {
            super.addSharedTransition(listViewHolder, mediaItem, i2, z);
        }
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new DummyDragAndDropDelegate();
    }

    public GridHelper createGridHelper(String str) {
        int i2;
        if (isHighLightPictures()) {
            i2 = R.array.highlight_pictures_column_count;
        } else {
            i2 = R.array.portrait_pictures_column_count;
        }
        return new GridHelper.Builder(str).setResources(i2).build();
    }

    public PicturesViewHolderFactory createViewHolderFactory() {
        if (isHighLightPictures()) {
            return new HighlightPicturesViewHolderFactory(getContext());
        }
        return new PortraitPicturesViewHolderFactory(getContext());
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount()) {
            public PicturesViewHolderFactory createViewHolderFactory(Context context) {
                return FixUpPicturesFragment.this.createViewHolderFactory();
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_suggestion_pictures_layout;
    }

    public String getScreenId() {
        if (isHighLightPictures()) {
            return AnalyticsScreenId.SCREEN_SUGGEST_HIGHLIGHT_PICTURES.toString();
        }
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_SUGGEST_PORTRAIT_PICTURES_EDIT.toString();
        }
        return AnalyticsScreenId.SCREEN_SUGGEST_PORTRAIT_PICTURES_NORMAL.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public boolean isHighLightPictures() {
        return LocationKey.isHighLightPictures(getLocationKey());
    }

    public PicturesLayoutManager createLayoutManager() {
        return new FixUpPicturesLayoutManager(this.mRecyclerView.getContext(), getColumnCount(), getLocationKey());
    }

    public FixUpPicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new FixUpPicturesViewAdapter(this, getLocationKey(), false);
    }

    public FixUpPicturesPresenter createPresenter(IFixUpPictures iFixUpPictures) {
        return new FixUpPicturesPresenter(this.mBlackboard, iFixUpPictures);
    }
}
