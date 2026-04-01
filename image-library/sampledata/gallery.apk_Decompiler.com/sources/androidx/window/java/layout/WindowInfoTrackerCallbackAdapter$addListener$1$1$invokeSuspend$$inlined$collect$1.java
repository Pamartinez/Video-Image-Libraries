package androidx.window.java.layout;

import Yf.h;
import androidx.core.util.Consumer;
import kotlin.Metadata;
import me.x;
import qe.C1227c;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"androidx/window/java/layout/WindowInfoTrackerCallbackAdapter$addListener$1$1$invokeSuspend$$inlined$collect$1", "LYf/h;", "value", "Lme/x;", "emit", "(Ljava/lang/Object;Lqe/c;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WindowInfoTrackerCallbackAdapter$addListener$1$1$invokeSuspend$$inlined$collect$1 implements h {
    final /* synthetic */ Consumer $consumer$inlined;

    public WindowInfoTrackerCallbackAdapter$addListener$1$1$invokeSuspend$$inlined$collect$1(Consumer consumer) {
        this.$consumer$inlined = consumer;
    }

    public Object emit(T t, C1227c cVar) {
        this.$consumer$inlined.accept(t);
        return x.f4917a;
    }
}
