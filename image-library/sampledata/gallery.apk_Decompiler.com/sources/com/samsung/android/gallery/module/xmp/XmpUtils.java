package com.samsung.android.gallery.module.xmp;

import A.a;
import android.text.TextUtils;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.properties.XMPProperty;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.XmpType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.JpegParser;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import xa.C0715a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class XmpUtils {
    static final ArrayList<XmpType> EMPTY = new ArrayList<>();
    static final ConcurrentHashMap<Integer, ArrayList<XmpType>> sCacheData = new ConcurrentHashMap<>();
    static final ConcurrentHashMap<Integer, Long> sMotionPhotoPresentationCache = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MotionPhotoPresentationParser {
        static final Pattern PATTERN = Pattern.compile("(GCamera:MotionPhotoPresentationTimestampUs=\\\")(.*?)(\\\")");

        public static long get(String str) {
            if (str == null || !str.contains(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS)) {
                return -1;
            }
            try {
                Matcher matcher = PATTERN.matcher(str);
                if (matcher.find()) {
                    return Long.parseLong(matcher.group(2));
                }
                return -1;
            } catch (Error | Exception unused) {
                return -1;
            }
        }
    }

    private static XMPMeta computeXmpMetaIfAbsent(String str) {
        XMPMeta xmpMeta = getXmpMeta(str);
        if (xmpMeta == null) {
            return XmpDecoder.createXMPMeta();
        }
        return xmpMeta;
    }

    public static long getHeifMotionPhotoPresentationTime(FileItemInterface fileItemInterface) {
        FileInputStream fileInputStream;
        XMPProperty property;
        try {
            fileInputStream = new FileInputStream(fileItemInterface.getPath());
            XMPMeta xmpMeta = MotionPhotoVideoUtils.getXmpMeta(fileInputStream.getFD());
            if (xmpMeta == null || (property = xmpMeta.getProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "GCamera:MotionPhotoPresentationTimestampUs")) == null) {
                fileInputStream.close();
                return -1;
            }
            long parseLong = Long.parseLong(property.getValue());
            fileInputStream.close();
            return parseLong;
        } catch (Exception e) {
            Log.e((CharSequence) "XmpUtils", "getHeifMotionPhotoPresentationTime: ", e.getMessage());
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static long getMotionPhotoPresentationTime(FileItemInterface fileItemInterface) {
        Long l = sMotionPhotoPresentationCache.get(Integer.valueOf(fileItemInterface.getFileHashCode()));
        if (l != null) {
            return l.longValue();
        }
        return -1;
    }

    public static long[] getVideoStreamInfoFromGoogleMotionPhoto(String str, long j2) {
        if (PreferenceFeatures.OneUi30.SUPPORT_GOOGLE_MOTION_PHOTO) {
            return getVideoStreamInfoFromPhoto(getXmpMeta(str), j2);
        }
        return null;
    }

    public static long[] getVideoStreamInfoFromPhoto(XMPMeta xMPMeta, long j2) {
        int i2;
        long j3;
        if (!PreferenceFeatures.OneUi30.SUPPORT_GOOGLE_MOTION_PHOTO) {
            return null;
        }
        if (xMPMeta != null) {
            try {
                i2 = xMPMeta.countArrayItems(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory");
            } catch (XMPException e) {
                Log.e("XmpUtils", "getVideoStream#GoogleVideoStream failed. e=" + e.getMessage());
            }
        } else {
            i2 = 0;
        }
        int i7 = 1;
        while (true) {
            if (i7 > i2) {
                break;
            }
            XMPProperty structField = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + i7 + "]/Container:Item", MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Semantic");
            if (structField == null || !MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO.equals(structField.getValue())) {
                i7++;
            } else {
                XMPProperty structField2 = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + i7 + "]/Container:Item", MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Length");
                if (structField2 != null) {
                    long parseInt = (long) Integer.parseInt(structField2.getValue());
                    if (parseInt > ErrorCodeConvertor.ERROR_NOT_ALLOWED_CALLER) {
                        XMPProperty structField3 = xMPMeta.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + i7 + "]/Container:Item", MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Padding");
                        if (structField3 != null) {
                            j3 = (long) Integer.parseInt(structField3.getValue());
                        } else {
                            j3 = 0;
                        }
                        return new long[]{j2 - parseInt, parseInt - j3};
                    }
                }
            }
        }
        Log.w("XmpUtils", "getVideoStream#GoogleVideoStream not found");
        return null;
    }

    private static XMPMeta getXmpMeta(String str) {
        return XmpDecoder.extractXMPMeta(str);
    }

    public static ArrayList<XmpType> getXmpTags(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || fileItemInterface.getPath() == null) {
            return EMPTY;
        }
        return sCacheData.computeIfAbsent(Integer.valueOf(fileItemInterface.getFileHashCode()), new C0715a(fileItemInterface, 0));
    }

    private static boolean hasGoogleHdrGM(XMPMeta xMPMeta) {
        if (xMPMeta != null) {
            try {
                if ("1.0".equalsIgnoreCase(xMPMeta.getPropertyString(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, "hdrgm:Version"))) {
                    return true;
                }
            } catch (XMPException e) {
                if (resetIfSchemaMismatch(e)) {
                    Log.e("XmpUtils", "hasGoogleHdrGM failed. e=" + e.getMessage());
                }
            }
        }
        return false;
    }

    private static boolean isGoogleMotionPhoto(XMPMeta xMPMeta) {
        if (PreferenceFeatures.OneUi30.SUPPORT_GOOGLE_MOTION_PHOTO && xMPMeta != null) {
            try {
                Integer propertyInteger = xMPMeta.getPropertyInteger(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "GCamera:MotionPhoto");
                if (propertyInteger == null || propertyInteger.intValue() != 1) {
                    return false;
                }
                return true;
            } catch (XMPException e) {
                if (resetIfSchemaMismatch(e)) {
                    Log.e("XmpUtils", "isGoogleMotionPhoto failed. e=" + e.getMessage());
                }
            }
        }
        return false;
    }

    private static boolean isImage360(XMPMeta xMPMeta) {
        if (xMPMeta != null) {
            try {
                if ("equirectangular".equalsIgnoreCase(xMPMeta.getPropertyString("http://ns.google.com/photos/1.0/panorama/", "GPano:ProjectionType"))) {
                    return true;
                }
            } catch (XMPException e) {
                if (resetIfSchemaMismatch(e)) {
                    Log.e("XmpUtils", "isImage360 failed. e=" + e.getMessage());
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$getXmpTags$0(FileItemInterface fileItemInterface, Integer num) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<XmpType> loadXmp = loadXmp(fileItemInterface);
        a.A(new Object[]{Long.valueOf(fileItemInterface.getFileId()), loadXmp, Long.valueOf(currentTimeMillis)}, new StringBuilder("getXmpTags"), "XmpUtils");
        return loadXmp;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Long lambda$loadMotionPhotoPresentationTime$1(FileItemInterface fileItemInterface, Integer num) {
        long j2;
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        if (fileItemInterface.isHeif()) {
            j2 = getHeifMotionPhotoPresentationTime(fileItemInterface);
        } else {
            ArrayList<String> loadXmp = JpegParser.loadXmp(fileItemInterface.getPath(), false);
            if (!loadXmp.isEmpty()) {
                str = loadXmp.get(0);
            } else {
                str = null;
            }
            if (str != null) {
                j2 = MotionPhotoPresentationParser.get(str);
            } else {
                j2 = -1;
            }
        }
        Log.d("XmpUtils", "loadMotionPhotoPresentationTime" + Logger.vt(Long.valueOf(fileItemInterface.getFileId()), Boolean.valueOf(fileItemInterface.isHeif()), Long.valueOf(j2), Long.valueOf(currentTimeMillis)));
        return Long.valueOf(j2);
    }

    public static long loadMotionPhotoPresentationTime(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || fileItemInterface.getPath() == null) {
            return -1;
        }
        return sMotionPhotoPresentationCache.computeIfAbsent(Integer.valueOf(fileItemInterface.getFileHashCode()), new C0715a(fileItemInterface, 1)).longValue();
    }

    public static ArrayList<XmpType> loadXmp(FileItemInterface fileItemInterface) {
        String camModel = fileItemInterface.getCamModel();
        if (!fileItemInterface.isHeif() && (camModel == null || !camModel.startsWith("Apple"))) {
            return loadXmp(fileItemInterface.getPath());
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            boolean z = B1.a.z(fileItemInterface.getPath());
            Log.d("XmpUtils", "loadXmp(G)#GM" + Logger.vt(Boolean.valueOf(z), Long.valueOf(currentTimeMillis)));
            if (z) {
                return new ArrayList<>(List.of(XmpType.XmpHdrGM));
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("loadXmp(G)#GM failed. e="), "XmpUtils");
        }
        return EMPTY;
    }

    public static String readFromXMP(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            XMPMeta xmpMeta = getXmpMeta(str);
            if (xmpMeta == null || !xmpMeta.doesPropertyExist("http://ns.google.com/photos/1.0/panorama/", str2)) {
                return null;
            }
            return xmpMeta.getPropertyString("http://ns.google.com/photos/1.0/panorama/", str2);
        } catch (XMPException e) {
            Log.w("XmpUtils", "readFromXMP failed. e=" + e.getMessage());
            return null;
        }
    }

    public static boolean resetIfSchemaMismatch(Exception exc) {
        if (!"Schema namespace URI and prefix mismatch".equals(exc.getMessage())) {
            return false;
        }
        XMPMetaFactory.reset();
        return true;
    }

    public static boolean writeToXMP(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            XMPMeta computeXmpMetaIfAbsent = computeXmpMetaIfAbsent(str);
            computeXmpMetaIfAbsent.setProperty("http://ns.google.com/photos/1.0/panorama/", "DIRECTION", str2);
            return XmpDecoder.writeXMPMeta(str, computeXmpMetaIfAbsent);
        } catch (XMPException e) {
            Log.w("XmpUtils", "writeToXMP failed. e=" + e.getMessage());
            return false;
        }
    }

    public static ArrayList<XmpType> loadXmp(String str) {
        long[] videoStreamInfoFromPhoto;
        try {
            XMPMeta xmpMeta = getXmpMeta(str);
            if (xmpMeta != null) {
                ArrayList<XmpType> arrayList = new ArrayList<>();
                if (isImage360(xmpMeta)) {
                    arrayList.add(XmpType.XmpImage360);
                }
                if (isGoogleMotionPhoto(xmpMeta) && (videoStreamInfoFromPhoto = getVideoStreamInfoFromPhoto(xmpMeta, new File(str).length())) != null && videoStreamInfoFromPhoto[1] > 4) {
                    byte[] readBytes = FileUtils.readBytes(str, videoStreamInfoFromPhoto[0] + 4, 4);
                    String str2 = readBytes != null ? new String(readBytes, StandardCharsets.UTF_8) : null;
                    if (MediaDefs.Image.HEIF.HEIF_FTYP_BOX.equals(str2)) {
                        arrayList.add(XmpType.XmpMotionPhoto);
                    } else {
                        Log.w("XmpUtils", "loadXmp#MotionPhoto failed " + str2);
                    }
                }
                if (hasGoogleHdrGM(xmpMeta)) {
                    arrayList.add(XmpType.XmpHdrGM);
                }
                return arrayList;
            }
        } catch (Error | Exception e) {
            Log.w("XmpUtils", "getXmpTags failed. e=" + Logger.toSimpleString(e));
        }
        return EMPTY;
    }
}
