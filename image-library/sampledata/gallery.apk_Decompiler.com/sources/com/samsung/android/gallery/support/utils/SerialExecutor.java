package com.samsung.android.gallery.support.utils;

import android.util.Pair;
import bc.d;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SerialExecutor<T> {
    private final ArrayList<Pair<Integer, Object>> mRunnableList = new ArrayList<>();

    private void execNext(T t) {
        Pair pair;
        if (this.mRunnableList.isEmpty()) {
            pair = null;
        } else {
            pair = this.mRunnableList.remove(0);
        }
        if (pair != null) {
            d dVar = new d((Object) this, (Object) pair, (Object) t, 8);
            if (((Integer) pair.first).intValue() == 1) {
                ThreadUtil.runOnUiThread(dVar);
            } else if (((Integer) pair.first).intValue() == 2) {
                ThreadUtil.runOnBgThread(dVar);
            } else {
                throw new AssertionError("invalid type : " + pair.first);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execNext$0(Pair pair, Object obj) {
        Object obj2 = pair.second;
        if (obj2 instanceof Consumer) {
            ((Consumer) obj2).accept(obj);
            execNext(obj);
        } else if (obj2 instanceof Supplier) {
            execNext(((Supplier) obj2).get());
        } else if (obj2 instanceof Runnable) {
            ((Runnable) obj2).run();
            execNext(obj);
        }
    }

    public void exec() {
        execNext((Object) null);
    }

    public SerialExecutor<T> runOnBg(Supplier<T> supplier) {
        this.mRunnableList.add(new Pair(2, supplier));
        return this;
    }

    public SerialExecutor<T> runOnUi(Consumer<T> consumer) {
        this.mRunnableList.add(new Pair(1, consumer));
        return this;
    }

    public SerialExecutor<T> runOnBg(Runnable runnable) {
        this.mRunnableList.add(new Pair(2, runnable));
        return this;
    }

    public SerialExecutor<T> runOnUi(Runnable runnable) {
        this.mRunnableList.add(new Pair(1, runnable));
        return this;
    }
}
