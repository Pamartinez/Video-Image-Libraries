package com.samsung.android.sesl.visualeffect.lighteffects.common.control;

import android.graphics.PointF;
import android.util.Size;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toPointF", "Landroid/graphics/PointF;", "Landroid/util/Size;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IColorEffectKt {
    public static final PointF toPointF(Size size) {
        j.e(size, "<this>");
        return new PointF((float) size.getWidth(), (float) size.getHeight());
    }
}
