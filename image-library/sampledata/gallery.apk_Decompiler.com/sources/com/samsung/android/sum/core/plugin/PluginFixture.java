package com.samsung.android.sum.core.plugin;

import android.content.Context;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.filter.ContentFilterRegister;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.functional.DescriptorLoader;
import com.samsung.android.sum.core.functional.DescriptorStreamLoader;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.functional.OperatorWrapper;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageProducer;
import com.samsung.android.sum.core.plugin.PluginFixture;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PluginFixture<T extends PluginFixture<?>> implements Cloneable {
    private Consumer<PluginFixture<T>> cloneConsumer;
    private Supplier<Boolean> cloneableChecker;
    protected Map<Enum<?>, int[]> consumingMessages;
    protected ContentFilterRegister contentFilterRegister;
    private Runnable deInitializer;
    private DescriptorLoader descriptorLoader;
    private DescriptorStreamLoader descriptorStreamLoader;
    private Map<String, Object> extra;
    private Consumer<Context> initializer;
    protected Function<Message, Boolean> messageConsumer;
    protected PlaceHolder<MessageProducer> messageProducerHolder;
    protected Map<Enum<?>, Operator> operatorMap;
    protected Consumer<PluginDescriptor> pluginDescriptorPairConsumer;
    protected PlaceHolder<MediaFilter> successorFilterHolder;

    public PluginFixture(Plugin<T> plugin) {
        plugin.bindToFixture(this);
        if (this.operatorMap == null) {
            this.operatorMap = new HashMap();
        }
        this.extra = new HashMap();
        this.cloneableChecker = new h(plugin);
        if (plugin instanceof CloneablePlugin) {
            this.cloneConsumer = new i(0, plugin);
        }
        Def.require(isValid(), "fail to check validation of plugin", new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Operator lambda$getOperator$3(Enum enumR) {
        return OperatorWrapper.of(enumR, this.operatorMap.get(enumR));
    }

    public Map<Enum<?>, int[]> getConsumingMessages() {
        return this.consumingMessages;
    }

    public ContentFilterRegister getContentFilterRegister() {
        return this.contentFilterRegister;
    }

    public DescriptorLoader getDescriptorLoader() {
        return this.descriptorLoader;
    }

    public DescriptorStreamLoader getDescriptorStreamLoader() {
        return this.descriptorStreamLoader;
    }

    public <V> V getExtra(String str) {
        return this.extra.get(str);
    }

    public Function<Message, Boolean> getMessageConsumer() {
        return this.messageConsumer;
    }

    public PlaceHolder<MessageProducer> getMessageProducerHolder() {
        return this.messageProducerHolder;
    }

    public Operator getOperator(Enum<?> enumR) {
        if (this.operatorMap.containsKey(enumR)) {
            return OperatorWrapper.of(enumR, this.operatorMap.get(enumR));
        }
        return null;
    }

    public Consumer<PluginDescriptor> getPluginDescriptorPairConsumer() {
        return this.pluginDescriptorPairConsumer;
    }

    public PlaceHolder<MediaFilter> getSuccessorFilterHolder() {
        return this.successorFilterHolder;
    }

    public void init(Context context) {
        Consumer<Context> consumer = this.initializer;
        if (consumer != null) {
            consumer.accept(context);
        }
    }

    public Boolean isCloneablePlugin() {
        return this.cloneableChecker.get();
    }

    public abstract boolean isValid();

    public void release() {
        Runnable runnable = this.deInitializer;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void setConsumingMessages(Map<Enum<?>, int[]> map) {
        this.consumingMessages = map;
    }

    public void setContentFilterRegister(ContentFilterRegister contentFilterRegister2) {
        this.contentFilterRegister = contentFilterRegister2;
    }

    public void setDeInitializer(Runnable runnable) {
        this.deInitializer = runnable;
    }

    public void setDescriptorLoader(DescriptorLoader descriptorLoader2) {
        this.descriptorLoader = descriptorLoader2;
    }

    public void setDescriptorStreamLoader(DescriptorStreamLoader descriptorStreamLoader2) {
        this.descriptorStreamLoader = descriptorStreamLoader2;
    }

    public PluginFixture<T> setExtra(HashMap<String, Object> hashMap) {
        this.extra = hashMap;
        return this;
    }

    public void setInitializer(Consumer<Context> consumer) {
        this.initializer = consumer;
    }

    public void setMessageConsumer(Function<Message, Boolean> function) {
        this.messageConsumer = function;
    }

    public void setMessageProducerHolder(PlaceHolder<MessageProducer> placeHolder) {
        this.messageProducerHolder = placeHolder;
    }

    public PluginFixture<T> setOperator(Enum<?> enumR, Operator operator) {
        if (this.operatorMap == null) {
            this.operatorMap = new HashMap();
        }
        this.operatorMap.put(enumR, operator);
        return this;
    }

    public void setPluginDescriptorPairConsumer(Consumer<PluginDescriptor> consumer) {
        this.pluginDescriptorPairConsumer = consumer;
    }

    public void setSuccessorFilterHolder(PlaceHolder<MediaFilter> placeHolder) {
        this.successorFilterHolder = placeHolder;
    }

    public PluginFixture<? extends PluginFixture<T>> clone() {
        try {
            PluginFixture<? extends PluginFixture<T>> pluginFixture = (PluginFixture) super.clone();
            Consumer<PluginFixture<T>> consumer = this.cloneConsumer;
            if (consumer != null) {
                consumer.accept(pluginFixture);
            }
            return pluginFixture;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("fail to clone plugin-fixture", e);
        }
    }

    public <V> V getExtra(String str, V v) {
        return this.extra.getOrDefault(str, v);
    }

    public PluginFixture<T> setExtra(String str, Object obj) {
        this.extra.put(str, obj);
        return this;
    }

    public Operator getOperator(String str) {
        return (Operator) this.operatorMap.keySet().stream().filter(new a(1, str)).findFirst().map(new m(2, this)).orElse((Object) null);
    }
}
