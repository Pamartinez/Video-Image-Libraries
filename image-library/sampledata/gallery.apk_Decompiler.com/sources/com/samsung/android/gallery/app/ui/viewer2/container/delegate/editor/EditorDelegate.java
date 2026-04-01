package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import D5.e;
import c4.C0438h;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditorDelegate extends AbsVuDelegate<IVuContainerView> {
    private AbsEditorTransitionHandler mTransitionHandler;

    public EditorDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        prepareEditor(objArr[0]);
    }

    /* access modifiers changed from: private */
    public void onEditorReenterTransitionEnd(Object... objArr) {
        Optional.ofNullable(this.mTransitionHandler).ifPresent(new C0438h(6));
        this.mTransitionHandler = null;
    }

    public void onDestroy() {
        Optional.ofNullable(this.mTransitionHandler).ifPresent(new C0438h(5));
        this.mBlackboard.erase("data://viewer_app_transition_callback");
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        Optional.ofNullable(this.mTransitionHandler).ifPresent(new e(1, eventMessage));
        int i2 = eventMessage.what;
        if (i2 == 1103 || i2 == 3201) {
            return true;
        }
        return false;
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        Optional.ofNullable(this.mTransitionHandler).ifPresent(new b(i2, viewerObjectComposite, 0));
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        Optional.ofNullable(this.mTransitionHandler).ifPresent(new b(i2, viewerObjectComposite, 1));
    }

    public void onResume() {
        super.onResume();
        BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
    }

    public void prepareEditor(MediaItem mediaItem) {
        AbsEditorTransitionHandler absEditorTransitionHandler;
        Log.at(this.TAG, "prepareEditor");
        Optional.ofNullable(this.mTransitionHandler).ifPresent(new C0438h(6));
        if (mediaItem.isVideo()) {
            absEditorTransitionHandler = new VideoEditorTransitionHandler((IVuContainerView) this.mView, this.mActionInvoker);
        } else {
            absEditorTransitionHandler = new PhotoEditorTransitionHandler((IVuContainerView) this.mView, this.mActionInvoker);
        }
        this.mTransitionHandler = absEditorTransitionHandler;
        absEditorTransitionHandler.prepareEditor(mediaItem);
        BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, true);
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        this.mActionInvoker.add(ViewerAction.PHOTO_EDITOR_REENTER_TRANSITION_END, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.VIDEO_EDITOR_REENTER_TRANSITION_END, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.PREPARE_EDITOR, new a(this, 1));
    }
}
