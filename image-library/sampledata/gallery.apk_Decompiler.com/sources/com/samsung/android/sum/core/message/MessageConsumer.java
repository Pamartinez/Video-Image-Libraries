package com.samsung.android.sum.core.message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MessageConsumer {
    int[] getMessagesToReceive() {
        return null;
    }

    boolean onMessageReceived(Message message) {
        return false;
    }
}
