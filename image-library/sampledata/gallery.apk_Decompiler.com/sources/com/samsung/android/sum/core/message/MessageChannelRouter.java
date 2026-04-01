package com.samsung.android.sum.core.message;

import Ad.C0720a;
import L5.b;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MessageChannelRouter {
    /* access modifiers changed from: private */
    public static final String TAG = Def.tagOf((Class<?>) MessageChannelRouter.class);
    private final List<MessageChannel> errorListener = new LinkedList();
    private final List<MessageChannel> eventListener = new LinkedList();
    private final Map<Integer, List<MessageChannel>> messageSubscribers = new HashMap();
    private ReplayMessageChannel replayChannel;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ReplayMessageChannel extends BlockingMessageChannel {
        public ReplayMessageChannel(int i2) {
            super("", i2);
        }

        public int drainTo(List<Message> list) {
            return this.queue.drainTo(list);
        }

        public void send(Message message) {
            String access$000 = MessageChannelRouter.TAG;
            SLog.d(access$000, "send replay message: " + message.getCode());
            if (!this.queue.offer(message)) {
                this.queue.poll();
                Def.check(this.queue.offer(message), "fail to send message as replay", new Object[0]);
            }
        }
    }

    public MessageChannelRouter() {
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addMessageSubscriber$1(Message message) {
        return this.messageSubscribers.containsKey(Integer.valueOf(message.getCode()));
    }

    public void addMessageSubscriber(MessageSubscriber messageSubscriber) {
        SLog.d(TAG, "addMessageSubscriber");
        for (Integer num : messageSubscriber.getSubscribeMessages()) {
            if (Message.isError(num.intValue()) || num.intValue() == 1003) {
                this.errorListener.add(messageSubscriber.getMessageChannel());
            } else if (num.intValue() == 1000) {
                this.eventListener.add(messageSubscriber.getMessageChannel());
            } else {
                if (!this.messageSubscribers.containsKey(num)) {
                    this.messageSubscribers.put(num, new LinkedList());
                }
                this.messageSubscribers.get(num).add(messageSubscriber.getMessageChannel());
            }
        }
        if (this.replayChannel != null) {
            ArrayList arrayList = new ArrayList();
            this.replayChannel.drainTo(arrayList);
            Map map = (Map) arrayList.stream().collect(Collectors.partitioningBy(new e(1, this)));
            Boolean bool = Boolean.FALSE;
            if (map.containsKey(bool)) {
                this.replayChannel.queue.addAll((Collection) map.get(bool));
            }
            for (Message message : (List) Optional.ofNullable((List) map.get(Boolean.TRUE)).orElseGet(new C0720a(1))) {
                List list = this.messageSubscribers.get(Integer.valueOf(message.getCode()));
                if (list != null) {
                    list.forEach(new g(1, message));
                }
            }
        }
    }

    public MessagePublisher newMessagePublisher() {
        return new MessagePublisher(new a(1, this));
    }

    public List<MessageChannel> queryMessageChannel(int i2) {
        ReplayMessageChannel replayMessageChannel;
        String str = TAG;
        SLog.d(str, "queryMessageChannel: code=" + i2);
        if (Message.isError(i2)) {
            return this.errorListener;
        }
        List<MessageChannel> list = (List) Stream.concat((Stream) Optional.ofNullable(this.messageSubscribers.get(Integer.valueOf(i2))).map(new b(25)).orElseGet(new C0720a(17)), this.eventListener.stream()).collect(Collectors.toList());
        SLog.d(str, "messageChannels: " + Arrays.toString(list.toArray()));
        if (list.isEmpty() && (replayMessageChannel = this.replayChannel) != null) {
            list.add(replayMessageChannel);
        }
        return list;
    }

    public void removeMessageSubscriber(MessageSubscriber messageSubscriber) {
        this.messageSubscribers.forEach(new b(2, messageSubscriber));
    }

    public MessageChannelRouter(int i2) {
        this.replayChannel = new ReplayMessageChannel(i2);
    }
}
