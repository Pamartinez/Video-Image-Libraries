package com.samsung.android.gallery.app.service.abstraction;

import F.b;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import bc.C0584a;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.abstraction.IProgressJob;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.dialog.DummyDialogHelper;
import com.samsung.android.gallery.module.service.dialog.NormalDialogHelper;
import com.samsung.android.gallery.module.service.notification.DefaultNotificationHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.o3dp.lib3dphotography.i;
import d4.a;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsProgressService extends BaseService {
    protected Blackboard mBlackboard;
    private final SubscriberListener mCancelListener = new a(this, 0);
    private final String mClassName;
    private int mCleanDelay = 0;
    private boolean mCollectOff;
    protected DialogHelper mDialogHelper = new DummyDialogHelper();
    private final Blackboard mGlobalBlackboard = Blackboard.getApplicationInstance();
    private Handler mHandler;
    private boolean mInterrupted;
    protected final IProgressJob mJobCallback = new com.samsung.android.sdk.scs.ai.language.a(13, this);
    protected String mLocationKey;
    private DefaultNotificationHelper mNotificationHelper;
    protected String mNotificationMessage;
    protected String mNotificationTitle;
    private final SubscriberListener mOptionListener = new a(this, 1);
    private final Queue<ProgressJob> mQueue = new ConcurrentLinkedQueue();
    private final SubscriberListener mRenameListener = new a(this, 2);
    private final String mServiceName;
    private ServiceState mState = ServiceState.NONE;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ServiceState {
        NONE,
        PREPARE,
        START,
        PROGRESS,
        INTERRUPT,
        END,
        TERMINATE
    }

    public AbsProgressService(String str, String str2) {
        this.mServiceName = str;
        this.mClassName = str2;
    }

    private void clean(boolean z) {
        if (this.mHandler == null) {
            emergencyStop();
            return;
        }
        sendProgressMessageDelayed(7, Boolean.valueOf(z), (Bundle) null, (long) this.mCleanDelay);
    }

    private void dismissNotification() {
        DefaultNotificationHelper defaultNotificationHelper = this.mNotificationHelper;
        if (defaultNotificationHelper != null) {
            defaultNotificationHelper.dismiss();
        }
    }

    private void doJobInThread() {
        SimpleThreadPool.getInstance().execute(new C0584a(28, this));
    }

    private void emergencyStop() {
        Log.e(this.TAG, "emergency stop current service. required variable is null");
        stopSelf();
    }

    private int getNotificationId() {
        return this.mServiceName.hashCode();
    }

    /* access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                Log.i(this.TAG, "handleMessage#START");
                if (!onPrepare((Intent) message.obj)) {
                    Log.w((CharSequence) this.TAG, "handleMessage#END. not prepared", this.mState, Boolean.TRUE);
                    onEnd(true);
                    break;
                } else {
                    onStart();
                    break;
                }
            case 1:
                onProgress();
                break;
            case 2:
                Log.i(this.TAG, "handleMessage#CONTINUE", this.mState);
                onContinue((Intent) message.obj);
                break;
            case 3:
                Log.i(this.TAG, "handleMessage#OPTION", this.mState);
                onOption(message.obj, message.getData());
                break;
            case 4:
                Log.i(this.TAG, "handleMessage#RENAME", this.mState);
                onRename(message.obj, message.getData());
                break;
            case 5:
                Log.i(this.TAG, "handleMessage#APPEND", this.mState);
                onAppend((Intent) message.obj);
                break;
            case 6:
                Log.i(this.TAG, "handleMessage#END", this.mState, Boolean.FALSE);
                onEnd(false);
                break;
            case 7:
                Log.i(this.TAG, "handleMessage#CLEAN", this.mState, message.obj);
                onClean(((Boolean) message.obj).booleanValue());
                break;
            case 8:
                Log.i(this.TAG, "handleMessage#TERMINATE", this.mState, message.obj);
                onTerminate(((Boolean) message.obj).booleanValue());
                break;
            case 9:
                Log.i(this.TAG, "handleMessage#INTERRUPT", this.mState);
                onInterrupt((Intent) message.obj);
                break;
            default:
                String str = this.TAG;
                Log.w(str, "handleMessage#UNKNOWN " + message.what);
                break;
        }
        return true;
    }

    private void initDialogHelper() {
        NormalDialogHelper normalDialogHelper = new NormalDialogHelper();
        this.mDialogHelper = normalDialogHelper;
        normalDialogHelper.init(this.mBlackboard);
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread(this.mServiceName);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), new b(3, this));
    }

    private void initNotificationHelper() {
        DefaultNotificationHelper notificationHelper = getNotificationHelper();
        this.mNotificationHelper = notificationHelper;
        notificationHelper.create(getChannelName());
        showNotification();
    }

    private boolean isExecutableState() {
        if (ServiceState.NONE.ordinal() >= this.mState.ordinal() || this.mState.ordinal() >= ServiceState.END.ordinal()) {
            return false;
        }
        return true;
    }

    private void keepGoing(Intent intent) {
        if (this.mHandler == null) {
            emergencyStop();
        } else if (this.mState.ordinal() < ServiceState.END.ordinal()) {
            sendProgressMessage(2, intent);
        } else {
            String str = this.TAG;
            Log.w(str, "unable to continue. [" + this.mState + "]");
            if (this.mState == ServiceState.TERMINATE) {
                terminate(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$doJobInThread$3() {
        doJob(this.mQueue.poll());
    }

    /* access modifiers changed from: private */
    public void next() {
        next(0);
    }

    private void nextFromQueue() {
        if (this.mQueue.isEmpty()) {
            Log.d(this.TAG, "queue is empty.");
            stop();
        } else if (isInterrupted()) {
            Log.d(this.TAG, "interrupted.");
            clearQueue();
            stop();
        } else if (this.mHandler == null) {
            Log.e(this.TAG, "terminated.");
        } else {
            ServiceState serviceState = ServiceState.PROGRESS;
            this.mState = serviceState;
            this.mBlackboard.publish("data://progress_service_state", Integer.valueOf(serviceState.ordinal()));
            doJobInThread();
        }
    }

    private void onAppend(Intent intent) {
        SimpleThreadPool.getInstance().execute(new i(3, this, intent));
    }

    /* access modifiers changed from: private */
    public void onCancel(Object obj, Bundle bundle) {
        Log.d(this.TAG, "progress cancel requested", this.mState);
        boolean isExecutableState = isExecutableState();
        boolean z = !isExecutableState;
        if (isExecutableState) {
            try {
                ServiceState serviceState = ServiceState.INTERRUPT;
                this.mState = serviceState;
                this.mBlackboard.publish("data://progress_service_state", Integer.valueOf(serviceState.ordinal()));
                this.mInterrupted = true;
                onCancelInternal();
                next();
            } catch (Exception e) {
                A.a.s(e, new StringBuilder("progress cancel failed, e="), this.TAG);
                z = true;
            }
        }
        Log.d(this.TAG, "progress cancelled", Boolean.valueOf(this.mInterrupted), Boolean.valueOf(z));
        if (z) {
            stop();
        }
    }

    private void onClean(boolean z) {
        try {
            onCleanInternal();
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("progress clean failed, e="), this.TAG);
        }
        terminate(z);
    }

    private void onContinue(Intent intent) {
        Blackboard instance;
        int ordinal = this.mState.ordinal();
        if (ordinal > ServiceState.NONE.ordinal() && ordinal < ServiceState.INTERRUPT.ordinal() && (instance = Blackboard.getInstance(intent.getStringExtra("blackboard_name"))) != null && instance != this.mBlackboard) {
            unsubscribeEvents();
            collectDataChange(false);
            this.mBlackboard = instance;
            initDialogHelper();
            subscribeEvents();
            collectDataChange(true);
            onContinueInternal();
        }
    }

    private void onEnd(boolean z) {
        this.mGlobalBlackboard.erase("data://running_service");
        if (isExecutableState()) {
            ServiceState serviceState = ServiceState.END;
            this.mState = serviceState;
            this.mBlackboard.publish("data://progress_service_state", Integer.valueOf(serviceState.ordinal()));
            try {
                onEndInternal();
            } catch (Exception e) {
                A.a.s(e, new StringBuilder("progress stop failed, e="), this.TAG);
            }
            clean(z);
            return;
        }
        terminate(z);
    }

    private void onInterrupt(Intent intent) {
        Log.d(this.TAG, "progress interrupt requested", this.mState);
        boolean isExecutableState = isExecutableState();
        boolean z = !isExecutableState;
        if (isExecutableState) {
            try {
                if (onInterruptInternal(intent)) {
                    ServiceState serviceState = ServiceState.INTERRUPT;
                    this.mState = serviceState;
                    this.mBlackboard.publish("data://progress_service_state", serviceState);
                    this.mInterrupted = true;
                    next();
                }
            } catch (Exception e) {
                A.a.s(e, new StringBuilder("process interrupt failed, e="), this.TAG);
                z = true;
            }
        }
        Log.d(this.TAG, "progress interrupted", Boolean.valueOf(this.mInterrupted), Boolean.valueOf(z));
        if (z) {
            stop();
        }
    }

    private void onOption(Object obj, Bundle bundle) {
        SimpleThreadPool.getInstance().execute(new d4.b(this, obj, bundle, 1));
    }

    private boolean onPrepare(Intent intent) {
        boolean z;
        this.mState = ServiceState.PREPARE;
        if (!onPrepareInternal(intent) || !addJobs(intent)) {
            z = false;
        } else {
            z = true;
        }
        this.mBlackboard.publish("data://progress_service_state", Integer.valueOf(this.mState.ordinal()));
        Log.d(this.TAG, "progress prepared", Boolean.valueOf(z));
        return z;
    }

    private void onProgress() {
        nextFromQueue();
    }

    private void onRename(Object obj, Bundle bundle) {
        SimpleThreadPool.getInstance().execute(new d4.b(this, obj, bundle, 0));
    }

    private void onStart() {
        ServiceState serviceState = ServiceState.START;
        this.mState = serviceState;
        this.mBlackboard.publish("data://progress_service_state", Integer.valueOf(serviceState.ordinal()));
        Log.d(this.TAG, "progress started");
        onStartInternal();
        next(500);
    }

    private void onTerminate(boolean z) {
        ServiceState serviceState = ServiceState.TERMINATE;
        this.mState = serviceState;
        this.mBlackboard.publish("data://progress_service_state", Integer.valueOf(serviceState.ordinal()));
        try {
            onTerminateInternal(z);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "progress terminate failed, e=" + e.getMessage());
        } finally {
            stopSelf();
        }
    }

    /* access modifiers changed from: private */
    public void option(Object obj, Bundle bundle) {
        if (this.mHandler == null) {
            emergencyStop();
        } else {
            sendProgressMessage(3, obj, bundle);
        }
    }

    private void releaseHandler() {
        try {
            this.mHandler.removeCallbacksAndMessages((Object) null);
            this.mHandler.getLooper().quitSafely();
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("releaseThreadHandler failed, e="), this.TAG);
        }
        this.mHandler = null;
    }

    /* access modifiers changed from: private */
    public void rename(Object obj, Bundle bundle) {
        if (this.mHandler == null) {
            emergencyStop();
        } else {
            sendProgressMessage(4, obj, bundle);
        }
    }

    private void showNotification() {
        this.mNotificationHelper.show(this);
    }

    private void start(Intent intent) {
        if (this.mHandler == null) {
            emergencyStop();
        } else if (this.mState == ServiceState.NONE) {
            sendProgressMessage(0, intent);
        } else {
            String str = this.TAG;
            Log.w(str, "unable to start. [" + this.mState + "] is not idle state.");
            ServiceState serviceState = this.mState;
            if (serviceState == ServiceState.TERMINATE) {
                terminate(true);
            } else if (serviceState == ServiceState.PROGRESS) {
                sendProgressMessage(5, intent);
            }
        }
    }

    private void stop() {
        if (this.mHandler == null) {
            emergencyStop();
            return;
        }
        removeProgressMessageIfExist(6);
        sendProgressMessageDelayed(6, (Object) null, (Bundle) null, 100);
    }

    private void subscribeEvents() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.subscribeOnUi("command://CancelDialog", this.mCancelListener);
            this.mBlackboard.subscribeOnUi("command://OperationSelected", this.mOptionListener);
            this.mBlackboard.subscribeOnUi("command://RenameSelected", this.mRenameListener);
        }
    }

    private void terminate(boolean z) {
        if (this.mHandler == null) {
            emergencyStop();
        } else {
            sendProgressMessage(8, Boolean.valueOf(z));
        }
    }

    private void unsubscribeEvents() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.unsubscribe("command://CancelDialog", this.mCancelListener);
            this.mBlackboard.unsubscribe("command://OperationSelected", this.mOptionListener);
            this.mBlackboard.unsubscribe("command://RenameSelected", this.mRenameListener);
        }
    }

    public abstract boolean addJobs(Intent intent);

    public void addToQueue(ProgressJob progressJob) {
        this.mQueue.add(progressJob);
    }

    public void appendToQueue(Queue<ProgressJob> queue) {
        this.mQueue.addAll(queue);
    }

    public void clearQueue() {
        this.mQueue.clear();
    }

    public void collectDataChange(boolean z) {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            BlackboardUtils.collectExternalDataChangedEvent(blackboard, z);
        }
    }

    public abstract void doJob(ProgressJob progressJob);

    public void forceRefreshData() {
        BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, !SdkConfig.atLeast(SdkConfig.GED.Q));
    }

    public abstract String getChannelName();

    public DefaultNotificationHelper getNotificationHelper() {
        return new DefaultNotificationHelper(this, getNotificationId(), this.mServiceName, this.mClassName);
    }

    public abstract int getPercentage();

    public int getQueueSize() {
        return this.mQueue.size();
    }

    public Queue<ProgressJob> getUnFinishedJob() {
        return this.mQueue;
    }

    public void interruptService() {
        this.mInterrupted = true;
    }

    public boolean isInterrupted() {
        return this.mInterrupted;
    }

    public boolean isQueueEmpty() {
        return this.mQueue.isEmpty();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCleanInternal() {
        dismissNotification();
        stopForeground(true);
        unsubscribeEvents();
        collectDataChange(false);
    }

    public abstract void onContinueInternal();

    public final void onCreate() {
        super.onCreate();
        initHandler();
    }

    public final void onDestroy() {
        this.mBlackboard.publish("data://progress_service_state", Integer.valueOf(ServiceState.NONE.ordinal()));
        releaseHandler();
        super.onDestroy();
    }

    public void onEndInternal() {
        if (this.mCollectOff) {
            collectDataChange(false);
        }
    }

    public boolean onInterruptInternal(Intent intent) {
        return true;
    }

    public boolean onPrepareInternal(Intent intent) {
        Blackboard instance = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        this.mBlackboard = instance;
        if (instance == null) {
            Log.w(this.TAG, "blackboard is null.");
            return false;
        }
        this.mGlobalBlackboard.publish("data://running_service", this.mServiceName);
        this.mLocationKey = intent.getStringExtra("location_key");
        initDialogHelper();
        initNotificationHelper();
        startForeground(this.mNotificationHelper.getSummaryId(), this.mNotificationHelper.getSummaryNotification());
        return true;
    }

    public final int onStartCommand(Intent intent, int i2, int i7) {
        if (!(intent == null || intent.getAction() == null)) {
            String action = intent.getAction();
            Log.i(this.TAG, "service receives [" + Logger.getSimpleName(action) + "]");
            action.getClass();
            char c5 = 65535;
            switch (action.hashCode()) {
                case -670797158:
                    if (action.equals("com.samsung.android.gallery.app.service.STOP_SERVICE")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -540770623:
                    if (action.equals("com.samsung.android.gallery.app.service.CONTINUE_SERVICE ")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 980299926:
                    if (action.equals("com.samsung.android.gallery.app.service.START_SERVICE")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 1082223299:
                    if (action.equals("com.samsung.android.gallery.app.service.DELETE_SERVICE")) {
                        c5 = 3;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    interruptService();
                    Handler handler = this.mHandler;
                    if (handler != null) {
                        handler.removeCallbacksAndMessages((Object) null);
                        sendProgressMessage(9, intent);
                        break;
                    } else {
                        onInterrupt(intent);
                        break;
                    }
                case 1:
                    keepGoing(intent);
                    break;
                case 2:
                    start(intent);
                    break;
                case 3:
                    terminate(false);
                    break;
            }
        }
        Log.w(this.TAG, "unable to operate startCommand [" + this.mState + "]");
        ServiceState serviceState = this.mState;
        if (serviceState == ServiceState.NONE || serviceState == ServiceState.TERMINATE) {
            terminate(true);
        }
        return 2;
    }

    public void onStartInternal() {
        collectDataChange(true);
        subscribeEvents();
        this.mNotificationHelper.updateChannelName(getChannelName());
    }

    public void onTerminateInternal(boolean z) {
        if (z) {
            Blackboard blackboard = this.mBlackboard;
            if (blackboard != null) {
                blackboard.erase("data://user/selected");
            }
            dismissNotification();
        }
    }

    public final void removeProgressMessageIfExist(int i2) {
        Handler handler = this.mHandler;
        if (handler != null && handler.hasMessages(i2)) {
            this.mHandler.removeMessages(i2);
        }
    }

    public final void sendProgressMessage(int i2, Object obj) {
        sendProgressMessageDelayed(i2, obj, (Bundle) null, 0);
    }

    public final void sendProgressMessageDelayed(int i2, Object obj, Bundle bundle, long j2) {
        Handler handler = this.mHandler;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(i2, obj);
            if (bundle != null) {
                obtainMessage.setData(bundle);
            }
            this.mHandler.sendMessageDelayed(obtainMessage, j2);
        }
    }

    public void setChangeCollectOff() {
        this.mCollectOff = true;
    }

    public void setCleanDelay(int i2) {
        this.mCleanDelay = i2;
    }

    public void updateNotification() {
        this.mNotificationHelper.update(this, getPercentage(), this.mNotificationTitle, this.mNotificationMessage);
    }

    private void next(long j2) {
        if (this.mHandler == null) {
            emergencyStop();
            return;
        }
        removeProgressMessageIfExist(1);
        sendProgressMessageDelayed(1, (Object) null, (Bundle) null, j2);
    }

    public final void sendProgressMessage(int i2, Object obj, Bundle bundle) {
        sendProgressMessageDelayed(i2, obj, bundle, 0);
    }

    public void onCancelInternal() {
    }

    /* renamed from: onAppendInternal */
    public void lambda$onAppend$2(Intent intent) {
    }

    /* renamed from: onOptionInternal */
    public void lambda$onOption$0(Object obj, Bundle bundle) {
    }

    /* renamed from: onRenameInternal */
    public void lambda$onRename$1(Object obj, Bundle bundle) {
    }
}
