package com.samsung.android.gallery.module.search.engine;

import android.text.TextUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchResultCache {
    private static final SearchResultCache sInstance = new SearchResultCache();
    private final ConcurrentHashMap<String, SearchResult> mCaches = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SearchResult {
        ExtraResults extraResults;
        String ids;

        public SearchResult(String str, ExtraResults extraResults2) {
            this.ids = str;
            this.extraResults = extraResults2;
        }

        public String toString() {
            return this.ids.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX).length + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.ids.hashCode();
        }
    }

    public static SearchResultCache getInstance() {
        return sInstance;
    }

    public void clear() {
        this.mCaches.clear();
    }

    public ExtraResults getExtraResults(String str) {
        SearchResult orDefault = this.mCaches.getOrDefault(str, (Object) null);
        if (orDefault != null) {
            return orDefault.extraResults;
        }
        return null;
    }

    public String getIds(String str) {
        SearchResult orDefault = this.mCaches.getOrDefault(str, (Object) null);
        if (orDefault != null) {
            return orDefault.ids;
        }
        return null;
    }

    public void put(String str, String str2, ExtraResults extraResults) {
        if (!TextUtils.isEmpty(str2)) {
            this.mCaches.put(str, new SearchResult(str2, extraResults));
        }
    }
}
