package androidx.media3.transformer;

import F2.C0010c0;
import F2.C0033o;
import F2.C0037s;
import F2.C0040v;
import F2.G;
import F2.N;
import F2.U;
import F2.y0;
import J2.b;
import android.media.CamcorderProfile;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.util.Range;
import android.util.Size;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.util.Assertions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EncoderUtil {
    private static final C0037s mimeTypeToEncoders = new C0037s();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api29 {
        public static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo) {
            return mediaCodecInfo.isHardwareAccelerated();
        }
    }

    private static int alignResolution(int i2, int i7) {
        if (i2 % 10 != 1) {
            return Math.round(((float) i2) / ((float) i7)) * i7;
        }
        return (int) (Math.floor((double) (((float) i2) / ((float) i7))) * ((double) i7));
    }

    public static int findHighestSupportedEncodingLevel(MediaCodecInfo mediaCodecInfo, String str, int i2) {
        int i7 = -1;
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : mediaCodecInfo.getCapabilitiesForType(str).profileLevels) {
            if (codecProfileLevel.profile == i2) {
                i7 = Math.max(i7, codecProfileLevel.level);
            }
        }
        return i7;
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [F2.b0, F2.N] */
    public static C0010c0 findSupportedEncodingProfiles(MediaCodecInfo mediaCodecInfo, String str) {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = mediaCodecInfo.getCapabilitiesForType(str).profileLevels;
        ? n = new N(4);
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : codecProfileLevelArr) {
            n.a(Integer.valueOf(codecProfileLevel.profile));
        }
        return n.g();
    }

    public static int getClosestSupportedSampleRate(MediaCodecInfo mediaCodecInfo, String str, int i2) {
        MediaCodecInfo.AudioCapabilities audioCapabilities = (MediaCodecInfo.AudioCapabilities) Assertions.checkNotNull(mediaCodecInfo.getCapabilitiesForType(str).getAudioCapabilities());
        int[] supportedSampleRates = audioCapabilities.getSupportedSampleRates();
        int i7 = 0;
        int i8 = Integer.MAX_VALUE;
        if (supportedSampleRates != null) {
            int length = supportedSampleRates.length;
            while (i7 < length) {
                int i10 = supportedSampleRates[i7];
                if (Math.abs(i10 - i2) < Math.abs(i8 - i2)) {
                    i8 = i10;
                }
                i7++;
            }
            return i8;
        }
        Range[] supportedSampleRateRanges = audioCapabilities.getSupportedSampleRateRanges();
        int length2 = supportedSampleRateRanges.length;
        while (i7 < length2) {
            int intValue = ((Integer) supportedSampleRateRanges[i7].clamp(Integer.valueOf(i2))).intValue();
            if (Math.abs(intValue - i2) < Math.abs(i8 - i2)) {
                i8 = intValue;
            }
            i7++;
        }
        return i8;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        if (r5.equals("video/hevc") == false) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static F2.U getCodecProfilesForHdrFormat(java.lang.String r5, int r6) {
        /*
            r0 = 2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            r2 = 4096(0x1000, float:5.74E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r5.getClass()
            int r3 = r5.hashCode()
            r4 = -1
            switch(r3) {
                case -1851077871: goto L_0x0046;
                case -1662735862: goto L_0x003a;
                case -1662541442: goto L_0x0030;
                case 1331836730: goto L_0x0024;
                case 1599127257: goto L_0x0018;
                default: goto L_0x0016;
            }
        L_0x0016:
            r0 = r4
            goto L_0x0051
        L_0x0018:
            java.lang.String r0 = "video/x-vnd.on2.vp9"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0022
            goto L_0x0016
        L_0x0022:
            r0 = 4
            goto L_0x0051
        L_0x0024:
            java.lang.String r0 = "video/avc"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x002e
            goto L_0x0016
        L_0x002e:
            r0 = 3
            goto L_0x0051
        L_0x0030:
            java.lang.String r3 = "video/hevc"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0051
            goto L_0x0016
        L_0x003a:
            java.lang.String r0 = "video/av01"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0044
            goto L_0x0016
        L_0x0044:
            r0 = 1
            goto L_0x0051
        L_0x0046:
            java.lang.String r0 = "video/dolby-vision"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0050
            goto L_0x0016
        L_0x0050:
            r0 = 0
        L_0x0051:
            r5 = 6
            r3 = 7
            switch(r0) {
                case 0: goto L_0x008f;
                case 1: goto L_0x0081;
                case 2: goto L_0x0073;
                case 3: goto L_0x0066;
                case 4: goto L_0x0057;
                default: goto L_0x0056;
            }
        L_0x0056:
            goto L_0x009c
        L_0x0057:
            if (r6 == r3) goto L_0x005b
            if (r6 != r5) goto L_0x009c
        L_0x005b:
            r5 = 8192(0x2000, float:1.14794E-41)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            F2.y0 r5 = F2.U.D(r2, r5)
            return r5
        L_0x0066:
            if (r6 != r3) goto L_0x009c
            r5 = 16
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            F2.y0 r5 = F2.U.B(r5)
            return r5
        L_0x0073:
            if (r6 != r3) goto L_0x007a
            F2.y0 r5 = F2.U.B(r1)
            return r5
        L_0x007a:
            if (r6 != r5) goto L_0x009c
            F2.y0 r5 = F2.U.B(r2)
            return r5
        L_0x0081:
            if (r6 != r3) goto L_0x0088
            F2.y0 r5 = F2.U.B(r1)
            return r5
        L_0x0088:
            if (r6 != r5) goto L_0x009c
            F2.y0 r5 = F2.U.B(r2)
            return r5
        L_0x008f:
            if (r6 != r3) goto L_0x009c
            r5 = 256(0x100, float:3.59E-43)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            F2.y0 r5 = F2.U.B(r5)
            return r5
        L_0x009c:
            F2.G r5 = F2.U.e
            F2.y0 r5 = F2.y0.f278h
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.EncoderUtil.getCodecProfilesForHdrFormat(java.lang.String, int):F2.U");
    }

    public static Range<Integer> getSupportedBitrateRange(MediaCodecInfo mediaCodecInfo, String str) {
        return ((MediaCodecInfo.VideoCapabilities) Assertions.checkNotNull(mediaCodecInfo.getCapabilitiesForType(str).getVideoCapabilities())).getBitrateRange();
    }

    public static U getSupportedColorFormats(MediaCodecInfo mediaCodecInfo, String str) {
        List list;
        int[] iArr = mediaCodecInfo.getCapabilitiesForType(str).colorFormats;
        if (iArr.length == 0) {
            list = Collections.EMPTY_LIST;
        } else {
            list = new b(iArr, 0, iArr.length);
        }
        return U.y(list);
    }

    public static synchronized U getSupportedEncoders(String str) {
        C0033o oVar;
        U y;
        synchronized (EncoderUtil.class) {
            maybePopulateEncoderInfo();
            C0037s sVar = mimeTypeToEncoders;
            String S = k.S(str);
            Collection collection = (Collection) sVar.f256h.get(S);
            if (collection == null) {
                collection = sVar.f();
            }
            List list = (List) collection;
            if (list instanceof RandomAccess) {
                oVar = new C0033o(sVar, S, list, (C0033o) null);
            } else {
                oVar = new C0033o(sVar, S, list, (C0033o) null);
            }
            y = U.y(oVar);
        }
        return y;
    }

    public static U getSupportedEncodersForHdrEditing(String str, ColorInfo colorInfo) {
        if (Build.VERSION.SDK_INT < 33 || colorInfo == null) {
            G g = U.e;
            return y0.f278h;
        }
        U supportedEncoders = getSupportedEncoders(str);
        C0040v.c(4, "initialCapacity");
        Object[] objArr = new Object[4];
        int i2 = 0;
        for (int i7 = 0; i7 < supportedEncoders.size(); i7++) {
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) supportedEncoders.get(i7);
            if (!mediaCodecInfo.isAlias() && isHdrEditingSupported(mediaCodecInfo, str, colorInfo)) {
                int i8 = i2 + 1;
                int e = N.e(objArr.length, i8);
                if (e > objArr.length) {
                    objArr = Arrays.copyOf(objArr, e);
                }
                objArr[i2] = mediaCodecInfo;
                i2 = i8;
            }
        }
        return U.w(i2, objArr);
    }

    public static Size getSupportedResolution(MediaCodecInfo mediaCodecInfo, String str, int i2, int i7) {
        MediaCodecInfo.VideoCapabilities videoCapabilities = (MediaCodecInfo.VideoCapabilities) Assertions.checkNotNull(mediaCodecInfo.getCapabilitiesForType(str).getVideoCapabilities());
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        int alignResolution = alignResolution(i2, widthAlignment);
        int alignResolution2 = alignResolution(i7, heightAlignment);
        if (isSizeSupported(mediaCodecInfo, str, alignResolution, alignResolution2)) {
            return new Size(alignResolution, alignResolution2);
        }
        float[] fArr = {0.95f, 0.9f, 0.85f, 0.8f, 0.75f, 0.7f, 0.6666667f, 0.6f, 0.55f, 0.5f, 0.4f, 0.33333334f, 0.25f};
        for (int i8 = 0; i8 < 13; i8++) {
            float f = fArr[i8];
            int alignResolution3 = alignResolution(Math.round(((float) i2) * f), widthAlignment);
            int alignResolution4 = alignResolution(Math.round(((float) i7) * f), heightAlignment);
            if (isSizeSupported(mediaCodecInfo, str, alignResolution3, alignResolution4)) {
                return new Size(alignResolution3, alignResolution4);
            }
        }
        int intValue = videoCapabilities.getSupportedHeightsFor(videoCapabilities.getSupportedWidths().clamp(Integer.valueOf(i2)).intValue()).clamp(Integer.valueOf(i7)).intValue();
        if (intValue != i7) {
            i2 = alignResolution((int) Math.round((((double) i2) * ((double) intValue)) / ((double) i7)), widthAlignment);
            i7 = alignResolution(intValue, heightAlignment);
        }
        if (isSizeSupported(mediaCodecInfo, str, i2, i7)) {
            return new Size(i2, i7);
        }
        return null;
    }

    public static boolean isBitrateModeSupported(MediaCodecInfo mediaCodecInfo, String str, int i2) {
        return ((MediaCodecInfo.EncoderCapabilities) Assertions.checkNotNull(mediaCodecInfo.getCapabilitiesForType(str).getEncoderCapabilities())).isBitrateModeSupported(i2);
    }

    public static boolean isFeatureSupported(MediaCodecInfo mediaCodecInfo, String str, String str2) {
        return mediaCodecInfo.getCapabilitiesForType(str).isFeatureSupported(str2);
    }

    public static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo, String str) {
        return Api29.isHardwareAccelerated(mediaCodecInfo);
    }

    public static boolean isHdrEditingSupported(MediaCodecInfo mediaCodecInfo, String str, ColorInfo colorInfo) {
        if (!str.equals("video/dolby-vision") && !isFeatureSupported(mediaCodecInfo, str, "hdr-editing") && (colorInfo.colorTransfer != 7 || Build.VERSION.SDK_INT < 35 || !isFeatureSupported(mediaCodecInfo, str, "hlg-editing"))) {
            return false;
        }
        U codecProfilesForHdrFormat = getCodecProfilesForHdrFormat(str, colorInfo.colorTransfer);
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : mediaCodecInfo.getCapabilitiesForType(str).profileLevels) {
            if (codecProfilesForHdrFormat.contains(Integer.valueOf(codecProfileLevel.profile))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSizeSupported(MediaCodecInfo mediaCodecInfo, String str, int i2, int i7) {
        if (((MediaCodecInfo.VideoCapabilities) Assertions.checkNotNull(mediaCodecInfo.getCapabilitiesForType(str).getVideoCapabilities())).isSizeSupported(i2, i7)) {
            return true;
        }
        if (i2 == 1920 && i7 == 1080) {
            return CamcorderProfile.hasProfile(6);
        }
        if (i2 == 3840 && i7 == 2160) {
            return CamcorderProfile.hasProfile(8);
        }
        return false;
    }

    private static synchronized void maybePopulateEncoderInfo() {
        boolean z;
        synchronized (EncoderUtil.class) {
            if (mimeTypeToEncoders.f257i == 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(0).getCodecInfos()) {
                    if (mediaCodecInfo.isEncoder()) {
                        for (String S : mediaCodecInfo.getSupportedTypes()) {
                            mimeTypeToEncoders.put(k.S(S), mediaCodecInfo);
                        }
                    }
                }
            }
        }
    }
}
