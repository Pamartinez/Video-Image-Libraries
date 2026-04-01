package com.samsung.android.livetranslation.task;

import android.content.Context;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.livetranslation.task.LiveTranslationTaskManager;
import com.samsung.android.livetranslation.text.KeyFrame;
import com.samsung.android.livetranslation.text.SceneText;
import com.samsung.android.livetranslation.util.LTTLogger;
import com.samsung.android.livetranslation.util.Util;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PostTRLTask extends LiveTranslationTask {
    private final String TAG = "PostTRLTask";
    private final WeakReference<Context> contextHolder;
    private final LiveTranslationTaskManager.LiveTranslationTaskManagerListener liveTranslationTaskManagerListener;
    private final List<String> translatedStr;

    public PostTRLTask(KeyFrame keyFrame, LiveTranslationTask.TaskStatusListener taskStatusListener, Context context, LiveTranslationTaskManager.LiveTranslationTaskManagerListener liveTranslationTaskManagerListener2, List<String> list) {
        super(keyFrame, taskStatusListener);
        this.contextHolder = new WeakReference<>(context);
        this.liveTranslationTaskManagerListener = liveTranslationTaskManagerListener2;
        this.translatedStr = list;
    }

    private void detectAndClearSameTranslatedResult(KeyFrame keyFrame) {
        Iterator<SceneText> it = keyFrame.getSceneTexts().iterator();
        while (it.hasNext()) {
            SceneText next = it.next();
            String str = this.TAG;
            LTTLogger.d(str, "Paragraph type =" + next.getTrlUnit());
            if (!next.isParagraphRendering()) {
                Iterator<SceneText> it2 = next.getComponents().iterator();
                while (it2.hasNext()) {
                    SceneText next2 = it2.next();
                    String str2 = this.TAG;
                    LTTLogger.d(str2, "Line Original =" + next2.getValue());
                    String str3 = this.TAG;
                    LTTLogger.d(str3, "Line Translated =" + next2.getTrsValue());
                    String trsValue = next2.getTrsValue();
                    int length = trsValue.length();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            LTTLogger.d(this.TAG, "Empty mask rendering");
                            next2.setTrsValue(" ");
                            break;
                        }
                        int codePointAt = trsValue.codePointAt(i2);
                        if (Character.isWhitespace(codePointAt)) {
                            i2 += Character.charCount(codePointAt);
                        } else if (Util.removeMoreThanOneSpaceBetweenWords(next2.getValue()).trim().equalsIgnoreCase(Util.removeMoreThanOneSpaceBetweenWords(next2.getTrsValue()).trim())) {
                            LTTLogger.d(this.TAG, "Rendering skipped");
                            next2.setTrsValue("");
                        }
                    }
                }
            } else {
                String str4 = this.TAG;
                LTTLogger.d(str4, "Para Original =" + next.getValue());
                String str5 = this.TAG;
                LTTLogger.d(str5, "Para Translated =" + next.getTrsValue());
                String str6 = this.TAG;
                LTTLogger.d(str6, "Para Distributed =" + next.getSplitTRSLines());
                if (Util.removeMoreThanOneSpaceBetweenWords(next.getValue()).trim().equalsIgnoreCase(Util.removeMoreThanOneSpaceBetweenWords(next.getTrsValue()).trim())) {
                    next.getSplitTRSLines().clear();
                }
            }
        }
    }

    public Boolean doInBackground(Void... voidArr) {
        String str = this.TAG;
        LTTLogger.d(str, "doInBackground:E" + this.translatedStr);
        TRLResultOrganizer organizer = new TRLResultManager(this.contextHolder.get(), this.mKeyFrame, this.translatedStr).getOrganizer();
        this.mKeyFrame.setTRLResultString(this.translatedStr);
        if (!organizer.setTRLResultToKeyFrame()) {
            return Boolean.FALSE;
        }
        SceneTextFormatter sceneTextFormatter = new SceneTextFormatter();
        SceneTextFormatter.setTextScaleX(false);
        detectAndClearSameTranslatedResult(this.mKeyFrame);
        sceneTextFormatter.doSceneTextFormatting(this.mKeyFrame);
        return Boolean.TRUE;
    }

    public void onPostExecute(Boolean bool) {
        if (bool.booleanValue()) {
            LTTLogger.i(this.TAG, "onPostExecute: TRL completed");
            this.mStatusListener.updateStatus(LiveTranslationTaskManager.STATUS.TRL_COMPLETED);
            this.liveTranslationTaskManagerListener.onTRLFinish(true, this.mKeyFrame, LiveTranslationTask.ERRORTYPE.ERR_NO_ERROR);
            return;
        }
        LTTLogger.i(this.TAG, "onPostExecute: TRL Failed");
        this.mStatusListener.updateStatus(LiveTranslationTaskManager.STATUS.TRL_FAIL);
        this.liveTranslationTaskManagerListener.onTRLFinish(false, this.mKeyFrame, LiveTranslationTask.ERRORTYPE.ERR_INVALID_PARSING_DATA);
    }
}
