package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptorPair;
import com.samsung.android.sum.core.descriptor.StaplePluginDescriptor;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageProducer;
import com.samsung.android.sum.core.plugin.PluginFixture;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PluginFilter<T extends PluginFixture<?>> extends MediaFilterBase implements MediaFilter {
    private static final String TAG = Def.tagOf((Class<?>) PluginFilter.class);
    protected PluginDescriptor descriptor;
    protected MessageProducer messageProducer;
    protected T plugin;

    public PluginFilter(PluginDescriptor pluginDescriptor, T t) {
        this.descriptor = pluginDescriptor;
        this.plugin = t;
    }

    public int[] getMessagesToReceive() {
        String operatorName = getOperatorName();
        String str = TAG;
        SLog.v(str, "getMessagesToReceive E: " + operatorName);
        int[] iArr = null;
        if (operatorName == null) {
            return null;
        }
        if (this.plugin.getConsumingMessages() != null) {
            Map<Enum<?>, int[]> consumingMessages = this.plugin.getConsumingMessages();
            Iterator<Enum<?>> it = consumingMessages.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Enum next = it.next();
                if (next.name().equals(operatorName)) {
                    iArr = consumingMessages.get(next);
                    break;
                }
            }
        }
        String str2 = TAG;
        SLog.v(str2, "getMessagesToReceive X: " + ((String) Optional.ofNullable(iArr).map(new n(1)).orElse("")));
        return iArr;
    }

    public String getOperatorName() {
        PluginDescriptor pluginDescriptor = this.descriptor;
        if (pluginDescriptor instanceof StaplePluginDescriptor) {
            return pluginDescriptor.getOperatorName();
        }
        if (!(pluginDescriptor instanceof PluginDescriptorPair)) {
            return null;
        }
        return (String) Optional.ofNullable(((PluginDescriptorPair) pluginDescriptor).getPrimaryDescriptor().getOperatorName()).orElse(((PluginDescriptorPair) this.descriptor).getSubDescriptor().getOperatorName());
    }

    public boolean onMessageReceived(Message message) {
        if (this.plugin.getMessageConsumer() != null) {
            return this.plugin.getMessageConsumer().apply(message).booleanValue();
        }
        return super.onMessageReceived(message);
    }

    public void setMessageProducer(MessageProducer messageProducer2) {
        this.messageProducer = messageProducer2;
        PlaceHolder<MessageProducer> messageProducerHolder = this.plugin.getMessageProducerHolder();
        if (messageProducerHolder != null) {
            messageProducerHolder.put(messageProducer2);
        }
    }
}
