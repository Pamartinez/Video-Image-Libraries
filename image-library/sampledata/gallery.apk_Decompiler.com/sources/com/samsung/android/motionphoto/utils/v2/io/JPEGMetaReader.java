package com.samsung.android.motionphoto.utils.v2.io;

import B1.a;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MetaType;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.sum.core.SLog;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import me.n;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000e2\u00020\u00012\u00020\u0002:\u0001\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\nH\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\nH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaReader;", "Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase;", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader;", "mediaFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "boxes", "", "Lcom/samsung/android/motionphoto/utils/v2/MetaType;", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "getXMP", "getExif", "getCameraDebugInfo", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class JPEGMetaReader extends JPEGMetaBase implements ImageMetaReader {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private final Map<MetaType, ImageMetaReader.Box> boxes = new LinkedHashMap();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaReader$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(JPEGMetaReader.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JPEGMetaReader(MediaFile mediaFile) {
        super(mediaFile);
        Throwable th;
        j.e(mediaFile, "mediaFile");
        FileInputStream newInputFileStream = mediaFile.newInputFileStream();
        try {
            newInputFileStream.getChannel().position(2);
            while (true) {
                FileChannel channel = newInputFileStream.getChannel();
                j.d(channel, "getChannel(...)");
                i findNextAppNMarker = findNextAppNMarker(channel, 1, 4);
                long longValue = ((Number) findNextAppNMarker.d).longValue();
                int intValue = ((Number) findNextAppNMarker.e).intValue();
                if (longValue > 0) {
                    n meta = getMeta(newInputFileStream, (short) (intValue + 65504));
                    MetaType metaType = (MetaType) meta.d;
                    long longValue2 = ((Number) meta.e).longValue();
                    long longValue3 = ((Number) meta.f).longValue();
                    if (metaType != MetaType.META_UNKNOWN) {
                        this.boxes.put(metaType, new ImageMetaReader.Box(longValue2, longValue3, getByteReader()));
                    }
                    newInputFileStream.skipNBytes(longValue3);
                } else {
                    newInputFileStream.close();
                    return;
                }
            }
        } catch (Throwable th2) {
            a.g(newInputFileStream, th);
            throw th2;
        }
    }

    public ImageMetaReader.Box getCameraDebugInfo() {
        return this.boxes.get(MetaType.META_CAMERA_DEBUG);
    }

    public ImageMetaReader.Box getExif() {
        return this.boxes.get(MetaType.META_EXIF);
    }

    public ImageMetaReader.Box getXMP() {
        return this.boxes.get(MetaType.META_XMP);
    }
}
