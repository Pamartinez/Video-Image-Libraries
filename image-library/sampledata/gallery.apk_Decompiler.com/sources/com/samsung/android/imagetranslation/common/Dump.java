package com.samsung.android.imagetranslation.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Environment;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Dump {
    public static boolean DISABLE_INPAINTING = false;
    public static boolean DUMP_ENABLE = true;
    public static String INPAINTING_MASK_LEVEL = "L";
    public static float INPAINTING_MASK_PADDING = 0.0f;
    private static boolean IS_ANNOTATION_ENABLED = false;
    public static boolean IS_INIT_DUMP_SUCCESS = false;
    public static boolean IS_INPAINTED_FRAME_ENABLED = false;
    public static boolean IS_INPAINTED_UPSCALED_IMAGE_DUMP = false;
    public static boolean IS_INPAINTING_MASK_DUMP_ENABLE = false;
    public static boolean IS_MASK_DUMP_ENABLE = false;
    public static boolean IS_OCR_DUMP_ENABLE = false;
    public static boolean IS_OCR_JSON_DUMP_ENABLED = false;
    public static boolean IS_ORIGINAL_IMAGE_DUMP = false;
    public static boolean IS_ORIGINAL_UPSCALED_IMAGE_DUMP = false;
    public static boolean IS_PADDED_POLY_ENABLED = false;
    public static boolean IS_RENDERED_FRAME_ENABLED = false;
    public static boolean IS_TRANSLATED_DUMP_ENABLED = false;
    public static boolean IS_UNIFORM_FONT_ENABLED = true;
    private static boolean LEVEL_CHAR = false;
    private static boolean LEVEL_LINE = false;
    private static boolean LEVEL_PARAGRAPH = false;
    private static boolean LEVEL_WORD = false;
    private static int LINE_WIDTH = 0;
    private static final String PREF_NAME = "LttEngineSharedPreference";
    public static boolean PRINT_BLOCK_TYPE = false;
    public static String RENDERING_TYPE = "H";
    private static final String TAG = "Dump";
    private static int TEXT_SIZE;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Key {
        PARAGRAPH,
        LINE,
        WORD,
        CHAR,
        OCR,
        MASK,
        INPAINTING_MASK,
        PADDED,
        ANNOTATION,
        RENDERED_FRAME,
        DUMP_OCR_JSON,
        DUMP_TRANSLATION_STRING,
        DUMP_INPAINTED_FRAME,
        DISABLE_INPAINTING,
        PRINT_BLOCK_TYPE,
        TEXT_RENDERED_FRAME,
        RENDERING_TYPE,
        LINE_WIDTH,
        TEXT_SIZE,
        INPAINTING_MASK_PADDING,
        INPAINTING_MASK_LEVEL,
        IS_UNIFORM_FONT_ENABLED
    }

    private static void annotatePolys(Point[] pointArr, Canvas canvas, Paint paint, float f, Paint paint2) {
        Point point;
        Point point2;
        int i2 = 1;
        for (int i7 = 0; i7 < pointArr.length; i7++) {
            if (i7 == pointArr.length - 1) {
                point2 = new Point(pointArr[i7]);
                point = new Point(pointArr[0]);
            } else {
                point2 = new Point(pointArr[i7]);
                point = new Point(pointArr[i7 + 1]);
            }
            canvas.drawLine((float) point2.x, (float) point2.y, (float) point.x, (float) point.y, paint);
            canvas.drawText(i2 + "", (float) point2.x, (float) point2.y, paint2);
            i2++;
        }
    }

    public static void createFolder(String str) {
        String str2 = TAG;
        LTTLogger.d(str2, "make directory : " + str);
        File file = new File(str);
        if (!file.isDirectory() && !file.mkdirs()) {
            LTTLogger.d(str2, "make directory!!, fail");
        }
    }

    /* JADX WARNING: type inference failed for: r16v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void drawOCR(android.graphics.Bitmap r17, java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.imagetranslation.jni.SceneText> r18, boolean r19) {
        /*
            boolean r0 = IS_INIT_DUMP_SUCCESS
            if (r0 == 0) goto L_0x0149
            boolean r0 = IS_OCR_DUMP_ENABLE
            if (r0 != 0) goto L_0x000a
            goto L_0x0149
        L_0x000a:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
            r1 = 1
            r2 = r17
            android.graphics.Bitmap r0 = r2.copy(r0, r1)
            android.graphics.Canvas r2 = new android.graphics.Canvas
            r2.<init>(r0)
            int r3 = TEXT_SIZE
            float r3 = (float) r3
            int r4 = LINE_WIDTH
            float r4 = (float) r4
            android.graphics.Paint r5 = new android.graphics.Paint
            r5.<init>()
            android.graphics.Paint r6 = new android.graphics.Paint
            r6.<init>()
            r7 = -16776961(0xffffffffff0000ff, float:-1.7014636E38)
            r6.setColor(r7)
            r6.setStrokeWidth(r4)
            r4 = -65536(0xffffffffffff0000, float:NaN)
            r5.setColor(r4)
            r5.setTextSize(r3)
            java.util.Iterator r3 = r18.iterator()
            r8 = 0
            r9 = r8
        L_0x003f:
            boolean r10 = r3.hasNext()
            if (r10 == 0) goto L_0x012c
            java.lang.Object r10 = r3.next()
            com.samsung.android.imagetranslation.jni.SceneText r10 = (com.samsung.android.imagetranslation.jni.SceneText) r10
            r6.setColor(r7)
            boolean r11 = LEVEL_PARAGRAPH
            r12 = -256(0xffffffffffffff00, float:NaN)
            r13 = 1097859072(0x41700000, float:15.0)
            if (r11 == 0) goto L_0x00ba
            android.graphics.Point[] r11 = r10.getPoly()
            boolean r14 = r10.isParagraphRendering()
            if (r14 == 0) goto L_0x0063
            java.lang.String r14 = "H"
            goto L_0x0065
        L_0x0063:
            java.lang.String r14 = "L"
        L_0x0065:
            boolean r15 = PRINT_BLOCK_TYPE
            if (r15 == 0) goto L_0x009a
            r15 = r11[r8]
            r16 = r1
            int r1 = r15.x
            int r1 = r1 + -5
            float r1 = (float) r1
            int r15 = r15.y
            int r15 = r15 + -2
            float r15 = (float) r15
            r2.drawText(r14, r1, r15, r5)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r14 = "B-"
            r1.<init>(r14)
            int r14 = r9 + 1
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            r11 = r11[r16]
            int r14 = r11.x
            int r14 = r14 + 5
            float r14 = (float) r14
            int r11 = r11.y
            int r11 = r11 + -2
            float r11 = (float) r11
            r2.drawText(r1, r14, r11, r5)
            goto L_0x009c
        L_0x009a:
            r16 = r1
        L_0x009c:
            int r1 = r10.getBlockType()
            com.samsung.android.imagetranslation.data.LttOcrResult$BlockInfo$BLOCK_TYPE r11 = com.samsung.android.imagetranslation.data.LttOcrResult.BlockInfo.BLOCK_TYPE.TABULAR
            int r11 = r11.ordinal()
            if (r1 != r11) goto L_0x00ab
            r6.setColor(r12)
        L_0x00ab:
            if (r19 == 0) goto L_0x00b2
            android.graphics.Point[] r1 = r10.getPaddedBoxPoly()
            goto L_0x00b6
        L_0x00b2:
            android.graphics.Point[] r1 = r10.getPoly()
        L_0x00b6:
            drawRect(r1, r6, r2, r13, r5)
            goto L_0x00bc
        L_0x00ba:
            r16 = r1
        L_0x00bc:
            java.util.concurrent.CopyOnWriteArrayList r1 = r10.getComponents()
            java.util.Iterator r1 = r1.iterator()
        L_0x00c4:
            boolean r10 = r1.hasNext()
            if (r10 == 0) goto L_0x0126
            java.lang.Object r10 = r1.next()
            com.samsung.android.imagetranslation.jni.SceneText r10 = (com.samsung.android.imagetranslation.jni.SceneText) r10
            r11 = -16711936(0xffffffffff00ff00, float:-1.7146522E38)
            r6.setColor(r11)
            boolean r11 = LEVEL_LINE
            if (r11 == 0) goto L_0x00e1
            android.graphics.Point[] r11 = r10.getPoly()
            drawRect(r11, r6, r2, r13, r5)
        L_0x00e1:
            java.util.concurrent.CopyOnWriteArrayList r10 = r10.getComponents()
            java.util.Iterator r10 = r10.iterator()
        L_0x00e9:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00c4
            java.lang.Object r11 = r10.next()
            com.samsung.android.imagetranslation.jni.SceneText r11 = (com.samsung.android.imagetranslation.jni.SceneText) r11
            r6.setColor(r4)
            boolean r14 = LEVEL_WORD
            if (r14 == 0) goto L_0x0103
            android.graphics.Point[] r14 = r11.getPoly()
            drawRect(r14, r6, r2, r13, r5)
        L_0x0103:
            java.util.concurrent.CopyOnWriteArrayList r11 = r11.getComponents()
            java.util.Iterator r11 = r11.iterator()
        L_0x010b:
            boolean r14 = r11.hasNext()
            if (r14 == 0) goto L_0x00e9
            java.lang.Object r14 = r11.next()
            com.samsung.android.imagetranslation.jni.SceneText r14 = (com.samsung.android.imagetranslation.jni.SceneText) r14
            r6.setColor(r12)
            boolean r15 = LEVEL_CHAR
            if (r15 == 0) goto L_0x010b
            android.graphics.Point[] r14 = r14.getPoly()
            drawRect(r14, r6, r2, r13, r5)
            goto L_0x010b
        L_0x0126:
            int r9 = r9 + 1
            r1 = r16
            goto L_0x003f
        L_0x012c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "ocr_dump_"
            r1.<init>(r2)
            long r2 = java.lang.System.currentTimeMillis()
            r1.append(r2)
            java.lang.String r2 = ".png"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "OCR"
            dumpBitmap(r0, r1, r2)
            return
        L_0x0149:
            java.lang.String r0 = TAG
            java.lang.String r1 = "OCR Dump is not enabled"
            com.samsung.android.imagetranslation.common.LTTLogger.d(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.imagetranslation.common.Dump.drawOCR(android.graphics.Bitmap, java.util.concurrent.CopyOnWriteArrayList, boolean):void");
    }

    private static void drawRect(Point[] pointArr, Paint paint, Canvas canvas, float f, Paint paint2) {
        Point point;
        Point point2;
        if (pointArr != null) {
            int i2 = 0;
            while (i2 < pointArr.length) {
                if (i2 == pointArr.length - 1) {
                    point2 = new Point(pointArr[i2]);
                    point = new Point(pointArr[0]);
                } else {
                    point2 = new Point(pointArr[i2]);
                    point = new Point(pointArr[i2 + 1]);
                }
                Paint paint3 = paint;
                Canvas canvas2 = canvas;
                canvas2.drawLine((float) point2.x, (float) point2.y, (float) point.x, (float) point.y, paint3);
                if (IS_ANNOTATION_ENABLED) {
                    annotatePolys(pointArr, canvas2, paint3, f, paint2);
                }
                i2++;
                canvas = canvas2;
                paint = paint3;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056 A[SYNTHETIC, Splitter:B:23:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005c A[SYNTHETIC, Splitter:B:26:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void dumpBitmap(android.graphics.Bitmap r4, java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "Dump bitmap"
            com.samsung.android.imagetranslation.common.LTTLogger.d(r0, r1)
            boolean r1 = IS_INIT_DUMP_SUCCESS
            if (r1 != 0) goto L_0x0011
            java.lang.String r4 = "Dump is not initialized"
            com.samsung.android.imagetranslation.common.LTTLogger.d(r0, r4)
            return
        L_0x0011:
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x004a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004a }
            r3.<init>()     // Catch:{ Exception -> 0x004a }
            java.lang.String r6 = getStoragePath(r6)     // Catch:{ Exception -> 0x004a }
            r3.append(r6)     // Catch:{ Exception -> 0x004a }
            r3.append(r5)     // Catch:{ Exception -> 0x004a }
            java.lang.String r5 = r3.toString()     // Catch:{ Exception -> 0x004a }
            r2.<init>(r5)     // Catch:{ Exception -> 0x004a }
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            r6 = 100
            r4.compress(r5, r6, r2)     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            r2.close()     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            java.lang.String r4 = "bitmap dump success"
            com.samsung.android.imagetranslation.common.LTTLogger.d(r0, r4)     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            r2.close()     // Catch:{ IOException -> 0x003d }
            return
        L_0x003d:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0059
        L_0x0042:
            r4 = move-exception
            r1 = r2
            goto L_0x005a
        L_0x0045:
            r4 = move-exception
            r1 = r2
            goto L_0x004b
        L_0x0048:
            r4 = move-exception
            goto L_0x005a
        L_0x004a:
            r4 = move-exception
        L_0x004b:
            java.lang.String r5 = TAG     // Catch:{ all -> 0x0048 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0048 }
            com.samsung.android.imagetranslation.common.LTTLogger.e(r5, r4)     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ IOException -> 0x003d }
        L_0x0059:
            return
        L_0x005a:
            if (r1 == 0) goto L_0x0064
            r1.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0064:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.imagetranslation.common.Dump.dumpBitmap(android.graphics.Bitmap, java.lang.String, java.lang.String):void");
    }

    public static void dumpFile(String str, String str2, String str3) {
        FileWriter fileWriter;
        String str4 = TAG;
        LTTLogger.d(str4, "dumpFile");
        if (!IS_INIT_DUMP_SUCCESS) {
            LTTLogger.d(str4, "Dump is not initialized");
            return;
        }
        try {
            File file = new File(getStoragePath(str3) + str2);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create file: " + file.getAbsolutePath());
                }
            }
            fileWriter = new FileWriter(file, true);
            fileWriter.write(str);
            fileWriter.close();
            return;
        } catch (Exception e) {
            LTTLogger.e(TAG, e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void dumpStringListToJsonFile(List<String> list, String str, String str2) {
        FileWriter fileWriter;
        String json = new GsonBuilder().setPrettyPrinting().create().toJson((Object) list, new TypeToken<List<String>>() {
        }.getType());
        try {
            File file = new File(getStoragePath(str2) + str);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create file: " + file.getAbsolutePath());
                }
            }
            fileWriter = new FileWriter(file, true);
            fileWriter.write(json);
            fileWriter.close();
            return;
        } catch (Exception e) {
            LTTLogger.e(TAG, e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PREF_NAME, 0);
    }

    public static String getStoragePath(String str) {
        String str2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/LttDump/" + str + "/";
        createFolder(str2);
        return str2;
    }

    public static void initDump(Context context) {
        if (!Config.IS_USER_DEBUG) {
            LTTLogger.d(TAG, "Dump not supported as device is not userdebug binary");
            return;
        }
        SharedPreferences sharedPreference = getSharedPreference(context);
        Key key = Key.OCR;
        if (!sharedPreference.contains(key.toString()) || !DUMP_ENABLE) {
            IS_INIT_DUMP_SUCCESS = false;
            LTTLogger.d(TAG, "Dump not initialized as dump file is not present");
            return;
        }
        IS_INIT_DUMP_SUCCESS = true;
        String str = TAG;
        LTTLogger.d(str, "Dump initialized");
        if (sharedPreference.contains(key.toString())) {
            IS_OCR_DUMP_ENABLE = sharedPreference.getBoolean(key.toString(), false);
        }
        Key key2 = Key.MASK;
        if (sharedPreference.contains(key2.toString())) {
            IS_MASK_DUMP_ENABLE = sharedPreference.getBoolean(key2.toString(), false);
        }
        Key key3 = Key.INPAINTING_MASK;
        if (sharedPreference.contains(key3.toString())) {
            IS_INPAINTING_MASK_DUMP_ENABLE = sharedPreference.getBoolean(key3.toString(), false);
        }
        Key key4 = Key.PARAGRAPH;
        if (sharedPreference.contains(key4.toString())) {
            LEVEL_PARAGRAPH = sharedPreference.getBoolean(key4.toString(), false);
        }
        Key key5 = Key.LINE;
        if (sharedPreference.contains(key5.toString())) {
            LEVEL_LINE = sharedPreference.getBoolean(key5.toString(), false);
        }
        Key key6 = Key.WORD;
        if (sharedPreference.contains(key6.toString())) {
            LEVEL_WORD = sharedPreference.getBoolean(key6.toString(), false);
        }
        Key key7 = Key.CHAR;
        if (sharedPreference.contains(key7.toString())) {
            LEVEL_CHAR = sharedPreference.getBoolean(key7.toString(), false);
        }
        Key key8 = Key.PADDED;
        if (sharedPreference.contains(key8.toString())) {
            IS_PADDED_POLY_ENABLED = sharedPreference.getBoolean(key8.toString(), false);
        }
        Key key9 = Key.ANNOTATION;
        if (sharedPreference.contains(key9.toString())) {
            IS_ANNOTATION_ENABLED = sharedPreference.getBoolean(key9.toString(), false);
        }
        Key key10 = Key.RENDERED_FRAME;
        if (sharedPreference.contains(key10.toString())) {
            IS_RENDERED_FRAME_ENABLED = sharedPreference.getBoolean(key10.toString(), false);
        }
        Key key11 = Key.DUMP_OCR_JSON;
        if (sharedPreference.contains(key11.toString())) {
            IS_OCR_JSON_DUMP_ENABLED = sharedPreference.getBoolean(key11.toString(), false);
        }
        Key key12 = Key.DUMP_TRANSLATION_STRING;
        if (sharedPreference.contains(key12.toString())) {
            IS_TRANSLATED_DUMP_ENABLED = sharedPreference.getBoolean(key12.toString(), false);
        }
        Key key13 = Key.DUMP_INPAINTED_FRAME;
        if (sharedPreference.contains(key13.toString())) {
            IS_INPAINTED_FRAME_ENABLED = sharedPreference.getBoolean(key13.toString(), false);
        }
        Key key14 = Key.DISABLE_INPAINTING;
        if (sharedPreference.contains(key14.toString())) {
            DISABLE_INPAINTING = sharedPreference.getBoolean(key14.toString(), false);
        }
        Key key15 = Key.PRINT_BLOCK_TYPE;
        if (sharedPreference.contains(key15.toString())) {
            PRINT_BLOCK_TYPE = sharedPreference.getBoolean(key15.toString(), false);
        }
        Key key16 = Key.RENDERING_TYPE;
        if (sharedPreference.contains(key16.toString())) {
            RENDERING_TYPE = sharedPreference.getString(key16.toString(), "H");
        }
        Key key17 = Key.LINE_WIDTH;
        if (sharedPreference.contains(key17.toString())) {
            LINE_WIDTH = sharedPreference.getInt(key17.toString(), 5);
        }
        Key key18 = Key.TEXT_SIZE;
        if (sharedPreference.contains(key18.toString())) {
            TEXT_SIZE = sharedPreference.getInt(key18.toString(), 100);
        }
        Key key19 = Key.INPAINTING_MASK_PADDING;
        if (sharedPreference.contains(key19.toString())) {
            INPAINTING_MASK_PADDING = sharedPreference.getFloat(key19.toString(), 0.0f);
        }
        Key key20 = Key.INPAINTING_MASK_LEVEL;
        if (sharedPreference.contains(key20.toString())) {
            INPAINTING_MASK_LEVEL = sharedPreference.getString(key20.toString(), "L");
        }
        Key key21 = Key.IS_UNIFORM_FONT_ENABLED;
        if (sharedPreference.contains(key21.toString())) {
            IS_UNIFORM_FONT_ENABLED = sharedPreference.getBoolean(key21.toString(), true);
        }
        LTTLogger.d(str, "IS_OCR_DUMP_ENABLE : " + IS_OCR_DUMP_ENABLE + "\nIS_MASK_DUMP_ENABLE : " + IS_MASK_DUMP_ENABLE + "\nIS_INPAINTING_MASK_DUMP_ENABLE : " + IS_INPAINTING_MASK_DUMP_ENABLE + "\nLEVEL_PARAGRAPH : " + LEVEL_PARAGRAPH + "\nLEVEL_LINE : " + LEVEL_LINE + "\nLEVEL_WORD : " + LEVEL_WORD + "\nLEVEL_CHAR : " + LEVEL_CHAR + "\nIS_PADDED_POLY_ENABLED : " + IS_PADDED_POLY_ENABLED + "\nIS_ANNOTATION_ENABLED : " + IS_ANNOTATION_ENABLED + "\nIS_RENDERED_FRAME_ENABLED :" + IS_RENDERED_FRAME_ENABLED + "\nIS_INPAINTED_FRAME_ENABLED :" + IS_INPAINTED_FRAME_ENABLED + "\nDISABLE_INPAINTING :" + DISABLE_INPAINTING + "\nPRINT_BLOCK_TYPE :" + PRINT_BLOCK_TYPE + "\nIS_OCR_JSON_DUMP_ENABLED :" + IS_OCR_JSON_DUMP_ENABLED + "\nTRANSLATED_RESULT_DUMP :" + IS_TRANSLATED_DUMP_ENABLED + "\nRENDERING_TYPE :" + RENDERING_TYPE + "\nLINE_WIDTH : " + LINE_WIDTH + "\nTEXT_SIZE : " + TEXT_SIZE + "\nINPAINTING_MASK_PADDING : " + INPAINTING_MASK_PADDING + "\nINPAINTING_MASK_LEVEL : " + INPAINTING_MASK_LEVEL + "\nIS_UNIFORM_FONT_ENABLED : " + IS_UNIFORM_FONT_ENABLED);
    }

    public static Bitmap intArrayToBitmap(int[] iArr, int i2, int i7) {
        Bitmap createBitmap = Bitmap.createBitmap(i2, i7, Bitmap.Config.ARGB_8888);
        for (int i8 = 0; i8 < iArr.length; i8++) {
            int i10 = iArr[i8];
            createBitmap.setPixel(i8 % i2, i8 / i2, Color.rgb(i10, i10, i10));
        }
        return createBitmap;
    }

    public static boolean isDumpEnabled() {
        return IS_INIT_DUMP_SUCCESS;
    }
}
