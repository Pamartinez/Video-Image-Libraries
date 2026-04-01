package q2;

import android.graphics.Rect;
import android.view.View;
import java.util.List;
import kotlin.jvm.internal.j;

/* renamed from: q2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface C0268b {
    View getReferenceView(C0267a aVar);

    Rect getReferenceViewInset(C0267a aVar) {
        j.e(aVar, "type");
        return new Rect();
    }

    List getReferenceViews(C0267a aVar) {
        j.e(aVar, "type");
        return null;
    }

    void onStartHideFloatingBackground() {
    }

    void onStartShowFloatingBackground() {
    }
}
