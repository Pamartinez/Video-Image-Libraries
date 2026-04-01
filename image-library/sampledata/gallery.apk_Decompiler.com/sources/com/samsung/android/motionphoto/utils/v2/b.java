package com.samsung.android.motionphoto.utils.v2;

import java.nio.channels.FileChannel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Ae.b {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaFile e;

    public /* synthetic */ b(int i2, MediaFile mediaFile) {
        this.d = i2;
        this.e = mediaFile;
    }

    public final Object invoke(Object obj) {
        int i2 = this.d;
        MediaFile mediaFile = this.e;
        FileChannel fileChannel = (FileChannel) obj;
        switch (i2) {
            case 0:
                return MediaFile.mimeType_delegate$lambda$5$lambda$4(mediaFile, fileChannel);
            default:
                return MotionPhotoEditImpl.addMPVDBox$lambda$20(mediaFile, fileChannel);
        }
    }
}
