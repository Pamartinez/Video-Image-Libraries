package com.samsung.android.motionphoto.utils.v2;

import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MotionPhotoEdit extends MotionPhotoInfo {
    MotionPhotoEdit addSEFData(int i2, String str, MediaFile mediaFile);

    MotionPhotoEdit addSEFData(int i2, String str, byte[] bArr);

    MotionPhotoEdit addSEFInfo(SEFInfo sEFInfo);

    MotionPhotoEdit addVideo(File file);

    MotionPhotoEdit addVideo(FileDescriptor fileDescriptor);

    MotionPhotoEdit addXMPInfo(XMPInfo xMPInfo);

    void commit();

    void commit(File file);

    void commit(FileDescriptor fileDescriptor);

    SEFEdit getSEFEdit();

    XMPEdit getXMPEdit();

    MotionPhotoEdit removeAllData();

    MotionPhotoEdit removeVideo();

    MotionPhotoEdit replaceImage(MediaBuffer mediaBuffer);

    MotionPhotoEdit replaceVideo(File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            MotionPhotoEdit replaceVideo = replaceVideo(fileInputStream.getFD());
            fileInputStream.close();
            return replaceVideo;
        } catch (IOException e) {
            SLog.w("MotionPhotoEdit", "fail to replaceVideo: " + e);
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    MotionPhotoEdit replaceVideo(FileDescriptor fileDescriptor);

    MotionPhotoEdit setCaptureTimestampUs(long j2);
}
