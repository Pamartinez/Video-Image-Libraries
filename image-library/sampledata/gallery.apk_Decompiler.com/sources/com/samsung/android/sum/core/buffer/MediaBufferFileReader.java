package com.samsung.android.sum.core.buffer;

import Bd.C0726b;
import android.media.ExifInterface;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MediaFormatBuilder;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaBufferFileReader {
    private static final String TAG = Def.tagOf((Class<?>) MediaBufferFileReader.class);
    private static final List<String> imageExt;
    private BiFunction<MediaFormat, String, MediaBuffer> compressedMediaReader;
    private Consumer<ExifInterface> exifConsumer;
    private MediaFormat format;
    private final List<String> paths;

    /* renamed from: com.samsung.android.sum.core.buffer.MediaBufferFileReader$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$MediaType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.sum.core.types.MediaType[] r0 = com.samsung.android.sum.core.types.MediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$MediaType = r0
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.RAW_IMAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MediaType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.COMPRESSED_IMAGE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MediaType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.COMPRESSED_VIDEO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.MediaBufferFileReader.AnonymousClass1.<clinit>():void");
        }
    }

    static {
        List<String> list = (List) Arrays.stream(ColorFormat.values()).skip(0).map(new C0923a(22)).collect(Collectors.toList());
        imageExt = list;
        list.addAll(Arrays.asList(new String[]{"i420", "jpg", IFormat.FORMAT_HEIC, "png", IFormat.FORMAT_JPEG}));
    }

    public MediaBufferFileReader(String... strArr) {
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str = strArr[i2];
            if (new File(str).exists()) {
                i2++;
            } else {
                throw new FileNotFoundException(C0212a.l("no file for ", str));
            }
        }
        this.paths = Arrays.asList(strArr);
    }

    public static String getExtension(String str) {
        return str.substring(str.lastIndexOf(".") + 1).toLowerCase(Locale.ROOT);
    }

    /* access modifiers changed from: private */
    /* renamed from: getFormatFromName */
    public MediaFormat lambda$read$2(String str) {
        String extension = getExtension(str);
        if (isImage(extension)) {
            return getImageFormatFromName(str, extension);
        }
        if (isVideo(extension)) {
            return getVideoFormatFromName(str, extension);
        }
        throw new UnsupportedOperationException(C0212a.l("not supported yet for ", extension));
    }

    /* JADX WARNING: type inference failed for: r7v10, types: [java.lang.Object, java.util.function.IntFunction] */
    private MediaFormat getImageFormatFromName(String str, String str2) {
        ColorFormat valueOf;
        MediaFormatBuilder newBuilder = MediaFormat.newBuilder();
        ColorFormat colorFormat = ColorFormat.NONE;
        if (Arrays.asList(new String[]{"jpg", IFormat.FORMAT_HEIC, "png", IFormat.FORMAT_JPEG}).contains(str2.toLowerCase())) {
            newBuilder.setMediaType(MediaType.COMPRESSED_IMAGE);
        } else {
            newBuilder.setMediaType(MediaType.RAW_IMAGE);
            if ("i420".equals(str2)) {
                valueOf = ColorFormat.YUV420;
            } else {
                valueOf = ColorFormat.valueOf(str2.toUpperCase(Locale.ROOT));
            }
            colorFormat = valueOf;
            Matcher matcher = Pattern.compile("\\d+x\\d+").matcher(str);
            if (matcher.find()) {
                Integer[] numArr = (Integer[]) Arrays.stream(matcher.group().split("x")).map(new C0923a(21)).toArray(new Object());
                newBuilder.setShape(numArr[0].intValue(), numArr[1].intValue());
            } else {
                throw new UnsupportedOperationException("not supported yet");
            }
        }
        newBuilder.setColorFormat(colorFormat);
        newBuilder.setDataType(DataType.of(DataType.U8, colorFormat.getChannels()));
        return newBuilder.build();
    }

    private MediaFormat getVideoFormatFromName(String str, String str2) {
        return MediaFormat.newVideoBuilder().asCompressed().setDataType(DataType.U8C3).build();
    }

    private boolean isImage(String str) {
        return imageExt.contains(str);
    }

    private boolean isVideo(String str) {
        return IFormat.FORMAT_MP4.equals(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer[] lambda$getImageFormatFromName$1(int i2) {
        return new Integer[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$read$3(MediaFormat mediaFormat, String str, BiFunction biFunction) {
        return (MediaBuffer) biFunction.apply(mediaFormat, str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ IllegalArgumentException lambda$read$4() {
        return new IllegalArgumentException("not implement internal compress image reader yet, plz should set explicitly");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$read$5(MediaFormat mediaFormat, String str, BiFunction biFunction) {
        return (MediaBuffer) biFunction.apply(mediaFormat, str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ IllegalArgumentException lambda$read$6() {
        return new IllegalArgumentException("not implement internal compress image reader yet, plz should set explicitly");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$read$7(String str) {
        MediaFormat mediaFormat = (MediaFormat) Optional.ofNullable(this.format).orElseGet(new C0726b(5, this, str));
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$MediaType[mediaFormat.getMediaType().ordinal()];
        if (i2 == 1) {
            return readRawImage(mediaFormat, str);
        }
        if (i2 == 2) {
            MediaBuffer mediaBuffer = (MediaBuffer) Optional.ofNullable(this.compressedMediaReader).map(new z(mediaFormat, str, 0)).orElseThrow(new u(1));
            ExifInterface readExif = readExif(str);
            if (readExif != null) {
                int i7 = 0;
                switch (readExif.getAttributeInt("Orientation", 0)) {
                    case 3:
                    case 4:
                        i7 = MOCRLang.KHMER;
                        break;
                    case 5:
                    case 6:
                        i7 = 90;
                        break;
                    case 7:
                    case 8:
                        i7 = 270;
                        break;
                }
                if (i7 != 0) {
                    mediaBuffer.setExtra(Message.KEY_ROTATION, Integer.valueOf(i7));
                }
                Consumer<ExifInterface> consumer = this.exifConsumer;
                if (consumer != null) {
                    consumer.accept(readExif);
                }
            }
            return mediaBuffer;
        } else if (i2 == 3) {
            return (MediaBuffer) Optional.ofNullable(this.compressedMediaReader).map(new z(mediaFormat, str, 1)).orElseThrow(new u(2));
        } else {
            throw new UnsupportedOperationException("not support yet");
        }
    }

    private static ExifInterface readExif(String str) {
        ExifInterface exifInterface;
        Exception e;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
            exifInterface = new ExifInterface(fileInputStream);
            try {
                fileInputStream.close();
                return exifInterface;
            } catch (Exception e7) {
                e = e7;
                e.printStackTrace();
                return exifInterface;
            }
        } catch (Exception e8) {
            e = e8;
            exifInterface = null;
            e.printStackTrace();
            return exifInterface;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0062 A[SYNTHETIC, Splitter:B:23:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006d A[SYNTHETIC, Splitter:B:29:0x006d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.sum.core.buffer.MediaBuffer readRawImage(com.samsung.android.sum.core.format.MediaFormat r7, java.lang.String r8) {
        /*
            r6 = this;
            java.io.File r6 = new java.io.File
            r6.<init>(r8)
            boolean r0 = r6.exists()
            java.lang.String r1 = "not exist input file "
            java.lang.String r8 = i.C0212a.l(r1, r8)
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            com.samsung.android.sum.core.Def.check(r0, r8, r2)
            long r2 = r6.length()
            long r4 = r7.size()
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 < 0) goto L_0x0022
            r1 = 1
        L_0x0022:
            com.samsung.android.sum.core.Def.require(r1)
            r8 = 0
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x005b, all -> 0x0059 }
            r0.<init>(r6)     // Catch:{ IOException -> 0x005b, all -> 0x0059 }
            long r1 = r6.length()     // Catch:{ IOException -> 0x0057 }
            int r6 = (int) r1     // Catch:{ IOException -> 0x0057 }
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocateDirect(r6)     // Catch:{ IOException -> 0x0057 }
            java.nio.ByteOrder r1 = java.nio.ByteOrder.nativeOrder()     // Catch:{ IOException -> 0x0057 }
            java.nio.ByteBuffer r6 = r6.order(r1)     // Catch:{ IOException -> 0x0057 }
            java.nio.channels.FileChannel r1 = r0.getChannel()     // Catch:{ IOException -> 0x0057 }
            r1.read(r6)     // Catch:{ IOException -> 0x0057 }
            java.lang.Object[] r6 = new java.lang.Object[]{r7, r6}     // Catch:{ IOException -> 0x0057 }
            com.samsung.android.sum.core.buffer.MediaBuffer r6 = com.samsung.android.sum.core.buffer.MediaBuffer.of((java.lang.Object[]) r6)     // Catch:{ IOException -> 0x0057 }
            r0.close()     // Catch:{ IOException -> 0x004f }
            return r6
        L_0x004f:
            r7 = move-exception
            r7.printStackTrace()
            return r6
        L_0x0054:
            r6 = move-exception
            r8 = r0
            goto L_0x006b
        L_0x0057:
            r6 = move-exception
            goto L_0x005d
        L_0x0059:
            r6 = move-exception
            goto L_0x006b
        L_0x005b:
            r6 = move-exception
            r0 = r8
        L_0x005d:
            r6.printStackTrace()     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x006a
            r0.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r6 = move-exception
            r6.printStackTrace()
        L_0x006a:
            return r8
        L_0x006b:
            if (r8 == 0) goto L_0x0075
            r8.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0075:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.MediaBufferFileReader.readRawImage(com.samsung.android.sum.core.format.MediaFormat, java.lang.String):com.samsung.android.sum.core.buffer.MediaBuffer");
    }

    public MediaBuffer read() {
        List list = (List) this.paths.stream().map(new m(2, this)).filter(new C0927e(2)).collect(Collectors.toList());
        if (list.size() == 1) {
            return (MediaBuffer) list.get(0);
        }
        return MediaBuffer.newGroupAlloc().setBuffers(0, (List<MediaBuffer>) list).allocate();
    }

    public MediaBufferFileReader setCompressedMediaReader(BiFunction<MediaFormat, String, MediaBuffer> biFunction) {
        this.compressedMediaReader = biFunction;
        return this;
    }

    public MediaBufferFileReader setExifConsumer(Consumer<ExifInterface> consumer) {
        this.exifConsumer = consumer;
        return this;
    }

    public MediaBufferFileReader setFormat(MediaFormat mediaFormat) {
        this.format = mediaFormat;
        return this;
    }
}
