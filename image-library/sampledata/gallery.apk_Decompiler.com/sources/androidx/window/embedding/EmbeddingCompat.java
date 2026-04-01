package androidx.window.embedding;

import android.util.Log;
import androidx.window.embedding.EmbeddingInterfaceCompat;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0014¨\u0006\u0016"}, d2 = {"Landroidx/window/embedding/EmbeddingCompat;", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "embeddingExtension", "Landroidx/window/embedding/EmbeddingAdapter;", "adapter", "<init>", "(Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;Landroidx/window/embedding/EmbeddingAdapter;)V", "()V", "", "Landroidx/window/embedding/EmbeddingRule;", "rules", "Lme/x;", "setSplitRules", "(Ljava/util/Set;)V", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "embeddingCallback", "setEmbeddingCallback", "(Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;)V", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "Landroidx/window/embedding/EmbeddingAdapter;", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EmbeddingCompat implements EmbeddingInterfaceCompat {
    public static final Companion Companion = new Companion((e) null);
    public static final boolean DEBUG = true;
    private static final String TAG = "EmbeddingCompat";
    private final EmbeddingAdapter adapter;
    private final ActivityEmbeddingComponent embeddingExtension;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\r\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/embedding/EmbeddingCompat$Companion;", "", "()V", "DEBUG", "", "TAG", "", "embeddingComponent", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "getExtensionApiLevel", "", "()Ljava/lang/Integer;", "isEmbeddingAvailable", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final ActivityEmbeddingComponent embeddingComponent() {
            if (!isEmbeddingAvailable()) {
                return new EmptyEmbeddingComponent();
            }
            ActivityEmbeddingComponent activityEmbeddingComponent = WindowExtensionsProvider.getWindowExtensions().getActivityEmbeddingComponent();
            if (activityEmbeddingComponent == null) {
                return new EmptyEmbeddingComponent();
            }
            return activityEmbeddingComponent;
        }

        public final Integer getExtensionApiLevel() {
            try {
                return Integer.valueOf(WindowExtensionsProvider.getWindowExtensions().getVendorApiLevel());
            } catch (NoClassDefFoundError unused) {
                Log.d(EmbeddingCompat.TAG, "Embedding extension version not found");
                return null;
            } catch (UnsupportedOperationException unused2) {
                Log.d(EmbeddingCompat.TAG, "Stub Extension");
                return null;
            }
        }

        public final boolean isEmbeddingAvailable() {
            try {
                if (WindowExtensionsProvider.getWindowExtensions().getActivityEmbeddingComponent() != null) {
                    return true;
                }
                return false;
            } catch (NoClassDefFoundError unused) {
                Log.d(EmbeddingCompat.TAG, "Embedding extension version not found");
                return false;
            } catch (UnsupportedOperationException unused2) {
                Log.d(EmbeddingCompat.TAG, "Stub Extension");
                return false;
            }
        }

        private Companion() {
        }
    }

    public EmbeddingCompat(ActivityEmbeddingComponent activityEmbeddingComponent, EmbeddingAdapter embeddingAdapter) {
        j.e(activityEmbeddingComponent, "embeddingExtension");
        j.e(embeddingAdapter, "adapter");
        this.embeddingExtension = activityEmbeddingComponent;
        this.adapter = embeddingAdapter;
    }

    public void setEmbeddingCallback(EmbeddingInterfaceCompat.EmbeddingCallbackInterface embeddingCallbackInterface) {
        j.e(embeddingCallbackInterface, "embeddingCallback");
        this.embeddingExtension.setSplitInfoCallback(new EmbeddingTranslatingCallback(embeddingCallbackInterface, this.adapter));
    }

    public void setSplitRules(Set<? extends EmbeddingRule> set) {
        j.e(set, "rules");
        this.embeddingExtension.setEmbeddingRules(this.adapter.translate(set));
    }

    public EmbeddingCompat() {
        this(Companion.embeddingComponent(), new EmbeddingAdapter());
    }
}
