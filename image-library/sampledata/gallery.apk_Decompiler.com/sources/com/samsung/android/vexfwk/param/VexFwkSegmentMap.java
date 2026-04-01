package com.samsung.android.vexfwk.param;

import Tf.a;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkSegmentMap;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkSegmentMap {
    public static final Companion Companion = new Companion((e) null);
    private static final int INT_SIZE = 4;
    private static final int STRING_LENGTH_SIZE = 4;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkSegmentMap$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "", "", "", "<init>", "()V", "INT_SIZE", "STRING_LENGTH_SIZE", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<Map<Integer, ? extends String>> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public Map<Integer, String> fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (byteBuffer.remaining() > 0) {
                int i2 = byteBuffer.getInt();
                byte[] bArr = new byte[byteBuffer.getInt()];
                byteBuffer.get(bArr);
                linkedHashMap.put(Integer.valueOf(i2), new String(bArr, a.f3815a));
            }
            return linkedHashMap;
        }

        public ByteBuffer toBuffer(Map<Integer, String> map) {
            j.e(map, "value");
            int size = map.size() * 8;
            for (Map.Entry next : map.entrySet()) {
                ((Number) next.getKey()).intValue();
                byte[] bytes = ((String) next.getValue()).getBytes(a.f3815a);
                j.d(bytes, "getBytes(...)");
                size += bytes.length;
            }
            ByteBuffer allocateBuffer = allocateBuffer(size);
            for (Map.Entry next2 : map.entrySet()) {
                int intValue = ((Number) next2.getKey()).intValue();
                String str = (String) next2.getValue();
                allocateBuffer.putInt(intValue);
                Charset charset = a.f3815a;
                byte[] bytes2 = str.getBytes(charset);
                j.d(bytes2, "getBytes(...)");
                allocateBuffer.putInt(bytes2.length);
                byte[] bytes3 = str.getBytes(charset);
                j.d(bytes3, "getBytes(...)");
                allocateBuffer.put(bytes3);
            }
            allocateBuffer.flip();
            return allocateBuffer;
        }
    }
}
