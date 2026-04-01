package O8;

import android.graphics.Point;
import android.graphics.PointF;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TextExtractionHelper f2857a;
    public final /* synthetic */ PointF b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ float f2858c;

    public /* synthetic */ d(TextExtractionHelper textExtractionHelper, PointF pointF, float f) {
        this.f2857a = textExtractionHelper;
        this.b = pointF;
        this.f2858c = f;
    }

    public final Object apply(Object obj) {
        return this.f2857a.lambda$getHighlightPoints$3(this.b, this.f2858c, (Point) obj);
    }
}
