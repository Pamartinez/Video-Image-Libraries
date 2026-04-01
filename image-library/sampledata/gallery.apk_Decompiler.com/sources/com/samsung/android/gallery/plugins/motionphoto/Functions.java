package com.samsung.android.gallery.plugins.motionphoto;

import A2.d;
import A4.C0367b;
import A4.C0372g;
import A4.O;
import Ba.a;
import Ba.b;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.ExifCompat;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import i.C0212a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Functions {
    Context context;
    MediaItem item;

    public Functions(Context context2, MediaItem mediaItem) {
        this.context = context2;
        this.item = mediaItem;
        FileUtils.delete(getTempPath());
    }

    private Intent createShareIntent(Uri uri, long j2) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setType("video/*");
        intent.addFlags(1);
        intent.putExtra("exit_on_sent", false);
        intent.putExtra("more_actions_print", 0);
        intent.putExtra("more_actions_quick_connect", 1);
        intent.putExtra("more_actions_package_name", "com.sec.android.gallery3d");
        intent.putExtra("more_actions_screen_mirroring", 0);
        intent.putExtra("more_actions_screen_sharing", 0);
        intent.putExtra("more_actions_dlna", 1);
        intent.putExtra("location_key", "");
        intent.putExtra("item_index", -1);
        Intent createChooser = Intent.createChooser(intent, this.context.getString(R$string.share_short));
        createChooser.putExtra("sem_extra_chooser_content_count", 1);
        createChooser.putExtra("sem_extra_chooser_content_size", StringCompat.toReadableSize((double) j2));
        Intent intent2 = (Intent) createChooser.getParcelableExtra("android.intent.extra.INTENT");
        if (intent2 != null) {
            intent2.addFlags(134742016);
        }
        return createChooser;
    }

    private String getTempPath() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileUtils.getExternalFilesDir("motion"));
        return C0212a.p(sb2, File.separator, "temp.mp4");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$capture$0(String str) {
        Log.d("Functions", "scan done");
        String translatedDirectory = BucketUtils.getTranslatedDirectory(str);
        Context context2 = this.context;
        Utils.showToast(context2, context2.getString(R$string.toast_image_saved_in, new Object[]{translatedDirectory}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$capture$1(Bitmap bitmap) {
        String str = StorageInfo.getDefault().videoCaptures;
        FileUtils.makeDirectoryIfAbsent(str);
        String buildUnique = new FileNameBuilder(this.item.getPath()).setDirectoryPath(str).setPreFix("VideoCapture_").buildUnique();
        if (saveBitmap(buildUnique, bitmap)) {
            saveExif(buildUnique, bitmap.getWidth(), bitmap.getHeight());
            saveSef(buildUnique);
            MediaScanner.scanFile(buildUnique, new b(this, buildUnique, 1));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$capture$2(Bitmap bitmap) {
        SimpleThreadPool.getInstance().execute(new d(15, this, bitmap));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDeleteConfirmed$6() {
        Log.d("Functions", "scan done");
        Context context2 = this.context;
        Utils.showToast(context2, context2.getString(R$string.deleting_video_from_motion_photo));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDeleteConfirmed$7(String str) {
        if (MotionPhotoUtils.deleteVideo(str)) {
            Log.d("Functions", "success : " + this.item);
            MediaScanner.scanFile(str, new O(13, this));
            return;
        }
        Log.e((CharSequence) "Functions", C0212a.l("failed : ", str), this.item);
        Utils.showToast(this.context, R$string.image_save_fail);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveVideo$8(String str) {
        Log.d("Functions", "scan done");
        String translatedDirectory = BucketUtils.getTranslatedDirectory(str);
        Context context2 = this.context;
        Utils.showToast(context2, context2.getString(R$string.video_saved_in, new Object[]{translatedDirectory}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$share$4(Intent intent) {
        this.context.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$share$5() {
        String tempPath = getTempPath();
        FileUtils.delete(tempPath);
        if (MotionPhotoUtils.exportVideo(this.item.getPath(), tempPath)) {
            Intent createShareIntent = createShareIntent(FileProviderUtil.getUri(this.context, tempPath), new File(tempPath).length());
            Log.d("Functions", "share ", Logger.toString(createShareIntent), tempPath, Boolean.valueOf(FileUtils.exists(tempPath)));
            ThreadUtil.postOnUiThread(new d(16, this, createShareIntent));
        }
    }

    /* access modifiers changed from: private */
    public void onDeleteConfirmed() {
        MediaItem mediaItem = this.item;
        if (mediaItem != null) {
            SimpleThreadPool.getInstance().execute(new d(17, this, mediaItem.getPath()));
            return;
        }
        Log.e("Functions", "failed. null");
    }

    private boolean saveBitmap(String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new SecureFile(str));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            Log.d("Functions", "saveBitmap : " + Logger.getEncodedString(str));
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            Log.e((CharSequence) "Functions", "saveBitmap failed " + Logger.toString(bitmap), (Throwable) e);
            FileUtils.delete(str);
            Log.d("Functions", "saveBitmap fail : " + Logger.getEncodedString(str));
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void capture(MotionPhotoViewHolder motionPhotoViewHolder) {
        motionPhotoViewHolder.captureFrame(new C0367b(11, this));
    }

    public void createGif(Context context2) {
        String str;
        if (context2 != null) {
            if (SdkConfig.atLeast(SdkConfig.GED.S)) {
                str = getTempPath();
            } else {
                str = new FileNameBuilder(this.item.getPath()).setExtension(IFormat.FORMAT_MP4).build();
            }
            Log.d("Functions", "createGif", str);
            try {
                Intent createGifIntent = MotionPhotoUtils.createGifIntent(this.item.getPath(), str);
                context2.startActivity(createGifIntent);
                Log.d("Functions", "createGif export as gif", createGifIntent);
            } catch (Exception e) {
                FileUtils.delete(str);
                Utils.showToast(context2, R$string.image_save_fail);
                Log.d("Functions", "createGif failed. e=" + e.getMessage());
            }
        }
    }

    public void delete(final Runnable runnable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(R$string.deleting_video_clip_confirm_dialog).setPositiveButton(R$string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                Functions.this.onDeleteConfirmed();
                dialogInterface.dismiss();
                runnable.run();
            }
        }).setNeutralButton(R$string.cancel, new a(0));
        builder.create().show();
    }

    public void saveExif(String str, int i2, int i7) {
        ExifUtils.copy(this.item.getPath(), str, i2, i7);
        ExifCompat exifCompat = new ExifCompat(str);
        exifCompat.setOrientation(0);
        exifCompat.saveAttributes();
    }

    public void saveSef(String str) {
        SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
        File file = new File(this.item.getPath());
        File file2 = new File(str);
        try {
            SefInfo sefInfo = SefInfo.IMAGE_UTC_DATA;
            sefFileCompat.addData(file2, sefInfo.keyName, sefFileCompat.getData(file, sefInfo.keyName), sefInfo.keyCode, this.item.getMimeType());
        } catch (IOException unused) {
            Log.e("Functions", "saveSef failed");
        }
    }

    public void saveVideo() {
        String buildUnique = new FileNameBuilder(this.item.getPath()).setExtension(IFormat.FORMAT_MP4).buildUnique();
        if (MotionPhotoUtils.exportVideo(this.item.getPath(), buildUnique)) {
            Log.d("Functions", "success export as video");
            MediaScanner.scanFile(buildUnique, new b(this, buildUnique, 0));
            return;
        }
        Log.e((CharSequence) "Functions", "fail export as video", buildUnique);
        Utils.showToast(this.context, R$string.video_save_fail);
    }

    public void share() {
        SimpleThreadPool.getInstance().execute(new C0372g(10, this));
    }
}
