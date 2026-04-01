package com.samsung.android.app.sdk.deepsky.objectcapture.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import androidx.core.content.FileProvider;
import i.C0212a;
import java.io.File;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u000eJ%\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0006¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00068\u0002XT¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00068\u0002XT¢\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u00068\u0002XT¢\u0006\u0006\n\u0004\b\u001d\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b\u001e\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b\u001f\u0010\u001bR\u0014\u0010 \u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b \u0010\u001bR\u0014\u0010!\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b!\u0010\u001bR\u0014\u0010\"\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b\"\u0010\u001bR\u0014\u0010#\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b#\u0010\u001bR\u0014\u0010$\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b$\u0010\u001bR\u0014\u0010%\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b%\u0010\u001bR\u0014\u0010&\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b&\u0010\u001bR\u0014\u0010'\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b'\u0010\u001bR\u0014\u0010(\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b(\u0010\u001bR\u0014\u0010)\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b)\u0010\u001bR\u0014\u0010*\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b*\u0010\u001bR\u0014\u0010+\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b+\u0010\u001bR\u0014\u0010,\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b,\u0010\u001bR\u0014\u0010-\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b-\u0010\u001bR\u0014\u0010/\u001a\u00020.8\u0006XT¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020.8\u0006XT¢\u0006\u0006\n\u0004\b1\u00100R\u0014\u00102\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b2\u0010\u001bR\u0014\u00103\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b3\u0010\u001bR\u0014\u00104\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b4\u0010\u001b¨\u00065"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/utils/ServiceManagerUtil;", "", "<init>", "()V", "Landroid/content/Context;", "context", "", "getTemporaryClippedImageFilePath", "(Landroid/content/Context;)Ljava/lang/String;", "path", "Lme/x;", "checkPathExists", "(Ljava/lang/String;)V", "getFileName", "()Ljava/lang/String;", "Landroid/graphics/Bitmap;", "bitmap", "getImageClipperFilePath", "(Landroid/graphics/Bitmap;Landroid/content/Context;)Ljava/lang/String;", "getImageClipperDirectoryPath", "Ljava/io/File;", "file", "authority", "Landroid/net/Uri;", "getUriAndProvidePermissionStickerDB", "(Landroid/content/Context;Ljava/io/File;Ljava/lang/String;)Landroid/net/Uri;", "TAG", "Ljava/lang/String;", "TEMPORARY_FILE_TO_SEND", "TEMPORARY_DIR_PATH_TO_SEND", "PHOTO_EDIT_PACKAGE_NAME", "PHOTO_EDIT_SERVICE_CLASS_NAME", "PHOTO_EDIT_ACTIVITY_CLASS_NAME", "PHOTO_EDIT_EXTRA_SERVICE_KEY", "PHOTO_EDIT_EXTRA_SERVICE_VALUE", "PHOTO_EDIT_EXTRA_FROM_GALLERY_KEY", "PHOTO_EDIT_EXTRA_FROM_DEEP_SKY_KEY", "PHOTO_EDIT_EXTRA_FILE_PATH_KEY", "PHOTO_EDIT_EXTRA_ORIGINAL_FILE_PATH_KEY", "PHOTO_EDIT_EXTRA_SAVE_DIR_KEY", "STICKER_CENTER_PACKAGE_NAME", "STICKER_CENTER_SAVE_IMAGE_ACTION", "STICKER_CENTER_IMAGE_PATH", "STICKER_CENTER_GIF_ACCESS_TOKEN", "STICKER_CENTER_INTENT_FILTER_STRING_EXTRA_IMAGE_RECT", "STICKER_CENTER_INTENT_FILTER_STRING_EXTRA_USE_ANIMATED", "", "MSG_CLIPPED_IMAGE_DB_INSERTION_REPLY", "I", "MSG_INSERT_CLIPPED_IMAGE_TO_DB", "KEY_RESPONSE_DATA", "KEY_IS_STICKER_LIMIT_EXCEEDED", "KEY_CLIPPED_IMAGE_FILE_PATH", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ServiceManagerUtil {
    public static final ServiceManagerUtil INSTANCE = new ServiceManagerUtil();
    public static final String KEY_CLIPPED_IMAGE_FILE_PATH = "clipped_filepath";
    public static final String KEY_IS_STICKER_LIMIT_EXCEEDED = "is_sticker_limit_exceeded";
    public static final String KEY_RESPONSE_DATA = "response_data";
    public static final int MSG_CLIPPED_IMAGE_DB_INSERTION_REPLY = 1001;
    public static final int MSG_INSERT_CLIPPED_IMAGE_TO_DB = 1002;
    public static final String PHOTO_EDIT_ACTIVITY_CLASS_NAME = "com.sec.android.mimage.photoretouching.SPEActivity";
    public static final String PHOTO_EDIT_EXTRA_FILE_PATH_KEY = "filepath";
    public static final String PHOTO_EDIT_EXTRA_FROM_DEEP_SKY_KEY = "isFromDeepsky";
    public static final String PHOTO_EDIT_EXTRA_FROM_GALLERY_KEY = "isFromGalleryDetails";
    public static final String PHOTO_EDIT_EXTRA_ORIGINAL_FILE_PATH_KEY = "originalFilePath";
    public static final String PHOTO_EDIT_EXTRA_SAVE_DIR_KEY = "saveDir";
    public static final String PHOTO_EDIT_EXTRA_SERVICE_KEY = "service";
    public static final String PHOTO_EDIT_EXTRA_SERVICE_VALUE = "spe_lasso";
    public static final String PHOTO_EDIT_PACKAGE_NAME = "com.sec.android.mimage.photoretouching";
    public static final String PHOTO_EDIT_SERVICE_CLASS_NAME = "com.sec.android.mimage.photoretouching.service.MyStickerService";
    public static final String STICKER_CENTER_GIF_ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String STICKER_CENTER_IMAGE_PATH = "IMAGE_PATH";
    public static final String STICKER_CENTER_INTENT_FILTER_STRING_EXTRA_IMAGE_RECT = "IMAGE_RECT";
    public static final String STICKER_CENTER_INTENT_FILTER_STRING_EXTRA_USE_ANIMATED = "USE_ANIMATED";
    public static final String STICKER_CENTER_PACKAGE_NAME = "com.samsung.android.stickercenter";
    public static final String STICKER_CENTER_SAVE_IMAGE_ACTION = "com.samsung.android.stickercenter.ACTION_STICKER_FILTER";
    private static final String TAG = "ServiceManagerUtil";
    private static final String TEMPORARY_DIR_PATH_TO_SEND = "/.clippedImages/";
    private static final String TEMPORARY_FILE_TO_SEND = "clippedImage.png";

    private ServiceManagerUtil() {
    }

    private final void checkPathExists(String str) {
        File file = new File(str);
        if (!file.exists() && file.mkdirs()) {
            file.canWrite();
            file.canRead();
        }
    }

    private final String getFileName() {
        return TEMPORARY_FILE_TO_SEND;
    }

    private final String getTemporaryClippedImageFilePath(Context context) {
        File externalFilesDir = context.getExternalFilesDir((String) null);
        Objects.requireNonNull(externalFilesDir);
        String A10 = C0212a.A(externalFilesDir.getAbsolutePath(), TEMPORARY_DIR_PATH_TO_SEND);
        checkPathExists(A10);
        return A10;
    }

    public final String getImageClipperDirectoryPath() {
        String A10 = C0212a.A(Environment.getExternalStorageDirectory().getPath(), "/DCIM/Clipped images/");
        checkPathExists(A10);
        return A10;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        B1.a.g(r5, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getImageClipperFilePath(android.graphics.Bitmap r4, android.content.Context r5) {
        /*
            r3 = this;
            java.lang.String r0 = "ServiceManagerUtil"
            java.lang.String r1 = "bitmap"
            kotlin.jvm.internal.j.e(r4, r1)
            java.lang.String r1 = "context"
            kotlin.jvm.internal.j.e(r5, r1)
            java.lang.String r5 = r3.getTemporaryClippedImageFilePath(r5)     // Catch:{ Exception -> 0x003b }
            java.lang.String r3 = r3.getFileName()     // Catch:{ Exception -> 0x003b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003b }
            r1.<init>()     // Catch:{ Exception -> 0x003b }
            r1.append(r5)     // Catch:{ Exception -> 0x003b }
            r1.append(r3)     // Catch:{ Exception -> 0x003b }
            java.lang.String r3 = r1.toString()     // Catch:{ Exception -> 0x003b }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x003b }
            r5.<init>(r3)     // Catch:{ Exception -> 0x003b }
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x0043 }
            r2 = 100
            boolean r4 = r4.compress(r1, r2, r5)     // Catch:{ all -> 0x0043 }
            r5.close()     // Catch:{ Exception -> 0x003b }
            if (r4 == 0) goto L_0x003d
            java.lang.String r4 = "getImageClipperFilePath is success."
            com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger.i(r0, r4)     // Catch:{ Exception -> 0x003b }
            return r3
        L_0x003b:
            r3 = move-exception
            goto L_0x004a
        L_0x003d:
            java.lang.String r4 = "getImageClipperFilePath is fail."
            com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger.i(r0, r4)     // Catch:{ Exception -> 0x003b }
            return r3
        L_0x0043:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r4 = move-exception
            B1.a.g(r5, r3)     // Catch:{ Exception -> 0x003b }
            throw r4     // Catch:{ Exception -> 0x003b }
        L_0x004a:
            r3.printStackTrace()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "getImageClipperFilePath error : "
            r3.<init>(r4)
            me.x r4 = me.x.f4917a
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger.e(r0, r3)
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil.getImageClipperFilePath(android.graphics.Bitmap, android.content.Context):java.lang.String");
    }

    public final Uri getUriAndProvidePermissionStickerDB(Context context, File file, String str) {
        j.e(context, "context");
        j.e(file, "file");
        j.e(str, "authority");
        Uri uriForFile = FileProvider.getUriForFile(context, str, file);
        context.grantUriPermission(STICKER_CENTER_PACKAGE_NAME, uriForFile, 3);
        j.b(uriForFile);
        return uriForFile;
    }
}
