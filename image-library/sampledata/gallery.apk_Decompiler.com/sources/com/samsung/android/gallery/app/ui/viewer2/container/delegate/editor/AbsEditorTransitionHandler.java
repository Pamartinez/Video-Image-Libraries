package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import Ba.f;
import G6.a;
import N2.j;
import android.net.Uri;
import android.transition.Transition;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import bc.C0584a;
import bc.d;
import c4.C0431a;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemRetryLoader;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.module.transition.TransitionName;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleTransitionListener;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsEditorTransitionHandler {
    protected static final boolean SUPPORT_TRANSITION = Features.isEnabled(Features.SUPPORT_APP_TRANSITION);
    protected final String TAG;
    protected final ActionInvoker mActionInvoker;
    protected final Blackboard mBlackboard;
    protected final IVuContainerView mContainer;
    protected final EditorInfo mInfo = new EditorInfo();
    /* access modifiers changed from: private */
    public final AtomicBoolean mTransitionState;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Index {
        prepareEditor(0),
        handleItemEditedEvent(1),
        OnFallback(1),
        saveND(2),
        saveAsCopy(2),
        prepareReturnTransition(3),
        prepareReturnData(4),
        reloadData(5),
        OnPageSelected(6),
        OnPageInvalidate(6),
        startHide(7),
        reenterFromVE(8),
        startReturningAppTransition(10),
        prepareReenterTransition(11),
        startPostponedEnterTransition(12),
        OnReenterTransitionEnd(13);
        
        final int id;

        private Index(int i2) {
            this.id = i2;
        }
    }

    public AbsEditorTransitionHandler(IVuContainerView iVuContainerView, ActionInvoker actionInvoker) {
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        ActionInvoker actionInvoker2 = new ActionInvoker(simpleName);
        this.mActionInvoker = actionInvoker2;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.mTransitionState = atomicBoolean;
        this.mContainer = iVuContainerView;
        this.mBlackboard = iVuContainerView.getBlackboard();
        ((ContainerModel) iVuContainerView.getModel()).setTransitionState(atomicBoolean);
        actionInvoker.attach(actionInvoker2);
        setEventHandlerListener();
    }

    private long getMediaIdFromUri(Uri uri) {
        try {
            return Long.parseLong(uri.getLastPathSegment());
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    private boolean isNonDestructiveEditedFile(MediaItem mediaItem, String str) {
        if (str == null) {
            return true;
        }
        if (str.equals(mediaItem.getPath())) {
            MediaItem clone = mediaItem.clone();
            ThumbnailUtil.updateImageInfo(clone);
            Optional.ofNullable(clone.getPath()).ifPresent(new a(clone, 11));
            boolean equals = MediaItemUtil.equals(clone, mediaItem);
            String str2 = this.TAG;
            Log.at(str2, "isNonDestructiveEditedFile : " + Logger.v(Boolean.valueOf(equals), clone, mediaItem));
            return equals;
        }
        String str3 = this.TAG;
        StringBuilder sb2 = new StringBuilder("isNDE failed : ");
        StringBuilder t = C0212a.t(str, " , ");
        t.append(mediaItem.getPath());
        sb2.append(Logger.getEncodedString(t.toString()));
        Log.at(str3, sb2.toString());
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setTransitionListener$0(SimpleTransitionListener simpleTransitionListener, Transition transition) {
        if (this.mContainer.isDestroyed() || !this.mTransitionState.get()) {
            transition.removeListener(simpleTransitionListener);
            return;
        }
        Log.atw(this.TAG, "ReenterTransition MONITOR");
        simpleTransitionListener.onTransitionEnd(transition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$waitNewMediaItemLoaded$2(String str, MediaItem mediaItem) {
        return Boolean.valueOf(isNonDestructiveEditedFile(mediaItem, str));
    }

    private void resetPostSetAlphaOnce() {
        View transitionView = getTransitionView();
        if (transitionView instanceof PhotoView) {
            ((PhotoView) transitionView).postSetAlphaOnce(false);
        }
    }

    private void setTransitionListener() {
        Transition transition = (Transition) Optional.ofNullable(this.mContainer.getActivity()).map(new C0431a(10)).map(new C0431a(11)).orElse((Object) null);
        if (transition != null) {
            AnonymousClass1 r1 = new SimpleTransitionListener() {
                public void onTransitionEnd(Transition transition) {
                    if (AbsEditorTransitionHandler.this.mTransitionState.getAndSet(false)) {
                        AbsEditorTransitionHandler.this.onReenterTransitionEnd();
                    }
                    transition.removeListener(this);
                }
            };
            this.mTransitionState.set(true);
            transition.addListener(r1);
            ThreadUtil.postOnUiThreadDelayed(new d((Object) this, (Object) r1, (Object) transition, 5), 2000);
        }
    }

    public void clearAppTransition() {
        Log.at(this.TAG, "clearAppTransition");
        this.mBlackboard.erase("data://viewer_app_transition_callback");
        unRegisterBroadcastReceiver();
        resetPostSetAlphaOnce();
    }

    public void clearCache(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThumbnailLoader.getInstance().removePppMemCache(mediaItem);
            ThumbnailLoader.getInstance().removeCache(mediaItem);
            this.mActionInvoker.invoke(ViewerAction.REMOVE_TIMELAPSE_INFO, mediaItem);
            return;
        }
        Log.atw(this.TAG, "clearCache Failed");
    }

    public MediaItem getCurrentItem() {
        if (this.mContainer.getEventContext() != null) {
            return this.mContainer.getEventContext().getCurrentItem();
        }
        return null;
    }

    public Uri getEditedItemUri() {
        return this.mInfo.mUri;
    }

    public View getTransitionView() {
        ViewerObjectComposite currentViewer = this.mContainer.getCurrentViewer();
        if (currentViewer != null) {
            return currentViewer.getViewHolder().getTransitionView();
        }
        return null;
    }

    public void handleSharingItemEditedEvent(EventMessage eventMessage) {
        MediaItem mediaItem = (MediaItem) eventMessage.obj;
        MediaItem currentItem = getCurrentItem();
        if (mediaItem != null && currentItem != null) {
            lambda$waitNewMediaItemLoaded$3(mediaItem.getMediaId(), currentItem, mediaItem);
        }
    }

    public boolean hasEditedItemUri() {
        if (this.mInfo.mUri != null) {
            return true;
        }
        return false;
    }

    public abstract void log(Index index, String str);

    public void onDestroy() {
        Log.at(this.TAG, "onDestroy");
        clearAppTransition();
        this.mActionInvoker.detachFromParent();
    }

    public void onHandleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1102) {
            String str = this.TAG;
            Log.at(str, "onHandleEvent" + Logger.v(Integer.valueOf(eventMessage.what), "EVENT_APP_REENTER_TRANSITION"));
            if (SUPPORT_TRANSITION) {
                clearAppTransition();
                startReturningAppTransition(MOCRLang.KHMER);
            }
        } else if (i2 == 6016) {
            String str2 = this.TAG;
            Log.at(str2, "onHandleEvent" + Logger.v(Integer.valueOf(eventMessage.what), "EVENT_ITEM_EDITED_FROM_SHARING_EDITOR"));
            handleSharingItemEditedEvent(eventMessage);
        }
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        Index index = Index.OnPageInvalidate;
        log(index, "#" + i2);
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        Index index = Index.OnPageSelected;
        log(index, "#" + i2);
    }

    public void onReenterTransitionEnd() {
        log(Index.OnReenterTransitionEnd, "");
        this.mContainer.getActionInvoker().invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.TRUE);
    }

    public void prepareEditor(MediaItem mediaItem) {
        log(Index.prepareEditor, MediaItemUtil.getDebugLog(mediaItem));
    }

    public void prepareReenterTransition() {
        log(Index.prepareReenterTransition, "");
        View transitionView = getTransitionView();
        if (transitionView != null) {
            transitionView.setTransitionName(TransitionName.getAppToApp(((Boolean) Optional.ofNullable(getCurrentItem()).map(new C0431a(9)).orElse(Boolean.FALSE)).booleanValue()));
        }
    }

    /* renamed from: prepareReturnTransition */
    public void lambda$waitNewMediaItemLoaded$3(long j2, MediaItem mediaItem, MediaItem mediaItem2) {
        Index index = Index.prepareReturnTransition;
        StringBuilder j3 = j.j(j2, "{", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        j3.append(mediaItem2.getMediaId());
        j3.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        j3.append(mediaItem2.getFileId());
        j3.append("}");
        log(index, j3.toString());
    }

    public void reloadData() {
        log(Index.reloadData, "");
        this.mBlackboard.post("command://event/DataDirty", (Object) null);
        BlackboardUtils.removeDataChangeObservingDelay(this.mBlackboard);
        BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
    }

    public void startPostponedEnterTransition() {
        FragmentActivity activity = this.mContainer.getActivity();
        if (activity != null) {
            ViewUtils.setVisibility(getTransitionView(), 0);
            activity.startPostponedEnterTransition();
            Index index = Index.startPostponedEnterTransition;
            log(index, "{" + ViewUtils.getTransitionName(getTransitionView()) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + ViewUtils.getVisibilityCode(getTransitionView()) + "}");
        }
    }

    public void startReturningAppTransition(int i2) {
        log(Index.startReturningAppTransition, "");
        if (!this.mTransitionState.get()) {
            prepareReenterTransition();
            setTransitionListener();
            ThreadUtil.postOnUiThreadDelayed(new C0584a(17, this), (long) i2);
        }
    }

    public abstract void unRegisterBroadcastReceiver();

    public void waitNewMediaItemLoaded(Uri uri, String str) {
        this.mBlackboard.post("command://ForceNotifyChangeFromEditor", (Object) null);
        MediaItem currentItem = getCurrentItem();
        if (currentItem == null) {
            startReturningAppTransition(MOCRLang.KHMER);
            return;
        }
        long mediaIdFromUri = getMediaIdFromUri(uri);
        this.mInfo.mUri = uri;
        ((ContainerModel) this.mContainer.getModel()).clearLastPreviewData();
        MediaItemRetryLoader mediaItemRetryLoader = new MediaItemRetryLoader(10, 100);
        if (mediaIdFromUri == currentItem.getMediaId()) {
            log(Index.saveND, "");
            clearCache(currentItem);
            BlackboardUtils.cancelAndEraseViewerBitmap(this.mBlackboard, (FileItemInterface) currentItem);
            mediaItemRetryLoader.setResultChecker(new Qa.a(4, (Object) this, (Object) str));
        } else {
            log(Index.saveAsCopy, "");
        }
        if (currentItem.getGroupTypeEnum() != null) {
            mediaItemRetryLoader.setGroupType(currentItem.getGroupTypeEnum());
        }
        mediaItemRetryLoader.setSuccessCallback(new f((Object) this, mediaIdFromUri, currentItem, 5)).getMediaItemFromUri(uri);
    }

    public void setEventHandlerListener() {
    }
}
