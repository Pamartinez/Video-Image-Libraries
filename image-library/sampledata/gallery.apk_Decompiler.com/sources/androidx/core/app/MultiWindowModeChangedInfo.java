package androidx.core.app;

import android.content.res.Configuration;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0019\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\u0003\u0010\nR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/core/app/MultiWindowModeChangedInfo;", "", "", "isInMultiWindowMode", "<init>", "(Z)V", "Landroid/content/res/Configuration;", "newConfig", "(ZLandroid/content/res/Configuration;)V", "Z", "()Z", "newConfiguration", "Landroid/content/res/Configuration;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MultiWindowModeChangedInfo {
    private final boolean isInMultiWindowMode;
    private Configuration newConfiguration;

    public MultiWindowModeChangedInfo(boolean z) {
        this.isInMultiWindowMode = z;
    }

    public final boolean isInMultiWindowMode() {
        return this.isInMultiWindowMode;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MultiWindowModeChangedInfo(boolean z, Configuration configuration) {
        this(z);
        j.e(configuration, "newConfig");
        this.newConfiguration = configuration;
    }
}
