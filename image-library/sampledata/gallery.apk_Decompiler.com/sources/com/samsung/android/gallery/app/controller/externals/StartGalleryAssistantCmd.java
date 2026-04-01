package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ShareData;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartGalleryAssistantCmd extends BaseCommand {
    private MediaItem[] mItems;
    private long mSelectedNum = 0;

    private Intent getGalleryAssistantIntent() {
        Intent intent = new Intent("com.samsung.android.gallery.assistant.app.action.ACTION_LAUNCH_ASSISTANT_GALLERY");
        intent.putExtra("selected_number", this.mItems.length);
        return intent;
    }

    private ArrayList<Uri> getUriList() {
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : this.mItems) {
            if (MediaItemUtil.isSharableItem(mediaItem, true)) {
                arrayList.add(ContentUri.getUri(mediaItem));
            }
        }
        return arrayList;
    }

    private void startGalleryAssistant() {
        try {
            Activity activity = getActivity();
            Intent galleryAssistantIntent = getGalleryAssistantIntent();
            ArrayList<Uri> uriList = getUriList();
            galleryAssistantIntent.putExtra("selectedItems", uriList);
            this.mSelectedNum = (long) uriList.size();
            activity.startActivity(galleryAssistantIntent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("startGalleryAssistant failed e="), this.TAG);
        }
    }

    private void startUnlimitedGalleryAssistant() {
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : this.mItems) {
            if (MediaItemUtil.isSharableItem(mediaItem, true)) {
                arrayList.add(new ShareData(ContentUri.getUriString(mediaItem), mediaItem.getBucketID(), FileUtils.getBucketNameFromPath(mediaItem.getReferencePath())));
            }
        }
        Log.d(this.TAG, "startUnlimitedGalleryAssistant", Integer.valueOf(arrayList.size()));
        ShareList.setShareList(arrayList);
        this.mSelectedNum = (long) ShareList.getShareList().size();
        try {
            getActivity().startActivity(getGalleryAssistantIntent());
        } catch (Exception e) {
            a.s(e, new StringBuilder("startGalleryAssistant failed e="), this.TAG);
        }
    }

    public Long getAnalyticsValue() {
        long j2 = this.mSelectedNum;
        if (j2 > 0) {
            return Long.valueOf(j2);
        }
        return null;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_EDIT_WITH_GALLERY_ASSISTANT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        this.mItems = mediaItemArr;
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "invalid items");
            return;
        }
        getBlackboard().postEvent(EventMessage.obtain(1003));
        if (this.mItems.length > 500) {
            startUnlimitedGalleryAssistant();
        } else {
            startGalleryAssistant();
        }
    }
}
