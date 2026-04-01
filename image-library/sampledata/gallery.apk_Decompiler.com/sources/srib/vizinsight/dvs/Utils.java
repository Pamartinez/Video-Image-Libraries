package srib.vizinsight.dvs;

import N2.j;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.icu.text.SimpleDateFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.util.Size;
import android.webkit.MimeTypeMap;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoParser;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import com.samsung.android.sdk.cover.ScoverState;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Utils {
    private static int THRESHOLD = 2868;
    private static int scaleFactor;

    public static Bitmap getDownSizedBitmap(Bitmap bitmap) {
        float f;
        float f5;
        boolean z = false;
        scaleFactor = 0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int max = Math.max(width, height);
        if (max <= THRESHOLD) {
            return bitmap;
        }
        if (height > width) {
            z = true;
        }
        if (z) {
            f = ((float) width) / ((float) height);
        } else {
            f = ((float) height) / ((float) width);
        }
        Log.d("Utils", "getResizedBitmap ratio:" + f + " isPortrait:" + z + " THRESHOLD:" + THRESHOLD);
        while (max > THRESHOLD) {
            max /= 2;
            scaleFactor++;
            Log.d("Utils", "getResizedBitmap scaleFactor:" + scaleFactor);
        }
        float f8 = (float) max;
        int i2 = (int) (f * f8);
        if (z) {
            f5 = (float) i2;
        } else {
            f5 = f8;
        }
        if (!z) {
            f8 = (float) i2;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) f5, (int) f8, true);
        Log.d("Utils", "getResizedBitmap final width:" + createScaledBitmap.getWidth() + " height:" + createScaledBitmap.getHeight());
        return createScaledBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFilePath(android.content.Context r11, android.net.Uri r12) {
        /*
            android.content.Context r0 = r11.getApplicationContext()
            boolean r0 = android.provider.DocumentsContract.isDocumentUri(r0, r12)
            r1 = 0
            if (r0 == 0) goto L_0x0053
            boolean r0 = isExternalStorageDocument(r12)
            java.lang.String r2 = ":"
            r3 = 1
            if (r0 == 0) goto L_0x0037
            java.lang.String r11 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String[] r11 = r11.split(r2)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            r12.append(r0)
            java.lang.String r0 = "/"
            r12.append(r0)
            r11 = r11[r3]
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            return r11
        L_0x0037:
            boolean r0 = isDownloadsDocument(r12)
            if (r0 == 0) goto L_0x0057
            java.lang.String r12 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String r0 = "content://downloads/public_downloads"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.Long r12 = java.lang.Long.valueOf(r12)
            long r2 = r12.longValue()
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r0, r2)
        L_0x0053:
            r6 = r12
            r8 = r1
            r9 = r8
            goto L_0x0093
        L_0x0057:
            boolean r0 = isMediaDocument(r12)
            if (r0 == 0) goto L_0x0053
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String[] r0 = r0.split(r2)
            r2 = 0
            r4 = r0[r2]
            java.lang.String r5 = "image"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0073
            android.net.Uri r12 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L_0x0088
        L_0x0073:
            java.lang.String r5 = "video"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x007e
            android.net.Uri r12 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            goto L_0x0088
        L_0x007e:
            java.lang.String r5 = "audio"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0088
            android.net.Uri r12 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        L_0x0088:
            java.lang.String[] r4 = new java.lang.String[r3]
            r0 = r0[r3]
            r4[r2] = r0
            java.lang.String r0 = "_id=?"
            r6 = r12
            r8 = r0
            r9 = r4
        L_0x0093:
            java.lang.String r12 = "content"
            java.lang.String r0 = r6.getScheme()
            boolean r12 = r12.equalsIgnoreCase(r0)
            if (r12 == 0) goto L_0x00bd
            java.lang.String r12 = "_data"
            java.lang.String[] r7 = new java.lang.String[]{r12}
            android.content.ContentResolver r5 = r11.getContentResolver()     // Catch:{ Exception -> 0x00ce }
            r10 = 0
            android.database.Cursor r11 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00ce }
            int r12 = r11.getColumnIndexOrThrow(r12)     // Catch:{ Exception -> 0x00ce }
            boolean r0 = r11.moveToFirst()     // Catch:{ Exception -> 0x00ce }
            if (r0 == 0) goto L_0x00ce
            java.lang.String r11 = r11.getString(r12)     // Catch:{ Exception -> 0x00ce }
            return r11
        L_0x00bd:
            java.lang.String r11 = "file"
            java.lang.String r12 = r6.getScheme()
            boolean r11 = r11.equalsIgnoreCase(r12)
            if (r11 == 0) goto L_0x00ce
            java.lang.String r11 = r6.getPath()
            return r11
        L_0x00ce:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: srib.vizinsight.dvs.Utils.getFilePath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static String getGIFORVideoPath(String str, String str2, int i2) {
        String str3;
        j.w("getGIFORVideoPath : inputImagePath : ", str, "Utils");
        if (str == null || str.lastIndexOf("/") == -1) {
            return null;
        }
        String substring = str.substring(str.lastIndexOf(".") + 1);
        if (i2 == 0) {
            str3 = "gif";
        } else {
            str3 = substring;
        }
        String l = C0212a.l(".", substring);
        return str.replaceAll(l, str2 + "." + str3);
    }

    public static int getImageOrientation(String str) {
        ExifInterface exifInterface;
        try {
            exifInterface = new ExifInterface(str);
        } catch (IOException e) {
            e.printStackTrace();
            exifInterface = null;
        }
        return exifInterface.getAttributeInt("Orientation", 0);
    }

    public static String getImagePath(String str, String str2) {
        if (str == null || str.lastIndexOf("/") == -1) {
            return null;
        }
        String l = C0212a.l(".", str.substring(str.lastIndexOf(".") + 1));
        return str.replaceAll(l, str2 + ".png");
    }

    public static int getIndexFromTime(int i2, int i7, int i8) {
        if (i2 >= i8) {
            return i7 - 1;
        }
        int i10 = i2 % i8;
        if (i10 < 0) {
            i10 += i8;
        }
        return (int) Math.round((((double) (i7 - 1)) / ((double) i8)) * ((double) i10));
    }

    public static Size getLowerResolution(float f, float f5, int i2, int i7) {
        Log.i("Utils", "getLowerResolution frameWidth:" + f + " frameHeight:" + f5 + " max_res:" + i7 + "orientation:" + i2);
        if (i7 != -1) {
            float f8 = (float) i7;
            if (f > f8 || f5 > f8) {
                if (f > f5) {
                    f5 = (f5 / f) * f8;
                    f = f8;
                } else {
                    f = (f / f5) * f8;
                    f5 = f8;
                }
            }
        }
        StringBuilder sb2 = new StringBuilder("getLowerResolution Final new frameWidth:");
        int i8 = (int) f;
        sb2.append(i8);
        sb2.append(" new frameHeight:");
        sb2.append(f5);
        Log.i("Utils", sb2.toString());
        return new Size(i8, (int) f5);
    }

    public static String getPath(Context context, Uri uri) {
        String str = null;
        if (uri == null) {
            return null;
        }
        Uri uri2 = uri;
        Cursor query = context.getContentResolver().query(uri2, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
        if (query != null) {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
            query.moveToFirst();
            String string = query.getString(columnIndexOrThrow);
            query.close();
            j.w("Returning from A ", string, "Utils");
            return string;
        }
        try {
            str = getFilePath(context, uri2);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        j.w("Returning from B ", str, "Utils");
        return str;
    }

    public static byte[] getRGBA(int i2, int i7, Bitmap bitmap) {
        int[] iArr = new int[(i2 * i7)];
        byte[] bArr = new byte[(i2 * 4 * i7)];
        int i8 = i2;
        int i10 = i7;
        bitmap.getPixels(iArr, 0, i8, 0, 0, i2, i10);
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < i10; i13++) {
            for (int i14 = 0; i14 < i8; i14++) {
                int i15 = iArr[i12];
                int i16 = (-16777216 & i15) >> 24;
                int i17 = (16711680 & i15) >> 16;
                int i18 = (65280 & i15) >> 8;
                int i19 = i15 & ScoverState.TYPE_NFC_SMART_COVER;
                int i20 = i11 + 1;
                if (i17 < 0) {
                    i17 = 0;
                } else if (i17 > 255) {
                    i17 = 255;
                }
                bArr[i11] = (byte) i17;
                int i21 = i11 + 2;
                if (i18 < 0) {
                    i18 = 0;
                } else if (i18 > 255) {
                    i18 = 255;
                }
                bArr[i20] = (byte) i18;
                int i22 = i11 + 3;
                if (i19 < 0) {
                    i19 = 0;
                } else if (i19 > 255) {
                    i19 = 255;
                }
                bArr[i21] = (byte) i19;
                i11 += 4;
                if (i16 < 0) {
                    i16 = 0;
                } else if (i16 > 255) {
                    i16 = 255;
                }
                bArr[i22] = (byte) i16;
                i12++;
            }
        }
        return bArr;
    }

    public static String getResultFilePath(Context context, Uri uri) {
        String str = get_video_folder_name();
        Log.d("Utils", "getGifFilePath : gallery_folder_name : " + str);
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        Log.d("Utils", "getGifFilePath : currentDateandTime : " + format);
        String str2 = "Clipped_image_" + format + ".gif";
        Log.d("Utils", "getGifFilePath : image_folder_name : " + str2);
        String str3 = str + str2;
        j.w("getGifFilePath : gif_result_filepath : ", str3, "Utils");
        return str3;
    }

    public static MotionPhotoVideoUtils.MotionPhotoInfo getSEFDataPosition(File file) {
        MotionPhotoParser.DataPosition64 parseSEFTail = new MotionPhotoParser(file).parseSEFTail(isJpeg(file.toString()));
        if (parseSEFTail == null) {
            Log.e("Utils getSEFDataPosition", "Fail to get sef info");
            return null;
        }
        Log.d("Utils getSEFDataPosition", "MP info - offset:" + parseSEFTail.getOffset() + " length:" + parseSEFTail.getLength());
        return new MotionPhotoVideoUtils.MotionPhotoInfo(parseSEFTail.getOffset(), parseSEFTail.getLength(), parseSEFTail.isMPV2());
    }

    public static int getScaleFactor() {
        return scaleFactor;
    }

    public static int getTimeFromIndex(int i2, int i7, int i8, int i10) {
        return Math.max(((int) ((((double) (i8 - 1)) / ((double) i7)) * ((double) i2))) - ((int) ((75.0d / ((double) i10)) * 10.0d)), 0);
    }

    public static Bitmap get_exif_corrected_bitmap(String str) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            Matrix matrix = new Matrix();
            if (attributeInt == 3) {
                matrix.preRotate(180.0f);
            } else if (attributeInt == 6) {
                matrix.preRotate(90.0f);
            } else if (attributeInt == 8) {
                matrix.preRotate(270.0f);
            }
            return Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, false);
        } catch (Exception e) {
            e.printStackTrace();
            return decodeFile;
        }
    }

    public static String get_video_folder_name() {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        j.w("getGifFilePath : gallery_folder_path : ", absolutePath, "Utils");
        try {
            return absolutePath + "/DCIM/Clipped images/";
        } catch (ArrayIndexOutOfBoundsException unused) {
            return "/sdcard/DCIM/Clipped images/";
        }
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isJpeg(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg")) {
            return true;
        }
        return false;
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isValidImagePath(String str) {
        if (str == null || str.lastIndexOf(".") == -1) {
            return false;
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(str.lastIndexOf(".") + 1)).split("/")[0].equals("image");
    }

    public static void list_files(String str, ArrayList<File> arrayList) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    arrayList.add(file);
                } else if (file.isDirectory()) {
                    list_files(file.getAbsolutePath(), arrayList);
                }
            }
        }
    }

    public static void list_files_in_first_dir(String str, ArrayList<File> arrayList) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    arrayList.add(file);
                } else {
                    file.isDirectory();
                }
            }
        }
    }

    public static Bitmap overlay(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, new Matrix(), (Paint) null);
        canvas.drawBitmap(bitmap2, new Matrix(), (Paint) null);
        return createBitmap;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i2) {
        Matrix matrix = new Matrix();
        switch (i2) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bitmap2 = bitmap;
            Bitmap createBitmap = Bitmap.createBitmap(bitmap2, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap2.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
}
