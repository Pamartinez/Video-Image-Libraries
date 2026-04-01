package com.samsung.o3dp.lib3dphotography;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.o3dp.lib3dphotography.utils.ImageUtil;
import java.util.function.UnaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements UnaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ float f4211a;
    public final /* synthetic */ Rect b;

    public /* synthetic */ k(float f, Rect rect) {
        this.f4211a = f;
        this.b = rect;
    }

    public final Object apply(Object obj) {
        return ImageUtil.scaleBitmap((Bitmap) obj, this.f4211a, this.b);
    }
}
