package androidx.emoji2.text;

import java.util.concurrent.ThreadFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f987a;

    public /* synthetic */ a(String str) {
        this.f987a = str;
    }

    public final Thread newThread(Runnable runnable) {
        return ConcurrencyHelpers.lambda$createBackgroundPriorityExecutor$0(this.f987a, runnable);
    }
}
