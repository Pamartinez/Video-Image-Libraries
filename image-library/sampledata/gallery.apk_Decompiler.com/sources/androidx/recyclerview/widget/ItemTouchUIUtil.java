package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ItemTouchUIUtil {
    void clearView(View view);

    void onDraw(Canvas canvas, RecyclerView recyclerView, View view, float f, float f5, int i2, boolean z);

    void onDrawOver(Canvas canvas, RecyclerView recyclerView, View view, float f, float f5, int i2, boolean z);

    void onSelected(View view);
}
