package com.samsung.android.gallery.support.library.sef;

import N2.j;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Sdr {
    final Drdh drdh;
    final int drsl;
    final int drsp;
    byte[] packet;

    public Sdr(byte[] bArr) {
        this.drdh = new Drdh(BitOp.toInt(bArr, 0));
        this.drsp = BitOp.toInt(bArr, 4);
        this.drsl = BitOp.toInt(bArr, 8);
    }

    public Sdr pack() {
        this.packet = ByteBuffer.allocate(12).putInt(BitOp.flipInt(this.drdh.value)).putInt(BitOp.flipInt(this.drsp)).putInt(BitOp.flipInt(this.drsl)).array();
        return this;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SDR{");
        sb2.append(this.drdh.dataType);
        sb2.append(',');
        sb2.append(this.drdh.option);
        sb2.append(',');
        sb2.append(this.drsp);
        sb2.append(',');
        return j.e(sb2, this.drsl, '}');
    }

    public Sdr(SefData sefData, int i2) {
        this.drdh = sefData.drdh;
        this.drsp = i2;
        this.drsl = sefData.packetSize;
    }
}
