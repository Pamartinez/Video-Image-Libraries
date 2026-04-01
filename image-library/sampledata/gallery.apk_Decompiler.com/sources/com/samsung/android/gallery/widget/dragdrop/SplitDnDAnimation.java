package com.samsung.android.gallery.widget.dragdrop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadowInfo;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;
import n4.C0491c;
import r6.e;
import tb.C0709a;
import ub.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SplitDnDAnimation implements DnDAnimation {
    /* access modifiers changed from: private */
    public ViewGroup mAnimViewsParent;
    private final ArrayList<Bitmap> mBitmapList = new ArrayList<>();
    private Paint mBorderPaint;
    private Point mDiffPositionFromRootView = new Point();
    private final DragShadowInfo mDragShadowInfo = new DragShadowInfo();
    private float mDragThumbRadius;
    private int mDragThumbSize;
    /* access modifiers changed from: private */
    public final ArrayList<DragViewSet> mDragViewSets = new ArrayList<>();
    /* access modifiers changed from: private */
    public AnimatorSet mGatherAnimation;
    private int mIconHalfSize;
    private int mInsetLeft = 0;
    private int mInsetTop = 0;
    /* access modifiers changed from: private */
    public boolean mIsDragging = false;
    private boolean mIsRtl;
    private BooleanSupplier mIsSplitMode;
    private boolean mIsStarted;
    private GalleryListView mListView;
    /* access modifiers changed from: private */
    public View mPressedThumbnailView;
    private ListViewHolder mPressedViewHolder;
    private int mPrevX;
    private int mPrevY;

    private void calcDiffPositionFromRootView() {
        int[] iArr = new int[2];
        this.mAnimViewsParent.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        this.mListView.getLocationInWindow(iArr2);
        this.mDiffPositionFromRootView = new Point(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
    }

    private void calcInsetSize() {
        WindowInsets rootWindowInsets = this.mListView.getRootWindowInsets();
        if (rootWindowInsets != null) {
            this.mInsetLeft = rootWindowInsets.getInsets(WindowInsets.Type.systemBars()).left;
            this.mInsetTop = rootWindowInsets.getInsets(WindowInsets.Type.systemBars()).top;
        }
    }

    private void createAndAddAnimView(RecyclerView.ViewHolder viewHolder, int i2) {
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        View makeAnimView = makeAnimView(viewGroup);
        this.mAnimViewsParent.addView(makeAnimView);
        this.mDragViewSets.add(new DragViewSet(viewGroup, makeAnimView, i2));
    }

    private void createAnimViews() {
        ArrayList<Integer> selectedItemList = this.mListView.getSelectedItemList();
        if (selectedItemList != null) {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            Stream<Integer> filter = selectedItemList.stream().filter(new a(this, 0));
            GalleryListView galleryListView = this.mListView;
            Objects.requireNonNull(galleryListView);
            filter.map(new C0709a(1, galleryListView)).filter(new a(this, 1)).forEach(new C0491c(17, this, atomicInteger));
        }
    }

    private ArrayList<ObjectAnimator> createGatherAnimators(float f, float f5) {
        ArrayList<ObjectAnimator> arrayList = new ArrayList<>();
        this.mDragViewSets.forEach(new b(this, f, f5, arrayList));
        return arrayList;
    }

    private void drawBorder(Canvas canvas, RectF rectF) {
        float f = this.mDragThumbRadius;
        canvas.drawRoundRect(rectF, f, f, getBorderPaint());
    }

    private void drawThumbnail(Canvas canvas, Bitmap bitmap, RectF rectF) {
        Paint paint = new Paint();
        float f = this.mDragThumbRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rectF.left, rectF.top, paint);
    }

    private Bitmap getAnimationBitmap(View view) {
        Bitmap bitmapFromView = ViewUtils.getBitmapFromView(view);
        if (bitmapFromView != null && !bitmapFromView.isRecycled()) {
            if (bitmapFromView.getWidth() != bitmapFromView.getHeight()) {
                bitmapFromView = BitmapUtils.resizeAndCropCenter(bitmapFromView, this.mDragThumbSize);
            }
            if (bitmapFromView != null) {
                Bitmap createBitmap = BitmapUtils.createBitmap(bitmapFromView.getWidth(), bitmapFromView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                RectF rectF = new RectF(0.0f, 0.0f, (float) createBitmap.getWidth(), (float) createBitmap.getHeight());
                drawThumbnail(canvas, bitmapFromView, rectF);
                drawBorder(canvas, rectF);
                return createBitmap;
            }
        }
        return bitmapFromView;
    }

    private Paint getBorderPaint() {
        if (this.mBorderPaint == null) {
            this.mBorderPaint = this.mDragShadowInfo.getBorderPaint(this.mAnimViewsParent.getContext());
        }
        return this.mBorderPaint;
    }

    private ObjectAnimator[] getReturnAnimators() {
        ArrayList arrayList = new ArrayList();
        this.mDragViewSets.forEach(new d(1, arrayList));
        arrayList.add(ObjectAnimator.ofFloat(this.mPressedThumbnailView, "alpha", new float[]{0.7f, 1.0f}));
        return (ObjectAnimator[]) arrayList.toArray(new ObjectAnimator[0]);
    }

    private void initValues() {
        this.mPressedThumbnailView = ((ViewGroup) this.mPressedViewHolder.itemView).getChildAt(0);
        this.mIconHalfSize = this.mDragShadowInfo.getCountIconSize(this.mAnimViewsParent.getContext()) / 2;
        this.mDragThumbSize = this.mDragShadowInfo.getThumbSize(this.mAnimViewsParent.getContext(), this.mPressedViewHolder);
        this.mDragThumbRadius = this.mDragShadowInfo.getRadius(this.mAnimViewsParent.getContext());
        this.mIsRtl = this.mDragShadowInfo.isRtl();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createAnimViews$0(Integer num) {
        if (num.intValue() < this.mListView.findFirstVisibleItemPositionCompat() || num.intValue() > this.mListView.findLastVisibleItemPositionCompat()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createAnimViews$1(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null || viewHolder == this.mPressedViewHolder) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createAnimViews$2(AtomicInteger atomicInteger, RecyclerView.ViewHolder viewHolder) {
        createAndAddAnimView(viewHolder, atomicInteger.getAndIncrement());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGatherAnimators$3(float f, float f5, ArrayList arrayList, DragViewSet dragViewSet) {
        int i2;
        dragViewSet.setAnimViewAlphaForGatherAnim(this.mDragViewSets.size());
        float f8 = (float) this.mInsetLeft;
        int i7 = this.mDragThumbSize;
        if (this.mIsRtl) {
            i2 = -this.mIconHalfSize;
        } else {
            i2 = this.mIconHalfSize;
        }
        float f10 = (f - (((float) (i2 + i7)) / 2.0f)) + f8;
        float f11 = (f5 - (((float) (i7 - this.mIconHalfSize)) / 2.0f)) + ((float) this.mInsetTop);
        arrayList.add(ObjectAnimator.ofFloat(dragViewSet.mAnimView, "x", new float[]{(float) (dragViewSet.mViewHolderItemView.getLeft() + this.mDiffPositionFromRootView.x), f10}));
        arrayList.add(ObjectAnimator.ofFloat(dragViewSet.mAnimView, "y", new float[]{(float) (dragViewSet.mViewHolderItemView.getTop() + this.mDiffPositionFromRootView.y), f11}));
        if (this.mIsSplitMode.getAsBoolean()) {
            arrayList.add(ObjectAnimator.ofFloat(dragViewSet.mViewHolderThumbnailView, "alpha", new float[]{1.0f, 0.7f}));
        } else {
            dragViewSet.mViewHolderThumbnailView.setAlpha(0.7f);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getReturnAnimators$5(ArrayList arrayList, DragViewSet dragViewSet) {
        arrayList.add(ObjectAnimator.ofFloat(dragViewSet.mAnimView, "scaleX", new float[]{0.0f}));
        arrayList.add(ObjectAnimator.ofFloat(dragViewSet.mAnimView, "scaleY", new float[]{0.0f}));
        arrayList.add(ObjectAnimator.ofFloat(dragViewSet.mAnimView, "alpha", new float[]{0.7f, 0.0f}));
        arrayList.add(ObjectAnimator.ofFloat(dragViewSet.mViewHolderThumbnailView, "alpha", new float[]{0.7f, 1.0f}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startMoveAnim$4(int i2, int i7, DragViewSet dragViewSet) {
        int i8;
        int i10 = this.mInsetLeft + i2;
        int i11 = this.mDragThumbSize;
        if (this.mIsRtl) {
            i8 = -this.mIconHalfSize;
        } else {
            i8 = this.mIconHalfSize;
        }
        dragViewSet.startMoveAnim(i10 - ((i8 + i11) / 2), (i7 - ((i11 - this.mIconHalfSize) / 2)) + this.mInsetTop, this.mDragViewSets.size());
    }

    private View makeAnimView(ViewGroup viewGroup) {
        View childAt = viewGroup.getChildAt(0);
        ImageView imageView = new ImageView(viewGroup.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        int i2 = this.mDragThumbSize;
        layoutParams.width = i2;
        layoutParams.height = i2;
        imageView.setLayoutParams(layoutParams);
        Bitmap animationBitmap = getAnimationBitmap(childAt);
        if (animationBitmap != null) {
            this.mBitmapList.add(animationBitmap);
            imageView.setImageBitmap(animationBitmap);
        }
        return imageView;
    }

    private void recycleAnimationBitmaps() {
        if (!this.mBitmapList.isEmpty()) {
            Iterator<Bitmap> it = this.mBitmapList.iterator();
            while (it.hasNext()) {
                BitmapUtils.putBitmap(it.next());
            }
            this.mBitmapList.clear();
        }
    }

    private void runGatherAnim(ObjectAnimator[] objectAnimatorArr) {
        long j2;
        if (objectAnimatorArr.length == 0) {
            Log.e("DnDAnimation", "startDragAnim abort - The count of the selected view is 0");
            return;
        }
        this.mPressedThumbnailView.setAlpha(0.7f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mGatherAnimation = animatorSet;
        animatorSet.playTogether(objectAnimatorArr);
        AnimatorSet animatorSet2 = this.mGatherAnimation;
        if (this.mIsSplitMode.getAsBoolean()) {
            j2 = 250;
        } else {
            j2 = 150;
        }
        animatorSet2.setDuration(j2);
        this.mGatherAnimation.setInterpolator(new LinearInterpolator());
        this.mGatherAnimation.addListener(new AnimatorListenerAdapter() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onAnimationEnd$0(DragViewSet dragViewSet) {
                SplitDnDAnimation.this.mAnimViewsParent.removeView(dragViewSet.mAnimView);
                dragViewSet.mViewHolderThumbnailView.setAlpha(1.0f);
            }

            public void onAnimationEnd(Animator animator) {
                Log.d("DnDAnimation", "startDragAnim#end {" + SplitDnDAnimation.this.mIsDragging + "}");
                if (!SplitDnDAnimation.this.mIsDragging) {
                    SplitDnDAnimation.this.mDragViewSets.forEach(new d(2, this));
                    SplitDnDAnimation.this.mPressedThumbnailView.setAlpha(1.0f);
                    SplitDnDAnimation.this.mDragViewSets.clear();
                }
                if (SplitDnDAnimation.this.mGatherAnimation != null) {
                    SplitDnDAnimation.this.mGatherAnimation.removeAllListeners();
                }
                SplitDnDAnimation.this.mGatherAnimation = null;
            }
        });
        this.mGatherAnimation.start();
    }

    private void runReturnAnim() {
        boolean z;
        GalleryListView galleryListView = this.mListView;
        boolean z3 = true;
        if (galleryListView == null || this.mAnimViewsParent == null) {
            if (galleryListView == null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mAnimViewsParent != null) {
                z3 = false;
            }
            Log.e((CharSequence) "DnDAnimation", "runReturnAnim skip mListView", valueOf, Boolean.valueOf(z3));
            return;
        }
        Log.d("DnDAnimation", "runReturnAnim");
        AnimatorSet animatorSet = this.mGatherAnimation;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(getReturnAnimators());
        animatorSet2.setDuration(250);
        animatorSet2.setInterpolator(new LinearInterpolator());
        animatorSet2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SplitDnDAnimation.this.clear();
            }
        });
        try {
            animatorSet2.start();
            this.mIsStarted = true;
        } catch (Exception e) {
            Log.e("DnDAnimation", "runReturnAnim failed, e=" + e.getMessage());
            Iterator<Animator.AnimatorListener> it = animatorSet2.getListeners().iterator();
            while (it.hasNext()) {
                Animator.AnimatorListener next = it.next();
                if (next != null) {
                    next.onAnimationEnd(animatorSet2);
                }
            }
        }
    }

    private void startMoveAnim(int i2, int i7) {
        this.mDragViewSets.forEach(new a(this, i2, i7));
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.util.function.Consumer, java.lang.Object] */
    public void clear() {
        if (this.mIsStarted) {
            Log.d("DnDAnimation", "clear will handle in onAnimationEnd");
            this.mIsStarted = false;
            return;
        }
        Optional.ofNullable(this.mGatherAnimation).ifPresent(new e(10));
        this.mDragViewSets.forEach(new Object());
        Optional.ofNullable(this.mPressedThumbnailView).ifPresent(new e(11));
        ViewGroup viewGroup = this.mAnimViewsParent;
        if (viewGroup == null) {
            if (!this.mDragViewSets.isEmpty()) {
                viewGroup = (ViewGroup) this.mDragViewSets.get(0).mAnimView.getParent();
            } else {
                viewGroup = null;
            }
        }
        if (viewGroup != null) {
            this.mDragViewSets.forEach(new d(0, viewGroup));
        }
        this.mDragViewSets.clear();
        recycleAnimationBitmaps();
    }

    public void onDrop(boolean z) {
        if (z) {
            clear();
        } else {
            runReturnAnim();
        }
    }

    public void onMove(int i2, int i7) {
        AnimatorSet animatorSet = this.mGatherAnimation;
        if (animatorSet != null && animatorSet.isRunning()) {
            return;
        }
        if (this.mPrevX != i2 || this.mPrevY != i7) {
            this.mPrevX = i2;
            this.mPrevY = i7;
            startMoveAnim(i2, i7);
        }
    }

    public void onPrepare(ViewGroup viewGroup, GalleryListView galleryListView, ListViewHolder listViewHolder, BooleanSupplier booleanSupplier) {
        this.mAnimViewsParent = viewGroup;
        this.mListView = galleryListView;
        this.mPressedViewHolder = listViewHolder;
        this.mIsSplitMode = booleanSupplier;
        initValues();
        calcInsetSize();
        calcDiffPositionFromRootView();
    }

    public void onStarted(int i2, int i7) {
        this.mPrevX = i2;
        this.mPrevY = i7;
        createAnimViews();
        runGatherAnim((ObjectAnimator[]) createGatherAnimators((float) i2, (float) i7).toArray(new ObjectAnimator[0]));
    }

    public void setIsDragging(boolean z) {
        this.mIsDragging = z;
    }
}
