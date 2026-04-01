package com.samsung.android.gallery.app.ui.list.search.toolbar;

import A4.H;
import A4.Q;
import A6.a;
import A9.b;
import B8.e;
import J5.d;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.search.filter.SearchFilterUtil;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersView;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchSelectedFiltersDelegate {
    private final Blackboard mBlackboard;
    private final Context mContext;
    private final SearchSelectedFiltersView mFilterView;
    private final View mSearchTextView;

    public SearchSelectedFiltersDelegate(IBaseListView iBaseListView, SearchSelectedFiltersView searchSelectedFiltersView, View view) {
        this.mBlackboard = iBaseListView.getBlackboard();
        this.mContext = iBaseListView.getContext();
        this.mFilterView = searchSelectedFiltersView;
        this.mSearchTextView = view;
        initMainKeywordEntity(iBaseListView.getLocationKey());
        initSubKeywordEntities();
    }

    private boolean fromMyQuery(String str) {
        return ArgumentsUtil.getArgValue(str, "my_query", false);
    }

    private void initMainKeywordEntity(String str) {
        String argValue = ArgumentsUtil.getArgValue(str, "term", "");
        if (!fromMyQuery(str) && TextUtils.equals(argValue, "key_word")) {
            return;
        }
        if (!TextUtils.equals(argValue, "persontag") || ArgumentsUtil.getArgValue(str, "isNamed", false)) {
            if (this.mFilterView != null) {
                String str2 = "title";
                if ((!fromMyQuery(str) || !IntelligentSearchIndexField.CREATURE_TERM.contains(argValue) || !TextUtils.isEmpty(ArgumentsUtil.getArgValue(str, str2, ""))) && !"multi_modal".equals(argValue)) {
                    this.mFilterView.initMainKeywordEntity(str);
                } else {
                    if (!"multi_modal".equals(argValue)) {
                        str2 = "sub";
                    }
                    SimpleThreadPool.getInstance().submit(new b(this, argValue, ArgumentsUtil.getArgValue(str, str2, ""), 29));
                }
            }
            ViewUtils.setVisibility(this.mFilterView, 0);
            ViewUtils.setVisibility(this.mSearchTextView, 4);
        }
    }

    private void initSubKeywordEntities() {
        String argValue = ArgumentsUtil.getArgValue((String) this.mBlackboard.read("location://variable/currentv1"), "SelectedFilter", "");
        if (!TextUtils.isEmpty(argValue)) {
            SimpleThreadPool.getInstance().execute(new d(this, SearchFilterUtil.getFilterEntities(argValue), 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSubKeywordEntities$3(ArrayList arrayList) {
        if (this.mFilterView != null && !arrayList.isEmpty()) {
            this.mFilterView.initSubEntities(arrayList, true);
        }
        this.mBlackboard.postEvent(EventMessage.obtain(8522));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSubKeywordEntities$4(ArrayList arrayList) {
        IdentityCreatureUtil.Category category;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        HashMap hashMap = new HashMap();
        Iterator it = arrayList.iterator();
        FilterResultsEntity filterResultsEntity = null;
        while (it.hasNext()) {
            FilterResultsEntity filterResultsEntity2 = (FilterResultsEntity) it.next();
            if (filterResultsEntity2.isCreature()) {
                long parseLong = Long.parseLong(filterResultsEntity2.getRawLabels());
                if (filterResultsEntity2.isPet()) {
                    arrayList3.add(parseLong + "");
                } else {
                    arrayList2.add(parseLong + "");
                }
                if (filterResultsEntity2.isPerson()) {
                    category = IdentityCreatureUtil.Category.PEOPLE;
                } else {
                    category = IdentityCreatureUtil.Category.PET;
                }
                hashMap.put(IdentityCreatureUtil.createWithUnifiedId(parseLong, category), filterResultsEntity2);
            } else if (TextUtils.equals("face_count", filterResultsEntity2.getCategory())) {
                filterResultsEntity = filterResultsEntity2;
            }
        }
        if (filterResultsEntity != null) {
            arrayList.remove(filterResultsEntity);
        }
        loadCreatureThumb(arrayList2, arrayList3, hashMap);
        for (FilterResultsEntity filterResultsEntity3 : hashMap.values()) {
            if (filterResultsEntity3.isCreature() && filterResultsEntity3.getThumb() == null) {
                filterResultsEntity3.setThumb(ThumbnailLoader.getInstance().getReplacedThumbnail(this.mContext, ResourceUtil.getBrokenDrawable((MediaItem) null), ResourceUtil.getBrokenBgColor((MediaItem) null)));
            }
        }
        ThreadUtil.postOnUiThreadDelayed(new d(this, arrayList, 1), 200);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postThumbnailTypeMainFilter$2(String str, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.runOnUiThread(new a((Object) this, (Object) bitmap, (Object) str, (Object) mediaItem2, 7));
    }

    private void loadCreatureThumb(ArrayList<String> arrayList, ArrayList<String> arrayList2, HashMap<String, FilterResultsEntity> hashMap) {
        if (!arrayList.isEmpty()) {
            Iterator<MediaItem> it = PeopleDataManager.loadItems(arrayList).iterator();
            while (it.hasNext()) {
                setCreatureThumb(it.next(), hashMap);
            }
        }
        if (!arrayList2.isEmpty()) {
            Iterator<MediaItem> it2 = PetDataManager.loadItems(arrayList2).iterator();
            while (it2.hasNext()) {
                setCreatureThumb(it2.next(), hashMap);
            }
        }
    }

    private MediaItem loadMediaItem(String str, String str2) {
        ArrayList<MediaItem> loadItemsFromCache;
        if (IntelligentSearchIndexField.CREATURE_TERM.contains(str)) {
            ArrayList arrayList = new ArrayList(List.of(String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str2))));
            if (IdentityCreatureUtil.isPerson(str2)) {
                loadItemsFromCache = PeopleDataManager.loadItemsFromCache(arrayList);
            } else {
                loadItemsFromCache = PetDataManager.loadItemsFromCache(arrayList);
            }
            return loadItemsFromCache.get(0);
        } else if (!"multi_modal".equals(str)) {
            return null;
        } else {
            Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).setFileId(Long.parseLong(str2)));
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        MediaItem load = MediaItemLoader.load(query);
                        query.close();
                        return load;
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        }
        throw th;
    }

    /* access modifiers changed from: private */
    /* renamed from: postThumbnailTypeMainFilter */
    public void lambda$initMainKeywordEntity$0(String str, String str2) {
        MediaItem loadMediaItem;
        UniqueKey uniqueKey;
        if (!TextUtils.isEmpty(str2) && (loadMediaItem = loadMediaItem(str, str2)) != null) {
            ArrayList<String> arrayList = IntelligentSearchIndexField.CREATURE_TERM;
            if (arrayList.contains(str)) {
                str2 = String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str2));
            }
            if (arrayList.contains(str)) {
                String subCategory = loadMediaItem.getSubCategory();
                Objects.requireNonNull(subCategory);
                uniqueKey = new M8.a(subCategory, 9);
            } else {
                uniqueKey = new e(loadMediaItem, 1);
            }
            ThumbnailLoader.getInstance().getOrLoad(loadMediaItem, ThumbKind.MEDIUM_KIND, uniqueKey, new Q((Object) this, (Object) str2, (Object) loadMediaItem, 4));
        }
    }

    private void setCreatureThumb(MediaItem mediaItem, HashMap<String, FilterResultsEntity> hashMap) {
        if (mediaItem != null) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.SMALL_NC_KIND;
            String subCategory = mediaItem.getSubCategory();
            Objects.requireNonNull(subCategory);
            instance.getOrLoad(mediaItem, thumbKind, new M8.a(subCategory, 9), new H(24, (Object) hashMap, (Object) mediaItem));
        }
    }

    public void addMainFilter(Bitmap bitmap, String str) {
        lambda$postThumbnailTypeMainFilter$1(bitmap, str, (MediaItem) null);
    }

    public Object getFilterResult() {
        SearchSelectedFiltersView searchSelectedFiltersView = this.mFilterView;
        if (searchSelectedFiltersView != null) {
            return searchSelectedFiltersView.getFilterResult();
        }
        return null;
    }

    public boolean hasFilter() {
        return this.mFilterView.hasFilter();
    }

    public void notifyDataPrepared() {
        this.mFilterView.notifyDataPrepared();
    }

    public void onDestroy() {
        this.mBlackboard.erase("data://user/SearchToolbarSelectedFilters");
    }

    public void replaceMainFilter(String str) {
        this.mFilterView.replaceMainFilter(str);
    }

    public void showIme() {
        this.mFilterView.showIme();
    }

    public void updateFadingEdge() {
        this.mFilterView.updateFadingEdgeEnable();
    }

    /* renamed from: addMainFilter */
    public void lambda$postThumbnailTypeMainFilter$1(Bitmap bitmap, String str, MediaItem mediaItem) {
        this.mFilterView.addMainFilter(str, (String) null, bitmap, true, mediaItem);
    }
}
