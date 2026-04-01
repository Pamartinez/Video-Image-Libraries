package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data;

import android.graphics.Point;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0017\u001a\u0004\b\b\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\u000e¨\u0006 "}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;", "", "", "data", "", "Landroid/graphics/Point;", "poly", "", "isVertical", "<init>", "(Ljava/lang/String;[Landroid/graphics/Point;Z)V", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "getSelectableCharacters", "()Ljava/util/List;", "Ljava/lang/String;", "getData", "()Ljava/lang/String;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "setPoly", "([Landroid/graphics/Point;)V", "Z", "()Z", "setVertical", "(Z)V", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "selectableWords", "Ljava/util/List;", "getSelectableWords", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectableLine {
    private final String data;
    private boolean isVertical;
    private Point[] poly;
    private final List<SelectableWord> selectableWords = new ArrayList();

    public SelectableLine(String str, Point[] pointArr, boolean z) {
        j.e(str, "data");
        j.e(pointArr, "poly");
        this.data = str;
        this.poly = pointArr;
        this.isVertical = z;
    }

    public final Point[] getPoly() {
        return this.poly;
    }

    public final List<SelectableCharacter> getSelectableCharacters() {
        ArrayList arrayList = new ArrayList();
        for (SelectableWord selectableCharacters : this.selectableWords) {
            arrayList.addAll(selectableCharacters.getSelectableCharacters());
        }
        return arrayList;
    }

    public final List<SelectableWord> getSelectableWords() {
        return this.selectableWords;
    }
}
