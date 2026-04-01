package com.samsung.android.sum.core.message;

import android.util.Pair;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.u;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MessagePublisher {
    private static final String TAG = Def.tagOf((Class<?>) MessagePublisher.class);
    private final Function<Integer, List<MessageChannel>> messageChannelQuery;
    private final MessageProducer messageProducer = new MessageProducerImpl(this);
    private String name;

    public MessagePublisher(Function<Integer, List<MessageChannel>> function) {
        this.messageChannelQuery = function;
    }

    public List<MessageChannel> getChannels(int i2) {
        return this.messageChannelQuery.apply(Integer.valueOf(i2));
    }

    public Message getMessage(int i2) {
        return new Message(i2).setPublisher(this);
    }

    public MessageProducer getMessageProducer() {
        return this.messageProducer;
    }

    public String getName() {
        return this.name;
    }

    public void sendMessage(Message message) {
        message.setPublisher(this).post();
    }

    public void setName(String str) {
        this.name = str;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MessageProducerImpl implements MessageProducer {
        private final WeakReference<MessagePublisher> weakProducer;

        public MessageProducerImpl(MessagePublisher messagePublisher) {
            this.weakProducer = new WeakReference<>(messagePublisher);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Message lambda$newMessage$2(int i2, Object obj, MessagePublisher messagePublisher) {
            return messagePublisher.getMessage(i2, new HashMap<String, Object>(obj) {
                final /* synthetic */ Object val$data;

                {
                    this.val$data = r2;
                    put("data", r2);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Message lambda$newMessage$3(int i2, String str, Object obj, MessagePublisher messagePublisher) {
            return messagePublisher.getMessage(i2, new HashMap<String, Object>(str, obj) {
                final /* synthetic */ Object val$data;
                final /* synthetic */ String val$key;

                {
                    this.val$key = r2;
                    this.val$data = r3;
                    put(r2, r3);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Message lambda$newMessage$4(int i2, Pair[] pairArr, MessagePublisher messagePublisher) {
            return messagePublisher.getMessage(i2, new HashMap<String, Object>(pairArr) {
                final /* synthetic */ Pair[] val$keyValues;

                {
                    this.val$keyValues = r3;
                    Arrays.asList(r3).forEach(new g(4, this));
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$new$0(Pair pair) {
                    put((String) pair.first, pair.second);
                }
            });
        }

        public Message newMessage(int i2) {
            return (Message) Optional.ofNullable(this.weakProducer.get()).map(new i(i2)).orElseThrow(new u(4));
        }

        public Message newMessage(int i2, Map<String, Object> map) {
            return (Message) Optional.ofNullable(this.weakProducer.get()).map(new h(i2, map)).orElseThrow(new u(4));
        }

        public Message newMessage(int i2, Object obj) {
            return (Message) Optional.ofNullable(this.weakProducer.get()).map(new j(this, i2, obj, 1)).orElseThrow(new u(4));
        }

        public Message newMessage(int i2, String str, Object obj) {
            return (Message) Optional.ofNullable(this.weakProducer.get()).map(new k(this, i2, str, obj)).orElseThrow(new u(4));
        }

        public Message newMessage(int i2, Pair<String, Object>... pairArr) {
            return (Message) Optional.ofNullable(this.weakProducer.get()).map(new j(this, i2, pairArr, 0)).orElseThrow(new u(4));
        }
    }

    public Message getMessage(int i2, Map<String, Object> map) {
        return new Message(i2).put(map).setPublisher(this);
    }
}
