package androidx.appsearch.platformstorage.util;

import android.app.appsearch.BatchResultCallback;
import androidx.appsearch.app.AppSearchBatchResult;
import androidx.appsearch.platformstorage.converter.AppSearchResultToPlatformConverter;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.util.Preconditions;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BatchResultCallbackAdapter<K, PlatformValue, JetpackValue> implements BatchResultCallback {
    private final ResolvableFuture<AppSearchBatchResult<K, JetpackValue>> mFuture;
    private final Function<AppSearchBatchResult<K, JetpackValue>, AppSearchBatchResult<K, JetpackValue>> mResultTransformer;
    private final Function<PlatformValue, JetpackValue> mValueMapper;

    public BatchResultCallbackAdapter(ResolvableFuture<AppSearchBatchResult<K, JetpackValue>> resolvableFuture, Function<PlatformValue, JetpackValue> function) {
        this(resolvableFuture, function, (Function) null);
    }

    public void onResult(android.app.appsearch.AppSearchBatchResult<K, PlatformValue> appSearchBatchResult) {
        AppSearchBatchResult<K, JetpackValue> platformAppSearchBatchResultToJetpack = AppSearchResultToPlatformConverter.platformAppSearchBatchResultToJetpack(appSearchBatchResult, this.mValueMapper);
        Function<AppSearchBatchResult<K, JetpackValue>, AppSearchBatchResult<K, JetpackValue>> function = this.mResultTransformer;
        if (function != null) {
            platformAppSearchBatchResultToJetpack = function.apply(platformAppSearchBatchResultToJetpack);
        }
        this.mFuture.set(platformAppSearchBatchResultToJetpack);
    }

    public void onSystemError(Throwable th) {
        this.mFuture.setException(th);
    }

    public BatchResultCallbackAdapter(ResolvableFuture<AppSearchBatchResult<K, JetpackValue>> resolvableFuture, Function<PlatformValue, JetpackValue> function, Function<AppSearchBatchResult<K, JetpackValue>, AppSearchBatchResult<K, JetpackValue>> function2) {
        this.mFuture = (ResolvableFuture) Preconditions.checkNotNull(resolvableFuture);
        this.mValueMapper = (Function) Preconditions.checkNotNull(function);
        this.mResultTransformer = function2;
    }
}
