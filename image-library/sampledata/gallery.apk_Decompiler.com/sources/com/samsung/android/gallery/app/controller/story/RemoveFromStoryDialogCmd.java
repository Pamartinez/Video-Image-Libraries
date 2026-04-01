package com.samsung.android.gallery.app.controller.story;

import U3.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveFromStoryDialogCmd extends BaseCommand {
    Object[] mParams;

    private String getDescription() {
        int selectedItemCount = getHandler().getSelectedItemCount();
        return AppResources.getQuantityString(R.plurals.remove_n_items_from_story, selectedItemCount, Integer.valueOf(selectedItemCount));
    }

    public Long getAnalyticsValue() {
        MediaItem[] selectedItems;
        EventContext handler = getHandler();
        if (handler == null || (selectedItems = handler.getSelectedItems()) == null) {
            return 0L;
        }
        return Long.valueOf((long) selectedItems.length);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_LIST_REMOVE_DONE.toString();
    }

    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        boolean z = false;
        if (arrayList == null || arrayList.isEmpty()) {
            i2 = 0;
        } else {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("onConfirmed=");
        if (i2 == 1) {
            z = true;
        }
        sb2.append(z);
        Log.d(str, sb2.toString());
        if (i2 == 1) {
            new RemoveFromStoryCmd().execute(eventContext, this.mParams);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mParams = objArr;
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("type", 0).appendArg("description", getDescription()).appendArg("option1", AppResources.getString(R.string.cancel)).appendArg("option1", AppResources.getString(R.string.remove)).appendArg("screenId", getScreenId()).build()).setOnDataCompleteListener(new a(3, this)).start();
    }
}
