package com.samsung.android.vexfwk.param;

import A.a;
import com.samsung.android.vexfwk.param.VexFwkWineBoxInfo;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B\u0017\b\u0016\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkWineInfo;", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo;", "<init>", "()V", "array", "(Ljava/util/LinkedList;)V", "toString", "", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkWineInfo extends LinkedList<VexFwkWineBoxInfo> {
    public static final Companion Companion = new Companion((e) null);
    private static final int WINE_ARRAY_SIZE = 4;
    private static final int WINE_MEMBERS_SIZE = 36;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkWineInfo$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkWineInfo;", "<init>", "()V", "WINE_MEMBERS_SIZE", "", "WINE_ARRAY_SIZE", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkWineInfo> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkWineInfo fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            int i2 = byteBuffer.getInt();
            if (i2 * 36 == byteBuffer.remaining()) {
                VexFwkWineInfo vexFwkWineInfo = new VexFwkWineInfo();
                for (int i7 = 0; i7 < i2; i7++) {
                    vexFwkWineInfo.add(new VexFwkWineBoxInfo(byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getFloat(), VexFwkWineBoxInfo.Tag.Companion.toTag(byteBuffer.getInt()), byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt()));
                }
                return vexFwkWineInfo;
            }
            throw new IllegalStateException("Check failed.");
        }

        public ByteBuffer toBuffer(VexFwkWineInfo vexFwkWineInfo) {
            j.e(vexFwkWineInfo, "value");
            ByteBuffer allocateBuffer = allocateBuffer((vexFwkWineInfo.size() * 36) + 4);
            allocateBuffer.putInt(vexFwkWineInfo.size());
            Iterator it = vexFwkWineInfo.iterator();
            while (it.hasNext()) {
                VexFwkWineBoxInfo vexFwkWineBoxInfo = (VexFwkWineBoxInfo) it.next();
                allocateBuffer.putInt(vexFwkWineBoxInfo.getLeft());
                allocateBuffer.putInt(vexFwkWineBoxInfo.getTop());
                allocateBuffer.putInt(vexFwkWineBoxInfo.getRight());
                allocateBuffer.putInt(vexFwkWineBoxInfo.getBottom());
                allocateBuffer.putFloat(vexFwkWineBoxInfo.getScore());
                allocateBuffer.putInt(vexFwkWineBoxInfo.getTag().getValue());
                allocateBuffer.putInt(vexFwkWineBoxInfo.getHeight());
                allocateBuffer.putInt(vexFwkWineBoxInfo.getWidth());
                allocateBuffer.putInt(vexFwkWineBoxInfo.getTrackId());
            }
            return allocateBuffer;
        }
    }

    public VexFwkWineInfo() {
    }

    public /* bridge */ boolean contains(VexFwkWineBoxInfo vexFwkWineBoxInfo) {
        return super.contains(vexFwkWineBoxInfo);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(VexFwkWineBoxInfo vexFwkWineBoxInfo) {
        return super.indexOf(vexFwkWineBoxInfo);
    }

    public /* bridge */ int lastIndexOf(VexFwkWineBoxInfo vexFwkWineBoxInfo) {
        return super.lastIndexOf(vexFwkWineBoxInfo);
    }

    public final /* bridge */ VexFwkWineBoxInfo remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ VexFwkWineBoxInfo removeAt(int i2) {
        return (VexFwkWineBoxInfo) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        Iterator it = iterator();
        j.d(it, "iterator(...)");
        while (it.hasNext()) {
            Object next = it.next();
            j.d(next, "next(...)");
            VexFwkWineBoxInfo vexFwkWineBoxInfo = (VexFwkWineBoxInfo) next;
            int left = vexFwkWineBoxInfo.getLeft();
            int top = vexFwkWineBoxInfo.getTop();
            int right = vexFwkWineBoxInfo.getRight();
            int bottom = vexFwkWineBoxInfo.getBottom();
            float score = vexFwkWineBoxInfo.getScore();
            int value = vexFwkWineBoxInfo.getTag().getValue();
            int height = vexFwkWineBoxInfo.getHeight();
            int width = vexFwkWineBoxInfo.getWidth();
            int trackId = vexFwkWineBoxInfo.getTrackId();
            StringBuilder h5 = a.h(left, top, "left: ", ", top: ", ", right: ");
            N2.j.x(h5, right, ", bottom: ", bottom, ", score: ");
            h5.append(score);
            h5.append(", tag: ");
            h5.append(value);
            h5.append(", height: ");
            N2.j.x(h5, height, ", width: ", width, ", trackId: ");
            h5.append(trackId);
            h5.append(" \n");
            sb2.append(h5.toString());
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public VexFwkWineInfo(LinkedList<VexFwkWineBoxInfo> linkedList) {
        j.e(linkedList, "array");
        addAll(linkedList);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof VexFwkWineBoxInfo)) {
            return false;
        }
        return contains((VexFwkWineBoxInfo) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof VexFwkWineBoxInfo)) {
            return -1;
        }
        return indexOf((VexFwkWineBoxInfo) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof VexFwkWineBoxInfo)) {
            return -1;
        }
        return lastIndexOf((VexFwkWineBoxInfo) obj);
    }

    public /* bridge */ boolean remove(VexFwkWineBoxInfo vexFwkWineBoxInfo) {
        return super.remove(vexFwkWineBoxInfo);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof VexFwkWineBoxInfo)) {
            return false;
        }
        return remove((VexFwkWineBoxInfo) obj);
    }
}
