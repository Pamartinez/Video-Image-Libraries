package com.samsung.android.gallery.app.ui.list.search;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryActivityItemViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryActivityCardHolder extends CategoryCardHolderV2 {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActivityItemAdapter extends CategoryItemAdapterV2 {
        ArrayList<MediaItem> mList = new ArrayList<>();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class UnsupportedInPicker {
            static final HashSet<String> set = new HashSet<>(List.of("Duplicates", "Trash", "PrivateAlbum", "OldDocuments"));

            public static boolean contains(String str) {
                return set.contains(str);
            }
        }

        public ActivityItemAdapter(ISearchView iSearchView, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
            super(iSearchView, str, galleryListView, categoryPropertyHelper, z);
            int count = this.mMediaData.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                this.mList.add(this.mMediaData.read(i2));
            }
            if (PickerUtil.isPickerMode(iSearchView.getBlackboard())) {
                this.mList.removeIf(new a(0));
            } else if (!PocFeatures.SUPPORT_PRIVATE_ALBUM) {
                this.mList.removeIf(new a(1));
            }
            this.mDataCount = this.mList.size();
        }

        private boolean isLastLineItem(int i2) {
            float f = (float) this.mPropertyHelper.getColumnCount(getContext())[0];
            if (((int) Math.ceil((double) (((float) (i2 + 1)) / f))) == ((int) Math.ceil((double) (((float) getDataCount()) / f)))) {
                return true;
            }
            return false;
        }

        public int getDataCount() {
            return this.mList.size();
        }

        public int getItemCount() {
            return getDataCount();
        }

        public MediaItem getMediaItemFromCache(int i2) {
            return this.mList.get(i2);
        }

        public boolean isDataAvailable() {
            if (getDataCount() > 0) {
                return true;
            }
            return false;
        }

        public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
            super.onBindViewHolder(listViewHolder, i2);
            ((CategoryActivityItemViewHolder) listViewHolder).updateDividerVisibility(!isLastLineItem(i2));
        }

        public MediaItem getMediaItemFromCache(int i2, int i7) {
            return getMediaItemFromCache(i2);
        }

        public int getMediaItemPosition(int i2) {
            return i2;
        }
    }

    public CategoryActivityCardHolder(View view, int i2) {
        super(view, i2);
    }

    public /* bridge */ /* synthetic */ void bind(ISearchView iSearchView, MediaData mediaData) {
        super.bind(iSearchView, mediaData);
    }

    public CategoryItemAdapterV2 createAdapter(ISearchView iSearchView, String str) {
        return new ActivityItemAdapter(iSearchView, str, this.mListView, this.mPropertyHelper, false);
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged() {
        super.onConfigurationChanged();
    }

    public /* bridge */ /* synthetic */ void recycle() {
        super.recycle();
    }

    public /* bridge */ /* synthetic */ void setNotifySupplier(BooleanSupplier booleanSupplier) {
        super.setNotifySupplier(booleanSupplier);
    }

    public void setTitle(Context context, int i2) {
        super.setTitle(context, i2);
        ViewUtils.setText(this.mHeaderCount, "");
    }

    public /* bridge */ /* synthetic */ void updateContentPadding(boolean z) {
        super.updateContentPadding(z);
    }

    public /* bridge */ /* synthetic */ void updateDivider(boolean z) {
        super.updateDivider(z);
    }

    public /* bridge */ /* synthetic */ void updateDividerMarginTop() {
        super.updateDividerMarginTop();
    }
}
