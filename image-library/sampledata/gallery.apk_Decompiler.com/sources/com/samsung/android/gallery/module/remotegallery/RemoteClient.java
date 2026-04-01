package com.samsung.android.gallery.module.remotegallery;

import A.a;
import A4.C0368c;
import A4.C0369d;
import A4.M;
import D3.j;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.text.TextUtils;
import ba.C0582a;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.remotegallery.TransferHeader;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteClient {
    private static final ConcurrentHashMap<String, String> sServerInfo = new ConcurrentHashMap<>();
    private DataInputStream fromServer;
    private DataTransfer mDataTransfer;
    private String mHash;
    private String server_address;
    private Socket socket;
    private DataOutputStream toServer;

    public RemoteClient(String str) {
        this.server_address = str;
        String str2 = sServerInfo.get(str);
        if (str2 != null) {
            this.mHash = str2;
            this.mDataTransfer = new DataTransfer(str2);
            Log.i("RemoteClient", "set server ip : " + str);
            return;
        }
        a.u("fail set server : ", str, "RemoteClient");
    }

    private ArrayList<MediaItem> getFileList(int i2) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Cursor query = DbCompat.query(DbKey.ALL_PICTURES, new C0369d(i2, 17));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemLoader.load(query));
                    } while (query.moveToNext());
                    query.close();
                    return arrayList;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        Log.i("RemoteClient", "fail get file");
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$download$4(long j2, SecureFile secureFile, AtomicBoolean atomicBoolean) {
        try {
            TransferHeader transferHeader = new TransferHeader();
            transferHeader.command = TransferHeader.COMMANDS.DOWNLOAD;
            transferHeader.fileId = j2;
            sendHeader(transferHeader);
            atomicBoolean.set(this.mDataTransfer.receiveBytesToFile(this.fromServer, secureFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getPreview$2(long j2, AtomicReference atomicReference) {
        try {
            TransferHeader transferHeader = new TransferHeader();
            transferHeader.command = TransferHeader.COMMANDS.PREVIEW;
            transferHeader.fileId = j2;
            sendHeader(transferHeader);
            atomicReference.set(this.mDataTransfer.receiveBytes(this.fromServer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getThumb$3(long j2, AtomicReference atomicReference) {
        try {
            TransferHeader transferHeader = new TransferHeader();
            transferHeader.command = TransferHeader.COMMANDS.THUMB;
            transferHeader.fileId = j2;
            sendHeader(transferHeader);
            atomicReference.set(this.mDataTransfer.receiveBytes(this.fromServer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$query$6(int i2, int i7, ArrayList arrayList) {
        String receiveString;
        try {
            TransferHeader transferHeader = new TransferHeader();
            transferHeader.command = TransferHeader.COMMANDS.QUERY;
            transferHeader.fileOffset = i2;
            transferHeader.fileCount = i7;
            sendHeader(transferHeader);
            receiveString = this.mDataTransfer.receiveString(this.fromServer);
            Log.d("RemoteClient", "query done : " + receiveString.length());
            ArrayList arrayList2 = (ArrayList) GsonTool.fromJsonString(new TypeToken<ArrayList<MediaItem>>() {
            }, receiveString);
            arrayList.clear();
            if (arrayList2 != null) {
                arrayList.addAll(arrayList2);
            }
            Log.d("RemoteClient", "query done : " + arrayList.size());
        } catch (JsonSyntaxException e) {
            Log.e("RemoteClient", "ALL : ".concat(receiveString));
            throw e;
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendFiles$0(MediaItem[] mediaItemArr) {
        try {
            ArrayList arrayList = new ArrayList();
            Collections.addAll(arrayList, mediaItemArr);
            if (arrayList.isEmpty()) {
                Log.e("RemoteClient", "empty");
            } else if (arrayList.size() <= 100) {
                uploadFiles(this.toServer, arrayList);
            } else {
                ArrayList arrayList2 = new ArrayList();
                int i2 = 1;
                do {
                    arrayList2.add((MediaItem) arrayList.remove(0));
                    if (arrayList2.size() == 100 || arrayList.isEmpty()) {
                        Log.i("RemoteClient", "sendFiles sub " + i2);
                        uploadFiles(this.toServer, arrayList2);
                        arrayList2.clear();
                        i2++;
                    }
                } while (!arrayList.isEmpty());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendFiles$1(int i2) {
        try {
            ArrayList<MediaItem> fileList = getFileList(i2);
            if (fileList.isEmpty()) {
                Log.e("RemoteClient", "empty");
            } else if (fileList.size() <= 500) {
                uploadFiles(this.toServer, fileList);
            } else {
                ArrayList arrayList = new ArrayList();
                int i7 = 1;
                do {
                    arrayList.add(fileList.remove(0));
                    if (arrayList.size() == 500 || fileList.isEmpty()) {
                        Log.i("RemoteClient", "sendFiles sub " + i7);
                        uploadFiles(this.toServer, arrayList);
                        arrayList.clear();
                        i7++;
                    }
                } while (!fileList.isEmpty());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Socket openSocket() {
        Socket socket2 = new Socket();
        socket2.connect(new InetSocketAddress(this.server_address, 5999), 5000);
        return socket2;
    }

    private void prepare(String str) {
        Log.i("RemoteClient", "start client for " + str);
        this.socket = openSocket();
        Log.i("RemoteClient", "open socket : " + this.server_address);
        this.fromServer = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
        this.toServer = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
    }

    private void release() {
        try {
            Socket socket2 = this.socket;
            if (socket2 != null && !socket2.isClosed()) {
                Socket socket3 = this.socket;
                Log.i("RemoteClient", "release E", socket3, Boolean.valueOf(socket3.isBound()), Boolean.valueOf(this.socket.isClosed()), Boolean.valueOf(this.socket.isConnected()));
                this.socket.close();
                Socket socket4 = this.socket;
                Log.i("RemoteClient", "release X", socket4, Boolean.valueOf(socket4.isBound()), Boolean.valueOf(this.socket.isClosed()), Boolean.valueOf(this.socket.isConnected()));
                this.socket = null;
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: sendFile */
    public boolean lambda$uploadFiles$8(DataOutputStream dataOutputStream, MediaItem mediaItem) {
        BufferedInputStream bufferedInputStream;
        String path = mediaItem.getPath();
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                Log.i("RemoteClient", "prepare file : " + mediaItem);
                Log.i("RemoteClient", "file send : " + file.getName());
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read != -1) {
                        dataOutputStream.writeInt(read);
                        dataOutputStream.write(bArr, 0, read);
                        dataOutputStream.flush();
                    } else {
                        dataOutputStream.writeInt(-1);
                        dataOutputStream.flush();
                        bufferedInputStream.close();
                        Log.i("RemoteClient", "file send done : " + file.getName());
                        bufferedInputStream.close();
                        fileInputStream.close();
                        return true;
                    }
                }
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private void sendHeader(TransferHeader transferHeader) {
        this.mDataTransfer.send(this.toServer, GsonTool.toJsonString(transferHeader));
    }

    public static void setServerInfo(String str, String str2) {
        sServerInfo.put(str, str2);
    }

    private void terminate() {
        String readUTF = this.fromServer.readUTF();
        Log.i("RemoteClient", "from server : " + readUTF);
    }

    private void uploadFiles(DataOutputStream dataOutputStream, ArrayList<MediaItem> arrayList) {
        TransferHeader transferHeader = new TransferHeader();
        transferHeader.command = TransferHeader.COMMANDS.UPLOAD_FILES;
        transferHeader.fileCount = arrayList.size();
        arrayList.forEach(new C0582a(10, transferHeader));
        sendHeader(transferHeader);
        arrayList.forEach(new h(this, dataOutputStream));
    }

    public boolean download(long j2, SecureFile secureFile) {
        ThreadUtil.assertBgThread("download");
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        execStreamDownload(a.f("download:", j2), new j(this, j2, secureFile, atomicBoolean));
        return atomicBoolean.get();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0050 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void exec(java.lang.String r6, java.lang.Runnable r7) {
        /*
            r5 = this;
            java.lang.String r0 = "RemoteClient"
            java.lang.String r1 = "exec fail : "
            java.lang.String r2 = "exec done for "
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            r5.prepare(r6)     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            r7.run()     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            r5.terminate()     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            r7.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            r7.append(r6)     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            java.lang.String r6 = r7.toString()     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            java.lang.String r7 = com.samsung.android.gallery.support.utils.Logger.vt((long) r3)     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            java.lang.Object[] r7 = new java.lang.Object[]{r7}     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            com.samsung.android.gallery.support.utils.Log.i(r0, r6, r7)     // Catch:{ SocketTimeoutException -> 0x0050, Exception -> 0x0030 }
            r5.release()
            return
        L_0x002e:
            r6 = move-exception
            goto L_0x005b
        L_0x0030:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x002e }
            r7.<init>(r1)     // Catch:{ all -> 0x002e }
            r7.append(r6)     // Catch:{ all -> 0x002e }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x002e }
            com.samsung.android.gallery.support.utils.Log.e(r0, r7)     // Catch:{ all -> 0x002e }
            r6.printStackTrace()     // Catch:{ all -> 0x002e }
            android.content.Context r6 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ all -> 0x002e }
            java.lang.String r7 = "connection error"
            com.samsung.android.gallery.support.utils.Utils.showToast((android.content.Context) r6, (java.lang.String) r7)     // Catch:{ all -> 0x002e }
        L_0x004c:
            r5.release()
            goto L_0x005a
        L_0x0050:
            android.content.Context r6 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ all -> 0x002e }
            java.lang.String r7 = "connection time out"
            com.samsung.android.gallery.support.utils.Utils.showToast((android.content.Context) r6, (java.lang.String) r7)     // Catch:{ all -> 0x002e }
            goto L_0x004c
        L_0x005a:
            return
        L_0x005b:
            r5.release()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.remotegallery.RemoteClient.exec(java.lang.String, java.lang.Runnable):void");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execStreamDownload(java.lang.String r6, java.lang.Runnable r7) {
        /*
            r5 = this;
            java.lang.String r0 = "RemoteClient"
            java.lang.String r1 = "exec fail : "
            java.lang.String r2 = "exec done for "
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            r5.prepare(r6)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            r7.run()     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            r7.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            r7.append(r6)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            java.lang.String r6 = r7.toString()     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            java.lang.String r7 = com.samsung.android.gallery.support.utils.Logger.vt((long) r3)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            java.lang.Object[] r7 = new java.lang.Object[]{r7}     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            com.samsung.android.gallery.support.utils.Log.i(r0, r6, r7)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x002d }
            r5.release()
            return
        L_0x002b:
            r6 = move-exception
            goto L_0x0058
        L_0x002d:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x002b }
            r7.<init>(r1)     // Catch:{ all -> 0x002b }
            r7.append(r6)     // Catch:{ all -> 0x002b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x002b }
            com.samsung.android.gallery.support.utils.Log.e(r0, r7)     // Catch:{ all -> 0x002b }
            r6.printStackTrace()     // Catch:{ all -> 0x002b }
            android.content.Context r6 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ all -> 0x002b }
            java.lang.String r7 = "connection error"
            com.samsung.android.gallery.support.utils.Utils.showToast((android.content.Context) r6, (java.lang.String) r7)     // Catch:{ all -> 0x002b }
        L_0x0049:
            r5.release()
            goto L_0x0057
        L_0x004d:
            android.content.Context r6 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ all -> 0x002b }
            java.lang.String r7 = "connection time out"
            com.samsung.android.gallery.support.utils.Utils.showToast((android.content.Context) r6, (java.lang.String) r7)     // Catch:{ all -> 0x002b }
            goto L_0x0049
        L_0x0057:
            return
        L_0x0058:
            r5.release()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.remotegallery.RemoteClient.execStreamDownload(java.lang.String, java.lang.Runnable):void");
    }

    public Bitmap getPreview(long j2) {
        AtomicReference atomicReference = new AtomicReference();
        exec(a.f("getPreview:", j2), new g(this, j2, atomicReference, 0));
        byte[] bArr = (byte[]) atomicReference.get();
        if (bArr != null && bArr.length > 0) {
            return ImageDecoder.decodeByteArray(bArr, 0, bArr.length, new BitmapOptions());
        }
        Log.e("RemoteClient", "get preview Fail");
        return null;
    }

    public byte[] getThumb(long j2) {
        AtomicReference atomicReference = new AtomicReference();
        exec(a.f("getThumb:", j2), new g(this, j2, atomicReference, 1));
        return (byte[]) atomicReference.get();
    }

    public void query(ArrayList<MediaItem> arrayList, int i2, int i7) {
        exec(Contract.QUERY, new M((Object) this, i2, i7, (Object) arrayList, 8));
    }

    public void sendFiles(MediaItem[] mediaItemArr) {
        exec("sendFiles", new c(1, this, mediaItemArr));
    }

    public void sendFiles(int i2) {
        exec("sendFiles", new C0368c(this, i2, 23));
    }
}
