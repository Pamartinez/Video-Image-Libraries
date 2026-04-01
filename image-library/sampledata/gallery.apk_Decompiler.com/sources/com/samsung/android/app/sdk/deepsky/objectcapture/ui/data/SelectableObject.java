package com.samsung.android.app.sdk.deepsky.objectcapture.ui.data;

import android.graphics.Bitmap;
import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J'\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/data/SelectableObject;", "", "index", "", "rect", "Landroid/graphics/Rect;", "bitmap", "Landroid/graphics/Bitmap;", "<init>", "(ILandroid/graphics/Rect;Landroid/graphics/Bitmap;)V", "getIndex", "()I", "setIndex", "(I)V", "getRect", "()Landroid/graphics/Rect;", "setRect", "(Landroid/graphics/Rect;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectableObject {
    private Bitmap bitmap;
    private int index;
    private Rect rect;

    public SelectableObject(int i2, Rect rect2, Bitmap bitmap2) {
        j.e(rect2, "rect");
        j.e(bitmap2, "bitmap");
        this.index = i2;
        this.rect = rect2;
        this.bitmap = bitmap2;
    }

    public static /* synthetic */ SelectableObject copy$default(SelectableObject selectableObject, int i2, Rect rect2, Bitmap bitmap2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = selectableObject.index;
        }
        if ((i7 & 2) != 0) {
            rect2 = selectableObject.rect;
        }
        if ((i7 & 4) != 0) {
            bitmap2 = selectableObject.bitmap;
        }
        return selectableObject.copy(i2, rect2, bitmap2);
    }

    public final int component1() {
        return this.index;
    }

    public final Rect component2() {
        return this.rect;
    }

    public final Bitmap component3() {
        return this.bitmap;
    }

    public final SelectableObject copy(int i2, Rect rect2, Bitmap bitmap2) {
        j.e(rect2, "rect");
        j.e(bitmap2, "bitmap");
        return new SelectableObject(i2, rect2, bitmap2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SelectableObject)) {
            return false;
        }
        SelectableObject selectableObject = (SelectableObject) obj;
        if (this.index == selectableObject.index && j.a(this.rect, selectableObject.rect) && j.a(this.bitmap, selectableObject.bitmap)) {
            return true;
        }
        return false;
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Rect getRect() {
        return this.rect;
    }

    public int hashCode() {
        int hashCode = this.rect.hashCode();
        return this.bitmap.hashCode() + ((hashCode + (Integer.hashCode(this.index) * 31)) * 31);
    }

    public final void setBitmap(Bitmap bitmap2) {
        j.e(bitmap2, "<set-?>");
        this.bitmap = bitmap2;
    }

    public final void setIndex(int i2) {
        this.index = i2;
    }

    public final void setRect(Rect rect2) {
        j.e(rect2, "<set-?>");
        this.rect = rect2;
    }

    public String toString() {
        int i2 = this.index;
        Rect rect2 = this.rect;
        Bitmap bitmap2 = this.bitmap;
        return "SelectableObject(index=" + i2 + ", rect=" + rect2 + ", bitmap=" + bitmap2 + ")";
    }
}
