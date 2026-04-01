package com.samsung.android.gallery.module.media;

import F9.a;
import android.media.MediaScannerConnection;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.library.sef.SefParser;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DualPhotoManipulator2 {
    private byte[] mBackupBuffer;
    private ExifInterface mExif;
    private byte[] mMediaData;
    private Mode mMode;
    private final SefParser mSefParser;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Mode {
        CloseUp,
        Wide
    }

    public DualPhotoManipulator2(String str) {
        this.mSefParser = new SefParser(str);
    }

    public static long changeCover(boolean z, String str, int i2, long j2) {
        DualPhotoManipulator2 unpack = new DualPhotoManipulator2(str).unpack();
        if (z == unpack.isWide()) {
            Log.e((CharSequence) "DualPhotoManipulator2", "changeCover skip", Boolean.valueOf(z), Boolean.valueOf(unpack.isWide()));
            return -1;
        }
        unpack.toggle().apply();
        return FileUtils.length(str);
    }

    private void changeToCloseUp() {
        this.mSefParser.addData("DualShot_2", 1, this.mSefParser.getMediaData());
        this.mMediaData = this.mSefParser.getData("DualShot_3");
        this.mSefParser.deleteData("DualShot_3");
    }

    private void changeToWide() {
        this.mSefParser.addData("DualShot_3", 1, this.mSefParser.getMediaData());
        this.mMediaData = this.mSefParser.getData("DualShot_2");
        this.mSefParser.deleteData("DualShot_2");
    }

    public static boolean isWideImage(String str) {
        return SefParser.hasData(str, "DualShot_3");
    }

    private boolean writeFile(byte[] bArr, String str) {
        FileOutputStream fileOutputStream;
        System.currentTimeMillis();
        try {
            fileOutputStream = new FileOutputStream(new SecureFile(str));
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            Log.e((CharSequence) "DualPhotoManipulator2", "writeFile failed", (Throwable) e);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public boolean apply(OutputStream outputStream) {
        outputStream.write(this.mMediaData);
        this.mSefParser.apply(outputStream);
        outputStream.flush();
        return true;
    }

    public boolean backup(String str) {
        byte[] readFile = FileUtils.readFile(str);
        this.mBackupBuffer = readFile;
        if (readFile == null || readFile.length <= 0) {
            return false;
        }
        return true;
    }

    public boolean isWide() {
        if (this.mMode == Mode.Wide) {
            return true;
        }
        return false;
    }

    public boolean restore(String str) {
        if (this.mBackupBuffer == null) {
            Log.e("DualPhotoManipulator2", "restore skip. empty buffer");
            return false;
        }
        String str2 = StorageInfo.getDefault().download;
        FileUtils.makeDirectoryIfAbsent(str2);
        String nameFromPath = FileUtils.getNameFromPath(str);
        File file = new File(str2, FileUtils.addSuffix(nameFromPath, "_" + System.currentTimeMillis()));
        if (!writeFile(this.mBackupBuffer, file.getPath())) {
            Log.e("DualPhotoManipulator2", "restore failed");
            return false;
        }
        MediaScannerConnection.scanFile(AppResources.getAppContext(), new String[]{file.getPath()}, (String[]) null, new a(0));
        return true;
    }

    public DualPhotoManipulator2 toggle() {
        if (this.mMode == Mode.Wide) {
            changeToCloseUp();
            return this;
        }
        changeToWide();
        return this;
    }

    public DualPhotoManipulator2 unpack() {
        Mode mode;
        this.mExif = ExifUtils.getExif(this.mSefParser.getPath());
        this.mSefParser.unpack();
        if (this.mSefParser.hasData("DualShot_3")) {
            mode = Mode.Wide;
        } else {
            mode = Mode.CloseUp;
        }
        this.mMode = mode;
        return this;
    }

    public boolean apply(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        long currentTimeMillis = System.currentTimeMillis();
        byte[] bArr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            apply((OutputStream) byteArrayOutputStream);
            bArr = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            Log.e((CharSequence) "DualPhotoManipulator2", "apply failed", (Throwable) e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (bArr == null) {
            Log.d("DualPhotoManipulator2", "apply failed to change" + Logger.vt(currentTimeMillis));
            return false;
        }
        long length = FileUtils.length(str);
        backup(str);
        boolean writeFile = writeFile(bArr, str);
        if (writeFile) {
            ExifUtils.copyDateLocation(this.mExif, str);
        } else {
            Utils.showThemeToast(R$string.image_save_fail);
            restore(str);
        }
        A.a.A(new Object[]{Boolean.valueOf(writeFile), Integer.valueOf(bArr.length), Long.valueOf(length), Long.valueOf(FileUtils.length(str)), Long.valueOf(currentTimeMillis)}, new StringBuilder("apply"), "DualPhotoManipulator2");
        return writeFile;
        throw th;
    }

    public boolean apply() {
        return apply(this.mSefParser.getPath());
    }
}
