package com.samsung.android.motionphoto.utils.v2.video;

import Ae.b;
import Ae.d;
import Tf.v;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.MPFile;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import com.samsung.android.sum.core.SLog;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import me.x;
import ne.C1192j;
import ne.C1194l;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 O2\u00060\u0001j\u0002`\u0002:\u0003PQOB\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\rJ;\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00102$\u0010\u0018\u001a \u0012\u0004\u0012\u00020\u0013\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00150\u00140\u0012¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\u001a\u0010\u001cJ\u0015\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130\u00148\u0002X\u0004¢\u0006\u0006\n\u0004\b%\u0010&R,\u0010)\u001a\u001a\u0012\u0004\u0012\u00020\u0013\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020(0\u00150'8\u0002X\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\"\u0010,\u001a\u00020+8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u00102\u001a\u00020+8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b2\u0010-\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R\"\u00105\u001a\u00020+8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b5\u0010-\u001a\u0004\b6\u0010/\"\u0004\b7\u00101R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001e\u00108R6\u00109\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020+0\u0015\u0018\u00010\u00148\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b9\u0010&\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R`\u0010@\u001a@\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020+\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020+0\u00150\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020+0\u0015\u0018\u00010>j\u0004\u0018\u0001`?8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\"\u0010F\u001a\u00020\u00078\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u0014\u0010M\u001a\u00020L8\u0002X\u0004¢\u0006\u0006\n\u0004\bM\u0010N¨\u0006R"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "builder", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;)V", "", "flags", "convertExtractorFlagsToMediaCodecFlags", "(I)I", "Lme/x;", "start", "()V", "stop", "close", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "source", "", "", "", "Lme/i;", "Ljava/nio/ByteBuffer;", "Landroid/media/MediaCodec$BufferInfo;", "samples", "Lcom/samsung/android/motionphoto/utils/v2/video/Result;", "writeTrack", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;Ljava/util/Map;)Lcom/samsung/android/motionphoto/utils/v2/video/Result;", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;)Lcom/samsung/android/motionphoto/utils/v2/video/Result;", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "eventListener", "setOnEventListener", "(Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;)V", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "Landroid/media/MediaMuxer;", "muxer", "Landroid/media/MediaMuxer;", "targetMimes", "Ljava/util/List;", "", "Landroid/media/MediaFormat;", "trackInfos", "Ljava/util/Map;", "", "firstTransferTimestampUs", "J", "getFirstTransferTimestampUs$motionphoto_utils_v2_0_17_release", "()J", "setFirstTransferTimestampUs$motionphoto_utils_v2_0_17_release", "(J)V", "lastTransferTimestampUs", "getLastTransferTimestampUs$motionphoto_utils_v2_0_17_release", "setLastTransferTimestampUs$motionphoto_utils_v2_0_17_release", "lastTransferredTimestampUs", "getLastTransferredTimestampUs$motionphoto_utils_v2_0_17_release", "setLastTransferredTimestampUs$motionphoto_utils_v2_0_17_release", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "trimList", "getTrimList$motionphoto_utils_v2_0_17_release", "()Ljava/util/List;", "setTrimList$motionphoto_utils_v2_0_17_release", "(Ljava/util/List;)V", "Lkotlin/Function3;", "Lcom/samsung/android/motionphoto/utils/v2/video/TimeRangeAdjust;", "adjustTimeRange", "LAe/d;", "getAdjustTimeRange$motionphoto_utils_v2_0_17_release", "()LAe/d;", "setAdjustTimeRange$motionphoto_utils_v2_0_17_release", "(LAe/d;)V", "maxTransferDurationSec", "I", "getMaxTransferDurationSec$motionphoto_utils_v2_0_17_release", "()I", "setMaxTransferDurationSec$motionphoto_utils_v2_0_17_release", "(I)V", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isRun", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Companion", "Builder", "TransferMode", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoTransfer implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private d adjustTimeRange;
    private final Builder builder;
    private EventListener eventListener;
    private long firstTransferTimestampUs;
    private final AtomicBoolean isRun;
    private long lastTransferTimestampUs;
    private long lastTransferredTimestampUs;
    private int maxTransferDurationSec;
    private final MediaMuxer muxer;
    private final List<String> targetMimes;
    private final Map<String, i> trackInfos;
    private List<i> trimList;

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b2\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\u000b\u001a\u00020\u00002\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0017\u0010\u0016J\u0015\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010#\u001a\u00020\u00002\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 ¢\u0006\u0004\b#\u0010$J\u0015\u0010&\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u0013¢\u0006\u0004\b&\u0010\u0016Jo\u0010-\u001a\u00020\u00002\u0018\u0010)\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130(0'2F\b\u0002\u0010,\u001a@\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130(0'\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130(\u0018\u00010*j\u0004\u0018\u0001`+¢\u0006\u0004\b-\u0010.J\u0015\u00100\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u001c¢\u0006\u0004\b0\u0010\u001fJ\u0015\u00103\u001a\u00020\u00002\u0006\u00102\u001a\u000201¢\u0006\u0004\b3\u00104J\r\u00106\u001a\u000205¢\u0006\u0004\b6\u00107R\"\u0010\u0005\u001a\u00020\u00048\u0000@\u0000X.¢\u0006\u0012\n\u0004\b\u0005\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0=8\u0000X\u0004¢\u0006\f\n\u0004\b\n\u0010>\u001a\u0004\b?\u0010@R&\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000f0A8\u0000X\u0004¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER\"\u0010F\u001a\u00020\u00138\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\"\u0010L\u001a\u00020\u00138\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bL\u0010G\u001a\u0004\bM\u0010I\"\u0004\bN\u0010KR$\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR0\u0010T\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 \u0018\u00010(8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR$\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010Z\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\"\u0010%\u001a\u00020\u00138\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b%\u0010G\u001a\u0004\b_\u0010I\"\u0004\b`\u0010KR6\u0010)\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130(\u0018\u00010'8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b)\u0010>\u001a\u0004\ba\u0010@\"\u0004\bb\u0010cR`\u0010,\u001a@\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130(0'\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130(\u0018\u00010*j\u0004\u0018\u0001`+8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b,\u0010d\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\"\u0010/\u001a\u00020\u001c8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b/\u0010i\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\"\u00102\u001a\u0002018\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b2\u0010n\u001a\u0004\bo\u0010p\"\u0004\bq\u0010r¨\u0006s"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "", "<init>", "()V", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "outputFile", "setOutputFile", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "", "", "tracks", "setTracks", "([Ljava/lang/String;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "codecType", "Landroid/media/MediaFormat;", "format", "addTrackFormat", "(Lcom/samsung/android/motionphoto/utils/v2/MimeType;Landroid/media/MediaFormat;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "", "timestampUs", "setFirstTimestampUs", "(J)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "setLastTimestampUs", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "eventListener", "setEventListener", "(Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "", "orientationHint", "setOrientationHint", "(I)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "", "latitude", "longitude", "setLocation", "(FF)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "captureTimeUs", "setCaptureTimeUs", "", "Lme/i;", "trimList", "Lkotlin/Function3;", "Lcom/samsung/android/motionphoto/utils/v2/video/TimeRangeAdjust;", "adjustTimeRange", "setTrimList", "(Ljava/util/List;LAe/d;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "maxTransferDurationSec", "setMaxTransferDurationSecond", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$TransferMode;", "mode", "setTransferMode", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$TransferMode;)Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer;", "build", "()Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "getOutputFile$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "setOutputFile$motionphoto_utils_v2_0_17_release", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "", "Ljava/util/List;", "getTracks$motionphoto_utils_v2_0_17_release", "()Ljava/util/List;", "", "trackFormats", "Ljava/util/Map;", "getTrackFormats$motionphoto_utils_v2_0_17_release", "()Ljava/util/Map;", "firstTimestampUs", "J", "getFirstTimestampUs$motionphoto_utils_v2_0_17_release", "()J", "setFirstTimestampUs$motionphoto_utils_v2_0_17_release", "(J)V", "lastTimestampUs", "getLastTimestampUs$motionphoto_utils_v2_0_17_release", "setLastTimestampUs$motionphoto_utils_v2_0_17_release", "Ljava/lang/Integer;", "getOrientationHint$motionphoto_utils_v2_0_17_release", "()Ljava/lang/Integer;", "setOrientationHint$motionphoto_utils_v2_0_17_release", "(Ljava/lang/Integer;)V", "location", "Lme/i;", "getLocation$motionphoto_utils_v2_0_17_release", "()Lme/i;", "setLocation$motionphoto_utils_v2_0_17_release", "(Lme/i;)V", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "getEventListener$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "setEventListener$motionphoto_utils_v2_0_17_release", "(Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;)V", "getCaptureTimeUs$motionphoto_utils_v2_0_17_release", "setCaptureTimeUs$motionphoto_utils_v2_0_17_release", "getTrimList$motionphoto_utils_v2_0_17_release", "setTrimList$motionphoto_utils_v2_0_17_release", "(Ljava/util/List;)V", "LAe/d;", "getAdjustTimeRange$motionphoto_utils_v2_0_17_release", "()LAe/d;", "setAdjustTimeRange$motionphoto_utils_v2_0_17_release", "(LAe/d;)V", "I", "getMaxTransferDurationSec$motionphoto_utils_v2_0_17_release", "()I", "setMaxTransferDurationSec$motionphoto_utils_v2_0_17_release", "(I)V", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$TransferMode;", "getMode$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$TransferMode;", "setMode$motionphoto_utils_v2_0_17_release", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$TransferMode;)V", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private d adjustTimeRange;
        private long captureTimeUs;
        private EventListener eventListener;
        private long firstTimestampUs;
        private long lastTimestampUs;
        private i location;
        private int maxTransferDurationSec;
        private TransferMode mode = TransferMode.STRICT_SUB_VIDEO;
        private Integer orientationHint;
        public MediaFile outputFile;
        private final Map<MimeType, MediaFormat> trackFormats = new LinkedHashMap();
        private final List<String> tracks = new ArrayList();
        private List<i> trimList;

        public static /* synthetic */ Builder setTrimList$default(Builder builder, List list, d dVar, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                dVar = null;
            }
            return builder.setTrimList(list, dVar);
        }

        public final Builder addTrackFormat(MimeType mimeType, MediaFormat mediaFormat) {
            j.e(mimeType, "codecType");
            j.e(mediaFormat, "format");
            this.trackFormats.put(mimeType, mediaFormat);
            return this;
        }

        public final VideoTransfer build() {
            return new VideoTransfer(this, (e) null);
        }

        public final d getAdjustTimeRange$motionphoto_utils_v2_0_17_release() {
            return this.adjustTimeRange;
        }

        public final long getCaptureTimeUs$motionphoto_utils_v2_0_17_release() {
            return this.captureTimeUs;
        }

        public final EventListener getEventListener$motionphoto_utils_v2_0_17_release() {
            return this.eventListener;
        }

        public final long getFirstTimestampUs$motionphoto_utils_v2_0_17_release() {
            return this.firstTimestampUs;
        }

        public final long getLastTimestampUs$motionphoto_utils_v2_0_17_release() {
            return this.lastTimestampUs;
        }

        public final i getLocation$motionphoto_utils_v2_0_17_release() {
            return this.location;
        }

        public final int getMaxTransferDurationSec$motionphoto_utils_v2_0_17_release() {
            return this.maxTransferDurationSec;
        }

        public final TransferMode getMode$motionphoto_utils_v2_0_17_release() {
            return this.mode;
        }

        public final Integer getOrientationHint$motionphoto_utils_v2_0_17_release() {
            return this.orientationHint;
        }

        public final MediaFile getOutputFile$motionphoto_utils_v2_0_17_release() {
            MediaFile mediaFile = this.outputFile;
            if (mediaFile != null) {
                return mediaFile;
            }
            j.k("outputFile");
            throw null;
        }

        public final Map<MimeType, MediaFormat> getTrackFormats$motionphoto_utils_v2_0_17_release() {
            return this.trackFormats;
        }

        public final List<String> getTracks$motionphoto_utils_v2_0_17_release() {
            return this.tracks;
        }

        public final List<i> getTrimList$motionphoto_utils_v2_0_17_release() {
            return this.trimList;
        }

        public final void setAdjustTimeRange$motionphoto_utils_v2_0_17_release(d dVar) {
            this.adjustTimeRange = dVar;
        }

        public final Builder setCaptureTimeUs(long j2) {
            this.captureTimeUs = j2;
            return this;
        }

        public final void setCaptureTimeUs$motionphoto_utils_v2_0_17_release(long j2) {
            this.captureTimeUs = j2;
        }

        public final Builder setEventListener(EventListener eventListener2) {
            j.e(eventListener2, "eventListener");
            this.eventListener = eventListener2;
            return this;
        }

        public final void setEventListener$motionphoto_utils_v2_0_17_release(EventListener eventListener2) {
            this.eventListener = eventListener2;
        }

        public final Builder setFirstTimestampUs(long j2) {
            this.firstTimestampUs = j2;
            return this;
        }

        public final void setFirstTimestampUs$motionphoto_utils_v2_0_17_release(long j2) {
            this.firstTimestampUs = j2;
        }

        public final Builder setLastTimestampUs(long j2) {
            this.lastTimestampUs = j2;
            return this;
        }

        public final void setLastTimestampUs$motionphoto_utils_v2_0_17_release(long j2) {
            this.lastTimestampUs = j2;
        }

        public final Builder setLocation(float f, float f5) {
            this.location = new i(Float.valueOf(f), Float.valueOf(f5));
            return this;
        }

        public final void setLocation$motionphoto_utils_v2_0_17_release(i iVar) {
            this.location = iVar;
        }

        public final void setMaxTransferDurationSec$motionphoto_utils_v2_0_17_release(int i2) {
            this.maxTransferDurationSec = i2;
        }

        public final Builder setMaxTransferDurationSecond(int i2) {
            this.maxTransferDurationSec = i2;
            return this;
        }

        public final void setMode$motionphoto_utils_v2_0_17_release(TransferMode transferMode) {
            j.e(transferMode, "<set-?>");
            this.mode = transferMode;
        }

        public final Builder setOrientationHint(int i2) {
            this.orientationHint = Integer.valueOf(i2);
            return this;
        }

        public final void setOrientationHint$motionphoto_utils_v2_0_17_release(Integer num) {
            this.orientationHint = num;
        }

        public final Builder setOutputFile(MediaFile mediaFile) {
            j.e(mediaFile, "outputFile");
            setOutputFile$motionphoto_utils_v2_0_17_release(mediaFile);
            return this;
        }

        public final void setOutputFile$motionphoto_utils_v2_0_17_release(MediaFile mediaFile) {
            j.e(mediaFile, "<set-?>");
            this.outputFile = mediaFile;
        }

        public final Builder setTracks(String... strArr) {
            j.e(strArr, "tracks");
            this.tracks.addAll(C1192j.x0(strArr));
            return this;
        }

        public final Builder setTransferMode(TransferMode transferMode) {
            j.e(transferMode, "mode");
            this.mode = transferMode;
            return this;
        }

        public final Builder setTrimList(List<i> list, d dVar) {
            j.e(list, "trimList");
            this.trimList = list;
            this.adjustTimeRange = dVar;
            return this;
        }

        public final void setTrimList$motionphoto_utils_v2_0_17_release(List<i> list) {
            this.trimList = list;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$TransferMode;", "", "<init>", "(Ljava/lang/String;I)V", "FLEXIBLE", "STRICT_SUB_VIDEO", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TransferMode {
        FLEXIBLE,
        STRICT_SUB_VIDEO;

        static {
            TransferMode[] $values;
            $ENTRIES = c.t($values);
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }
    }

    static {
        String tagOf = SLog.tagOf(VideoTransfer.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public /* synthetic */ VideoTransfer(Builder builder2, e eVar) {
        this(builder2);
    }

    /* access modifiers changed from: private */
    public static final x _init_$lambda$1(VideoTransfer videoTransfer, MimeType mimeType, MediaFormat mediaFormat) {
        j.e(mimeType, "type");
        j.e(mediaFormat, "format");
        videoTransfer.trackInfos.put(mimeType.getValue(), new i(Integer.valueOf(videoTransfer.muxer.addTrack(mediaFormat)), mediaFormat));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$2(Ae.c cVar, Object obj, Object obj2) {
        cVar.invoke(obj, obj2);
    }

    private final int convertExtractorFlagsToMediaCodecFlags(int i2) {
        boolean containsAnyFlag = CommonsKt.containsAnyFlag(i2, 1);
        return CommonsKt.containsAnyFlag(i2, 4) ? containsAnyFlag | true ? 1 : 0 : containsAnyFlag ? 1 : 0;
    }

    public void close() {
        this.muxer.release();
        this.isRun.set(false);
    }

    public final d getAdjustTimeRange$motionphoto_utils_v2_0_17_release() {
        return this.adjustTimeRange;
    }

    public final long getFirstTransferTimestampUs$motionphoto_utils_v2_0_17_release() {
        return this.firstTransferTimestampUs;
    }

    public final long getLastTransferTimestampUs$motionphoto_utils_v2_0_17_release() {
        return this.lastTransferTimestampUs;
    }

    public final long getLastTransferredTimestampUs$motionphoto_utils_v2_0_17_release() {
        return this.lastTransferredTimestampUs;
    }

    public final int getMaxTransferDurationSec$motionphoto_utils_v2_0_17_release() {
        return this.maxTransferDurationSec;
    }

    public final List<i> getTrimList$motionphoto_utils_v2_0_17_release() {
        return this.trimList;
    }

    public final void setAdjustTimeRange$motionphoto_utils_v2_0_17_release(d dVar) {
        this.adjustTimeRange = dVar;
    }

    public final void setFirstTransferTimestampUs$motionphoto_utils_v2_0_17_release(long j2) {
        this.firstTransferTimestampUs = j2;
    }

    public final void setLastTransferTimestampUs$motionphoto_utils_v2_0_17_release(long j2) {
        this.lastTransferTimestampUs = j2;
    }

    public final void setLastTransferredTimestampUs$motionphoto_utils_v2_0_17_release(long j2) {
        this.lastTransferredTimestampUs = j2;
    }

    public final void setMaxTransferDurationSec$motionphoto_utils_v2_0_17_release(int i2) {
        this.maxTransferDurationSec = i2;
    }

    public final void setOnEventListener(EventListener eventListener2) {
        j.e(eventListener2, "eventListener");
        this.eventListener = eventListener2;
    }

    public final void setTrimList$motionphoto_utils_v2_0_17_release(List<i> list) {
        this.trimList = list;
    }

    public final void start() {
        if (this.isRun.compareAndSet(false, true)) {
            this.muxer.start();
        }
    }

    public final void stop() {
        if (this.isRun.compareAndSet(true, false)) {
            this.muxer.stop();
        }
    }

    public final Result writeTrack(MPFile mPFile, Map<String, ? extends List<? extends i>> map) {
        Iterator<Map.Entry<String, ? extends List<? extends i>>> it;
        Iterator<Map.Entry<String, ? extends List<? extends i>>> it2;
        VideoTransfer videoTransfer;
        long j2;
        long j3;
        Long l;
        Long l8;
        VideoTransfer videoTransfer2 = this;
        MPFile mPFile2 = mPFile;
        j.e(mPFile2, "source");
        Map<String, ? extends List<? extends i>> map2 = map;
        j.e(map2, "samples");
        SLog.d(TAG, "writeVideo from transcoded samples[" + map2.size() + "]");
        long j8 = videoTransfer2.lastTransferTimestampUs;
        long j10 = 0;
        if (j8 == 0) {
            Iterator it3 = map2.values().iterator();
            if (!it3.hasNext()) {
                l8 = null;
            } else {
                List list = (List) it3.next();
                ListIterator listIterator = list.listIterator(list.size());
                while (listIterator.hasPrevious()) {
                    MediaCodec.BufferInfo bufferInfo = (MediaCodec.BufferInfo) ((i) listIterator.previous()).e;
                    if ((bufferInfo.flags & 4) == 0) {
                        l8 = Long.valueOf(bufferInfo.presentationTimeUs);
                        while (it3.hasNext()) {
                            List list2 = (List) it3.next();
                            ListIterator listIterator2 = list2.listIterator(list2.size());
                            while (listIterator2.hasPrevious()) {
                                MediaCodec.BufferInfo bufferInfo2 = (MediaCodec.BufferInfo) ((i) listIterator2.previous()).e;
                                if ((bufferInfo2.flags & 4) == 0) {
                                    Long valueOf = Long.valueOf(bufferInfo2.presentationTimeUs);
                                    if (l8.compareTo(valueOf) > 0) {
                                        l8 = valueOf;
                                    }
                                }
                            }
                            throw new NoSuchElementException("List contains no element matching the predicate.");
                        }
                    }
                }
                throw new NoSuchElementException("List contains no element matching the predicate.");
            }
            j8 = l8 != null ? l8.longValue() : 0;
        }
        try {
            Map<String, i> map3 = videoTransfer2.trackInfos;
            for (T next : map3.keySet()) {
                if (v.t0((String) next, "video")) {
                    i iVar = map3.get(next);
                    j.b(iVar);
                    MediaFormat mediaFormat = (MediaFormat) iVar.e;
                    mediaFormat.getInteger("width");
                    mediaFormat.getInteger("height");
                    videoTransfer2.start();
                    Iterator<Map.Entry<String, ? extends List<? extends i>>> it4 = map2.entrySet().iterator();
                    long j11 = Long.MAX_VALUE;
                    while (it.hasNext()) {
                        Map.Entry next2 = it.next();
                        String str = (String) next2.getKey();
                        List list3 = (List) next2.getValue();
                        SLog.d(TAG, "writeVideo: mimeType=" + str + ", data=" + list3.size());
                        Iterable iterable = videoTransfer2.targetMimes;
                        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                            Iterator it5 = iterable.iterator();
                            while (true) {
                                if (!it5.hasNext()) {
                                    break;
                                } else if (v.t0(str, (String) it5.next())) {
                                    i iVar2 = videoTransfer2.trackInfos.get(str);
                                    j.b(iVar2);
                                    int intValue = ((Number) iVar2.d).intValue();
                                    Iterator it6 = list3.iterator();
                                    while (true) {
                                        if (!it6.hasNext()) {
                                            it2 = it;
                                            j2 = j10;
                                            j3 = j11;
                                            break;
                                        }
                                        i iVar3 = (i) it6.next();
                                        ByteBuffer byteBuffer = (ByteBuffer) iVar3.d;
                                        MediaCodec.BufferInfo bufferInfo3 = (MediaCodec.BufferInfo) iVar3.e;
                                        String str2 = TAG;
                                        it2 = it;
                                        j2 = j10;
                                        j3 = j11;
                                        SLog.d(str2, "buffer=" + CommonsKt.asString(bufferInfo3) + ", firstTransferTimestampUs=" + videoTransfer2.firstTransferTimestampUs + ", lastTransferTimestampUs=" + j8);
                                        if (CommonsKt.containFlag(bufferInfo3, 2)) {
                                            SLog.i(str2, "skip codec-config");
                                        } else {
                                            long j12 = bufferInfo3.presentationTimeUs;
                                            long j13 = videoTransfer2.firstTransferTimestampUs;
                                            if (j12 < j13) {
                                                StringBuilder j14 = N2.j.j(j12, "skip frame(", " us) before ");
                                                j14.append(j13);
                                                j14.append(" us");
                                                SLog.d(str2, j14.toString());
                                            } else if (j12 <= j8) {
                                                Iterator it7 = it6;
                                                bufferInfo3.presentationTimeUs = j12 + videoTransfer2.lastTransferredTimestampUs;
                                                SLog.d(str2, CommonsKt.trimToOneLine("[" + intValue + "][" + str + "] write sample:\n                            | info=" + CommonsKt.asString(bufferInfo3) + ",\n                            | lastTrackTimestampUs=" + videoTransfer2.lastTransferredTimestampUs + " us \n                        "));
                                                videoTransfer2.muxer.writeSampleData(intValue, byteBuffer, bufferInfo3);
                                                byteBuffer.rewind();
                                                long j15 = bufferInfo3.presentationTimeUs;
                                                j11 = j15 < j3 ? j15 : j3;
                                                j10 = j15 > j2 ? j15 : j2;
                                                EventListener eventListener2 = videoTransfer2.eventListener;
                                                if (eventListener2 != null) {
                                                    eventListener2.onEachFrameTransferred(mPFile2, str, bufferInfo3);
                                                }
                                                Map<String, ? extends List<? extends i>> map4 = map;
                                                it = it2;
                                                it6 = it7;
                                            } else {
                                                long videoDurationUs = mPFile2.getMpInfo().getVideoDurationUs() + videoTransfer2.lastTransferredTimestampUs;
                                                Iterator it8 = map.values().iterator();
                                                if (!it8.hasNext()) {
                                                    l = null;
                                                } else {
                                                    l = Long.valueOf(((MediaCodec.BufferInfo) ((i) C1194l.T0((List) it8.next())).e).presentationTimeUs);
                                                    while (it8.hasNext()) {
                                                        Long valueOf2 = Long.valueOf(((MediaCodec.BufferInfo) ((i) C1194l.T0((List) it8.next())).e).presentationTimeUs);
                                                        if (l.compareTo(valueOf2) < 0) {
                                                            MPFile mPFile3 = mPFile;
                                                            l = valueOf2;
                                                        } else {
                                                            MPFile mPFile4 = mPFile;
                                                        }
                                                    }
                                                }
                                                SLog.i(str2, CommonsKt.trimToOneLine("[" + intValue + "][" + str + "]reached EOS:\n                            | sampleTimestampUs=" + j12 + ", \n                            | min-track-durationUs=" + j8 + ",\n                            | last-ts from duration=" + videoDurationUs + " us, \n                            | max-track-duration=" + l + "\n                        "));
                                            }
                                        }
                                        Map<String, ? extends List<? extends i>> map5 = map;
                                        it = it2;
                                        j10 = j2;
                                        j11 = j3;
                                    }
                                    videoTransfer = this;
                                    j10 = j2;
                                    j11 = j3;
                                } else {
                                    videoTransfer2 = this;
                                    mPFile2 = mPFile;
                                    Map<String, ? extends List<? extends i>> map6 = map;
                                }
                            }
                        }
                        it2 = it;
                        videoTransfer = this;
                        SLog.w(TAG, N2.j.d("target mime types are [", C1194l.R0(videoTransfer.targetMimes, (String) null, (String) null, (String) null, (b) null, 63), "], but current is ", str, ", skip this track"));
                        Map<String, ? extends List<? extends i>> map7 = map;
                        videoTransfer2 = videoTransfer;
                        it4 = it2;
                        mPFile2 = mPFile;
                    }
                    VideoTransfer videoTransfer3 = videoTransfer2;
                    videoTransfer3.lastTransferredTimestampUs += j8;
                    EventListener eventListener3 = videoTransfer3.eventListener;
                    if (eventListener3 != null) {
                        eventListener3.onTransferComplete(mPFile);
                    }
                    return new Result((ExportEvent) null, 0, 0, 0, j10 - j11, (MediaFormat) null, 47, (e) null);
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Exception unused) {
            Map<String, i> map8 = videoTransfer2.trackInfos;
            i iVar4 = map8.get(C1194l.K0(map8.keySet()));
            j.b(iVar4);
            ((MediaFormat) iVar4.e).getInteger("max-input-size");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00dc, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00dd, code lost:
        B1.a.g(r2, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e0, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private VideoTransfer(com.samsung.android.motionphoto.utils.v2.video.VideoTransfer.Builder r8) {
        /*
            r7 = this;
            r7.<init>()
            r7.builder = r8
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r7.trackInfos = r0
            java.util.concurrent.atomic.AtomicBoolean r0 = new java.util.concurrent.atomic.AtomicBoolean
            r1 = 0
            r0.<init>(r1)
            r7.isRun = r0
            java.lang.String r0 = TAG
            com.samsung.android.motionphoto.utils.v2.MediaFile r2 = r8.getOutputFile$motionphoto_utils_v2_0_17_release()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "outputFile="
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r2)
            com.samsung.android.motionphoto.utils.v2.MediaFile r2 = r8.getOutputFile$motionphoto_utils_v2_0_17_release()
            java.io.FileOutputStream r2 = r2.newOutputFileStream()
            android.media.MediaMuxer r3 = new android.media.MediaMuxer     // Catch:{ all -> 0x00da }
            java.io.FileDescriptor r4 = r2.getFD()     // Catch:{ all -> 0x00da }
            r3.<init>(r4, r1)     // Catch:{ all -> 0x00da }
            r2.close()
            r7.muxer = r3
            java.util.Map r1 = r8.getTrackFormats$motionphoto_utils_v2_0_17_release()
            Tf.w r2 = new Tf.w
            r4 = 5
            r2.<init>(r4, r7)
            Ba.h r4 = new Ba.h
            r5 = 27
            r4.<init>(r5, r2)
            r1.forEach(r4)
            java.util.List r1 = r8.getTracks$motionphoto_utils_v2_0_17_release()
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x006d
            java.lang.String r1 = "audio"
            java.lang.String r2 = "video"
            java.lang.String[] r1 = new java.lang.String[]{r1, r2}
            java.util.List r1 = ne.C1195m.q0(r1)
            goto L_0x0071
        L_0x006d:
            java.util.List r1 = r8.getTracks$motionphoto_utils_v2_0_17_release()
        L_0x0071:
            r7.targetMimes = r1
            java.lang.Integer r1 = r8.getOrientationHint$motionphoto_utils_v2_0_17_release()
            if (r1 == 0) goto L_0x0080
            int r1 = r1.intValue()
            r3.setOrientationHint(r1)
        L_0x0080:
            me.i r1 = r8.getLocation$motionphoto_utils_v2_0_17_release()
            if (r1 == 0) goto L_0x0099
            java.lang.Object r2 = r1.d
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            java.lang.Object r1 = r1.e
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            r3.setLocation(r2, r1)
        L_0x0099:
            long r1 = r8.getFirstTimestampUs$motionphoto_utils_v2_0_17_release()
            r7.firstTransferTimestampUs = r1
            long r1 = r8.getLastTimestampUs$motionphoto_utils_v2_0_17_release()
            r7.lastTransferTimestampUs = r1
            int r1 = r8.getMaxTransferDurationSec$motionphoto_utils_v2_0_17_release()
            r7.maxTransferDurationSec = r1
            Ae.d r1 = r8.getAdjustTimeRange$motionphoto_utils_v2_0_17_release()
            r7.adjustTimeRange = r1
            java.util.List r1 = r8.getTrimList$motionphoto_utils_v2_0_17_release()
            r7.trimList = r1
            long r1 = r7.firstTransferTimestampUs
            long r3 = r7.lastTransferTimestampUs
            long r7 = r8.getCaptureTimeUs$motionphoto_utils_v2_0_17_release()
            java.lang.String r5 = "first="
            java.lang.String r6 = ", last="
            java.lang.StringBuilder r1 = N2.j.j(r1, r5, r6)
            r1.append(r3)
            java.lang.String r2 = ", capture="
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r7)
            return
        L_0x00da:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00dc }
        L_0x00dc:
            r8 = move-exception
            B1.a.g(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.video.VideoTransfer.<init>(com.samsung.android.motionphoto.utils.v2.video.VideoTransfer$Builder):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        r5 = java.nio.ByteBuffer.allocateDirect(r27);
        r7 = false;
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02de, code lost:
        r28 = r4.getSampleTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02e4, code lost:
        if (r17 > r28) goto L_0x0493;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02e8, code lost:
        if (r28 >= r11) goto L_0x0493;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x02ea, code lost:
        r28 = r7;
        r7 = new android.media.MediaCodec.BufferInfo();
        r29 = r13;
        r7.flags = convertExtractorFlagsToMediaCodecFlags(r4.getSampleFlags());
        r32 = r14;
        r7.presentationTimeUs = r4.getSampleTime() + r0.lastTransferredTimestampUs;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0309, code lost:
        if (r28 != false) goto L_0x03ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0317, code lost:
        if (com.samsung.android.motionphoto.utils.v2.CommonsKt.containsAnyFlag(r7.flags, 1) == false) goto L_0x03ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0319, code lost:
        r13 = r0.trimList;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x031b, code lost:
        if (r13 == null) goto L_0x0358;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:?, code lost:
        r13 = r13.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0327, code lost:
        if (r13.hasNext() == false) goto L_0x0350;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0329, code lost:
        r14 = r13.next();
        r31 = r13;
        r34 = r5;
        r33 = r6;
        r5 = r7.presentationTimeUs;
        r35 = ((java.lang.Number) ((me.i) r14).e).longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0342, code lost:
        if (r5 > r35) goto L_0x0349;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0346, code lost:
        if (r35 >= r11) goto L_0x0349;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0349, code lost:
        r13 = r31;
        r6 = r33;
        r5 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0350, code lost:
        r34 = r5;
        r33 = r6;
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0355, code lost:
        r14 = (me.i) r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0358, code lost:
        r34 = r5;
        r33 = r6;
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x035d, code lost:
        if (r14 == null) goto L_0x03ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        r5 = TAG;
        r6 = r7.presentationTimeUs;
        r13 = r14.d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0365, code lost:
        r31 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:?, code lost:
        r3 = r14.e;
        r1 = new java.lang.StringBuilder();
        r1.append("[");
        r1.append(r15);
        r35 = r15;
        r1.append("]range(");
        r1.append(r6);
        r1.append(r2);
        r1.append(r11);
        r1.append(")\n                                | contains trim-range(");
        r1.append(r13);
        r1.append(r2);
        r1.append(r3);
        r1.append("),\n                                | move to next sync\n                            ");
        com.samsung.android.sum.core.SLog.i(r5, com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r1.toString()));
        r4.seekTo(((java.lang.Number) r14.e).longValue(), 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x03b0, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x03b1, code lost:
        r2 = r0;
        r1 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x03b6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x03b7, code lost:
        r31 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x03ba, code lost:
        r31 = r3;
        r35 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x03c0, code lost:
        if (r0.maxTransferDurationSec <= 0) goto L_0x03e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x03cf, code lost:
        if (((int) java.util.concurrent.TimeUnit.MICROSECONDS.toSeconds(r11 - r7.presentationTimeUs)) <= r0.maxTransferDurationSec) goto L_0x03e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x03d1, code lost:
        r4.seekTo(r9, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x03d5, code lost:
        r1 = r42;
        r7 = r28;
        r13 = r29;
        r3 = r31;
        r14 = r32;
        r6 = r33;
        r5 = r34;
        r15 = r35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x03e7, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x03e8, code lost:
        r3 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x03ec, code lost:
        r31 = r3;
        r34 = r5;
        r33 = r6;
        r35 = r15;
        r1 = r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x03fb, code lost:
        if (r4.readSampleData(r3, 0) < 0) goto L_0x048b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x03fd, code lost:
        r7.size = r3.limit();
        r7.offset = 0;
        r13 = r33;
        r0.muxer.writeSampleData(r13, r3, r7);
        r14 = TAG;
        r15 = com.samsung.android.motionphoto.utils.v2.CommonsKt.asString(r7);
        r5 = r0.lastTransferredTimestampUs;
        r29 = r1;
        r1 = new java.lang.StringBuilder();
        r1.append("[");
        r1.append(r13);
        r30 = r2;
        r1.append("][");
        r2 = r35;
        r1.append(r2);
        r33 = r9;
        r1.append("] write sample:\n                        | info=");
        r1.append(r15);
        r1.append(",\n                        | processedFrames=");
        r9 = r29 + 1;
        r1.append(r9);
        r1.append("\n                        | lastMotionTimestampUs=");
        r1.append(r5);
        r1.append(" us \n                    ");
        com.samsung.android.sum.core.SLog.d(r14, com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r1.toString()));
        r5 = r7.presentationTimeUs;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0461, code lost:
        if (r5 >= r19) goto L_0x0465;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0463, code lost:
        r19 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0467, code lost:
        if (r5 <= r21) goto L_0x046b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0469, code lost:
        r21 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x046b, code lost:
        r1 = r0.eventListener;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x046d, code lost:
        if (r1 == null) goto L_0x0475;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x046f, code lost:
        r5 = r42;
        r1.onEachFrameTransferred(r5, r2, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0475, code lost:
        r5 = r42;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0477, code lost:
        r4.advance();
        r15 = r2;
        r1 = r5;
        r6 = r13;
        r7 = r29;
        r2 = r30;
        r14 = r32;
        r5 = r3;
        r13 = r9;
        r3 = r31;
        r9 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0492, code lost:
        throw new java.lang.IllegalArgumentException("Failed requirement.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0493, code lost:
        r30 = r2;
        r31 = r3;
        r3 = r5;
        r13 = r6;
        r28 = r7;
        r33 = r9;
        r32 = r14;
        r2 = r15;
        r5 = r1;
        r1 = TAG;
        com.samsung.android.sum.core.SLog.i(r1, com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine("[" + r2 + "]reached EOS:\n                            | sampleTimestampUs=" + r4.getSampleTime() + ", \n                            | minLastTimestampUs=" + r11 + ",\n                        "));
        r6 = r0.builder.getMode$motionphoto_utils_v2_0_17_release();
        r7 = com.samsung.android.motionphoto.utils.v2.video.VideoTransfer.TransferMode.STRICT_SUB_VIDEO;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x04da, code lost:
        if (r6 != r7) goto L_0x04e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x04dc, code lost:
        r6 = r0.muxer;
        r8 = new android.media.MediaCodec.BufferInfo();
        r8.flags = 4;
        r6.writeSampleData(r13, r3, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x04e9, code lost:
        r3 = r32;
        r4.unselectTrack(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x04f4, code lost:
        if (r0.builder.getMode$motionphoto_utils_v2_0_17_release() != r7) goto L_0x051a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x04f6, code lost:
        if (r28 == false) goto L_0x04fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0519, code lost:
        throw new java.lang.IllegalArgumentException(("no frame written in this track[" + r2 + "], cancel this").toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x051a, code lost:
        if (r28 != false) goto L_0x0574;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x051c, code lost:
        com.samsung.android.sum.core.SLog.w(r1, "no IDR frame written in this track[" + r2 + "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x053c, code lost:
        com.samsung.android.sum.core.SLog.w(TAG, "target mime types are [" + ne.C1194l.R0(r0.targetMimes, (java.lang.String) null, (java.lang.String) null, (java.lang.String) null, (Ae.b) null, 63) + "], but current is " + r2 + ", skip this track");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r8 = r5.get(ne.C1194l.K0(r5.keySet()));
        kotlin.jvm.internal.j.b(r8);
        r13 = ((android.media.MediaFormat) ((me.i) r8).e).getInteger("max-input-size");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0248, code lost:
        if (((java.util.Collection) r6).isEmpty() != false) goto L_0x024a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x024a, code lost:
        r30 = r2;
        r31 = r3;
        r25 = r5;
        r26 = r8;
        r33 = r9;
        r27 = r13;
        r3 = r14;
        r2 = r15;
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0271, code lost:
        r6 = r5.get(r15);
        kotlin.jvm.internal.j.b(r6);
        r6 = ((java.lang.Number) ((me.i) r6).d).intValue();
        r7 = TAG;
        r25 = r5;
        r5 = new java.lang.StringBuilder();
        r26 = r8;
        r5.append("mimeType=");
        r5.append(r15);
        r5.append(", muxTrackIndex=");
        r5.append(r6);
        r5.append(", maxInputSize=");
        r5.append(r13);
        com.samsung.android.sum.core.SLog.d(r7, r5.toString());
        r4.selectTrack(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02b3, code lost:
        if (r9 <= r17) goto L_0x02d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r5 = new java.lang.StringBuilder();
        r5.append("[");
        r5.append(r15);
        r27 = r13;
        r5.append("] seek to prev sync from ");
        r5.append(r9);
        com.samsung.android.sum.core.SLog.i(r7, r5.toString());
        r4.seekTo(r9, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02d6, code lost:
        r27 = r13;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:74:0x01f9 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008a A[SYNTHETIC, Splitter:B:17:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b9 A[SYNTHETIC, Splitter:B:22:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c3 A[SYNTHETIC, Splitter:B:27:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0119 A[SYNTHETIC, Splitter:B:45:0x0119] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0190 A[SYNTHETIC, Splitter:B:61:0x0190] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01c3 A[Catch:{ Exception -> 0x01f9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.motionphoto.utils.v2.video.Result writeTrack(com.samsung.android.motionphoto.utils.v2.MPFile r42) {
        /*
            r41 = this;
            r0 = r41
            r1 = r42
            java.lang.String r2 = ":"
            java.lang.String r3 = "source"
            kotlin.jvm.internal.j.e(r1, r3)
            java.lang.String r3 = TAG
            long r4 = r1.getDistinctDurationMs()
            long r6 = r0.lastTransferredTimestampUs
            java.lang.String r8 = r1.path()
            java.lang.String r9 = "writeVideo: dur="
            java.lang.String r10 = ", lastTrackTimestampUs="
            java.lang.StringBuilder r4 = N2.j.j(r4, r9, r10)
            r4.append(r6)
            java.lang.String r5 = ", path="
            r4.append(r5)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r3, (java.lang.String) r4)
            java.io.FileInputStream r3 = r1.newInputFileStream()
            android.media.MediaExtractor r4 = new android.media.MediaExtractor     // Catch:{ all -> 0x05bf }
            r4.<init>()     // Catch:{ all -> 0x05bf }
            com.samsung.android.motionphoto.utils.v2.MimeType r5 = r1.getMimeType()     // Catch:{ all -> 0x05bf }
            boolean r5 = r5.isImage()     // Catch:{ all -> 0x05bf }
            if (r5 == 0) goto L_0x006d
            com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl r5 = r1.getMpInfo()     // Catch:{ all -> 0x0068 }
            boolean r5 = r5.isValid()     // Catch:{ all -> 0x0068 }
            if (r5 == 0) goto L_0x006d
            java.io.FileDescriptor r5 = r3.getFD()     // Catch:{ all -> 0x0068 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl r6 = r1.getMpInfo()     // Catch:{ all -> 0x0068 }
            long r6 = r6.getVideoPosition()     // Catch:{ all -> 0x0068 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl r8 = r1.getMpInfo()     // Catch:{ all -> 0x0068 }
            long r8 = r8.getVideoSize()     // Catch:{ all -> 0x0068 }
            r4.setDataSource(r5, r6, r8)     // Catch:{ all -> 0x0068 }
            goto L_0x007e
        L_0x0068:
            r0 = move-exception
            r2 = r0
            r1 = r3
            goto L_0x05ce
        L_0x006d:
            com.samsung.android.motionphoto.utils.v2.MimeType r5 = r1.getMimeType()     // Catch:{ all -> 0x05bf }
            boolean r5 = r5.isVideo()     // Catch:{ all -> 0x05bf }
            if (r5 == 0) goto L_0x05c2
            java.io.FileDescriptor r5 = r3.getFD()     // Catch:{ all -> 0x05bf }
            r4.setDataSource(r5)     // Catch:{ all -> 0x05bf }
        L_0x007e:
            java.util.Map<java.lang.String, me.i> r5 = r0.trackInfos     // Catch:{ all -> 0x05bf }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x05bf }
            java.lang.String r6 = "mime"
            java.lang.String r7 = "getTrackFormat(...)"
            if (r5 == 0) goto L_0x00b9
            java.util.LinkedHashMap r5 = new java.util.LinkedHashMap     // Catch:{ all -> 0x0068 }
            r5.<init>()     // Catch:{ all -> 0x0068 }
            int r9 = r4.getTrackCount()     // Catch:{ all -> 0x0068 }
            r10 = 0
        L_0x0094:
            if (r10 >= r9) goto L_0x00bb
            android.media.MediaFormat r11 = r4.getTrackFormat(r10)     // Catch:{ all -> 0x0068 }
            kotlin.jvm.internal.j.d(r11, r7)     // Catch:{ all -> 0x0068 }
            java.lang.String r12 = r11.getString(r6)     // Catch:{ all -> 0x0068 }
            kotlin.jvm.internal.j.b(r12)     // Catch:{ all -> 0x0068 }
            android.media.MediaMuxer r13 = r0.muxer     // Catch:{ all -> 0x0068 }
            int r13 = r13.addTrack(r11)     // Catch:{ all -> 0x0068 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x0068 }
            me.i r14 = new me.i     // Catch:{ all -> 0x0068 }
            r14.<init>(r13, r11)     // Catch:{ all -> 0x0068 }
            r5.put(r12, r14)     // Catch:{ all -> 0x0068 }
            int r10 = r10 + 1
            goto L_0x0094
        L_0x00b9:
            java.util.Map<java.lang.String, me.i> r5 = r0.trackInfos     // Catch:{ all -> 0x05bf }
        L_0x00bb:
            long r9 = r0.firstTransferTimestampUs     // Catch:{ all -> 0x05bf }
            r11 = 0
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 != 0) goto L_0x010f
            java.util.Set r9 = r5.entrySet()     // Catch:{ all -> 0x0068 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0068 }
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x0068 }
            if (r10 != 0) goto L_0x00d3
            r10 = 0
            goto L_0x0109
        L_0x00d3:
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x0068 }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ all -> 0x0068 }
            java.lang.Object r10 = r10.getKey()     // Catch:{ all -> 0x0068 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x0068 }
            long r15 = r1.getVideoFirstFrameTimestampUs(r10)     // Catch:{ all -> 0x0068 }
            java.lang.Long r10 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x0068 }
        L_0x00e7:
            boolean r13 = r9.hasNext()     // Catch:{ all -> 0x0068 }
            if (r13 == 0) goto L_0x0109
            java.lang.Object r13 = r9.next()     // Catch:{ all -> 0x0068 }
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13     // Catch:{ all -> 0x0068 }
            java.lang.Object r13 = r13.getKey()     // Catch:{ all -> 0x0068 }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ all -> 0x0068 }
            long r15 = r1.getVideoFirstFrameTimestampUs(r13)     // Catch:{ all -> 0x0068 }
            java.lang.Long r13 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x0068 }
            int r15 = r10.compareTo(r13)     // Catch:{ all -> 0x0068 }
            if (r15 <= 0) goto L_0x00e7
            r10 = r13
            goto L_0x00e7
        L_0x0109:
            if (r10 == 0) goto L_0x0111
            long r9 = r10.longValue()     // Catch:{ all -> 0x0068 }
        L_0x010f:
            r15 = r11
            goto L_0x0113
        L_0x0111:
            r9 = r11
            r15 = r9
        L_0x0113:
            long r11 = r0.lastTransferTimestampUs     // Catch:{ all -> 0x05bf }
            int r13 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r13 != 0) goto L_0x0167
            java.util.Set r11 = r5.entrySet()     // Catch:{ all -> 0x0068 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x0068 }
            boolean r12 = r11.hasNext()     // Catch:{ all -> 0x0068 }
            if (r12 != 0) goto L_0x0129
            r12 = 0
            goto L_0x015f
        L_0x0129:
            java.lang.Object r12 = r11.next()     // Catch:{ all -> 0x0068 }
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12     // Catch:{ all -> 0x0068 }
            java.lang.Object r12 = r12.getKey()     // Catch:{ all -> 0x0068 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0068 }
            long r12 = r1.getDistinctLastTimestampUs(r12)     // Catch:{ all -> 0x0068 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0068 }
        L_0x013d:
            boolean r13 = r11.hasNext()     // Catch:{ all -> 0x0068 }
            if (r13 == 0) goto L_0x015f
            java.lang.Object r13 = r11.next()     // Catch:{ all -> 0x0068 }
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13     // Catch:{ all -> 0x0068 }
            java.lang.Object r13 = r13.getKey()     // Catch:{ all -> 0x0068 }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ all -> 0x0068 }
            long r17 = r1.getDistinctLastTimestampUs(r13)     // Catch:{ all -> 0x0068 }
            java.lang.Long r13 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x0068 }
            int r17 = r12.compareTo(r13)     // Catch:{ all -> 0x0068 }
            if (r17 <= 0) goto L_0x013d
            r12 = r13
            goto L_0x013d
        L_0x015f:
            if (r12 == 0) goto L_0x0166
            long r11 = r12.longValue()     // Catch:{ all -> 0x0068 }
            goto L_0x0167
        L_0x0166:
            r11 = r15
        L_0x0167:
            java.lang.String r13 = TAG     // Catch:{ all -> 0x05bf }
            r17 = r15
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x05bf }
            r15.<init>()     // Catch:{ all -> 0x05bf }
            java.lang.String r14 = "firstTransferTimestampUs="
            r15.append(r14)     // Catch:{ all -> 0x05bf }
            r15.append(r9)     // Catch:{ all -> 0x05bf }
            java.lang.String r14 = " us, lastTransferTimestampUs="
            r15.append(r14)     // Catch:{ all -> 0x05bf }
            r15.append(r11)     // Catch:{ all -> 0x05bf }
            java.lang.String r14 = " us"
            r15.append(r14)     // Catch:{ all -> 0x05bf }
            java.lang.String r14 = r15.toString()     // Catch:{ all -> 0x05bf }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r13, (java.lang.String) r14)     // Catch:{ all -> 0x05bf }
            Ae.d r13 = r0.adjustTimeRange     // Catch:{ all -> 0x05bf }
            if (r13 == 0) goto L_0x01b5
            java.lang.Long r14 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0068 }
            java.lang.Long r15 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x0068 }
            java.util.List<me.i> r8 = r0.trimList     // Catch:{ all -> 0x0068 }
            kotlin.jvm.internal.j.b(r8)     // Catch:{ all -> 0x0068 }
            java.lang.Object r8 = r13.invoke(r14, r15, r8)     // Catch:{ all -> 0x0068 }
            me.i r8 = (me.i) r8     // Catch:{ all -> 0x0068 }
            if (r8 == 0) goto L_0x01b5
            java.lang.Object r9 = r8.d     // Catch:{ all -> 0x0068 }
            java.lang.Number r9 = (java.lang.Number) r9     // Catch:{ all -> 0x0068 }
            long r9 = r9.longValue()     // Catch:{ all -> 0x0068 }
            java.lang.Object r8 = r8.e     // Catch:{ all -> 0x0068 }
            java.lang.Number r8 = (java.lang.Number) r8     // Catch:{ all -> 0x0068 }
            long r11 = r8.longValue()     // Catch:{ all -> 0x0068 }
        L_0x01b5:
            java.util.Set r8 = r5.keySet()     // Catch:{ Exception -> 0x01f9 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x01f9 }
        L_0x01bd:
            boolean r13 = r8.hasNext()     // Catch:{ Exception -> 0x01f9 }
            if (r13 == 0) goto L_0x01f1
            java.lang.Object r13 = r8.next()     // Catch:{ Exception -> 0x01f9 }
            r14 = r13
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ Exception -> 0x01f9 }
            java.lang.String r15 = "video"
            boolean r14 = Tf.v.t0(r14, r15)     // Catch:{ Exception -> 0x01f9 }
            if (r14 == 0) goto L_0x01bd
            java.lang.Object r8 = r5.get(r13)     // Catch:{ Exception -> 0x01f9 }
            kotlin.jvm.internal.j.b(r8)     // Catch:{ Exception -> 0x01f9 }
            me.i r8 = (me.i) r8     // Catch:{ Exception -> 0x01f9 }
            java.lang.Object r8 = r8.e     // Catch:{ Exception -> 0x01f9 }
            android.media.MediaFormat r8 = (android.media.MediaFormat) r8     // Catch:{ Exception -> 0x01f9 }
            java.lang.String r13 = "width"
            int r13 = r8.getInteger(r13)     // Catch:{ Exception -> 0x01f9 }
            java.lang.String r14 = "height"
            int r8 = r8.getInteger(r14)     // Catch:{ Exception -> 0x01f9 }
            int r13 = r13 * r8
            int r13 = r13 * 2
            goto L_0x0214
        L_0x01f1:
            java.util.NoSuchElementException r8 = new java.util.NoSuchElementException     // Catch:{ Exception -> 0x01f9 }
            java.lang.String r13 = "Collection contains no element matching the predicate."
            r8.<init>(r13)     // Catch:{ Exception -> 0x01f9 }
            throw r8     // Catch:{ Exception -> 0x01f9 }
        L_0x01f9:
            java.util.Set r8 = r5.keySet()     // Catch:{ all -> 0x05bf }
            java.lang.Object r8 = ne.C1194l.K0(r8)     // Catch:{ all -> 0x05bf }
            java.lang.Object r8 = r5.get(r8)     // Catch:{ all -> 0x05bf }
            kotlin.jvm.internal.j.b(r8)     // Catch:{ all -> 0x05bf }
            me.i r8 = (me.i) r8     // Catch:{ all -> 0x05bf }
            java.lang.Object r8 = r8.e     // Catch:{ all -> 0x05bf }
            android.media.MediaFormat r8 = (android.media.MediaFormat) r8     // Catch:{ all -> 0x05bf }
            java.lang.String r13 = "max-input-size"
            int r13 = r8.getInteger(r13)     // Catch:{ all -> 0x05bf }
        L_0x0214:
            r0.start()     // Catch:{ all -> 0x05bf }
            int r8 = r4.getTrackCount()     // Catch:{ all -> 0x05bf }
            r14 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r19 = r14
            r21 = r17
            r14 = 0
        L_0x0225:
            if (r14 >= r8) goto L_0x0589
            android.media.MediaFormat r15 = r4.getTrackFormat(r14)     // Catch:{ all -> 0x03b6 }
            kotlin.jvm.internal.j.d(r15, r7)     // Catch:{ all -> 0x03b6 }
            java.lang.String r15 = r15.getString(r6)     // Catch:{ all -> 0x03b6 }
            kotlin.jvm.internal.j.b(r15)     // Catch:{ all -> 0x03b6 }
            r23 = r6
            java.util.List<java.lang.String> r6 = r0.targetMimes     // Catch:{ all -> 0x03b6 }
            java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch:{ all -> 0x03b6 }
            r24 = r7
            boolean r7 = r6 instanceof java.util.Collection     // Catch:{ all -> 0x03b6 }
            if (r7 == 0) goto L_0x025b
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7     // Catch:{ all -> 0x0068 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0068 }
            if (r7 == 0) goto L_0x025b
        L_0x024a:
            r30 = r2
            r31 = r3
            r25 = r5
            r26 = r8
            r33 = r9
            r27 = r13
            r3 = r14
            r2 = r15
            r5 = r1
            goto L_0x053c
        L_0x025b:
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x03b6 }
        L_0x025f:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x03b6 }
            if (r7 == 0) goto L_0x024a
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x03b6 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x03b6 }
            boolean r7 = Tf.v.t0(r15, r7)     // Catch:{ all -> 0x03b6 }
            if (r7 == 0) goto L_0x0536
            java.lang.Object r6 = r5.get(r15)     // Catch:{ all -> 0x03b6 }
            kotlin.jvm.internal.j.b(r6)     // Catch:{ all -> 0x03b6 }
            me.i r6 = (me.i) r6     // Catch:{ all -> 0x03b6 }
            java.lang.Object r6 = r6.d     // Catch:{ all -> 0x03b6 }
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ all -> 0x03b6 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x03b6 }
            java.lang.String r7 = TAG     // Catch:{ all -> 0x03b6 }
            r25 = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b6 }
            r5.<init>()     // Catch:{ all -> 0x03b6 }
            r26 = r8
            java.lang.String r8 = "mimeType="
            r5.append(r8)     // Catch:{ all -> 0x03b6 }
            r5.append(r15)     // Catch:{ all -> 0x03b6 }
            java.lang.String r8 = ", muxTrackIndex="
            r5.append(r8)     // Catch:{ all -> 0x03b6 }
            r5.append(r6)     // Catch:{ all -> 0x03b6 }
            java.lang.String r8 = ", maxInputSize="
            r5.append(r8)     // Catch:{ all -> 0x03b6 }
            r5.append(r13)     // Catch:{ all -> 0x03b6 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x03b6 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r7, (java.lang.String) r5)     // Catch:{ all -> 0x03b6 }
            r4.selectTrack(r14)     // Catch:{ all -> 0x03b6 }
            int r5 = (r9 > r17 ? 1 : (r9 == r17 ? 0 : -1))
            java.lang.String r8 = "["
            if (r5 <= 0) goto L_0x02d6
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r5.<init>()     // Catch:{ all -> 0x0068 }
            r5.append(r8)     // Catch:{ all -> 0x0068 }
            r5.append(r15)     // Catch:{ all -> 0x0068 }
            r27 = r13
            java.lang.String r13 = "] seek to prev sync from "
            r5.append(r13)     // Catch:{ all -> 0x0068 }
            r5.append(r9)     // Catch:{ all -> 0x0068 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0068 }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r7, (java.lang.String) r5)     // Catch:{ all -> 0x0068 }
            r5 = 0
            r4.seekTo(r9, r5)     // Catch:{ all -> 0x0068 }
            goto L_0x02d8
        L_0x02d6:
            r27 = r13
        L_0x02d8:
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocateDirect(r27)     // Catch:{ all -> 0x03b6 }
            r7 = 0
            r13 = 0
        L_0x02de:
            long r28 = r4.getSampleTime()     // Catch:{ all -> 0x03b6 }
            int r30 = (r17 > r28 ? 1 : (r17 == r28 ? 0 : -1))
            if (r30 > 0) goto L_0x0493
            int r28 = (r28 > r11 ? 1 : (r28 == r11 ? 0 : -1))
            if (r28 >= 0) goto L_0x0493
            r28 = r7
            android.media.MediaCodec$BufferInfo r7 = new android.media.MediaCodec$BufferInfo     // Catch:{ all -> 0x03b6 }
            r7.<init>()     // Catch:{ all -> 0x03b6 }
            r29 = r13
            int r13 = r4.getSampleFlags()     // Catch:{ all -> 0x03b6 }
            int r13 = r0.convertExtractorFlagsToMediaCodecFlags(r13)     // Catch:{ all -> 0x03b6 }
            r7.flags = r13     // Catch:{ all -> 0x03b6 }
            long r30 = r4.getSampleTime()     // Catch:{ all -> 0x03b6 }
            r32 = r14
            long r13 = r0.lastTransferredTimestampUs     // Catch:{ all -> 0x03b6 }
            long r13 = r30 + r13
            r7.presentationTimeUs = r13     // Catch:{ all -> 0x03b6 }
            if (r28 != 0) goto L_0x03ec
            int r13 = r7.flags     // Catch:{ all -> 0x03b6 }
            r30 = 1
            int[] r14 = new int[]{r30}     // Catch:{ all -> 0x03b6 }
            boolean r13 = com.samsung.android.motionphoto.utils.v2.CommonsKt.containsAnyFlag(r13, r14)     // Catch:{ all -> 0x03b6 }
            if (r13 == 0) goto L_0x03ec
            java.util.List<me.i> r13 = r0.trimList     // Catch:{ all -> 0x03b6 }
            if (r13 == 0) goto L_0x0358
            java.lang.Iterable r13 = (java.lang.Iterable) r13     // Catch:{ all -> 0x0068 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ all -> 0x0068 }
        L_0x0323:
            boolean r14 = r13.hasNext()     // Catch:{ all -> 0x0068 }
            if (r14 == 0) goto L_0x0350
            java.lang.Object r14 = r13.next()     // Catch:{ all -> 0x0068 }
            r31 = r13
            r13 = r14
            me.i r13 = (me.i) r13     // Catch:{ all -> 0x0068 }
            r34 = r5
            r33 = r6
            long r5 = r7.presentationTimeUs     // Catch:{ all -> 0x0068 }
            java.lang.Object r13 = r13.e     // Catch:{ all -> 0x0068 }
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ all -> 0x0068 }
            long r35 = r13.longValue()     // Catch:{ all -> 0x0068 }
            int r5 = (r5 > r35 ? 1 : (r5 == r35 ? 0 : -1))
            if (r5 > 0) goto L_0x0349
            int r5 = (r35 > r11 ? 1 : (r35 == r11 ? 0 : -1))
            if (r5 >= 0) goto L_0x0349
            goto L_0x0355
        L_0x0349:
            r13 = r31
            r6 = r33
            r5 = r34
            goto L_0x0323
        L_0x0350:
            r34 = r5
            r33 = r6
            r14 = 0
        L_0x0355:
            me.i r14 = (me.i) r14     // Catch:{ all -> 0x0068 }
            goto L_0x035d
        L_0x0358:
            r34 = r5
            r33 = r6
            r14 = 0
        L_0x035d:
            if (r14 == 0) goto L_0x03ba
            java.lang.String r5 = TAG     // Catch:{ all -> 0x03b6 }
            long r6 = r7.presentationTimeUs     // Catch:{ all -> 0x03b6 }
            java.lang.Object r13 = r14.d     // Catch:{ all -> 0x03b6 }
            r31 = r3
            java.lang.Object r3 = r14.e     // Catch:{ all -> 0x03b0 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b0 }
            r1.<init>()     // Catch:{ all -> 0x03b0 }
            r1.append(r8)     // Catch:{ all -> 0x03b0 }
            r1.append(r15)     // Catch:{ all -> 0x03b0 }
            r35 = r15
            java.lang.String r15 = "]range("
            r1.append(r15)     // Catch:{ all -> 0x03b0 }
            r1.append(r6)     // Catch:{ all -> 0x03b0 }
            r1.append(r2)     // Catch:{ all -> 0x03b0 }
            r1.append(r11)     // Catch:{ all -> 0x03b0 }
            java.lang.String r6 = ")\n                                | contains trim-range("
            r1.append(r6)     // Catch:{ all -> 0x03b0 }
            r1.append(r13)     // Catch:{ all -> 0x03b0 }
            r1.append(r2)     // Catch:{ all -> 0x03b0 }
            r1.append(r3)     // Catch:{ all -> 0x03b0 }
            java.lang.String r3 = "),\n                                | move to next sync\n                            "
            r1.append(r3)     // Catch:{ all -> 0x03b0 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x03b0 }
            java.lang.String r1 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r1)     // Catch:{ all -> 0x03b0 }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r5, (java.lang.String) r1)     // Catch:{ all -> 0x03b0 }
            java.lang.Object r1 = r14.e     // Catch:{ all -> 0x03b0 }
            java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ all -> 0x03b0 }
            long r5 = r1.longValue()     // Catch:{ all -> 0x03b0 }
            r1 = r30
            r4.seekTo(r5, r1)     // Catch:{ all -> 0x03b0 }
            goto L_0x03d5
        L_0x03b0:
            r0 = move-exception
        L_0x03b1:
            r2 = r0
            r1 = r31
            goto L_0x05ce
        L_0x03b6:
            r0 = move-exception
            r31 = r3
            goto L_0x03b1
        L_0x03ba:
            r31 = r3
            r35 = r15
            int r1 = r0.maxTransferDurationSec     // Catch:{ all -> 0x03b0 }
            if (r1 <= 0) goto L_0x03e7
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MICROSECONDS     // Catch:{ all -> 0x03b0 }
            long r5 = r7.presentationTimeUs     // Catch:{ all -> 0x03b0 }
            long r5 = r11 - r5
            long r5 = r1.toSeconds(r5)     // Catch:{ all -> 0x03b0 }
            int r1 = (int) r5     // Catch:{ all -> 0x03b0 }
            int r3 = r0.maxTransferDurationSec     // Catch:{ all -> 0x03b0 }
            if (r1 <= r3) goto L_0x03e7
            r1 = 1
            r4.seekTo(r9, r1)     // Catch:{ all -> 0x03b0 }
        L_0x03d5:
            r1 = r42
            r7 = r28
            r13 = r29
            r3 = r31
            r14 = r32
            r6 = r33
            r5 = r34
            r15 = r35
            goto L_0x02de
        L_0x03e7:
            r1 = 1
        L_0x03e8:
            r3 = r34
            r5 = 0
            goto L_0x03f7
        L_0x03ec:
            r31 = r3
            r34 = r5
            r33 = r6
            r35 = r15
            r1 = r28
            goto L_0x03e8
        L_0x03f7:
            int r6 = r4.readSampleData(r3, r5)     // Catch:{ all -> 0x03b0 }
            if (r6 < 0) goto L_0x048b
            int r6 = r3.limit()     // Catch:{ all -> 0x03b0 }
            r7.size = r6     // Catch:{ all -> 0x03b0 }
            r7.offset = r5     // Catch:{ all -> 0x03b0 }
            android.media.MediaMuxer r6 = r0.muxer     // Catch:{ all -> 0x03b0 }
            r13 = r33
            r6.writeSampleData(r13, r3, r7)     // Catch:{ all -> 0x03b0 }
            int r6 = r29 + 1
            java.lang.String r14 = TAG     // Catch:{ all -> 0x03b0 }
            java.lang.String r15 = com.samsung.android.motionphoto.utils.v2.CommonsKt.asString(r7)     // Catch:{ all -> 0x03b0 }
            r28 = r6
            long r5 = r0.lastTransferredTimestampUs     // Catch:{ all -> 0x03b0 }
            r29 = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b0 }
            r1.<init>()     // Catch:{ all -> 0x03b0 }
            r1.append(r8)     // Catch:{ all -> 0x03b0 }
            r1.append(r13)     // Catch:{ all -> 0x03b0 }
            r30 = r2
            java.lang.String r2 = "]["
            r1.append(r2)     // Catch:{ all -> 0x03b0 }
            r2 = r35
            r1.append(r2)     // Catch:{ all -> 0x03b0 }
            r33 = r9
            java.lang.String r9 = "] write sample:\n                        | info="
            r1.append(r9)     // Catch:{ all -> 0x03b0 }
            r1.append(r15)     // Catch:{ all -> 0x03b0 }
            java.lang.String r9 = ",\n                        | processedFrames="
            r1.append(r9)     // Catch:{ all -> 0x03b0 }
            r9 = r28
            r1.append(r9)     // Catch:{ all -> 0x03b0 }
            java.lang.String r10 = "\n                        | lastMotionTimestampUs="
            r1.append(r10)     // Catch:{ all -> 0x03b0 }
            r1.append(r5)     // Catch:{ all -> 0x03b0 }
            java.lang.String r5 = " us \n                    "
            r1.append(r5)     // Catch:{ all -> 0x03b0 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x03b0 }
            java.lang.String r1 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r1)     // Catch:{ all -> 0x03b0 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r14, (java.lang.String) r1)     // Catch:{ all -> 0x03b0 }
            long r5 = r7.presentationTimeUs     // Catch:{ all -> 0x03b0 }
            int r1 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r1 >= 0) goto L_0x0465
            r19 = r5
        L_0x0465:
            int r1 = (r5 > r21 ? 1 : (r5 == r21 ? 0 : -1))
            if (r1 <= 0) goto L_0x046b
            r21 = r5
        L_0x046b:
            com.samsung.android.motionphoto.utils.v2.video.EventListener r1 = r0.eventListener     // Catch:{ all -> 0x03b0 }
            if (r1 == 0) goto L_0x0475
            r5 = r42
            r1.onEachFrameTransferred(r5, r2, r7)     // Catch:{ all -> 0x03b0 }
            goto L_0x0477
        L_0x0475:
            r5 = r42
        L_0x0477:
            r4.advance()     // Catch:{ all -> 0x03b0 }
            r15 = r2
            r1 = r5
            r6 = r13
            r7 = r29
            r2 = r30
            r14 = r32
            r5 = r3
            r13 = r9
            r3 = r31
            r9 = r33
            goto L_0x02de
        L_0x048b:
            java.lang.String r0 = "Failed requirement."
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x03b0 }
            r1.<init>(r0)     // Catch:{ all -> 0x03b0 }
            throw r1     // Catch:{ all -> 0x03b0 }
        L_0x0493:
            r30 = r2
            r31 = r3
            r3 = r5
            r13 = r6
            r28 = r7
            r33 = r9
            r32 = r14
            r2 = r15
            r5 = r1
            java.lang.String r1 = TAG     // Catch:{ all -> 0x03b0 }
            long r6 = r4.getSampleTime()     // Catch:{ all -> 0x03b0 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b0 }
            r9.<init>()     // Catch:{ all -> 0x03b0 }
            r9.append(r8)     // Catch:{ all -> 0x03b0 }
            r9.append(r2)     // Catch:{ all -> 0x03b0 }
            java.lang.String r8 = "]reached EOS:\n                            | sampleTimestampUs="
            r9.append(r8)     // Catch:{ all -> 0x03b0 }
            r9.append(r6)     // Catch:{ all -> 0x03b0 }
            java.lang.String r6 = ", \n                            | minLastTimestampUs="
            r9.append(r6)     // Catch:{ all -> 0x03b0 }
            r9.append(r11)     // Catch:{ all -> 0x03b0 }
            java.lang.String r6 = ",\n                        "
            r9.append(r6)     // Catch:{ all -> 0x03b0 }
            java.lang.String r6 = r9.toString()     // Catch:{ all -> 0x03b0 }
            java.lang.String r6 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r6)     // Catch:{ all -> 0x03b0 }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r1, (java.lang.String) r6)     // Catch:{ all -> 0x03b0 }
            com.samsung.android.motionphoto.utils.v2.video.VideoTransfer$Builder r6 = r0.builder     // Catch:{ all -> 0x03b0 }
            com.samsung.android.motionphoto.utils.v2.video.VideoTransfer$TransferMode r6 = r6.getMode$motionphoto_utils_v2_0_17_release()     // Catch:{ all -> 0x03b0 }
            com.samsung.android.motionphoto.utils.v2.video.VideoTransfer$TransferMode r7 = com.samsung.android.motionphoto.utils.v2.video.VideoTransfer.TransferMode.STRICT_SUB_VIDEO     // Catch:{ all -> 0x03b0 }
            if (r6 != r7) goto L_0x04e9
            android.media.MediaMuxer r6 = r0.muxer     // Catch:{ all -> 0x03b0 }
            android.media.MediaCodec$BufferInfo r8 = new android.media.MediaCodec$BufferInfo     // Catch:{ all -> 0x03b0 }
            r8.<init>()     // Catch:{ all -> 0x03b0 }
            r9 = 4
            r8.flags = r9     // Catch:{ all -> 0x03b0 }
            r6.writeSampleData(r13, r3, r8)     // Catch:{ all -> 0x03b0 }
        L_0x04e9:
            r3 = r32
            r4.unselectTrack(r3)     // Catch:{ all -> 0x03b0 }
            com.samsung.android.motionphoto.utils.v2.video.VideoTransfer$Builder r6 = r0.builder     // Catch:{ all -> 0x03b0 }
            com.samsung.android.motionphoto.utils.v2.video.VideoTransfer$TransferMode r6 = r6.getMode$motionphoto_utils_v2_0_17_release()     // Catch:{ all -> 0x03b0 }
            if (r6 != r7) goto L_0x051a
            if (r28 == 0) goto L_0x04fa
            goto L_0x0574
        L_0x04fa:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b0 }
            r0.<init>()     // Catch:{ all -> 0x03b0 }
            java.lang.String r1 = "no frame written in this track["
            r0.append(r1)     // Catch:{ all -> 0x03b0 }
            r0.append(r2)     // Catch:{ all -> 0x03b0 }
            java.lang.String r1 = "], cancel this"
            r0.append(r1)     // Catch:{ all -> 0x03b0 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x03b0 }
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x03b0 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x03b0 }
            r1.<init>(r0)     // Catch:{ all -> 0x03b0 }
            throw r1     // Catch:{ all -> 0x03b0 }
        L_0x051a:
            if (r28 != 0) goto L_0x0574
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b0 }
            r6.<init>()     // Catch:{ all -> 0x03b0 }
            java.lang.String r7 = "no IDR frame written in this track["
            r6.append(r7)     // Catch:{ all -> 0x03b0 }
            r6.append(r2)     // Catch:{ all -> 0x03b0 }
            java.lang.String r2 = "]"
            r6.append(r2)     // Catch:{ all -> 0x03b0 }
            java.lang.String r2 = r6.toString()     // Catch:{ all -> 0x03b0 }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x03b0 }
            goto L_0x0574
        L_0x0536:
            r30 = r2
            r31 = r3
            goto L_0x025f
        L_0x053c:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x03b0 }
            java.util.List<java.lang.String> r6 = r0.targetMimes     // Catch:{ all -> 0x03b0 }
            r35 = r6
            java.lang.Iterable r35 = (java.lang.Iterable) r35     // Catch:{ all -> 0x03b0 }
            r39 = 0
            r40 = 63
            r36 = 0
            r37 = 0
            r38 = 0
            java.lang.String r6 = ne.C1194l.R0(r35, r36, r37, r38, r39, r40)     // Catch:{ all -> 0x03b0 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b0 }
            r7.<init>()     // Catch:{ all -> 0x03b0 }
            java.lang.String r8 = "target mime types are ["
            r7.append(r8)     // Catch:{ all -> 0x03b0 }
            r7.append(r6)     // Catch:{ all -> 0x03b0 }
            java.lang.String r6 = "], but current is "
            r7.append(r6)     // Catch:{ all -> 0x03b0 }
            r7.append(r2)     // Catch:{ all -> 0x03b0 }
            java.lang.String r2 = ", skip this track"
            r7.append(r2)     // Catch:{ all -> 0x03b0 }
            java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x03b0 }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x03b0 }
        L_0x0574:
            int r14 = r3 + 1
            r1 = r5
            r6 = r23
            r7 = r24
            r5 = r25
            r8 = r26
            r13 = r27
            r2 = r30
            r3 = r31
            r9 = r33
            goto L_0x0225
        L_0x0589:
            r5 = r1
            r31 = r3
            r4.release()     // Catch:{ all -> 0x05ba }
            r1 = r31
            r2 = 0
            B1.a.g(r1, r2)
            long r11 = r21 - r19
            int r1 = (r11 > r17 ? 1 : (r11 == r17 ? 0 : -1))
            if (r1 <= 0) goto L_0x05a0
            long r1 = r0.lastTransferredTimestampUs
            long r1 = r1 + r11
            r0.lastTransferredTimestampUs = r1
        L_0x05a0:
            com.samsung.android.motionphoto.utils.v2.video.EventListener r1 = r0.eventListener
            if (r1 == 0) goto L_0x05a7
            r1.onTransferComplete(r5)
        L_0x05a7:
            com.samsung.android.motionphoto.utils.v2.video.Result r3 = new com.samsung.android.motionphoto.utils.v2.video.Result
            long r0 = r0.firstTransferTimestampUs
            long r9 = r0 - r19
            r14 = 39
            r15 = 0
            r4 = 0
            r5 = 0
            r7 = 0
            r13 = 0
            r3.<init>(r4, r5, r7, r9, r11, r13, r14, r15)
            return r3
        L_0x05ba:
            r0 = move-exception
            r1 = r31
        L_0x05bd:
            r2 = r0
            goto L_0x05ce
        L_0x05bf:
            r0 = move-exception
            r1 = r3
            goto L_0x05bd
        L_0x05c2:
            r1 = r3
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x05cc }
            java.lang.String r2 = "source is neither Video nor MotionPhoto"
            r0.<init>(r2)     // Catch:{ all -> 0x05cc }
            throw r0     // Catch:{ all -> 0x05cc }
        L_0x05cc:
            r0 = move-exception
            goto L_0x05bd
        L_0x05ce:
            throw r2     // Catch:{ all -> 0x05cf }
        L_0x05cf:
            r0 = move-exception
            B1.a.g(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.video.VideoTransfer.writeTrack(com.samsung.android.motionphoto.utils.v2.MPFile):com.samsung.android.motionphoto.utils.v2.video.Result");
    }
}
