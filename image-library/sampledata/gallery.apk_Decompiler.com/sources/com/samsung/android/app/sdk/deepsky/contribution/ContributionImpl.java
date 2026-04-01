package com.samsung.android.app.sdk.deepsky.contribution;

import android.content.Context;
import com.samsung.android.app.sdk.deepsky.common.AppSearch;
import com.samsung.android.app.sdk.deepsky.common.ShortcutManagerWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00102\u00020\u00012\u00020\u0001:\u0001\u0010B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contribution/ContributionImpl;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/common/AppSearch;", "appSearch", "Lcom/samsung/android/app/sdk/deepsky/common/ShortcutManagerWrapper;", "shortcutManagerWrapper", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/common/AppSearch;Lcom/samsung/android/app/sdk/deepsky/common/ShortcutManagerWrapper;)V", "", "checkIfAccessAllowed", "()Z", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/common/AppSearch;", "Lcom/samsung/android/app/sdk/deepsky/common/ShortcutManagerWrapper;", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ContributionImpl {
    public static final Companion Companion = new Companion((e) null);
    private final AppSearch appSearch;
    private final Context context;
    private final ShortcutManagerWrapper shortcutManagerWrapper;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contribution/ContributionImpl$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public ContributionImpl(Context context2, AppSearch appSearch2, ShortcutManagerWrapper shortcutManagerWrapper2) {
        j.e(context2, "context");
        j.e(appSearch2, "appSearch");
        j.e(shortcutManagerWrapper2, "shortcutManagerWrapper");
        this.context = context2;
        this.appSearch = appSearch2;
        this.shortcutManagerWrapper = shortcutManagerWrapper2;
    }

    public boolean checkIfAccessAllowed() {
        return true;
    }
}
