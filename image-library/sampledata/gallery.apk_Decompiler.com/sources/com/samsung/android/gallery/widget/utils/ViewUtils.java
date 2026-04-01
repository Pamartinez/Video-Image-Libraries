package com.samsung.android.gallery.widget.utils;

import A.a;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Size;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.insets.GradientProtection;
import androidx.core.view.insets.ProtectionLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.R$dimen;
import e6.C0453a;
import gc.f;
import h2.s;
import h2.u;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewUtils {
    private static String sLastRemovedViewLog;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ProtectionGradientType {
        ALL,
        HORIZONTAL,
        VERTICAL
    }

    public static void addView(ViewGroup viewGroup, View view) {
        if (viewGroup != null && view != null) {
            viewGroup.addView(view);
        }
    }

    public static void cancelAnimation(View view) {
        Animation animation;
        if (view != null) {
            animation = view.getAnimation();
        } else {
            animation = null;
        }
        if (animation != null && animation.hasStarted()) {
            animation.setAnimationListener((Animation.AnimationListener) null);
            animation.cancel();
            view.clearAnimation();
        }
    }

    public static ArrayList<View> childOf(ViewGroup viewGroup) {
        ArrayList<View> arrayList = new ArrayList<>();
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof ViewGroup) {
                arrayList.addAll(childOf((ViewGroup) childAt));
            } else {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    public static <T extends View> void consumeIfPresent(T t, Consumer<T> consumer) {
        if (t != null) {
            consumer.accept(t);
        }
    }

    public static boolean contains(View view, View view2) {
        if (!(view == null || view2 == null)) {
            RectF viewRect = getViewRect(view);
            RectF viewRect2 = getViewRect(view2);
            if (view.getHeight() < view2.getHeight()) {
                float f = viewRect.top;
                float f5 = viewRect2.top;
                if (f > f5 || viewRect.bottom < f5) {
                    float f8 = viewRect2.bottom;
                    if ((f > f8 || viewRect.bottom < f8) && (f < f5 || viewRect.bottom > f8)) {
                        return false;
                    }
                }
                return true;
            } else if (viewRect.top > viewRect2.top || viewRect.bottom < viewRect2.bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public static View createAnchorViewInEventPosition(ViewGroup viewGroup, MotionEvent motionEvent) {
        View view = new View(viewGroup.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
        view.setBackgroundColor(0);
        viewGroup.addView(view);
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        view.setX(motionEvent.getRawX() - ((float) iArr[0]));
        view.setY(motionEvent.getRawY() - ((float) iArr[1]));
        return view;
    }

    private static GradientProtection createGradientProtection(int i2, int i7) {
        GradientProtection gradientProtection = new GradientProtection(i2, i7);
        gradientProtection.setScale(2.0f);
        return gradientProtection;
    }

    public static void disableSeslRecoil(View view) {
        if (view instanceof ViewPager2) {
            try {
                Optional.ofNullable(((ViewPager2) view).getChildAt(0)).ifPresent(new C0453a(17));
            } catch (Exception e) {
                a.s(e, new StringBuilder("disableSeslRecoil failed. e="), "ViewUtils");
            }
        }
    }

    public static void doOnApplyWindowInsets(View view, s sVar) {
        u.a(view, sVar);
    }

    public static String dump() {
        return sLastRemovedViewLog;
    }

    public static ViewGroup findParentViewById(View view, int i2) {
        if (view == null) {
            return null;
        }
        if (view.getId() == i2) {
            return (ViewGroup) view;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof View)) {
            return null;
        }
        return findParentViewById((View) parent, i2);
    }

    public static ViewGroup findParentViewByTag(View view, String str) {
        if (!str.equals(view.getTag())) {
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                return findParentViewByTag((View) parent, str);
            }
            return null;
        } else if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        } else {
            return null;
        }
    }

    public static View findParentViewHasSpace(ViewParent viewParent) {
        while (viewParent instanceof View) {
            View view = (View) viewParent;
            if (view.getWidth() != 0 && view.getHeight() != 0) {
                return view;
            }
            viewParent = viewParent.getParent();
        }
        return null;
    }

    public static float getAlpha(View view) {
        if (view != null) {
            return view.getAlpha();
        }
        return 0.0f;
    }

    public static Bitmap getBitmapFromSurfaceView(SurfaceView surfaceView) {
        if (surfaceView == null) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        AtomicReference atomicReference = new AtomicReference((Object) null);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        loadSurfaceCaptureBitmap(surfaceView, new e(21, atomicReference, countDownLatch));
        try {
            countDownLatch.await(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        Log.d("ViewUtils", "getBitmapFromSurfaceView " + Logger.toSimpleString((Bitmap) atomicReference.get()) + " +" + (System.currentTimeMillis() - currentTimeMillis));
        return (Bitmap) atomicReference.get();
    }

    public static Bitmap getBitmapFromView(View view) {
        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0 || view.getWidth() <= 0 || view.getHeight() <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static int getExpectedMaxWidth(View view) {
        View findParentViewHasSpace = findParentViewHasSpace(view.getParent());
        if (findParentViewHasSpace != null) {
            return findParentViewHasSpace.getWidth();
        }
        return view.getRootView().getWidth();
    }

    public static int getFlexibleSideSpace(View view, Activity activity) {
        Resources resources;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (!needUpdateSideSpacing(displayMetrics) || (resources = view.getResources()) == null) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        resources.getValue(R$dimen.list_view_width_ratio, typedValue, true);
        if (resources.getBoolean(R$bool.isDexDrawerSize)) {
            return ((int) (((float) displayMetrics.widthPixels) - resources.getDimension(R$dimen.content_layout_width_for_large_screen))) / 2;
        }
        return ((int) ((1.0f - typedValue.getFloat()) * ((float) displayMetrics.widthPixels))) / 2;
    }

    public static Rect getGlobalVisileRect(View view) {
        if (view == null) {
            return new Rect();
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect;
    }

    public static int getHeight(View view) {
        if (view != null) {
            return view.getHeight();
        }
        return 0;
    }

    public static <T extends ViewGroup.LayoutParams> T getLayoutParams(View view, Class<T> cls) {
        if (view == null) {
            return null;
        }
        return (ViewGroup.LayoutParams) cls.cast(view.getLayoutParams());
    }

    public static int getMeasureTextViewWidth(TextView textView) {
        TextPaint paint;
        if (textView == null || (paint = textView.getPaint()) == null || TextUtils.isEmpty(textView.getText())) {
            return 0;
        }
        return (int) (paint.measureText(textView.getText(), 0, textView.getText().length()) + 1.0f);
    }

    public static int getMeasuredHeight(View view) {
        return getMeasuredSize(view)[1];
    }

    public static int[] getMeasuredSize(View view) {
        if (view == null) {
            return new int[]{0, 0};
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (width == 0 || height == 0) {
            view.measure(0, 0);
            width = view.getMeasuredWidth();
            height = view.getMeasuredHeight();
        }
        return new int[]{width, height};
    }

    public static int getMeasuredWidth(View view) {
        return getMeasuredSize(view)[0];
    }

    public static String getTransitionName(View view) {
        if (view != null) {
            return view.getTransitionName();
        }
        return "";
    }

    public static PointF getViewCenter(View view) {
        return new PointF((float) ((((view.getWidth() - view.getPaddingRight()) - view.getPaddingLeft()) / 2) + view.getPaddingLeft()), (float) ((((view.getHeight() - view.getPaddingBottom()) - view.getPaddingTop()) / 2) + view.getPaddingTop()));
    }

    public static RectF getViewRect(View view) {
        if (view == null) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        return new RectF((float) i2, (float) iArr[1], (float) (view.getWidth() + i2), (float) (view.getHeight() + iArr[1]));
    }

    public static Size getViewSize(View view) {
        if (view != null) {
            return new Size(view.getWidth(), view.getHeight());
        }
        return null;
    }

    public static Character getVisibilityCode(View view) {
        char c5;
        if (view == null) {
            return 'N';
        }
        int visibility = view.getVisibility();
        if (visibility == 0) {
            c5 = 'V';
        } else if (visibility == 8) {
            c5 = 'G';
        } else {
            c5 = 'I';
        }
        return Character.valueOf(c5);
    }

    public static int getWidth(View view) {
        if (view != null) {
            return view.getWidth();
        }
        return 0;
    }

    public static boolean hasFocus(View view) {
        if (view == null || !view.hasFocus()) {
            return false;
        }
        return true;
    }

    public static View inflateViewStub(View view) {
        if (isViewStub(view)) {
            return ((ViewStub) view).inflate();
        }
        return view;
    }

    public static boolean isAttachedToWindow(View view) {
        if (view == null || !view.isAttachedToWindow()) {
            return false;
        }
        return true;
    }

    public static boolean isGone(View view) {
        if (view == null || view.getVisibility() == 8) {
            return true;
        }
        return false;
    }

    public static boolean isInVisibleRange(View view, View view2, float f) {
        return isInVisibleRange(view, view2, f, false);
    }

    public static boolean isOpaque(View view) {
        if (view == null || view.getAlpha() == 0.0f) {
            return false;
        }
        return true;
    }

    public static boolean isShown(View view) {
        if (view == null || !view.isShown()) {
            return false;
        }
        return true;
    }

    public static boolean isStubVisible(View view) {
        if (view == null || (view instanceof ViewStub) || view.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public static boolean isTouchedOnLocalView(View view, MotionEvent motionEvent, int i2) {
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        return rect.contains((int) motionEvent.getX(), ((int) motionEvent.getY()) + i2);
    }

    public static boolean isTouchedOnRange(MotionEvent motionEvent, int i2, int i7) {
        if (((float) i2) >= motionEvent.getY() || motionEvent.getY() >= ((float) i7)) {
            return false;
        }
        return true;
    }

    public static boolean isTouchedOnView(View view, MotionEvent motionEvent) {
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    public static boolean isTouchedOnViewRaw(View view, MotionEvent motionEvent) {
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
    }

    public static boolean isTranslucent(View view) {
        if (view == null || view.getAlpha() != 0.0f) {
            return false;
        }
        return true;
    }

    public static boolean isViewStub(View view) {
        return view instanceof ViewStub;
    }

    public static boolean isViewStubInflatable(ViewStub viewStub) {
        if (viewStub == null || !(viewStub.getParent() instanceof ViewGroup)) {
            return false;
        }
        return true;
    }

    public static boolean isViewVisibilityChanged(View view, int i2) {
        if (view == null || view.getVisibility() == i2) {
            return false;
        }
        return true;
    }

    public static boolean isVisible(View view) {
        if (view == null || view.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public static boolean isWindowAttachedToWindow(Window window) {
        View view;
        if (window != null) {
            view = window.getDecorView();
        } else {
            view = null;
        }
        return isAttachedToWindow(view);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getBitmapFromSurfaceView$1(AtomicReference atomicReference, CountDownLatch countDownLatch, Bitmap bitmap) {
        atomicReference.set(bitmap);
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadSurfaceCaptureBitmap$0(Consumer consumer, Bitmap bitmap, int i2) {
        if (i2 != 0) {
            bitmap = null;
        }
        consumer.accept(bitmap);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setTouchArea$2(View view, int i2, int i7, int i8, int i10, View view2) {
        int i11;
        boolean z = true;
        if (view.getLayoutDirection() != 1) {
            z = false;
        }
        Rect rect = new Rect();
        view.getHitRect(rect);
        int i12 = rect.left;
        if (z) {
            i11 = i2;
        } else {
            i11 = i7;
        }
        rect.left = i12 - i11;
        rect.top -= i8;
        int i13 = rect.right;
        if (z) {
            i2 = i7;
        }
        rect.right = i13 + i2;
        rect.bottom += i10;
        view2.setTouchDelegate(new GalleryTouchDelegate(rect, view));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setTouchAreaComposite$3(View view, int i2, int i7, int i8, int i10, View view2) {
        int i11;
        boolean z = true;
        if (view.getLayoutDirection() != 1) {
            z = false;
        }
        Rect rect = new Rect();
        view.getHitRect(rect);
        int i12 = rect.left;
        if (z) {
            i11 = i2;
        } else {
            i11 = i7;
        }
        rect.left = i12 - i11;
        rect.top -= i8;
        int i13 = rect.right;
        if (z) {
            i2 = i7;
        }
        rect.right = i13 + i2;
        rect.bottom += i10;
        if (view2.getTouchDelegate() instanceof TouchDelegateComposite) {
            ((TouchDelegateComposite) view2.getTouchDelegate()).addDelegate(rect, view);
        }
    }

    public static void loadSurfaceCaptureBitmap(SurfaceView surfaceView, Consumer<Bitmap> consumer) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
            PixelCopy.request(surfaceView, createBitmap, new f(createBitmap, consumer), ThreadUtil.getBackgroundThreadHandler());
        } catch (Exception e) {
            Log.w((CharSequence) "ViewUtils", "loadSurfaceCaptureBitmap fail : ", e.toString());
            consumer.accept((Object) null);
        }
    }

    public static boolean needUpdateSideSpacing(DisplayMetrics displayMetrics) {
        int pixelsToDp = pixelsToDp(displayMetrics.densityDpi, (float) displayMetrics.widthPixels);
        int pixelsToDp2 = pixelsToDp(displayMetrics.densityDpi, (float) displayMetrics.heightPixels);
        if (pixelsToDp < 589 || pixelsToDp2 <= 411) {
            return false;
        }
        return true;
    }

    private static int pixelsToDp(int i2, float f) {
        return (int) (f / (((float) i2) / 160.0f));
    }

    public static void post(View view, Runnable runnable) {
        if (view != null) {
            view.post(runnable);
        }
    }

    public static void postDelayed(View view, Runnable runnable, long j2) {
        if (view != null) {
            view.postDelayed(runnable, j2);
        }
    }

    public static void postOnGlobalLayout(View view, final Runnable runnable) {
        if (view != null) {
            final ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        runnable.run();
                        if (viewTreeObserver.isAlive()) {
                            viewTreeObserver.removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    public static View reinflateViewStub(ViewStub viewStub, View view) {
        int visibility = view.getVisibility();
        replaceView(view, viewStub);
        View inflate = viewStub.inflate();
        setVisibility(inflate, visibility);
        return inflate;
    }

    public static void removeAllViews(ViewGroup viewGroup) {
        ThreadUtil.assertUiThread("removeAllViews" + Logger.toString((View) viewGroup));
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            if (viewGroup instanceof FrameLayout) {
                sLastRemovedViewLog = "removeAllViews " + Logger.v(viewGroup);
            }
        }
    }

    public static void removeAllViewsInLayout(ViewGroup viewGroup) {
        if (viewGroup != null) {
            viewGroup.removeAllViewsInLayout();
            if (viewGroup instanceof FrameLayout) {
                sLastRemovedViewLog = "removeAllViewsInLayout " + Logger.v(viewGroup);
            }
        }
    }

    public static void removeSelf(View view) {
        if (view != null) {
            removeView((ViewGroup) view.getParent(), view);
        }
    }

    public static void removeView(ViewGroup viewGroup, View view) {
        ThreadUtil.assertUiThread("removeView" + Logger.toString(view));
        if (viewGroup != null && view != null) {
            viewGroup.removeView(view);
            if (viewGroup instanceof FrameLayout) {
                sLastRemovedViewLog = "removeView " + Logger.v(viewGroup, view);
            }
        }
    }

    public static void replaceView(ViewGroup viewGroup, View view, View view2) {
        if (viewGroup != null && view != null) {
            int indexOfChild = viewGroup.indexOfChild(view);
            if (indexOfChild < 0) {
                Log.e("ViewUtils", "replaceView failed invalid index");
                return;
            }
            viewGroup.removeViewInLayout(view);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                viewGroup.addView(view2, indexOfChild, layoutParams);
            } else {
                viewGroup.addView(view2, indexOfChild);
            }
        }
    }

    public static void requestCancelDraw(View view, int i2, Runnable runnable) {
        try {
            Log.w("ViewUtils", "requestCancelDraw " + i2);
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(i2, view, runnable) {
                int timesLeft;
                final /* synthetic */ int val$nFrameTimes;
                final /* synthetic */ Runnable val$runnable;
                final /* synthetic */ View val$view;

                {
                    this.val$nFrameTimes = r1;
                    this.val$view = r2;
                    this.val$runnable = r3;
                    this.timesLeft = Math.max(0, r1);
                }

                /* JADX INFO: finally extract failed */
                public boolean onPreDraw() {
                    try {
                        int i2 = this.timesLeft;
                        if (i2 > 0) {
                            if (i2 == 1) {
                                this.val$view.getViewTreeObserver().removeOnPreDrawListener(this);
                                Runnable runnable = this.val$runnable;
                                if (runnable != null) {
                                    runnable.run();
                                }
                            }
                            this.timesLeft--;
                            return false;
                        }
                        this.timesLeft = i2 - 1;
                        return true;
                    } catch (Throwable th) {
                        this.timesLeft--;
                        throw th;
                    }
                }
            });
        } catch (NullPointerException e) {
            Log.e("ViewUtils", e.toString());
        }
    }

    public static void resetAccessibilityFocus(View view) {
        if (view != null) {
            view.performAccessibilityAction(128, (Bundle) null);
            view.post(new D5.a(view, 5));
        }
    }

    public static void resetPivot(View view) {
        if (view != null) {
            view.resetPivot();
        }
    }

    public static void resize(View view, int i2, int i7) {
        ViewGroup.LayoutParams layoutParams;
        if (view != null) {
            layoutParams = view.getLayoutParams();
        } else {
            layoutParams = null;
        }
        if (layoutParams != null) {
            layoutParams.width = i2;
            layoutParams.height = i7;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setAccessibilityDelegate(View view, View.AccessibilityDelegate accessibilityDelegate) {
        if (view != null) {
            view.setAccessibilityDelegate(accessibilityDelegate);
        }
    }

    public static void setAccessibilityRoleDescription(View view, final int i2) {
        if (view != null) {
            view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                    AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setRoleDescription(AppResources.getString(i2));
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                }
            });
        }
    }

    public static void setAllViewEnabled(View view, boolean z, boolean z3) {
        setViewEnabled(view, z, z3);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                setAllViewEnabled(viewGroup.getChildAt(i2), z, false);
            }
        }
    }

    public static void setAlpha(View view, float f) {
        if (view != null) {
            view.setAlpha(f);
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        if (view != null) {
            view.setBackground(drawable);
        }
    }

    public static void setBackgroundColor(View view, int i2) {
        if (view != null) {
            view.setBackgroundColor(i2);
        }
    }

    public static void setBackgroundResource(View view, int i2) {
        if (view != null) {
            view.setBackgroundResource(i2);
        }
    }

    public static void setContentDescription(View view, String str) {
        if (view != null) {
            view.setContentDescription(str);
        }
    }

    public static void setDrawableCornerRadius(Drawable drawable, float f) {
        if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setCornerRadius(f);
        }
    }

    public static void setFlexibleSideMargin(View view, View view2) {
        if (view != null && view2 != null) {
            ViewMarginUtils.setHorizontalMargin(view2, getFlexibleSideSpace(view, (Activity) view.getContext()));
        }
    }

    public static void setForeground(View view, Drawable drawable) {
        if (view != null) {
            view.setForeground(drawable);
        }
    }

    public static void setHeight(View view, int i2) {
        ViewGroup.LayoutParams layoutParams;
        if (view != null) {
            layoutParams = view.getLayoutParams();
        } else {
            layoutParams = null;
        }
        if (layoutParams != null && layoutParams.height != i2) {
            layoutParams.height = i2;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setImageDrawable(ImageView imageView, Drawable drawable) {
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public static void setImageTintColor(ImageView imageView, ColorStateList colorStateList) {
        if (imageView != null) {
            imageView.setImageTintList(colorStateList);
        }
    }

    public static void setLastRoundedCorner(RecyclerView recyclerView, boolean z) {
        if (recyclerView != null) {
            recyclerView.seslSetLastRoundedCorner(z);
        }
    }

    public static void setMainLayoutFlexibleSideSpacing(View view, View view2) {
        int flexibleSideSpace;
        if (view != null && (flexibleSideSpace = getFlexibleSideSpace(view, (Activity) view.getContext())) > 0) {
            view.setPadding(flexibleSideSpace, view.getPaddingTop(), flexibleSideSpace, view.getPaddingBottom());
            ViewMarginUtils.setHorizontalMargin(view2, -flexibleSideSpace);
        }
    }

    public static void setMatchParentView(View view) {
        if (view != null) {
            view.setId(View.generateViewId());
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = -1;
                layoutParams.height = -1;
                view.setLayoutParams(layoutParams);
                return;
            }
            Log.e("ViewUtils", "setMatchParentView no parent " + view);
        }
    }

    public static void setMaxLines(TextView textView, int i2) {
        if (textView != null) {
            textView.setMaxLines(i2);
        }
    }

    public static void setOnClickListener(View view, View.OnClickListener onClickListener) {
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public static void setRotation(View view, float f) {
        if (view != null) {
            view.setRotation(f);
        }
    }

    public static void setScale(View view, float f, float f5) {
        if (view != null) {
            view.setScaleX(f);
            view.setScaleY(f5);
        }
    }

    public static void setShapeBorder(View view, Drawable drawable) {
        if (view != null) {
            view.setForeground(drawable);
        }
    }

    public static void setStubVisibility(View view, int i2) {
        if (view != null && !(view instanceof ViewStub) && view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
    }

    public static void setText(TextView textView, String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    public static void setTextColor(TextView textView, int i2) {
        if (textView != null) {
            textView.setTextColor(textView.getContext().getColor(i2));
        }
    }

    public static void setTextSize(TextView textView, int i2, int i7) {
        if (textView != null) {
            textView.setTextSize(i2, (float) i7);
        }
    }

    public static void setTouchArea(View view, int i2, int i7, int i8, int i10) {
        setTouchArea(view, (View) view.getParent(), i2, i7, i8, i10);
    }

    public static void setTouchAreaComposite(View view, int i2) {
        Resources resources = view != null ? view.getResources() : null;
        int dimensionPixelOffset = resources != null ? resources.getDimensionPixelOffset(i2) : 0;
        setTouchAreaComposite(view, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
    }

    public static void setTranslationX(View view, float f) {
        if (view != null) {
            view.setTranslationX(f);
        }
    }

    public static void setTranslationY(View view, float f) {
        if (view != null) {
            view.setTranslationY(f);
        }
    }

    public static void setTranslationZ(View view, float f) {
        if (view != null) {
            view.setTranslationZ(f);
        }
    }

    public static void setViewEnabled(View view, boolean z) {
        setViewEnabled(view, z, true);
    }

    public static void setViewEnabledWithoutAlphaChange(View view, boolean z) {
        setViewEnabled(view, z, false);
    }

    public static void setViewShape(View view, final int i2, final float f) {
        if (view != null) {
            view.setOutlineProvider(new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    float f;
                    int i2;
                    if (view.getWidth() > 0) {
                        if (i2 == 0) {
                            f = ((float) view.getWidth()) / 2.0f;
                        } else {
                            f = f;
                        }
                        float f5 = f;
                        int i7 = 0;
                        if (i2 == 3) {
                            i2 = (int) (-f5);
                        } else {
                            i2 = 0;
                        }
                        int width = view.getWidth();
                        int height = view.getHeight();
                        if (i2 == 2) {
                            i7 = (int) f5;
                        }
                        outline.setRoundRect(0, i2, width, height + i7, f5);
                    }
                }
            });
            view.setClipToOutline(true);
        }
    }

    public static void setViewSize(View view, Integer num, Integer num2) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (num != null) {
                layoutParams.width = num.intValue();
            }
            if (num2 != null) {
                layoutParams.height = num2.intValue();
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setVisibility(View view, int i2) {
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    public static void setVisibleOrGone(View view, boolean z) {
        int i2;
        if (view != null) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
    }

    public static void setVisibleOrInvisible(View view, boolean z) {
        int i2;
        if (view != null) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 4;
            }
            view.setVisibility(i2);
        }
    }

    public static void setWidth(View view, int i2) {
        ViewGroup.LayoutParams layoutParams;
        if (view != null) {
            layoutParams = view.getLayoutParams();
        } else {
            layoutParams = null;
        }
        if (layoutParams != null && layoutParams.width != i2) {
            layoutParams.width = i2;
            view.setLayoutParams(layoutParams);
        }
    }

    public static <T extends View> void startAnimation(T t, Function<T, Animation> function) {
        if (t != null) {
            t.startAnimation(function.apply(t));
        }
    }

    public static void updateGradientProtection(ProtectionLayout protectionLayout, ProtectionGradientType protectionGradientType, int i2) {
        if (protectionLayout != null) {
            ArrayList arrayList = new ArrayList();
            if (protectionGradientType == ProtectionGradientType.ALL) {
                arrayList.add(createGradientProtection(2, i2));
                arrayList.add(createGradientProtection(8, i2));
                arrayList.add(createGradientProtection(1, i2));
                arrayList.add(createGradientProtection(4, i2));
            } else if (protectionGradientType == ProtectionGradientType.VERTICAL) {
                arrayList.add(createGradientProtection(2, i2));
                arrayList.add(createGradientProtection(8, i2));
            } else if (protectionGradientType == ProtectionGradientType.HORIZONTAL) {
                arrayList.add(createGradientProtection(1, i2));
                arrayList.add(createGradientProtection(4, i2));
            }
            protectionLayout.setProtections(arrayList);
        }
    }

    public static boolean isInVisibleRange(View view, View view2, float f, boolean z) {
        if (view != null && (z || view.isAttachedToWindow())) {
            RectF viewRect = getViewRect(view2);
            RectF viewRect2 = getViewRect(view);
            if (viewRect2.intersect(viewRect)) {
                if ((viewRect2.height() * viewRect2.width()) / ((float) (view.getHeight() * view.getWidth())) > f) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void setText(TextView textView, CharSequence charSequence) {
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public static void setTouchArea(View view, View view2, int i2, int i7, int i8, int i10) {
        if (view != null && view2 != null) {
            View view3 = view2;
            view3.post(new gc.e(view, i8, i2, i7, i10, view3, 0));
        }
    }

    private static void setViewEnabled(View view, boolean z, boolean z3) {
        if (view != null) {
            view.setEnabled(z);
            if (z3) {
                view.setAlpha(z ? 1.0f : 0.45f);
            }
        }
    }

    public static void setText(TextView textView, int i2) {
        if (textView != null) {
            textView.setText(i2);
        }
    }

    public static void requestCancelDraw(View view, int i2) {
        requestCancelDraw(view, i2, (Runnable) null);
    }

    public static void setMainLayoutFlexibleSideSpacing(View view) {
        if (view != null) {
            setMainLayoutFlexibleSideSpacing(view, (Activity) view.getContext());
        }
    }

    public static void setTouchAreaComposite(View view, int i2, int i7, int i8, int i10) {
        View view2 = view != null ? (View) view.getParent() : null;
        if (view2 != null) {
            if (view2.getTouchDelegate() == null) {
                view2.setTouchDelegate(new TouchDelegateComposite(view2));
            }
            view2.post(new gc.e(view, i8, i2, i7, i10, view2, 1));
        }
    }

    public static void setMainLayoutFlexibleSideSpacing(View view, Activity activity) {
        if (view != null) {
            int flexibleSideSpace = getFlexibleSideSpace(view, activity);
            view.setPadding(flexibleSideSpace, view.getPaddingTop(), flexibleSideSpace, view.getPaddingBottom());
        }
    }

    public static void replaceView(View view, View view2) {
        if (view != null) {
            replaceView((ViewGroup) view.getParent(), view, view2);
        }
    }
}
