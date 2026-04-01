package com.samsung.android.app.sdk.deepsky.objectcapture.base;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;", "", "objBitmap", "Landroid/graphics/Bitmap;", "boundRect", "Landroid/graphics/Rect;", "<init>", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;)V", "getObjBitmap", "()Landroid/graphics/Bitmap;", "getBoundRect", "()Landroid/graphics/Rect;", "isNotEmptyPixelOrNull", "", "touchPoint", "Landroid/graphics/Point;", "isNotEmptyPixelOrNull$deepsky_sdk_objectcapture_8_5_9_release", "(Landroid/graphics/Point;)Ljava/lang/Boolean;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectInfo {
    private final Rect boundRect;
    private final Bitmap objBitmap;

    public ObjectInfo(Bitmap bitmap, Rect rect) {
        j.e(bitmap, "objBitmap");
        j.e(rect, "boundRect");
        this.objBitmap = bitmap;
        this.boundRect = rect;
    }

    public static /* synthetic */ ObjectInfo copy$default(ObjectInfo objectInfo, Bitmap bitmap, Rect rect, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bitmap = objectInfo.objBitmap;
        }
        if ((i2 & 2) != 0) {
            rect = objectInfo.boundRect;
        }
        return objectInfo.copy(bitmap, rect);
    }

    public final Bitmap component1() {
        return this.objBitmap;
    }

    public final Rect component2() {
        return this.boundRect;
    }

    public final ObjectInfo copy(Bitmap bitmap, Rect rect) {
        j.e(bitmap, "objBitmap");
        j.e(rect, "boundRect");
        return new ObjectInfo(bitmap, rect);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectInfo)) {
            return false;
        }
        ObjectInfo objectInfo = (ObjectInfo) obj;
        if (j.a(this.objBitmap, objectInfo.objBitmap) && j.a(this.boundRect, objectInfo.boundRect)) {
            return true;
        }
        return false;
    }

    public final Rect getBoundRect() {
        return this.boundRect;
    }

    public final Bitmap getObjBitmap() {
        return this.objBitmap;
    }

    public int hashCode() {
        return this.boundRect.hashCode() + (this.objBitmap.hashCode() * 31);
    }

    public final Boolean isNotEmptyPixelOrNull$deepsky_sdk_objectcapture_8_5_9_release(Point point) {
        boolean z;
        j.e(point, "touchPoint");
        if (!this.boundRect.contains(point.x, point.y)) {
            return null;
        }
        Point point2 = new Point(point);
        Rect rect = this.boundRect;
        point2.offset(-rect.left, -rect.top);
        if (this.objBitmap.getPixel(point2.x, point2.y) != 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public String toString() {
        Bitmap bitmap = this.objBitmap;
        Rect rect = this.boundRect;
        return "ObjectInfo(objBitmap=" + bitmap + ", boundRect=" + rect + ")";
    }
}
