package com.samsung.android.sdk.sgpl.graphics;

import Ad.C0720a;
import android.graphics.BitmapFactory;
import com.samsung.android.sdk.sgpl.graphics.Build;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CodecConfig {
    static final int MAX_JAVA_HEAP_SIZE = 5242880;
    public final ImageCodec imageCodec;
    final boolean inDebug;
    final boolean inHeap;
    public final VideoCodec videoCodec;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        ImageCodecImpl[] imageCodecs;
        CodecBitmapPool inBitmapPool;
        CustomCodec inCustomCodec;
        boolean inDebug;
        boolean inHeap;
        CodecPolicy inImageCodecPolicy;
        BiFunction<String, String, String> inLogBuilder;
        CodecPolicy inVideoCodecPolicy;
        VideoCodecImpl[] videoCodecs;

        /* JADX WARNING: type inference failed for: r5v2, types: [java.util.function.Predicate, java.lang.Object] */
        private ImageCodecImpl[] createImageCodec(CodecPolicy codecPolicy) {
            ArrayList arrayList = (ArrayList) Stream.of(codecPolicy == CodecPolicy.PreferVendor ? new ImageCodecImpl[]{createVendorImageCodec(), new ImageCodecImpl()} : codecPolicy == CodecPolicy.PreferAndroid ? new ImageCodecImpl[]{new ImageCodecImpl(), createVendorImageCodec()} : codecPolicy == CodecPolicy.VendorOnly ? new ImageCodecImpl[]{createVendorImageCodec()} : new ImageCodecImpl[]{new ImageCodecImpl()}).filter(new Object()).collect(Collectors.toCollection(new C0720a(1)));
            if (arrayList.isEmpty()) {
                arrayList.add(new ImageCodecImpl());
            }
            return (ImageCodecImpl[]) arrayList.toArray(new ImageCodecImpl[0]);
        }

        private ImageCodecImpl createVendorImageCodec() {
            int i2 = Build.SEM.SDK_INT;
            if (i2 >= 150000) {
                return new ImageCodecImpl34();
            }
            if (i2 >= 130000) {
                return new ImageCodecImpl31();
            }
            if (i2 >= 120000) {
                return new ImageCodecImpl30();
            }
            if (i2 > 0) {
                return new ImageCodecImpl28();
            }
            return null;
        }

        private VideoCodecImpl[] createVideoCodec(CodecPolicy codecPolicy) {
            int i2 = Build.SEM.SDK_INT;
            if (i2 >= 130000) {
                return new VideoCodecImpl[]{new VideoCodecImpl31()};
            } else if (i2 < 100000) {
                return new VideoCodecImpl[]{new VideoCodecImpl()};
            } else {
                return new VideoCodecImpl[]{new VideoCodecImpl28()};
            }
        }

        public CodecConfig build() {
            CodecPolicy codecPolicy = this.inImageCodecPolicy;
            if (codecPolicy == null) {
                codecPolicy = CodecPolicy.PreferVendor;
            }
            this.imageCodecs = createImageCodec(codecPolicy);
            CodecPolicy codecPolicy2 = this.inVideoCodecPolicy;
            if (codecPolicy2 == null) {
                codecPolicy2 = CodecPolicy.PreferVendor;
            }
            this.videoCodecs = createVideoCodec(codecPolicy2);
            BiFunction<String, String, String> biFunction = this.inLogBuilder;
            if (biFunction != null) {
                Log.logBuilder = biFunction;
            }
            CodecBitmapPool codecBitmapPool = this.inBitmapPool;
            if (codecBitmapPool != null) {
                BitmapToolkit.bitmapPool = codecBitmapPool;
            }
            BitmapToolkit.DEBUG = this.inDebug;
            return new CodecConfig(this, 0);
        }

        public Builder withBitmapPool(CodecBitmapPool codecBitmapPool) {
            this.inBitmapPool = codecBitmapPool;
            return this;
        }

        public Builder withCustomCodec(CustomCodec customCodec) {
            this.inCustomCodec = customCodec;
            return this;
        }

        public Builder withDebug(boolean z) {
            this.inDebug = z;
            return this;
        }

        public Builder withHeap(boolean z) {
            this.inHeap = z;
            return this;
        }

        public Builder withImageCodecPolicy(CodecPolicy codecPolicy) {
            this.inImageCodecPolicy = codecPolicy;
            return this;
        }

        public Builder withLogBuilder(BiFunction<String, String, String> biFunction) {
            this.inLogBuilder = biFunction;
            return this;
        }

        public Builder withVideoCodecPolicy(CodecPolicy codecPolicy) {
            this.inVideoCodecPolicy = codecPolicy;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CodecPolicy {
        PreferVendor,
        PreferAndroid,
        VendorOnly,
        AndroidOnly
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CustomCodec {
        boolean decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options);

        boolean decodeFile(String str, BitmapFactory.Options options) {
            return false;
        }

        boolean decodeStream(InputStream inputStream, BitmapFactory.Options options) {
            return false;
        }
    }

    public /* synthetic */ CodecConfig(Builder builder, int i2) {
        this(builder);
    }

    private CodecConfig(Builder builder) {
        this.inDebug = builder.inDebug;
        this.inHeap = builder.inHeap;
        this.imageCodec = new ImageCodec(builder);
        this.videoCodec = new VideoCodec(builder);
    }
}
