package com.samsung.android.sum.core.filter.factory;

import L5.b;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.DecorateFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.NNFilter;
import com.samsung.android.sum.core.filter.PluginDecorateFilter;
import com.samsung.android.sum.core.filter.PluginFilter;
import com.samsung.android.sum.core.filter.collection.ParallelFilter;
import com.samsung.android.sum.core.filter.collection.SequentialFilter;
import com.samsung.android.sum.core.plugin.PluginStore;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaFilterFactory {
    /* access modifiers changed from: private */
    public static final String TAG = Def.tagOf((Class<?>) MediaFilterFactory.class);
    private Function<Integer, BufferChannel> bufferChannelSupplier;
    private final Map<Class<?>, List<MediaFilterCreator>> creators;
    private final PluginStore pluginStore;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Function<Integer, BufferChannel> bufferChannelSupplier;
        private final Map<Class<?>, Comparator<MediaFilterCreator>> comparators;
        private final Map<Class<?>, List<MediaFilterCreator>> creators;
        private final List<PluginStore> pluginStores;

        public Builder() {
            this.pluginStores = new ArrayList();
            this.creators = new HashMap();
            this.comparators = new HashMap();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaFilter lambda$addDefaultCreators$0(MediaFilterFactory mediaFilterFactory, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
            String access$000 = MediaFilterFactory.TAG;
            SLog.d(access$000, "MediaFilterCreate: " + mFDescriptor);
            try {
                return (MediaFilter) Class.forName(mFDescriptor.getFilterId()).getConstructor(new Class[]{mFDescriptor.getClass()}).newInstance(new Object[]{mFDescriptor});
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("id: " + mFDescriptor.getFilterId() + "\ndesc: " + mFDescriptor);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$build$1(Class cls, Comparator comparator) {
            if (this.creators.containsKey(cls)) {
                this.creators.get(cls).sort(comparator);
            }
        }

        public Builder addBufferChannelSupplier(Function<Integer, BufferChannel> function) {
            this.bufferChannelSupplier = function;
            return this;
        }

        public Builder addCreator(Class<?> cls, MediaFilterCreator mediaFilterCreator) {
            if (!this.creators.containsKey(cls)) {
                this.creators.put(cls, new ArrayList());
            }
            this.creators.get(cls).add(0, mediaFilterCreator);
            return this;
        }

        public Builder addCreatorComparator(Class<?> cls, Comparator<MediaFilterCreator> comparator) {
            this.comparators.put(cls, comparator);
            return this;
        }

        /* JADX WARNING: type inference failed for: r0v6, types: [com.samsung.android.sum.core.filter.factory.MediaFilterCreator, java.lang.Object] */
        public Builder addDefaultCreators() {
            AnonymousClass1 r0 = new ArrayList<MediaFilterCreator>() {
                {
                    add(new DecorateFilterCreator());
                }
            };
            AnonymousClass2 r1 = new ArrayList<MediaFilterCreator>() {
                {
                    add(new PluginFilterCreator());
                }
            };
            this.creators.put(DecorateFilter.class, r0);
            this.creators.put(PluginFilter.class, r1);
            this.creators.put(PluginDecorateFilter.class, r1);
            addCreator(ParallelFilter.class, new ParallelFilterCreator());
            addCreator(SequentialFilter.class, new SequentialFilterCreator());
            addCreator(NNFilter.class, new NNFilterCreator());
            addCreator(MediaFilter.class, new Object());
            return this;
        }

        public Builder addPluginStore(PluginStore pluginStore) {
            this.pluginStores.add(pluginStore);
            return this;
        }

        public MediaFilterFactory build() {
            this.comparators.forEach(new j(this));
            MediaFilterFactory mediaFilterFactory = new MediaFilterFactory(this.creators, PluginStore.of(this.pluginStores));
            Function<Integer, BufferChannel> function = this.bufferChannelSupplier;
            if (function != null) {
                mediaFilterFactory.setBufferChannelSupplier(function);
            }
            return mediaFilterFactory;
        }

        public Builder addPluginStore(PluginStore... pluginStoreArr) {
            this.pluginStores.addAll(Arrays.asList(pluginStoreArr));
            return this;
        }

        public Builder(MediaFilterFactory mediaFilterFactory) {
            this.pluginStores = new ArrayList();
            HashMap hashMap = new HashMap();
            this.creators = hashMap;
            this.comparators = new HashMap();
            hashMap.putAll(mediaFilterFactory.getCreatorRegistry());
        }
    }

    public MediaFilterFactory(Map<Class<?>, List<MediaFilterCreator>> map, PluginStore pluginStore2) {
        this.creators = map;
        this.pluginStore = pluginStore2;
        init();
    }

    private void init() {
        Stream.concat(((List) Optional.ofNullable(this.creators.get(PluginFilter.class)).orElse(new ArrayList())).stream(), ((List) Optional.ofNullable(this.creators.get(PluginDecorateFilter.class)).orElse(new ArrayList())).stream()).filter(new b(1)).forEach(new h(this, 0));
        this.creators.values().stream().filter(new b(2)).flatMap(new b(25)).forEach(new h(this, 1));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$init$0(MediaFilterCreator mediaFilterCreator) {
        return mediaFilterCreator instanceof PluginFilterCreator;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$1(MediaFilterCreator mediaFilterCreator) {
        ((PluginFilterCreator) mediaFilterCreator).setPluginStore(this.pluginStore);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$init$2(List list) {
        if (list.isEmpty() || !(list.get(0) instanceof MediaFilterCreatorChain)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$3(MediaFilterCreator mediaFilterCreator) {
        ((MediaFilterCreatorChain) mediaFilterCreator).prepare(this.creators);
    }

    private MediaFilter wrapAdditionalFilter(MediaFilter mediaFilter) {
        mediaFilter.getDescriptor();
        return mediaFilter;
    }

    public void clear() {
        this.creators.clear();
        this.pluginStore.clear();
    }

    public Function<Integer, BufferChannel> getBufferChannelSupplier() {
        return this.bufferChannelSupplier;
    }

    public Map<Class<?>, List<MediaFilterCreator>> getCreatorRegistry() {
        return this.creators;
    }

    public PluginStore getPluginStore() {
        return this.pluginStore;
    }

    public MediaFilter newFilter(Class<?> cls, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        ArrayList arrayList;
        String str = TAG;
        SLog.i(str, "newFilter: type=" + cls + ", desc=" + mFDescriptor + ", filter=" + mediaFilter);
        List<MediaFilterCreator> list = this.creators.get(cls);
        if (list == null) {
            Class<?> filterTypeFromId = mFDescriptor.getFilterTypeFromId();
            if (filterTypeFromId == null || !this.creators.containsKey(filterTypeFromId)) {
                SLog.w(str, "no creators for type: " + cls + ", use creator of MediaFilter instead");
                arrayList = new ArrayList(this.creators.get(MediaFilter.class));
            } else {
                SLog.w(str, "no creators for type: " + cls + ", use creator of type-from-id instead");
                arrayList = new ArrayList(this.creators.get(filterTypeFromId));
            }
            list = arrayList;
            this.creators.put(cls, list);
        }
        for (MediaFilterCreator mediaFilterCreator : list) {
            try {
                return wrapAdditionalFilter(mediaFilterCreator.newFilter(this, mFDescriptor, mediaFilter));
            } catch (Exception e) {
                String str2 = TAG;
                SLog.d(str2, "fail to create by creator: " + mediaFilterCreator);
                e.printStackTrace();
            }
        }
        throw new IllegalStateException("fail to create filter: type=" + cls + ", descriptor=" + mFDescriptor);
    }

    public void setBufferChannelSupplier(Function<Integer, BufferChannel> function) {
        this.bufferChannelSupplier = function;
    }

    public MediaFilter newFilter(MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        return newFilter(mFDescriptor.getFilterType(), mFDescriptor, mediaFilter);
    }

    public MediaFilter newFilter(MFDescriptor mFDescriptor) {
        return newFilter(mFDescriptor, (MediaFilter) null);
    }
}
