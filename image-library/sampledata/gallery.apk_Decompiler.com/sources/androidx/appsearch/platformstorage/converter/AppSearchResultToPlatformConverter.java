package androidx.appsearch.platformstorage.converter;

import androidx.appsearch.app.AppSearchBatchResult;
import androidx.appsearch.app.AppSearchResult;
import androidx.core.util.Preconditions;
import java.util.Map;
import java.util.function.Function;
import o.C0245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppSearchResultToPlatformConverter {
    public static <K, PlatformValue, JetpackValue> AppSearchBatchResult<K, JetpackValue> platformAppSearchBatchResultToJetpack(android.app.appsearch.AppSearchBatchResult<K, PlatformValue> appSearchBatchResult, Function<PlatformValue, JetpackValue> function) {
        Preconditions.checkNotNull(appSearchBatchResult);
        Preconditions.checkNotNull(function);
        AppSearchBatchResult.Builder builder = new AppSearchBatchResult.Builder();
        for (Map.Entry entry : appSearchBatchResult.getSuccesses().entrySet()) {
            try {
                builder.setSuccess(entry.getKey(), function.apply(entry.getValue()));
            } catch (Throwable th) {
                builder.setResult(entry.getKey(), AppSearchResult.throwableToFailedResult(th));
            }
        }
        for (Map.Entry entry2 : appSearchBatchResult.getFailures().entrySet()) {
            builder.setFailure(entry2.getKey(), C0245a.e(entry2.getValue()).getResultCode(), C0245a.e(entry2.getValue()).getErrorMessage());
        }
        return builder.build();
    }
}
