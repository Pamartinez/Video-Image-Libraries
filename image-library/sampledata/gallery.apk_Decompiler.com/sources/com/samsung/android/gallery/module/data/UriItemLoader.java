package com.samsung.android.gallery.module.data;

import A.a;
import Ad.C0720a;
import N2.j;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.microsoft.YourPhone;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.providers.UriInterface;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SefData;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.scsp.common.ContentType;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class UriItemLoader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CropMediaItem extends MediaItem {
        public String getDiskCacheKey() {
            return getPath() + "/" + getDateModified();
        }
    }

    public static MediaItem createCropMediaItem(Context context, Uri uri, String str, boolean z) {
        return updateMediaItem(new CropMediaItem(), context, uri, str, z);
    }

    public static MediaItem createHttpMediaItem(Uri uri, String str) {
        MediaType mediaType;
        MediaItem mediaItem = new MediaItem();
        mediaItem.setPath(uri.toString());
        mediaItem.mDisplayName = getDisplayName(uri.getPath());
        mediaItem.mHttpUri = uri.toString();
        mediaItem.mMimeType = str;
        mediaItem.mStorageType = StorageType.UriItem;
        if (str.contains("video")) {
            mediaType = MediaType.Video;
        } else {
            mediaType = MediaType.Image;
        }
        mediaItem.mMediaType = mediaType;
        Log.v("UriItemLoader", "createHttpMediaItem " + MediaItemUtil.getSimpleLog(mediaItem) + ArcCommonLog.TAG_COMMA + Logger.getEncodedString(uri.toString()));
        return mediaItem;
    }

    public static MediaItem createMediaItem(Context context, Uri uri, String str, boolean z) {
        return updateMediaItem(new MediaItem(), context, uri, str, z);
    }

    public static MediaItem createUriItem(Context context, File file) {
        return createMediaItem(context, FileProviderUtil.getUri(context, file), (String) null, false);
    }

    public static String getDisplayName(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            int lastIndexOf = str.lastIndexOf("/");
            if (lastIndexOf >= 0) {
                return str.substring(lastIndexOf + 1);
            }
            return str;
        } catch (Exception unused) {
            return str;
        }
    }

    private static long getFileSize(Context context, Uri uri) {
        AssetFileDescriptor openAssetFileDescriptor;
        long j2;
        String path;
        if ("file".equals(uri.getScheme()) && (path = uri.getPath()) != null) {
            return FileUtils.length(path);
        }
        try {
            openAssetFileDescriptor = context.getApplicationContext().getContentResolver().openAssetFileDescriptor(uri, "r");
            if (openAssetFileDescriptor == null) {
                j2 = 0;
            } else {
                j2 = openAssetFileDescriptor.getLength();
            }
            if (openAssetFileDescriptor == null) {
                return j2;
            }
            openAssetFileDescriptor.close();
            return j2;
        } catch (IOException | IllegalArgumentException | IllegalStateException | NullPointerException e) {
            j.v("getFileSize failed. - ", e, "UriItemLoader");
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static ArrayList<MediaItem> getItemsFromMediaUris(Uri[] uriArr) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        loadFromContentUris((ArrayList) Arrays.stream(uriArr).collect(Collectors.toCollection(new C0720a(1))), arrayList);
        return arrayList;
    }

    public static MediaItem getLongExposureItem(Context context, String str, MediaItem mediaItem) {
        MediaItem mediaItem2;
        String mimeType = FileType.getMimeType(str);
        if (mimeType.startsWith("video/")) {
            mediaItem2 = createMediaItem(context, FileProviderUtil.getUri(context, str), FileType.getMimeType(str), false);
        } else {
            mediaItem2 = MediaItemBuilder.createTempItem(str, mimeType);
            mediaItem2.setDateModified(mediaItem.getDateModified());
        }
        if (new SefData().read(str).contain(SefInfo.MOTION_PHOTO_DATA.keyName)) {
            mediaItem2.setSefFileType(2608, 0);
        }
        VideoPropData of2 = VideoPropData.of(mediaItem2);
        of2.longExposure = true;
        of2.longExposurePath = str;
        mediaItem2.setExtra(ExtrasID.ORIGIN_PATH, mediaItem.getPath());
        mediaItem2.setExtra(ExtrasID.ORIGINAL_FILE_ID, Long.valueOf(mediaItem.getFileId()));
        return mediaItem2;
    }

    public static ArrayList<MediaItem> getMediaItemFromUris(Context context, Uri[] uriArr) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        for (Uri uri : uriArr) {
            String uri2 = uri.toString();
            if (uri2.contains(".share")) {
                arrayList.add(getSharingUriItem(context, uri));
            } else if (MediaUri.getInstance().matches(uri2)) {
                loadFromContentUris(new ArrayList(List.of(uri)), arrayList);
            } else if ((PreferenceFeatures.OneUi7x.SUPPORT_STORY_LIVE_EFFECT_TYPE && uri2.contains("liveeffect")) || (PreferenceFeatures.OneUi8x.SUPPORT_RECAP && uri2.contains("recap"))) {
                arrayList.add(getStoryUriItem(context, uri));
            }
        }
        return arrayList;
    }

    public static MediaType getMediaType(String str, boolean z) {
        if (str != null) {
            if (str.startsWith("video/")) {
                return MediaType.Video;
            }
            if (str.startsWith("image/")) {
                return MediaType.Image;
            }
        }
        if (z) {
            return MediaType.Image;
        }
        try {
            new InternalException("UnsupportedMimeType : " + str).post();
        } catch (InternalException unused) {
        }
        return MediaType.Unsupported;
    }

    private static MediaItem getMsYourPhoneMediaItem(Context context, Uri uri) {
        Cursor query;
        Throwable th;
        MediaItem mediaItem = new MediaItem();
        try {
            Uri uri2 = uri;
            query = context.getContentResolver().query(uri2, new String[]{"_display_name", "internalFileSize", "type"}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    mediaItem.mDisplayName = query.getString(query.getColumnIndex("_display_name"));
                    mediaItem.mFileSize = query.getLong(query.getColumnIndex("internalFileSize"));
                    mediaItem.mMimeType = query.getString(query.getColumnIndex("type"));
                }
            }
            if (query != null) {
                query.close();
            }
            MediaType mediaType = getMediaType(mediaItem.mMimeType, false);
            if (mediaType == MediaType.Unsupported) {
                return null;
            }
            mediaItem.mMediaType = mediaType;
            mediaItem.mStorageType = StorageType.UriItem;
            mediaItem.setPath(uri2.toString());
            return mediaItem;
        } catch (NullPointerException e) {
            NullPointerException nullPointerException = e;
            Log.e("UriItemLoader", "getMsYourPhoneMediaItem failed e=" + nullPointerException.getMessage());
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private static MediaItem getMultiControlMediaItem(Context context, Uri uri, String str) {
        Throwable th;
        MediaItem mediaItem = new MediaItem();
        Uri uri2 = uri;
        Cursor query = context.getContentResolver().query(uri2, new String[]{"_display_name", IParameterKey.SIZE}, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    Log.d("UriItemLoader", "count = " + query.getColumnCount());
                    mediaItem.setDisplayName(query.getString(query.getColumnIndex("_display_name")));
                    mediaItem.mFileSize = query.getLong(query.getColumnIndex(IParameterKey.SIZE));
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (query != null) {
            query.close();
        }
        mediaItem.mMimeType = str;
        MediaType mediaType = getMediaType(str, false);
        mediaItem.mMediaType = mediaType;
        if (mediaType == MediaType.Unsupported) {
            Log.e("UriItemLoader", "not support mimeType");
            return null;
        }
        mediaItem.mStorageType = StorageType.UriItem;
        mediaItem.setPath(uri2.toString());
        return mediaItem;
        throw th;
    }

    private static int getSharingFileDuration(Context context, Uri uri) {
        String uri2 = uri.toString();
        if (TextUtils.isEmpty(uri2) || !uri2.contains(".share")) {
            Log.d("UriItemLoader", "Uri is empty or doesn't contains .share");
            return 0;
        }
        File externalFilesDir = context.getExternalFilesDir(".share");
        if (externalFilesDir == null) {
            Log.e("UriItemLoader", "target directory is not created. prepare failed.");
            return 0;
        }
        return MediaHelper.getVideoDuration(externalFilesDir + File.separator + getDisplayName(uri.getPath()));
    }

    public static ArrayList<MediaItem> getSharingItemsFromFileUris(Context context, Uri[] uriArr) {
        return (ArrayList) Arrays.stream(uriArr).map(new s(context)).collect(Collectors.toCollection(new C0720a(1)));
    }

    /* access modifiers changed from: private */
    public static MediaItem getSharingUriItem(Context context, Uri uri) {
        BufferedInputStream bufferedInputStream;
        MediaItem createMediaItem = createMediaItem(context, uri, (String) null, false);
        if (createMediaItem.isVideo()) {
            createMediaItem.setFileDuration(getSharingFileDuration(context, uri));
            return createMediaItem;
        }
        try {
            bufferedInputStream = new BufferedInputStream(context.getContentResolver().openInputStream(uri));
            if (createMediaItem.getOrientation() == 0) {
                ThumbnailUtil.updateImageInfo((ThumbnailInterface) createMediaItem, (InputStream) bufferedInputStream);
            }
            bufferedInputStream.close();
            return createMediaItem;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getSharingUriItem failed e="), "UriItemLoader");
            return createMediaItem;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static MediaItem getStoryUriItem(Context context, Uri uri) {
        BufferedInputStream bufferedInputStream;
        MediaItem createMediaItem = createMediaItem(context, uri, (String) null, false);
        if (createMediaItem.isVideo()) {
            MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(context, uri);
            createMediaItem.setSize(videoInfo.width, videoInfo.height);
            createMediaItem.setFileDuration(videoInfo.duration);
            return createMediaItem;
        }
        try {
            bufferedInputStream = new BufferedInputStream(context.getContentResolver().openInputStream(uri));
            if (createMediaItem.getOrientation() == 0) {
                ThumbnailUtil.updateImageInfo((ThumbnailInterface) createMediaItem, (InputStream) bufferedInputStream);
            }
            bufferedInputStream.close();
            return createMediaItem;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getStoryUriItem failed e="), "UriItemLoader");
            return createMediaItem;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static boolean isFileUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if ("file".equals(scheme) || "content".equals(scheme)) {
            return true;
        }
        return false;
    }

    public static boolean isHttpUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if ("http".equals(scheme) || "https".equals(scheme)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$loadFromContentUris$17(String[] strArr) {
        if (strArr.length != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d A[SYNTHETIC, Splitter:B:14:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$loadFromContentUris$19(java.util.ArrayList r5, java.lang.String[] r6) {
        /*
            java.lang.String r0 = "UriItemLoader"
            java.lang.String r1 = "loadFromContentUris failed "
            java.lang.String r2 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES     // Catch:{ SQLiteException -> 0x0041 }
            com.samsung.android.gallery.module.data.f r3 = new com.samsung.android.gallery.module.data.f     // Catch:{ SQLiteException -> 0x0041 }
            r4 = 3
            r3.<init>(r4, r6)     // Catch:{ SQLiteException -> 0x0041 }
            android.database.Cursor r2 = com.samsung.android.gallery.database.dal.DbCompat.query(r2, r3)     // Catch:{ SQLiteException -> 0x0041 }
            if (r2 == 0) goto L_0x0028
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x0028
        L_0x0018:
            com.samsung.android.gallery.module.data.MediaItem r6 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r2)     // Catch:{ all -> 0x0026 }
            r5.add(r6)     // Catch:{ all -> 0x0026 }
            boolean r6 = r2.moveToNext()     // Catch:{ all -> 0x0026 }
            if (r6 != 0) goto L_0x0018
            goto L_0x003b
        L_0x0026:
            r5 = move-exception
            goto L_0x0044
        L_0x0028:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0026 }
            r5.<init>(r1)     // Catch:{ all -> 0x0026 }
            java.lang.String r6 = java.util.Arrays.toString(r6)     // Catch:{ all -> 0x0026 }
            r5.append(r6)     // Catch:{ all -> 0x0026 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0026 }
            com.samsung.android.gallery.support.utils.Log.e(r0, r5)     // Catch:{ all -> 0x0026 }
        L_0x003b:
            if (r2 == 0) goto L_0x0043
            r2.close()     // Catch:{ SQLiteException -> 0x0041 }
            return
        L_0x0041:
            r5 = move-exception
            goto L_0x004f
        L_0x0043:
            return
        L_0x0044:
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ all -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch:{ SQLiteException -> 0x0041 }
        L_0x004e:
            throw r5     // Catch:{ SQLiteException -> 0x0041 }
        L_0x004f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r1 = "loadFromContentUris failed. e="
            r6.<init>(r1)
            java.lang.String r5 = r5.getMessage()
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.samsung.android.gallery.support.utils.Log.e(r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.data.UriItemLoader.lambda$loadFromContentUris$19(java.util.ArrayList, java.lang.String[]):void");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadLocalUri$13(MediaItem[] mediaItemArr, MediaItem mediaItem, Integer num) {
        mediaItemArr[num.intValue()] = mediaItem;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$loadUri$1(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$loadUri$2(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$loadUri$3(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadUri$4(MediaItem[] mediaItemArr, MediaItem mediaItem, Integer num) {
        mediaItemArr[num.intValue()] = mediaItem;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadUri$8(MediaItem[] mediaItemArr, MediaItem mediaItem, Integer num) {
        mediaItemArr[num.intValue()] = mediaItem;
    }

    public static void loadFromContentUris(ArrayList<Uri> arrayList, ArrayList<MediaItem> arrayList2) {
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Iterator<Uri> it = arrayList.iterator();
        while (it.hasNext()) {
            String uri = it.next().toString();
            if (MediaUri.getInstance().isSecMediaUri(uri)) {
                arrayList3.add(uri);
            } else {
                arrayList4.add(uri);
            }
        }
        Arrays.stream(new String[][]{(String[]) arrayList3.toArray(new String[0]), (String[]) arrayList4.toArray(new String[0])}).filter(new e(3)).forEachOrdered(new p(arrayList2, 0));
    }

    public static void loadLocalUri(ArrayList<Uri> arrayList, HashMap<String, List<Integer>> hashMap, MediaItem[] mediaItemArr) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Uri> it = arrayList.iterator();
        while (it.hasNext()) {
            Uri next = it.next();
            MediaItem from = MediaItem.from(next);
            from.setDateModified(currentTimeMillis);
            Optional.ofNullable(hashMap.get(next.toString())).ifPresent(new q(mediaItemArr, from, 5));
            arrayList2.add(from);
        }
        SimpleThreadPool.getInstance().execute(new v(arrayList2));
    }

    public static boolean loadMediaItemFromUris(ArrayList<Uri> arrayList, ArrayList<MediaItem> arrayList2) {
        return loadUri(arrayList, arrayList2);
    }

    public static boolean loadMediaItemFromUrisOnSkipException(Context context, ArrayList<Uri> arrayList, ArrayList<MediaItem> arrayList2) {
        return loadMediaItemFromUrisOnSkipException(context, arrayList, arrayList2, (HashMap<Uri, String>) null);
    }

    public static boolean loadMediaItemFromUrisWithGroup(ArrayList<Uri> arrayList, ArrayList<MediaItem> arrayList2) {
        Cursor query;
        if (arrayList.isEmpty()) {
            Log.e("UriItemLoader", "loadMediaItemFromUrisWithGroup failed. uriList is null");
            return false;
        }
        if (arrayList.get(0) != null && MediaUri.getInstance().matches(arrayList.get(0).toString())) {
            try {
                query = DbCompat.query(DbKey.ALL_PICTURES, new p(arrayList, 2));
                if (query != null) {
                    if (query.moveToFirst()) {
                        do {
                            arrayList2.add(MediaItemLoader.load(query));
                        } while (query.moveToNext());
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e) {
                Log.e("UriItemLoader", "loadMediaItemFromUrisWithGroup failed" + e.getMessage());
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (arrayList2.size() != 0) {
            return true;
        }
        Log.e("UriItemLoader", "loadMediaItemFromUrisWithGroup failed. No data available");
        return false;
        throw th;
    }

    public static void loadMediaUri(ArrayList<Uri> arrayList, Consumer<MediaItem> consumer) {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new p(arrayList, 1));
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        consumer.accept(MediaItemLoader.load(query));
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (SQLiteException e) {
            Log.e("UriItemLoader", "loadUri failed" + e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static boolean loadUri(ArrayList<Uri> arrayList, ArrayList<MediaItem> arrayList2) {
        ArrayList<Uri> arrayList3 = arrayList;
        ArrayList<MediaItem> arrayList4 = arrayList2;
        if (arrayList3 == null || arrayList3.isEmpty()) {
            Log.e("UriItemLoader", "loadUri skip. empty input");
            return false;
        }
        TimeTickLog timeTickLog = new TimeTickLog();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        HashMap hashMap = new HashMap();
        UriInterface instance = MediaUri.getInstance();
        for (int i2 = 0; i2 < arrayList3.size(); i2++) {
            Uri uri = arrayList3.get(i2);
            String uri2 = uri.toString();
            if (instance.isSecMediaUri(uri2)) {
                ((List) hashMap.computeIfAbsent("s" + uri.getLastPathSegment(), new j(12))).add(Integer.valueOf(i2));
                arrayList5.add(uri);
            } else if (instance.isGedMediaUri(uri2)) {
                ((List) hashMap.computeIfAbsent("g" + uri.getLastPathSegment(), new j(13))).add(Integer.valueOf(i2));
                arrayList6.add(uri);
            } else {
                List list = (List) hashMap.computeIfAbsent(uri2, new j(14));
                if (list.isEmpty()) {
                    arrayList7.add(uri);
                }
                list.add(Integer.valueOf(i2));
            }
        }
        timeTickLog.tick();
        int size = arrayList3.size();
        MediaItem[] mediaItemArr = new MediaItem[size];
        LatchBuilder latchBuilder = new LatchBuilder("loadUri");
        if (arrayList5.size() > 0) {
            latchBuilder.addWorker(new t(arrayList5, hashMap, mediaItemArr, 0));
        }
        if (arrayList6.size() > 0) {
            latchBuilder.addWorker(new t(arrayList6, hashMap, mediaItemArr, 1));
        }
        if (arrayList7.size() > 0) {
            latchBuilder.setCurrent(new t(arrayList7, hashMap, mediaItemArr, 2));
        }
        latchBuilder.start();
        timeTickLog.tick();
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            MediaItem mediaItem = mediaItemArr[i8];
            if (mediaItem == null) {
                MediaItem mediaItem2 = new MediaItem();
                mediaItem2.mPath = arrayList3.get(i8).toString();
                mediaItem2.mStorageType = StorageType.UriItem;
                mediaItem2.mMediaType = MediaType.Image;
                arrayList4.add(mediaItem2);
                i7++;
                Log.e((CharSequence) "UriItemLoader", "loadUri wrong index", Integer.valueOf(i8), mediaItem2.mPath);
            } else {
                arrayList4.add(mediaItem);
            }
        }
        timeTickLog.tick();
        Log.d("UriItemLoader", "loadUri" + Logger.v(j.g(new StringBuilder("i:"), arrayList3), j.g(new StringBuilder("o:"), arrayList4), j.g(new StringBuilder("s:"), arrayList5), j.g(new StringBuilder("g:"), arrayList6), j.g(new StringBuilder("u:"), arrayList7), C0086a.i(i7, "w:")) + " +" + timeTickLog.summary());
        return !arrayList4.isEmpty();
    }

    private static void updateFromGedMp(Context context, Uri uri, MediaItem mediaItem) {
        Throwable th;
        Cursor query = context.getContentResolver().query(uri, new String[]{"title", "_display_name", "_data"}, "1", (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    mediaItem.setTitle(query.getString(0));
                    mediaItem.setDisplayName(query.getString(1));
                    mediaItem.setExtra(ExtrasID.ORIGIN_PATH, query.getString(2));
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (query != null) {
            query.close();
            return;
        }
        return;
        throw th;
    }

    public static MediaItem updateMediaItem(MediaItem mediaItem, Context context, Uri uri, String str, boolean z) {
        mediaItem.mMimeType = MimeType.from(uri);
        if (!TextUtils.isEmpty(str) && (TextUtils.isEmpty(mediaItem.mMimeType) || ContentType.OCTET_STREAM.equals(mediaItem.mMimeType))) {
            mediaItem.mMimeType = str;
        }
        if (!FileType.containsMimeType(mediaItem.mMimeType)) {
            try {
                new InternalException("UnknownMimeType : " + mediaItem.mMimeType).post();
            } catch (InternalException unused) {
            }
        }
        mediaItem.mMediaType = getMediaType(mediaItem.mMimeType, false);
        mediaItem.mStorageType = StorageType.UriItem;
        mediaItem.mFileSize = getFileSize(context, uri);
        mediaItem.setPath(uri.toString());
        if (mediaItem.mFileSize == 0) {
            Log.w("UriItemLoader", "size zero. " + Logger.getEncodedString((Object) uri));
        }
        if (MediaUri.getInstance().isGedMediaUri(uri.toString())) {
            updateFromGedMp(context, uri, mediaItem);
        }
        if (z && mediaItem.isImage()) {
            ThumbnailUtil.updateImageInfo((ThumbnailInterface) mediaItem, uri);
        }
        return mediaItem;
    }

    public static boolean loadMediaItemFromUrisOnSkipException(Context context, ArrayList<Uri> arrayList, ArrayList<MediaItem> arrayList2, HashMap<Uri, String> hashMap) {
        MediaItem mediaItem;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Iterator<Uri> it = arrayList.iterator();
        while (it.hasNext()) {
            Uri next = it.next();
            if (MediaUri.getInstance().matches(next.toString())) {
                arrayList3.add(next);
            } else if (isFileUri(next)) {
                arrayList4.add(next);
            } else {
                Log.e("UriItemLoader", "loadMediaItemFromUrisOnSkipException:: not supported uri=" + Logger.getEncodedString(next.toString()));
            }
        }
        if (!arrayList3.isEmpty()) {
            loadFromContentUris(arrayList3, arrayList2);
        }
        if (!arrayList4.isEmpty()) {
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                Uri uri = (Uri) it2.next();
                String str = hashMap != null ? hashMap.get(uri) : null;
                try {
                    if (YourPhone.isYourPhonePath(uri.toString())) {
                        mediaItem = getMsYourPhoneMediaItem(context, uri);
                    } else if (uri.toString().contains("com.samsung.android.inputshare.provider")) {
                        mediaItem = getMultiControlMediaItem(context, uri, str);
                    } else {
                        mediaItem = createMediaItem(context, uri, (String) null, false);
                    }
                    if (mediaItem != null) {
                        arrayList2.add(mediaItem);
                    }
                } catch (SecurityException e) {
                    Log.e("UriItemLoader", "SecurityException on getMediaItem() " + e);
                }
            }
        }
        if (arrayList2.size() != 0) {
            return true;
        }
        Log.e("UriItemLoader", "loadMediaItemFromUrisOnSkipException failed. No data available");
        return false;
    }
}
