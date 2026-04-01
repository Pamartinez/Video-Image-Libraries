package com.samsung.android.sum.core.filter.factory;

import Qa.a;
import android.content.Context;
import android.util.Pair;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.descriptor.DecoratePluginDescriptor;
import com.samsung.android.sum.core.descriptor.DecorateStreamPluginDescriptor;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptorPair;
import com.samsung.android.sum.core.descriptor.StaplePluginDescriptor;
import com.samsung.android.sum.core.descriptor.StreamPluginDescriptor;
import com.samsung.android.sum.core.filter.ByPassFilter;
import com.samsung.android.sum.core.filter.ContentFilter;
import com.samsung.android.sum.core.filter.ContentFilterRegister;
import com.samsung.android.sum.core.filter.DecoratePluginInOutStreamFilter;
import com.samsung.android.sum.core.filter.DecoratedPluginFilter;
import com.samsung.android.sum.core.filter.ImgpFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.NNFilter;
import com.samsung.android.sum.core.filter.OutStreamPluginFilter;
import com.samsung.android.sum.core.filter.PluginInOutStreamFilter;
import com.samsung.android.sum.core.filter.StaplePluginFilter;
import com.samsung.android.sum.core.functional.ModelSelector;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.plugin.NNPlugin;
import com.samsung.android.sum.core.plugin.PluginFixture;
import com.samsung.android.sum.core.plugin.PluginStore;
import com.samsung.android.sum.core.plugin.StaplePluginFixture;
import com.samsung.android.sum.core.plugin.StreamPluginFixture;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PluginFilterCreator implements MediaFilterCreator {
    private static final String TAG = Def.tagOf((Class<?>) PluginFilterCreator.class);
    private PluginStore pluginStore;

    private MediaFilter createDecorateFilter(MediaFilterFactory mediaFilterFactory, PluginDescriptor pluginDescriptor, MediaFilter mediaFilter) {
        String str = TAG;
        SLog.d(str, "create DecorateFilter= " + pluginDescriptor + "successor= " + mediaFilter);
        if (mediaFilter == null) {
            MFDescriptor successorDescriptor = ((DecoratePluginDescriptor) pluginDescriptor).getSuccessorDescriptor();
            SLog.d(str, "successor descriptor= " + successorDescriptor);
            mediaFilter = mediaFilterFactory.newFilter(successorDescriptor);
            SLog.d(str, "successor = " + mediaFilter);
        }
        PluginStore.Entry entry = (PluginStore.Entry) Optional.ofNullable(this.pluginStore.get(pluginDescriptor)).orElseThrow(new u(4));
        PluginFixture pluginFixture = entry.getPluginFixture();
        if (pluginFixture.isCloneablePlugin().booleanValue()) {
            pluginFixture = pluginFixture.clone();
        }
        PluginDescriptorPair pluginDescriptorPair = new PluginDescriptorPair(pluginDescriptor, (PluginDescriptor) entry.getDescriptor());
        Consumer<PluginDescriptor> pluginDescriptorPairConsumer = pluginFixture.getPluginDescriptorPairConsumer();
        if (pluginDescriptorPairConsumer != null) {
            pluginDescriptorPairConsumer.accept(pluginDescriptorPair);
        }
        DecoratedPluginFilter decoratedPluginFilter = new DecoratedPluginFilter(pluginDescriptorPair, pluginFixture, mediaFilter, mediaFilter.getDescriptor());
        return (MediaFilter) Optional.ofNullable(pluginFixture.getContentFilterRegister()).map(new a(4, decoratedPluginFilter)).orElse(decoratedPluginFilter);
    }

    /* JADX WARNING: type inference failed for: r0v22, types: [java.lang.Object, java.util.function.BinaryOperator] */
    private MediaFilter createImgpFilter(ImgpDescriptor imgpDescriptor) {
        ImgpPlugin imgpPlugin;
        String str = TAG;
        SLog.i(str, "createImgpFilter: " + imgpDescriptor);
        if (imgpDescriptor.getPluginId() == ImgpPlugin.Type.ANY) {
            List list = (List) this.pluginStore.keyStream().filter(new m(2, (List) Arrays.stream(ImgpPlugin.Type.values()).filter(new b(4)).map(new c(3)).collect(Collectors.toList()))).map(new a(7, this)).filter(new b(5)).map(new c(4)).collect(Collectors.toList());
            Def.require(!list.isEmpty());
            if (imgpDescriptor.isLatestPluginsOrder()) {
                Collections.reverse(list);
            }
            if (list.size() == 1) {
                imgpPlugin = (ImgpPlugin) list.get(0);
            } else {
                imgpPlugin = (ImgpPlugin) list.stream().reduce(new Object()).orElseThrow(new u(4));
            }
        } else {
            PluginStore.Entry entry = (PluginStore.Entry) Optional.ofNullable(this.pluginStore.get(imgpDescriptor)).orElseThrow(new u(4));
            ((PluginDescriptor) entry.getDescriptor()).copyTo(imgpDescriptor);
            imgpPlugin = (ImgpPlugin) entry.getPluginFixture();
        }
        if (imgpPlugin.isCloneablePlugin().booleanValue()) {
            imgpPlugin = (ImgpPlugin) imgpPlugin.clone();
        }
        ImgpFilter imgpFilter = new ImgpFilter(imgpDescriptor, imgpPlugin);
        return (MediaFilter) Optional.ofNullable(imgpPlugin.getContentFilterRegister()).map(new a(8, imgpFilter)).orElse(imgpFilter);
    }

    /* JADX WARNING: type inference failed for: r4v7, types: [java.lang.Object, java.util.function.IntFunction] */
    private MediaFilter createNNFilter(NNDescriptor nNDescriptor, MediaFilter mediaFilter) {
        List list;
        Object obj;
        PluginStore.Entry entry = (PluginStore.Entry) Optional.ofNullable(this.pluginStore.get(nNDescriptor)).orElseThrow(new u(4));
        NNPlugin nNPlugin = (NNPlugin) entry.getPluginFixture();
        if (nNPlugin.isCloneablePlugin().booleanValue()) {
            nNPlugin = (NNPlugin) nNPlugin.clone();
        }
        ((PluginDescriptor) entry.getDescriptor()).copyTo(nNDescriptor);
        Context context = this.pluginStore.getContext();
        if (context != null) {
            Pair<String, Pattern> load = nNPlugin.getModelPathLoader().load(nNDescriptor.getModelId());
            try {
                list = (List) Arrays.stream(context.getAssets().list((String) load.first)).filter(new m(1, load)).map(new a(context, (Object) load, 9)).filter(new b(3)).collect(Collectors.toList());
                String arrays = Arrays.toString(list.stream().map(new com.samsung.android.sdk.scs.ai.visual.c2pa.a(11)).toArray(new Object()));
                SLog.d(TAG, "success to load model file: " + ((String) load.first) + "/" + arrays);
            } catch (IOException e) {
                e.printStackTrace();
                list = new ArrayList();
            }
            if (!list.isEmpty()) {
                boolean z = true;
                if (list.size() > 1) {
                    ModelSelector modelSelector = nNPlugin.getModelSelector();
                    if (modelSelector == null) {
                        z = false;
                    }
                    Def.require(z, "multiple model found, but model selector is not given", new Object[0]);
                    nNDescriptor.setModelSelector(modelSelector);
                }
                nNDescriptor.setNNFileDescriptors(list);
                Objects.requireNonNull(mediaFilter);
                obj = new NNFilter(nNDescriptor, nNPlugin, mediaFilter);
            } else if (nNDescriptor.isIgnorableFilter()) {
                obj = new ByPassFilter(nNDescriptor);
            } else {
                throw new IllegalArgumentException(Def.fmtstr("can't find model file: " + ((String) load.first) + ", regex=" + load.second, new Object[0]));
            }
            return (MediaFilter) Optional.ofNullable(nNPlugin.getContentFilterRegister()).map(new a(5, obj)).orElse(obj);
        }
        throw new IllegalStateException("NNPlugin filter require context from PluginStore, but nothing is given");
    }

    private MediaFilter createPluginFilter(MediaFilterFactory mediaFilterFactory, PluginDescriptor pluginDescriptor, MediaFilter mediaFilter) {
        String str = TAG;
        SLog.d(str, "create StapleFilter= " + pluginDescriptor);
        PluginStore.Entry entry = (PluginStore.Entry) Optional.ofNullable(this.pluginStore.get(pluginDescriptor)).orElseThrow(new u(4));
        StaplePluginFixture staplePluginFixture = (StaplePluginFixture) entry.getPluginFixture();
        if (staplePluginFixture.isCloneablePlugin().booleanValue()) {
            staplePluginFixture = (StaplePluginFixture) staplePluginFixture.clone();
        }
        PluginDescriptorPair pluginDescriptorPair = new PluginDescriptorPair(pluginDescriptor, (PluginDescriptor) entry.getDescriptor());
        Consumer<PluginDescriptor> pluginDescriptorPairConsumer = staplePluginFixture.getPluginDescriptorPairConsumer();
        if (pluginDescriptorPairConsumer != null) {
            pluginDescriptorPairConsumer.accept(pluginDescriptorPair);
        }
        StaplePluginFilter staplePluginFilter = new StaplePluginFilter(pluginDescriptorPair, staplePluginFixture);
        return (MediaFilter) Optional.ofNullable(staplePluginFixture.getContentFilterRegister()).map(new a(6, staplePluginFilter)).orElse(staplePluginFilter);
    }

    private MediaFilter createStreamFilter(MediaFilterFactory mediaFilterFactory, PluginDescriptor pluginDescriptor, MediaFilter mediaFilter) {
        Object obj;
        String str = TAG;
        SLog.d(str, "create StreamFilter= " + pluginDescriptor + ", successor=" + mediaFilter);
        if (mediaFilter == null && (pluginDescriptor instanceof DecorateStreamPluginDescriptor)) {
            MFDescriptor successorDescriptor = ((DecorateStreamPluginDescriptor) pluginDescriptor).getSuccessorDescriptor();
            SLog.d(str, "successor descriptor= " + successorDescriptor);
            mediaFilter = mediaFilterFactory.newFilter(successorDescriptor);
            SLog.d(str, "successor = " + mediaFilter);
        }
        PluginStore.Entry entry = (PluginStore.Entry) Optional.ofNullable(this.pluginStore.get(pluginDescriptor)).orElseThrow(new u(4));
        PluginFixture pluginFixture = entry.getPluginFixture();
        if (pluginFixture.isCloneablePlugin().booleanValue()) {
            pluginFixture = pluginFixture.clone();
        }
        PluginDescriptorPair pluginDescriptorPair = new PluginDescriptorPair(pluginDescriptor, (PluginDescriptor) entry.getDescriptor());
        Consumer<PluginDescriptor> pluginDescriptorPairConsumer = pluginFixture.getPluginDescriptorPairConsumer();
        if (pluginDescriptorPairConsumer != null) {
            pluginDescriptorPairConsumer.accept(pluginDescriptorPair);
        }
        if (mediaFilter != null) {
            obj = new DecoratePluginInOutStreamFilter(pluginDescriptorPair, pluginFixture, mediaFilter);
        } else if (pluginDescriptorPair.getFilterType() == OutStreamPluginFilter.class) {
            obj = new OutStreamPluginFilter(pluginDescriptorPair, (StreamPluginFixture) pluginFixture);
        } else {
            obj = new PluginInOutStreamFilter(pluginDescriptorPair, pluginFixture);
        }
        return (MediaFilter) Optional.ofNullable(pluginFixture.getContentFilterRegister()).map(new a(3, obj)).orElse(obj);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaFilter lambda$createDecorateFilter$7(MediaFilter mediaFilter, ContentFilterRegister contentFilterRegister) {
        return new ContentFilter(contentFilterRegister, mediaFilter);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$createImgpFilter$0(ImgpPlugin.Type type) {
        if (type != ImgpPlugin.Type.CUSTOM) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Optional lambda$createImgpFilter$3(String str) {
        return Optional.ofNullable(this.pluginStore.get(MFDescriptor.newBuilder().setImgpPluginType(ImgpPlugin.Type.valueOf(str)).build(ImgpDescriptor.class))).map(new c(5));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaFilter lambda$createImgpFilter$5(MediaFilter mediaFilter, ContentFilterRegister contentFilterRegister) {
        return new ContentFilter(contentFilterRegister, mediaFilter);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056 A[SYNTHETIC, Splitter:B:20:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061 A[SYNTHETIC, Splitter:B:26:0x0061] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.samsung.android.sum.core.types.nn.NNFileDescriptor lambda$createNNFilter$10(android.content.Context r3, android.util.Pair r4, java.lang.String r5) {
        /*
            r0 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            r1.<init>()     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            java.lang.Object r4 = r4.first     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            r1.append(r4)     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            java.lang.String r4 = "/"
            r1.append(r4)     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            r1.append(r5)     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            java.lang.String r4 = r1.toString()     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            android.content.res.AssetFileDescriptor r3 = r3.openFd(r4)     // Catch:{ IOException -> 0x004f, all -> 0x004d }
            com.samsung.android.sum.core.types.nn.NNFileDescriptor r4 = new com.samsung.android.sum.core.types.nn.NNFileDescriptor     // Catch:{ IOException -> 0x004b }
            android.os.ParcelFileDescriptor r1 = r3.getParcelFileDescriptor()     // Catch:{ IOException -> 0x004b }
            android.os.ParcelFileDescriptor r1 = r1.dup()     // Catch:{ IOException -> 0x004b }
            r4.<init>((android.os.ParcelFileDescriptor) r1)     // Catch:{ IOException -> 0x004b }
            r4.setName(r5)     // Catch:{ IOException -> 0x004b }
            long r1 = r3.getStartOffset()     // Catch:{ IOException -> 0x004b }
            r4.setOffset(r1)     // Catch:{ IOException -> 0x004b }
            long r1 = r3.getDeclaredLength()     // Catch:{ IOException -> 0x004b }
            r4.setLength(r1)     // Catch:{ IOException -> 0x004b }
            r3.close()     // Catch:{ IOException -> 0x0043 }
            return r4
        L_0x0043:
            r3 = move-exception
            r3.printStackTrace()
            return r4
        L_0x0048:
            r4 = move-exception
            r0 = r3
            goto L_0x005f
        L_0x004b:
            r4 = move-exception
            goto L_0x0051
        L_0x004d:
            r4 = move-exception
            goto L_0x005f
        L_0x004f:
            r4 = move-exception
            r3 = r0
        L_0x0051:
            r4.printStackTrace()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x005e
            r3.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r3 = move-exception
            r3.printStackTrace()
        L_0x005e:
            return r0
        L_0x005f:
            if (r0 == 0) goto L_0x0069
            r0.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x0069
        L_0x0065:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0069:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.filter.factory.PluginFilterCreator.lambda$createNNFilter$10(android.content.Context, android.util.Pair, java.lang.String):com.samsung.android.sum.core.types.nn.NNFileDescriptor");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$createNNFilter$11(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaFilter lambda$createNNFilter$12(MediaFilter mediaFilter, ContentFilterRegister contentFilterRegister) {
        return new ContentFilter(contentFilterRegister, mediaFilter);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaFilter lambda$createPluginFilter$6(MediaFilter mediaFilter, ContentFilterRegister contentFilterRegister) {
        return new ContentFilter(contentFilterRegister, mediaFilter);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaFilter lambda$createStreamFilter$8(MediaFilter mediaFilter, ContentFilterRegister contentFilterRegister) {
        return new ContentFilter(contentFilterRegister, mediaFilter);
    }

    public PluginStore getPluginStore() {
        return this.pluginStore;
    }

    public MediaFilter newFilter(MediaFilterFactory mediaFilterFactory, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        Def.require(mFDescriptor instanceof PluginDescriptor);
        if (mFDescriptor instanceof NNDescriptor) {
            return createNNFilter((NNDescriptor) mFDescriptor, mediaFilter);
        }
        if (mFDescriptor instanceof ImgpDescriptor) {
            return createImgpFilter((ImgpDescriptor) mFDescriptor);
        }
        if (mFDescriptor instanceof DecoratePluginDescriptor) {
            return createDecorateFilter(mediaFilterFactory, (DecoratePluginDescriptor) mFDescriptor, mediaFilter);
        }
        if (mFDescriptor instanceof StaplePluginDescriptor) {
            return createPluginFilter(mediaFilterFactory, (StaplePluginDescriptor) mFDescriptor, mediaFilter);
        }
        if ((mFDescriptor instanceof StreamPluginDescriptor) || (mFDescriptor instanceof DecorateStreamPluginDescriptor)) {
            return createStreamFilter(mediaFilterFactory, (PluginDescriptor) mFDescriptor, mediaFilter);
        }
        throw new UnsupportedOperationException("not yet supported except NNDescriptor");
    }

    public void setPluginStore(PluginStore pluginStore2) {
        this.pluginStore = pluginStore2;
    }
}
