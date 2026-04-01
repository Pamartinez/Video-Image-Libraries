package com.samsung.android.gallery.app.controller.sharing;

import A4.C0387w;
import E3.e;
import Fa.C0571z;
import M4.j;
import Ob.a;
import S3.b;
import S3.c;
import S3.d;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseSelectedCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.SimpleEventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChooseSharedAlbumCmd extends BaseSelectedCommand {
    private String mGroupId;
    private MediaItem[] mSelectedItems;
    private String mSpaceId;

    /* access modifiers changed from: private */
    public void addContentsToSharedAlbum(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = 0;
        }
        if (i2 == 0 || !(arrayList.get(0) instanceof Object[])) {
            getBlackboard().erase("data://user/selected");
            return;
        }
        Object[] objArr = (Object[]) arrayList.get(0);
        this.mSpaceId = (String) objArr[0];
        this.mGroupId = (String) objArr[1];
        long count = Arrays.stream(this.mSelectedItems).filter(new C0571z(5)).count();
        if (count <= 0 || !CloudStateCompat.isEnabled()) {
            requestAddContents();
            return;
        }
        ThreadPool.getInstance().submit(new b(this, count, Arrays.stream(this.mSelectedItems).filter(new C0571z(5)).filter(new d(0)).count(), 1));
    }

    private MediaItem[] checkMediaItem(MediaItem[] mediaItemArr) {
        int i2;
        int length = mediaItemArr.length;
        MediaItem[] mediaItemArr2 = (MediaItem[]) ((Stream) Arrays.stream(mediaItemArr).filter(new j(29)).sequential()).toArray(new C0387w(29));
        if (length != mediaItemArr2.length) {
            if (mediaItemArr2.length == 0) {
                i2 = R.string.unsupported_file;
            } else {
                i2 = R.string.unsupported_file_deselected;
            }
            showToast(i2);
        }
        return mediaItemArr2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addContentsToSharedAlbum$6(long j2, long j3, ThreadPool.JobContext jobContext) {
        showAddCloudItemDialog(j2, j3);
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$checkMediaItem$2(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isBroken()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$checkMediaItem$3(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(MediaItem[] mediaItemArr, long j2, EventContext eventContext, Integer num) {
        String str = this.TAG;
        Log.d(str, "onPreExecute#PppChecker" + Logger.vt(Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)));
        if (num.intValue() > 0) {
            mediaItemArr = replacePppItem(eventContext, mediaItemArr);
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$1(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (GppmHelper.SUPPORT_DONATION_MULTIPLE) {
            EventContext eventContext2 = eventContext;
            MediaItem[] mediaItemArr2 = mediaItemArr;
            executePppChecker(eventContext2, mediaItemArr2, new e(this, mediaItemArr2, System.currentTimeMillis(), eventContext2, 6));
            return;
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSharingAlbumDialogAfterLoadingItem$4(Object obj) {
        ArrayList<MediaItem> arrayList;
        Uri[] uriArr = (Uri[]) obj;
        if (uriArr[0] == null || !MediaUri.getInstance().matches(uriArr[0].toString())) {
            arrayList = UriItemLoader.getSharingItemsFromFileUris(getContext(), uriArr);
        } else {
            arrayList = UriItemLoader.getItemsFromMediaUris(uriArr);
        }
        if (!arrayList.isEmpty()) {
            this.mSelectedItems = (MediaItem[]) arrayList.toArray(new MediaItem[0]);
            ThreadUtil.postOnUiThread(new Qb.e(7, this));
        }
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
            getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
            getBlackboard().erase("data://user/selected");
            return;
        }
        requestAddContents();
    }

    private void requestAddContents() {
        EventContext handler = getHandler();
        if (handler.getContext() == null) {
            handler = new SimpleEventContext(getBlackboard());
        }
        new RequestSharedAlbumCmd().execute(handler, RequestCmdType.REQUEST_ADD_CONTENTS, this.mSpaceId, this.mGroupId, this.mSelectedItems);
        getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        if (getHandler().isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
        getBlackboard().erase("data://user/selected");
    }

    private void showAddCloudItemDialog(long j2, long j3) {
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/AddCloudItemToSharedAlbum").appendArg("cloudItemCount", j2).appendArg("imageCount", j3).appendArg("dismissAsCancel", true).build()).setOnDataCompleteListener(new c(this, 1)).start();
    }

    /* access modifiers changed from: private */
    public void showSharingAlbumChoice(Object obj) {
        if (obj instanceof Uri[]) {
            showSharingAlbumDialogAfterLoadingItem(obj);
            return;
        }
        this.mSelectedItems = (MediaItem[]) obj;
        int maxUploadCount = MdeSharingHelper.getMaxUploadCount(getContext());
        if (this.mSelectedItems.length > maxUploadCount) {
            showToast(getContext().getResources().getQuantityString(R.plurals.cant_add_more_than_n_items_at_once, maxUploadCount, new Object[]{Integer.valueOf(maxUploadCount)}));
        } else {
            showSharingAlbumDialog();
        }
    }

    /* access modifiers changed from: private */
    public void showSharingAlbumDialog() {
        MediaItem[] checkMediaItem = checkMediaItem(this.mSelectedItems);
        this.mSelectedItems = checkMediaItem;
        if (checkMediaItem == null || checkMediaItem.length == 0) {
            Log.she(this.TAG, "There is no items to add.");
            return;
        }
        getBlackboard().publish("data://user/selected", this.mSelectedItems);
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/move/SharingAlbumChoice").build()).setOnDataCompleteListener(new c(this, 0)).start();
    }

    private void showSharingAlbumDialogAfterLoadingItem(Object obj) {
        ThreadUtil.postOnBgThread(new a(10, this, obj));
    }

    public Long getAnalyticsValue() {
        long j2;
        if (getHandler() != null) {
            j2 = (long) getHandler().getSelectedItems().length;
        } else {
            j2 = 0;
        }
        return Long.valueOf(j2);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_ADD_TO_SHARED_ALBUM.toString();
    }

    public void onExecute(EventContext eventContext, final Object... objArr) {
        MdeSharingService.getInstance().connectSessionAsync(2, new ConnectListener() {
            public void onFailure(int i2) {
                Log.she(ChooseSharedAlbumCmd.this.TAG, "connectSessionAsync onFailure but keep going.");
                ChooseSharedAlbumCmd.this.showSharingAlbumChoice(objArr[0]);
            }

            public void onSuccess() {
                ChooseSharedAlbumCmd.this.showSharingAlbumChoice(objArr[0]);
            }
        });
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            Log.d(this.TAG, "onPreExecute", Integer.valueOf(eventContext.getSelectedItemCount()), Boolean.valueOf(eventContext.isSelectionMode()));
            if (eventContext.isSelectionMode()) {
                loadAndExecute(eventContext, new N3.c(17, this, eventContext));
            } else {
                super.onPreExecute(eventContext, eventContext.getSelectedItems());
            }
        } else {
            super.onPreExecute(eventContext, objArr);
        }
    }
}
