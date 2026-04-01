package com.samsung.android.sum.core.controller;

import A8.C0544a;
import Ad.C0720a;
import android.os.ConditionVariable;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.controller.MediaController;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.graph.Graph;
import com.samsung.android.sum.core.message.BlockingMessageChannel;
import com.samsung.android.sum.core.message.ContentsInfo;
import com.samsung.android.sum.core.message.Event;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageConsumer;
import com.samsung.android.sum.core.message.MessagePublisher;
import com.samsung.android.sum.core.message.MessageSubscriberBase;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaFilterController implements MediaController<Response>, MessageConsumer {
    private static final long MAX_RELEASE_TIMEOUT_MILLIS = 1000;
    /* access modifiers changed from: private */
    public static final String TAG = Def.tagOf((Class<?>) MediaFilterController.class);
    private Thread argentReleaseThread;
    private final List<Supplier<Message>> conservedMessageTasks;
    private final Map<Integer, ContentsInfo> contentsInfoMap = new HashMap();
    private MediaController.OnEventListener eventListener;
    private final Condition graphCondition;
    private final ReentrantLock graphLock;
    /* access modifiers changed from: private */
    public final int id;
    protected boolean isActive = true;
    private Graph<MediaFilter> mediaFilterGraph;
    protected AtomicInteger mediaIdGenerator = new AtomicInteger(0);
    protected MessagePublisher messagePublisher;
    protected List<Consumer<MessagePublisher>> messagePublisherListeners = new ArrayList();
    protected MessageSubscriberImpl messageSubscriber;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MessageSubscriberImpl extends MessageSubscriberBase {
        private final Thread messageThread;

        public MessageSubscriberImpl() {
            super(new BlockingMessageChannel("MediaFilterController"));
            Thread thread = new Thread(new h(0, this));
            this.messageThread = thread;
            thread.start();
            ((BlockingMessageChannel) getMessageChannel()).setThreadWeakReference(new WeakReference(thread));
        }

        /* access modifiers changed from: private */
        public void threadEntry() {
            while (true) {
                try {
                    onMessageReceived((Message) getMessageChannel().receive());
                } catch (CancellationException unused) {
                    SLog.d(MediaFilterController.TAG, "message channel is canceled");
                    return;
                }
            }
        }

        public void release() {
            getMessageChannel().cancel();
        }
    }

    public MediaFilterController(int i2) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.graphLock = reentrantLock;
        this.graphCondition = reentrantLock.newCondition();
        this.conservedMessageTasks = new CopyOnWriteArrayList();
        this.id = i2;
        MessageSubscriberImpl messageSubscriberImpl = new MessageSubscriberImpl();
        this.messageSubscriber = messageSubscriberImpl;
        messageSubscriberImpl.addMessageConsumer(this);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onMessageReceived$10(Message message, Integer num) {
        if (num.intValue() == message.getCode()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Event lambda$onMessageReceived$11(Message message) {
        return (Event) Event.of(message).put("id", Integer.valueOf(this.id));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$request$2(MediaBuffer mediaBuffer) {
        if (((Boolean) mediaBuffer.getExtra("singular-buffer", Boolean.FALSE)).booleanValue()) {
            return new ArrayList<MediaBuffer>(mediaBuffer) {
                final /* synthetic */ MediaBuffer val$it;

                {
                    this.val$it = r2;
                    add(r2);
                }
            };
        }
        return mediaBuffer.asList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$0(MediaBuffer mediaBuffer) {
        int intValue = ((Integer) mediaBuffer.getExtra("media-id", -1)).intValue();
        if (intValue < 0) {
            intValue = this.mediaIdGenerator.incrementAndGet();
            mediaBuffer.setExtra("media-id", Integer.valueOf(intValue));
        }
        ContentsInfo contentsInfo = new ContentsInfo();
        contentsInfo.setContentsId(intValue);
        if (mediaBuffer.containsExtra("show-progress") && ((Boolean) mediaBuffer.getExtra("show-progress")).booleanValue()) {
            contentsInfo.setData("show-progress", Boolean.TRUE);
        }
        if (mediaBuffer.containsExtra(Message.KEY_DISPLAY_NAME)) {
            contentsInfo.setData(Message.KEY_DISPLAY_NAME, mediaBuffer.getExtra(Message.KEY_DISPLAY_NAME));
        }
        if (mediaBuffer.getFormat() != null) {
            contentsInfo.setOriginalDataType(mediaBuffer.getFormat().getDataType());
            contentsInfo.setOriginalColorFormat(mediaBuffer.getFormat().getColorFormat());
        }
        this.contentsInfoMap.put(Integer.valueOf(intValue), contentsInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MutableMediaBuffer lambda$run$1(MediaBuffer mediaBuffer) {
        Integer num = (Integer) mediaBuffer.getExtra("media-id");
        int intValue = num.intValue();
        ContentsInfo contentsInfo = this.contentsInfoMap.get(num);
        MediaType mediaType = mediaBuffer.getFormat().getMediaType();
        String str = TAG;
        SLog.d(str, "[#" + intValue + "]mediaType=" + mediaType + ", contentsInfo= refactoring");
        MutableMediaBuffer mutable = mediaBuffer.toMutable();
        if (!mediaType.isMetaData() && !mediaType.isScala() && !((Boolean) mutable.getExtra("freezed", Boolean.FALSE)).booleanValue()) {
            SLog.d(str, "convert to original format");
            ColorFormat colorFormat = mutable.getFormat().getColorFormat();
            if (colorFormat != contentsInfo.getOriginalColorFormat()) {
                SLog.d(str, Def.fmtstr("color-format of output(%s) is differ from one of input(%s)", colorFormat.name(), contentsInfo.getOriginalColorFormat().name()));
                MutableMediaBuffer mutableOf = MediaBuffer.mutableOf(contentsInfo.getOriginalColorFormat());
                UniImgp.ofCvtColor().run((MediaBuffer) mutable, mutableOf);
                mutable.put(mutableOf.get());
            }
            DataType dataType = mutable.getFormat().getDataType();
            if (dataType != contentsInfo.getOriginalDataType()) {
                SLog.d(str, Def.fmtstr("data-type of output(%s) is differ from one of input(%s)", dataType.name(), contentsInfo.getOriginalDataType().name()));
                MutableMediaBuffer mutableOf2 = MediaBuffer.mutableOf(contentsInfo.getOriginalDataType());
                UniImgp.ofCvtData().run((MediaBuffer) mutable, mutableOf2);
                mutable.put(mutableOf2.get());
            }
        }
        return mutable;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendMessage$3(Request request, ConditionVariable conditionVariable, Message message) {
        request.put(message.get());
        conditionVariable.open();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Message lambda$sendMessage$4(Request request, Message message) {
        ConditionVariable conditionVariable;
        if (!request.isOneWay()) {
            conditionVariable = new ConditionVariable();
            message.setPublisher(this.messagePublisher).then(new e(4, request, conditionVariable));
        } else {
            conditionVariable = null;
        }
        this.messagePublisher.sendMessage(message);
        if (request.isOneWay() || conditionVariable.block(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS)) {
            return message;
        }
        throw new IllegalStateException(Def.fmtstr("timeout to get response of %d for %d ms", Integer.valueOf(request.getCode()), Long.valueOf(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Message lambda$sendMessage$5(Message message) {
        this.messagePublisher.sendMessage(message);
        return message;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Message lambda$sendMessage$7(int i2, Consumer consumer) {
        Message newMessage = this.messagePublisher.getMessageProducer().newMessage(i2);
        consumer.accept(newMessage);
        newMessage.post();
        return newMessage;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaFilterGraph$9(Consumer consumer) {
        consumer.accept(this.messagePublisher);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$tryRelease$12() {
        try {
            release();
        } catch (Exception e) {
            String str = TAG;
            SLog.i(str, "fail to release: " + e);
        }
    }

    private void onIndispensableError(Exception exc) {
        String str = TAG;
        SLog.e(str, "received ERROR_MALFORMED/ERROR_NODE_DISABLED: " + exc);
        if (this.isActive) {
            this.isActive = false;
            tryRelease(3);
        }
    }

    private void tryRelease(int i2) {
        int i7 = 0;
        int i8 = 100;
        while (true) {
            try {
                if (this.argentReleaseThread == null) {
                    this.argentReleaseThread = new Thread(new h(1, this), "argent-release-thread");
                }
                this.argentReleaseThread.start();
                return;
            } catch (Throwable th) {
                if (i7 >= i2) {
                    String str = TAG;
                    SLog.e(str, "fail to release for " + i2 + "times due to" + th);
                    return;
                }
                try {
                    Thread.sleep((long) i8);
                } catch (Exception e) {
                    String str2 = TAG;
                    SLog.i(str2, "waiting is interrupted: " + e);
                }
                i8 *= 2;
                i7++;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        r1.graphLock.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r1.graphLock.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        com.samsung.android.sum.core.SLog.i(TAG, "fail to get graph");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean waitIfGraphAbsent(long r2, java.util.concurrent.TimeUnit r4) {
        /*
            r1 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r1.graphLock
            r0.lock()
            com.samsung.android.sum.core.graph.Graph<com.samsung.android.sum.core.filter.MediaFilter> r0 = r1.mediaFilterGraph     // Catch:{ InterruptedException -> 0x0018 }
            if (r0 != 0) goto L_0x0011
            java.util.concurrent.locks.Condition r0 = r1.graphCondition     // Catch:{ InterruptedException -> 0x0018 }
            r0.await(r2, r4)     // Catch:{ InterruptedException -> 0x0018 }
            goto L_0x0011
        L_0x000f:
            r2 = move-exception
            goto L_0x0026
        L_0x0011:
            java.util.concurrent.locks.ReentrantLock r1 = r1.graphLock
            r1.unlock()
            r1 = 1
            return r1
        L_0x0018:
            java.lang.String r2 = TAG     // Catch:{ all -> 0x000f }
            java.lang.String r3 = "fail to get graph"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x000f }
            java.util.concurrent.locks.ReentrantLock r1 = r1.graphLock
            r1.unlock()
            r1 = 0
            return r1
        L_0x0026:
            java.util.concurrent.locks.ReentrantLock r1 = r1.graphLock
            r1.unlock()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.controller.MediaFilterController.waitIfGraphAbsent(long, java.util.concurrent.TimeUnit):boolean");
    }

    public void addMessagePublisherListener(Consumer<MessagePublisher> consumer) {
        MessagePublisher messagePublisher2 = this.messagePublisher;
        if (messagePublisher2 != null) {
            consumer.accept(messagePublisher2);
        } else {
            this.messagePublisherListeners.add(consumer);
        }
    }

    public int[] getMessagesToReceive() {
        return new int[]{1003, 101, 102, 109, 110, 105, 106, 107, 108, 111, 112, 113, 114, 115, 116};
    }

    /* JADX WARNING: type inference failed for: r4v27, types: [com.samsung.android.sum.core.message.Message] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onMessageReceived(com.samsung.android.sum.core.message.Message r8) {
        /*
            r7 = this;
            java.lang.String r0 = "display-name"
            java.lang.String r1 = "show-progress"
            java.lang.String r2 = "media-id"
            java.lang.String r3 = TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onMessageReceived: "
            r4.<init>(r5)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r3, (java.lang.String) r4)
            r3 = 0
            boolean r4 = r8.isError()     // Catch:{ Exception -> 0x0053 }
            r5 = 0
            if (r4 == 0) goto L_0x006b
            java.lang.Exception r4 = r8.getException()     // Catch:{ Exception -> 0x0053 }
            boolean r4 = r4 instanceof com.samsung.android.sum.core.exception.ContentFilterOutException     // Catch:{ Exception -> 0x0053 }
            if (r4 == 0) goto L_0x0056
            java.lang.Exception r4 = r8.getException()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ Exception -> 0x0053 }
            java.util.Optional r4 = java.util.Optional.ofNullable(r4)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r5 = "none"
            java.lang.Object r4 = r4.orElse(r5)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x0053 }
            r5 = 501(0x1f5, float:7.02E-43)
            com.samsung.android.sum.core.message.Event r4 = com.samsung.android.sum.core.message.Event.of((int) r5, (java.lang.String) r4)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r5 = "id"
            int r6 = r7.id     // Catch:{ Exception -> 0x0053 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0053 }
            com.samsung.android.sum.core.message.Message r4 = r4.put(r5, r6)     // Catch:{ Exception -> 0x0053 }
            r5 = r4
            com.samsung.android.sum.core.message.Event r5 = (com.samsung.android.sum.core.message.Event) r5     // Catch:{ Exception -> 0x0053 }
            goto L_0x0056
        L_0x0053:
            r7 = move-exception
            goto L_0x00e3
        L_0x0056:
            int r4 = r8.getCode()     // Catch:{ Exception -> 0x0053 }
            r6 = -2
            if (r4 == r6) goto L_0x0064
            int r4 = r8.getCode()     // Catch:{ Exception -> 0x0053 }
            r6 = -8
            if (r4 != r6) goto L_0x006b
        L_0x0064:
            java.lang.Exception r4 = r8.getException()     // Catch:{ Exception -> 0x0053 }
            r7.onIndispensableError(r4)     // Catch:{ Exception -> 0x0053 }
        L_0x006b:
            boolean r4 = r8.contains(r2)     // Catch:{ Exception -> 0x0053 }
            if (r4 == 0) goto L_0x00c9
            r4 = 111(0x6f, float:1.56E-43)
            r6 = 116(0x74, float:1.63E-43)
            java.util.stream.IntStream r4 = java.util.stream.IntStream.range(r4, r6)     // Catch:{ Exception -> 0x0053 }
            java.util.stream.Stream r4 = r4.boxed()     // Catch:{ Exception -> 0x0053 }
            com.samsung.android.sum.core.controller.f r6 = new com.samsung.android.sum.core.controller.f     // Catch:{ Exception -> 0x0053 }
            r6.<init>(r8)     // Catch:{ Exception -> 0x0053 }
            boolean r4 = r4.noneMatch(r6)     // Catch:{ Exception -> 0x0053 }
            if (r4 == 0) goto L_0x00c9
            java.lang.Object r2 = r8.get(r2)     // Catch:{ Exception -> 0x0053 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x0053 }
            r2.getClass()     // Catch:{ Exception -> 0x0053 }
            java.util.Map<java.lang.Integer, com.samsung.android.sum.core.message.ContentsInfo> r4 = r7.contentsInfoMap     // Catch:{ Exception -> 0x0053 }
            java.lang.Object r2 = r4.get(r2)     // Catch:{ Exception -> 0x0053 }
            com.samsung.android.sum.core.message.ContentsInfo r2 = (com.samsung.android.sum.core.message.ContentsInfo) r2     // Catch:{ Exception -> 0x0053 }
            com.samsung.android.sum.core.message.ContentsInfo r4 = com.samsung.android.sum.core.message.ContentsInfo.wrap(r8)     // Catch:{ Exception -> 0x0053 }
            r2.join(r4)     // Catch:{ Exception -> 0x0053 }
            int r4 = r8.getCode()     // Catch:{ Exception -> 0x0053 }
            r6 = 108(0x6c, float:1.51E-43)
            if (r4 != r6) goto L_0x00c9
            boolean r4 = r2.getAsBooleanOr(r1, r3)     // Catch:{ Exception -> 0x0053 }
            if (r4 == 0) goto L_0x00c9
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ Exception -> 0x0053 }
            r8.put(r1, r4)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r1 = "whole-frames"
            int r4 = r2.getWholeFrames()     // Catch:{ Exception -> 0x0053 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0053 }
            r8.put(r1, r4)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r1 = ""
            java.lang.Object r1 = r2.getDataOr(r0, r1)     // Catch:{ Exception -> 0x0053 }
            r8.put(r0, r1)     // Catch:{ Exception -> 0x0053 }
        L_0x00c9:
            com.samsung.android.sum.core.controller.MediaController$OnEventListener r0 = r7.eventListener     // Catch:{ Exception -> 0x0053 }
            if (r0 == 0) goto L_0x00e2
            java.util.Optional r1 = java.util.Optional.ofNullable(r5)     // Catch:{ Exception -> 0x0053 }
            com.samsung.android.sum.core.controller.a r2 = new com.samsung.android.sum.core.controller.a     // Catch:{ Exception -> 0x0053 }
            r4 = 1
            r2.<init>(r7, r8, r4)     // Catch:{ Exception -> 0x0053 }
            java.lang.Object r7 = r1.orElseGet(r2)     // Catch:{ Exception -> 0x0053 }
            com.samsung.android.sum.core.message.Event r7 = (com.samsung.android.sum.core.message.Event) r7     // Catch:{ Exception -> 0x0053 }
            r0.onEvent(r7)     // Catch:{ Exception -> 0x0053 }
            r7 = 1
            return r7
        L_0x00e2:
            return r3
        L_0x00e3:
            java.lang.String r8 = TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "fail to handle msg: "
            r0.<init>(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            com.samsung.android.sum.core.SLog.w((java.lang.String) r8, (java.lang.String) r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.controller.MediaFilterController.onMessageReceived(com.samsung.android.sum.core.message.Message):boolean");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:12|13|14|15|16|17) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void quitNow() {
        /*
            r4 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r4.graphLock
            r0.lock()
            com.samsung.android.sum.core.graph.Graph<com.samsung.android.sum.core.filter.MediaFilter> r0 = r4.mediaFilterGraph     // Catch:{ all -> 0x0010 }
            r1 = 0
            if (r0 == 0) goto L_0x0012
            r0.quitNow()     // Catch:{ all -> 0x0010 }
            r4.mediaFilterGraph = r1     // Catch:{ all -> 0x0010 }
            goto L_0x0012
        L_0x0010:
            r0 = move-exception
            goto L_0x0043
        L_0x0012:
            com.samsung.android.sum.core.controller.MediaFilterController$MessageSubscriberImpl r0 = r4.messageSubscriber     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x001b
            r0.release()     // Catch:{ all -> 0x0010 }
            r4.messageSubscriber = r1     // Catch:{ all -> 0x0010 }
        L_0x001b:
            java.lang.Thread r0 = r4.argentReleaseThread     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x0033
            r0.interrupt()     // Catch:{ all -> 0x0010 }
            java.lang.Thread r0 = r4.argentReleaseThread     // Catch:{ Exception -> 0x002a }
            r2 = 1000(0x3e8, double:4.94E-321)
            r0.join(r2)     // Catch:{ Exception -> 0x002a }
            goto L_0x0031
        L_0x002a:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x0010 }
            java.lang.String r2 = "fail to join argentReleaseThread for 1000ms"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r2)     // Catch:{ all -> 0x0010 }
        L_0x0031:
            r4.argentReleaseThread = r1     // Catch:{ all -> 0x0010 }
        L_0x0033:
            java.util.List<java.util.function.Consumer<com.samsung.android.sum.core.message.MessagePublisher>> r0 = r4.messagePublisherListeners     // Catch:{ all -> 0x0010 }
            r0.clear()     // Catch:{ all -> 0x0010 }
            java.util.List<java.util.function.Supplier<com.samsung.android.sum.core.message.Message>> r0 = r4.conservedMessageTasks     // Catch:{ all -> 0x0010 }
            r0.clear()     // Catch:{ all -> 0x0010 }
            java.util.concurrent.locks.ReentrantLock r4 = r4.graphLock
            r4.unlock()
            return
        L_0x0043:
            java.util.concurrent.locks.ReentrantLock r4 = r4.graphLock
            r4.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.controller.MediaFilterController.quitNow():void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:12|13|14|15|16|17) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void quitSafely() {
        /*
            r4 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r4.graphLock
            r0.lock()
            com.samsung.android.sum.core.graph.Graph<com.samsung.android.sum.core.filter.MediaFilter> r0 = r4.mediaFilterGraph     // Catch:{ all -> 0x0010 }
            r1 = 0
            if (r0 == 0) goto L_0x0012
            r0.quitSafely()     // Catch:{ all -> 0x0010 }
            r4.mediaFilterGraph = r1     // Catch:{ all -> 0x0010 }
            goto L_0x0012
        L_0x0010:
            r0 = move-exception
            goto L_0x0043
        L_0x0012:
            com.samsung.android.sum.core.controller.MediaFilterController$MessageSubscriberImpl r0 = r4.messageSubscriber     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x001b
            r0.release()     // Catch:{ all -> 0x0010 }
            r4.messageSubscriber = r1     // Catch:{ all -> 0x0010 }
        L_0x001b:
            java.lang.Thread r0 = r4.argentReleaseThread     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x0033
            r0.interrupt()     // Catch:{ all -> 0x0010 }
            java.lang.Thread r0 = r4.argentReleaseThread     // Catch:{ Exception -> 0x002a }
            r2 = 1000(0x3e8, double:4.94E-321)
            r0.join(r2)     // Catch:{ Exception -> 0x002a }
            goto L_0x0031
        L_0x002a:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x0010 }
            java.lang.String r2 = "fail to join argentReleaseThread for 1000ms"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r2)     // Catch:{ all -> 0x0010 }
        L_0x0031:
            r4.argentReleaseThread = r1     // Catch:{ all -> 0x0010 }
        L_0x0033:
            java.util.List<java.util.function.Consumer<com.samsung.android.sum.core.message.MessagePublisher>> r0 = r4.messagePublisherListeners     // Catch:{ all -> 0x0010 }
            r0.clear()     // Catch:{ all -> 0x0010 }
            java.util.List<java.util.function.Supplier<com.samsung.android.sum.core.message.Message>> r0 = r4.conservedMessageTasks     // Catch:{ all -> 0x0010 }
            r0.clear()     // Catch:{ all -> 0x0010 }
            java.util.concurrent.locks.ReentrantLock r4 = r4.graphLock
            r4.unlock()
            return
        L_0x0043:
            java.util.concurrent.locks.ReentrantLock r4 = r4.graphLock
            r4.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.controller.MediaFilterController.quitSafely():void");
    }

    @Deprecated
    public void release() {
        quitSafely();
    }

    public void sendMessage(Request request) {
        String str = TAG;
        SLog.d(str, "sendMessage...E: " + request);
        Message message = request.toMessage();
        C0544a aVar = new C0544a(this, request, message, 1);
        if (this.messagePublisher == null) {
            SLog.i(str, "graph is not created yet. add message to waiting task: " + message.getCode());
            this.conservedMessageTasks.add(aVar);
        } else if (waitIfGraphAbsent(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            aVar.get();
        }
        SLog.d(str, "sendMessage...X: " + request);
    }

    /* JADX INFO: finally extract failed */
    public void setMediaFilterGraph(Graph<MediaFilter> graph) {
        this.graphLock.lock();
        try {
            Graph<MediaFilter> graph2 = this.mediaFilterGraph;
            if (graph2 == null) {
                this.mediaFilterGraph = graph;
                graph.setMessageSubscriber(this.messageSubscriber);
                this.messagePublisher = graph.getMessagePublisher();
            } else if (graph2 != graph) {
                graph.release();
            }
            this.graphLock.unlock();
            String str = TAG;
            SLog.d(str, "run prevReceivedMessageTasks, size=" + this.conservedMessageTasks.size());
            this.conservedMessageTasks.forEach(new b(0));
            this.conservedMessageTasks.clear();
            if (!this.messagePublisherListeners.isEmpty()) {
                this.messagePublisherListeners.forEach(new c(this, 0));
            }
            this.graphLock.lock();
            try {
                this.graphCondition.signalAll();
            } finally {
                this.graphLock.unlock();
            }
        } catch (Throwable th) {
            this.graphLock.unlock();
            throw th;
        }
    }

    public void setOnEventListener(MediaController.OnEventListener onEventListener) {
        this.eventListener = onEventListener;
    }

    public Response request(Request request) {
        String str = TAG;
        SLog.d(str, "request...E" + request);
        Response of2 = Response.of((Message) request);
        int code = request.getCode();
        if (code == 707 || code == 708) {
            sendMessage(request);
        } else {
            switch (code) {
                case Message.PROCESS_DATA /*701*/:
                    List list = (List) Optional.ofNullable(request.getInputBuffer()).map(new d(0, this)).orElseGet(new C0720a(1));
                    SLog.d(str, "input-buffers[#" + list.size() + "]: " + list);
                    List list2 = (List) Optional.ofNullable(request.getOutputBuffer()).map(new g(1)).orElseGet(new C0720a(1));
                    SLog.d(str, "output-buffers[#" + list2.size() + "]: " + list2);
                    try {
                        of2.join(run((List<MediaBuffer>) list, (List<MediaBuffer>) list2));
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        of2 = Response.of(-2, e);
                        break;
                    }
                case Message.PAUSE_GRAPH /*702*/:
                    this.mediaFilterGraph.pause();
                    break;
                case Message.RESUME_GRAPH /*703*/:
                    this.mediaFilterGraph.resume();
                    break;
                default:
                    if (request.isCustomRequest()) {
                        sendMessage(request);
                        break;
                    } else {
                        throw new IllegalArgumentException("unknown request: " + request.getCode());
                    }
            }
        }
        String str2 = TAG;
        SLog.d(str2, "request...X: " + of2);
        return of2;
    }

    public Response run(List<MediaBuffer> list, List<MediaBuffer> list2) {
        long currentTimeMillis = System.currentTimeMillis();
        list.forEach(new c(this, 1));
        MediaController.OnEventListener onEventListener = this.eventListener;
        if (onEventListener != null) {
            onEventListener.onEvent(Event.of(101, (Map<String, Object>) new HashMap<String, Object>(list, currentTimeMillis) {
                final /* synthetic */ long val$beginTs;
                final /* synthetic */ List val$inBuffers;

                {
                    this.val$inBuffers = r3;
                    this.val$beginTs = r4;
                    put("id", Integer.valueOf(MediaFilterController.this.id));
                    put("contents-list", r3.stream().map(new g(0)).collect(Collectors.toList()));
                    put("timestampMs", Long.valueOf(r4));
                }

                /* access modifiers changed from: private */
                public static /* synthetic */ Integer lambda$new$0(MediaBuffer mediaBuffer) {
                    Integer num = (Integer) mediaBuffer.getExtra("media-id");
                    num.intValue();
                    return num;
                }
            }));
        }
        if (!waitIfGraphAbsent(5, TimeUnit.SECONDS)) {
            return Response.of(-1);
        }
        Response run = this.mediaFilterGraph.run(list, list2);
        long currentTimeMillis2 = System.currentTimeMillis();
        MediaController.OnEventListener onEventListener2 = this.eventListener;
        if (onEventListener2 != null) {
            onEventListener2.onEvent((Event) Event.of(102, "timestampMs", Long.valueOf(currentTimeMillis2)).put("id", Integer.valueOf(this.id)));
        }
        String str = TAG;
        SLog.d(str, "run X: processing total " + (currentTimeMillis2 - currentTimeMillis) + " ms[#" + list2.size() + "]");
        if (run.isOk()) {
            run.setBuffer(list2);
            List list3 = (List) list2.stream().map(new d(1, this)).collect(Collectors.toList());
            SLog.d(str, "buffer-list[" + list3.size() + "]: " + Arrays.toString(list3.toArray()));
            list2.clear();
            list2.addAll(list3);
        }
        return run;
    }

    public void sendMessage(Message message) {
        String str = TAG;
        SLog.d(str, "sendMessage...E: " + message);
        a aVar = new a(this, message, 0);
        if (this.messagePublisher == null) {
            SLog.d(str, "graph is not created yet. add message to waiting task");
            this.conservedMessageTasks.add(aVar);
        } else if (waitIfGraphAbsent(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            aVar.get();
        }
        SLog.d(str, "sendMessage...X");
    }

    public void sendMessage(int i2) {
        sendMessage(i2, new b(1));
    }

    public void sendMessage(int i2, Consumer<Message> consumer) {
        e eVar = new e(this, i2, consumer, 0);
        if (this.messagePublisher == null) {
            SLog.d(TAG, "graph is not created yet. add message to waiting task");
            this.conservedMessageTasks.add(eVar);
        } else if (waitIfGraphAbsent(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            eVar.get();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendMessage$6(Message message) {
    }
}
