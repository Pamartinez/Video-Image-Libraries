package com.samsung.android.motionphoto.utils.v2;

import Ae.b;
import Ae.c;
import Ae.d;
import Bd.C0725a;
import D9.a;
import Sf.q;
import Tf.w;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.ConditionVariable;
import android.os.SemSystemProperties;
import androidx.core.util.Pair;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.video.ExportEvent;
import com.samsung.android.motionphoto.utils.v2.video.ProgressUnit;
import com.samsung.android.motionphoto.utils.v2.video.Result;
import com.samsung.android.motionphoto.utils.v2.video.VideoTask;
import com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder;
import com.samsung.android.motionphoto.utils.v2.video.VideoTranscodingTask;
import com.samsung.android.motionphoto.utils.v2.video.VideoTransfer;
import com.samsung.android.motionphoto.utils.v2.video.VideoTransferringTask;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.message.Message;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.i;
import me.x;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000¥\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u000f*\u0001r\u0018\u0000 {2\u00060\u0001j\u0002`\u0002:\u0005|}~{B\u0007¢\u0006\u0004\b\u0003\u0010\u0004B\u0011\b\u0012\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0003\u0010\u0007J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rJ9\u0010\f\u001a\u00020\u000b2*\u0010\u0010\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e\"\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0004\b\f\u0010\u0011J)\u0010\f\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0004\b\f\u0010\u0015J9\u0010\f\u001a\u00020\u000b2*\u0010\u0019\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00160\u000e\"\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0004\b\f\u0010\u001aJ!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0012¢\u0006\u0004\b\u001c\u0010\u001dJ)\u0010\f\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u001e\u001a\u00020\b¢\u0006\u0004\b\f\u0010!J)\u0010\f\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\"0\u00122\u0006\u0010\u001e\u001a\u00020\"¢\u0006\u0004\b\f\u0010#J\u000f\u0010$\u001a\u00020\u000bH\u0016¢\u0006\u0004\b$\u0010\u0004J\r\u0010&\u001a\u00020%¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u000bH\u0002¢\u0006\u0004\b(\u0010\u0004J\u0017\u0010+\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020)H\u0002¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\u000bH\u0002¢\u0006\u0004\b-\u0010\u0004J\u0017\u0010/\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u001bH\u0002¢\u0006\u0004\b/\u00100J%\u00104\u001a\u0002032\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\u0006\u0010\u0006\u001a\u000202H\u0002¢\u0006\u0004\b4\u00105J3\u0010;\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002032\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000b09H\u0002¢\u0006\u0004\b;\u0010<R\u0016\u0010>\u001a\u00020=8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b>\u0010?R*\u0010B\u001a\u0016\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u000b\u0018\u00010@j\u0004\u0018\u0001`A8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010E\u001a\u0004\u0018\u00010D8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010H\u001a\u00020G8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010K\u001a\u00020J8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bK\u0010LR6\u0010O\u001a\"\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u000b\u0018\u00010Mj\u0004\u0018\u0001`N8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bO\u0010PR6\u0010R\u001a\"\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u000b\u0018\u00010Mj\u0004\u0018\u0001`Q8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bR\u0010PR6\u0010T\u001a\"\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u000b\u0018\u00010Mj\u0004\u0018\u0001`S8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bT\u0010PR6\u0010V\u001a\"\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u000b\u0018\u00010Mj\u0004\u0018\u0001`U8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bV\u0010PR0\u0010:\u001a\u001c\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u000b\u0018\u00010Wj\u0004\u0018\u0001`X8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b:\u0010YR\u001a\u0010\\\u001a\b\u0012\u0004\u0012\u00020[0Z8\u0002X\u0004¢\u0006\u0006\n\u0004\b\\\u0010]R\u001a\u0010`\u001a\b\u0012\u0004\u0012\u00020_0^8\u0002X\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u0016\u0010c\u001a\u00020b8\u0002@\u0002X.¢\u0006\u0006\n\u0004\bc\u0010dR\u0016\u0010e\u001a\u00020b8\u0002@\u0002X.¢\u0006\u0006\n\u0004\be\u0010dR\u0018\u0010g\u001a\u0004\u0018\u00010f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bg\u0010hR\u001b\u0010n\u001a\u00020i8BX\u0002¢\u0006\f\n\u0004\bj\u0010k\u001a\u0004\bl\u0010mR\u0018\u0010p\u001a\u0004\u0018\u00010o8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bp\u0010qR\u0014\u0010s\u001a\u00020r8\u0002X\u0004¢\u0006\u0006\n\u0004\bs\u0010tR\u0014\u0010w\u001a\u00020f8BX\u0004¢\u0006\u0006\u001a\u0004\bu\u0010vR\u0014\u0010z\u001a\u00020o8BX\u0004¢\u0006\u0006\u001a\u0004\bx\u0010y¨\u0006\u0001"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionScrap;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;", "builder", "(Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;)V", "Ljava/io/File;", "src", "dst", "Lme/x;", "export", "(Ljava/io/File;Ljava/io/File;)V", "", "Landroidx/core/util/Pair;", "files", "([Landroidx/core/util/Pair;)V", "", "inputs", "outputs", "(Ljava/util/List;Ljava/util/List;)V", "Lme/i;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfo;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "motionPhotoInfos", "([Lme/i;)V", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "computeDistinctDuration", "(Ljava/util/List;)Ljava/util/List;", "output", "Ljava/util/concurrent/Future;", "Lcom/samsung/android/motionphoto/utils/v2/video/Result;", "(Ljava/util/List;Ljava/io/File;)Ljava/util/concurrent/Future;", "", "(Ljava/util/List;Ljava/lang/String;)Ljava/util/concurrent/Future;", "close", "", "cancel", "()Z", "reportProgress", "Lcom/samsung/android/motionphoto/utils/v2/video/ExportEvent;", "event", "notifyComplete", "(Lcom/samsung/android/motionphoto/utils/v2/video/ExportEvent;)V", "closeSafely", "mpFile", "parseVideoInfo", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;)V", "mpFiles", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;", "Landroid/media/MediaFormat;", "prepareTasks", "(Ljava/util/List;Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer$Builder;)Landroid/media/MediaFormat;", "", "numTasks", "format", "Lkotlin/Function0;", "onCompleteListener", "executeTasks", "(ILandroid/media/MediaFormat;LAe/a;)Ljava/util/concurrent/Future;", "Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;", "progressUnit", "Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;", "Lkotlin/Function1;", "Lcom/samsung/android/motionphoto/utils/v2/OnProgressEventListener;", "onProgressEventListener", "LAe/b;", "Lcom/samsung/android/motionphoto/utils/v2/OnProgressListener;", "onProgressListener", "Lcom/samsung/android/motionphoto/utils/v2/OnProgressListener;", "Lcom/samsung/android/motionphoto/utils/v2/ProgressMeasureStrategy;", "progressMeasureStrategy", "Lcom/samsung/android/motionphoto/utils/v2/ProgressMeasureStrategy;", "Ljava/util/concurrent/atomic/AtomicInteger;", "processedFrames", "Ljava/util/concurrent/atomic/AtomicInteger;", "Lkotlin/Function3;", "Lcom/samsung/android/motionphoto/utils/v2/OnMergeProgressChangedListener;", "onMergeProgressChangedListener", "LAe/d;", "Lcom/samsung/android/motionphoto/utils/v2/OnMergeCompleteListener;", "onMergeCompleteListener", "Lcom/samsung/android/motionphoto/utils/v2/OnTranscodingProgressChangedListener;", "onTranscodingProgressChangedListener", "Lcom/samsung/android/motionphoto/utils/v2/OnTranscodingCompleteListener;", "onTranscodingCompleteListener", "Lkotlin/Function2;", "Lcom/samsung/android/motionphoto/utils/v2/OnCompleteListener;", "LAe/c;", "", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;", "transcodingTasks", "Ljava/util/List;", "Ljava/util/concurrent/PriorityBlockingQueue;", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransferringTask;", "mergeTasks", "Ljava/util/concurrent/PriorityBlockingQueue;", "Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$ProgressTracker;", "totalProgressTracker", "Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$ProgressTracker;", "transcodingProgressTracker", "Ljava/util/concurrent/ExecutorService;", "_threadPool", "Ljava/util/concurrent/ExecutorService;", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder;", "videoTranscoder$delegate", "Lme/f;", "getVideoTranscoder", "()Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscoder;", "videoTranscoder", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer;", "_videoTransfer", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer;", "com/samsung/android/motionphoto/utils/v2/MotionScrap$eventListener$1", "eventListener", "Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$eventListener$1;", "getThreadPool", "()Ljava/util/concurrent/ExecutorService;", "threadPool", "getVideoTransfer", "()Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransfer;", "videoTransfer", "Companion", "ProgressTracker", "Builder", "TranscodingVO", "FutureWrapper", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionScrap implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String TAG;
    private ExecutorService _threadPool;
    private VideoTransfer _videoTransfer;
    private final MotionScrap$eventListener$1 eventListener;
    private final PriorityBlockingQueue<VideoTransferringTask> mergeTasks;
    private c onCompleteListener;
    /* access modifiers changed from: private */
    public d onMergeCompleteListener;
    /* access modifiers changed from: private */
    public d onMergeProgressChangedListener;
    private b onProgressEventListener;
    private OnProgressListener onProgressListener;
    /* access modifiers changed from: private */
    public d onTranscodingCompleteListener;
    /* access modifiers changed from: private */
    public d onTranscodingProgressChangedListener;
    private AtomicInteger processedFrames;
    private ProgressMeasureStrategy progressMeasureStrategy;
    private ProgressUnit progressUnit;
    /* access modifiers changed from: private */
    public ProgressTracker totalProgressTracker;
    /* access modifiers changed from: private */
    public ProgressTracker transcodingProgressTracker;
    private final List<VideoTranscodingTask> transcodingTasks;
    private final f videoTranscoder$delegate;

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b$\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J1\u0010\u000e\u001a\u00020\u00002\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f¢\u0006\u0004\b\u000e\u0010\u000fJ1\u0010\u0012\u001a\u00020\u00002\"\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\u0010¢\u0006\u0004\b\u0012\u0010\u000fJ1\u0010\u0015\u001a\u00020\u00002\"\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\u0013¢\u0006\u0004\b\u0015\u0010\u000fJ1\u0010\u0018\u001a\u00020\u00002\"\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\u0016¢\u0006\u0004\b\u0018\u0010\u000fJ+\u0010\u001e\u001a\u00020\u00002\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000b0\u0019j\u0002`\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ!\u0010\u001e\u001a\u00020\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0 ¢\u0006\u0004\b\u001e\u0010!J\u001f\u0010%\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020#¢\u0006\u0004\b%\u0010&J\u001b\u0010%\u001a\u00020\u00002\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020(0'¢\u0006\u0004\b%\u0010)J\r\u0010+\u001a\u00020*¢\u0006\u0004\b+\u0010,R\"\u0010\u0005\u001a\u00020\u00048\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R6\u00104\u001a\u0016\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000b\u0018\u000102j\u0004\u0018\u0001`38\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010:\u001a\u0004\u0018\u00010\"8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\"\u0010@\u001a\u00020#8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ERB\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\u0004\u0018\u0001`\u00108\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JRB\u0010\u0017\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\u0004\u0018\u0001`\u00168\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010F\u001a\u0004\bK\u0010H\"\u0004\bL\u0010JRB\u0010\r\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\u0004\u0018\u0001`\f8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\r\u0010F\u001a\u0004\bM\u0010H\"\u0004\bN\u0010JRB\u0010\u0014\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\u0004\u0018\u0001`\u00138\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010F\u001a\u0004\bO\u0010H\"\u0004\bP\u0010JR<\u0010Q\u001a\u001c\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0019j\u0004\u0018\u0001`\u001c8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010V¨\u0006W"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;", "", "<init>", "()V", "Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;", "progressUnit", "setProgressUnit", "(Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;)Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;", "Lkotlin/Function3;", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "", "Lme/x;", "Lcom/samsung/android/motionphoto/utils/v2/OnTranscodingProgressChangedListener;", "onTranscodingProgressChangedListener", "setOnTranscodingProgressChangedListener", "(LAe/d;)Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;", "Lcom/samsung/android/motionphoto/utils/v2/OnMergeProgressChangedListener;", "onMergeProgressChangedListener", "setOnMergeProgressChangedListener", "Lcom/samsung/android/motionphoto/utils/v2/OnTranscodingCompleteListener;", "onTranscodingCompleteListener", "setOnTranscodingCompleteListener", "Lcom/samsung/android/motionphoto/utils/v2/OnMergeCompleteListener;", "onMergeCompleteListener", "setOnMergeCompleteListener", "Lkotlin/Function2;", "", "Lcom/samsung/android/motionphoto/utils/v2/video/ExportEvent;", "Lcom/samsung/android/motionphoto/utils/v2/OnCompleteListener;", "listener", "setOnCompleteListener", "(LAe/c;)Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;", "Ljava/util/function/BiConsumer;", "(Ljava/util/function/BiConsumer;)Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;", "Lcom/samsung/android/motionphoto/utils/v2/OnProgressListener;", "Lcom/samsung/android/motionphoto/utils/v2/ProgressMeasureStrategy;", "strategy", "setOnProgressListener", "(Lcom/samsung/android/motionphoto/utils/v2/OnProgressListener;Lcom/samsung/android/motionphoto/utils/v2/ProgressMeasureStrategy;)Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;", "Ljava/util/function/Consumer;", "", "(Ljava/util/function/Consumer;)Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Builder;", "Lcom/samsung/android/motionphoto/utils/v2/MotionScrap;", "build", "()Lcom/samsung/android/motionphoto/utils/v2/MotionScrap;", "Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;", "getProgressUnit$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;", "setProgressUnit$motionphoto_utils_v2_0_17_release", "(Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;)V", "Lkotlin/Function1;", "Lcom/samsung/android/motionphoto/utils/v2/OnProgressEventListener;", "onProgressEventListener", "LAe/b;", "getOnProgressEventListener$motionphoto_utils_v2_0_17_release", "()LAe/b;", "setOnProgressEventListener$motionphoto_utils_v2_0_17_release", "(LAe/b;)V", "onProgressListener", "Lcom/samsung/android/motionphoto/utils/v2/OnProgressListener;", "getOnProgressListener$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/OnProgressListener;", "setOnProgressListener$motionphoto_utils_v2_0_17_release", "(Lcom/samsung/android/motionphoto/utils/v2/OnProgressListener;)V", "progressMeasureStrategy", "Lcom/samsung/android/motionphoto/utils/v2/ProgressMeasureStrategy;", "getProgressMeasureStrategy$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/ProgressMeasureStrategy;", "setProgressMeasureStrategy$motionphoto_utils_v2_0_17_release", "(Lcom/samsung/android/motionphoto/utils/v2/ProgressMeasureStrategy;)V", "LAe/d;", "getOnMergeProgressChangedListener$motionphoto_utils_v2_0_17_release", "()LAe/d;", "setOnMergeProgressChangedListener$motionphoto_utils_v2_0_17_release", "(LAe/d;)V", "getOnMergeCompleteListener$motionphoto_utils_v2_0_17_release", "setOnMergeCompleteListener$motionphoto_utils_v2_0_17_release", "getOnTranscodingProgressChangedListener$motionphoto_utils_v2_0_17_release", "setOnTranscodingProgressChangedListener$motionphoto_utils_v2_0_17_release", "getOnTranscodingCompleteListener$motionphoto_utils_v2_0_17_release", "setOnTranscodingCompleteListener$motionphoto_utils_v2_0_17_release", "onCompleteListener", "LAe/c;", "getOnCompleteListener$motionphoto_utils_v2_0_17_release", "()LAe/c;", "setOnCompleteListener$motionphoto_utils_v2_0_17_release", "(LAe/c;)V", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private c onCompleteListener;
        private d onMergeCompleteListener;
        private d onMergeProgressChangedListener;
        private b onProgressEventListener;
        private OnProgressListener onProgressListener;
        private d onTranscodingCompleteListener;
        private d onTranscodingProgressChangedListener;
        private ProgressMeasureStrategy progressMeasureStrategy = new SimpleProcessMeasureStrategy(0, 0, 3, (e) null);
        private ProgressUnit progressUnit = ProgressUnit.TIME;

        /* access modifiers changed from: private */
        public static final x setOnCompleteListener$lambda$0(BiConsumer biConsumer, boolean z, ExportEvent exportEvent) {
            j.e(exportEvent, "event");
            biConsumer.accept(Boolean.valueOf(z), exportEvent);
            return x.f4917a;
        }

        public static /* synthetic */ Builder setOnProgressListener$default(Builder builder, OnProgressListener onProgressListener2, ProgressMeasureStrategy progressMeasureStrategy2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                progressMeasureStrategy2 = new SimpleProcessMeasureStrategy(0, 0, 3, (e) null);
            }
            return builder.setOnProgressListener(onProgressListener2, progressMeasureStrategy2);
        }

        /* access modifiers changed from: private */
        public static final void setOnProgressListener$lambda$1(Consumer consumer, float f) {
            consumer.accept(Float.valueOf(f));
        }

        public final MotionScrap build() {
            return new MotionScrap(this, (e) null);
        }

        public final c getOnCompleteListener$motionphoto_utils_v2_0_17_release() {
            return this.onCompleteListener;
        }

        public final d getOnMergeCompleteListener$motionphoto_utils_v2_0_17_release() {
            return this.onMergeCompleteListener;
        }

        public final d getOnMergeProgressChangedListener$motionphoto_utils_v2_0_17_release() {
            return this.onMergeProgressChangedListener;
        }

        public final b getOnProgressEventListener$motionphoto_utils_v2_0_17_release() {
            return this.onProgressEventListener;
        }

        public final OnProgressListener getOnProgressListener$motionphoto_utils_v2_0_17_release() {
            return this.onProgressListener;
        }

        public final d getOnTranscodingCompleteListener$motionphoto_utils_v2_0_17_release() {
            return this.onTranscodingCompleteListener;
        }

        public final d getOnTranscodingProgressChangedListener$motionphoto_utils_v2_0_17_release() {
            return this.onTranscodingProgressChangedListener;
        }

        public final ProgressMeasureStrategy getProgressMeasureStrategy$motionphoto_utils_v2_0_17_release() {
            return this.progressMeasureStrategy;
        }

        public final ProgressUnit getProgressUnit$motionphoto_utils_v2_0_17_release() {
            return this.progressUnit;
        }

        public final Builder setOnCompleteListener(c cVar) {
            j.e(cVar, "listener");
            this.onCompleteListener = cVar;
            return this;
        }

        public final void setOnCompleteListener$motionphoto_utils_v2_0_17_release(c cVar) {
            this.onCompleteListener = cVar;
        }

        public final Builder setOnMergeCompleteListener(d dVar) {
            j.e(dVar, "onMergeCompleteListener");
            this.onMergeCompleteListener = dVar;
            return this;
        }

        public final void setOnMergeCompleteListener$motionphoto_utils_v2_0_17_release(d dVar) {
            this.onMergeCompleteListener = dVar;
        }

        public final Builder setOnMergeProgressChangedListener(d dVar) {
            j.e(dVar, "onMergeProgressChangedListener");
            this.onMergeProgressChangedListener = dVar;
            return this;
        }

        public final void setOnMergeProgressChangedListener$motionphoto_utils_v2_0_17_release(d dVar) {
            this.onMergeProgressChangedListener = dVar;
        }

        public final void setOnProgressEventListener$motionphoto_utils_v2_0_17_release(b bVar) {
            this.onProgressEventListener = bVar;
        }

        public final Builder setOnProgressListener(OnProgressListener onProgressListener2, ProgressMeasureStrategy progressMeasureStrategy2) {
            j.e(onProgressListener2, "listener");
            j.e(progressMeasureStrategy2, "strategy");
            this.onProgressListener = onProgressListener2;
            this.progressMeasureStrategy = progressMeasureStrategy2;
            return this;
        }

        public final void setOnProgressListener$motionphoto_utils_v2_0_17_release(OnProgressListener onProgressListener2) {
            this.onProgressListener = onProgressListener2;
        }

        public final Builder setOnTranscodingCompleteListener(d dVar) {
            j.e(dVar, "onTranscodingCompleteListener");
            this.onTranscodingCompleteListener = dVar;
            return this;
        }

        public final void setOnTranscodingCompleteListener$motionphoto_utils_v2_0_17_release(d dVar) {
            this.onTranscodingCompleteListener = dVar;
        }

        public final Builder setOnTranscodingProgressChangedListener(d dVar) {
            j.e(dVar, "onTranscodingProgressChangedListener");
            this.onTranscodingProgressChangedListener = dVar;
            return this;
        }

        public final void setOnTranscodingProgressChangedListener$motionphoto_utils_v2_0_17_release(d dVar) {
            this.onTranscodingProgressChangedListener = dVar;
        }

        public final void setProgressMeasureStrategy$motionphoto_utils_v2_0_17_release(ProgressMeasureStrategy progressMeasureStrategy2) {
            j.e(progressMeasureStrategy2, "<set-?>");
            this.progressMeasureStrategy = progressMeasureStrategy2;
        }

        public final Builder setProgressUnit(ProgressUnit progressUnit2) {
            j.e(progressUnit2, "progressUnit");
            this.progressUnit = progressUnit2;
            return this;
        }

        public final void setProgressUnit$motionphoto_utils_v2_0_17_release(ProgressUnit progressUnit2) {
            j.e(progressUnit2, "<set-?>");
            this.progressUnit = progressUnit2;
        }

        public final Builder setOnCompleteListener(BiConsumer<Boolean, ExportEvent> biConsumer) {
            j.e(biConsumer, "listener");
            this.onCompleteListener = new w(3, biConsumer);
            return this;
        }

        public final Builder setOnProgressListener(Consumer<Float> consumer) {
            j.e(consumer, "listener");
            this.onProgressListener = new a(8, consumer);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\n\u001a\u00020\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0002¢\u0006\u0004\b\u0018\u0010\u0011J\r\u0010\u0019\u001a\u00020\u0002¢\u0006\u0004\b\u0019\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001aR \u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u001b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR?\u0010#\u001a&\u0012\f\u0012\n \u001f*\u0004\u0018\u00010\u001e0\u001e \u001f*\u0012\u0012\f\u0012\n \u001f*\u0004\u0018\u00010\u001e0\u001e\u0018\u00010\u00010\u00018BX\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u0016\u0010\"R?\u0010'\u001a&\u0012\f\u0012\n \u001f*\u0004\u0018\u00010$0$ \u001f*\u0012\u0012\f\u0012\n \u001f*\u0004\u0018\u00010$0$\u0018\u00010\u00010\u00018BX\u0002¢\u0006\f\n\u0004\b%\u0010!\u001a\u0004\b&\u0010\"¨\u0006("}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$FutureWrapper;", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/motionphoto/utils/v2/video/Result;", "Ljava/util/concurrent/Executor;", "executor", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MotionScrap;Ljava/util/concurrent/Executor;)V", "Ljava/util/concurrent/FutureTask;", "task", "Lme/x;", "plusAssign", "(Ljava/util/concurrent/FutureTask;)V", "", "mayInterruptIfRunning", "cancel", "(Z)Z", "join", "()Lcom/samsung/android/motionphoto/utils/v2/video/Result;", "get", "isCancelled", "()Z", "", "getAll", "()Ljava/util/List;", "getFirst", "getLast", "Ljava/util/concurrent/Executor;", "", "futures", "Ljava/util/List;", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "all$delegate", "Lme/f;", "()Ljava/util/concurrent/CompletableFuture;", "all", "", "any$delegate", "getAny", "any", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class FutureWrapper extends CompletableFuture<Result> {
        private final f all$delegate = L1.d.q(new m(this, 0));
        private final f any$delegate = L1.d.q(new m(this, 1));
        private final Executor executor;
        private final List<CompletableFuture<Result>> futures = new ArrayList();
        final /* synthetic */ MotionScrap this$0;

        public FutureWrapper(MotionScrap motionScrap, Executor executor2) {
            j.e(executor2, "executor");
            this.this$0 = motionScrap;
            this.executor = executor2;
        }

        /* access modifiers changed from: private */
        public static final CompletableFuture all_delegate$lambda$0(FutureWrapper futureWrapper) {
            CompletableFuture[] completableFutureArr = (CompletableFuture[]) futureWrapper.futures.toArray(new CompletableFuture[0]);
            return CompletableFuture.allOf((CompletableFuture[]) Arrays.copyOf(completableFutureArr, completableFutureArr.length));
        }

        /* access modifiers changed from: private */
        public static final CompletableFuture any_delegate$lambda$1(FutureWrapper futureWrapper) {
            CompletableFuture[] completableFutureArr = (CompletableFuture[]) futureWrapper.futures.toArray(new CompletableFuture[0]);
            return CompletableFuture.anyOf((CompletableFuture[]) Arrays.copyOf(completableFutureArr, completableFutureArr.length));
        }

        private final CompletableFuture<Void> getAll() {
            return (CompletableFuture) this.all$delegate.getValue();
        }

        private final CompletableFuture<Object> getAny() {
            return (CompletableFuture) this.any$delegate.getValue();
        }

        public boolean cancel(boolean z) {
            boolean cancel = getAny().cancel(z);
            for (CompletableFuture cancel2 : this.futures) {
                cancel2.cancel(z);
            }
            return cancel;
        }

        public final Result getFirst() {
            try {
                return (Result) ((CompletableFuture) C1194l.L0(this.futures)).get();
            } catch (Exception e) {
                MotionScrap motionScrap = this.this$0;
                ExportEvent exportEvent = ExportEvent.EXECUTE_CANCEL;
                motionScrap.notifyComplete(exportEvent);
                if ((e instanceof InterruptedException) || (e instanceof CancellationException)) {
                    return new Result(exportEvent, 0, 0, 0, 0, (MediaFormat) null, 62, (e) null);
                }
                throw e;
            }
        }

        public final Result getLast() {
            try {
                return (Result) ((CompletableFuture) C1194l.T0(this.futures)).get();
            } catch (Exception e) {
                MotionScrap motionScrap = this.this$0;
                ExportEvent exportEvent = ExportEvent.EXECUTE_CANCEL;
                motionScrap.notifyComplete(exportEvent);
                if ((e instanceof InterruptedException) || (e instanceof CancellationException)) {
                    return new Result(exportEvent, 0, 0, 0, 0, (MediaFormat) null, 62, (e) null);
                }
                throw e;
            }
        }

        public boolean isCancelled() {
            String access$getTAG$cp = MotionScrap.TAG;
            boolean isCancelled = getAny().isCancelled();
            Iterable<CompletableFuture> iterable = this.futures;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (CompletableFuture isCancelled2 : iterable) {
                arrayList.add(Boolean.valueOf(isCancelled2.isCancelled()));
            }
            String R02 = C1194l.R0(arrayList, (String) null, (String) null, (String) null, (b) null, 63);
            SLog.d(access$getTAG$cp, "isCancelled: " + isCancelled + ArcCommonLog.TAG_COMMA + R02);
            return getAny().isCancelled();
        }

        public final void plusAssign(FutureTask<Result> futureTask) {
            j.e(futureTask, "task");
            this.futures.add(CommonsKt.toCompletableFuture(futureTask, this.executor));
        }

        public Result get() {
            try {
                getAll().get();
                return (Result) ((CompletableFuture) C1194l.T0(this.futures)).get();
            } catch (Exception e) {
                MotionScrap motionScrap = this.this$0;
                ExportEvent exportEvent = ExportEvent.EXECUTE_CANCEL;
                motionScrap.notifyComplete(exportEvent);
                if ((e instanceof InterruptedException) || (e instanceof CancellationException)) {
                    return new Result(exportEvent, 0, 0, 0, 0, (MediaFormat) null, 62, (e) null);
                }
                throw e;
            }
        }

        /* renamed from: getAll  reason: collision with other method in class */
        public final List<Result> m39getAll() {
            Result result;
            Iterable<CompletableFuture> iterable = this.futures;
            MotionScrap motionScrap = this.this$0;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (CompletableFuture completableFuture : iterable) {
                try {
                    result = (Result) completableFuture.get();
                } catch (Exception e) {
                    ExportEvent exportEvent = ExportEvent.EXECUTE_CANCEL;
                    motionScrap.notifyComplete(exportEvent);
                    if ((e instanceof InterruptedException) || (e instanceof CancellationException)) {
                        result = new Result(exportEvent, 0, 0, 0, 0, (MediaFormat) null, 62, (e) null);
                    } else {
                        throw e;
                    }
                }
                arrayList.add(result);
            }
            return arrayList;
        }

        public Result join() {
            try {
                getAll().join();
                return (Result) ((CompletableFuture) C1194l.T0(this.futures)).get();
            } catch (Exception e) {
                Exception exc = e;
                if ((exc instanceof InterruptedException) || (exc instanceof CancellationException)) {
                    return new Result(ExportEvent.EXECUTE_CANCEL, 0, 0, 0, 0, (MediaFormat) null, 62, (e) null);
                }
                throw exc;
            }
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rB\u0011\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\f\u0010\u0010J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0002J\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0000J\b\u0010\u001f\u001a\u00020\u0003H\u0016J\b\u0010 \u001a\u00020!H\u0016J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\nHÆ\u0003JS\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nHÆ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019¨\u0006*"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$TranscodingVO;", "", "width", "", "height", "rotation", "audioCodec", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "videoCodec", "audioCodecFormat", "Landroid/media/MediaFormat;", "videoCodecFormat", "<init>", "(IIILcom/samsung/android/motionphoto/utils/v2/MimeType;Lcom/samsung/android/motionphoto/utils/v2/MimeType;Landroid/media/MediaFormat;Landroid/media/MediaFormat;)V", "mpFile", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;)V", "getWidth", "()I", "getHeight", "getRotation", "getAudioCodec", "()Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "getVideoCodec", "getAudioCodecFormat", "()Landroid/media/MediaFormat;", "getVideoCodecFormat", "equals", "", "other", "hasSameParameters", "hashCode", "toString", "", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TranscodingVO {
        private final MimeType audioCodec;
        private final MediaFormat audioCodecFormat;
        private final int height;
        private final int rotation;
        private final MimeType videoCodec;
        private final MediaFormat videoCodecFormat;
        private final int width;

        public TranscodingVO() {
            this(0, 0, 0, (MimeType) null, (MimeType) null, (MediaFormat) null, (MediaFormat) null, 127, (e) null);
        }

        public static /* synthetic */ TranscodingVO copy$default(TranscodingVO transcodingVO, int i2, int i7, int i8, MimeType mimeType, MimeType mimeType2, MediaFormat mediaFormat, MediaFormat mediaFormat2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                i2 = transcodingVO.width;
            }
            if ((i10 & 2) != 0) {
                i7 = transcodingVO.height;
            }
            if ((i10 & 4) != 0) {
                i8 = transcodingVO.rotation;
            }
            if ((i10 & 8) != 0) {
                mimeType = transcodingVO.audioCodec;
            }
            if ((i10 & 16) != 0) {
                mimeType2 = transcodingVO.videoCodec;
            }
            if ((i10 & 32) != 0) {
                mediaFormat = transcodingVO.audioCodecFormat;
            }
            if ((i10 & 64) != 0) {
                mediaFormat2 = transcodingVO.videoCodecFormat;
            }
            MediaFormat mediaFormat3 = mediaFormat;
            MediaFormat mediaFormat4 = mediaFormat2;
            MimeType mimeType3 = mimeType;
            MimeType mimeType4 = mimeType2;
            return transcodingVO.copy(i2, i7, i8, mimeType3, mimeType4, mediaFormat3, mediaFormat4);
        }

        public final int component1() {
            return this.width;
        }

        public final int component2() {
            return this.height;
        }

        public final int component3() {
            return this.rotation;
        }

        public final MimeType component4() {
            return this.audioCodec;
        }

        public final MimeType component5() {
            return this.videoCodec;
        }

        public final MediaFormat component6() {
            return this.audioCodecFormat;
        }

        public final MediaFormat component7() {
            return this.videoCodecFormat;
        }

        public final TranscodingVO copy(int i2, int i7, int i8, MimeType mimeType, MimeType mimeType2, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
            j.e(mimeType, "audioCodec");
            j.e(mimeType2, "videoCodec");
            return new TranscodingVO(i2, i7, i8, mimeType, mimeType2, mediaFormat, mediaFormat2);
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof TranscodingVO) {
                return hasSameParameters((TranscodingVO) obj);
            }
            if (obj instanceof MPFile) {
                return equals(new TranscodingVO((MPFile) obj));
            }
            throw new IllegalArgumentException("unsupported type: " + obj);
        }

        public final MimeType getAudioCodec() {
            return this.audioCodec;
        }

        public final MediaFormat getAudioCodecFormat() {
            return this.audioCodecFormat;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getRotation() {
            return this.rotation;
        }

        public final MimeType getVideoCodec() {
            return this.videoCodec;
        }

        public final MediaFormat getVideoCodecFormat() {
            return this.videoCodecFormat;
        }

        public final int getWidth() {
            return this.width;
        }

        public final boolean hasSameParameters(TranscodingVO transcodingVO) {
            j.e(transcodingVO, "other");
            if (this.width == transcodingVO.width && this.height == transcodingVO.height && this.rotation == transcodingVO.rotation && this.audioCodec == transcodingVO.audioCodec && this.videoCodec == transcodingVO.videoCodec) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i2 = 0;
            for (Object hashCode : C1195m.q0(Integer.valueOf(this.width), Integer.valueOf(this.height), Integer.valueOf(this.rotation), this.audioCodec, this.videoCodec)) {
                i2 = (i2 * 31) + hashCode.hashCode();
            }
            return i2;
        }

        public String toString() {
            int i2 = this.width;
            int i7 = this.height;
            int i8 = this.rotation;
            MimeType mimeType = this.audioCodec;
            MimeType mimeType2 = this.videoCodec;
            StringBuilder h5 = A.a.h(i2, i7, "w/h/rotation/audio-codec/video-codec=", "/", "/");
            h5.append(i8);
            h5.append("/[");
            h5.append(mimeType);
            h5.append("]/[");
            h5.append(mimeType2);
            h5.append("]");
            return h5.toString();
        }

        public TranscodingVO(int i2, int i7, int i8, MimeType mimeType, MimeType mimeType2, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
            j.e(mimeType, "audioCodec");
            j.e(mimeType2, "videoCodec");
            this.width = i2;
            this.height = i7;
            this.rotation = i8;
            this.audioCodec = mimeType;
            this.videoCodec = mimeType2;
            this.audioCodecFormat = mediaFormat;
            this.videoCodecFormat = mediaFormat2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ TranscodingVO(int i2, int i7, int i8, MimeType mimeType, MimeType mimeType2, MediaFormat mediaFormat, MediaFormat mediaFormat2, int i10, e eVar) {
            this((i10 & 1) != 0 ? 0 : i2, (i10 & 2) != 0 ? 0 : i7, (i10 & 4) != 0 ? 0 : i8, (i10 & 8) != 0 ? MimeType.NONE : mimeType, (i10 & 16) != 0 ? MimeType.NONE : mimeType2, (i10 & 32) != 0 ? null : mediaFormat, (i10 & 64) != 0 ? null : mediaFormat2);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public TranscodingVO(MPFile mPFile) {
            this(mPFile.getVideoWidth(), mPFile.getVideoHeight(), mPFile.getRotation(), mPFile.getAudioCodecType(), mPFile.getVideoCodecType(), mPFile.getAudioCodecFormat(), mPFile.getVideoCodecFormat());
            j.e(mPFile, "mpFile");
        }
    }

    static {
        String tagOf = SLog.tagOf(MotionScrap.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public /* synthetic */ MotionScrap(Builder builder, e eVar) {
        this(builder);
    }

    /* access modifiers changed from: private */
    public final void closeSafely() {
        VideoTransfer videoTransfer = this._videoTransfer;
        if (videoTransfer != null) {
            videoTransfer.stop();
            videoTransfer.close();
        }
        this._videoTransfer = null;
        ExecutorService executorService = this._threadPool;
        if (executorService != null) {
            executorService.shutdown();
        }
        this._threadPool = null;
    }

    private final Future<Result> executeTasks(int i2, MediaFormat mediaFormat, Ae.a aVar) {
        String str = TAG;
        SLog.i(str, "startMuxing: numTasks=" + i2);
        b bVar = this.onProgressEventListener;
        if (bVar != null) {
            bVar.invoke(ExportEvent.EXECUTE);
        }
        this.processedFrames.set(0);
        this._threadPool = Executors.newFixedThreadPool(2);
        ConditionVariable conditionVariable = new ConditionVariable();
        FutureWrapper futureWrapper = new FutureWrapper(this, getThreadPool());
        MediaFormat mediaFormat2 = mediaFormat;
        Future submit = getThreadPool().submit(new k(this, futureWrapper, conditionVariable, mediaFormat2, 0));
        j.c(submit, "null cannot be cast to non-null type java.util.concurrent.FutureTask<@[FlexibleNullability] com.samsung.android.motionphoto.utils.v2.video.Result?>");
        futureWrapper.plusAssign((FutureTask) submit);
        FutureWrapper futureWrapper2 = futureWrapper;
        MediaFormat mediaFormat3 = mediaFormat2;
        FutureWrapper futureWrapper3 = futureWrapper2;
        Future submit2 = getThreadPool().submit(new l(this, aVar, i2, conditionVariable, futureWrapper2, mediaFormat3));
        j.c(submit2, "null cannot be cast to non-null type java.util.concurrent.FutureTask<@[FlexibleNullability] com.samsung.android.motionphoto.utils.v2.video.Result?>");
        futureWrapper3.plusAssign((FutureTask) submit2);
        return futureWrapper3;
    }

    /* access modifiers changed from: private */
    public static final Result executeTasks$lambda$47(MotionScrap motionScrap, FutureWrapper futureWrapper, ConditionVariable conditionVariable, MediaFormat mediaFormat) {
        MotionScrap motionScrap2 = motionScrap;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            for (VideoTranscodingTask videoTranscodingTask : motionScrap2.transcodingTasks) {
                if (!futureWrapper.isCancelled()) {
                    motionScrap2.getVideoTranscoder().process(videoTranscodingTask);
                    motionScrap2.mergeTasks.offer(new VideoTransferringTask(videoTranscodingTask));
                    conditionVariable.open();
                } else {
                    throw new InterruptedException();
                }
            }
            return new Result(ExportEvent.EXECUTE_COMPLETE, 0, System.currentTimeMillis() - currentTimeMillis, 0, 0, mediaFormat, 26, (e) null);
        } catch (Throwable th) {
            th.printStackTrace();
            if (th instanceof InterruptedException) {
                ExportEvent exportEvent = ExportEvent.EXECUTE_CANCEL;
                Result result = new Result(exportEvent, 0, 0, 0, 0, (MediaFormat) null, 62, (e) null);
                motionScrap2.notifyComplete(exportEvent);
                return result;
            }
            ExportEvent exportEvent2 = ExportEvent.EXECUTE_FAIL;
            Result result2 = new Result(exportEvent2, 0, 0, 0, 0, (MediaFormat) null, 62, (e) null);
            motionScrap2.notifyComplete(exportEvent2);
            return result2;
        }
    }

    /* access modifiers changed from: private */
    public static final Result executeTasks$lambda$54(MotionScrap motionScrap, Ae.a aVar, int i2, ConditionVariable conditionVariable, FutureWrapper futureWrapper, MediaFormat mediaFormat) {
        Result result;
        MotionScrap motionScrap2 = motionScrap;
        int i7 = i2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            int i8 = 0;
            while (i8 < i7) {
                VideoTransferringTask peek = motionScrap2.mergeTasks.peek();
                if (peek == null || peek.getId() != i8) {
                    conditionVariable.block();
                    conditionVariable.close();
                } else if (!futureWrapper.isCancelled()) {
                    String str = TAG;
                    SLog.d(str, "targetTaskId=" + i8 + "/" + i7);
                    VideoTransferringTask poll = motionScrap2.mergeTasks.poll();
                    j.b(poll);
                    VideoTransferringTask videoTransferringTask = poll;
                    if (videoTransferringTask.isFromTranscoding()) {
                        motionScrap2.getVideoTransfer().writeTrack(videoTransferringTask.getMpFile(), videoTransferringTask.getAllTranscodedSamples());
                    } else {
                        motionScrap2.getVideoTransfer().writeTrack(videoTransferringTask.getMpFile());
                    }
                    i8++;
                } else {
                    throw new InterruptedException();
                }
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            ExportEvent exportEvent = ExportEvent.EXECUTE_COMPLETE;
            long transcodingTimeMs = futureWrapper.getFirst().getTranscodingTimeMs();
            MediaFormat mediaFormat2 = mediaFormat;
            mediaFormat2.setLong("durationUs", motionScrap2.getVideoTransfer().getLastTransferredTimestampUs$motionphoto_utils_v2_0_17_release() / ((long) 1000));
            Result result2 = new Result(exportEvent, currentTimeMillis2, transcodingTimeMs, 0, 0, mediaFormat2, 24, (e) null);
            String str2 = TAG;
            long transferTimeMs = result2.getTransferTimeMs();
            SLog.i(str2, "exporting is done: " + transferTimeMs);
            b bVar = motionScrap2.onProgressEventListener;
            if (bVar != null) {
                bVar.invoke(exportEvent);
            }
            motionScrap2.notifyComplete(exportEvent);
            aVar.invoke();
            return result2;
        } catch (Throwable th) {
            aVar.invoke();
            throw th;
        }
    }

    private final ExecutorService getThreadPool() {
        ExecutorService executorService = this._threadPool;
        j.b(executorService);
        return executorService;
    }

    private final VideoTranscoder getVideoTranscoder() {
        return (VideoTranscoder) this.videoTranscoder$delegate.getValue();
    }

    private final VideoTransfer getVideoTransfer() {
        VideoTransfer videoTransfer = this._videoTransfer;
        j.b(videoTransfer);
        return videoTransfer;
    }

    /* access modifiers changed from: private */
    public final void notifyComplete(ExportEvent exportEvent) {
        boolean z;
        OnProgressListener onProgressListener2;
        try {
            if (exportEvent == ExportEvent.EXECUTE_COMPLETE) {
                z = true;
            } else {
                z = false;
            }
            String str = TAG;
            SLog.i(str, "notifyComplete: " + exportEvent + ", success=" + z);
            if (z && (onProgressListener2 = this.onProgressListener) != null) {
                onProgressListener2.onProgress(1.0f);
            }
            c cVar = this.onCompleteListener;
            if (cVar != null) {
                cVar.invoke(Boolean.valueOf(z), exportEvent);
            }
        } catch (Exception e) {
            String str2 = TAG;
            SLog.e(str2, "onCompleteListener failed: " + e);
        }
    }

    private final void parseVideoInfo(MPFile mPFile) {
        Throwable th;
        mPFile.setRotation(new ExifInfoImpl((MediaFile) mPFile).getRotation());
        FileInputStream newInputFileStream = mPFile.newInputFileStream();
        try {
            newInputFileStream.getChannel().position(0);
            MediaExtractor mediaExtractor = new MediaExtractor();
            mediaExtractor.setDataSource(newInputFileStream.getFD(), mPFile.getMpInfo().getVideoPosition(), mPFile.getMpInfo().getVideoSize());
            int trackCount = mediaExtractor.getTrackCount();
            for (int i2 = 0; i2 < trackCount; i2++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i2);
                j.d(trackFormat, "getTrackFormat(...)");
                String string = trackFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                j.b(string);
                mPFile.addCodecFormat(MimeType.Companion.of(string), trackFormat);
            }
            mediaExtractor.release();
            B1.a.g(newInputFileStream, (Throwable) null);
        } catch (Throwable th2) {
            B1.a.g(newInputFileStream, th);
            throw th2;
        }
    }

    private final MediaFormat prepareTasks(List<MPFile> list, VideoTransfer.Builder builder) {
        ArrayList arrayList;
        Object obj;
        VideoTask videoTask;
        b bVar = this.onProgressEventListener;
        if (bVar != null) {
            bVar.invoke(ExportEvent.PREPARE);
        }
        Iterable<MPFile> iterable = list;
        for (MPFile parseVideoInfo : iterable) {
            parseVideoInfo(parseVideoInfo);
        }
        List list2 = (List) iterable;
        Iterable iterable2 = list2;
        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
            Iterator it = iterable2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((MPFile) it.next()).getAudioCodecType() != MimeType.NONE) {
                    ArrayList arrayList2 = new ArrayList();
                    for (Object next : iterable2) {
                        if (((MPFile) next).getAudioCodecType() != MimeType.NONE) {
                            arrayList2.add(next);
                        }
                    }
                    arrayList = arrayList2;
                }
            }
        }
        arrayList = list2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object next2 : C1194l.g1(arrayList, new MotionScrap$prepareTasks$lambda$29$$inlined$sortedByDescending$1())) {
            TranscodingVO transcodingVO = new TranscodingVO((MPFile) next2);
            Object obj2 = linkedHashMap.get(transcodingVO);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(transcodingVO, obj2);
            }
            ((List) obj2).add(next2);
        }
        prepareTasks$lambda$29$lambda$28(linkedHashMap);
        Iterator it2 = linkedHashMap.entrySet().iterator();
        if (!it2.hasNext()) {
            obj = null;
        } else {
            obj = it2.next();
            if (it2.hasNext()) {
                int size = ((List) ((Map.Entry) obj).getValue()).size();
                while (true) {
                    Object next3 = it2.next();
                    int size2 = ((List) ((Map.Entry) next3).getValue()).size();
                    if (size < size2) {
                        obj = next3;
                        size = size2;
                    }
                    if (!it2.hasNext()) {
                        break;
                    }
                    VideoTransfer.Builder builder2 = builder;
                }
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        TranscodingVO transcodingVO2 = (TranscodingVO) (entry != null ? entry.getKey() : null);
        if (transcodingVO2 == null) {
            transcodingVO2 = new TranscodingVO(0, 0, 0, (MimeType) null, (MimeType) null, (MediaFormat) null, (MediaFormat) null, 127, (e) null);
        }
        SLog.i(TAG, "frequent TranscodingVO=" + transcodingVO2);
        this.totalProgressTracker = new ProgressTracker(this.progressUnit, list2, false, false, 12, (e) null);
        ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable, 10));
        int i2 = 0;
        for (Object next4 : iterable) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                MPFile mPFile = (MPFile) next4;
                if (transcodingVO2.equals(mPFile)) {
                    videoTask = new VideoTransferringTask(i2, mPFile);
                } else {
                    videoTask = new VideoTranscodingTask(i2, mPFile);
                }
                SLog.d(TAG, i2 + "-th task is " + videoTask + ", path=" + videoTask.getMpFile().path());
                arrayList3.add(videoTask);
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        ArrayList<VideoTask> arrayList4 = new ArrayList<>();
        ArrayList<VideoTask> arrayList5 = new ArrayList<>();
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            Object next5 = it3.next();
            if (((VideoTask) next5) instanceof VideoTransferringTask) {
                arrayList4.add(next5);
            } else {
                arrayList5.add(next5);
            }
        }
        int i8 = 0;
        for (MPFile distinctFrameCount : iterable) {
            i8 += distinctFrameCount.distinctFrameCount();
        }
        int i10 = 0;
        for (VideoTask mpFile : arrayList5) {
            i10 += mpFile.getMpFile().distinctFrameCount();
        }
        SLog.d(TAG, "totalFrames transfer=" + i8 + ", transcoding=" + i10);
        this.progressMeasureStrategy.setTotalFrames(i8, i10);
        for (Map.Entry next6 : ((VideoTask) C1194l.L0(arrayList4)).getCodecFormats().entrySet()) {
            builder.addTrackFormat((MimeType) next6.getKey(), (MediaFormat) next6.getValue());
        }
        SLog.d(TAG, "transcodingRequired=" + C1194l.R0(arrayList5, (String) null, (String) null, (String) null, new C0725a(23), 31));
        for (VideoTask videoTask2 : arrayList5) {
            j.c(videoTask2, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.video.VideoTranscodingTask");
            VideoTranscodingTask videoTranscodingTask = (VideoTranscodingTask) videoTask2;
            videoTranscodingTask.setTranscodingVO$motionphoto_utils_v2_0_17_release(transcodingVO2);
            this.transcodingTasks.add(videoTranscodingTask);
        }
        ProgressUnit progressUnit2 = this.progressUnit;
        ArrayList arrayList6 = new ArrayList(C1196n.w0(arrayList5, 10));
        for (VideoTask mpFile2 : arrayList5) {
            arrayList6.add(mpFile2.getMpFile());
        }
        this.transcodingProgressTracker = new ProgressTracker(progressUnit2, arrayList6, true, false);
        for (VideoTask videoTask3 : arrayList4) {
            PriorityBlockingQueue<VideoTransferringTask> priorityBlockingQueue = this.mergeTasks;
            j.c(videoTask3, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.video.VideoTransferringTask");
            priorityBlockingQueue.add((VideoTransferringTask) videoTask3);
        }
        b bVar2 = this.onProgressEventListener;
        if (bVar2 != null) {
            bVar2.invoke(ExportEvent.PREPARE_DONE);
        }
        MediaFormat mediaFormat = new MediaFormat();
        String str = TAG;
        int width = transcodingVO2.getWidth();
        int height = transcodingVO2.getHeight();
        int rotation = transcodingVO2.getRotation();
        StringBuilder h5 = A.a.h(width, height, "w/h/rotation=", "/", "/");
        h5.append(rotation);
        SLog.i(str, h5.toString());
        mediaFormat.setString(MediaDefs.Image.HEIF.HEIF_MIME_BOX, MimeType.VIDEO_MP4.getValue());
        mediaFormat.setInteger(Message.KEY_ROTATION, transcodingVO2.getRotation());
        mediaFormat.setInteger("width", transcodingVO2.getWidth());
        mediaFormat.setInteger("height", transcodingVO2.getHeight());
        return mediaFormat;
    }

    private static final void prepareTasks$lambda$29$lambda$28(Map map) {
        j.e(map, "it");
        for (Map.Entry entry : map.entrySet()) {
            String str = TAG;
            Object key = entry.getKey();
            int size = ((List) entry.getValue()).size();
            SLog.d(str, "frequent properties: k=" + key + ", size=" + size);
        }
    }

    /* access modifiers changed from: private */
    public static final CharSequence prepareTasks$lambda$36(VideoTask videoTask) {
        j.e(videoTask, "it");
        return videoTask.path();
    }

    /* access modifiers changed from: private */
    public final void reportProgress() {
        OnProgressListener onProgressListener2;
        float onFrameProcessed = this.progressMeasureStrategy.onFrameProcessed(this.processedFrames.incrementAndGet());
        if (onFrameProcessed >= 0.0f && (onProgressListener2 = this.onProgressListener) != null) {
            onProgressListener2.onProgress(B1.a.k(onFrameProcessed, 0.0f, 0.999f));
        }
    }

    /* access modifiers changed from: private */
    public static final VideoTranscoder videoTranscoder_delegate$lambda$2(MotionScrap motionScrap) {
        VideoTranscoder.Builder builder = new VideoTranscoder.Builder();
        builder.useDecoderCallback(SemSystemProperties.getBoolean("secmm.motionphoto.dec-async", false));
        VideoTranscoder build = builder.build();
        build.setOnEventListener(motionScrap.eventListener);
        return build;
    }

    public final boolean cancel() {
        try {
            close();
            return true;
        } catch (Exception e) {
            String str = TAG;
            SLog.e(str, "close failed: " + e);
            return false;
        }
    }

    public void close() {
        VideoTransfer videoTransfer = this._videoTransfer;
        if (videoTransfer != null) {
            videoTransfer.stop();
            videoTransfer.close();
        }
        this._videoTransfer = null;
        ExecutorService executorService = this._threadPool;
        if (executorService != null) {
            executorService.shutdownNow();
        }
        this._threadPool = null;
    }

    public final List<MPFile> computeDistinctDuration(List<MPFile> list) {
        Iterable<i> iterable;
        j.e(list, "files");
        SLog.d(TAG, "computeDistinctDuration");
        b bVar = this.onProgressEventListener;
        if (bVar != null) {
            bVar.invoke(ExportEvent.ANALYZE);
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            MPFile mPFile = (MPFile) next;
            if (mPFile.getMimeType().isImage() && mPFile.getMpInfo().isValid()) {
                arrayList.add(next);
            }
        }
        List g12 = C1194l.g1(arrayList, new MotionScrap$computeDistinctDuration$$inlined$sortedBy$1());
        ArrayList arrayList2 = new ArrayList();
        Iterator it = g12.iterator();
        if (!it.hasNext()) {
            iterable = C1202t.d;
        } else {
            ArrayList arrayList3 = new ArrayList();
            Object next2 = it.next();
            while (it.hasNext()) {
                Object next3 = it.next();
                arrayList3.add(new i(next2, next3));
                next2 = next3;
            }
            iterable = arrayList3;
        }
        for (i iVar : iterable) {
            MPFile mPFile2 = (MPFile) iVar.d;
            long m = B1.a.m(((MPFile) iVar.e).getStartTimeMs() - mPFile2.getStartTimeMs(), 0, mPFile2.getDurationMs());
            long durationMs = mPFile2.getDurationMs() / ((long) Math.max(mPFile2.videoFrameCount(), 1));
            if (m <= durationMs) {
                String str = TAG;
                String path = mPFile2.path();
                SLog.i(str, "skip overlapped file: " + path + " rawDistinctDurationMs=" + m + "ms, frameDurationMs=" + durationMs);
            } else {
                mPFile2.setDistinctDurationMs(m);
                arrayList2.add(mPFile2);
            }
        }
        MPFile mPFile3 = (MPFile) C1194l.T0(g12);
        mPFile3.setDistinctDurationMs(mPFile3.getDurationMs());
        arrayList2.add(mPFile3);
        b bVar2 = this.onProgressEventListener;
        if (bVar2 != null) {
            bVar2.invoke(ExportEvent.ANALYZE_DONE);
        }
        return arrayList2;
    }

    public final void export(File file, File file2) {
        j.e(file, "src");
        j.e(file2, "dst");
        export(new i(MotionPhotoInfo.parse(file), new MediaFile(file2)));
    }

    public MotionScrap() {
        this.progressUnit = ProgressUnit.TIME;
        this.progressMeasureStrategy = new SimpleProcessMeasureStrategy(0, 0, 3, (e) null);
        this.processedFrames = new AtomicInteger(0);
        this.transcodingTasks = new ArrayList();
        this.mergeTasks = new PriorityBlockingQueue<>();
        this.videoTranscoder$delegate = L1.d.q(new q(5, this));
        this.eventListener = new MotionScrap$eventListener$1(this);
    }

    public final void export(List<? extends File> list, List<? extends File> list2) {
        j.e(list, "inputs");
        j.e(list2, "outputs");
        ArrayList r1 = C1194l.r1(list, list2);
        ArrayList arrayList = new ArrayList(C1196n.w0(r1, 10));
        Iterator it = r1.iterator();
        while (it.hasNext()) {
            i iVar = (i) it.next();
            arrayList.add(new i(MotionPhotoInfo.parse((File) iVar.d), new MediaFile((File) iVar.e)));
        }
        i[] iVarArr = (i[]) arrayList.toArray(new i[0]);
        export((i[]) Arrays.copyOf(iVarArr, iVarArr.length));
    }

    private MotionScrap(Builder builder) {
        this();
        this.progressUnit = builder.getProgressUnit$motionphoto_utils_v2_0_17_release();
        this.onProgressEventListener = builder.getOnProgressEventListener$motionphoto_utils_v2_0_17_release();
        this.onProgressListener = builder.getOnProgressListener$motionphoto_utils_v2_0_17_release();
        this.progressMeasureStrategy = builder.getProgressMeasureStrategy$motionphoto_utils_v2_0_17_release();
        this.onMergeProgressChangedListener = builder.getOnMergeProgressChangedListener$motionphoto_utils_v2_0_17_release();
        this.onMergeCompleteListener = builder.getOnMergeCompleteListener$motionphoto_utils_v2_0_17_release();
        this.onTranscodingProgressChangedListener = builder.getOnTranscodingProgressChangedListener$motionphoto_utils_v2_0_17_release();
        this.onTranscodingCompleteListener = builder.getOnTranscodingCompleteListener$motionphoto_utils_v2_0_17_release();
        this.onCompleteListener = builder.getOnCompleteListener$motionphoto_utils_v2_0_17_release();
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0004\b\u0014\u0010\u0013J\r\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001b\u001a\u0004\b\b\u0010\u001cR\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u001b\u001a\u0004\b\t\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0016\u0010 \u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b \u0010\u001eR\u0016\u0010!\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010\u001eR\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\"8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b%\u0010$R\u0016\u0010&\u001a\u00020\"8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010$R\u0016\u0010'\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010\u001eR\u0016\u0010(\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b(\u0010\u001eR\u0014\u0010+\u001a\u00020\u00118BX\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006,"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$ProgressTracker;", "", "Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;", "progressUnit", "", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "mpFiles", "", "isOverlapIgnored", "isMonotonicTimestamp", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;Ljava/util/List;ZZ)V", "Landroid/media/MediaCodec$BufferInfo;", "bufferInfo", "update", "(Landroid/media/MediaCodec$BufferInfo;)Z", "Lme/i;", "", "report", "()Lme/i;", "reportFiles", "Lme/x;", "updateFileCount", "()V", "Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;", "getProgressUnit", "()Lcom/samsung/android/motionphoto/utils/v2/video/ProgressUnit;", "Z", "()Z", "totalFileCount", "I", "totalDurationMs", "totalFrameCount", "frameCount", "", "durationUs", "J", "firstTimestampUs", "lastTimestampUs", "fileCount", "tick", "getDurationMs", "()I", "durationMs", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ProgressTracker {
        private long durationUs;
        private int fileCount;
        private long firstTimestampUs;
        private int frameCount;
        private final boolean isMonotonicTimestamp;
        private final boolean isOverlapIgnored;
        private long lastTimestampUs;
        private final ProgressUnit progressUnit;
        private int tick;
        private int totalDurationMs;
        private final int totalFileCount;
        private int totalFrameCount;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.samsung.android.motionphoto.utils.v2.video.ProgressUnit[] r0 = com.samsung.android.motionphoto.utils.v2.video.ProgressUnit.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.samsung.android.motionphoto.utils.v2.video.ProgressUnit r1 = com.samsung.android.motionphoto.utils.v2.video.ProgressUnit.TIME     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.samsung.android.motionphoto.utils.v2.video.ProgressUnit r1 = com.samsung.android.motionphoto.utils.v2.video.ProgressUnit.FRAME     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionScrap.ProgressTracker.WhenMappings.<clinit>():void");
            }
        }

        public ProgressTracker(ProgressUnit progressUnit2, List<MPFile> list, boolean z, boolean z3) {
            j.e(progressUnit2, "progressUnit");
            j.e(list, "mpFiles");
            this.progressUnit = progressUnit2;
            this.isOverlapIgnored = z;
            this.isMonotonicTimestamp = z3;
            this.totalFileCount = list.size();
            this.tick = 1;
            int i2 = WhenMappings.$EnumSwitchMapping$0[progressUnit2.ordinal()];
            if (i2 == 1) {
                long j2 = 0;
                for (MPFile mPFile : list) {
                    j2 += this.isOverlapIgnored ? mPFile.getDistinctDurationMs() : mPFile.getDurationMs();
                }
                int i7 = (int) j2;
                this.totalDurationMs = i7;
                this.tick = i7 * 10;
            } else if (i2 == 2) {
                int i8 = 0;
                for (MPFile videoFrameCount : list) {
                    i8 += videoFrameCount.videoFrameCount();
                }
                this.totalFrameCount = i8;
                this.tick = i8 / 100;
            } else {
                throw new UnsupportedOperationException();
            }
        }

        private final int getDurationMs() {
            return (int) (this.durationUs / ((long) 1000));
        }

        public final ProgressUnit getProgressUnit() {
            return this.progressUnit;
        }

        public final boolean isMonotonicTimestamp() {
            return this.isMonotonicTimestamp;
        }

        public final boolean isOverlapIgnored() {
            return this.isOverlapIgnored;
        }

        public final i report() {
            int i2 = WhenMappings.$EnumSwitchMapping$0[this.progressUnit.ordinal()];
            if (i2 == 1) {
                return new i(Integer.valueOf(getDurationMs()), Integer.valueOf(this.totalDurationMs));
            }
            if (i2 == 2) {
                return new i(Integer.valueOf(this.frameCount), Integer.valueOf(this.totalFrameCount));
            }
            throw new UnsupportedOperationException();
        }

        public final i reportFiles() {
            return new i(Integer.valueOf(this.fileCount), Integer.valueOf(this.totalFileCount));
        }

        public final boolean update(MediaCodec.BufferInfo bufferInfo) {
            j.e(bufferInfo, "bufferInfo");
            int i2 = WhenMappings.$EnumSwitchMapping$0[this.progressUnit.ordinal()];
            if (i2 == 1) {
                if (this.firstTimestampUs == 0) {
                    this.firstTimestampUs = bufferInfo.presentationTimeUs;
                }
                if (!this.isMonotonicTimestamp) {
                    long j2 = this.lastTimestampUs;
                    long j3 = bufferInfo.presentationTimeUs;
                    if (j2 > j3) {
                        this.lastTimestampUs = j3;
                    }
                }
                long j8 = this.durationUs;
                long j10 = bufferInfo.presentationTimeUs;
                long j11 = (j10 - this.lastTimestampUs) + j8;
                this.durationUs = j11;
                this.lastTimestampUs = j10;
                if (j11 % ((long) this.tick) <= 35000) {
                    return true;
                }
                return false;
            } else if (i2 == 2) {
                int i7 = this.frameCount + 1;
                this.frameCount = i7;
                if (i7 % this.tick == 0) {
                    return true;
                }
                return false;
            } else {
                throw new UnsupportedOperationException();
            }
        }

        public final void updateFileCount() {
            this.fileCount++;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ProgressTracker(ProgressUnit progressUnit2, List list, boolean z, boolean z3, int i2, e eVar) {
            this(progressUnit2, list, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? true : z3);
        }
    }

    public final Future<Result> export(List<MPFile> list, File file) {
        Throwable th;
        j.e(list, "inputs");
        j.e(file, "output");
        List<MPFile> computeDistinctDuration = computeDistinctDuration(list);
        String str = TAG;
        String R02 = C1194l.R0(computeDistinctDuration, "\n", (String) null, (String) null, (b) null, 62);
        SLog.i(str, "exportVideo: targetFiles => \n" + R02);
        if (file.exists()) {
            String path = file.getPath();
            SLog.w(str, "delete exist output file: " + path);
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            VideoTransfer.Builder builder = new VideoTransfer.Builder();
            FileDescriptor fd2 = fileOutputStream.getFD();
            j.d(fd2, "getFD(...)");
            builder.setOutputFile(new MediaFile(fd2));
            builder.setTransferMode(VideoTransfer.TransferMode.FLEXIBLE);
            MediaFormat prepareTasks = prepareTasks(computeDistinctDuration, builder);
            if (prepareTasks.containsKey(Message.KEY_ROTATION)) {
                builder.setOrientationHint(prepareTasks.getInteger(Message.KEY_ROTATION));
            }
            this._videoTransfer = builder.build();
            getVideoTransfer().setOnEventListener(this.eventListener);
            getVideoTransfer().start();
            Future<Result> executeTasks = executeTasks(computeDistinctDuration.size(), prepareTasks, new MotionScrap$export$4$1(this));
            fileOutputStream.close();
            return executeTasks;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            B1.a.g(fileOutputStream, th);
            throw th3;
        }
    }

    public final Future<Result> export(List<String> list, String str) {
        j.e(list, "inputs");
        j.e(str, "output");
        try {
            Iterable<String> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (String file : iterable) {
                arrayList.add(new MPFile(new File(file)));
            }
            return export((List<MPFile>) arrayList, new File(str));
        } catch (Exception e) {
            Exception exc = e;
            String str2 = TAG;
            SLog.e(str2, "export failed: " + exc);
            ExportEvent exportEvent = ExportEvent.EXECUTE_FAIL;
            notifyComplete(exportEvent);
            return CompletableFuture.completedFuture(new Result(exportEvent, 0, 0, 0, 0, (MediaFormat) null, 62, (e) null));
        }
    }

    public final void export(Pair<File, File>... pairArr) {
        j.e(pairArr, "files");
        ArrayList arrayList = new ArrayList(pairArr.length);
        for (Pair<File, File> pair : pairArr) {
            MotionPhotoInfo parse = MotionPhotoInfo.parse((File) pair.first);
            S s = pair.second;
            j.d(s, "second");
            arrayList.add(new i(parse, new MediaFile((File) s)));
        }
        i[] iVarArr = (i[]) arrayList.toArray(new i[0]);
        export((i[]) Arrays.copyOf(iVarArr, iVarArr.length));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        B1.a.g(r1, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        r7 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void export(me.i... r7) {
        /*
            r6 = this;
            java.lang.String r6 = "motionPhotoInfos"
            kotlin.jvm.internal.j.e(r7, r6)
            int r6 = r7.length
            r0 = 0
        L_0x0007:
            if (r0 >= r6) goto L_0x0035
            r1 = r7[r0]
            java.lang.Object r2 = r1.d
            com.samsung.android.motionphoto.utils.v2.MotionPhotoInfo r2 = (com.samsung.android.motionphoto.utils.v2.MotionPhotoInfo) r2
            java.lang.Object r1 = r1.e
            com.samsung.android.motionphoto.utils.v2.MediaFile r1 = (com.samsung.android.motionphoto.utils.v2.MediaFile) r1
            java.io.FileOutputStream r1 = r1.newOutputFileStream()
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch:{ all -> 0x002e }
            r4 = 0
            r3.position(r4)     // Catch:{ all -> 0x002e }
            byte[] r2 = r2.getVideo()     // Catch:{ all -> 0x002e }
            r1.write(r2)     // Catch:{ all -> 0x002e }
            r2 = 0
            B1.a.g(r1, r2)
            int r0 = r0 + 1
            goto L_0x0007
        L_0x002e:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r7 = move-exception
            B1.a.g(r1, r6)
            throw r7
        L_0x0035:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionScrap.export(me.i[]):void");
    }
}
