package com.samsung.android.gallery.app.ui.list.stories.pinch;

import Bb.k;
import D3.i;
import android.content.Context;
import android.view.View;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreview;
import com.samsung.android.gallery.app.ui.list.stories.StoriesViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IPinView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesPinchViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinchViewAdapter<V extends IStoriesPinchView> extends StoriesViewAdapter<V> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoriesPinchThumbnailPreview extends ThumbnailPreview<IStoriesPinchView> {
        public StoriesPinchThumbnailPreview(IStoriesPinchView iStoriesPinchView) {
            super(iStoriesPinchView);
        }

        public int getLayoutPosition(PreviewViewHolder previewViewHolder) {
            if (!(previewViewHolder instanceof StoriesPinchViewHolder)) {
                return super.getLayoutPosition(previewViewHolder);
            }
            int intValue = ((Integer) Optional.ofNullable(((IStoriesPinchView) this.mView).getPinView()).map(new i(18)).orElse(0)).intValue();
            int layoutPosition = super.getLayoutPosition(previewViewHolder);
            if (intValue == 0) {
                intValue = 1;
            }
            return layoutPosition + intValue;
        }

        public List<PreviewViewHolder> listOf() {
            ArrayList arrayList = new ArrayList();
            IPinView pinView = ((IStoriesPinchView) this.mView).getPinView();
            if (pinView != null) {
                arrayList.addAll(pinView.getPreviewViewHolders());
            }
            arrayList.addAll(super.listOf());
            return arrayList;
        }
    }

    public StoriesPinchViewAdapter(V v, String str, View view) {
        super(v, str, view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnItemFavoriteClickListener$0(ListViewHolder listViewHolder, int i2, ListViewHolder listViewHolder2, MediaItem mediaItem) {
        if (isSelectionMode()) {
            handleItemClick(listViewHolder2, listViewHolder.getViewPosition(), mediaItem, i2);
        } else {
            ((IStoriesPinchView) this.mView).onListItemFavoriteClick(listViewHolder2.getDecoView(59), mediaItem, listViewHolder.getViewPosition());
        }
    }

    private void setOnItemFavoriteClickListener(ListViewHolder listViewHolder) {
        int viewType = listViewHolder.getViewType();
        if (viewType == 0 && (listViewHolder instanceof StoriesPinchViewHolder)) {
            ((StoriesPinchViewHolder) listViewHolder).setOnItemFavoriteClickListener(new k(viewType, (Object) this, (Object) listViewHolder));
        }
    }

    public void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        if (requireBindHeader()) {
            super.attachHeaderViewToHolder(listViewHolder);
        } else {
            Log.d(this.TAG, "ignore attachHeaderViewToHolder due to no main item");
        }
    }

    public ThumbnailPreview<?> createThumbnailPreview() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_STORY_LIVE_EFFECT_TYPE) {
            return new StoriesPinchThumbnailPreview((IStoriesPinchView) this.mView);
        }
        return super.createThumbnailPreview();
    }

    public StoriesViewHolderFactory createViewHolderFactory(Context context) {
        return new StoriesViewHolderFactory(context, ((IStoriesPinchView) this.mView).getDimenHelper(context));
    }

    public int getItemHeight(int i2) {
        GalleryGridLayoutManager galleryGridLayoutManager;
        if (!isHeader(i2) && this.mItemHeight < 0) {
            IBaseListView iBaseListView = this.mView;
            if (iBaseListView != null) {
                galleryGridLayoutManager = ((IStoriesPinchView) iBaseListView).getLayoutManager();
            } else {
                galleryGridLayoutManager = null;
            }
            if (galleryGridLayoutManager instanceof PinchLayoutManager) {
                return ((PinchLayoutManager) galleryGridLayoutManager).getHintViewHeight(i2, getGridSize());
            }
        }
        return super.getItemHeight(i2);
    }

    public void onSelectOuter(int i2, boolean z) {
        if (isSelectionMode()) {
            this.mSelectionManager.select(Integer.valueOf(i2), z, false);
            onSelected(i2, z, true);
        }
    }

    public boolean requireBindHeader() {
        if (getDataCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean supportHover() {
        return false;
    }

    public boolean supportPinchInternal(boolean z) {
        if (!((IStoriesPinchView) this.mView).isPortrait() || ((IStoriesPinchView) this.mView).isTabletDpi() || ((IStoriesPinchView) this.mView).isEmptyViewShowing()) {
            return false;
        }
        return true;
    }

    public void updateFavorite(int i2, int i7, boolean z) {
        String str;
        if (i2 < 0) {
            notifyItemRangeChanged();
        } else if (i7 == 0) {
            if (z) {
                str = "stories_favorite_on";
            } else {
                str = "stories_favorite_off";
            }
            notifyItemChanged(i2, str);
            IBaseListView iBaseListView = this.mView;
            if (iBaseListView != null && ((IStoriesPinchView) iBaseListView).getListView() != null) {
                ((IStoriesPinchView) this.mView).getListView().requestLayout();
            }
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, int i7) {
        super.onBindViewHolder(listViewHolder, i2, i7);
        listViewHolder.updateDecoration(SerializeOptions.SORT, Integer.valueOf(((IStoriesPinchView) this.mView).getDepthFromGridSize(i7)), Boolean.valueOf(((IStoriesPinchView) this.mView).getLayoutManager().hintDrawerOpened(i7)));
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("stories_favorite_on") && listViewHolder.updateDecoration(64, Boolean.TRUE)) {
            return;
        }
        if (!list.contains("stories_favorite_off") || !listViewHolder.updateDecoration(64, Boolean.FALSE)) {
            if (list.contains("updateDecoView")) {
                int gridSize = getGridSize();
                listViewHolder.updateDecoration(SerializeOptions.SORT, Integer.valueOf(((IStoriesPinchView) this.mView).getDepthFromGridSize(gridSize)), Boolean.valueOf(((IStoriesPinchView) this.mView).getLayoutManager().hintDrawerOpened(gridSize)));
                if (list.size() == 1) {
                    return;
                }
            }
            setOnItemFavoriteClickListener(listViewHolder);
            super.onBindViewHolder(listViewHolder, i2, list);
        }
    }
}
