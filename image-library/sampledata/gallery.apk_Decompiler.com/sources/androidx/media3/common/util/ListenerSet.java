package androidx.media3.common.util;

import Ab.b;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.FlagSet;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ListenerSet<T> {
    private final Clock clock;
    private final ArrayDeque<Runnable> flushingEvents;
    private final HandlerWrapper handler;
    private final IterationFinishedEvent<T> iterationFinishedEvent;
    private final CopyOnWriteArraySet<ListenerHolder<T>> listeners;
    private final ArrayDeque<Runnable> queuedEvents;
    private boolean released;
    private final Object releasedLock;
    private boolean throwsWhenUsingWrongThread;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Event<T> {
        void invoke(T t);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface IterationFinishedEvent<T> {
        void invoke(T t, FlagSet flagSet);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ListenerHolder<T> {
        private FlagSet.Builder flagsBuilder = new FlagSet.Builder();
        public final T listener;
        private boolean needsIterationFinishedEvent;
        private boolean released;

        public ListenerHolder(T t) {
            this.listener = t;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ListenerHolder.class != obj.getClass()) {
                return false;
            }
            return this.listener.equals(((ListenerHolder) obj).listener);
        }

        public int hashCode() {
            return this.listener.hashCode();
        }

        public void invoke(int i2, Event<T> event) {
            if (!this.released) {
                if (i2 != -1) {
                    this.flagsBuilder.add(i2);
                }
                this.needsIterationFinishedEvent = true;
                event.invoke(this.listener);
            }
        }

        public void iterationFinished(IterationFinishedEvent<T> iterationFinishedEvent) {
            if (!this.released && this.needsIterationFinishedEvent) {
                FlagSet build = this.flagsBuilder.build();
                this.flagsBuilder = new FlagSet.Builder();
                this.needsIterationFinishedEvent = false;
                iterationFinishedEvent.invoke(this.listener, build);
            }
        }

        public void release(IterationFinishedEvent<T> iterationFinishedEvent) {
            this.released = true;
            if (this.needsIterationFinishedEvent) {
                this.needsIterationFinishedEvent = false;
                iterationFinishedEvent.invoke(this.listener, this.flagsBuilder.build());
            }
        }
    }

    public ListenerSet(Looper looper, Clock clock2, IterationFinishedEvent<T> iterationFinishedEvent2) {
        this(new CopyOnWriteArraySet(), looper, clock2, iterationFinishedEvent2, true);
    }

    /* access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        Iterator<ListenerHolder<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().iterationFinished(this.iterationFinishedEvent);
            if (this.handler.hasMessages(1)) {
                break;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$queueEvent$0(CopyOnWriteArraySet copyOnWriteArraySet, int i2, Event event) {
        Iterator it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            ((ListenerHolder) it.next()).invoke(i2, event);
        }
    }

    private void verifyCurrentThread() {
        boolean z;
        if (this.throwsWhenUsingWrongThread) {
            if (Thread.currentThread() == this.handler.getLooper().getThread()) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
        }
    }

    public void add(T t) {
        Assertions.checkNotNull(t);
        synchronized (this.releasedLock) {
            try {
                if (!this.released) {
                    this.listeners.add(new ListenerHolder(t));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ListenerSet<T> copy(Looper looper, IterationFinishedEvent<T> iterationFinishedEvent2) {
        return copy(looper, this.clock, iterationFinishedEvent2);
    }

    public void flushEvents() {
        verifyCurrentThread();
        if (!this.queuedEvents.isEmpty()) {
            if (!this.handler.hasMessages(1)) {
                HandlerWrapper handlerWrapper = this.handler;
                handlerWrapper.sendMessageAtFrontOfQueue(handlerWrapper.obtainMessage(1));
            }
            boolean isEmpty = this.flushingEvents.isEmpty();
            this.flushingEvents.addAll(this.queuedEvents);
            this.queuedEvents.clear();
            if (isEmpty) {
                while (!this.flushingEvents.isEmpty()) {
                    this.flushingEvents.peekFirst().run();
                    this.flushingEvents.removeFirst();
                }
            }
        }
    }

    public void queueEvent(int i2, Event<T> event) {
        verifyCurrentThread();
        this.queuedEvents.add(new b((Object) new CopyOnWriteArraySet(this.listeners), i2, (Object) event, 10));
    }

    public void release() {
        verifyCurrentThread();
        synchronized (this.releasedLock) {
            this.released = true;
        }
        Iterator<ListenerHolder<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().release(this.iterationFinishedEvent);
        }
        this.listeners.clear();
    }

    public void sendEvent(int i2, Event<T> event) {
        queueEvent(i2, event);
        flushEvents();
    }

    private ListenerSet(CopyOnWriteArraySet<ListenerHolder<T>> copyOnWriteArraySet, Looper looper, Clock clock2, IterationFinishedEvent<T> iterationFinishedEvent2, boolean z) {
        this.clock = clock2;
        this.listeners = copyOnWriteArraySet;
        this.iterationFinishedEvent = iterationFinishedEvent2;
        this.releasedLock = new Object();
        this.flushingEvents = new ArrayDeque<>();
        this.queuedEvents = new ArrayDeque<>();
        this.handler = clock2.createHandler(looper, new F.b(0, this));
        this.throwsWhenUsingWrongThread = z;
    }

    public ListenerSet<T> copy(Looper looper, Clock clock2, IterationFinishedEvent<T> iterationFinishedEvent2) {
        return new ListenerSet<>(this.listeners, looper, clock2, iterationFinishedEvent2, this.throwsWhenUsingWrongThread);
    }
}
