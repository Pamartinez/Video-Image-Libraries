package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper;

import android.graphics.Point;
import android.view.View;
import android.widget.TextView;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J#\u0010\u0006\u001a\u00020\u00052\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/DragAndDropHelper$createDragShadowBuilder$1", "Landroid/view/View$DragShadowBuilder;", "Landroid/graphics/Point;", "outShadowSize", "outShadowTouchPoint", "Lme/x;", "onProvideShadowMetrics", "(Landroid/graphics/Point;Landroid/graphics/Point;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DragAndDropHelper$createDragShadowBuilder$1 extends View.DragShadowBuilder {
    final /* synthetic */ TextView $textView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DragAndDropHelper$createDragShadowBuilder$1(TextView textView) {
        super(textView);
        this.$textView = textView;
    }

    public void onProvideShadowMetrics(Point point, Point point2) {
        if (point != null) {
            point.x = this.$textView.getMeasuredWidth();
        }
        if (point != null) {
            point.y = this.$textView.getMeasuredHeight();
        }
        if (point2 != null) {
            point2.x = this.$textView.getMeasuredWidth() / 2;
        }
        if (point2 != null) {
            point2.y = this.$textView.getMeasuredHeight() / 2;
        }
    }
}
