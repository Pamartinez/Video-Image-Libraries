package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import a6.g;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import bc.d;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.AbsEditorTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.GroupItemUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoEditorTransitionHandler extends AbsEditorTransitionHandler {
    private static final Object BITMAP_LOCK = new Object();
    MediaItem mEditedItem;
    private final IPhotoEditorBehavior mPhotoEditorBehavior;
    private BroadcastReceiver mPhotoEditorFallbackReceiver;
    String mPreloadedBitmapKey;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FallbackBroadcastReceiver extends BroadcastReceiver {
        final ActionInvoker mActionInvoker;

        public FallbackBroadcastReceiver(ActionInvoker actionInvoker) {
            this.mActionInvoker = actionInvoker;
        }

        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                Log.at("FallbackBroadcastReceiver", "Fail to receive " + intent);
                return;
            }
            String action = intent.getAction();
            Log.at("FallbackBroadcastReceiver", "Received BR from PE with action " + action);
            this.mActionInvoker.invoke(ViewerAction.PHOTO_EDITOR_FALLBACK, intent);
        }
    }

    public PhotoEditorTransitionHandler(IVuContainerView iVuContainerView, ActionInvoker actionInvoker) {
        super(iVuContainerView, actionInvoker);
        IPhotoEditorBehavior iPhotoEditorBehavior;
        if (AbsEditorTransitionHandler.SUPPORT_TRANSITION) {
            iPhotoEditorBehavior = new PhotoEditorTransitionBehavior(this.mInfo);
        } else {
            iPhotoEditorBehavior = new PhotoEditorServiceBehavior(this.mInfo);
        }
        this.mPhotoEditorBehavior = iPhotoEditorBehavior;
    }

    private IntentFilter getIntentFilterForPhotoEditingApp() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.sec.android.mimage.photoretouching.action.saved");
        intentFilter.addAction("com.sec.android.mimage.photoretouching.action.canceled");
        return intentFilter;
    }

    private void handleItemEditedEvent(Object obj) {
        String str;
        MediaItem currentItem = getCurrentItem();
        if (currentItem == null || !currentItem.isSharing()) {
            AbsEditorTransitionHandler.Index index = AbsEditorTransitionHandler.Index.handleItemEditedEvent;
            if (obj != null) {
                str = obj.toString();
            } else {
                str = "";
            }
            log(index, str);
            this.mPhotoEditorBehavior.setTransitionReentered(true);
            if (obj != null) {
                Bundle bundle = (Bundle) obj;
                this.mPhotoEditorBehavior.cancelReturnTransition(bundle.getBoolean("no_return_transition"));
                Uri uri = (Uri) bundle.get("saved_uri");
                String string = bundle.getString("output");
                if (uri != null) {
                    waitNewMediaItemLoaded(uri, string);
                } else {
                    startReturningAppTransition(MOCRLang.KHMER);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEventHandlerListener$0(Object[] objArr) {
        onFallBack(objArr[0]);
    }

    private void onFallBack(Intent intent) {
        String action = intent.getAction();
        log(AbsEditorTransitionHandler.Index.OnFallback, action);
        if ("com.sec.android.mimage.photoretouching.action.canceled".equals(action)) {
            startReturningAppTransition(MOCRLang.KHMER);
        } else if ("com.sec.android.mimage.photoretouching.action.saved".equals(action)) {
            handleItemEditedEvent(intent.getExtras());
        }
    }

    /* access modifiers changed from: private */
    public void onHidePhotoEditor(boolean z) {
        Log.at(this.TAG, "onHidePhotoEditor");
        if (z) {
            startReturningAppTransition(0);
        } else {
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.TRUE);
        }
    }

    private void preloadOriginalImage(MediaItem mediaItem) {
        synchronized (BITMAP_LOCK) {
            try {
                String str = this.mPreloadedBitmapKey;
                if (str != null) {
                    this.mBlackboard.erase(str);
                }
                long currentTimeMillis = System.currentTimeMillis();
                this.mPreloadedBitmapKey = MediaItemUtil.getViewerBitmapKey(mediaItem);
                Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.parse((FileItemInterface) mediaItem, true).adjustInSampleSize(BitmapSizeHolder.size()));
                this.mBlackboard.publish(this.mPreloadedBitmapKey, decodedBitmap);
                String str2 = this.TAG;
                Log.at(str2, "preloadOriginalImage " + mediaItem.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.toString(decodedBitmap) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mPreloadedBitmapKey + " +" + (System.currentTimeMillis() - currentTimeMillis));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: prepareReturnData */
    public void lambda$prepareReturnTransition$1(MediaItem mediaItem, MediaItem mediaItem2) {
        AbsEditorTransitionHandler.Index index = AbsEditorTransitionHandler.Index.prepareReturnData;
        log(index, "\nOLD=" + MediaItemUtil.getDebugLog(mediaItem) + "\nNEW=" + MediaItemUtil.getDebugLog(mediaItem2));
        long currentTimeMillis = System.currentTimeMillis();
        Bitmap loadThumbnailSync = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem2, ThumbKind.MEDIUM_KIND);
        String str = this.TAG;
        Log.at(str, "preloadThumbnail " + mediaItem2.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.toString(loadThumbnailSync) + " +" + (System.currentTimeMillis() - currentTimeMillis));
        this.mEditedItem = mediaItem2;
        preloadOriginalImage(mediaItem2);
        reloadData();
    }

    private void saveBitmapToSharedMemory() {
        Bitmap bitmap;
        ViewerObjectComposite currentViewer = this.mContainer.getCurrentViewer();
        if (currentViewer != null) {
            bitmap = currentViewer.getModel().getBitmap();
        } else {
            bitmap = null;
        }
        if (bitmap != null && bitmap.getHeight() > 0 && bitmap.getWidth() > 0) {
            ShareList.setSharedMemory(BitmapUtils.getJpegFromBitmap(bitmap, 1920));
            Log.at(this.TAG, "saveBitmapToSharedMemory");
        }
    }

    private void startHide(int i2) {
        AbsEditorTransitionHandler.Index index = AbsEditorTransitionHandler.Index.startHide;
        log(index, "#" + i2);
        verifyPeHide(i2);
        ((ContainerModel) this.mContainer.getModel()).setReservedPosition(-1);
        this.mPhotoEditorBehavior.hidePhotoEditor();
    }

    private void verifyPeHide(int i2) {
        ViewerObjectComposite currentViewer = this.mContainer.getCurrentViewer();
        if (currentViewer == null || currentViewer.getModel().getBitmap() == null) {
            String str = this.TAG;
            Log.atw(str, "verifyPeHide #" + i2 + " failed. bitmap not ready. " + currentViewer);
            return;
        }
        MediaItem read = ((ContainerModel) this.mContainer.getModel()).getMediaData().read(i2);
        if (!MediaItemUtil.equalsSimple(this.mEditedItem, read) && !GroupItemUtil.isSameGroupContent(this.mEditedItem, read)) {
            String str2 = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "verifyPeHide #", " failed. not match. ");
            o2.append(this.mEditedItem);
            o2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            o2.append(read);
            Log.atw(str2, o2.toString());
        }
    }

    public void clearAppTransition() {
        super.clearAppTransition();
        this.mPhotoEditorBehavior.setTransitionReentered(true);
    }

    public void handleSharingItemEditedEvent(EventMessage eventMessage) {
        this.mPhotoEditorBehavior.setTransitionReentered(true);
        super.handleSharingItemEditedEvent(eventMessage);
    }

    public void log(AbsEditorTransitionHandler.Index index, String str) {
        String str2 = this.TAG;
        Log.at(str2, "i#" + index.id + " " + index.name() + " " + str);
    }

    public void onDestroy() {
        super.onDestroy();
        synchronized (BITMAP_LOCK) {
            try {
                String str = this.mPreloadedBitmapKey;
                if (str != null) {
                    Object read = this.mBlackboard.read(str);
                    if (read != null && ObjectDictionary.getRefCounter(read) == 0) {
                        this.mBlackboard.erase(this.mPreloadedBitmapKey);
                    }
                    this.mPreloadedBitmapKey = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onHandleEvent(EventMessage eventMessage) {
        super.onHandleEvent(eventMessage);
        int i2 = eventMessage.what;
        if (i2 == 1103) {
            String str = this.TAG;
            Log.at(str, "onHandleEvent" + Logger.v(Integer.valueOf(eventMessage.what), "EVENT_ITEM_EDITED_FROM_PHOTO_EDITOR"));
            Object obj = eventMessage.obj;
            if (obj != null) {
                handleItemEditedEvent(obj);
            }
        } else if (i2 == 10002) {
            String str2 = this.TAG;
            Log.at(str2, "onHandleEvent" + Logger.v(Integer.valueOf(eventMessage.what), "EVENT_APP_TRANSITION_PHOTO_EDITOR"));
            onFallBack((Intent) eventMessage.obj);
        }
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        super.onPageInvalidate(i2, viewerObjectComposite);
        startHide(i2);
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        super.onPageSelected(i2, viewerObjectComposite);
        startHide(i2);
    }

    public void onReenterTransitionEnd() {
        super.onReenterTransitionEnd();
        this.mActionInvoker.invoke(ViewerAction.PHOTO_EDITOR_REENTER_TRANSITION_END, new Object[0]);
    }

    public void prepareEditor(MediaItem mediaItem) {
        super.prepareEditor(mediaItem);
        registerPhotoEditorFallbackReceiver(this.mContainer.getContext());
        this.mPhotoEditorBehavior.setTransitionReentered(false);
        this.mPhotoEditorBehavior.preparePhotoEditor(this.mContainer.getContext(), new g(18, this));
        if (AbsEditorTransitionHandler.SUPPORT_TRANSITION && mediaItem.isHeif()) {
            saveBitmapToSharedMemory();
        }
    }

    public void prepareReturnTransition(long j2, MediaItem mediaItem, MediaItem mediaItem2) {
        super.lambda$waitNewMediaItemLoaded$3(j2, mediaItem, mediaItem2);
        ((ContainerModel) this.mContainer.getModel()).setReservedPosition(j2);
        if (ThreadUtil.isMainThread()) {
            SimpleThreadPool.getInstance().execute(new d((Object) this, (Object) mediaItem, (Object) mediaItem2, 6));
        } else {
            lambda$prepareReturnTransition$1(mediaItem, mediaItem2);
        }
    }

    public void registerPhotoEditorFallbackReceiver(Context context) {
        if (context != null && !Features.isEnabled(Features.IS_UPSM) && !SdkConfig.atLeast(SdkConfig.SEM.U) && this.mPhotoEditorFallbackReceiver == null) {
            FallbackBroadcastReceiver fallbackBroadcastReceiver = new FallbackBroadcastReceiver(this.mActionInvoker);
            this.mPhotoEditorFallbackReceiver = fallbackBroadcastReceiver;
            AndroidCompat.registerReceiver(context, fallbackBroadcastReceiver, getIntentFilterForPhotoEditingApp());
        }
    }

    public void setEventHandlerListener() {
        super.setEventHandlerListener();
        this.mActionInvoker.add(ViewerAction.PHOTO_EDITOR_FALLBACK, new B7.d(11, this));
    }

    public void unRegisterBroadcastReceiver() {
        if (this.mPhotoEditorFallbackReceiver != null) {
            AndroidCompat.unregisterReceiver(this.mContainer.getContext(), this.mPhotoEditorFallbackReceiver);
            this.mPhotoEditorFallbackReceiver = null;
        }
    }
}
