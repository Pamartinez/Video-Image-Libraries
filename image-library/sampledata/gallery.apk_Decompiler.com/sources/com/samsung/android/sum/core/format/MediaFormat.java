package com.samsung.android.sum.core.format;

import android.graphics.Rect;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.SplitType;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaFormat extends Serializable, Cloneable {
    public static final int EXIF = 1;
    public static final int GAINMAP = 3;
    public static final int ICC = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface MetadataKey {
    }

    @Deprecated
    static MediaFormat audioOf(Object... objArr) {
        return of(MediaType.AUDIO, objArr);
    }

    @Deprecated
    static MediaFormat compressedAudioOf(Object... objArr) {
        return of(MediaType.COMPRESSED_AUDIO, objArr);
    }

    @Deprecated
    static MediaFormat compressedImageOf(Object... objArr) {
        return of(MediaType.COMPRESSED_IMAGE, objArr);
    }

    @Deprecated
    static MediaFormat compressedVideoOf(Object... objArr) {
        return of(MediaType.COMPRESSED_VIDEO, objArr);
    }

    @Deprecated
    static MediaFormat gainMapOf(Object... objArr) {
        return mutableGainMapOf(objArr).toMediaFormat();
    }

    static List<MediaFormat> getPlanes(MediaFormat mediaFormat) {
        if (!mediaFormat.getMediaType().isImage()) {
            throw new UnsupportedOperationException("not support non image format");
        } else if (mediaFormat.getDataType() == DataType.NONE) {
            throw new IllegalStateException("not support non data type");
        } else if (!mediaFormat.getColorFormat().isPlanar()) {
            return new ArrayList<MediaFormat>() {
                {
                    add(MediaFormat.this);
                }
            };
        } else {
            ArrayList arrayList = new ArrayList();
            if (mediaFormat.getColorFormat().isYuv()) {
                DataType depth = mediaFormat.getDataType().depth();
                Shape shape = mediaFormat.getShape().toMutableShape().setRows(mediaFormat.getRows() >> 1).setCols(mediaFormat.getCols() >> 1).setChannels(mediaFormat.getColorFormat().numberOfChromaChannels()).toShape();
                arrayList.add(newImageBuilder().setDataType(depth, 1).setShape(mediaFormat.getShape()).buildMutable());
                IntStream.range(1, mediaFormat.getColorFormat().numberOfPlanes()).forEach(new d(arrayList, depth, shape, 1));
                return (List) arrayList.stream().map(new e(4)).collect(Collectors.toList());
            }
            throw new UnsupportedOperationException("not support yet for planar except yuv format");
        }
    }

    @Deprecated
    static MediaFormat imageOf(Object... objArr) {
        return of(MediaType.IMAGE, objArr);
    }

    /* access modifiers changed from: private */
    static /* synthetic */ MediaFormat lambda$getPlanes$1(MutableMediaFormat mutableMediaFormat) {
        if (mutableMediaFormat != null) {
            return mutableMediaFormat.toMediaFormat();
        }
        return mutableMediaFormat;
    }

    @Deprecated
    static MediaFormat metaOf(Object... objArr) {
        return of(MediaType.META, Stream.concat(Stream.of(DataType.U8C1), Arrays.stream(objArr)).toArray());
    }

    @Deprecated
    static MutableMediaFormat mutableAudioOf(Object... objArr) {
        return mutableOf(MediaType.AUDIO, objArr);
    }

    @Deprecated
    static MutableMediaFormat mutableCompressedAudioOf(Object... objArr) {
        return mutableOf(MediaType.COMPRESSED_AUDIO, objArr);
    }

    @Deprecated
    static MutableMediaFormat mutableCompressedImageOf(Object... objArr) {
        return mutableOf(MediaType.COMPRESSED_IMAGE, objArr);
    }

    @Deprecated
    static MutableMediaFormat mutableCompressedVideoOf(Object... objArr) {
        return mutableOf(MediaType.COMPRESSED_VIDEO, objArr);
    }

    @Deprecated
    static MutableMediaFormat mutableEmptyOf() {
        return newBuilder().buildMutable();
    }

    @Deprecated
    static MutableMediaFormat mutableGainMapOf(Object... objArr) {
        return mutableMetaOf(objArr).set("gain-map", Boolean.TRUE);
    }

    @Deprecated
    static MutableMediaFormat mutableImageOf(Object... objArr) {
        return mutableOf(MediaType.IMAGE, objArr);
    }

    @Deprecated
    static MutableMediaFormat mutableMetaOf(Object... objArr) {
        return mutableOf(MediaType.META, Stream.concat(Stream.of(DataType.U8C1), Arrays.stream(objArr)).toArray());
    }

    @Deprecated
    static MutableMediaFormat mutableOf(MediaType mediaType, Object... objArr) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    static MutableMediaFormat mutableScalaOf(Object... objArr) {
        return mutableOf(MediaType.SCALA, objArr);
    }

    @Deprecated
    static MediaFormat mutableThumbnailOf(Object... objArr) {
        return mutableOf(MediaType.IMAGE, Shape.of(384, 512));
    }

    @Deprecated
    static MutableMediaFormat mutableVideoOf(Object... objArr) {
        return mutableOf(MediaType.VIDEO, objArr);
    }

    static MediaFormatBuilder newAudioBuilder() {
        return newBuilder(MediaType.AUDIO);
    }

    static MediaFormatBuilder newBuilder() {
        return new MediaFormatBuilder();
    }

    static MediaFormatBuilder newImageBuilder() {
        return newBuilder(MediaType.IMAGE);
    }

    static MediaFormatBuilder newMetaBuilder() {
        return newBuilder(MediaType.META);
    }

    static MediaFormatBuilder newVideoBuilder() {
        return newBuilder(MediaType.VIDEO);
    }

    @Deprecated
    static MediaFormat of(MediaType mediaType, Object... objArr) {
        return mutableOf(mediaType, objArr).toMediaFormat();
    }

    @Deprecated
    static MediaFormat scalarOf(Object... objArr) {
        return of(MediaType.SCALA, objArr);
    }

    @Deprecated
    static MediaFormat thumbnailOf(Object... objArr) {
        return of(MediaType.IMAGE, Shape.of(384, 512));
    }

    @Deprecated
    static MediaFormat videoOf(Object... objArr) {
        return of(MediaType.VIDEO, objArr);
    }

    float bytePerPixel();

    float bytePerSample();

    boolean checkTypeOf(String str, Class<?> cls);

    boolean contains(String str);

    boolean containsAllOf(String... strArr);

    boolean containsAnyOf(String... strArr);

    String contentToString();

    String contentToString(Object obj);

    int dimension() {
        return getRows() * getCols();
    }

    <T> T get(String str);

    <T> T get(String str, T t);

    List<String> getAttributeKeys();

    int getBatch();

    int getChannels();

    CodecType getCodecType();

    ColorFormat getColorFormat() {
        throw new UnsupportedOperationException("not support for " + getMediaType());
    }

    ColorSpace getColorSpace() {
        throw new UnsupportedOperationException("not suppor for " + getMediaType());
    }

    int getCols();

    Rect getCropRect();

    DataType getDataType();

    Rect getDimensionRect() {
        return new Rect(0, 0, getCols(), getRows());
    }

    FlipType getFlipType();

    MediaType getMediaType();

    List<? extends MediaFormat> getPlanesFormat();

    int getRotation();

    int getRows();

    Shape getShape();

    SplitType getSplitType();

    long getUsage();

    boolean isValid();

    <T> T remove(String str);

    long size();

    MutableMediaFormat toMutableFormat();

    int total() {
        return getChannels() * dimension();
    }

    static MediaFormatBuilder newBuilder(MediaType mediaType) {
        return new MediaFormatBuilder().setMediaType(mediaType);
    }
}
