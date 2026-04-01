package com.samsung.android.motionphoto.utils.v2.io;

import com.samsung.android.motionphoto.utils.v2.ExifInfo;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.XMPInfo;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\bf\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH&¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012H&¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0007H&¢\u0006\u0004\b\u0016\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaWriter;", "", "", "size", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "reserveXMP", "(I)Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "Lme/x;", "removeXMP", "()V", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "xmpInfo", "writeXMP", "(Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;)V", "Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;", "exifInfo", "writeExif", "(Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;)V", "", "data", "writeCameraDebugInfo", "([B)V", "removeCameraDebugInfo", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ImageMetaWriter {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaWriter$Companion;", "", "<init>", "()V", "of", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaWriter;", "mediaFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.samsung.android.motionphoto.utils.v2.MimeType[] r0 = com.samsung.android.motionphoto.utils.v2.MimeType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_JPEG     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_HEIC     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.ImageMetaWriter.Companion.WhenMappings.<clinit>():void");
            }
        }

        private Companion() {
        }

        public final ImageMetaWriter of(MediaFile mediaFile) {
            j.e(mediaFile, "mediaFile");
            int i2 = WhenMappings.$EnumSwitchMapping$0[mediaFile.getMimeType().ordinal()];
            if (i2 == 1) {
                return new JPEGMetaWriter(mediaFile);
            }
            if (i2 == 2) {
                return new HEIFMetaWriter(mediaFile);
            }
            throw new UnsupportedOperationException();
        }
    }

    void removeCameraDebugInfo();

    void removeXMP();

    ImageMetaReader.Box reserveXMP(int i2);

    void writeCameraDebugInfo(byte[] bArr);

    void writeExif(ExifInfo exifInfo);

    void writeXMP(XMPInfo xMPInfo);
}
