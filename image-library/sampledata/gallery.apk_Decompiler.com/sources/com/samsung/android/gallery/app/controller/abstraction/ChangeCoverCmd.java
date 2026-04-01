package com.samsung.android.gallery.app.controller.abstraction;

import A9.b;
import Da.f;
import G3.a;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ChangeCoverCmd extends BaseCommand {
    protected MediaItem mMediaItem;

    /* access modifiers changed from: private */
    public void changeCoverFromPickerForCrop(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            Log.e(this.TAG, "changeCoverFromPickerForCrop invalid data");
        } else if (arrayList.get(0) instanceof MediaItem) {
            changeCover((MediaItem) arrayList.get(0));
        } else if (!(arrayList.get(0) instanceof Object[])) {
            Log.e(this.TAG, "changeCoverFromPickerForCrop invalid data");
        } else {
            Object[] objArr = (Object[]) arrayList.get(0);
            changeCover((MediaItem) objArr[0], (Intent) objArr[1]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeCover$0(MediaItem mediaItem) {
        lambda$changeCover$1(mediaItem, "0,0,0,0");
    }

    private void publishCropData() {
        getBlackboard().publish(DataKey.DATA("location://cropView"), this.mMediaItem);
    }

    public void changeCover(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThreadUtil.postOnBgThread(new f(26, this, mediaItem));
        }
    }

    /* renamed from: changeCover */
    public abstract void lambda$changeCover$1(MediaItem mediaItem, String str);

    public void changeCoverFromCropper(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            Log.e(this.TAG, "changeCoverFromCropper invalid data");
            return;
        }
        changeCover(this.mMediaItem, (Intent) arrayList.get(0));
    }

    public void launchCropper(String str) {
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(str).setOnDataCompleteListener(new a(this, 1)).start();
    }

    public void launchPickerForCrop(String str) {
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(str).setOnDataCompleteListener(new a(this, 0)).start();
    }

    public UriBuilder makeCommonDataByCrop(int i2, int i7) {
        publishCropData();
        return new UriBuilder("data://user/Crop").appendArg("aspectX", i2).appendArg("aspectY", i7).appendArg("pick-from-gallery", true).appendArg("is-get-rect-result", true);
    }

    public UriBuilder makeCommonDataByPickAndCrop() {
        publishCropData();
        return new UriBuilder("data://user/pick/SingleItem").appendArg("crop", true).appendArg("is-get-rect-result", true);
    }

    private void changeCover(MediaItem mediaItem, Intent intent) {
        if (mediaItem == null || intent == null || intent.getExtras() == null) {
            Log.e(this.TAG, "changeCoverFromCropper invalid data");
            return;
        }
        String string = intent.getExtras().getString("key-get-rect-result");
        if (!TextUtils.isEmpty(string)) {
            ThreadUtil.postOnBgThread(new b(this, mediaItem, string, 19));
        } else {
            Log.w(this.TAG, "changeCoverFromCropper result is null/empty");
        }
        getBlackboard().erase("data://user/Crop/ReqInfo");
    }
}
