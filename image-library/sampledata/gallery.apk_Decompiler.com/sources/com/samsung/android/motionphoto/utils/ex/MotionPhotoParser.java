package com.samsung.android.motionphoto.utils.ex;

import N2.j;
import Tf.a;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.scsp.common.Header;
import i.C0212a;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 A2\u00020\u0001:\u0004BCDAB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0004\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u0011¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010\"\u001a\u00020\u001f¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u0004\u0018\u00010#¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u0004\u0018\u00010#¢\u0006\u0004\b(\u0010'J\u0017\u0010)\u001a\u0004\u0018\u00010#2\u0006\u0010\"\u001a\u00020\u001f¢\u0006\u0004\b)\u0010%J\u000f\u0010*\u001a\u0004\u0018\u00010#¢\u0006\u0004\b*\u0010'J\u000f\u0010+\u001a\u0004\u0018\u00010#¢\u0006\u0004\b+\u0010'J\r\u0010,\u001a\u00020\u0011¢\u0006\u0004\b,\u0010-J\r\u0010.\u001a\u00020\u0011¢\u0006\u0004\b.\u0010-R\u0014\u0010/\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b6\u00105R(\u00109\u001a\b\u0012\u0004\u0012\u000208078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0011\u0010@\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b?\u0010-¨\u0006E"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser;", "", "Ljava/io/FileInputStream;", "inStream", "<init>", "(Ljava/io/FileInputStream;)V", "", "filePath", "(Ljava/lang/String;)V", "Ljava/io/File;", "file", "(Ljava/io/File;)V", "Ljava/io/FileDescriptor;", "fd", "(Ljava/io/FileDescriptor;)V", "", "n", "", "read", "(I)J", "order", "(II)J", "readString", "(I)Ljava/lang/String;", "", "readBuffer", "(I)[B", "Lme/x;", "close", "()V", "videoSize", "", "readJpegSefTail", "(J)Z", "isJpeg", "Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$DataPosition64;", "parseSEFTail", "(Z)Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$DataPosition64;", "parseJpegSefTail", "()Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$DataPosition64;", "parseHeicSefTail", "parseAutoPlaySefTail", "parseJpegAutoPlaySefTail", "parseHeicAutoPlaySefTail", "getSEFStartOffset", "()J", "getSEFTailStartSignatureOffset", "TAG", "Ljava/lang/String;", "Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SefFile$SefFileStream;", "f", "Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SefFile$SefFileStream;", "sefStartOffset", "J", "sefTailStartSignatureOffset", "Ljava/util/concurrent/LinkedBlockingQueue;", "Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SEFTailData;", "sefTailDataQueue", "Ljava/util/concurrent/LinkedBlockingQueue;", "getSefTailDataQueue", "()Ljava/util/concurrent/LinkedBlockingQueue;", "setSefTailDataQueue", "(Ljava/util/concurrent/LinkedBlockingQueue;)V", "getFileSize", "fileSize", "Companion", "SEFTailData", "SefFile", "DataPosition64", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionPhotoParser {
    public static final String CAMERA_CAPTURE_MODE_INFO_KEY_NAME = "Camera_Capture_Mode_Info";
    public static final int CAMERA_CAPTURE_MODE_INFO_TYPE = 3169;
    public static final String COLOR_DISPLAY_P3_KEY_NAME = "Color_Display_P3";
    public static final int COLOR_DISPLAY_P3_TYPE = 3265;
    public static final Companion Companion = new Companion((e) null);
    public static final String IMAGE_UTC_DATA_KEY_NAME = "Image_UTC_Data";
    public static final int IMAGE_UTC_DATA_TYPE = 2561;
    public static final int MAX_SEF_DATA_COUNT = 100;
    public static final int MOTION_PHOTO_AUTOPLAY_DATA_TYPE = 2611;
    public static final String MOTION_PHOTO_AUTOPLAY_KEY_NAME = "MotionPhoto_AutoPlay";
    public static final String MOTION_PHOTO_DATA_KEY_NAME = "MotionPhoto_Data";
    public static final int MOTION_PHOTO_DATA_TYPE = 2608;
    public static final int MOTION_PHOTO_INFO_DATA_TYPE = 2610;
    public static final String MOTION_PHOTO_INFO_KEY_NAME = "MotionPhoto_Info";
    public static final int MOTION_PHOTO_VERSION_DATA_TYPE = 2609;
    public static final String MOTION_PHOTO_VERSION_KEY_NAME = "MotionPhoto_Version";
    private final String TAG;
    private final SefFile.SefFileStream f;
    private long sefStartOffset;
    private LinkedBlockingQueue<SEFTailData> sefTailDataQueue;
    private long sefTailStartSignatureOffset;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$Companion;", "", "<init>", "()V", "MOTION_PHOTO_DATA_TYPE", "", "MOTION_PHOTO_DATA_KEY_NAME", "", "MOTION_PHOTO_VERSION_DATA_TYPE", "MOTION_PHOTO_VERSION_KEY_NAME", "MOTION_PHOTO_INFO_DATA_TYPE", "MOTION_PHOTO_INFO_KEY_NAME", "MOTION_PHOTO_AUTOPLAY_DATA_TYPE", "MOTION_PHOTO_AUTOPLAY_KEY_NAME", "IMAGE_UTC_DATA_TYPE", "IMAGE_UTC_DATA_KEY_NAME", "COLOR_DISPLAY_P3_TYPE", "COLOR_DISPLAY_P3_KEY_NAME", "CAMERA_CAPTURE_MODE_INFO_TYPE", "CAMERA_CAPTURE_MODE_INFO_KEY_NAME", "MAX_SEF_DATA_COUNT", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$DataPosition64;", "", "offset", "", "length", "isMPV2", "", "offsetPos", "lengthPos", "<init>", "(JJIJJ)V", "getOffset", "()J", "getLength", "()I", "getOffsetPos", "getLengthPos", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DataPosition64 {
        private final int isMPV2;
        private final long length;
        private final long lengthPos;
        private final long offset;
        private final long offsetPos;

        public DataPosition64(long j2, long j3, int i2, long j8, long j10) {
            this.offset = j2;
            this.length = j3;
            this.isMPV2 = i2;
            this.offsetPos = j8;
            this.lengthPos = j10;
        }

        public static /* synthetic */ DataPosition64 copy$default(DataPosition64 dataPosition64, long j2, long j3, int i2, long j8, long j10, int i7, Object obj) {
            long j11;
            long j12;
            if ((i7 & 1) != 0) {
                j2 = dataPosition64.offset;
            }
            long j13 = j2;
            if ((i7 & 2) != 0) {
                j3 = dataPosition64.length;
            }
            long j14 = j3;
            if ((i7 & 4) != 0) {
                i2 = dataPosition64.isMPV2;
            }
            int i8 = i2;
            if ((i7 & 8) != 0) {
                j11 = dataPosition64.offsetPos;
            } else {
                j11 = j8;
            }
            if ((i7 & 16) != 0) {
                j12 = dataPosition64.lengthPos;
            } else {
                j12 = j10;
            }
            return dataPosition64.copy(j13, j14, i8, j11, j12);
        }

        public final long component1() {
            return this.offset;
        }

        public final long component2() {
            return this.length;
        }

        public final int component3() {
            return this.isMPV2;
        }

        public final long component4() {
            return this.offsetPos;
        }

        public final long component5() {
            return this.lengthPos;
        }

        public final DataPosition64 copy(long j2, long j3, int i2, long j8, long j10) {
            return new DataPosition64(j2, j3, i2, j8, j10);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DataPosition64)) {
                return false;
            }
            DataPosition64 dataPosition64 = (DataPosition64) obj;
            if (this.offset == dataPosition64.offset && this.length == dataPosition64.length && this.isMPV2 == dataPosition64.isMPV2 && this.offsetPos == dataPosition64.offsetPos && this.lengthPos == dataPosition64.lengthPos) {
                return true;
            }
            return false;
        }

        public final long getLength() {
            return this.length;
        }

        public final long getLengthPos() {
            return this.lengthPos;
        }

        public final long getOffset() {
            return this.offset;
        }

        public final long getOffsetPos() {
            return this.offsetPos;
        }

        public int hashCode() {
            return Long.hashCode(this.lengthPos) + C0212a.c(C0212a.b(this.isMPV2, C0212a.c(Long.hashCode(this.offset) * 31, 31, this.length), 31), 31, this.offsetPos);
        }

        public final int isMPV2() {
            return this.isMPV2;
        }

        public String toString() {
            long j2 = this.offset;
            long j3 = this.length;
            int i2 = this.isMPV2;
            long j8 = this.offsetPos;
            long j10 = this.lengthPos;
            StringBuilder j11 = j.j(j2, "DataPosition64(offset=", ", length=");
            j11.append(j3);
            j11.append(", isMPV2=");
            j11.append(i2);
            j11.append(", offsetPos=");
            j11.append(j8);
            j11.append(", lengthPos=");
            return C0212a.o(j11, j10, ")");
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001:\u0002\u0017\u0018J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\u000e\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH&¢\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH&¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0002H&¢\u0006\u0004\b\u0014\u0010\bJ\u000f\u0010\u0015\u001a\u00020\u0004H&¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SefFile;", "", "", "l", "Lme/x;", "seek", "(J)V", "position", "()J", "", "bytes", "", "offset", "length", "read", "([BII)V", "Ljava/nio/ByteBuffer;", "buffer", "write", "(Ljava/nio/ByteBuffer;II)V", "size", "close", "()V", "SefRandomAccessFile", "SefFileStream", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SefFile {

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0018\u0010\fJ\u000f\u0010\u0019\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u0005¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SefFile$SefFileStream;", "Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SefFile;", "Ljava/io/FileInputStream;", "stream", "<init>", "(Ljava/io/FileInputStream;)V", "", "l", "Lme/x;", "seek", "(J)V", "position", "()J", "", "bytes", "", "offset", "length", "read", "([BII)V", "Ljava/nio/ByteBuffer;", "buffer", "write", "(Ljava/nio/ByteBuffer;II)V", "size", "close", "()V", "Ljava/io/FileInputStream;", "getStream", "()Ljava/io/FileInputStream;", "setStream", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class SefFileStream implements SefFile {
            private FileInputStream stream;

            public SefFileStream(FileInputStream fileInputStream) {
                kotlin.jvm.internal.j.e(fileInputStream, "stream");
                this.stream = fileInputStream;
            }

            public void close() {
                this.stream.getChannel().close();
            }

            public final FileInputStream getStream() {
                return this.stream;
            }

            public long position() {
                return this.stream.getChannel().position();
            }

            public void read(byte[] bArr, int i2, int i7) {
                kotlin.jvm.internal.j.e(bArr, Header.BYTES);
                int i8 = i7 + i2;
                ByteBuffer allocate = ByteBuffer.allocate(i8);
                this.stream.getChannel().read(allocate);
                for (int i10 = 0; i10 < i8; i10++) {
                    bArr[i10] = allocate.get(i2 + i10);
                }
            }

            public void seek(long j2) {
                this.stream.getChannel().position(j2);
            }

            public final void setStream(FileInputStream fileInputStream) {
                kotlin.jvm.internal.j.e(fileInputStream, "<set-?>");
                this.stream = fileInputStream;
            }

            public long size() {
                return this.stream.getChannel().size();
            }

            public void write(ByteBuffer byteBuffer, int i2, int i7) {
                kotlin.jvm.internal.j.e(byteBuffer, "buffer");
                this.stream.getChannel().write(new ByteBuffer[]{byteBuffer});
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0018\u0010\fJ\u000f\u0010\u0019\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u0005¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SefFile$SefRandomAccessFile;", "Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SefFile;", "Ljava/io/RandomAccessFile;", "raf", "<init>", "(Ljava/io/RandomAccessFile;)V", "", "l", "Lme/x;", "seek", "(J)V", "position", "()J", "", "bytes", "", "offset", "length", "read", "([BII)V", "Ljava/nio/ByteBuffer;", "buffer", "write", "(Ljava/nio/ByteBuffer;II)V", "size", "close", "()V", "Ljava/io/RandomAccessFile;", "getRaf", "()Ljava/io/RandomAccessFile;", "setRaf", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class SefRandomAccessFile implements SefFile {
            private RandomAccessFile raf;

            public SefRandomAccessFile(RandomAccessFile randomAccessFile) {
                kotlin.jvm.internal.j.e(randomAccessFile, "raf");
                this.raf = randomAccessFile;
            }

            public void close() {
                this.raf.getChannel().close();
            }

            public final RandomAccessFile getRaf() {
                return this.raf;
            }

            public long position() {
                return this.raf.getChannel().position();
            }

            public void read(byte[] bArr, int i2, int i7) {
                kotlin.jvm.internal.j.e(bArr, Header.BYTES);
                int i8 = i7 + i2;
                ByteBuffer allocate = ByteBuffer.allocate(i8);
                this.raf.getChannel().read(allocate);
                for (int i10 = 0; i10 < i8; i10++) {
                    bArr[i10] = allocate.get(i2 + i10);
                }
            }

            public void seek(long j2) {
                this.raf.seek(j2);
            }

            public final void setRaf(RandomAccessFile randomAccessFile) {
                kotlin.jvm.internal.j.e(randomAccessFile, "<set-?>");
                this.raf = randomAccessFile;
            }

            public long size() {
                return this.raf.getChannel().size();
            }

            public void write(ByteBuffer byteBuffer, int i2, int i7) {
                kotlin.jvm.internal.j.e(byteBuffer, "buffer");
                this.raf.getChannel().write(new ByteBuffer[]{byteBuffer});
            }
        }

        void close();

        long position();

        void read(byte[] bArr, int i2, int i7);

        void seek(long j2);

        long size();

        void write(ByteBuffer byteBuffer, int i2, int i7);
    }

    public MotionPhotoParser(FileInputStream fileInputStream) {
        kotlin.jvm.internal.j.e(fileInputStream, "inStream");
        this.TAG = "MotionPhotoParser";
        this.f = new SefFile.SefFileStream(fileInputStream);
        this.sefTailDataQueue = new LinkedBlockingQueue<>();
    }

    private final long read(int i2) {
        byte[] readBuffer = readBuffer(i2);
        long j2 = 0;
        for (int i7 = i2 - 1; -1 < i7; i7--) {
            j2 = (j2 << 8) | ((long) (readBuffer[i7] & 255));
        }
        return j2;
    }

    private final byte[] readBuffer(int i2) {
        if (i2 < 1 || i2 > 31) {
            throw new IndexOutOfBoundsException("support size in range");
        }
        byte[] bArr = new byte[(i2 + 1)];
        this.f.read(bArr, 0, i2);
        return bArr;
    }

    private final String readString(int i2) {
        return new String(readBuffer(i2), 0, i2, a.f3815a);
    }

    public final void close() {
        Log.d(this.TAG, "close parser");
        this.f.close();
    }

    public final long getFileSize() {
        return this.f.size();
    }

    public final long getSEFStartOffset() {
        return this.sefStartOffset;
    }

    public final long getSEFTailStartSignatureOffset() {
        return this.sefTailStartSignatureOffset;
    }

    public final LinkedBlockingQueue<SEFTailData> getSefTailDataQueue() {
        return this.sefTailDataQueue;
    }

    public final DataPosition64 parseAutoPlaySefTail(boolean z) {
        if (z) {
            return parseJpegAutoPlaySefTail();
        }
        return parseHeicAutoPlaySefTail();
    }

    public final DataPosition64 parseHeicAutoPlaySefTail() {
        int i2 = 4;
        long j2 = (long) 4;
        long size = this.f.size() - j2;
        this.f.seek(size);
        DataPosition64 dataPosition64 = null;
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_SIGNATURE)) {
            Log.w(this.TAG, "no SEFT");
            this.f.close();
            return null;
        }
        long j3 = size - j2;
        this.f.seek(j3);
        long read = j3 - read(4);
        this.f.seek(read);
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_START_SIGNATURE)) {
            Log.w(this.TAG, "no SEFH");
            this.f.close();
            return null;
        }
        read(4);
        long j8 = read + j2 + j2;
        long read2 = read(4);
        if (read2 > 100) {
            String str = this.TAG;
            Log.w(str, "invalid sef data count:" + read2);
            this.f.close();
            return null;
        }
        long j10 = j8 + j2;
        long j11 = 0;
        while (j11 < read2) {
            read(2);
            int read3 = (int) read(2);
            long j12 = j10 + j2;
            long read4 = read(i2);
            long j13 = j12 + j2;
            long read5 = read(i2);
            long j14 = j13 + j2;
            DataPosition64 dataPosition642 = dataPosition64;
            C0086a.C(read3, "found sef data: ", this.TAG);
            if (read3 == 2611) {
                this.f.seek(read - read4);
                read(2);
                int read6 = (int) read(2);
                if (read3 != read6) {
                    Log.w(this.TAG, A.a.d(read3, read6, "tail data type[", "] and data type[", "] are different!"));
                    this.f.close();
                    return dataPosition642;
                }
                int read7 = (int) read(4);
                String readString = readString(read7);
                if (!kotlin.jvm.internal.j.a(readString, "MotionPhoto_AutoPlay")) {
                    String str2 = this.TAG;
                    Log.w(str2, "invalid key name[" + readString + "]");
                    this.f.close();
                    return dataPosition642;
                }
                return new DataPosition64(this.f.position(), read5 - ((long) (8 + read7)), 0, j12, j13);
            }
            i2 = 4;
            j11++;
            j10 = j14;
            dataPosition64 = dataPosition642;
        }
        return dataPosition64;
    }

    public final DataPosition64 parseHeicSefTail() {
        long j2 = (long) 4;
        long size = this.f.size() - j2;
        this.f.seek(size);
        DataPosition64 dataPosition64 = null;
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_SIGNATURE)) {
            Log.w(this.TAG, "no SEFT");
            this.f.close();
            return null;
        }
        long j3 = size - j2;
        this.f.seek(j3);
        long read = j3 - read(4);
        this.sefTailStartSignatureOffset = read;
        this.f.seek(read);
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_START_SIGNATURE)) {
            Log.w(this.TAG, "no SEFH");
            this.f.close();
            return null;
        }
        read(4);
        long read2 = read(4);
        if (read2 > 100) {
            String str = this.TAG;
            Log.w(str, "invalid sef data count:" + read2);
            this.f.close();
            return null;
        }
        long j8 = 0;
        while (j8 < read2) {
            read(2);
            int read3 = (int) read(2);
            long read4 = read(4);
            read(4);
            DataPosition64 dataPosition642 = dataPosition64;
            C0086a.C(read3, "found sef data: ", this.TAG);
            if (read3 == 2561) {
                this.sefStartOffset = read - read4;
            } else if (read3 == 2608) {
                this.f.seek(read - read4);
                read(2);
                int read5 = (int) read(2);
                if (read3 != read5) {
                    Log.w(this.TAG, A.a.d(read3, read5, "tail data type[", "] and data type[", "] are different!"));
                    this.f.close();
                    return dataPosition642;
                }
                String readString = readString((int) read(4));
                if (!kotlin.jvm.internal.j.a(readString, "MotionPhoto_Data")) {
                    String str2 = this.TAG;
                    Log.w(str2, "invalid key name[" + readString + "]");
                    this.f.close();
                    return dataPosition642;
                }
                long position = this.f.position();
                if (kotlin.jvm.internal.j.a(readString(4), "mpv2")) {
                    return new DataPosition64(read(4, 1), read(4, 1), 3, position + j2, position + ((long) 8));
                }
                Log.i(this.TAG, "found Motionphoto, it is not mpv2");
                return new DataPosition64(position, read - position, 2, position, position + j2);
            }
            j8++;
            dataPosition64 = dataPosition642;
        }
        return dataPosition64;
    }

    public final DataPosition64 parseJpegAutoPlaySefTail() {
        int i2 = 4;
        long j2 = (long) 4;
        long size = this.f.size() - j2;
        this.f.seek(size);
        DataPosition64 dataPosition64 = null;
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_SIGNATURE)) {
            Log.w(this.TAG, "no SEFT");
            this.f.close();
            return null;
        }
        long j3 = size - j2;
        this.f.seek(j3);
        long read = j3 - read(4);
        this.f.seek(read);
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_START_SIGNATURE)) {
            Log.w(this.TAG, "no SEFH");
            this.f.close();
            return null;
        }
        read(4);
        long j8 = read + j2 + j2;
        long read2 = read(4);
        if (read2 > 100) {
            String str = this.TAG;
            Log.w(str, "invalid sef data count:" + read2);
            this.f.close();
            return null;
        }
        long j10 = j8 + j2;
        long j11 = 0;
        while (j11 < read2) {
            read(2);
            int read3 = (int) read(2);
            long j12 = j10 + j2;
            long read4 = read(i2);
            long j13 = j12 + j2;
            long read5 = read(i2);
            long j14 = j13 + j2;
            DataPosition64 dataPosition642 = dataPosition64;
            C0086a.C(read3, "found sef data: ", this.TAG);
            if (read3 == 2611) {
                this.f.seek(read - read4);
                read(2);
                int read6 = (int) read(2);
                if (read3 != read6) {
                    Log.w(this.TAG, A.a.d(read3, read6, "tail data type[", "] and data type[", "] are different!"));
                    this.f.close();
                    return dataPosition642;
                }
                int read7 = (int) read(4);
                String readString = readString(read7);
                if (!kotlin.jvm.internal.j.a(readString, "MotionPhoto_AutoPlay")) {
                    String str2 = this.TAG;
                    Log.w(str2, "invalid key name[" + readString + "]");
                    this.f.close();
                    return dataPosition642;
                }
                return new DataPosition64(this.f.position(), read5 - ((long) (8 + read7)), 0, j12, j13);
            }
            i2 = 4;
            j11++;
            j10 = j14;
            dataPosition64 = dataPosition642;
        }
        return dataPosition64;
    }

    public final DataPosition64 parseJpegSefTail() {
        long j2 = (long) 4;
        long size = this.f.size() - j2;
        this.f.seek(size);
        DataPosition64 dataPosition64 = null;
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_SIGNATURE)) {
            Log.w(this.TAG, "no SEFT");
            this.f.close();
            return null;
        }
        long j3 = size - j2;
        this.f.seek(j3);
        long read = j3 - read(4);
        this.sefTailStartSignatureOffset = read;
        this.f.seek(read);
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_START_SIGNATURE)) {
            Log.w(this.TAG, "no SEFH");
            this.f.close();
            return null;
        }
        read(4);
        long j8 = read + j2 + j2;
        long read2 = read(4);
        if (read2 > 100) {
            String str = this.TAG;
            Log.w(str, "invalid sef data count:" + read2);
            this.f.close();
            return null;
        }
        long j10 = j8 + j2;
        long j11 = 0;
        while (j11 < read2) {
            read(2);
            DataPosition64 dataPosition642 = dataPosition64;
            int read3 = (int) read(2);
            long j12 = j10 + j2;
            long read4 = read(4);
            long j13 = j12 + j2;
            long read5 = read(4);
            long j14 = j13 + j2;
            C0086a.C(read3, "found sef data: ", this.TAG);
            if (j11 == 0 && read3 != 2608) {
                this.sefStartOffset = read - read4;
            } else if (read3 == 2608) {
                this.f.seek(read - read4);
                read(2);
                int read6 = (int) read(2);
                if (read3 != read6) {
                    Log.w(this.TAG, A.a.d(read3, read6, "tail data type[", "] and data type[", "] are different!"));
                    this.f.close();
                    return dataPosition642;
                }
                int read7 = (int) read(4);
                String readString = readString(read7);
                if (!kotlin.jvm.internal.j.a(readString, "MotionPhoto_Data")) {
                    String str2 = this.TAG;
                    Log.w(str2, "invalid key name[" + readString + "]");
                    this.f.close();
                    return dataPosition642;
                }
                return new DataPosition64(this.f.position(), read5 - ((long) (8 + read7)), 0, j12, j13);
            }
            j11++;
            dataPosition64 = dataPosition642;
            j10 = j14;
        }
        return dataPosition64;
    }

    public final DataPosition64 parseSEFTail(boolean z) {
        if (z) {
            return parseJpegSefTail();
        }
        return parseHeicSefTail();
    }

    public final boolean readJpegSefTail(long j2) {
        long j3 = (long) 4;
        long size = this.f.size() - j3;
        this.f.seek(size);
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_SIGNATURE)) {
            Log.w(this.TAG, "no SEFT");
            this.f.close();
            return false;
        }
        long j8 = size - j3;
        this.f.seek(j8);
        long read = j8 - read(4);
        this.sefTailStartSignatureOffset = read;
        this.f.seek(read);
        if (!kotlin.jvm.internal.j.a(readString(4), MediaDefs.Meta.SEF.SEF_TAIL_START_SIGNATURE)) {
            Log.w(this.TAG, "no SEFH");
            this.f.close();
            return false;
        }
        read(4);
        long j10 = read + j3 + j3;
        long read2 = read(4);
        if (read2 > 100) {
            String str = this.TAG;
            Log.w(str, "invalid sef data count:" + read2);
            this.f.close();
            return false;
        }
        long j11 = j10 + j3;
        for (long j12 = 0; j12 < read2; j12++) {
            read(2);
            int read3 = (int) read(2);
            long j13 = j11 + j3;
            C0086a.C(read3, "found sef data: ", this.TAG);
            long read4 = read(4) + j2;
            if (read3 != 2609) {
                this.sefTailDataQueue.put(new SEFTailData(j13, read4));
            }
            long j14 = j13 + j3;
            long read5 = read(4);
            if (read3 == 2608) {
                this.sefTailDataQueue.put(new SEFTailData(j14, read5 + j2));
            }
            j11 = j14 + j3;
        }
        return true;
    }

    public final void setSefTailDataQueue(LinkedBlockingQueue<SEFTailData> linkedBlockingQueue) {
        kotlin.jvm.internal.j.e(linkedBlockingQueue, "<set-?>");
        this.sefTailDataQueue = linkedBlockingQueue;
    }

    private final long read(int i2, int i7) {
        byte[] readBuffer = readBuffer(i2);
        long j2 = 0;
        for (int i8 = i2 - 1; -1 < i8; i8--) {
            j2 = (j2 << 8) | ((long) (readBuffer[i8] & 255));
        }
        return i7 == 1 ? (long) ByteBuffer.wrap(readBuffer, 0, i2).order(ByteOrder.BIG_ENDIAN).getInt() : j2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MotionPhotoParser(String str) {
        this(new File(str));
        kotlin.jvm.internal.j.e(str, "filePath");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MotionPhotoParser(File file) {
        this(new FileInputStream(file));
        kotlin.jvm.internal.j.e(file, "file");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MotionPhotoParser(FileDescriptor fileDescriptor) {
        this(new FileInputStream(fileDescriptor));
        kotlin.jvm.internal.j.e(fileDescriptor, "fd");
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\r\u0010\fJ$\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001d\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SEFTailData;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "", "offset", "value", "<init>", "(JJ)V", "Lme/x;", "close", "()V", "component1", "()J", "component2", "copy", "(JJ)Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoParser$SEFTailData;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getOffset", "getValue", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SEFTailData implements AutoCloseable {
        private final long offset;
        private final long value;

        public SEFTailData(long j2, long j3) {
            this.offset = j2;
            this.value = j3;
        }

        public static /* synthetic */ SEFTailData copy$default(SEFTailData sEFTailData, long j2, long j3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = sEFTailData.offset;
            }
            if ((i2 & 2) != 0) {
                j3 = sEFTailData.value;
            }
            return sEFTailData.copy(j2, j3);
        }

        public final long component1() {
            return this.offset;
        }

        public final long component2() {
            return this.value;
        }

        public final SEFTailData copy(long j2, long j3) {
            return new SEFTailData(j2, j3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SEFTailData)) {
                return false;
            }
            SEFTailData sEFTailData = (SEFTailData) obj;
            if (this.offset == sEFTailData.offset && this.value == sEFTailData.value) {
                return true;
            }
            return false;
        }

        public final long getOffset() {
            return this.offset;
        }

        public final long getValue() {
            return this.value;
        }

        public int hashCode() {
            return Long.hashCode(this.value) + (Long.hashCode(this.offset) * 31);
        }

        public String toString() {
            long j2 = this.offset;
            return C0212a.o(j.j(j2, "SEFTailData(offset=", ", value="), this.value, ")");
        }

        public void close() {
        }
    }
}
