package com.samsung.android.gallery.app.controller.album;

import A4.C0381p;
import A5.a;
import Ad.C0720a;
import Fb.h;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveAutoUpdatedItemsCmd extends BaseCommand {
    private int mAlbumId;
    private int mFlag = 0;
    private ArrayList<Long> mIds;
    private boolean mIsFromViewer = false;

    private ArrayList<Long> getIdStringFromItems(MediaItem[] mediaItemArr) {
        return (ArrayList) Arrays.stream(mediaItemArr).map(new a(12, this)).distinct().collect(Collectors.toCollection(new C0720a(1)));
    }

    private String getTitle() {
        int i2;
        if (!this.mIsFromViewer) {
            int i7 = this.mFlag;
            if ((i7 & 2) != 0 && (i7 & 4) != 0) {
                i2 = R.plurals.remove_n_items_from_album;
            } else if ((i7 & 4) != 0) {
                i2 = R.plurals.remove_n_videos_from_album;
            } else {
                i2 = R.plurals.remove_n_images_from_album;
            }
            return getContext().getResources().getQuantityString(i2, this.mIds.size(), new Object[]{Integer.valueOf(this.mIds.size())});
        } else if ((this.mFlag & 2) != 0) {
            return getContext().getString(R.string.remove_image_from_album);
        } else {
            return getContext().getString(R.string.remove_video_from_album);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Long lambda$getIdStringFromItems$1(MediaItem mediaItem) {
        int i2;
        int i7 = this.mFlag;
        if (mediaItem.isVideo()) {
            i2 = 4;
        } else {
            i2 = 2;
        }
        this.mFlag = i7 | i2;
        return Long.valueOf(mediaItem.getFileId());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$removeAutoUpdatedItems$0(ThreadPool.JobContext jobContext) {
        AutoAlbumHelper.getInstance().removeAutoAlbumContents(this.mIds, (long) this.mAlbumId);
        getBlackboard().postBroadcastEvent(EventMessage.obtain(101));
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            getBlackboard().postBroadcastEvent(EventMessage.obtain(104));
        }
        if (getHandler().isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
        if (!this.mIsFromViewer) {
            return null;
        }
        getBlackboard().postEvent(EventMessage.obtain(3015));
        return null;
    }

    /* access modifiers changed from: private */
    public void removeAutoUpdatedItems(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        if (arrayList == null || arrayList.isEmpty()) {
            i2 = -1;
        } else {
            i2 = ((Integer) C0212a.i(arrayList, 1)).intValue();
        }
        if (i2 == 1 && !this.mIds.isEmpty()) {
            ThreadPool.getInstance().submit(new C0381p(1, this));
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z = false;
        this.mAlbumId = objArr[0].intValue();
        int intValue = objArr[1].intValue();
        if (!AlbumType.isAutoAlbum(intValue)) {
            Log.e(this.TAG, "Stop RemoveAutoUpdatedItemsCmd [" + intValue + "]");
            return;
        }
        String locationKey = getHandler().getLocationKey();
        if (!TextUtils.isEmpty(locationKey) && locationKey.contains("viewer")) {
            z = true;
        }
        this.mIsFromViewer = z;
        this.mIds = getIdStringFromItems(eventContext.getSelectedItems());
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", getTitle()).appendArg("option1", getContext().getString(R.string.remove)).build()).setOnDataCompleteListener(new h(9, this)).start();
    }
}
