package o;

import android.app.appsearch.AppSearchResult;
import androidx.appsearch.platformstorage.PlatformStorage;
import androidx.concurrent.futures.ResolvableFuture;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ ResolvableFuture d;
    public final /* synthetic */ PlatformStorage.GlobalSearchContext e;

    public /* synthetic */ d(ResolvableFuture resolvableFuture, PlatformStorage.GlobalSearchContext globalSearchContext) {
        this.d = resolvableFuture;
        this.e = globalSearchContext;
    }

    public final void accept(Object obj) {
        PlatformStorage.lambda$createGlobalSearchSessionAsync$1(this.d, this.e, (AppSearchResult) obj);
    }
}
