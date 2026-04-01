package com.samsung.android.gallery.module.xmp;

import android.util.Log;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class XmpDecoder {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Section {
        /* access modifiers changed from: private */
        public final byte[] mData;
        /* access modifiers changed from: private */
        public final int mLength;
        /* access modifiers changed from: private */
        public final int mMarker;

        public /* synthetic */ Section(int i2, int i7, int i8, byte[] bArr) {
            this(i2, i7, bArr);
        }

        private Section(int i2, int i7, byte[] bArr) {
            this.mMarker = i2;
            this.mLength = i7;
            this.mData = bArr;
        }
    }

    static {
        try {
            XMPMetaFactory.getSchemaRegistry().registerNamespace("http://ns.google.com/photos/1.0/panorama/", "GPano");
        } catch (XMPException e) {
            Log.e("XmpDecoder", e.getMessage());
        }
    }

    private static byte[] createByteBuffer(int i2) {
        return new byte[i2];
    }

    public static XMPMeta createXMPMeta() {
        return XMPMetaFactory.create();
    }

    private static Section createXMPSection(XMPMeta xMPMeta) {
        byte[] bufferFromXMPMeta = getBufferFromXMPMeta(xMPMeta);
        if (bufferFromXMPMeta == null) {
            return null;
        }
        int length = bufferFromXMPMeta.length;
        byte[] bArr = new byte[(length + 29)];
        System.arraycopy(MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(), 0, bArr, 0, 29);
        System.arraycopy(bufferFromXMPMeta, 0, bArr, 29, bufferFromXMPMeta.length);
        return new Section(225, length + 31, 0, bArr);
    }

    public static XMPMeta extractXMPMeta(String str) {
        FileInputStream fileInputStream;
        if (str == null || !isJpeg(str)) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(str);
            XMPMeta extractXMPMeta = extractXMPMeta((InputStream) fileInputStream);
            fileInputStream.close();
            return extractXMPMeta;
        } catch (Exception e) {
            Log.e("XmpDecoder", "extractXMPMeta failed. e=" + Logger.toSimpleString((Throwable) e));
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static byte[] getBufferFromXMPMeta(XMPMeta xMPMeta) {
        byte[] bArr;
        XMPException e;
        try {
            SerializeOptions serializeOptions = new SerializeOptions();
            serializeOptions.setUseCompactFormat(true);
            serializeOptions.setOmitPacketWrapper(true);
            bArr = XMPMetaFactory.serializeToBuffer(xMPMeta, serializeOptions);
            try {
                if (bArr.length > 65502) {
                    return null;
                }
                return bArr;
            } catch (XMPException e7) {
                e = e7;
                Log.e("XmpDecoder", "getBufferFromXMPMeta: Serialize xmp failed:\n" + e.getMessage());
                return bArr;
            }
        } catch (XMPException e8) {
            e = e8;
            bArr = null;
            Log.e("XmpDecoder", "getBufferFromXMPMeta: Serialize xmp failed:\n" + e.getMessage());
            return bArr;
        }
    }

    private static Section getSection(int i2, int i7, byte[] bArr) {
        return new Section(i2, i7, 0, bArr);
    }

    private static int getXMPContentEnd(byte[] bArr) {
        for (int length = bArr.length - 1; length >= 1; length--) {
            if (bArr[length] == 62 && bArr[length - 1] != 63) {
                return length + 1;
            }
        }
        return bArr.length;
    }

    private static List<Section> getXmpSections(String str, XMPMeta xMPMeta) {
        FileInputStream fileInputStream;
        List<Section> list = null;
        try {
            fileInputStream = new FileInputStream(str);
            list = insertXMPSection(parse(fileInputStream, false), createXMPSection(xMPMeta));
            fileInputStream.close();
            return list;
        } catch (IOException e) {
            Log.e("XmpDecoder", Logger.getEncodedString(e.getMessage()));
            return list;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static boolean hasXMPHeader(byte[] bArr) {
        if (bArr.length < 29) {
            return false;
        }
        try {
            byte[] bArr2 = new byte[29];
            System.arraycopy(bArr, 0, bArr2, 0, 29);
            return new String(bArr2, "UTF-8").equals(MediaDefs.Meta.XMP.XMP_SIGNATURE);
        } catch (UnsupportedEncodingException e) {
            Log.e("XmpDecoder", "hasXMPHeader:\n" + e.getMessage());
            return false;
        }
    }

    private static List<Section> insertXMPSection(List<Section> list, Section section) {
        if (!(list == null || list.size() <= 1 || section == null)) {
            int i2 = 0;
            while (i2 < list.size()) {
                if (list.get(i2).mMarker != 225 || !hasXMPHeader(list.get(i2).mData)) {
                    i2++;
                } else {
                    list.set(i2, section);
                    return list;
                }
            }
        }
        return null;
    }

    private static boolean isJpeg(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg")) {
            return true;
        }
        return false;
    }

    private static List<Section> parse(InputStream inputStream, boolean z) {
        int read;
        try {
            if (inputStream.read() == 255) {
                if (inputStream.read() == 216) {
                    ArrayList arrayList = new ArrayList();
                    while (true) {
                        int read2 = inputStream.read();
                        if (read2 == -1) {
                            break;
                        } else if (read2 != 255) {
                            return null;
                        } else {
                            while (true) {
                                read = inputStream.read();
                                if (read != 255) {
                                    break;
                                }
                            }
                            if (read == -1) {
                                return null;
                            }
                            if (read != 218) {
                                int read3 = inputStream.read();
                                int read4 = inputStream.read();
                                if (read3 == -1 && read4 == -1) {
                                    return null;
                                }
                                int i2 = (read3 << 8) | read4;
                                if (z) {
                                    if (read != 225) {
                                        inputStream.skip((long) (i2 - 2));
                                    }
                                }
                                int i7 = i2 - 2;
                                byte[] createByteBuffer = createByteBuffer(i7);
                                if (inputStream.read(createByteBuffer, 0, i7) != -1) {
                                    arrayList.add(getSection(read, i2, createByteBuffer));
                                }
                            } else if (!z) {
                                byte[] createByteBuffer2 = createByteBuffer(inputStream.available());
                                if (inputStream.read(createByteBuffer2, 0, createByteBuffer2.length) != -1) {
                                    arrayList.add(getSection(read, -1, createByteBuffer2));
                                    return arrayList;
                                }
                            }
                        }
                    }
                    return arrayList;
                }
            }
            return null;
        } catch (IOException e) {
            Log.e("XmpDecoder", "Could not parse file.\n" + e.getMessage());
            return null;
        }
    }

    private static void writeJpegFile(OutputStream outputStream, List<Section> list) {
        outputStream.write(ScoverState.TYPE_NFC_SMART_COVER);
        outputStream.write(216);
        for (Section next : list) {
            outputStream.write(ScoverState.TYPE_NFC_SMART_COVER);
            outputStream.write(next.mMarker);
            if (next.mLength > 0) {
                int b = next.mLength & ScoverState.TYPE_NFC_SMART_COVER;
                outputStream.write(next.mLength >> 8);
                outputStream.write(b);
            }
            outputStream.write(next.mData);
        }
    }

    public static boolean writeXMPMeta(String str, XMPMeta xMPMeta) {
        List<Section> xmpSections;
        FileOutputStream fileOutputStream;
        if (xMPMeta == null || str == null || !isJpeg(str) || (xmpSections = getXmpSections(str, xMPMeta)) == null || xmpSections.size() <= 0) {
            return false;
        }
        try {
            fileOutputStream = new FileOutputStream(new SecureFile(str));
            writeJpegFile(fileOutputStream, xmpSections);
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            Log.e("XmpDecoder", "writeXMPMeta failed. e=" + Logger.toSimpleString((Throwable) e));
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static XMPMeta extractXMPMeta(InputStream inputStream) {
        List<Section> parse = parse(inputStream, true);
        if (parse == null || parse.size() <= 0) {
            return null;
        }
        for (Section next : parse) {
            if (hasXMPHeader(next.mData)) {
                byte[] createByteBuffer = createByteBuffer(getXMPContentEnd(next.mData) - 29);
                System.arraycopy(next.mData, 29, createByteBuffer, 0, createByteBuffer.length);
                try {
                    return XMPMetaFactory.parseFromBuffer(createByteBuffer);
                } catch (Exception e) {
                    Log.e("XmpDecoder", "XMP parse error:" + Logger.toSimpleString((Throwable) e));
                    return null;
                }
            }
        }
        return null;
    }
}
