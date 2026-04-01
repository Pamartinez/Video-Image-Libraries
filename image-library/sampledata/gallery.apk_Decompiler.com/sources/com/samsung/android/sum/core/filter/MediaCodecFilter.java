package com.samsung.android.sum.core.filter;

import A.a;
import Ad.C0720a;
import D8.C0546a;
import android.media.MediaCodec;
import android.os.ConditionVariable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.Tag;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.descriptor.CodecDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.functional.CountingLatch;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageProducer;
import com.samsung.android.sum.core.types.MediaType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaCodecFilter extends MediaInOutStreamFilterBase {
    private static final String TAG = Def.tagOf((Class<?>) MediaCodecFilter.class);
    protected CountingLatch bufferInUseCounter;
    protected final CodecDescriptor codecDescriptor;
    protected final CountingLatch codecOnReadyCountingLatch = CountingLatch.of();
    protected final ConditionVariable cvPause;
    protected AtomicLong endTimeUs = new AtomicLong(Long.MAX_VALUE);
    protected final Map<Integer, MediaBuffer> eosBuffers = new HashMap();
    protected Set<Integer> flushRequestedContents;
    protected boolean isReachedInputEos;
    protected boolean isReachedOutputEos;
    protected final Map<Integer, Long> lastTimestampToProcess = new HashMap();
    protected MediaCodec mediaCodec;
    protected int mediaId = -1;
    protected MessageProducer messageProducer;
    protected final Map<Integer, Integer> numFramesToProcess = new HashMap();
    protected int processedFrames = 0;
    protected AtomicLong startTimeUs = new AtomicLong(-1);

    public MediaCodecFilter(CodecDescriptor codecDescriptor2) {
        super(codecDescriptor2);
        ConditionVariable conditionVariable = new ConditionVariable();
        this.cvPause = conditionVariable;
        this.flushRequestedContents = new HashSet();
        this.codecDescriptor = codecDescriptor2;
        conditionVariable.open();
    }

    private void forceStop() {
        SLog.i(getTag(), " receive force-stop-message");
        this.isReachedInputEos = true;
        this.isReachedOutputEos = true;
        if (((CodecDescriptor) getDescriptor()).isRunInstant()) {
            release();
            return;
        }
        this.mediaId = -1;
        this.codecOnReadyCountingLatch.reset();
    }

    private boolean isMatchedMessageToCodec(Message message) {
        boolean z;
        CodecDescriptor codecDescriptor2 = (CodecDescriptor) getDescriptor();
        MediaType mediaType = (MediaType) message.get(Message.KEY_MEDIA_TYPE);
        if (((!mediaType.isAudio() || !codecDescriptor2.getMediaType().isAudio()) && (!mediaType.isVideo() || !codecDescriptor2.getMediaType().isVideo())) || !Objects.equals(codecDescriptor2.getExtra(Message.KEY_EXTRA_CONFIG_ID), message.get(Message.KEY_EXTRA_CONFIG_ID))) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            Tag tag = getTag(mediaType);
            SLog.d(tag, "message of " + mediaType + " is not match this codec type " + codecDescriptor2.getMediaType());
        }
        return z;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int[] lambda$getMessagesToReceive$0() {
        return new int[0];
    }

    public abstract void configCodec(Message message);

    public MFDescriptor getDescriptor() {
        return this.codecDescriptor;
    }

    public MediaCodec getMediaCodec() {
        return this.mediaCodec;
    }

    public int[] getMessagesToReceive() {
        return IntStream.concat(IntStream.of(new int[]{201, Message.END_OF_STREAM, Message.CODEC_NUM_EXTRA_FRAMES, 4, Message.FORCE_STOP}), Arrays.stream((int[]) Optional.ofNullable(this.codecDescriptor.getMessageToReceive()).orElseGet(new C0720a(16)))).toArray();
    }

    public Tag getTag() {
        return getTag(this.codecDescriptor.getMediaType());
    }

    public boolean onMessageReceived(Message message) {
        SLog.v(getTag(), "onMessageReceived: " + message.getCode());
        HashMap hashMap = new HashMap();
        int code = message.getCode();
        boolean z = true;
        if (code == 4) {
            Set<Integer> set = this.flushRequestedContents;
            Integer num = (Integer) message.get("media-id");
            Objects.requireNonNull(num);
            set.add(num);
        } else if (code != 201) {
            if (code == 711) {
                forceStop();
            } else if (code == 205) {
                Integer num2 = (Integer) message.get("media-id", -1);
                if (num2.intValue() > 0 && this.numFramesToProcess.get(num2).intValue() != 0) {
                    int intValue = ((Integer) message.get("extra-frames")).intValue();
                    SLog.v(getTag(), a.d(this.numFramesToProcess.computeIfPresent(num2, new C0546a(intValue, 1)).intValue(), intValue, "update whole frame num to ", "(add ", ")"));
                }
            } else if (code != 206) {
                z = false;
            } else {
                Integer num3 = (Integer) message.get("media-id", -1);
                int intValue2 = num3.intValue();
                Long l = (Long) message.get("last-timestampUs");
                long longValue = l.longValue();
                if (intValue2 > 0) {
                    SLog.v(getTag(), "last timestampUs set as " + longValue);
                    this.lastTimestampToProcess.put(num3, l);
                }
            }
        } else if (!isMatchedMessageToCodec(message)) {
            return false;
        } else {
            configCodec(message);
            SLog.d(getTag(), "initialize numToProcess & lastTimestampToProcess for " + this.mediaId);
            this.numFramesToProcess.putIfAbsent(Integer.valueOf(this.mediaId), 0);
            this.lastTimestampToProcess.putIfAbsent(Integer.valueOf(this.mediaId), Long.MAX_VALUE);
            this.codecOnReadyCountingLatch.up(1);
        }
        if (message.isRequestToReply()) {
            message.reply(hashMap);
        }
        return z;
    }

    public void pause() {
        this.cvPause.close();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:22|23|(1:25)(1:26)|27|28|(1:30)|31) */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0061, code lost:
        if (r8.bufferInUseCounter.getCount() > r1) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0063, code lost:
        r8.messageProducer.newMessage(-2).post();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006e, code lost:
        r4 = getTag();
        com.samsung.android.sum.core.SLog.w(r4, "codec stop called but not started yet. minCount=" + r1 + "remain=" + r8.bufferInUseCounter.getCount());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r0 = r8.bufferInUseCounter;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0091, code lost:
        if (r0 != null) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0093, code lost:
        r0.reset();
        r8.bufferInUseCounter = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0098, code lost:
        r0 = r8.mediaCodec;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x005b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void release() {
        /*
            r8 = this;
            java.lang.String r0 = "codec stop called but not started yet. minCount="
            monitor-enter(r8)
            com.samsung.android.sum.core.Tag r1 = r8.getTag()     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "release...E"
            com.samsung.android.sum.core.SLog.v((com.samsung.android.sum.core.Tag) r1, (java.lang.String) r2)     // Catch:{ all -> 0x0050 }
            android.media.MediaCodec r1 = r8.mediaCodec     // Catch:{ all -> 0x0050 }
            r2 = 0
            if (r1 == 0) goto L_0x00ac
            com.samsung.android.sum.core.descriptor.CodecDescriptor r1 = r8.codecDescriptor     // Catch:{ all -> 0x0050 }
            com.samsung.android.sum.core.types.MediaType r1 = r1.getMediaType()     // Catch:{ all -> 0x0050 }
            java.util.Optional r1 = java.util.Optional.ofNullable(r1)     // Catch:{ all -> 0x0050 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.a r3 = new com.samsung.android.sdk.scs.ai.visual.c2pa.a     // Catch:{ all -> 0x0050 }
            r4 = 20
            r3.<init>(r4)     // Catch:{ all -> 0x0050 }
            java.util.Optional r1 = r1.map(r3)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r3 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0050 }
            java.lang.Object r1 = r1.orElse(r3)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0050 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0050 }
            r3 = 0
            com.samsung.android.sum.core.functional.CountingLatch r4 = r8.bufferInUseCounter     // Catch:{ IllegalStateException -> 0x005b }
            if (r4 == 0) goto L_0x0041
            java.lang.String r5 = "wait encoded buffer to release"
            r6 = 2000(0x7d0, double:9.88E-321)
            r4.await(r2, r6, r5)     // Catch:{ IllegalStateException -> 0x005b }
            goto L_0x0041
        L_0x003f:
            r0 = move-exception
            goto L_0x009b
        L_0x0041:
            android.media.MediaCodec r4 = r8.mediaCodec     // Catch:{ IllegalStateException -> 0x005b }
            r4.stop()     // Catch:{ IllegalStateException -> 0x005b }
            com.samsung.android.sum.core.functional.CountingLatch r0 = r8.bufferInUseCounter     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x0053
            r0.reset()     // Catch:{ all -> 0x0050 }
            r8.bufferInUseCounter = r3     // Catch:{ all -> 0x0050 }
            goto L_0x0053
        L_0x0050:
            r0 = move-exception
            goto L_0x00d7
        L_0x0053:
            android.media.MediaCodec r0 = r8.mediaCodec     // Catch:{ all -> 0x0050 }
        L_0x0055:
            r0.release()     // Catch:{ all -> 0x0050 }
            r8.mediaCodec = r3     // Catch:{ all -> 0x0050 }
            goto L_0x00ac
        L_0x005b:
            com.samsung.android.sum.core.functional.CountingLatch r4 = r8.bufferInUseCounter     // Catch:{ all -> 0x003f }
            int r4 = r4.getCount()     // Catch:{ all -> 0x003f }
            if (r4 <= r1) goto L_0x006e
            com.samsung.android.sum.core.message.MessageProducer r0 = r8.messageProducer     // Catch:{ all -> 0x003f }
            r1 = -2
            com.samsung.android.sum.core.message.Message r0 = r0.newMessage(r1)     // Catch:{ all -> 0x003f }
            r0.post()     // Catch:{ all -> 0x003f }
            goto L_0x008f
        L_0x006e:
            com.samsung.android.sum.core.Tag r4 = r8.getTag()     // Catch:{ all -> 0x003f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x003f }
            r5.<init>(r0)     // Catch:{ all -> 0x003f }
            r5.append(r1)     // Catch:{ all -> 0x003f }
            java.lang.String r0 = "remain="
            r5.append(r0)     // Catch:{ all -> 0x003f }
            com.samsung.android.sum.core.functional.CountingLatch r0 = r8.bufferInUseCounter     // Catch:{ all -> 0x003f }
            int r0 = r0.getCount()     // Catch:{ all -> 0x003f }
            r5.append(r0)     // Catch:{ all -> 0x003f }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x003f }
            com.samsung.android.sum.core.SLog.w((com.samsung.android.sum.core.Tag) r4, (java.lang.String) r0)     // Catch:{ all -> 0x003f }
        L_0x008f:
            com.samsung.android.sum.core.functional.CountingLatch r0 = r8.bufferInUseCounter     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x0098
            r0.reset()     // Catch:{ all -> 0x0050 }
            r8.bufferInUseCounter = r3     // Catch:{ all -> 0x0050 }
        L_0x0098:
            android.media.MediaCodec r0 = r8.mediaCodec     // Catch:{ all -> 0x0050 }
            goto L_0x0055
        L_0x009b:
            com.samsung.android.sum.core.functional.CountingLatch r1 = r8.bufferInUseCounter     // Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x00a4
            r1.reset()     // Catch:{ all -> 0x0050 }
            r8.bufferInUseCounter = r3     // Catch:{ all -> 0x0050 }
        L_0x00a4:
            android.media.MediaCodec r1 = r8.mediaCodec     // Catch:{ all -> 0x0050 }
            r1.release()     // Catch:{ all -> 0x0050 }
            r8.mediaCodec = r3     // Catch:{ all -> 0x0050 }
            throw r0     // Catch:{ all -> 0x0050 }
        L_0x00ac:
            r8.processedFrames = r2     // Catch:{ all -> 0x0050 }
            java.util.Map<java.lang.Integer, java.lang.Integer> r0 = r8.numFramesToProcess     // Catch:{ all -> 0x0050 }
            int r1 = r8.mediaId     // Catch:{ all -> 0x0050 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0050 }
            r0.remove(r1)     // Catch:{ all -> 0x0050 }
            java.util.Map<java.lang.Integer, java.lang.Long> r0 = r8.lastTimestampToProcess     // Catch:{ all -> 0x0050 }
            int r1 = r8.mediaId     // Catch:{ all -> 0x0050 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0050 }
            r0.remove(r1)     // Catch:{ all -> 0x0050 }
            r0 = -1
            r8.mediaId = r0     // Catch:{ all -> 0x0050 }
            com.samsung.android.sum.core.functional.CountingLatch r0 = r8.codecOnReadyCountingLatch     // Catch:{ all -> 0x0050 }
            r0.reset()     // Catch:{ all -> 0x0050 }
            com.samsung.android.sum.core.Tag r0 = r8.getTag()     // Catch:{ all -> 0x0050 }
            java.lang.String r1 = "release...X"
            com.samsung.android.sum.core.SLog.v((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0050 }
            monitor-exit(r8)
            return
        L_0x00d7:
            monitor-exit(r8)     // Catch:{ all -> 0x0050 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.filter.MediaCodecFilter.release():void");
    }

    public void resume() {
        this.cvPause.open();
    }

    public void setMessageProducer(MessageProducer messageProducer2) {
        this.messageProducer = messageProducer2;
    }

    public Stream<MediaFilter> stream() {
        return null;
    }
}
