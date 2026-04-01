package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture;

import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import u7.C0520a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DummyObjectCaptureHandler extends ViewerObject {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_DONE, Boolean.FALSE);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE, new C0520a(6, this));
    }
}
