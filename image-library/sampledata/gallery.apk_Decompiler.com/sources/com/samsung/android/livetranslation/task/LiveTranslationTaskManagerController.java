package com.samsung.android.livetranslation.task;

import android.content.Context;
import android.graphics.Rect;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.livetranslation.task.LiveTranslationTaskManager;
import com.samsung.android.livetranslation.text.KeyFrame;
import com.samsung.android.livetranslation.util.LTTLogger;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveTranslationTaskManagerController {
    private static final String TAG = "LiveTranslationTaskManagerController";
    private boolean mIsPreviewMode;
    private String mLaunchModeName;
    private ArrayList<LiveTranslationTaskManager> mLiveTranslationTaskManagers;

    public LiveTranslationTaskManagerController(boolean z) {
        this.mIsPreviewMode = z;
        init();
    }

    public int getManagerSize() {
        return this.mLiveTranslationTaskManagers.size();
    }

    public void init() {
        this.mLiveTranslationTaskManagers = new ArrayList<>();
    }

    public synchronized void release() {
        try {
            LTTLogger.d(TAG, "release()");
            if (this.mLiveTranslationTaskManagers != null) {
                for (int i2 = 0; i2 < this.mLiveTranslationTaskManagers.size(); i2++) {
                    this.mLiveTranslationTaskManagers.get(i2).cancel();
                }
                this.mLiveTranslationTaskManagers.clear();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void requestNewTask(Context context, KeyFrame keyFrame, Rect rect, LiveTranslationTask.TASKTYPE tasktype, LiveTranslationTaskManager.LiveTranslationTaskManagerListener liveTranslationTaskManagerListener, LttOcrResult lttOcrResult) {
        KeyFrame keyFrame2 = keyFrame;
        this.mLiveTranslationTaskManagers.add(new LiveTranslationTaskManager(context, keyFrame2, rect, liveTranslationTaskManagerListener, this.mLaunchModeName, this.mIsPreviewMode));
        ((LiveTranslationTaskManager) C0212a.i(this.mLiveTranslationTaskManagers, 1)).requestTask(keyFrame2.getSrcLang(), keyFrame2.getTarLang(), tasktype, lttOcrResult);
    }

    public synchronized void requestSuccessiveTRL(String str, String str2, List<String> list) {
        int size = this.mLiveTranslationTaskManagers.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            LiveTranslationTaskManager liveTranslationTaskManager = this.mLiveTranslationTaskManagers.get(size);
            LTTLogger.d(TAG, "onSTRFinish : idx=" + size + "Status=" + liveTranslationTaskManager.getStatus());
            if (liveTranslationTaskManager.getStatus() == LiveTranslationTaskManager.STATUS.STR_COMPLETED) {
                liveTranslationTaskManager.requestTaskPostTRL(str, str2, list);
                break;
            }
            size--;
        }
    }

    public void requestTask(int i2, String str, String str2, LiveTranslationTask.TASKTYPE tasktype) {
        this.mLiveTranslationTaskManagers.get(i2).requestTask(str, str2, tasktype, (LttOcrResult) null);
    }
}
