package androidx.activity;

import Ae.a;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0006H'¢\u0006\u0004\b\u000e\u0010\bJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u000f\u0010\bJ\u0017\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0014\u0010\u0013R*\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00028G@GX\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u0005R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u001a8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR*\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001d8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Landroidx/activity/OnBackPressedCallback;", "", "", "enabled", "<init>", "(Z)V", "Lme/x;", "remove", "()V", "Landroidx/activity/BackEventCompat;", "backEvent", "handleOnBackStarted", "(Landroidx/activity/BackEventCompat;)V", "handleOnBackProgressed", "handleOnBackPressed", "handleOnBackCancelled", "Landroidx/activity/Cancellable;", "cancellable", "addCancellable", "(Landroidx/activity/Cancellable;)V", "removeCancellable", "value", "isEnabled", "Z", "()Z", "setEnabled", "Ljava/util/concurrent/CopyOnWriteArrayList;", "cancellables", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function0;", "enabledChangedCallback", "LAe/a;", "getEnabledChangedCallback$activity_release", "()LAe/a;", "setEnabledChangedCallback$activity_release", "(LAe/a;)V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OnBackPressedCallback {
    private final CopyOnWriteArrayList<Cancellable> cancellables = new CopyOnWriteArrayList<>();
    private a enabledChangedCallback;
    private boolean isEnabled;

    public OnBackPressedCallback(boolean z) {
        this.isEnabled = z;
    }

    public final void addCancellable(Cancellable cancellable) {
        j.e(cancellable, "cancellable");
        this.cancellables.add(cancellable);
    }

    public final a getEnabledChangedCallback$activity_release() {
        return this.enabledChangedCallback;
    }

    public abstract void handleOnBackPressed();

    public void handleOnBackProgressed(BackEventCompat backEventCompat) {
        j.e(backEventCompat, "backEvent");
    }

    public void handleOnBackStarted(BackEventCompat backEventCompat) {
        j.e(backEventCompat, "backEvent");
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final void remove() {
        for (Cancellable cancel : this.cancellables) {
            cancel.cancel();
        }
    }

    public final void removeCancellable(Cancellable cancellable) {
        j.e(cancellable, "cancellable");
        this.cancellables.remove(cancellable);
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
        a aVar = this.enabledChangedCallback;
        if (aVar != null) {
            aVar.invoke();
        }
    }

    public final void setEnabledChangedCallback$activity_release(a aVar) {
        this.enabledChangedCallback = aVar;
    }

    public void handleOnBackCancelled() {
    }
}
