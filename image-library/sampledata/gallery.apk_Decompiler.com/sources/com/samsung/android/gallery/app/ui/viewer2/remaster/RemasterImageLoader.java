package com.samsung.android.gallery.app.ui.viewer2.remaster;

import B7.d;
import Ba.h;
import D7.b;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.SubscriberListenerInfo;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.ImageLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterImageLoader extends ImageLoader {
    private final HashMap<String, SubscriberListenerInfo> mImageSubscriberMap = new HashMap<>();

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clearSubscriber$1(String str, SubscriberListenerInfo subscriberListenerInfo) {
        unsubscribe(subscriberListenerInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$subscribe$0(MediaItem mediaItem, int i2, Object obj, Bundle bundle) {
        lambda$subscribe$2(mediaItem, i2, obj, bundle);
    }

    /* access modifiers changed from: private */
    public void onRemastered(Object... objArr) {
        requestDecode(this.mModel.getRemasteredMediaItem(), this.mModel.getPosition());
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.ON_DEMAND_REMASTERED, new d(8, this));
    }

    public void clearSubscriber() {
        this.mImageSubscriberMap.forEach(new h(9, this));
        this.mImageSubscriberMap.clear();
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what == 3037 && eventMessage.obj != null) {
            eraseDecodedBitmap(this.mModel.getOriginMediaItem(), this.mModel.getPosition(), true);
            requestDecode((MediaItem) eventMessage.obj, this.mModel.getPosition());
        }
        return false;
    }

    public boolean isValidItem(MediaItem mediaItem) {
        if (super.isValidItem(mediaItem) || MediaItemUtil.equals(this.mModel.getOriginMediaItem(), mediaItem) || MediaItemUtil.equals(this.mModel.getRemasteredMediaItem(), mediaItem)) {
            return true;
        }
        return false;
    }

    public boolean needsLastRequestedItem() {
        return false;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        if (LocationKey.isRemasterPictures(this.mModel.getContainerModel().getLocationKey())) {
            eraseDecodedBitmap(this.mModel.getOriginMediaItem(), this.mModel.getPosition(), true);
        }
        eraseDecodedBitmap(this.mModel.getRemasteredMediaItem(), this.mModel.getPosition(), true);
        clearSubscriber();
    }

    public void requestBitmap(String str) {
        super.requestBitmap(str);
        if (LocationKey.isRemasterPictures(this.mModel.getContainerModel().getLocationKey())) {
            requestDecode(this.mModel.getOriginMediaItem(), this.mModel.getPosition());
        }
    }

    public void subscribe(String str, MediaItem mediaItem, int i2) {
        this.mImageSubscriberMap.put(mediaItem.getPath(), subscribe(str, new b(this, mediaItem, i2, 1)));
    }

    public void unsubscribe(MediaItem mediaItem) {
        SubscriberListenerInfo subscriberListenerInfo;
        if (mediaItem != null && (subscriberListenerInfo = this.mImageSubscriberMap.get(mediaItem.getPath())) != null) {
            unsubscribe(subscriberListenerInfo);
        }
    }

    public void updateFullSizeBitmap() {
    }
}
