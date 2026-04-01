package com.samsung.android.gallery.widget.search.filter;

import B8.e;
import F8.a;
import J.g;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsFiltersAdapter extends RecyclerView.Adapter<SearchFiltersViewHolder> {
    final String TAG = getClass().getSimpleName();
    final Blackboard mBlackboard;

    public AbsFiltersAdapter(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private void bindThumbnailTypeViewHolder(SearchFiltersViewHolder searchFiltersViewHolder, FilterResultsEntity filterResultsEntity, boolean z) {
        if (filterResultsEntity.getThumb() == null) {
            SimpleThreadPool.getInstance().execute(new a((Object) this, (Object) filterResultsEntity, (Object) searchFiltersViewHolder, z, 5));
            return;
        }
        searchFiltersViewHolder.bindThumbnailTypeViewHolder(filterResultsEntity.getThumb(), z, isEnabled());
    }

    private void bindViewHolderInternal(SearchFiltersViewHolder searchFiltersViewHolder, FilterResultsEntity filterResultsEntity, boolean z) {
        searchFiltersViewHolder.bind(filterResultsEntity.getName(), z, isEnabled(), filterResultsEntity.getFieldIcon());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnailTypeViewHolder$1(FilterResultsEntity filterResultsEntity, SearchFiltersViewHolder searchFiltersViewHolder, boolean z) {
        MediaItem loadMediaItem = loadMediaItem(filterResultsEntity);
        if (loadMediaItem != null) {
            loadThumb(searchFiltersViewHolder, filterResultsEntity, z, loadMediaItem);
            return;
        }
        String str = this.TAG;
        Log.sw(str, "bindThumbnailTypeViewHolder : mediaItem is null (" + filterResultsEntity.getRawLabels() + ")");
        ThreadUtil.postOnUiThread(new Ob.a(19, this, filterResultsEntity));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadThumb$2(FilterResultsEntity filterResultsEntity, MediaItem mediaItem, Bitmap bitmap, SearchFiltersViewHolder searchFiltersViewHolder, boolean z) {
        setEntityThumb(filterResultsEntity, mediaItem, bitmap);
        searchFiltersViewHolder.bindThumbnailTypeViewHolder(filterResultsEntity.getThumb(), z, isEnabled());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadThumb$3(FilterResultsEntity filterResultsEntity, MediaItem mediaItem, SearchFiltersViewHolder searchFiltersViewHolder, boolean z, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        boolean z3 = z;
        Bitmap bitmap2 = bitmap;
        SearchFiltersViewHolder searchFiltersViewHolder2 = searchFiltersViewHolder;
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.postOnUiThread(new Tb.a(this, filterResultsEntity, mediaItem2, bitmap2, searchFiltersViewHolder2, z3));
    }

    private void setEntityThumb(FilterResultsEntity filterResultsEntity, MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap == null) {
            mediaItem.setBroken(true);
            bitmap = ThumbnailLoader.getInstance().getReplacedThumbnail((Context) null, ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
        }
        filterResultsEntity.setThumb(bitmap);
        filterResultsEntity.setMediaItem(mediaItem);
    }

    public abstract void addClickListener(SearchFiltersViewHolder searchFiltersViewHolder);

    public abstract int getItemLayoutResId();

    public boolean isEnabled() {
        return true;
    }

    public MediaItem loadMediaItem(FilterResultsEntity filterResultsEntity) {
        IdentityCreatureUtil.Category category;
        MediaItem mediaItem = filterResultsEntity.getMediaItem();
        if (mediaItem == null) {
            if (filterResultsEntity.isCreature()) {
                long j2 = UnsafeCast.toLong(filterResultsEntity.getRawLabels(), 0);
                if (j2 != 0) {
                    if (filterResultsEntity.isPerson()) {
                        category = IdentityCreatureUtil.Category.PEOPLE;
                    } else {
                        category = IdentityCreatureUtil.Category.PET;
                    }
                    String createWithUnifiedId = IdentityCreatureUtil.createWithUnifiedId(j2, category);
                    if (filterResultsEntity.isPerson()) {
                        return PeopleDataManager.loadHeaderItem(createWithUnifiedId);
                    }
                    return PetDataManager.loadHeaderItem(createWithUnifiedId);
                }
            } else if (filterResultsEntity.isMultiModal()) {
                Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).setFileId(UnsafeCast.toLong(filterResultsEntity.getRawLabels(), -1)));
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            mediaItem = MediaItemLoader.load(query);
                        }
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                }
                if (query != null) {
                    query.close();
                }
            }
        }
        return mediaItem;
        throw th;
    }

    public void loadThumb(SearchFiltersViewHolder searchFiltersViewHolder, FilterResultsEntity filterResultsEntity, boolean z, MediaItem mediaItem) {
        UniqueKey uniqueKey;
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.SMALL_NC_KIND;
        if (filterResultsEntity.isCreature()) {
            String subCategory = mediaItem.getSubCategory();
            Objects.requireNonNull(subCategory);
            uniqueKey = new M8.a(subCategory, 9);
        } else {
            Objects.requireNonNull(mediaItem);
            uniqueKey = new e(mediaItem, 1);
        }
        MediaItem mediaItem2 = mediaItem;
        instance.getOrLoad(mediaItem2, thumbKind, uniqueKey, new g(2, (Object) this, (Object) filterResultsEntity, (Object) mediaItem2, (Object) searchFiltersViewHolder, z));
    }

    public void onBindViewHolder(SearchFiltersViewHolder searchFiltersViewHolder, FilterResultsEntity filterResultsEntity, boolean z) {
        if (filterResultsEntity.isThumbnailType()) {
            bindThumbnailTypeViewHolder(searchFiltersViewHolder, filterResultsEntity, z);
        } else {
            bindViewHolderInternal(searchFiltersViewHolder, filterResultsEntity, z);
        }
    }

    /* renamed from: removeEntity */
    public abstract void lambda$bindThumbnailTypeViewHolder$0(FilterResultsEntity filterResultsEntity);

    public boolean supportTheme() {
        return false;
    }

    public SearchFiltersViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        SearchFiltersViewHolder searchFiltersViewHolder = new SearchFiltersViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayoutResId(), viewGroup, false), i2, supportTheme());
        addClickListener(searchFiltersViewHolder);
        return searchFiltersViewHolder;
    }
}
