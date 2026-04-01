package com.samsung.android.gallery.module.album;

import V8.a;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumTitleHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AlbumNameHolder {
        static Map<Integer, String> sAlbumName;

        /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, com.samsung.android.gallery.support.utils.SystemEnvironment$EnvironmentChangeListener] */
        static {
            initialize(AppResources.getAppContext());
            SystemEnvironment.addObserver("AlbumNameHolder", new Object(), 3);
        }

        public static String getAlbumTitle(int i2, String str) {
            String str2 = sAlbumName.get(Integer.valueOf(i2));
            if (str2 != null) {
                return str2;
            }
            return str;
        }

        public static void initialize(Context context) {
            HashMap hashMap = new HashMap();
            try {
                StorageInfo storageInfo = StorageInfo.getDefault();
                StorageInfo.BucketHolder buckets = storageInfo.buckets();
                Integer valueOf = Integer.valueOf(buckets.camera);
                int i2 = R$string.dcim_name;
                hashMap.put(valueOf, context.getString(i2));
                Integer valueOf2 = Integer.valueOf(buckets.screenShot);
                int i7 = R$string.screenshot;
                hashMap.put(valueOf2, context.getString(i7));
                Integer valueOf3 = Integer.valueOf(buckets.download);
                int i8 = R$string.download;
                hashMap.put(valueOf3, context.getString(i8));
                hashMap.put(Integer.valueOf(buckets.screenRecordings), context.getString(R$string.screen_recordings));
                hashMap.put(Integer.valueOf(buckets.videoScreenShots), context.getString(R$string.screenrecorder));
                hashMap.put(Integer.valueOf(buckets.videoCaptures), context.getString(R$string.video_captures));
                hashMap.put(Integer.valueOf(buckets.scan), context.getString(R$string.scans));
                String str = storageInfo.gif;
                if (str != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str)), context.getString(R$string.gifs));
                }
                String str2 = storageInfo.agif;
                if (str2 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str2)), context.getString(R$string.animated_gif));
                }
                String str3 = storageInfo.liveMessage;
                if (str3 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str3)), context.getString(R$string.live_messages));
                }
                String str4 = storageInfo.collage;
                if (str4 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str4)), context.getString(R$string.sa_collage));
                }
                String str5 = storageInfo.bixbyVision;
                if (str5 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str5)), context.getString(R$string.vision_intelligence_title));
                }
                String str6 = storageInfo.superSlow;
                if (str6 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str6)), context.getString(R$string.super_slow_clips));
                }
                String str7 = storageInfo.myEmoji;
                if (str7 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str7)), context.getString(R$string.my_emoji));
                }
                String str8 = storageInfo.arEmoji;
                if (str8 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str8)), context.getString(R$string.ar_emoji));
                }
                String str9 = storageInfo.arEmojiCamera;
                if (str9 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str9)), context.getString(R$string.ar_emoji_camera));
                }
                String str10 = storageInfo.galaxyAvatar;
                if (str10 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str10)), context.getString(R$string.galaxy_avatar));
                }
                String str11 = storageInfo.avatarCamera;
                if (str11 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str11)), context.getString(R$string.avatar_camera));
                }
                String str12 = storageInfo.decoPic;
                if (str12 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str12)), context.getString(R$string.deco_pic));
                }
                String str13 = storageInfo.arDoodle;
                if (str13 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str13)), context.getString(R$string.ar_doodle));
                }
                String str14 = storageInfo.videoEditor;
                if (str14 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str14)), context.getString(R$string.video_editor));
                }
                String str15 = storageInfo.videoCollage;
                if (str15 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str15)), context.getString(R$string.sa_collage));
                }
                String str16 = storageInfo.stories;
                if (str16 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str16)), context.getString(R$string.stories));
                }
                String str17 = storageInfo.clippedImages;
                if (str17 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str17)), context.getString(R$string.clipped_images));
                }
                String str18 = storageInfo.studio;
                if (str18 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str18)), context.getString(R$string.video_studio_app_name));
                }
                String str19 = storageInfo.documentScans;
                if (str19 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str19)), context.getString(R$string.document_scans));
                }
                String str20 = storageInfo.creativeStudio;
                if (str20 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str20)), context.getString(R$string.creative_studio));
                }
                String str21 = storageInfo.glasses;
                if (str21 != null) {
                    hashMap.put(Integer.valueOf(FileUtils.getBucketId(str21)), context.getString(R$string.glasses));
                }
                hashMap.put(Integer.valueOf(BucketUtils.VirtualBucketHolder.screenShots), context.getString(i7));
                hashMap.put(Integer.valueOf(BucketUtils.VirtualBucketHolder.recent), context.getString(R$string.recently_added));
                hashMap.put(Integer.valueOf(BucketUtils.VirtualBucketHolder.favorite), context.getString(R$string.smart_album_favorites));
                hashMap.put(Integer.valueOf(BucketUtils.VirtualBucketHolder.mergedCamera), context.getString(i2));
                hashMap.put(Integer.valueOf(BucketUtils.VirtualBucketHolder.mergedScreenShot), context.getString(i7));
                hashMap.put(Integer.valueOf(BucketUtils.VirtualBucketHolder.mergedDownload), context.getString(i8));
                FileUtils.getSdcardDirList().stream().map(new a(10)).forEach(new d(hashMap, context, 0));
                FileUtils.getExternalStorageNameList().stream().map(new a(10)).forEach(new d(hashMap, context, 1));
            } catch (Exception e) {
                Log.e((CharSequence) "AlbumNameHolder", "build map failed", (Throwable) e);
            }
            sAlbumName = hashMap;
            Log.d("AlbumNameHolder", "initialize", Integer.valueOf(hashMap.size()));
        }

        public static Boolean isTranslatedAlbum(int i2) {
            boolean z;
            if (sAlbumName.get(Integer.valueOf(i2)) != null) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$initialize$0(HashMap hashMap, Context context, StorageInfo storageInfo) {
            StorageInfo.BucketHolder buckets = storageInfo.buckets();
            hashMap.put(Integer.valueOf(buckets.root), context.getString(R$string.new_album_storage_sdcard));
            hashMap.put(Integer.valueOf(buckets.camera), context.getString(R$string.dcim_name));
            hashMap.put(Integer.valueOf(buckets.download), context.getString(R$string.download));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$initialize$1(HashMap hashMap, Context context, StorageInfo storageInfo) {
            int i2;
            Integer valueOf = Integer.valueOf(storageInfo.buckets().root);
            if (storageInfo.dual) {
                i2 = R$string.internal_storage_dual_messenger;
            } else {
                i2 = R$string.new_album_storage_internal_storage;
            }
            hashMap.put(valueOf, context.getString(i2));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DirectoryNameHolder {
        private static final Map<Integer, String> sDirectoryName;

        static {
            HashMap hashMap = new HashMap();
            sDirectoryName = hashMap;
            hashMap.put(Integer.valueOf(R$string.download), "Download");
            hashMap.put(Integer.valueOf(R$string.dcim_name), "Camera");
            hashMap.put(Integer.valueOf(R$string.sa_collage), "Collage");
            hashMap.put(Integer.valueOf(R$string.video_editor), "Video Editor");
            hashMap.put(Integer.valueOf(R$string.animated_gif), "Animated GIF");
            hashMap.put(Integer.valueOf(R$string.gifs), "GIF");
            hashMap.put(Integer.valueOf(R$string.super_slow_clips), "SuperSlow");
            hashMap.put(Integer.valueOf(R$string.screenshot), "Screenshots");
            hashMap.put(Integer.valueOf(R$string.screenrecorder), "Video screenshots");
            hashMap.put(Integer.valueOf(R$string.my_emoji), "My Emoji");
            hashMap.put(Integer.valueOf(R$string.ar_emoji), "AR Emoji");
            hashMap.put(Integer.valueOf(R$string.ar_emoji_camera), "AR Emoji camera");
            hashMap.put(Integer.valueOf(R$string.galaxy_avatar), "Galaxy Avatar");
            hashMap.put(Integer.valueOf(R$string.avatar_camera), "Avatar Camera");
            hashMap.put(Integer.valueOf(R$string.deco_pic), "Deco Pic");
            hashMap.put(Integer.valueOf(R$string.vision_intelligence_title), "Bixby Vision");
            hashMap.put(Integer.valueOf(R$string.live_messages), "Live message");
            hashMap.put(Integer.valueOf(R$string.screen_recordings), "Screen recordings");
            hashMap.put(Integer.valueOf(R$string.ar_doodle), "AR Doodle");
            hashMap.put(Integer.valueOf(R$string.video_captures), "Videocaptures");
            hashMap.put(Integer.valueOf(R$string.stories), "Stories");
            hashMap.put(Integer.valueOf(R$string.clipped_images), "Clipped images");
            hashMap.put(Integer.valueOf(R$string.video_studio_app_name), "Studio");
            hashMap.put(Integer.valueOf(R$string.document_scans), "Document scans");
            hashMap.put(Integer.valueOf(R$string.creative_studio), "Creative studio");
            hashMap.put(Integer.valueOf(R$string.glasses), "Glasses");
        }

        public static String getDirectoryName(Context context, String str) {
            if (!TextUtils.isEmpty(str)) {
                for (Map.Entry next : sDirectoryName.entrySet()) {
                    if (str.equalsIgnoreCase(context.getString(((Integer) next.getKey()).intValue()))) {
                        return (String) next.getValue();
                    }
                }
            }
            return str;
        }
    }

    public static String getAlbumTitle(int i2, String str) {
        return AlbumNameHolder.getAlbumTitle(i2, str);
    }

    public static String getDirectoryName(Context context, String str) {
        return DirectoryNameHolder.getDirectoryName(context, str);
    }

    public static Boolean isTranslatedAlbum(int i2) {
        return AlbumNameHolder.isTranslatedAlbum(i2);
    }

    public static String getAlbumTitle(int i2) {
        return AlbumNameHolder.getAlbumTitle(i2, (String) null);
    }
}
