package com.samsung.android.gallery.module.c2pa;

import A4.A;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.ai.visual.c2pa.Action;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paAction;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paAssertion;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifest;
import com.samsung.android.sdk.scs.ai.visual.c2pa.DigitalSourceType;
import com.samsung.android.visual.ai.sdkcommon.b;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paWrapper {
    private static final Object LOCK = new Object();
    private static final C2paWrapper instance = new C2paWrapper();
    private static final AtomicInteger sRefCount = new AtomicInteger(0);
    private C2paImp mC2paImpl;
    private final C2paReferenceManager<Consumer<C2paInfo>> mReferenceManager = new C2paReferenceManager<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class C2paReferenceManager<T> {
        final ConcurrentHashMap<Integer, Reference<T>> map = new ConcurrentHashMap<>();

        public Reference<T> obtainReference(T t) {
            Reference<T> reference = new Reference<>(t);
            this.map.put(Integer.valueOf(t.hashCode()), reference);
            return reference;
        }

        /* JADX WARNING: type inference failed for: r5v2, types: [java.util.function.Consumer, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, java.util.function.Function] */
        public void recycle() {
            int size = this.map.size();
            Collection<Reference<T>> values = this.map.values();
            this.map.clear();
            if (size > 0) {
                Log.d("C2paWrapper", "recycle", Integer.valueOf(size), values.stream().map(new Object()).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]")));
            }
            values.forEach(new Object());
        }

        public void removeIfPresent(T t) {
            Reference remove = this.map.remove(Integer.valueOf(t.hashCode()));
            if (remove != null) {
                remove.clear();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CacheHolder {
        static final LruCache<Integer, C2paInfo> sCache = new LruCache<>(30);

        public static C2paInfo get(int i2) {
            return sCache.get(Integer.valueOf(i2));
        }

        public static void put(int i2, C2paInfo c2paInfo) {
            sCache.put(Integer.valueOf(i2), c2paInfo);
        }

        public static void recycle() {
            sCache.evictAll();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Manifest {
        public static final String actionEdit = new C2paManifest.Builder().claimGenerator(SeApiCompat.getBrandName()).build();
        public static final String actionEditedMeta = createManifestBuilder(C2paAction.C2PA_EDITED_METADATA).build();

        private static C2paManifest.Builder createManifestBuilder(C2paAction c2paAction) {
            return new C2paManifest.Builder().claimGenerator(SeApiCompat.getBrandName()).addAssertion(new C2paAssertion.Builder().actions(List.of(new Action.Builder().action(c2paAction).digitalSourceType(DigitalSourceType.TRAINED_ALGORITHMIC_MEDIA).build())).build());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Reference<T> {
        T value;

        public Reference(T t) {
            this.value = t;
        }

        public void clear() {
            this.value = null;
        }

        public T get() {
            return this.value;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimpleIC2paEmbedCallback extends b {
        private Consumer<Boolean> callback;

        public SimpleIC2paEmbedCallback(Consumer<Boolean> consumer) {
            this.callback = consumer;
        }

        public void onError(String str) {
            Log.e((CharSequence) "C2paWrapper", "onError", str, C2paError.Companion.fromErrorString(str));
            Consumer<Boolean> consumer = this.callback;
            if (consumer != null) {
                consumer.accept(Boolean.FALSE);
                this.callback = null;
            }
        }

        public void onSuccess() {
            Consumer<Boolean> consumer = this.callback;
            if (consumer != null) {
                consumer.accept(Boolean.TRUE);
                this.callback = null;
            }
        }
    }

    public static C2paWrapper getInstance() {
        return instance;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extract$1(C2paInfo c2paInfo, Consumer consumer) {
        this.mReferenceManager.removeIfPresent(consumer);
        consumer.accept(c2paInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extract$2(int i2, Reference reference, C2paInfo c2paInfo) {
        CacheHolder.put(i2, c2paInfo);
        Optional.ofNullable((Consumer) reference.get()).ifPresent(new A(25, (Object) this, (Object) c2paInfo));
        reference.clear();
    }

    public void close() {
        if (sRefCount.decrementAndGet() == 0) {
            synchronized (LOCK) {
                this.mC2paImpl.close();
                this.mC2paImpl = null;
            }
            CacheHolder.recycle();
            this.mReferenceManager.recycle();
            Log.d("C2paWrapper", "close");
        }
    }

    public void create(String str, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2, boolean z, Consumer<Boolean> consumer) {
        C2paImp c2paImp = this.mC2paImpl;
        if (c2paImp == null) {
            Log.e("C2paWrapper", "create mC2paImpl is null");
            consumer.accept(Boolean.FALSE);
            return;
        }
        c2paImp.create(str, fileItemInterface, fileItemInterface2, z, new SimpleIC2paEmbedCallback(consumer));
    }

    public void extract(FileItemInterface fileItemInterface, Consumer<C2paInfo> consumer) {
        if (fileItemInterface == null || fileItemInterface.getPath() == null) {
            Log.w("C2paWrapper", "extract skip for null item or path");
            consumer.accept(C2paInfo.EMPTY);
        } else if (!PocFeatures.isEnabled(PocFeatures.C2paSecMp) || DetailsData.of(fileItemInterface).hasC2pa) {
            int complexHashCode = fileItemInterface.getComplexHashCode();
            C2paInfo c2paInfo = CacheHolder.get(complexHashCode);
            if (c2paInfo != null) {
                consumer.accept(c2paInfo);
                return;
            }
            this.mC2paImpl.extract(fileItemInterface.getPath(), new a(this, complexHashCode, this.mReferenceManager.obtainReference(consumer)));
        } else {
            consumer.accept(C2paInfo.EMPTY);
        }
    }

    public void open() {
        if (sRefCount.getAndIncrement() == 0) {
            synchronized (LOCK) {
                try {
                    if (Features.isEnabled(Features.SUPPORT_C2PA)) {
                        this.mC2paImpl = new C2paScsImpl();
                    } else {
                        this.mC2paImpl = new C2paDummyImp();
                    }
                    this.mC2paImpl.open();
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            Log.d("C2paWrapper", "open");
        }
    }

    public void update(String str, FileItemInterface fileItemInterface, Function<FileItemInterface, Boolean> function, Consumer<Boolean> consumer) {
        C2paImp c2paImp = this.mC2paImpl;
        if (c2paImp == null) {
            Log.e("C2paWrapper", "update mC2paImpl is null");
            consumer.accept(Boolean.FALSE);
            return;
        }
        c2paImp.update(str, fileItemInterface, function, new SimpleIC2paEmbedCallback(consumer));
    }
}
