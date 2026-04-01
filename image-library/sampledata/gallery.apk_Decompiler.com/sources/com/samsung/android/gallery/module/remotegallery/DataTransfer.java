package com.samsung.android.gallery.module.remotegallery;

import c0.C0086a;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataTransfer {
    private String mHashCode;

    public DataTransfer(String str) {
        this.mHashCode = str;
    }

    public byte[] receiveBytes(DataInputStream dataInputStream) {
        long j2 = UnsafeCast.toLong(dataInputStream.readUTF(), 0);
        if (j2 > 2147483647L) {
            throw new IllegalStateException();
        } else if (j2 > 104857600) {
            Log.e("DataTransfer", "too big data : " + j2);
            return null;
        } else {
            byte[] bArr = new byte[((int) j2)];
            Log.i("DataTransfer", "receive start : " + j2);
            int i2 = 0;
            int i7 = 0;
            while (true) {
                int readInt = dataInputStream.readInt();
                if (readInt != -1) {
                    while (readInt > 0) {
                        int read = dataInputStream.read(bArr, i2, readInt);
                        i2 += read;
                        readInt -= read;
                    }
                    int i8 = i2 / 1000000;
                    if (i8 != i7) {
                        Log.d("DataTransfer", String.format(Locale.ENGLISH, "receive %.1f percent", new Object[]{Float.valueOf((((float) i2) / ((float) j2)) * 100.0f)}));
                        i7 = i8;
                    }
                } else {
                    Log.i("DataTransfer", C0086a.i(i2, "receive end : "), Integer.valueOf(Arrays.hashCode(bArr)));
                    return bArr;
                }
            }
        }
    }

    public boolean receiveBytesToFile(DataInputStream dataInputStream, File file) {
        boolean z;
        long j2 = UnsafeCast.toLong(dataInputStream.readUTF(), 0);
        Log.i("DataTransfer", "receive start : " + j2);
        long currentTimeMillis = System.currentTimeMillis();
        FileUtils.writeFile(file, dataInputStream, new byte[32768]);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        float f = ((float) j2) / 1000000.0f;
        Log.i("DataTransfer", "receive end : " + file.length(), String.format(Locale.ENGLISH, "received %.1fMB, speed : %.1f MB / Sec", new Object[]{Float.valueOf(f), Float.valueOf(f / (((float) currentTimeMillis2) / 1000.0f))}));
        if (file.length() == j2) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            Log.e((CharSequence) "DataTransfer", "receiveBytesToFile failed", Long.valueOf(file.length()), Long.valueOf(j2));
        }
        return z;
    }

    public String receiveString(DataInputStream dataInputStream) {
        byte[] receiveBytes = receiveBytes(dataInputStream);
        String str = new String(receiveBytes, StandardCharsets.UTF_8);
        Log.i("DataTransfer", "receiveString end : ", Integer.valueOf(str.length()), Integer.valueOf(str.hashCode()), Integer.valueOf(receiveBytes.length), Integer.valueOf(Arrays.hashCode(receiveBytes)));
        return str;
    }

    public void send(DataOutputStream dataOutputStream, String str) {
        int length = str.length();
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        Log.i("DataTransfer", C0086a.i(length, "send String start : "), Integer.valueOf(str.hashCode()), Integer.valueOf(bytes.length), Integer.valueOf(Arrays.hashCode(bytes)));
        send(dataOutputStream, (long) bytes.length, bytes);
    }

    public void sendStream(DataOutputStream dataOutputStream, FileInputStream fileInputStream, long j2) {
        Log.i("DataTransfer", "sendStream start : " + fileInputStream.available(), Long.valueOf(j2));
        dataOutputStream.writeUTF(String.valueOf(j2));
        dataOutputStream.flush();
        byte[] bArr = new byte[32768];
        long j3 = 0;
        int i2 = 0;
        while (true) {
            int read = fileInputStream.read(bArr, 0, 32768);
            if (read >= 0) {
                dataOutputStream.write(bArr, 0, read);
                j3 += (long) read;
                long j8 = j3 / 1000000;
                if (j8 != ((long) i2)) {
                    i2 = (int) j8;
                    Log.d("DataTransfer", String.format(Locale.ENGLISH, "sendStream %.1f percent", new Object[]{Float.valueOf((((float) j3) / ((float) j2)) * 100.0f)}));
                }
            } else {
                dataOutputStream.flush();
                Log.i("DataTransfer", "send end : " + j3, Integer.valueOf(Arrays.hashCode(bArr)));
                return;
            }
        }
    }

    public void send(DataOutputStream dataOutputStream, long j2, byte[] bArr) {
        dataOutputStream.writeUTF(String.valueOf(j2));
        Log.i("DataTransfer", "send start : " + j2, Integer.valueOf(Arrays.hashCode(bArr)));
        dataOutputStream.flush();
        int i2 = 0;
        long j3 = j2;
        int i7 = 0;
        while (j3 > 0) {
            int min = (int) Math.min(32768, j3);
            dataOutputStream.writeInt(min);
            dataOutputStream.write(bArr, i7, min);
            i2 += min;
            j3 -= 32768;
            i7 += 32768;
            dataOutputStream.flush();
        }
        dataOutputStream.writeInt(-1);
        dataOutputStream.flush();
        Log.i("DataTransfer", "send end : " + i2);
    }
}
