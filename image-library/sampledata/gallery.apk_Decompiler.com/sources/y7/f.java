package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;
import com.samsung.android.gallery.widget.clip.ClipView;
import com.samsung.android.gallery.widget.clip.textextraction.TextExtractionView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements TextExtractionView.OnViewClickListener, TextExtractionView.OnViewReadyListener, ClipView.OnToggleConsumeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TextExtractionHandler f2757a;

    public /* synthetic */ f(TextExtractionHandler textExtractionHandler) {
        this.f2757a = textExtractionHandler;
    }

    public void onToggleConsumed() {
        this.f2757a.lambda$initView$10();
    }
}
