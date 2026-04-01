package com.samsung.android.sum.core.format;

import Ad.C0720a;
import android.graphics.Rect;
import android.util.Pair;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.t;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.SplitType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StapleMutableMediaFormat implements MutableMediaFormat {
    private static final String TAG = Def.tagOf((Class<?>) StapleMutableMediaFormat.class);
    private static final long serialVersionUID = 1;
    protected Map<String, Object> attributes = new HashMap();
    private ColorFormat colorFormat;
    private ColorSpace colorSpace = ColorSpace.NONE;
    protected DataType dataType;
    protected MediaType mediaType;
    protected MutableShape shape;
    private long usage = 51;

    public StapleMutableMediaFormat(MediaFormatBuilder mediaFormatBuilder) {
        ColorFormat colorFormat2 = ColorFormat.NONE;
        this.colorFormat = colorFormat2;
        this.mediaType = mediaFormatBuilder.mediaType;
        this.dataType = mediaFormatBuilder.dataType;
        this.shape = mediaFormatBuilder.shape;
        this.colorFormat = mediaFormatBuilder.colorFormat;
        this.colorSpace = mediaFormatBuilder.colorSpace;
        this.usage = mediaFormatBuilder.usage;
        FlipType flipType = mediaFormatBuilder.flipType;
        if (flipType != FlipType.NONE) {
            setFlipType(flipType);
        }
        CodecType codecType = mediaFormatBuilder.codecType;
        if (codecType != CodecType.NONE) {
            setCodecType(codecType);
        }
        if (this.colorFormat != colorFormat2 && this.mediaType == MediaType.NONE) {
            this.mediaType = MediaType.IMAGE;
        }
        Rect rect = mediaFormatBuilder.cropRect;
        if (rect != null) {
            setCropRect(rect);
        }
        adjustChannels(ColorFormat.class, DataType.class, Shape.class);
    }

    private void configAudio(Object... objArr) {
        for (Object next : config(objArr)) {
            if (next instanceof Integer) {
                setDataType(DataType.U8C1);
                setShape(Shape.of(1, ((Integer) next).intValue(), 1, 1));
            } else {
                throw new UnsupportedOperationException("not supported argument: " + next);
            }
        }
    }

    private void configImage(Object... objArr) {
        for (Object next : config(objArr)) {
            if (next instanceof Rect) {
                Rect rect = (Rect) next;
                if (this.shape == null) {
                    this.shape = new StapleMutableShape(1, -1, -1, -1);
                }
                this.shape.setCols(rect.width());
                this.shape.setRows(rect.height());
            } else if (next instanceof ColorFormat) {
                this.colorFormat = (ColorFormat) next;
            } else if (next instanceof ColorSpace) {
                this.colorSpace = (ColorSpace) next;
            } else {
                throw new UnsupportedOperationException("not support for " + next);
            }
        }
        adjustChannels(ColorFormat.class, DataType.class, Shape.class);
    }

    private String getColorString() {
        try {
            return "color=" + getColorFormat();
        } catch (UnsupportedOperationException unused) {
            return "";
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$adjustChannels$8(Class cls) {
        if (cls == MutableShape.class || cls == Shape.class) {
            return (Integer) Optional.ofNullable(this.shape).map(new e(11)).orElse(-1);
        }
        if (cls == DataType.class) {
            return (Integer) Optional.ofNullable(this.dataType).map(new e(7)).orElse(-1);
        }
        if (cls == ColorFormat.class) {
            try {
                return (Integer) Optional.ofNullable(getColorFormat()).map(new e(8)).orElse(-1);
            } catch (UnsupportedOperationException unused) {
                return -1;
            }
        } else {
            throw new IllegalArgumentException("not support channel supplier " + cls);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$adjustChannels$9(Integer num) {
        if (num.intValue() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Float lambda$bytePerPixel$4(ColorFormat colorFormat2) {
        float f;
        if (colorFormat2.isPlanar()) {
            f = colorFormat2.bytePerPixel();
        } else {
            f = 1.0f;
        }
        return Float.valueOf(f);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Float lambda$bytePerSample$2(ColorFormat colorFormat2) {
        float f;
        if (colorFormat2.isPlanar()) {
            f = colorFormat2.bytePerPixel();
        } else {
            f = 1.0f;
        }
        return Float.valueOf(f);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$config$0(Object obj) {
        if ((obj instanceof DataType) || (obj instanceof Shape) || (obj instanceof Integer)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$containsAllOf$7(String str) {
        Stream<String> stream = this.attributes.keySet().stream();
        Objects.requireNonNull(str);
        return stream.anyMatch(new t(3, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$containsAnyOf$6(String str) {
        Stream<String> stream = this.attributes.keySet().stream();
        Objects.requireNonNull(str);
        return stream.anyMatch(new t(3, str));
    }

    public void adjustChannels(Class<?>... clsArr) {
        int intValue = ((Integer) Stream.of(clsArr).map(new g(this, 4)).filter(new b(1)).findFirst().orElse(-1)).intValue();
        if (intValue != -1) {
            DataType dataType2 = this.dataType;
            if (!(dataType2 == null || dataType2 == DataType.NONE || intValue == dataType2.channels())) {
                this.dataType = DataType.of(this.dataType.depth(), intValue);
            }
            MutableShape mutableShape = this.shape;
            if (!(mutableShape == null || intValue == mutableShape.getChannels())) {
                this.shape.setChannels(intValue);
            }
        }
        MutableShape mutableShape2 = this.shape;
        if (mutableShape2 != null && mutableShape2.getBatch() == -1) {
            this.shape.setBatch(1);
        }
    }

    public float bytePerPixel() {
        return ((Float) Optional.ofNullable(getDataType()).map(new e(1)).orElse(Float.valueOf(0.0f))).floatValue() * ((Float) Optional.ofNullable((ColorFormat) get("color-format")).map(new e(0)).orElse(Float.valueOf(1.0f))).floatValue();
    }

    public float bytePerSample() {
        return ((Float) Optional.ofNullable(getDataType()).map(new e(3)).orElse(Float.valueOf(0.0f))).floatValue() * ((Float) Optional.ofNullable((ColorFormat) get("color-format")).map(new e(2)).orElse(Float.valueOf(1.0f))).floatValue();
    }

    public boolean checkTypeOf(String str, Class<?> cls) {
        if (!this.attributes.containsKey(str)) {
            return false;
        }
        Object obj = this.attributes.get(str);
        Objects.requireNonNull(obj);
        if (cls.isAssignableFrom(obj.getClass())) {
            return true;
        }
        return false;
    }

    public List<Object> config(Object... objArr) {
        Map map = (Map) Arrays.stream(objArr).collect(Collectors.partitioningBy(new b(0)));
        Boolean bool = Boolean.TRUE;
        if (map.containsKey(bool)) {
            for (Object next : (List) map.get(bool)) {
                if (next instanceof DataType) {
                    this.dataType = (DataType) next;
                } else if (next instanceof Shape) {
                    this.shape = ((Shape) next).toMutableShape();
                } else if (next instanceof Integer) {
                    int intValue = ((Integer) next).intValue();
                    if (intValue == 1) {
                        set("exif", Boolean.TRUE);
                    } else if (intValue == 2) {
                        set("icc", Boolean.TRUE);
                    } else if (intValue != 3) {
                        String str = TAG;
                        SLog.w(str, "not supported metadata-key " + next);
                    } else {
                        set("gain-map", Boolean.TRUE);
                    }
                }
            }
        }
        return (List) Optional.ofNullable((List) map.get(Boolean.FALSE)).orElseGet(new C0720a(1));
    }

    public boolean contains(String str) {
        return this.attributes.containsKey(str);
    }

    public boolean containsAllOf(String... strArr) {
        return Arrays.stream(strArr).allMatch(new f(this, 0));
    }

    public boolean containsAnyOf(String... strArr) {
        return Arrays.stream(strArr).anyMatch(new f(this, 1));
    }

    public String contentToString() {
        return contentToString(this);
    }

    public <T> T get(String str) {
        str.getClass();
        if (!str.equals("color-format")) {
            return this.attributes.get(str);
        }
        return this.colorFormat;
    }

    public List<String> getAttributeKeys() {
        return new ArrayList(this.attributes.keySet());
    }

    public int getBatch() {
        return ((Integer) Optional.ofNullable(this.shape).map(new e(6)).orElse(-1)).intValue();
    }

    public int getChannels() {
        return ((Integer) Optional.ofNullable(this.shape).map(new e(11)).orElse(-1)).intValue();
    }

    public CodecType getCodecType() {
        return (CodecType) Optional.ofNullable((CodecType) get("codec-type")).orElse(CodecType.NONE);
    }

    public ColorFormat getColorFormat() {
        return this.colorFormat;
    }

    public ColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public int getCols() {
        return ((Integer) Optional.ofNullable(this.shape).map(new e(10)).orElse(-1)).intValue();
    }

    public Rect getCropRect() {
        return (Rect) Optional.ofNullable((Rect) get("crop-rect")).orElse((Object) null);
    }

    public DataType getDataType() {
        return (DataType) Optional.ofNullable(this.dataType).orElse(DataType.NONE);
    }

    public FlipType getFlipType() {
        return (FlipType) Optional.ofNullable((FlipType) get("flip-type")).orElse(FlipType.NONE);
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public List<? extends MediaFormat> getPlanesFormat() {
        if (!this.mediaType.isImage() || getDataType() == DataType.NONE) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!getColorFormat().isPlanar()) {
            arrayList.add(this);
            return arrayList;
        } else if (getColorFormat().isYuv()) {
            DataType depth = getDataType().depth();
            Shape shape2 = getShape().toMutableShape().setRows(getRows() >> 1).setCols(getCols() >> 1).setChannels(getColorFormat().numberOfChromaChannels()).toShape();
            arrayList.add(MediaFormat.newImageBuilder().setDataType(depth, 1).setShape(getShape()).buildMutable());
            IntStream.range(1, getColorFormat().numberOfPlanes()).forEach(new d(arrayList, depth, shape2, 0));
            return arrayList;
        } else {
            throw new UnsupportedOperationException("not support yet for planar except yuv format");
        }
    }

    public int getRotation() {
        return ((Integer) get("rotation", 0)).intValue();
    }

    public int getRows() {
        return ((Integer) Optional.ofNullable(this.shape).map(new e(12)).orElse(-1)).intValue();
    }

    public Shape getShape() {
        return (Shape) Optional.ofNullable(this.shape).map(new e(5)).orElse((Object) null);
    }

    public SplitType getSplitType() {
        return (SplitType) Optional.ofNullable((SplitType) get("split-type")).orElse(SplitType.NONE);
    }

    public long getUsage() {
        return this.usage;
    }

    public boolean isValid() {
        if (this.mediaType == MediaType.NONE || this.dataType == DataType.NONE || this.shape == null) {
            return false;
        }
        if (this.colorSpace == ColorSpace.NONE || this.colorFormat != ColorFormat.NONE) {
            return true;
        }
        return false;
    }

    public <T> T remove(String str) {
        return this.attributes.remove(str);
    }

    public MutableMediaFormat set(String str, Object obj) {
        str.getClass();
        if (!str.equals("color-format")) {
            this.attributes.put(str, obj);
            return this;
        }
        this.colorFormat = (ColorFormat) obj;
        return this;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, java.util.function.Supplier] */
    public MutableMediaFormat setChannels(int i2) {
        this.shape = ((MutableShape) Optional.ofNullable(this.shape).orElseGet(new Object())).setChannels(i2);
        adjustChannels(ColorFormat.class, Shape.class, DataType.class);
        return this;
    }

    public MutableMediaFormat setCodecType(CodecType codecType) {
        set("codec-type", codecType);
        return this;
    }

    public MutableMediaFormat setColorFormat(ColorFormat colorFormat2) {
        this.colorFormat = colorFormat2;
        adjustChannels(ColorFormat.class);
        return this;
    }

    public MutableMediaFormat setColorSpace(ColorSpace colorSpace2) {
        this.colorSpace = colorSpace2;
        return this;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, java.util.function.Supplier] */
    public MutableMediaFormat setCols(int i2) {
        this.shape = ((MutableShape) Optional.ofNullable(this.shape).orElseGet(new Object())).setCols(i2);
        adjustChannels(ColorFormat.class, Shape.class, DataType.class);
        return this;
    }

    public MutableMediaFormat setCropRect(Rect rect) {
        set("crop-rect", rect);
        return this;
    }

    public MutableMediaFormat setDataType(DataType dataType2) {
        this.dataType = dataType2;
        adjustChannels(ColorFormat.class, DataType.class, Shape.class);
        return this;
    }

    public MutableMediaFormat setFlipType(FlipType flipType) {
        set("flip-type", flipType);
        return this;
    }

    public MutableMediaFormat setMediaType(MediaType mediaType2) {
        this.mediaType = mediaType2;
        if (mediaType2 == MediaType.SCALA) {
            this.shape = Shape.mutableOf(1, 1, 1, 1);
        }
        return this;
    }

    public MutableMediaFormat setRotation(int i2) {
        set("rotation", Integer.valueOf(i2));
        return this;
    }

    public MutableMediaFormat setRows(int i2) {
        this.shape = ((MutableShape) Optional.ofNullable(this.shape).orElse(Shape.mutableOf())).setRows(i2);
        adjustChannels(ColorFormat.class, Shape.class, DataType.class);
        return this;
    }

    public MutableMediaFormat setShape(Shape shape2) {
        this.shape = shape2.toMutableShape();
        adjustChannels(ColorFormat.class, Shape.class, DataType.class);
        return this;
    }

    public MutableMediaFormat setSplitType(SplitType splitType) {
        set("split-type", splitType);
        return this;
    }

    public MutableMediaFormat setUsage(long j2) {
        this.usage = j2;
        return this;
    }

    public long size() {
        return (long) ((int) (bytePerSample() * ((float) ((Integer) Optional.ofNullable(this.shape).map(new e(9)).orElse(0)).intValue())));
    }

    public MediaFormat toMediaFormat() {
        return new StapleMediaFormat(this);
    }

    public String toString() {
        return contentToString(this);
    }

    public String contentToString(Object obj) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Def.taglnOf(obj));
        sb2.append(Def.contentToString("mediaType=" + this.mediaType, "dataType=" + this.dataType, getColorString(), "shape=" + this.shape, "colorspace=" + this.colorSpace));
        sb2.append("\nattributes=");
        sb2.append(Collections.singletonList(this.attributes));
        return sb2.toString();
    }

    public MutableMediaFormat dup() {
        try {
            return (MutableMediaFormat) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MutableMediaFormat dupAll() {
        StapleMutableMediaFormat stapleMutableMediaFormat = (StapleMutableMediaFormat) dup();
        MutableShape mutableShape = this.shape;
        if (mutableShape != null) {
            stapleMutableMediaFormat.shape = (MutableShape) mutableShape.dupAll();
        }
        stapleMutableMediaFormat.attributes = new HashMap(this.attributes);
        return stapleMutableMediaFormat;
    }

    public <T> T get(String str, T t) {
        str.getClass();
        if (!str.equals("color-format")) {
            return this.attributes.getOrDefault(str, t);
        }
        return this.colorFormat;
    }

    public MutableMediaFormat set(MediaFilter.Option option) {
        SplitType splitType = SplitType.NONE;
        SplitType splitType2 = (SplitType) option.get(2, splitType);
        if (splitType2 != splitType) {
            if (option.contains(8)) {
                this.attributes.put("split-type", splitType2);
            } else if (option.contains(9)) {
                this.attributes.put("merge-type", splitType2);
            }
        }
        Pair pair = (Pair) option.get(1);
        if (pair != null && option.contains(8)) {
            this.attributes.put("pad-type", pair.first);
            this.attributes.put("pad-size", pair.second);
        }
        return this;
    }

    public MutableMediaFormat toMutableFormat() {
        return this;
    }

    private void configVideo(Object... objArr) {
    }
}
