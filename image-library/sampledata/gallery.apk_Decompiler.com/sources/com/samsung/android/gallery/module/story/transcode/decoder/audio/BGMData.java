package com.samsung.android.gallery.module.story.transcode.decoder.audio;

import java.io.FileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BGMData {
    private final int mDuration;
    private final FileDescriptor mFileDescriptor;
    private final String mFileName;
    private final String mFilePath;

    public BGMData(FileDescriptor fileDescriptor, String str, String str2, int i2) {
        this.mFileName = str2;
        this.mFileDescriptor = fileDescriptor;
        this.mFilePath = str;
        this.mDuration = i2;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public FileDescriptor getFileDescriptor() {
        return this.mFileDescriptor;
    }
}
