package com.samsung.android.sdk.scs.base.connection;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.feature.FeatureStatusCache;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ServiceExecutor extends ThreadPoolExecutor implements InternalServiceConnectionListener, Application.ActivityLifecycleCallbacks, AutoCloseable {
    private static final boolean CONNECTION_TIMER_ON = false;
    private static final String TAG = "ScsApi@ServiceExecutor";
    private final Condition mConnectionCondition;
    private final InternalServiceConnectionListener mConnectionListener = new InternalServiceConnectionListener() {
        public void onConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(ServiceExecutor.TAG, "onConnected");
            ServiceExecutor.this.onConnected(componentName, iBinder);
            ServiceExecutor.this.unlockConnection(true, "connected, signal all");
        }

        public void onDisconnected(ComponentName componentName) {
            Log.d(ServiceExecutor.TAG, "onDisconnected");
            ServiceExecutor.this.onDisconnected(componentName);
            ServiceExecutor.this.unlockConnection(false, "disconnected, signal all");
        }

        public void onError() {
            Log.d(ServiceExecutor.TAG, "onError");
            ServiceExecutor.this.onError();
            ServiceExecutor.this.unlockConnection(false, "onError, signal all");
        }
    };
    private final ReentrantLock mConnectionLock;
    private TimerTask mConnectionManagementTask;
    protected ConnectionManager mConnectionManager;
    protected final Context mContext;
    private boolean mIsConnected = false;
    private final AtomicInteger mTaskCount;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ServiceExecutor(Context context, int i2, int i7, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i2, i7, j2, timeUnit, blockingQueue);
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mConnectionLock = reentrantLock;
        this.mConnectionCondition = reentrantLock.newCondition();
        allowCoreThreadTimeOut(true);
        Log.d(TAG, "use application context");
        this.mContext = context.getApplicationContext();
        this.mTaskCount = new AtomicInteger(0);
        this.mConnectionManager = new ConnectionManager();
        Log.d(TAG, "ServiceExecutor. ctor()");
    }

    private boolean connect(Context context, Intent intent, InternalServiceConnectionListener internalServiceConnectionListener) {
        Log.d(TAG, "connect");
        if (this.mConnectionManager.isServiceConnected()) {
            return true;
        }
        return this.mConnectionManager.connect(context, intent, internalServiceConnectionListener);
    }

    /* access modifiers changed from: private */
    public void unlockConnection(boolean z, String str) {
        this.mConnectionLock.lock();
        try {
            this.mIsConnected = z;
            Log.d(TAG, str);
            this.mConnectionCondition.signalAll();
        } finally {
            this.mConnectionLock.unlock();
        }
    }

    public void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        this.mTaskCount.getAndDecrement();
        Log.d(TAG, "afterExecute(). mTaskCount: " + this.mTaskCount);
    }

    public void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        Log.objectInfo(TAG, "task", this, runnable);
        if (runnable instanceof TaskRunnable) {
            String featureName = ((TaskRunnable) runnable).getFeatureName();
            if (FeatureStatusCache.getStatus(featureName) == -1000) {
                int checkFeature = Feature.checkFeature(this.mContext, featureName);
                Log.d(TAG, "beforeExecute(). First check for " + featureName + ". status: " + checkFeature);
            }
        } else {
            Log.e(TAG, "Unexpected runnable!!!!");
        }
        this.mConnectionLock.lock();
        try {
            if (!this.mIsConnected) {
                Log.d(TAG, "beforeExecute() : not connected, try to connect");
                if (!connect(this.mContext, getServiceIntent(), this.mConnectionListener)) {
                    Log.e(TAG, "beforeExecute() : failed to bind service");
                    thread.interrupt();
                } else {
                    Log.d(TAG, "beforeExecute() : before wait");
                    if (!this.mIsConnected) {
                        this.mConnectionCondition.await();
                    }
                    Log.d(TAG, "beforeExecute() : after wait");
                    if (!this.mIsConnected) {
                        thread.interrupt();
                    }
                }
            }
        } catch (InterruptedException | SecurityException e) {
            e.printStackTrace();
            thread.interrupt();
        } catch (Throwable th) {
            this.mConnectionLock.unlock();
            throw th;
        }
        this.mConnectionLock.unlock();
        this.mTaskCount.getAndIncrement();
        Log.d(TAG, "beforeExecute(). mTaskCount: " + this.mTaskCount);
    }

    public final /* synthetic */ void close() {
        boolean isTerminated;
        if (this != ForkJoinPool.commonPool() && !(isTerminated = isTerminated())) {
            shutdown();
            boolean z = false;
            while (!isTerminated) {
                try {
                    isTerminated = awaitTermination(1, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void deInit() {
        Log.d(TAG, "deInit");
        ConnectionManager connectionManager = this.mConnectionManager;
        if (connectionManager != null) {
            connectionManager.disconnect();
        }
    }

    public void finalize() {
        super.finalize();
        Log.d(TAG, "finalize");
        ConnectionManager connectionManager = this.mConnectionManager;
        if (connectionManager != null) {
            connectionManager.disconnect();
        }
    }

    public abstract Intent getServiceIntent();

    public boolean isConnected() {
        return this.mConnectionManager.isServiceConnected();
    }

    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, "onActivityDestroyed");
        deInit();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ServiceExecutor(Activity activity, int i2, int i7, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i2, i7, j2, timeUnit, blockingQueue);
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mConnectionLock = reentrantLock;
        this.mConnectionCondition = reentrantLock.newCondition();
        allowCoreThreadTimeOut(true);
        Log.d(TAG, "use activity context");
        this.mContext = activity;
        activity.registerActivityLifecycleCallbacks(this);
        this.mTaskCount = new AtomicInteger(0);
        this.mConnectionManager = new ConnectionManager();
        Log.d(TAG, "ServiceExecutor. ctor()");
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
