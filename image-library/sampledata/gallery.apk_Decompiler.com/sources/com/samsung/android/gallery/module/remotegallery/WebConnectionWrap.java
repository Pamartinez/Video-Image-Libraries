package com.samsung.android.gallery.module.remotegallery;

import android.database.Cursor;
import android.graphics.Bitmap;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import i.C0212a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class WebConnectionWrap implements Runnable {
    public IThumbnailLoader mThumbnailLoader;
    private final Socket socket;

    public WebConnectionWrap(Socket socket2, IThumbnailLoader iThumbnailLoader) {
        this.socket = socket2;
        this.mThumbnailLoader = iThumbnailLoader;
    }

    private void sendBodyV2(DataOutputStream dataOutputStream, ArrayList<MediaItem> arrayList, int i2) {
        StringBuilder sb2 = new StringBuilder("<html>");
        sb2.append(HtmlGallery.HEADER);
        String[] strArr = {"", "", ""};
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            MediaItem mediaItem = arrayList.get(i7);
            int i8 = i7 % 2;
            mediaItem.getFileId();
            strArr[i8] = C0212a.p(new StringBuilder(), strArr[i8], HtmlGallery.ITEM.replace("__SRC__", "img/" + mediaItem.getFileId()).replace("__STYLE__", "").replace("__TITLE__", mediaItem.getDisplayName()));
        }
        sb2.append(HtmlGallery.BODY.replaceAll("__PAGE_NUM__", i2 + "").replace("__COLUMN1__", strArr[0]).replace("__COLUMN2__", strArr[1]).replace("__DEEPLINK__", RemoteServer.getInstance().getDeepLink(Utils.getWifiAddress())));
        sb2.append("</html>");
        sendResponseRaw(200, dataOutputStream, sb2.toString(), false);
    }

    public void run() {
        Cursor query;
        Cursor query2;
        try {
            RemoteGalleryUtil.wakeUp();
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            Log.d("RemoteWebServer", "run : " + this.socket);
            String readLine = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(readLine);
            String nextToken = stringTokenizer.nextToken();
            String nextToken2 = stringTokenizer.nextToken();
            while (bufferedReader.ready()) {
                Log.d("RemoteWebServer", "REQ :" + readLine);
                readLine = bufferedReader.readLine();
            }
            if (nextToken.equals("GET")) {
                if (!nextToken2.equals("/")) {
                    if (!nextToken2.startsWith("/?page=")) {
                        if (nextToken2.startsWith("/img/")) {
                            long j2 = UnsafeCast.toLong(nextToken2.replace("/img/", ""));
                            query2 = DbCompat.query(DbKey.ALL_PICTURES, new a(j2, 1));
                            if (query2 != null) {
                                if (query2.moveToFirst()) {
                                    MediaItem load = MediaItemLoader.load(query2);
                                    Bitmap thumbnail = this.mThumbnailLoader.getThumbnail(load);
                                    if (load.getOrientation() == 0) {
                                        if (load.getOrientationTag() == 2) {
                                        }
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                                        sendResponseBuffer(200, dataOutputStream, byteArray);
                                        Log.d("RemoteWebServer", "send jpg : " + j2 + "/" + byteArray.length);
                                    }
                                    thumbnail = new BitmapOperator(thumbnail).rotate(load.getOrientation(), load.getOrientationTag()).apply();
                                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream2);
                                    byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                                    sendResponseBuffer(200, dataOutputStream, byteArray2);
                                    Log.d("RemoteWebServer", "send jpg : " + j2 + "/" + byteArray2.length);
                                }
                            }
                            if (query2 != null) {
                                query2.close();
                            }
                        } else if (nextToken2.startsWith("/fullImage/")) {
                            query = DbCompat.query(DbKey.ALL_PICTURES, new a(UnsafeCast.toLong(nextToken2.replace("/fullImage/", "")), 2));
                            if (query != null) {
                                if (query.moveToFirst()) {
                                    sendResponse(200, dataOutputStream, MediaItemLoader.load(query).getPath(), true);
                                }
                            }
                            if (query != null) {
                                query.close();
                            }
                        } else if (nextToken2.startsWith("/style.css")) {
                            sendResponseBuffer(200, dataOutputStream, HtmlGallery.CSS.getBytes(StandardCharsets.UTF_8));
                        }
                    }
                }
                String replace = nextToken2.replace("/?page=", "");
                int i2 = 0;
                if (!replace.equals("/")) {
                    i2 = UnsafeCast.toInt(replace, 0);
                }
                sendBodyV2(dataOutputStream, RemoteServer.getInstance().getFileList(i2 * 20, 20), i2);
                Log.d("RemoteWebServer", "response done");
            }
            dataOutputStream.flush();
            try {
                this.socket.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            throw th;
            throw th;
        } catch (Exception e7) {
            e7.printStackTrace();
            this.socket.close();
        } catch (Throwable th) {
            try {
                this.socket.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            throw th;
        }
    }

    public void sendFile(FileInputStream fileInputStream, DataOutputStream dataOutputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                dataOutputStream.write(bArr, 0, read);
            } else {
                fileInputStream.close();
                return;
            }
        }
    }

    public void sendResponse(int i2, DataOutputStream dataOutputStream, String str, boolean z) {
        if (!z) {
            sendResponseRaw(i2, dataOutputStream, C0212a.m("<html><title>HTTP Server in java</title><body>", str, "</body></html>"), z);
        } else {
            sendResponseRaw(i2, dataOutputStream, str, z);
        }
    }

    public void sendResponseBuffer(int i2, DataOutputStream dataOutputStream, byte[] bArr) {
        String str;
        if (i2 == 200) {
            str = "HTTP/1.1 200 OK\r\n";
        } else {
            str = "HTTP/1.1 404 Not Found\r\n";
        }
        dataOutputStream.writeBytes(str);
        dataOutputStream.writeBytes("Server: Java HTTPServer");
        dataOutputStream.writeBytes("Content-Type: \r\n");
        dataOutputStream.writeBytes("Content-Length: " + bArr.length + "\r\n");
        dataOutputStream.writeBytes("Connection: close\r\n");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.write(bArr, 0, bArr.length);
        dataOutputStream.close();
    }

    public void sendResponseRaw(int i2, DataOutputStream dataOutputStream, String str, boolean z) {
        String str2;
        FileInputStream fileInputStream;
        String str3;
        if (i2 == 200) {
            str2 = "HTTP/1.1 200 OK\r\n";
        } else {
            str2 = "HTTP/1.1 404 Not Found\r\n";
        }
        String str4 = "Content-Type: text/html\r\n";
        if (z) {
            fileInputStream = new FileInputStream(str);
            str3 = "Content-Length: " + Integer.toString(fileInputStream.available()) + "\r\n";
            if (!str.endsWith(".htm") && !str.endsWith(".html")) {
                str4 = "Content-Type: \r\n";
            }
        } else {
            str3 = "Content-Length: " + str.length() + "\r\n";
            fileInputStream = null;
        }
        dataOutputStream.writeBytes(str2);
        dataOutputStream.writeBytes("Server: Java HTTPServer");
        dataOutputStream.writeBytes(str4);
        dataOutputStream.writeBytes(str3);
        dataOutputStream.writeBytes("Connection: close\r\n");
        dataOutputStream.writeBytes("\r\n");
        if (z) {
            sendFile(fileInputStream, dataOutputStream);
        } else {
            dataOutputStream.writeBytes(str);
        }
        dataOutputStream.close();
    }
}
