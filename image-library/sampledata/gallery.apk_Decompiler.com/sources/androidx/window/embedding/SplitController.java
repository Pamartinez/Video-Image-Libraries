package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.v;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u0007¢\u0006\u0004\b\u0010\u0010\u0003J1\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001b\u001a\u00020\u00072\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006&"}, d2 = {"Landroidx/window/embedding/SplitController;", "", "<init>", "()V", "", "Landroidx/window/embedding/EmbeddingRule;", "staticRules", "Lme/x;", "setStaticSplitRules", "(Ljava/util/Set;)V", "getSplitRules", "()Ljava/util/Set;", "rule", "registerRule", "(Landroidx/window/embedding/EmbeddingRule;)V", "unregisterRule", "clearRegisteredRules", "Landroid/app/Activity;", "activity", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "consumer", "addSplitListener", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "removeSplitListener", "(Landroidx/core/util/Consumer;)V", "", "isSplitSupported", "()Z", "Landroidx/window/embedding/EmbeddingBackend;", "embeddingBackend", "Landroidx/window/embedding/EmbeddingBackend;", "staticSplitRules", "Ljava/util/Set;", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SplitController {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static volatile SplitController globalInstance = null;
    /* access modifiers changed from: private */
    public static final ReentrantLock globalLock = new ReentrantLock();
    public static final boolean sDebug = false;
    private final EmbeddingBackend embeddingBackend;
    private Set<? extends EmbeddingRule> staticSplitRules;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0000XT¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/window/embedding/SplitController$Companion;", "", "<init>", "()V", "Landroidx/window/embedding/SplitController;", "getInstance", "()Landroidx/window/embedding/SplitController;", "Landroid/content/Context;", "context", "", "staticRuleResourceId", "Lme/x;", "initialize", "(Landroid/content/Context;I)V", "globalInstance", "Landroidx/window/embedding/SplitController;", "Ljava/util/concurrent/locks/ReentrantLock;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "", "sDebug", "Z", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final SplitController getInstance() {
            if (SplitController.globalInstance == null) {
                ReentrantLock access$getGlobalLock$cp = SplitController.globalLock;
                access$getGlobalLock$cp.lock();
                try {
                    if (SplitController.globalInstance == null) {
                        SplitController.globalInstance = new SplitController((e) null);
                    }
                } finally {
                    access$getGlobalLock$cp.unlock();
                }
            }
            SplitController access$getGlobalInstance$cp = SplitController.globalInstance;
            j.b(access$getGlobalInstance$cp);
            return access$getGlobalInstance$cp;
        }

        public final void initialize(Context context, int i2) {
            j.e(context, "context");
            Set parseSplitRules$window_release = new SplitRuleParser().parseSplitRules$window_release(context, i2);
            SplitController instance = getInstance();
            if (parseSplitRules$window_release == null) {
                parseSplitRules$window_release = v.d;
            }
            instance.setStaticSplitRules(parseSplitRules$window_release);
        }

        private Companion() {
        }
    }

    public /* synthetic */ SplitController(e eVar) {
        this();
    }

    public static final SplitController getInstance() {
        return Companion.getInstance();
    }

    public static final void initialize(Context context, int i2) {
        Companion.initialize(context, i2);
    }

    /* access modifiers changed from: private */
    public final void setStaticSplitRules(Set<? extends EmbeddingRule> set) {
        this.staticSplitRules = set;
        this.embeddingBackend.setSplitRules(set);
    }

    public final void addSplitListener(Activity activity, Executor executor, Consumer<List<SplitInfo>> consumer) {
        j.e(activity, "activity");
        j.e(executor, "executor");
        j.e(consumer, "consumer");
        this.embeddingBackend.registerSplitListenerForActivity(activity, executor, consumer);
    }

    public final void clearRegisteredRules() {
        this.embeddingBackend.setSplitRules(this.staticSplitRules);
    }

    public final Set<EmbeddingRule> getSplitRules() {
        return C1194l.p1(this.embeddingBackend.getSplitRules());
    }

    public final boolean isSplitSupported() {
        return this.embeddingBackend.isSplitSupported();
    }

    public final void registerRule(EmbeddingRule embeddingRule) {
        j.e(embeddingRule, "rule");
        this.embeddingBackend.registerRule(embeddingRule);
    }

    public final void removeSplitListener(Consumer<List<SplitInfo>> consumer) {
        j.e(consumer, "consumer");
        this.embeddingBackend.unregisterSplitListenerForActivity(consumer);
    }

    public final void unregisterRule(EmbeddingRule embeddingRule) {
        j.e(embeddingRule, "rule");
        this.embeddingBackend.unregisterRule(embeddingRule);
    }

    private SplitController() {
        this.embeddingBackend = ExtensionEmbeddingBackend.Companion.getInstance();
        this.staticSplitRules = v.d;
    }
}
