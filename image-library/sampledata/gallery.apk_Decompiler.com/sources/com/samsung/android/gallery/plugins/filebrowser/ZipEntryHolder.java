package com.samsung.android.gallery.plugins.filebrowser;

import A.a;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ZipEntryHolder {
    ZipData data;
    ZipEntry entry;

    public ZipEntryHolder(ZipData zipData, ZipEntry zipEntry) {
        this.data = zipData;
        this.entry = zipEntry;
    }

    public InputStream getInputStream() {
        return this.data.getInputStream(this.entry);
    }

    public String getName() {
        return this.entry.getName();
    }

    public long getSize() {
        return this.entry.getSize();
    }

    public long lastModified() {
        return this.entry.getLastModifiedTime().toMillis();
    }

    public byte[] read() {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        int read;
        InputStream inputStream2;
        try {
            inputStream2 = getInputStream();
            byte[] readBytes = FileUtils.readBytes(inputStream2);
            if (inputStream2 == null) {
                return readBytes;
            }
            inputStream2.close();
            return readBytes;
            throw th;
            throw th;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("read failed. e="), "ZipEntryHolder");
            try {
                inputStream = getInputStream();
                byteArrayOutputStream = new ByteArrayOutputStream();
                if (((long) inputStream.available()) > 209715200) {
                    byte[] bArr = new byte[16384];
                    while (((long) byteArrayOutputStream.size()) < 209715200 && (read = inputStream.read(bArr)) > 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return byteArray;
                }
                byteArrayOutputStream.close();
                inputStream.close();
                return null;
            } catch (Error | Exception e7) {
                a.z(e7, new StringBuilder("read(limit) failed. e="), "ZipEntryHolder");
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } catch (Throwable th2) {
            byteArrayOutputStream.close();
        }
    }
}
