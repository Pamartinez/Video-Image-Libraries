package r6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RelatedPage;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedInfo;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ RelatedPage e;

    public /* synthetic */ f(RelatedPage relatedPage, int i2) {
        this.d = i2;
        this.e = relatedPage;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.onProgressDone((Void) obj);
                return;
            case 1:
                this.e.onProgressUpdated(((Float) obj).floatValue());
                return;
            default:
                this.e.lambda$ruleOutFromRelated$1((RelatedInfo) obj);
                return;
        }
    }
}
