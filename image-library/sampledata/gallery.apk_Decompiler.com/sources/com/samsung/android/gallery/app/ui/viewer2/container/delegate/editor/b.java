package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ ViewerObjectComposite f;

    public /* synthetic */ b(int i2, ViewerObjectComposite viewerObjectComposite, int i7) {
        this.d = i7;
        this.e = i2;
        this.f = viewerObjectComposite;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((AbsEditorTransitionHandler) obj).onPageInvalidate(this.e, this.f);
                return;
            default:
                ((AbsEditorTransitionHandler) obj).onPageSelected(this.e, this.f);
                return;
        }
    }
}
