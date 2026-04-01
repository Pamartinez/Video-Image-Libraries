package androidx.media3.common;

import android.net.Uri;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import com.samsung.scsp.common.ContentType;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileTypes {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int inferFileTypeFromMimeType(java.lang.String r24) {
        /*
            r0 = -1
            if (r24 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = androidx.media3.common.MimeTypes.normalizeMimeType(r24)
            r1.getClass()
            int r2 = r1.hashCode()
            r3 = 21
            r4 = 20
            r5 = 19
            r6 = 18
            r7 = 17
            r8 = 16
            r9 = 15
            r10 = 14
            r11 = 13
            r12 = 12
            r13 = 11
            r14 = 10
            r15 = 9
            r16 = 8
            r17 = 7
            r18 = 6
            r19 = 5
            r20 = 4
            r21 = 3
            r22 = 1
            r23 = 0
            switch(r2) {
                case -2123537834: goto L_0x01db;
                case -1662384011: goto L_0x01cd;
                case -1662384007: goto L_0x01c0;
                case -1662095187: goto L_0x01b2;
                case -1606874997: goto L_0x01a5;
                case -1487656890: goto L_0x0198;
                case -1487464693: goto L_0x018b;
                case -1487464690: goto L_0x017e;
                case -1487394660: goto L_0x0170;
                case -1487018032: goto L_0x0163;
                case -1248337486: goto L_0x0156;
                case -1079884372: goto L_0x0148;
                case -1004728940: goto L_0x013a;
                case -879272239: goto L_0x012d;
                case -879258763: goto L_0x0120;
                case -387023398: goto L_0x0113;
                case -43467528: goto L_0x0106;
                case 13915911: goto L_0x00f8;
                case 187078296: goto L_0x00eb;
                case 187078297: goto L_0x00de;
                case 187078669: goto L_0x00d1;
                case 187090232: goto L_0x00c4;
                case 187091926: goto L_0x00b6;
                case 187099443: goto L_0x00a9;
                case 1331848029: goto L_0x009b;
                case 1503095341: goto L_0x008e;
                case 1504578661: goto L_0x0081;
                case 1504619009: goto L_0x0074;
                case 1504824762: goto L_0x0067;
                case 1504831518: goto L_0x005a;
                case 1505118770: goto L_0x004d;
                case 2039520277: goto L_0x003f;
                default: goto L_0x003c;
            }
        L_0x003c:
            r1 = r0
            goto L_0x01e7
        L_0x003f:
            java.lang.String r2 = "video/x-matroska"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0049
            goto L_0x003c
        L_0x0049:
            r1 = 31
            goto L_0x01e7
        L_0x004d:
            java.lang.String r2 = "audio/webm"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0056
            goto L_0x003c
        L_0x0056:
            r1 = 30
            goto L_0x01e7
        L_0x005a:
            java.lang.String r2 = "audio/mpeg"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0063
            goto L_0x003c
        L_0x0063:
            r1 = 29
            goto L_0x01e7
        L_0x0067:
            java.lang.String r2 = "audio/midi"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0070
            goto L_0x003c
        L_0x0070:
            r1 = 28
            goto L_0x01e7
        L_0x0074:
            java.lang.String r2 = "audio/flac"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x007d
            goto L_0x003c
        L_0x007d:
            r1 = 27
            goto L_0x01e7
        L_0x0081:
            java.lang.String r2 = "audio/eac3"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x008a
            goto L_0x003c
        L_0x008a:
            r1 = 26
            goto L_0x01e7
        L_0x008e:
            java.lang.String r2 = "audio/3gpp"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0097
            goto L_0x003c
        L_0x0097:
            r1 = 25
            goto L_0x01e7
        L_0x009b:
            java.lang.String r2 = "video/mp4"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00a5
            goto L_0x003c
        L_0x00a5:
            r1 = 24
            goto L_0x01e7
        L_0x00a9:
            java.lang.String r2 = "audio/wav"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00b2
            goto L_0x003c
        L_0x00b2:
            r1 = 23
            goto L_0x01e7
        L_0x00b6:
            java.lang.String r2 = "audio/ogg"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00c0
            goto L_0x003c
        L_0x00c0:
            r1 = 22
            goto L_0x01e7
        L_0x00c4:
            java.lang.String r2 = "audio/mp4"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00ce
            goto L_0x003c
        L_0x00ce:
            r1 = r3
            goto L_0x01e7
        L_0x00d1:
            java.lang.String r2 = "audio/amr"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00db
            goto L_0x003c
        L_0x00db:
            r1 = r4
            goto L_0x01e7
        L_0x00de:
            java.lang.String r2 = "audio/ac4"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00e8
            goto L_0x003c
        L_0x00e8:
            r1 = r5
            goto L_0x01e7
        L_0x00eb:
            java.lang.String r2 = "audio/ac3"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00f5
            goto L_0x003c
        L_0x00f5:
            r1 = r6
            goto L_0x01e7
        L_0x00f8:
            java.lang.String r2 = "video/x-flv"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0103
            goto L_0x003c
        L_0x0103:
            r1 = r7
            goto L_0x01e7
        L_0x0106:
            java.lang.String r2 = "application/webm"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0110
            goto L_0x003c
        L_0x0110:
            r1 = r8
            goto L_0x01e7
        L_0x0113:
            java.lang.String r2 = "audio/x-matroska"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x011d
            goto L_0x003c
        L_0x011d:
            r1 = r9
            goto L_0x01e7
        L_0x0120:
            java.lang.String r2 = "image/png"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x012a
            goto L_0x003c
        L_0x012a:
            r1 = r10
            goto L_0x01e7
        L_0x012d:
            java.lang.String r2 = "image/bmp"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0137
            goto L_0x003c
        L_0x0137:
            r1 = r11
            goto L_0x01e7
        L_0x013a:
            java.lang.String r2 = "text/vtt"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0145
            goto L_0x003c
        L_0x0145:
            r1 = r12
            goto L_0x01e7
        L_0x0148:
            java.lang.String r2 = "video/x-msvideo"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0153
            goto L_0x003c
        L_0x0153:
            r1 = r13
            goto L_0x01e7
        L_0x0156:
            java.lang.String r2 = "application/mp4"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0160
            goto L_0x003c
        L_0x0160:
            r1 = r14
            goto L_0x01e7
        L_0x0163:
            java.lang.String r2 = "image/webp"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x016d
            goto L_0x003c
        L_0x016d:
            r1 = r15
            goto L_0x01e7
        L_0x0170:
            java.lang.String r2 = "image/jpeg"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x017a
            goto L_0x003c
        L_0x017a:
            r1 = r16
            goto L_0x01e7
        L_0x017e:
            java.lang.String r2 = "image/heif"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0188
            goto L_0x003c
        L_0x0188:
            r1 = r17
            goto L_0x01e7
        L_0x018b:
            java.lang.String r2 = "image/heic"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0195
            goto L_0x003c
        L_0x0195:
            r1 = r18
            goto L_0x01e7
        L_0x0198:
            java.lang.String r2 = "image/avif"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01a2
            goto L_0x003c
        L_0x01a2:
            r1 = r19
            goto L_0x01e7
        L_0x01a5:
            java.lang.String r2 = "audio/amr-wb"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01af
            goto L_0x003c
        L_0x01af:
            r1 = r20
            goto L_0x01e7
        L_0x01b2:
            java.lang.String r2 = "video/webm"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01bd
            goto L_0x003c
        L_0x01bd:
            r1 = r21
            goto L_0x01e7
        L_0x01c0:
            java.lang.String r2 = "video/mp2t"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01cb
            goto L_0x003c
        L_0x01cb:
            r1 = 2
            goto L_0x01e7
        L_0x01cd:
            java.lang.String r2 = "video/mp2p"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01d8
            goto L_0x003c
        L_0x01d8:
            r1 = r22
            goto L_0x01e7
        L_0x01db:
            java.lang.String r2 = "audio/eac3-joc"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01e5
            goto L_0x003c
        L_0x01e5:
            r1 = r23
        L_0x01e7:
            switch(r1) {
                case 0: goto L_0x01ff;
                case 1: goto L_0x01fe;
                case 2: goto L_0x01fd;
                case 3: goto L_0x01fc;
                case 4: goto L_0x01fb;
                case 5: goto L_0x01fa;
                case 6: goto L_0x01f9;
                case 7: goto L_0x01f9;
                case 8: goto L_0x01f8;
                case 9: goto L_0x01f7;
                case 10: goto L_0x01f6;
                case 11: goto L_0x01f5;
                case 12: goto L_0x01f4;
                case 13: goto L_0x01f3;
                case 14: goto L_0x01f2;
                case 15: goto L_0x01fc;
                case 16: goto L_0x01fc;
                case 17: goto L_0x01f1;
                case 18: goto L_0x01ff;
                case 19: goto L_0x01f0;
                case 20: goto L_0x01fb;
                case 21: goto L_0x01f6;
                case 22: goto L_0x01ef;
                case 23: goto L_0x01ee;
                case 24: goto L_0x01f6;
                case 25: goto L_0x01fb;
                case 26: goto L_0x01ff;
                case 27: goto L_0x01ed;
                case 28: goto L_0x01ec;
                case 29: goto L_0x01eb;
                case 30: goto L_0x01fc;
                case 31: goto L_0x01fc;
                default: goto L_0x01ea;
            }
        L_0x01ea:
            return r0
        L_0x01eb:
            return r17
        L_0x01ec:
            return r9
        L_0x01ed:
            return r20
        L_0x01ee:
            return r12
        L_0x01ef:
            return r15
        L_0x01f0:
            return r22
        L_0x01f1:
            return r19
        L_0x01f2:
            return r7
        L_0x01f3:
            return r5
        L_0x01f4:
            return r11
        L_0x01f5:
            return r8
        L_0x01f6:
            return r16
        L_0x01f7:
            return r6
        L_0x01f8:
            return r10
        L_0x01f9:
            return r4
        L_0x01fa:
            return r3
        L_0x01fb:
            return r21
        L_0x01fc:
            return r18
        L_0x01fd:
            return r13
        L_0x01fe:
            return r14
        L_0x01ff:
            return r23
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.FileTypes.inferFileTypeFromMimeType(java.lang.String):int");
    }

    public static int inferFileTypeFromResponseHeaders(Map<String, List<String>> map) {
        String str;
        List list = map.get(ContentType.KEY);
        if (list == null || list.isEmpty()) {
            str = null;
        } else {
            str = (String) list.get(0);
        }
        return inferFileTypeFromMimeType(str);
    }

    public static int inferFileTypeFromUri(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            return -1;
        }
        if (lastPathSegment.endsWith(".ac3") || lastPathSegment.endsWith(".ec3")) {
            return 0;
        }
        if (lastPathSegment.endsWith(".ac4")) {
            return 1;
        }
        if (lastPathSegment.endsWith(".adts") || lastPathSegment.endsWith(".aac")) {
            return 2;
        }
        if (lastPathSegment.endsWith(".amr")) {
            return 3;
        }
        if (lastPathSegment.endsWith(".flac")) {
            return 4;
        }
        if (lastPathSegment.endsWith(".flv")) {
            return 5;
        }
        if (lastPathSegment.endsWith(".mid") || lastPathSegment.endsWith(".midi") || lastPathSegment.endsWith(".smf")) {
            return 15;
        }
        if (lastPathSegment.startsWith(".mk", lastPathSegment.length() - 4) || lastPathSegment.endsWith(".webm")) {
            return 6;
        }
        if (lastPathSegment.endsWith(".mp3")) {
            return 7;
        }
        if (lastPathSegment.endsWith(O3DPConstant.MP4_EXTENSION) || lastPathSegment.startsWith(".m4", lastPathSegment.length() - 4) || lastPathSegment.startsWith(O3DPConstant.MP4_EXTENSION, lastPathSegment.length() - 5) || lastPathSegment.startsWith(".cmf", lastPathSegment.length() - 5)) {
            return 8;
        }
        if (lastPathSegment.startsWith(".og", lastPathSegment.length() - 4) || lastPathSegment.endsWith(".opus")) {
            return 9;
        }
        if (lastPathSegment.endsWith(".ps") || lastPathSegment.endsWith(".mpeg") || lastPathSegment.endsWith(".mpg") || lastPathSegment.endsWith(".m2p")) {
            return 10;
        }
        if (lastPathSegment.endsWith(".ts") || lastPathSegment.startsWith(".ts", lastPathSegment.length() - 4)) {
            return 11;
        }
        if (lastPathSegment.endsWith(".wav") || lastPathSegment.endsWith(".wave")) {
            return 12;
        }
        if (lastPathSegment.endsWith(".vtt") || lastPathSegment.endsWith(".webvtt")) {
            return 13;
        }
        if (lastPathSegment.endsWith(".jpg") || lastPathSegment.endsWith(".jpeg")) {
            return 14;
        }
        if (lastPathSegment.endsWith(".avi")) {
            return 16;
        }
        if (lastPathSegment.endsWith(".png")) {
            return 17;
        }
        if (lastPathSegment.endsWith(".webp")) {
            return 18;
        }
        if (lastPathSegment.endsWith(".bmp") || lastPathSegment.endsWith(".dib")) {
            return 19;
        }
        if (lastPathSegment.endsWith(".heic") || lastPathSegment.endsWith(".heif")) {
            return 20;
        }
        if (lastPathSegment.endsWith(".avif")) {
            return 21;
        }
        return -1;
    }
}
