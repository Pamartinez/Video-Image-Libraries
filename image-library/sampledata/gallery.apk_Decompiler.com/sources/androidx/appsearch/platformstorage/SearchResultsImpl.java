package androidx.appsearch.platformstorage;

import android.app.appsearch.AppSearchResult;
import android.content.Context;
import android.os.Build;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.app.SearchResult;
import androidx.appsearch.app.SearchResults;
import androidx.appsearch.app.SearchSpec;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.appsearch.platformstorage.converter.SearchResultToPlatformConverter;
import androidx.appsearch.platformstorage.util.AppSearchVersionUtil;
import androidx.collection.ArraySet;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import o.C0245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchResultsImpl implements SearchResults {
    private final Context mContext;
    private final Executor mExecutor;
    private final android.app.appsearch.SearchResults mPlatformResults;
    private final SearchSpec mSearchSpec;

    public SearchResultsImpl(android.app.appsearch.SearchResults searchResults, SearchSpec searchSpec, Executor executor, Context context) {
        this.mPlatformResults = C0245a.m(Preconditions.checkNotNull(searchResults));
        this.mSearchSpec = (SearchSpec) Preconditions.checkNotNull(searchSpec);
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mContext = (Context) Preconditions.checkNotNull(context);
    }

    private void applyProjectionToNestedDocument(GenericDocument.Builder builder, GenericDocument genericDocument, String str, Set<String> set) {
        ArraySet arraySet = new ArraySet();
        for (String next : set) {
            if (next.startsWith(str + ".")) {
                arraySet.add(next.substring(str.length() + 1));
            }
        }
        if (!arraySet.isEmpty()) {
            GenericDocument.Builder builder2 = new GenericDocument.Builder(genericDocument);
            for (String next2 : genericDocument.getPropertyNames()) {
                GenericDocument propertyDocument = genericDocument.getPropertyDocument(next2);
                if (propertyDocument != null) {
                    applyProjectionToNestedDocument(builder2, propertyDocument, next2, arraySet);
                } else if (!arraySet.contains(next2)) {
                    builder2.clearProperty(next2);
                }
            }
            builder.setPropertyDocument(str, builder2.build());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getNextPageAsync$0(ResolvableFuture resolvableFuture, AppSearchResult appSearchResult) {
        boolean z;
        boolean z3;
        if (appSearchResult.isSuccess()) {
            List list = (List) appSearchResult.getResultValue();
            ArrayList arrayList = new ArrayList(list.size());
            Map<String, List<String>> projections = this.mSearchSpec.getProjections();
            boolean z7 = true;
            if (Build.VERSION.SDK_INT < 34) {
                z = true;
            } else {
                z = false;
            }
            if (AppSearchVersionUtil.getAppSearchVersionCode(this.mContext) < 340800000) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!z || !z3 || projections.isEmpty()) {
                z7 = false;
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                int i7 = Build.VERSION.SDK_INT;
                if ((i7 == 31 || i7 == 32) && i2 == 0 && !this.mSearchSpec.getFilterNamespaces().isEmpty() && !this.mSearchSpec.getFilterNamespaces().contains(C0245a.k(list.get(i2)).getGenericDocument().getNamespace())) {
                    resolvableFuture.set(Collections.EMPTY_LIST);
                    return;
                }
                SearchResult jetpackSearchResult = SearchResultToPlatformConverter.toJetpackSearchResult(C0245a.k(list.get(i2)));
                if (z7) {
                    arrayList.add(manuallyApplyProjection(jetpackSearchResult, projections));
                } else {
                    arrayList.add(jetpackSearchResult);
                }
            }
            resolvableFuture.set(arrayList);
            return;
        }
        resolvableFuture.setException(new AppSearchException(appSearchResult.getResultCode(), appSearchResult.getErrorMessage()));
    }

    private SearchResult manuallyApplyProjection(SearchResult searchResult, Map<String, List<String>> map) {
        ArraySet arraySet;
        boolean z;
        GenericDocument genericDocument = searchResult.getGenericDocument();
        GenericDocument.Builder builder = new GenericDocument.Builder(genericDocument);
        List list = map.get(genericDocument.getSchemaType());
        List list2 = map.get("*");
        if (list != null) {
            arraySet = new ArraySet(list);
        } else if (list2 != null) {
            arraySet = new ArraySet(list2);
        } else {
            arraySet = null;
        }
        if (arraySet != null) {
            for (String next : genericDocument.getPropertyNames()) {
                GenericDocument propertyDocument = genericDocument.getPropertyDocument(next);
                if (propertyDocument != null) {
                    applyProjectionToNestedDocument(builder, propertyDocument, next, arraySet);
                } else if (!arraySet.contains(next)) {
                    builder.clearProperty(next);
                }
            }
        }
        SearchResult.Builder genericDocument2 = new SearchResult.Builder(searchResult).setGenericDocument(builder.build());
        List<SearchResult.MatchInfo> matchInfos = searchResult.getMatchInfos();
        if (!matchInfos.isEmpty()) {
            genericDocument2.clearMatchInfos();
        }
        for (SearchResult.MatchInfo next2 : matchInfos) {
            String propertyPath = next2.getPropertyPath();
            boolean z3 = false;
            if (list == null || !list.contains(propertyPath)) {
                z = false;
            } else {
                z = true;
            }
            if (list2 != null && list2.contains(propertyPath)) {
                z3 = true;
            }
            if (z || z3) {
                genericDocument2.addMatchInfo(new SearchResult.MatchInfo.Builder(next2).build());
            }
        }
        return genericDocument2.build();
    }

    public void close() {
        this.mPlatformResults.close();
    }

    public ListenableFuture getNextPageAsync() {
        ResolvableFuture create = ResolvableFuture.create();
        this.mPlatformResults.getNextPage(this.mExecutor, new a(this, create));
        return create;
    }
}
