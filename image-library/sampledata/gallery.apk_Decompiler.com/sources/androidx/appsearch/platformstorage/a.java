package androidx.appsearch.platformstorage;

import android.app.appsearch.AppSearchResult;
import androidx.concurrent.futures.ResolvableFuture;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ SearchResultsImpl d;
    public final /* synthetic */ ResolvableFuture e;

    public /* synthetic */ a(SearchResultsImpl searchResultsImpl, ResolvableFuture resolvableFuture) {
        this.d = searchResultsImpl;
        this.e = resolvableFuture;
    }

    public final void accept(Object obj) {
        this.d.lambda$getNextPageAsync$0(this.e, (AppSearchResult) obj);
    }
}
