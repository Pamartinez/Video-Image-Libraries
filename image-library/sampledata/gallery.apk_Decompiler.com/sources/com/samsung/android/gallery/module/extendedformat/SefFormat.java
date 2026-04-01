package com.samsung.android.gallery.module.extendedformat;

import H3.n;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SefFormat {
    protected final String TAG = tag();
    protected Uri mResultUri;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$scanFile$0(CountDownLatch countDownLatch, String str, Uri uri) {
        this.mResultUri = uri;
        countDownLatch.countDown();
    }

    public boolean addSefInfo(String str, SefInfo sefInfo, byte[] bArr) {
        if (SeApiCompat.getSefFileCompat().addData((File) new SecureFile(str), sefInfo, bArr) != 0) {
            return true;
        }
        return false;
    }

    public File getDstFile(File file, String str, String str2) {
        SecureFile secureFile = new SecureFile(file.getParent(), C0212a.A(str, str2));
        String absolutePath = secureFile.getAbsolutePath();
        if (secureFile.exists()) {
            return new SecureFile(FileUtils.getNewFilePath(absolutePath));
        }
        return secureFile;
    }

    public byte[] getSefInfo(String str, String str2) {
        try {
            return SeApiCompat.getSefFileCompat().getData(new File(str), str2);
        } catch (IOException unused) {
            Log.e(this.TAG, "getSefInfo failed");
            return null;
        }
    }

    public boolean removeSefInfo(String str, String str2) {
        try {
            SeApiCompat.getSefFileCompat().deleteData(new SecureFile(str), str2);
            return true;
        } catch (IOException unused) {
            Log.e(this.TAG, "removeSefInfo failed");
            return false;
        }
    }

    public void scanFile(Context context, File file) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, (String[]) null, new n(4, this, countDownLatch));
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract String tag();
}
