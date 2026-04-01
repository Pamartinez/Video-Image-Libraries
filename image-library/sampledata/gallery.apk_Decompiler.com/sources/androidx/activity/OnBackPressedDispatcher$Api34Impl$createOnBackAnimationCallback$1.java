package androidx.activity;

import Ae.a;
import Ae.b;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"androidx/activity/OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1", "Landroid/window/OnBackAnimationCallback;", "Landroid/window/BackEvent;", "backEvent", "Lme/x;", "onBackStarted", "(Landroid/window/BackEvent;)V", "onBackProgressed", "onBackInvoked", "()V", "onBackCancelled", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1 implements OnBackAnimationCallback {
    final /* synthetic */ a $onBackCancelled;
    final /* synthetic */ a $onBackInvoked;
    final /* synthetic */ b $onBackProgressed;
    final /* synthetic */ b $onBackStarted;

    public OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1(b bVar, b bVar2, a aVar, a aVar2) {
        this.$onBackStarted = bVar;
        this.$onBackProgressed = bVar2;
        this.$onBackInvoked = aVar;
        this.$onBackCancelled = aVar2;
    }

    public void onBackCancelled() {
        this.$onBackCancelled.invoke();
    }

    public void onBackInvoked() {
        this.$onBackInvoked.invoke();
    }

    public void onBackProgressed(BackEvent backEvent) {
        j.e(backEvent, "backEvent");
        this.$onBackProgressed.invoke(new BackEventCompat(backEvent));
    }

    public void onBackStarted(BackEvent backEvent) {
        j.e(backEvent, "backEvent");
        this.$onBackStarted.invoke(new BackEventCompat(backEvent));
    }
}
