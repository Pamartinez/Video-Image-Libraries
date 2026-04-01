package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u0000 <2\u00020\u0001:\u0002<=B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0015\u0010\rJ\u0017\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0017\u0010\bJ\u0017\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0018\u0010\bJ\u000f\u0010\u0019\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0019\u0010\u0013J\u0017\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001aH\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b$\u0010#R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%R\"\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020'0&8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010\u0014\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010*R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020+8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010,R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b0\u0010%R\u0016\u00101\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b1\u0010%R&\u00104\u001a\u0012\u0012\u0004\u0012\u00020\t02j\b\u0012\u0004\u0012\u00020\t`38\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R$\u0010;\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t8V@VX\u000e¢\u0006\f\u001a\u0004\b8\u00109\"\u0004\b:\u0010\r¨\u0006>"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry;", "Landroidx/lifecycle/Lifecycle;", "Landroidx/lifecycle/LifecycleOwner;", "provider", "", "enforceMainThread", "<init>", "(Landroidx/lifecycle/LifecycleOwner;Z)V", "(Landroidx/lifecycle/LifecycleOwner;)V", "Landroidx/lifecycle/Lifecycle$State;", "next", "Lme/x;", "moveToState", "(Landroidx/lifecycle/Lifecycle$State;)V", "Landroidx/lifecycle/LifecycleObserver;", "observer", "calculateTargetState", "(Landroidx/lifecycle/LifecycleObserver;)Landroidx/lifecycle/Lifecycle$State;", "popParentState", "()V", "state", "pushParentState", "lifecycleOwner", "forwardPass", "backwardPass", "sync", "", "methodName", "enforceMainThreadIfNeeded", "(Ljava/lang/String;)V", "Landroidx/lifecycle/Lifecycle$Event;", "event", "handleLifecycleEvent", "(Landroidx/lifecycle/Lifecycle$Event;)V", "addObserver", "(Landroidx/lifecycle/LifecycleObserver;)V", "removeObserver", "Z", "Landroidx/arch/core/internal/FastSafeIterableMap;", "Landroidx/lifecycle/LifecycleRegistry$ObserverWithState;", "observerMap", "Landroidx/arch/core/internal/FastSafeIterableMap;", "Landroidx/lifecycle/Lifecycle$State;", "Ljava/lang/ref/WeakReference;", "Ljava/lang/ref/WeakReference;", "", "addingObserverCounter", "I", "handlingEvent", "newEventOccurred", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "parentStates", "Ljava/util/ArrayList;", "isSynced", "()Z", "getCurrentState", "()Landroidx/lifecycle/Lifecycle$State;", "setCurrentState", "currentState", "Companion", "ObserverWithState", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LifecycleRegistry extends Lifecycle {
    public static final Companion Companion = new Companion((e) null);
    private int addingObserverCounter;
    private final boolean enforceMainThread;
    private boolean handlingEvent;
    private final WeakReference<LifecycleOwner> lifecycleOwner;
    private boolean newEventOccurred;
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> observerMap;
    private ArrayList<Lifecycle.State> parentStates;
    private Lifecycle.State state;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0001¢\u0006\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry$Companion;", "", "<init>", "()V", "Landroidx/lifecycle/Lifecycle$State;", "state1", "state2", "min$lifecycle_runtime_release", "(Landroidx/lifecycle/Lifecycle$State;Landroidx/lifecycle/Lifecycle$State;)Landroidx/lifecycle/Lifecycle$State;", "min", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final Lifecycle.State min$lifecycle_runtime_release(Lifecycle.State state, Lifecycle.State state2) {
            j.e(state, "state1");
            if (state2 == null || state2.compareTo(state) >= 0) {
                return state;
            }
            return state2;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry$ObserverWithState;", "", "Landroidx/lifecycle/LifecycleObserver;", "observer", "Landroidx/lifecycle/Lifecycle$State;", "initialState", "<init>", "(Landroidx/lifecycle/LifecycleObserver;Landroidx/lifecycle/Lifecycle$State;)V", "Landroidx/lifecycle/LifecycleOwner;", "owner", "Landroidx/lifecycle/Lifecycle$Event;", "event", "Lme/x;", "dispatchEvent", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$Event;)V", "state", "Landroidx/lifecycle/Lifecycle$State;", "getState", "()Landroidx/lifecycle/Lifecycle$State;", "setState", "(Landroidx/lifecycle/Lifecycle$State;)V", "Landroidx/lifecycle/LifecycleEventObserver;", "lifecycleObserver", "Landroidx/lifecycle/LifecycleEventObserver;", "getLifecycleObserver", "()Landroidx/lifecycle/LifecycleEventObserver;", "setLifecycleObserver", "(Landroidx/lifecycle/LifecycleEventObserver;)V", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ObserverWithState {
        private LifecycleEventObserver lifecycleObserver;
        private Lifecycle.State state;

        public ObserverWithState(LifecycleObserver lifecycleObserver2, Lifecycle.State state2) {
            j.e(state2, "initialState");
            j.b(lifecycleObserver2);
            this.lifecycleObserver = Lifecycling.lifecycleEventObserver(lifecycleObserver2);
            this.state = state2;
        }

        public final void dispatchEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            j.e(event, "event");
            Lifecycle.State targetState = event.getTargetState();
            this.state = LifecycleRegistry.Companion.min$lifecycle_runtime_release(this.state, targetState);
            LifecycleEventObserver lifecycleEventObserver = this.lifecycleObserver;
            j.b(lifecycleOwner);
            lifecycleEventObserver.onStateChanged(lifecycleOwner, event);
            this.state = targetState;
        }

        public final Lifecycle.State getState() {
            return this.state;
        }
    }

    private LifecycleRegistry(LifecycleOwner lifecycleOwner2, boolean z) {
        this.enforceMainThread = z;
        this.observerMap = new FastSafeIterableMap<>();
        this.state = Lifecycle.State.INITIALIZED;
        this.parentStates = new ArrayList<>();
        this.lifecycleOwner = new WeakReference<>(lifecycleOwner2);
    }

    private final void backwardPass(LifecycleOwner lifecycleOwner2) {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> descendingIterator = this.observerMap.descendingIterator();
        j.d(descendingIterator, "observerMap.descendingIterator()");
        while (descendingIterator.hasNext() && !this.newEventOccurred) {
            Map.Entry next = descendingIterator.next();
            j.d(next, "next()");
            LifecycleObserver lifecycleObserver = (LifecycleObserver) next.getKey();
            ObserverWithState observerWithState = (ObserverWithState) next.getValue();
            while (observerWithState.getState().compareTo(this.state) > 0 && !this.newEventOccurred && this.observerMap.contains(lifecycleObserver)) {
                Lifecycle.Event downFrom = Lifecycle.Event.Companion.downFrom(observerWithState.getState());
                if (downFrom != null) {
                    pushParentState(downFrom.getTargetState());
                    observerWithState.dispatchEvent(lifecycleOwner2, downFrom);
                    popParentState();
                } else {
                    throw new IllegalStateException("no event down from " + observerWithState.getState());
                }
            }
        }
    }

    private final Lifecycle.State calculateTargetState(LifecycleObserver lifecycleObserver) {
        Lifecycle.State state2;
        ObserverWithState value;
        Map.Entry<LifecycleObserver, ObserverWithState> ceil = this.observerMap.ceil(lifecycleObserver);
        Lifecycle.State state3 = null;
        if (ceil == null || (value = ceil.getValue()) == null) {
            state2 = null;
        } else {
            state2 = value.getState();
        }
        if (!this.parentStates.isEmpty()) {
            state3 = (Lifecycle.State) C0212a.i(this.parentStates, 1);
        }
        Companion companion = Companion;
        return companion.min$lifecycle_runtime_release(companion.min$lifecycle_runtime_release(this.state, state2), state3);
    }

    private final void enforceMainThreadIfNeeded(String str) {
        if (this.enforceMainThread && !ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException(C0212a.m("Method ", str, " must be called on the main thread").toString());
        }
    }

    private final void forwardPass(LifecycleOwner lifecycleOwner2) {
        SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions = this.observerMap.iteratorWithAdditions();
        j.d(iteratorWithAdditions, "observerMap.iteratorWithAdditions()");
        while (iteratorWithAdditions.hasNext() && !this.newEventOccurred) {
            Map.Entry entry = (Map.Entry) iteratorWithAdditions.next();
            LifecycleObserver lifecycleObserver = (LifecycleObserver) entry.getKey();
            ObserverWithState observerWithState = (ObserverWithState) entry.getValue();
            while (observerWithState.getState().compareTo(this.state) < 0 && !this.newEventOccurred && this.observerMap.contains(lifecycleObserver)) {
                pushParentState(observerWithState.getState());
                Lifecycle.Event upFrom = Lifecycle.Event.Companion.upFrom(observerWithState.getState());
                if (upFrom != null) {
                    observerWithState.dispatchEvent(lifecycleOwner2, upFrom);
                    popParentState();
                } else {
                    throw new IllegalStateException("no event up from " + observerWithState.getState());
                }
            }
        }
    }

    private final boolean isSynced() {
        if (this.observerMap.size() == 0) {
            return true;
        }
        Map.Entry<LifecycleObserver, ObserverWithState> eldest = this.observerMap.eldest();
        j.b(eldest);
        Lifecycle.State state2 = eldest.getValue().getState();
        Map.Entry<LifecycleObserver, ObserverWithState> newest = this.observerMap.newest();
        j.b(newest);
        Lifecycle.State state3 = newest.getValue().getState();
        if (state2 == state3 && this.state == state3) {
            return true;
        }
        return false;
    }

    private final void moveToState(Lifecycle.State state2) {
        Lifecycle.State state3 = this.state;
        if (state3 != state2) {
            if (state3 == Lifecycle.State.INITIALIZED && state2 == Lifecycle.State.DESTROYED) {
                throw new IllegalStateException(("no event down from " + this.state + " in component " + this.lifecycleOwner.get()).toString());
            }
            this.state = state2;
            if (this.handlingEvent || this.addingObserverCounter != 0) {
                this.newEventOccurred = true;
                return;
            }
            this.handlingEvent = true;
            sync();
            this.handlingEvent = false;
            if (this.state == Lifecycle.State.DESTROYED) {
                this.observerMap = new FastSafeIterableMap<>();
            }
        }
    }

    private final void popParentState() {
        ArrayList<Lifecycle.State> arrayList = this.parentStates;
        arrayList.remove(arrayList.size() - 1);
    }

    private final void pushParentState(Lifecycle.State state2) {
        this.parentStates.add(state2);
    }

    private final void sync() {
        LifecycleOwner lifecycleOwner2 = this.lifecycleOwner.get();
        if (lifecycleOwner2 != null) {
            while (!isSynced()) {
                this.newEventOccurred = false;
                Lifecycle.State state2 = this.state;
                Map.Entry<LifecycleObserver, ObserverWithState> eldest = this.observerMap.eldest();
                j.b(eldest);
                if (state2.compareTo(eldest.getValue().getState()) < 0) {
                    backwardPass(lifecycleOwner2);
                }
                Map.Entry<LifecycleObserver, ObserverWithState> newest = this.observerMap.newest();
                if (!this.newEventOccurred && newest != null && this.state.compareTo(newest.getValue().getState()) > 0) {
                    forwardPass(lifecycleOwner2);
                }
            }
            this.newEventOccurred = false;
            return;
        }
        throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
    }

    public void addObserver(LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner2;
        boolean z;
        j.e(lifecycleObserver, "observer");
        enforceMainThreadIfNeeded("addObserver");
        Lifecycle.State state2 = this.state;
        Lifecycle.State state3 = Lifecycle.State.DESTROYED;
        if (state2 != state3) {
            state3 = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, state3);
        if (this.observerMap.putIfAbsent(lifecycleObserver, observerWithState) == null && (lifecycleOwner2 = this.lifecycleOwner.get()) != null) {
            if (this.addingObserverCounter != 0 || this.handlingEvent) {
                z = true;
            } else {
                z = false;
            }
            Lifecycle.State calculateTargetState = calculateTargetState(lifecycleObserver);
            this.addingObserverCounter++;
            while (observerWithState.getState().compareTo(calculateTargetState) < 0 && this.observerMap.contains(lifecycleObserver)) {
                pushParentState(observerWithState.getState());
                Lifecycle.Event upFrom = Lifecycle.Event.Companion.upFrom(observerWithState.getState());
                if (upFrom != null) {
                    observerWithState.dispatchEvent(lifecycleOwner2, upFrom);
                    popParentState();
                    calculateTargetState = calculateTargetState(lifecycleObserver);
                } else {
                    throw new IllegalStateException("no event up from " + observerWithState.getState());
                }
            }
            if (!z) {
                sync();
            }
            this.addingObserverCounter--;
        }
    }

    public Lifecycle.State getCurrentState() {
        return this.state;
    }

    public void handleLifecycleEvent(Lifecycle.Event event) {
        j.e(event, "event");
        enforceMainThreadIfNeeded("handleLifecycleEvent");
        moveToState(event.getTargetState());
    }

    public void removeObserver(LifecycleObserver lifecycleObserver) {
        j.e(lifecycleObserver, "observer");
        enforceMainThreadIfNeeded("removeObserver");
        this.observerMap.remove(lifecycleObserver);
    }

    public void setCurrentState(Lifecycle.State state2) {
        j.e(state2, "state");
        enforceMainThreadIfNeeded("setCurrentState");
        moveToState(state2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LifecycleRegistry(LifecycleOwner lifecycleOwner2) {
        this(lifecycleOwner2, true);
        j.e(lifecycleOwner2, "provider");
    }
}
