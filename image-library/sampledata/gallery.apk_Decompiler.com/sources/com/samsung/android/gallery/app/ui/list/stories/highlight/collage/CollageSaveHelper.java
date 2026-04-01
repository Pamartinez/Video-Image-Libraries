package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import I3.f;
import W.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.window.embedding.c;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.story.CollageVideoSaveCmd;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.story.DurationHelper;
import com.samsung.android.gallery.module.story.ExportOptions;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CollageSaveHelper {
    private static Paint createBgColorPaint(Context context) {
        Paint paint = new Paint();
        paint.setColor(context.getColor(R.color.story_highlight_collage_content_bg_color));
        return paint;
    }

    private static ExportOptions createExportOptions(MediaItem mediaItem, int[] iArr, Bitmap bitmap, String str) {
        BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
        bgmExtraInfo.bgmName = mediaItem.getPath();
        HashMap hashMap = new HashMap();
        hashMap.put(mediaItem, new KenBurnsInfo());
        return new ExportOptions().setRequestCode(1803).setPlayTimeMs(Math.min(DurationHelper.getItemDuration(mediaItem), TextToSpeechConst.MAX_SPEECH_INPUT)).setSize(iArr).setBackgroundBitmap(bitmap).setDisplayPositionRect(str).setKenBurnsMap(hashMap).setBgmExtraInfo(bgmExtraInfo);
    }

    private static Bitmap getBitmapFromView(View view) {
        Bitmap createBitmap = BitmapUtils.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawPaint(createBgColorPaint(view.getContext()));
        view.draw(canvas);
        return createBitmap;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveGridVideoCollage$3(View view, MediaItem mediaItem, int[] iArr, String str, String str2, EventContext eventContext) {
        ExportOptions createExportOptions = createExportOptions(mediaItem, iArr, getBitmapFromView(view), str);
        createExportOptions.setCornerRadius(str2);
        new CollageVideoSaveCmd().execute(eventContext, createExportOptions);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveImageCollage$2(View view, int[] iArr) {
        Bitmap bitmapFromView = getBitmapFromView(view);
        int i2 = iArr[0];
        int i7 = iArr[1];
        float width = ((float) i2) / ((float) view.getWidth());
        int width2 = (int) (((float) bitmapFromView.getWidth()) * width);
        int height = (int) (((float) bitmapFromView.getHeight()) * width);
        Rect rect = new Rect(0, 0, bitmapFromView.getWidth(), bitmapFromView.getHeight());
        int max = Math.max((i2 - width2) / 2, 0);
        int max2 = Math.max((i7 - height) / 2, 0);
        Rect rect2 = new Rect(max, max2, i2 - max, i7 - max2);
        Bitmap createBitmap = BitmapUtils.createBitmap(i2, i7, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmapFromView, rect, rect2, createBgColorPaint(view.getContext()));
        Log.d("CollageSaveHelper", "handleSave ", Integer.valueOf(width2), Integer.valueOf(height), Integer.valueOf(bitmapFromView.getWidth()), Integer.valueOf(bitmapFromView.getHeight()), Float.valueOf(((float) height) / ((float) width2)));
        String createSaveFilePath = StoryHelper.createSaveFilePath(StorageInfo.getDefault().stories, true);
        if (startBitmapToFile(createBitmap, createSaveFilePath)) {
            MediaScanner.scanFile(createSaveFilePath, new a(18));
            ThreadUtil.postOnUiThreadDelayed(new b9.a(createSaveFilePath, 1), 300);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveIrregularVideoCollage$4(MediaItem mediaItem, int[] iArr, Bitmap bitmap, String str, Bitmap bitmap2, EventContext eventContext) {
        ExportOptions createExportOptions = createExportOptions(mediaItem, iArr, bitmap, str);
        createExportOptions.setForegroundBitmap(bitmap2);
        new CollageVideoSaveCmd().execute(eventContext, createExportOptions);
    }

    private static void prepareDirectory(String str) {
        if (!FileUtils.makeDirectoryIfAbsent((File) new SecureFile(FileUtils.getDirectoryFromPath(str)))) {
            Log.d("CollageSaveHelper", "failed create directory : " + Logger.getEncodedString(FileUtils.getDirectoryFromPath(str)));
            throw new RuntimeException("fail to create folder");
        }
    }

    public static void saveGridVideoCollage(EventContext eventContext, View view, int[] iArr, MediaItem mediaItem, String str, String str2) {
        MediaItem mediaItem2 = mediaItem;
        SimpleThreadPool.getInstance().execute(new f(view, mediaItem2, iArr, str, str2, eventContext));
    }

    public static void saveImageCollage(IMvpBaseView iMvpBaseView, View view, int[] iArr) {
        SimpleThreadPool.getInstance().execute(new c(18, view, iArr));
        iMvpBaseView.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_COLLAGE_DOWNLOAD);
    }

    public static void saveIrregularVideoCollage(EventContext eventContext, Bitmap bitmap, Bitmap bitmap2, int[] iArr, MediaItem mediaItem, String str) {
        int[] iArr2 = iArr;
        MediaItem mediaItem2 = mediaItem;
        String str2 = str;
        SimpleThreadPool.getInstance().execute(new f(mediaItem2, iArr2, bitmap, str2, bitmap2, eventContext));
    }

    /* access modifiers changed from: private */
    public static void showSuccessToast(String str) {
        Utils.showToast(AppResources.getAppContext(), AppResources.getString(R.string.collage_saved_in, BucketUtils.translatePath(str, false)));
    }

    private static boolean startBitmapToFile(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        try {
            prepareDirectory(str);
            SecureFile secureFile = new SecureFile(str);
            if (!secureFile.createNewFile()) {
                return false;
            }
            fileOutputStream = new FileOutputStream(secureFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException | NullPointerException | SecurityException e) {
            e.printStackTrace();
            FileUtils.delete(str);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
