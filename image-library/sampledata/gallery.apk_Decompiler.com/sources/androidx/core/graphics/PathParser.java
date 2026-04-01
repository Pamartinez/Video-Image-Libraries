package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PathParser {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c5, float[] fArr) {
        arrayList.add(new PathDataNode(c5, fArr));
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
            if (pathDataNodeArr[i2].mType != pathDataNodeArr2[i2].mType || pathDataNodeArr[i2].mParams.length != pathDataNodeArr2[i2].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static float[] copyOfRange(float[] fArr, int i2, int i7) {
        if (i2 <= i7) {
            int length = fArr.length;
            if (i2 < 0 || i2 > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i8 = i7 - i2;
            int min = Math.min(i8, length - i2);
            float[] fArr2 = new float[i8];
            System.arraycopy(fArr, i2, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i7 = 1;
        while (i7 < str.length()) {
            int nextStart = nextStart(str, i7);
            String trim = str.substring(i2, nextStart).trim();
            if (!trim.isEmpty()) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i2 = nextStart;
            i7 = nextStart + 1;
        }
        if (i7 - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[0]);
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        try {
            PathDataNode.nodesToPath(createNodesFromPathData(str), path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException(C0212a.l("Error in parsing ", str), e);
        }
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
            pathDataNodeArr2[i2] = new PathDataNode(pathDataNodeArr[i2]);
        }
        return pathDataNodeArr2;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        r2 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0039 A[LOOP:0: B:1:0x0007->B:20:0x0039, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void extract(java.lang.String r8, int r9, androidx.core.graphics.PathParser.ExtractFloatResult r10) {
        /*
            r0 = 0
            r10.mEndWithNegOrDot = r0
            r1 = r9
            r2 = r0
            r3 = r2
            r4 = r3
        L_0x0007:
            int r5 = r8.length()
            if (r1 >= r5) goto L_0x003c
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L_0x0029
            r6 = 69
            if (r5 == r6) goto L_0x0035
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L_0x0035
            switch(r5) {
                case 44: goto L_0x0029;
                case 45: goto L_0x002c;
                case 46: goto L_0x0022;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0033
        L_0x0022:
            if (r3 != 0) goto L_0x0027
            r2 = r0
            r3 = r7
            goto L_0x0036
        L_0x0027:
            r10.mEndWithNegOrDot = r7
        L_0x0029:
            r2 = r0
            r4 = r7
            goto L_0x0036
        L_0x002c:
            if (r1 == r9) goto L_0x0033
            if (r2 != 0) goto L_0x0033
            r10.mEndWithNegOrDot = r7
            goto L_0x0029
        L_0x0033:
            r2 = r0
            goto L_0x0036
        L_0x0035:
            r2 = r7
        L_0x0036:
            if (r4 == 0) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x003c:
            r10.mEndPosition = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.extract(java.lang.String, int, androidx.core.graphics.PathParser$ExtractFloatResult):void");
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i2 = 1;
            int i7 = 0;
            while (i2 < length) {
                extract(str, i2, extractFloatResult);
                int i8 = extractFloatResult.mEndPosition;
                if (i2 < i8) {
                    fArr[i7] = Float.parseFloat(str.substring(i2, i8));
                    i7++;
                }
                if (extractFloatResult.mEndWithNegOrDot) {
                    i2 = i8;
                } else {
                    i2 = i8 + 1;
                }
            }
            return copyOfRange(fArr, 0, i7);
        } catch (NumberFormatException e) {
            throw new RuntimeException(C0212a.m("error in parsing \"", str, "\""), e);
        }
    }

    private static int nextStart(String str, int i2) {
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if ((charAt - 'Z') * (charAt - 'A') > 0) {
                if ((charAt - 'z') * (charAt - 'a') > 0) {
                    continue;
                    i2++;
                }
            }
            if (!(charAt == 'e' || charAt == 'E')) {
                break;
            }
            i2++;
        }
        return i2;
    }

    public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
        float[] fArr = new float[6];
        char c5 = 'm';
        for (PathDataNode pathDataNode : pathDataNodeArr) {
            PathDataNode.addCommand(path, fArr, c5, pathDataNode.mType, pathDataNode.mParams);
            c5 = pathDataNode.mType;
        }
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i2 = 0; i2 < pathDataNodeArr2.length; i2++) {
            char unused = pathDataNodeArr[i2].mType = pathDataNodeArr2[i2].mType;
            for (int i7 = 0; i7 < pathDataNodeArr2[i2].mParams.length; i7++) {
                pathDataNodeArr[i2].mParams[i7] = pathDataNodeArr2[i2].mParams[i7];
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PathDataNode {
        /* access modifiers changed from: private */
        public final float[] mParams;
        /* access modifiers changed from: private */
        public char mType;

        public PathDataNode(char c5, float[] fArr) {
            this.mType = c5;
            this.mParams = fArr;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:100:0x022d, code lost:
            r1 = 0.0f;
            r2 = r8;
            r8 = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:101:0x0232, code lost:
            r1 = 0.0f;
            r2 = r8;
            r8 = r21;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:102:0x0236, code lost:
            r14 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:103:0x023d, code lost:
            if (r11[r9 + 4] == r1) goto L_0x0246;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:0x023f, code lost:
            r9 = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:105:0x0241, code lost:
            r1 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x0246, code lost:
            r9 = r21;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x0249, code lost:
            drawArc(r26, r1, r2, r3, r4, r5, r6, r0, r8, r9);
            r7 = r1 + r11[r12];
            r8 = r2 + r11[r13];
            r3 = r7;
            r4 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:108:0x0258, code lost:
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r0.lineTo(r7, r11[r14]);
            r2 = r11[r14];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:109:0x0269, code lost:
            r21 = r12;
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r9 = r14 + 1;
            r3 = r14 + 2;
            r5 = r14 + 3;
            r0.quadTo(r11[r14], r11[r9], r11[r3], r11[r5]);
            r1 = r11[r14];
            r2 = r11[r9];
            r7 = r11[r3];
            r8 = r11[r5];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:110:0x028f, code lost:
            r21 = r12;
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r0.lineTo(r11[r14], r8);
            r7 = r11[r14];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:111:0x02a3, code lost:
            r21 = r12;
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r9 = r14 + 2;
            r7 = r14 + 3;
            r8 = r14 + 4;
            r12 = r14 + 5;
            r0.cubicTo(r11[r14], r11[r14 + 1], r11[r9], r11[r7], r11[r8], r11[r12]);
            r0 = r11[r8];
            r1 = r11[r12];
            r2 = r11[r9];
            r3 = r11[r7];
            r7 = r0;
            r8 = r1;
            r4 = r3;
            r3 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x02d2, code lost:
            r1 = r7;
            r2 = r8;
            r21 = r12;
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r12 = r14 + 5;
            r3 = r11[r12];
            r13 = r14 + 6;
            r4 = r11[r13];
            r5 = r11[r14];
            r6 = r11[r14 + 1];
            r7 = r11[r14 + 2];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x02f6, code lost:
            if (r11[r14 + 3] == 0.0f) goto L_0x02fc;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:114:0x02f8, code lost:
            r0 = 0.0f;
            r8 = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:115:0x02fc, code lost:
            r0 = 0.0f;
            r8 = r21;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x0305, code lost:
            if (r11[r14 + 4] == r0) goto L_0x030c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:118:0x0307, code lost:
            r9 = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
            if (r9 >= r11.length) goto L_0x0329;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:0x030c, code lost:
            r9 = r21;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:121:0x030f, code lost:
            drawArc(r26, r1, r2, r3, r4, r5, r6, r7, r8, r9);
            r3 = r11[r12];
            r7 = r3;
            r4 = r11[r13];
            r8 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:122:0x031a, code lost:
            r9 = r14 + r18;
            r0 = r26;
            r1 = r10;
            r12 = r21;
            r13 = r22;
            r14 = r23;
            r15 = r24;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:123:0x0329, code lost:
            r27[r12] = r7;
            r27[r13] = r8;
            r27[r14] = r3;
            r27[r15] = r4;
            r27[4] = r19;
            r27[5] = r20;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:124:0x033f, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
            if (r10 == 'A') goto L_0x02d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
            if (r10 == 'C') goto L_0x02a3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
            if (r10 == 'H') goto L_0x028f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
            if (r10 == 'Q') goto L_0x0269;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
            r21 = r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
            if (r10 == 'V') goto L_0x0258;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
            if (r10 == 'a') goto L_0x0208;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x005f, code lost:
            if (r10 == 'c') goto L_0x01d9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
            r22 = r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0065, code lost:
            if (r10 == 'h') goto L_0x01ca;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0069, code lost:
            if (r10 == 'q') goto L_0x01a8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x001d, code lost:
            r18 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x006b, code lost:
            r23 = r14;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x006f, code lost:
            if (r10 == 'v') goto L_0x019c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0073, code lost:
            if (r10 == 'L') goto L_0x018c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0077, code lost:
            if (r10 == 'M') goto L_0x0172;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0079, code lost:
            r24 = r15;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0081, code lost:
            if (r10 == 'S') goto L_0x013b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0087, code lost:
            if (r10 == 'T') goto L_0x0119;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x008b, code lost:
            if (r10 == 'l') goto L_0x0108;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x008f, code lost:
            if (r10 == 'm') goto L_0x00f2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0091, code lost:
            if (r10 == 's') goto L_0x00bc;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0093, code lost:
            if (r10 == 't') goto L_0x0098;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0095, code lost:
            r14 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0098, code lost:
            if (r1 == 'q') goto L_0x00a4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x009a, code lost:
            if (r1 == 't') goto L_0x00a4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x009c, code lost:
            if (r1 == 'Q') goto L_0x00a4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x009e, code lost:
            if (r1 != 'T') goto L_0x00a1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a1, code lost:
            r1 = 0.0f;
            r5 = 0.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a4, code lost:
            r5 = r7 - r3;
            r1 = r8 - r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a8, code lost:
            r3 = r9 + 1;
            r0.rQuadTo(r5, r1, r11[r9], r11[r3]);
            r5 = r5 + r7;
            r1 = r1 + r8;
            r7 = r7 + r11[r9];
            r8 = r8 + r11[r3];
            r4 = r1;
            r3 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00bc, code lost:
            if (r1 == 'c') goto L_0x00cc;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x00be, code lost:
            if (r1 == 's') goto L_0x00cc;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c2, code lost:
            if (r1 == 'C') goto L_0x00cc;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c6, code lost:
            if (r1 != 'S') goto L_0x00c9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c9, code lost:
            r1 = 0.0f;
            r2 = 0.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cc, code lost:
            r2 = r8 - r4;
            r1 = r7 - r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d2, code lost:
            r12 = r9 + 1;
            r13 = r9 + 2;
            r14 = r9 + 3;
            r0.rCubicTo(r1, r2, r11[r9], r11[r12], r11[r13], r11[r14]);
            r1 = r11[r9] + r7;
            r2 = r11[r12] + r8;
            r7 = r7 + r11[r13];
            r3 = r11[r14];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ee, code lost:
            r8 = r8 + r3;
            r3 = r1;
            r4 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f2, code lost:
            r1 = r11[r9];
            r7 = r7 + r1;
            r2 = r11[r9 + 1];
            r8 = r8 + r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x00fa, code lost:
            if (r9 <= 0) goto L_0x0100;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x00fc, code lost:
            r0.rLineTo(r1, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x0100, code lost:
            r0.rMoveTo(r1, r2);
            r19 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x0105, code lost:
            r20 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0108, code lost:
            r2 = r9 + 1;
            r0.rLineTo(r11[r9], r11[r2]);
            r7 = r7 + r11[r9];
            r1 = r11[r2];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x0116, code lost:
            r8 = r8 + r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x0119, code lost:
            if (r1 == 'q') goto L_0x0121;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x011b, code lost:
            if (r1 == 't') goto L_0x0121;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:73:0x011d, code lost:
            if (r1 == 'Q') goto L_0x0121;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x011f, code lost:
            if (r1 != 'T') goto L_0x0127;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x0121, code lost:
            r7 = (r7 * 2.0f) - r3;
            r8 = (r8 * 2.0f) - r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x0127, code lost:
            r2 = r9 + 1;
            r0.quadTo(r7, r8, r11[r9], r11[r2]);
            r1 = r11[r9];
            r2 = r11[r2];
            r3 = r7;
            r4 = r8;
            r14 = r9;
            r7 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x0138, code lost:
            r8 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x013b, code lost:
            if (r1 == 'c') goto L_0x014b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x013d, code lost:
            if (r1 == 's') goto L_0x014b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0032, code lost:
            r18 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x0141, code lost:
            if (r1 == 'C') goto L_0x014b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:0x0145, code lost:
            if (r1 != 'S') goto L_0x0148;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:0x0148, code lost:
            r1 = r7;
            r2 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x014b, code lost:
            r7 = (r7 * 2.0f) - r3;
            r8 = (r8 * 2.0f) - r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x0152, code lost:
            r7 = r9 + 1;
            r8 = r9 + 2;
            r12 = r9 + 3;
            r0.cubicTo(r1, r2, r11[r9], r11[r7], r11[r8], r11[r12]);
            r1 = r11[r9];
            r2 = r11[r7];
            r7 = r11[r8];
            r8 = r11[r12];
            r14 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:0x016e, code lost:
            r3 = r1;
            r4 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x0172, code lost:
            r24 = r15;
            r1 = r11[r9];
            r2 = r11[r9 + 1];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:89:0x017a, code lost:
            if (r9 <= 0) goto L_0x0183;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:0x017c, code lost:
            r0.lineTo(r1, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:0x017f, code lost:
            r7 = r1;
            r8 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x0183, code lost:
            r0.moveTo(r1, r2);
            r7 = r1;
            r19 = r7;
            r8 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x018c, code lost:
            r24 = r15;
            r2 = r9 + 1;
            r0.lineTo(r11[r9], r11[r2]);
            r1 = r11[r9];
            r2 = r11[r2];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:0x019c, code lost:
            r24 = r15;
            r0.rLineTo(0.0f, r11[r9]);
            r1 = r11[r9];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x01a8, code lost:
            r23 = r14;
            r24 = r15;
            r2 = r9 + 1;
            r4 = r9 + 2;
            r6 = r9 + 3;
            r0.rQuadTo(r11[r9], r11[r2], r11[r4], r11[r6]);
            r1 = r11[r9] + r7;
            r2 = r11[r2] + r8;
            r7 = r7 + r11[r4];
            r3 = r11[r6];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:96:0x01ca, code lost:
            r23 = r14;
            r24 = r15;
            r0.rLineTo(r11[r9], 0.0f);
            r7 = r7 + r11[r9];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:97:0x01d9, code lost:
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r12 = r9 + 2;
            r13 = r9 + 3;
            r14 = r9 + 4;
            r15 = r9 + 5;
            r0.rCubicTo(r11[r9], r11[r9 + 1], r11[r12], r11[r13], r11[r14], r11[r15]);
            r0 = r11[r12] + r7;
            r1 = r11[r13] + r8;
            r7 = r7 + r11[r14];
            r8 = r8 + r11[r15];
            r3 = r0;
            r4 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:0x0208, code lost:
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r12 = r9 + 5;
            r3 = r11[r12] + r7;
            r13 = r9 + 6;
            r4 = r11[r13] + r8;
            r5 = r11[r9];
            r6 = r11[r9 + 1];
            r0 = r11[r9 + 2];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:99:0x022b, code lost:
            if (r11[r9 + 3] == 0.0f) goto L_0x0232;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0037, code lost:
            r7 = r1;
            r8 = r2;
            r19 = r5;
            r20 = r6;
            r9 = 0;
            r1 = r28;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static void addCommand(android.graphics.Path r26, float[] r27, char r28, char r29, float[] r30) {
            /*
                r0 = r26
                r10 = r29
                r11 = r30
                r12 = 0
                r1 = r27[r12]
                r13 = 1
                r2 = r27[r13]
                r14 = 2
                r3 = r27[r14]
                r15 = 3
                r4 = r27[r15]
                r16 = 4
                r5 = r27[r16]
                r17 = 5
                r6 = r27[r17]
                switch(r10) {
                    case 65: goto L_0x0035;
                    case 67: goto L_0x0031;
                    case 72: goto L_0x002e;
                    case 76: goto L_0x001d;
                    case 77: goto L_0x001d;
                    case 81: goto L_0x002b;
                    case 83: goto L_0x002b;
                    case 84: goto L_0x001d;
                    case 86: goto L_0x002e;
                    case 90: goto L_0x0020;
                    case 97: goto L_0x0035;
                    case 99: goto L_0x0031;
                    case 104: goto L_0x002e;
                    case 108: goto L_0x001d;
                    case 109: goto L_0x001d;
                    case 113: goto L_0x002b;
                    case 115: goto L_0x002b;
                    case 116: goto L_0x001d;
                    case 118: goto L_0x002e;
                    case 122: goto L_0x0020;
                    default: goto L_0x001d;
                }
            L_0x001d:
                r18 = r14
                goto L_0x0037
            L_0x0020:
                r0.close()
                r0.moveTo(r5, r6)
                r1 = r5
                r3 = r1
                r2 = r6
                r4 = r2
                goto L_0x001d
            L_0x002b:
                r18 = r16
                goto L_0x0037
            L_0x002e:
                r18 = r13
                goto L_0x0037
            L_0x0031:
                r7 = 6
            L_0x0032:
                r18 = r7
                goto L_0x0037
            L_0x0035:
                r7 = 7
                goto L_0x0032
            L_0x0037:
                r7 = r1
                r8 = r2
                r19 = r5
                r20 = r6
                r9 = r12
                r1 = r28
            L_0x0040:
                int r2 = r11.length
                if (r9 >= r2) goto L_0x0329
                r2 = 65
                if (r10 == r2) goto L_0x02d2
                r2 = 67
                if (r10 == r2) goto L_0x02a3
                r6 = 72
                if (r10 == r6) goto L_0x028f
                r6 = 81
                if (r10 == r6) goto L_0x0269
                r21 = r12
                r12 = 86
                if (r10 == r12) goto L_0x0258
                r12 = 97
                if (r10 == r12) goto L_0x0208
                r12 = 99
                if (r10 == r12) goto L_0x01d9
                r22 = r13
                r13 = 104(0x68, float:1.46E-43)
                if (r10 == r13) goto L_0x01ca
                r13 = 113(0x71, float:1.58E-43)
                if (r10 == r13) goto L_0x01a8
                r23 = r14
                r14 = 118(0x76, float:1.65E-43)
                if (r10 == r14) goto L_0x019c
                r14 = 76
                if (r10 == r14) goto L_0x018c
                r14 = 77
                if (r10 == r14) goto L_0x0172
                r14 = 115(0x73, float:1.61E-43)
                r24 = r15
                r15 = 83
                r25 = 1073741824(0x40000000, float:2.0)
                if (r10 == r15) goto L_0x013b
                r5 = 116(0x74, float:1.63E-43)
                r15 = 84
                if (r10 == r15) goto L_0x0119
                r2 = 108(0x6c, float:1.51E-43)
                if (r10 == r2) goto L_0x0108
                r2 = 109(0x6d, float:1.53E-43)
                if (r10 == r2) goto L_0x00f2
                if (r10 == r14) goto L_0x00bc
                if (r10 == r5) goto L_0x0098
            L_0x0095:
                r14 = r9
                goto L_0x031a
            L_0x0098:
                if (r1 == r13) goto L_0x00a4
                if (r1 == r5) goto L_0x00a4
                if (r1 == r6) goto L_0x00a4
                if (r1 != r15) goto L_0x00a1
                goto L_0x00a4
            L_0x00a1:
                r1 = 0
                r5 = 0
                goto L_0x00a8
            L_0x00a4:
                float r5 = r7 - r3
                float r1 = r8 - r4
            L_0x00a8:
                r2 = r11[r9]
                int r3 = r9 + 1
                r4 = r11[r3]
                r0.rQuadTo(r5, r1, r2, r4)
                float r5 = r5 + r7
                float r1 = r1 + r8
                r2 = r11[r9]
                float r7 = r7 + r2
                r2 = r11[r3]
                float r8 = r8 + r2
                r4 = r1
                r3 = r5
                goto L_0x0095
            L_0x00bc:
                if (r1 == r12) goto L_0x00cc
                if (r1 == r14) goto L_0x00cc
                r2 = 67
                if (r1 == r2) goto L_0x00cc
                r2 = 83
                if (r1 != r2) goto L_0x00c9
                goto L_0x00cc
            L_0x00c9:
                r1 = 0
                r2 = 0
                goto L_0x00d2
            L_0x00cc:
                float r5 = r7 - r3
                float r1 = r8 - r4
                r2 = r1
                r1 = r5
            L_0x00d2:
                r3 = r11[r9]
                int r12 = r9 + 1
                r4 = r11[r12]
                int r13 = r9 + 2
                r5 = r11[r13]
                int r14 = r9 + 3
                r6 = r11[r14]
                r0.rCubicTo(r1, r2, r3, r4, r5, r6)
                r1 = r11[r9]
                float r1 = r1 + r7
                r2 = r11[r12]
                float r2 = r2 + r8
                r3 = r11[r13]
                float r7 = r7 + r3
                r3 = r11[r14]
            L_0x00ee:
                float r8 = r8 + r3
                r3 = r1
                r4 = r2
                goto L_0x0095
            L_0x00f2:
                r1 = r11[r9]
                float r7 = r7 + r1
                int r2 = r9 + 1
                r2 = r11[r2]
                float r8 = r8 + r2
                if (r9 <= 0) goto L_0x0100
                r0.rLineTo(r1, r2)
                goto L_0x0095
            L_0x0100:
                r0.rMoveTo(r1, r2)
                r19 = r7
            L_0x0105:
                r20 = r8
                goto L_0x0095
            L_0x0108:
                r1 = r11[r9]
                int r2 = r9 + 1
                r5 = r11[r2]
                r0.rLineTo(r1, r5)
                r1 = r11[r9]
                float r7 = r7 + r1
                r1 = r11[r2]
            L_0x0116:
                float r8 = r8 + r1
                goto L_0x0095
            L_0x0119:
                if (r1 == r13) goto L_0x0121
                if (r1 == r5) goto L_0x0121
                if (r1 == r6) goto L_0x0121
                if (r1 != r15) goto L_0x0127
            L_0x0121:
                float r7 = r7 * r25
                float r7 = r7 - r3
                float r8 = r8 * r25
                float r8 = r8 - r4
            L_0x0127:
                r1 = r11[r9]
                int r2 = r9 + 1
                r3 = r11[r2]
                r0.quadTo(r7, r8, r1, r3)
                r1 = r11[r9]
                r2 = r11[r2]
                r3 = r7
                r4 = r8
                r14 = r9
                r7 = r1
            L_0x0138:
                r8 = r2
                goto L_0x031a
            L_0x013b:
                if (r1 == r12) goto L_0x014b
                if (r1 == r14) goto L_0x014b
                r2 = 67
                if (r1 == r2) goto L_0x014b
                r2 = 83
                if (r1 != r2) goto L_0x0148
                goto L_0x014b
            L_0x0148:
                r1 = r7
                r2 = r8
                goto L_0x0152
            L_0x014b:
                float r7 = r7 * r25
                float r7 = r7 - r3
                float r8 = r8 * r25
                float r8 = r8 - r4
                goto L_0x0148
            L_0x0152:
                r3 = r11[r9]
                int r7 = r9 + 1
                r4 = r11[r7]
                int r8 = r9 + 2
                r5 = r11[r8]
                int r12 = r9 + 3
                r6 = r11[r12]
                r0.cubicTo(r1, r2, r3, r4, r5, r6)
                r1 = r11[r9]
                r2 = r11[r7]
                r3 = r11[r8]
                r4 = r11[r12]
                r7 = r3
                r8 = r4
                r14 = r9
            L_0x016e:
                r3 = r1
                r4 = r2
                goto L_0x031a
            L_0x0172:
                r24 = r15
                r1 = r11[r9]
                int r2 = r9 + 1
                r2 = r11[r2]
                if (r9 <= 0) goto L_0x0183
                r0.lineTo(r1, r2)
            L_0x017f:
                r7 = r1
                r8 = r2
                goto L_0x0095
            L_0x0183:
                r0.moveTo(r1, r2)
                r7 = r1
                r19 = r7
                r8 = r2
                goto L_0x0105
            L_0x018c:
                r24 = r15
                r1 = r11[r9]
                int r2 = r9 + 1
                r5 = r11[r2]
                r0.lineTo(r1, r5)
                r1 = r11[r9]
                r2 = r11[r2]
                goto L_0x017f
            L_0x019c:
                r24 = r15
                r1 = r11[r9]
                r2 = 0
                r0.rLineTo(r2, r1)
                r1 = r11[r9]
                goto L_0x0116
            L_0x01a8:
                r23 = r14
                r24 = r15
                r1 = r11[r9]
                int r2 = r9 + 1
                r3 = r11[r2]
                int r4 = r9 + 2
                r5 = r11[r4]
                int r6 = r9 + 3
                r12 = r11[r6]
                r0.rQuadTo(r1, r3, r5, r12)
                r1 = r11[r9]
                float r1 = r1 + r7
                r2 = r11[r2]
                float r2 = r2 + r8
                r3 = r11[r4]
                float r7 = r7 + r3
                r3 = r11[r6]
                goto L_0x00ee
            L_0x01ca:
                r23 = r14
                r24 = r15
                r1 = r11[r9]
                r2 = 0
                r0.rLineTo(r1, r2)
                r1 = r11[r9]
                float r7 = r7 + r1
                goto L_0x0095
            L_0x01d9:
                r22 = r13
                r23 = r14
                r24 = r15
                r1 = r11[r9]
                int r2 = r9 + 1
                r2 = r11[r2]
                int r12 = r9 + 2
                r3 = r11[r12]
                int r13 = r9 + 3
                r4 = r11[r13]
                int r14 = r9 + 4
                r5 = r11[r14]
                int r15 = r9 + 5
                r6 = r11[r15]
                r0.rCubicTo(r1, r2, r3, r4, r5, r6)
                r0 = r11[r12]
                float r0 = r0 + r7
                r1 = r11[r13]
                float r1 = r1 + r8
                r2 = r11[r14]
                float r7 = r7 + r2
                r2 = r11[r15]
                float r8 = r8 + r2
                r3 = r0
                r4 = r1
                goto L_0x0095
            L_0x0208:
                r22 = r13
                r23 = r14
                r24 = r15
                int r12 = r9 + 5
                r0 = r11[r12]
                float r3 = r0 + r7
                int r13 = r9 + 6
                r0 = r11[r13]
                float r4 = r0 + r8
                r5 = r11[r9]
                int r0 = r9 + 1
                r6 = r11[r0]
                int r0 = r9 + 2
                r0 = r11[r0]
                int r1 = r9 + 3
                r1 = r11[r1]
                r2 = 0
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 == 0) goto L_0x0232
                r1 = r2
                r2 = r8
                r8 = r22
                goto L_0x0236
            L_0x0232:
                r1 = r2
                r2 = r8
                r8 = r21
            L_0x0236:
                int r14 = r9 + 4
                r14 = r11[r14]
                int r1 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
                r14 = r9
                if (r1 == 0) goto L_0x0246
                r9 = r22
            L_0x0241:
                r1 = r7
                r7 = r0
                r0 = r26
                goto L_0x0249
            L_0x0246:
                r9 = r21
                goto L_0x0241
            L_0x0249:
                drawArc(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
                r3 = r11[r12]
                float r7 = r1 + r3
                r1 = r11[r13]
                float r8 = r2 + r1
                r3 = r7
                r4 = r8
                goto L_0x031a
            L_0x0258:
                r1 = r7
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                r2 = r11[r14]
                r0.lineTo(r1, r2)
                r2 = r11[r14]
                goto L_0x0138
            L_0x0269:
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                r1 = r11[r14]
                int r9 = r14 + 1
                r2 = r11[r9]
                int r3 = r14 + 2
                r4 = r11[r3]
                int r5 = r14 + 3
                r6 = r11[r5]
                r0.quadTo(r1, r2, r4, r6)
                r1 = r11[r14]
                r2 = r11[r9]
                r3 = r11[r3]
                r4 = r11[r5]
                r7 = r3
                r8 = r4
                goto L_0x016e
            L_0x028f:
                r2 = r8
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                r1 = r11[r14]
                r0.lineTo(r1, r2)
                r1 = r11[r14]
                r7 = r1
                goto L_0x031a
            L_0x02a3:
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                r1 = r11[r14]
                int r9 = r14 + 1
                r2 = r11[r9]
                int r9 = r14 + 2
                r3 = r11[r9]
                int r7 = r14 + 3
                r4 = r11[r7]
                int r8 = r14 + 4
                r5 = r11[r8]
                int r12 = r14 + 5
                r6 = r11[r12]
                r0.cubicTo(r1, r2, r3, r4, r5, r6)
                r0 = r11[r8]
                r1 = r11[r12]
                r2 = r11[r9]
                r3 = r11[r7]
                r7 = r0
                r8 = r1
                r4 = r3
                r3 = r2
                goto L_0x031a
            L_0x02d2:
                r1 = r7
                r2 = r8
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                int r12 = r14 + 5
                r3 = r11[r12]
                int r13 = r14 + 6
                r4 = r11[r13]
                r5 = r11[r14]
                int r9 = r14 + 1
                r6 = r11[r9]
                int r9 = r14 + 2
                r7 = r11[r9]
                int r9 = r14 + 3
                r0 = r11[r9]
                r8 = 0
                int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
                if (r0 == 0) goto L_0x02fc
                r0 = r8
                r8 = r22
                goto L_0x02ff
            L_0x02fc:
                r0 = r8
                r8 = r21
            L_0x02ff:
                int r9 = r14 + 4
                r9 = r11[r9]
                int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r0 == 0) goto L_0x030c
                r9 = r22
            L_0x0309:
                r0 = r26
                goto L_0x030f
            L_0x030c:
                r9 = r21
                goto L_0x0309
            L_0x030f:
                drawArc(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
                r0 = r11[r12]
                r1 = r11[r13]
                r3 = r0
                r7 = r3
                r4 = r1
                r8 = r4
            L_0x031a:
                int r9 = r14 + r18
                r0 = r26
                r1 = r10
                r12 = r21
                r13 = r22
                r14 = r23
                r15 = r24
                goto L_0x0040
            L_0x0329:
                r1 = r7
                r2 = r8
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r27[r21] = r1
                r27[r22] = r2
                r27[r23] = r3
                r27[r24] = r4
                r27[r16] = r19
                r27[r17] = r20
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.PathDataNode.addCommand(android.graphics.Path, float[], char, char, float[]):void");
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d5, double d6, double d7, double d9, double d10, double d11) {
            double d12 = d3;
            int ceil = (int) Math.ceil(Math.abs((d11 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d9);
            double sin = Math.sin(d9);
            double cos2 = Math.cos(d10);
            double sin2 = Math.sin(d10);
            double d13 = -d12;
            double d14 = d13 * cos;
            double d15 = d5 * sin;
            double d16 = (d14 * sin2) - (d15 * cos2);
            double d17 = d13 * sin;
            double d18 = d5 * cos;
            double d19 = (cos2 * d18) + (sin2 * d17);
            double d20 = d11 / ((double) ceil);
            double d21 = d19;
            double d22 = d16;
            int i2 = 0;
            double d23 = d6;
            double d24 = d7;
            double d25 = d10;
            while (i2 < ceil) {
                double d26 = d25 + d20;
                double sin3 = Math.sin(d26);
                double cos3 = Math.cos(d26);
                double d27 = (((d12 * cos) * cos3) + d) - (d15 * sin3);
                int i7 = i2;
                double d28 = (d18 * sin3) + (d3 * sin * cos3) + d2;
                double d29 = (d14 * sin3) - (d15 * cos3);
                double d30 = (cos3 * d18) + (sin3 * d17);
                double d31 = d26 - d25;
                double tan = Math.tan(d31 / 2.0d);
                double sqrt = ((Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d) * Math.sin(d31)) / 3.0d;
                double d32 = (d22 * sqrt) + d23;
                int i8 = ceil;
                double d33 = (d21 * sqrt) + d24;
                double d34 = d17;
                Path path2 = path;
                path2.rLineTo(0.0f, 0.0f);
                Path path3 = path2;
                path3.cubicTo((float) d32, (float) d33, (float) (d27 - (sqrt * d29)), (float) (d28 - (sqrt * d30)), (float) d27, (float) d28);
                sin = sin;
                d20 = d20;
                d23 = d27;
                d17 = d34;
                cos = cos;
                d21 = d30;
                d22 = d29;
                d12 = d3;
                d24 = d28;
                i2 = i7 + 1;
                ceil = i8;
                d25 = d26;
            }
        }

        private static void drawArc(Path path, float f, float f5, float f8, float f10, float f11, float f12, float f13, boolean z, boolean z3) {
            double d;
            double d2;
            boolean z7;
            float f14 = f;
            float f15 = f8;
            float f16 = f13;
            double radians = Math.toRadians((double) f16);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = (double) f14;
            float f17 = f5;
            double d5 = (double) f17;
            double d6 = d5;
            double d7 = d3;
            double d9 = (double) f11;
            double d10 = (double) f12;
            double d11 = ((d6 * cos) + (((double) (-f14)) * sin)) / d10;
            double d12 = ((d5 * sin) + (d3 * cos)) / d9;
            float f18 = f10;
            double d13 = (double) f18;
            double d14 = ((d13 * sin) + (((double) f15) * cos)) / d9;
            double d15 = ((d13 * cos) + (((double) (-f15)) * sin)) / d10;
            double d16 = d12 - d14;
            double d17 = d11 - d15;
            double d18 = (d12 + d14) / 2.0d;
            double d19 = (d11 + d15) / 2.0d;
            double d20 = d16;
            double d21 = (d17 * d17) + (d16 * d16);
            if (d21 == MapUtil.INVALID_LOCATION) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double d22 = (1.0d / d21) - 0.25d;
            if (d22 < MapUtil.INVALID_LOCATION) {
                Log.w("PathParser", "Points are too far apart " + d21);
                float sqrt = (float) (Math.sqrt(d21) / 1.99999d);
                float f19 = f11 * sqrt;
                float f20 = sqrt * f12;
                Path path2 = path;
                drawArc(path2, f, f17, f15, f18, f19, f20, f16, z, z3);
                return;
            }
            boolean z9 = z3;
            double sqrt2 = Math.sqrt(d22);
            double d23 = sqrt2 * d20;
            double d24 = sqrt2 * d17;
            if (z == z9) {
                d2 = d18 - d24;
                d = d19 + d23;
            } else {
                d2 = d18 + d24;
                d = d19 - d23;
            }
            double atan2 = Math.atan2(d11 - d, d12 - d2);
            double atan22 = Math.atan2(d15 - d, d14 - d2) - atan2;
            int i2 = (atan22 > MapUtil.INVALID_LOCATION ? 1 : (atan22 == MapUtil.INVALID_LOCATION ? 0 : -1));
            if (i2 >= 0) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (z9 != z7) {
                if (i2 > 0) {
                    atan22 -= 6.283185307179586d;
                } else {
                    atan22 += 6.283185307179586d;
                }
            }
            double d25 = d2 * d9;
            double d26 = d * d10;
            Path path3 = path;
            double d27 = (d25 * cos) - (d26 * sin);
            arcToBezier(path3, d27, (d26 * cos) + (d25 * sin), d9, d10, d7, d6, radians, atan2, atan22);
        }

        @Deprecated
        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            PathParser.nodesToPath(pathDataNodeArr, path);
        }

        public PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            float[] fArr = pathDataNode.mParams;
            this.mParams = PathParser.copyOfRange(fArr, 0, fArr.length);
        }
    }
}
