package com.samsung.android.app.sdk.deepsky.barcode.result;

import android.graphics.Point;
import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;", "", "", "rawData", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/ActionUIResource;", "uiResource", "<init>", "(Ljava/lang/String;Landroid/graphics/Rect;[Landroid/graphics/Point;Lcom/samsung/android/app/sdk/deepsky/barcode/result/ActionUIResource;)V", "Ljava/lang/String;", "getRawData", "()Ljava/lang/String;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/ActionUIResource;", "getUiResource", "()Lcom/samsung/android/app/sdk/deepsky/barcode/result/ActionUIResource;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Barcode {
    private final Point[] poly;
    private final String rawData;
    private final Rect rect;
    private final ActionUIResource uiResource;

    public Barcode(String str, Rect rect2, Point[] pointArr, ActionUIResource actionUIResource) {
        j.e(str, "rawData");
        j.e(rect2, "rect");
        j.e(pointArr, "poly");
        j.e(actionUIResource, "uiResource");
        this.rawData = str;
        this.rect = rect2;
        this.poly = pointArr;
        this.uiResource = actionUIResource;
    }

    public final Point[] getPoly() {
        return this.poly;
    }

    public final ActionUIResource getUiResource() {
        return this.uiResource;
    }
}
