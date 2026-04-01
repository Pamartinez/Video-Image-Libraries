package com.samsung.android.sum.core.filter;

import C3.C0392b;
import C9.a;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.os.ConditionVariable;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.h;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.MediaParserDescriptor;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageProducer;
import com.samsung.android.sum.core.types.MediaType;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaParserFilter extends MediaOutputStreamFilterBase {
    private static final int MAX_TRY_DEQUE_BUFFER = 2;
    private static final String TAG = Def.tagOf((Class<?>) MediaParserFilter.class);
    private int bitrate;
    private final ConditionVariable cvPause;
    /* access modifiers changed from: private */
    public final MediaParserDescriptor descriptor;
    private MessageProducer messageProducer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TrackInfo {
        /* access modifiers changed from: private */
        public int maxInputSize;
        /* access modifiers changed from: private */
        public MediaType mediaType;

        public TrackInfo(MediaType mediaType2, int i2) {
            this.mediaType = mediaType2;
            this.maxInputSize = i2;
        }
    }

    public MediaParserFilter(MediaParserDescriptor mediaParserDescriptor) {
        super(mediaParserDescriptor);
        ConditionVariable conditionVariable = new ConditionVariable();
        this.cvPause = conditionVariable;
        this.descriptor = mediaParserDescriptor;
        conditionVariable.open();
    }

    private MediaType getMediaType(String str) {
        if (str.startsWith("video")) {
            return MediaType.COMPRESSED_VIDEO;
        }
        if (str.startsWith("audio")) {
            return MediaType.COMPRESSED_AUDIO;
        }
        if (str.startsWith("application")) {
            return MediaType.META;
        }
        return MediaType.NONE;
    }

    private boolean isTrackDirectlyMuxing(String str) {
        return ((Boolean) Optional.ofNullable(this.descriptor.getExtra("direct-mux-track")).map(new a(str, 5)).orElse(Boolean.FALSE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$getFileDescriptorFromBuffer$0(Object obj) {
        if (obj instanceof ParcelFileDescriptor) {
            return ((ParcelFileDescriptor) obj).getFileDescriptor();
        }
        if (obj instanceof FileDescriptor) {
            return obj;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isTrackDirectlyMuxing$1(String str, Object obj) {
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
        Objects.requireNonNull(str);
        return Boolean.valueOf(stream.anyMatch(new C0392b(str, 13)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$2(MediaExtractor mediaExtractor, FileDescriptor fileDescriptor, int i2, List list, int i7) {
        String str;
        MediaExtractor mediaExtractor2 = mediaExtractor;
        int i8 = i7;
        String string = mediaExtractor2.getTrackFormat(i8).getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
        String str2 = TAG;
        SLog.i(str2, "add track for " + string);
        Objects.requireNonNull(string);
        MediaType mediaType = getMediaType(string);
        if (!this.descriptor.isToParse(mediaType)) {
            SLog.i(str2, "descriptor doesn't have parse type: " + mediaType);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(MediaDefs.Image.HEIF.HEIF_MIME_BOX, string);
        MediaFormat trackFormat = mediaExtractor2.getTrackFormat(i8);
        SLog.d(str2, "Config format: " + trackFormat);
        hashMap.put("media-format", trackFormat);
        hashMap.put(Message.KEY_MEDIA_TYPE, mediaType);
        if (mediaType != MediaType.META) {
            if (mediaType == MediaType.COMPRESSED_VIDEO) {
                str = "video-format";
            } else {
                str = "audio-format";
            }
            hashMap.put(str, trackFormat);
        }
        if (trackFormat.containsKey("width")) {
            hashMap.put("width", Integer.valueOf(trackFormat.getInteger("width")));
        }
        if (trackFormat.containsKey("height")) {
            hashMap.put("height", Integer.valueOf(trackFormat.getInteger("height")));
        }
        int i10 = 0;
        if (trackFormat.containsKey(Message.KEY_ROTATION)) {
            hashMap.put(Message.KEY_ROTATION, Integer.valueOf(trackFormat.getInteger(Message.KEY_ROTATION)));
        } else {
            hashMap.put(Message.KEY_ROTATION, 0);
        }
        if (trackFormat.containsKey("bitrate")) {
            hashMap.put("bitrate", Integer.valueOf(trackFormat.getInteger("bitrate")));
        } else {
            int i11 = this.bitrate;
            if (i11 != 0) {
                hashMap.put("bitrate", Integer.valueOf(i11));
            } else if (trackFormat.containsKey("durationUs")) {
                int fileSize = (int) (((Def.getFileSize(fileDescriptor) << 3) * 1000000) / trackFormat.getLong("durationUs"));
                this.bitrate = fileSize;
                hashMap.put("bitrate", Integer.valueOf(fileSize));
            }
        }
        if (trackFormat.containsKey("color-standard")) {
            hashMap.put("color-standard", Integer.valueOf(trackFormat.getInteger("color-standard")));
        }
        if (trackFormat.containsKey("color-transfer")) {
            hashMap.put("color-transfer", Integer.valueOf(trackFormat.getInteger("color-transfer")));
        }
        if (trackFormat.containsKey("color-range")) {
            hashMap.put("color-range", Integer.valueOf(trackFormat.getInteger("color-range")));
        }
        if (trackFormat.containsKey("frame-rate")) {
            hashMap.put("frame-rate", Integer.valueOf(trackFormat.getInteger("frame-rate")));
        }
        if (this.descriptor.containsExtra("i-frame-interval")) {
            hashMap.put("i-frame-interval", this.descriptor.getExtra("i-frame-interval"));
        } else {
            hashMap.put("i-frame-interval", Float.valueOf((float) trackFormat.getInteger("i-frame-interval", 1)));
        }
        if (trackFormat.containsKey("sample-rate")) {
            hashMap.put("sample-rate", Integer.valueOf(trackFormat.getInteger("sample-rate")));
        }
        if (trackFormat.containsKey("channel-count")) {
            hashMap.put("channel-count", Integer.valueOf(trackFormat.getInteger("channel-count")));
        }
        if (trackFormat.containsKey("max-input-size")) {
            hashMap.put("max-input-size", Integer.valueOf(trackFormat.getInteger("max-input-size")));
            i10 = trackFormat.getInteger("max-input-size");
        }
        if (isTrackDirectlyMuxing(string)) {
            SLog.i(str2, "mux direct for ".concat(string));
            this.messageProducer.newMessage(203).put("media-id", Integer.valueOf(i2)).put(Message.KEY_MEDIA_TYPE, mediaType).put("media-format", new MediaFormat(trackFormat)).put("direct-mux-track", string).post();
        }
        hashMap.put("media-id", Integer.valueOf(i2));
        this.messageProducer.newMessage(201, (Map<String, Object>) hashMap).post();
        list.add(new Pair(Integer.valueOf(i8), new TrackInfo(mediaType, i10)));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$run$3(List list, Pair pair) {
        int indexOf = list.indexOf(((TrackInfo) pair.second).mediaType);
        if (indexOf == -1) {
            return Integer.MAX_VALUE;
        }
        return indexOf;
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [android.util.Pair, int], vars: [r23v0 ?, r23v5 ?, r23v4 ?, r23v1 ?, r23v2 ?, r23v8 ?, r23v3 ?, r23v7 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    public /* synthetic */ void lambda$run$4(android.media.MediaExtractor r19, long r20, int r22, 
/*
Method generation error in method: com.samsung.android.sum.core.filter.MediaParserFilter.lambda$run$4(android.media.MediaExtractor, long, int, android.util.Pair):void, dex: classes4.dex
    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r23v0 ?
    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
    	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:157)
    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:129)
    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
    	at java.util.ArrayList.forEach(ArrayList.java:1259)
    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
    
*/

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public FileDescriptor getFileDescriptorFromBuffer(MediaBuffer mediaBuffer) {
        if (mediaBuffer.getData() instanceof ParcelFileDescriptor) {
            Class cls = ParcelFileDescriptor.class;
            ((ParcelFileDescriptor) mediaBuffer.getTypedData(cls)).checkError();
            return ((ParcelFileDescriptor) mediaBuffer.getTypedData(cls)).getFileDescriptor();
        } else if (mediaBuffer.getData() instanceof FileDescriptor) {
            return (FileDescriptor) mediaBuffer.getTypedData(FileDescriptor.class);
        } else {
            ((ParcelFileDescriptor) mediaBuffer.getExtra(Message.KEY_FILE_DESCRIPTOR)).checkError();
            return (FileDescriptor) Optional.ofNullable(mediaBuffer.getExtra(Message.KEY_FILE_DESCRIPTOR)).map(new com.samsung.android.sdk.scs.ai.visual.c2pa.a(28)).orElseThrow(new h(0));
        }
    }

    public void pause() {
        this.cvPause.close();
    }

    public void resume() {
        this.cvPause.open();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MediaExtractor mediaExtractor;
        Throwable th;
        MediaBuffer mediaBuffer2 = mediaBuffer;
        MutableMediaBuffer mutableMediaBuffer2 = mutableMediaBuffer;
        String str = TAG;
        SLog.d(str, "run: ibuf=" + mediaBuffer2 + ", obuf=" + mutableMediaBuffer2);
        MediaExtractor mediaExtractor2 = new MediaExtractor();
        try {
            int intValue = ((Integer) mediaBuffer2.getExtra("media-id")).intValue();
            FileDescriptor fileDescriptorFromBuffer = getFileDescriptorFromBuffer(mediaBuffer);
            Def.require(fileDescriptorFromBuffer.valid());
            FileDescriptor fileDescriptorFromBuffer2 = getFileDescriptorFromBuffer(mutableMediaBuffer2);
            Def.require(fileDescriptorFromBuffer2.valid());
            ((Long) mediaBuffer2.getExtra(Message.KEY_START_TIME_US, -1L)).getClass();
            long longValue = ((Long) mediaBuffer2.getExtra(Message.KEY_END_TIME_US, Long.MAX_VALUE)).longValue();
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(fileDescriptorFromBuffer);
                int intValue2 = ((Integer) Optional.ofNullable(mediaMetadataRetriever.extractMetadata(32)).map(new e(18)).orElse(0)).intValue();
                this.bitrate = ((Integer) Optional.ofNullable(mediaMetadataRetriever.extractMetadata(20)).map(new e(18)).orElse(0)).intValue();
                mediaMetadataRetriever.release();
                this.messageProducer.newMessage(2, (Map<String, Object>) new HashMap<String, Object>(intValue, intValue2, mediaBuffer2, mutableMediaBuffer2) {
                    final /* synthetic */ int val$frameCount;
                    final /* synthetic */ MediaBuffer val$ibuf;
                    final /* synthetic */ int val$mediaId;
                    final /* synthetic */ MutableMediaBuffer val$obuf;

                    {
                        this.val$mediaId = r2;
                        this.val$frameCount = r3;
                        this.val$ibuf = r4;
                        this.val$obuf = r5;
                        put("media-id", Integer.valueOf(r2));
                        put(Message.KEY_WHOLE_FRAMES, Integer.valueOf(r3));
                        if (r4.containsExtra(Message.KEY_IN_FILE)) {
                            put(Message.KEY_IN_FILE, r4.getExtra(Message.KEY_IN_FILE));
                        }
                        if (r5.containsExtra(Message.KEY_OUT_FILE)) {
                            put(Message.KEY_OUT_FILE, r5.getExtra(Message.KEY_OUT_FILE));
                        }
                    }
                }).post();
                mediaMetadataRetriever.close();
                mediaExtractor2.setDataSource(fileDescriptorFromBuffer);
                MessageProducer messageProducer2 = this.messageProducer;
                MediaExtractor mediaExtractor3 = mediaExtractor2;
                try {
                    AnonymousClass2 r0 = new HashMap<String, Object>(intValue, mediaExtractor3, fileDescriptorFromBuffer2, mutableMediaBuffer, mediaBuffer) {
                        final /* synthetic */ MediaExtractor val$extractor;
                        final /* synthetic */ MediaBuffer val$ibuf;
                        final /* synthetic */ int val$mediaId;
                        final /* synthetic */ MutableMediaBuffer val$obuf;
                        final /* synthetic */ FileDescriptor val$outputFd;

                        {
                            this.val$mediaId = r3;
                            this.val$extractor = r4;
                            this.val$outputFd = r5;
                            this.val$obuf = r6;
                            this.val$ibuf = r7;
                            put("media-id", Integer.valueOf(r3));
                            put("track-count", Integer.valueOf(Integer.min(MediaParserFilter.this.descriptor.countToParse(), r4.getTrackCount())));
                            put(Message.KEY_OUT_FILE, r5);
                            if (r6.containsExtra(Message.KEY_OUT_EXTRA_FILE)) {
                                put(Message.KEY_OUT_EXTRA_FILE, r6.getExtra(Message.KEY_OUT_EXTRA_FILE));
                            }
                            if (!r6.containsExtra(Message.KEY_CACHE_ID) && r7.containsExtra(Message.KEY_CACHE_ID)) {
                                put(Message.KEY_CACHE_ID, r7.getExtra(Message.KEY_CACHE_ID));
                            }
                        }
                    };
                    int i2 = intValue;
                    mediaExtractor = mediaExtractor3;
                    messageProducer2.newMessage(204, (Map<String, Object>) r0).post();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = arrayList;
                    IntStream.range(0, mediaExtractor.getTrackCount()).forEach(new k(this, mediaExtractor, fileDescriptorFromBuffer, i2, arrayList));
                    arrayList2.sort(Comparator.comparingInt(new U9.a(2, this.descriptor.getPriority())));
                    arrayList2.forEach(new l(this, mediaExtractor, longValue, i2));
                    mediaExtractor.release();
                    return mutableMediaBuffer;
                } catch (IOException e) {
                    e = e;
                    mediaExtractor = mediaExtractor3;
                    try {
                        String str2 = TAG;
                        SLog.i(str2, "fail to parse: " + e);
                        throw new IllegalStateException(e);
                    } catch (Throwable th2) {
                        th = th2;
                        mediaExtractor.release();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    mediaExtractor = mediaExtractor3;
                    mediaExtractor.release();
                    throw th;
                }
            } catch (IOException e7) {
                e = e7;
                String str22 = TAG;
                SLog.i(str22, "fail to parse: " + e);
                throw new IllegalStateException(e);
            } catch (Throwable th4) {
                th.addSuppressed(th4);
            }
            throw th;
        } catch (IOException e8) {
            e = e8;
            mediaExtractor = mediaExtractor2;
            String str222 = TAG;
            SLog.i(str222, "fail to parse: " + e);
            throw new IllegalStateException(e);
        } catch (Throwable th5) {
            th = th5;
            mediaExtractor = mediaExtractor2;
            mediaExtractor.release();
            throw th;
        }
    }

    public void setMessageProducer(MessageProducer messageProducer2) {
        this.messageProducer = messageProducer2;
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }
}
