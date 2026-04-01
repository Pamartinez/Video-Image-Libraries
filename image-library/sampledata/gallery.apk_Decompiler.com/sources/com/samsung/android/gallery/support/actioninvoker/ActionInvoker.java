package com.samsung.android.gallery.support.actioninvoker;

import Gb.a;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionInvoker {
    private final CopyOnWriteArrayList<ActionInvoker> mChildList = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<Enum, ConcurrentLinkedDeque<ExclusiveListener>> mExclusiveListenerListMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Enum, CopyOnWriteArrayList<ActionInvokeListener>> mListenerListMap = new ConcurrentHashMap<>();
    private ActionInvoker mParent;
    private final String mTag;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExclusiveListener {
        private static long sequence;
        final ActionInvokeListener mListener;
        final long mSequence;
        final CharSequence mTag;

        public ExclusiveListener(ActionInvokeListener actionInvokeListener, CharSequence charSequence) {
            long j2 = sequence;
            sequence = 1 + j2;
            this.mSequence = j2;
            this.mListener = actionInvokeListener;
            this.mTag = charSequence;
        }

        public String toString() {
            return "ExclusiveListener{" + this.mTag + '}';
        }
    }

    public ActionInvoker(String str) {
        this.mTag = str;
    }

    private <E extends Enum<E>> boolean invokeExclusive(E e, Object[] objArr) {
        ExclusiveListener peekExclusive = peekExclusive(e, (ExclusiveListener) null);
        if (peekExclusive == null) {
            return false;
        }
        Log.d("ActionInvoker", "invokeExclusive", e, peekExclusive, this.mTag);
        peekExclusive.mListener.onHandle(objArr);
        return true;
    }

    private <E extends Enum<E>> void invokeInternal(E e, Object[] objArr) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.mListenerListMap.get(e);
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((ActionInvokeListener) it.next()).onHandle(objArr);
            }
        }
        Iterator<ActionInvoker> it2 = this.mChildList.iterator();
        while (it2.hasNext()) {
            it2.next().invokeInternal(e, objArr);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ CopyOnWriteArrayList lambda$add$0(Enum enumR) {
        return new CopyOnWriteArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ConcurrentLinkedDeque lambda$addExclusive$1(Enum enumR) {
        return new ConcurrentLinkedDeque();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasExclusive$2(ActionInvokeListener actionInvokeListener, ExclusiveListener exclusiveListener) {
        if (exclusiveListener.mListener == actionInvokeListener) {
            return true;
        }
        return false;
    }

    private <E extends Enum<E>> ExclusiveListener peekExclusive(E e, ExclusiveListener exclusiveListener) {
        ConcurrentLinkedDeque concurrentLinkedDeque = this.mExclusiveListenerListMap.get(e);
        if (concurrentLinkedDeque != null) {
            ExclusiveListener exclusiveListener2 = (ExclusiveListener) concurrentLinkedDeque.peekFirst();
            if (exclusiveListener == null || (exclusiveListener2 != null && exclusiveListener2.mSequence > exclusiveListener.mSequence)) {
                exclusiveListener = exclusiveListener2;
            }
        }
        Iterator<ActionInvoker> it = this.mChildList.iterator();
        while (it.hasNext()) {
            ExclusiveListener peekExclusive = it.next().peekExclusive(e, exclusiveListener);
            if (exclusiveListener == null || (peekExclusive != null && peekExclusive.mSequence > exclusiveListener.mSequence)) {
                exclusiveListener = peekExclusive;
            }
        }
        return exclusiveListener;
    }

    public <E extends Enum<E>> void add(E e, ActionInvokeListener actionInvokeListener) {
        this.mListenerListMap.computeIfAbsent(e, new a(18)).add(actionInvokeListener);
    }

    public <E extends Enum<E>> void addExclusive(E e, ActionInvokeListener actionInvokeListener, CharSequence charSequence) {
        ConcurrentLinkedDeque computeIfAbsent = this.mExclusiveListenerListMap.computeIfAbsent(e, new a(19));
        Iterator it = computeIfAbsent.iterator();
        while (it.hasNext()) {
            if (((ExclusiveListener) it.next()).mListener == actionInvokeListener) {
                throw new IllegalStateException("listenerList is already exist");
            }
        }
        computeIfAbsent.addFirst(new ExclusiveListener(actionInvokeListener, charSequence));
    }

    public void attach(ActionInvoker actionInvoker) {
        actionInvoker.mParent = this;
        this.mChildList.addIfAbsent(actionInvoker);
    }

    public void detach(ActionInvoker actionInvoker) {
        if (this.mChildList.remove(actionInvoker)) {
            actionInvoker.mParent = null;
        }
    }

    public void detachFromParent() {
        ActionInvoker actionInvoker = this.mParent;
        if (actionInvoker != null) {
            actionInvoker.detach(this);
        }
    }

    public <E extends Enum<E>> boolean hasExclusive(E e) {
        ActionInvoker actionInvoker = this.mParent;
        if (actionInvoker != null) {
            return actionInvoker.hasExclusive(e);
        }
        return peekExclusive(e, (ExclusiveListener) null) != null;
    }

    public <E extends Enum<E>> void invoke(E e, Object... objArr) {
        ActionInvoker actionInvoker = this.mParent;
        if (actionInvoker != null) {
            actionInvoker.invoke(e, objArr);
        } else if (!invokeExclusive(e, objArr)) {
            invokeInternal(e, objArr);
        }
    }

    public <E extends Enum<E>> boolean remove(E e, ActionInvokeListener actionInvokeListener) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.mListenerListMap.get(e);
        if (copyOnWriteArrayList == null || !copyOnWriteArrayList.remove(actionInvokeListener)) {
            return false;
        }
        return true;
    }

    public <E extends Enum<E>> void removeExclusive(E e, ActionInvokeListener actionInvokeListener) {
        ConcurrentLinkedDeque concurrentLinkedDeque = this.mExclusiveListenerListMap.get(e);
        if (concurrentLinkedDeque != null) {
            Iterator it = concurrentLinkedDeque.iterator();
            while (it.hasNext()) {
                if (((ExclusiveListener) it.next()).mListener == actionInvokeListener) {
                    concurrentLinkedDeque.remove();
                    return;
                }
            }
            throw new IllegalStateException("listenerList is not exist");
        }
        throw new IllegalStateException("listenerList is not exist");
    }

    public String toString() {
        return this.mTag + " / " + super.toString();
    }

    public <E extends Enum<E>> boolean hasExclusive(E e, ActionInvokeListener actionInvokeListener) {
        ConcurrentLinkedDeque concurrentLinkedDeque = this.mExclusiveListenerListMap.get(e);
        return concurrentLinkedDeque != null && concurrentLinkedDeque.stream().anyMatch(new a(actionInvokeListener));
    }
}
