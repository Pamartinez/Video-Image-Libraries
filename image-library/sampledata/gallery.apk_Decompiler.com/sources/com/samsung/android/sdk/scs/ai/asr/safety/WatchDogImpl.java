package com.samsung.android.sdk.scs.ai.asr.safety;

import c0.C0086a;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class WatchDogImpl implements WatchDog {
    private final String TAG;
    private final long createTime;
    private final boolean interrupt;
    private final Thread thread;
    private final List<TimeOutCheck> watchDogCallbacks = Collections.synchronizedList(new ArrayList());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TimeOutCheck {
        private final String TAG;
        /* access modifiers changed from: private */
        public final WatchDogCallback callback;
        private final long startTime;
        private final long timeoutTime;

        public /* synthetic */ TimeOutCheck(String str, WatchDogCallback watchDogCallback, long j2, int i2) {
            this(str, watchDogCallback, j2);
        }

        /* access modifiers changed from: private */
        public boolean isMatched(WatchDogCallback watchDogCallback) {
            if (this.callback == watchDogCallback) {
                return true;
            }
            return false;
        }

        public boolean isTimeout() {
            if (System.currentTimeMillis() > this.timeoutTime) {
                return true;
            }
            return false;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("TimeCheck{callback=");
            sb2.append(this.callback);
            sb2.append(", timeoutTime=");
            sb2.append(WatchDog.createTimeStr(this.timeoutTime));
            sb2.append(", watchDog=");
            return C0086a.m(sb2, this.TAG, '}');
        }

        private TimeOutCheck(String str, WatchDogCallback watchDogCallback, long j2) {
            this.TAG = str;
            this.callback = watchDogCallback;
            long currentTimeMillis = System.currentTimeMillis();
            this.startTime = currentTimeMillis;
            this.timeoutTime = currentTimeMillis + j2;
        }
    }

    public WatchDogImpl(boolean z, Thread thread2) {
        String str = "WatchDog@" + hashCode();
        this.TAG = str;
        this.interrupt = z;
        this.thread = thread2;
        this.createTime = System.currentTimeMillis();
        Log.i(str, "created " + z);
    }

    public void close() {
        this.watchDogCallbacks.clear();
        WatchDogService.remove(this.thread);
    }

    public void executeWatchDog() {
        for (TimeOutCheck next : this.watchDogCallbacks) {
            if (next != null) {
                try {
                    if (next.isTimeout() && this.watchDogCallbacks.remove(next)) {
                        String str = this.TAG;
                        Log.i(str, "start to handle watchdog " + next + " , " + this.thread);
                        if (this.interrupt) {
                            this.thread.interrupt();
                        }
                        next.callback.onTimeoutDetected();
                    }
                } catch (Exception e) {
                    if (WatchDog.isDevelop) {
                        Log.e(this.TAG, "error to handle watchdog execution", e);
                    }
                } catch (Throwable th) {
                    next.callback.onTimeoutDetected();
                    throw th;
                }
            }
        }
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public boolean isWatching() {
        return !this.watchDogCallbacks.isEmpty();
    }

    public String toString() {
        return this.TAG + "{" + this.watchDogCallbacks.size() + ", createTime=" + WatchDog.createTimeStr(this.createTime) + '}';
    }

    public void unWatchBlocking(WatchDogCallback watchDogCallback) {
        if (watchDogCallback != null) {
            List<TimeOutCheck> list = this.watchDogCallbacks;
            if (!list.removeAll((Collection) list.stream().filter(new c(watchDogCallback)).collect(Collectors.toSet()))) {
                String str = this.TAG;
                Log.w(str, "unWatchBlocking failed. already handled by watchdog service. " + watchDogCallback);
            }
        } else {
            Log.w(this.TAG, "ignore unWatchdog because callback is null");
        }
        if (!this.watchDogCallbacks.isEmpty()) {
            String str2 = this.TAG;
            Log.w(str2, "remained " + this.watchDogCallbacks.size());
        }
    }

    public void watchBlocking(WatchDogCallback watchDogCallback, long j2) {
        if (watchDogCallback != null) {
            this.watchDogCallbacks.add(new TimeOutCheck(this.TAG, watchDogCallback, j2, 0));
            WatchDogService.triggerSchedule();
            return;
        }
        Log.w(this.TAG, "ignore watchdog because callback is null");
    }
}
