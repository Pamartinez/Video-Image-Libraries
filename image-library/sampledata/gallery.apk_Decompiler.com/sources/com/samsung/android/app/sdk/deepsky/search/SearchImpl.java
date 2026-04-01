package com.samsung.android.app.sdk.deepsky.search;

import android.content.Context;
import com.samsung.android.app.sdk.deepsky.common.ContentProviderCaller;
import com.samsung.android.app.sdk.deepsky.common.SystemDataSource;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00132\u00020\u00012\u00020\u0001:\u0001\u0013B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/search/SearchImpl;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "contentProviderCaller", "Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;", "systemDatasource", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;)V", "", "checkIfAccessAllowed", "()Z", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;", "Lcom/samsung/android/app/sdk/deepsky/search/CommandSender;", "commandSender", "Lcom/samsung/android/app/sdk/deepsky/search/CommandSender;", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchImpl {
    public static final Companion Companion = new Companion((e) null);
    private CommandSender commandSender = new SearchImpl$commandSender$1(this);
    private final ContentProviderCaller contentProviderCaller;
    private final Context context;
    private final SystemDataSource systemDatasource;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/search/SearchImpl$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public SearchImpl(Context context2, ContentProviderCaller contentProviderCaller2, SystemDataSource systemDataSource) {
        j.e(context2, "context");
        j.e(contentProviderCaller2, "contentProviderCaller");
        j.e(systemDataSource, "systemDatasource");
        this.context = context2;
        this.contentProviderCaller = contentProviderCaller2;
        this.systemDatasource = systemDataSource;
    }

    public final boolean checkIfAccessAllowed() {
        return true;
    }
}
