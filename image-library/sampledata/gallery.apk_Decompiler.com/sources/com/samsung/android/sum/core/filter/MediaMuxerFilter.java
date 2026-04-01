package com.samsung.android.sum.core.filter;

import Ba.h;
import Gb.c;
import N2.j;
import O3.l;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.ConditionVariable;
import android.system.ErrnoException;
import android.system.Int64Ref;
import android.system.Os;
import android.util.Log;
import android.util.Pair;
import c4.C0438h;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.Tag;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.cache.DiskCache;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.MediaMuxerDescriptor;
import com.samsung.android.sum.core.exception.StreamFilterExitException;
import com.samsung.android.sum.core.format.MediaFormatBuilder;
import com.samsung.android.sum.core.functional.CountingLatch;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageProducer;
import com.samsung.android.sum.core.types.MediaType;
import i.C0212a;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import ld.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaMuxerFilter extends MediaInputStreamFilterBase {
    private static final String TAG = Def.tagOf((Class<?>) MediaMuxerFilter.class);
    private String cacheId;
    private final Map<Integer, List<Runnable>> codecFormatDataTasks = new HashMap();
    private final List<Runnable> configDataTasks = new ArrayList();
    private final Object configLock = new Object();
    private MediaFormatBuilder contentsFormatBuilder;
    private final MediaMuxerDescriptor descriptor;
    private Set<MediaType> directMuxingTracks;
    private DiskCache diskCache;
    private final Map<Integer, Map<String, Object>> extraMap = new HashMap();
    private final AtomicReference<ConditionVariable> isChannelReady = new AtomicReference<>();
    private final AtomicBoolean isMuxerRunning = new AtomicBoolean(false);
    private final ConditionVariable isResumed = new ConditionVariable(true);
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private int mediaId = -1;
    private MessageProducer messageProducer;
    private MediaMuxer muxer;
    private final CountingLatch muxerOnReadyCountingLatch = CountingLatch.of();
    private FileDescriptor outputFd;
    private boolean storeCache;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private final Map<MediaType, Pair<String, Integer>> trackIndexMap = new HashMap();

    public MediaMuxerFilter(MediaMuxerDescriptor mediaMuxerDescriptor) {
        super(mediaMuxerDescriptor);
        this.descriptor = mediaMuxerDescriptor;
    }

    private void createMuxer(Message message, String str) {
        FileOutputStream fileOutputStream;
        boolean z = true;
        if (message.get(str) instanceof File) {
            try {
                fileOutputStream = new FileOutputStream((File) message.get(str));
                FileDescriptor fd2 = fileOutputStream.getFD();
                this.outputFd = fd2;
                if (fd2 == null) {
                    z = false;
                }
                Def.require(z);
                this.muxer = new MediaMuxer(this.outputFd, 0);
                fileOutputStream.close();
                return;
            } catch (IOException e) {
                Log.e(TAG, "fail to create muxer. file is invalid." + e);
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            FileDescriptor fileDescriptor = (FileDescriptor) message.get(str);
            this.outputFd = fileDescriptor;
            if (fileDescriptor == null) {
                z = false;
            }
            Def.require(z);
            try {
                this.muxer = new MediaMuxer(this.outputFd, 0);
                return;
            } catch (IOException e7) {
                Log.e(TAG, "fail to create muxer. fd is invalid." + e7);
                return;
            }
        }
        throw th;
    }

    private void feedExistFramesToBufferChannel(FileDescriptor fileDescriptor) {
        SLog.v(getTag(), "feedExistFramesToBufferChannel");
        if (!isInputChannelConfigured()) {
            AtomicReference<ConditionVariable> atomicReference = this.isChannelReady;
            ConditionVariable conditionVariable = new ConditionVariable();
            while (true) {
                if (!atomicReference.compareAndSet((Object) null, conditionVariable)) {
                    if (atomicReference.get() != null) {
                        break;
                    }
                } else {
                    this.isChannelReady.get().block();
                    break;
                }
            }
        }
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            mediaExtractor.setDataSource(fileDescriptor);
            IntStream.range(0, mediaExtractor.getTrackCount()).forEach(new c(1, this, mediaExtractor));
        } catch (IOException | IllegalStateException e) {
            String str = TAG;
            SLog.i(str, "fail to mux: " + e);
        } finally {
            mediaExtractor.release();
        }
    }

    private boolean isDirectMuxingTrack(MediaType mediaType) {
        Tag tag = getTag();
        SLog.d(tag, "isDirectMuxingTrack: mediaType=" + mediaType + ", of filter=" + ((String) this.directMuxingTracks.stream().map(new a(21)).collect(Collectors.joining(ArcCommonLog.TAG_COMMA))));
        return this.directMuxingTracks.stream().anyMatch(new i(mediaType, 2));
    }

    private boolean isMatchedMessageToMuxer(Message message) {
        return Objects.equals(this.descriptor.getExtra(Message.KEY_EXTRA_CONFIG_ID), message.get(Message.KEY_EXTRA_CONFIG_ID));
    }

    private boolean isUseOutExtraFile() {
        return ((Boolean) this.descriptor.getExtra("use-out-extra-file", Boolean.FALSE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$feedExistFramesToBufferChannel$16(MediaExtractor mediaExtractor, int i2) {
        MediaType mediaType;
        String string = mediaExtractor.getTrackFormat(i2).getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
        if (string.startsWith("video")) {
            mediaType = MediaType.RAW_VIDEO;
        } else {
            mediaType = MediaType.RAW_AUDIO;
        }
        BufferChannel inputChannel = getInputChannel(mediaType);
        if (inputChannel == null) {
            SLog.w(getTag(), "no given buffer-channel for " + mediaType);
            return;
        }
        mediaExtractor.selectTrack(i2);
        while (true) {
            int sampleSize = (int) mediaExtractor.getSampleSize();
            if (sampleSize < 0) {
                SLog.v(getTag(), "parser reached EOS");
                mediaExtractor.unselectTrack(i2);
                return;
            }
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(sampleSize);
            allocateDirect.order(ByteOrder.nativeOrder());
            boolean z = false;
            if (sampleSize == mediaExtractor.readSampleData(allocateDirect, 0)) {
                z = true;
            }
            Def.check(z);
            MediaBuffer of2 = MediaBuffer.of(mediaType, allocateDirect);
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            bufferInfo.size = sampleSize;
            bufferInfo.presentationTimeUs = mediaExtractor.getSampleTime();
            if ((mediaExtractor.getSampleFlags() & 1) != 0) {
                bufferInfo.flags |= 1;
            }
            of2.setExtra("buffer-info", bufferInfo);
            mediaExtractor.advance();
            Tag tag = getTag();
            StringBuilder k = j.k("push to buffer-channel[", string, "]: ");
            k.append(bufferInfo.presentationTimeUs);
            k.append("[us]");
            SLog.v(tag, k.toString());
            inputChannel.send(of2);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isDirectMuxingTrack$3(MediaType mediaType, MediaType mediaType2) {
        if (mediaType2.rank() == mediaType.rank()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onMessageReceived$0(Message message) {
        return "onMessageReceived: " + message;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMessageReceived$2(MediaType mediaType, Pair pair) {
        BufferChannel inputChannel = getInputChannel(mediaType);
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        bufferInfo.flags |= 4;
        MediaBuffer allocate = MediaBuffer.newMetaAlloc().setFlags(8).setExtra("buffer-info", bufferInfo).allocate();
        SLog.i(getTag(), "send forced-eos-buffer to " + mediaType + " track");
        inputChannel.send(allocate);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onReceiveCodecFormatData$6(MediaType mediaType, MediaType mediaType2) {
        if (mediaType2.rank() == mediaType.rank()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onReceiveCodecFormatData$7(MediaType mediaType, MediaType mediaType2) {
        if (mediaType2.rank() == mediaType.rank()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onReceiveCodecFormatData$8(Message message) {
        if (this.muxer == null) {
            SLog.d(getTag(), "no muxer exist, ignore this");
        } else if (this.isMuxerRunning.get()) {
            SLog.i(getTag(), "skip this. muxer is already running...");
        } else {
            if (message.containsAll(Message.KEY_MEDIA_TYPE, "media-format")) {
                MediaType mediaType = (MediaType) message.get(Message.KEY_MEDIA_TYPE);
                MediaFormat mediaFormat = new MediaFormat((MediaFormat) message.get("media-format"));
                if (!this.descriptor.getMediaTypes().stream().noneMatch(new i(mediaType, 0)) || this.trackIndexMap.isEmpty() || !this.trackIndexMap.keySet().stream().anyMatch(new i(mediaType, 1))) {
                    if (mediaType.isVideo() && mediaFormat.containsKey(Message.KEY_ROTATION)) {
                        int integer = mediaFormat.getInteger(Message.KEY_ROTATION);
                        this.muxer.setOrientationHint(integer);
                        this.contentsFormatBuilder.setAttribute(Message.KEY_ROTATION, Integer.valueOf(integer));
                    }
                    if (mediaFormat.containsKey("width")) {
                        this.contentsFormatBuilder.setCols(mediaFormat.getInteger("width"));
                    }
                    if (mediaFormat.containsKey("height")) {
                        this.contentsFormatBuilder.setRows(mediaFormat.getInteger("height"));
                    }
                    int addTrack = this.muxer.addTrack(mediaFormat);
                    this.trackIndexMap.put(mediaType, new Pair(mediaFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX), Integer.valueOf(addTrack)));
                    message.reply("track-idx", Integer.valueOf(addTrack));
                } else {
                    Tag tag = getTag();
                    SLog.d(tag, "skip muxing target data on=" + mediaType);
                    return;
                }
            }
            int up = this.muxerOnReadyCountingLatch.up();
            Tag tag2 = getTag();
            SLog.d(tag2, "onReceiveCodecFormatData: countingLatch cnt=" + up);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$onReceiveCodecFormatData$9(Integer num) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x014e A[SYNTHETIC, Splitter:B:48:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0154 A[SYNTHETIC, Splitter:B:51:0x0154] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01c7  */
    /* JADX WARNING: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$onReceiveConfigureData$10(com.samsung.android.sum.core.message.Message r8) {
        /*
            r7 = this;
            int r0 = r7.mediaId
            r1 = 1
            r2 = 0
            r3 = -1
            if (r0 != r3) goto L_0x0009
            r0 = r1
            goto L_0x000a
        L_0x0009:
            r0 = r2
        L_0x000a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "contentsId is not reset yet: "
            r4.<init>(r5)
            int r5 = r7.mediaId
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.Object[] r5 = new java.lang.Object[r2]
            com.samsung.android.sum.core.Def.require(r0, r4, r5)
            com.samsung.android.sum.core.Tag r0 = r7.getTag()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "msg="
            r4.<init>(r5)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r4)
            java.lang.Object r0 = r7.configLock
            monitor-enter(r0)
            java.lang.String r4 = "media-id"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01df }
            java.lang.Object r3 = r8.get(r4, r3)     // Catch:{ all -> 0x01df }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ all -> 0x01df }
            int r3 = r3.intValue()     // Catch:{ all -> 0x01df }
            r7.mediaId = r3     // Catch:{ all -> 0x01df }
            monitor-exit(r0)     // Catch:{ all -> 0x01df }
            com.samsung.android.sum.core.Tag r0 = r7.getTag()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "update contentsId: "
            r3.<init>(r4)
            int r4 = r7.mediaId
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r3)
            com.samsung.android.sum.core.Tag r0 = r7.getTag()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "isUseOutExtraFile="
            r3.<init>(r4)
            boolean r4 = r7.isUseOutExtraFile()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r3)
            boolean r0 = r7.isUseOutExtraFile()
            if (r0 == 0) goto L_0x0097
            java.lang.String r0 = "output-extra-file"
            boolean r0 = r8.contains(r0)
            if (r0 == 0) goto L_0x0097
            com.samsung.android.sum.core.Tag r0 = r7.getTag()
            java.lang.String r3 = "use out extra file on outputFd"
            com.samsung.android.sum.core.SLog.i((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r3)
            java.lang.String r0 = "output-extra-file"
            r7.createMuxer(r8, r0)
            goto L_0x009c
        L_0x0097:
            java.lang.String r0 = "output-file"
            r7.createMuxer(r8, r0)
        L_0x009c:
            com.samsung.android.sum.core.Tag r0 = r7.getTag()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "outputFd size: "
            r3.<init>(r4)
            java.io.FileDescriptor r4 = r7.outputFd
            long r4 = com.samsung.android.sum.core.Def.getFileSize(r4)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r3)
            android.media.MediaMuxer r0 = r7.muxer
            if (r0 == 0) goto L_0x00de
            java.util.Map r0 = r8.get()
            java.lang.String r3 = "location"
            boolean r0 = r0.containsKey(r3)
            if (r0 == 0) goto L_0x00de
            java.lang.String r0 = "location"
            java.lang.Object r0 = r8.get(r0)
            android.location.Location r0 = (android.location.Location) r0
            android.media.MediaMuxer r3 = r7.muxer
            double r4 = r0.getLatitude()
            float r4 = (float) r4
            double r5 = r0.getLongitude()
            float r0 = (float) r5
            r3.setLocation(r4, r0)
        L_0x00de:
            java.lang.String r0 = "cache-id"
            java.lang.Object r0 = r8.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.util.Optional r0 = java.util.Optional.ofNullable(r0)
            com.samsung.android.sdk.scs.ai.visual.c2pa.a r3 = new com.samsung.android.sdk.scs.ai.visual.c2pa.a
            r4 = 24
            r3.<init>(r4)
            java.util.Optional r0 = r0.map(r3)
            r3 = 0
            java.lang.Object r0 = r0.orElse(r3)
            java.lang.String r0 = (java.lang.String) r0
            r7.cacheId = r0
            boolean r4 = r7.storeCache
            if (r4 != 0) goto L_0x0174
            com.samsung.android.sum.core.cache.DiskCache r4 = r7.diskCache
            if (r4 == 0) goto L_0x0174
            if (r0 == 0) goto L_0x0174
            java.io.File r0 = r4.get(r0)
            if (r0 == 0) goto L_0x015d
            boolean r4 = r0.exists()
            if (r4 == 0) goto L_0x015d
            com.samsung.android.sum.core.Tag r4 = r7.getTag()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "restore from cache: "
            r5.<init>(r6)
            java.lang.String r6 = r7.cacheId
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r4, (java.lang.String) r5)
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0148 }
            r4.<init>(r0)     // Catch:{ IOException -> 0x0148 }
            java.io.FileDescriptor r0 = r4.getFD()     // Catch:{ IOException -> 0x0143, all -> 0x0140 }
            r7.feedExistFramesToBufferChannel(r0)     // Catch:{ IOException -> 0x0143, all -> 0x0140 }
            r4.close()     // Catch:{ IOException -> 0x013b }
            goto L_0x0174
        L_0x013b:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0174
        L_0x0140:
            r7 = move-exception
            r3 = r4
            goto L_0x0152
        L_0x0143:
            r0 = move-exception
            r3 = r4
            goto L_0x0149
        L_0x0146:
            r7 = move-exception
            goto L_0x0152
        L_0x0148:
            r0 = move-exception
        L_0x0149:
            r0.printStackTrace()     // Catch:{ all -> 0x0146 }
            if (r3 == 0) goto L_0x0174
            r3.close()     // Catch:{ IOException -> 0x013b }
            goto L_0x0174
        L_0x0152:
            if (r3 == 0) goto L_0x015c
            r3.close()     // Catch:{ IOException -> 0x0158 }
            goto L_0x015c
        L_0x0158:
            r8 = move-exception
            r8.printStackTrace()
        L_0x015c:
            throw r7
        L_0x015d:
            com.samsung.android.sum.core.Tag r0 = r7.getTag()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "no cache exist: "
            r3.<init>(r4)
            java.lang.String r4 = r7.cacheId
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r3)
        L_0x0174:
            com.samsung.android.sum.core.format.MediaFormatBuilder r0 = com.samsung.android.sum.core.format.MediaFormat.newImageBuilder()
            r7.contentsFormatBuilder = r0
            java.lang.String r0 = "track-count"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r8 = r8.get(r0, r2)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            com.samsung.android.sum.core.descriptor.MediaMuxerDescriptor r0 = r7.descriptor
            java.util.List r0 = r0.getMediaTypes()
            int r0 = r0.size()
            int r8 = java.lang.Integer.min(r8, r0)
            com.samsung.android.sum.core.functional.CountingLatch r0 = r7.muxerOnReadyCountingLatch
            int r2 = r7.getInputChannelCount()
            int r2 = r2 - r8
            int r2 = r2 + r1
            int r8 = r0.up(r2)
            com.samsung.android.sum.core.Tag r0 = r7.getTag()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onReceiveConfigureData: countingLatch cnt="
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r8)
            java.util.Map<java.lang.Integer, java.util.List<java.lang.Runnable>> r8 = r7.codecFormatDataTasks
            int r0 = r7.mediaId
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            boolean r8 = r8.containsKey(r0)
            if (r8 == 0) goto L_0x01de
            java.util.Map<java.lang.Integer, java.util.List<java.lang.Runnable>> r8 = r7.codecFormatDataTasks
            int r7 = r7.mediaId
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r7 = r8.remove(r7)
            java.util.List r7 = (java.util.List) r7
            O3.l r8 = new O3.l
            r0 = 0
            r8.<init>(r0)
            r7.forEach(r8)
        L_0x01de:
            return
        L_0x01df:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01df }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.filter.MediaMuxerFilter.lambda$onReceiveConfigureData$10(com.samsung.android.sum.core.message.Message):void");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$onReceiveExtraData$11(Integer num) {
        return new HashMap();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onReceiveExtraData$12(Map map, String str, Object obj) {
        if (!map.containsKey(str)) {
            map.put(str, obj);
        } else if (map.get(str) instanceof List) {
            ((List) map.get(str)).add(obj);
        } else {
            map.put(str, new ArrayList<Object>(map.get(str), obj) {
                final /* synthetic */ Object val$prevValue;
                final /* synthetic */ Object val$value;

                {
                    this.val$prevValue = r2;
                    this.val$value = r3;
                    add(r2);
                    add(r3);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaType lambda$prepare$4(String str) {
        if (str.contains("video")) {
            return MediaType.VIDEO;
        }
        if (str.contains("audio")) {
            return MediaType.AUDIO;
        }
        throw new UnsupportedOperationException("not supported media-type: ".concat(str));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Set lambda$prepare$5(Object obj) {
        Stream stream;
        if (obj instanceof String) {
            stream = Stream.of((String) obj);
        } else if (obj instanceof String[]) {
            stream = Arrays.stream((String[]) obj);
        } else if (obj instanceof List) {
            stream = ((List) obj).stream();
        } else {
            throw new IllegalArgumentException("invalid type: " + obj);
        }
        return (Set) stream.map(new a(27)).collect(Collectors.toSet());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$run$13(Pair pair, MediaType mediaType) {
        String str;
        Pair pair2 = pair;
        MediaType mediaType2 = mediaType;
        try {
            int intValue = ((Integer) pair2.second).intValue();
            addTag(mediaType2, "track: " + ((String) pair2.first));
            SLog.d(getTag(mediaType2), ": muxing on track start for " + this.mediaId);
            BufferChannel inputChannel = getInputChannel(mediaType2);
            boolean z = false;
            long j2 = 0;
            int i2 = 0;
            while (!z) {
                this.isResumed.block();
                MediaBuffer mediaBuffer = (MediaBuffer) inputChannel.receive();
                SLog.v(getTag(mediaType2), "receive buffer: " + mediaBuffer);
                MediaCodec.BufferInfo bufferInfo = (MediaCodec.BufferInfo) mediaBuffer.getExtra("buffer-info");
                if (bufferInfo == null) {
                    bufferInfo = new MediaCodec.BufferInfo();
                    bufferInfo.size = ((Integer) mediaBuffer.getExtra("chunk-size", -1)).intValue();
                    bufferInfo.presentationTimeUs = ((Long) mediaBuffer.getExtra(Message.KEY_TIMESTAMP_US, -1L)).longValue();
                    if (mediaBuffer.containFlags(8)) {
                        bufferInfo.flags |= 4;
                    }
                    SLog.d(getTag(mediaType2), Def.fmtstr("make buffer-info from media-buffer(size=%d, ts=%d, flags=0x%x)", Integer.valueOf(bufferInfo.size), Long.valueOf(bufferInfo.presentationTimeUs), Integer.valueOf(bufferInfo.flags)));
                }
                boolean z3 = true;
                if (mediaBuffer.containsExtra("sample-flag")) {
                    int intValue2 = ((Integer) mediaBuffer.getExtra("sample-flag")).intValue();
                    if ((intValue2 & 1) == 1) {
                        bufferInfo.flags |= 1;
                    }
                    if ((intValue2 & 4) == 4) {
                        bufferInfo.flags |= 8;
                    }
                }
                if ((bufferInfo.flags & 4) == 0) {
                    z3 = z;
                } else if (mediaBuffer.containsExtra("media-id") && ((Integer) mediaBuffer.getExtra("media-id")).intValue() != this.mediaId) {
                    Tag tag = getTag(mediaType2);
                    Integer num = (Integer) mediaBuffer.getExtra("media-id");
                    num.intValue();
                    SLog.w(tag, Def.fmtstr("ignore mismatched eos-buffer: media-id of buffer(%d) - one of muxer(%d)", num, Integer.valueOf(this.mediaId)));
                    mediaBuffer.release();
                }
                if (bufferInfo.size > 0) {
                    ByteBuffer byteBuffer = (ByteBuffer) mediaBuffer.getTypedData(ByteBuffer.class);
                    byteBuffer.position(bufferInfo.offset);
                    byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                    Tag tag2 = getTag(mediaType2);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("write data[#");
                    i2++;
                    sb2.append(i2);
                    sb2.append("]: ");
                    sb2.append(bufferInfo.presentationTimeUs);
                    sb2.append("us");
                    SLog.d(tag2, sb2.toString());
                    try {
                        this.muxer.writeSampleData(intValue, byteBuffer, bufferInfo);
                    } catch (Exception e) {
                        SLog.d(getTag(mediaType2), "fail to write sample: " + e);
                    }
                    SLog.v(getTag(mediaType2), "outputFd size: " + Def.getFileSize(this.outputFd));
                    j2 = bufferInfo.presentationTimeUs;
                    if (this.descriptor.isMediaTypeToNotifyEvent(mediaType2)) {
                        this.messageProducer.newMessage(108, (Pair<String, Object>[]) new Pair[]{new Pair("media-id", Integer.valueOf(this.mediaId)), new Pair(Message.KEY_MEDIA_TYPE, mediaType2), new Pair(Message.KEY_PROCESSED_FRAMES, Integer.valueOf(i2))}).post();
                    }
                }
                if (z3) {
                    SLog.i(getTag(mediaType2), "muxer reached EOS: " + this.mediaId + ", write # = " + i2);
                }
                List list = (List) mediaBuffer.getExtra("eos-release-listener");
                if (list != null) {
                    SLog.i(getTag(mediaType2), "found eosReleaseListener: " + list);
                    list.forEach(new l(0));
                }
                mediaBuffer.release();
                z = z3;
            }
            MediaFormatBuilder mediaFormatBuilder = this.contentsFormatBuilder;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("last-");
            if (mediaType2.isVideo()) {
                str = "video";
            } else {
                str = "audio";
            }
            sb3.append(str);
            sb3.append("-timestamp-us");
            mediaFormatBuilder.setAttribute(sb3.toString(), Long.valueOf(j2));
            SLog.d(getTag(mediaType2), ": muxing on track end");
            return Boolean.TRUE;
        } catch (Exception e7) {
            e7.printStackTrace();
            SLog.w(getTag(mediaType2), "fail to mux track: " + e7);
            return Boolean.FALSE;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$14(List list, MediaType mediaType, Pair pair) {
        list.add(this.threadPool.submit(new Tc.a((Object) this, (Object) pair, (Object) mediaType, 6)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$run$15(File file) {
        try {
            Os.sendfile(new FileOutputStream(file).getFD(), this.outputFd, new Int64Ref(0), Def.getFileSize(this.outputFd));
            return Boolean.TRUE;
        } catch (ErrnoException | IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    private void onReceiveCodecFormatData(Message message) {
        String join = String.join(ArcCommonLog.TAG_COMMA, new CharSequence[]{"media-id=" + message.get("media-id", -1), "media-type=" + message.get(Message.KEY_MEDIA_TYPE), "direct-mux-track: " + ((String) message.get("direct-mux-track", "n/a"))});
        SLog.i(getTag(), "onReceiveCodecFormatData: " + join);
        MediaType mediaType = (MediaType) message.get(Message.KEY_MEDIA_TYPE);
        Objects.requireNonNull(mediaType);
        boolean isDirectMuxingTrack = isDirectMuxingTrack(mediaType);
        if (!isDirectMuxingTrack && message.contains("direct-mux-track")) {
            SLog.i(getTag(), "not matched media-type for direct muxing track. skip this: " + join);
        } else if (!isDirectMuxingTrack || message.contains("direct-mux-track")) {
            j jVar = new j(this, message, 1);
            Integer num = (Integer) message.get("media-id");
            Objects.requireNonNull(num);
            int intValue = num.intValue();
            synchronized (this.configLock) {
                try {
                    if (this.muxer != null) {
                        if (intValue == this.mediaId) {
                            SLog.d(getTag(), "run track-config task now");
                            jVar.run();
                        }
                    }
                    SLog.d(getTag(), "store track-config task & run later");
                    this.codecFormatDataTasks.computeIfAbsent(num, new a(26)).add(jVar);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else {
            SLog.i(getTag(), "matched media-type for direct muxing track but config-data is not. skip this: " + join);
        }
    }

    private void onReceiveConfigureData(Message message) {
        Tag tag = getTag();
        SLog.i(tag, "onReceiveConfigureData: " + message.get("media-id", -1));
        j jVar = new j(this, message, 0);
        synchronized (this.configLock) {
            try {
                if (this.mediaId != -1 || !this.configDataTasks.isEmpty()) {
                    SLog.d(getTag(), "store config task & run later");
                    this.configDataTasks.add(jVar);
                } else {
                    SLog.d(getTag(), "run config task now");
                    jVar.run();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void onReceiveExtraData(int i2, Map<String, Object> map) {
        map.forEach(new A9.a(20, this, this.extraMap.computeIfAbsent(Integer.valueOf(i2), new a(23))));
    }

    private String toString(MediaCodec.BufferInfo bufferInfo) {
        StringBuilder sb2 = new StringBuilder("flags=0x");
        sb2.append(Integer.toHexString(bufferInfo.flags));
        sb2.append(", ts=");
        return C0212a.o(sb2, bufferInfo.presentationTimeUs, " us");
    }

    public void configInputChannel(Function<Enum<?>, BufferChannel> function, int i2) {
        super.configInputChannel(function, i2);
        if (this.isChannelReady.get() != null) {
            this.isChannelReady.get().open();
        }
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public int[] getMessagesToReceive() {
        return new int[]{204, 203, 1, Message.FORCE_STOP};
    }

    public boolean onMessageReceived(Message message) {
        SLog.d(getTag(), (Supplier<String>) new c(message, 2), (Consumer<Exception>) new C0438h(12));
        int code = message.getCode();
        boolean z = false;
        if (code == 1) {
            Pair pair = (Pair) message.remove("cache");
            this.diskCache = (DiskCache) pair.first;
            this.storeCache = ((Boolean) pair.second).booleanValue();
            if (this.diskCache != null) {
                z = true;
            }
            Def.require(z);
            SLog.d(getTag(), "store: " + this.storeCache + ", disk-cache: " + this.diskCache);
            return true;
        } else if (code != 711) {
            if (code != 203) {
                if (code != 204) {
                    return false;
                }
                if (isMatchedMessageToMuxer(message)) {
                    onReceiveConfigureData(message);
                    return true;
                }
            } else if (isMatchedMessageToMuxer(message)) {
                onReceiveCodecFormatData(message);
            }
            return true;
        } else {
            SLog.i(getTag(), "receive force-stop-message");
            if (!((Boolean) message.get("skip-inject-eos-meta", Boolean.FALSE)).booleanValue()) {
                this.trackIndexMap.forEach(new h(28, this));
            }
            this.muxerOnReadyCountingLatch.reset();
            return true;
        }
    }

    public void pause() {
        this.isResumed.close();
    }

    public void prepare() {
        super.prepare();
        this.isRunning.set(true);
        this.directMuxingTracks = (Set) Optional.ofNullable(this.descriptor.getExtra("direct-mux-track")).map(new a(25)).orElseGet(new b(7));
    }

    public void release() {
        SLog.d(getTag(), "release...E");
        if (this.isRunning.compareAndSet(true, false)) {
            this.extraMap.clear();
            synchronized (this.configLock) {
                this.configDataTasks.clear();
            }
            this.codecFormatDataTasks.clear();
            this.muxerOnReadyCountingLatch.reset();
            this.threadPool.shutdownNow();
        }
        SLog.d(getTag(), "release...X");
    }

    public void resume() {
        this.isResumed.open();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        Tag tag;
        StringBuilder sb2;
        boolean z;
        File file;
        SLog.v(getTag(), "run: ibuf=" + mediaBuffer + ", obuf=" + mutableMediaBuffer);
        try {
            if (getInputChannelCount() != 0) {
                z = true;
            } else {
                z = false;
            }
            Def.require(z);
            synchronized (this.configLock) {
                if (!this.configDataTasks.isEmpty()) {
                    this.configDataTasks.remove(0).run();
                }
                SLog.v(getTag(), "mediaId=" + this.mediaId);
            }
            if (this.isRunning.get()) {
                this.muxerOnReadyCountingLatch.await(getInputChannelCount() + 1);
                if (this.muxer != null) {
                    this.isMuxerRunning.set(true);
                    this.muxer.start();
                    ArrayList arrayList = new ArrayList();
                    this.trackIndexMap.forEach(new A9.a(19, this, arrayList));
                    SLog.d(getTag(), "wait result received...E: " + arrayList.size());
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Future future = (Future) it.next();
                        try {
                            SLog.d(getTag(), "result: " + future.get());
                        } catch (CancellationException | ExecutionException e) {
                            SLog.d(getTag(), "task canceled: " + e.getMessage());
                        }
                    }
                    arrayList.clear();
                    SLog.d(getTag(), "wait result received...X");
                    SLog.d(getTag(), "total outputFd size: " + Def.getFileSize(this.outputFd));
                    this.muxer.stop();
                    DiskCache diskCache2 = this.diskCache;
                    if (diskCache2 != null) {
                        if (this.storeCache) {
                            if (this.cacheId == null) {
                                this.cacheId = "";
                            }
                            SLog.d(getTag(), "cache output file to " + this.cacheId);
                            this.diskCache.put(this.cacheId, new com.samsung.android.motionphoto.utils.ex.b(8, this));
                        } else {
                            String str = this.cacheId;
                            if (!(str == null || (file = diskCache2.get(str)) == null || !file.exists())) {
                                boolean delete = file.delete();
                                SLog.d(getTag(), "cache is consumed, remove it: " + delete);
                            }
                        }
                    }
                    MediaMuxer mediaMuxer = this.muxer;
                    if (mediaMuxer != null) {
                        mediaMuxer.release();
                        this.muxer = null;
                        tag = getTag();
                        sb2 = new StringBuilder("muxer released: ");
                        sb2.append(this.mediaId);
                        SLog.i(tag, sb2.toString());
                    }
                    this.isMuxerRunning.set(false);
                    this.trackIndexMap.clear();
                    MediaBuffer wrap = MediaBuffer.newAlloc().setMediaFormat((com.samsung.android.sum.core.format.MediaFormat) Optional.ofNullable(this.contentsFormatBuilder).map(new a(22)).orElse((Object) null)).setExtra("media-id", Integer.valueOf(this.mediaId)).wrap(this.outputFd);
                    String str2 = this.cacheId;
                    if (str2 != null) {
                        wrap.setExtra(Message.KEY_CACHE_ID, str2);
                    }
                    if (isUseOutExtraFile()) {
                        wrap.setExtra("use-out-extra-file", Boolean.TRUE);
                    }
                    mutableMediaBuffer.put(wrap);
                    synchronized (this.configLock) {
                        this.mediaId = -1;
                        this.muxerOnReadyCountingLatch.reset();
                    }
                    return mutableMediaBuffer;
                }
                mediaBuffer.release();
                throw new StreamFilterExitException("no muxer is given, might be released");
            }
            throw new StreamFilterExitException("filter is already released");
        } catch (Exception e7) {
            try {
                SLog.w(getTag(), "fail to muxing: " + e7);
                MediaMuxer mediaMuxer2 = this.muxer;
                if (mediaMuxer2 != null) {
                    mediaMuxer2.release();
                    this.muxer = null;
                    tag = getTag();
                    sb2 = new StringBuilder("muxer released: ");
                }
            } catch (Throwable th) {
                MediaMuxer mediaMuxer3 = this.muxer;
                if (mediaMuxer3 != null) {
                    mediaMuxer3.release();
                    this.muxer = null;
                    SLog.i(getTag(), "muxer released: " + this.mediaId);
                }
                this.isMuxerRunning.set(false);
                this.trackIndexMap.clear();
                throw th;
            }
        } catch (Throwable th2) {
            while (true) {
            }
            throw th2;
        }
    }

    public void setMessageProducer(MessageProducer messageProducer2) {
        this.messageProducer = messageProducer2;
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onMessageReceived$1(Exception exc) {
    }
}
