package com.samsung.android.gallery.app.ui.list.search.pdc;

import B8.e;
import B8.j;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryPeopleItemViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import l6.a;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PdcResultAdapter extends RecyclerView.Adapter<CategoryPeopleItemViewHolder> {
    private Consumer<ArrayList<MediaItem>> mCallback;
    private int mItemSideMargin;
    private ArrayList<MediaItem> mItems = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;
    private final ArrayList<MediaItem> mSelectedItems = new ArrayList<>();

    public PdcResultAdapter(Context context) {
        this.mLayoutInflater = LayoutInflater.from(context);
        updateItemSideMargin(context);
    }

    private void bindThumbnail(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$bindThumbnail$1(listViewHolder, memCache);
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new a(10, this, listViewHolder));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$2(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new C0235b(this, listViewHolder, bitmap, 25));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(CategoryPeopleItemViewHolder categoryPeopleItemViewHolder, ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        onItemClicked(categoryPeopleItemViewHolder, mediaItem);
    }

    private void notifySelectedItemChanged() {
        Consumer<ArrayList<MediaItem>> consumer = this.mCallback;
        if (consumer != null) {
            consumer.accept(this.mSelectedItems);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindThumbnail */
    public void lambda$bindThumbnail$1(ListViewHolder listViewHolder, Bitmap bitmap) {
        listViewHolder.bindImage(bitmap);
    }

    private void onItemClicked(CategoryPeopleItemViewHolder categoryPeopleItemViewHolder, MediaItem mediaItem) {
        if (!this.mSelectedItems.remove(mediaItem)) {
            this.mSelectedItems.add(mediaItem);
            categoryPeopleItemViewHolder.setChecked(true);
        } else {
            categoryPeopleItemViewHolder.setChecked(false);
        }
        notifySelectedItemChanged();
    }

    private void updateItemSideMargin(Context context) {
        int i2;
        Resources resources = context.getResources();
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            i2 = R.dimen.search_item_card_people_thumbnail_spacing_expansion_85;
        } else {
            i2 = R.dimen.search_category_people_item_side_margin;
        }
        this.mItemSideMargin = resources.getDimensionPixelOffset(i2);
    }

    public CategoryPeopleItemViewHolder createPeopleItemViewHolder(ViewGroup viewGroup, int i2) {
        int i7;
        LayoutInflater layoutInflater = this.mLayoutInflater;
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            i7 = R.layout.recycler_category_item_removable_creature_layout_85;
        } else {
            i7 = R.layout.recycler_category_item_people_layout_v3;
        }
        return new CategoryPeopleItemViewHolder(layoutInflater.inflate(i7, viewGroup, false), i2, true);
    }

    public void filterData(ArrayList<String> arrayList) {
        Iterator it = new ArrayList(this.mItems).iterator();
        boolean z = false;
        while (it.hasNext()) {
            MediaItem mediaItem = (MediaItem) it.next();
            if (arrayList.stream().noneMatch(new j(mediaItem, 12))) {
                this.mItems.remove(mediaItem);
                z = true;
            }
        }
        if (z) {
            notifyDataSetChanged();
        }
    }

    public ArrayList<MediaItem> getAllItems() {
        return this.mItems;
    }

    public MediaItem getItem(int i2) {
        if (i2 < 0 || i2 >= this.mItems.size()) {
            return null;
        }
        return this.mItems.get(i2);
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public ArrayList<MediaItem> getSelectedItems() {
        return this.mSelectedItems;
    }

    public void handleResolutionChanged(Context context) {
        updateItemSideMargin(context);
    }

    public void onRecycled() {
        this.mItems.clear();
        notifyDataSetChanged();
    }

    public void setData(ArrayList<MediaItem> arrayList, boolean z) {
        this.mItems = arrayList;
        this.mSelectedItems.clear();
        if (z) {
            notifySelectedItemChanged();
        }
    }

    public void setOnSelectedFaceChangedListener(Consumer<ArrayList<MediaItem>> consumer) {
        this.mCallback = consumer;
    }

    public void onBindViewHolder(CategoryPeopleItemViewHolder categoryPeopleItemViewHolder, int i2) {
        MediaItem item = getItem(i2);
        if (item != null) {
            categoryPeopleItemViewHolder.bind(item);
            categoryPeopleItemViewHolder.setOnItemClickListener(new a(9, this, categoryPeopleItemViewHolder));
            bindThumbnail(categoryPeopleItemViewHolder, item);
            ViewMarginUtils.setHorizontalMargin(categoryPeopleItemViewHolder.getRootView(), this.mItemSideMargin);
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                categoryPeopleItemViewHolder.setThumbnailShape(1, (float) categoryPeopleItemViewHolder.itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.search_category_people_item_radius_85));
            } else {
                categoryPeopleItemViewHolder.setThumbnailShape(0, 0.0f);
            }
            categoryPeopleItemViewHolder.setChecked(this.mSelectedItems.contains(item));
        }
    }

    public CategoryPeopleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return createPeopleItemViewHolder(viewGroup, i2);
    }
}
