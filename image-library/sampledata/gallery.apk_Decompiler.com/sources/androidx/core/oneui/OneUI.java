package androidx.core.oneui;

import androidx.reflect.os.SeslBuildReflector$SeslVersionReflector;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Landroidx/core/oneui/OneUI;", "", "<init>", "()V", "", "getCurrentSepSdkVersion", "()I", "Landroidx/core/oneui/OneUI$Version;", "version", "", "isGreaterOrEqual", "(Landroidx/core/oneui/OneUI$Version;)Z", "currentSepSdkVersion", "I", "Version", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OneUI {
    public static final OneUI INSTANCE = new OneUI();
    private static final int currentSepSdkVersion = SeslBuildReflector$SeslVersionReflector.getField_SEM_PLATFORM_INT();

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\rj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Landroidx/core/oneui/OneUI$Version;", "", "", "displayName", "", "sepSdkVersion", "<init>", "(Ljava/lang/String;ILjava/lang/String;I)V", "Ljava/lang/String;", "getDisplayName", "()Ljava/lang/String;", "I", "getSepSdkVersion", "()I", "Companion", "UNKNOWN", "ONEUI_8_0", "ONEUI_8_5", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Version {
        UNKNOWN(C2paManifestList.UNKNOWN_VALUE, Integer.MIN_VALUE),
        ONEUI_8_0("8.0", 170000),
        ONEUI_8_5("8.5", 170500);
        
        public static final Companion Companion = null;
        private final String displayName;
        private final int sepSdkVersion;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/core/oneui/OneUI$Version$Companion;", "", "<init>", "()V", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        static {
            Companion = new Companion((e) null);
        }

        private Version(String str, int i2) {
            this.displayName = str;
            this.sepSdkVersion = i2;
        }

        public final int getSepSdkVersion() {
            return this.sepSdkVersion;
        }
    }

    private OneUI() {
    }

    public static final int getCurrentSepSdkVersion() {
        return currentSepSdkVersion;
    }

    public static final boolean isGreaterOrEqual(Version version) {
        j.e(version, "version");
        if (getCurrentSepSdkVersion() >= version.getSepSdkVersion()) {
            return true;
        }
        return false;
    }
}
