package com.samsung.android.gallery.support.library.abstraction;

import N2.j;
import java.io.FileDescriptor;
import java.io.FileInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileDataSource {
    public String filePath;
    public FileInputStream inputStream;
    public long length;
    public long offset;

    public FileDataSource(String str, long j2, long j3) {
        this.filePath = str;
        this.offset = j2;
        this.length = j3;
    }

    public FileDescriptor getFD() {
        try {
            if (this.inputStream == null) {
                this.inputStream = new FileInputStream(this.filePath);
            }
            return this.inputStream.getFD();
        } catch (Exception e) {
            j.D(e, new StringBuilder("getFD failed. e="), getClass().getSimpleName());
            return null;
        }
    }

    public void release() {
        FileInputStream fileInputStream = this.inputStream;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (Exception e) {
                j.D(e, new StringBuilder("release failed. e="), getClass().getSimpleName());
            }
            this.inputStream = null;
        }
    }
}
