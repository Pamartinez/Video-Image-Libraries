package Pd;

import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public int f3630a;
    public ByteBuffer b;

    public c(int i2) {
        this.f3630a = i2 + 8;
    }

    public float[] a(int i2) {
        float[] fArr = new float[i2];
        ByteBuffer byteBuffer = this.b;
        byteBuffer.position(this.f3630a);
        byteBuffer.asFloatBuffer().get(fArr);
        this.f3630a = (i2 * 4) + this.f3630a;
        return fArr;
    }

    public int b() {
        ByteBuffer byteBuffer = this.b;
        byteBuffer.position(this.f3630a);
        int i2 = byteBuffer.asIntBuffer().get();
        this.f3630a += 4;
        return i2;
    }
}
