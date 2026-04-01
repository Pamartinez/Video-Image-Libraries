package com.samsung.android.app.sdk.deepsky.smartwidget;

import com.samsung.android.app.sdk.deepsky.common.ContentProviderCaller;
import com.samsung.android.app.sdk.deepsky.common.SystemDataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00102\u00020\u00012\u00020\u0001:\u0001\u0010B\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/smartwidget/SmartWidgetImpl;", "", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "contentProviderCaller", "Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;", "systemDataSource", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;)V", "", "checkIfAccessAllowed", "()Z", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;", "Ljava/util/concurrent/ExecutorService;", "mExecutorService", "Ljava/util/concurrent/ExecutorService;", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SmartWidgetImpl {
    public static final Companion Companion = new Companion((e) null);
    private final ContentProviderCaller contentProviderCaller;
    private final ExecutorService mExecutorService;
    private final SystemDataSource systemDataSource;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/smartwidget/SmartWidgetImpl$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public SmartWidgetImpl(ContentProviderCaller contentProviderCaller2, SystemDataSource systemDataSource2) {
        j.e(contentProviderCaller2, "contentProviderCaller");
        j.e(systemDataSource2, "systemDataSource");
        this.contentProviderCaller = contentProviderCaller2;
        this.systemDataSource = systemDataSource2;
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        j.d(newSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this.mExecutorService = newSingleThreadExecutor;
    }

    public boolean checkIfAccessAllowed() {
        return true;
    }
}
