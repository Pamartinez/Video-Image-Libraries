package com.samsung.android.sum.core.filter;

import A.a;
import N2.j;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.SemSystemProperties;
import android.util.Pair;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.Tag;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.descriptor.CodecDescriptor;
import com.samsung.android.sum.core.functional.CountingLatch;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.MediaType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EncoderFilter extends MediaCodecFilter {
    private static final String SEC_AAC_CODEC_ENCODER_NAME = "c2.sec.aac.encoder";
    private static final String TAG = SLog.tagOf(EncoderFilter.class);
    private static final int TIME_OUT_DEQUEUE_OUTPUT_US = 10000;
    private final boolean isCodecSpecificDataRequired;
    private boolean isOutputFormatSent;
    private long lastTimestampUs = 0;
    private int orientation = 0;
    private MediaBuffer unHandledInputBuffer;

    public EncoderFilter(CodecDescriptor codecDescriptor) {
        super(codecDescriptor);
        this.isCodecSpecificDataRequired = ((Boolean) Optional.of(SemSystemProperties.get("ro.product.model")).map(new b(5, codecDescriptor)).orElse(Boolean.FALSE)).booleanValue();
    }

    private Pair<Integer, Integer> getEncodingShape(int i2, int i7) {
        float f;
        CodecDescriptor codecDescriptor = (CodecDescriptor) getDescriptor();
        if (!(codecDescriptor.getScale() == 0.0f && codecDescriptor.getCropRectRatio() == 0.0f)) {
            if (codecDescriptor.getScale() != 0.0f) {
                f = codecDescriptor.getScale();
            } else {
                f = codecDescriptor.getCropRectRatio();
            }
            i2 = ((((int) (((float) i2) * f)) + 1) >> 1) << 1;
            i7 = ((((int) (((float) i7) * f)) + 1) >> 1) << 1;
        }
        if (codecDescriptor.getAlign() != 0) {
            int align = codecDescriptor.getAlign();
            int i8 = -align;
            i2 = ((i2 + align) - 1) & i8;
            i7 = ((i7 + align) - 1) & i8;
        }
        String str = TAG;
        StringBuilder h5 = a.h(i2, i7, "encoding-shape: w/h=", "/", ", scale=");
        h5.append(codecDescriptor.getScale());
        h5.append(", crop-rect-ration=");
        h5.append(codecDescriptor.getCropRectRatio());
        h5.append(", align=");
        h5.append(codecDescriptor.getAlign());
        SLog.i(str, h5.toString());
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i7));
    }

    private boolean isReachedLastFrame(int i2) {
        Tag tag = getTag();
        StringBuilder sb2 = new StringBuilder("isReachedLastFrame for ");
        j.x(sb2, this.mediaId, ": ", i2, "/");
        sb2.append(this.numFramesToProcess.get(Integer.valueOf(this.mediaId)));
        SLog.v(tag, sb2.toString());
        int intValue = this.numFramesToProcess.get(Integer.valueOf(this.mediaId)).intValue();
        if (intValue <= 0 || intValue > i2) {
            return false;
        }
        return true;
    }

    private boolean isReachedLastTimestamp(long j2) {
        Tag tag = getTag();
        SLog.v(tag, "isReachedLastTimestamp for " + this.mediaId + ": " + j2 + "/" + this.lastTimestampToProcess.get(Integer.valueOf(this.mediaId)));
        if (this.lastTimestampToProcess.get(Integer.valueOf(this.mediaId)).longValue() <= j2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$configCodec$1(Message message) {
        return "configCodec: " + message;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Pair lambda$configCodec$3(Message message) {
        return new Pair((Integer) message.get("width"), (Integer) message.get("height"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$configCodec$4(MediaBuffer mediaBuffer) {
        Integer num = (Integer) mediaBuffer.getExtra("media-id", Integer.valueOf(this.mediaId));
        int intValue = num.intValue();
        if (mediaBuffer.containFlags(8)) {
            Tag tag = getTag();
            SLog.i(tag, "update eos buffer: id=" + intValue + ", buf=" + mediaBuffer);
            this.eosBuffers.put(num, mediaBuffer);
            if (mediaBuffer.containsExtra(Message.KEY_WHOLE_FRAMES)) {
                Map<Integer, Integer> map = this.numFramesToProcess;
                Integer num2 = (Integer) mediaBuffer.getExtra(Message.KEY_WHOLE_FRAMES);
                num2.intValue();
                map.put(num, num2);
                Tag tag2 = getTag();
                StringBuilder o2 = C0086a.o(intValue, "update # of frames for ", ": ");
                o2.append(this.numFramesToProcess.get(num));
                SLog.i(tag2, o2.toString());
            }
            if (mediaBuffer.containsExtra(Message.KEY_END_TIME_US)) {
                this.lastTimestampToProcess.put(num, (Long) mediaBuffer.getExtra(Message.KEY_END_TIME_US));
                Tag tag3 = getTag();
                StringBuilder o3 = C0086a.o(intValue, "update lastTimestampUs for ", ": ");
                o3.append(this.lastTimestampToProcess.get(num));
                SLog.i(tag3, o3.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$new$0(CodecDescriptor codecDescriptor, String str) {
        boolean z = true;
        if (!Arrays.asList(new String[]{""}).contains(str.substring(str.indexOf("-") + 1).toLowerCase()) || !codecDescriptor.getMediaType().isVideo()) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$run$5() {
        return MediaBuffer.newMetaAlloc().setFlags(8).setExtra("media-id", Integer.valueOf(this.mediaId)).allocate();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$6(int i2) {
        Tag tag = getTag();
        SLog.v(tag, "release output buffer: " + i2);
        this.bufferInUseCounter.down();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$7(int i2, MediaCodec.BufferInfo bufferInfo) {
        CountingLatch countingLatch;
        CountingLatch countingLatch2;
        CountingLatch countingLatch3;
        Tag tag = getTag();
        StringBuilder o2 = C0086a.o(i2, "Release output buffer: index=", ", size=");
        o2.append(bufferInfo.size);
        o2.append(", flags=");
        o2.append(bufferInfo.flags);
        o2.append(", ts=");
        o2.append(bufferInfo.presentationTimeUs);
        o2.append(", offset=");
        o2.append(bufferInfo.offset);
        SLog.v(tag, o2.toString());
        try {
            this.mediaCodec.releaseOutputBuffer(i2, false);
            if (this.mediaCodec != null && (countingLatch3 = this.bufferInUseCounter) != null) {
                countingLatch3.down();
            }
        } catch (Exception e) {
            Tag tag2 = getTag();
            SLog.w(tag2, "fail to release buffer: " + e);
            if (this.mediaCodec != null && (countingLatch = this.bufferInUseCounter) != null) {
                countingLatch.down();
            }
        } catch (Throwable th) {
            if (!(this.mediaCodec == null || (countingLatch2 = this.bufferInUseCounter) == null)) {
                countingLatch2.down();
            }
            throw th;
        }
    }

    private void sendOutputFormat(MediaType mediaType) {
        Tag tag = getTag();
        SLog.d(tag, "contentsId=" + this.mediaId + ", track format = " + this.mediaCodec.getOutputFormat());
        HashMap hashMap = new HashMap();
        hashMap.put(Message.KEY_MEDIA_TYPE, mediaType);
        hashMap.put("media-id", Integer.valueOf(this.mediaId));
        MediaFormat outputFormat = this.mediaCodec.getOutputFormat();
        outputFormat.setInteger(Message.KEY_ROTATION, this.orientation);
        hashMap.put("media-format", outputFormat);
        Message newMessage = this.messageProducer.newMessage(203, (Map<String, Object>) hashMap);
        if (this.codecDescriptor.containsExtra(Message.KEY_EXTRA_CONFIG_ID)) {
            newMessage.put(Message.KEY_EXTRA_CONFIG_ID, this.codecDescriptor.getExtra(Message.KEY_EXTRA_CONFIG_ID));
        }
        newMessage.post();
        this.isOutputFormatSent = true;
        SLog.d(getTag(), "now ready to start encode");
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x042c A[Catch:{ IOException -> 0x0470 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0104 A[SYNTHETIC, Splitter:B:27:0x0104] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02aa A[Catch:{ IOException -> 0x0470 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02d9 A[Catch:{ IOException -> 0x0470 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0308 A[Catch:{ IOException -> 0x0470 }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0397 A[Catch:{ IOException -> 0x0470 }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x040c A[Catch:{ IOException -> 0x0470 }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0415 A[Catch:{ IOException -> 0x0470 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void configCodec(com.samsung.android.sum.core.message.Message r27) {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
            java.lang.String r2 = "rotation-degrees"
            java.lang.String r3 = "priority"
            java.lang.String r4 = "i-frame-interval"
            java.lang.String r5 = "color-format"
            java.lang.String r6 = "color-space"
            java.lang.String r7 = "notify-media-format"
            java.lang.String r8 = "encoding-height"
            java.lang.String r9 = "encoding-width"
            java.lang.String r10 = "level"
            java.lang.String r11 = "profile"
            java.lang.String r12 = "not supported type"
            java.lang.String r13 = "overwrite output shape to given encoding-width/height["
            com.samsung.android.sum.core.Tag r14 = r0.getTag()
            com.samsung.android.sum.core.filter.c r15 = new com.samsung.android.sum.core.filter.c
            r16 = r12
            r12 = 0
            r15.<init>(r1, r12)
            c4.h r12 = new c4.h
            r17 = r2
            r2 = 9
            r12.<init>(r2)
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r14, (java.util.function.Supplier<java.lang.String>) r15, (java.util.function.Consumer<java.lang.Exception>) r12)
            com.samsung.android.sum.core.functional.CountingLatch r2 = r0.codecOnReadyCountingLatch
            r12 = 0
            java.lang.Integer r14 = java.lang.Integer.valueOf(r12)
            int r2 = r2.await(r12)
            if (r2 != 0) goto L_0x0045
            r15 = 1
        L_0x0042:
            r18 = r3
            goto L_0x0047
        L_0x0045:
            r15 = r12
            goto L_0x0042
        L_0x0047:
            java.lang.String r3 = "count="
            java.lang.String r2 = c0.C0086a.i(r2, r3)
            java.lang.Object[] r3 = new java.lang.Object[r12]
            com.samsung.android.sum.core.Def.require(r15, r2, r3)
            int r2 = r0.mediaId
            r3 = -1
            if (r2 != r3) goto L_0x0059
            r2 = 1
            goto L_0x005a
        L_0x0059:
            r2 = r12
        L_0x005a:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r19 = r3
            java.lang.String r3 = "contentsId="
            r15.<init>(r3)
            int r3 = r0.mediaId
            r15.append(r3)
            java.lang.String r3 = r15.toString()
            java.lang.Object[] r15 = new java.lang.Object[r12]
            com.samsung.android.sum.core.Def.require(r2, r3, r15)
            java.lang.String r2 = "media-id"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r19)
            java.lang.Object r2 = r1.get(r2, r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r0.mediaId = r2
            com.samsung.android.sum.core.descriptor.MFDescriptor r2 = r0.getDescriptor()
            com.samsung.android.sum.core.descriptor.CodecDescriptor r2 = (com.samsung.android.sum.core.descriptor.CodecDescriptor) r2
            java.lang.String r3 = "mime"
            java.lang.Object r3 = r1.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r15 = r2.getMimeType()
            if (r15 == 0) goto L_0x00a7
            java.lang.String r15 = "n/a"
            java.lang.String r12 = r2.getMimeType()
            boolean r12 = r15.equals(r12)
            if (r12 != 0) goto L_0x00a7
            java.lang.String r3 = r2.getMimeType()
        L_0x00a7:
            int r12 = r2.getBitrate()
            java.lang.String r15 = "bitrate"
            if (r12 != 0) goto L_0x00f3
            java.lang.Object r12 = r1.get(r15)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            float r20 = r2.getScale()
            r21 = 0
            int r20 = (r20 > r21 ? 1 : (r20 == r21 ? 0 : -1))
            if (r20 == 0) goto L_0x00e8
            r20 = r12
            float r12 = r2.getScale()
            r21 = r14
            r22 = r15
            double r14 = (double) r12
            r23 = r4
            r24 = r5
            r4 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r4 = java.lang.Math.pow(r14, r4)
            double r4 = java.lang.Math.log10(r4)
            int r4 = (int) r4
            double r4 = (double) r4
            r14 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r4 = java.lang.Math.pow(r14, r4)
            int r4 = (int) r4
            int r12 = r20 * r4
            goto L_0x00f8
        L_0x00e8:
            r23 = r4
            r24 = r5
            r20 = r12
        L_0x00ee:
            r21 = r14
            r22 = r15
            goto L_0x00f8
        L_0x00f3:
            r23 = r4
            r24 = r5
            goto L_0x00ee
        L_0x00f8:
            com.samsung.android.sum.core.types.MediaType r4 = r2.getMediaType()
            boolean r5 = r4.isVideo()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r14 = "aac-profile"
            if (r5 == 0) goto L_0x0397
            android.util.Pair r5 = r2.getRectSize()     // Catch:{ IOException -> 0x0470 }
            java.util.Optional r5 = java.util.Optional.ofNullable(r5)     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.filter.c r15 = new com.samsung.android.sum.core.filter.c     // Catch:{ IOException -> 0x0470 }
            r20 = r4
            r4 = 1
            r15.<init>(r1, r4)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r4 = r5.orElseGet(r15)     // Catch:{ IOException -> 0x0470 }
            android.util.Pair r4 = (android.util.Pair) r4     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r5 = r4.first     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IOException -> 0x0470 }
            int r5 = r5.intValue()     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r4 = r4.second     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r15 = "height"
            r16 = r4
            java.lang.String r4 = "width"
            if (r5 != 0) goto L_0x0154
            if (r16 != 0) goto L_0x0154
            boolean r25 = r1.contains(r4)     // Catch:{ IOException -> 0x0470 }
            if (r25 == 0) goto L_0x0144
            java.lang.Object r5 = r1.get(r4)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IOException -> 0x0470 }
            int r5 = r5.intValue()     // Catch:{ IOException -> 0x0470 }
        L_0x0144:
            boolean r25 = r1.contains(r15)     // Catch:{ IOException -> 0x0470 }
            if (r25 == 0) goto L_0x0154
            java.lang.Object r16 = r1.get(r15)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r16 = (java.lang.Integer) r16     // Catch:{ IOException -> 0x0470 }
            int r16 = r16.intValue()     // Catch:{ IOException -> 0x0470 }
        L_0x0154:
            r25 = r12
            r12 = r5
            r5 = r16
            android.util.Pair r5 = r0.getEncodingShape(r12, r5)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r12 = r5.first     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r12 = (java.lang.Integer) r12     // Catch:{ IOException -> 0x0470 }
            int r12 = r12.intValue()     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r5 = r5.second     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IOException -> 0x0470 }
            int r5 = r5.intValue()     // Catch:{ IOException -> 0x0470 }
            r16 = r5
            java.lang.String[] r5 = new java.lang.String[]{r9, r8}     // Catch:{ IOException -> 0x0470 }
            boolean r5 = r1.containsAll(r5)     // Catch:{ IOException -> 0x0470 }
            if (r5 == 0) goto L_0x01ac
            java.lang.Object r5 = r1.get(r9)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IOException -> 0x0470 }
            int r12 = r5.intValue()     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r5 = r1.get(r8)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IOException -> 0x0470 }
            int r5 = r5.intValue()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r8 = TAG     // Catch:{ IOException -> 0x0470 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0470 }
            r9.<init>(r13)     // Catch:{ IOException -> 0x0470 }
            r9.append(r12)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r13 = ", "
            r9.append(r13)     // Catch:{ IOException -> 0x0470 }
            r9.append(r5)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r13 = "]"
            r9.append(r13)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r8, (java.lang.String) r9)     // Catch:{ IOException -> 0x0470 }
            goto L_0x01ae
        L_0x01ac:
            r5 = r16
        L_0x01ae:
            boolean r8 = r2.containsExtra(r7)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r9 = "frame-rate"
            if (r8 == 0) goto L_0x021b
            com.samsung.android.sum.core.message.MessageProducer r8 = r0.messageProducer     // Catch:{ IOException -> 0x0470 }
            r13 = 3
            com.samsung.android.sum.core.message.Message r8 = r8.newMessage(r13)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r2 = r2.getExtra(r7)     // Catch:{ IOException -> 0x0470 }
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ IOException -> 0x0470 }
            int r7 = r2.length     // Catch:{ IOException -> 0x0470 }
            r13 = 0
        L_0x01c5:
            if (r13 >= r7) goto L_0x0218
            r16 = r2
            r2 = r16[r13]     // Catch:{ IOException -> 0x0470 }
            boolean r19 = r4.equals(r2)     // Catch:{ IOException -> 0x0470 }
            if (r19 == 0) goto L_0x01db
            java.lang.Integer r2 = java.lang.Integer.valueOf(r12)     // Catch:{ IOException -> 0x0470 }
            r8.put(r4, r2)     // Catch:{ IOException -> 0x0470 }
        L_0x01d8:
            r19 = r4
            goto L_0x01fa
        L_0x01db:
            boolean r19 = r15.equals(r2)     // Catch:{ IOException -> 0x0470 }
            if (r19 == 0) goto L_0x01e9
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x0470 }
            r8.put(r15, r2)     // Catch:{ IOException -> 0x0470 }
            goto L_0x01d8
        L_0x01e9:
            boolean r19 = r9.equals(r2)     // Catch:{ IOException -> 0x0470 }
            if (r19 == 0) goto L_0x0201
            r19 = r4
            java.lang.String r4 = "fps"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ IOException -> 0x0470 }
            r8.put(r4, r2)     // Catch:{ IOException -> 0x0470 }
        L_0x01fa:
            int r13 = r13 + 1
            r2 = r16
            r4 = r19
            goto L_0x01c5
        L_0x0201:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ IOException -> 0x0470 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0470 }
            r1.<init>()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r3 = "unsupported: "
            r1.append(r3)     // Catch:{ IOException -> 0x0470 }
            r1.append(r2)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0470 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0470 }
            throw r0     // Catch:{ IOException -> 0x0470 }
        L_0x0218:
            r8.post()     // Catch:{ IOException -> 0x0470 }
        L_0x021b:
            android.media.MediaFormat r2 = android.media.MediaFormat.createVideoFormat(r3, r12, r5)     // Catch:{ IOException -> 0x0470 }
            boolean r4 = r1.contains(r11)     // Catch:{ IOException -> 0x0470 }
            if (r4 == 0) goto L_0x0232
            java.lang.Object r4 = r1.get(r11)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r11, r4)     // Catch:{ IOException -> 0x0470 }
        L_0x0232:
            boolean r4 = r1.contains(r10)     // Catch:{ IOException -> 0x0470 }
            if (r4 == 0) goto L_0x0245
            java.lang.Object r4 = r1.get(r10)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r10, r4)     // Catch:{ IOException -> 0x0470 }
        L_0x0245:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ IOException -> 0x0470 }
            r5 = 33
            java.lang.String r7 = "color-range"
            java.lang.String r8 = "color-transfer"
            java.lang.String r10 = "color-standard"
            if (r4 < r5) goto L_0x02a2
            boolean r4 = r1.contains(r6)     // Catch:{ IOException -> 0x0470 }
            if (r4 == 0) goto L_0x02a2
            java.lang.Object r4 = r1.get(r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            int r5 = android.hardware.DataSpace.getStandard(r4)     // Catch:{ IOException -> 0x0470 }
            int r5 = com.samsung.android.sum.core.ColorUtils.getMediaFormatColorStandard(r5)     // Catch:{ IOException -> 0x0470 }
            int r6 = android.hardware.DataSpace.getTransfer(r4)     // Catch:{ IOException -> 0x0470 }
            int r6 = com.samsung.android.sum.core.ColorUtils.getMediaFormatColorTransfer(r6)     // Catch:{ IOException -> 0x0470 }
            int r4 = android.hardware.DataSpace.getRange(r4)     // Catch:{ IOException -> 0x0470 }
            int r4 = com.samsung.android.sum.core.ColorUtils.getMediaFormatColorRange(r4)     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.Tag r11 = r0.getTag()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r12 = "found color-standard: %d, color-transfer: %d, color-range: %d"
            java.lang.Integer r13 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x0470 }
            r19 = r14
            java.lang.Integer r14 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object[] r13 = new java.lang.Object[]{r13, r15, r14}     // Catch:{ IOException -> 0x0470 }
            java.lang.String r12 = com.samsung.android.sum.core.Def.fmtstr(r12, r13)     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r11, (java.lang.String) r12)     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r10, r5)     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r8, r6)     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r7, r4)     // Catch:{ IOException -> 0x0470 }
            goto L_0x02a4
        L_0x02a2:
            r19 = r14
        L_0x02a4:
            boolean r4 = r1.contains(r10)     // Catch:{ IOException -> 0x0470 }
            if (r4 == 0) goto L_0x02d3
            com.samsung.android.sum.core.Tag r4 = r0.getTag()     // Catch:{ IOException -> 0x0470 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0470 }
            r5.<init>()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r6 = "found color-standard: "
            r5.append(r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r6 = r1.get(r10)     // Catch:{ IOException -> 0x0470 }
            r5.append(r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r4, (java.lang.String) r5)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r4 = r1.get(r10)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r10, r4)     // Catch:{ IOException -> 0x0470 }
        L_0x02d3:
            boolean r4 = r1.contains(r8)     // Catch:{ IOException -> 0x0470 }
            if (r4 == 0) goto L_0x0302
            com.samsung.android.sum.core.Tag r4 = r0.getTag()     // Catch:{ IOException -> 0x0470 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0470 }
            r5.<init>()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r6 = "found color-transfer: "
            r5.append(r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r6 = r1.get(r8)     // Catch:{ IOException -> 0x0470 }
            r5.append(r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r4, (java.lang.String) r5)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r4 = r1.get(r8)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r8, r4)     // Catch:{ IOException -> 0x0470 }
        L_0x0302:
            boolean r4 = r1.contains(r7)     // Catch:{ IOException -> 0x0470 }
            if (r4 == 0) goto L_0x0331
            com.samsung.android.sum.core.Tag r4 = r0.getTag()     // Catch:{ IOException -> 0x0470 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0470 }
            r5.<init>()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r6 = "found color-range: "
            r5.append(r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r6 = r1.get(r7)     // Catch:{ IOException -> 0x0470 }
            r5.append(r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r4, (java.lang.String) r5)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r4 = r1.get(r7)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r7, r4)     // Catch:{ IOException -> 0x0470 }
        L_0x0331:
            r4 = 2135033992(0x7f420888, float:2.5791453E38)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x0470 }
            r5 = r24
            java.lang.Object r4 = r1.get(r5, r4)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r5, r4)     // Catch:{ IOException -> 0x0470 }
            java.lang.Object r4 = r1.get(r9)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r9, r4)     // Catch:{ IOException -> 0x0470 }
            r4 = r23
            java.lang.Object r5 = r1.get(r4)     // Catch:{ IOException -> 0x0470 }
            java.lang.Float r5 = (java.lang.Float) r5     // Catch:{ IOException -> 0x0470 }
            float r5 = r5.floatValue()     // Catch:{ IOException -> 0x0470 }
            r2.setFloat(r4, r5)     // Catch:{ IOException -> 0x0470 }
            r4 = r18
            r5 = r21
            java.lang.Object r6 = r1.get(r4, r5)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ IOException -> 0x0470 }
            int r6 = r6.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r4, r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r4 = "vendor.qti-ext-enc-content-adaptive-mode.value"
            r6 = 1
            r2.setInteger(r4, r6)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r4 = "vendor.qti-ext-enc-linear-color-format.value"
            r2.setInteger(r4, r6)     // Catch:{ IOException -> 0x0470 }
            r4 = r17
            java.lang.Object r5 = r1.get(r4, r5)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IOException -> 0x0470 }
            int r5 = r5.intValue()     // Catch:{ IOException -> 0x0470 }
            r0.orientation = r5     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r4, r5)     // Catch:{ IOException -> 0x0470 }
            r4 = r19
        L_0x0392:
            r5 = r22
            r12 = r25
            goto L_0x03d5
        L_0x0397:
            r20 = r4
            r25 = r12
            r19 = r14
            boolean r2 = r20.isAudio()     // Catch:{ IOException -> 0x0470 }
            if (r2 == 0) goto L_0x045a
            java.lang.String r2 = "sample-rate"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException -> 0x0470 }
            int r2 = r2.intValue()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r4 = "channel-count"
            java.lang.Object r4 = r1.get(r4)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x0470 }
            int r4 = r4.intValue()     // Catch:{ IOException -> 0x0470 }
            android.media.MediaFormat r2 = android.media.MediaFormat.createAudioFormat(r3, r2, r4)     // Catch:{ IOException -> 0x0470 }
            r4 = r19
            boolean r5 = r1.contains(r4)     // Catch:{ IOException -> 0x0470 }
            if (r5 == 0) goto L_0x0392
            java.lang.Object r5 = r1.get(r4)     // Catch:{ IOException -> 0x0470 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IOException -> 0x0470 }
            int r5 = r5.intValue()     // Catch:{ IOException -> 0x0470 }
            r2.setInteger(r4, r5)     // Catch:{ IOException -> 0x0470 }
            goto L_0x0392
        L_0x03d5:
            r2.setInteger(r5, r12)     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.Tag r5 = r0.getTag()     // Catch:{ IOException -> 0x0470 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0470 }
            r6.<init>()     // Catch:{ IOException -> 0x0470 }
            java.lang.String r7 = "(id: #"
            r6.append(r7)     // Catch:{ IOException -> 0x0470 }
            int r7 = r0.mediaId     // Catch:{ IOException -> 0x0470 }
            r6.append(r7)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r7 = ") media-format="
            r6.append(r7)     // Catch:{ IOException -> 0x0470 }
            r6.append(r2)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r5, (java.lang.String) r6)     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.functional.CountingLatch r5 = com.samsung.android.sum.core.functional.CountingLatch.downOf()     // Catch:{ IOException -> 0x0470 }
            r0.bufferInUseCounter = r5     // Catch:{ IOException -> 0x0470 }
            boolean r5 = r20.isAudio()     // Catch:{ IOException -> 0x0470 }
            if (r5 == 0) goto L_0x0415
            boolean r1 = r1.contains(r4)     // Catch:{ IOException -> 0x0470 }
            if (r1 == 0) goto L_0x0415
            java.lang.String r1 = "c2.sec.aac.encoder"
            android.media.MediaCodec r1 = android.media.MediaCodec.createByCodecName(r1)     // Catch:{ IOException -> 0x0470 }
            r0.mediaCodec = r1     // Catch:{ IOException -> 0x0470 }
            goto L_0x041b
        L_0x0415:
            android.media.MediaCodec r1 = android.media.MediaCodec.createEncoderByType(r3)     // Catch:{ IOException -> 0x0470 }
            r0.mediaCodec = r1     // Catch:{ IOException -> 0x0470 }
        L_0x041b:
            android.media.MediaCodec r1 = r0.mediaCodec     // Catch:{ IOException -> 0x0470 }
            r3 = 0
            r6 = 1
            r1.configure(r2, r3, r3, r6)     // Catch:{ IOException -> 0x0470 }
            r1 = r20
            com.samsung.android.sum.core.channel.BufferChannel r1 = r0.getInputChannel(r1)     // Catch:{ IOException -> 0x0470 }
            boolean r2 = r1 instanceof com.samsung.android.sum.core.channel.SurfaceWriteChannel     // Catch:{ IOException -> 0x0470 }
            if (r2 == 0) goto L_0x0454
            com.samsung.android.sum.core.channel.SurfaceWriteChannel r1 = (com.samsung.android.sum.core.channel.SurfaceWriteChannel) r1     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.channel.SurfaceChannelConfig$Builder r2 = new com.samsung.android.sum.core.channel.SurfaceChannelConfig$Builder     // Catch:{ IOException -> 0x0470 }
            r2.<init>()     // Catch:{ IOException -> 0x0470 }
            android.media.MediaCodec r3 = r0.mediaCodec     // Catch:{ IOException -> 0x0470 }
            android.view.Surface r3 = r3.createInputSurface()     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.channel.SurfaceChannelConfig$Builder r2 = r2.setSurface(r3)     // Catch:{ IOException -> 0x0470 }
            r3 = 5000(0x1388, double:2.4703E-320)
            com.samsung.android.sum.core.channel.SurfaceChannelConfig$Builder r2 = r2.setTimeoutInMillis(r3)     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.descriptor.b r3 = new com.samsung.android.sum.core.descriptor.b     // Catch:{ IOException -> 0x0470 }
            r4 = 2
            r3.<init>(r4, r0)     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.channel.SurfaceChannelConfig$Builder r2 = r2.setMetaDataHandler(r3)     // Catch:{ IOException -> 0x0470 }
            com.samsung.android.sum.core.channel.SurfaceChannelConfig r2 = r2.build()     // Catch:{ IOException -> 0x0470 }
            r1.configure(r2)     // Catch:{ IOException -> 0x0470 }
        L_0x0454:
            android.media.MediaCodec r0 = r0.mediaCodec     // Catch:{ IOException -> 0x0470 }
            r0.start()     // Catch:{ IOException -> 0x0470 }
            return
        L_0x045a:
            r1 = r20
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ IOException -> 0x0470 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0470 }
            r3 = r16
            r2.<init>(r3)     // Catch:{ IOException -> 0x0470 }
            r2.append(r1)     // Catch:{ IOException -> 0x0470 }
            java.lang.String r1 = r2.toString()     // Catch:{ IOException -> 0x0470 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0470 }
            throw r0     // Catch:{ IOException -> 0x0470 }
        L_0x0470:
            r0 = move-exception
            r0.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.filter.EncoderFilter.configCodec(com.samsung.android.sum.core.message.Message):void");
    }

    public boolean onMessageReceived(Message message) {
        if (message.getCode() != 712) {
            return super.onMessageReceived(message);
        }
        int intValue = ((Integer) message.get("width", 0)).intValue();
        int intValue2 = ((Integer) message.get("height", 0)).intValue();
        if (intValue <= 0 || intValue2 <= 0) {
            return true;
        }
        message.reply(new HashMap<String, Object>(getEncodingShape(intValue, intValue2)) {
            final /* synthetic */ Pair val$encodingShape;

            {
                this.val$encodingShape = r3;
                put("encoding-width", r3.first);
                put("encoding-height", r3.second);
            }
        });
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v34, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r25v0 */
    /* JADX WARNING: type inference failed for: r25v1 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r25v3 */
    /* JADX WARNING: type inference failed for: r25v4 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r2v2, types: [boolean, int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x044b  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x045a  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.sum.core.buffer.MutableMediaBuffer run(com.samsung.android.sum.core.buffer.MediaBuffer r27, com.samsung.android.sum.core.buffer.MutableMediaBuffer r28) {
        /*
            r26 = this;
            r1 = r26
            com.samsung.android.sum.core.Tag r0 = r1.getTag()
            java.lang.String r2 = "run"
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r0, (java.lang.String) r2)
            com.samsung.android.sum.core.functional.CountingLatch r0 = r1.codecOnReadyCountingLatch
            r2 = 1
            r0.await(r2)
            android.media.MediaCodec r0 = r1.mediaCodec
            if (r0 == 0) goto L_0x0463
            com.samsung.android.sum.core.descriptor.MFDescriptor r0 = r1.getDescriptor()
            r3 = r0
            com.samsung.android.sum.core.descriptor.CodecDescriptor r3 = (com.samsung.android.sum.core.descriptor.CodecDescriptor) r3
            com.samsung.android.sum.core.types.MediaType r0 = r3.getMediaType()
            com.samsung.android.sum.core.channel.BufferChannel r4 = r1.getInputChannel(r0)
            com.samsung.android.sum.core.channel.BufferChannel r5 = r1.getOutputChannel(r0)
            java.util.concurrent.atomic.AtomicInteger r6 = new java.util.concurrent.atomic.AtomicInteger
            r6.<init>()
            boolean r7 = r4 instanceof com.samsung.android.sum.core.channel.SurfaceWriteChannel
            r1.isReachedInputEos = r7
            r8 = 0
            r1.isReachedOutputEos = r8
            r1.isOutputFormatSent = r8
            r1.processedFrames = r8
            android.media.MediaCodec r9 = r1.mediaCodec
            android.media.MediaCodecInfo r9 = r9.getCodecInfo()
            java.lang.String r9 = r9.getCanonicalName()
            r1.addTag(r0, r9)
            r9 = r8
        L_0x0046:
            r10 = -1
            r11 = 0
            boolean r13 = r1.isReachedInputEos     // Catch:{ all -> 0x006d }
            if (r13 == 0) goto L_0x0073
            boolean r13 = r1.isReachedOutputEos     // Catch:{ all -> 0x006d }
            if (r13 != 0) goto L_0x0052
            goto L_0x0073
        L_0x0052:
            boolean r0 = r3.isRunInstant()
            if (r0 == 0) goto L_0x0065
            if (r7 == 0) goto L_0x005f
            com.samsung.android.sum.core.channel.SurfaceWriteChannel r4 = (com.samsung.android.sum.core.channel.SurfaceWriteChannel) r4
            r4.reset()
        L_0x005f:
            r1.lastTimestampUs = r11
            r1.release()
            return r28
        L_0x0065:
            r1.mediaId = r10
            com.samsung.android.sum.core.functional.CountingLatch r0 = r1.codecOnReadyCountingLatch
            r0.reset()
            return r28
        L_0x006d:
            r0 = move-exception
            r13 = r7
            r16 = r11
            goto L_0x0445
        L_0x0073:
            android.os.ConditionVariable r13 = r1.cvPause     // Catch:{ all -> 0x006d }
            r13.block()     // Catch:{ all -> 0x006d }
            boolean r13 = r1.isReachedInputEos     // Catch:{ all -> 0x006d }
            r14 = 10000(0x2710, double:4.9407E-320)
            if (r13 != 0) goto L_0x01b2
            if (r9 == 0) goto L_0x01b2
            java.util.Set<java.lang.Integer> r13 = r1.flushRequestedContents     // Catch:{ all -> 0x01ad }
            r16 = r11
            int r11 = r1.mediaId     // Catch:{ all -> 0x00d0 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00d0 }
            boolean r11 = r13.contains(r11)     // Catch:{ all -> 0x00d0 }
            r12 = 8
            if (r11 == 0) goto L_0x00d8
            com.samsung.android.sum.core.Tag r11 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d0 }
            r13.<init>()     // Catch:{ all -> 0x00d0 }
            java.lang.String r14 = "flush requested for "
            r13.append(r14)     // Catch:{ all -> 0x00d0 }
            int r14 = r1.mediaId     // Catch:{ all -> 0x00d0 }
            r13.append(r14)     // Catch:{ all -> 0x00d0 }
            java.lang.String r14 = " skip until eos"
            r13.append(r14)     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.SLog.i((com.samsung.android.sum.core.Tag) r11, (java.lang.String) r13)     // Catch:{ all -> 0x00d0 }
        L_0x00b1:
            java.lang.Object r11 = r4.receive()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBuffer r11 = (com.samsung.android.sum.core.buffer.MediaBuffer) r11     // Catch:{ all -> 0x00d0 }
            int[] r13 = new int[]{r12}     // Catch:{ all -> 0x00d0 }
            boolean r13 = r11.containFlags(r13)     // Catch:{ all -> 0x00d0 }
            if (r13 == 0) goto L_0x00d4
            r1.unHandledInputBuffer = r11     // Catch:{ all -> 0x00d0 }
            java.util.Set<java.lang.Integer> r11 = r1.flushRequestedContents     // Catch:{ all -> 0x00d0 }
            int r12 = r1.mediaId     // Catch:{ all -> 0x00d0 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x00d0 }
            r11.remove(r12)     // Catch:{ all -> 0x00d0 }
            goto L_0x0046
        L_0x00d0:
            r0 = move-exception
        L_0x00d1:
            r13 = r7
            goto L_0x0445
        L_0x00d4:
            r11.release()     // Catch:{ all -> 0x00d0 }
            goto L_0x00b1
        L_0x00d8:
            android.media.MediaCodec r11 = r1.mediaCodec     // Catch:{ all -> 0x00d0 }
            int r11 = r11.dequeueInputBuffer(r14)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.Tag r13 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            r27 = r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d0 }
            r12.<init>()     // Catch:{ all -> 0x00d0 }
            r25 = r8
            java.lang.String r8 = "dequeue input buffer: "
            r12.append(r8)     // Catch:{ all -> 0x00d0 }
            r12.append(r11)     // Catch:{ all -> 0x00d0 }
            java.lang.String r8 = r12.toString()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.SLog.v((com.samsung.android.sum.core.Tag) r13, (java.lang.String) r8)     // Catch:{ all -> 0x00d0 }
            if (r11 < 0) goto L_0x01b6
            com.samsung.android.sum.core.buffer.MediaBuffer r8 = r1.unHandledInputBuffer     // Catch:{ all -> 0x00d0 }
            if (r8 == 0) goto L_0x010f
            com.samsung.android.sum.core.Tag r8 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.String r12 = "use unHandled input buffer"
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r8, (java.lang.String) r12)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBuffer r8 = r1.unHandledInputBuffer     // Catch:{ all -> 0x00d0 }
            r12 = 0
            r1.unHandledInputBuffer = r12     // Catch:{ all -> 0x00d0 }
            goto L_0x0115
        L_0x010f:
            java.lang.Object r8 = r4.receive()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBuffer r8 = (com.samsung.android.sum.core.buffer.MediaBuffer) r8     // Catch:{ all -> 0x00d0 }
        L_0x0115:
            com.samsung.android.sum.core.Tag r12 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d0 }
            r13.<init>()     // Catch:{ all -> 0x00d0 }
            java.lang.String r10 = "buffer="
            r13.append(r10)     // Catch:{ all -> 0x00d0 }
            r13.append(r8)     // Catch:{ all -> 0x00d0 }
            java.lang.String r10 = r13.toString()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.SLog.v((com.samsung.android.sum.core.Tag) r12, (java.lang.String) r10)     // Catch:{ all -> 0x00d0 }
            int[] r10 = new int[]{r27}     // Catch:{ all -> 0x00d0 }
            boolean r10 = r8.containFlags(r10)     // Catch:{ all -> 0x00d0 }
            if (r10 == 0) goto L_0x0154
            com.samsung.android.sum.core.Tag r10 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.String r12 = "reached input EOS"
            com.samsung.android.sum.core.SLog.i((com.samsung.android.sum.core.Tag) r10, (java.lang.String) r12)     // Catch:{ all -> 0x00d0 }
            android.media.MediaCodec r10 = r1.mediaCodec     // Catch:{ all -> 0x00d0 }
            r22 = 0
            r24 = 4
            r20 = 0
            r21 = 0
            r18 = r10
            r19 = r11
            r18.queueInputBuffer(r19, r20, r21, r22, r24)     // Catch:{ all -> 0x00d0 }
            r1.isReachedInputEos = r2     // Catch:{ all -> 0x00d0 }
            goto L_0x019a
        L_0x0154:
            r10 = r11
            boolean r11 = r4.isClosedForReceive()     // Catch:{ all -> 0x00d0 }
            if (r11 != 0) goto L_0x01a5
            com.samsung.android.sum.core.functional.CountingLatch r11 = r1.bufferInUseCounter     // Catch:{ all -> 0x019e }
            r11.up()     // Catch:{ all -> 0x019e }
            android.media.MediaCodec r11 = r1.mediaCodec     // Catch:{ all -> 0x019e }
            java.nio.ByteBuffer r11 = r11.getInputBuffer(r10)     // Catch:{ all -> 0x019e }
            java.lang.Class<java.nio.ByteBuffer> r12 = java.nio.ByteBuffer.class
            java.lang.Object r12 = r8.getTypedData(r12)     // Catch:{ all -> 0x019e }
            java.nio.ByteBuffer r12 = (java.nio.ByteBuffer) r12     // Catch:{ all -> 0x019e }
            r12.rewind()     // Catch:{ all -> 0x019e }
            r11.put(r12)     // Catch:{ all -> 0x019e }
            java.lang.String r12 = "timestampUs"
            java.lang.Long r13 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x019e }
            java.lang.Object r12 = r8.getExtra(r12, r13)     // Catch:{ all -> 0x019e }
            java.lang.Long r12 = (java.lang.Long) r12     // Catch:{ all -> 0x019e }
            long r22 = r12.longValue()     // Catch:{ all -> 0x019e }
            android.media.MediaCodec r12 = r1.mediaCodec     // Catch:{ all -> 0x019e }
            int r21 = r11.limit()     // Catch:{ all -> 0x019e }
            r24 = 0
            r20 = 0
            r19 = r10
            r18 = r12
            r18.queueInputBuffer(r19, r20, r21, r22, r24)     // Catch:{ all -> 0x019e }
            com.samsung.android.sum.core.functional.CountingLatch r10 = r1.bufferInUseCounter     // Catch:{ all -> 0x00d0 }
            r10.down()     // Catch:{ all -> 0x00d0 }
        L_0x019a:
            r8.release()     // Catch:{ all -> 0x00d0 }
            goto L_0x01b6
        L_0x019e:
            r0 = move-exception
            com.samsung.android.sum.core.functional.CountingLatch r2 = r1.bufferInUseCounter     // Catch:{ all -> 0x00d0 }
            r2.down()     // Catch:{ all -> 0x00d0 }
            throw r0     // Catch:{ all -> 0x00d0 }
        L_0x01a5:
            java.util.concurrent.CancellationException r0 = new java.util.concurrent.CancellationException     // Catch:{ all -> 0x00d0 }
            java.lang.String r2 = "input channel is already closed"
            r0.<init>(r2)     // Catch:{ all -> 0x00d0 }
            throw r0     // Catch:{ all -> 0x00d0 }
        L_0x01ad:
            r0 = move-exception
            r16 = r11
            goto L_0x00d1
        L_0x01b2:
            r25 = r8
            r16 = r11
        L_0x01b6:
            android.media.MediaCodec$BufferInfo r8 = new android.media.MediaCodec$BufferInfo     // Catch:{ all -> 0x00d0 }
            r8.<init>()     // Catch:{ all -> 0x00d0 }
            android.media.MediaCodec r10 = r1.mediaCodec     // Catch:{ all -> 0x00d0 }
            int r10 = r10.dequeueOutputBuffer(r8, r14)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.Tag r11 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d0 }
            r12.<init>()     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = "Dequeue output buffer: index="
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            r12.append(r10)     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = ", size="
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            int r13 = r8.size     // Catch:{ all -> 0x00d0 }
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = ", flags="
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            int r13 = r8.flags     // Catch:{ all -> 0x00d0 }
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = ", timestampUs="
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            long r13 = r8.presentationTimeUs     // Catch:{ all -> 0x00d0 }
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = ", offset="
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            int r13 = r8.offset     // Catch:{ all -> 0x00d0 }
            r12.append(r13)     // Catch:{ all -> 0x00d0 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.SLog.v((com.samsung.android.sum.core.Tag) r11, (java.lang.String) r12)     // Catch:{ all -> 0x00d0 }
            java.lang.String r11 = "buffer-info"
            java.lang.String r12 = "encoder reached eos: "
            r14 = -1
            if (r10 != r14) goto L_0x02ac
            boolean r14 = r4 instanceof com.samsung.android.sum.core.channel.SurfaceWriteChannel     // Catch:{ all -> 0x00d0 }
            java.lang.String r15 = "retry dequeue output buffer"
            if (r14 == 0) goto L_0x02a3
            int r14 = r1.processedFrames     // Catch:{ all -> 0x00d0 }
            boolean r14 = r1.isReachedLastFrame(r14)     // Catch:{ all -> 0x00d0 }
            if (r14 != 0) goto L_0x022d
            r27 = 4
            long r13 = r1.lastTimestampUs     // Catch:{ all -> 0x00d0 }
            boolean r13 = r1.isReachedLastTimestamp(r13)     // Catch:{ all -> 0x00d0 }
            if (r13 == 0) goto L_0x0221
            goto L_0x022f
        L_0x0221:
            com.samsung.android.sum.core.Tag r8 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.SLog.v((com.samsung.android.sum.core.Tag) r8, (java.lang.String) r15)     // Catch:{ all -> 0x00d0 }
        L_0x0228:
            r15 = r6
            r13 = r7
            r6 = r2
            goto L_0x043f
        L_0x022d:
            r27 = 4
        L_0x022f:
            com.samsung.android.sum.core.Tag r13 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d0 }
            r14.<init>()     // Catch:{ all -> 0x00d0 }
            r14.append(r12)     // Catch:{ all -> 0x00d0 }
            int r12 = r1.mediaId     // Catch:{ all -> 0x00d0 }
            r14.append(r12)     // Catch:{ all -> 0x00d0 }
            java.lang.String r12 = r14.toString()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.SLog.i((com.samsung.android.sum.core.Tag) r13, (java.lang.String) r12)     // Catch:{ all -> 0x00d0 }
            r1.isReachedOutputEos = r2     // Catch:{ all -> 0x00d0 }
            boolean r12 = r1.isOutputFormatSent     // Catch:{ all -> 0x00d0 }
            if (r12 != 0) goto L_0x0259
            com.samsung.android.sum.core.Tag r12 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = "send output format forced"
            com.samsung.android.sum.core.SLog.w((com.samsung.android.sum.core.Tag) r12, (java.lang.String) r13)     // Catch:{ all -> 0x00d0 }
            r1.sendOutputFormat(r0)     // Catch:{ all -> 0x00d0 }
        L_0x0259:
            int r12 = r8.flags     // Catch:{ all -> 0x00d0 }
            r12 = r12 | 4
            r8.flags = r12     // Catch:{ all -> 0x00d0 }
            java.util.Map<java.lang.Integer, com.samsung.android.sum.core.buffer.MediaBuffer> r12 = r1.eosBuffers     // Catch:{ all -> 0x00d0 }
            int r13 = r1.mediaId     // Catch:{ all -> 0x00d0 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x00d0 }
            java.lang.Object r12 = r12.remove(r13)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBuffer r12 = (com.samsung.android.sum.core.buffer.MediaBuffer) r12     // Catch:{ all -> 0x00d0 }
            java.util.Optional r12 = java.util.Optional.ofNullable(r12)     // Catch:{ all -> 0x00d0 }
            J5.c r13 = new J5.c     // Catch:{ all -> 0x00d0 }
            r14 = 19
            r13.<init>(r14, r1)     // Catch:{ all -> 0x00d0 }
            java.lang.Object r12 = r12.orElseGet(r13)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBuffer r12 = (com.samsung.android.sum.core.buffer.MediaBuffer) r12     // Catch:{ all -> 0x00d0 }
            int r13 = r1.processedFrames     // Catch:{ all -> 0x00d0 }
            if (r13 != 0) goto L_0x0289
            int[] r13 = new int[]{r27}     // Catch:{ all -> 0x00d0 }
            r12.setFlags(r13)     // Catch:{ all -> 0x00d0 }
        L_0x0289:
            r12.setExtra(r11, r8)     // Catch:{ all -> 0x00d0 }
            A4.c r8 = new A4.c     // Catch:{ all -> 0x00d0 }
            r11 = 25
            r8.<init>(r1, r10, r11)     // Catch:{ all -> 0x00d0 }
            java.lang.Runnable[] r10 = new java.lang.Runnable[r2]     // Catch:{ all -> 0x00d0 }
            r10[r25] = r8     // Catch:{ all -> 0x00d0 }
            r12.addOnReleaseListener(r10)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.functional.CountingLatch r8 = r1.bufferInUseCounter     // Catch:{ all -> 0x00d0 }
            r8.up()     // Catch:{ all -> 0x00d0 }
            r5.send(r12)     // Catch:{ all -> 0x00d0 }
            goto L_0x0228
        L_0x02a3:
            com.samsung.android.sum.core.Tag r8 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.SLog.v((com.samsung.android.sum.core.Tag) r8, (java.lang.String) r15)     // Catch:{ all -> 0x00d0 }
            goto L_0x0228
        L_0x02ac:
            r27 = 4
            r13 = -2
            if (r10 != r13) goto L_0x02b6
            r1.sendOutputFormat(r0)     // Catch:{ all -> 0x00d0 }
            goto L_0x0228
        L_0x02b6:
            if (r10 < 0) goto L_0x0228
            int r13 = r8.flags     // Catch:{ all -> 0x00d0 }
            r13 = r13 & 2
            if (r13 == 0) goto L_0x02ce
            boolean r9 = r1.isCodecSpecificDataRequired     // Catch:{ all -> 0x00d0 }
            if (r9 != 0) goto L_0x02cd
            android.media.MediaCodec r8 = r1.mediaCodec     // Catch:{ all -> 0x00d0 }
            r13 = r25
            r8.releaseOutputBuffer(r10, r13)     // Catch:{ all -> 0x00d0 }
            r9 = r2
            r8 = r13
            goto L_0x0046
        L_0x02cd:
            r9 = r2
        L_0x02ce:
            r13 = r25
            android.media.MediaCodec r14 = r1.mediaCodec     // Catch:{ all -> 0x00d0 }
            java.nio.ByteBuffer r14 = r14.getOutputBuffer(r10)     // Catch:{ all -> 0x00d0 }
            java.util.Objects.requireNonNull(r14)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r15 = com.samsung.android.sum.core.buffer.MediaBuffer.newAlloc(r0)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.types.DataType r13 = com.samsung.android.sum.core.types.DataType.U8C1     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r13 = r15.setDataType(r13)     // Catch:{ all -> 0x00d0 }
            java.lang.String r15 = "media-id"
            int r2 = r1.mediaId     // Catch:{ all -> 0x00d0 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r2 = r13.setExtra(r15, r2)     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = "track-idx"
            int r15 = r6.get()     // Catch:{ all -> 0x00d0 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r2 = r2.setExtra(r13, r15)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r2 = r2.setExtra(r11, r8)     // Catch:{ all -> 0x00d0 }
            a6.a r11 = new a6.a     // Catch:{ all -> 0x00d0 }
            r13 = 1
            r11.<init>((java.lang.Object) r1, (int) r10, (java.lang.Object) r8, (int) r13)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r2 = r2.setOnReleaseListener(r11)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.buffer.MediaBuffer r2 = r2.wrap(r14)     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.Tag r10 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d0 }
            r11.<init>()     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = "size="
            r11.append(r13)     // Catch:{ all -> 0x00d0 }
            int r13 = r8.size     // Catch:{ all -> 0x00d0 }
            r11.append(r13)     // Catch:{ all -> 0x00d0 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.SLog.v((com.samsung.android.sum.core.Tag) r10, (java.lang.String) r11)     // Catch:{ all -> 0x00d0 }
            int r10 = r8.size     // Catch:{ all -> 0x00d0 }
            if (r10 == 0) goto L_0x040a
            int r10 = r1.processedFrames     // Catch:{ all -> 0x00d0 }
            int r11 = r10 + 1
            r1.processedFrames = r11     // Catch:{ all -> 0x00d0 }
            if (r10 != 0) goto L_0x033c
            int[] r10 = new int[]{r27}     // Catch:{ all -> 0x00d0 }
            r2.setFlags(r10)     // Catch:{ all -> 0x00d0 }
        L_0x033c:
            long r10 = r8.presentationTimeUs     // Catch:{ all -> 0x00d0 }
            r1.lastTimestampUs = r10     // Catch:{ all -> 0x00d0 }
            com.samsung.android.sum.core.Tag r10 = r1.getTag()     // Catch:{ all -> 0x00d0 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d0 }
            r11.<init>()     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = "# of encoded frames: "
            r11.append(r13)     // Catch:{ all -> 0x00d0 }
            int r13 = r1.processedFrames     // Catch:{ all -> 0x00d0 }
            r11.append(r13)     // Catch:{ all -> 0x00d0 }
            java.lang.String r13 = "["
            r11.append(r13)     // Catch:{ all -> 0x00d0 }
            r15 = r6
            r13 = r7
            long r6 = r8.presentationTimeUs     // Catch:{ all -> 0x03c6 }
            r11.append(r6)     // Catch:{ all -> 0x03c6 }
            java.lang.String r6 = "]("
            r11.append(r6)     // Catch:{ all -> 0x03c6 }
            int r6 = r8.flags     // Catch:{ all -> 0x03c6 }
            java.lang.String r6 = java.lang.Integer.toHexString(r6)     // Catch:{ all -> 0x03c6 }
            r11.append(r6)     // Catch:{ all -> 0x03c6 }
            java.lang.String r6 = ")"
            r11.append(r6)     // Catch:{ all -> 0x03c6 }
            java.lang.String r6 = r11.toString()     // Catch:{ all -> 0x03c6 }
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r10, (java.lang.String) r6)     // Catch:{ all -> 0x03c6 }
            com.samsung.android.sum.core.Tag r6 = r1.getTag()     // Catch:{ all -> 0x03c6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x03c6 }
            r7.<init>()     // Catch:{ all -> 0x03c6 }
            java.lang.String r10 = "total # :"
            r7.append(r10)     // Catch:{ all -> 0x03c6 }
            java.util.Map<java.lang.Integer, java.lang.Integer> r10 = r1.numFramesToProcess     // Catch:{ all -> 0x03c6 }
            int r11 = r1.mediaId     // Catch:{ all -> 0x03c6 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x03c6 }
            java.lang.Object r10 = r10.get(r11)     // Catch:{ all -> 0x03c6 }
            r7.append(r10)     // Catch:{ all -> 0x03c6 }
            java.lang.String r10 = ", last ts: "
            r7.append(r10)     // Catch:{ all -> 0x03c6 }
            java.util.Map<java.lang.Integer, java.lang.Long> r10 = r1.lastTimestampToProcess     // Catch:{ all -> 0x03c6 }
            int r11 = r1.mediaId     // Catch:{ all -> 0x03c6 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x03c6 }
            java.lang.Object r10 = r10.get(r11)     // Catch:{ all -> 0x03c6 }
            r7.append(r10)     // Catch:{ all -> 0x03c6 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x03c6 }
            com.samsung.android.sum.core.SLog.d((com.samsung.android.sum.core.Tag) r6, (java.lang.String) r7)     // Catch:{ all -> 0x03c6 }
            boolean r6 = r4 instanceof com.samsung.android.sum.core.channel.SurfaceWriteChannel     // Catch:{ all -> 0x03c6 }
            if (r6 == 0) goto L_0x03f4
            int r6 = r1.processedFrames     // Catch:{ all -> 0x03c6 }
            boolean r6 = r1.isReachedLastFrame(r6)     // Catch:{ all -> 0x03c6 }
            if (r6 != 0) goto L_0x03c9
            long r6 = r8.presentationTimeUs     // Catch:{ all -> 0x03c6 }
            boolean r6 = r1.isReachedLastTimestamp(r6)     // Catch:{ all -> 0x03c6 }
            if (r6 == 0) goto L_0x03f4
            goto L_0x03c9
        L_0x03c6:
            r0 = move-exception
            goto L_0x0445
        L_0x03c9:
            int r6 = r8.flags     // Catch:{ all -> 0x03c6 }
            r6 = r6 | 4
            r8.flags = r6     // Catch:{ all -> 0x03c6 }
            java.util.Map<java.lang.Integer, com.samsung.android.sum.core.buffer.MediaBuffer> r6 = r1.eosBuffers     // Catch:{ all -> 0x03c6 }
            int r7 = r1.mediaId     // Catch:{ all -> 0x03c6 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x03c6 }
            boolean r6 = r6.containsKey(r7)     // Catch:{ all -> 0x03c6 }
            if (r6 == 0) goto L_0x03f4
            java.util.Map<java.lang.Integer, com.samsung.android.sum.core.buffer.MediaBuffer> r6 = r1.eosBuffers     // Catch:{ all -> 0x03c6 }
            int r7 = r1.mediaId     // Catch:{ all -> 0x03c6 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x03c6 }
            java.lang.Object r6 = r6.remove(r7)     // Catch:{ all -> 0x03c6 }
            com.samsung.android.sum.core.buffer.MediaBuffer r6 = (com.samsung.android.sum.core.buffer.MediaBuffer) r6     // Catch:{ all -> 0x03c6 }
            if (r6 == 0) goto L_0x03f4
            java.util.Map r6 = r6.getExtra()     // Catch:{ all -> 0x03c6 }
            r2.setExtra(r6)     // Catch:{ all -> 0x03c6 }
        L_0x03f4:
            int r6 = r8.offset     // Catch:{ all -> 0x03c6 }
            r14.position(r6)     // Catch:{ all -> 0x03c6 }
            int r6 = r8.offset     // Catch:{ all -> 0x03c6 }
            int r7 = r8.size     // Catch:{ all -> 0x03c6 }
            int r6 = r6 + r7
            r14.limit(r6)     // Catch:{ all -> 0x03c6 }
            com.samsung.android.sum.core.functional.CountingLatch r6 = r1.bufferInUseCounter     // Catch:{ all -> 0x03c6 }
            r6.up()     // Catch:{ all -> 0x03c6 }
            r5.send(r2)     // Catch:{ all -> 0x03c6 }
            goto L_0x040c
        L_0x040a:
            r15 = r6
            r13 = r7
        L_0x040c:
            int r6 = r8.flags     // Catch:{ all -> 0x03c6 }
            r6 = r6 & 4
            if (r6 == 0) goto L_0x043e
            com.samsung.android.sum.core.Tag r6 = r1.getTag()     // Catch:{ all -> 0x03c6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x03c6 }
            r7.<init>()     // Catch:{ all -> 0x03c6 }
            r7.append(r12)     // Catch:{ all -> 0x03c6 }
            int r10 = r1.mediaId     // Catch:{ all -> 0x03c6 }
            r7.append(r10)     // Catch:{ all -> 0x03c6 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x03c6 }
            com.samsung.android.sum.core.SLog.i((com.samsung.android.sum.core.Tag) r6, (java.lang.String) r7)     // Catch:{ all -> 0x03c6 }
            r6 = 1
            r1.isReachedOutputEos = r6     // Catch:{ all -> 0x03c6 }
            boolean r7 = r4 instanceof com.samsung.android.sum.core.channel.SurfaceWriteChannel     // Catch:{ all -> 0x03c6 }
            if (r7 != 0) goto L_0x043f
            int r7 = r8.size     // Catch:{ all -> 0x03c6 }
            if (r7 != 0) goto L_0x043f
            com.samsung.android.sum.core.functional.CountingLatch r7 = r1.bufferInUseCounter     // Catch:{ all -> 0x03c6 }
            r7.up()     // Catch:{ all -> 0x03c6 }
            r5.send(r2)     // Catch:{ all -> 0x03c6 }
            goto L_0x043f
        L_0x043e:
            r6 = 1
        L_0x043f:
            r2 = r6
            r7 = r13
            r6 = r15
            r8 = 0
            goto L_0x0046
        L_0x0445:
            boolean r2 = r3.isRunInstant()
            if (r2 == 0) goto L_0x045a
            if (r13 == 0) goto L_0x0452
            com.samsung.android.sum.core.channel.SurfaceWriteChannel r4 = (com.samsung.android.sum.core.channel.SurfaceWriteChannel) r4
            r4.reset()
        L_0x0452:
            r2 = r16
            r1.lastTimestampUs = r2
            r1.release()
            goto L_0x0462
        L_0x045a:
            r14 = -1
            r1.mediaId = r14
            com.samsung.android.sum.core.functional.CountingLatch r1 = r1.codecOnReadyCountingLatch
            r1.reset()
        L_0x0462:
            throw r0
        L_0x0463:
            r27.release()
            com.samsung.android.sum.core.exception.StreamFilterExitException r0 = new com.samsung.android.sum.core.exception.StreamFilterExitException
            java.lang.String r1 = "no media-codec given, might be released"
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.filter.EncoderFilter.run(com.samsung.android.sum.core.buffer.MediaBuffer, com.samsung.android.sum.core.buffer.MutableMediaBuffer):com.samsung.android.sum.core.buffer.MutableMediaBuffer");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$configCodec$2(Exception exc) {
    }
}
