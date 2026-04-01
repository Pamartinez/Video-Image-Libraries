package com.samsung.android.gallery.app.controller.viewer;

import E3.g;
import N2.j;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.message.DownloadMsgMgr;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadHttpForViewerCmd extends BaseCommand {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DownloadCompleteListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DownloadSingleTask extends AsyncTask<Void, Void, Object[]> {
        private Dialog mDialog;
        private DownloadCompleteListener mListener;
        private MediaItem mMediaItem;
        private WeakReference<Context> mRef;

        public DownloadSingleTask(Context context, MediaItem mediaItem, DownloadCompleteListener downloadCompleteListener) {
            Dialog spinnerDialog = getSpinnerDialog(context);
            this.mDialog = spinnerDialog;
            if (spinnerDialog.getWindow() != null) {
                this.mDialog.getWindow().setGravity(17);
            }
            this.mMediaItem = mediaItem;
            this.mListener = downloadCompleteListener;
            this.mRef = new WeakReference<>(context);
        }

        private boolean copyStream(InputStream inputStream, OutputStream outputStream) {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr, 0, 4096);
                if (read <= 0) {
                    return true;
                }
                outputStream.write(bArr, 0, read);
            }
        }

        private boolean download(URL url, File file) {
            FileOutputStream fileOutputStream;
            InputStream inputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                inputStream = new UrlConnectionWrapper(false).openConnection(url).setConnectTimeout().getInputStream();
                boolean copyStream = copyStream(inputStream, fileOutputStream);
                if (inputStream != null) {
                    inputStream.close();
                }
                fileOutputStream.close();
                return copyStream;
                throw th;
                throw th;
            } catch (IOException e) {
                j.r(e, new StringBuilder("download failed e="), "DownloadHttpForViewerCmd");
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }

        private String getFileName(MediaItem mediaItem) {
            String str = "";
            String displayName = mediaItem.getDisplayName();
            if (FileType.isKnownType(displayName)) {
                return displayName;
            }
            String fileExtension = FileType.getFileExtension(mediaItem.getMimeType());
            if (fileExtension != null) {
                try {
                    str = fileExtension.replaceAll("\\W", str);
                } catch (Exception unused) {
                }
            } else {
                str = fileExtension;
            }
            if (TextUtils.isEmpty(str)) {
                return "temp00";
            }
            return C0212a.l("temp00.", str);
        }

        private Dialog getSpinnerDialog(Context context) {
            return new AlertDialog.Builder(context, R.style.TransparentDialogStyle).setView(LayoutInflater.from(context).inflate(R.layout.spinner_dialog, (ViewGroup) null, false)).create();
        }

        public void onPreExecute() {
            this.mDialog.show();
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(8:9|(1:11)|14|15|(2:17|18)|19|20|21) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x00bd */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object[] doInBackground(java.lang.Void... r11) {
            /*
                r10 = this;
                java.lang.String r11 = "DownloadHttpForViewerCmd"
                java.lang.String r0 = "createDirectory failed "
                java.lang.String r1 = "download success "
                java.lang.String r2 = "file://"
                java.lang.String r3 = "downloading... "
                r4 = 0
                long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00aa }
                java.lang.ref.WeakReference<android.content.Context> r7 = r10.mRef     // Catch:{ Exception -> 0x00aa }
                java.lang.Object r7 = r7.get()     // Catch:{ Exception -> 0x00aa }
                android.content.Context r7 = (android.content.Context) r7     // Catch:{ Exception -> 0x00aa }
                java.lang.String r8 = ".internet"
                java.io.File r7 = r7.getExternalFilesDir(r8)     // Catch:{ Exception -> 0x00aa }
                if (r7 == 0) goto L_0x00d5
                java.lang.String r8 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x00aa }
                boolean r8 = com.samsung.android.gallery.support.utils.FileUtils.makeDirectoryIfAbsent((java.lang.String) r8)     // Catch:{ Exception -> 0x00aa }
                if (r8 != 0) goto L_0x002b
                goto L_0x00d5
            L_0x002b:
                java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.module.data.MediaItem r8 = r10.mMediaItem     // Catch:{ Exception -> 0x00aa }
                java.lang.String r8 = r8.getPath()     // Catch:{ Exception -> 0x00aa }
                r0.<init>(r8)     // Catch:{ Exception -> 0x00aa }
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
                r8.<init>()     // Catch:{ Exception -> 0x00aa }
                java.lang.String r9 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x00aa }
                r8.append(r9)     // Catch:{ Exception -> 0x00aa }
                java.lang.String r9 = java.io.File.separator     // Catch:{ Exception -> 0x00aa }
                r8.append(r9)     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.module.data.MediaItem r9 = r10.mMediaItem     // Catch:{ Exception -> 0x00aa }
                java.lang.String r9 = r10.getFileName(r9)     // Catch:{ Exception -> 0x00aa }
                r8.append(r9)     // Catch:{ Exception -> 0x00aa }
                java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.support.utils.FileUtils.deleteFilesInDir(r7)     // Catch:{ Exception -> 0x00aa }
                java.io.File r7 = com.samsung.android.gallery.support.utils.FileUtils.createNewFileIfAbsent(r8)     // Catch:{ Exception -> 0x00aa }
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
                r9.<init>(r3)     // Catch:{ Exception -> 0x00aa }
                r9.append(r8)     // Catch:{ Exception -> 0x00aa }
                java.lang.String r3 = r9.toString()     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.support.utils.Log.d(r11, r3)     // Catch:{ Exception -> 0x00aa }
                boolean r0 = r10.download(r0, r7)     // Catch:{ Exception -> 0x00aa }
                if (r0 == 0) goto L_0x00f8
                com.samsung.android.gallery.module.data.MediaItem r0 = r10.mMediaItem     // Catch:{ Exception -> 0x00aa }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
                r3.<init>(r2)     // Catch:{ Exception -> 0x00aa }
                java.lang.String r2 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x00aa }
                r3.append(r2)     // Catch:{ Exception -> 0x00aa }
                java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x00aa }
                r0.setPath(r2)     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.module.data.MediaItem r0 = r10.mMediaItem     // Catch:{ Exception -> 0x00aa }
                long r2 = r7.length()     // Catch:{ Exception -> 0x00aa }
                r0.setFileSize(r2)     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r0 = com.samsung.android.gallery.module.thumbnail.ThumbnailLoader.getInstance()     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.module.data.MediaItem r2 = r10.mMediaItem     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.module.thumbnail.type.ThumbKind r3 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.MEDIUM_KIND     // Catch:{ Exception -> 0x00aa }
                android.graphics.Bitmap r0 = r0.loadThumbnailSync(r2, r3)     // Catch:{ Exception -> 0x00aa }
                if (r0 == 0) goto L_0x00ac
                com.samsung.android.gallery.module.data.MediaItem r2 = r10.mMediaItem     // Catch:{ Exception -> 0x00aa }
                int r3 = r0.getWidth()     // Catch:{ Exception -> 0x00aa }
                int r7 = r0.getHeight()     // Catch:{ Exception -> 0x00aa }
                r2.setSize(r3, r7)     // Catch:{ Exception -> 0x00aa }
                goto L_0x00ac
            L_0x00aa:
                r10 = move-exception
                goto L_0x00ee
            L_0x00ac:
                long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00aa }
                long r2 = r2 - r5
                r5 = 800(0x320, double:3.953E-321)
                long r5 = r5 - r2
                r2 = 0
                int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
                if (r2 <= 0) goto L_0x00bd
                java.lang.Thread.sleep(r5)     // Catch:{ InterruptedException -> 0x00bd }
            L_0x00bd:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
                r2.<init>(r1)     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.module.data.MediaItem r1 = r10.mMediaItem     // Catch:{ Exception -> 0x00aa }
                r2.append(r1)     // Catch:{ Exception -> 0x00aa }
                java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.support.utils.Log.d(r11, r1)     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.module.data.MediaItem r10 = r10.mMediaItem     // Catch:{ Exception -> 0x00aa }
                java.lang.Object[] r10 = new java.lang.Object[]{r10, r0}     // Catch:{ Exception -> 0x00aa }
                return r10
            L_0x00d5:
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
                r10.<init>(r0)     // Catch:{ Exception -> 0x00aa }
                if (r7 != 0) goto L_0x00df
                java.lang.String r0 = "null"
                goto L_0x00e3
            L_0x00df:
                java.lang.String r0 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x00aa }
            L_0x00e3:
                r10.append(r0)     // Catch:{ Exception -> 0x00aa }
                java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x00aa }
                com.samsung.android.gallery.support.utils.Log.e(r11, r10)     // Catch:{ Exception -> 0x00aa }
                return r4
            L_0x00ee:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "download failed e="
                r0.<init>(r1)
                A.a.s(r10, r0, r11)
            L_0x00f8:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.viewer.DownloadHttpForViewerCmd.DownloadSingleTask.doInBackground(java.lang.Void[]):java.lang.Object[]");
        }

        public void onPostExecute(Object[] objArr) {
            try {
                this.mDialog.dismiss();
                if (objArr == null) {
                    boolean isImage = this.mMediaItem.isImage();
                    Toast.makeText(this.mRef.get(), DownloadMsgMgr.getDownloadFailToastMessage(this.mRef.get(), CloudErrorType.Network, isImage ? 1 : 0, isImage ^ true ? 1 : 0), 0).show();
                }
                DownloadCompleteListener downloadCompleteListener = this.mListener;
                if (downloadCompleteListener != null) {
                    ((g) downloadCompleteListener).a(objArr);
                }
                this.mListener = null;
                this.mRef = null;
                this.mDialog = null;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UrlConnectionWrapper {
        URLConnection mConnection;
        final boolean mUseProxy;

        public UrlConnectionWrapper(boolean z) {
            this.mUseProxy = z;
        }

        public InputStream getInputStream() {
            return this.mConnection.getInputStream();
        }

        public UrlConnectionWrapper openConnection(URL url) {
            URLConnection uRLConnection;
            if (this.mUseProxy) {
                uRLConnection = url.openConnection();
            } else {
                uRLConnection = url.openConnection(Proxy.NO_PROXY);
            }
            this.mConnection = uRLConnection;
            return this;
        }

        public UrlConnectionWrapper setConnectTimeout() {
            this.mConnection.setConnectTimeout(15000);
            return this;
        }
    }

    private boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        DownloadCompleteListener downloadCompleteListener = objArr[1];
        if (mediaItem == null) {
            Log.e("DownloadHttpForViewerCmd", "delivered item is null");
        } else if (isNetworkConnected()) {
            new DownloadSingleTask(getContext(), mediaItem, downloadCompleteListener).execute(new Void[0]);
        } else {
            showToast((int) R.string.sharing_invitations_check_your_network);
            ((g) downloadCompleteListener).a((Object[]) null);
        }
    }
}
