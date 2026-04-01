package androidx.window.embedding;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\ba\u0018\u00002\u00020\u0001:\u0001\fJ\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/window/embedding/EmbeddingInterfaceCompat;", "", "", "Landroidx/window/embedding/EmbeddingRule;", "rules", "Lme/x;", "setSplitRules", "(Ljava/util/Set;)V", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "embeddingCallback", "setEmbeddingCallback", "(Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;)V", "EmbeddingCallbackInterface", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface EmbeddingInterfaceCompat {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "", "", "Landroidx/window/embedding/SplitInfo;", "splitInfo", "Lme/x;", "onSplitInfoChanged", "(Ljava/util/List;)V", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EmbeddingCallbackInterface {
        void onSplitInfoChanged(List<SplitInfo> list);
    }

    void setEmbeddingCallback(EmbeddingCallbackInterface embeddingCallbackInterface);

    void setSplitRules(Set<? extends EmbeddingRule> set);
}
