package com.samsung.android.app.sdk.deepsky.objectcapture.impl;

import A.a;
import Ae.b;
import He.F;
import L1.d;
import Sf.q;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Looper;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.MaskedObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCapture;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureDrawHelperImpl;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjectExtractor;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkVideoObjectRemover;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.i;
import me.k;
import me.x;
import ne.C1192j;
import ne.C1194l;
import ne.C1202t;
import srib.vizinsight.dvs.UNF_Image_Clipper_Interface;
import srib.vizinsight.dvs.UNF_Object_RoI_Info;
import srib.vizinsight.dvs.UNF_Object_Segment_Info;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 Q2\u00020\u0001:\u0001QB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u000b\u001a\u00020\n*\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000f\u001a\u00020\u000e*\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0014\u001a\u00020\u0013*\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J3\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n0\u00182\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001c\u001a\u00020\u001b*\u00020\rH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u0006*\u00020\rH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010 \u001a\u00020\u0006*\u00020\rH\u0002¢\u0006\u0004\b \u0010\u001fJ\u001f\u0010$\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b$\u0010%J\u001f\u0010&\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b&\u0010'J\u0017\u0010+\u001a\u00020*2\u0006\u0010)\u001a\u00020(H\u0002¢\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u0011H\u0016¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\u0006H\u0016¢\u0006\u0004\b/\u0010\bJ'\u0010-\u001a\u0002032\u0006\u0010!\u001a\u00020\u00112\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u000200H\u0016¢\u0006\u0004\b-\u00104J/\u0010;\u001a\u00020:2\u0006\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u000e2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\u001bH\u0017¢\u0006\u0004\b;\u0010<J'\u0010>\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\"2\u0006\u0010=\u001a\u00020\nH\u0016¢\u0006\u0004\b>\u0010?J/\u0010>\u001a\u00020:2\u0006\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020@2\u0006\u00109\u001a\u00020\u001bH\u0016¢\u0006\u0004\b>\u0010BJ\u000f\u0010C\u001a\u00020\u0006H\u0016¢\u0006\u0004\bC\u0010\bJ\u000f\u0010D\u001a\u00020\u001bH\u0016¢\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u00020\u001bH\u0016¢\u0006\u0004\bF\u0010EJ\u0017\u0010H\u001a\u00020G2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\bH\u0010IR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010JR\u001b\u0010P\u001a\u00020K8BX\u0002¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O¨\u0006R"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/SribObjectCaptureImpl;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCapture;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lme/x;", "checkThread", "()V", "Lsrib/vizinsight/dvs/UNF_Object_RoI_Info;", "Landroid/graphics/Rect;", "toRect", "(Lsrib/vizinsight/dvs/UNF_Object_RoI_Info;)Landroid/graphics/Rect;", "Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;", "", "dump", "(Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;)Ljava/lang/String;", "Landroid/graphics/Bitmap;", "inputImage", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "toObjectResult", "(Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;Landroid/graphics/Bitmap;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "mask", "rect", "Lme/i;", "recreateBitmap", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/Rect;)Lme/i;", "", "isEmpty", "(Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;)Z", "check", "(Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;)V", "recycleObjectsBitmap", "bitmap", "", "array", "maskedBitmapByIntArray", "(Landroid/graphics/Bitmap;[I)Landroid/graphics/Bitmap;", "extractRectByIntArray", "(Landroid/graphics/Bitmap;[I)Landroid/graphics/Rect;", "", "message", "", "error", "(Ljava/lang/Object;)Ljava/lang/Void;", "capture", "(Landroid/graphics/Bitmap;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "init", "", "x", "y", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "(Landroid/graphics/Bitmap;FF)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "inputPath", "outputPath", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$ObjectMask;", "videoInputMask", "mediaScan", "", "removeVideoObject", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$ObjectMask;Z)I", "roi", "removeImageObject", "(Landroid/graphics/Bitmap;[ILandroid/graphics/Rect;)Landroid/graphics/Bitmap;", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;", "imageInputMask", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;Z)I", "release", "isObjectCaptureSupported", "()Z", "isObjectRemoveSupported", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper;", "getObjectCaptureDrawHelper", "(Landroid/content/Context;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper;", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/SribCaptureWrapper;", "instance$delegate", "Lme/f;", "getInstance", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/SribCaptureWrapper;", "instance", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SribObjectCaptureImpl implements ObjectCapture {
    private static final Object CLASS_LOCK = new Object();
    private static final Companion Companion = new Companion((e) null);
    private static final AtomicBoolean IS_INIT_STATE = new AtomicBoolean();
    private static final int SINGLE_OBJECT_SIZE = 1;
    private static final int SUCCESS_RETURN_ID = 0;
    private static final String TAG = "SribObjectCaptureImpl";
    private final Context context;
    private final f instance$delegate = d.q(new q(2, this));

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/SribObjectCaptureImpl$Companion;", "", "<init>", "()V", "CLASS_LOCK", "Ljava/lang/Object;", "IS_INIT_STATE", "Ljava/util/concurrent/atomic/AtomicBoolean;", "TAG", "", "SUCCESS_RETURN_ID", "", "SINGLE_OBJECT_SIZE", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public SribObjectCaptureImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final void check(UNF_Object_Segment_Info uNF_Object_Segment_Info) {
        if (!isEmpty(uNF_Object_Segment_Info)) {
            if (uNF_Object_Segment_Info.mSingleRect == null) {
                throw new IllegalStateException("Required value was null.");
            } else if (uNF_Object_Segment_Info.mSingleBitmap == null) {
                throw new IllegalStateException("Required value was null.");
            } else if (uNF_Object_Segment_Info.solutionInfo == null) {
                throw new IllegalStateException("Required value was null.");
            }
        }
    }

    private final void checkThread() {
        if (j.a(Thread.currentThread(), Looper.getMainLooper().getThread())) {
            throw new IllegalStateException("Should be called on worker thread");
        }
    }

    private final String dump(UNF_Object_Segment_Info uNF_Object_Segment_Info) {
        Integer num;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("UNF_Object_Segment_RESULT: " + uNF_Object_Segment_Info.hashCode() + "}");
        sb2.append(10);
        sb2.append(" mSalientNum: " + uNF_Object_Segment_Info.mSalientNum);
        sb2.append(10);
        if (!isEmpty(uNF_Object_Segment_Info)) {
            UNF_Object_RoI_Info uNF_Object_RoI_Info = uNF_Object_Segment_Info.mSingleRect;
            j.d(uNF_Object_RoI_Info, "mSingleRect");
            sb2.append(" mSingleRect: " + toRect(uNF_Object_RoI_Info));
            sb2.append(10);
            Bitmap bitmap = uNF_Object_Segment_Info.mSingleBitmap;
            Integer num2 = null;
            if (bitmap != null) {
                num = Integer.valueOf(bitmap.getWidth());
            } else {
                num = null;
            }
            Bitmap bitmap2 = uNF_Object_Segment_Info.mSingleBitmap;
            if (bitmap2 != null) {
                num2 = Integer.valueOf(bitmap2.getHeight());
            }
            sb2.append(" mSingleBitmap: " + num + "x" + num2);
            sb2.append(10);
        }
        sb2.append(" errorCode: " + uNF_Object_Segment_Info.errorCode);
        sb2.append(10);
        sb2.append(" solutionInfo: " + uNF_Object_Segment_Info.solutionInfo);
        sb2.append(10);
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    private final Void error(Object obj) {
        throw new IllegalStateException(obj.toString());
    }

    private final Rect extractRectByIntArray(Bitmap bitmap, int[] iArr) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int length = iArr.length;
        int i2 = -1;
        int i7 = -1;
        for (int i8 = 0; i8 < length; i8++) {
            if (iArr[i8] != 0) {
                int floorMod = Math.floorMod(i8, bitmap.getWidth());
                int floorDiv = Math.floorDiv(i8, bitmap.getWidth());
                if (width > floorMod) {
                    width = floorMod;
                } else if (height > floorDiv) {
                    height = floorDiv;
                } else if (i2 < floorMod) {
                    i2 = floorMod;
                } else if (i7 < floorDiv) {
                    i7 = floorDiv;
                }
            }
        }
        Rect rect = new Rect();
        StringBuilder h5 = a.h(width, height, "rect -> left: ", " top: ", " right: ");
        h5.append(i2);
        h5.append(" bottom: ");
        h5.append(i7);
        LibLogger.i(TAG, h5.toString());
        rect.set(width, height, i2, i7);
        return rect;
    }

    private final SribCaptureWrapper getInstance() {
        return (SribCaptureWrapper) this.instance$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final SribCaptureWrapperImpl instance_delegate$lambda$2(SribObjectCaptureImpl sribObjectCaptureImpl) {
        Object obj;
        try {
            obj = new SribCaptureWrapperImpl();
        } catch (Throwable th) {
            obj = L2.a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 == null) {
            return (SribCaptureWrapperImpl) obj;
        }
        String message = a7.getMessage();
        if (message == null) {
            message = "";
        }
        sribObjectCaptureImpl.error(message);
        throw new RuntimeException();
    }

    private final boolean isEmpty(UNF_Object_Segment_Info uNF_Object_Segment_Info) {
        if (uNF_Object_Segment_Info.mSalientNum < 1) {
            return true;
        }
        return false;
    }

    private final Bitmap maskedBitmapByIntArray(Bitmap bitmap, int[] iArr) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr2 = new int[(width * height)];
        bitmap.getPixels(iArr2, 0, width, 0, 0, width, height);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        j.d(createBitmap, "createBitmap(...)");
        int[] iArr3 = new int[iArr.length];
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] != 0) {
                iArr3[i2] = iArr2[i2];
            } else {
                iArr3[i2] = 0;
            }
        }
        createBitmap.setPixels(iArr3, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    private final i recreateBitmap(Bitmap bitmap, Bitmap bitmap2, Rect rect) {
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, config);
        j.d(createBitmap, "createBitmap(...)");
        Bitmap.Config config2 = bitmap.getConfig();
        if (config2 != null) {
            config = config2;
        }
        Bitmap createBitmap2 = Bitmap.createBitmap(bitmap.copy(config, true), rect.left, rect.top, rect.width(), rect.height());
        j.d(createBitmap2, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(createBitmap2, 0.0f, 0.0f, paint);
        createBitmap2.recycle();
        return new i(createBitmap, rect);
    }

    private final void recycleObjectsBitmap(UNF_Object_Segment_Info uNF_Object_Segment_Info) {
        Bitmap[] bitmapArr = uNF_Object_Segment_Info.mObjectsBitmap;
        if (bitmapArr != null) {
            ArrayList<Bitmap> arrayList = new ArrayList<>();
            for (Bitmap bitmap : bitmapArr) {
                if (!bitmap.isRecycled()) {
                    arrayList.add(bitmap);
                }
            }
            for (Bitmap recycle : arrayList) {
                recycle.recycle();
            }
        }
    }

    private final ObjectResult toObjectResult(UNF_Object_Segment_Info uNF_Object_Segment_Info, Bitmap bitmap) {
        String str;
        String str2;
        check(uNF_Object_Segment_Info);
        if (isEmpty(uNF_Object_Segment_Info)) {
            ObjectInfo objectInfo = new ObjectInfo(bitmap, new Rect());
            ObjectInfo objectInfo2 = new ObjectInfo(bitmap, new Rect());
            int i2 = uNF_Object_Segment_Info.errorCode;
            String str3 = uNF_Object_Segment_Info.solutionInfo;
            if (str3 == null) {
                str2 = "";
            } else {
                str2 = str3;
            }
            return new ObjectResult(false, objectInfo, objectInfo2, C1202t.d, i2, str2);
        }
        Bitmap bitmap2 = uNF_Object_Segment_Info.mSingleBitmap;
        j.d(bitmap2, "mSingleBitmap");
        UNF_Object_RoI_Info uNF_Object_RoI_Info = uNF_Object_Segment_Info.mSingleRect;
        j.d(uNF_Object_RoI_Info, "mSingleRect");
        i recreateBitmap = recreateBitmap(bitmap, bitmap2, toRect(uNF_Object_RoI_Info));
        ObjectInfo objectInfo3 = new ObjectInfo((Bitmap) recreateBitmap.d, (Rect) recreateBitmap.e);
        ArrayList arrayList = new ArrayList();
        if (uNF_Object_Segment_Info.mSalientNum == 1) {
            arrayList.add(objectInfo3);
        } else {
            int length = uNF_Object_Segment_Info.mObjectsBitmap.length;
            for (int i7 = 0; i7 < length; i7++) {
                Bitmap bitmap3 = uNF_Object_Segment_Info.mObjectsBitmap[i7];
                j.d(bitmap3, "get(...)");
                UNF_Object_RoI_Info uNF_Object_RoI_Info2 = uNF_Object_Segment_Info.mObjectsRect[i7];
                j.d(uNF_Object_RoI_Info2, "get(...)");
                i recreateBitmap2 = recreateBitmap(bitmap, bitmap3, toRect(uNF_Object_RoI_Info2));
                arrayList.add(new ObjectInfo((Bitmap) recreateBitmap2.d, (Rect) recreateBitmap2.e));
            }
        }
        recycleObjectsBitmap(uNF_Object_Segment_Info);
        int i8 = uNF_Object_Segment_Info.errorCode;
        String str4 = uNF_Object_Segment_Info.solutionInfo;
        if (str4 == null) {
            str = "";
        } else {
            str = str4;
        }
        return new ObjectResult(true, objectInfo3, objectInfo3, arrayList, i8, str);
    }

    private final Rect toRect(UNF_Object_RoI_Info uNF_Object_RoI_Info) {
        return new Rect((int) uNF_Object_RoI_Info.f5113x1, (int) uNF_Object_RoI_Info.f5115y1, (int) uNF_Object_RoI_Info.f5114x2, (int) uNF_Object_RoI_Info.f5116y2);
    }

    public ObjectResult capture(Bitmap bitmap) {
        Object obj;
        j.e(bitmap, "bitmap");
        checkThread();
        if (IS_INIT_STATE.get()) {
            UNF_Object_Segment_Info uNF_Object_Segment_Info = new UNF_Object_Segment_Info();
            uNF_Object_Segment_Info.mSalientNum = 0;
            synchronized (CLASS_LOCK) {
                try {
                    getInstance().capture(bitmap, uNF_Object_Segment_Info);
                    obj = x.f4917a;
                } catch (Throwable th) {
                    obj = L2.a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    LibLogger.e(TAG, ScreenShotFilterType.CAPTURE, a7);
                }
            }
            String dump = dump(uNF_Object_Segment_Info);
            LibLogger.i(TAG, "capture, outResult: " + dump);
            return toObjectResult(uNF_Object_Segment_Info, bitmap);
        }
        throw new IllegalStateException("init api must be called first");
    }

    public ObjectCaptureDrawHelper getObjectCaptureDrawHelper(Context context2) {
        j.e(context2, "context");
        return new ObjectCaptureDrawHelperImpl(context2);
    }

    public void init() {
        checkThread();
        synchronized (CLASS_LOCK) {
            AtomicBoolean atomicBoolean = IS_INIT_STATE;
            if (atomicBoolean.get()) {
                LibLogger.w(TAG, "init, already init state");
                return;
            }
            int init = getInstance().init();
            if (init == 0) {
                atomicBoolean.set(true);
                String version = getInstance().getVersion();
                LibLogger.i(TAG, "init, version: " + version);
                return;
            }
            throw new IllegalStateException(("Init failed, ret: " + init).toString());
        }
    }

    public boolean isObjectCaptureSupported() {
        boolean Is_Support_Unified_Image_Clipper = UNF_Image_Clipper_Interface.getInstance().Is_Support_Unified_Image_Clipper();
        LibLogger.w(TAG, "isObjectCaptureSupported, isSupported: " + Is_Support_Unified_Image_Clipper);
        return Is_Support_Unified_Image_Clipper;
    }

    public boolean isObjectRemoveSupported() {
        boolean z;
        if (!VexFwkObjectExtractor.Companion.isAvailable() || !VexFwkImageObjectRemover.Companion.isAvailable()) {
            z = false;
        } else {
            z = true;
        }
        LibLogger.w(TAG, "isObjectRemoveSupported, isSupported: " + z);
        return z;
    }

    public void release() {
        checkThread();
        LibLogger.i(TAG, "release SribObjectCapture");
        synchronized (CLASS_LOCK) {
            if (IS_INIT_STATE.getAndSet(false)) {
                getInstance().release();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        He.F.u(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap removeImageObject(android.graphics.Bitmap r3, int[] r4, android.graphics.Rect r5) {
        /*
            r2 = this;
            java.lang.String r0 = "bitmap"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "mask"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.String r0 = "roi"
            kotlin.jvm.internal.j.e(r5, r0)
            r2.checkThread()
            java.util.concurrent.atomic.AtomicBoolean r2 = IS_INIT_STATE
            boolean r2 = r2.get()
            if (r2 == 0) goto L_0x003c
            com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover r2 = new com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover
            r2.<init>()
            r0 = 1
            r1 = 0
            com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover.configure$default(r2, r1, r0, r1)
            java.util.concurrent.CompletableFuture r3 = r2.removeObject(r3, r4, r5)     // Catch:{ all -> 0x0035 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x0035 }
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3     // Catch:{ all -> 0x0035 }
            He.F.u(r2, r1)
            kotlin.jvm.internal.j.b(r3)
            return r3
        L_0x0035:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r4 = move-exception
            He.F.u(r2, r3)
            throw r4
        L_0x003c:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "init api must be called first"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.impl.SribObjectCaptureImpl.removeImageObject(android.graphics.Bitmap, int[], android.graphics.Rect):android.graphics.Bitmap");
    }

    public int removeVideoObject(String str, String str2, VexFwkVideoObjectRemover.ObjectMask objectMask, boolean z) {
        j.e(str, "inputPath");
        j.e(str2, "outputPath");
        j.e(objectMask, "videoInputMask");
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        He.F.u(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003c, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int removeImageObject(java.lang.String r2, java.lang.String r3, com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover.ObjectMask r4, boolean r5) {
        /*
            r1 = this;
            java.lang.String r5 = "inputPath"
            kotlin.jvm.internal.j.e(r2, r5)
            java.lang.String r5 = "outputPath"
            kotlin.jvm.internal.j.e(r3, r5)
            java.lang.String r5 = "imageInputMask"
            kotlin.jvm.internal.j.e(r4, r5)
            r1.checkThread()
            java.util.concurrent.atomic.AtomicBoolean r1 = IS_INIT_STATE
            boolean r1 = r1.get()
            if (r1 == 0) goto L_0x003d
            com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover r1 = new com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover
            r1.<init>()
            r5 = 0
            r0 = 1
            com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover.configure$default(r1, r5, r0, r5)
            java.util.concurrent.CompletableFuture r2 = r1.removeObject(r2, r3, r4, r0)     // Catch:{ all -> 0x0036 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0036 }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r2 = (com.samsung.android.vexfwk.param.VexFwkNodeResultCode) r2     // Catch:{ all -> 0x0036 }
            He.F.u(r1, r5)
            int r1 = r2.getValue()
            return r1
        L_0x0036:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r3 = move-exception
            He.F.u(r1, r2)
            throw r3
        L_0x003d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "init api must be called first"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.impl.SribObjectCaptureImpl.removeImageObject(java.lang.String, java.lang.String, com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover$ObjectMask, boolean):int");
    }

    public MaskedObjectResult capture(Bitmap bitmap, float f, float f5) {
        Throwable th;
        Bitmap bitmap2 = bitmap;
        j.e(bitmap2, "bitmap");
        checkThread();
        if (!IS_INIT_STATE.get()) {
            throw new IllegalStateException("init api must be called first");
        } else if (!VexFwkObjectExtractor.Companion.isAvailable()) {
            LibLogger.i(TAG, "VexFwkAILasso is not available. Returning dummy result.");
            return new MaskedObjectResult(false, (Bitmap) null, (int[]) null, (Rect) null, 0, (String) null, 48, (e) null);
        } else {
            LibLogger.i(TAG, "segmentObject start");
            VexFwkObjectExtractor vexFwkObjectExtractor = new VexFwkObjectExtractor();
            vexFwkObjectExtractor.configure();
            try {
                int[] iArr = vexFwkObjectExtractor.extractObject(bitmap2, f, f5).get();
                F.u(vexFwkObjectExtractor, (Throwable) null);
                j.b(iArr);
                Rect extractRectByIntArray = extractRectByIntArray(bitmap2, iArr);
                Bitmap maskedBitmapByIntArray = maskedBitmapByIntArray(bitmap2, iArr);
                Bitmap createBitmap = Bitmap.createBitmap(maskedBitmapByIntArray, extractRectByIntArray.left, extractRectByIntArray.top, extractRectByIntArray.width(), extractRectByIntArray.height());
                j.d(createBitmap, "createBitmap(...)");
                maskedBitmapByIntArray.recycle();
                int width = createBitmap.getWidth();
                int height = createBitmap.getHeight();
                LibLogger.i(TAG, "croppedBitmap -> width: " + width + " height: " + height);
                int length = iArr.length;
                String R02 = C1194l.R0(C1192j.y0(iArr), (String) null, (String) null, (String) null, (b) null, 63);
                LibLogger.i(TAG, "maskSegmented (" + length + " " + R02 + ")");
                return new MaskedObjectResult(true, createBitmap, iArr, extractRectByIntArray, 0, (String) null, 48, (e) null);
            } catch (Throwable th2) {
                F.u(vexFwkObjectExtractor, th);
                throw th2;
            }
        }
    }
}
