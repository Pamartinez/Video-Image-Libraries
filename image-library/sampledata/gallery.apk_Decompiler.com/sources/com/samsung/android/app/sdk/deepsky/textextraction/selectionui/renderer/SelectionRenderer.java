package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableBarcode;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JG\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/SelectionRenderer;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/Canvas;", "canvas", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "selectedCharacters", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableBarcode;", "selectedBarcodes", "Landroid/graphics/Rect;", "boundary", "", "radius", "Lme/x;", "drawSelection", "(Landroid/graphics/Canvas;Ljava/util/List;Ljava/util/List;Landroid/graphics/Rect;F)V", "Landroid/content/Context;", "Landroid/graphics/Paint;", "selectionPaint", "Landroid/graphics/Paint;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectionRenderer {
    private final Context context;
    private final Paint selectionPaint;

    public SelectionRenderer(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        Paint paint = new Paint();
        paint.setFlags(1);
        paint.setColor(context2.getColor(R$color.textextraction_selection_area_color));
        paint.setStyle(Paint.Style.FILL);
        this.selectionPaint = paint;
    }

    public final void drawSelection(Canvas canvas, List<SelectableCharacter> list, List<SelectableBarcode> list2, Rect rect, float f) {
        j.e(canvas, "canvas");
        j.e(list, "selectedCharacters");
        j.e(list2, "selectedBarcodes");
        if (rect == null) {
            for (SelectableCharacter path : list) {
                canvas.drawPath(path.getPath(), this.selectionPaint);
            }
        } else {
            Path path2 = new Path();
            for (SelectableCharacter path3 : list) {
                path2.op(path2, path3.getPath(), Path.Op.UNION);
            }
            Path path4 = new Path();
            path4.addRoundRect(new RectF(rect), f, f, Path.Direction.CW);
            Path path5 = new Path();
            path5.op(path2, path4, Path.Op.INTERSECT);
            canvas.drawPath(path5, this.selectionPaint);
        }
        for (SelectableBarcode path6 : list2) {
            canvas.drawPath(path6.getPath(), this.selectionPaint);
        }
    }
}
