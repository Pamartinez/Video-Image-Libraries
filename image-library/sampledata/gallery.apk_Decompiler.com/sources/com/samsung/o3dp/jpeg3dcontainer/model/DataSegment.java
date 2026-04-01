package com.samsung.o3dp.jpeg3dcontainer.model;

import com.samsung.o3dp.jpeg3dcontainer.util.ByteUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataSegment implements Segment {
    private static final int NUM_BYTES_OF_DATA_LENGTH_FIELD = 2;
    private final byte[] data;
    private final Marker marker;

    public DataSegment(Marker marker2, byte[] bArr) {
        this.marker = marker2;
        this.data = bArr;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getLength() {
        return Marker.getLength() + this.data.length + 2;
    }

    public Marker getMarker() {
        return this.marker;
    }

    public byte[] toBytes() {
        return ByteUtil.concat(this.marker.toBytes(), ByteUtil.intToByteArray(this.data.length + 2), this.data);
    }
}
