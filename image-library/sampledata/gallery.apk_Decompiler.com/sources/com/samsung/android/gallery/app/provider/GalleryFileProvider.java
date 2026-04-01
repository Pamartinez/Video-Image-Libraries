package com.samsung.android.gallery.app.provider;

import android.content.Context;
import android.net.Uri;
import android.os.FileObserver;
import android.os.ParcelFileDescriptor;
import androidx.core.content.FileProvider;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryFileProvider extends FileProvider {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CapturedFileObserver extends FileObserver {
        private FileEventListener mListener;

        public CapturedFileObserver(String str) {
            super(str);
        }

        public void onEvent(int i2, String str) {
            FileEventListener fileEventListener = this.mListener;
            if (fileEventListener != null) {
                a aVar = (a) fileEventListener;
                GalleryFileProvider.lambda$openCapturedFile$0(aVar.f2511a, aVar.b, i2);
            }
        }

        public void setFileEventListener(FileEventListener fileEventListener) {
            this.mListener = fileEventListener;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FileEventListener {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$openCapturedFile$0(CapturedFileObserver capturedFileObserver, CountDownLatch countDownLatch, int i2) {
        if (i2 == 8) {
            capturedFileObserver.stopWatching();
            countDownLatch.countDown();
        }
    }

    private void openCapturedFile(Context context, String str) {
        if (((Boolean) Blackboard.getApplicationInstance().read("data://user/CapturedFileWriting", Boolean.FALSE)).booleanValue()) {
            SecureFile secureFile = new SecureFile(context.getFilesDir(), str.substring(str.indexOf("/captured")));
            CountDownLatch countDownLatch = new CountDownLatch(1);
            CapturedFileObserver capturedFileObserver = new CapturedFileObserver(secureFile.getAbsolutePath());
            capturedFileObserver.setFileEventListener(new a(capturedFileObserver, countDownLatch));
            capturedFileObserver.startWatching();
            try {
                if (!countDownLatch.await(5, TimeUnit.SECONDS)) {
                    Log.e("GalleryFileProvider", "file writing timed out");
                }
            } catch (InterruptedException e) {
                Log.e("GalleryFileProvider", e.getMessage());
            }
        }
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) {
        Context context = getContext();
        String path = uri.getPath();
        if (!(context == null || path == null || !path.startsWith("/files/captured"))) {
            try {
                openCapturedFile(context, path);
            } catch (Error | Exception e) {
                Log.e((CharSequence) "GalleryFileProvider", "open file failed", e);
            }
        }
        return super.openFile(uri, str);
    }
}
