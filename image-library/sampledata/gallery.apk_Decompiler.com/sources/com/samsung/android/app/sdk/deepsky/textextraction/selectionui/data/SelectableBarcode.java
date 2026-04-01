package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data;

import android.graphics.Path;
import android.graphics.Point;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006R6\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0006R\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0014\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableBarcode;", "", "", "Landroid/graphics/Point;", "poly", "<init>", "([Landroid/graphics/Point;)V", "value", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "setPoly", "Landroid/graphics/Path;", "path", "Landroid/graphics/Path;", "getPath", "()Landroid/graphics/Path;", "setPath", "(Landroid/graphics/Path;)V", "", "isSelected", "Z", "()Z", "setSelected", "(Z)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectableBarcode {
    private boolean isSelected;
    private Path path;
    private Point[] poly;

    public SelectableBarcode(Point[] pointArr) {
        j.e(pointArr, "poly");
        this.poly = pointArr;
        this.path = PointUtil.INSTANCE.createPathFromPoly(pointArr);
    }

    public final Path getPath() {
        return this.path;
    }

    public final Point[] getPoly() {
        return this.poly;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }
}
