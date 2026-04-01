package com.samsung.android.sum.core.message;

import Ad.C0720a;
import L5.b;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MessageSubscriberBase implements MessageSubscriber {
    private static final String TAG = Def.tagOf((Class<?>) MessageSubscriberBase.class);
    protected List<MessageConsumer> errorListener = new LinkedList();
    protected List<MessageConsumer> eventListener = new LinkedList();
    protected MessageChannel messageChannel;
    protected Map<Integer, List<MessageConsumer>> messageConsumers = new HashMap();

    public MessageSubscriberBase(MessageChannel messageChannel2) {
        this.messageChannel = messageChannel2;
    }

    public void addMessageConsumer(MessageConsumer messageConsumer) {
        String str = TAG;
        SLog.d(str, "addMessageConsumer: " + messageConsumer);
        int[] messagesToReceive = messageConsumer.getMessagesToReceive();
        if (messagesToReceive == null || messagesToReceive.length == 0) {
            SLog.d(str, "no consume code given");
            return;
        }
        for (int i2 : messagesToReceive) {
            if (i2 == 1003 || Message.isError(i2)) {
                this.errorListener.add(messageConsumer);
            } else if (i2 == 1000) {
                this.eventListener.add(messageConsumer);
            } else {
                if (!this.messageConsumers.containsKey(Integer.valueOf(i2))) {
                    this.messageConsumers.put(Integer.valueOf(i2), new LinkedList());
                }
                this.messageConsumers.get(Integer.valueOf(i2)).add(messageConsumer);
            }
        }
    }

    public void bindToMessageChannelRouter(MessageChannelRouter messageChannelRouter) {
        messageChannelRouter.addMessageSubscriber(this);
    }

    public MessageChannel getMessageChannel() {
        return this.messageChannel;
    }

    public Integer[] getSubscribeMessages() {
        ArrayList arrayList = new ArrayList(this.messageConsumers.keySet());
        if (!this.eventListener.isEmpty()) {
            arrayList.add(1000);
        }
        if (!this.errorListener.isEmpty()) {
            arrayList.add(1003);
        }
        return (Integer[]) arrayList.toArray(new Integer[0]);
    }

    public void onMessageReceived(Message message) {
        int code = message.getCode();
        if (Message.isError(code)) {
            this.errorListener.forEach(new g(2, message));
        } else {
            Stream.concat((Stream) Optional.ofNullable(this.messageConsumers.get(Integer.valueOf(code))).map(new b(25)).orElseGet(new C0720a(18)), this.eventListener.stream()).forEach(new g(3, message));
        }
    }

    public void release() {
        String str = TAG;
        SLog.d(str, "close message channel: " + this.messageChannel);
        this.messageChannel.cancel();
    }

    public void removeMessageConsumer(MessageConsumer messageConsumer) {
        this.messageConsumers.forEach(new b(3, messageConsumer));
    }
}
