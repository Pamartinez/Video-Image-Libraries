package com.samsung.android.gallery.app.controller.sharing;

import Fa.C0571z;
import M4.j;
import S3.C0409a;
import S3.b;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddContentsToSharedAlbumCmd extends BaseCommand {
    private String mGroupId;
    private MediaItem[] mSelectedItems;
    private String mSpaceId;

    /* access modifiers changed from: private */
    public void addToSharedAlbum(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = 0;
        }
        if (i2 != 0) {
            MediaItem[] mediaItemArr = (MediaItem[]) arrayList.get(0);
            this.mSelectedItems = mediaItemArr;
            if (mediaItemArr == null || mediaItemArr.length == 0) {
                Log.she(this.TAG, "There are no items to add.");
                return;
            }
            long count = Arrays.stream(mediaItemArr).filter(new C0571z(5)).count();
            if (count <= 0 || !CloudStateCompat.isEnabled()) {
                requestAddContents();
                return;
            }
            ThreadPool.getInstance().submit(new b(this, count, Arrays.stream(this.mSelectedItems).filter(new C0571z(5)).filter(new j(28)).count(), 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addToSharedAlbum$1(long j2, long j3, ThreadPool.JobContext jobContext) {
        showAddCloudItemDialog(j2, j3);
        return null;
    }

    /* access modifiers changed from: private */
    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = 0;
        }
        if (i2 == 0) {
            Log.sh(this.TAG, "Cancel operation");
        } else {
            requestAddContents();
        }
    }

    private void requestAddContents() {
        new RequestSharedAlbumCmd().execute(getHandler(), RequestCmdType.REQUEST_ADD_CONTENTS, this.mSpaceId, this.mGroupId, this.mSelectedItems);
    }

    private void showAddCloudItemDialog(long j2, long j3) {
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/AddCloudItemToSharedAlbum").appendArg("cloudItemCount", j2).appendArg("imageCount", j3).build()).setOnDataCompleteListener(new C0409a(this, 1)).start();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_SHARING_ADD_ITEM.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem headerItem = eventContext.getHeaderItem();
        this.mSpaceId = MediaItemMde.getSpaceId(headerItem);
        this.mGroupId = MediaItemMde.getGroupId(headerItem);
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/pick/Item").appendArg("is-pick-to-add-shared-album", true).appendArg("is_pick-limited-file-size", true).appendArg("pick-limited-file-size", (long) ErrorCodeConvertor.ERROR_NOT_ALLOWED_CALLER).appendArg("pick-max-item", MdeSharingHelper.getMaxUploadCount(getContext())).build()).setOnDataCompleteListener(new C0409a(this, 0)).start();
    }
}
