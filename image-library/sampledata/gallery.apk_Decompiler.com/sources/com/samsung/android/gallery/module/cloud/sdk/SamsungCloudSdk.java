package com.samsung.android.gallery.module.cloud.sdk;

import A.a;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.samsung.android.gallery.compat.account.SamsungAccountTokenLoader;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sum.core.Def;
import com.samsung.scsp.framework.core.Scsp;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.ScspSuppliers;
import com.samsung.scsp.framework.core.identity.AccountInfoSupplier;
import com.samsung.scsp.framework.core.listeners.NetworkStatusListener;
import com.samsung.scsp.framework.core.listeners.ProgressListener;
import com.samsung.scsp.media.Media;
import com.samsung.scsp.media.MediaConstants;
import com.samsung.scsp.media.MediaList;
import com.samsung.scsp.media.SamsungCloudMedia;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SamsungCloudSdk {
    private static final String[] CLOUD_IMAGE_PROJECTION = {"is_favorite", "is_hide", "captured_url", "captured_app", "orientation"};
    private static final String[] CLOUD_VIDEO_PROJECTION = {"is_favorite", "is_hide", "resumePos", "isPlayed", "orientation"};
    static boolean FEATURE_USE_FD_DOWNLOAD = SdkConfig.atLeast(SdkConfig.GED.S);
    static boolean FEATURE_USE_SEC_MP = Features.isEnabled(Features.USE_SEC_MP);
    private static final MediaScanResult RESULT = new MediaScanResult();
    private static volatile SamsungAccountInfo sSamsungAccountInfo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaScanResult {
        Uri mUri = null;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScspSetup implements AccountInfoSupplier {
        static final ScspSetup instance = new ScspSetup();
        SamsungAccountInfo accountInfo;
        final ScspSuppliers scspSuppliers = new ScspSuppliers.Builder().with((AccountInfoSupplier) this).build();

        public String getAccessToken() {
            SamsungAccountInfo samsungAccountInfo = this.accountInfo;
            if (samsungAccountInfo == null) {
                return "";
            }
            return samsungAccountInfo.accessToken;
        }

        public String getUserId() {
            SamsungAccountInfo samsungAccountInfo = this.accountInfo;
            if (samsungAccountInfo == null) {
                return "";
            }
            return samsungAccountInfo.userId;
        }

        public void onUnauthorized() {
            this.accountInfo = SamsungCloudSdk.requestAccessToken(AppResources.getAppContext(), true);
        }

        public void update(Context context, SamsungAccountInfo samsungAccountInfo) {
            boolean z;
            if (this.accountInfo == null) {
                z = true;
            } else {
                z = false;
            }
            this.accountInfo = samsungAccountInfo;
            if (z) {
                Scsp.setUp(context.getApplicationContext(), "22n6hzkam0", this.scspSuppliers);
            }
        }
    }

    public static boolean checkFileValid(ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor != null && parcelFileDescriptor.getStatSize() > 0) {
            return true;
        }
        Log.e("SamsungCloudSdk", "download failed. fd size is invalid .");
        return false;
    }

    public static Media clearCloud(Context context, Media media) {
        return emptyCloud(context, media, false);
    }

    public static ArrayList<Uri> download(Context context, DownloadParams downloadParams) {
        return download(context, downloadParams, false);
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean downloadFileDirectly(android.content.Context r12, com.samsung.android.gallery.module.cloud.abstraction.DownloadParams r13) {
        /*
            java.lang.String r1 = "SamsungCloudSdk"
            java.lang.String r0 = "download directly"
            java.lang.String r2 = "create directory failed : "
            r3 = 0
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Error | Exception -> 0x0035 }
            r6 = 1
            com.samsung.android.gallery.module.cloud.sdk.SamsungAccountInfo r6 = requestAccessToken(r12, r6)     // Catch:{ Error | Exception -> 0x0035 }
            if (r6 == 0) goto L_0x009e
            com.samsung.android.gallery.support.utils.SecureFile r7 = new com.samsung.android.gallery.support.utils.SecureFile     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.String r8 = r13.getTargetPath()     // Catch:{ Error | Exception -> 0x0035 }
            r7.<init>(r8)     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.String r7 = r7.getParent()     // Catch:{ Error | Exception -> 0x0035 }
            boolean r8 = com.samsung.android.gallery.support.utils.FileUtils.makeDirectoryIfAbsent((java.lang.String) r7)     // Catch:{ Error | Exception -> 0x0035 }
            if (r8 != 0) goto L_0x0038
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Error | Exception -> 0x0035 }
            r12.<init>(r2)     // Catch:{ Error | Exception -> 0x0035 }
            r12.append(r7)     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.String r12 = r12.toString()     // Catch:{ Error | Exception -> 0x0035 }
            com.samsung.android.gallery.support.utils.Log.e(r1, r12)     // Catch:{ Error | Exception -> 0x0035 }
            return r3
        L_0x0035:
            r0 = move-exception
            r12 = r0
            goto L_0x0094
        L_0x0038:
            setupScsp(r12, r6)     // Catch:{ Error | Exception -> 0x0035 }
            com.samsung.scsp.media.SamsungCloudMedia r12 = new com.samsung.scsp.media.SamsungCloudMedia     // Catch:{ Error | Exception -> 0x0035 }
            r12.<init>()     // Catch:{ Error | Exception -> 0x0035 }
            com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller r2 = r13.getDownloadCanceller()     // Catch:{ Error | Exception -> 0x0035 }
            r6 = 0
            if (r2 == 0) goto L_0x0052
            com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller r2 = r13.getDownloadCanceller()     // Catch:{ Error | Exception -> 0x0035 }
            com.samsung.android.gallery.module.cloud.sdk.DownloadStatus r2 = (com.samsung.android.gallery.module.cloud.sdk.DownloadStatus) r2     // Catch:{ Error | Exception -> 0x0035 }
            r2.setSamsungCloudMedia(r12)     // Catch:{ Error | Exception -> 0x0035 }
            r11 = r2
            goto L_0x0053
        L_0x0052:
            r11 = r6
        L_0x0053:
            com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor r2 = r13.getDownloadMonitor()     // Catch:{ Error | Exception -> 0x0035 }
            if (r2 == 0) goto L_0x0060
            com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor r2 = r13.getDownloadMonitor()     // Catch:{ Error | Exception -> 0x0035 }
            r6 = r2
            com.samsung.android.gallery.module.cloud.sdk.DownloadMonitor r6 = (com.samsung.android.gallery.module.cloud.sdk.DownloadMonitor) r6     // Catch:{ Error | Exception -> 0x0035 }
        L_0x0060:
            r10 = r6
            com.samsung.scsp.media.Files r6 = r12.files     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.String r7 = r13.getCloudServerId()     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.String r8 = r13.getTargetPath()     // Catch:{ Error | Exception -> 0x0035 }
            com.samsung.scsp.media.MediaConstants$FileType r9 = com.samsung.scsp.media.MediaConstants.FileType.ORIGINAL     // Catch:{ Error | Exception -> 0x0035 }
            r6.downloadFile((java.lang.String) r7, (java.lang.String) r8, (com.samsung.scsp.media.MediaConstants.FileType) r9, (com.samsung.scsp.framework.core.listeners.ProgressListener) r10, (com.samsung.scsp.framework.core.listeners.NetworkStatusListener) r11)     // Catch:{ Error | Exception -> 0x0035 }
            boolean r12 = checkFileValid((com.samsung.android.gallery.module.cloud.abstraction.DownloadParams) r13)     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Error | Exception -> 0x0035 }
            r13.<init>(r0)     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r12)     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r0)     // Catch:{ Error | Exception -> 0x0035 }
            r13.append(r0)     // Catch:{ Error | Exception -> 0x0035 }
            java.lang.String r13 = r13.toString()     // Catch:{ Error | Exception -> 0x0035 }
            com.samsung.android.gallery.support.utils.Log.d(r1, r13)     // Catch:{ Error | Exception -> 0x0035 }
            return r12
        L_0x0094:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = "download directly failed. e="
            r13.<init>(r0)
            A.a.z(r12, r13, r1)
        L_0x009e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.cloud.sdk.SamsungCloudSdk.downloadFileDirectly(android.content.Context, com.samsung.android.gallery.module.cloud.abstraction.DownloadParams):boolean");
    }

    public static boolean downloadItemOriginalFile(Context context, String str, String str2, String str3, boolean z) {
        String str4;
        Log.d("SamsungCloudSdk", "download original start", Boolean.valueOf(z));
        try {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(str3)) {
                    String[] splitPathAndName = FileUtils.splitPathAndName(str2);
                    if (!TextUtils.isEmpty(splitPathAndName[0])) {
                        if (!TextUtils.isEmpty(splitPathAndName[1])) {
                            String str5 = splitPathAndName[0];
                            String str6 = splitPathAndName[1];
                            SamsungAccountInfo requestAccessToken = requestAccessToken(context, z);
                            if (requestAccessToken == null) {
                                Log.e("SamsungCloudSdk", "download original failed. samsung account info is null.");
                                Log.d("SamsungCloudSdk", "download original completed");
                                return false;
                            } else if (!FileUtils.makeDirectoryIfAbsent(str5)) {
                                Log.e("SamsungCloudSdk", "download original failed. mkdir " + str5);
                                Log.d("SamsungCloudSdk", "download original completed");
                                return false;
                            } else {
                                Media media = new Media();
                                media.photoId = str;
                                media.originalBinaryHash = str3;
                                setupScsp(context, requestAccessToken);
                                new SamsungCloudMedia().files.downloadItemOriginalFile(media, str5 + "/" + str6, (ProgressListener) null, (NetworkStatusListener) null);
                                Log.d("SamsungCloudSdk", "download original completed");
                                return true;
                            }
                        }
                    }
                    Log.e("SamsungCloudSdk", "download original failed. invalid file path");
                    Log.d("SamsungCloudSdk", "download original completed");
                    return false;
                }
            }
            if (TextUtils.isEmpty(str)) {
                str4 = "invalid cloud server id";
            } else {
                str4 = "invalid cloud original binary hash";
            }
            Log.e("SamsungCloudSdk", "download original failed. ".concat(str4));
            Log.d("SamsungCloudSdk", "download original completed");
            return false;
        } catch (ScspException e) {
            Log.e("SamsungCloudSdk", "download original failed [" + e.rcode + "] e=" + e.getMessage());
            Log.d("SamsungCloudSdk", "download original completed");
            return false;
        } catch (Exception e7) {
            Log.e((CharSequence) "SamsungCloudSdk", "download original failed", (Throwable) e7);
            Log.d("SamsungCloudSdk", "download original completed");
            return false;
        } catch (Throwable th) {
            Log.d("SamsungCloudSdk", "download original completed");
            throw th;
        }
    }

    private static Media emptyCloud(Context context, Media media, boolean z) {
        Log.d("SamsungCloudSdk", "empty cloud start", Boolean.valueOf(z));
        try {
            SamsungAccountInfo requestAccessToken = requestAccessToken(context, z);
            if (requestAccessToken == null) {
                Log.e("SamsungCloudSdk", "empty cloud failed. samsung account info is null.");
                Log.d("SamsungCloudSdk", "empty cloud completed");
                return null;
            }
            setupScsp(context, requestAccessToken);
            MediaList deletePhotos = new SamsungCloudMedia().trash.deletePhotos(getMediaList(media), (NetworkStatusListener) null);
            if (deletePhotos != null) {
                if (deletePhotos.getCount() > 0) {
                    Media media2 = deletePhotos.getList().get(0);
                    Log.d("SamsungCloudSdk", "empty cloud completed");
                    return media2;
                }
            }
            Log.w("SamsungCloudSdk", "empty cloud failed. MediaList is null or count is under 0");
            Log.d("SamsungCloudSdk", "empty cloud completed");
            return null;
        } catch (ScspException e) {
            if (CloudErrorType.isError(e.rcode)) {
                Media errorMedia = getErrorMedia(e.rcode);
                Log.d("SamsungCloudSdk", "empty cloud completed");
                return errorMedia;
            }
            Log.e("SamsungCloudSdk", "empty cloud failed. [" + e.rcode + "][" + e.getMessage() + "]");
            Log.d("SamsungCloudSdk", "empty cloud completed");
            return null;
        } catch (Error | Exception e7) {
            Log.e((CharSequence) "SamsungCloudSdk", "empty cloud failed", e7);
            Log.d("SamsungCloudSdk", "empty cloud completed");
            return null;
        } catch (Throwable th) {
            Log.d("SamsungCloudSdk", "empty cloud completed");
            throw th;
        }
    }

    public static ArrayList<Uri> getContentUriAfterDownload(Context context, Uri uri, DownloadParams downloadParams) {
        if (requestMediaScan(context, uri, downloadParams)) {
            return getScanResult();
        }
        return null;
    }

    public static String getDownloadUrl(Context context, String str) {
        return getDownloadUrl(context, str, false);
    }

    private static ArrayList<Uri> getErrorArrayList(long j2) {
        ArrayList<Uri> arrayList = new ArrayList<>();
        arrayList.add(Uri.parse(String.valueOf(j2)));
        return arrayList;
    }

    public static Media getErrorMedia(int i2) {
        Media media = new Media();
        media.rcode = Integer.valueOf(i2);
        return media;
    }

    public static List<Media> getMediaList(Media media) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(media);
        return arrayList;
    }

    public static SamsungAccountInfo getSamsungAccountInfo(Context context, boolean z) {
        return requestAccessToken(context, z);
    }

    public static ArrayList<Uri> getScanResult() {
        MediaScanResult mediaScanResult = RESULT;
        synchronized (mediaScanResult) {
            try {
                mediaScanResult.wait(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
            } catch (Exception e) {
                Log.e("SamsungCloudSdk", "result wait failed. e=" + e.getMessage());
            }
        }
        StringBuilder sb2 = new StringBuilder("media scan result : ");
        MediaScanResult mediaScanResult2 = RESULT;
        sb2.append(mediaScanResult2.mUri);
        Log.d("SamsungCloudSdk", sb2.toString());
        if (mediaScanResult2.mUri == null) {
            return null;
        }
        ArrayList<Uri> arrayList = new ArrayList<>();
        arrayList.add(mediaScanResult2.mUri);
        return arrayList;
    }

    public static String getStreamUrl(Context context, String str) {
        return getStreamUrl(context, str, false);
    }

    public static void initAccountInfo(SamsungAccountInfo samsungAccountInfo) {
        sSamsungAccountInfo = samsungAccountInfo;
    }

    public static Uri insertRecordForMerge(Context context, DownloadParams downloadParams) {
        Uri videoUri;
        String[] strArr;
        Cursor query;
        Throwable th;
        ContentValues contentValues = new ContentValues();
        if (downloadParams.getMediaType() == 1) {
            videoUri = MediaUri.getInstance().getImageUri();
        } else {
            videoUri = MediaUri.getInstance().getVideoUri();
        }
        Uri uri = videoUri;
        contentValues.put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, Integer.valueOf(downloadParams.getMediaType()));
        contentValues.put("mime_type", downloadParams.getMimeType());
        contentValues.put("relative_path", FileUtils.getRelativePath(downloadParams.getTargetPath()));
        contentValues.put("volume_name", FileUtils.getVolumeName(downloadParams.getTargetPath()));
        if (FEATURE_USE_FD_DOWNLOAD) {
            contentValues.put("is_pending", 1);
        }
        if (!SdkConfig.atLeast(SdkConfig.GED.R)) {
            contentValues.put(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME, Long.valueOf(downloadParams.getDateTaken()));
            Log.d("SamsungCloudSdk", "insertRecordForMerge: dateTaken = " + downloadParams.getDateTaken());
        }
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Uri withAppendedId = ContentUris.withAppendedId(MediaUri.getInstance().getSecCloudUri(), downloadParams.getFileId());
            if (downloadParams.getMediaType() == 1) {
                strArr = CLOUD_IMAGE_PROJECTION;
            } else {
                strArr = CLOUD_VIDEO_PROJECTION;
            }
            query = contentResolver.query(withAppendedId, strArr, (String) null, (String[]) null, (String) null, (CancellationSignal) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    setContentValues(contentValues, query, downloadParams);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("merge local cloud failed, e="), "SamsungCloudSdk");
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        try {
            Uri insert = context.getContentResolver().insert(uri, contentValues);
            if (!FEATURE_USE_FD_DOWNLOAD && SdkConfig.atLeast(SdkConfig.GED.R)) {
                FileUtils.setDateModified(downloadParams.getTargetPath(), FileUtils.getDateModified(downloadParams.getTargetPath()) - 1);
            }
            return insert;
        } catch (Exception e7) {
            Exception exc = e7;
            Log.e("SamsungCloudSdk", "insert merged valued failed, e=" + exc.getMessage());
            if (FEATURE_USE_FD_DOWNLOAD || !SdkConfig.atLeast(SdkConfig.GED.R)) {
                return null;
            }
            FileUtils.setDateModified(downloadParams.getTargetPath(), FileUtils.getDateModified(downloadParams.getTargetPath()) - 1);
            return null;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            if (!FEATURE_USE_FD_DOWNLOAD && SdkConfig.atLeast(SdkConfig.GED.R)) {
                FileUtils.setDateModified(downloadParams.getTargetPath(), FileUtils.getDateModified(downloadParams.getTargetPath()) - 1);
            }
            throw th4;
        }
        throw th;
    }

    public static ParcelFileDescriptor openFileDescriptor(Context context, Uri uri) {
        if (context == null) {
            throw new IllegalStateException("open file descriptor failed. null context");
        } else if (uri != null) {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "rw");
            if (openFileDescriptor != null) {
                return openFileDescriptor;
            }
            throw new IllegalStateException("open file descriptor failed. null descriptor");
        } else {
            throw new IllegalStateException("open file descriptor failed. null uri");
        }
    }

    public static void removeInsertedRecord(Context context, Uri uri) {
        if (FEATURE_USE_FD_DOWNLOAD && uri != null) {
            a.k(context.getContentResolver().delete(uri, (String) null, (String[]) null), "removeInsertedRecord: result = ", "SamsungCloudSdk");
        }
    }

    public static synchronized SamsungAccountInfo requestAccessToken(Context context, boolean z) {
        synchronized (SamsungCloudSdk.class) {
            if (!z) {
                if (sSamsungAccountInfo != null) {
                    Log.i("SamsungCloudSdk", "request token skip");
                    SamsungAccountInfo samsungAccountInfo = sSamsungAccountInfo;
                    return samsungAccountInfo;
                }
            }
            String str = null;
            if (context == null) {
                Log.w("SamsungCloudSdk", "request token failed. null context");
                return null;
            }
            if (z) {
                if (sSamsungAccountInfo != null) {
                    str = sSamsungAccountInfo.accessToken;
                }
            }
            Bundle loadAccessToken = new SamsungAccountTokenLoader().loadAccessToken(context, str);
            if (loadAccessToken != null) {
                sSamsungAccountInfo = new SamsungAccountInfo(loadAccessToken);
            }
            SamsungAccountInfo samsungAccountInfo2 = sSamsungAccountInfo;
            return samsungAccountInfo2;
        }
    }

    public static boolean requestMediaScan(Context context, Uri uri, DownloadParams downloadParams) {
        Log.d("SamsungCloudSdk", "media scan start : " + downloadParams.getFileId() + " , " + Logger.getEncodedString(downloadParams.getTargetPath()));
        if (FEATURE_USE_SEC_MP) {
            boolean z = false;
            if (FEATURE_USE_FD_DOWNLOAD || insertRecordForMerge(context, downloadParams) != null) {
                int reserveDateTime = new FilesApi().reserveDateTime(downloadParams.getFileId(), downloadParams.getDateTaken());
                StringBuilder sb2 = new StringBuilder("reserve datetime {");
                sb2.append(downloadParams.getFileId());
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append(downloadParams.getDateTaken());
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (reserveDateTime > 0) {
                    z = true;
                }
                sb2.append(z);
                sb2.append("}");
                Log.d("SamsungCloudSdk", sb2.toString());
                updateIsPending(context, uri);
                MediaScanner.scanFiles(context, new String[]{downloadParams.getTargetPath()}, (String[]) null, new F9.a(1));
            } else {
                Log.e("SamsungCloudSdk", "getContentUriAfterDownload: insert mp failed, fileId = " + downloadParams.getFileId());
                return false;
            }
        } else {
            MediaScanner.scanFiles(context, new String[]{downloadParams.getFileId() + downloadParams.getTargetPath()}, (String[]) null, new F9.a(1));
        }
        return true;
    }

    public static Media restoreCloud(Context context, Media media) {
        return restoreCloud(context, media, false);
    }

    public static void scanCompleted(String str, Uri uri) {
        MediaScanResult mediaScanResult = RESULT;
        mediaScanResult.mUri = uri;
        synchronized (mediaScanResult) {
            mediaScanResult.notify();
        }
    }

    public static void setContentValues(ContentValues contentValues, Cursor cursor, DownloadParams downloadParams) {
        contentValues.put("is_favorite", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("is_favorite"))));
        contentValues.put("orientation", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("orientation"))));
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            contentValues.put(BundleKey.GROUP_ID, Long.valueOf(downloadParams.getFileId()));
            File file = new File(downloadParams.getTargetPath());
            contentValues.put("relative_path", FileUtils.getRelativePath(file.getAbsolutePath()));
            contentValues.put("_display_name", file.getName());
            return;
        }
        contentValues.put("picasa_id", Long.valueOf(downloadParams.getFileId()));
        contentValues.put("is_hide", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("is_hide"))));
        if (downloadParams.getMediaType() == 1) {
            contentValues.put("captured_url", cursor.getString(cursor.getColumnIndex("captured_url")));
            contentValues.put("captured_app", cursor.getString(cursor.getColumnIndex("captured_app")));
            return;
        }
        contentValues.put("resumePos", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("resumePos"))));
        contentValues.put("isPlayed", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("isPlayed"))));
    }

    public static void setupScsp(Context context, SamsungAccountInfo samsungAccountInfo) {
        ScspSetup.instance.update(context, samsungAccountInfo);
    }

    public static void updateIsPending(Context context, Uri uri) {
        if (FEATURE_USE_FD_DOWNLOAD) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_pending", 0);
            a.k(context.getContentResolver().update(uri, contentValues, (String) null, (String[]) null), "updateIsPending: result = ", "SamsungCloudSdk");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: android.net.Uri} */
    /* JADX WARNING: type inference failed for: r6v1 */
    /* JADX WARNING: type inference failed for: r6v3, types: [java.util.ArrayList<android.net.Uri>] */
    /* JADX WARNING: type inference failed for: r6v5 */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0107 A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0109 A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:70:0x0130=Splitter:B:70:0x0130, B:62:0x00f5=Splitter:B:62:0x00f5} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<android.net.Uri> download(android.content.Context r13, com.samsung.android.gallery.module.cloud.abstraction.DownloadParams r14, boolean r15) {
        /*
            java.lang.String r1 = "download completed"
            java.lang.String r2 = "download failed ["
            java.lang.String r3 = "download failed"
            java.lang.String r0 = "download failed. mkdir "
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r15)
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r5 = "SamsungCloudSdk"
            java.lang.String r6 = "download start"
            com.samsung.android.gallery.support.utils.Log.d(r5, r6, r4)
            java.lang.String r4 = r14.getTargetPath()
            r6 = 0
            if (r4 != 0) goto L_0x0038
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r15 = "invalid filePath : "
            r13.<init>(r15)
            java.lang.String r14 = r14.getOriginTargetPath()
            java.lang.String r14 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r14)
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            com.samsung.android.gallery.support.utils.Log.d(r5, r13)
            return r6
        L_0x0038:
            com.samsung.android.gallery.module.cloud.sdk.SamsungAccountInfo r15 = requestAccessToken(r13, r15)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            if (r15 != 0) goto L_0x0054
            java.lang.String r15 = "download failed. samsung account info is null."
            com.samsung.android.gallery.support.utils.Log.e(r5, r15)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            com.samsung.android.gallery.support.utils.Log.d(r5, r1)
            return r6
        L_0x0047:
            r0 = move-exception
            r13 = r0
            goto L_0x0159
        L_0x004b:
            r0 = move-exception
            r15 = r0
            r4 = r6
            goto L_0x00f5
        L_0x0050:
            r0 = move-exception
            r14 = r0
            goto L_0x0130
        L_0x0054:
            java.lang.String r4 = r14.getTargetPath()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            boolean r4 = com.samsung.android.gallery.support.utils.FileUtils.makeParentIfAbsent((java.lang.String) r4)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            if (r4 != 0) goto L_0x0079
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            r15.<init>(r0)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            java.lang.String r0 = r14.getTargetPath()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            java.lang.String r0 = com.samsung.android.gallery.support.utils.FileUtils.getParent(r0)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            r15.append(r0)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            java.lang.String r15 = r15.toString()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            com.samsung.android.gallery.support.utils.Log.e(r5, r15)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            com.samsung.android.gallery.support.utils.Log.d(r5, r1)
            return r6
        L_0x0079:
            setupScsp(r13, r15)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            com.samsung.scsp.media.SamsungCloudMedia r15 = new com.samsung.scsp.media.SamsungCloudMedia     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            r15.<init>()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller r0 = r14.getDownloadCanceller()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            if (r0 == 0) goto L_0x0092
            com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller r0 = r14.getDownloadCanceller()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            com.samsung.android.gallery.module.cloud.sdk.DownloadStatus r0 = (com.samsung.android.gallery.module.cloud.sdk.DownloadStatus) r0     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            r0.setSamsungCloudMedia(r15)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            r12 = r0
            goto L_0x0093
        L_0x0092:
            r12 = r6
        L_0x0093:
            com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor r0 = r14.getDownloadMonitor()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor r0 = r14.getDownloadMonitor()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            com.samsung.android.gallery.module.cloud.sdk.DownloadMonitor r0 = (com.samsung.android.gallery.module.cloud.sdk.DownloadMonitor) r0     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            r11 = r0
            goto L_0x00a2
        L_0x00a1:
            r11 = r6
        L_0x00a2:
            boolean r0 = FEATURE_USE_FD_DOWNLOAD     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            if (r0 == 0) goto L_0x00d7
            android.net.Uri r4 = insertRecordForMerge(r13, r14)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            android.os.ParcelFileDescriptor r9 = openFileDescriptor(r13, r4)     // Catch:{ ScspException -> 0x00c6, Exception -> 0x00c3 }
            com.samsung.scsp.media.Files r7 = r15.files     // Catch:{ all -> 0x00ca }
            java.lang.String r8 = r14.getCloudServerId()     // Catch:{ all -> 0x00ca }
            com.samsung.scsp.media.MediaConstants$FileType r10 = com.samsung.scsp.media.MediaConstants.FileType.ORIGINAL     // Catch:{ all -> 0x00ca }
            r7.downloadFile((java.lang.String) r8, (android.os.ParcelFileDescriptor) r9, (com.samsung.scsp.media.MediaConstants.FileType) r10, (com.samsung.scsp.framework.core.listeners.ProgressListener) r11, (com.samsung.scsp.framework.core.listeners.NetworkStatusListener) r12)     // Catch:{ all -> 0x00ca }
            boolean r15 = checkFileValid((android.os.ParcelFileDescriptor) r9)     // Catch:{ all -> 0x00ca }
            if (r9 == 0) goto L_0x00eb
            r9.close()     // Catch:{ ScspException -> 0x00c6, Exception -> 0x00c3 }
            goto L_0x00eb
        L_0x00c3:
            r0 = move-exception
            r15 = r0
            goto L_0x00f5
        L_0x00c6:
            r0 = move-exception
            r14 = r0
            r6 = r4
            goto L_0x0130
        L_0x00ca:
            r0 = move-exception
            r15 = r0
            if (r9 == 0) goto L_0x00d6
            r9.close()     // Catch:{ all -> 0x00d2 }
            goto L_0x00d6
        L_0x00d2:
            r0 = move-exception
            r15.addSuppressed(r0)     // Catch:{ ScspException -> 0x00c6, Exception -> 0x00c3 }
        L_0x00d6:
            throw r15     // Catch:{ ScspException -> 0x00c6, Exception -> 0x00c3 }
        L_0x00d7:
            com.samsung.scsp.media.Files r7 = r15.files     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            java.lang.String r8 = r14.getCloudServerId()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            java.lang.String r9 = r14.getTargetPath()     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            com.samsung.scsp.media.MediaConstants$FileType r10 = com.samsung.scsp.media.MediaConstants.FileType.ORIGINAL     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            r7.downloadFile((java.lang.String) r8, (java.lang.String) r9, (com.samsung.scsp.media.MediaConstants.FileType) r10, (com.samsung.scsp.framework.core.listeners.ProgressListener) r11, (com.samsung.scsp.framework.core.listeners.NetworkStatusListener) r12)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            boolean r15 = checkFileValid((com.samsung.android.gallery.module.cloud.abstraction.DownloadParams) r14)     // Catch:{ ScspException -> 0x0050, Exception -> 0x004b }
            r4 = r6
        L_0x00eb:
            if (r15 == 0) goto L_0x00f1
            java.util.ArrayList r6 = getContentUriAfterDownload(r13, r4, r14)     // Catch:{ ScspException -> 0x00c6, Exception -> 0x00c3 }
        L_0x00f1:
            com.samsung.android.gallery.support.utils.Log.d(r5, r1)
            return r6
        L_0x00f5:
            removeInsertedRecord(r13, r4)     // Catch:{ all -> 0x0047 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
            r13.<init>(r3)     // Catch:{ all -> 0x0047 }
            long r2 = r14.getFileId()     // Catch:{ all -> 0x0047 }
            java.lang.Long r14 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0047 }
            if (r4 == 0) goto L_0x0109
            r0 = 1
            goto L_0x010a
        L_0x0109:
            r0 = 0
        L_0x010a:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0047 }
            java.lang.Object[] r14 = new java.lang.Object[]{r14, r0}     // Catch:{ all -> 0x0047 }
            java.lang.String r14 = com.samsung.android.gallery.support.utils.Logger.v(r14)     // Catch:{ all -> 0x0047 }
            r13.append(r14)     // Catch:{ all -> 0x0047 }
            java.lang.String r14 = " e="
            r13.append(r14)     // Catch:{ all -> 0x0047 }
            java.lang.String r14 = r15.getMessage()     // Catch:{ all -> 0x0047 }
            r13.append(r14)     // Catch:{ all -> 0x0047 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0047 }
            com.samsung.android.gallery.support.utils.Log.e(r5, r13)     // Catch:{ all -> 0x0047 }
            com.samsung.android.gallery.support.utils.Log.d(r5, r1)
            return r6
        L_0x0130:
            int r15 = r14.rcode     // Catch:{ all -> 0x0047 }
            long r3 = (long) r15     // Catch:{ all -> 0x0047 }
            removeInsertedRecord(r13, r6)     // Catch:{ all -> 0x0047 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
            r13.<init>(r2)     // Catch:{ all -> 0x0047 }
            r13.append(r3)     // Catch:{ all -> 0x0047 }
            java.lang.String r15 = "] e="
            r13.append(r15)     // Catch:{ all -> 0x0047 }
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x0047 }
            r13.append(r14)     // Catch:{ all -> 0x0047 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0047 }
            com.samsung.android.gallery.support.utils.Log.e(r5, r13)     // Catch:{ all -> 0x0047 }
            java.util.ArrayList r13 = getErrorArrayList(r3)     // Catch:{ all -> 0x0047 }
            com.samsung.android.gallery.support.utils.Log.d(r5, r1)
            return r13
        L_0x0159:
            com.samsung.android.gallery.support.utils.Log.d(r5, r1)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.cloud.sdk.SamsungCloudSdk.download(android.content.Context, com.samsung.android.gallery.module.cloud.abstraction.DownloadParams, boolean):java.util.ArrayList");
    }

    private static String getDownloadUrl(Context context, String str, boolean z) {
        C0212a.x("get download url start refresh=", "SamsungCloudSdk", z);
        try {
            SamsungAccountInfo requestAccessToken = requestAccessToken(context, z);
            if (requestAccessToken == null) {
                Log.e("SamsungCloudSdk", "get download url failed. samsung account info is null.");
                Log.d("SamsungCloudSdk", "get download url end");
                return null;
            }
            setupScsp(context, requestAccessToken);
            String downloadUrl = new SamsungCloudMedia().files.getDownloadUrl(str, MediaConstants.FileType.ORIGINAL, new NetworkStatusListener() {
                public void onClosed(int i2) {
                }

                public void onStarted(int i2) {
                }
            });
            Log.d("SamsungCloudSdk", "get download url end");
            return downloadUrl;
        } catch (ScspException e) {
            Log.e("SamsungCloudSdk", "get download url failed. [" + e.rcode + "][" + e.getMessage() + "]");
            Log.d("SamsungCloudSdk", "get download url end");
            return null;
        } catch (Exception e7) {
            Log.e((CharSequence) "SamsungCloudSdk", "get download url failed", (Throwable) e7);
            Log.d("SamsungCloudSdk", "get download url end");
            return null;
        } catch (Throwable th) {
            Log.d("SamsungCloudSdk", "get download url end");
            throw th;
        }
    }

    private static String getStreamUrl(Context context, String str, boolean z) {
        try {
            SamsungAccountInfo requestAccessToken = requestAccessToken(context, z);
            if (requestAccessToken == null) {
                Log.e("SamsungCloudSdk", "get stream url failed. null samsung account");
                return null;
            }
            setupScsp(context, requestAccessToken);
            String stream = new SamsungCloudMedia().files.getStream(str, (NetworkStatusListener) null);
            Log.d("SamsungCloudSdk", "get stream url {" + z + "}");
            return stream;
        } catch (ScspException e) {
            if (CloudErrorType.isGdprError(e.rcode)) {
                Log.w("SamsungCloudSdk", "getStreamUrl failed {gdpr} e=" + e.getMessage());
                return String.valueOf(e.rcode);
            }
            Log.w("SamsungCloudSdk", "getStreamUrl failed {" + e.rcode + "} e=" + e.getMessage());
            return null;
        } catch (Exception e7) {
            Log.e((CharSequence) "SamsungCloudSdk", "getStreamUrl failed", (Throwable) e7);
            return null;
        }
    }

    private static Media restoreCloud(Context context, Media media, boolean z) {
        Log.d("SamsungCloudSdk", "restore cloud start", Boolean.valueOf(z));
        try {
            SamsungAccountInfo requestAccessToken = requestAccessToken(context, z);
            if (requestAccessToken == null) {
                Log.e("SamsungCloudSdk", "restore cloud failed. samsung account info is null.");
                Log.d("SamsungCloudSdk", "restore cloud completed");
                return null;
            }
            setupScsp(context, requestAccessToken);
            MediaList restorePhotos = new SamsungCloudMedia().trash.restorePhotos(getMediaList(media), (NetworkStatusListener) null);
            if (restorePhotos != null) {
                if (restorePhotos.getCount() > 0) {
                    Media media2 = restorePhotos.getList().get(0);
                    Log.d("SamsungCloudSdk", "restore cloud completed");
                    return media2;
                }
            }
            Log.w("SamsungCloudSdk", "restore cloud failed. null media or wrong count");
            Log.d("SamsungCloudSdk", "restore cloud completed");
            return null;
        } catch (ScspException e) {
            if (CloudErrorType.isError(e.rcode)) {
                Media errorMedia = getErrorMedia(e.rcode);
                Log.d("SamsungCloudSdk", "restore cloud completed");
                return errorMedia;
            }
            Log.e("SamsungCloudSdk", "restore cloud failed [" + e.rcode + "] e=" + e.getMessage());
            Log.d("SamsungCloudSdk", "restore cloud completed");
            return null;
        } catch (Exception e7) {
            Log.e((CharSequence) "SamsungCloudSdk", "restore cloud failed", (Throwable) e7);
            Log.d("SamsungCloudSdk", "restore cloud completed");
            return null;
        } catch (Throwable th) {
            Log.d("SamsungCloudSdk", "restore cloud completed");
            throw th;
        }
    }

    public static boolean checkFileValid(DownloadParams downloadParams) {
        if (FileUtils.exists(downloadParams.getTargetPath())) {
            return true;
        }
        Log.e("SamsungCloudSdk", "download failed. file not exist.");
        return false;
    }
}
