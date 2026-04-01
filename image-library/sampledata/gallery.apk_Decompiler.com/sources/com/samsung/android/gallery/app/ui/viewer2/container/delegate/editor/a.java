package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2575a;
    public final /* synthetic */ EditorDelegate b;

    public /* synthetic */ a(EditorDelegate editorDelegate, int i2) {
        this.f2575a = i2;
        this.b = editorDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2575a;
        EditorDelegate editorDelegate = this.b;
        switch (i2) {
            case 0:
                editorDelegate.onEditorReenterTransitionEnd(objArr);
                return;
            default:
                editorDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
        }
    }
}
