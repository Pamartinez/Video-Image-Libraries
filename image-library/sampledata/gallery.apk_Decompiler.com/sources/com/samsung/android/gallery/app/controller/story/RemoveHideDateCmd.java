package com.samsung.android.gallery.app.controller.story;

import Ob.a;
import android.util.Log;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.HideRuleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveHideDateCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(Object[] objArr) {
        removeHideDate(objArr[0], objArr[1].intValue());
    }

    private boolean removeHideDate(MediaItem mediaItem, int i2) {
        if (mediaItem == null) {
            return false;
        }
        if (DbCompat.storyApi().removeHideRule(HideRuleData.of(mediaItem.getItemsInFolder()[i2]).hideRuleId)) {
            publishHideRuleChanged();
            return true;
        }
        Log.w(this.TAG, "fail to removeHideDate");
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        SimpleThreadPool.getInstance().execute(new a(24, this, objArr));
    }

    public void publishHideRuleChanged() {
        Blackboard.postGlobal("command://event/DataChanged", EventMessage.obtain(1036, 1, 0, (Object) null));
    }
}
