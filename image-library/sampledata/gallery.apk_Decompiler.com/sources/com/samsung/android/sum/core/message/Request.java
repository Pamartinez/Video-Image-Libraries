package com.samsung.android.sum.core.message;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import c4.C0438h;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.filter.n;
import com.samsung.android.sum.core.message.Response;
import i.C0212a;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Request extends Message {
    private static final String KEY_INPUT_BUFFER_LIST = "input-buffer-list";
    private static final String KEY_OUTPUT_BUFFER_LIST = "output-buffer-list";
    private static final String TAG = Def.tagOf((Class<?>) Request.class);
    private List<MediaBuffer> inputBufferList;
    private boolean isNotifyEvent = false;
    private boolean isOneWay = false;
    private Consumer<Map<String, Object>> onReplyListener;
    private List<MediaBuffer> outputBufferList;
    private Messenger receiver;

    private Request(Message message) {
        super(message);
        Bundle data = message.getData();
        Parcelable[] parcelableArray = data.getParcelableArray(KEY_INPUT_BUFFER_LIST);
        if (parcelableArray != null) {
            this.inputBufferList = (List) Arrays.stream(parcelableArray).map(new n(3)).collect(Collectors.toList());
        }
        Parcelable[] parcelableArray2 = data.getParcelableArray(KEY_OUTPUT_BUFFER_LIST);
        if (parcelableArray2 != null) {
            this.outputBufferList = (List) Arrays.stream(parcelableArray2).map(new n(4)).collect(Collectors.toList());
        }
        this.isOneWay = data.getBoolean(Message.KEY_ONE_WAY, false);
        this.isNotifyEvent = data.getBoolean(Message.KEY_EVENT_NOTI, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$contentToString$5() {
        return C0212a.p(new StringBuilder("["), Def.contentToString("isOneWay=" + this.isOneWay, "inputBufferList=" + this.inputBufferList, "outputBufferList=" + this.outputBufferList, "receiver=" + this.receiver, "onReplyListener=" + this.onReplyListener), "]");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$getInputBuffer$2(List list) {
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() != 1) {
            return MediaBuffer.newGroupAlloc().setBuffers(list).allocate();
        }
        ((MediaBuffer) list.get(0)).setExtra("singular-buffer", Boolean.TRUE);
        return (MediaBuffer) list.get(0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$getOutputBuffer$3(List list) {
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            return (MediaBuffer) list.get(0);
        }
        return MediaBuffer.newGroupAlloc().setBuffers(list).allocate();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$new$0(Parcelable parcelable) {
        return (MediaBuffer) parcelable;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$new$1(Parcelable parcelable) {
        return (MediaBuffer) parcelable;
    }

    public static Request of(@RequestType int i2) {
        return new Request(i2);
    }

    public Request asEventNoti() {
        this.isNotifyEvent = true;
        return this;
    }

    public Request asOneWay() {
        this.isOneWay = true;
        return this;
    }

    public String contentToString() {
        return super.contentToString(this, new l(this, 0));
    }

    public ContentValues getContentValues() {
        return (ContentValues) Optional.ofNullable(getContentValuesList()).flatMap(new n(7)).orElse((Object) null);
    }

    public List<ContentValues> getContentValuesList() {
        return (List) get("content-values");
    }

    public MediaBuffer getInputBuffer() {
        return (MediaBuffer) Optional.ofNullable(this.inputBufferList).map(new n(5)).orElse((Object) null);
    }

    public String[] getIntrinsicAttributeNames() {
        return new String[]{Message.KEY_ONE_WAY, Message.KEY_EVENT_NOTI, KEY_INPUT_BUFFER_LIST, KEY_OUTPUT_BUFFER_LIST};
    }

    public Consumer<Map<String, Object>> getOnReplyListener() {
        return this.onReplyListener;
    }

    public MediaBuffer getOutputBuffer() {
        return (MediaBuffer) Optional.ofNullable(this.outputBufferList).map(new n(6)).orElse((Object) null);
    }

    public Messenger getReceiver() {
        return this.receiver;
    }

    public boolean isEventNoti() {
        return this.isNotifyEvent;
    }

    public boolean isOneWay() {
        return this.isOneWay;
    }

    public Message post() {
        if (this.receiver == null) {
            String str = TAG;
            SLog.d(str, "no receiver object given for code " + getCode() + ", skip to send");
            return this;
        }
        try {
            String str2 = TAG;
            SLog.d(str2, "send request: " + this);
            this.receiver.send(toAndroidMessage());
            List<MediaBuffer> list = this.inputBufferList;
            if (list != null) {
                list.forEach(new C0438h(13));
            }
            List<MediaBuffer> list2 = this.outputBufferList;
            if (list2 != null) {
                list2.forEach(new C0438h(13));
            }
            this.inputBufferList = null;
            this.outputBufferList = null;
            Runnable runnable = this.onPostListener;
            if (runnable != null) {
                runnable.run();
                return this;
            }
        } catch (RemoteException e) {
            String str3 = TAG;
            SLog.w(str3, "fail to send request: " + e.getMessage());
            List<MediaBuffer> list3 = this.inputBufferList;
            if (list3 != null) {
                list3.forEach(new C0438h(13));
            }
            List<MediaBuffer> list4 = this.outputBufferList;
            if (list4 != null) {
                list4.forEach(new C0438h(13));
            }
            this.inputBufferList = null;
            this.outputBufferList = null;
            Runnable runnable2 = this.onPostListener;
            if (runnable2 != null) {
                runnable2.run();
            }
        } catch (Throwable th) {
            List<MediaBuffer> list5 = this.inputBufferList;
            if (list5 != null) {
                list5.forEach(new C0438h(13));
            }
            List<MediaBuffer> list6 = this.outputBufferList;
            if (list6 != null) {
                list6.forEach(new C0438h(13));
            }
            this.inputBufferList = null;
            this.outputBufferList = null;
            Runnable runnable3 = this.onPostListener;
            if (runnable3 != null) {
                runnable3.run();
            }
            throw th;
        }
        return this;
    }

    public Request setInputBuffer(MediaBuffer... mediaBufferArr) {
        this.inputBufferList = Arrays.asList(mediaBufferArr);
        return this;
    }

    public void setOnReplyListener(Consumer<Map<String, Object>> consumer) {
        this.onReplyListener = consumer;
    }

    public Request setOutputBuffer(MediaBuffer... mediaBufferArr) {
        this.outputBufferList = Arrays.asList(mediaBufferArr);
        return this;
    }

    public Request setReceiver(Messenger messenger) {
        this.receiver = messenger;
        return this;
    }

    public Message then(Consumer<Message> consumer) {
        this.extra = Response.ListenerManager.getInstance().register(consumer);
        return this;
    }

    public Message toAndroidMessage() {
        Message androidMessage = super.toAndroidMessage();
        Bundle data = androidMessage.getData();
        List<MediaBuffer> list = this.inputBufferList;
        if (list != null) {
            data.putParcelableArray(KEY_INPUT_BUFFER_LIST, (Parcelable[]) list.toArray(new MediaBuffer[0]));
        }
        List<MediaBuffer> list2 = this.outputBufferList;
        if (list2 != null) {
            data.putParcelableArray(KEY_OUTPUT_BUFFER_LIST, (Parcelable[]) list2.toArray(new MediaBuffer[0]));
        }
        data.putBoolean(Message.KEY_ONE_WAY, this.isOneWay);
        data.putBoolean(Message.KEY_EVENT_NOTI, this.isNotifyEvent);
        return androidMessage;
    }

    public Message toMessage() {
        return new Message((Message) this);
    }

    public Request toNoResponsive() {
        return (Request) of((Message) this).asOneWay().setFlags(1);
    }

    public Request toResponsive(Messenger messenger) {
        return (Request) of((Message) this).setResponseReceiver(messenger);
    }

    public static Request of(@RequestType int i2, String str, Object obj) {
        return (Request) new Request(i2).put(str, obj);
    }

    public Request setInputBuffer(List<MediaBuffer> list) {
        this.inputBufferList = list;
        return this;
    }

    public Request setOutputBuffer(List<MediaBuffer> list) {
        this.outputBufferList = list;
        return this;
    }

    public static Request of(Message message) {
        return new Request(message);
    }

    public static Request of(Message message) {
        return new Request(message);
    }

    private Request(Message message) {
        super(message);
    }

    private Request(int i2) {
        super(1001, i2);
    }
}
