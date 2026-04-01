package com.samsung.android.gallery.module.remaster;

import M9.o;
import android.app.Activity;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterSaveController {
    private final Activity mActivity;
    private final Blackboard mBlackBoard;
    private final boolean mIsFromSuggestion;

    public RemasterSaveController(Activity activity, Blackboard blackboard, boolean z) {
        this.mActivity = activity;
        this.mBlackBoard = blackboard;
        this.mIsFromSuggestion = z;
    }

    private boolean isLastItem(MediaData mediaData) {
        if (mediaData == null || mediaData.getCount() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postSaveFromOnDemand$0() {
        Activity activity = this.mActivity;
        if (activity == null || !activity.hasWindowFocus()) {
            this.mBlackBoard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        } else {
            BlackboardUtils.publishBackKeyEvent(this.mBlackBoard);
        }
    }

    private void postSaveFromOnDemand() {
        ThreadUtil.postOnUiThreadDelayed(new o(29, this), 100);
    }

    private void postSaveFromSuggestion(MediaData mediaData) {
        BlackboardUtils.forceRefreshPicturesData(this.mBlackBoard, false);
        if (isLastItem(mediaData)) {
            this.mBlackBoard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        } else {
            this.mBlackBoard.postEvent(EventMessage.obtain(3015));
        }
    }

    public void onPostSave(MediaData mediaData) {
        if (this.mIsFromSuggestion) {
            postSaveFromSuggestion(mediaData);
        } else {
            postSaveFromOnDemand();
        }
    }
}
