package com.samsung.android.motionphoto.utils.v2;

import Ae.b;
import Ke.v0;
import L1.d;
import Tf.a;
import android.graphics.RectF;
import androidx.core.util.Pair;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaWriter;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.io.File;
import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.i;
import me.x;
import te.C1295a;

@Metadata(d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010%\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u000f*\u0002¨\u0001\u0018\u0000 ´\u00012\u00020\u00012\u00020\u0002:\u0004µ\u0001´\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u0019\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\u00012\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010&\u001a\u00020\u00012\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u0001H\u0016¢\u0006\u0004\b(\u0010\u001fJ\u0017\u0010+\u001a\u00020\u00012\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\u00012\u0006\u0010*\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\u00012\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b0\u0010'J\u0017\u00100\u001a\u00020\u00012\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b0\u00103J\u000f\u00105\u001a\u000204H\u0016¢\u0006\u0004\b5\u00106J\u0017\u00105\u001a\u0002042\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b5\u00107J\u0017\u00105\u001a\u0002042\u0006\u00108\u001a\u00020$H\u0016¢\u0006\u0004\b5\u00109J\u0010\u0010;\u001a\u00020:H\u0001¢\u0006\u0004\b;\u0010<J\u0018\u0010?\u001a\n >*\u0004\u0018\u00010=0=H\u0001¢\u0006\u0004\b?\u0010@J\u0018\u0010A\u001a\n >*\u0004\u0018\u00010=0=H\u0001¢\u0006\u0004\bA\u0010@J\u0018\u0010C\u001a\n >*\u0004\u0018\u00010B0BH\u0001¢\u0006\u0004\bC\u0010DJ\u0010\u0010E\u001a\u00020\u0013H\u0001¢\u0006\u0004\bE\u0010FJ\u0010\u0010G\u001a\u00020\u0013H\u0001¢\u0006\u0004\bG\u0010FJ\u0010\u0010H\u001a\u00020\u0013H\u0001¢\u0006\u0004\bH\u0010FJ\u0018\u0010I\u001a\n >*\u0004\u0018\u00010\u00150\u0015H\u0001¢\u0006\u0004\bI\u0010JJ\u0010\u0010K\u001a\u00020\fH\u0001¢\u0006\u0004\bK\u0010\u000eJ\u0018\u0010L\u001a\n >*\u0004\u0018\u00010B0BH\u0001¢\u0006\u0004\bL\u0010DJ\u0010\u0010M\u001a\u00020\u0013H\u0001¢\u0006\u0004\bM\u0010FJ\u0010\u0010N\u001a\u00020\fH\u0001¢\u0006\u0004\bN\u0010\u000eJ\u0010\u0010O\u001a\u00020\u0013H\u0001¢\u0006\u0004\bO\u0010FJ\u0010\u0010P\u001a\u00020\fH\u0001¢\u0006\u0004\bP\u0010\u000eJ\u0010\u0010Q\u001a\u00020\fH\u0001¢\u0006\u0004\bQ\u0010\u000eJ\u0010\u0010R\u001a\u00020\u0013H\u0001¢\u0006\u0004\bR\u0010FJ\u0010\u0010S\u001a\u00020\fH\u0001¢\u0006\u0004\bS\u0010\u000eJ\u0010\u0010T\u001a\u00020\fH\u0001¢\u0006\u0004\bT\u0010\u000eJP\u0010W\u001aB\u0012\f\u0012\n >*\u0004\u0018\u00010B0B\u0012\f\u0012\n >*\u0004\u0018\u00010\f0\f >* \u0012\f\u0012\n >*\u0004\u0018\u00010B0B\u0012\f\u0012\n >*\u0004\u0018\u00010\f0\f\u0018\u00010V0UH\u0001¢\u0006\u0004\bW\u0010XJ\u0018\u0010Y\u001a\n >*\u0004\u0018\u00010\u00150\u0015H\u0001¢\u0006\u0004\bY\u0010JJ\u0010\u0010Z\u001a\u00020\fH\u0001¢\u0006\u0004\bZ\u0010\u000eJ\u0018\u0010[\u001a\n >*\u0004\u0018\u00010-0-H\u0001¢\u0006\u0004\b[\u0010\\J\u0018\u0010]\u001a\n >*\u0004\u0018\u00010)0)H\u0001¢\u0006\u0004\b]\u0010^J\u0018\u0010`\u001a\n >*\u0004\u0018\u00010_0_H\u0001¢\u0006\u0004\b`\u0010aJ\u0018\u0010c\u001a\n >*\u0004\u0018\u00010b0bH\u0001¢\u0006\u0004\bc\u0010dJ\u0018\u0010e\u001a\n >*\u0004\u0018\u00010\u00170\u0017H\u0001¢\u0006\u0004\be\u0010fJ\u001f\u0010e\u001a\u00020\f2\r\b\u0001\u00102\u001a\u000701¢\u0006\u0002\bgH\u0001¢\u0006\u0004\be\u0010hJ\u001f\u0010e\u001a\u00020\f2\r\b\u0001\u00108\u001a\u00070$¢\u0006\u0002\bgH\u0001¢\u0006\u0004\be\u0010iJ\u0018\u0010k\u001a\n >*\u0004\u0018\u00010j0jH\u0001¢\u0006\u0004\bk\u0010lJ\u0017\u0010n\u001a\u0002042\u0006\u0010m\u001a\u00020\u001bH\u0002¢\u0006\u0004\bn\u0010oJ\u001f\u0010r\u001a\u0002042\u0006\u0010m\u001a\u00020\u001b2\u0006\u0010q\u001a\u00020pH\u0002¢\u0006\u0004\br\u0010sJ\u001f\u0010&\u001a\u0002042\u0006\u0010m\u001a\u00020\u001b2\u0006\u0010q\u001a\u00020pH\u0002¢\u0006\u0004\b&\u0010sJ\u001f\u0010v\u001a\u0002042\u0006\u0010t\u001a\u00020\u00132\u0006\u0010u\u001a\u00020\u0013H\u0002¢\u0006\u0004\bv\u0010wJ7\u0010|\u001a\u0002042\u0006\u00102\u001a\u00020\u001b2\u001e\u0010{\u001a\u001a\u0012\u0004\u0012\u00020y\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130z0xH\u0002¢\u0006\u0004\b|\u0010}J\u001f\u0010|\u001a\u0002042\u0006\u00102\u001a\u00020\u001b2\u0006\u0010~\u001a\u00020\u0017H\u0002¢\u0006\u0004\b|\u0010J \u0010|\u001a\u0002042\u0006\u00102\u001a\u00020\u001b2\u0006\u0010~\u001a\u00020\u001bH\u0002¢\u0006\u0005\b|\u0010\u0001J\u0019\u0010\u0001\u001a\u0002042\u0006\u00102\u001a\u00020\u001bH\u0002¢\u0006\u0005\b\u0001\u0010oJ\u0011\u0010\u0001\u001a\u000204H\u0002¢\u0006\u0005\b\u0001\u00106J\u0011\u0010\u0001\u001a\u000204H\u0002¢\u0006\u0005\b\u0001\u00106J\u0011\u0010\u0001\u001a\u000204H\u0002¢\u0006\u0005\b\u0001\u00106J\u0011\u0010\u0001\u001a\u000204H\u0002¢\u0006\u0005\b\u0001\u00106J\u0011\u0010\u0001\u001a\u00020:H\u0002¢\u0006\u0005\b\u0001\u0010<J\u0011\u0010\u0001\u001a\u00020:H\u0002¢\u0006\u0005\b\u0001\u0010<J\u0011\u0010\u0001\u001a\u000204H\u0002¢\u0006\u0005\b\u0001\u00106J\u0011\u0010\u0001\u001a\u000204H\u0002¢\u0006\u0005\b\u0001\u00106J\u0011\u0010\u0001\u001a\u000204H\u0002¢\u0006\u0005\b\u0001\u00106J\u001b\u0010\u0001\u001a\u0002042\u0007\u0010\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u0017\u00105\u001a\u0002042\u0006\u00102\u001a\u00020\u001bH\u0002¢\u0006\u0004\b5\u0010oJ\u001a\u0010\u0001\u001a\u00020\f2\u0006\u00102\u001a\u00020\u001bH\u0002¢\u0006\u0006\b\u0001\u0010\u0001R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010\u0001R+\u0010\u0001\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u000e¢\u0006\u0018\n\u0006\b\u0001\u0010\u0001\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R\u001b\u0010\u0001\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0019\u0010\u0001\u001a\u00020:8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u0019\u0010\u0001\u001a\u00020:8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b\u0001\u0010\u0001R\u001f\u0010\u0001\u001a\u00020\t8BX\u0002¢\u0006\u000f\n\u0006\b\u0001\u0010\u0001\u001a\u0005\b\u0001\u0010\u000bR\u001f\u0010¢\u0001\u001a\u00020\u00068BX\u0002¢\u0006\u000f\n\u0006\b \u0001\u0010\u0001\u001a\u0005\b¡\u0001\u0010\bRO\u0010¦\u0001\u001a:\u0012\u0005\u0012\u00030¤\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002040x0£\u0001j\u001c\u0012\u0005\u0012\u00030¤\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002040x`¥\u00018\u0002X\u0004¢\u0006\b\n\u0006\b¦\u0001\u0010§\u0001R\u0018\u0010©\u0001\u001a\u00030¨\u00018\u0002X\u0004¢\u0006\b\n\u0006\b©\u0001\u0010ª\u0001R\u0016\u0010¬\u0001\u001a\u00020)8BX\u0004¢\u0006\u0007\u001a\u0005\b«\u0001\u0010^R\u0016\u0010®\u0001\u001a\u00020-8BX\u0004¢\u0006\u0007\u001a\u0005\b­\u0001\u0010\\R\u0017\u0010±\u0001\u001a\u00020\u001b8BX\u0004¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001R\u0017\u0010³\u0001\u001a\u00020\u001b8BX\u0004¢\u0006\b\u001a\u0006\b²\u0001\u0010°\u0001¨\u0006¶\u0001"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEditImpl;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfo;", "motionPhotoInfo", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfo;)V", "Lcom/samsung/android/motionphoto/utils/v2/MPXMPEdit;", "getXMPEdit", "()Lcom/samsung/android/motionphoto/utils/v2/MPXMPEdit;", "Lcom/samsung/android/motionphoto/utils/v2/MPSEFEdit;", "getSEFEdit", "()Lcom/samsung/android/motionphoto/utils/v2/MPSEFEdit;", "", "getFileSize", "()J", "getImageSize", "captureTimestampUs", "setCaptureTimestampUs", "(J)Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "", "type", "", "name", "", "data", "addSEFData", "(ILjava/lang/String;[B)Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "dataFile", "(ILjava/lang/String;Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "removeAllData", "()Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "Lcom/samsung/android/sum/core/buffer/MediaBuffer;", "imageBuffer", "replaceImage", "(Lcom/samsung/android/sum/core/buffer/MediaBuffer;)Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "Ljava/io/FileDescriptor;", "videoFd", "replaceVideo", "(Ljava/io/FileDescriptor;)Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "removeVideo", "Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "other", "addSEFInfo", "(Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;)Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "addXMPInfo", "(Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;)Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "addVideo", "Ljava/io/File;", "file", "(Ljava/io/File;)Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEdit;", "Lme/x;", "commit", "()V", "(Ljava/io/File;)V", "fileDescriptor", "(Ljava/io/FileDescriptor;)V", "", "isValid", "()Z", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoFormat;", "kotlin.jvm.PlatformType", "getSEFMotionPhotoVersion", "()Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoFormat;", "getXMPMotionPhotoVersion", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "getImageMimeType", "()Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "getImageWidth", "()I", "getImageHeight", "getImageRotation", "getDateTimeTaken", "()Ljava/lang/String;", "getCaptureTimestampUs", "getVideoMimeType", "getNumTracksOfVideo", "getVideoDurationUs", "getVideoBitrate", "getVideoPosition", "getVideoSize", "getAutoPlayVideoBitrate", "getAutoPlayVideoPosition", "getAutoPlayVideoSize", "", "", "getTrackDurationsOfVideo", "()Ljava/util/Map;", "getExtraInfo", "getImageVideoPadding", "getXMPInfo", "()Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "getSEFInfo", "()Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;", "getExifInfo", "()Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;", "Lcom/samsung/android/motionphoto/utils/v2/VideoInfo;", "getVideoInfo", "()Lcom/samsung/android/motionphoto/utils/v2/VideoInfo;", "getVideo", "()[B", "Lkotlin/jvm/internal/EnhancedNullability;", "(Ljava/io/File;)J", "(Ljava/io/FileDescriptor;)J", "Landroid/graphics/RectF;", "getCropRect", "()Landroid/graphics/RectF;", "mediaFile", "removeMPVDBox", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "Ljava/nio/ByteBuffer;", "videoBuffer", "replaceMPVDBox", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;Ljava/nio/ByteBuffer;)V", "position", "length", "replaceMPDataByMPVDBox", "(II)V", "Lkotlin/Function1;", "Ljava/nio/channels/FileChannel;", "Lme/i;", "writeVideo", "addMPVDBox", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;LAe/b;)V", "video", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;[B)V", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "resetXMPOfGainmap", "prepareImageType", "prepareXMP", "prepareOutputFile", "prepareCommit", "containsMotionPhotoPropInXMP", "containsImageDataInXMP", "fillMotionPhotoPropInXMP", "fillMotionPhotoDataInXMP", "fillImageDataInXMP", "orgFileSize", "completeCommit", "(J)V", "encodeImage", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)J", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfo;", "image", "Lcom/samsung/android/sum/core/buffer/MediaBuffer;", "getImage", "()Lcom/samsung/android/sum/core/buffer/MediaBuffer;", "setImage", "(Lcom/samsung/android/sum/core/buffer/MediaBuffer;)V", "_outputFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "isImageXMPRemoved", "Z", "isMotionPhotoXMPRemoved", "sefEdit$delegate", "Lme/f;", "getSefEdit", "sefEdit", "xmpEdit$delegate", "getXmpEdit", "xmpEdit", "Ljava/util/LinkedHashMap;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEditImpl$ExtraType;", "Lkotlin/collections/LinkedHashMap;", "extras", "Ljava/util/LinkedHashMap;", "com/samsung/android/motionphoto/utils/v2/MotionPhotoEditImpl$mpEditMediator$1", "mpEditMediator", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEditImpl$mpEditMediator$1;", "getSefInfo", "sefInfo", "getXmpInfo", "xmpInfo", "getInputFile", "()Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "inputFile", "getOutputFile", "outputFile", "Companion", "ExtraType", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionPhotoEditImpl implements MotionPhotoEdit, MotionPhotoInfo {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String TAG;
    private MediaFile _outputFile;
    private final LinkedHashMap<ExtraType, b> extras = new LinkedHashMap<>();
    private MediaBuffer image;
    private boolean isImageXMPRemoved;
    private boolean isMotionPhotoXMPRemoved;
    private final MotionPhotoInfo motionPhotoInfo;
    private final MotionPhotoEditImpl$mpEditMediator$1 mpEditMediator;
    private final f sefEdit$delegate = d.q(new d(this, 0));
    private final f xmpEdit$delegate = d.q(new d(this, 1));

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEditImpl$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoEditImpl$ExtraType;", "", "<init>", "(Ljava/lang/String;I)V", "ADD_MPVD", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ExtraType {
        ADD_MPVD;

        static {
            ExtraType[] $values;
            $ENTRIES = c.t($values);
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }
    }

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
                com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_HEIC     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_JPEG     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.WhenMappings.<clinit>():void");
        }
    }

    static {
        String tagOf = SLog.tagOf(MotionPhotoEdit.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public MotionPhotoEditImpl(MotionPhotoInfo motionPhotoInfo2) {
        j.e(motionPhotoInfo2, "motionPhotoInfo");
        this.motionPhotoInfo = motionPhotoInfo2;
        MotionPhotoEditImpl$mpEditMediator$1 motionPhotoEditImpl$mpEditMediator$1 = new MotionPhotoEditImpl$mpEditMediator$1(this);
        getSefEdit().setMotionPhotoMediator(motionPhotoEditImpl$mpEditMediator$1);
        getXmpEdit().setMotionPhotoMediator(motionPhotoEditImpl$mpEditMediator$1);
        this.mpEditMediator = motionPhotoEditImpl$mpEditMediator$1;
    }

    private final void addMPVDBox(MediaFile mediaFile, b bVar) {
        this.mpEditMediator.notify(MPEditEvent.WRITE_MPVD, (MPEditComponent) null);
        i iVar = (i) mediaFile.useOutputFileChannel(new Wf.c(2, this, bVar));
        replaceMPDataByMPVDBox(((Number) iVar.d).intValue(), ((Number) iVar.e).intValue());
    }

    /* access modifiers changed from: private */
    public static final i addMPVDBox$lambda$17(MotionPhotoEditImpl motionPhotoEditImpl, b bVar, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        fileChannel.position(motionPhotoEditImpl.getImageSize());
        return (i) bVar.invoke(fileChannel);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00fb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final me.i addMPVDBox$lambda$18(byte[] r26, java.nio.channels.FileChannel r27) {
        /*
            r0 = r26
            r1 = r27
            java.lang.String r2 = "fc"
            kotlin.jvm.internal.j.e(r1, r2)
            int r2 = r0.length
            r3 = 8
            int r2 = r2 + r3
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Integer[] r4 = new java.lang.Integer[]{r2}
            kotlin.jvm.internal.w r2 = kotlin.jvm.internal.v.f4727a
            java.lang.Class<java.lang.Integer> r5 = java.lang.Integer.class
            He.d r5 = r2.b(r5)
            java.lang.Class r10 = java.lang.Short.TYPE
            He.d r6 = r2.b(r10)
            boolean r6 = r5.equals(r6)
            java.lang.String r11 = "getBytes(...)"
            java.lang.String r12 = "null cannot be cast to non-null type kotlin.Long"
            java.lang.Class r13 = java.lang.Long.TYPE
            java.lang.String r14 = "null cannot be cast to non-null type kotlin.Int"
            r15 = 4
            java.lang.Class r7 = java.lang.Integer.TYPE
            java.lang.String r8 = "null cannot be cast to non-null type kotlin.Short"
            r16 = 2
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            r17 = 0
            if (r6 == 0) goto L_0x005b
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r16)
            r4 = r4[r17]
            kotlin.jvm.internal.j.c(r4, r8)
            java.lang.Short r4 = (java.lang.Short) r4
            short r4 = r4.shortValue()
            r5.putShort(r4)
            byte[] r4 = r5.array()
        L_0x0052:
            r18 = r3
        L_0x0054:
            r3 = r7
            r0 = r9
            r19 = r15
            r15 = r8
            goto L_0x00ca
        L_0x005b:
            He.d r6 = r2.b(r7)
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x007a
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r15)
            r4 = r4[r17]
            kotlin.jvm.internal.j.c(r4, r14)
            int r4 = r4.intValue()
            r5.putInt(r4)
            byte[] r4 = r5.array()
            goto L_0x0052
        L_0x007a:
            He.d r6 = r2.b(r13)
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r3)
            r4 = r4[r17]
            kotlin.jvm.internal.j.c(r4, r12)
            java.lang.Long r4 = (java.lang.Long) r4
            r18 = r3
            long r3 = r4.longValue()
            r5.putLong(r3)
            byte[] r4 = r5.array()
            goto L_0x0054
        L_0x009d:
            r18 = r3
            He.d r3 = r2.b(r9)
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0183
            r3 = r8
            r8 = 0
            r5 = r9
            r9 = 63
            r6 = r5
            r5 = 0
            r19 = r6
            r6 = 0
            r20 = r7
            r7 = 0
            r0 = r19
            r19 = r15
            r15 = r3
            r3 = r20
            java.lang.String r4 = ne.C1192j.s0(r4, r5, r6, r7, r8, r9)
            java.nio.charset.Charset r5 = Tf.a.f3815a
            byte[] r4 = r4.getBytes(r5)
            kotlin.jvm.internal.j.d(r4, r11)
        L_0x00ca:
            kotlin.jvm.internal.j.b(r4)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r1, (byte[]) r4)
            java.lang.String r4 = "mpvd"
            java.lang.String[] r20 = new java.lang.String[]{r4}
            He.d r4 = r2.b(r0)
            He.d r5 = r2.b(r10)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00fb
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r16)
            r2 = r20[r17]
            kotlin.jvm.internal.j.c(r2, r15)
            java.lang.Short r2 = (java.lang.Short) r2
            short r2 = r2.shortValue()
            r0.putShort(r2)
            byte[] r0 = r0.array()
            goto L_0x015e
        L_0x00fb:
            He.d r3 = r2.b(r3)
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x011c
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r19)
            r2 = r20[r17]
            kotlin.jvm.internal.j.c(r2, r14)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r0.putInt(r2)
            byte[] r0 = r0.array()
            goto L_0x015e
        L_0x011c:
            He.d r3 = r2.b(r13)
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x013d
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r18)
            r2 = r20[r17]
            kotlin.jvm.internal.j.c(r2, r12)
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            r0.putLong(r2)
            byte[] r0 = r0.array()
            goto L_0x015e
        L_0x013d:
            He.d r0 = r2.b(r0)
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x017d
            r24 = 0
            r25 = 63
            r21 = 0
            r22 = 0
            r23 = 0
            java.lang.String r0 = ne.C1192j.s0(r20, r21, r22, r23, r24, r25)
            java.nio.charset.Charset r2 = Tf.a.f3815a
            byte[] r0 = r0.getBytes(r2)
            kotlin.jvm.internal.j.d(r0, r11)
        L_0x015e:
            kotlin.jvm.internal.j.b(r0)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r1, (byte[]) r0)
            long r2 = r1.position()
            r0 = r26
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r1, (byte[]) r0)
            int r1 = (int) r2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            int r0 = r0.length
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            me.i r2 = new me.i
            r2.<init>(r1, r0)
            return r2
        L_0x017d:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x0183:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.addMPVDBox$lambda$18(byte[], java.nio.channels.FileChannel):me.i");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final me.i addMPVDBox$lambda$20(com.samsung.android.motionphoto.utils.v2.MediaFile r27, java.nio.channels.FileChannel r28) {
        /*
            r0 = r28
            java.lang.String r1 = "ofc"
            kotlin.jvm.internal.j.e(r0, r1)
            long r1 = r27.size()
            int r1 = (int) r1
            r2 = 8
            int r1 = r1 + r2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer[] r3 = new java.lang.Integer[]{r1}
            kotlin.jvm.internal.w r1 = kotlin.jvm.internal.v.f4727a
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            He.d r4 = r1.b(r4)
            java.lang.Class r9 = java.lang.Short.TYPE
            He.d r5 = r1.b(r9)
            boolean r5 = r4.equals(r5)
            java.lang.String r10 = "getBytes(...)"
            java.lang.String r11 = "null cannot be cast to non-null type kotlin.Long"
            java.lang.Class r12 = java.lang.Long.TYPE
            java.lang.String r13 = "null cannot be cast to non-null type kotlin.Int"
            r14 = 4
            java.lang.Class r15 = java.lang.Integer.TYPE
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.Short"
            r16 = 2
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r17 = 0
            if (r5 == 0) goto L_0x005b
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r16)
            r3 = r3[r17]
            kotlin.jvm.internal.j.c(r3, r6)
            java.lang.Short r3 = (java.lang.Short) r3
            short r3 = r3.shortValue()
            r4.putShort(r3)
            byte[] r3 = r4.array()
        L_0x0054:
            r18 = r2
        L_0x0056:
            r2 = r6
            r19 = r14
            r14 = r7
            goto L_0x00c6
        L_0x005b:
            He.d r5 = r1.b(r15)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x007a
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r14)
            r3 = r3[r17]
            kotlin.jvm.internal.j.c(r3, r13)
            int r3 = r3.intValue()
            r4.putInt(r3)
            byte[] r3 = r4.array()
            goto L_0x0054
        L_0x007a:
            He.d r5 = r1.b(r12)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x009d
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r2)
            r3 = r3[r17]
            kotlin.jvm.internal.j.c(r3, r11)
            java.lang.Long r3 = (java.lang.Long) r3
            r18 = r2
            long r2 = r3.longValue()
            r4.putLong(r2)
            byte[] r3 = r4.array()
            goto L_0x0056
        L_0x009d:
            r18 = r2
            He.d r2 = r1.b(r7)
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x01a2
            r2 = r7
            r7 = 0
            r8 = 63
            r4 = 0
            r5 = 0
            r19 = r6
            r6 = 0
            r26 = r14
            r14 = r2
            r2 = r19
            r19 = r26
            java.lang.String r3 = ne.C1192j.s0(r3, r4, r5, r6, r7, r8)
            java.nio.charset.Charset r4 = Tf.a.f3815a
            byte[] r3 = r3.getBytes(r4)
            kotlin.jvm.internal.j.d(r3, r10)
        L_0x00c6:
            kotlin.jvm.internal.j.b(r3)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r0, (byte[]) r3)
            java.lang.String r3 = "mpvd"
            java.lang.String[] r20 = new java.lang.String[]{r3}
            He.d r3 = r1.b(r14)
            He.d r4 = r1.b(r9)
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x00f7
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r16)
            r3 = r20[r17]
            kotlin.jvm.internal.j.c(r3, r2)
            java.lang.Short r3 = (java.lang.Short) r3
            short r2 = r3.shortValue()
            r1.putShort(r2)
            byte[] r1 = r1.array()
            goto L_0x015a
        L_0x00f7:
            He.d r2 = r1.b(r15)
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0118
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r19)
            r2 = r20[r17]
            kotlin.jvm.internal.j.c(r2, r13)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r1.putInt(r2)
            byte[] r1 = r1.array()
            goto L_0x015a
        L_0x0118:
            He.d r2 = r1.b(r12)
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0139
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r18)
            r2 = r20[r17]
            kotlin.jvm.internal.j.c(r2, r11)
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            r1.putLong(r2)
            byte[] r1 = r1.array()
            goto L_0x015a
        L_0x0139:
            He.d r1 = r1.b(r14)
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x019c
            r24 = 0
            r25 = 63
            r21 = 0
            r22 = 0
            r23 = 0
            java.lang.String r1 = ne.C1192j.s0(r20, r21, r22, r23, r24, r25)
            java.nio.charset.Charset r2 = Tf.a.f3815a
            byte[] r1 = r1.getBytes(r2)
            kotlin.jvm.internal.j.d(r1, r10)
        L_0x015a:
            kotlin.jvm.internal.j.b(r1)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r0, (byte[]) r1)
            long r1 = r0.position()
            java.lang.String r3 = TAG
            long r4 = r27.size()
            java.lang.String r6 = "write video on "
            java.lang.String r7 = " for "
            java.lang.StringBuilder r6 = N2.j.j(r1, r6, r7)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            com.samsung.android.sum.core.SLog.i((java.lang.String) r3, (java.lang.String) r4)
            com.samsung.android.motionphoto.utils.v2.e r3 = new com.samsung.android.motionphoto.utils.v2.e
            r4 = 0
            r3.<init>(r0, r4)
            r0 = r27
            r0.useInputFileChannel(r3)
            int r1 = (int) r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            long r2 = r0.size()
            int r0 = (int) r2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            me.i r2 = new me.i
            r2.<init>(r1, r0)
            return r2
        L_0x019c:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x01a2:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.addMPVDBox$lambda$20(com.samsung.android.motionphoto.utils.v2.MediaFile, java.nio.channels.FileChannel):me.i");
    }

    /* access modifiers changed from: private */
    public static final x addMPVDBox$lambda$20$lambda$19(FileChannel fileChannel, FileChannel fileChannel2) {
        j.e(fileChannel2, "ifc");
        CommonsKt.transferAllTo(fileChannel2, 0, fileChannel2.size(), fileChannel);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x addSEFData$lambda$3(MotionPhotoEditImpl motionPhotoEditImpl, byte[] bArr, MediaFile mediaFile) {
        j.e(mediaFile, "it");
        motionPhotoEditImpl.addMPVDBox(mediaFile, bArr);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x addSEFData$lambda$4(MotionPhotoEditImpl motionPhotoEditImpl, MediaFile mediaFile, MediaFile mediaFile2) {
        j.e(mediaFile2, "it");
        motionPhotoEditImpl.addMPVDBox(mediaFile2, mediaFile);
        return x.f4917a;
    }

    private final void completeCommit(long j2) {
        long j3;
        if (!this.isMotionPhotoXMPRemoved && j2 != getFileSize()) {
            String str = TAG;
            long fileSize = getFileSize();
            StringBuilder j8 = N2.j.j(j2, "size is changed ", " > ");
            j8.append(fileSize);
            j8.append(", update xmp mp info");
            SLog.i(str, j8.toString());
            MPXMPEdit xmpEdit = getXmpEdit();
            if (xmpEdit.getProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs") == null) {
                try {
                    j3 = getVideoDurationUs();
                } catch (Exception e) {
                    String str2 = TAG;
                    SLog.i(str2, "fail to get video-duration: " + e);
                    j3 = 0;
                }
                getXmpEdit().setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs", Long.valueOf(j3));
            }
            xmpEdit.setItemField(MediaDefs.Meta.XMP.XMP_KEY_PRIMARY, MediaDefs.Meta.XMP.XMP_KEY_PADDING, Long.valueOf(getImageVideoPadding()));
            xmpEdit.setItem(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, MimeType.VIDEO_MP4);
            xmpEdit.setItemField(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, Long.valueOf(getFileSize() - getVideoPosition()));
            xmpEdit.setItemField(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, MediaDefs.Meta.XMP.XMP_KEY_PADDING, 0);
            xmpEdit.commit(getOutputFile());
        }
    }

    private final boolean containsImageDataInXMP() {
        return getXmpEdit().getItemNames().contains(MediaDefs.Meta.XMP.XMP_KEY_PRIMARY);
    }

    private final boolean containsMotionPhotoPropInXMP() {
        if (getXmpEdit().getProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO) != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00d5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00d6, code lost:
        B1.a.g(r8, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00d9, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long encodeImage(com.samsung.android.motionphoto.utils.v2.MediaFile r8) {
        /*
            r7 = this;
            com.samsung.android.sum.core.buffer.MediaBuffer r0 = r7.image
            kotlin.jvm.internal.j.b(r0)
            com.samsung.android.sum.core.buffer.MediaBuffer r1 = r0.getExifBuffer()
            if (r1 == 0) goto L_0x0030
            java.lang.Class<com.samsung.android.sum.core.UniExifInterface> r2 = com.samsung.android.sum.core.UniExifInterface.class
            java.lang.Object r1 = r1.getTypedData(r2)
            com.samsung.android.sum.core.UniExifInterface r1 = (com.samsung.android.sum.core.UniExifInterface) r1
            if (r1 == 0) goto L_0x0030
            me.i r7 = new me.i
            java.lang.String r2 = "ImageWidth"
            r3 = 0
            int r2 = r1.getAttributeInt(r2, r3)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r4 = "ImageLength"
            int r1 = r1.getAttributeInt(r4, r3)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r7.<init>(r2, r1)
            goto L_0x004e
        L_0x0030:
            com.samsung.android.motionphoto.utils.v2.ExifInfo r1 = r7.getExifInfo()
            int r1 = r1.getWidth()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.samsung.android.motionphoto.utils.v2.ExifInfo r7 = r7.getExifInfo()
            int r7 = r7.getHeight()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            me.i r2 = new me.i
            r2.<init>(r1, r7)
            r7 = r2
        L_0x004e:
            java.lang.Object r1 = r7.d
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            java.lang.Object r7 = r7.e
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            com.samsung.android.sum.solution.filter.UniImgp$Option r2 = new com.samsung.android.sum.solution.filter.UniImgp$Option
            r2.<init>()
            com.samsung.android.sum.core.format.MediaFormatBuilder r3 = com.samsung.android.sum.core.format.MediaFormat.newImageBuilder()
            com.samsung.android.sum.core.format.MediaFormat r3 = r3.build()
            r2.setPersistentInputFormat(r3)
            Bd.a r3 = new Bd.a
            r4 = 22
            r3.<init>(r4)
            java.lang.Object r3 = r8.useInputFileChannel(r3)
            com.samsung.android.sum.core.types.CodecType r3 = (com.samsung.android.sum.core.types.CodecType) r3
            java.io.FileOutputStream r8 = r8.newOutputFileStream()
            com.samsung.android.sum.core.format.MediaFormatBuilder r4 = com.samsung.android.sum.core.format.MediaFormat.newBuilder()     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.types.MediaType r5 = com.samsung.android.sum.core.types.MediaType.COMPRESSED_IMAGE     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.format.MediaFormatBuilder r4 = r4.setMediaType(r5)     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.types.ColorFormat r6 = com.samsung.android.sum.core.types.ColorFormat.NV12     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.format.MediaFormatBuilder r4 = r4.setColorFormat(r6)     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.format.MediaFormatBuilder r3 = r4.setCodecType(r3)     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.format.MediaFormatBuilder r7 = r3.setShape(r1, r7)     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.format.MediaFormat r7 = r7.build()     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.types.ColorFormat r1 = r7.getColorFormat()     // Catch:{ all -> 0x00d3 }
            r2.setPreferredColorFormat(r1)     // Catch:{ all -> 0x00d3 }
            r2.setPersistentOutputFormat(r7)     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r7 = com.samsung.android.sum.core.buffer.MediaBuffer.newImageAlloc()     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r7 = r7.setMediaType(r5)     // Catch:{ all -> 0x00d3 }
            java.io.FileDescriptor r1 = r8.getFD()     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.buffer.MediaBuffer r7 = r7.wrap(r1)     // Catch:{ all -> 0x00d3 }
            java.lang.String r1 = "wrap(...)"
            kotlin.jvm.internal.j.d(r7, r1)     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.solution.filter.UniImgp r1 = com.samsung.android.sum.solution.filter.UniImgp.of(r2)     // Catch:{ all -> 0x00d3 }
            com.samsung.android.sum.core.functional.Operator r1 = r1.newOperator()     // Catch:{ all -> 0x00d3 }
            r1.run((com.samsung.android.sum.core.buffer.MediaBuffer) r0, (com.samsung.android.sum.core.buffer.MediaBuffer) r7)     // Catch:{ all -> 0x00d3 }
            java.nio.channels.FileChannel r7 = r8.getChannel()     // Catch:{ all -> 0x00d3 }
            long r0 = r7.size()     // Catch:{ all -> 0x00d3 }
            r7 = 0
            B1.a.g(r8, r7)
            return r0
        L_0x00d3:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00d5 }
        L_0x00d5:
            r0 = move-exception
            B1.a.g(r8, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.encodeImage(com.samsung.android.motionphoto.utils.v2.MediaFile):long");
    }

    /* access modifiers changed from: private */
    public static final CodecType encodeImage$lambda$39(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        fileChannel.position(0);
        if (MediaFile.Companion.isJpeg(fileChannel)) {
            return CodecType.JPEG_QURAM;
        }
        return CodecType.HEIF;
    }

    private final void fillImageDataInXMP() {
        getXmpEdit().setItem(MediaDefs.Meta.XMP.XMP_KEY_PRIMARY, getImageMimeType());
        getXmpEdit().setItemField(MediaDefs.Meta.XMP.XMP_KEY_PRIMARY, MediaDefs.Meta.XMP.XMP_KEY_PADDING, 0);
    }

    private final void fillMotionPhotoDataInXMP() {
        fillMotionPhotoPropInXMP();
        getXmpEdit().setItem(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, MimeType.VIDEO_MP4);
    }

    private final void fillMotionPhotoPropInXMP() {
        getXmpEdit().setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, 1);
        getXmpEdit().setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_MOTION_PHOTO_FORMAT_VERSION, 1);
    }

    private final MediaFile getInputFile() {
        MotionPhotoInfo motionPhotoInfo2 = this.motionPhotoInfo;
        j.c(motionPhotoInfo2, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl");
        return ((MotionPhotoInfoImpl) motionPhotoInfo2).getMediaFile$motionphoto_utils_v2_0_17_release();
    }

    /* access modifiers changed from: private */
    public final MediaFile getOutputFile() {
        MediaFile mediaFile = this._outputFile;
        if (mediaFile == null) {
            return getInputFile();
        }
        return mediaFile;
    }

    /* access modifiers changed from: private */
    public final MPSEFEdit getSefEdit() {
        return (MPSEFEdit) this.sefEdit$delegate.getValue();
    }

    private final SEFInfo getSefInfo() {
        MotionPhotoInfo motionPhotoInfo2 = this.motionPhotoInfo;
        j.c(motionPhotoInfo2, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl");
        return ((MotionPhotoInfoImpl) motionPhotoInfo2).getSEFInfo();
    }

    /* access modifiers changed from: private */
    public final MPXMPEdit getXmpEdit() {
        return (MPXMPEdit) this.xmpEdit$delegate.getValue();
    }

    private final XMPInfo getXmpInfo() {
        MotionPhotoInfo motionPhotoInfo2 = this.motionPhotoInfo;
        j.c(motionPhotoInfo2, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl");
        return ((MotionPhotoInfoImpl) motionPhotoInfo2).getXMPInfo();
    }

    private final void prepareCommit() {
        prepareImageType();
        if (getXmpInfo().isNotEmpty()) {
            prepareXMP();
        }
        if (j.a(getInputFile(), getOutputFile()) || !getOutputFile().isEmpty()) {
            SLog.i(TAG, "output file is given, just write it");
        } else {
            prepareOutputFile();
        }
    }

    private final void prepareImageType() {
        if (WhenMappings.$EnumSwitchMapping$0[getOutputFile().getMimeType().ordinal()] == 1 && getXmpInfo().isNotEmpty() && ImageMetaReader.Companion.of(getOutputFile()).getXMP() == null) {
            ImageMetaWriter.Companion.of(getOutputFile()).reserveXMP(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE);
            this.mpEditMediator.notify(MPEditEvent.RESERVE_XMP, (MPEditComponent) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        B1.a.g(r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void prepareOutputFile() {
        /*
            r5 = this;
            com.samsung.android.sum.core.buffer.MediaBuffer r0 = r5.image
            r1 = 0
            if (r0 != 0) goto L_0x0027
            com.samsung.android.motionphoto.utils.v2.MediaFile r0 = r5.getOutputFile()
            java.io.FileOutputStream r0 = r0.newOutputFileStream()
            com.samsung.android.motionphoto.utils.v2.MediaFile r5 = r5.getInputFile()     // Catch:{ all -> 0x0020 }
            java.io.File r5 = r5.getFile$motionphoto_utils_v2_0_17_release()     // Catch:{ all -> 0x0020 }
            java.nio.file.Path r5 = r5.toPath()     // Catch:{ all -> 0x0020 }
            java.nio.file.Files.copy(r5, r0)     // Catch:{ all -> 0x0020 }
            B1.a.g(r0, r1)
            return
        L_0x0020:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r1 = move-exception
            B1.a.g(r0, r5)
            throw r1
        L_0x0027:
            com.samsung.android.sum.core.format.MediaFormat r0 = r0.getFormat()
            com.samsung.android.sum.core.types.MediaType r0 = r0.getMediaType()
            boolean r0 = r0.isCompressed()
            if (r0 == 0) goto L_0x0041
            com.samsung.android.motionphoto.utils.v2.MediaFile r5 = r5.getOutputFile()
            java.io.FileOutputStream r5 = r5.newOutputFileStream()
            B1.a.g(r5, r1)
            return
        L_0x0041:
            com.samsung.android.motionphoto.utils.v2.MediaFile r0 = r5.getOutputFile()
            com.samsung.android.motionphoto.utils.v2.MimeType r0 = r0.getMimeType()
            com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_HEIC
            if (r0 != r1) goto L_0x006e
            java.util.LinkedHashMap<com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl$ExtraType, Ae.b> r0 = r5.extras
            com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl$ExtraType r1 = com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.ExtraType.ADD_MPVD
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x006e
            com.samsung.android.motionphoto.utils.v2.MPSEFEdit r0 = r5.getSefEdit()
            java.lang.String r2 = "MotionPhoto_Data"
            byte[] r0 = r0.getData((java.lang.String) r2)
            if (r0 == 0) goto L_0x006e
            java.util.LinkedHashMap<com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl$ExtraType, Ae.b> r2 = r5.extras
            com.samsung.android.motionphoto.utils.v2.f r3 = new com.samsung.android.motionphoto.utils.v2.f
            r4 = 1
            r3.<init>(r5, r0, r4)
            r2.put(r1, r3)
        L_0x006e:
            com.samsung.android.motionphoto.utils.v2.MediaFile r0 = r5.getOutputFile()
            r5.encodeImage(r0)
            com.samsung.android.motionphoto.utils.v2.MediaFile r0 = r5.getOutputFile()
            r5.resetXMPOfGainmap(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.prepareOutputFile():void");
    }

    /* access modifiers changed from: private */
    public static final x prepareOutputFile$lambda$31$lambda$30(MotionPhotoEditImpl motionPhotoEditImpl, byte[] bArr, MediaFile mediaFile) {
        j.e(mediaFile, "it");
        motionPhotoEditImpl.addMPVDBox(mediaFile, bArr);
        return x.f4917a;
    }

    private final void prepareXMP() {
        if (!this.isImageXMPRemoved && !containsImageDataInXMP()) {
            fillImageDataInXMP();
        }
        if (!this.isMotionPhotoXMPRemoved && !containsMotionPhotoPropInXMP()) {
            fillMotionPhotoDataInXMP();
        }
    }

    /* access modifiers changed from: private */
    public static final x removeAllData$lambda$5(MotionPhotoEditImpl motionPhotoEditImpl, MediaFile mediaFile) {
        j.e(mediaFile, "it");
        motionPhotoEditImpl.removeMPVDBox(mediaFile);
        return x.f4917a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x007a, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        B1.a.g(r0, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x007e, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void removeMPVDBox(com.samsung.android.motionphoto.utils.v2.MediaFile r10) {
        /*
            r9 = this;
            java.lang.String r0 = TAG
            java.lang.String r1 = "removeMPVDBox"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r1)
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x007f }
            java.io.File r10 = r10.getFile$motionphoto_utils_v2_0_17_release()     // Catch:{ Exception -> 0x007f }
            java.lang.String r1 = "rw"
            r0.<init>(r10, r1)     // Catch:{ Exception -> 0x007f }
            com.samsung.android.motionphoto.utils.v2.MPSEFEdit r9 = r9.getSefEdit()     // Catch:{ all -> 0x0078 }
            r10 = 2608(0xa30, float:3.655E-42)
            androidx.core.util.Pair r9 = r9.getDataPositionLength((int) r10)     // Catch:{ all -> 0x0078 }
            java.lang.String r10 = "getDataPositionLength(...)"
            kotlin.jvm.internal.j.d(r9, r10)     // Catch:{ all -> 0x0078 }
            F r10 = r9.first     // Catch:{ all -> 0x0078 }
            S r9 = r9.second     // Catch:{ all -> 0x0078 }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ all -> 0x0078 }
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ all -> 0x0078 }
            java.nio.channels.FileChannel r1 = r0.getChannel()     // Catch:{ all -> 0x0078 }
            long r1 = r1.size()     // Catch:{ all -> 0x0078 }
            long r3 = r10.longValue()     // Catch:{ all -> 0x0078 }
            kotlin.jvm.internal.j.b(r9)     // Catch:{ all -> 0x0078 }
            int r5 = r9.intValue()     // Catch:{ all -> 0x0078 }
            long r5 = (long) r5     // Catch:{ all -> 0x0078 }
            long r3 = r3 + r5
            long r1 = r1 - r3
            int r3 = (int) r1     // Catch:{ all -> 0x0078 }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)     // Catch:{ all -> 0x0078 }
            java.nio.channels.FileChannel r4 = r0.getChannel()     // Catch:{ all -> 0x0078 }
            long r5 = r10.longValue()     // Catch:{ all -> 0x0078 }
            int r9 = r9.intValue()     // Catch:{ all -> 0x0078 }
            long r7 = (long) r9     // Catch:{ all -> 0x0078 }
            long r5 = r5 + r7
            r4.read(r3, r5)     // Catch:{ all -> 0x0078 }
            r3.flip()     // Catch:{ all -> 0x0078 }
            java.nio.channels.FileChannel r9 = r0.getChannel()     // Catch:{ all -> 0x0078 }
            long r4 = r10.longValue()     // Catch:{ all -> 0x0078 }
            r6 = 8
            long r4 = r4 - r6
            r9.write(r3, r4)     // Catch:{ all -> 0x0078 }
            java.nio.channels.FileChannel r9 = r0.getChannel()     // Catch:{ all -> 0x0078 }
            long r3 = r10.longValue()     // Catch:{ all -> 0x0078 }
            long r3 = r3 - r6
            long r3 = r3 + r1
            r9.truncate(r3)     // Catch:{ all -> 0x0078 }
            r0.close()     // Catch:{ Exception -> 0x007f }
            return
        L_0x0078:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x007a }
        L_0x007a:
            r10 = move-exception
            B1.a.g(r0, r9)     // Catch:{ Exception -> 0x007f }
            throw r10     // Catch:{ Exception -> 0x007f }
        L_0x007f:
            r9 = move-exception
            r9.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.removeMPVDBox(com.samsung.android.motionphoto.utils.v2.MediaFile):void");
    }

    /* access modifiers changed from: private */
    public static final x removeVideo$lambda$11(MotionPhotoEditImpl motionPhotoEditImpl, MediaFile mediaFile) {
        j.e(mediaFile, "it");
        motionPhotoEditImpl.removeMPVDBox(mediaFile);
        return x.f4917a;
    }

    private final void replaceMPDataByMPVDBox(int i2, int i7) {
        ByteBuffer allocate = ByteBuffer.allocate(12);
        String version = MotionPhotoFormat.V2.getVersion();
        j.d(version, "getVersion(...)");
        byte[] bytes = version.getBytes(a.f3815a);
        j.d(bytes, "getBytes(...)");
        allocate.put(bytes);
        allocate.putInt(i2);
        allocate.putInt(i7);
        getSefEdit().putData(2608, "MotionPhoto_Data", allocate.array());
    }

    private final void replaceMPVDBox(MediaFile mediaFile, ByteBuffer byteBuffer) {
        mediaFile.useFileChannel(new StandardOpenOption[]{StandardOpenOption.READ, StandardOpenOption.WRITE}, new g(this, byteBuffer, 1));
    }

    /* access modifiers changed from: private */
    public static final FileChannel replaceMPVDBox$lambda$14(MotionPhotoEditImpl motionPhotoEditImpl, ByteBuffer byteBuffer, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        Pair<Long, Integer> dataPositionLength = motionPhotoEditImpl.getSefEdit().getDataPositionLength(2608);
        j.d(dataPositionLength, "getDataPositionLength(...)");
        Long l = (Long) dataPositionLength.first;
        Integer num = (Integer) dataPositionLength.second;
        long size = fileChannel.size();
        long longValue = l.longValue();
        j.b(num);
        ByteBuffer allocate = ByteBuffer.allocate((int) (size - (longValue + ((long) num.intValue()))));
        fileChannel.read(allocate, l.longValue() + ((long) num.intValue()));
        allocate.rewind();
        fileChannel.position(l.longValue());
        fileChannel.write(byteBuffer);
        fileChannel.write(allocate);
        return fileChannel.truncate(l.longValue() + ((long) byteBuffer.limit()) + ((long) allocate.limit()));
    }

    /* access modifiers changed from: private */
    public static final x replaceVideo$lambda$7(MotionPhotoEditImpl motionPhotoEditImpl, ByteBuffer byteBuffer, MediaFile mediaFile) {
        j.e(mediaFile, "it");
        j.b(byteBuffer);
        motionPhotoEditImpl.replaceVideo(mediaFile, byteBuffer);
        return x.f4917a;
    }

    private final void resetXMPOfGainmap(MediaFile mediaFile) {
        getXmpEdit().removeProperty(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_FORMAT_VERSION).removeItem("Gainmap");
        XMPInfoImpl xMPInfoImpl = new XMPInfoImpl(mediaFile);
        for (String str : xMPInfoImpl.getPropertyNames()) {
            String property = xMPInfoImpl.getProperty(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, str);
            if (property != null) {
                getXmpEdit().setProperty(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, str, property);
            }
        }
        for (String str2 : xMPInfoImpl.getItemNames()) {
            XMPItemInfo xMPItemInfo = xMPInfoImpl.getItems$motionphoto_utils_v2_0_17_release().get(str2);
            j.b(xMPItemInfo);
            XMPItemInfo xMPItemInfo2 = xMPItemInfo;
            getXmpEdit().setItem(str2, MimeType.Companion.of(xMPItemInfo2.getMime()));
            String str3 = xMPItemInfo2.get(MediaDefs.Meta.XMP.XMP_KEY_LENGTH);
            if (str3 != null) {
                getXmpEdit().setItemField(str2, MediaDefs.Meta.XMP.XMP_KEY_LENGTH, str3);
            }
            String str4 = xMPItemInfo2.get(MediaDefs.Meta.XMP.XMP_KEY_PADDING);
            if (str4 != null) {
                getXmpEdit().setItemField(str2, MediaDefs.Meta.XMP.XMP_KEY_PADDING, str4);
            }
        }
        if (xMPInfoImpl.getItemNames().isEmpty() && mediaFile.getMimeType() == MimeType.IMAGE_HEIC) {
            ImageMetaWriter.Companion.of(mediaFile).reserveXMP(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE);
        }
    }

    /* access modifiers changed from: private */
    public static final MPSEFEdit sefEdit_delegate$lambda$0(MotionPhotoEditImpl motionPhotoEditImpl) {
        SEFEdit edit = motionPhotoEditImpl.getSefInfo().edit();
        j.d(edit, "edit(...)");
        return new MPSEFEdit(edit);
    }

    /* access modifiers changed from: private */
    public static final MPXMPEdit xmpEdit_delegate$lambda$1(MotionPhotoEditImpl motionPhotoEditImpl) {
        XMPEdit edit = motionPhotoEditImpl.getXmpInfo().edit();
        j.d(edit, "edit(...)");
        return new MPXMPEdit(edit);
    }

    public MotionPhotoEdit addSEFData(int i2, String str, byte[] bArr) {
        j.e(str, "name");
        j.e(bArr, "data");
        String str2 = TAG;
        SLog.d(str2, "addSEFData: type=" + i2 + ", name=" + str + ", data=" + bArr);
        if (i2 == 2608 && getImageMimeType() == MimeType.IMAGE_HEIC) {
            this.extras.put(ExtraType.ADD_MPVD, new f(this, bArr, 0));
            MPSEFEdit sefEdit = getSefEdit();
            String version = MotionPhotoFormat.V3.getVersion();
            j.d(version, "getVersion(...)");
            byte[] bytes = version.getBytes(a.f3815a);
            j.d(bytes, "getBytes(...)");
            sefEdit.putData(2609, "MotionPhoto_Version", bytes);
            return this;
        }
        getSefEdit().putData(i2, str, bArr);
        return this;
    }

    public MotionPhotoEdit addSEFInfo(SEFInfo sEFInfo) {
        j.e(sEFInfo, "other");
        SEFInfoImpl sEFInfoImpl = (SEFInfoImpl) sEFInfo;
        SEFInfo sefInfo = getSefInfo();
        j.c(sefInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        SEFInfoImpl sEFInfoImpl2 = (SEFInfoImpl) sefInfo;
        sEFInfoImpl.loadAllData$motionphoto_utils_v2_0_17_release();
        sEFInfoImpl2.getDataInfos$motionphoto_utils_v2_0_17_release().addAll(sEFInfoImpl.getDataInfos$motionphoto_utils_v2_0_17_release());
        sEFInfoImpl2.setDirty$motionphoto_utils_v2_0_17_release(true);
        return this;
    }

    public MotionPhotoEdit addVideo(FileDescriptor fileDescriptor) {
        j.e(fileDescriptor, "videoFd");
        int i2 = WhenMappings.$EnumSwitchMapping$0[getOutputFile().getMimeType().ordinal()];
        if (i2 == 1) {
            addMPVDBox(getOutputFile(), new MediaFile(fileDescriptor));
            return this;
        } else if (i2 == 2) {
            getSefEdit().putData("MotionPhoto_Data", new MediaFile(fileDescriptor));
            return this;
        } else {
            throw new v0("An operation is not implemented: not implemented yet", 2);
        }
    }

    public MotionPhotoEdit addXMPInfo(XMPInfo xMPInfo) {
        j.e(xMPInfo, "other");
        ((XMPInfoImpl) xMPInfo).copyTo$motionphoto_utils_v2_0_17_release(getXmpInfo());
        return this;
    }

    public void commit(File file) {
        j.e(file, "file");
        commit(new MediaFile(file));
    }

    public int getAutoPlayVideoBitrate() {
        return this.motionPhotoInfo.getAutoPlayVideoBitrate();
    }

    public long getAutoPlayVideoPosition() {
        return this.motionPhotoInfo.getAutoPlayVideoPosition();
    }

    public long getAutoPlayVideoSize() {
        return this.motionPhotoInfo.getAutoPlayVideoSize();
    }

    public long getCaptureTimestampUs() {
        return this.motionPhotoInfo.getCaptureTimestampUs();
    }

    public RectF getCropRect() {
        return this.motionPhotoInfo.getCropRect();
    }

    public String getDateTimeTaken() {
        return this.motionPhotoInfo.getDateTimeTaken();
    }

    public ExifInfo getExifInfo() {
        return this.motionPhotoInfo.getExifInfo();
    }

    public String getExtraInfo() {
        return this.motionPhotoInfo.getExtraInfo();
    }

    public long getFileSize() {
        return this.motionPhotoInfo.getFileSize();
    }

    public final MediaBuffer getImage() {
        return this.image;
    }

    public int getImageHeight() {
        return this.motionPhotoInfo.getImageHeight();
    }

    public MimeType getImageMimeType() {
        return this.motionPhotoInfo.getImageMimeType();
    }

    public int getImageRotation() {
        return this.motionPhotoInfo.getImageRotation();
    }

    public long getImageSize() {
        return this.motionPhotoInfo.getImageSize();
    }

    public long getImageVideoPadding() {
        return this.motionPhotoInfo.getImageVideoPadding();
    }

    public int getImageWidth() {
        return this.motionPhotoInfo.getImageWidth();
    }

    public int getNumTracksOfVideo() {
        return this.motionPhotoInfo.getNumTracksOfVideo();
    }

    public SEFInfo getSEFInfo() {
        return this.motionPhotoInfo.getSEFInfo();
    }

    public MotionPhotoFormat getSEFMotionPhotoVersion() {
        return this.motionPhotoInfo.getSEFMotionPhotoVersion();
    }

    public Map<MimeType, Long> getTrackDurationsOfVideo() {
        return this.motionPhotoInfo.getTrackDurationsOfVideo();
    }

    public long getVideo(File file) {
        j.e(file, "file");
        return this.motionPhotoInfo.getVideo(file);
    }

    public int getVideoBitrate() {
        return this.motionPhotoInfo.getVideoBitrate();
    }

    public long getVideoDurationUs() {
        return this.motionPhotoInfo.getVideoDurationUs();
    }

    public VideoInfo getVideoInfo() {
        return this.motionPhotoInfo.getVideoInfo();
    }

    public MimeType getVideoMimeType() {
        return this.motionPhotoInfo.getVideoMimeType();
    }

    public long getVideoPosition() {
        return this.motionPhotoInfo.getVideoPosition();
    }

    public long getVideoSize() {
        return this.motionPhotoInfo.getVideoSize();
    }

    public XMPInfo getXMPInfo() {
        return this.motionPhotoInfo.getXMPInfo();
    }

    public MotionPhotoFormat getXMPMotionPhotoVersion() {
        return this.motionPhotoInfo.getXMPMotionPhotoVersion();
    }

    public boolean isValid() {
        return this.motionPhotoInfo.isValid();
    }

    public MotionPhotoEdit removeAllData() {
        if (getImageMimeType() == MimeType.IMAGE_HEIC && getSEFMotionPhotoVersion() != MotionPhotoFormat.V1) {
            this.extras.put(ExtraType.ADD_MPVD, new c(this, 1));
        }
        getSefEdit().removeData("MotionPhoto_Data", "MotionPhoto_Version", "MotionPhoto_Info", "MotionPhoto_AutoPlay", MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO);
        getXmpEdit().removeProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO).removeProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_MOTION_PHOTO_FORMAT_VERSION).removeProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs").removeItem(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO);
        this.isMotionPhotoXMPRemoved = true;
        return this;
    }

    public MotionPhotoEdit removeVideo() {
        if (getImageMimeType() == MimeType.IMAGE_HEIC && getSEFMotionPhotoVersion() != MotionPhotoFormat.V1) {
            this.extras.put(ExtraType.ADD_MPVD, new c(this, 0));
        }
        getSefEdit().removeData("MotionPhoto_Data", "MotionPhoto_Version", "MotionPhoto_Info", "MotionPhoto_AutoPlay", MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO);
        if (getXmpInfo().isNotEmpty()) {
            getXmpEdit().removeProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO).removeProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_MOTION_PHOTO_FORMAT_VERSION).removeProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs").removeItem(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO);
        }
        this.isMotionPhotoXMPRemoved = true;
        return this;
    }

    public MotionPhotoEdit replaceImage(MediaBuffer mediaBuffer) {
        j.e(mediaBuffer, "imageBuffer");
        this.image = mediaBuffer;
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0086, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0087, code lost:
        B1.a.g(r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008a, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.motionphoto.utils.v2.MotionPhotoEdit replaceVideo(java.io.FileDescriptor r6) {
        /*
            r5 = this;
            java.lang.String r0 = "videoFd"
            kotlin.jvm.internal.j.e(r6, r0)
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r6)
            byte[] r6 = r0.readAllBytes()     // Catch:{ all -> 0x0084 }
            r0.close()
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r6)
            com.samsung.android.motionphoto.utils.v2.MPXMPEdit r0 = r5.getXmpEdit()
            int r1 = r6.limit()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "MotionPhoto"
            java.lang.String r3 = "Length"
            r0.replaceItemField(r2, r3, r1)
            com.samsung.android.motionphoto.utils.v2.MimeType r0 = r5.getImageMimeType()
            com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_HEIC
            java.lang.String r2 = "MotionPhoto_Data"
            if (r0 != r1) goto L_0x0078
            java.util.LinkedHashMap<com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl$ExtraType, Ae.b> r0 = r5.extras
            com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl$ExtraType r1 = com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.ExtraType.ADD_MPVD
            com.samsung.android.motionphoto.utils.v2.g r3 = new com.samsung.android.motionphoto.utils.v2.g
            r4 = 0
            r3.<init>(r5, r6, r4)
            r0.put(r1, r3)
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r0 = r5.getSEFMotionPhotoVersion()
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r1 = com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat.V1
            if (r0 == r1) goto L_0x006c
            com.samsung.android.motionphoto.utils.v2.MPSEFEdit r0 = r5.getSefEdit()
            byte[] r0 = r0.getData((java.lang.String) r2)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)
            r1 = 8
            r0.position(r1)
            int r6 = r6.limit()
            r0.putInt(r6)
            com.samsung.android.motionphoto.utils.v2.MPSEFEdit r6 = r5.getSefEdit()
            byte[] r0 = r0.array()
            r6.putData((java.lang.String) r2, (byte[]) r0)
            return r5
        L_0x006c:
            com.samsung.android.motionphoto.utils.v2.MPSEFEdit r0 = r5.getSefEdit()
            byte[] r6 = r6.array()
            r0.putData((java.lang.String) r2, (byte[]) r6)
            return r5
        L_0x0078:
            com.samsung.android.motionphoto.utils.v2.MPSEFEdit r0 = r5.getSefEdit()
            byte[] r6 = r6.array()
            r0.putData((java.lang.String) r2, (byte[]) r6)
            return r5
        L_0x0084:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0086 }
        L_0x0086:
            r6 = move-exception
            B1.a.g(r0, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl.replaceVideo(java.io.FileDescriptor):com.samsung.android.motionphoto.utils.v2.MotionPhotoEdit");
    }

    public MotionPhotoEdit setCaptureTimestampUs(long j2) {
        if (!containsMotionPhotoPropInXMP()) {
            fillMotionPhotoPropInXMP();
        }
        getXmpEdit().setProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs", Long.valueOf(j2));
        return this;
    }

    public final void setImage(MediaBuffer mediaBuffer) {
        this.image = mediaBuffer;
    }

    public void commit(FileDescriptor fileDescriptor) {
        j.e(fileDescriptor, "fileDescriptor");
        commit(new MediaFile(fileDescriptor));
    }

    public MPSEFEdit getSEFEdit() {
        return getSefEdit();
    }

    public long getVideo(FileDescriptor fileDescriptor) {
        j.e(fileDescriptor, "fileDescriptor");
        return this.motionPhotoInfo.getVideo(fileDescriptor);
    }

    public MPXMPEdit getXMPEdit() {
        return getXmpEdit();
    }

    private final void commit(MediaFile mediaFile) {
        this._outputFile = mediaFile;
        commit();
    }

    public byte[] getVideo() {
        return this.motionPhotoInfo.getVideo();
    }

    public void commit() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = TAG;
        SLog.i(str, "commit: " + this);
        long fileSize = getFileSize();
        prepareCommit();
        boolean isDirty$motionphoto_utils_v2_0_17_release = getXmpEdit().isDirty$motionphoto_utils_v2_0_17_release();
        boolean isDirty$motionphoto_utils_v2_0_17_release2 = getSefEdit().isDirty$motionphoto_utils_v2_0_17_release();
        SLog.i(str, "xmp-isDirty: " + isDirty$motionphoto_utils_v2_0_17_release + ", sef-isDirty: " + isDirty$motionphoto_utils_v2_0_17_release2);
        if (getXmpEdit().isDirty$motionphoto_utils_v2_0_17_release()) {
            getXmpEdit().commit(getOutputFile());
        }
        Collection<b> values = this.extras.values();
        j.d(values, "<get-values>(...)");
        for (b invoke : values) {
            invoke.invoke(getOutputFile());
        }
        if (getSefEdit().isDirty$motionphoto_utils_v2_0_17_release()) {
            getSefEdit().commit(getOutputFile());
        }
        completeCommit(fileSize);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        String str2 = TAG;
        SLog.i(str2, "commit processing time: " + currentTimeMillis2 + " ms");
    }

    public MotionPhotoEdit addVideo(File file) {
        j.e(file, "file");
        int i2 = WhenMappings.$EnumSwitchMapping$0[getOutputFile().getMimeType().ordinal()];
        if (i2 == 1) {
            addMPVDBox(getOutputFile(), new MediaFile(file));
            return this;
        } else if (i2 == 2) {
            getSefEdit().putData("MotionPhoto_Data", new MediaFile(file));
            return this;
        } else {
            throw new v0("An operation is not implemented: not implemented yet", 2);
        }
    }

    private final void addMPVDBox(MediaFile mediaFile, byte[] bArr) {
        String str = TAG;
        SLog.i(str, "addMPVDBox: file=" + mediaFile + ", video=" + bArr);
        addMPVDBox(mediaFile, (b) new Ad.f(4, (Object) bArr));
    }

    public MotionPhotoEdit addSEFData(int i2, String str, MediaFile mediaFile) {
        j.e(str, "name");
        j.e(mediaFile, "dataFile");
        String str2 = TAG;
        SLog.d(str2, "addSEFData: type=" + i2 + ", name=" + str + ", data=" + mediaFile);
        if (i2 == 2608 && getImageMimeType() == MimeType.IMAGE_HEIC) {
            this.extras.put(ExtraType.ADD_MPVD, new Wf.c(1, this, mediaFile));
            MPSEFEdit sefEdit = getSefEdit();
            String version = MotionPhotoFormat.V3.getVersion();
            j.d(version, "getVersion(...)");
            byte[] bytes = version.getBytes(a.f3815a);
            j.d(bytes, "getBytes(...)");
            sefEdit.putData(2609, "MotionPhoto_Version", bytes);
            return this;
        }
        getSefEdit().putData(i2, str, mediaFile);
        return this;
    }

    private final void addMPVDBox(MediaFile mediaFile, MediaFile mediaFile2) {
        String str = TAG;
        SLog.i(str, "addMPVDBox: file=" + mediaFile + ", video=" + mediaFile2);
        addMPVDBox(mediaFile, (b) new b(1, mediaFile2));
    }

    private final void replaceVideo(MediaFile mediaFile, ByteBuffer byteBuffer) {
        if (j.a(getInputFile(), getOutputFile())) {
            replaceMPVDBox(mediaFile, byteBuffer);
        } else if (getOutputFile().getMimeType() == MimeType.IMAGE_HEIC) {
            MediaFile outputFile = getOutputFile();
            byte[] array = byteBuffer.array();
            j.d(array, "array(...)");
            addMPVDBox(outputFile, array);
        } else if (getOutputFile().getMimeType() == MimeType.IMAGE_JPEG) {
            getSefEdit().putData("MotionPhoto_Data", byteBuffer.array());
        } else {
            throw new v0("An operation is not implemented: not yet implement", 2);
        }
    }
}
