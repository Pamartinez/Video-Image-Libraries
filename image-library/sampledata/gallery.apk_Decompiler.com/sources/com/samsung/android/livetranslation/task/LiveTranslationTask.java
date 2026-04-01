package com.samsung.android.livetranslation.task;

import android.os.AsyncTask;
import com.samsung.android.livetranslation.task.LiveTranslationTaskManager;
import com.samsung.android.livetranslation.text.KeyFrame;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LiveTranslationTask extends AsyncTask<Void, Void, Boolean> {
    KeyFrame mKeyFrame;
    TaskStatusListener mStatusListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ERRORTYPE {
        public static final int ERR_INVALID_PARSING_DATA = 3003;
        public static final int ERR_NO_ERROR = 3005;
        public static final int ERR_SAME_LANGUAGE = 3004;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TASKTYPE {
        ENTIRE,
        POST_STR,
        POST_TRL
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TaskStatusListener {
        void updateStatus(LiveTranslationTaskManager.STATUS status);
    }

    public LiveTranslationTask(KeyFrame keyFrame, TaskStatusListener taskStatusListener) {
        this.mKeyFrame = keyFrame;
        this.mStatusListener = taskStatusListener;
    }

    public abstract Boolean doInBackground(Void... voidArr);

    public abstract void onPostExecute(Boolean bool);

    public void cancel() {
    }
}
