package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import A4.C0378m;
import B2.h;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.common.Rune;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00012\u00020\u0001:\u0002\u0001B\u0013\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001d\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0004\u0010\bB%\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u000e¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u000e¢\u0006\u0004\b\u0017\u0010\u0016J\r\u0010\u0018\u001a\u00020\u000e¢\u0006\u0004\b\u0018\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010#\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J'\u0010(\u001a\u00020\u000e2\b\u0010%\u001a\u0004\u0018\u00010!2\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u001f¢\u0006\u0004\b(\u0010)JG\u00103\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\b\u0010/\u001a\u0004\u0018\u00010.2\u0006\u00100\u001a\u00020.2\u0006\u00101\u001a\u00020.2\u0006\u00102\u001a\u00020.¢\u0006\u0004\b3\u00104J\r\u00105\u001a\u00020\u000e¢\u0006\u0004\b5\u0010\u0016J\u000f\u00106\u001a\u00020\u000eH\u0002¢\u0006\u0004\b6\u0010\u0016J\u001f\u00108\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001f2\u0006\u00107\u001a\u00020!H\u0002¢\u0006\u0004\b8\u0010$J\u000f\u00109\u001a\u00020\u000eH\u0002¢\u0006\u0004\b9\u0010\u0016JG\u0010E\u001a\u00020\u000e2\b\u0010;\u001a\u0004\u0018\u00010:2\b\u0010=\u001a\u0004\u0018\u00010<2\b\u0010?\u001a\u0004\u0018\u00010>2\u0006\u0010@\u001a\u00020\t2\u0006\u0010B\u001a\u00020A2\b\u0010D\u001a\u0004\u0018\u00010CH\u0002¢\u0006\u0004\bE\u0010FJ\u0017\u0010H\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020\u001fH\u0002¢\u0006\u0004\bH\u0010IJ'\u0010K\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020*2\u0006\u0010J\u001a\u00020*2\u0006\u00102\u001a\u00020.H\u0002¢\u0006\u0004\bK\u0010LJ'\u0010N\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020*2\u0006\u0010M\u001a\u00020*2\u0006\u0010J\u001a\u00020*H\u0002¢\u0006\u0004\bN\u0010OJ/\u0010T\u001a\u00020*2\u0006\u0010P\u001a\u00020*2\u0006\u0010Q\u001a\u00020\t2\u0006\u0010R\u001a\u00020\t2\u0006\u0010S\u001a\u00020.H\u0002¢\u0006\u0004\bT\u0010UR\u0018\u0010W\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u0018\u0010Z\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0018\u0010\\\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0018\u0010^\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0018\u0010a\u001a\u0004\u0018\u00010`8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\ba\u0010bR\u0018\u0010d\u001a\u0004\u0018\u00010c8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bf\u0010gR\u0016\u0010h\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bh\u0010gR\u0016\u0010i\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bi\u0010gR\u0018\u0010j\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bj\u0010kR\u0018\u0010l\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\r\u0010nR\u0018\u0010p\u001a\u0004\u0018\u00010o8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bp\u0010qR\u0018\u0010r\u001a\u0004\u0018\u00010o8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\br\u0010qR\u0016\u0010s\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bs\u0010gR\u0016\u0010t\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bt\u0010gR\u0016\u0010u\u001a\u00020\u001f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bu\u0010vR\u0016\u0010w\u001a\u00020\u001f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bw\u0010vR\u0016\u0010x\u001a\u00020\u001f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bx\u0010vR\u0016\u0010y\u001a\u00020\u001f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\by\u0010vR\"\u0010z\u001a\u00020C8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bz\u0010{\u001a\u0004\b|\u0010}\"\u0004\b~\u0010R&\u0010\u0001\u001a\u00020C8\u0006@\u0006X\u000e¢\u0006\u0015\n\u0005\b\u0001\u0010{\u001a\u0005\b\u0001\u0010}\"\u0005\b\u0001\u0010R&\u0010\u0001\u001a\u00020C8\u0006@\u0006X\u000e¢\u0006\u0015\n\u0005\b\u0001\u0010{\u001a\u0005\b\u0001\u0010}\"\u0005\b\u0001\u0010R)\u0010\u0001\u001a\u00020.8\u0006@\u0006X\u000e¢\u0006\u0018\n\u0006\b\u0001\u0010\u0001\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R)\u0010\u0001\u001a\u00020.8\u0006@\u0006X\u000e¢\u0006\u0018\n\u0006\b\u0001\u0010\u0001\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R)\u0010\u0001\u001a\u00020.8\u0006@\u0006X\u000e¢\u0006\u0018\n\u0006\b\u0001\u0010\u0001\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00030\u00018\u0002X\u0004¢\u0006\b\n\u0006\b\u0001\u0010\u0001¨\u0006\u0001"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureView;", "Landroid/widget/FrameLayout;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyle", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureViewListener;", "listener", "Lme/x;", "setViewListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureViewListener;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/DimView;", "view", "setDimView", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/DimView;)V", "startAnimation", "()V", "startTabAnimation", "fadeOutAnimation", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;", "setOnStartDragListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;", "setOnMoveClipListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;)V", "", "scaleFactor", "Landroid/graphics/Rect;", "dndRect", "startScaleDownAnimation", "(FLandroid/graphics/Rect;)V", "scaledObjectRectInScreen", "translationX", "translationY", "setDistanceOfTouchFromCenter", "(Landroid/graphics/Rect;FF)V", "Landroid/graphics/Bitmap;", "bitmap", "x", "y", "", "isSelectionMode", "useOutGlowLayer", "useParticleLayer", "isSelectAll", "setCapturedInfo", "(Landroid/graphics/Bitmap;IILjava/lang/Boolean;ZZZ)V", "recycleBitmap", "init", "objectRectForDndInScreen", "startDragAndDrop", "setDragListener", "Landroid/content/ClipData;", "data", "Landroid/view/View$DragShadowBuilder;", "shadowBuilder", "", "myLocalState", "flag", "Landroid/graphics/RectF;", "selectedArea", "Landroid/graphics/Point;", "location", "startDragAndDropWithLocation", "(Landroid/content/ClipData;Landroid/view/View$DragShadowBuilder;Ljava/lang/Object;ILandroid/graphics/RectF;Landroid/graphics/Point;)V", "scale", "scaleDownAndMove", "(F)V", "outGlowBitmap", "useOutGlowLayerView", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Z)V", "particleBitmap", "useParticleLayerView", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;)V", "source", "size", "space", "shouldDrawOriginal", "getOutGlowBitmap", "(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/MaskedImageView;", "maskedImageView", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/MaskedImageView;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/DummyImageView;", "dummyImageView", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/DummyImageView;", "dimView", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/DimView;", "frameLayout", "Landroid/widget/FrameLayout;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ParticleLayerView;", "particleLayerView", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ParticleLayerView;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/OutGlowLayerView;", "outGlowLayerView", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/OutGlowLayerView;", "shadowSpace", "I", "particleSpace", "extraSpace", "dragListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnStartDragListener;", "moveClipListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper$OnMoveClipListener;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureViewListener;", "Landroid/animation/AnimatorSet;", "zoomAnimatorSet", "Landroid/animation/AnimatorSet;", "lightAnimatorSet", "distanceOfTouchFromCenterX", "distanceOfTouchFromCenterY", "toGoFactorX", "F", "toGoFactorY", "moveFromScaleAnimationX", "moveFromScaleAnimationY", "currentPoint", "Landroid/graphics/Point;", "getCurrentPoint", "()Landroid/graphics/Point;", "setCurrentPoint", "(Landroid/graphics/Point;)V", "lastTouchPoint", "getLastTouchPoint", "setLastTouchPoint", "lastTranslatePoint", "getLastTranslatePoint", "setLastTranslatePoint", "multiTapMode", "Z", "getMultiTapMode", "()Z", "setMultiTapMode", "(Z)V", "touchProcessing", "getTouchProcessing", "setTouchProcessing", "dragging", "getDragging", "setDragging", "Landroid/view/View$OnAttachStateChangeListener;", "attachStateChangeListener", "Landroid/view/View$OnAttachStateChangeListener;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCaptureView extends FrameLayout {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "ObjectCaptureView";
    private final View.OnAttachStateChangeListener attachStateChangeListener = new ObjectCaptureView$attachStateChangeListener$1(this);
    private Point currentPoint = new Point(0, 0);
    private DimView dimView;
    private int distanceOfTouchFromCenterX;
    private int distanceOfTouchFromCenterY;
    private ObjectCaptureDrawHelper.OnStartDragListener dragListener;
    private boolean dragging;
    private DummyImageView dummyImageView;
    private int extraSpace;
    private FrameLayout frameLayout;
    private Point lastTouchPoint = new Point(0, 0);
    private Point lastTranslatePoint = new Point(0, 0);
    private AnimatorSet lightAnimatorSet;
    /* access modifiers changed from: private */
    public ObjectCaptureViewListener listener;
    private MaskedImageView maskedImageView;
    private ObjectCaptureDrawHelper.OnMoveClipListener moveClipListener;
    private float moveFromScaleAnimationX;
    private float moveFromScaleAnimationY;
    private boolean multiTapMode;
    private OutGlowLayerView outGlowLayerView;
    private ParticleLayerView particleLayerView;
    private int particleSpace;
    private int shadowSpace;
    private float toGoFactorX;
    private float toGoFactorY;
    private boolean touchProcessing = true;
    private AnimatorSet zoomAnimatorSet;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureView$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectCaptureView(Context context) {
        super(context);
        j.b(context);
        init();
    }

    private final Bitmap getOutGlowBitmap(Bitmap bitmap, int i2, int i7, boolean z) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() + i2, bitmap.getHeight() + i2, Bitmap.Config.ARGB_8888);
        j.d(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        int color = getContext().getColor(R.color.glow_color);
        canvas.drawColor(color, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setMaskFilter(new BlurMaskFilter(4.0f, BlurMaskFilter.Blur.NORMAL));
        Bitmap extractAlpha = bitmap.extractAlpha();
        j.d(extractAlpha, "extractAlpha(...)");
        paint.setColor(color);
        float f = (float) i7;
        float f5 = (float) (i2 / 2);
        float f8 = f5 - f;
        canvas.drawBitmap(extractAlpha, f8, f5, paint);
        canvas.drawBitmap(extractAlpha, f5, f8, paint);
        float f10 = f + f5;
        canvas.drawBitmap(extractAlpha, f10, f5, paint);
        canvas.drawBitmap(extractAlpha, f5, f10, paint);
        if (z) {
            canvas.drawBitmap(bitmap, f5, f5, (Paint) null);
        }
        extractAlpha.recycle();
        return createBitmap;
    }

    private final void init() {
        Object systemService = getContext().getSystemService("layout_inflater");
        j.c(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
        ((LayoutInflater) systemService).inflate(R.layout.objectcaptureview_layout, this);
        this.maskedImageView = (MaskedImageView) findViewById(R.id.lighting_gradient);
        this.dummyImageView = (DummyImageView) findViewById(R.id.dummy_layer);
        this.frameLayout = (FrameLayout) findViewById(R.id.framelayout_for_capture_animation);
        this.particleLayerView = (ParticleLayerView) findViewById(R.id.particle_layer);
        this.outGlowLayerView = (OutGlowLayerView) findViewById(R.id.glow_layer);
        addOnAttachStateChangeListener(this.attachStateChangeListener);
    }

    private final void scaleDownAndMove(float f) {
        Point point = this.lastTranslatePoint;
        int i2 = point.x;
        Point point2 = this.currentPoint;
        int i7 = i2 + point2.x;
        Point point3 = this.lastTouchPoint;
        point.set(i7 - point3.x, (point.y + point2.y) - point3.y);
        Point point4 = this.lastTouchPoint;
        Point point5 = this.currentPoint;
        point4.set(point5.x, point5.y);
        setScaleX(f);
        setScaleY(f);
        float f5 = ((float) 1) - f;
        float f8 = this.toGoFactorX * f5;
        this.moveFromScaleAnimationX = f8;
        this.moveFromScaleAnimationY = f5 * this.toGoFactorY;
        setTranslationX(((float) this.lastTranslatePoint.x) + f8);
        setTranslationY(((float) this.lastTranslatePoint.y) + this.moveFromScaleAnimationY);
    }

    private final void setDragListener() {
        setOnDragListener(new C0378m(1, this));
    }

    /* access modifiers changed from: private */
    public static final boolean setDragListener$lambda$1(ObjectCaptureView objectCaptureView, View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        LibLogger.i(TAG, "onDrag. dragEvent: " + action);
        int action2 = dragEvent.getAction();
        if (action2 == 1) {
            objectCaptureView.dragging = true;
        } else if (action2 == 3) {
            LibLogger.i(TAG, "ACTION_DROP in ObjectCaptureView");
            objectCaptureView.dragging = false;
            ObjectCaptureViewListener objectCaptureViewListener = objectCaptureView.listener;
            if (objectCaptureViewListener != null) {
                objectCaptureViewListener.showObjectCaptureResult(false);
            }
            return false;
        } else if (action2 == 4) {
            objectCaptureView.dragging = false;
            if (dragEvent.getResult()) {
                LibLogger.i(TAG, "ACTION_DRAG_ENDED : Drag item is consumed");
                ObjectCaptureViewListener objectCaptureViewListener2 = objectCaptureView.listener;
                if (objectCaptureViewListener2 != null) {
                    objectCaptureViewListener2.clearObjectCaptureView();
                }
            } else {
                LibLogger.i(TAG, "ACTION_DRAG_ENDED : Nobody take care drag event.");
                ObjectCaptureViewListener objectCaptureViewListener3 = objectCaptureView.listener;
                if (objectCaptureViewListener3 != null) {
                    objectCaptureViewListener3.showObjectCaptureResult(false);
                }
            }
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final void startDragAndDrop(float f, Rect rect) {
        setDragListener();
        ObjectCaptureDrawHelper.OnStartDragListener onStartDragListener = this.dragListener;
        if (onStartDragListener != null) {
            LibLogger.i(TAG, "startDragAndDrop: OnStartDragListener. onStarDrag()");
            startDragAndDropWithLocation(onStartDragListener.onStarDrag(), new ObjectCaptureDragShadowBuilder(this, f), this, 4195075, new RectF((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom), (Point) null);
            setAlpha(0.0f);
            ObjectCaptureDrawHelper.OnMoveClipListener onMoveClipListener = this.moveClipListener;
            if (onMoveClipListener != null) {
                onMoveClipListener.onMoveClip();
            }
        }
    }

    private final void startDragAndDropWithLocation(ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i2, RectF rectF, Point point) {
        try {
            LibLogger.i(TAG, "startDragAndDropWithLocation: selectedArea = " + rectF);
            Method declaredMethod = View.class.getDeclaredMethod("hidden_startDragAndDrop", new Class[]{ClipData.class, View.DragShadowBuilder.class, Object.class, Integer.TYPE, RectF.class, Point.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[]{clipData, dragShadowBuilder, obj, Integer.valueOf(i2), rectF, point});
        } catch (Exception unused) {
            LibLogger.e(TAG, "failed to call hidden_startDragAndDrop");
        }
    }

    /* access modifiers changed from: private */
    public static final void startScaleDownAnimation$lambda$3$lambda$2(ObjectCaptureView objectCaptureView, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "valueAnimator");
        Object animatedValue = valueAnimator.getAnimatedValue("scaleX");
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        objectCaptureView.scaleDownAndMove(((Float) animatedValue).floatValue());
    }

    private final void useOutGlowLayerView(Bitmap bitmap, Bitmap bitmap2, boolean z) {
        if (Build.VERSION.SDK_INT >= 33) {
            OutGlowLayerView outGlowLayerView2 = this.outGlowLayerView;
            j.b(outGlowLayerView2);
            outGlowLayerView2.updateMask(bitmap2, bitmap, (float) (this.extraSpace + this.shadowSpace + (this.shadowSpace / 2)));
            OutGlowLayerView outGlowLayerView3 = this.outGlowLayerView;
            j.b(outGlowLayerView3);
            outGlowLayerView3.updateSelectAll(z);
            OutGlowLayerView outGlowLayerView4 = this.outGlowLayerView;
            j.b(outGlowLayerView4);
            outGlowLayerView4.startAnimation();
        }
    }

    private final void useParticleLayerView(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
        if (Build.VERSION.SDK_INT >= 33) {
            int i2 = this.shadowSpace;
            int i7 = this.particleSpace + i2 + this.extraSpace;
            ParticleLayerView particleLayerView2 = this.particleLayerView;
            j.b(particleLayerView2);
            particleLayerView2.updateMask(bitmap2, bitmap3, (float) (this.extraSpace + (i2 / 2)));
            ParticleLayerView particleLayerView3 = this.particleLayerView;
            j.b(particleLayerView3);
            Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            j.d(copy, "copy(...)");
            particleLayerView3.addShadow(copy, i7);
            ParticleLayerView particleLayerView4 = this.particleLayerView;
            j.b(particleLayerView4);
            particleLayerView4.startAnimation();
        }
    }

    public final void fadeOutAnimation() {
        Float f;
        FrameLayout frameLayout2 = this.frameLayout;
        if (frameLayout2 != null) {
            f = Float.valueOf(frameLayout2.getAlpha());
        } else {
            f = null;
        }
        if (f != null && f.floatValue() == 1.0f) {
            Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.capture_bounce_out_animator);
            loadAnimator.setTarget(this.frameLayout);
            loadAnimator.start();
        }
    }

    public final Point getCurrentPoint() {
        return this.currentPoint;
    }

    public final boolean getDragging() {
        return this.dragging;
    }

    public final Point getLastTouchPoint() {
        return this.lastTouchPoint;
    }

    public final Point getLastTranslatePoint() {
        return this.lastTranslatePoint;
    }

    public final boolean getMultiTapMode() {
        return this.multiTapMode;
    }

    public final boolean getTouchProcessing() {
        return this.touchProcessing;
    }

    public final void recycleBitmap() {
        if (Build.VERSION.SDK_INT >= 33) {
            ParticleLayerView particleLayerView2 = this.particleLayerView;
            if (particleLayerView2 != null) {
                particleLayerView2.recycle();
            }
            OutGlowLayerView outGlowLayerView2 = this.outGlowLayerView;
            if (outGlowLayerView2 != null) {
                outGlowLayerView2.recycle();
            }
            MaskedImageView maskedImageView2 = this.maskedImageView;
            if (maskedImageView2 != null) {
                maskedImageView2.recycleBitmap();
            }
            DummyImageView dummyImageView2 = this.dummyImageView;
            if (dummyImageView2 != null) {
                dummyImageView2.recycleBitmap();
            }
        }
    }

    public final void setCapturedInfo(Bitmap bitmap, int i2, int i7, Boolean bool, boolean z, boolean z3, boolean z7) {
        j.e(bitmap, "bitmap");
        this.shadowSpace = getResources().getDimensionPixelSize(R.dimen.extra_space_for_shadow);
        this.particleSpace = getResources().getDimensionPixelSize(R.dimen.particle_extra_space_for_shadow);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.extra_space);
        this.extraSpace = dimensionPixelSize;
        int i8 = this.shadowSpace + dimensionPixelSize;
        if (z3) {
            i8 += this.particleSpace;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(bitmap.getWidth() + i8, bitmap.getHeight() + i8);
        int i10 = i8 / 2;
        layoutParams.leftMargin = i2 - i10;
        layoutParams.topMargin = i7 - i10;
        setLayoutParams(layoutParams);
        if (z) {
            LibLogger.i(TAG, "useOutGlow");
            Bitmap outGlowBitmap = getOutGlowBitmap(bitmap, i8, this.shadowSpace, true);
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            Bitmap copy = outGlowBitmap.copy(config, true);
            j.d(copy, "copy(...)");
            useOutGlowLayerView(bitmap, copy, z7);
            if (z3) {
                LibLogger.i(TAG, "useParticle");
                Bitmap outGlowBitmap2 = getOutGlowBitmap(bitmap, i8, this.shadowSpace + this.particleSpace, false);
                Bitmap copy2 = outGlowBitmap2.copy(config, true);
                j.d(copy2, "copy(...)");
                Bitmap copy3 = outGlowBitmap.copy(config, true);
                j.d(copy3, "copy(...)");
                useParticleLayerView(bitmap, copy2, copy3);
                outGlowBitmap2.recycle();
            }
            outGlowBitmap.recycle();
        }
        j.b(bool);
        if (!bool.booleanValue()) {
            MaskedImageView maskedImageView2 = this.maskedImageView;
            j.b(maskedImageView2);
            maskedImageView2.setMask(bitmap, i8, this.shadowSpace);
            DummyImageView dummyImageView2 = this.dummyImageView;
            j.b(dummyImageView2);
            dummyImageView2.setMask(bitmap, i8);
            if (Rune.SUPPORT_AFTER_ONEUI_7_0 && !z7) {
                MaskedImageView maskedImageView3 = this.maskedImageView;
                j.b(maskedImageView3);
                maskedImageView3.startAnimation();
            }
            DimView dimView2 = this.dimView;
            if (dimView2 != null) {
                dimView2.startLightDimAnimation();
            }
        }
    }

    public final void setCurrentPoint(Point point) {
        j.e(point, "<set-?>");
        this.currentPoint = point;
    }

    public final void setDimView(DimView dimView2) {
        this.dimView = dimView2;
    }

    public final void setDistanceOfTouchFromCenter(Rect rect, float f, float f5) {
        if (rect != null) {
            int i2 = rect.right;
            int i7 = rect.left;
            int D = C0086a.D(i2, i7, 2, i7);
            int i8 = rect.bottom;
            int i10 = rect.top;
            int D4 = C0086a.D(i8, i10, 2, i10);
            Point point = this.lastTouchPoint;
            int i11 = point.x;
            int i12 = (i11 - D) + ((int) f);
            this.distanceOfTouchFromCenterX = i12;
            int i13 = point.y;
            int i14 = (i13 - D4) + ((int) f5);
            this.distanceOfTouchFromCenterY = i14;
            float f8 = (float) i12;
            float f10 = (float) 1;
            float f11 = ObjectCaptureDrawHelperImpl.captureImageScaleFactor;
            this.toGoFactorX = f8 / (f10 - f11);
            this.toGoFactorY = ((float) i14) / (f10 - f11);
            LibLogger.i(TAG, "setDistanceOfTouchFromCenter lastTouchPoint: " + i11 + " lastTouchPoint: " + i13);
            int i15 = this.distanceOfTouchFromCenterX;
            StringBuilder sb2 = new StringBuilder("setDistanceOfTouchFromCenter distanceOfTouchFromCenterX: ");
            sb2.append(i15);
            LibLogger.i(TAG, sb2.toString());
            MaskedImageView maskedImageView2 = this.maskedImageView;
            if (maskedImageView2 != null) {
                maskedImageView2.setStartPoint(new PointF((float) this.distanceOfTouchFromCenterX, (float) this.distanceOfTouchFromCenterY));
            }
        } else {
            LibLogger.e(TAG, "can't setDistanceOfTouchFromCenter. objectInitRect is null");
        }
        this.moveFromScaleAnimationX = 0.0f;
        this.moveFromScaleAnimationY = 0.0f;
    }

    public final void setDragging(boolean z) {
        this.dragging = z;
    }

    public final void setLastTouchPoint(Point point) {
        j.e(point, "<set-?>");
        this.lastTouchPoint = point;
    }

    public final void setLastTranslatePoint(Point point) {
        j.e(point, "<set-?>");
        this.lastTranslatePoint = point;
    }

    public final void setMultiTapMode(boolean z) {
        this.multiTapMode = z;
    }

    public final void setOnMoveClipListener(ObjectCaptureDrawHelper.OnMoveClipListener onMoveClipListener) {
        this.moveClipListener = onMoveClipListener;
    }

    public final void setOnStartDragListener(ObjectCaptureDrawHelper.OnStartDragListener onStartDragListener) {
        this.dragListener = onStartDragListener;
    }

    public final void setTouchProcessing(boolean z) {
        this.touchProcessing = z;
    }

    public final void setViewListener(ObjectCaptureViewListener objectCaptureViewListener) {
        j.e(objectCaptureViewListener, "listener");
        this.listener = objectCaptureViewListener;
    }

    public final void startAnimation() {
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.capture_zoom_animator);
        j.c(loadAnimator, "null cannot be cast to non-null type android.animation.AnimatorSet");
        AnimatorSet animatorSet = (AnimatorSet) loadAnimator;
        this.zoomAnimatorSet = animatorSet;
        animatorSet.setTarget(this.frameLayout);
        AnimatorSet animatorSet2 = this.zoomAnimatorSet;
        j.b(animatorSet2);
        animatorSet2.start();
        if (!Rune.SUPPORT_AFTER_ONEUI_7_0) {
            Animator loadAnimator2 = AnimatorInflater.loadAnimator(getContext(), R.animator.capture_light_animator);
            j.c(loadAnimator2, "null cannot be cast to non-null type android.animation.AnimatorSet");
            AnimatorSet animatorSet3 = (AnimatorSet) loadAnimator2;
            this.lightAnimatorSet = animatorSet3;
            animatorSet3.setTarget(this.maskedImageView);
            AnimatorSet animatorSet4 = this.lightAnimatorSet;
            j.b(animatorSet4);
            animatorSet4.start();
        }
        DimView dimView2 = this.dimView;
        if (dimView2 != null) {
            dimView2.startLightDimAnimation();
        }
    }

    public final void startScaleDownAnimation(float f, Rect rect) {
        j.e(rect, "dndRect");
        LibLogger.d(TAG, "start scale down animation!");
        AnimatorSet animatorSet = this.zoomAnimatorSet;
        if (animatorSet != null) {
            animatorSet.end();
        }
        AnimatorSet animatorSet2 = this.lightAnimatorSet;
        if (animatorSet2 != null) {
            animatorSet2.end();
        }
        DimView dimView2 = this.dimView;
        if (dimView2 != null) {
            dimView2.startRemoveDimAnimation();
        }
        OutGlowLayerView outGlowLayerView2 = this.outGlowLayerView;
        if (outGlowLayerView2 != null) {
            outGlowLayerView2.endTimeAnimation();
        }
        MaskedImageView maskedImageView2 = this.maskedImageView;
        if (maskedImageView2 != null) {
            maskedImageView2.setVisibility(8);
        }
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.capture_scale_down_animator);
        j.c(loadAnimator, "null cannot be cast to non-null type android.animation.ObjectAnimator");
        ObjectAnimator objectAnimator = (ObjectAnimator) loadAnimator;
        objectAnimator.setFloatValues(new float[]{1.0f, ObjectCaptureDrawHelperImpl.captureImageScaleFactor});
        objectAnimator.addUpdateListener(new h(10, this));
        objectAnimator.addListener(new ObjectCaptureView$startScaleDownAnimation$1$2(this, f, rect));
        objectAnimator.start();
    }

    public final void startTabAnimation() {
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.capture_bounce_in_animator);
        j.c(loadAnimator, "null cannot be cast to non-null type android.animation.AnimatorSet");
        AnimatorSet animatorSet = (AnimatorSet) loadAnimator;
        this.zoomAnimatorSet = animatorSet;
        animatorSet.setTarget(this.frameLayout);
        AnimatorSet animatorSet2 = this.zoomAnimatorSet;
        j.b(animatorSet2);
        animatorSet2.start();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectCaptureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.b(context);
        init();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectCaptureView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.b(context);
        init();
    }
}
