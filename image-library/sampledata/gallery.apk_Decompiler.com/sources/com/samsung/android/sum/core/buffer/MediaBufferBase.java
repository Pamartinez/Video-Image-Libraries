package com.samsung.android.sum.core.buffer;

import O3.l;
import android.hardware.HardwareBuffer;
import android.os.Parcel;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.format.MediaFormat;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaBufferBase implements MediaBuffer {
    static final int DATA_HARDWARE_BUFFER = 2;
    static final int DATA_NOTHING = 1;
    static final int DATA_PARCELABLE = 4;
    static final int DATA_PARCEL_FILEDESCRIPTOR = 3;
    static final int DATA_SERIALIZABLE = 5;
    protected static final String INDENT_MARK = "    ";
    private static final String TAG = Def.tagOf((Class<?>) MediaBufferBase.class);
    protected Align align;
    protected final Object dataLock;
    protected HashMap<String, Object> extra;
    protected int flags;
    protected MediaFormat format;
    protected List<Object> internalBuffers;
    protected boolean isReleased;
    protected List<Runnable> onReleaseListeners;
    protected AtomicInteger refCount;

    public MediaBufferBase(MediaFormat mediaFormat) {
        this.onReleaseListeners = new ArrayList();
        this.isReleased = false;
        this.refCount = new AtomicInteger(1);
        this.dataLock = new Object();
        this.flags = 0;
        this.extra = new HashMap<>();
        this.internalBuffers = new ArrayList();
        this.align = new Align();
        this.format = mediaFormat;
        this.align = Align.setByFormat(mediaFormat);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addExtra$1(Map.Entry entry) {
        return !this.extra.containsKey(entry.getKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$containFlags$0(int i2) {
        if ((this.flags & i2) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$containsAllExtra$6(HashMap hashMap, String str) {
        Stream stream = hashMap.keySet().stream();
        Objects.requireNonNull(str);
        return stream.anyMatch(new t(3, str));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$containsAnyExtra$4(HashMap hashMap, String str) {
        Stream stream = hashMap.keySet().stream();
        Objects.requireNonNull(str);
        return stream.anyMatch(new t(3, str));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$release$9(Object obj) {
        if (BufferExtension.isRequiredToRelease(obj.getClass())) {
            BufferExtension.dealloc(obj);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$transformDataTo$8(Object obj) {
        this.internalBuffers.add(obj);
    }

    public void addExtra(Map<String, Object> map) {
        HashMap<String, Object> hashMap = this.extra;
        if (hashMap != map) {
            hashMap.putAll((Map) map.entrySet().stream().filter(new t(0, this)).collect(Collectors.toMap(new C0923a(17), new C0923a(18))));
        }
    }

    public void addOnReleaseListener(Runnable... runnableArr) {
        this.onReleaseListeners.addAll(Arrays.asList(runnableArr));
    }

    public MediaBuffer clearFlags(int... iArr) {
        if (iArr == null) {
            this.flags = 0;
            return this;
        }
        for (int i2 : iArr) {
            this.flags = (~i2) & this.flags;
        }
        return this;
    }

    public boolean containFlags(int... iArr) {
        return Arrays.stream(iArr).allMatch(new w(this));
    }

    public boolean containsAllExtra(String... strArr) {
        return ((Boolean) Optional.ofNullable(this.extra).map(new s(strArr, 0)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean containsAnyExtra(String... strArr) {
        return ((Boolean) Optional.ofNullable(this.extra).map(new s(strArr, 1)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean containsExtra(String str) {
        return ((Boolean) Optional.ofNullable(this.extra).map(new m(1, str)).orElse(Boolean.FALSE)).booleanValue();
    }

    public String contentToString(Object obj, Supplier<String> supplier) {
        String str;
        try {
            str = this.extra.toString();
        } catch (Exception unused) {
            str = "extra - n/a";
        }
        StringBuilder sb2 = new StringBuilder("{ ");
        sb2.append(Def.taglnOf(obj));
        return C0212a.p(sb2, Def.contentToStringln(INDENT_MARK, "format=" + ((String) Optional.ofNullable(this.format).map(new C0923a(19)).orElse("n/a")), "alignShape=" + ((String) Optional.ofNullable(this.align).map(new C0923a(20)).orElse("n/a")), C0212a.l("extra=", str), "flags=0x" + Integer.toHexString(this.flags), (String) Optional.ofNullable(supplier).map(new C0923a(16)).orElse("")), " }");
    }

    public int describeContents() {
        return 0;
    }

    public void finalize() {
        try {
            String str = TAG;
            SLog.v(str, "finalize called, release this buffer");
            if (!this.isReleased) {
                SLog.v(str, "not released: set ref# from " + this.refCount.get() + " to 1");
                this.refCount.set(1);
            }
            close();
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public Align getAlign() {
        return this.align;
    }

    public int getChannels() {
        return getFormat().getChannels();
    }

    public int getCols() {
        return getFormat().getCols();
    }

    public Map<String, Object> getExtra() {
        return (Map) Optional.ofNullable(this.extra).orElseGet(new u(0));
    }

    public int getFlags() {
        return this.flags;
    }

    public MediaFormat getFormat() {
        return this.format;
    }

    public int getRows() {
        return getFormat().getRows();
    }

    public int getScanline() {
        return getAlign().getScanline();
    }

    public int getStride() {
        return getAlign().getStride();
    }

    public <T> T getTypedDataOr(Class<T> cls, T t) {
        try {
            return getTypedData(cls);
        } catch (NullPointerException unused) {
            return t;
        }
    }

    public boolean isRequiredToReleaseExplicitly() {
        return ((Boolean) Optional.ofNullable(getDataClass()).map(new C0923a(15)).orElse(Boolean.FALSE)).booleanValue();
    }

    public void release() {
        release((Runnable) null);
    }

    public List<Runnable> removeAllOnReleaseListeners() {
        ArrayList arrayList = new ArrayList(this.onReleaseListeners);
        this.onReleaseListeners.clear();
        return arrayList;
    }

    public <T> T removeExtra(String str) {
        return getExtra().remove(str);
    }

    public void setExtra(String str, Object obj) {
        getExtra().put(str, obj);
    }

    public MediaBuffer setFlags(int... iArr) {
        for (int i2 : iArr) {
            this.flags = i2 | this.flags;
        }
        return this;
    }

    public MediaBuffer setScanline(int i2) {
        this.align.setScanline(i2);
        return this;
    }

    public MediaBuffer setStride(int i2) {
        this.align.setStride(i2);
        return this;
    }

    public String toString() {
        return contentToString(this, (Supplier<String>) null);
    }

    public <T, V> V transformDataTo(T t, Class<V> cls) {
        try {
            BufferExtension.putInternalBufferHandler(new o(1, this));
            return BufferExtension.transform(getFormat(), t, cls);
        } catch (UnsupportedOperationException unused) {
            if ((t instanceof HardwareBuffer) || cls == HardwareBuffer.class) {
                return BufferExtension.transform(getFormat(), t, cls);
            }
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeSerializable(this.format);
        parcel.writeInt(i2);
        parcel.writeMap(this.extra);
    }

    public MediaBuffer dup() {
        String str = TAG;
        SLog.v(str, "dup E: " + hashCode());
        int incrementAndGet = this.refCount.incrementAndGet();
        SLog.v(str, "dup X: " + hashCode() + "ref#" + incrementAndGet);
        return this;
    }

    public MediaBuffer dupAll() {
        return copyTo(getDataClass());
    }

    public <V> V getExtra(String str) {
        return getExtra().get(str);
    }

    public final void release(Runnable runnable) {
        String str = TAG;
        SLog.v(str, "release E: " + hashCode());
        if (this.isReleased || this.refCount.decrementAndGet() != 0) {
            SLog.v(str, "release X: skipped & ref#" + this.refCount.get() + ", isReleased=" + this.isReleased);
            return;
        }
        synchronized (this.dataLock) {
            try {
                this.isReleased = true;
                if (runnable != null) {
                    runnable.run();
                }
                this.internalBuffers.forEach(new r(0));
                this.internalBuffers.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (!this.onReleaseListeners.isEmpty()) {
            this.onReleaseListeners.forEach(new l(0));
            this.onReleaseListeners.clear();
        }
        HashMap<String, Object> hashMap = this.extra;
        if (hashMap != null) {
            hashMap.clear();
            this.extra = null;
        }
        this.format = null;
        this.align = null;
        SLog.v(str, "release X: " + hashCode());
    }

    public void setExtra(Map<String, Object> map) {
        HashMap<String, Object> hashMap = this.extra;
        if (hashMap != map) {
            hashMap.putAll(map);
        }
    }

    public <V> V getExtra(String str, V v) {
        return getExtra().computeIfAbsent(str, new x(0, v));
    }

    public MediaBufferBase(MediaFormat mediaFormat, Align align2) {
        this.onReleaseListeners = new ArrayList();
        this.isReleased = false;
        this.refCount = new AtomicInteger(1);
        this.dataLock = new Object();
        this.flags = 0;
        this.extra = new HashMap<>();
        this.internalBuffers = new ArrayList();
        new Align();
        this.format = mediaFormat;
        this.align = align2;
    }

    public MediaBufferBase(Parcel parcel) {
        this.onReleaseListeners = new ArrayList();
        this.isReleased = false;
        this.refCount = new AtomicInteger(1);
        this.dataLock = new Object();
        this.flags = 0;
        this.extra = new HashMap<>();
        this.internalBuffers = new ArrayList();
        this.align = new Align();
        this.format = (MediaFormat) parcel.readSerializable();
        this.flags = parcel.readInt();
        parcel.readMap(this.extra, (ClassLoader) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$getExtra$2(Object obj, String str) {
        return obj;
    }
}
