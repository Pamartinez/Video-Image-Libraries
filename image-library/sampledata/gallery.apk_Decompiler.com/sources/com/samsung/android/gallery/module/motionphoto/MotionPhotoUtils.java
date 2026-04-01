package com.samsung.android.gallery.module.motionphoto;

import B8.d;
import Gb.a;
import android.content.Intent;
import android.graphics.RectF;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfo;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoPlayback;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoUtils {
    private static final CharSequence TAG = "MotionPhotoUtils";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SectionInfo {
        public long dateTaken;
        public long duration;
        public long endPosition;
        public String filePath;
        public long startPosition;
    }

    public static Intent createGifIntent(String str, String str2) {
        FileUtils.delete(str2);
        if (!exportVideo(str, str2)) {
            return null;
        }
        MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(str2);
        if (videoInfo.isVertical()) {
            return createGifMakeIntent(str, str2, videoInfo.duration, videoInfo.height, videoInfo.width);
        }
        return createGifMakeIntent(str, str2, videoInfo.duration, videoInfo.width, videoInfo.height);
    }

    private static Intent createGifMakeIntent(String str, String str2, int i2, int i7, int i8) {
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.video", "com.samsung.android.video.player.activity.GifPlayerActivity");
        if (FileUtils.isInExternalFilesDir(str2)) {
            intent.setDataAndType(FileProviderUtil.getUri(AppResources.getAppContext(), str2), Encode.ContentType.VIDEO_MP4);
            intent.putExtra("original_path", str);
            intent.addFlags(1);
        } else {
            intent.putExtra("gif_video_path", str2);
        }
        intent.putExtra("gif_current_position", 0);
        intent.putExtra("gif_duration", i2);
        intent.putExtra("gif_video_width", i7);
        intent.putExtra("gif_video_height", i8);
        intent.putExtra("from_application", "com.sec.android.gallery3d");
        return intent;
    }

    public static boolean deleteVideo(String str) {
        try {
            MotionPhotoInfo.edit((File) new SecureFile(str)).removeVideo().commit();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean exportVideo(String str, String str2) {
        try {
            MotionPhotoInfo.parse((File) new SecureFile(str)).getVideo((File) new SecureFile(str2));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static long[] getAutoPlayVideoDataPosition(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        long autoPlayVideoPosition = MotionPhotoInfo.parse((File) new SecureFile(str)).getAutoPlayVideoPosition();
        long autoPlayVideoSize = MotionPhotoInfo.parse((File) new SecureFile(str)).getAutoPlayVideoSize();
        if (autoPlayVideoSize <= 0) {
            return null;
        }
        return new long[]{autoPlayVideoPosition, autoPlayVideoSize};
    }

    public static RectF getMotionPhotoCropInfo(File file) {
        try {
            return MotionPhotoInfo.parse(file).getCropRect();
        } catch (Exception e) {
            CharSequence charSequence = TAG;
            Log.e(charSequence, "getMotionPhotoCropInfo failed. e=" + e.getMessage());
            return null;
        }
    }

    public static List<SectionInfo> getOverlappingSection(List<SectionInfo> list) {
        long currentTimeMillis = System.currentTimeMillis();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (SectionInfo next : list) {
            linkedHashSet.add(new MotionPhotoVideoUtils.ItemInfo(new SecureFile(next.filePath), next.dateTaken, next.duration));
        }
        try {
            LinkedHashSet<MotionPhotoVideoUtils.SectionInfo> overlappingSection = new MotionPhotoVideoUtils().getOverlappingSection(linkedHashSet);
            ArrayList arrayList = new ArrayList();
            Iterator<MotionPhotoVideoUtils.SectionInfo> it = overlappingSection.iterator();
            while (it.hasNext()) {
                MotionPhotoVideoUtils.SectionInfo next2 = it.next();
                SectionInfo sectionInfo = new SectionInfo();
                sectionInfo.filePath = next2.file.getAbsolutePath();
                sectionInfo.startPosition = next2.startPosition;
                sectionInfo.endPosition = next2.endPosition;
                arrayList.add(sectionInfo);
            }
            CharSequence charSequence = TAG;
            Log.d(charSequence, "getOverlappingSection" + Logger.vt(currentTimeMillis));
            return arrayList;
        } catch (Exception e) {
            Exception exc = e;
            CharSequence charSequence2 = TAG;
            Log.e(charSequence2, "getOverlappingSection failed. e=" + exc.getMessage());
            Log.d(charSequence2, "getOverlappingSection" + Logger.vt(currentTimeMillis));
            return new ArrayList();
        } catch (Throwable th) {
            Throwable th2 = th;
            CharSequence charSequence3 = TAG;
            Log.d(charSequence3, "getOverlappingSection" + Logger.vt(currentTimeMillis));
            throw th2;
        }
    }

    public static long[] getVideoStreamInfoFromMotionPhoto(String str) {
        return getVideoStreamInfoFromMotionPhoto(str, new VideoReqInfo.Builder().build());
    }

    public static long getXmpTimeStamp(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            long captureTimestampUs = MotionPhotoInfo.parse((File) new SecureFile(str)).getCaptureTimestampUs();
            CharSequence charSequence = TAG;
            Log.d(charSequence, "getXmpTimeStamp" + Logger.vt(Long.valueOf(captureTimestampUs), Long.valueOf(currentTimeMillis)));
            return captureTimestampUs;
        } catch (Exception e) {
            CharSequence charSequence2 = TAG;
            Log.e(charSequence2, "getXmpTimeStamp failed. e=" + e.getMessage());
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaPlayback lambda$getPlaybackInfo$1(MotionPhotoPlayback motionPhotoPlayback) {
        return new MediaPlayback((int) motionPhotoPlayback.getStartTimeMs(), (int) motionPhotoPlayback.getEndTimeMS(), motionPhotoPlayback.getPlaySpeed());
    }

    public static boolean supportMotionSefPlay(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.hasSefFileTypes(SefInfo.MOTION_PHOTO_AUTO_PLAY.keyCode)) {
            return false;
        }
        return true;
    }

    public ArrayList<MediaPlayback> getPlaybackInfo(File file, MotionPhotoViewMode motionPhotoViewMode) {
        MotionPhotoPlayback.Type type;
        ArrayList<MediaPlayback> arrayList = new ArrayList<>();
        try {
            MotionPhotoViewMode motionPhotoViewMode2 = MotionPhotoViewMode.BOOMERANG;
            if (motionPhotoViewMode != motionPhotoViewMode2) {
                if (motionPhotoViewMode != MotionPhotoViewMode.SLOW_MO) {
                    return arrayList;
                }
            }
            if (motionPhotoViewMode == motionPhotoViewMode2) {
                type = MotionPhotoPlayback.Type.BOOMERANG;
            } else {
                type = MotionPhotoPlayback.Type.SLOW_MOTION;
            }
            MotionPhotoPlayback.of(type, file).stream().map(new a(8)).forEach(new d(arrayList, 6));
            return arrayList;
        } catch (Exception e) {
            CharSequence charSequence = TAG;
            Log.e(charSequence, "getPlaybackInfo failed. e=" + e.getMessage());
            return arrayList;
        }
    }

    public static long[] getVideoStreamInfoFromMotionPhoto(String str, VideoReqInfo videoReqInfo) {
        long[] autoPlayVideoDataPosition;
        try {
            if (videoReqInfo.isMotionSefPlay && (autoPlayVideoDataPosition = getAutoPlayVideoDataPosition(str)) != null) {
                return autoPlayVideoDataPosition;
            }
            return new long[]{MotionPhotoInfo.parse((File) new SecureFile(str)).getVideoPosition(), MotionPhotoInfo.parse((File) new SecureFile(str)).getVideoSize()};
        } catch (Error | Exception e) {
            if (Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                return SeApiCompat.getMediaTranscodeCompat().getVideoStreamInfoFromMotionPhoto(str);
            }
            CharSequence charSequence = TAG;
            Log.e(charSequence, "getVideoStreamInfoFromMotionPhoto failed. " + Logger.getEncodedString(str), e);
            return null;
        }
    }
}
