package com.samsung.android.gallery.app.controller.album;

import D3.j;
import Fa.C0571z;
import H3.m;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReorderAlbumCmd extends BaseCommand {
    private ArrayList<MediaItem> mAllItems;
    private boolean mIsAlbums;

    private boolean checkNeedToUpdateOrder(EventContext eventContext, int i2) {
        if (this.mAllItems.isEmpty() || (this.mAllItems.get(0).getAlbumOrder() != 0 && ((MediaItem) C0212a.i(this.mAllItems, 1)).getAlbumOrder() != 0 && !containsDefaultOrder() && !containsSameOrder(i2) && this.mIsAlbums)) {
            return false;
        }
        new UpdateOrderCmd().execute(eventContext, new m(this), Integer.valueOf(i2));
        return true;
    }

    private boolean containsDefaultOrder() {
        return this.mAllItems.stream().anyMatch(new C0571z(13));
    }

    private boolean containsSameOrder(int i2) {
        if (this.mAllItems.size() == 1) {
            return false;
        }
        if (i2 == 0) {
            return isSameOrder(i2, i2 + 1);
        }
        if (i2 == this.mAllItems.size() - 1) {
            return isSameOrder(i2 - 1, i2);
        }
        if (isSameOrder(i2 - 1, i2) || isSameOrder(i2, i2 + 1)) {
            return true;
        }
        return false;
    }

    private boolean isSameOrder(int i2, int i7) {
        if (this.mAllItems.get(i2).getAlbumOrder() == this.mAllItems.get(i7).getAlbumOrder()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$containsDefaultOrder$1(MediaItem mediaItem) {
        if (mediaItem.getAlbumOrder() == 1000000000000000007L) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAlbumOrder$0(ArrayList arrayList, long j2, MediaItem mediaItem) {
        String str;
        boolean updateOrder = updateOrder(arrayList, j2);
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("updateAlbumOrder {");
        if (mediaItem.isFolder()) {
            str = "folder";
        } else {
            str = "album";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(j2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(arrayList.size());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(updateOrder);
        sb2.append("} ");
        sb2.append(Logger.getEncodedString(mediaItem.getPath()));
        Log.d(str2, sb2.toString());
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            reloadAlbum();
        }
    }

    /* access modifiers changed from: private */
    public void reloadAlbum() {
        getBlackboard().postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
    }

    private void updateAlbumOrder(EventContext eventContext, MediaItem mediaItem, int i2) {
        if (checkNeedToUpdateOrder(eventContext, i2)) {
            Log.d(this.TAG, "updateAlbumOrder skip");
            return;
        }
        ArrayList arrayList = new ArrayList();
        long albumOrder = mediaItem.getAlbumOrder();
        arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
        ThreadUtil.postOnBgThread(new j((Object) this, (Object) arrayList, albumOrder, (Object) mediaItem, 1));
    }

    private boolean updateOrder(ArrayList<Integer> arrayList, long j2) {
        getBlackboard().publish("local_db_updating", Boolean.TRUE);
        boolean updateAlbumOrder = AlbumHelper.getInstance().updateAlbumOrder(arrayList, j2);
        AlbumHelper.getInstance().clearAlbumOrderForHidden();
        getBlackboard().publish("local_db_updating", Boolean.FALSE);
        return updateAlbumOrder;
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mAllItems = objArr[0];
        int intValue = objArr[1].intValue();
        if (this.mAllItems.size() > intValue) {
            this.mIsAlbums = objArr[2].booleanValue();
            updateAlbumOrder(eventContext, this.mAllItems.get(intValue), intValue);
        }
    }
}
