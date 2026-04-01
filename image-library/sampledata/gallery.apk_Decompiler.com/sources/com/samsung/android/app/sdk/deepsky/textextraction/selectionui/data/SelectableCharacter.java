package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data;

import android.graphics.Path;
import android.graphics.Point;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u000e\u001a\u0004\b\b\u0010\u000f\"\u0004\b\u0010\u0010\u0011R6\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\"\u001a\u00020!8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006("}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "", "", "data", "", "Landroid/graphics/Point;", "poly", "", "isVertical", "<init>", "(Ljava/lang/String;[Landroid/graphics/Point;Z)V", "Ljava/lang/String;", "getData", "()Ljava/lang/String;", "Z", "()Z", "setVertical", "(Z)V", "value", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "setPoly", "([Landroid/graphics/Point;)V", "isSelected", "setSelected", "Landroid/graphics/Path;", "path", "Landroid/graphics/Path;", "getPath", "()Landroid/graphics/Path;", "setPath", "(Landroid/graphics/Path;)V", "", "index", "I", "getIndex", "()I", "setIndex", "(I)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectableCharacter {
    private final String data;
    private int index = -1;
    private boolean isSelected;
    private boolean isVertical;
    private Path path;
    private Point[] poly;

    public SelectableCharacter(String str, Point[] pointArr, boolean z) {
        j.e(str, "data");
        j.e(pointArr, "poly");
        this.data = str;
        this.isVertical = z;
        this.poly = pointArr;
        this.path = PointUtil.INSTANCE.createPathFromPoly(pointArr);
    }

    public final String getData() {
        return this.data;
    }

    public final int getIndex() {
        return this.index;
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

    public final boolean isVertical() {
        return this.isVertical;
    }

    public final void setIndex(int i2) {
        this.index = i2;
    }

    public final void setPoly(Point[] pointArr) {
        j.e(pointArr, "value");
        this.poly = pointArr;
        this.path = PointUtil.INSTANCE.createPathFromPoly(pointArr);
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }
}
