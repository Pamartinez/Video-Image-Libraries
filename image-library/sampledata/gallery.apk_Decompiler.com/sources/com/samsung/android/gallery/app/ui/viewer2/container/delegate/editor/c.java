package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoEditorTransitionHandler e;

    public /* synthetic */ c(VideoEditorTransitionHandler videoEditorTransitionHandler, int i2) {
        this.d = i2;
        this.e = videoEditorTransitionHandler;
    }

    public final void run() {
        int i2 = this.d;
        VideoEditorTransitionHandler videoEditorTransitionHandler = this.e;
        switch (i2) {
            case 0:
                videoEditorTransitionHandler.reenterFromVideoEditingApp();
                return;
            default:
                videoEditorTransitionHandler.lambda$reenterFromVideoEditingApp$0();
                return;
        }
    }
}
