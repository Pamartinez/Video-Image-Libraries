package O8;

import com.samsung.android.app.sdk.deepsky.textextraction.capsule.SimpleCapsuleClickListener;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements TextExtractionDrawHelper.TranslateCapsuleClickListener, SimpleCapsuleClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TextExtractionHelper f2856a;

    public /* synthetic */ b(TextExtractionHelper textExtractionHelper) {
        this.f2856a = textExtractionHelper;
    }

    public void a(boolean z) {
        this.f2856a.onTranslateClicked(z);
    }
}
