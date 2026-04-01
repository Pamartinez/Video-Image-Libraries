package com.samsung.android.gallery.module.media;

import A.a;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaCaptureWorker extends Thread {
    protected final String TAG = getClass().getSimpleName();
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;
    protected final MediaCaptureCompat mMediaCaptureCompat;
    protected final String mOutFilePath;

    public MediaCaptureWorker(String str, Consumer<Integer> consumer, Consumer<Boolean> consumer2, MediaCaptureCompat.OnErrorListener onErrorListener) {
        MediaCaptureCompat createMediaCaptureCompat = SeApiCompat.createMediaCaptureCompat();
        this.mMediaCaptureCompat = createMediaCaptureCompat;
        createMediaCaptureCompat.setOnProgressListener(consumer);
        createMediaCaptureCompat.setOnCompleteListener(consumer2);
        createMediaCaptureCompat.setOnErrorListener(onErrorListener);
        this.mOutFilePath = str;
    }

    public String exportTempMotionPhotoVideo(String str) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileUtils.getExternalFilesDir("motion"));
        String p6 = C0212a.p(sb2, File.separator, "temp_motion_ism.mp4");
        if (new MotionPhotoVideoUtils().saveVideo(new SecureFile(str), new SecureFile(p6))) {
            return p6;
        }
        return null;
    }

    public void finish() {
        long currentTimeMillis = System.currentTimeMillis();
        this.mMediaCaptureCompat.release();
        a.x(new StringBuilder("finish +"), currentTimeMillis, this.TAG);
        Utils.closeSilently(this.mFileInputStream);
        Utils.closeSilently(this.mFileOutputStream);
        this.mFileInputStream = null;
        this.mFileOutputStream = null;
    }

    public String getFileInputStreamName(FileItemInterface fileItemInterface) {
        return fileItemInterface.getPath();
    }

    public void initBackgroundMusic(BgmOptions bgmOptions) {
        this.mMediaCaptureCompat.initBackgroundMusic(bgmOptions);
    }

    public void prepare(FileItemInterface fileItemInterface, List<MediaPlayback> list, Consumer<Boolean> consumer) {
        if (!this.mMediaCaptureCompat.support() || !supportInternal(fileItemInterface)) {
            Log.e(this.TAG, "MediaCapture is not supported");
            return;
        }
        try {
            SecureFile secureFile = new SecureFile(this.mOutFilePath);
            this.mFileInputStream = new FileInputStream(getFileInputStreamName(fileItemInterface));
            this.mFileOutputStream = new FileOutputStream(secureFile);
            this.mMediaCaptureCompat.setDataSource(this.mFileInputStream.getFD());
            this.mMediaCaptureCompat.setOutputFile(this.mFileOutputStream.getFD());
            prepareInternal(fileItemInterface, list, consumer);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "prepare fail(" + list.size() + "): " + e);
            consumer.accept(Boolean.FALSE);
        }
    }

    public abstract void prepareInternal(FileItemInterface fileItemInterface, List<MediaPlayback> list, Consumer<Boolean> consumer);

    public final void run() {
        this.mMediaCaptureCompat.startCapture();
    }

    public boolean supportInternal(FileItemInterface fileItemInterface) {
        return true;
    }
}
