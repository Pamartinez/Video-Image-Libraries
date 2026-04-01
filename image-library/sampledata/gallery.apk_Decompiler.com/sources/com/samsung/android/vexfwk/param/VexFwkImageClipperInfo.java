package com.samsung.android.vexfwk.param;

import android.graphics.Rect;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.vexfwk.imageclipper.VexFwkImageClipperResultCode;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B)\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u001b\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageClipperInfo;", "", "objectsRect", "", "Landroid/graphics/Rect;", "singleRect", "resultCode", "Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode;", "<init>", "([Landroid/graphics/Rect;Landroid/graphics/Rect;Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode;)V", "getObjectsRect", "()[Landroid/graphics/Rect;", "[Landroid/graphics/Rect;", "getSingleRect", "()Landroid/graphics/Rect;", "getResultCode", "()Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageClipperInfo {
    private static final int CLIPPER_INFO_OBJ_SIZE = 4;
    public static final Companion Companion = new Companion((e) null);
    private static final int RECT_SIZE = 16;
    private final Rect[] objectsRect;
    private final VexFwkImageClipperResultCode resultCode;
    private final Rect singleRect;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageClipperInfo$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkImageClipperInfo;", "<init>", "()V", "RECT_SIZE", "", "CLIPPER_INFO_OBJ_SIZE", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "calculateSize", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkImageClipperInfo> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private final int calculateSize(VexFwkImageClipperInfo vexFwkImageClipperInfo) {
            int i2;
            Rect[] objectsRect = vexFwkImageClipperInfo.getObjectsRect();
            if (objectsRect != null) {
                i2 = (objectsRect.length * 16) + 4;
            } else {
                i2 = 4;
            }
            if (vexFwkImageClipperInfo.getSingleRect() != null) {
                i2 += 16;
            }
            return i2 + 4;
        }

        private Companion() {
        }

        public VexFwkImageClipperInfo fromBuffer(ByteBuffer byteBuffer) {
            Rect[] rectArr;
            Rect rect;
            j.e(byteBuffer, "buffer");
            int i2 = byteBuffer.getInt();
            if (i2 == byteBuffer.remaining()) {
                int i7 = byteBuffer.getInt();
                if (i7 > 0) {
                    LinkedList linkedList = new LinkedList();
                    for (int i8 = 0; i8 < i7; i8++) {
                        linkedList.add(new Rect(byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt()));
                    }
                    rectArr = (Rect[]) linkedList.toArray(new Rect[0]);
                    rect = new Rect(byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt());
                } else {
                    rectArr = null;
                    rect = null;
                }
                return new VexFwkImageClipperInfo(rectArr, rect, VexFwkImageClipperResultCode.Companion.fromInt(byteBuffer.getInt()));
            }
            throw new IllegalStateException(N2.j.b(i2, byteBuffer.remaining(), "buffer capacity must be ", ". buffer capacity is ").toString());
        }

        public ByteBuffer toBuffer(VexFwkImageClipperInfo vexFwkImageClipperInfo) {
            j.e(vexFwkImageClipperInfo, "value");
            int calculateSize = calculateSize(vexFwkImageClipperInfo);
            ByteBuffer allocateBuffer = allocateBuffer(calculateSize + 4);
            allocateBuffer.putInt(calculateSize);
            Rect[] objectsRect = vexFwkImageClipperInfo.getObjectsRect();
            if (objectsRect != null) {
                allocateBuffer.putInt(objectsRect.length);
                for (Rect rect : objectsRect) {
                    allocateBuffer.putInt(rect.left);
                    allocateBuffer.putInt(rect.top);
                    allocateBuffer.putInt(rect.right);
                    allocateBuffer.putInt(rect.bottom);
                }
            } else {
                allocateBuffer.putInt(0);
            }
            Rect singleRect = vexFwkImageClipperInfo.getSingleRect();
            if (singleRect != null) {
                allocateBuffer.putInt(singleRect.left);
                allocateBuffer.putInt(singleRect.top);
                allocateBuffer.putInt(singleRect.right);
                allocateBuffer.putInt(singleRect.bottom);
            }
            allocateBuffer.putInt(vexFwkImageClipperInfo.getResultCode().getValue());
            return allocateBuffer;
        }
    }

    public VexFwkImageClipperInfo(Rect[] rectArr, Rect rect, VexFwkImageClipperResultCode vexFwkImageClipperResultCode) {
        j.e(vexFwkImageClipperResultCode, OCRServiceConstant.KEY_RESULT_CODE);
        this.objectsRect = rectArr;
        this.singleRect = rect;
        this.resultCode = vexFwkImageClipperResultCode;
    }

    public final Rect[] getObjectsRect() {
        return this.objectsRect;
    }

    public final VexFwkImageClipperResultCode getResultCode() {
        return this.resultCode;
    }

    public final Rect getSingleRect() {
        return this.singleRect;
    }
}
