package com.samsung.android.gallery.support.utils;

import android.text.TextUtils;
import com.samsung.android.gallery.support.library.SeApiCompat;
import i.C0212a;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StorageInfo {
    private static final ConcurrentHashMap<String, StorageInfo> sStorageInfoMap = new ConcurrentHashMap<>();
    public String agif;
    public final String album;
    public String arDoodle;
    public String arEmoji;
    public String arEmojiCamera;
    public String avatarCamera;
    public String bixbyVision;
    public final String camera;
    public final String cameraCover;
    public String clippedImages;
    public String collage;
    public final String creativeStudio;
    public final String dcim;
    public String decoPic;
    public final String documentScans;
    public final String download;
    public final boolean dual;
    public String galaxyAvatar;
    public String gear;
    public String gear360;
    public String gif;
    public final String glasses;
    public String highlight;
    private String id;
    public String liveMessage;
    private volatile BucketHolder mBucketHolder;
    public final String media;
    public final String movies;
    public String myEmoji;
    public final String pictures;
    public final boolean primary;
    public String quickShare;
    public final String root;
    public final String rootRw;
    public final String scan;
    public final String screenRecordings;
    public final String screenShot;
    public final String screenShotAlt;
    public String stories;
    public String studio;
    public String superSlow;
    public final String trash;
    public final String videoCaptures;
    public String videoCollage;
    public String videoEditor;
    public final String videoScreenShots;
    public final String volume;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BucketHolder {
        public final int camera;
        public final int cameraCover;
        public final int dcim;
        public final int download;
        public final int pictures;
        public final int quickShare;
        public final int root;
        public final int scan;
        public final int screenRecordings;
        public final int screenShot;
        public final int screenShotAlt;
        public final int videoCaptures;
        public final int videoScreenShots;

        public BucketHolder(StorageInfo storageInfo) {
            this.root = FileUtils.getBucketId(storageInfo.root);
            this.dcim = FileUtils.getBucketId(storageInfo.dcim);
            this.pictures = FileUtils.getBucketId(storageInfo.pictures);
            this.download = FileUtils.getBucketId(storageInfo.download);
            this.camera = FileUtils.getBucketId(storageInfo.camera);
            this.cameraCover = FileUtils.getBucketId(storageInfo.cameraCover);
            this.quickShare = FileUtils.getBucketId(storageInfo.quickShare);
            this.screenShot = FileUtils.getBucketId(storageInfo.screenShot);
            this.screenShotAlt = FileUtils.getBucketId(storageInfo.screenShotAlt);
            this.screenRecordings = FileUtils.getBucketId(storageInfo.screenRecordings);
            this.videoScreenShots = FileUtils.getBucketId(storageInfo.videoScreenShots);
            this.videoCaptures = FileUtils.getBucketId(storageInfo.videoCaptures);
            this.scan = FileUtils.getBucketId(storageInfo.scan);
        }
    }

    public StorageInfo(String str) {
        boolean z;
        String str2;
        boolean z3 = true;
        String str3 = "/storage/emulated/";
        if (str.isEmpty() || str.startsWith(str3)) {
            z = true;
        } else {
            z = false;
        }
        this.primary = z;
        String replaceFirst = str.replaceFirst(!z ? "/storage/" : str3, "");
        this.volume = replaceFirst;
        this.dual = (!z || !((Boolean) Optional.of(Integer.valueOf(UnsafeCast.toInt(replaceFirst, 0))).map(new C0670h(28)).orElse(Boolean.FALSE)).booleanValue()) ? false : z3;
        this.root = str;
        if (z) {
            str2 = str;
        } else {
            str2 = SecureDigitalPolicy.getWritablePath(str);
        }
        this.rootRw = str2;
        StringBuilder s = C0212a.s(str);
        String str4 = File.separator;
        String p6 = C0212a.p(s, str4, "DCIM");
        this.dcim = p6;
        String B = C0212a.B(str, str4, "Pictures");
        this.pictures = B;
        String B9 = C0212a.B(str, str4, "Movies");
        this.movies = B9;
        String B10 = C0212a.B(str, str4, "Download");
        this.download = B10;
        this.media = str + str4 + "Android" + str4 + "media";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(str4);
        sb2.append("Android");
        sb2.append(str4);
        sb2.append(".Trash");
        this.trash = C0212a.p(sb2, str4, "com.sec.android.gallery3d");
        String A10 = C0212a.A(p6, str4);
        this.camera = C0212a.A(A10, "Camera");
        this.cameraCover = C0212a.A(A10, "CoverCamera");
        this.album = p6;
        this.screenShot = C0212a.A(A10, "Screenshots");
        this.screenShotAlt = C0212a.B(B, str4, "Screenshots");
        this.screenRecordings = C0212a.A(A10, "Screen recordings");
        this.videoScreenShots = C0212a.A(A10, "Video screenshots");
        this.videoCaptures = C0212a.A(A10, "Videocaptures");
        this.quickShare = C0212a.B(B10, str4, "Quick Share");
        this.studio = C0212a.B(B9, str4, "Studio");
        this.scan = C0212a.A(A10, "SCAN");
        this.documentScans = C0212a.A(A10, "Document scans");
        this.creativeStudio = C0212a.A(A10, "Creative studio");
        this.glasses = C0212a.A(A10, "Glasses");
        if (z) {
            this.myEmoji = C0212a.A(A10, "My Emoji");
            this.arEmoji = C0212a.A(A10, "AR Emoji");
            this.arEmojiCamera = C0212a.A(A10, "AR Emoji camera");
            this.galaxyAvatar = C0212a.A(A10, "Galaxy Avatar");
            this.avatarCamera = C0212a.A(A10, "Avatar Camera");
            this.arDoodle = C0212a.A(A10, "AR Doodle");
            this.decoPic = C0212a.A(A10, "Deco Pic");
            this.videoEditor = C0212a.A(A10, "Video Editor");
            this.agif = C0212a.A(A10, "Animated GIF");
            this.gif = C0212a.A(A10, "GIF");
            this.superSlow = C0212a.A(A10, "SuperSlow");
            this.highlight = C0212a.A(A10, "Highlight Video");
            String A11 = C0212a.A(A10, "Collage");
            this.collage = A11;
            this.videoCollage = A11;
            this.bixbyVision = C0212a.A(A10, "Bixby Vision");
            this.gear360 = C0212a.A(A10, "Gear 360");
            this.liveMessage = C0212a.A(A10, "Live message");
            this.stories = C0212a.A(A10, "Stories");
            this.clippedImages = C0212a.A(A10, "Clipped images");
            this.gear = C0212a.B(B, str4, "Gear");
        }
    }

    public static StorageInfo getDefault() {
        return getInstance(FileUtils.EXTERNAL_STORAGE_DIR);
    }

    public static StorageInfo getInstance(String str) {
        return sStorageInfoMap.computeIfAbsent(str, new C0670h(27));
    }

    public static StorageInfo getRemovable() {
        return getInstance(FileUtils.SDCARD_DIR);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$new$0(Integer num) {
        boolean z;
        if (num.intValue() < 95 || num.intValue() >= 100) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public BucketHolder buckets() {
        if (this.mBucketHolder == null) {
            this.mBucketHolder = new BucketHolder(this);
        }
        return this.mBucketHolder;
    }

    public String getCardId() {
        if (this.primary) {
            return "external-primary";
        }
        if (this.id == null) {
            this.id = SeApiCompat.getSdcardId(AppResources.getAppContext());
        }
        return this.id;
    }

    public String getDocumentsDir(String str) {
        String str2;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.root);
        String str3 = File.separator;
        sb2.append(str3);
        sb2.append("Documents");
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = C0212a.A(str3, str);
        }
        sb2.append(str2);
        return sb2.toString();
    }

    public List<String> getMajorList() {
        return List.of(this.root, this.dcim, this.pictures, this.download, this.camera, this.screenShot);
    }

    public String getMediaPath(String str) {
        String str2;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.media);
        String str3 = File.separator;
        sb2.append(str3);
        sb2.append("com.sec.android.gallery3d");
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = C0212a.A(str3, str);
        }
        sb2.append(str2);
        String sb3 = sb2.toString();
        FileUtils.makeDirectoryIfAbsent(sb3);
        return sb3;
    }

    public boolean isNonMoviePath(String str) {
        if (this.primary) {
            if (str.startsWith(this.camera) || str.startsWith(this.videoEditor) || str.startsWith(this.highlight) || str.startsWith(this.arEmoji) || str.startsWith(this.galaxyAvatar) || str.startsWith(this.avatarCamera) || str.startsWith(this.decoPic) || str.startsWith(this.arDoodle)) {
                return true;
            }
            return false;
        } else if (str.startsWith(this.camera) || str.startsWith(this.studio)) {
            return true;
        } else {
            return false;
        }
    }
}
