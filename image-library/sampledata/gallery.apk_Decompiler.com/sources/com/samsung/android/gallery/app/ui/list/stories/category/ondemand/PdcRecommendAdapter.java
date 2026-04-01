package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import Ad.C0720a;
import B8.e;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PdcRecommendAdapter extends RecyclerView.Adapter<PdcRecommendItemViewHolder> {
    private String mCurrentRelation;
    private Consumer<Boolean> mItemSelectListener;
    private LinkedHashMap<String, ArrayList<MediaItem>> mRecommendItemMap = new LinkedHashMap<>();
    private Map<String, List<Long>> mRecommendMap = new LinkedHashMap();
    private ArrayList<MediaItem> mRelationItems = new ArrayList<>();
    private ArrayList<String> mRelations = new ArrayList<>();
    private final ArrayList<MediaItem> mSelectedItems = new ArrayList<>();

    private void bindThumbnail(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$bindThumbnail$4(listViewHolder, memCache);
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new b(this, listViewHolder));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$5(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new a(this, listViewHolder, bitmap, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(PdcRecommendItemViewHolder pdcRecommendItemViewHolder, ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        onItemClicked(pdcRecommendItemViewHolder, mediaItem);
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindThumbnail */
    public void lambda$bindThumbnail$4(ListViewHolder listViewHolder, Bitmap bitmap) {
        listViewHolder.bindImage(bitmap);
    }

    private void onItemClicked(PdcRecommendItemViewHolder pdcRecommendItemViewHolder, MediaItem mediaItem) {
        if (!this.mSelectedItems.remove(mediaItem)) {
            this.mSelectedItems.add(mediaItem);
            pdcRecommendItemViewHolder.setChecked(true);
            Optional.ofNullable(this.mItemSelectListener).ifPresent(new d(0));
            return;
        }
        pdcRecommendItemViewHolder.setChecked(false);
        Optional.ofNullable(this.mItemSelectListener).ifPresent(new d(1));
    }

    private void relationTo(int i2) {
        if (i2 > 0) {
            this.mRecommendMap.remove(this.mCurrentRelation);
        }
        String str = this.mRelations.get(i2);
        this.mCurrentRelation = str;
        this.mRelationItems = this.mRecommendItemMap.get(str);
    }

    public String getCurrentRelationKey() {
        return this.mCurrentRelation;
    }

    public MediaItem getItem(int i2) {
        if (i2 < 0 || i2 >= this.mRelationItems.size()) {
            return null;
        }
        return this.mRelationItems.get(i2);
    }

    public int getItemCount() {
        return this.mRelationItems.size();
    }

    public String getRelationName() {
        String str;
        String str2 = this.mCurrentRelation;
        if (str2 != null) {
            str = RelationshipKeySet.getRelationshipTypes(str2);
        } else {
            str = null;
        }
        if (str != null) {
            return RelationshipKeySet.getRelationshipName(AppResources.getAppContext(), str);
        }
        return " ";
    }

    public Map<String, List<Long>> getRemainedRelations() {
        return this.mRecommendMap;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.function.Function] */
    public ArrayList<String> getSelectedCreatures() {
        return (ArrayList) this.mSelectedItems.stream().map(new Object()).collect(Collectors.toCollection(new C0720a(1)));
    }

    public ArrayList<MediaItem> getSelectedItems() {
        return this.mSelectedItems;
    }

    public boolean hasMoreItems() {
        if (this.mRelations.indexOf(this.mCurrentRelation) < this.mRelations.size() - 1) {
            return true;
        }
        return false;
    }

    public boolean next() {
        int i2;
        if (!hasMoreItems()) {
            return false;
        }
        String str = this.mCurrentRelation;
        if (str == null) {
            i2 = -1;
        } else {
            i2 = this.mRelations.indexOf(str);
        }
        relationTo(i2 + 1);
        if (this.mRelationItems == null) {
            return false;
        }
        notifyDataSetChanged();
        return true;
    }

    public PdcRecommendItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new PdcRecommendItemViewHolder(C0086a.d(viewGroup, R.layout.recycler_stories_pdc_recommend_item_layout, viewGroup, false), i2, true);
    }

    public void setData(HashMap<String, ArrayList<MediaItem>> hashMap, Map<String, List<Long>> map) {
        this.mRelations = new ArrayList<>(map.keySet());
        this.mSelectedItems.clear();
        this.mRecommendItemMap = new LinkedHashMap<>(hashMap);
        this.mRecommendMap = map;
        this.mCurrentRelation = null;
    }

    public void setItemSelectListener(Consumer<Boolean> consumer) {
        this.mItemSelectListener = consumer;
    }

    public void onBindViewHolder(PdcRecommendItemViewHolder pdcRecommendItemViewHolder, int i2) {
        MediaItem item = getItem(i2);
        if (item != null) {
            pdcRecommendItemViewHolder.bind(item);
            pdcRecommendItemViewHolder.setOnItemClickListener(new b(this, pdcRecommendItemViewHolder));
            bindThumbnail(pdcRecommendItemViewHolder, item);
            pdcRecommendItemViewHolder.setThumbnailShape(0, 0.0f);
            pdcRecommendItemViewHolder.setChecked(this.mSelectedItems.contains(item));
        }
    }
}
