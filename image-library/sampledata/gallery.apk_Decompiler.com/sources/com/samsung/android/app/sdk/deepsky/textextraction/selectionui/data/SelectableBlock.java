package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data;

import android.graphics.Point;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableBlock;", "", "", "data", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/lang/String;[Landroid/graphics/Point;)V", "Ljava/lang/String;", "getData", "()Ljava/lang/String;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "setPoly", "([Landroid/graphics/Point;)V", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;", "selectableLines", "Ljava/util/List;", "getSelectableLines", "()Ljava/util/List;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectableBlock {
    private final String data;
    private Point[] poly;
    private final List<SelectableLine> selectableLines = new ArrayList();

    public SelectableBlock(String str, Point[] pointArr) {
        j.e(str, "data");
        j.e(pointArr, "poly");
        this.data = str;
        this.poly = pointArr;
    }

    public final Point[] getPoly() {
        return this.poly;
    }

    public final List<SelectableLine> getSelectableLines() {
        return this.selectableLines;
    }
}
