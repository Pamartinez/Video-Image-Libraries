package com.samsung.android.gallery.app.remote.v2;

import G6.a;
import U9.b;
import a8.d;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import b4.C0424d;
import com.samsung.android.gallery.app.remote.type.ControllerButtonType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PresentationViewPager extends RelativeLayout implements IPresentationView {
    /* access modifiers changed from: private */
    public PresentationViewPagerAdapter mAdapter;
    private ControlViewClickCallback mClickCallback;
    private ImageView mCloseIcon;
    private int mCurrentPosition = -1;
    private TextView mDateTimeView;
    private int mDirection;
    /* access modifiers changed from: private */
    public int mFakeStartPosition = -1;
    private TextView mLocationView;
    private ImageView mNextIcon;
    ViewPager2.OnPageChangeCallback mOnPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        private float adjustRtlPosition(float f) {
            if (Features.isEnabled(Features.IS_RTL)) {
                return -f;
            }
            return f;
        }

        private float getDragBy(int i2, float f) {
            int width = PresentationViewPager.this.mViewPager.getWidth();
            return -(((f - ((Float) PresentationViewPager.this.mProgress.get()).floatValue()) * ((float) PresentationViewPager.this.mViewPager.getWidth())) + ((float) ((i2 - PresentationViewPager.this.mFakeStartPosition) * width)));
        }

        public void onPageScrollStateChanged(int i2) {
            super.onPageScrollStateChanged(i2);
            if (i2 == 1 && !PresentationViewPager.this.mViewPager.isFakeDragging()) {
                PresentationViewPager.this.mViewPager.beginFakeDrag();
                PresentationViewPager presentationViewPager = PresentationViewPager.this;
                presentationViewPager.mFakeStartPosition = presentationViewPager.mViewPager.getCurrentItem();
                PresentationViewPager.this.mProgress.set(Float.valueOf(0.0f));
            }
        }

        public void onPageScrolled(int i2, float f, int i7) {
            super.onPageScrolled(i2, f, i7);
            if (PresentationViewPager.this.mViewPager.isFakeDragging()) {
                PresentationViewPager.this.mViewPager.fakeDragBy(adjustRtlPosition(getDragBy(i2, f)));
                PresentationViewPager.this.mFakeStartPosition = i2;
                PresentationViewPager.this.mProgress.set(Float.valueOf(f));
            }
        }

        public void onPageSelected(int i2) {
            if (PresentationViewPager.this.mViewPager.getAdapter() == null) {
                PresentationViewPager presentationViewPager = PresentationViewPager.this;
                presentationViewPager.setAdapterInternal(presentationViewPager.mAdapter, i2);
            }
        }
    };
    private final boolean mOsdEnable;
    private Consumer<MediaItem> mPlayConsumeAfterPhotoShow;
    private ImageView mPrevIcon;
    /* access modifiers changed from: private */
    public final AtomicReference<Float> mProgress = new AtomicReference<>(Float.valueOf(0.0f));
    ViewPager2 mViewPager;
    private boolean mVisible;

    public PresentationViewPager(Context context, PresentationViewPagerAdapter presentationViewPagerAdapter, boolean z) {
        super(context);
        initView();
        this.mAdapter = presentationViewPagerAdapter;
        this.mOsdEnable = z;
    }

    private PresentationViewPagerHolder getViewHolder(int i2) {
        RecyclerView seslGetListView = this.mViewPager.seslGetListView();
        if (seslGetListView != null) {
            return (PresentationViewPagerHolder) seslGetListView.findViewHolderForAdapterPosition(i2);
        }
        return null;
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.mirroring_presentation_v2_view_pager, this, true);
        ViewPager2 viewPager2 = (ViewPager2) inflate.findViewById(R.id.mirroring_view_pager);
        this.mViewPager = viewPager2;
        viewPager2.setOffscreenPageLimit(1);
        this.mViewPager.setPageTransformer(new MarginPageTransformer(getResources().getDimensionPixelSize(R.dimen.presentation_viewpager_interval_margin)));
        this.mViewPager.setUserInputEnabled(false);
        this.mCloseIcon = (ImageView) inflate.findViewById(R.id.mirror_close_icon);
        this.mPrevIcon = (ImageView) inflate.findViewById(R.id.mirror_prev_icon);
        this.mNextIcon = (ImageView) inflate.findViewById(R.id.mirror_next_icon);
        this.mDateTimeView = (TextView) inflate.findViewById(R.id.presentation_date_time);
        this.mLocationView = (TextView) inflate.findViewById(R.id.presentation_location);
        this.mPrevIcon.setOnClickListener(new C0424d(this, 0));
        this.mNextIcon.setOnClickListener(new C0424d(this, 1));
        this.mCloseIcon.setOnClickListener(new C0424d(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        onPresentationControllerClicked(ControllerButtonType.PREV);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$1(View view) {
        onPresentationControllerClicked(ControllerButtonType.NEXT);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$2(View view) {
        onPresentationControllerClicked(ControllerButtonType.CLOSE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateViews$3(MediaItem mediaItem, TextView textView) {
        textView.setText(new SimpleDateFormat("yyyy-MM-dd EEEE", Locale.getDefault()).format(Long.valueOf(mediaItem.getDateTaken())));
        textView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateViews$4(MediaItem mediaItem, TextView textView) {
        textView.setText(mediaItem.getLocation());
        textView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViews$5(View view) {
        onPresentationControllerClicked(ControllerButtonType.PLAY);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViews$6(ImageView imageView) {
        imageView.setOnClickListener(new C0424d(this, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViews$7(PhotoViewMotionControl photoViewMotionControl, MediaItem mediaItem, Bitmap bitmap) {
        PresentationViewPagerHolder viewHolder = getViewHolder(this.mCurrentPosition);
        if (viewHolder != null) {
            if (photoViewMotionControl != null) {
                viewHolder.setMotionControl(photoViewMotionControl);
            }
            viewHolder.updateImage(mediaItem, bitmap);
            if (PreferenceFeatures.Remote.SHOW_INFO_IN_PRESENTATION) {
                Optional.ofNullable(this.mDateTimeView).ifPresent(new a(mediaItem, 6));
                Optional.ofNullable(this.mLocationView).ifPresent(new a(mediaItem, 7));
            }
            Optional.ofNullable(viewHolder.getPlayView()).ifPresent(new b(26, this));
        }
    }

    private void onPresentationControllerClicked(ControllerButtonType controllerButtonType) {
        ControlViewClickCallback controlViewClickCallback = this.mClickCallback;
        if (controlViewClickCallback != null) {
            controlViewClickCallback.onClick(controllerButtonType);
        }
    }

    /* access modifiers changed from: private */
    public void setAdapterInternal(PresentationViewPagerAdapter presentationViewPagerAdapter, int i2) {
        this.mViewPager.setAdapter(presentationViewPagerAdapter);
        this.mViewPager.setCurrentItem(i2, false);
    }

    private void setVisibility(boolean z) {
        int i2;
        int i7;
        int i8;
        int i10 = 4;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        this.mVisible = z;
        ImageView imageView = this.mCloseIcon;
        if (this.mOsdEnable) {
            i7 = i2;
        } else {
            i7 = 4;
        }
        ViewUtils.setVisibility(imageView, i7);
        ImageView imageView2 = this.mNextIcon;
        if (this.mOsdEnable) {
            i8 = i2;
        } else {
            i8 = 4;
        }
        ViewUtils.setVisibility(imageView2, i8);
        ImageView imageView3 = this.mPrevIcon;
        if (this.mOsdEnable) {
            i10 = i2;
        }
        ViewUtils.setVisibility(imageView3, i10);
    }

    public void clearViews() {
        this.mViewPager.setAdapter((RecyclerView.Adapter) null);
    }

    public View getMediaView(int i2) {
        if (i2 < 0) {
            i2 = this.mCurrentPosition;
        }
        PresentationViewPagerHolder viewHolder = getViewHolder(i2);
        if (viewHolder != null) {
            return viewHolder.getMediaView();
        }
        return null;
    }

    public ViewPager2.OnPageChangeCallback getViewPagerCallback() {
        return this.mOnPageChangeCallback;
    }

    public boolean handleTouchController(MotionEvent motionEvent) {
        ImageView playView;
        PresentationViewPagerHolder viewHolder = getViewHolder(this.mCurrentPosition);
        if (!(viewHolder == null || (playView = viewHolder.getPlayView()) == null || motionEvent.getAction() != 1)) {
            int y = (int) motionEvent.getY();
            float x9 = (float) ((int) motionEvent.getX());
            if (x9 >= playView.getX()) {
                float f = (float) y;
                if (f >= playView.getY() && x9 <= playView.getX() + ((float) playView.getWidth()) && f <= playView.getY() + ((float) playView.getHeight())) {
                    return playView.performClick();
                }
            }
        }
        return false;
    }

    public void hideMediaView() {
        PresentationViewPagerHolder viewHolder = getViewHolder(this.mCurrentPosition);
        if (viewHolder != null) {
            viewHolder.hideMediaView();
        }
    }

    public void onAnimationFrameUpdated(Bitmap bitmap) {
        PresentationViewPagerHolder viewHolder = getViewHolder(this.mCurrentPosition);
        if (viewHolder != null) {
            viewHolder.updateImage(bitmap);
        }
    }

    public void onDataChanged() {
        if (this.mViewPager.isFakeDragging()) {
            this.mViewPager.endFakeDrag();
        }
        ((PresentationViewPagerAdapter) this.mViewPager.getAdapter()).clearCache();
    }

    public void pendingRun(Runnable runnable) {
        RecyclerView seslGetListView = this.mViewPager.seslGetListView();
        if (seslGetListView != null) {
            seslGetListView.post(runnable);
        } else {
            post(runnable);
        }
    }

    public void setControlViewClickListener(ControlViewClickCallback controlViewClickCallback) {
        this.mClickCallback = controlViewClickCallback;
    }

    public void showMediaView() {
        PresentationViewPagerHolder viewHolder = getViewHolder(this.mCurrentPosition);
        if (viewHolder != null) {
            viewHolder.showMediaView();
        }
    }

    public void startAdapterWithPosition(int i2) {
        if (this.mViewPager.getAdapter() == null) {
            setAdapterInternal(this.mAdapter, i2);
        }
    }

    public void toggleControlView() {
        setVisibility(!this.mVisible);
    }

    public void updateAnimationView(int i2, Consumer<MediaItem> consumer, MediaItem mediaItem, int i7) {
        this.mDirection = i2;
        this.mCurrentPosition = i7;
        this.mPlayConsumeAfterPhotoShow = consumer;
    }

    public void updateViews(MediaItem mediaItem, Bitmap bitmap, PhotoViewMotionControl photoViewMotionControl, int i2) {
        boolean z;
        RecyclerView seslGetListView = this.mViewPager.seslGetListView();
        int currentItem = this.mViewPager.getCurrentItem();
        PresentationViewPagerHolder viewHolder = getViewHolder(currentItem);
        if (viewHolder != null) {
            viewHolder.hideMediaView();
        }
        int i7 = this.mCurrentPosition;
        if (i2 == i7) {
            if (currentItem != i7) {
                if (this.mViewPager.isFakeDragging()) {
                    this.mViewPager.endFakeDrag();
                }
                boolean z3 = false;
                if (currentItem < 0 || Math.abs(this.mCurrentPosition - currentItem) >= 2) {
                    z = false;
                } else {
                    z = true;
                }
                ViewPager2 viewPager2 = this.mViewPager;
                int i8 = this.mCurrentPosition;
                if (this.mDirection != 0 && z) {
                    z3 = true;
                }
                viewPager2.setCurrentItem(i8, z3);
            }
            MediaItem mediaItem2 = mediaItem;
            seslGetListView.post(new d((Object) this, (Object) photoViewMotionControl, mediaItem2, (Object) bitmap, 1));
            Consumer<MediaItem> consumer = this.mPlayConsumeAfterPhotoShow;
            if (consumer != null) {
                consumer.accept(mediaItem2);
                this.mPlayConsumeAfterPhotoShow = null;
            }
        }
    }
}
