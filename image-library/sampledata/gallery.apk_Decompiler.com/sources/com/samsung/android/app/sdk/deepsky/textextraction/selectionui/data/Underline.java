package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data;

import android.graphics.Point;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/Underline;", "", "Landroid/graphics/Point;", "from", "to", "<init>", "(Landroid/graphics/Point;Landroid/graphics/Point;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/graphics/Point;", "getFrom", "()Landroid/graphics/Point;", "getTo", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Underline {
    private final Point from;
    private final Point to;

    public Underline(Point point, Point point2) {
        j.e(point, "from");
        j.e(point2, "to");
        this.from = point;
        this.to = point2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Underline)) {
            return false;
        }
        Underline underline = (Underline) obj;
        if (j.a(this.from, underline.from) && j.a(this.to, underline.to)) {
            return true;
        }
        return false;
    }

    public final Point getFrom() {
        return this.from;
    }

    public final Point getTo() {
        return this.to;
    }

    public int hashCode() {
        return this.to.hashCode() + (this.from.hashCode() * 31);
    }

    public String toString() {
        Point point = this.from;
        Point point2 = this.to;
        return "Underline(from=" + point + ", to=" + point2 + ")";
    }
}
