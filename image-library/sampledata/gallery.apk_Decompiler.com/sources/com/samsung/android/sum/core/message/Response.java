package com.samsung.android.sum.core.message;

import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import c4.C0438h;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.filter.n;
import com.samsung.android.sum.core.message.Message;
import i.C0212a;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Response extends Message {
    private static final String KEY_BUFFER_LIST = "buffer-list";
    private static final String TAG = Def.tagOf((Class<?>) Response.class);
    private List<MediaBuffer> bufferList;
    private Consumer<Message> responseListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ListenerManager {
        private static volatile ListenerManager sInstance;
        private final Map<Integer, Consumer<Message>> consumerMap = new ConcurrentHashMap();
        private final AtomicInteger idGenerator = new AtomicInteger(0);

        private ListenerManager() {
        }

        public static ListenerManager getInstance() {
            if (sInstance == null) {
                synchronized (ListenerManager.class) {
                    try {
                        if (sInstance == null) {
                            sInstance = new ListenerManager();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return sInstance;
        }

        public void clear() {
            this.consumerMap.clear();
        }

        public int register(Consumer<Message> consumer) {
            int incrementAndGet = this.idGenerator.incrementAndGet();
            this.consumerMap.put(Integer.valueOf(incrementAndGet), consumer);
            return incrementAndGet;
        }

        public Consumer<Message> unRegister(int i2) {
            return this.consumerMap.remove(Integer.valueOf(i2));
        }
    }

    private Response(int i2) {
        super(1002, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$contentToString$2() {
        return C0212a.p(new StringBuilder("["), Def.contentToString("bufferList=" + this.bufferList, "responseListener=" + this.responseListener), "]");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$getBuffer$1(List list) {
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            return this.bufferList.get(0);
        }
        return MediaBuffer.newGroupAlloc().setBuffers(list).allocate();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$new$0(Parcelable parcelable) {
        return (MediaBuffer) parcelable;
    }

    public static Response of(Message message) {
        return new Response(message);
    }

    public String contentToString() {
        return super.contentToString(this, new l(this, 1));
    }

    public MediaBuffer getBuffer() {
        return (MediaBuffer) Optional.ofNullable(this.bufferList).map(new a(2, this)).orElse((Object) null);
    }

    public List<MediaBuffer> getBufferList() {
        return this.bufferList;
    }

    public String[] getIntrinsicAttributeNames() {
        return new String[]{KEY_BUFFER_LIST};
    }

    public Consumer<Message> getResponseListener() {
        return this.responseListener;
    }

    public Response join(Response response) {
        if (response.isOk()) {
            List<MediaBuffer> list = response.bufferList;
            if (list != null) {
                List<MediaBuffer> list2 = this.bufferList;
                if (list2 == null) {
                    this.bufferList = list;
                } else {
                    list2.addAll(list);
                }
            }
            this.data.putAll(response.data);
        } else {
            this.code = response.code;
            this.type = response.type;
            Exception exc = response.exception;
            if (exc != null) {
                this.exception = exc;
            }
        }
        int i2 = response.extra;
        if (i2 > 0) {
            this.extra = i2;
        }
        Messenger messenger = response.replyTo;
        if (messenger != null) {
            this.replyTo = messenger;
        }
        Consumer<Message> consumer = response.responseListener;
        if (consumer != null) {
            this.responseListener = consumer;
        }
        this.flags = response.flags | this.flags;
        return this;
    }

    public Message post() {
        if (this.replyTo == null) {
            String str = TAG;
            SLog.d(str, "no reply object given for code " + getCode() + ", skip to send");
            return this;
        } else if (containFlags(1)) {
            SLog.d(TAG, "ignore to post flag is set, skip this");
            return this;
        } else {
            try {
                String str2 = TAG;
                SLog.d(str2, "send response: " + this);
                this.replyTo.send(toAndroidMessage());
                List<MediaBuffer> list = this.bufferList;
                if (list != null) {
                    list.forEach(new C0438h(13));
                }
                this.bufferList = null;
                Runnable runnable = this.onPostListener;
                if (runnable != null) {
                    runnable.run();
                    return this;
                }
            } catch (RemoteException e) {
                String str3 = TAG;
                SLog.w(str3, "fail to send response: " + e.getMessage());
                List<MediaBuffer> list2 = this.bufferList;
                if (list2 != null) {
                    list2.forEach(new C0438h(13));
                }
                this.bufferList = null;
                Runnable runnable2 = this.onPostListener;
                if (runnable2 != null) {
                    runnable2.run();
                }
            } catch (Throwable th) {
                List<MediaBuffer> list3 = this.bufferList;
                if (list3 != null) {
                    list3.forEach(new C0438h(13));
                }
                this.bufferList = null;
                Runnable runnable3 = this.onPostListener;
                if (runnable3 != null) {
                    runnable3.run();
                }
                throw th;
            }
            return this;
        }
    }

    public Response setBuffer(MediaBuffer... mediaBufferArr) {
        this.bufferList = Arrays.asList(mediaBufferArr);
        return this;
    }

    public Message toAndroidMessage() {
        SLog.v(TAG, "toAndroidMessage");
        Message androidMessage = super.toAndroidMessage();
        if (this.bufferList != null) {
            androidMessage.getData().putParcelableArray(KEY_BUFFER_LIST, (Parcelable[]) this.bufferList.toArray(new MediaBuffer[0]));
        }
        return androidMessage;
    }

    public Message toMessage() {
        return new Message((Message) this);
    }

    private Response(Message message) {
        super(message);
        Parcelable[] parcelableArray = message.getData().getParcelableArray(KEY_BUFFER_LIST);
        if (parcelableArray != null) {
            this.bufferList = (List) Arrays.stream(parcelableArray).map(new n(8)).collect(Collectors.toList());
        }
        if (this.extra != 0) {
            this.responseListener = ListenerManager.getInstance().unRegister(this.extra);
        }
    }

    public static Response of(int i2) {
        return new Response(i2);
    }

    public Response setBuffer(List<MediaBuffer> list) {
        this.bufferList = list;
        return this;
    }

    public static Response of(int i2, String str, Object obj) {
        return (Response) new Response(i2).put(str, obj);
    }

    public static Response of(int i2, Exception exc) {
        return (Response) new Response(i2).setException(exc);
    }

    public static Response of(Message message) {
        return new Response(message);
    }

    private Response(Message message) {
        super(message);
        if (this.data == null) {
            this.data = new HashMap();
        }
        if (this.extra != 0) {
            this.responseListener = ListenerManager.getInstance().unRegister(this.extra);
        }
    }

    public Response post(Message.BundledDataHandler bundledDataHandler) {
        this.bundledDataHandler = bundledDataHandler;
        return (Response) post();
    }
}
