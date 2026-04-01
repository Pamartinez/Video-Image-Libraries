package com.arcsoft.libarccommon.utils;

import N2.j;
import android.graphics.Bitmap;
import android.media.Image;
import c0.C0086a;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import i.C0212a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ArcAsvlscreenUtils {
    private static final String TAG = "ArcSoft_ArcAsvlscreenUtils";

    /* renamed from: com.arcsoft.libarccommon.utils.ArcAsvlscreenUtils$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                android.graphics.Bitmap$Config[] r0 = android.graphics.Bitmap.Config.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$graphics$Bitmap$Config = r0
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$android$graphics$Bitmap$Config     // Catch:{ NoSuchFieldError -> 0x001d }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$android$graphics$Bitmap$Config     // Catch:{ NoSuchFieldError -> 0x0028 }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ALPHA_8     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arcsoft.libarccommon.utils.ArcAsvlscreenUtils.AnonymousClass1.<clinit>():void");
        }
    }

    public static void ArcAsvlScreenAlloc(ASVLOFFSCREEN asvloffscreen) {
        if (asvloffscreen != null) {
            int i2 = asvloffscreen.m_PixelFormat;
            if (!(i2 == 261 || i2 == 513 || i2 == 516)) {
                if (i2 == 1537) {
                    int i7 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                    int i8 = i7 / 4;
                    asvloffscreen.m_Plane0 = new byte[i7];
                    asvloffscreen.m_Plane1 = new byte[i8];
                    asvloffscreen.m_Plane2 = new byte[i8];
                    return;
                } else if (i2 != 3074) {
                    if (i2 == 2049 || i2 == 2050 || i2 == 3841 || i2 == 3842) {
                        int i10 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                        asvloffscreen.m_Plane0 = new byte[i10];
                        asvloffscreen.m_Plane1 = new byte[(i10 / 2)];
                        return;
                    }
                    switch (i2) {
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8:
                            break;
                        default:
                            return;
                    }
                }
            }
            asvloffscreen.m_Plane0 = new byte[(asvloffscreen.m_Pitch0 * asvloffscreen.m_Height)];
        }
    }

    public static ASVLOFFSCREEN ArcAsvlScreenCopy(ASVLOFFSCREEN asvloffscreen) {
        if (asvloffscreen == null) {
            return null;
        }
        try {
            new ASVLOFFSCREEN();
            return (ASVLOFFSCREEN) asvloffscreen.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void ArcAsvlScreenFree(ASVLOFFSCREEN asvloffscreen) {
        if (asvloffscreen != null) {
            asvloffscreen.m_Plane0 = null;
            asvloffscreen.m_Plane1 = null;
            asvloffscreen.m_Plane2 = null;
            asvloffscreen.m_Plane3 = null;
        }
    }

    public static Bitmap buildAsvlscreenToBitmap(ASVLOFFSCREEN asvloffscreen) {
        Bitmap bitmap = null;
        if (asvloffscreen == null) {
            ArcCommonLog.e(TAG, "ERROR! res: 2");
            return null;
        }
        int i2 = asvloffscreen.m_Width;
        int i7 = asvloffscreen.m_Height;
        int i8 = asvloffscreen.m_PixelFormat;
        if (i8 == 261) {
            bitmap = Bitmap.createBitmap(i2, i7, Bitmap.Config.RGB_565);
        } else if (i8 == 773) {
            bitmap = Bitmap.createBitmap(i2, i7, Bitmap.Config.ARGB_8888);
        } else if (i8 != 1793) {
            ArcCommonLog.e(TAG, "unsupport bitmap format ...");
        } else {
            bitmap = Bitmap.createBitmap(i2, i7, Bitmap.Config.ALPHA_8);
        }
        byte[] bArr = asvloffscreen.m_Plane0;
        if (!(bArr == null || bitmap == null)) {
            bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
        }
        return bitmap;
    }

    public static void buildBitmapToAsvlscreen(Bitmap bitmap, ASVLOFFSCREEN asvloffscreen) {
        if (bitmap == null || asvloffscreen == null) {
            ArcCommonLog.e(TAG, "ERROR! res: 2");
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int rowBytes = bitmap.getRowBytes();
        asvloffscreen.m_Width = width;
        asvloffscreen.m_Height = height;
        asvloffscreen.m_Pitch0 = rowBytes;
        asvloffscreen.m_Pitch1 = 0;
        asvloffscreen.m_Pitch2 = 0;
        asvloffscreen.m_Pitch3 = 0;
        int i2 = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[bitmap.getConfig().ordinal()];
        if (i2 == 1) {
            asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8;
        } else if (i2 == 2) {
            asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_RGB16_R5G6B5;
        } else if (i2 != 3) {
            ArcCommonLog.e(TAG, "unsupport bitmap format ...");
        } else {
            asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_GRAY;
        }
        ByteBuffer allocate = ByteBuffer.allocate(rowBytes * height);
        bitmap.copyPixelsToBuffer(allocate);
        asvloffscreen.m_Plane0 = allocate.array();
        asvloffscreen.m_Plane1 = null;
        asvloffscreen.m_Plane2 = null;
        asvloffscreen.m_Plane3 = null;
    }

    public static void buildImageToAsvlscreen(Image image, ASVLOFFSCREEN asvloffscreen) {
        int width = image.getWidth();
        int height = image.getHeight();
        int format = image.getFormat();
        if (format != 32) {
            if (format == 35) {
                Image.Plane[] planes = image.getPlanes();
                if (planes == null || planes.length >= 3) {
                    Image.Plane plane = planes[0];
                    ByteBuffer buffer = plane.getBuffer();
                    int rowStride = plane.getRowStride();
                    int height2 = image.getHeight();
                    int pixelStride = planes[2].getPixelStride();
                    if (1 == pixelStride) {
                        ByteBuffer buffer2 = planes[1].getBuffer();
                        ByteBuffer buffer3 = planes[2].getBuffer();
                        int rowStride2 = planes[1].getRowStride();
                        int rowStride3 = planes[2].getRowStride();
                        if (buffer != null && buffer2 != null && buffer3 != null) {
                            buffer.rewind();
                            buffer2.rewind();
                            buffer3.rewind();
                            int i2 = rowStride * height2;
                            int i7 = (rowStride2 * height2) / 2;
                            int i8 = (height2 * rowStride3) / 2;
                            asvloffscreen.m_Width = width;
                            asvloffscreen.m_Height = height;
                            asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_I420;
                            asvloffscreen.m_Pitch0 = rowStride;
                            asvloffscreen.m_Pitch1 = rowStride2;
                            asvloffscreen.m_Pitch2 = rowStride3;
                            byte[] bArr = asvloffscreen.m_Plane0;
                            if (bArr == null || bArr.length != i2) {
                                asvloffscreen.m_Plane0 = new byte[i2];
                            }
                            buffer.get(asvloffscreen.m_Plane0, 0, buffer.remaining());
                            byte[] bArr2 = asvloffscreen.m_Plane1;
                            if (bArr2 == null || bArr2.length != i7) {
                                asvloffscreen.m_Plane1 = new byte[i7];
                            }
                            buffer2.get(asvloffscreen.m_Plane1, 0, buffer2.remaining());
                            byte[] bArr3 = asvloffscreen.m_Plane2;
                            if (bArr3 == null || bArr3.length != i8) {
                                asvloffscreen.m_Plane2 = new byte[i8];
                            }
                            buffer3.get(asvloffscreen.m_Plane2, 0, buffer3.remaining());
                            return;
                        }
                        return;
                    } else if (2 == pixelStride) {
                        planes[1].getBuffer();
                        ByteBuffer buffer4 = planes[2].getBuffer();
                        planes[1].getRowStride();
                        int rowStride4 = planes[2].getRowStride();
                        if (buffer != null && buffer4 != null) {
                            int i10 = rowStride * height2;
                            int i11 = (height2 * rowStride4) / 2;
                            buffer.rewind();
                            buffer4.rewind();
                            asvloffscreen.m_Width = width;
                            asvloffscreen.m_Height = height;
                            asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_NV21;
                            asvloffscreen.m_Pitch0 = rowStride;
                            asvloffscreen.m_Pitch1 = rowStride4;
                            byte[] bArr4 = asvloffscreen.m_Plane0;
                            if (bArr4 == null || bArr4.length != i10) {
                                asvloffscreen.m_Plane0 = new byte[i10];
                            }
                            buffer.get(asvloffscreen.m_Plane0, 0, buffer.remaining());
                            byte[] bArr5 = asvloffscreen.m_Plane1;
                            if (bArr5 == null || bArr5.length != i11) {
                                asvloffscreen.m_Plane1 = new byte[i11];
                            }
                            buffer4.get(asvloffscreen.m_Plane1, 0, buffer4.remaining());
                            return;
                        }
                        return;
                    } else {
                        ArcCommonLog.e(TAG, "unsupport image format 1 ...");
                        return;
                    }
                } else {
                    return;
                }
            } else if (format != 1144402265) {
                return;
            }
        }
        Image.Plane[] planes2 = image.getPlanes();
        if (planes2 == null || planes2.length >= 1) {
            int rowStride5 = planes2[0].getRowStride();
            ByteBuffer buffer5 = planes2[0].getBuffer();
            if (buffer5 != null) {
                buffer5.rewind();
                asvloffscreen.m_Width = width;
                asvloffscreen.m_Height = height;
                asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_DEPTH_U16;
                asvloffscreen.m_Pitch0 = rowStride5;
                int i12 = rowStride5 * height;
                byte[] bArr6 = asvloffscreen.m_Plane0;
                if (bArr6 == null || bArr6.length != i12) {
                    asvloffscreen.m_Plane0 = new byte[i12];
                }
                buffer5.get(asvloffscreen.m_Plane0, 0, buffer5.remaining());
            }
        }
    }

    public static void buildImageToAsvlscreen2(Image image, ASVLOFFSCREEN asvloffscreen) {
        int width = image.getWidth();
        int height = image.getHeight();
        int format = image.getFormat();
        if (format != 32) {
            if (format == 35) {
                Image.Plane[] planes = image.getPlanes();
                if (planes == null || planes.length >= 3) {
                    Image.Plane plane = planes[0];
                    Image.Plane plane2 = planes[1];
                    Image.Plane plane3 = planes[2];
                    int rowStride = plane.getRowStride();
                    image.getHeight();
                    ByteBuffer buffer = planes[0].getBuffer();
                    int pixelStride = planes[2].getPixelStride();
                    if (1 == pixelStride) {
                        ByteBuffer buffer2 = planes[1].getBuffer();
                        ByteBuffer buffer3 = planes[2].getBuffer();
                        int rowStride2 = planes[1].getRowStride();
                        int rowStride3 = planes[2].getRowStride();
                        if (buffer != null && buffer2 != null && buffer3 != null) {
                            buffer.rewind();
                            buffer2.rewind();
                            buffer3.rewind();
                            asvloffscreen.m_Width = width;
                            asvloffscreen.m_Height = height;
                            asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_I420;
                            asvloffscreen.m_Pitch0 = rowStride;
                            asvloffscreen.m_Pitch1 = rowStride2;
                            asvloffscreen.m_Pitch2 = rowStride3;
                            asvloffscreen.m_PlaneByteBuffer0 = buffer;
                            asvloffscreen.m_PlaneByteBuffer1 = buffer2;
                            asvloffscreen.m_PlaneByteBuffer2 = buffer3;
                            return;
                        }
                        return;
                    } else if (2 == pixelStride) {
                        planes[1].getBuffer();
                        ByteBuffer buffer4 = planes[2].getBuffer();
                        planes[1].getRowStride();
                        int rowStride4 = planes[2].getRowStride();
                        if (buffer != null && buffer4 != null) {
                            buffer.rewind();
                            buffer4.rewind();
                            asvloffscreen.m_Width = width;
                            asvloffscreen.m_Height = height;
                            asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_NV21;
                            asvloffscreen.m_Pitch0 = rowStride;
                            asvloffscreen.m_Pitch1 = rowStride4;
                            asvloffscreen.m_PlaneByteBuffer0 = buffer;
                            asvloffscreen.m_PlaneByteBuffer1 = buffer4;
                            return;
                        }
                        return;
                    } else {
                        ArcCommonLog.e(TAG, "unsupport image format 2...");
                        return;
                    }
                } else {
                    return;
                }
            } else if (format != 1144402265) {
                return;
            }
        }
        Image.Plane[] planes2 = image.getPlanes();
        if (planes2 == null || planes2.length >= 1) {
            int rowStride5 = planes2[0].getRowStride();
            ByteBuffer buffer5 = planes2[0].getBuffer();
            if (buffer5 != null) {
                buffer5.rewind();
                asvloffscreen.m_Width = width;
                asvloffscreen.m_Height = height;
                asvloffscreen.m_PixelFormat = ASVLOFFSCREEN.ASVL_PAF_DEPTH_U16;
                asvloffscreen.m_Pitch0 = rowStride5;
                asvloffscreen.m_PlaneByteBuffer0 = buffer5;
            }
        }
    }

    public static void dumpAsvlscreen(ASVLOFFSCREEN asvloffscreen, String str, String str2, boolean z) {
        String str3;
        if (asvloffscreen != null && str != null && str2 != null) {
            File file = new File(str);
            if (file.exists() || file.mkdirs()) {
                int i2 = asvloffscreen.m_PixelFormat;
                if (i2 == 261) {
                    StringBuilder s = C0212a.s(str);
                    j.z(s, File.separator, str2, "_");
                    s.append(asvloffscreen.m_Width * 2);
                    s.append("x");
                    s.append(asvloffscreen.m_Height);
                    s.append("_");
                    s.append(asvloffscreen.m_Width);
                    s.append("x");
                    str3 = C0086a.l(s, asvloffscreen.m_Height, ".RGB565");
                } else if (i2 == 513 || i2 == 516) {
                    StringBuilder s5 = C0212a.s(str);
                    j.z(s5, File.separator, str2, "_");
                    s5.append(asvloffscreen.m_Width * 3);
                    s5.append("x");
                    s5.append(asvloffscreen.m_Height);
                    s5.append("_");
                    s5.append(asvloffscreen.m_Width);
                    s5.append("x");
                    str3 = C0086a.l(s5, asvloffscreen.m_Height, ".RGB24");
                } else if (i2 == 1537) {
                    StringBuilder s6 = C0212a.s(str);
                    j.z(s6, File.separator, str2, "_");
                    s6.append(asvloffscreen.m_Width);
                    s6.append("x");
                    s6.append(asvloffscreen.m_Height);
                    s6.append("_");
                    s6.append(asvloffscreen.m_Pitch0);
                    s6.append("x");
                    str3 = C0086a.l(s6, asvloffscreen.m_Height, ".I420");
                } else if (i2 == 1793) {
                    StringBuilder s10 = C0212a.s(str);
                    j.z(s10, File.separator, str2, "_");
                    s10.append(asvloffscreen.m_Width);
                    s10.append("x");
                    s10.append(asvloffscreen.m_Height);
                    s10.append("_");
                    s10.append(asvloffscreen.m_Pitch0);
                    s10.append("x");
                    str3 = C0086a.l(s10, asvloffscreen.m_Height, ".GRAY");
                } else if (i2 == 3074) {
                    StringBuilder s11 = C0212a.s(str);
                    j.z(s11, File.separator, str2, "_");
                    s11.append(asvloffscreen.m_Width);
                    s11.append("x");
                    s11.append(asvloffscreen.m_Height);
                    s11.append("_");
                    s11.append(asvloffscreen.m_Pitch0);
                    s11.append("x");
                    str3 = C0086a.l(s11, asvloffscreen.m_Height, ".RAW");
                } else if (i2 == 2049) {
                    StringBuilder s12 = C0212a.s(str);
                    j.z(s12, File.separator, str2, "_");
                    s12.append(asvloffscreen.m_Width);
                    s12.append("x");
                    s12.append(asvloffscreen.m_Height);
                    s12.append("_");
                    s12.append(asvloffscreen.m_Pitch0);
                    s12.append("x");
                    str3 = C0086a.l(s12, asvloffscreen.m_Height, ".NV12");
                } else if (i2 == 2050) {
                    StringBuilder s13 = C0212a.s(str);
                    j.z(s13, File.separator, str2, "_");
                    s13.append(asvloffscreen.m_Width);
                    s13.append("x");
                    s13.append(asvloffscreen.m_Height);
                    s13.append("_");
                    s13.append(asvloffscreen.m_Pitch0);
                    s13.append("x");
                    str3 = C0086a.l(s13, asvloffscreen.m_Height, ".NV21");
                } else if (i2 == 3841 || i2 == 3842) {
                    StringBuilder s14 = C0212a.s(str);
                    j.z(s14, File.separator, str2, "_");
                    s14.append(asvloffscreen.m_Width);
                    s14.append("x");
                    s14.append(asvloffscreen.m_Height);
                    s14.append("_");
                    s14.append(asvloffscreen.m_Pitch0);
                    s14.append("x");
                    str3 = C0086a.l(s14, asvloffscreen.m_Height, ".P010");
                } else {
                    switch (i2) {
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8:
                            StringBuilder s15 = C0212a.s(str);
                            j.z(s15, File.separator, str2, "_");
                            s15.append(asvloffscreen.m_Width * 4);
                            s15.append("x");
                            s15.append(asvloffscreen.m_Height);
                            s15.append("_");
                            s15.append(asvloffscreen.m_Width);
                            s15.append("x");
                            str3 = C0086a.l(s15, asvloffscreen.m_Height, ".RGB32");
                            break;
                        default:
                            return;
                    }
                }
                dumpAsvlscreen(asvloffscreen, str3, z);
            }
        }
    }

    public static void dumpAsvlscreenWithoutExtraPitch(ASVLOFFSCREEN asvloffscreen, String str, String str2, boolean z) {
        String str3;
        if (asvloffscreen != null && str != null && str2 != null) {
            File file = new File(str);
            if (file.exists() || file.mkdirs()) {
                int i2 = asvloffscreen.m_PixelFormat;
                if (i2 == 261) {
                    StringBuilder s = C0212a.s(str);
                    j.z(s, File.separator, str2, "_");
                    s.append(asvloffscreen.m_Width * 2);
                    s.append("x");
                    s.append(asvloffscreen.m_Height);
                    s.append("_");
                    s.append(asvloffscreen.m_Width);
                    s.append("x");
                    str3 = C0086a.l(s, asvloffscreen.m_Height, ".RGB565");
                } else if (i2 == 513 || i2 == 516) {
                    StringBuilder s5 = C0212a.s(str);
                    j.z(s5, File.separator, str2, "_");
                    s5.append(asvloffscreen.m_Width * 3);
                    s5.append("x");
                    s5.append(asvloffscreen.m_Height);
                    s5.append("_");
                    s5.append(asvloffscreen.m_Width);
                    s5.append("x");
                    str3 = C0086a.l(s5, asvloffscreen.m_Height, ".RGB24");
                } else if (i2 == 1537) {
                    StringBuilder s6 = C0212a.s(str);
                    j.z(s6, File.separator, str2, "_");
                    s6.append(asvloffscreen.m_Width);
                    s6.append("x");
                    s6.append(asvloffscreen.m_Height);
                    s6.append("_");
                    s6.append(asvloffscreen.m_Width);
                    s6.append("x");
                    str3 = C0086a.l(s6, asvloffscreen.m_Height, ".I420");
                } else if (i2 == 1793) {
                    StringBuilder s10 = C0212a.s(str);
                    j.z(s10, File.separator, str2, "_");
                    s10.append(asvloffscreen.m_Width);
                    s10.append("x");
                    s10.append(asvloffscreen.m_Height);
                    s10.append("_");
                    s10.append(asvloffscreen.m_Width);
                    s10.append("x");
                    str3 = C0086a.l(s10, asvloffscreen.m_Height, ".GRAY");
                } else if (i2 == 3074) {
                    StringBuilder s11 = C0212a.s(str);
                    j.z(s11, File.separator, str2, "_");
                    s11.append(asvloffscreen.m_Width);
                    s11.append("x");
                    s11.append(asvloffscreen.m_Height);
                    s11.append("_");
                    s11.append(asvloffscreen.m_Width * 2);
                    s11.append("x");
                    str3 = C0086a.l(s11, asvloffscreen.m_Height, ".RAW");
                } else if (i2 == 2049) {
                    StringBuilder s12 = C0212a.s(str);
                    j.z(s12, File.separator, str2, "_");
                    s12.append(asvloffscreen.m_Width);
                    s12.append("x");
                    s12.append(asvloffscreen.m_Height);
                    s12.append("_");
                    s12.append(asvloffscreen.m_Width);
                    s12.append("x");
                    str3 = C0086a.l(s12, asvloffscreen.m_Height, ".NV12");
                } else if (i2 == 2050) {
                    StringBuilder s13 = C0212a.s(str);
                    j.z(s13, File.separator, str2, "_");
                    s13.append(asvloffscreen.m_Width);
                    s13.append("x");
                    s13.append(asvloffscreen.m_Height);
                    s13.append("_");
                    s13.append(asvloffscreen.m_Width);
                    s13.append("x");
                    str3 = C0086a.l(s13, asvloffscreen.m_Height, ".NV21");
                } else if (i2 == 3841 || i2 == 3842) {
                    StringBuilder s14 = C0212a.s(str);
                    j.z(s14, File.separator, str2, "_");
                    s14.append(asvloffscreen.m_Width);
                    s14.append("x");
                    s14.append(asvloffscreen.m_Height);
                    s14.append("_");
                    s14.append(asvloffscreen.m_Width * 2);
                    s14.append("x");
                    str3 = C0086a.l(s14, asvloffscreen.m_Height, ".P010");
                } else {
                    switch (i2) {
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8:
                            StringBuilder s15 = C0212a.s(str);
                            j.z(s15, File.separator, str2, "_");
                            s15.append(asvloffscreen.m_Width * 4);
                            s15.append("x");
                            s15.append(asvloffscreen.m_Height);
                            s15.append("_");
                            s15.append(asvloffscreen.m_Width);
                            s15.append("x");
                            str3 = C0086a.l(s15, asvloffscreen.m_Height, ".RGB32");
                            break;
                        default:
                            return;
                    }
                }
                dumpAsvlscreenWithoutExtraPitch(asvloffscreen, str3, z);
            }
        }
    }

    public static ArrayList<String> getInfoFromAsvlName(String str) {
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf != -1) {
            name = name.substring(0, lastIndexOf);
        }
        Matcher matcher = Pattern.compile("\\d+(?:\\.\\d+)?").matcher(name);
        ArrayList<String> arrayList = new ArrayList<>();
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public static void readAsvlscreenFromFile(String str, ASVLOFFSCREEN asvloffscreen) {
        File file = new File(str);
        if (file.exists() && !file.isDirectory() && asvloffscreen != null) {
            try {
                byte[] readAllBytes = Files.readAllBytes(file.toPath());
                int i2 = asvloffscreen.m_PixelFormat;
                if (!(i2 == 261 || i2 == 513 || i2 == 516)) {
                    if (i2 == 1537) {
                        int i7 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                        asvloffscreen.m_Plane0 = Arrays.copyOfRange(readAllBytes, 0, i7);
                        int i8 = (i7 / 4) + i7;
                        asvloffscreen.m_Plane1 = Arrays.copyOfRange(readAllBytes, i7, i8);
                        asvloffscreen.m_Plane2 = Arrays.copyOfRange(readAllBytes, i8, (i7 * 3) / 2);
                        return;
                    } else if (!(i2 == 1793 || i2 == 3074)) {
                        if (i2 == 2049 || i2 == 2050 || i2 == 3841 || i2 == 3842) {
                            int i10 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                            asvloffscreen.m_Plane0 = Arrays.copyOfRange(readAllBytes, 0, i10);
                            asvloffscreen.m_Plane1 = Arrays.copyOfRange(readAllBytes, i10, (i10 * 3) / 2);
                            return;
                        }
                        switch (i2) {
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8:
                                break;
                            default:
                                return;
                        }
                    }
                }
                asvloffscreen.m_Plane0 = readAllBytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readAsvlscreenFromFile2(String str, ASVLOFFSCREEN asvloffscreen) {
        File file = new File(str);
        if (file.exists() && !file.isDirectory() && asvloffscreen != null) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                int i2 = asvloffscreen.m_PixelFormat;
                if (!(i2 == 261 || i2 == 513 || i2 == 516)) {
                    if (i2 == 1537) {
                        int i7 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                        byte[] bArr = new byte[i7];
                        randomAccessFile.seek(0);
                        randomAccessFile.readFully(bArr);
                        asvloffscreen.m_Plane0 = bArr;
                        byte[] bArr2 = new byte[(i7 / 4)];
                        randomAccessFile.seek((long) i7);
                        randomAccessFile.readFully(bArr2);
                        asvloffscreen.m_Plane1 = bArr2;
                        byte[] bArr3 = new byte[(i7 / 4)];
                        randomAccessFile.seek((long) (i7 + (i7 / 4)));
                        randomAccessFile.readFully(bArr3);
                        asvloffscreen.m_Plane2 = bArr3;
                        return;
                    } else if (!(i2 == 1793 || i2 == 3074)) {
                        if (i2 == 2049 || i2 == 2050 || i2 == 3841 || i2 == 3842) {
                            int i8 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                            byte[] bArr4 = new byte[i8];
                            randomAccessFile.seek(0);
                            randomAccessFile.readFully(bArr4);
                            asvloffscreen.m_Plane0 = bArr4;
                            byte[] bArr5 = new byte[(i8 / 2)];
                            randomAccessFile.seek((long) i8);
                            randomAccessFile.readFully(bArr5);
                            asvloffscreen.m_Plane1 = bArr5;
                            return;
                        }
                        switch (i2) {
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8:
                                break;
                            default:
                                return;
                        }
                    }
                }
                byte[] bArr6 = new byte[(asvloffscreen.m_Pitch0 * asvloffscreen.m_Height)];
                randomAccessFile.seek(0);
                randomAccessFile.readFully(bArr6);
                asvloffscreen.m_Plane0 = bArr6;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readAsvlscreenFromHugeFile(String str, long j2, ASVLOFFSCREEN asvloffscreen) {
        File file = new File(str);
        if (file.exists() && !file.isDirectory() && asvloffscreen != null && 0 <= j2) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                int i2 = asvloffscreen.m_PixelFormat;
                if (!(i2 == 261 || i2 == 513 || i2 == 516)) {
                    if (i2 == 1537) {
                        int i7 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                        int i8 = i7 / 4;
                        long j3 = j2 * ((long) ((i7 * 3) / 2));
                        byte[] bArr = new byte[i7];
                        randomAccessFile.seek(j3);
                        randomAccessFile.readFully(bArr);
                        asvloffscreen.m_Plane0 = bArr;
                        byte[] bArr2 = new byte[i8];
                        long j8 = j3 + ((long) i7);
                        randomAccessFile.seek(j8);
                        randomAccessFile.readFully(bArr2);
                        asvloffscreen.m_Plane1 = bArr2;
                        byte[] bArr3 = new byte[i8];
                        randomAccessFile.seek(j8 + ((long) i8));
                        randomAccessFile.readFully(bArr3);
                        asvloffscreen.m_Plane2 = bArr3;
                        return;
                    } else if (!(i2 == 1793 || i2 == 3074)) {
                        if (i2 == 2049 || i2 == 2050 || i2 == 3841 || i2 == 3842) {
                            int i10 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                            long j10 = j2 * ((long) ((i10 * 3) / 2));
                            byte[] bArr4 = new byte[i10];
                            randomAccessFile.seek(j10);
                            randomAccessFile.readFully(bArr4);
                            asvloffscreen.m_Plane0 = bArr4;
                            byte[] bArr5 = new byte[(i10 / 2)];
                            randomAccessFile.seek(j10 + ((long) i10));
                            randomAccessFile.readFully(bArr5);
                            asvloffscreen.m_Plane1 = bArr5;
                            return;
                        }
                        switch (i2) {
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8:
                            case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8:
                                break;
                            default:
                                return;
                        }
                    }
                }
                int i11 = asvloffscreen.m_Pitch0 * asvloffscreen.m_Height;
                byte[] bArr6 = new byte[i11];
                randomAccessFile.seek(j2 * ((long) i11));
                randomAccessFile.readFully(bArr6);
                asvloffscreen.m_Plane0 = bArr6;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dumpAsvlscreen(ASVLOFFSCREEN asvloffscreen, String str, boolean z) {
        if (asvloffscreen != null && str != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str), z);
                int i2 = asvloffscreen.m_PixelFormat;
                int i7 = 0;
                if (i2 == 261) {
                    byte[] bArr = new byte[(asvloffscreen.m_Width * 2 * asvloffscreen.m_Height)];
                    while (i7 < asvloffscreen.m_Height) {
                        int i8 = asvloffscreen.m_Width;
                        System.arraycopy(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i7, bArr, i7 * i8 * 2, i8 * 2);
                        i7++;
                    }
                    fileOutputStream.write(bArr);
                } else if (i2 == 513 || i2 == 516) {
                    byte[] bArr2 = new byte[(asvloffscreen.m_Width * 3 * asvloffscreen.m_Height)];
                    while (i7 < asvloffscreen.m_Height) {
                        int i10 = asvloffscreen.m_Width;
                        System.arraycopy(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i7, bArr2, i7 * i10 * 3, i10 * 3);
                        i7++;
                    }
                    fileOutputStream.write(bArr2);
                } else if (i2 == 1537) {
                    fileOutputStream.write(asvloffscreen.m_Plane0);
                    fileOutputStream.write(asvloffscreen.m_Plane1);
                    fileOutputStream.write(asvloffscreen.m_Plane2);
                } else if (i2 == 1793 || i2 == 3074) {
                    fileOutputStream.write(asvloffscreen.m_Plane0);
                } else if (i2 == 2049 || i2 == 2050 || i2 == 3841 || i2 == 3842) {
                    fileOutputStream.write(asvloffscreen.m_Plane0);
                    fileOutputStream.write(asvloffscreen.m_Plane1);
                } else {
                    switch (i2) {
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8:
                            byte[] bArr3 = new byte[(asvloffscreen.m_Width * 4 * asvloffscreen.m_Height)];
                            while (i7 < asvloffscreen.m_Height) {
                                int i11 = asvloffscreen.m_Width;
                                System.arraycopy(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i7, bArr3, i7 * i11 * 4, i11 * 4);
                                i7++;
                            }
                            fileOutputStream.write(bArr3);
                            break;
                        default:
                            return;
                    }
                }
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        }
    }

    public static void dumpAsvlscreenWithoutExtraPitch(ASVLOFFSCREEN asvloffscreen, String str, boolean z) {
        if (asvloffscreen != null && str != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str), z);
                int i2 = asvloffscreen.m_PixelFormat;
                int i7 = 0;
                if (i2 == 261) {
                    int i8 = asvloffscreen.m_Width * 2;
                    byte[] bArr = new byte[(asvloffscreen.m_Height * i8)];
                    while (i7 < asvloffscreen.m_Height) {
                        System.arraycopy(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i7, bArr, i7 * i8, i8);
                        i7++;
                    }
                    fileOutputStream.write(bArr);
                } else if (i2 == 513 || i2 == 516) {
                    int i10 = asvloffscreen.m_Width * 3;
                    byte[] bArr2 = new byte[(asvloffscreen.m_Height * i10)];
                    while (i7 < asvloffscreen.m_Height) {
                        System.arraycopy(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i7, bArr2, i7 * i10, i10);
                        i7++;
                    }
                    fileOutputStream.write(bArr2);
                } else if (i2 == 1537) {
                    int i11 = asvloffscreen.m_Width;
                    for (int i12 = 0; i12 < asvloffscreen.m_Height; i12++) {
                        fileOutputStream.write(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i12, i11);
                    }
                    for (int i13 = 0; i13 < asvloffscreen.m_Height / 2; i13++) {
                        fileOutputStream.write(asvloffscreen.m_Plane1, asvloffscreen.m_Pitch1 * i13, i11 / 2);
                    }
                    while (i7 < asvloffscreen.m_Height / 2) {
                        fileOutputStream.write(asvloffscreen.m_Plane2, asvloffscreen.m_Pitch2 * i7, i11 / 2);
                        i7++;
                    }
                } else if (i2 == 1793) {
                    int i14 = asvloffscreen.m_Width;
                    while (i7 < asvloffscreen.m_Height) {
                        fileOutputStream.write(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i7, i14);
                        i7++;
                    }
                } else if (i2 == 3074) {
                    int i15 = asvloffscreen.m_Width * 2;
                    while (i7 < asvloffscreen.m_Height) {
                        fileOutputStream.write(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i7, i15);
                        i7++;
                    }
                } else if (i2 == 2049 || i2 == 2050) {
                    int i16 = asvloffscreen.m_Width;
                    for (int i17 = 0; i17 < asvloffscreen.m_Height; i17++) {
                        fileOutputStream.write(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i17, i16);
                    }
                    while (i7 < asvloffscreen.m_Height / 2) {
                        fileOutputStream.write(asvloffscreen.m_Plane1, asvloffscreen.m_Pitch1 * i7, i16);
                        i7++;
                    }
                } else if (i2 == 3841 || i2 == 3842) {
                    int i18 = asvloffscreen.m_Width * 2;
                    for (int i19 = 0; i19 < asvloffscreen.m_Height; i19++) {
                        fileOutputStream.write(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i19, i18);
                    }
                    while (i7 < asvloffscreen.m_Height / 2) {
                        fileOutputStream.write(asvloffscreen.m_Plane1, asvloffscreen.m_Pitch1 * i7, i18);
                        i7++;
                    }
                } else {
                    switch (i2) {
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_A8R8G8B8:
                        case ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8A8:
                            int i20 = asvloffscreen.m_Width * 4;
                            byte[] bArr3 = new byte[(asvloffscreen.m_Height * i20)];
                            while (i7 < asvloffscreen.m_Height) {
                                System.arraycopy(asvloffscreen.m_Plane0, asvloffscreen.m_Pitch0 * i7, bArr3, i7 * i20, i20);
                                i7++;
                            }
                            fileOutputStream.write(bArr3);
                            break;
                        default:
                            return;
                    }
                }
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        }
    }
}
