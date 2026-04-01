package com.samsung.android.gallery.app.controller.internals;

import K4.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RangeDatePickerCmd extends BaseCommand {
    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            Object[] objArr = (Object[]) arrayList.get(0);
            if (DbCompat.storyApi().addHideDateRule(((Long) objArr[0]).longValue(), ((Long) objArr[1]).longValue())) {
                publishHideRuleChanged();
            } else {
                Log.d(this.TAG, "fail to dateHideRule");
            }
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/RangeDatePicker").appendArg("current_date", Calendar.getInstance().getTimeInMillis()).build()).setOnDataCompleteListener(new a(25, this)).start();
    }

    public void publishHideRuleChanged() {
        Blackboard.postGlobal("command://event/DataChanged", EventMessage.obtain(1036, 1, 0, (Object) null));
    }
}
