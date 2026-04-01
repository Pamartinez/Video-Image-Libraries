package com.samsung.android.motionphoto.utils.ex;

import Wc.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import ba.C0582a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferFileWriter;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.plugin.NativeUniImgpPlugin;
import com.samsung.android.sum.core.plugin.Plugin;
import com.samsung.android.sum.solution.filter.UniImgp;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import i.C0212a;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.InvalidParameterException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 .2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002./B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\r\u0010\fJ\u001f\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000e\u0010\fJ\u001f\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000f\u0010\fJ\u001f\u0010\u0010\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0010\u0010\fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ%\u0010\u001f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010$\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b$\u0010%J\u001d\u0010(\u001a\u00020\u00122\u0006\u0010'\u001a\u00020&2\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b(\u0010)R\"\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X.¢\u0006\u0012\n\u0004\b\u0004\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010\u0006¨\u00060"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/WrapVSWEnginePlugin;", "Lcom/samsung/android/sum/core/plugin/Plugin;", "Lcom/samsung/android/sum/core/plugin/ImgpPlugin;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/sum/core/buffer/MediaBuffer;", "ibuf", "Lcom/samsung/android/sum/core/buffer/MutableMediaBuffer;", "obuf", "x1Upscaler", "(Lcom/samsung/android/sum/core/buffer/MediaBuffer;Lcom/samsung/android/sum/core/buffer/MutableMediaBuffer;)Lcom/samsung/android/sum/core/buffer/MutableMediaBuffer;", "x4UpscalerUniImgp", "x4Upscaler", "x3Upscaler", "x2Upscaler", "fixture", "Lme/x;", "bindToFixture", "(Lcom/samsung/android/sum/core/plugin/ImgpPlugin;)V", "Ljava/nio/ByteBuffer;", "heapBuffer", "heapToDirectByteBuffer", "(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "", "byteArray", "byteArrayToDirectByteBuffer", "([B)Ljava/nio/ByteBuffer;", "", "scale", "VSWUpscaler", "(Lcom/samsung/android/sum/core/buffer/MediaBuffer;Lcom/samsung/android/sum/core/buffer/MutableMediaBuffer;I)Lcom/samsung/android/sum/core/buffer/MutableMediaBuffer;", "buf", "", "name", "dumpImage", "(Lcom/samsung/android/sum/core/buffer/MediaBuffer;Ljava/lang/String;)V", "Landroid/graphics/Bitmap;", "bitmap", "dumpBitmap", "(Landroid/graphics/Bitmap;Ljava/lang/String;)V", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "Companion", "OpType", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WrapVSWEnginePlugin implements Plugin<ImgpPlugin> {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    public Context context;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/WrapVSWEnginePlugin$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/WrapVSWEnginePlugin$OpType;", "", "<init>", "(Ljava/lang/String;I)V", "X2_UPSCALER", "X3_UPSCALER", "X4_UPSCALER", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum OpType {
        X2_UPSCALER,
        X3_UPSCALER,
        X4_UPSCALER;

        static {
            OpType[] $values;
            $ENTRIES = c.t($values);
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }
    }

    static {
        String tagOf = Def.tagOf((Class<?>) WrapVSWEnginePlugin.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public WrapVSWEnginePlugin(Context context2) {
        j.e(context2, "context");
        setContext(context2);
    }

    /* access modifiers changed from: private */
    public static final MFDescriptor bindToFixture$lambda$0() {
        return MFDescriptor.newBuilder().setPluginClass(WrapVSWEnginePlugin.class).build(ImgpDescriptor.class);
    }

    /* access modifiers changed from: private */
    public static final void bindToFixture$lambda$1(WrapVSWEnginePlugin wrapVSWEnginePlugin, Context context2) {
        wrapVSWEnginePlugin.setContext(context2);
    }

    /* access modifiers changed from: private */
    public static final Boolean dumpImage$lambda$9(MediaBuffer mediaBuffer, String str) {
        return Boolean.valueOf(new NativeUniImgpPlugin().writeCompressedImage(mediaBuffer, str));
    }

    private final MutableMediaBuffer x1Upscaler(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MediaFormat format = mediaBuffer.getFormat();
        Object typedData = mediaBuffer.getTypedData(ByteBuffer.class);
        j.d(typedData, "getTypedData(...)");
        mutableMediaBuffer.put(MediaBuffer.of(format, (ByteBuffer) typedData));
        new MediaBufferFileWriter("/sdcard/Pictures", "ByteArray_ByteBuffer_wrap.jpg").setCompressImageWriter(new a(1)).write(mutableMediaBuffer);
        return mutableMediaBuffer;
    }

    /* access modifiers changed from: private */
    public static final Boolean x1Upscaler$lambda$2(MediaBuffer mediaBuffer, String str) {
        return Boolean.valueOf(new NativeUniImgpPlugin().writeCompressedImage(mediaBuffer, str));
    }

    /* access modifiers changed from: private */
    public final MutableMediaBuffer x2Upscaler(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        String str = TAG;
        MediaFormat format = mutableMediaBuffer.getFormat();
        Log.d(str, "x2_Upsclaer E: " + mediaBuffer + " -> " + format);
        VSWUpscaler(mediaBuffer, mutableMediaBuffer, 2);
        StringBuilder sb2 = new StringBuilder("x2_Upscaler X: ");
        sb2.append(mutableMediaBuffer);
        Log.d(str, sb2.toString());
        return mutableMediaBuffer;
    }

    /* access modifiers changed from: private */
    public final MutableMediaBuffer x3Upscaler(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        String str = TAG;
        MediaFormat format = mutableMediaBuffer.getFormat();
        Log.d(str, "x3_Upsclaer E: " + mediaBuffer + " -> " + format);
        VSWUpscaler(mediaBuffer, mutableMediaBuffer, 3);
        StringBuilder sb2 = new StringBuilder("x3_Upscaler X: ");
        sb2.append(mutableMediaBuffer);
        Log.d(str, sb2.toString());
        return mutableMediaBuffer;
    }

    /* access modifiers changed from: private */
    public final MutableMediaBuffer x4Upscaler(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        VSWUpscaler(mediaBuffer, mutableMediaBuffer, 4);
        return mutableMediaBuffer;
    }

    private final MutableMediaBuffer x4UpscalerUniImgp(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        String str = TAG;
        MediaFormat format = mutableMediaBuffer.getFormat();
        Log.d(str, "VSW_Upscaler E: " + mediaBuffer + " -> " + format);
        MutableMediaFormat mutableImageOf = MediaFormat.mutableImageOf(new Object[0]);
        mutableImageOf.setRows(mediaBuffer.getRows() << 2);
        mutableImageOf.setCols(mediaBuffer.getCols() << 2);
        mutableImageOf.setDataType(mediaBuffer.getFormat().getDataType());
        mutableImageOf.set("interpolation", 2);
        MediaBuffer of2 = MediaBuffer.of(mutableImageOf);
        UniImgp.Option option = new UniImgp.Option();
        option.latestPluginsOrder();
        UniImgp.ofResize(option).run(mediaBuffer, of2);
        mutableMediaBuffer.put(of2);
        MediaFormat format2 = mutableMediaBuffer.getFormat();
        Log.d(str, "VSW_Upscaler X: " + mutableMediaBuffer + " and " + format2);
        return mutableMediaBuffer;
    }

    public final MutableMediaBuffer VSWUpscaler(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer, int i2) {
        j.e(mediaBuffer, "ibuf");
        j.e(mutableMediaBuffer, "obuf");
        String str = TAG;
        MediaFormat format = mutableMediaBuffer.getFormat();
        Log.d(str, "VSW_Upscaler E: " + mediaBuffer + " -> " + format);
        throw new InvalidParameterException(C0212a.j(i2, "not supported scale: ", " at VSW_Upscaler"));
    }

    public final ByteBuffer byteArrayToDirectByteBuffer(byte[] bArr) {
        j.e(bArr, "byteArray");
        Buffer flip = ByteBuffer.allocateDirect(bArr.length).put(bArr).flip();
        j.c(flip, "null cannot be cast to non-null type java.nio.ByteBuffer");
        return (ByteBuffer) flip;
    }

    public final void dumpBitmap(Bitmap bitmap, String str) {
        j.e(bitmap, "bitmap");
        j.e(str, "name");
        File file = new File("/sdcard/Pictures/".concat(str));
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        fileOutputStream.close();
    }

    public final void dumpImage(MediaBuffer mediaBuffer, String str) {
        j.e(mediaBuffer, "buf");
        j.e(str, "name");
        new MediaBufferFileWriter("/sdcard/Pictures", str).setCompressImageWriter(new a(2)).write(mediaBuffer);
    }

    public final Context getContext() {
        Context context2 = this.context;
        if (context2 != null) {
            return context2;
        }
        j.k("context");
        throw null;
    }

    public final ByteBuffer heapToDirectByteBuffer(ByteBuffer byteBuffer) {
        j.e(byteBuffer, "heapBuffer");
        byteBuffer.rewind();
        int remaining = byteBuffer.remaining();
        byte[] bArr = new byte[remaining];
        byteBuffer.get(bArr);
        Buffer flip = ByteBuffer.allocateDirect(remaining).put(bArr).flip();
        j.c(flip, "null cannot be cast to non-null type java.nio.ByteBuffer");
        return (ByteBuffer) flip;
    }

    public final void setContext(Context context2) {
        j.e(context2, "<set-?>");
        this.context = context2;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, com.samsung.android.sum.core.functional.DescriptorLoader] */
    public void bindToFixture(ImgpPlugin imgpPlugin) {
        j.e(imgpPlugin, "fixture");
        imgpPlugin.setDescriptorLoader(new Object());
        imgpPlugin.setImgProcessor(OpType.X2_UPSCALER, new d(this, 0));
        imgpPlugin.setImgProcessor(OpType.X3_UPSCALER, new d(this, 1));
        imgpPlugin.setImgProcessor(OpType.X4_UPSCALER, new d(this, 2));
        imgpPlugin.setInitializer(new C0582a(13, this));
    }
}
