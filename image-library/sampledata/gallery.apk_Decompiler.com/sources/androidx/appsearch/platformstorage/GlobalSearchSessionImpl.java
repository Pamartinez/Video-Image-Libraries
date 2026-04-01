package androidx.appsearch.platformstorage;

import android.app.appsearch.AppSearchResult;
import android.app.appsearch.BatchResultCallback;
import android.app.appsearch.GetByDocumentIdRequest;
import android.app.appsearch.GetSchemaResponse;
import android.app.appsearch.observer.ObserverCallback;
import android.app.appsearch.observer.ObserverSpec;
import android.content.Context;
import android.os.Build;
import androidx.appsearch.app.Features;
import androidx.appsearch.app.GlobalSearchSession;
import androidx.appsearch.app.SearchResults;
import androidx.appsearch.app.SearchSpec;
import androidx.appsearch.platformstorage.converter.RequestToPlatformConverter;
import androidx.appsearch.platformstorage.converter.SearchSpecToPlatformConverter;
import androidx.appsearch.platformstorage.util.BatchResultCallbackAdapter;
import androidx.collection.ArrayMap;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.samsung.android.gallery.plugins.portrait.t;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class GlobalSearchSessionImpl implements GlobalSearchSession {
    private final Context mContext;
    private final Executor mExecutor;
    private final Features mFeatures;
    private final Map<Object, ObserverCallback> mObserverCallbacksLocked = new ArrayMap();
    private final android.app.appsearch.GlobalSearchSession mPlatformSession;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ApiHelperForT {
        public static void getByDocumentId(android.app.appsearch.GlobalSearchSession globalSearchSession, String str, String str2, GetByDocumentIdRequest getByDocumentIdRequest, Executor executor, BatchResultCallback batchResultCallback) {
            globalSearchSession.getByDocumentId(str, str2, getByDocumentIdRequest, executor, batchResultCallback);
        }

        public static void getSchema(android.app.appsearch.GlobalSearchSession globalSearchSession, String str, String str2, Executor executor, Consumer<AppSearchResult<GetSchemaResponse>> consumer) {
            globalSearchSession.getSchema(str, str2, executor, consumer);
        }

        public static void registerObserverCallback(android.app.appsearch.GlobalSearchSession globalSearchSession, String str, ObserverSpec observerSpec, Executor executor, ObserverCallback observerCallback) {
            globalSearchSession.registerObserverCallback(str, observerSpec, executor, observerCallback);
        }

        public static void unregisterObserverCallback(android.app.appsearch.GlobalSearchSession globalSearchSession, String str, ObserverCallback observerCallback) {
            globalSearchSession.unregisterObserverCallback(str, observerCallback);
        }
    }

    public GlobalSearchSessionImpl(android.app.appsearch.GlobalSearchSession globalSearchSession, Executor executor, Context context) {
        this.mPlatformSession = t.f(Preconditions.checkNotNull(globalSearchSession));
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        Context context2 = (Context) Preconditions.checkNotNull(context);
        this.mContext = context2;
        this.mFeatures = new FeaturesImpl(context2);
    }

    public void close() {
        this.mPlatformSession.close();
    }

    /* JADX WARNING: type inference failed for: r7v2, types: [java.lang.Object, java.util.function.Function] */
    public ListenableFuture getByDocumentIdAsync(String str, String str2, androidx.appsearch.app.GetByDocumentIdRequest getByDocumentIdRequest) {
        if (Build.VERSION.SDK_INT >= 33) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(str2);
            Preconditions.checkNotNull(getByDocumentIdRequest);
            ResolvableFuture create = ResolvableFuture.create();
            ApiHelperForT.getByDocumentId(this.mPlatformSession, str, str2, RequestToPlatformConverter.toPlatformGetByDocumentIdRequest(getByDocumentIdRequest), this.mExecutor, new BatchResultCallbackAdapter(create, new Object()));
            return create;
        }
        throw new UnsupportedOperationException("GLOBAL_SEARCH_SESSION_GET_BY_ID is not supported on this AppSearch implementation.");
    }

    public SearchResults search(String str, SearchSpec searchSpec) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(searchSpec);
        return new SearchResultsImpl(this.mPlatformSession.search(str, SearchSpecToPlatformConverter.toPlatformSearchSpec(this.mContext, searchSpec)), searchSpec, this.mExecutor, this.mContext);
    }
}
