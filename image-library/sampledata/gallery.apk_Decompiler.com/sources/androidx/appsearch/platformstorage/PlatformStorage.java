package androidx.appsearch.platformstorage;

import android.app.appsearch.AppSearchManager;
import android.app.appsearch.AppSearchResult;
import android.content.Context;
import androidx.appsearch.app.AppSearchEnvironmentFactory;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.samsung.android.gallery.plugins.portrait.t;
import java.util.concurrent.Executor;
import o.C0245a;
import o.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PlatformStorage {
    static final Executor EXECUTOR = AppSearchEnvironmentFactory.getEnvironmentInstance().createCachedThreadPoolExecutor();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class GlobalSearchContext {
        final Context mContext;
        final Executor mExecutor;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            private final Context mContext;
            private Executor mExecutor;

            public Builder(Context context) {
                this.mContext = (Context) Preconditions.checkNotNull(context);
            }

            public GlobalSearchContext build() {
                if (this.mExecutor == null) {
                    this.mExecutor = PlatformStorage.EXECUTOR;
                }
                return new GlobalSearchContext(this.mContext, this.mExecutor);
            }

            public Builder setWorkerExecutor(Executor executor) {
                Preconditions.checkNotNull(executor);
                this.mExecutor = executor;
                return this;
            }
        }

        public GlobalSearchContext(Context context, Executor executor) {
            this.mContext = (Context) Preconditions.checkNotNull(context);
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        }
    }

    public static ListenableFuture createGlobalSearchSessionAsync(GlobalSearchContext globalSearchContext) {
        Preconditions.checkNotNull(globalSearchContext);
        AppSearchManager d = C0245a.d(globalSearchContext.mContext.getSystemService(C0245a.n()));
        ResolvableFuture create = ResolvableFuture.create();
        d.createGlobalSearchSession(globalSearchContext.mExecutor, new d(create, globalSearchContext));
        return create;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createGlobalSearchSessionAsync$1(ResolvableFuture resolvableFuture, GlobalSearchContext globalSearchContext, AppSearchResult appSearchResult) {
        if (appSearchResult.isSuccess()) {
            resolvableFuture.set(new GlobalSearchSessionImpl(t.f(appSearchResult.getResultValue()), globalSearchContext.mExecutor, globalSearchContext.mContext));
        } else {
            resolvableFuture.setException(new AppSearchException(appSearchResult.getResultCode(), appSearchResult.getErrorMessage()));
        }
    }
}
