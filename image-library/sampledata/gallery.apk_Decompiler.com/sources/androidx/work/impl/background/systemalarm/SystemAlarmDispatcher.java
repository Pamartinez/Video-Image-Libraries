package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkLauncher;
import androidx.work.impl.WorkLauncherImpl;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SystemAlarmDispatcher implements ExecutionListener {
    static final String TAG = Logger.tagWithPrefix("SystemAlarmDispatcher");
    final CommandHandler mCommandHandler;
    private CommandsCompletedListener mCompletedListener;
    final Context mContext;
    Intent mCurrentIntent;
    final List<Intent> mIntents;
    private final Processor mProcessor;
    private StartStopTokens mStartStopTokens;
    final TaskExecutor mTaskExecutor;
    private final WorkLauncher mWorkLauncher;
    private final WorkManagerImpl mWorkManager;
    private final WorkTimer mWorkTimer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AddRunnable implements Runnable {
        private final SystemAlarmDispatcher mDispatcher;
        private final Intent mIntent;
        private final int mStartId;

        public AddRunnable(SystemAlarmDispatcher systemAlarmDispatcher, Intent intent, int i2) {
            this.mDispatcher = systemAlarmDispatcher;
            this.mIntent = intent;
            this.mStartId = i2;
        }

        public void run() {
            this.mDispatcher.add(this.mIntent, this.mStartId);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CommandsCompletedListener {
        void onAllCommandsCompleted();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DequeueAndCheckForCompletion implements Runnable {
        private final SystemAlarmDispatcher mDispatcher;

        public DequeueAndCheckForCompletion(SystemAlarmDispatcher systemAlarmDispatcher) {
            this.mDispatcher = systemAlarmDispatcher;
        }

        public void run() {
            this.mDispatcher.dequeueAndCheckForCompletion();
        }
    }

    public SystemAlarmDispatcher(Context context) {
        this(context, (Processor) null, (WorkManagerImpl) null, (WorkLauncher) null);
    }

    private void assertMainThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    private boolean hasIntentWithAction(String str) {
        assertMainThread();
        synchronized (this.mIntents) {
            try {
                for (Intent action : this.mIntents) {
                    if (str.equals(action.getAction())) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void processCommand() {
        assertMainThread();
        PowerManager.WakeLock newWakeLock = WakeLocks.newWakeLock(this.mContext, "ProcessCommand");
        try {
            newWakeLock.acquire();
            this.mWorkManager.getWorkTaskExecutor().executeOnTaskThread(new Runnable() {
                public void run() {
                    Executor mainThreadExecutor;
                    DequeueAndCheckForCompletion dequeueAndCheckForCompletion;
                    synchronized (SystemAlarmDispatcher.this.mIntents) {
                        SystemAlarmDispatcher systemAlarmDispatcher = SystemAlarmDispatcher.this;
                        systemAlarmDispatcher.mCurrentIntent = systemAlarmDispatcher.mIntents.get(0);
                    }
                    Intent intent = SystemAlarmDispatcher.this.mCurrentIntent;
                    if (intent != null) {
                        String action = intent.getAction();
                        int intExtra = SystemAlarmDispatcher.this.mCurrentIntent.getIntExtra("KEY_START_ID", 0);
                        Logger logger = Logger.get();
                        String str = SystemAlarmDispatcher.TAG;
                        logger.debug(str, "Processing command " + SystemAlarmDispatcher.this.mCurrentIntent + ArcCommonLog.TAG_COMMA + intExtra);
                        Context context = SystemAlarmDispatcher.this.mContext;
                        PowerManager.WakeLock newWakeLock = WakeLocks.newWakeLock(context, action + " (" + intExtra + ")");
                        try {
                            Logger logger2 = Logger.get();
                            logger2.debug(str, "Acquiring operation wake lock (" + action + ") " + newWakeLock);
                            newWakeLock.acquire();
                            SystemAlarmDispatcher systemAlarmDispatcher2 = SystemAlarmDispatcher.this;
                            systemAlarmDispatcher2.mCommandHandler.onHandleIntent(systemAlarmDispatcher2.mCurrentIntent, intExtra, systemAlarmDispatcher2);
                            Logger logger3 = Logger.get();
                            logger3.debug(str, "Releasing operation wake lock (" + action + ") " + newWakeLock);
                            newWakeLock.release();
                            mainThreadExecutor = SystemAlarmDispatcher.this.mTaskExecutor.getMainThreadExecutor();
                            dequeueAndCheckForCompletion = new DequeueAndCheckForCompletion(SystemAlarmDispatcher.this);
                        } catch (Throwable th) {
                            Logger logger4 = Logger.get();
                            String str2 = SystemAlarmDispatcher.TAG;
                            logger4.debug(str2, "Releasing operation wake lock (" + action + ") " + newWakeLock);
                            newWakeLock.release();
                            SystemAlarmDispatcher.this.mTaskExecutor.getMainThreadExecutor().execute(new DequeueAndCheckForCompletion(SystemAlarmDispatcher.this));
                            throw th;
                        }
                        mainThreadExecutor.execute(dequeueAndCheckForCompletion);
                    }
                }
            });
        } finally {
            newWakeLock.release();
        }
    }

    public boolean add(Intent intent, int i2) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Adding command " + intent + " (" + i2 + ")");
        assertMainThread();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            Logger.get().warning(str, "Unknown command. Ignoring");
            return false;
        } else if ("ACTION_CONSTRAINTS_CHANGED".equals(action) && hasIntentWithAction("ACTION_CONSTRAINTS_CHANGED")) {
            return false;
        } else {
            intent.putExtra("KEY_START_ID", i2);
            synchronized (this.mIntents) {
                try {
                    boolean isEmpty = this.mIntents.isEmpty();
                    this.mIntents.add(intent);
                    if (isEmpty) {
                        processCommand();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        }
    }

    public void dequeueAndCheckForCompletion() {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Checking if commands are complete.");
        assertMainThread();
        synchronized (this.mIntents) {
            try {
                if (this.mCurrentIntent != null) {
                    Logger logger2 = Logger.get();
                    logger2.debug(str, "Removing command " + this.mCurrentIntent);
                    if (this.mIntents.remove(0).equals(this.mCurrentIntent)) {
                        this.mCurrentIntent = null;
                    } else {
                        throw new IllegalStateException("Dequeue-d command is not the first.");
                    }
                }
                SerialExecutor serialTaskExecutor = this.mTaskExecutor.getSerialTaskExecutor();
                if (!this.mCommandHandler.hasPendingCommands() && this.mIntents.isEmpty() && !serialTaskExecutor.hasPendingTasks()) {
                    Logger.get().debug(str, "No more commands & intents.");
                    CommandsCompletedListener commandsCompletedListener = this.mCompletedListener;
                    if (commandsCompletedListener != null) {
                        commandsCompletedListener.onAllCommandsCompleted();
                    }
                } else if (!this.mIntents.isEmpty()) {
                    processCommand();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Processor getProcessor() {
        return this.mProcessor;
    }

    public TaskExecutor getTaskExecutor() {
        return this.mTaskExecutor;
    }

    public WorkManagerImpl getWorkManager() {
        return this.mWorkManager;
    }

    public WorkTimer getWorkTimer() {
        return this.mWorkTimer;
    }

    public WorkLauncher getWorkerLauncher() {
        return this.mWorkLauncher;
    }

    public void onDestroy() {
        Logger.get().debug(TAG, "Destroying SystemAlarmDispatcher");
        this.mProcessor.removeExecutionListener(this);
        this.mCompletedListener = null;
    }

    public void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        this.mTaskExecutor.getMainThreadExecutor().execute(new AddRunnable(this, CommandHandler.createExecutionCompletedIntent(this.mContext, workGenerationalId, z), 0));
    }

    public void setCompletedListener(CommandsCompletedListener commandsCompletedListener) {
        if (this.mCompletedListener != null) {
            Logger.get().error(TAG, "A completion listener for SystemAlarmDispatcher already exists.");
        } else {
            this.mCompletedListener = commandsCompletedListener;
        }
    }

    public SystemAlarmDispatcher(Context context, Processor processor, WorkManagerImpl workManagerImpl, WorkLauncher workLauncher) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mStartStopTokens = StartStopTokens.create();
        workManagerImpl = workManagerImpl == null ? WorkManagerImpl.getInstance(context) : workManagerImpl;
        this.mWorkManager = workManagerImpl;
        this.mCommandHandler = new CommandHandler(applicationContext, workManagerImpl.getConfiguration().getClock(), this.mStartStopTokens);
        this.mWorkTimer = new WorkTimer(workManagerImpl.getConfiguration().getRunnableScheduler());
        processor = processor == null ? workManagerImpl.getProcessor() : processor;
        this.mProcessor = processor;
        TaskExecutor workTaskExecutor = workManagerImpl.getWorkTaskExecutor();
        this.mTaskExecutor = workTaskExecutor;
        this.mWorkLauncher = workLauncher == null ? new WorkLauncherImpl(processor, workTaskExecutor) : workLauncher;
        processor.addExecutionListener(this);
        this.mIntents = new ArrayList();
        this.mCurrentIntent = null;
    }
}
