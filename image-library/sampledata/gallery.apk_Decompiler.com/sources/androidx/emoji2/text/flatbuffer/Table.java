package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Table {
    protected ByteBuffer bb;
    protected int bb_pos;
    Utf8 utf8 = Utf8.getDefault();
    private int vtable_size;
    private int vtable_start;

    public int __indirect(int i2) {
        return this.bb.getInt(i2) + i2;
    }

    public int __offset(int i2) {
        if (i2 < this.vtable_size) {
            return this.bb.getShort(this.vtable_start + i2);
        }
        return 0;
    }

    public void __reset(int i2, ByteBuffer byteBuffer) {
        this.bb = byteBuffer;
        if (byteBuffer != null) {
            this.bb_pos = i2;
            int i7 = i2 - byteBuffer.getInt(i2);
            this.vtable_start = i7;
            this.vtable_size = this.bb.getShort(i7);
            return;
        }
        this.bb_pos = 0;
        this.vtable_start = 0;
        this.vtable_size = 0;
    }

    public int __vector(int i2) {
        int i7 = i2 + this.bb_pos;
        return this.bb.getInt(i7) + i7 + 4;
    }

    public int __vector_len(int i2) {
        int i7 = i2 + this.bb_pos;
        return this.bb.getInt(this.bb.getInt(i7) + i7);
    }
}
