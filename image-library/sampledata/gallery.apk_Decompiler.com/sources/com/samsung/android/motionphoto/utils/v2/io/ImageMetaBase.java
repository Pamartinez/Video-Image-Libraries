package com.samsung.android.motionphoto.utils.v2.io;

import com.samsung.android.motionphoto.utils.v2.MediaFile;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaBase;", "", "mediaFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "byteReader", "Lcom/samsung/android/motionphoto/utils/v2/io/MediaByteReader;", "getByteReader", "()Lcom/samsung/android/motionphoto/utils/v2/io/MediaByteReader;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageMetaBase {
    private final MediaByteReader byteReader;

    public ImageMetaBase(MediaFile mediaFile) {
        j.e(mediaFile, "mediaFile");
        this.byteReader = new ImageMetaBase$byteReader$1(mediaFile);
    }

    public final MediaByteReader getByteReader() {
        return this.byteReader;
    }
}
