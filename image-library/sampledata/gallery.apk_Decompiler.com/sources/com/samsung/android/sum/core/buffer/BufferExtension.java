package com.samsung.android.sum.core.buffer;

import Bd.C0726b;
import O3.c;
import android.graphics.Bitmap;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import c0.C0086a;
import com.samsung.android.motionphoto.core.MPSurfaceReader;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.UniExifInterface;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BufferExtension {
    private static final String TAG = Def.tagOf((Class<?>) BufferExtension.class);
    private static final String binaryKeySEP = "->";
    private static volatile BufferExtension sInstance;
    private final Map<String, Function<MediaFormat, ?>> allocMap = new ConcurrentHashMap();
    private final Map<String, Consumer<?>> deallocMap = new ConcurrentHashMap();
    private final Map<String, Function<?, MutableMediaFormat>> describeMap = new ConcurrentHashMap();
    private final Map<String, Class<?>> extensionClassMap = new HashMap();
    private final Map<Long, Consumer<?>> internalBufferHandlerMap = new ConcurrentHashMap();
    private final Map<String, Function<?, String>> stringfyMap = new ConcurrentHashMap();
    private final Map<String, TransformFunction> transformMap = new ConcurrentHashMap();
    private final List<Integer> wrappedTransformList = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Registry {
        private final Map<Class<?>, Function<MediaFormat, ?>> allocMap;
        private final Map<Class<?>, Consumer<?>> deallocMap;
        private final Map<Class<?>, Function<?, MutableMediaFormat>> describeMap;
        private final Map<Class<?>, Function<?, String>> stringfyMap;
        private final Map<Pair<Class<?>, Class<?>>, BiFunction<MediaFormat, ?, ?>> transformMap;
        private final List<Integer> wrappedTransformList;

        public <T> Registry addAlloc(Class<T> cls, Function<MediaFormat, ?> function) {
            this.allocMap.put(cls, function);
            return this;
        }

        public <T> Registry addDealloc(Class<T> cls, Consumer<T> consumer) {
            this.deallocMap.put(cls, consumer);
            return this;
        }

        public <T> Registry addDescribe(Class<T> cls, Function<T, MutableMediaFormat> function) {
            this.describeMap.put(cls, function);
            return this;
        }

        public <T> Registry addStringfy(Class<T> cls, Function<T, String> function) {
            this.stringfyMap.put(cls, function);
            return this;
        }

        public <T, R> Registry addTransform(Class<T> cls, Class<R> cls2, BiFunction<MediaFormat, T, R> biFunction) {
            this.transformMap.put(new Pair(cls, cls2), biFunction);
            return this;
        }

        public <T, R> Registry addWrappedTransform(Class<T> cls, Class<R> cls2, BiFunction<MediaFormat, T, R> biFunction) {
            this.transformMap.put(new Pair(cls, cls2), biFunction);
            this.wrappedTransformList.add(Integer.valueOf(biFunction.hashCode()));
            return this;
        }

        public Map<Class<?>, Function<MediaFormat, ?>> getAlloc() {
            return this.allocMap;
        }

        public Map<Class<?>, Consumer<?>> getDealloc() {
            return this.deallocMap;
        }

        public Map<Class<?>, Function<?, MutableMediaFormat>> getDescribe() {
            return this.describeMap;
        }

        public Map<Class<?>, Function<?, String>> getStringfy() {
            return this.stringfyMap;
        }

        public Map<Pair<Class<?>, Class<?>>, BiFunction<MediaFormat, ?, ?>> getTransform() {
            return this.transformMap;
        }

        public List<Integer> getWrappedTransform() {
            return this.wrappedTransformList;
        }

        public void register() {
            BufferExtension unused = BufferExtension.getInstance().registerDescribe(this.describeMap).registerAlloc(this.allocMap).registerDealloc(this.deallocMap).registerTransform(this.transformMap).registerWrappedTransform(this.wrappedTransformList).registerStringfy(this.stringfyMap);
        }

        private Registry() {
            this.describeMap = new HashMap();
            this.allocMap = new HashMap();
            this.deallocMap = new HashMap();
            this.transformMap = new HashMap();
            this.stringfyMap = new HashMap();
            this.wrappedTransformList = new ArrayList();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Unregistry {
        private final List<String> allocList;
        private final List<String> deallocList;
        private final List<String> describeList;
        private final List<String> stringfyList;
        private final List<String> transformList;

        public <T> Unregistry removeAlloc(Class<T> cls) {
            this.allocList.add(BufferExtension.getInstance().getUnaryKey(cls));
            return this;
        }

        public <T> Unregistry removeDealloc(Class<T> cls) {
            this.deallocList.add(BufferExtension.getInstance().getUnaryKey(cls));
            return this;
        }

        public <T> Unregistry removeDescribe(Class<T> cls) {
            this.describeList.add(BufferExtension.getInstance().getUnaryKey(cls));
            return this;
        }

        public <T> Unregistry removeStringfy(Class<T> cls) {
            this.stringfyList.add(BufferExtension.getInstance().getUnaryKey(cls));
            return this;
        }

        public <T, R> Unregistry removeTransform(Class<T> cls, Class<R> cls2) {
            this.transformList.add(BufferExtension.getInstance().getBinaryKey(cls, cls2));
            return this;
        }

        public void unregister() {
            BufferExtension unused = BufferExtension.getInstance().unRegisterDescribe(this.describeList).unRegisterAlloc(this.allocList).unRegisterDealloc(this.deallocList).unRegisterTransform(this.transformList).unRegisterStringfy(this.stringfyList);
        }

        private Unregistry() {
            this.describeList = new ArrayList();
            this.allocList = new ArrayList();
            this.deallocList = new ArrayList();
            this.transformList = new ArrayList();
            this.stringfyList = new ArrayList();
        }
    }

    /* JADX WARNING: type inference failed for: r2v9, types: [java.lang.Object, java.util.function.Function] */
    /* JADX WARNING: type inference failed for: r3v8, types: [java.util.function.BiFunction, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v9, types: [java.util.function.Consumer, java.lang.Object] */
    private BufferExtension() {
        Class<Number> cls = Number.class;
        Class<ByteBuffer> cls2 = ByteBuffer.class;
        Class<Bitmap> cls3 = Bitmap.class;
        Class<HardwareBuffer> cls4 = HardwareBuffer.class;
        Class<Image> cls5 = Image.class;
        Class<ParcelFileDescriptor> cls6 = ParcelFileDescriptor.class;
        Registry addDealloc = newRegistry().addDescribe(cls, new C0923a(2)).addDescribe(cls2, new C0923a(5)).addDescribe(cls3, new C0923a(6)).addDescribe(cls4, new C0923a(7)).addDescribe(cls5, new C0923a(8)).addTransform(cls, cls2, new C0929g(5)).addTransform(cls2, cls, new C0929g(6)).addStringfy(cls2, new C0923a(9)).addStringfy(cls6, new C0923a(10)).addTransform(cls2, cls4, new C0929g(7)).addTransform(cls4, cls2, new C0929g(0)).addTransform(cls3, cls2, new C0929g(1)).addTransform(cls2, cls3, new C0929g(2)).addTransform(cls2, UniExifInterface.class, new C0929g(3)).addTransform(cls5, cls4, new C0929g(4)).addStringfy(cls4, new C0928f(this, 1)).addStringfy(cls6, new C0923a(4)).addDealloc(cls4, new h(this, 0)).addDealloc(cls6, new r(1)).addStringfy(cls5, new C0928f(this, 2)).addDealloc(cls5, new h(this, 1)).addDealloc(cls3, new r(2));
        try {
            addDealloc.addDescribe(MPSurfaceReader.MPSurfaceImage.class, new Object()).addTransform(MPSurfaceReader.MPSurfaceImage.class, cls4, new Object()).addDealloc(MPSurfaceReader.MPSurfaceImage.class, new Object());
        } catch (NoClassDefFoundError e) {
            String str = TAG;
            SLog.w(str, "failed to add dealloc for MPSurfaceImage on " + e);
        }
        registerDescribe(addDealloc.getDescribe());
        registerAlloc(addDealloc.getAlloc());
        registerDealloc(addDealloc.getDealloc());
        registerStringfy(addDealloc.getStringfy());
        registerTransform(addDealloc.getTransform());
        registerWrappedTransform(addDealloc.getWrappedTransform());
    }

    private void addToClassMap(Class<?> cls) {
        if (!cls.getPackage().getName().startsWith("android") && !cls.getPackage().getName().startsWith("java")) {
            this.extensionClassMap.put(cls.getName(), cls);
        }
    }

    public static <T> T alloc(MediaFormat mediaFormat, Class<T> cls) {
        return getInstance().doAlloc(mediaFormat, cls);
    }

    public static <T> void dealloc(T t) {
        getInstance().doDealloc(t);
    }

    public static <T> MutableMediaFormat describe(T t) {
        return getInstance().doDescribe(t);
    }

    private <T> T doAlloc(MediaFormat mediaFormat, Class<T> cls) {
        Function function = this.allocMap.get(findAvailableUnaryKey(cls, this.allocMap));
        if (function != null) {
            return function.apply(mediaFormat);
        }
        throw new UnsupportedOperationException();
    }

    private <T> void doDealloc(T t) {
        Consumer consumer = this.deallocMap.get(findAvailableUnaryKey(t.getClass(), this.deallocMap));
        if (consumer != null) {
            consumer.accept(t);
            return;
        }
        throw new UnsupportedOperationException();
    }

    private <T> MutableMediaFormat doDescribe(T t) {
        Function function = this.describeMap.get(findAvailableUnaryKey(t.getClass(), this.describeMap));
        if (function != null) {
            return (MutableMediaFormat) function.apply(t);
        }
        throw new UnsupportedOperationException();
    }

    private <T> String doStringfy(T t) {
        return (String) Optional.ofNullable(this.stringfyMap.get(findAvailableUnaryKey(t.getClass(), this.stringfyMap))).map(new x(1, t)).orElse(t.toString());
    }

    private <T, R> R doTransform(MediaFormat mediaFormat, T t, Class<R> cls) {
        return this.transformMap.get(findAvailableBinaryKey(t.getClass(), cls, this.transformMap)).apply(mediaFormat, t);
    }

    private <T1, T2> String findAvailableBinaryKey(Class<T1> cls, Class<T2> cls2, Map<String, TransformFunction> map) {
        String binaryKey = getBinaryKey(cls, cls2);
        if (!map.containsKey(binaryKey)) {
            String str = TAG;
            SLog.d(str, "no transform exist for " + binaryKey + ", find alternatives");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList();
            Class<T1> cls3 = cls;
            Class<T2> cls4 = cls2;
            Class<T2> cls5 = cls4;
            ArrayList arrayList4 = arrayList3;
            Class<T1> cls6 = cls3;
            ArrayList arrayList5 = arrayList2;
            Map<String, TransformFunction> map2 = map;
            map2.put(binaryKey, (TransformFunction) map.keySet().stream().filter(new l(this, cls3, cls4, binaryKey, arrayList2, arrayList3)).findFirst().map(new m(0, map)).orElseGet(new n(this, arrayList5, arrayList4, binaryKey, map2, cls6, cls5)));
        }
        return binaryKey;
    }

    private <T, R> String findAvailableUnaryKey(Class<T> cls, Map<String, R> map) {
        String unaryKey = getUnaryKey(cls);
        if (!map.containsKey(unaryKey)) {
            map.put(unaryKey, map.get(map.keySet().stream().filter(new t(1, cls)).findFirst().orElseThrow(new C0925c(0, cls))));
        }
        return unaryKey;
    }

    /* access modifiers changed from: private */
    public <T, R> String getBinaryKey(Class<T> cls, Class<R> cls2) {
        return cls.getName() + binaryKeySEP + cls2.getName();
    }

    /* access modifiers changed from: private */
    public String getHwBufferId(HardwareBuffer hardwareBuffer) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Long.toHexString(hardwareBuffer.getId());
        }
        return "n/a";
    }

    /* access modifiers changed from: private */
    public static BufferExtension getInstance() {
        if (sInstance == null) {
            synchronized (BufferExtension.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new BufferExtension();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* access modifiers changed from: private */
    public <T> String getUnaryKey(Class<T> cls) {
        return cls.getName();
    }

    public static <T> boolean isRequiredToRelease(Class<T> cls) {
        try {
            String findAvailableUnaryKey = getInstance().findAvailableUnaryKey(cls, getInstance().deallocMap);
            String str = TAG;
            SLog.v(str, "found dealloc key" + findAvailableUnaryKey + " for " + cls);
            return true;
        } catch (UnsupportedOperationException unused) {
            return false;
        }
    }

    public static <T, R> boolean isSupportedTransform(Class<T> cls, Class<R> cls2) {
        return getInstance().transformMap.containsKey(getInstance().getBinaryKey(cls, cls2));
    }

    public static boolean isWrappedTransform(BiFunction<MediaFormat, Object, Object> biFunction) {
        return getInstance().wrappedTransformList.contains(Integer.valueOf(biFunction.hashCode()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$doStringfy$42(Object obj, Function function) {
        return (String) function.apply(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$findAvailableBinaryKey$45(Class cls, Class cls2, String str, List list, List list2, String str2) {
        try {
            String[] split = str2.split(binaryKeySEP);
            Class<?> cls3 = this.extensionClassMap.get(split[0]);
            if (cls3 == null) {
                cls3 = Class.forName(split[0]);
            }
            Class<?> cls4 = this.extensionClassMap.get(split[1]);
            if (cls4 == null) {
                cls4 = Class.forName(split[1]);
            }
            if (!cls3.isAssignableFrom(cls) || !cls4.isAssignableFrom(cls2)) {
                if (cls3.isAssignableFrom(cls)) {
                    list.add(new Pair(cls3, cls4));
                } else if (cls4.isAssignableFrom(cls2)) {
                    list2.add(new Pair(cls3, cls4));
                }
                return false;
            }
            String str3 = TAG;
            SLog.d(str3, "find alternative for " + str + ": " + str2);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ TransformFunction lambda$findAvailableBinaryKey$47(Pair pair, String str, Map map, Pair pair2) {
        String binaryKey = getBinaryKey((Class) pair.first, (Class) pair.second);
        String binaryKey2 = getBinaryKey((Class) pair2.first, (Class) pair2.second);
        String str2 = TAG;
        StringBuilder q = C0086a.q("find 2nd order combinations for", str, ": ", binaryKey, " => ");
        q.append(binaryKey2);
        SLog.d(str2, q.toString());
        return new TransformFunction((TransformFunction) map.get(binaryKey), (TransformFunction) map.get(binaryKey2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ TransformFunction lambda$findAvailableBinaryKey$48(List list, String str, Map map, Pair pair) {
        return (TransformFunction) list.stream().filter(new t(2, pair)).findFirst().map(new c(this, pair, str, map, 2)).orElse((Object) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ UnsupportedOperationException lambda$findAvailableBinaryKey$49(Class cls, Class cls2) {
        return new UnsupportedOperationException("no extension exist for " + cls + " -> " + cls2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ TransformFunction lambda$findAvailableBinaryKey$50(List list, List list2, String str, Map map, Class cls, Class cls2) {
        return (TransformFunction) list.stream().map(new c(this, list2, str, map, 1)).filter(new C0927e(0)).findFirst().orElseThrow(new C0726b(3, cls, cls2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$findAvailableUnaryKey$43(Class cls, String str) {
        try {
            return Class.forName(str).isAssignableFrom(cls);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ UnsupportedOperationException lambda$findAvailableUnaryKey$44(Class cls) {
        return new UnsupportedOperationException("no extension exist for " + cls);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MutableMediaFormat lambda$new$0(Number number) {
        MutableMediaFormat buildMutable = MediaFormat.newBuilder().setMediaType(MediaType.SCALA).setShape(1, 1).buildMutable();
        if (number instanceof Byte) {
            buildMutable.setDataType(DataType.U8C1);
            return buildMutable;
        } else if (number instanceof Integer) {
            buildMutable.setDataType(DataType.U32C1);
            return buildMutable;
        } else if (number instanceof Float) {
            buildMutable.setDataType(DataType.F32C1);
            return buildMutable;
        } else {
            throw new UnsupportedOperationException("implement not yet");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ByteBuffer lambda$new$10(MediaFormat mediaFormat, Bitmap bitmap) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocateDirect);
        allocateDirect.rewind();
        return allocateDirect;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Bitmap lambda$new$11(MediaFormat mediaFormat, ByteBuffer byteBuffer) {
        boolean z;
        if (mediaFormat.getColorFormat() == ColorFormat.RGBA) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        Bitmap createBitmap = Bitmap.createBitmap(mediaFormat.getCols(), mediaFormat.getRows(), Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(byteBuffer);
        return createBitmap;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$new$14(HardwareBuffer hardwareBuffer) {
        return Def.fmtstr("HardwareBuffer@%d[#0x%s: w=%d, h=%d, fmt=%d]", Integer.valueOf(hardwareBuffer.hashCode()), getHwBufferId(hardwareBuffer), Integer.valueOf(hardwareBuffer.getWidth()), Integer.valueOf(hardwareBuffer.getHeight()), Integer.valueOf(hardwareBuffer.getFormat()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$new$16(HardwareBuffer hardwareBuffer) {
        return "close hardware-buffer: 0x" + getHwBufferId(hardwareBuffer);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$17(HardwareBuffer hardwareBuffer) {
        if (!hardwareBuffer.isClosed()) {
            SLog.v(TAG, (Supplier<String>) new C0726b(4, this, hardwareBuffer));
            hardwareBuffer.close();
            return;
        }
        SLog.v(TAG, "fail to close hardware-buffer: already closed");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$18(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            parcelFileDescriptor.close();
        } catch (IOException e) {
            String str = TAG;
            SLog.w(str, "fail to close pfd: " + e);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ HardwareBuffer lambda$new$19(Image image) {
        try {
            return image.getHardwareBuffer();
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MutableMediaFormat lambda$new$2(Bitmap bitmap) {
        MutableMediaFormat buildMutable = MediaFormat.newImageBuilder().setShape(bitmap.getWidth(), bitmap.getHeight()).buildMutable();
        buildMutable.setDataType(DataType.U8C3);
        buildMutable.setColorFormat(ColorFormat.RGB);
        buildMutable.setColorSpace(ColorSpace.of(bitmap));
        float size = ((float) buildMutable.size()) / ((float) bitmap.getByteCount());
        if (((float) Math.round(size * 100.0f)) / 100.0f == 0.75f) {
            if (Build.VERSION.SDK_INT < 33 || bitmap.getConfig() != Bitmap.Config.RGBA_1010102) {
                buildMutable.setDataType(DataType.U8C4);
                buildMutable.setColorFormat(ColorFormat.RGBA);
                return buildMutable;
            }
            buildMutable.setDataType(DataType.U10C4);
            buildMutable.setColorFormat(ColorFormat.RGBA_1010102);
            return buildMutable;
        } else if (((float) Math.round(size * 10.0f)) / 10.0f == 0.5f) {
            buildMutable.setDataType(DataType.U16C3);
            return buildMutable;
        } else if (((float) Math.round(size * 1000.0f)) / 1000.0f == 0.375f) {
            buildMutable.setDataType(DataType.U16C4);
            buildMutable.setColorFormat(ColorFormat.RGBA);
            return buildMutable;
        } else if (Math.round(size) == 3) {
            buildMutable.setDataType(DataType.U8C1);
            buildMutable.setColorFormat(ColorFormat.GRAY);
            return buildMutable;
        } else {
            throw new IllegalArgumentException("byte count +" + bitmap.getByteCount() + "is differ from calculated buffer size" + buildMutable.size());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$new$20(Image image) {
        return Def.fmtstr("Image@%d[#0x%s: w=%d, h=%d, fmt=%d, ts=%d]", Integer.valueOf(image.hashCode()), Optional.of(image).map(new C0923a(3)).map(new C0928f(this, 3)).orElse("n/a"), Integer.valueOf(image.getWidth()), Integer.valueOf(image.getHeight()), Integer.valueOf(image.getFormat()), Long.valueOf(image.getTimestamp()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$new$21(Image image) {
        return "close image: 0x" + ((String) Optional.ofNullable(image.getHardwareBuffer()).map(new C0928f(this, 3)).orElse("n/a"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$22(Image image) {
        try {
            SLog.v(TAG, (Supplier<String>) new C0726b(2, this, image));
            image.close();
        } catch (IllegalStateException e) {
            String str = TAG;
            SLog.v(str, "fail to close image: " + e);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$23(Bitmap bitmap) {
        boolean booleanValue = ((Boolean) Optional.ofNullable(bitmap).map(new C0923a(11)).orElse(Boolean.FALSE)).booleanValue();
        String str = TAG;
        SLog.v(str, "recycle bitmap[" + bitmap + "]: isRecycled=" + booleanValue);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ByteBuffer lambda$new$5(MediaFormat mediaFormat, Number number) {
        if (number instanceof Integer) {
            ByteBuffer allocate = ByteBuffer.allocate(32);
            allocate.asIntBuffer().put(((Integer) number).intValue());
            return allocate;
        } else if (number instanceof Long) {
            ByteBuffer allocate2 = ByteBuffer.allocate(64);
            allocate2.asLongBuffer().put(((Long) number).longValue());
            return allocate2;
        } else if (number instanceof Float) {
            ByteBuffer allocate3 = ByteBuffer.allocate(32);
            allocate3.asFloatBuffer().put(((Float) number).floatValue());
            return allocate3;
        } else if (number instanceof Byte) {
            ByteBuffer allocate4 = ByteBuffer.allocate(8);
            allocate4.put(((Byte) number).byteValue());
            return allocate4;
        } else if (number instanceof Short) {
            ByteBuffer allocate5 = ByteBuffer.allocate(16);
            allocate5.asShortBuffer().put(((Short) number).shortValue());
            return allocate5;
        } else {
            throw new UnsupportedOperationException("not supported number type");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Number lambda$new$6(MediaFormat mediaFormat, ByteBuffer byteBuffer) {
        Def.check(mediaFormat.getMediaType().isScala(), "media is not scala", new Object[0]);
        if (mediaFormat.getDataType().isInt()) {
            return Integer.valueOf(byteBuffer.asIntBuffer().get(0));
        }
        if (mediaFormat.getDataType().isLong()) {
            return Long.valueOf(byteBuffer.asLongBuffer().get(0));
        }
        if (mediaFormat.getDataType().isFloat()) {
            return Float.valueOf(byteBuffer.asFloatBuffer().get(0));
        }
        if (mediaFormat.getDataType().isByte()) {
            return Byte.valueOf(byteBuffer.get(0));
        }
        if (mediaFormat.getDataType().isShort()) {
            return Short.valueOf(byteBuffer.asShortBuffer().get(0));
        }
        throw new UnsupportedOperationException("not supported scala type");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ HardwareBuffer lambda$new$8(MediaFormat mediaFormat, ByteBuffer byteBuffer) {
        HardwareBuffer create = SharedBufferManager.create(mediaFormat);
        SharedBufferManager.copyByteBufferToHwBuffer(mediaFormat, byteBuffer, create);
        return create;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ByteBuffer lambda$new$9(MediaFormat mediaFormat, HardwareBuffer hardwareBuffer) {
        Def.check(!hardwareBuffer.isClosed(), "given hardware-buffer is already closed!", new Object[0]);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect((int) mediaFormat.size());
        SharedBufferManager.copyToByteBuffer(mediaFormat, hardwareBuffer, allocateDirect);
        return allocateDirect;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$registerAlloc$29(Map.Entry entry) {
        addToClassMap((Class) entry.getKey());
        return getUnaryKey((Class) entry.getKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$registerDealloc$32(Map.Entry entry) {
        addToClassMap((Class) entry.getKey());
        return getUnaryKey((Class) entry.getKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$registerDescribe$26(Map.Entry entry) {
        addToClassMap((Class) entry.getKey());
        return getUnaryKey((Class) entry.getKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$registerStringfy$39(Map.Entry entry) {
        addToClassMap((Class) entry.getKey());
        return getUnaryKey((Class) entry.getKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$registerTransform$35(Map.Entry entry) {
        Class cls = (Class) ((Pair) entry.getKey()).first;
        Class cls2 = (Class) ((Pair) entry.getKey()).second;
        addToClassMap(cls);
        addToClassMap(cls2);
        return getBinaryKey(cls, cls2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ TransformFunction lambda$registerTransform$36(Map.Entry entry) {
        return new TransformFunction((BiFunction<MediaFormat, ?, ?>[]) new BiFunction[]{(BiFunction) entry.getValue()});
    }

    public static Registry newRegistry() {
        return new Registry();
    }

    public static Unregistry newUnregistry() {
        return new Unregistry();
    }

    public static <T> Consumer<T> popInternalBufferHandler() {
        return getInstance().internalBufferHandlerMap.remove(Long.valueOf(Thread.currentThread().getId()));
    }

    public static <T> void putInternalBufferHandler(Consumer<T> consumer) {
        getInstance().internalBufferHandlerMap.put(Long.valueOf(Thread.currentThread().getId()), consumer);
    }

    /* access modifiers changed from: private */
    public BufferExtension registerAlloc(Map<Class<?>, Function<MediaFormat, ?>> map) {
        this.allocMap.putAll((Map) map.entrySet().stream().collect(Collectors.toMap(new C0928f(this, 5), new C0923a(0))));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension registerDealloc(Map<Class<?>, Consumer<?>> map) {
        this.deallocMap.putAll((Map) map.entrySet().stream().collect(Collectors.toMap(new C0928f(this, 6), new C0923a(12))));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension registerDescribe(Map<Class<?>, Function<?, MutableMediaFormat>> map) {
        this.describeMap.putAll((Map) map.entrySet().stream().collect(Collectors.toMap(new C0928f(this, 4), new C0923a(0))));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension registerStringfy(Map<Class<?>, Function<?, String>> map) {
        this.stringfyMap.putAll((Map) map.entrySet().stream().collect(Collectors.toMap(new C0928f(this, 7), new C0923a(0))));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension registerTransform(Map<Pair<Class<?>, Class<?>>, BiFunction<MediaFormat, ?, ?>> map) {
        this.transformMap.putAll((Map) map.entrySet().stream().collect(Collectors.toMap(new C0928f(this, 0), new C0923a(1))));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension registerWrappedTransform(List<Integer> list) {
        this.wrappedTransformList.addAll(list);
        return this;
    }

    public static void reset() {
        BufferExtension instance = getInstance();
        instance.extensionClassMap.clear();
        instance.describeMap.clear();
        instance.allocMap.clear();
        instance.deallocMap.clear();
        instance.transformMap.clear();
        instance.stringfyMap.clear();
    }

    public static <T> String stringfy(T t) {
        return getInstance().doStringfy(t);
    }

    public static <T, R> R transform(MediaFormat mediaFormat, T t, Class<R> cls) {
        return getInstance().doTransform(mediaFormat, t, cls);
    }

    /* access modifiers changed from: private */
    public BufferExtension unRegisterAlloc(List<String> list) {
        this.allocMap.entrySet().removeIf(new C0924b(3, list));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension unRegisterDealloc(List<String> list) {
        this.deallocMap.entrySet().removeIf(new C0924b(0, list));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension unRegisterDescribe(List<String> list) {
        this.describeMap.entrySet().removeIf(new C0924b(2, list));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension unRegisterStringfy(List<String> list) {
        this.stringfyMap.entrySet().removeIf(new C0924b(1, list));
        return this;
    }

    /* access modifiers changed from: private */
    public BufferExtension unRegisterTransform(List<String> list) {
        this.transformMap.entrySet().removeIf(new C0924b(4, list));
        return this;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TransformFunction {
        private final List<BiFunction<MediaFormat, ?, ?>> functionList;

        @SafeVarargs
        public TransformFunction(BiFunction<MediaFormat, ?, ?>... biFunctionArr) {
            this.functionList = Arrays.asList(biFunctionArr);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(TransformFunction transformFunction) {
            this.functionList.addAll(transformFunction.functionList);
        }

        public <U, R> R apply(MediaFormat mediaFormat, U u) {
            Consumer popInternalBufferHandler = BufferExtension.popInternalBufferHandler();
            if (this.functionList.size() == 1) {
                popInternalBufferHandler = null;
            }
            Object obj = u;
            for (BiFunction next : this.functionList) {
                if (popInternalBufferHandler != null && BufferExtension.isWrappedTransform(next)) {
                    popInternalBufferHandler.accept(obj);
                }
                obj = next.apply(mediaFormat, obj);
            }
            return obj;
        }

        public TransformFunction(TransformFunction... transformFunctionArr) {
            this.functionList = new ArrayList();
            Arrays.asList(transformFunctionArr).forEach(new o(0, this));
        }
    }
}
