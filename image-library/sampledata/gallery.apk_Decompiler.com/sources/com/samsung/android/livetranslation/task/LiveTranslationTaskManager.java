package com.samsung.android.livetranslation.task;

import android.content.Context;
import android.graphics.Rect;
import c0.C0086a;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.livetranslation.text.KeyFrame;
import com.samsung.android.livetranslation.util.LTTLogger;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveTranslationTaskManager {
    private final String TAG = "LiveTranslationTaskManager";
    private Context mContext;
    private KeyFrame mKeyFrame;
    private LiveTranslationTaskManagerListener mLiveTranslationTaskManagerListener;
    private List<LiveTranslationTask> mPostSTRTasks = new ArrayList();
    private List<LiveTranslationTask> mPostTRLTasks = new ArrayList();
    private List<LiveTranslationTask> mSTRTasks = new ArrayList();
    /* access modifiers changed from: private */
    public STATUS mStatus;

    /* renamed from: com.samsung.android.livetranslation.task.LiveTranslationTaskManager$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$livetranslation$task$LiveTranslationTask$TASKTYPE;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.livetranslation.task.LiveTranslationTask$TASKTYPE[] r0 = com.samsung.android.livetranslation.task.LiveTranslationTask.TASKTYPE.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$livetranslation$task$LiveTranslationTask$TASKTYPE = r0
                com.samsung.android.livetranslation.task.LiveTranslationTask$TASKTYPE r1 = com.samsung.android.livetranslation.task.LiveTranslationTask.TASKTYPE.ENTIRE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$livetranslation$task$LiveTranslationTask$TASKTYPE     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.livetranslation.task.LiveTranslationTask$TASKTYPE r1 = com.samsung.android.livetranslation.task.LiveTranslationTask.TASKTYPE.POST_STR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$livetranslation$task$LiveTranslationTask$TASKTYPE     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.livetranslation.task.LiveTranslationTask$TASKTYPE r1 = com.samsung.android.livetranslation.task.LiveTranslationTask.TASKTYPE.POST_TRL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.livetranslation.task.LiveTranslationTaskManager.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface LiveTranslationTaskManagerListener {
        void onSTRFinish(boolean z, KeyFrame keyFrame, LttOcrResult lttOcrResult, int i2);

        void onTRLFinish(boolean z, KeyFrame keyFrame, int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum STATUS {
        STR_PREPARING,
        STR_WAITING,
        STR_ARRIVED,
        STR_FAIL,
        STR_COMPLETED,
        TRL_PREPARING,
        TRL_WAITING,
        TRL_ARRIVED,
        TRL_FAIL,
        TRL_COMPLETED,
        CANCELED
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class TaskStatusListenerImpl implements LiveTranslationTask.TaskStatusListener {
        public TaskStatusListenerImpl() {
        }

        public synchronized void updateStatus(STATUS status) {
            LiveTranslationTaskManager.this.mStatus = status;
        }
    }

    public LiveTranslationTaskManager(Context context, KeyFrame keyFrame, Rect rect, LiveTranslationTaskManagerListener liveTranslationTaskManagerListener, String str, boolean z) {
        this.mContext = context;
        this.mKeyFrame = keyFrame;
        this.mLiveTranslationTaskManagerListener = liveTranslationTaskManagerListener;
        this.mSTRTasks.clear();
        this.mPostSTRTasks.clear();
        this.mPostTRLTasks.clear();
    }

    private void cancelTask(List<LiveTranslationTask> list) {
        for (LiveTranslationTask cancel : list) {
            cancel.cancel();
        }
    }

    private static <T> T getLastElement(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return C0086a.f(1, list);
    }

    private boolean isValidCPs() {
        if (!this.mKeyFrame.isValidSTRCP() || !this.mKeyFrame.isValidTRLCP()) {
            return false;
        }
        return true;
    }

    private synchronized void updateStatus(STATUS status) {
        try {
            if (STATUS.CANCELED != this.mStatus) {
                this.mStatus = status;
            } else {
                String str = this.TAG;
                LTTLogger.e(str, "updateStatus() But current Status is STATUS.CANCELED , It can NOT change to newStatus:" + status);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void cancel() {
        updateStatus(STATUS.CANCELED);
        cancelTask(this.mPostTRLTasks);
        cancelTask(this.mPostSTRTasks);
        cancelTask(this.mSTRTasks);
    }

    public STATUS getStatus() {
        return this.mStatus;
    }

    public void requestTask(String str, String str2, LiveTranslationTask.TASKTYPE tasktype, LttOcrResult lttOcrResult) {
        requestTask(str, str2, tasktype, lttOcrResult, (List<String>) null);
    }

    public void requestTaskPostTRL(String str, String str2, List<String> list) {
        requestTask(str, str2, LiveTranslationTask.TASKTYPE.POST_TRL, (LttOcrResult) null, list);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b5, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void requestTask(java.lang.String r7, java.lang.String r8, com.samsung.android.livetranslation.task.LiveTranslationTask.TASKTYPE r9, com.samsung.android.imagetranslation.data.LttOcrResult r10, java.util.List<java.lang.String> r11) {
        /*
            r6 = this;
            java.lang.String r0 = "Unexpected CP : STR_CP="
            java.lang.String r1 = "requestTask() : srcLang="
            monitor-enter(r6)
            java.lang.String r2 = r6.TAG     // Catch:{ all -> 0x0053 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0053 }
            r3.<init>(r1)     // Catch:{ all -> 0x0053 }
            r3.append(r7)     // Catch:{ all -> 0x0053 }
            java.lang.String r7 = ", tarLang="
            r3.append(r7)     // Catch:{ all -> 0x0053 }
            r3.append(r8)     // Catch:{ all -> 0x0053 }
            java.lang.String r7 = ", taskType="
            r3.append(r7)     // Catch:{ all -> 0x0053 }
            r3.append(r9)     // Catch:{ all -> 0x0053 }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.util.LTTLogger.d(r2, r7)     // Catch:{ all -> 0x0053 }
            boolean r7 = r6.isValidCPs()     // Catch:{ all -> 0x0053 }
            if (r7 != 0) goto L_0x0056
            java.lang.String r7 = r6.TAG     // Catch:{ all -> 0x0053 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0053 }
            r8.<init>(r0)     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.text.KeyFrame r9 = r6.mKeyFrame     // Catch:{ all -> 0x0053 }
            int r9 = r9.getSTRCP()     // Catch:{ all -> 0x0053 }
            r8.append(r9)     // Catch:{ all -> 0x0053 }
            java.lang.String r9 = " TRL_CP="
            r8.append(r9)     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.text.KeyFrame r9 = r6.mKeyFrame     // Catch:{ all -> 0x0053 }
            int r9 = r9.getTRLCP()     // Catch:{ all -> 0x0053 }
            r8.append(r9)     // Catch:{ all -> 0x0053 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.util.LTTLogger.e(r7, r8)     // Catch:{ all -> 0x0053 }
            monitor-exit(r6)
            return
        L_0x0053:
            r0 = move-exception
            r7 = r0
            goto L_0x00b6
        L_0x0056:
            com.samsung.android.livetranslation.task.LiveTranslationTaskManager$STATUS r7 = r6.mStatus     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.task.LiveTranslationTaskManager$STATUS r8 = com.samsung.android.livetranslation.task.LiveTranslationTaskManager.STATUS.CANCELED     // Catch:{ all -> 0x0053 }
            if (r7 != r8) goto L_0x0065
            java.lang.String r7 = r6.TAG     // Catch:{ all -> 0x0053 }
            java.lang.String r8 = "requestTask() is canceled Task.......  return"
            com.samsung.android.livetranslation.util.LTTLogger.e(r7, r8)     // Catch:{ all -> 0x0053 }
            monitor-exit(r6)
            return
        L_0x0065:
            int[] r7 = com.samsung.android.livetranslation.task.LiveTranslationTaskManager.AnonymousClass1.$SwitchMap$com$samsung$android$livetranslation$task$LiveTranslationTask$TASKTYPE     // Catch:{ all -> 0x0053 }
            int r8 = r9.ordinal()     // Catch:{ all -> 0x0053 }
            r7 = r7[r8]     // Catch:{ all -> 0x0053 }
            r8 = 1
            r9 = 0
            if (r7 == r8) goto L_0x0099
            r8 = 2
            if (r7 == r8) goto L_0x0099
            r8 = 3
            if (r7 == r8) goto L_0x0078
            goto L_0x00b4
        L_0x0078:
            com.samsung.android.livetranslation.text.KeyFrame r7 = r6.mKeyFrame     // Catch:{ all -> 0x0053 }
            r7.setTRLReqString(r11)     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.task.PostTRLTask r0 = new com.samsung.android.livetranslation.task.PostTRLTask     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.text.KeyFrame r1 = r6.mKeyFrame     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.task.LiveTranslationTaskManager$TaskStatusListenerImpl r2 = new com.samsung.android.livetranslation.task.LiveTranslationTaskManager$TaskStatusListenerImpl     // Catch:{ all -> 0x0053 }
            r2.<init>()     // Catch:{ all -> 0x0053 }
            android.content.Context r3 = r6.mContext     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.task.LiveTranslationTaskManager$LiveTranslationTaskManagerListener r4 = r6.mLiveTranslationTaskManagerListener     // Catch:{ all -> 0x0053 }
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0053 }
            java.lang.Void[] r7 = new java.lang.Void[r9]     // Catch:{ all -> 0x0053 }
            r0.execute(r7)     // Catch:{ all -> 0x0053 }
            java.util.List<com.samsung.android.livetranslation.task.LiveTranslationTask> r7 = r6.mPostTRLTasks     // Catch:{ all -> 0x0053 }
            r7.add(r0)     // Catch:{ all -> 0x0053 }
            goto L_0x00b4
        L_0x0099:
            com.samsung.android.livetranslation.task.PostSTRTask r0 = new com.samsung.android.livetranslation.task.PostSTRTask     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.text.KeyFrame r1 = r6.mKeyFrame     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.task.LiveTranslationTaskManager$TaskStatusListenerImpl r2 = new com.samsung.android.livetranslation.task.LiveTranslationTaskManager$TaskStatusListenerImpl     // Catch:{ all -> 0x0053 }
            r2.<init>()     // Catch:{ all -> 0x0053 }
            android.content.Context r3 = r6.mContext     // Catch:{ all -> 0x0053 }
            com.samsung.android.livetranslation.task.LiveTranslationTaskManager$LiveTranslationTaskManagerListener r4 = r6.mLiveTranslationTaskManagerListener     // Catch:{ all -> 0x0053 }
            r5 = r10
            r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0053 }
            java.lang.Void[] r7 = new java.lang.Void[r9]     // Catch:{ all -> 0x0053 }
            r0.execute(r7)     // Catch:{ all -> 0x0053 }
            java.util.List<com.samsung.android.livetranslation.task.LiveTranslationTask> r7 = r6.mPostSTRTasks     // Catch:{ all -> 0x0053 }
            r7.add(r0)     // Catch:{ all -> 0x0053 }
        L_0x00b4:
            monitor-exit(r6)
            return
        L_0x00b6:
            monitor-exit(r6)     // Catch:{ all -> 0x0053 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.livetranslation.task.LiveTranslationTaskManager.requestTask(java.lang.String, java.lang.String, com.samsung.android.livetranslation.task.LiveTranslationTask$TASKTYPE, com.samsung.android.imagetranslation.data.LttOcrResult, java.util.List):void");
    }
}
