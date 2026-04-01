package com.samsung.android.sum.core.message;

import A.a;
import android.os.Build;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Parcelable;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sum.core.DebugUtils;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.C0923a;
import com.samsung.android.sum.core.buffer.GenericMediaBuffer;
import i.C0212a;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Message {
    public static final int BLOCK_DONE = 106;
    public static final int BLOCK_START = 105;
    public static final int CACHE_DATA = 1;
    public static final int CODEC_NUM_EXTRA_FRAMES = 205;
    public static final int CODEC_OUTPUT_FORMAT_CHANGED = 203;
    public static final int CONTENT_DONE = 110;
    public static final int CONTENT_START = 109;
    public static final int CREATE_GRAPH = 700;
    public static final int CREATE_MEDIAFILTER_CONTROLLER = 705;
    public static final int END_OF_STREAM = 206;
    public static final int ERROR = 1003;
    public static final int ERROR_DEAD_OBJECT = -5;
    public static final int ERROR_DISABLED = -8;
    public static final int ERROR_FAIL_EXECUTION = -6;
    public static final int ERROR_INVALID_STATE = -7;
    public static final int ERROR_MALFORMED = -2;
    public static final int ERROR_SERVICE_DEAD = -4;
    public static final int ERROR_TIMEOUT = -1;
    public static final int ERROR_UNSUPPORTED = -3;
    public static final int EVENT = 1000;
    public static final int FLUSH_DATA = 4;
    public static final int FORCE_STOP = 711;
    public static final int FRAME_DONE = 108;
    public static final int FRAME_START = 107;
    public static final int GLOBAL_FINISH = 102;
    public static final int GLOBAL_START = 101;
    public static final String KEY_BLOCK_ID = "block-id";
    public static final String KEY_CACHE_ID = "cache-id";
    public static final String KEY_CAPTURE_TIME_US = "capture-time-us";
    @Deprecated
    public static final String KEY_CONTENTS_ID = "media-id";
    public static final String KEY_CONTROLLER_ID = "controller-id";
    public static final String KEY_DISPLAY_NAME = "display-name";
    public static final String KEY_DURATION_MS = "duration";
    public static final String KEY_END_TIME_MS = "end-time-ms";
    public static final String KEY_END_TIME_US = "end-time-us";
    public static final String KEY_EVENT_NOTI = "event-noti";
    public static final String KEY_EXTRA_CONFIG_ID = "config-id";
    public static final String KEY_FILE_DESCRIPTOR = "file-descriptor";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_ID = "id";
    public static final String KEY_IN_BUFFER = "input-buffer";
    public static final String KEY_IN_FILE = "input-file";
    public static final String KEY_MEDIA_ID = "media-id";
    public static final String KEY_MEDIA_TYPE = "media-type";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_NUM_BLOCKS = "num-blocks";
    public static final String KEY_NUM_UNITS = "num-units";
    public static final String KEY_ONE_WAY = "one-way";
    public static final String KEY_OUT_BUFFER = "output-buffer";
    public static final String KEY_OUT_EXTRA_FILE = "output-extra-file";
    public static final String KEY_OUT_FILE = "output-file";
    public static final String KEY_POSITION = "position";
    public static final String KEY_PROCESSED_FRAMES = "number-of-frames";
    public static final String KEY_ROTATION = "rotation-degrees";
    public static final String KEY_START_TIME_MS = "start-time-ms";
    public static final String KEY_START_TIME_US = "start-time-us";
    public static final String KEY_STATUS = "status-code";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_TIMESTAMP_US = "timestampUs";
    public static final String KEY_UNIT_DESCRIPTION = "unit-description";
    public static final String KEY_UNIT_ID = "unit-id";
    public static final String KEY_WHOLE_FRAMES = "whole-frames";
    public static final String KEY_WIDTH = "width";
    public static final int MEDIA_CONFIG_DATA = 201;
    public static final int MF_PREPARE_BEG = 111;
    public static final int MF_PREPARE_END = 112;
    public static final int MF_RELEASE_BEG = 115;
    public static final int MF_RELEASE_END = 116;
    public static final int MF_RUN_BEG = 113;
    public static final int MF_RUN_END = 114;
    public static final int MSG_FLAG_IGNORE_POST = 1;
    public static final int MUXER_CONFIGURE_DATA = 204;
    public static final int OK = 0;
    public static final int PAUSE_GRAPH = 702;
    public static final int PROCESS_DATA = 701;
    public static final int QUERY_ENCODE_SHAPE = 712;
    public static final int RELEASE_GRAPH = 704;
    public static final int RELEASE_MEDIAFILTER_CONTROLLER = 706;
    public static final int REQUEST = 1001;
    public static final int RESPONSE = 1002;
    public static final int RESUME_GRAPH = 703;
    public static final int SERVICE_RECONNECTED = 301;
    public static final int START = 707;
    public static final int STARTED = 103;
    public static final int START_DONE = 709;
    public static final int STOP = 708;
    public static final int STOPPED = 104;
    public static final int STOP_DONE = 710;
    private static final String TAG = Def.tagOf((Class<?>) Message.class);
    public static final int TRACE_DATA = 2;
    public static final int VIDEO_FORMAT = 3;
    public static final int WARN = 1004;
    public static final int WARN_CANCELED = 502;
    public static final int WARN_FILTER_OUT_CONTENT = 501;
    static final int _CUSTOM_ERROR_TYPE_BASE_ = -900;
    static final int _CUSTOM_EVENT_TYPE_BASE_ = 400;
    static final int _CUSTOM_REQUEST_TYPE_BASE_ = 900;
    static final int _CUSTOM_WARN_TYPE_BASE_ = 600;
    static final int _END_OF_ERROR_TYPE_ = -999;
    static final int _END_OF_EVENT_TYPE_ = 499;
    private static final int _END_OF_MESSAGE_TYPE_ = 1099;
    static final int _END_OF_REQUEST_TYPE_ = 999;
    static final int _END_OF_WARN_TYPE_ = 699;
    static final int _START_OF_ERROR_TYPE_ = -1;
    static final int _START_OF_EVENT_TYPE_ = 0;
    private static final int _START_OF_MESSAGE_TYPE_ = 1000;
    static final int _START_OF_REQUEST_TYPE_ = 700;
    static final int _START_OF_WARN_TYPE_ = 500;
    protected BundledDataHandler bundledDataHandler;
    protected int code;
    protected Map<String, Object> data;
    protected Exception exception;
    protected int extra;
    protected int flags;
    private WeakReference<MessagePublisher> messagePublisher;
    protected Runnable onPostListener;
    protected Map<String, Parcelable> parcelableData;
    protected Messenger replyTo;
    private boolean requestToReply;
    private Consumer<Message> responseListener;
    protected WeakReference<Message> source;
    protected int type;

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BundledDataHandler {
        void accept(Bundle bundle);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface MessageType {
    }

    public Message() {
        if (DebugUtils.TRACE_MESSAGE) {
            this.data = new ConcurrentHashMap();
            this.parcelableData = new ConcurrentHashMap();
            return;
        }
        this.data = new HashMap();
        this.parcelableData = new HashMap();
    }

    private <T extends Parcelable> T getParcelableFromBundle(Bundle bundle, String str, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 33) {
            return (Parcelable) bundle.getParcelable(str, cls);
        }
        return bundle.getParcelable(str);
    }

    private <T extends Serializable> T getSerializableFromBundle(Bundle bundle, String str, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 33) {
            return bundle.getSerializable(str, cls);
        }
        return bundle.getSerializable(str);
    }

    private boolean isValidCode(int i2, int i7) {
        int typeOf = typeOf(i7);
        if (i2 == 1000) {
            return Stream.of(new Integer[]{1000, 1004, 1003}).anyMatch(new f(typeOf, 1));
        }
        if (i2 == 1002) {
            return Stream.of(new Integer[]{1002, 1000, 1001, 1003, 1004}).anyMatch(new f(typeOf, 0));
        }
        if (i2 == typeOf) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$containFlags$8(int i2) {
        if ((this.flags & i2) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$containsAll$4(String str) {
        return this.data.containsKey(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$containsAny$5(String str) {
        return this.data.containsKey(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isValidCode$2(int i2, Integer num) {
        if (num.intValue() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isValidCode$3(int i2, Integer num) {
        if (num.intValue() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Bundle bundle, String str) {
        if ("data".equals(str)) {
            Map<String, Object> map = (Map) getSerializableFromBundle(bundle, str, HashMap.class);
            if (map != null) {
                if (DebugUtils.TRACE_MESSAGE) {
                    map = new ConcurrentHashMap<>(map);
                }
                this.data = map;
            }
        } else if ("flags".equals(str)) {
            this.flags = bundle.getInt("flags");
        } else if ("exception".equals(str)) {
            this.exception = (Exception) getSerializableFromBundle(bundle, "exception", Exception.class);
        } else if (Arrays.stream(getIntrinsicAttributeNames()).noneMatch(new e(0, str))) {
            this.parcelableData.put(str, getParcelableFromBundle(bundle, str, Parcelable.class));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$post$7(MessageChannel messageChannel) {
        try {
            String str = TAG;
            SLog.d(str, "post: " + this.code + " to channel[" + messageChannel.getId() + "]: " + messageChannel);
            messageChannel.send(this);
        } catch (CancellationException unused) {
            SLog.d(TAG, "message channel already closed");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$put$6(String str, Object obj) {
        if (str == null || obj == null) {
            String str2 = TAG;
            SLog.w(str2, "ignore invalid key=" + str + ", value=" + obj);
        } else if (obj instanceof Parcelable) {
            this.parcelableData.put(str, (Parcelable) obj);
        } else {
            this.data.put(str, obj);
        }
    }

    public static int makeCustomError(int i2) {
        boolean z;
        if (i2 <= 0 || i2 >= 100) {
            z = false;
        } else {
            z = true;
        }
        Def.check(z, "custom-message-range should be in [0, 100)", new Object[0]);
        return -900 - i2;
    }

    public static int makeCustomEvent(int i2) {
        boolean z;
        if (i2 <= 0 || i2 >= 100) {
            z = false;
        } else {
            z = true;
        }
        Def.check(z, "custom-message-range should be in [0, 100)", new Object[0]);
        return i2 + 400;
    }

    public static int makeCustomRequest(int i2) {
        boolean z;
        if (i2 <= 0 || i2 >= 100) {
            z = false;
        } else {
            z = true;
        }
        Def.check(z, "custom-message-range should be in [0, 100)", new Object[0]);
        return i2 + _CUSTOM_REQUEST_TYPE_BASE_;
    }

    public static int makeCustomWarn(int i2) {
        boolean z;
        if (i2 <= 0 || i2 >= 100) {
            z = false;
        } else {
            z = true;
        }
        Def.check(z, "custom-message-range should be in [0, 100)", new Object[0]);
        return i2 + _CUSTOM_WARN_TYPE_BASE_;
    }

    public static int typeOf(int i2) {
        if (isError(i2)) {
            return 1003;
        }
        if (isWarn(i2)) {
            return 1004;
        }
        if (Def.isRangeIn(i2, 0, _END_OF_EVENT_TYPE_)) {
            return 1000;
        }
        if (Def.isRangeIn(i2, 700, _END_OF_REQUEST_TYPE_)) {
            return 1001;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "invalid message code: "));
    }

    public boolean containFlags(int... iArr) {
        return Arrays.stream(iArr).allMatch(new c(this));
    }

    public boolean contains(String str) {
        return this.data.containsKey(str);
    }

    public boolean containsAll(String... strArr) {
        return Arrays.stream(strArr).allMatch(new d(this, 0));
    }

    public boolean containsAny(String... strArr) {
        return Arrays.stream(strArr).anyMatch(new d(this, 1));
    }

    public String contentToString() {
        return contentToString(this, (Supplier<String>) null);
    }

    public Map<String, Object> get() {
        return (Map) Stream.concat(this.data.entrySet().stream(), this.parcelableData.entrySet().stream()).collect(Collectors.toMap(new C0923a(17), new C0923a(18)));
    }

    public BundledDataHandler getBundledDataHandler() {
        return this.bundledDataHandler;
    }

    public int getCode() {
        return this.code;
    }

    public Exception getException() {
        return this.exception;
    }

    public String[] getIntrinsicAttributeNames() {
        return new String[0];
    }

    public Messenger getResponseReceiver() {
        return this.replyTo;
    }

    public boolean isCustomError() {
        if (_END_OF_ERROR_TYPE_ > getCode() || getCode() > _CUSTOM_ERROR_TYPE_BASE_) {
            return false;
        }
        return true;
    }

    public boolean isCustomEvent() {
        if (400 > getCode() || getCode() > _END_OF_WARN_TYPE_) {
            return false;
        }
        return true;
    }

    public boolean isCustomRequest() {
        if (_CUSTOM_REQUEST_TYPE_BASE_ > getCode() || getCode() > _END_OF_REQUEST_TYPE_) {
            return false;
        }
        return true;
    }

    public boolean isCustomWarn() {
        if (_CUSTOM_WARN_TYPE_BASE_ > getCode() || getCode() > _END_OF_WARN_TYPE_) {
            return false;
        }
        return true;
    }

    public boolean isError() {
        return this.type == 1003 || isError(this.code) || ((Integer) get(KEY_STATUS, -1)).intValue() == 1003;
    }

    public boolean isErrorThen(Consumer<Integer> consumer) {
        boolean isError = isError();
        if (isError) {
            consumer.accept(Integer.valueOf(this.code));
        }
        return isError;
    }

    public boolean isOk() {
        return isOk(this.code) || ((Integer) get(KEY_STATUS, -1)).intValue() == 0;
    }

    public boolean isRequestToReply() {
        return this.requestToReply;
    }

    public boolean isWarn() {
        return this.type == 1004 || isWarn(this.code) || ((Integer) get(KEY_STATUS, -1)).intValue() == 1004;
    }

    public Message post() {
        MessagePublisher messagePublisher2 = this.messagePublisher.get();
        if (messagePublisher2 != null) {
            messagePublisher2.getChannels(this.code).forEach(new g(0, this));
        }
        Runnable runnable = this.onPostListener;
        if (runnable != null) {
            runnable.run();
        }
        return this;
    }

    public Message put(Map<String, Object> map) {
        map.forEach(new b(0, this));
        return this;
    }

    public <T> T remove(String str) {
        if (this.parcelableData.containsKey(str)) {
            return this.parcelableData.remove(str);
        }
        return this.data.remove(str);
    }

    public void reply(String str, Object obj) {
        Message message;
        Consumer<Message> consumer = this.responseListener;
        if (consumer != null) {
            consumer.accept(new Message(1002, 0).put(str, obj));
            return;
        }
        WeakReference<Message> weakReference = this.source;
        if (weakReference != null && (message = weakReference.get()) != null) {
            String str2 = TAG;
            SLog.d(str2, "no response-listener given, instead set (" + str + ArcCommonLog.TAG_COMMA + obj + ") to source msg=" + message);
            message.put(str, obj);
        }
    }

    public Message setBundledDataHandler(BundledDataHandler bundledDataHandler2) {
        this.bundledDataHandler = bundledDataHandler2;
        return this;
    }

    public Message setException(Exception exc) {
        this.exception = exc;
        return this;
    }

    public Message setFlags(int... iArr) {
        for (int i2 : iArr) {
            this.flags = i2 | this.flags;
        }
        return this;
    }

    public void setOnPostListener(Runnable runnable) {
        this.onPostListener = runnable;
    }

    public Message setPublisher(MessagePublisher messagePublisher2) {
        this.messagePublisher = new WeakReference<>(messagePublisher2);
        return this;
    }

    public Message setResponseReceiver(Messenger messenger) {
        this.replyTo = messenger;
        return this;
    }

    public Message then(Consumer<Message> consumer) {
        if (this.messagePublisher.get() != null) {
            this.responseListener = consumer;
            this.requestToReply = true;
        }
        return this;
    }

    public android.os.Message toAndroidMessage() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) this.data);
        this.parcelableData.forEach(new b(1, bundle));
        Exception exc = this.exception;
        if (exc != null) {
            bundle.putSerializable("exception", exc);
        }
        bundle.putInt("flags", this.flags);
        android.os.Message message = new android.os.Message();
        message.what = this.code;
        message.arg1 = this.type;
        message.arg2 = this.extra;
        message.replyTo = this.replyTo;
        message.setData(bundle);
        BundledDataHandler bundledDataHandler2 = this.bundledDataHandler;
        if (bundledDataHandler2 != null) {
            bundledDataHandler2.accept(bundle);
        }
        return message;
    }

    public String toString() {
        return contentToString();
    }

    public static boolean isError(int i2) {
        return Def.isRangeIn(i2, _END_OF_ERROR_TYPE_, -1);
    }

    public static boolean isOk(int i2) {
        return Def.isRangeIn(i2, 0, _END_OF_EVENT_TYPE_) || Def.isRangeIn(i2, 700, _END_OF_REQUEST_TYPE_);
    }

    public static boolean isWarn(int i2) {
        return Def.isRangeIn(i2, 500, _END_OF_WARN_TYPE_);
    }

    public String contentToString(Object obj, Supplier<String> supplier) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Def.tagOf(Optional.ofNullable(obj).orElse("")));
        String p6 = C0212a.p(new StringBuilder("["), Def.contentToString("type=" + this.type, "code=" + this.code, "extra=" + this.extra, "flags=" + Integer.toHexString(this.flags), "exception=" + this.exception, "bundledDataHandler=" + this.bundledDataHandler), "]");
        StringBuilder sb3 = new StringBuilder("data=");
        boolean z = DebugUtils.TRACE_MESSAGE;
        String str = "[trace off]";
        sb3.append(z ? this.data.toString() : str);
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder("parcelable-data=");
        if (z) {
            str = this.parcelableData.toString();
        }
        sb5.append(str);
        sb2.append(Def.contentToStringln("\t", p6, sb4, sb5.toString(), (String) Optional.ofNullable(supplier).map(new C0923a(16)).orElse("")));
        return sb2.toString();
    }

    public Message put(String str, Object obj) {
        if (obj instanceof Parcelable) {
            this.parcelableData.put(str, (Parcelable) obj);
            return this;
        }
        this.data.put(str, obj);
        return this;
    }

    public <T> T get(String str) {
        if (this.parcelableData.containsKey(str)) {
            return this.parcelableData.get(str);
        }
        return this.data.get(str);
    }

    public <T> T get(String str, T t) {
        if (this.parcelableData.containsKey(str)) {
            return this.parcelableData.getOrDefault(str, (Parcelable) t);
        }
        return this.data.getOrDefault(str, t);
    }

    public Message(int i2) {
        this(typeOf(i2), i2);
    }

    public void reply(Map<String, Object> map) {
        Consumer<Message> consumer = this.responseListener;
        if (consumer != null) {
            consumer.accept(new Message(1002, 0).put(map));
            return;
        }
        Message message = this.source.get();
        if (message != null) {
            String str = TAG;
            SLog.d(str, "no response-listener given, instead set (" + map + ") to source msg=" + message);
            message.put(map);
        }
    }

    public Message(int i2, int i7) {
        this();
        Def.require(isValidCode(i2, i7), a.d(i7, i2, "invalid code(", ") for message(", ")"), new Object[0]);
        this.type = i2;
        this.code = i7;
    }

    public Message(Message message) {
        this.type = message.type;
        this.code = message.code;
        this.extra = message.extra;
        this.flags = message.flags;
        this.replyTo = message.replyTo;
        this.data = message.data;
        this.parcelableData = message.parcelableData;
        this.exception = message.exception;
        this.bundledDataHandler = message.bundledDataHandler;
        this.source = new WeakReference<>(message);
        this.onPostListener = message.onPostListener;
        this.requestToReply = message.requestToReply;
        this.messagePublisher = message.messagePublisher;
        this.responseListener = message.responseListener;
    }

    public Message(android.os.Message message) {
        this();
        Bundle data2 = message.getData();
        data2.setClassLoader(GenericMediaBuffer.class.getClassLoader());
        data2.keySet().forEach(new e(11, this, data2));
        Exception exc = (Exception) data2.getSerializable("exception");
        if (exc != null) {
            this.exception = exc;
        }
        this.code = message.what;
        this.type = message.arg1;
        this.extra = message.arg2;
        this.replyTo = message.replyTo;
    }
}
