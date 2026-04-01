package com.samsung.android.app.sdk.deepsky.objectcapture.multi;

import a.C0068a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureDrawHelperImpl;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureView;
import com.samsung.android.motionphoto.utils.ex.b;
import i3.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 X2\u00020\u0001:\u0001XB{\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0017\u001a\u00020\u0006\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010 \u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0018H\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u001fH\u0002¢\u0006\u0004\b\"\u0010#J3\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060(2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060$2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0006H\u0002¢\u0006\u0004\b)\u0010*J\u001f\u0010-\u001a\u00020\u00182\u0006\u0010,\u001a\u00020+2\u0006\u0010&\u001a\u00020\u0004H\u0002¢\u0006\u0004\b-\u0010.J\u001f\u00101\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u0004H\u0002¢\u0006\u0004\b1\u00102J\u0017\u00104\u001a\u0002032\u0006\u0010\u001d\u001a\u00020\u001cH\u0002¢\u0006\u0004\b4\u00105J-\u00108\u001a\u00020\u001f2\u0006\u00106\u001a\u00020\n2\u0006\u00107\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b8\u00109J\u0015\u0010:\u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b:\u0010;J\r\u0010<\u001a\u00020\u001f¢\u0006\u0004\b<\u0010#J\u0017\u0010>\u001a\u00020\u001f2\b\u0010=\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b>\u0010?J\u0017\u0010@\u001a\u00020\u001f2\b\u0010=\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b@\u0010AR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010BR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010CR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010DR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010ER\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010FR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\r\u0010GR\u0014\u0010\u000e\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010GR\u0014\u0010\u000f\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010GR\u0014\u0010\u0010\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010GR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010HR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010IR\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010JR\u0014\u0010\u0017\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010DR\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010KR\u001d\u0010N\u001a\b\u0012\u0004\u0012\u00020M0L8\u0006¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010QR$\u0010R\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bR\u0010K\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u0016\u0010W\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bW\u0010C¨\u0006Y"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/multi/MultiObjectViewManager;", "", "Landroid/content/Context;", "context", "", "imageRatio", "Landroid/graphics/Point;", "centerOffset", "Landroid/view/ViewGroup;", "parentView", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "objectResult", "", "isSelectionMode", "useOutGlowLayerView", "useParticleLayerView", "isMultiTapMode", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;", "dragListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;", "moveClipListener", "", "index", "point", "Landroid/graphics/Rect;", "cropRect", "<init>", "(Landroid/content/Context;FLandroid/graphics/Point;Landroid/view/ViewGroup;Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;ZZZZLcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;ILandroid/graphics/Point;Landroid/graphics/Rect;)V", "Landroid/graphics/Bitmap;", "bitmap", "rect", "Lme/x;", "updateScaledObjectRectInScreen", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;)V", "calcCaptureImageScaleFactor", "()V", "", "points", "ratio", "offset", "", "fixDimensions", "([Landroid/graphics/Point;FLandroid/graphics/Point;)Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;", "oi", "getRect", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;F)Landroid/graphics/Rect;", "source", "scale", "resizeBitmapByScale", "(Landroid/graphics/Bitmap;F)Landroid/graphics/Bitmap;", "Landroid/graphics/Bitmap$Config;", "getConfig", "(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap$Config;", "result", "idx", "updateObjectView", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;ILandroid/graphics/Point;Landroid/graphics/Rect;)V", "startAnimation", "(I)V", "clearViewList", "listener", "setOnStartDragListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;)V", "setOnMoveClipListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;)V", "Landroid/content/Context;", "F", "Landroid/graphics/Point;", "Landroid/view/ViewGroup;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "Z", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;", "I", "Landroid/graphics/Rect;", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureView;", "objectCaptureViewList", "Ljava/util/List;", "getObjectCaptureViewList", "()Ljava/util/List;", "scaledObjectRectInScreen", "getScaledObjectRectInScreen", "()Landroid/graphics/Rect;", "setScaledObjectRectInScreen", "(Landroid/graphics/Rect;)V", "scaleFactor", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MultiObjectViewManager {
    public static final Companion Companion = new Companion((e) null);
    public static final int SELECT_ALL_INDEX = -1;
    public static final String TAG = "MultiObjectViewManager";
    private final Point centerOffset;
    private final Context context;
    private final Rect cropRect;
    private ObjectCaptureDrawHelper.OnStartDragListener dragListener;
    private final float imageRatio;
    private final int index;
    private final boolean isMultiTapMode;
    private final boolean isSelectionMode;
    private ObjectCaptureDrawHelper.OnMoveClipListener moveClipListener;
    private final List<ObjectCaptureView> objectCaptureViewList = new ArrayList();
    private final ObjectResult objectResult;
    private final ViewGroup parentView;
    private final Point point;
    private float scaleFactor = 1.0f;
    private Rect scaledObjectRectInScreen;
    private final boolean useOutGlowLayerView;
    private final boolean useParticleLayerView;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/multi/MultiObjectViewManager$Companion;", "", "<init>", "()V", "TAG", "", "SELECT_ALL_INDEX", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public MultiObjectViewManager(Context context2, float f, Point point2, ViewGroup viewGroup, ObjectResult objectResult2, boolean z, boolean z3, boolean z7, boolean z9, ObjectCaptureDrawHelper.OnStartDragListener onStartDragListener, ObjectCaptureDrawHelper.OnMoveClipListener onMoveClipListener, int i2, Point point3, Rect rect) {
        j.e(context2, "context");
        j.e(point2, "centerOffset");
        j.e(viewGroup, "parentView");
        j.e(objectResult2, "objectResult");
        j.e(point3, "point");
        j.e(rect, "cropRect");
        this.context = context2;
        this.imageRatio = f;
        this.centerOffset = point2;
        this.parentView = viewGroup;
        this.objectResult = objectResult2;
        this.isSelectionMode = z;
        this.useOutGlowLayerView = z3;
        this.useParticleLayerView = z7;
        this.isMultiTapMode = z9;
        this.dragListener = onStartDragListener;
        this.moveClipListener = onMoveClipListener;
        this.index = i2;
        this.point = point3;
        this.cropRect = rect;
        LibLogger.d(TAG, "imageRatio : " + f);
        updateObjectView(objectResult2, i2, point3, rect);
        LibLogger.d(TAG, "MultiObjectViewManager : " + objectResult2);
    }

    private final void calcCaptureImageScaleFactor() {
        int i2;
        int i7;
        float f;
        float f5;
        float f8;
        Object systemService = this.parentView.getContext().getSystemService("window");
        j.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        Point point2 = new Point();
        Point point3 = new Point();
        defaultDisplay.getRealSize(point2);
        defaultDisplay.getSize(point3);
        Rect rect = this.scaledObjectRectInScreen;
        j.b(rect);
        int width = rect.width();
        Rect rect2 = this.scaledObjectRectInScreen;
        j.b(rect2);
        int height = rect2.height();
        ObjectCaptureDrawHelperImpl.captureImageScaleFactor = 0.99f;
        if (defaultDisplay.getRotation() == 0 || defaultDisplay.getRotation() == 2) {
            i2 = point2.x / 2;
            i7 = (int) (((float) point3.y) * 0.25f);
            f = (float) point2.y;
            f5 = 0.2f;
        } else {
            f5 = 0.4f;
            i2 = (int) (((float) point2.x) * 0.4f);
            i7 = (int) (((float) point3.y) * 0.4f);
            f = (float) point2.y;
        }
        int i8 = (int) (f * f5);
        if (i8 > height) {
            i8 = height;
        }
        if (height > i7) {
            if (i7 < i8) {
                f8 = (float) i8;
            } else {
                f8 = (float) i7;
            }
            float f10 = f8 / ((float) height);
            int i10 = (int) (((float) width) * f10);
            if (i10 > i2) {
                ObjectCaptureDrawHelperImpl.captureImageScaleFactor = (((float) i2) / ((float) i10)) * f10;
            } else {
                ObjectCaptureDrawHelperImpl.captureImageScaleFactor = f10;
            }
        } else if (width > i2) {
            ObjectCaptureDrawHelperImpl.captureImageScaleFactor = ((float) i2) / ((float) width);
        }
        if (ObjectCaptureDrawHelperImpl.captureImageScaleFactor == 1.0f) {
            ObjectCaptureDrawHelperImpl.captureImageScaleFactor = 0.99f;
        }
    }

    private final List<Point> fixDimensions(Point[] pointArr, float f, Point point2) {
        Object collect = Stream.of(Arrays.copyOf(pointArr, pointArr.length)).map(new b(23, new a(f, point2))).collect(Collectors.toList());
        j.d(collect, "collect(...)");
        return (List) collect;
    }

    /* access modifiers changed from: private */
    public static final Point fixDimensions$lambda$5(float f, Point point2, Point point3) {
        j.e(point3, "point");
        return new Point((int) ((((float) point3.x) * f) + ((float) point2.x) + 0.5f), (int) ((((float) point3.y) * f) + ((float) point2.y) + 0.5f));
    }

    /* access modifiers changed from: private */
    public static final Point fixDimensions$lambda$6(Ae.b bVar, Object obj) {
        return (Point) bVar.invoke(obj);
    }

    private final Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        j.b(config);
        return config;
    }

    private final Rect getRect(ObjectInfo objectInfo, float f) {
        Rect boundRect = objectInfo.getBoundRect();
        Point point2 = new Point();
        point2.x = boundRect.left;
        point2.y = boundRect.top;
        Point point3 = new Point();
        point3.x = boundRect.right;
        point3.y = boundRect.bottom;
        Point[] pointArr = (Point[]) fixDimensions(new Point[]{point2, point3}, f, this.centerOffset).toArray(new Point[0]);
        Point point4 = pointArr[0];
        int i2 = point4.x;
        int i7 = point4.y;
        Point point5 = pointArr[1];
        return new Rect(i2, i7, point5.x, point5.y);
    }

    private final Bitmap resizeBitmapByScale(Bitmap bitmap, float f) {
        int W6 = C0068a.W(((float) bitmap.getWidth()) * f);
        int W10 = C0068a.W(((float) bitmap.getHeight()) * f);
        if (W6 == bitmap.getWidth() && W10 == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(W6, W10, getConfig(bitmap));
        j.d(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        canvas.scale(f, f);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        return createBitmap;
    }

    private final void updateScaledObjectRectInScreen(Bitmap bitmap, Rect rect) {
        float f = (float) 1;
        float width = (this.scaleFactor - f) * ((float) bitmap.getWidth());
        float f5 = (float) 2;
        float f8 = (float) ((int) (width / f5));
        float height = (float) ((int) (((this.scaleFactor - f) * ((float) bitmap.getHeight())) / f5));
        this.scaledObjectRectInScreen = new Rect((int) (((float) rect.left) - f8), (int) (((float) rect.top) - height), (int) (((float) rect.right) + f8), (int) (((float) rect.bottom) + height));
    }

    public final void clearViewList() {
        LibLogger.d(TAG, "clearViewList");
        for (ObjectCaptureView removeView : this.objectCaptureViewList) {
            this.parentView.removeView(removeView);
        }
        this.objectCaptureViewList.clear();
    }

    public final List<ObjectCaptureView> getObjectCaptureViewList() {
        return this.objectCaptureViewList;
    }

    public final Rect getScaledObjectRectInScreen() {
        return this.scaledObjectRectInScreen;
    }

    public final void setOnMoveClipListener(ObjectCaptureDrawHelper.OnMoveClipListener onMoveClipListener) {
        this.moveClipListener = onMoveClipListener;
        for (ObjectCaptureView onMoveClipListener2 : this.objectCaptureViewList) {
            onMoveClipListener2.setOnMoveClipListener(onMoveClipListener);
        }
    }

    public final void setOnStartDragListener(ObjectCaptureDrawHelper.OnStartDragListener onStartDragListener) {
        this.dragListener = onStartDragListener;
        for (ObjectCaptureView onStartDragListener2 : this.objectCaptureViewList) {
            onStartDragListener2.setOnStartDragListener(onStartDragListener);
        }
    }

    public final void setScaledObjectRectInScreen(Rect rect) {
        this.scaledObjectRectInScreen = rect;
    }

    public final void startAnimation(int i2) {
        LibLogger.d(TAG, "startAnimation " + i2);
        this.objectCaptureViewList.get(i2).startTabAnimation();
        int i7 = 0;
        for (Object next : this.objectCaptureViewList) {
            int i8 = i7 + 1;
            if (i7 >= 0) {
                ObjectCaptureView objectCaptureView = (ObjectCaptureView) next;
                if (i2 != i7) {
                    objectCaptureView.fadeOutAnimation();
                }
                i7 = i8;
            } else {
                C1195m.v0();
                throw null;
            }
        }
    }

    public final void updateObjectView(ObjectResult objectResult2, int i2, Point point2, Rect rect) {
        j.e(objectResult2, "result");
        j.e(point2, "point");
        j.e(rect, "cropRect");
        if (i2 != -1) {
            ObjectInfo objectInfo = objectResult2.getObjects().get(i2);
            ObjectInfo objectInfo2 = new ObjectInfo(objectInfo.getObjBitmap(), objectInfo.getBoundRect());
            ObjectCaptureView objectCaptureView = new ObjectCaptureView(this.context);
            Bitmap copy = objectInfo2.getObjBitmap().copy(Bitmap.Config.ARGB_8888, true);
            j.d(copy, "copy(...)");
            Bitmap resizeBitmapByScale = resizeBitmapByScale(copy, this.imageRatio);
            Rect rect2 = getRect(objectInfo2, this.imageRatio);
            int i7 = rect.left;
            int i8 = rect2.left;
            updateScaledObjectRectInScreen(resizeBitmapByScale, new Rect(i7 + i8, rect.top + rect2.top, rect2.width() + i7 + i8, rect2.height() + rect.top + rect2.top));
            objectCaptureView.getLastTranslatePoint().x = (objectCaptureView.getLastTranslatePoint().x + point2.x) - objectCaptureView.getLastTouchPoint().x;
            objectCaptureView.getLastTranslatePoint().y = (objectCaptureView.getLastTranslatePoint().y + point2.y) - objectCaptureView.getLastTouchPoint().y;
            objectCaptureView.setCurrentPoint(point2);
            objectCaptureView.setLastTouchPoint(point2);
            objectCaptureView.setDistanceOfTouchFromCenter(this.scaledObjectRectInScreen, 0.0f, 0.0f);
            objectCaptureView.setCapturedInfo(resizeBitmapByScale, rect2.left, rect2.top, Boolean.valueOf(this.isSelectionMode), this.useOutGlowLayerView, this.useParticleLayerView, false);
            objectCaptureView.setMultiTapMode(this.isMultiTapMode);
            objectCaptureView.setOnStartDragListener(this.dragListener);
            objectCaptureView.setOnMoveClipListener(this.moveClipListener);
            this.parentView.addView(objectCaptureView);
            this.objectCaptureViewList.add(objectCaptureView);
            calcCaptureImageScaleFactor();
        } else {
            for (ObjectInfo objectInfo3 : objectResult2.getObjects()) {
                ObjectInfo objectInfo4 = new ObjectInfo(objectInfo3.getObjBitmap(), objectInfo3.getBoundRect());
                ObjectCaptureView objectCaptureView2 = new ObjectCaptureView(this.context);
                Bitmap copy2 = objectInfo4.getObjBitmap().copy(Bitmap.Config.ARGB_8888, true);
                j.d(copy2, "copy(...)");
                Bitmap resizeBitmapByScale2 = resizeBitmapByScale(copy2, this.imageRatio);
                Rect rect3 = getRect(objectInfo4, this.imageRatio);
                objectCaptureView2.setCapturedInfo(resizeBitmapByScale2, rect3.left, rect3.top, Boolean.valueOf(this.isSelectionMode), this.useOutGlowLayerView, this.useParticleLayerView, true);
                objectCaptureView2.setMultiTapMode(this.isMultiTapMode);
                objectCaptureView2.setOnStartDragListener(this.dragListener);
                objectCaptureView2.setOnMoveClipListener(this.moveClipListener);
                this.parentView.addView(objectCaptureView2);
                this.objectCaptureViewList.add(objectCaptureView2);
            }
        }
        int size = this.objectCaptureViewList.size();
        LibLogger.d(TAG, "updateObjectView " + size);
    }
}
