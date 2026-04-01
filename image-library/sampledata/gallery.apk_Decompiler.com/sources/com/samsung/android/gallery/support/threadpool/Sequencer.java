package com.samsung.android.gallery.support.threadpool;

import A.a;
import A4.C0381p;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sequencer<T> extends Handler {
    private final int MAX_SIZE;
    private final HashMap<Integer, Sequencer<T>.DataPair> mIndex = new HashMap<>();
    private boolean mParallel = false;
    private Consumer<T> mProcessor;
    private final Deque<Sequencer<T>.DataPair> mReqQueue = new ArrayDeque();
    private ThreadPool mThreadPool;
    private OperatingType operatingType = OperatingType.OLDEST_FIRST;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DataPair extends Pair<Integer, T> {
        final long time = System.currentTimeMillis();

        public DataPair(Integer num, T t) {
            super(num, t);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DataPair)) {
                return false;
            }
            DataPair dataPair = (DataPair) obj;
            if (!((Integer) this.first).equals(dataPair.first) || !this.second.equals(dataPair.second)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum OperatingType {
        OLDEST_FIRST,
        RECENT_FIRST
    }

    public Sequencer(Looper looper, int i2) {
        super(looper);
        this.MAX_SIZE = i2;
    }

    private void add(Sequencer<T>.DataPair dataPair) {
        synchronized (this.mReqQueue) {
            this.mReqQueue.addLast(dataPair);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$processParallel$0(ThreadPool.JobContext jobContext) {
        Sequencer<T>.DataPair poll = poll();
        if (poll != null) {
            process(poll);
        }
        return Boolean.TRUE;
    }

    private Sequencer<T>.DataPair poll() {
        synchronized (this.mReqQueue) {
            try {
                if (this.operatingType == OperatingType.OLDEST_FIRST) {
                    Sequencer<T>.DataPair pollFirst = this.mReqQueue.pollFirst();
                    return pollFirst;
                }
                Sequencer<T>.DataPair pollLast = this.mReqQueue.pollLast();
                return pollLast;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void process(Sequencer<T>.DataPair dataPair) {
        a.A(new Object[]{dataPair.first, Long.valueOf(dataPair.time)}, new StringBuilder("process start : "), "Sequencer");
        this.mProcessor.accept(dataPair.second);
        synchronized (this.mReqQueue) {
            int size = this.mReqQueue.size();
            this.mIndex.remove(dataPair.first);
            Log.d("Sequencer", "process end : " + Logger.vt(dataPair.first, Integer.valueOf(size), Long.valueOf(dataPair.time)));
        }
    }

    private void processParallel() {
        if (this.mThreadPool.hasIdle()) {
            this.mThreadPool.submit(new C0381p(6, this));
        } else {
            sendEmptyMessageDelayed(0, 30);
        }
    }

    private void processSingle() {
        Sequencer<T>.DataPair poll = poll();
        if (poll != null) {
            process(poll);
        }
    }

    public void cancel(int i2) {
        synchronized (this.mReqQueue) {
            try {
                if (this.mIndex.containsKey(Integer.valueOf(i2))) {
                    if (this.mReqQueue.remove(this.mIndex.remove(Integer.valueOf(i2)))) {
                        Log.d("Sequencer", "cancel : " + i2, Integer.valueOf(this.mReqQueue.size()));
                    } else {
                        Log.d("Sequencer", "cancel fail : " + i2, Integer.valueOf(this.mReqQueue.size()));
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void enableParallel(int i2) {
        this.mParallel = true;
        this.mThreadPool = ThreadPool.createPrivateInstance("seq", i2);
    }

    public void exit() {
        synchronized (this.mReqQueue) {
            Log.d("Sequencer", "exit : " + this.mReqQueue.size());
            this.mReqQueue.clear();
            this.mIndex.clear();
        }
        getLooper().quitSafely();
    }

    public void handleMessage(Message message) {
        while (true) {
            if (this.mParallel) {
                processParallel();
            } else {
                processSingle();
            }
            synchronized (this.mReqQueue) {
                try {
                    if (this.mReqQueue.isEmpty()) {
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void request(int i2, T t) {
        DataPair pollFirst;
        if (this.mProcessor != null) {
            synchronized (this.mReqQueue) {
                try {
                    if (!this.mIndex.containsKey(Integer.valueOf(i2))) {
                        DataPair dataPair = new DataPair(Integer.valueOf(i2), t);
                        this.mIndex.put(Integer.valueOf(i2), dataPair);
                        add(dataPair);
                        int size = this.mReqQueue.size();
                        Log.d("Sequencer", "request : " + i2, Integer.valueOf(size));
                        if (size > this.MAX_SIZE && (pollFirst = this.mReqQueue.pollFirst()) != null) {
                            this.mIndex.remove(pollFirst.first);
                            Log.d("Sequencer", "drop : " + pollFirst.first);
                        }
                        sendEmptyMessage(0);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        throw new AssertionError("setProcessor before request");
    }

    public void setProcessor(Consumer<T> consumer) {
        this.mProcessor = consumer;
    }
}
