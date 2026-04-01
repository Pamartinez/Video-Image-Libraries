package com.samsung.o3dp.jpeg3dcontainer.model;

import com.samsung.o3dp.jpeg3dcontainer.util.ByteUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SosSegment implements Segment {
    private final byte[] image;

    public SosSegment(byte[] bArr) {
        this.image = bArr;
    }

    public byte[] getImage() {
        return this.image;
    }

    public int getLength() {
        return Marker.getLength() + this.image.length;
    }

    public Marker getMarker() {
        return Marker.SOS;
    }

    public byte[] toBytes() {
        return ByteUtil.concat(getMarker().toBytes(), this.image);
    }
}
