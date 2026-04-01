package com.samsung.android.gallery.module.remotegallery;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Pair;
import com.google.gson.JsonSyntaxException;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.remotegallery.TransferHeader;
import com.samsung.android.gallery.support.cache.BytesBuffer;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.Utils;
import i.C0212a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ConnectionWrap implements Runnable {
    public static final String TARGET_DIR;
    DataTransfer mDataTransfer;
    IThumbnailLoader mThumbnailLoader;
    private final Socket socket;

    static {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(StorageInfo.getDefault().pictures);
        TARGET_DIR = C0212a.p(sb2, File.separator, "RG");
    }

    public ConnectionWrap(Socket socket2, String str, IThumbnailLoader iThumbnailLoader) {
        this.socket = socket2;
        this.mDataTransfer = new DataTransfer(str);
        this.mThumbnailLoader = iThumbnailLoader;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.function.Consumer, java.lang.Object] */
    private long getLastMaxId() {
        Cursor query = DbCompat.query(DbKey.ALL_PICTURES, new Object());
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    long fileId = MediaItemLoader.load(query).getFileId();
                    query.close();
                    return fileId;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query == null) {
            return 0;
        }
        query.close();
        return 0;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$0(DataInputStream dataInputStream, ArrayList arrayList, AtomicInteger atomicInteger, TransferHeader transferHeader, Pair pair) {
        Log.i("RemoteServer", "receive File : " + ((String) pair.first), pair.second);
        FileUtils.makeDirectoryIfAbsent(new File(TARGET_DIR));
        String receiveFile = receiveFile(dataInputStream, (String) pair.first, (Long) pair.second);
        if (receiveFile != null) {
            arrayList.add(receiveFile);
        }
        atomicInteger.getAndIncrement();
        Context appContext = AppResources.getAppContext();
        Utils.showToast(appContext, "Received " + atomicInteger + "/" + transferHeader.fileCount);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$1(TransferHeader transferHeader) {
        new RemoteClient(this.socket.getInetAddress().toString().substring(1)).sendFiles(transferHeader.fileCount);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$scanFiles$4(long r4, java.util.ArrayList r6, java.util.concurrent.CountDownLatch r7) {
        /*
            java.lang.String r0 = "fail get uploaded files : "
            java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES
            com.samsung.android.gallery.module.remotegallery.a r2 = new com.samsung.android.gallery.module.remotegallery.a
            r3 = 0
            r2.<init>(r4, r3)
            android.database.Cursor r4 = com.samsung.android.gallery.database.dal.DbCompat.query(r1, r2)
            if (r4 == 0) goto L_0x0035
            boolean r5 = r4.moveToFirst()     // Catch:{ all -> 0x002c }
            if (r5 == 0) goto L_0x0035
        L_0x0016:
            com.samsung.android.gallery.module.data.MediaItem r5 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r4)     // Catch:{ all -> 0x002c }
            java.lang.String r0 = r5.getPath()     // Catch:{ all -> 0x002c }
            boolean r0 = r6.contains(r0)     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x002e
            com.samsung.android.gallery.module.remotegallery.RemoteServer r0 = com.samsung.android.gallery.module.remotegallery.RemoteServer.getInstance()     // Catch:{ all -> 0x002c }
            r0.addFileToTop(r5)     // Catch:{ all -> 0x002c }
            goto L_0x002e
        L_0x002c:
            r5 = move-exception
            goto L_0x0059
        L_0x002e:
            boolean r5 = r4.moveToNext()     // Catch:{ all -> 0x002c }
            if (r5 != 0) goto L_0x0016
            goto L_0x0050
        L_0x0035:
            java.lang.String r5 = "RemoteServer"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x002c }
            r6.<init>(r0)     // Catch:{ all -> 0x002c }
            android.os.Bundle r0 = r4.getExtras()     // Catch:{ all -> 0x002c }
            java.lang.String r1 = "query"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x002c }
            r6.append(r0)     // Catch:{ all -> 0x002c }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x002c }
            com.samsung.android.gallery.support.utils.Log.e(r5, r6)     // Catch:{ all -> 0x002c }
        L_0x0050:
            r7.countDown()     // Catch:{ all -> 0x002c }
            if (r4 == 0) goto L_0x0058
            r4.close()
        L_0x0058:
            return
        L_0x0059:
            if (r4 == 0) goto L_0x0063
            r4.close()     // Catch:{ all -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r4 = move-exception
            r5.addSuppressed(r4)
        L_0x0063:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.remotegallery.ConnectionWrap.lambda$scanFiles$4(long, java.util.ArrayList, java.util.concurrent.CountDownLatch):void");
    }

    private int read(DataInputStream dataInputStream, BufferedOutputStream bufferedOutputStream, byte[] bArr, int i2) {
        int read = dataInputStream.read(bArr, 0, i2);
        bufferedOutputStream.write(bArr, 0, read);
        bufferedOutputStream.flush();
        return read;
    }

    private TransferHeader readHeader(DataInputStream dataInputStream) {
        return (TransferHeader) GsonTool.fromJsonString(TransferHeader.class, this.mDataTransfer.receiveString(dataInputStream));
    }

    private String receiveFile(DataInputStream dataInputStream, String str, Long l) {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        Throwable th2;
        DataInputStream dataInputStream2 = dataInputStream;
        String str2 = str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TARGET_DIR);
        String buildUnique = new FileNameBuilder(C0212a.p(sb2, File.separator, str2)).buildUnique();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new SecureFile(buildUnique));
            try {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                byte[] bArr = new byte[4096];
                long j2 = 0;
                int i2 = 0;
                while (true) {
                    int readInt = dataInputStream2.readInt();
                    if (readInt != -1) {
                        int read = read(dataInputStream2, bufferedOutputStream, bArr, readInt);
                        while (true) {
                            j2 += (long) read;
                            if (read == readInt) {
                                break;
                            }
                            readInt -= read;
                            read = read(dataInputStream2, bufferedOutputStream, bArr, readInt);
                        }
                        if (j2 / 1000000 != ((long) i2)) {
                            i2 = (int) (j2 / 1000000);
                            Log.d("RemoteServer", String.format(Locale.ENGLISH, "receive %.1f percent", new Object[]{Float.valueOf((((float) j2) / ((float) l.longValue())) * 100.0f)}));
                        }
                    } else {
                        Log.i("RemoteServer", "receive end : " + str2, Long.valueOf(j2));
                        bufferedOutputStream.close();
                        fileOutputStream.close();
                        return buildUnique;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream.close();
                throw th;
            }
            throw th2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
    }

    private void scanFiles(ArrayList<String> arrayList, long j2) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Log.i("RemoteServer", a.f("scanFiles : ", j2), Integer.valueOf(arrayList.size()));
        MediaScanner.scanFiles((Collection<String>) arrayList, (MediaScannerListener) new e(j2, arrayList, countDownLatch));
        try {
            countDownLatch.await(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendEnd(DataOutputStream dataOutputStream, TransferHeader transferHeader) {
        dataOutputStream.writeUTF(transferHeader.command + " end");
        dataOutputStream.flush();
    }

    private void sendFile(DataOutputStream dataOutputStream, TransferHeader transferHeader) {
        MediaItem loadSecMediaId = MediaItemLoader.loadSecMediaId(transferHeader.fileId);
        if (loadSecMediaId != null && loadSecMediaId.getPath() != null) {
            Log.e("RemoteServer", "send file by stream");
            FileInputStream fileInputStream = new FileInputStream(loadSecMediaId.getPath());
            try {
                sendResponseStream(dataOutputStream, fileInputStream, new File(loadSecMediaId.getPath()).length());
                fileInputStream.close();
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    private void sendPreview(DataOutputStream dataOutputStream, TransferHeader transferHeader) {
        long j2 = transferHeader.fileId;
        MediaItem loadSecMediaId = MediaItemLoader.loadSecMediaId(j2);
        if (loadSecMediaId != null) {
            Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(loadSecMediaId.getPath(), BitmapOptionsFactory.of(loadSecMediaId).adjustInSampleSize(BitmapSizeHolder.size()));
            if (decodedBitmap != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                decodedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                sendResponse(dataOutputStream, byteArrayOutputStream.toByteArray());
            } else {
                Log.e("RemoteServer", "no bitmap : " + j2);
            }
        }
        sendEnd(dataOutputStream, transferHeader);
    }

    private void sendResponse(DataOutputStream dataOutputStream, byte[] bArr) {
        this.mDataTransfer.send(dataOutputStream, (long) bArr.length, bArr);
    }

    private void sendResponseStream(DataOutputStream dataOutputStream, FileInputStream fileInputStream, long j2) {
        this.mDataTransfer.sendStream(dataOutputStream, fileInputStream, j2);
    }

    private void sendThumbnail(DataOutputStream dataOutputStream, TransferHeader transferHeader) {
        long j2 = transferHeader.fileId;
        MediaItem loadSecMediaId = MediaItemLoader.loadSecMediaId(j2);
        Bitmap thumbnail = this.mThumbnailLoader.getThumbnail(loadSecMediaId);
        BytesBuffer bytesBuffer = new BytesBuffer();
        if (CacheManager.getInstance().get(0, CacheManager.generateKey(loadSecMediaId.getDiskCacheKey(), loadSecMediaId.getDateModified()), bytesBuffer)) {
            sendResponse(dataOutputStream, bytesBuffer.data);
            sendEnd(dataOutputStream, transferHeader);
            return;
        }
        Log.e("RemoteServer", "no cache : " + j2);
        if (thumbnail != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            sendResponse(dataOutputStream, byteArrayOutputStream.toByteArray());
        } else {
            Log.e("RemoteServer", "no bitmap : " + j2);
        }
        sendEnd(dataOutputStream, transferHeader);
    }

    public void run() {
        ConnectionWrap connectionWrap;
        Socket socket2;
        try {
            RemoteGalleryUtil.wakeUp();
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
            try {
                TransferHeader readHeader = readHeader(dataInputStream);
                if (2024011800 > readHeader.ver) {
                    String str = "Client version is low. Server = 2024011800/ Client=" + readHeader.ver;
                    Log.e("RemoteServer", str);
                    Utils.showToast(AppResources.getAppContext(), str);
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("RemoteServer", "received meta : " + readHeader.toString().replace("\n", ""));
                    if (TransferHeader.COMMANDS.UPLOAD_FILES.equals(readHeader.command)) {
                        AtomicInteger atomicInteger = new AtomicInteger();
                        ArrayList arrayList = new ArrayList();
                        long lastMaxId = getLastMaxId();
                        connectionWrap = this;
                        try {
                            readHeader.mFileData.forEach(new b(connectionWrap, dataInputStream, arrayList, atomicInteger, readHeader));
                            connectionWrap.scanFiles(arrayList, lastMaxId);
                            connectionWrap.sendEnd(dataOutputStream, readHeader);
                        } catch (IOException e7) {
                            e = e7;
                            try {
                                e.printStackTrace();
                                socket2 = connectionWrap.socket;
                                socket2.close();
                            } catch (Throwable th) {
                                th = th;
                                Throwable th2 = th;
                                try {
                                    connectionWrap.socket.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                                throw th2;
                            }
                        }
                    } else {
                        connectionWrap = this;
                        if (TransferHeader.COMMANDS.REQUEST_FILES.equals(readHeader.command)) {
                            connectionWrap.sendEnd(dataOutputStream, readHeader);
                            new Thread(new c(0, connectionWrap, readHeader)).start();
                        } else if (TransferHeader.COMMANDS.QUERY.equals(readHeader.command)) {
                            ArrayList<MediaItem> fileList = RemoteServer.getInstance().getFileList(readHeader.fileOffset, readHeader.fileCount);
                            Log.e("RemoteServer", "QUERY : get list done : " + fileList.size());
                            connectionWrap.sendResponse(dataOutputStream, GsonTool.toJsonStringForNetwork(fileList));
                            Log.e("RemoteServer", "QUERY : send done");
                            connectionWrap.sendEnd(dataOutputStream, readHeader);
                        } else if (TransferHeader.COMMANDS.THUMB.equals(readHeader.command)) {
                            connectionWrap.sendThumbnail(dataOutputStream, readHeader);
                        } else if (TransferHeader.COMMANDS.DOWNLOAD.equals(readHeader.command)) {
                            connectionWrap.sendFile(dataOutputStream, readHeader);
                        } else if (TransferHeader.COMMANDS.PREVIEW.equals(readHeader.command)) {
                            connectionWrap.sendPreview(dataOutputStream, readHeader);
                        }
                    }
                    try {
                        connectionWrap.socket.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
            } catch (JsonSyntaxException e10) {
                connectionWrap = this;
                JsonSyntaxException jsonSyntaxException = e10;
                Log.e("RemoteServer", "header parsing failed : " + jsonSyntaxException.getMessage());
                socket2 = connectionWrap.socket;
                socket2.close();
            }
        } catch (IOException e11) {
            e = e11;
            connectionWrap = this;
            e.printStackTrace();
            socket2 = connectionWrap.socket;
            socket2.close();
        } catch (Throwable th3) {
            th = th3;
            connectionWrap = this;
            Throwable th22 = th;
            connectionWrap.socket.close();
            throw th22;
        }
    }

    private void sendResponse(DataOutputStream dataOutputStream, String str) {
        this.mDataTransfer.send(dataOutputStream, str);
    }
}
