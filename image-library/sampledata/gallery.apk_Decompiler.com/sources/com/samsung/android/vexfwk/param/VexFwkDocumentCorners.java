package com.samsung.android.vexfwk.param;

import Ae.b;
import android.graphics.PointF;
import c0.C0086a;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1200r;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u000fB\t\b\u0012¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bB\u0017\b\u0016\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0004\b\u0004\u0010\u000bJ\b\u0010\f\u001a\u00020\u0000H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;", "Ljava/util/LinkedList;", "Landroid/graphics/PointF;", "", "<init>", "()V", "array", "", "([F)V", "points", "", "([Landroid/graphics/PointF;)V", "clone", "toString", "", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocumentCorners extends LinkedList<PointF> implements Cloneable {
    private static final int BUFFER_SIZE = 32;
    public static final Companion Companion = new Companion((e) null);
    private static final int FLOAT_COUNT = 8;
    private static final int POINTF_COUNT = 4;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;", "<init>", "()V", "FLOAT_COUNT", "", "POINTF_COUNT", "BUFFER_SIZE", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkDocumentCorners> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkDocumentCorners fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            if (byteBuffer.capacity() == 32) {
                VexFwkDocumentCorners vexFwkDocumentCorners = new VexFwkDocumentCorners((e) null);
                for (int i2 = 0; i2 < 4; i2++) {
                    vexFwkDocumentCorners.add(new PointF(byteBuffer.getFloat(), byteBuffer.getFloat()));
                }
                return vexFwkDocumentCorners;
            }
            throw new IllegalStateException(C0086a.i(byteBuffer.capacity(), "buffer capacity must be 32. buffer capacity is ").toString());
        }

        public ByteBuffer toBuffer(VexFwkDocumentCorners vexFwkDocumentCorners) {
            j.e(vexFwkDocumentCorners, "value");
            ByteBuffer allocateBuffer = allocateBuffer(32);
            Iterator it = vexFwkDocumentCorners.iterator();
            while (it.hasNext()) {
                PointF pointF = (PointF) it.next();
                allocateBuffer.putFloat(pointF.x);
                allocateBuffer.putFloat(pointF.y);
            }
            return allocateBuffer;
        }
    }

    public /* synthetic */ VexFwkDocumentCorners(e eVar) {
        this();
    }

    public /* bridge */ boolean contains(PointF pointF) {
        return super.contains(pointF);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(PointF pointF) {
        return super.indexOf(pointF);
    }

    public /* bridge */ int lastIndexOf(PointF pointF) {
        return super.lastIndexOf(pointF);
    }

    public final /* bridge */ PointF remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ PointF removeAt(int i2) {
        return (PointF) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public String toString() {
        return C1194l.R0(this, (String) null, (String) null, (String) null, (b) null, 63);
    }

    private VexFwkDocumentCorners() {
    }

    public VexFwkDocumentCorners clone() {
        return new VexFwkDocumentCorners((PointF[]) toArray(new PointF[0]));
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof PointF)) {
            return false;
        }
        return contains((PointF) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof PointF)) {
            return -1;
        }
        return indexOf((PointF) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof PointF)) {
            return -1;
        }
        return lastIndexOf((PointF) obj);
    }

    public /* bridge */ boolean remove(PointF pointF) {
        return super.remove(pointF);
    }

    public VexFwkDocumentCorners(float[] fArr) {
        j.e(fArr, "array");
        if (fArr.length == 8) {
            for (int i2 = 0; i2 < 4; i2++) {
                int i7 = i2 * 2;
                add(new PointF(fArr[i7], fArr[i7 + 1]));
            }
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof PointF)) {
            return false;
        }
        return remove((PointF) obj);
    }

    public VexFwkDocumentCorners(PointF[] pointFArr) {
        j.e(pointFArr, "points");
        if (pointFArr.length == 4) {
            C1200r.B0(this, pointFArr);
            return;
        }
        throw new IllegalStateException("Check failed.");
    }
}
