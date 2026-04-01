package com.samsung.android.gallery.app.controller.internals;

import K5.a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.quicksearch.QuickSearchManager;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickSearchRangeDatePickerCmd extends RangeDatePickerCmd {
    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onConfirmed$0(Object[] objArr, QuickSearchManager quickSearchManager) {
        quickSearchManager.setDateFiltered(true);
        quickSearchManager.setStartDate(objArr[0].longValue());
        quickSearchManager.setEndDate(objArr[1].longValue());
    }

    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            Optional.of(QuickSearchManager.getInstance(getBlackboard())).ifPresent(new a(20, (Object[]) arrayList.get(0)));
            getBlackboard().postEvent(EventMessage.obtain(1130));
        }
    }
}
