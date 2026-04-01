package com.samsung.android.vexfwk.param;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirection;", "", "from", "", "to", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "getTo", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkLanguageDirection {
    public static final Companion Companion = new Companion((e) null);
    private final String from;
    private final String to;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0017¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirection$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirection;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkLanguageDirection> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkLanguageDirection fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            byte[] bArr = new byte[byteBuffer.getInt()];
            byteBuffer.get(bArr);
            Charset charset = StandardCharsets.UTF_8;
            j.d(charset, "UTF_8");
            String str = new String(bArr, charset);
            byte[] bArr2 = new byte[byteBuffer.getInt()];
            byteBuffer.get(bArr2);
            j.d(charset, "UTF_8");
            return new VexFwkLanguageDirection(str, new String(bArr2, charset));
        }

        public ByteBuffer toBuffer(VexFwkLanguageDirection vexFwkLanguageDirection) {
            j.e(vexFwkLanguageDirection, "value");
            String from = vexFwkLanguageDirection.getFrom();
            Charset charset = StandardCharsets.UTF_8;
            j.d(charset, "UTF_8");
            byte[] bytes = from.getBytes(charset);
            j.d(bytes, "getBytes(...)");
            String to = vexFwkLanguageDirection.getTo();
            j.d(charset, "UTF_8");
            byte[] bytes2 = to.getBytes(charset);
            j.d(bytes2, "getBytes(...)");
            ByteBuffer order = allocateBuffer(bytes.length + 8 + bytes2.length).order(ByteOrder.nativeOrder());
            order.putInt(vexFwkLanguageDirection.getFrom().length());
            String from2 = vexFwkLanguageDirection.getFrom();
            j.d(charset, "UTF_8");
            byte[] bytes3 = from2.getBytes(charset);
            j.d(bytes3, "getBytes(...)");
            order.put(bytes3);
            order.putInt(vexFwkLanguageDirection.getTo().length());
            String to2 = vexFwkLanguageDirection.getTo();
            j.d(charset, "UTF_8");
            byte[] bytes4 = to2.getBytes(charset);
            j.d(bytes4, "getBytes(...)");
            order.put(bytes4);
            return order;
        }
    }

    public VexFwkLanguageDirection(String str, String str2) {
        j.e(str, "from");
        j.e(str2, "to");
        this.from = str;
        this.to = str2;
    }

    public static /* synthetic */ VexFwkLanguageDirection copy$default(VexFwkLanguageDirection vexFwkLanguageDirection, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = vexFwkLanguageDirection.from;
        }
        if ((i2 & 2) != 0) {
            str2 = vexFwkLanguageDirection.to;
        }
        return vexFwkLanguageDirection.copy(str, str2);
    }

    public static VexFwkLanguageDirection fromBuffer(ByteBuffer byteBuffer) {
        return Companion.fromBuffer(byteBuffer);
    }

    public static ByteBuffer toBuffer(VexFwkLanguageDirection vexFwkLanguageDirection) {
        return Companion.toBuffer(vexFwkLanguageDirection);
    }

    public final String component1() {
        return this.from;
    }

    public final String component2() {
        return this.to;
    }

    public final VexFwkLanguageDirection copy(String str, String str2) {
        j.e(str, "from");
        j.e(str2, "to");
        return new VexFwkLanguageDirection(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkLanguageDirection)) {
            return false;
        }
        VexFwkLanguageDirection vexFwkLanguageDirection = (VexFwkLanguageDirection) obj;
        if (j.a(this.from, vexFwkLanguageDirection.from) && j.a(this.to, vexFwkLanguageDirection.to)) {
            return true;
        }
        return false;
    }

    public final String getFrom() {
        return this.from;
    }

    public final String getTo() {
        return this.to;
    }

    public int hashCode() {
        return this.to.hashCode() + (this.from.hashCode() * 31);
    }

    public String toString() {
        return N2.j.d("VexFwkLanguageDirection(from=", this.from, ", to=", this.to, ")");
    }
}
