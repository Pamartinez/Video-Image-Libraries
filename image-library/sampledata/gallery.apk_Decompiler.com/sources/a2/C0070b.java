package a2;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: a2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0070b extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final Paint f973a;
    public final List b = Collections.unmodifiableList(new ArrayList());

    public C0070b() {
        Paint paint = new Paint();
        this.f973a = paint;
        paint.setStrokeWidth(5.0f);
        paint.setColor(-65281);
    }

    public final void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        Canvas canvas2;
        super.onDrawOver(canvas, recyclerView, state);
        float dimension = recyclerView.getResources().getDimension(R.dimen.m3_carousel_debug_keyline_width);
        Paint paint = this.f973a;
        paint.setStrokeWidth(dimension);
        for (C0075g gVar : this.b) {
            gVar.getClass();
            paint.setColor(ColorUtils.blendARGB(-65281, -16776961, 0.0f));
            if (((CarouselLayoutManager) recyclerView.getLayoutManager()).a()) {
                canvas2 = canvas;
                canvas2.drawLine(0.0f, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).e.e(), 0.0f, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).e.a(), paint);
            } else {
                canvas2 = canvas;
                canvas2.drawLine((float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).e.b(), 0.0f, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).e.c(), 0.0f, paint);
            }
            canvas = canvas2;
        }
    }
}
