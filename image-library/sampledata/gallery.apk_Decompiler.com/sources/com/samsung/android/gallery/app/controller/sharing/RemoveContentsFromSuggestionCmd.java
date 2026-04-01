package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import S3.d;
import android.content.Context;
import android.content.res.Resources;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveContentsFromSuggestionCmd extends BaseCommand {
    private MediaItem[] mItems;

    private String getTitle(Context context) {
        int i2;
        MediaItem[] mediaItemArr = this.mItems;
        int length = mediaItemArr.length;
        int count = (int) Stream.of(mediaItemArr).filter(new d(3)).count();
        Resources resources = context.getResources();
        if (count == 0) {
            i2 = R.plurals.remove_n_suggested_images_from_suggestion;
        } else if (count == length) {
            i2 = R.plurals.remove_n_suggested_videos_from_suggestion;
        } else {
            i2 = R.plurals.remove_n_suggested_items_from_suggestion;
        }
        return resources.getQuantityString(i2, length, new Object[]{Integer.valueOf(length)});
    }

    /* access modifiers changed from: private */
    public void removeContents(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && arrayList.size() > 0 && ((Integer) arrayList.get(0)).intValue() == 1) {
            getBlackboard().postEvent(EventMessage.obtain(6018));
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_REMOVE_SUGGESTED_ITEMS.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mItems = objArr[0];
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", getTitle(getContext())).appendArg("option1", getContext().getString(R.string.remove)).appendArg("screenId", getScreenId()).build()).setOnDataCompleteListener(new y(20, this)).start();
    }
}
