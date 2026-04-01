package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPhotoEditorBehavior {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnHidePhotoEditor {
    }

    void hidePhotoEditor();

    void preparePhotoEditor(Context context, OnHidePhotoEditor onHidePhotoEditor);

    void cancelReturnTransition(boolean z) {
    }

    void setTransitionReentered(boolean z) {
    }
}
