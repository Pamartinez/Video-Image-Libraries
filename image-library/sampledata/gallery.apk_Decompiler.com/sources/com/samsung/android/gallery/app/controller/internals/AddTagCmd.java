package com.samsung.android.gallery.app.controller.internals;

import O3.C0407a;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AddTagCmd extends BaseCommand {
    private boolean mHasSelectedTags;
    protected MediaItem[] mSelectedItems;
    private long mSelectedNum = 0;

    public void addTag(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            String obj = arrayList.get(0).toString();
            getBlackboard().publish("data://user/selected", this.mSelectedItems);
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.AddTagService");
            intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
            intent.putExtra("blackboard_name", getBlackboard().getName());
            intent.putExtra("tag_name", obj);
            getContext().startService(intent);
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    public Long getAnalyticsValue() {
        return Long.valueOf(this.mSelectedNum);
    }

    public String getEventId() {
        if (this.mHasSelectedTags) {
            return AnalyticsEventId.EVENT_EDIT_TAG.toString();
        }
        return AnalyticsEventId.EVENT_MENU_ADD_TAG.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        if (this.mSelectedItems == null) {
            this.mSelectedItems = eventContext.getSelectedItems();
        }
        this.mSelectedNum = (long) this.mSelectedItems.length;
        ArrayList arrayList = new ArrayList();
        if (Features.isEnabled(Features.SUPPORT_TAG_EDITOR)) {
            if (objArr == null || objArr.length <= 1) {
                str = null;
            } else {
                arrayList = objArr[0];
                str = objArr[1];
                this.mHasSelectedTags = true;
            }
            String build = new UriBuilder("data://user/fromTagEditor").appendArg("selected_tags", String.valueOf(arrayList.hashCode())).appendArg("content_uri", str).build();
            getBlackboard().publish(String.valueOf(arrayList.hashCode()), arrayList);
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(build, null).setOnDataCompleteListener(new C0407a(this, 0)).start();
            return;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/AddTag").build()).setOnDataCompleteListener(new C0407a(this, 1)).start();
    }

    public void onTagEditorResult(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            int i2 = 0;
            ArrayList arrayList2 = (ArrayList) ((Object[]) arrayList.get(0))[0];
            if (arrayList2 != null) {
                i2 = arrayList2.size();
            }
            if (i2 != 0) {
                String str = this.TAG;
                Log.d(str, "selectedTag = " + arrayList2);
                getBlackboard().publish("data://user/selected", this.mSelectedItems);
                Intent intent = new Intent();
                intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.AddTagService");
                intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
                intent.putExtra("blackboard_name", getBlackboard().getName());
                intent.putStringArrayListExtra("selected_tag", arrayList2);
                getContext().startService(intent);
                getBlackboard().postEvent(EventMessage.obtain(1003));
            }
        }
    }
}
