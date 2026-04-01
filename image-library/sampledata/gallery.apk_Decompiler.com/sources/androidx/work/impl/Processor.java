package androidx.work.impl;

import B8.g;
import android.content.Context;
import android.os.PowerManager;
import androidx.core.content.ContextCompat;
import androidx.work.Configuration;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Processor implements ForegroundProcessor {
    private static final String TAG = Logger.tagWithPrefix("Processor");
    private Context mAppContext;
    private Set<String> mCancelledIds = new HashSet();
    private Configuration mConfiguration;
    private Map<String, WorkerWrapper> mEnqueuedWorkMap = new HashMap();
    private PowerManager.WakeLock mForegroundLock = null;
    private Map<String, WorkerWrapper> mForegroundWorkMap = new HashMap();
    private final Object mLock = new Object();
    private final List<ExecutionListener> mOuterListeners = new ArrayList();
    private WorkDatabase mWorkDatabase;
    private Map<String, Set<StartStopToken>> mWorkRuns = new HashMap();
    private TaskExecutor mWorkTaskExecutor;

    public Processor(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase) {
        this.mAppContext = context;
        this.mConfiguration = configuration;
        this.mWorkTaskExecutor = taskExecutor;
        this.mWorkDatabase = workDatabase;
    }

    private WorkerWrapper cleanUpWorkerUnsafe(String str) {
        boolean z;
        WorkerWrapper remove = this.mForegroundWorkMap.remove(str);
        if (remove != null) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            remove = this.mEnqueuedWorkMap.remove(str);
        }
        this.mWorkRuns.remove(str);
        if (z) {
            stopForegroundService();
        }
        return remove;
    }

    private WorkerWrapper getWorkerWrapperUnsafe(String str) {
        WorkerWrapper workerWrapper = this.mForegroundWorkMap.get(str);
        if (workerWrapper == null) {
            return this.mEnqueuedWorkMap.get(str);
        }
        return workerWrapper;
    }

    private static boolean interrupt(String str, WorkerWrapper workerWrapper, int i2) {
        if (workerWrapper != null) {
            workerWrapper.interrupt(i2);
            Logger logger = Logger.get();
            String str2 = TAG;
            logger.debug(str2, "WorkerWrapper interrupted for " + str);
            return true;
        }
        Logger logger2 = Logger.get();
        String str3 = TAG;
        logger2.debug(str3, "WorkerWrapper could not be found for " + str);
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runOnExecuted$2(WorkGenerationalId workGenerationalId, boolean z) {
        synchronized (this.mLock) {
            try {
                for (ExecutionListener onExecuted : this.mOuterListeners) {
                    onExecuted.onExecuted(workGenerationalId, z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WorkSpec lambda$startWork$0(ArrayList arrayList, String str) {
        arrayList.addAll(this.mWorkDatabase.workTagDao().getTagsForWorkSpecId(str));
        return this.mWorkDatabase.workSpecDao().getWorkSpec(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startWork$1(ListenableFuture listenableFuture, WorkerWrapper workerWrapper) {
        boolean z;
        try {
            z = ((Boolean) listenableFuture.get()).booleanValue();
        } catch (InterruptedException | ExecutionException unused) {
            z = true;
        }
        onExecuted(workerWrapper, z);
    }

    private void onExecuted(WorkerWrapper workerWrapper, boolean z) {
        synchronized (this.mLock) {
            try {
                WorkGenerationalId workGenerationalId = workerWrapper.getWorkGenerationalId();
                String workSpecId = workGenerationalId.getWorkSpecId();
                if (getWorkerWrapperUnsafe(workSpecId) == workerWrapper) {
                    cleanUpWorkerUnsafe(workSpecId);
                }
                Logger logger = Logger.get();
                String str = TAG;
                logger.debug(str, getClass().getSimpleName() + " " + workSpecId + " executed; reschedule = " + z);
                for (ExecutionListener onExecuted : this.mOuterListeners) {
                    onExecuted.onExecuted(workGenerationalId, z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void runOnExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        this.mWorkTaskExecutor.getMainThreadExecutor().execute(new g((Object) this, (Object) workGenerationalId, z, 14));
    }

    private void stopForegroundService() {
        synchronized (this.mLock) {
            try {
                if (this.mForegroundWorkMap.isEmpty()) {
                    this.mAppContext.startService(SystemForegroundDispatcher.createStopForegroundIntent(this.mAppContext));
                    PowerManager.WakeLock wakeLock = this.mForegroundLock;
                    if (wakeLock != null) {
                        wakeLock.release();
                        this.mForegroundLock = null;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addExecutionListener(ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.add(executionListener);
        }
    }

    public WorkSpec getRunningWorkSpec(String str) {
        synchronized (this.mLock) {
            try {
                WorkerWrapper workerWrapperUnsafe = getWorkerWrapperUnsafe(str);
                if (workerWrapperUnsafe == null) {
                    return null;
                }
                WorkSpec workSpec = workerWrapperUnsafe.getWorkSpec();
                return workSpec;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean isCancelled(String str) {
        boolean contains;
        synchronized (this.mLock) {
            contains = this.mCancelledIds.contains(str);
        }
        return contains;
    }

    public boolean isEnqueued(String str) {
        boolean z;
        synchronized (this.mLock) {
            if (getWorkerWrapperUnsafe(str) != null) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void removeExecutionListener(ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.remove(executionListener);
        }
    }

    public void startForeground(String str, ForegroundInfo foregroundInfo) {
        synchronized (this.mLock) {
            try {
                Logger logger = Logger.get();
                String str2 = TAG;
                logger.info(str2, "Moving WorkSpec (" + str + ") to the foreground");
                WorkerWrapper remove = this.mEnqueuedWorkMap.remove(str);
                if (remove != null) {
                    if (this.mForegroundLock == null) {
                        PowerManager.WakeLock newWakeLock = WakeLocks.newWakeLock(this.mAppContext, "ProcessorForegroundLck");
                        this.mForegroundLock = newWakeLock;
                        newWakeLock.acquire();
                    }
                    this.mForegroundWorkMap.put(str, remove);
                    ContextCompat.startForegroundService(this.mAppContext, SystemForegroundDispatcher.createStartForegroundIntent(this.mAppContext, remove.getWorkGenerationalId(), foregroundInfo));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean startWork(StartStopToken startStopToken) {
        return startWork(startStopToken, (WorkerParameters.RuntimeExtras) null);
    }

    public boolean stopAndCancelWork(String str, int i2) {
        WorkerWrapper cleanUpWorkerUnsafe;
        synchronized (this.mLock) {
            Logger logger = Logger.get();
            String str2 = TAG;
            logger.debug(str2, "Processor cancelling " + str);
            this.mCancelledIds.add(str);
            cleanUpWorkerUnsafe = cleanUpWorkerUnsafe(str);
        }
        return interrupt(str, cleanUpWorkerUnsafe, i2);
    }

    public boolean stopForegroundWork(StartStopToken startStopToken, int i2) {
        WorkerWrapper cleanUpWorkerUnsafe;
        String workSpecId = startStopToken.getId().getWorkSpecId();
        synchronized (this.mLock) {
            cleanUpWorkerUnsafe = cleanUpWorkerUnsafe(workSpecId);
        }
        return interrupt(workSpecId, cleanUpWorkerUnsafe, i2);
    }

    public boolean stopWork(StartStopToken startStopToken, int i2) {
        String workSpecId = startStopToken.getId().getWorkSpecId();
        synchronized (this.mLock) {
            try {
                if (this.mForegroundWorkMap.get(workSpecId) != null) {
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.debug(str, "Ignored stopWork. WorkerWrapper " + workSpecId + " is in foreground");
                    return false;
                }
                Set set = this.mWorkRuns.get(workSpecId);
                if (set != null) {
                    if (set.contains(startStopToken)) {
                        WorkerWrapper cleanUpWorkerUnsafe = cleanUpWorkerUnsafe(workSpecId);
                        return interrupt(workSpecId, cleanUpWorkerUnsafe, i2);
                    }
                }
                return false;
            } finally {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x008b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean startWork(androidx.work.impl.StartStopToken r13, androidx.work.WorkerParameters.RuntimeExtras r14) {
        /*
            r12 = this;
            java.lang.String r0 = "Work "
            androidx.work.impl.model.WorkGenerationalId r1 = r13.getId()
            java.lang.String r2 = r1.getWorkSpecId()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            androidx.work.impl.WorkDatabase r3 = r12.mWorkDatabase
            Tc.a r4 = new Tc.a
            r5 = 9
            r4.<init>((java.lang.Object) r12, (java.io.Serializable) r10, (java.lang.String) r2, (int) r5)
            java.lang.Object r3 = r3.runInTransaction(r4)
            r9 = r3
            androidx.work.impl.model.WorkSpec r9 = (androidx.work.impl.model.WorkSpec) r9
            r3 = 0
            if (r9 != 0) goto L_0x003d
            androidx.work.Logger r13 = androidx.work.Logger.get()
            java.lang.String r14 = TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Didn't find WorkSpec for id "
            r0.<init>(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r13.warning(r14, r0)
            r12.runOnExecuted(r1, r3)
            return r3
        L_0x003d:
            java.lang.Object r11 = r12.mLock
            monitor-enter(r11)
            boolean r4 = r12.isEnqueued(r2)     // Catch:{ all -> 0x0084 }
            if (r4 == 0) goto L_0x008c
            java.util.Map<java.lang.String, java.util.Set<androidx.work.impl.StartStopToken>> r14 = r12.mWorkRuns     // Catch:{ all -> 0x0084 }
            java.lang.Object r14 = r14.get(r2)     // Catch:{ all -> 0x0084 }
            java.util.Set r14 = (java.util.Set) r14     // Catch:{ all -> 0x0084 }
            java.util.Iterator r2 = r14.iterator()     // Catch:{ all -> 0x0084 }
            java.lang.Object r2 = r2.next()     // Catch:{ all -> 0x0084 }
            androidx.work.impl.StartStopToken r2 = (androidx.work.impl.StartStopToken) r2     // Catch:{ all -> 0x0084 }
            androidx.work.impl.model.WorkGenerationalId r2 = r2.getId()     // Catch:{ all -> 0x0084 }
            int r2 = r2.getGeneration()     // Catch:{ all -> 0x0084 }
            int r4 = r1.getGeneration()     // Catch:{ all -> 0x0084 }
            if (r2 != r4) goto L_0x0087
            r14.add(r13)     // Catch:{ all -> 0x0084 }
            androidx.work.Logger r12 = androidx.work.Logger.get()     // Catch:{ all -> 0x0084 }
            java.lang.String r13 = TAG     // Catch:{ all -> 0x0084 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0084 }
            r14.<init>(r0)     // Catch:{ all -> 0x0084 }
            r14.append(r1)     // Catch:{ all -> 0x0084 }
            java.lang.String r0 = " is already enqueued for processing"
            r14.append(r0)     // Catch:{ all -> 0x0084 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0084 }
            r12.debug(r13, r14)     // Catch:{ all -> 0x0084 }
            goto L_0x008a
        L_0x0084:
            r0 = move-exception
            r12 = r0
            goto L_0x00ff
        L_0x0087:
            r12.runOnExecuted(r1, r3)     // Catch:{ all -> 0x0084 }
        L_0x008a:
            monitor-exit(r11)     // Catch:{ all -> 0x0084 }
            return r3
        L_0x008c:
            int r0 = r9.getGeneration()     // Catch:{ all -> 0x0084 }
            int r4 = r1.getGeneration()     // Catch:{ all -> 0x0084 }
            if (r0 == r4) goto L_0x009b
            r12.runOnExecuted(r1, r3)     // Catch:{ all -> 0x0084 }
            monitor-exit(r11)     // Catch:{ all -> 0x0084 }
            return r3
        L_0x009b:
            androidx.work.impl.WorkerWrapper$Builder r3 = new androidx.work.impl.WorkerWrapper$Builder     // Catch:{ all -> 0x0084 }
            android.content.Context r4 = r12.mAppContext     // Catch:{ all -> 0x0084 }
            androidx.work.Configuration r5 = r12.mConfiguration     // Catch:{ all -> 0x0084 }
            androidx.work.impl.utils.taskexecutor.TaskExecutor r6 = r12.mWorkTaskExecutor     // Catch:{ all -> 0x0084 }
            androidx.work.impl.WorkDatabase r8 = r12.mWorkDatabase     // Catch:{ all -> 0x0084 }
            r7 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0084 }
            androidx.work.impl.WorkerWrapper$Builder r12 = r3.withRuntimeExtras(r14)     // Catch:{ all -> 0x0084 }
            androidx.work.impl.WorkerWrapper r12 = r12.build()     // Catch:{ all -> 0x0084 }
            com.google.common.util.concurrent.ListenableFuture r14 = r12.launch()     // Catch:{ all -> 0x0084 }
            bc.d r0 = new bc.d     // Catch:{ all -> 0x0084 }
            r3 = 29
            r0.<init>((java.lang.Object) r7, (java.lang.Object) r14, (java.lang.Object) r12, (int) r3)     // Catch:{ all -> 0x0084 }
            androidx.work.impl.utils.taskexecutor.TaskExecutor r3 = r7.mWorkTaskExecutor     // Catch:{ all -> 0x0084 }
            java.util.concurrent.Executor r3 = r3.getMainThreadExecutor()     // Catch:{ all -> 0x0084 }
            r14.addListener(r0, r3)     // Catch:{ all -> 0x0084 }
            java.util.Map<java.lang.String, androidx.work.impl.WorkerWrapper> r14 = r7.mEnqueuedWorkMap     // Catch:{ all -> 0x0084 }
            r14.put(r2, r12)     // Catch:{ all -> 0x0084 }
            java.util.HashSet r12 = new java.util.HashSet     // Catch:{ all -> 0x0084 }
            r12.<init>()     // Catch:{ all -> 0x0084 }
            r12.add(r13)     // Catch:{ all -> 0x0084 }
            java.util.Map<java.lang.String, java.util.Set<androidx.work.impl.StartStopToken>> r13 = r7.mWorkRuns     // Catch:{ all -> 0x0084 }
            r13.put(r2, r12)     // Catch:{ all -> 0x0084 }
            monitor-exit(r11)     // Catch:{ all -> 0x0084 }
            androidx.work.Logger r12 = androidx.work.Logger.get()
            java.lang.String r13 = TAG
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.Class r0 = r7.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r14.append(r0)
            java.lang.String r0 = ": processing "
            r14.append(r0)
            r14.append(r1)
            java.lang.String r14 = r14.toString()
            r12.debug(r13, r14)
            r12 = 1
            return r12
        L_0x00ff:
            monitor-exit(r11)     // Catch:{ all -> 0x0084 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.Processor.startWork(androidx.work.impl.StartStopToken, androidx.work.WorkerParameters$RuntimeExtras):boolean");
    }
}
