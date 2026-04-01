package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle;

import Ae.a;
import Tf.k;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.view.View;
import android.view.animation.PathInterpolator;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableOcrResult;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.HandleDrawInfo;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.HandleDrawStrategy;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.DrawUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.VectorUtil;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.u;
import me.x;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \u00012\u00020\u0001:\u0006\u0001\u0001\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u000b¢\u0006\u0004\b\u001f\u0010\u000fJ\u0015\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#J\u0015\u0010&\u001a\u00020\u00102\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b&\u0010'J\u0017\u0010*\u001a\u00020(2\u0006\u0010)\u001a\u00020(H\u0002¢\u0006\u0004\b*\u0010+J\u001b\u0010.\u001a\u00020\u0010*\u00020,2\u0006\u0010-\u001a\u00020\bH\u0002¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\b0\u0010\u001eJM\u00109\u001a\u00020\u00102\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020(2\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00132\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001007H\u0002¢\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u0010H\u0002¢\u0006\u0004\b;\u0010\u0012J\u0013\u0010<\u001a\u00020\b*\u00020\bH\u0002¢\u0006\u0004\b<\u0010=J%\u0010A\u001a\u00020\b2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020 0>2\u0006\u0010@\u001a\u00020(H\u0002¢\u0006\u0004\bA\u0010BJ\u001d\u0010D\u001a\u00020\b2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020 0>H\u0002¢\u0006\u0004\bD\u0010EJ%\u0010H\u001a\b\u0012\u0004\u0012\u00020 0>2\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020(H\u0002¢\u0006\u0004\bH\u0010IJ#\u0010J\u001a\b\u0012\u0004\u0012\u00020 0>2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020 0>H\u0002¢\u0006\u0004\bJ\u0010KR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010LR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010MR\u0016\u00102\u001a\u0002018\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b2\u0010NR\u0016\u0010O\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010@\u001a\u00020(8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b@\u0010QR\u0016\u0010R\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bR\u0010PR\u0016\u0010S\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bS\u0010PR\u0016\u0010T\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bT\u0010PR\u0016\u0010U\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bU\u0010PR\u0016\u0010V\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bV\u0010PR\u0016\u0010W\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bW\u0010PR\u0016\u0010X\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bX\u0010PR\u0016\u0010Y\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bY\u0010PR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010ZR\u0016\u0010[\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b[\u0010\\R\u0016\u0010]\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b]\u0010\\R\u0016\u0010^\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010a\u001a\u00020`8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bc\u0010\\R\"\u0010d\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bd\u0010P\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\"\u0010i\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bi\u0010\\\u001a\u0004\bi\u0010\u000f\"\u0004\bj\u0010kR\"\u0010l\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bl\u0010\\\u001a\u0004\bl\u0010\u000f\"\u0004\bm\u0010kR\"\u0010n\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bn\u0010\\\u001a\u0004\bn\u0010\u000f\"\u0004\bo\u0010kR$\u0010q\u001a\u00020(2\u0006\u0010p\u001a\u00020(8\u0006@BX\u000e¢\u0006\f\n\u0004\bq\u0010Q\u001a\u0004\br\u0010sR\"\u0010u\u001a\u00020t8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bu\u0010v\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR$\u0010|\u001a\u00020{2\u0006\u0010p\u001a\u00020{8\u0006@BX\u000e¢\u0006\f\n\u0004\b|\u0010}\u001a\u0004\b~\u0010¨\u0006\u0001"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$HandleType;", "handleType", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$HandleType;)V", "", "x", "y", "", "contains", "(II)Z", "isNotEmpty", "()Z", "Lme/x;", "setEmpty", "()V", "Landroid/view/View;", "teView", "setTeView", "(Landroid/view/View;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$MovingState;", "newState", "updateMovingState", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$MovingState;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawInfo;", "handleDrawInfo", "updateHandle", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawInfo;)V", "isMoving", "Landroid/graphics/Point;", "touchPoint", "getEffectiveTouchPoint", "(Landroid/graphics/Point;)Landroid/graphics/Point;", "Landroid/graphics/Canvas;", "canvas", "draw", "(Landroid/graphics/Canvas;)V", "Landroid/graphics/Rect;", "defaultRect", "createDefaultTouchableAreaRect", "(Landroid/graphics/Rect;)Landroid/graphics/Rect;", "Landroid/graphics/drawable/RotateDrawable;", "degree", "setDegree", "(Landroid/graphics/drawable/RotateDrawable;I)V", "updateRectAndDrawable", "Landroid/graphics/drawable/LayerDrawable;", "drawable", "startBounds", "endBounds", "startRotation", "endRotation", "Lkotlin/Function0;", "handleAnimationCallback", "showHandleAnimation", "(Landroid/graphics/drawable/LayerDrawable;Landroid/graphics/Rect;Landroid/graphics/Rect;IILandroid/view/View;LAe/a;)V", "setScaledParameters", "dimenToPixel", "(I)I", "", "targetPoly", "drawAreaRect", "findHandleVisibleRotationAngle", "([Landroid/graphics/Point;Landroid/graphics/Rect;)I", "poly", "computeAngleOfPoly", "([Landroid/graphics/Point;)I", "angle", "rect", "getRotatedRect", "(ILandroid/graphics/Rect;)[Landroid/graphics/Point;", "getGlobalVisiblePoly", "([Landroid/graphics/Point;)[Landroid/graphics/Point;", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$HandleType;", "Landroid/graphics/drawable/LayerDrawable;", "rotationAngle", "I", "Landroid/graphics/Rect;", "drawableWidth", "drawableHeight", "scaledDrawableWidth", "scaledDrawableHeight", "scaledTouchAreaMarginLeft", "scaledTouchAreaMarginTop", "scaledTouchAreaMarginRight", "scaledTouchAreaMarginBottom", "Landroid/view/View;", "isMagnifyHandle", "Z", "isLeftToRight", "movingState", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$MovingState;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawStrategy;", "drawStrategy", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawStrategy;", "isAnimationActive", "color", "getColor", "()I", "setColor", "(I)V", "isShadowEnabled", "setShadowEnabled", "(Z)V", "isMovingEnabled", "setMovingEnabled", "isGradientEnabled", "setGradientEnabled", "value", "touchAreaRect", "getTouchAreaRect", "()Landroid/graphics/Rect;", "", "scaleFactor", "F", "getScaleFactor", "()F", "setScaleFactor", "(F)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "selectedChar", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "getSelectedChar", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "Companion", "HandleType", "MovingState", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Handle {
    public static final Companion Companion = new Companion((e) null);
    private static final PathInterpolator HANDLE_ANIMATION_INTERPOLATOR = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
    private int color;
    private final Context context;
    private Rect drawAreaRect = new Rect();
    private HandleDrawStrategy drawStrategy;
    private LayerDrawable drawable = new LayerDrawable(new Drawable[0]);
    private int drawableHeight;
    private int drawableWidth;
    private final HandleType handleType;
    /* access modifiers changed from: private */
    public boolean isAnimationActive;
    private boolean isGradientEnabled;
    private boolean isLeftToRight = true;
    private boolean isMagnifyHandle;
    private boolean isMovingEnabled;
    private boolean isShadowEnabled;
    private MovingState movingState;
    private int rotationAngle;
    private float scaleFactor;
    private int scaledDrawableHeight;
    private int scaledDrawableWidth;
    private int scaledTouchAreaMarginBottom;
    private int scaledTouchAreaMarginLeft;
    private int scaledTouchAreaMarginRight;
    private int scaledTouchAreaMarginTop;
    private SelectableCharacter selectedChar;
    private View teView;
    private Rect touchAreaRect;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$Companion;", "", "<init>", "()V", "TAG", "", "DRAWABLE_RESIZE_RATIO", "", "HANDLE_SCALE_FACTOR", "", "LTR_LEFT_BOTTOM_POSITION", "LTR_RIGHT_BOTTOM_POSITION", "RTL_LEFT_BOTTOM_POSITION", "RTL_RIGHT_BOTTOM_POSITION", "HANDLE_ANIMATION_INTERPOLATOR", "Landroid/view/animation/PathInterpolator;", "HANDLE_ANIMATION_MILLISECONDS", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$HandleType;", "", "<init>", "(Ljava/lang/String;I)V", "START", "END", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum HandleType {
        START,
        END;

        static {
            HandleType[] $values;
            $ENTRIES = c.t($values);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$MovingState;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "MOVING", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MovingState {
        IDLE,
        MOVING;

        static {
            MovingState[] $values;
            $ENTRIES = c.t($values);
        }
    }

    public Handle(Context context2, HandleType handleType2) {
        j.e(context2, "context");
        j.e(handleType2, "handleType");
        this.context = context2;
        this.handleType = handleType2;
        MovingState movingState2 = MovingState.IDLE;
        this.movingState = movingState2;
        this.drawStrategy = HandleDrawStrategyFactory.INSTANCE.createDrawStrategy(handleType2, movingState2, true, true);
        this.color = context2.getColor(R$color.textextraction_handle_tint_color);
        this.isMovingEnabled = true;
        this.touchAreaRect = new Rect();
        this.scaleFactor = 1.0f;
        this.selectedChar = SelectableOcrResult.Companion.getEMPTY_CHARACTER();
        RotateDrawable rotateDrawable = this.drawStrategy.getRotateDrawable(context2, this.color, this.isGradientEnabled);
        this.drawableWidth = rotateDrawable.getIntrinsicWidth() / 3;
        this.drawableHeight = rotateDrawable.getIntrinsicHeight() / 3;
    }

    private final int computeAngleOfPoly(Point[] pointArr) {
        float rotationDegree;
        if (this.isLeftToRight) {
            rotationDegree = VectorUtil.INSTANCE.getRotationDegree(pointArr[3], pointArr[2]);
        } else {
            rotationDegree = VectorUtil.INSTANCE.getRotationDegree(pointArr[2], pointArr[3]);
        }
        return (int) rotationDegree;
    }

    private final Rect createDefaultTouchableAreaRect(Rect rect) {
        return new Rect(rect.left - this.scaledTouchAreaMarginLeft, rect.top - this.scaledTouchAreaMarginTop, rect.right + this.scaledTouchAreaMarginRight, rect.bottom + this.scaledTouchAreaMarginBottom);
    }

    private final int dimenToPixel(int i2) {
        return this.context.getResources().getDimensionPixelSize(i2);
    }

    private final int findHandleVisibleRotationAngle(Point[] pointArr, Rect rect) {
        Integer valueOf = Integer.valueOf(MOCRLang.KHMER);
        Rect rect2 = new Rect();
        View view = this.teView;
        if (view != null) {
            view.getGlobalVisibleRect(rect2);
        }
        int computeAngleOfPoly = computeAngleOfPoly(pointArr);
        Integer[] numArr = {0, 90, -90, valueOf};
        ArrayList arrayList = new ArrayList(4);
        for (int i2 = 0; i2 < 4; i2++) {
            arrayList.add(Integer.valueOf(numArr[i2].intValue() + computeAngleOfPoly));
        }
        Integer[] numArr2 = (Integer[]) arrayList.toArray(new Integer[0]);
        j.e(numArr2, "<this>");
        int length = numArr2.length;
        Object[] copyOf = Arrays.copyOf(numArr2, length + 4);
        System.arraycopy(new Integer[]{0, 90, -90, valueOf}, 0, copyOf, length, 4);
        j.b(copyOf);
        Integer[] numArr3 = (Integer[]) copyOf;
        int length2 = numArr3.length;
        int i7 = 0;
        while (i7 < length2) {
            int intValue = numArr3[i7].intValue();
            Point[] globalVisiblePoly = getGlobalVisiblePoly(getRotatedRect(intValue, rect));
            int length3 = globalVisiblePoly.length;
            int i8 = 0;
            while (i8 < length3) {
                Point point = globalVisiblePoly[i8];
                if (!rect2.contains(point.x, point.y)) {
                    i7++;
                } else {
                    i8++;
                }
            }
            return intValue;
        }
        return MOCRLang.KHMER;
    }

    private final Point[] getGlobalVisiblePoly(Point[] pointArr) {
        int[] iArr = new int[2];
        View view = this.teView;
        if (view != null) {
            view.getLocationInWindow(iArr);
        }
        ArrayList arrayList = new ArrayList(pointArr.length);
        for (Point point : pointArr) {
            arrayList.add(new Point(point.x + iArr[0], point.y + iArr[1]));
        }
        return (Point[]) arrayList.toArray(new Point[0]);
    }

    private final Point[] getRotatedRect(int i2, Rect rect) {
        Point[] rotatedDefaultRect = this.drawStrategy.getRotatedDefaultRect(i2, rect);
        ArrayList arrayList = new ArrayList(rotatedDefaultRect.length);
        for (Point point : rotatedDefaultRect) {
            float f = this.scaleFactor;
            arrayList.add(new Point((int) (((float) point.x) / f), (int) (((float) point.y) / f)));
        }
        return (Point[]) arrayList.toArray(new Point[0]);
    }

    private final void setDegree(RotateDrawable rotateDrawable, int i2) {
        rotateDrawable.setLevel((int) ((((float) i2) / 360.0f) * ((float) 10000)));
    }

    private final void setScaledParameters() {
        float f;
        if (this.isMagnifyHandle) {
            f = this.scaleFactor * 1.2f;
        } else {
            f = this.scaleFactor;
        }
        int dimenToPixel = dimenToPixel(R$dimen.textextraction_handle_touch_margin_top);
        int dimenToPixel2 = dimenToPixel(R$dimen.textextraction_handle_touch_margin_bottom);
        int dimenToPixel3 = dimenToPixel(R$dimen.textextraction_handle_touch_margin_start);
        int dimenToPixel4 = dimenToPixel(R$dimen.textextraction_handle_touch_margin_end);
        this.scaledDrawableWidth = (int) (((float) this.drawableWidth) * f);
        this.scaledDrawableHeight = (int) (((float) this.drawableHeight) * f);
        this.scaledTouchAreaMarginLeft = (int) (((float) dimenToPixel3) * f);
        this.scaledTouchAreaMarginTop = (int) (((float) dimenToPixel) * f);
        this.scaledTouchAreaMarginRight = (int) (((float) dimenToPixel4) * f);
        this.scaledTouchAreaMarginBottom = (int) (((float) dimenToPixel2) * f);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [kotlin.jvm.internal.u, java.lang.Object] */
    private final void showHandleAnimation(LayerDrawable layerDrawable, Rect rect, Rect rect2, int i2, int i7, View view, a aVar) {
        Drawable drawable2 = layerDrawable.getDrawable(0);
        j.c(drawable2, "null cannot be cast to non-null type android.graphics.drawable.RotateDrawable");
        RotateDrawable rotateDrawable = (RotateDrawable) drawable2;
        ? obj = new Object();
        if (this.isShadowEnabled) {
            Drawable drawable3 = layerDrawable.getDrawable(1);
            j.c(drawable3, "null cannot be cast to non-null type android.graphics.drawable.RotateDrawable");
            obj.d = (RotateDrawable) drawable3;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new s3.a(rect, rect2, i2, i7, rotateDrawable, obj, this, view));
        ofFloat.addListener(new Handle$showHandleAnimation$1$2(this, aVar));
        ofFloat.setInterpolator(HANDLE_ANIMATION_INTERPOLATOR);
        ofFloat.setDuration(400);
        this.isAnimationActive = true;
        ofFloat.start();
    }

    /* access modifiers changed from: private */
    public static final void showHandleAnimation$lambda$6$lambda$5(Rect rect, Rect rect2, int i2, int i7, RotateDrawable rotateDrawable, u uVar, Handle handle, View view, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        DrawUtil drawUtil = DrawUtil.INSTANCE;
        Rect interpolateRect = drawUtil.interpolateRect(rect, rect2, floatValue);
        int interpolateInt = drawUtil.interpolateInt(i2, i7, floatValue);
        rotateDrawable.setBounds(interpolateRect);
        RotateDrawable rotateDrawable2 = (RotateDrawable) uVar.d;
        if (rotateDrawable2 != null) {
            rotateDrawable2.setBounds(interpolateRect);
        }
        handle.setDegree(rotateDrawable, interpolateInt);
        RotateDrawable rotateDrawable3 = (RotateDrawable) uVar.d;
        if (rotateDrawable3 != null) {
            handle.setDegree(rotateDrawable3, interpolateInt);
        }
        view.invalidate();
    }

    /* access modifiers changed from: private */
    public static final x updateHandle$lambda$2$lambda$1(Handle handle, HandleDrawInfo handleDrawInfo) {
        handle.updateRectAndDrawable(handleDrawInfo);
        return x.f4917a;
    }

    private final void updateRectAndDrawable(HandleDrawInfo handleDrawInfo) {
        RotateDrawable rotateDrawable;
        this.drawAreaRect = this.drawStrategy.getDefaultRect(handleDrawInfo, this.scaledDrawableWidth, this.scaledDrawableHeight);
        this.rotationAngle = findHandleVisibleRotationAngle(handleDrawInfo.getSelectedChar().getPoly(), this.drawAreaRect);
        HandleDrawStrategy handleDrawStrategy = this.drawStrategy;
        Rect rect = this.drawAreaRect;
        this.touchAreaRect = handleDrawStrategy.getTouchableAreaRect(rect, createDefaultTouchableAreaRect(rect), this.rotationAngle);
        RotateDrawable rotateDrawable2 = this.drawStrategy.getRotateDrawable(this.context, this.color, this.isGradientEnabled);
        setDegree(rotateDrawable2, this.rotationAngle);
        rotateDrawable2.setBounds(this.drawAreaRect);
        if (this.isShadowEnabled) {
            rotateDrawable = this.drawStrategy.getShadowDrawable(this.context);
            setDegree(rotateDrawable, this.rotationAngle);
            rotateDrawable.setBounds(this.drawAreaRect);
        } else {
            rotateDrawable = null;
        }
        this.drawable = new LayerDrawable(new RotateDrawable[]{rotateDrawable2, rotateDrawable});
    }

    public final boolean contains(int i2, int i7) {
        return this.touchAreaRect.contains(i2, i7);
    }

    public final void draw(Canvas canvas) {
        j.e(canvas, "canvas");
        this.drawable.draw(canvas);
    }

    public final Point getEffectiveTouchPoint(Point point) {
        j.e(point, "touchPoint");
        return this.drawStrategy.getEffectiveTouchPoint(point, this.drawableWidth, this.drawableHeight);
    }

    public final SelectableCharacter getSelectedChar() {
        return this.selectedChar;
    }

    public final boolean isMoving() {
        if (this.movingState == MovingState.MOVING) {
            return true;
        }
        return false;
    }

    public final boolean isNotEmpty() {
        return !this.drawAreaRect.isEmpty();
    }

    public final void setEmpty() {
        this.drawAreaRect = new Rect();
        this.touchAreaRect = new Rect();
    }

    public final void setScaleFactor(float f) {
        this.scaleFactor = f;
    }

    public final void setTeView(View view) {
        this.teView = view;
    }

    public final void updateHandle(HandleDrawInfo handleDrawInfo) {
        j.e(handleDrawInfo, "handleDrawInfo");
        this.isAnimationActive = false;
        this.selectedChar = handleDrawInfo.getSelectedChar();
        boolean isLeftToRight2 = handleDrawInfo.isLeftToRight();
        this.isLeftToRight = isLeftToRight2;
        this.drawStrategy = HandleDrawStrategyFactory.INSTANCE.createDrawStrategy(this.handleType, this.movingState, this.isMovingEnabled, isLeftToRight2);
        setScaledParameters();
        if (handleDrawInfo.getCharForAnimation() == null) {
            updateRectAndDrawable(handleDrawInfo);
            return;
        }
        Rect defaultRectForAnimation = this.drawStrategy.getDefaultRectForAnimation(handleDrawInfo, this.scaledDrawableWidth, this.scaledDrawableHeight);
        int findHandleVisibleRotationAngle = findHandleVisibleRotationAngle(handleDrawInfo.getCharForAnimation().getPoly(), defaultRectForAnimation);
        View view = this.teView;
        if (view != null) {
            showHandleAnimation(this.drawable, this.drawAreaRect, defaultRectForAnimation, this.rotationAngle, findHandleVisibleRotationAngle, view, new k(3, this, handleDrawInfo));
        }
    }

    public final void updateMovingState(MovingState movingState2) {
        boolean z;
        j.e(movingState2, "newState");
        this.movingState = movingState2;
        if (movingState2 != MovingState.IDLE) {
            z = true;
        } else {
            z = false;
        }
        this.isMagnifyHandle = z;
    }
}
