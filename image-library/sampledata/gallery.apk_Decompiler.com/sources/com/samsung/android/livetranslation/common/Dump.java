package com.samsung.android.livetranslation.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Environment;
import com.samsung.android.livetranslation.text.SceneText;
import com.samsung.android.livetranslation.util.LTTLogger;
import com.samsung.android.livetranslation.util.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Dump {
    public static boolean DUMP_ENABLE = true;
    private static boolean IS_ANNOTATION_ENABLED = false;
    public static boolean IS_INIT_DUMP_SUCCESS = false;
    public static boolean IS_MASK_DUMP_ENABLE = false;
    public static boolean IS_OCR_DUMP_ENABLE = false;
    public static boolean IS_OCR_JSON_DUMP_ENABLED = false;
    public static boolean IS_PADDED_POLY_ENABLED = false;
    public static boolean IS_RENDERED_FRAME_ENABLED = false;
    public static boolean IS_TRANSLATED_DUMP_ENABLED = false;
    private static boolean LEVEL_CHAR = false;
    private static boolean LEVEL_LINE = false;
    private static boolean LEVEL_PARAGRAPH = false;
    private static boolean LEVEL_WORD = false;
    private static int LINE_WIDTH = 0;
    private static final String PREF_NAME = "LttEngineSharedPreference";
    public static String RENDERING_TYPE = "H";
    private static final String TAG = "Dump";
    private static int TEXT_SIZE = 0;
    private static boolean isScaledImage = false;
    private static Bitmap originalImage;
    private static Bitmap scaledImage;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Key {
        PARAGRAPH,
        LINE,
        WORD,
        CHAR,
        OCR,
        MASK,
        PADDED,
        ANNOTATION,
        RENDERED_FRAME,
        DUMP_OCR_JSON,
        DUMP_TRANSLATION_STRING,
        RENDERING_TYPE,
        LINE_WIDTH,
        TEXT_SIZE
    }

    private static void annotatePolys(Point[] pointArr, Canvas canvas, Paint paint, float f, Paint paint2, Paint paint3) {
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
            canvas.drawText(i2 + "", (float) point2.x, (float) point2.y, paint3);
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

    public static void drawOCR(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList, boolean z, String str) {
        Point[] pointArr;
        boolean z3 = z;
        String str2 = str;
        String str3 = TAG;
        LTTLogger.d(str3, "Padding enabled : " + z3);
        Bitmap bitmap = originalImage;
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap copy = bitmap.copy(config, true);
        if ((str2.equals("upscaled") || str2.contains("padded")) && isScaledImage) {
            copy = scaledImage.copy(config, true);
        }
        Canvas canvas = new Canvas(copy);
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        paint2.setColor(-16776961);
        paint2.setStrokeWidth((float) LINE_WIDTH);
        Paint paint3 = new Paint();
        paint.setTextSize((float) TEXT_SIZE);
        paint.setColor(-16711936);
        paint3.setColor(-65536);
        Iterator<SceneText> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            SceneText next = it.next();
            paint2.setColor(-16776961);
            Paint paint4 = paint2;
            float f = 15.0f;
            if (LEVEL_PARAGRAPH) {
                if (z3) {
                    pointArr = next.getPaddedBoxPoly();
                } else {
                    pointArr = next.getPoly();
                }
                drawRect(pointArr, paint4, canvas, 15.0f, paint, paint3);
            }
            Iterator<SceneText> it2 = next.getComponents().iterator();
            while (it2.hasNext()) {
                SceneText next2 = it2.next();
                paint4.setColor(-16711936);
                if (LEVEL_LINE) {
                    drawRect(next2.getPoly(), paint4, canvas, f, paint, paint3);
                }
                Iterator<SceneText> it3 = next2.getComponents().iterator();
                while (it3.hasNext()) {
                    SceneText next3 = it3.next();
                    paint4.setColor(-65536);
                    if (LEVEL_WORD) {
                        drawRect(next3.getPoly(), paint4, canvas, f, paint, paint3);
                    }
                    Iterator<SceneText> it4 = next3.getComponents().iterator();
                    while (it4.hasNext()) {
                        SceneText next4 = it4.next();
                        paint4.setColor(-256);
                        if (LEVEL_CHAR) {
                            f = 15.0f;
                            drawRect(next4.getPoly(), paint4, canvas, 15.0f, paint, paint3);
                        } else {
                            f = 15.0f;
                        }
                    }
                }
            }
            paint2 = paint4;
        }
        dumpBitmap(copy, "ocr_dump_" + str2 + Util.getTimeInMilliSecond() + ".png", "OCR");
    }

    private static void drawRect(Point[] pointArr, Paint paint, Canvas canvas, float f, Paint paint2, Paint paint3) {
        Point point;
        Point point2;
        if (pointArr != null) {
            for (int i2 = 0; i2 < pointArr.length; i2++) {
                if (i2 == pointArr.length - 1) {
                    point2 = new Point(pointArr[i2]);
                    point = new Point(pointArr[0]);
                } else {
                    point2 = new Point(pointArr[i2]);
                    point = new Point(pointArr[i2 + 1]);
                }
                canvas.drawLine((float) point2.x, (float) point2.y, (float) point.x, (float) point.y, paint);
                if (IS_ANNOTATION_ENABLED) {
                    annotatePolys(pointArr, canvas, paint, f, paint2, paint3);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005f A[SYNTHETIC, Splitter:B:19:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065 A[SYNTHETIC, Splitter:B:22:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void dumpBitmap(android.graphics.Bitmap r6, java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "dump success: "
            java.lang.String r1 = TAG
            java.lang.String r2 = "Dump bitmap"
            com.samsung.android.livetranslation.util.LTTLogger.d(r1, r2)
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0053 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0053 }
            r4.<init>()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r5 = getStoragePath(r8)     // Catch:{ Exception -> 0x0053 }
            r4.append(r5)     // Catch:{ Exception -> 0x0053 }
            r4.append(r7)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0053 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0053 }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r4 = 100
            r6.compress(r2, r4, r3)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r3.close()     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r6.<init>(r0)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            java.lang.String r8 = getStoragePath(r8)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r6.append(r8)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r6.append(r7)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            com.samsung.android.livetranslation.util.LTTLogger.d(r1, r6)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r3.close()     // Catch:{ IOException -> 0x0046 }
            return
        L_0x0046:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0062
        L_0x004b:
            r6 = move-exception
            r2 = r3
            goto L_0x0063
        L_0x004e:
            r6 = move-exception
            r2 = r3
            goto L_0x0054
        L_0x0051:
            r6 = move-exception
            goto L_0x0063
        L_0x0053:
            r6 = move-exception
        L_0x0054:
            java.lang.String r7 = TAG     // Catch:{ all -> 0x0051 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x0051 }
            com.samsung.android.livetranslation.util.LTTLogger.e(r7, r6)     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0062:
            return
        L_0x0063:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ IOException -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r7 = move-exception
            r7.printStackTrace()
        L_0x006d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.livetranslation.common.Dump.dumpBitmap(android.graphics.Bitmap, java.lang.String, java.lang.String):void");
    }

    public static void dumpStringToFile(String str, String str2, String str3) {
        FileWriter fileWriter;
        String str4 = TAG;
        LTTLogger.d(str4, "dumpStringToFile");
        try {
            File file = new File(getStoragePath(str3) + str2);
            LTTLogger.d(str4, "dump path: " + getStoragePath(str3) + str2);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create file: " + file.getAbsolutePath());
                }
            }
            fileWriter = new FileWriter(file);
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

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A[SYNTHETIC, Splitter:B:24:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044 A[SYNTHETIC, Splitter:B:27:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void dumpYUV(byte[] r3, java.lang.String r4) {
        /*
            r0 = 0
            java.lang.String r1 = "YUV"
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0018 }
            java.lang.String r1 = getStoragePath(r1)     // Catch:{ Exception -> 0x0018 }
            r2.<init>(r1, r4)     // Catch:{ Exception -> 0x0018 }
            boolean r4 = r2.exists()     // Catch:{ Exception -> 0x0018 }
            if (r4 != 0) goto L_0x001a
            r2.createNewFile()     // Catch:{ Exception -> 0x0018 }
            goto L_0x001a
        L_0x0016:
            r3 = move-exception
            goto L_0x0042
        L_0x0018:
            r3 = move-exception
            goto L_0x0033
        L_0x001a:
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0018 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0018 }
            r4.write(r3)     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            r4.close()     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            r4.close()     // Catch:{ IOException -> 0x0029 }
            return
        L_0x0029:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0041
        L_0x002e:
            r3 = move-exception
            r0 = r4
            goto L_0x0042
        L_0x0031:
            r3 = move-exception
            r0 = r4
        L_0x0033:
            java.lang.String r4 = TAG     // Catch:{ all -> 0x0016 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x0016 }
            com.samsung.android.livetranslation.util.LTTLogger.e(r4, r3)     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x0041
            r0.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0041:
            return
        L_0x0042:
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.livetranslation.common.Dump.dumpYUV(byte[], java.lang.String):void");
    }

    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PREF_NAME, 0);
    }

    public static String getStoragePath(String str) {
        String str2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/Dump/" + str + "/";
        createFolder(str2);
        return str2;
    }

    public static void initDump(Context context, Bitmap bitmap) {
        SharedPreferences sharedPreference = getSharedPreference(context);
        Key key = Key.OCR;
        if (!sharedPreference.contains(key.toString()) || !DUMP_ENABLE) {
            IS_INIT_DUMP_SUCCESS = false;
            LTTLogger.d(TAG, "Dump not initialized");
            return;
        }
        String str = TAG;
        LTTLogger.d(str, "Dump initialized");
        IS_INIT_DUMP_SUCCESS = true;
        originalImage = bitmap;
        if (sharedPreference.contains(key.toString())) {
            IS_OCR_DUMP_ENABLE = sharedPreference.getBoolean(key.toString(), false);
        }
        Key key2 = Key.MASK;
        if (sharedPreference.contains(key2.toString())) {
            IS_MASK_DUMP_ENABLE = sharedPreference.getBoolean(key2.toString(), false);
        }
        Key key3 = Key.PARAGRAPH;
        if (sharedPreference.contains(key3.toString())) {
            LEVEL_PARAGRAPH = sharedPreference.getBoolean(key3.toString(), false);
        }
        Key key4 = Key.LINE;
        if (sharedPreference.contains(key4.toString())) {
            LEVEL_LINE = sharedPreference.getBoolean(key4.toString(), false);
        }
        Key key5 = Key.WORD;
        if (sharedPreference.contains(key5.toString())) {
            LEVEL_WORD = sharedPreference.getBoolean(key5.toString(), false);
        }
        Key key6 = Key.CHAR;
        if (sharedPreference.contains(key6.toString())) {
            LEVEL_CHAR = sharedPreference.getBoolean(key6.toString(), false);
        }
        Key key7 = Key.PADDED;
        if (sharedPreference.contains(key7.toString())) {
            IS_PADDED_POLY_ENABLED = sharedPreference.getBoolean(key7.toString(), false);
        }
        Key key8 = Key.ANNOTATION;
        if (sharedPreference.contains(key8.toString())) {
            IS_ANNOTATION_ENABLED = sharedPreference.getBoolean(key8.toString(), false);
        }
        Key key9 = Key.RENDERED_FRAME;
        if (sharedPreference.contains(key9.toString())) {
            IS_RENDERED_FRAME_ENABLED = sharedPreference.getBoolean(key9.toString(), false);
        }
        Key key10 = Key.DUMP_OCR_JSON;
        if (sharedPreference.contains(key10.toString())) {
            IS_OCR_JSON_DUMP_ENABLED = sharedPreference.getBoolean(key10.toString(), false);
        }
        Key key11 = Key.DUMP_TRANSLATION_STRING;
        if (sharedPreference.contains(key11.toString())) {
            IS_TRANSLATED_DUMP_ENABLED = sharedPreference.getBoolean(key11.toString(), false);
        }
        Key key12 = Key.RENDERING_TYPE;
        if (sharedPreference.contains(key12.toString())) {
            RENDERING_TYPE = sharedPreference.getString(key12.toString(), "H");
        }
        Key key13 = Key.LINE_WIDTH;
        if (sharedPreference.contains(key13.toString())) {
            LINE_WIDTH = sharedPreference.getInt(key13.toString(), 5);
        }
        Key key14 = Key.TEXT_SIZE;
        if (sharedPreference.contains(key14.toString())) {
            TEXT_SIZE = sharedPreference.getInt(key14.toString(), 100);
        }
        LTTLogger.d(str, "IS_OCR_DUMP_ENABLE : " + IS_OCR_DUMP_ENABLE + "\nIS_MASK_DUMP_ENABLE : " + IS_MASK_DUMP_ENABLE + "\nLEVEL_PARAGRAPH : " + LEVEL_PARAGRAPH + "\nLEVEL_LINE : " + LEVEL_LINE + "\nLEVEL_WORD : " + LEVEL_WORD + "\nLEVEL_CHAR : " + LEVEL_CHAR + "\nIS_PADDED_POLY_ENABLED : " + IS_PADDED_POLY_ENABLED + "\nIS_ANNOTATION_ENABLED : " + IS_ANNOTATION_ENABLED + "\nIS_RENDERED_FRAME_ENABLED :" + IS_RENDERED_FRAME_ENABLED + "\nIS_OCR_JSON_DUMP_ENABLED :" + IS_OCR_JSON_DUMP_ENABLED + "\nTRANSLATED_RESULT_DUMP :" + IS_TRANSLATED_DUMP_ENABLED + "\nRENDERING_TYPE :" + RENDERING_TYPE + "\nLINE_WIDTH : " + LINE_WIDTH + "\nTEXT_SIZE : " + TEXT_SIZE + "\n");
    }

    public static void setScaledImage(Bitmap bitmap) {
        scaledImage = bitmap;
        isScaledImage = true;
    }
}
