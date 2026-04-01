package com.samsung.android.gallery.app.controller.story;

import B8.g;
import O3.y;
import Ob.a;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateStoryWithPickCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public void createStory(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && arrayList.size() >= 2) {
            new CreateStoryCmd().execute(eventContext, (String) arrayList.get(0), (MediaItem[]) arrayList.get(1));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: doExecute */
    public void lambda$onExecute$0(EventContext eventContext, boolean z) {
        if (z) {
            Toast.makeText(getContext(), R.string.no_item_create_story_toast, 0).show();
            return;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData("data://user/dialog/StoryName", new UriBuilder("data://user/pick/Item").appendArg("pick-max-item", PickerUtil.getStoryMaxCount()).appendArg("is-pick-for-story-contents", true).build()).setOnDataCompleteListener(new y(29, this)).start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(EventContext eventContext) {
        boolean z;
        if (new MpHelper().getTimelineFileCount() == 0) {
            z = true;
        } else {
            z = false;
        }
        ThreadUtil.postOnUiThread(new g((Object) this, (Object) eventContext, z, 5));
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CREATE_STORY.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ThreadUtil.postOnBgThread(new a(23, this, eventContext));
    }
}
