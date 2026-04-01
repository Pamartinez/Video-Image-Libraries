package androidx.transition;

import androidx.transition.Transition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Transition.TransitionNotification {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1035a;

    public /* synthetic */ b(int i2) {
        this.f1035a = i2;
    }

    public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
        switch (this.f1035a) {
            case 0:
                transitionListener.onTransitionStart(transition, z);
                return;
            case 1:
                transitionListener.onTransitionEnd(transition, z);
                return;
            case 2:
                transitionListener.onTransitionCancel(transition);
                return;
            case 3:
                transitionListener.onTransitionPause(transition);
                return;
            default:
                transitionListener.onTransitionResume(transition);
                return;
        }
    }
}
