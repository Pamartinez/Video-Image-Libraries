package androidx.core.view;

import N2.j;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import k9.C0696a;
import q6.e;
import x.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslTouchTargetDelegate extends TouchDelegate {
    public static boolean DEBUG = false;
    final View mAnchorView;
    final HashSet<CapturedTouchDelegate> mTouchDelegateSet = new HashSet<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private final View mAnchorView;
        private final Queue<Consumer<SeslTouchTargetDelegate>> mQueue = new LinkedList();

        public Builder(View view) {
            this.mAnchorView = view;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$create$1(Consumer consumer) {
            SeslTouchTargetDelegate seslTouchTargetDelegate = new SeslTouchTargetDelegate(this.mAnchorView);
            for (Consumer<SeslTouchTargetDelegate> accept : this.mQueue) {
                accept.accept(seslTouchTargetDelegate);
            }
            consumer.accept(seslTouchTargetDelegate);
            if (SeslTouchTargetDelegate.DEBUG) {
                SeslTouchTargetDelegate.drawTouchBounds(this.mAnchorView, seslTouchTargetDelegate.mTouchDelegateSet);
            }
        }

        public Builder addDelegateView(View view, ExtraInsets extraInsets) {
            this.mQueue.add(new c(view, extraInsets));
            return this;
        }

        public void apply() {
            View view = this.mAnchorView;
            Objects.requireNonNull(view);
            create(new C0696a(1, view));
        }

        public void create(Consumer<SeslTouchTargetDelegate> consumer) {
            this.mAnchorView.post(new e(26, this, consumer));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CapturedTouchDelegate extends TouchDelegate {
        protected final Rect mBounds;
        protected final View mView;

        public CapturedTouchDelegate(Rect rect, View view) {
            super(rect, view);
            this.mBounds = rect;
            this.mView = view;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExtraInsets {
        public static final ExtraInsets NONE = new ExtraInsets(0, 0, 0, 0);
        int bottom;
        int left;
        int right;
        int top;

        private ExtraInsets(int i2, int i7, int i8, int i10) {
            this.left = i2;
            this.top = i7;
            this.right = i8;
            this.bottom = i10;
        }

        public static ExtraInsets of(int i2, int i7, int i8, int i10) {
            if (i2 == 0 && i7 == 0 && i8 == 0 && i10 == 0) {
                return NONE;
            }
            return new ExtraInsets(i2, i7, i8, i10);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ExtraInsets extraInsets = (ExtraInsets) obj;
            if (this.bottom == extraInsets.bottom && this.left == extraInsets.left && this.right == extraInsets.right && this.top == extraInsets.top) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("ExtraInsets{left=");
            sb2.append(this.left);
            sb2.append(", top=");
            sb2.append(this.top);
            sb2.append(", right=");
            sb2.append(this.right);
            sb2.append(", bottom=");
            return j.e(sb2, this.bottom, '}');
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class InvalidDelegateViewException extends RuntimeException {
        public InvalidDelegateViewException() {
            super("TouchTargetDelegate's delegateView must be child of anchorView");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TouchBoundsPainter {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class TouchBoundsBitmapDrawable extends BitmapDrawable {
            public TouchBoundsBitmapDrawable(Resources resources, Bitmap bitmap) {
                super(resources, bitmap);
            }
        }

        public static void drawTouchBounds(View view, List<Rect> list) {
            if (view.getMeasuredWidth() > 0 && view.getMeasuredHeight() > 0) {
                Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(0);
                Paint paint = new Paint();
                paint.setStrokeWidth(3.0f);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.parseColor("#34C3EF"));
                for (Rect drawRect : list) {
                    canvas.drawRect(drawRect, paint);
                }
                TouchBoundsBitmapDrawable touchBoundsBitmapDrawable = new TouchBoundsBitmapDrawable(view.getResources(), createBitmap);
                Drawable foreground = view.getForeground();
                if (foreground instanceof LayerDrawable) {
                    LayerDrawable layerDrawable = (LayerDrawable) foreground;
                    int numberOfLayers = layerDrawable.getNumberOfLayers() - 1;
                    if (layerDrawable.getDrawable(numberOfLayers) instanceof TouchBoundsBitmapDrawable) {
                        layerDrawable.setDrawable(numberOfLayers, touchBoundsBitmapDrawable);
                        return;
                    }
                }
                view.setForeground(new LayerDrawable((Drawable[]) Arrays.asList(new Drawable[]{foreground, touchBoundsBitmapDrawable}).toArray(new Drawable[2])));
            }
        }
    }

    public SeslTouchTargetDelegate(View view) {
        super(new Rect(), view);
        this.mAnchorView = view;
    }

    /* JADX WARNING: type inference failed for: r2v10, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Rect calculateViewBounds(android.view.View r4, android.view.View r5) {
        /*
            android.graphics.Rect r0 = new android.graphics.Rect
            int r1 = r5.getWidth()
            int r2 = r5.getHeight()
            r3 = 0
            r0.<init>(r3, r3, r1, r2)
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
        L_0x0013:
            boolean r2 = r5.equals(r4)
            if (r2 != 0) goto L_0x0045
            r5.getHitRect(r1)
            int r2 = r0.left
            int r3 = r1.left
            int r2 = r2 + r3
            r0.left = r2
            int r2 = r0.right
            int r3 = r1.left
            int r2 = r2 + r3
            r0.right = r2
            int r2 = r0.top
            int r3 = r1.top
            int r2 = r2 + r3
            r0.top = r2
            int r2 = r0.bottom
            int r3 = r1.top
            int r2 = r2 + r3
            r0.bottom = r2
            android.view.ViewParent r2 = r5.getParent()
            boolean r3 = r2 instanceof android.view.View
            if (r3 != 0) goto L_0x0041
            goto L_0x0045
        L_0x0041:
            r5 = r2
            android.view.View r5 = (android.view.View) r5
            goto L_0x0013
        L_0x0045:
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x004c
            return r0
        L_0x004c:
            androidx.core.view.SeslTouchTargetDelegate$InvalidDelegateViewException r4 = new androidx.core.view.SeslTouchTargetDelegate$InvalidDelegateViewException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.SeslTouchTargetDelegate.calculateViewBounds(android.view.View, android.view.View):android.graphics.Rect");
    }

    public static void drawTouchBounds(View view, HashSet<CapturedTouchDelegate> hashSet) {
        if (DEBUG) {
            ArrayList arrayList = new ArrayList();
            Iterator<CapturedTouchDelegate> it = hashSet.iterator();
            while (it.hasNext()) {
                CapturedTouchDelegate next = it.next();
                if (next.mView.getVisibility() != 8) {
                    arrayList.add(next.mBounds);
                }
            }
            if (arrayList.size() > 0) {
                TouchBoundsPainter.drawTouchBounds(view, arrayList);
            }
        }
    }

    public TouchDelegate addTouchDelegate(Rect rect, View view) {
        CapturedTouchDelegate capturedTouchDelegate = new CapturedTouchDelegate(rect, view);
        this.mTouchDelegateSet.add(capturedTouchDelegate);
        return capturedTouchDelegate;
    }

    public AccessibilityNodeInfo.TouchDelegateInfo getTouchDelegateInfo() {
        Log.i("SeslTouchTargetDelegate", "SeslTouchTargetDelegate does not support accessibility because it cannot support multi-touch delegation with AOSP View");
        ArrayMap arrayMap = new ArrayMap(1);
        arrayMap.put(new Region(), this.mAnchorView);
        return new AccessibilityNodeInfo.TouchDelegateInfo(arrayMap);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Iterator<CapturedTouchDelegate> it = this.mTouchDelegateSet.iterator();
        while (it.hasNext()) {
            CapturedTouchDelegate next = it.next();
            if (next.mView.getParent() == null) {
                Log.w("SeslTouchTargetDelegate", "delegate view(" + next.mView + ")'s getParent() is null");
            } else if (next.onTouchEvent(motionEvent)) {
                if (!DEBUG) {
                    return true;
                }
                Log.i("SeslTouchTargetDelegate", "touchEvent was consumed on touchDelegate " + next.mView);
                return true;
            }
        }
        return false;
    }

    public boolean onTouchExplorationHoverEvent(MotionEvent motionEvent) {
        Log.i("SeslTouchTargetDelegate", "SeslTouchTargetDelegate does not support accessibility because it cannot support multi-touch delegation with AOSP View");
        return false;
    }

    public TouchDelegate addTouchDelegate(View view, ExtraInsets extraInsets) {
        try {
            Rect calculateViewBounds = calculateViewBounds(this.mAnchorView, view);
            if (extraInsets != null) {
                calculateViewBounds.left -= extraInsets.left;
                calculateViewBounds.top -= extraInsets.top;
                calculateViewBounds.right += extraInsets.right;
                calculateViewBounds.bottom += extraInsets.bottom;
            }
            return addTouchDelegate(calculateViewBounds, view);
        } catch (InvalidDelegateViewException e) {
            Log.w("SeslTouchTargetDelegate", "delegateView must be child of anchorView");
            e.printStackTrace();
            return null;
        }
    }
}
