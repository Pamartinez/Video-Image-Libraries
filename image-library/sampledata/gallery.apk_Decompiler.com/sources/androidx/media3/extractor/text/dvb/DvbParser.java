package androidx.media3.extractor.text.dvb;

import F2.G;
import F2.U;
import F2.y0;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DvbParser implements SubtitleParser {
    private static final byte[] defaultMap2To4 = {0, 7, 8, 15};
    private static final byte[] defaultMap2To8 = {0, 119, -120, -1};
    private static final byte[] defaultMap4To8 = {0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    private Bitmap bitmap;
    private final Canvas canvas = new Canvas();
    private final ClutDefinition defaultClutDefinition = new ClutDefinition(0, generateDefault2BitClutEntries(), generateDefault4BitClutEntries(), generateDefault8BitClutEntries());
    private final DisplayDefinition defaultDisplayDefinition = new DisplayDefinition(719, 575, 0, 719, 0, 575);
    private final Paint defaultPaint;
    private final Paint fillRegionPaint;
    private final SubtitleService subtitleService;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ClutDefinition {
        public final int[] clutEntries2Bit;
        public final int[] clutEntries4Bit;
        public final int[] clutEntries8Bit;
        public final int id;

        public ClutDefinition(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
            this.id = i2;
            this.clutEntries2Bit = iArr;
            this.clutEntries4Bit = iArr2;
            this.clutEntries8Bit = iArr3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DisplayDefinition {
        public final int height;
        public final int horizontalPositionMaximum;
        public final int horizontalPositionMinimum;
        public final int verticalPositionMaximum;
        public final int verticalPositionMinimum;
        public final int width;

        public DisplayDefinition(int i2, int i7, int i8, int i10, int i11, int i12) {
            this.width = i2;
            this.height = i7;
            this.horizontalPositionMinimum = i8;
            this.horizontalPositionMaximum = i10;
            this.verticalPositionMinimum = i11;
            this.verticalPositionMaximum = i12;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ObjectData {
        public final byte[] bottomFieldData;
        public final int id;
        public final boolean nonModifyingColorFlag;
        public final byte[] topFieldData;

        public ObjectData(int i2, boolean z, byte[] bArr, byte[] bArr2) {
            this.id = i2;
            this.nonModifyingColorFlag = z;
            this.topFieldData = bArr;
            this.bottomFieldData = bArr2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PageComposition {
        public final SparseArray<PageRegion> regions;
        public final int state;
        public final int timeOutSecs;
        public final int version;

        public PageComposition(int i2, int i7, int i8, SparseArray<PageRegion> sparseArray) {
            this.timeOutSecs = i2;
            this.version = i7;
            this.state = i8;
            this.regions = sparseArray;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PageRegion {
        public final int horizontalAddress;
        public final int verticalAddress;

        public PageRegion(int i2, int i7) {
            this.horizontalAddress = i2;
            this.verticalAddress = i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RegionComposition {
        public final int clutId;
        public final int depth;
        public final boolean fillFlag;
        public final int height;
        public final int id;
        public final int levelOfCompatibility;
        public final int pixelCode2Bit;
        public final int pixelCode4Bit;
        public final int pixelCode8Bit;
        public final SparseArray<RegionObject> regionObjects;
        public final int width;

        public RegionComposition(int i2, boolean z, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15, SparseArray<RegionObject> sparseArray) {
            this.id = i2;
            this.fillFlag = z;
            this.width = i7;
            this.height = i8;
            this.levelOfCompatibility = i10;
            this.depth = i11;
            this.clutId = i12;
            this.pixelCode8Bit = i13;
            this.pixelCode4Bit = i14;
            this.pixelCode2Bit = i15;
            this.regionObjects = sparseArray;
        }

        public void mergeFrom(RegionComposition regionComposition) {
            SparseArray<RegionObject> sparseArray = regionComposition.regionObjects;
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                this.regionObjects.put(sparseArray.keyAt(i2), sparseArray.valueAt(i2));
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RegionObject {
        public final int backgroundPixelCode;
        public final int foregroundPixelCode;
        public final int horizontalPosition;
        public final int provider;
        public final int type;
        public final int verticalPosition;

        public RegionObject(int i2, int i7, int i8, int i10, int i11, int i12) {
            this.type = i2;
            this.provider = i7;
            this.horizontalPosition = i8;
            this.verticalPosition = i10;
            this.foregroundPixelCode = i11;
            this.backgroundPixelCode = i12;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SubtitleService {
        public final SparseArray<ClutDefinition> ancillaryCluts = new SparseArray<>();
        public final SparseArray<ObjectData> ancillaryObjects = new SparseArray<>();
        public final int ancillaryPageId;
        public final SparseArray<ClutDefinition> cluts = new SparseArray<>();
        public DisplayDefinition displayDefinition;
        public final SparseArray<ObjectData> objects = new SparseArray<>();
        public PageComposition pageComposition;
        public final SparseArray<RegionComposition> regions = new SparseArray<>();
        public final int subtitlePageId;

        public SubtitleService(int i2, int i7) {
            this.subtitlePageId = i2;
            this.ancillaryPageId = i7;
        }

        public void reset() {
            this.regions.clear();
            this.cluts.clear();
            this.objects.clear();
            this.ancillaryCluts.clear();
            this.ancillaryObjects.clear();
            this.displayDefinition = null;
            this.pageComposition = null;
        }
    }

    public DvbParser(List<byte[]> list) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(list.get(0));
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        Paint paint = new Paint();
        this.defaultPaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setPathEffect((PathEffect) null);
        Paint paint2 = new Paint();
        this.fillRegionPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint2.setPathEffect((PathEffect) null);
        this.subtitleService = new SubtitleService(readUnsignedShort, readUnsignedShort2);
    }

    private static byte[] buildClutMapTable(int i2, int i7, ParsableBitArray parsableBitArray) {
        byte[] bArr = new byte[i2];
        for (int i8 = 0; i8 < i2; i8++) {
            bArr[i8] = (byte) parsableBitArray.readBits(i7);
        }
        return bArr;
    }

    private static int[] generateDefault2BitClutEntries() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    private static int[] generateDefault4BitClutEntries() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i12 = 1; i12 < 16; i12++) {
            if (i12 < 8) {
                if ((i12 & 1) != 0) {
                    i8 = 255;
                } else {
                    i8 = 0;
                }
                if ((i12 & 2) != 0) {
                    i10 = 255;
                } else {
                    i10 = 0;
                }
                if ((i12 & 4) != 0) {
                    i11 = 255;
                } else {
                    i11 = 0;
                }
                iArr[i12] = getColor(ScoverState.TYPE_NFC_SMART_COVER, i8, i10, i11);
            } else {
                int i13 = 127;
                if ((i12 & 1) != 0) {
                    i2 = 127;
                } else {
                    i2 = 0;
                }
                if ((i12 & 2) != 0) {
                    i7 = 127;
                } else {
                    i7 = 0;
                }
                if ((i12 & 4) == 0) {
                    i13 = 0;
                }
                iArr[i12] = getColor(ScoverState.TYPE_NFC_SMART_COVER, i2, i7, i13);
            }
        }
        return iArr;
    }

    private static int[] generateDefault8BitClutEntries() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i25 = 0; i25 < 256; i25++) {
            int i26 = ScoverState.TYPE_NFC_SMART_COVER;
            if (i25 < 8) {
                if ((i25 & 1) != 0) {
                    i23 = 255;
                } else {
                    i23 = 0;
                }
                if ((i25 & 2) != 0) {
                    i24 = 255;
                } else {
                    i24 = 0;
                }
                if ((i25 & 4) == 0) {
                    i26 = 0;
                }
                iArr[i25] = getColor(63, i23, i24, i26);
            } else {
                int i27 = i25 & 136;
                int i28 = MOCRLang.MALAYALAM;
                int i29 = 85;
                if (i27 == 0) {
                    if ((i25 & 1) != 0) {
                        i2 = 85;
                    } else {
                        i2 = 0;
                    }
                    if ((i25 & 16) != 0) {
                        i7 = 170;
                    } else {
                        i7 = 0;
                    }
                    int i30 = i2 + i7;
                    if ((i25 & 2) != 0) {
                        i8 = 85;
                    } else {
                        i8 = 0;
                    }
                    if ((i25 & 32) != 0) {
                        i10 = 170;
                    } else {
                        i10 = 0;
                    }
                    int i31 = i8 + i10;
                    if ((i25 & 4) == 0) {
                        i29 = 0;
                    }
                    if ((i25 & 64) == 0) {
                        i28 = 0;
                    }
                    iArr[i25] = getColor(ScoverState.TYPE_NFC_SMART_COVER, i30, i31, i29 + i28);
                } else if (i27 != 8) {
                    int i32 = 43;
                    if (i27 == 128) {
                        if ((i25 & 1) != 0) {
                            i15 = 43;
                        } else {
                            i15 = 0;
                        }
                        int i33 = i15 + 127;
                        if ((i25 & 16) != 0) {
                            i16 = 85;
                        } else {
                            i16 = 0;
                        }
                        int i34 = i33 + i16;
                        if ((i25 & 2) != 0) {
                            i17 = 43;
                        } else {
                            i17 = 0;
                        }
                        int i35 = i17 + 127;
                        if ((i25 & 32) != 0) {
                            i18 = 85;
                        } else {
                            i18 = 0;
                        }
                        int i36 = i35 + i18;
                        if ((i25 & 4) == 0) {
                            i32 = 0;
                        }
                        int i37 = i32 + 127;
                        if ((i25 & 64) == 0) {
                            i29 = 0;
                        }
                        iArr[i25] = getColor(ScoverState.TYPE_NFC_SMART_COVER, i34, i36, i37 + i29);
                    } else if (i27 == 136) {
                        if ((i25 & 1) != 0) {
                            i19 = 43;
                        } else {
                            i19 = 0;
                        }
                        if ((i25 & 16) != 0) {
                            i20 = 85;
                        } else {
                            i20 = 0;
                        }
                        int i38 = i19 + i20;
                        if ((i25 & 2) != 0) {
                            i21 = 43;
                        } else {
                            i21 = 0;
                        }
                        if ((i25 & 32) != 0) {
                            i22 = 85;
                        } else {
                            i22 = 0;
                        }
                        int i39 = i21 + i22;
                        if ((i25 & 4) == 0) {
                            i32 = 0;
                        }
                        if ((i25 & 64) == 0) {
                            i29 = 0;
                        }
                        iArr[i25] = getColor(ScoverState.TYPE_NFC_SMART_COVER, i38, i39, i32 + i29);
                    }
                } else {
                    if ((i25 & 1) != 0) {
                        i11 = 85;
                    } else {
                        i11 = 0;
                    }
                    if ((i25 & 16) != 0) {
                        i12 = 170;
                    } else {
                        i12 = 0;
                    }
                    int i40 = i11 + i12;
                    if ((i25 & 2) != 0) {
                        i13 = 85;
                    } else {
                        i13 = 0;
                    }
                    if ((i25 & 32) != 0) {
                        i14 = 170;
                    } else {
                        i14 = 0;
                    }
                    int i41 = i13 + i14;
                    if ((i25 & 4) == 0) {
                        i29 = 0;
                    }
                    if ((i25 & 64) == 0) {
                        i28 = 0;
                    }
                    iArr[i25] = getColor(127, i40, i41, i29 + i28);
                }
            }
        }
        return iArr;
    }

    private static int getColor(int i2, int i7, int i8, int i10) {
        return (i2 << 24) | (i7 << 16) | (i8 << 8) | i10;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint2BitPixelCodeString(androidx.media3.common.util.ParsableBitArray r9, int[] r10, byte[] r11, int r12, int r13, android.graphics.Paint r14, android.graphics.Canvas r15) {
        /*
            r6 = 0
            r0 = r6
        L_0x0002:
            r1 = 2
            int r2 = r9.readBits(r1)
            r3 = 1
            if (r2 == 0) goto L_0x000d
            r7 = r0
            r8 = r3
            goto L_0x0059
        L_0x000d:
            boolean r2 = r9.readBit()
            r4 = 3
            if (r2 == 0) goto L_0x0021
            int r2 = r9.readBits(r4)
            int r2 = r2 + r4
            int r1 = r9.readBits(r1)
        L_0x001d:
            r7 = r0
            r8 = r2
            r2 = r1
            goto L_0x0059
        L_0x0021:
            boolean r2 = r9.readBit()
            if (r2 == 0) goto L_0x002b
            r7 = r0
            r8 = r3
        L_0x0029:
            r2 = r6
            goto L_0x0059
        L_0x002b:
            int r2 = r9.readBits(r1)
            if (r2 == 0) goto L_0x0057
            if (r2 == r3) goto L_0x0054
            if (r2 == r1) goto L_0x0048
            if (r2 == r4) goto L_0x003b
            r7 = r0
        L_0x0038:
            r2 = r6
            r8 = r2
            goto L_0x0059
        L_0x003b:
            r2 = 8
            int r2 = r9.readBits(r2)
            int r2 = r2 + 29
            int r1 = r9.readBits(r1)
            goto L_0x001d
        L_0x0048:
            r2 = 4
            int r2 = r9.readBits(r2)
            int r2 = r2 + 12
            int r1 = r9.readBits(r1)
            goto L_0x001d
        L_0x0054:
            r7 = r0
            r8 = r1
            goto L_0x0029
        L_0x0057:
            r7 = r3
            goto L_0x0038
        L_0x0059:
            if (r8 == 0) goto L_0x0073
            if (r14 == 0) goto L_0x0073
            if (r11 == 0) goto L_0x0061
            byte r2 = r11[r2]
        L_0x0061:
            r0 = r10[r2]
            r14.setColor(r0)
            float r1 = (float) r12
            float r2 = (float) r13
            int r0 = r12 + r8
            float r0 = (float) r0
            int r3 = r3 + r13
            float r4 = (float) r3
            r5 = r14
            r3 = r0
            r0 = r15
            r0.drawRect(r1, r2, r3, r4, r5)
        L_0x0073:
            int r12 = r12 + r8
            if (r7 == 0) goto L_0x0077
            return r12
        L_0x0077:
            r0 = r7
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.dvb.DvbParser.paint2BitPixelCodeString(androidx.media3.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint4BitPixelCodeString(androidx.media3.common.util.ParsableBitArray r9, int[] r10, byte[] r11, int r12, int r13, android.graphics.Paint r14, android.graphics.Canvas r15) {
        /*
            r6 = 0
            r0 = r6
        L_0x0002:
            r1 = 4
            int r2 = r9.readBits(r1)
            r3 = 1
            if (r2 == 0) goto L_0x000e
            r7 = r0
            r8 = r3
            goto L_0x0066
        L_0x000e:
            boolean r2 = r9.readBit()
            r4 = 3
            if (r2 != 0) goto L_0x0025
            int r1 = r9.readBits(r4)
            if (r1 == 0) goto L_0x0021
            int r1 = r1 + 2
            r7 = r0
            r8 = r1
        L_0x001f:
            r2 = r6
            goto L_0x0066
        L_0x0021:
            r7 = r3
        L_0x0022:
            r2 = r6
            r8 = r2
            goto L_0x0066
        L_0x0025:
            boolean r2 = r9.readBit()
            r7 = 2
            if (r2 != 0) goto L_0x0039
            int r2 = r9.readBits(r7)
            int r2 = r2 + r1
            int r1 = r9.readBits(r1)
        L_0x0035:
            r7 = r0
            r8 = r2
            r2 = r1
            goto L_0x0066
        L_0x0039:
            int r2 = r9.readBits(r7)
            if (r2 == 0) goto L_0x0063
            if (r2 == r3) goto L_0x005f
            if (r2 == r7) goto L_0x0054
            if (r2 == r4) goto L_0x0047
            r7 = r0
            goto L_0x0022
        L_0x0047:
            r2 = 8
            int r2 = r9.readBits(r2)
            int r2 = r2 + 25
            int r1 = r9.readBits(r1)
            goto L_0x0035
        L_0x0054:
            int r2 = r9.readBits(r1)
            int r2 = r2 + 9
            int r1 = r9.readBits(r1)
            goto L_0x0035
        L_0x005f:
            r2 = r6
            r8 = r7
            r7 = r0
            goto L_0x0066
        L_0x0063:
            r7 = r0
            r8 = r3
            goto L_0x001f
        L_0x0066:
            if (r8 == 0) goto L_0x0080
            if (r14 == 0) goto L_0x0080
            if (r11 == 0) goto L_0x006e
            byte r2 = r11[r2]
        L_0x006e:
            r0 = r10[r2]
            r14.setColor(r0)
            float r1 = (float) r12
            float r2 = (float) r13
            int r0 = r12 + r8
            float r0 = (float) r0
            int r3 = r3 + r13
            float r4 = (float) r3
            r5 = r14
            r3 = r0
            r0 = r15
            r0.drawRect(r1, r2, r3, r4, r5)
        L_0x0080:
            int r12 = r12 + r8
            if (r7 == 0) goto L_0x0084
            return r12
        L_0x0084:
            r0 = r7
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.dvb.DvbParser.paint4BitPixelCodeString(androidx.media3.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint8BitPixelCodeString(androidx.media3.common.util.ParsableBitArray r9, int[] r10, byte[] r11, int r12, int r13, android.graphics.Paint r14, android.graphics.Canvas r15) {
        /*
            r6 = 0
            r0 = r6
        L_0x0002:
            r1 = 8
            int r2 = r9.readBits(r1)
            r3 = 1
            if (r2 == 0) goto L_0x000e
            r7 = r0
            r8 = r3
            goto L_0x002e
        L_0x000e:
            boolean r2 = r9.readBit()
            r4 = 7
            if (r2 != 0) goto L_0x0023
            int r1 = r9.readBits(r4)
            if (r1 == 0) goto L_0x001f
            r7 = r0
            r8 = r1
            r2 = r6
            goto L_0x002e
        L_0x001f:
            r7 = r3
            r2 = r6
            r8 = r2
            goto L_0x002e
        L_0x0023:
            int r2 = r9.readBits(r4)
            int r1 = r9.readBits(r1)
            r7 = r0
            r8 = r2
            r2 = r1
        L_0x002e:
            if (r8 == 0) goto L_0x0048
            if (r14 == 0) goto L_0x0048
            if (r11 == 0) goto L_0x0036
            byte r2 = r11[r2]
        L_0x0036:
            r0 = r10[r2]
            r14.setColor(r0)
            float r1 = (float) r12
            float r2 = (float) r13
            int r0 = r12 + r8
            float r0 = (float) r0
            int r3 = r3 + r13
            float r4 = (float) r3
            r5 = r14
            r3 = r0
            r0 = r15
            r0.drawRect(r1, r2, r3, r4, r5)
        L_0x0048:
            int r12 = r12 + r8
            if (r7 == 0) goto L_0x004c
            return r12
        L_0x004c:
            r0 = r7
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.dvb.DvbParser.paint8BitPixelCodeString(androidx.media3.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    private static void paintPixelDataSubBlock(byte[] bArr, int[] iArr, int i2, int i7, int i8, Paint paint, Canvas canvas2) {
        Canvas canvas3;
        Paint paint2;
        int[] iArr2;
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        byte[] bArr5;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        byte[] bArr6 = null;
        byte[] bArr7 = null;
        int i10 = i7;
        int i11 = i8;
        byte[] bArr8 = null;
        while (parsableBitArray.bitsLeft() != 0) {
            int readBits = parsableBitArray.readBits(8);
            if (readBits != 240) {
                switch (readBits) {
                    case 16:
                        iArr2 = iArr;
                        Paint paint3 = paint;
                        canvas3 = canvas2;
                        if (i2 != 3) {
                            if (i2 != 2) {
                                bArr2 = null;
                                paint2 = paint3;
                                i10 = paint2BitPixelCodeString(parsableBitArray, iArr2, bArr2, i10, i11, paint2, canvas3);
                                parsableBitArray.byteAlign();
                                break;
                            } else if (bArr7 == null) {
                                bArr3 = defaultMap2To4;
                            } else {
                                bArr3 = bArr7;
                            }
                        } else if (bArr8 == null) {
                            bArr3 = defaultMap2To8;
                        } else {
                            bArr3 = bArr8;
                        }
                        paint2 = paint3;
                        bArr2 = bArr3;
                        i10 = paint2BitPixelCodeString(parsableBitArray, iArr2, bArr2, i10, i11, paint2, canvas3);
                        parsableBitArray.byteAlign();
                    case 17:
                        iArr2 = iArr;
                        Paint paint4 = paint;
                        canvas3 = canvas2;
                        if (i2 == 3) {
                            if (bArr6 == null) {
                                bArr5 = defaultMap4To8;
                            } else {
                                bArr5 = bArr6;
                            }
                            bArr4 = bArr5;
                        } else {
                            bArr4 = null;
                        }
                        paint2 = paint4;
                        i10 = paint4BitPixelCodeString(parsableBitArray, iArr2, bArr4, i10, i11, paint2, canvas3);
                        Paint paint5 = paint2;
                        parsableBitArray.byteAlign();
                        break;
                    case 18:
                        iArr2 = iArr;
                        paint2 = paint;
                        canvas3 = canvas2;
                        i10 = paint8BitPixelCodeString(parsableBitArray, iArr2, (byte[]) null, i10, i11, paint2, canvas3);
                        break;
                    default:
                        switch (readBits) {
                            case 32:
                                bArr7 = buildClutMapTable(4, 4, parsableBitArray);
                                break;
                            case 33:
                                bArr8 = buildClutMapTable(4, 8, parsableBitArray);
                                break;
                            case 34:
                                bArr6 = buildClutMapTable(16, 8, parsableBitArray);
                                break;
                        }
                        iArr2 = iArr;
                        paint2 = paint;
                        canvas3 = canvas2;
                        break;
                }
            } else {
                iArr2 = iArr;
                paint2 = paint;
                canvas3 = canvas2;
                i11 += 2;
                i10 = i7;
            }
            iArr = iArr2;
            paint = paint2;
            canvas2 = canvas3;
        }
    }

    private static void paintPixelDataSubBlocks(ObjectData objectData, ClutDefinition clutDefinition, int i2, int i7, int i8, Paint paint, Canvas canvas2) {
        int[] iArr;
        if (i2 == 3) {
            iArr = clutDefinition.clutEntries8Bit;
        } else if (i2 == 2) {
            iArr = clutDefinition.clutEntries4Bit;
        } else {
            iArr = clutDefinition.clutEntries2Bit;
        }
        int[] iArr2 = iArr;
        int i10 = i2;
        int i11 = i7;
        int i12 = i8;
        Paint paint2 = paint;
        Canvas canvas3 = canvas2;
        paintPixelDataSubBlock(objectData.topFieldData, iArr2, i10, i11, i12, paint2, canvas3);
        paintPixelDataSubBlock(objectData.bottomFieldData, iArr2, i10, i11, i12 + 1, paint2, canvas3);
    }

    private static ClutDefinition parseClutDefinition(ParsableBitArray parsableBitArray, int i2) {
        int[] iArr;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int i13 = 8;
        int readBits = parsableBitArray2.readBits(8);
        parsableBitArray2.skipBits(8);
        int i14 = 2;
        int i15 = i2 - 2;
        int[] generateDefault2BitClutEntries = generateDefault2BitClutEntries();
        int[] generateDefault4BitClutEntries = generateDefault4BitClutEntries();
        int[] generateDefault8BitClutEntries = generateDefault8BitClutEntries();
        while (i15 > 0) {
            int readBits2 = parsableBitArray2.readBits(i13);
            int readBits3 = parsableBitArray2.readBits(i13);
            if ((readBits3 & 128) != 0) {
                iArr = generateDefault2BitClutEntries;
            } else if ((readBits3 & 64) != 0) {
                iArr = generateDefault4BitClutEntries;
            } else {
                iArr = generateDefault8BitClutEntries;
            }
            if ((readBits3 & 1) != 0) {
                i11 = parsableBitArray2.readBits(i13);
                i10 = parsableBitArray2.readBits(i13);
                i8 = parsableBitArray2.readBits(i13);
                i7 = parsableBitArray2.readBits(i13);
                i12 = i15 - 6;
            } else {
                i8 = parsableBitArray2.readBits(4) << 4;
                i12 = i15 - 4;
                int readBits4 = parsableBitArray2.readBits(4) << 4;
                i7 = parsableBitArray2.readBits(i14) << 6;
                i11 = parsableBitArray2.readBits(6) << i14;
                i10 = readBits4;
            }
            if (i11 == 0) {
                i7 = 255;
                i10 = 0;
                i8 = 0;
            }
            double d = (double) i11;
            double d2 = (double) (i10 - 128);
            double d3 = (double) (i8 - 128);
            iArr[readBits2] = getColor((byte) (255 - (i7 & ScoverState.TYPE_NFC_SMART_COVER)), Util.constrainValue((int) ((1.402d * d2) + d), 0, (int) ScoverState.TYPE_NFC_SMART_COVER), Util.constrainValue((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 0, (int) ScoverState.TYPE_NFC_SMART_COVER), Util.constrainValue((int) ((d3 * 1.772d) + d), 0, (int) ScoverState.TYPE_NFC_SMART_COVER));
            i15 = i12;
            readBits = readBits;
            i13 = 8;
            i14 = 2;
        }
        return new ClutDefinition(readBits, generateDefault2BitClutEntries, generateDefault4BitClutEntries, generateDefault8BitClutEntries);
    }

    private static DisplayDefinition parseDisplayDefinition(ParsableBitArray parsableBitArray) {
        int i2;
        int i7;
        int i8;
        int i10;
        parsableBitArray.skipBits(4);
        boolean readBit = parsableBitArray.readBit();
        parsableBitArray.skipBits(3);
        int readBits = parsableBitArray.readBits(16);
        int readBits2 = parsableBitArray.readBits(16);
        if (readBit) {
            int readBits3 = parsableBitArray.readBits(16);
            int readBits4 = parsableBitArray.readBits(16);
            int readBits5 = parsableBitArray.readBits(16);
            i2 = parsableBitArray.readBits(16);
            i8 = readBits4;
            i7 = readBits5;
            i10 = readBits3;
        } else {
            i10 = 0;
            i7 = 0;
            i8 = readBits;
            i2 = readBits2;
        }
        return new DisplayDefinition(readBits, readBits2, i10, i8, i7, i2);
    }

    private static ObjectData parseObjectData(ParsableBitArray parsableBitArray) {
        byte[] bArr;
        int readBits = parsableBitArray.readBits(16);
        parsableBitArray.skipBits(4);
        int readBits2 = parsableBitArray.readBits(2);
        boolean readBit = parsableBitArray.readBit();
        parsableBitArray.skipBits(1);
        byte[] bArr2 = Util.EMPTY_BYTE_ARRAY;
        if (readBits2 == 1) {
            parsableBitArray.skipBits(parsableBitArray.readBits(8) * 16);
        } else if (readBits2 == 0) {
            int readBits3 = parsableBitArray.readBits(16);
            int readBits4 = parsableBitArray.readBits(16);
            if (readBits3 > 0) {
                bArr2 = new byte[readBits3];
                parsableBitArray.readBytes(bArr2, 0, readBits3);
            }
            if (readBits4 > 0) {
                bArr = new byte[readBits4];
                parsableBitArray.readBytes(bArr, 0, readBits4);
                return new ObjectData(readBits, readBit, bArr2, bArr);
            }
        }
        bArr = bArr2;
        return new ObjectData(readBits, readBit, bArr2, bArr);
    }

    private static PageComposition parsePageComposition(ParsableBitArray parsableBitArray, int i2) {
        int readBits = parsableBitArray.readBits(8);
        int readBits2 = parsableBitArray.readBits(4);
        int readBits3 = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(2);
        int i7 = i2 - 2;
        SparseArray sparseArray = new SparseArray();
        while (i7 > 0) {
            int readBits4 = parsableBitArray.readBits(8);
            parsableBitArray.skipBits(8);
            i7 -= 6;
            sparseArray.put(readBits4, new PageRegion(parsableBitArray.readBits(16), parsableBitArray.readBits(16)));
        }
        return new PageComposition(readBits, readBits2, readBits3, sparseArray);
    }

    private static RegionComposition parseRegionComposition(ParsableBitArray parsableBitArray, int i2) {
        int i7;
        int i8;
        char c5;
        int i10;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int readBits = parsableBitArray2.readBits(8);
        int i11 = 4;
        parsableBitArray2.skipBits(4);
        boolean readBit = parsableBitArray2.readBit();
        parsableBitArray2.skipBits(3);
        int i12 = 16;
        int readBits2 = parsableBitArray2.readBits(16);
        int readBits3 = parsableBitArray2.readBits(16);
        int i13 = readBits2;
        int readBits4 = parsableBitArray2.readBits(3);
        int readBits5 = parsableBitArray2.readBits(3);
        int i14 = 2;
        parsableBitArray2.skipBits(2);
        int i15 = readBits3;
        int i16 = readBits5;
        int i17 = i13;
        int readBits6 = parsableBitArray2.readBits(8);
        int readBits7 = parsableBitArray2.readBits(8);
        int i18 = i15;
        int readBits8 = parsableBitArray2.readBits(4);
        int i19 = readBits7;
        int readBits9 = parsableBitArray2.readBits(2);
        parsableBitArray2.skipBits(2);
        int i20 = i2 - 10;
        int i21 = i18;
        SparseArray sparseArray = new SparseArray();
        while (i20 > 0) {
            int readBits10 = parsableBitArray2.readBits(i12);
            int readBits11 = parsableBitArray2.readBits(i14);
            int readBits12 = parsableBitArray2.readBits(i14);
            int readBits13 = parsableBitArray2.readBits(12);
            parsableBitArray2.skipBits(i11);
            int readBits14 = parsableBitArray2.readBits(12);
            int i22 = i20 - 6;
            if (readBits11 != 1) {
                i10 = 2;
                if (readBits11 != 2) {
                    i8 = 0;
                    i7 = 0;
                    i20 = i22;
                    c5 = 8;
                    sparseArray.put(readBits10, new RegionObject(readBits11, readBits12, readBits13, readBits14, i8, i7));
                    char c6 = c5;
                    i12 = 16;
                    i14 = i10;
                    i11 = 4;
                }
            } else {
                i10 = 2;
            }
            c5 = 8;
            i20 -= 8;
            i8 = parsableBitArray2.readBits(8);
            i7 = parsableBitArray2.readBits(8);
            sparseArray.put(readBits10, new RegionObject(readBits11, readBits12, readBits13, readBits14, i8, i7));
            char c62 = c5;
            i12 = 16;
            i14 = i10;
            i11 = 4;
        }
        return new RegionComposition(readBits, readBit, i17, i21, readBits4, i16, readBits6, i19, readBits8, readBits9, sparseArray);
    }

    private static void parseSubtitlingSegment(ParsableBitArray parsableBitArray, SubtitleService subtitleService2) {
        RegionComposition regionComposition;
        int readBits = parsableBitArray.readBits(8);
        int readBits2 = parsableBitArray.readBits(16);
        int readBits3 = parsableBitArray.readBits(16);
        int bytePosition = parsableBitArray.getBytePosition() + readBits3;
        if (readBits3 * 8 > parsableBitArray.bitsLeft()) {
            Log.w("DvbParser", "Data field length exceeds limit");
            parsableBitArray.skipBits(parsableBitArray.bitsLeft());
            return;
        }
        switch (readBits) {
            case 16:
                if (readBits2 == subtitleService2.subtitlePageId) {
                    PageComposition pageComposition = subtitleService2.pageComposition;
                    PageComposition parsePageComposition = parsePageComposition(parsableBitArray, readBits3);
                    if (parsePageComposition.state == 0) {
                        if (!(pageComposition == null || pageComposition.version == parsePageComposition.version)) {
                            subtitleService2.pageComposition = parsePageComposition;
                            break;
                        }
                    } else {
                        subtitleService2.pageComposition = parsePageComposition;
                        subtitleService2.regions.clear();
                        subtitleService2.cluts.clear();
                        subtitleService2.objects.clear();
                        break;
                    }
                }
                break;
            case 17:
                PageComposition pageComposition2 = subtitleService2.pageComposition;
                if (readBits2 == subtitleService2.subtitlePageId && pageComposition2 != null) {
                    RegionComposition parseRegionComposition = parseRegionComposition(parsableBitArray, readBits3);
                    if (pageComposition2.state == 0 && (regionComposition = subtitleService2.regions.get(parseRegionComposition.id)) != null) {
                        parseRegionComposition.mergeFrom(regionComposition);
                    }
                    subtitleService2.regions.put(parseRegionComposition.id, parseRegionComposition);
                    break;
                }
            case 18:
                if (readBits2 != subtitleService2.subtitlePageId) {
                    if (readBits2 == subtitleService2.ancillaryPageId) {
                        ClutDefinition parseClutDefinition = parseClutDefinition(parsableBitArray, readBits3);
                        subtitleService2.ancillaryCluts.put(parseClutDefinition.id, parseClutDefinition);
                        break;
                    }
                } else {
                    ClutDefinition parseClutDefinition2 = parseClutDefinition(parsableBitArray, readBits3);
                    subtitleService2.cluts.put(parseClutDefinition2.id, parseClutDefinition2);
                    break;
                }
                break;
            case 19:
                if (readBits2 != subtitleService2.subtitlePageId) {
                    if (readBits2 == subtitleService2.ancillaryPageId) {
                        ObjectData parseObjectData = parseObjectData(parsableBitArray);
                        subtitleService2.ancillaryObjects.put(parseObjectData.id, parseObjectData);
                        break;
                    }
                } else {
                    ObjectData parseObjectData2 = parseObjectData(parsableBitArray);
                    subtitleService2.objects.put(parseObjectData2.id, parseObjectData2);
                    break;
                }
                break;
            case 20:
                if (readBits2 == subtitleService2.subtitlePageId) {
                    subtitleService2.displayDefinition = parseDisplayDefinition(parsableBitArray);
                    break;
                }
                break;
        }
        parsableBitArray.skipBytes(bytePosition - parsableBitArray.getBytePosition());
    }

    public int getCueReplacementBehavior() {
        return 2;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr, i7 + i2);
        parsableBitArray.setPosition(i2);
        consumer.accept(parse(parsableBitArray));
    }

    public void reset() {
        this.subtitleService.reset();
    }

    private CuesWithTiming parse(ParsableBitArray parsableBitArray) {
        int i2;
        SparseArray<PageRegion> sparseArray;
        int i7;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        while (parsableBitArray2.bitsLeft() >= 48 && parsableBitArray2.readBits(8) == 15) {
            parseSubtitlingSegment(parsableBitArray2, this.subtitleService);
        }
        SubtitleService subtitleService2 = this.subtitleService;
        PageComposition pageComposition = subtitleService2.pageComposition;
        if (pageComposition == null) {
            G g = U.e;
            return new CuesWithTiming(y0.f278h, -9223372036854775807L, -9223372036854775807L);
        }
        DisplayDefinition displayDefinition = subtitleService2.displayDefinition;
        if (displayDefinition == null) {
            displayDefinition = this.defaultDisplayDefinition;
        }
        Bitmap bitmap2 = this.bitmap;
        if (!(bitmap2 != null && displayDefinition.width + 1 == bitmap2.getWidth() && displayDefinition.height + 1 == this.bitmap.getHeight())) {
            Bitmap createBitmap = Bitmap.createBitmap(displayDefinition.width + 1, displayDefinition.height + 1, Bitmap.Config.ARGB_8888);
            this.bitmap = createBitmap;
            this.canvas.setBitmap(createBitmap);
        }
        ArrayList arrayList = new ArrayList();
        SparseArray<PageRegion> sparseArray2 = pageComposition.regions;
        int i8 = 0;
        while (i8 < sparseArray2.size()) {
            this.canvas.save();
            PageRegion valueAt = sparseArray2.valueAt(i8);
            RegionComposition regionComposition = this.subtitleService.regions.get(sparseArray2.keyAt(i8));
            int i10 = valueAt.horizontalAddress + displayDefinition.horizontalPositionMinimum;
            int i11 = valueAt.verticalAddress + displayDefinition.verticalPositionMinimum;
            this.canvas.clipRect(i10, i11, Math.min(regionComposition.width + i10, displayDefinition.horizontalPositionMaximum), Math.min(regionComposition.height + i11, displayDefinition.verticalPositionMaximum));
            ClutDefinition clutDefinition = this.subtitleService.cluts.get(regionComposition.clutId);
            if (clutDefinition == null && (clutDefinition = this.subtitleService.ancillaryCluts.get(regionComposition.clutId)) == null) {
                clutDefinition = this.defaultClutDefinition;
            }
            ClutDefinition clutDefinition2 = clutDefinition;
            SparseArray<RegionObject> sparseArray3 = regionComposition.regionObjects;
            int i12 = 0;
            while (i12 < sparseArray3.size()) {
                int keyAt = sparseArray3.keyAt(i12);
                RegionObject valueAt2 = sparseArray3.valueAt(i12);
                ObjectData objectData = this.subtitleService.objects.get(keyAt);
                if (objectData == null) {
                    objectData = this.subtitleService.ancillaryObjects.get(keyAt);
                }
                if (objectData != null) {
                    Paint paint = objectData.nonModifyingColorFlag ? null : this.defaultPaint;
                    sparseArray = sparseArray2;
                    i7 = i12;
                    paintPixelDataSubBlocks(objectData, clutDefinition2, regionComposition.depth, valueAt2.horizontalPosition + i10, valueAt2.verticalPosition + i11, paint, this.canvas);
                } else {
                    sparseArray = sparseArray2;
                    i7 = i12;
                }
                i12 = i7 + 1;
                sparseArray2 = sparseArray;
            }
            SparseArray<PageRegion> sparseArray4 = sparseArray2;
            if (regionComposition.fillFlag) {
                int i13 = regionComposition.depth;
                if (i13 == 3) {
                    i2 = clutDefinition2.clutEntries8Bit[regionComposition.pixelCode8Bit];
                } else if (i13 == 2) {
                    i2 = clutDefinition2.clutEntries4Bit[regionComposition.pixelCode4Bit];
                } else {
                    i2 = clutDefinition2.clutEntries2Bit[regionComposition.pixelCode2Bit];
                }
                this.fillRegionPaint.setColor(i2);
                this.canvas.drawRect((float) i10, (float) i11, (float) (regionComposition.width + i10), (float) (regionComposition.height + i11), this.fillRegionPaint);
            }
            arrayList.add(new Cue.Builder().setBitmap(Bitmap.createBitmap(this.bitmap, i10, i11, regionComposition.width, regionComposition.height)).setPosition(((float) i10) / ((float) displayDefinition.width)).setPositionAnchor(0).setLine(((float) i11) / ((float) displayDefinition.height), 0).setLineAnchor(0).setSize(((float) regionComposition.width) / ((float) displayDefinition.width)).setBitmapHeight(((float) regionComposition.height) / ((float) displayDefinition.height)).build());
            this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.canvas.restore();
            i8++;
            sparseArray2 = sparseArray4;
        }
        return new CuesWithTiming(arrayList, -9223372036854775807L, -9223372036854775807L);
    }
}
