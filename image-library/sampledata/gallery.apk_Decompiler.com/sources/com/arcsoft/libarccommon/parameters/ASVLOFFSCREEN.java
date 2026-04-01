package com.arcsoft.libarccommon.parameters;

import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ASVLOFFSCREEN implements Cloneable {
    public static final int ASVL_PAF_DEPTH_U16 = 3074;
    public static final int ASVL_PAF_GRAY = 1793;
    public static final int ASVL_PAF_I420 = 1537;
    public static final int ASVL_PAF_NV12 = 2049;
    public static final int ASVL_PAF_NV21 = 2050;
    public static final int ASVL_PAF_P010_LSB = 3842;
    public static final int ASVL_PAF_P010_MSB = 3841;
    public static final int ASVL_PAF_RGB16_R5G6B5 = 261;
    public static final int ASVL_PAF_RGB24_B8G8R8 = 513;
    public static final int ASVL_PAF_RGB24_R8G8B8 = 516;
    public static final int ASVL_PAF_RGB32_A8R8G8B8 = 772;
    public static final int ASVL_PAF_RGB32_B8G8R8 = 769;
    public static final int ASVL_PAF_RGB32_B8G8R8A8 = 770;
    public static final int ASVL_PAF_RGB32_R8G8B8 = 771;
    public static final int ASVL_PAF_RGB32_R8G8B8A8 = 773;
    public int m_Height;
    public int m_Pitch0;
    public int m_Pitch1;
    public int m_Pitch2;
    public int m_Pitch3;
    public int m_PixelFormat;
    public byte[] m_Plane0;
    public byte[] m_Plane1;
    public byte[] m_Plane2;
    public byte[] m_Plane3;
    public ByteBuffer m_PlaneByteBuffer0;
    public ByteBuffer m_PlaneByteBuffer1;
    public ByteBuffer m_PlaneByteBuffer2;
    public ByteBuffer m_PlaneByteBuffer3;
    public int m_Width;

    public Object clone() {
        ASVLOFFSCREEN asvloffscreen = (ASVLOFFSCREEN) super.clone();
        byte[] bArr = this.m_Plane0;
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            asvloffscreen.m_Plane0 = bArr2;
            System.arraycopy(this.m_Plane0, 0, bArr2, 0, bArr2.length);
        }
        byte[] bArr3 = this.m_Plane1;
        if (bArr3 != null) {
            byte[] bArr4 = new byte[bArr3.length];
            asvloffscreen.m_Plane1 = bArr4;
            System.arraycopy(this.m_Plane1, 0, bArr4, 0, bArr4.length);
        }
        byte[] bArr5 = this.m_Plane2;
        if (bArr5 != null) {
            byte[] bArr6 = new byte[bArr5.length];
            asvloffscreen.m_Plane2 = bArr6;
            System.arraycopy(this.m_Plane2, 0, bArr6, 0, bArr6.length);
        }
        byte[] bArr7 = this.m_Plane3;
        if (bArr7 != null) {
            byte[] bArr8 = new byte[bArr7.length];
            asvloffscreen.m_Plane3 = bArr8;
            System.arraycopy(this.m_Plane3, 0, bArr8, 0, bArr8.length);
        }
        ByteBuffer byteBuffer = this.m_PlaneByteBuffer0;
        if (byteBuffer != null) {
            asvloffscreen.m_PlaneByteBuffer0 = ByteBuffer.allocate(byteBuffer.capacity());
            this.m_PlaneByteBuffer0.rewind();
            asvloffscreen.m_PlaneByteBuffer0.put(this.m_PlaneByteBuffer0);
            this.m_PlaneByteBuffer0.rewind();
            asvloffscreen.m_PlaneByteBuffer0.flip();
        }
        ByteBuffer byteBuffer2 = this.m_PlaneByteBuffer1;
        if (byteBuffer2 != null) {
            asvloffscreen.m_PlaneByteBuffer1 = ByteBuffer.allocate(byteBuffer2.capacity());
            this.m_PlaneByteBuffer1.rewind();
            asvloffscreen.m_PlaneByteBuffer1.put(this.m_PlaneByteBuffer1);
            this.m_PlaneByteBuffer1.rewind();
            asvloffscreen.m_PlaneByteBuffer1.flip();
        }
        ByteBuffer byteBuffer3 = this.m_PlaneByteBuffer2;
        if (byteBuffer3 != null) {
            asvloffscreen.m_PlaneByteBuffer2 = ByteBuffer.allocate(byteBuffer3.capacity());
            this.m_PlaneByteBuffer2.rewind();
            asvloffscreen.m_PlaneByteBuffer2.put(this.m_PlaneByteBuffer2);
            this.m_PlaneByteBuffer2.rewind();
            asvloffscreen.m_PlaneByteBuffer2.flip();
        }
        ByteBuffer byteBuffer4 = this.m_PlaneByteBuffer3;
        if (byteBuffer4 != null) {
            asvloffscreen.m_PlaneByteBuffer3 = ByteBuffer.allocate(byteBuffer4.capacity());
            this.m_PlaneByteBuffer3.rewind();
            asvloffscreen.m_PlaneByteBuffer3.put(this.m_PlaneByteBuffer3);
            this.m_PlaneByteBuffer3.rewind();
            asvloffscreen.m_PlaneByteBuffer3.flip();
        }
        return asvloffscreen;
    }
}
