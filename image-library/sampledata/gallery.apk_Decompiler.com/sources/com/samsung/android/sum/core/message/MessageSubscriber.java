package com.samsung.android.sum.core.message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MessageSubscriber {
    void bindToMessageChannelRouter(MessageChannelRouter messageChannelRouter);

    MessageChannel getMessageChannel();

    Integer[] getSubscribeMessages();

    void onMessageReceived(Message message);

    void addMessageConsumer(MessageConsumer messageConsumer) {
    }

    void removeMessageConsumer(MessageConsumer messageConsumer) {
    }
}
