package com.samsung.android.gallery.plugins.motionphoto;

import A2.d;
import A4.C0367b;
import A4.M;
import A4.O;
import A9.b;
import Ab.a;
import B2.i;
import B4.c;
import Ba.q;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.R$dimen;
import com.samsung.android.gallery.plugins.R$drawable;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.support.observable.ObservableArrayList;
import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.utils.SystemUi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoCtrlView extends LinearLayout {
    private int mBitmapCount;
    private final ViewGroup mContainer;
    private Bitmap mCropBitmap;
    private boolean mHasTouch;
    private final RecyclerView mListView;
    private MediaItem mMediaItem;
    private final ImageView mPlayButton;
    private View.OnClickListener mPlayButtonListener;
    private final View mSeekHandler;
    private ProgressAnimator mSeekHandlerAnimator;
    private int mSeekMax;
    private int mSeekMin;
    private int mSeekPosition;
    private float mSeekProgress;
    private int mSeekRange;
    private BiConsumer<SeekState, Integer> mSeekStateListener;
    private final View mSeekbar;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SeekState {
        SEEK_BEGIN,
        SEEK_END,
        SEEK_PROGRESS
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SeekbarAdapter extends RecyclerView.Adapter<SeekbarItemHolder> {
        List<Bitmap> list = new ArrayList();

        public int getItemCount() {
            return this.list.size();
        }

        public void updateData(List<Bitmap> list2) {
            this.list = list2;
            ThreadUtil.runOnUiThread(new c(this));
        }

        public void onBindViewHolder(SeekbarItemHolder seekbarItemHolder, int i2) {
            seekbarItemHolder.onBind(this.list.get(i2));
        }

        public SeekbarItemHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new SeekbarItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.seekbar_item, viewGroup, false));
        }

        public void onViewAttachedToWindow(SeekbarItemHolder seekbarItemHolder) {
            super.onViewAttachedToWindow(seekbarItemHolder);
            ViewParent parent = seekbarItemHolder.itemView.getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup.LayoutParams layoutParams = seekbarItemHolder.itemView.getLayoutParams();
                layoutParams.width = ((View) parent).getWidth() / this.list.size();
                layoutParams.height = -1;
                seekbarItemHolder.itemView.setLayoutParams(layoutParams);
            }
        }

        public void onViewRecycled(SeekbarItemHolder seekbarItemHolder) {
            seekbarItemHolder.onRecycle();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SeekbarItemHolder extends RecyclerView.ViewHolder {
        final ImageView image;

        public SeekbarItemHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R$id.image);
        }

        public void onBind(Bitmap bitmap) {
            this.image.setImageBitmap(bitmap);
        }

        public void onRecycle() {
            this.image.setImageDrawable((Drawable) null);
        }
    }

    public VideoCtrlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    private void adjustViewMargin(ViewGroup viewGroup) {
        this.mBitmapCount = 7;
        int computeMargin = computeMargin(viewGroup.getContext());
        Log.d("VideoCtrlView", "adjustViewMargin", Integer.valueOf(computeMargin), Integer.valueOf(this.mBitmapCount));
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
        int i2 = computeMargin / 2;
        marginLayoutParams.setMarginStart(i2);
        marginLayoutParams.setMarginEnd(i2);
        viewGroup.setLayoutParams(marginLayoutParams);
    }

    private int computeMargin(Context context) {
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.video_ctrl_thumbnail_size);
        try {
            if (SystemUi.isInMultiWindowMode((Activity) context)) {
                return dimensionPixelOffset / 2;
            }
            if (context.getResources().getConfiguration().orientation == 2) {
                return dimensionPixelOffset * 6;
            }
            return dimensionPixelOffset;
        } catch (Exception e) {
            e.printStackTrace();
            return dimensionPixelOffset;
        }
    }

    private Bitmap getDefaultCropBitmap(MediaItem mediaItem, Bitmap bitmap) {
        if (this.mCropBitmap == null && bitmap != null) {
            this.mCropBitmap = VideoStripBuilder.getCropBitmap(bitmap, mediaItem, 224);
        }
        return this.mCropBitmap;
    }

    private int getPosition(float f) {
        return (int) ((f * ((float) this.mSeekRange)) / 100.0f);
    }

    private int getProgress(int i2) {
        return (int) ((((float) i2) * 100.0f) / ((float) this.mSeekRange));
    }

    /* access modifiers changed from: private */
    public void initSeekParams() {
        this.mSeekMin = this.mSeekbar.getResources().getDimensionPixelOffset(R$dimen.video_ctrl_seekbar_gap);
        int width = this.mSeekbar.getWidth() - this.mSeekbar.getResources().getDimensionPixelOffset(R$dimen.video_ctrl_seekbar_handler_size);
        this.mSeekMax = width;
        int i2 = this.mSeekMin;
        this.mSeekRange = width - i2;
        Log.d("VideoCtrlView", "initSeekParams", Integer.valueOf(i2), Integer.valueOf(this.mSeekMax), Integer.valueOf(this.mSeekRange));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$5(int i2, int i7, ObservableList observableList) {
        int i8 = i2;
        while (i8 < i2 + i7) {
            try {
                SeekbarItemHolder seekbarItemHolder = (SeekbarItemHolder) this.mListView.findViewHolderForAdapterPosition(i8);
                if (seekbarItemHolder != null) {
                    seekbarItemHolder.image.setImageBitmap((Bitmap) observableList.get(i8));
                }
                i8++;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$6(ObservableList observableList, int i2, int i7) {
        Log.d("VideoCtrlView", "onListUpdate", Integer.valueOf(i2), Integer.valueOf(i7));
        if (i7 > 0 && i2 >= 0 && i2 < observableList.size()) {
            ThreadUtil.postOnUiThread(new M((Object) this, i2, i7, (Object) observableList, 1));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$7(MediaItem mediaItem, Bitmap bitmap) {
        TimeTickLog timeTickLog = new TimeTickLog();
        int i2 = this.mBitmapCount;
        Bitmap[] bitmapArr = new Bitmap[i2];
        Arrays.fill(bitmapArr, getDefaultCropBitmap(mediaItem, bitmap));
        ObservableArrayList observableArrayList = new ObservableArrayList(bitmapArr);
        Optional.ofNullable((SeekbarAdapter) this.mListView.getAdapter()).ifPresent(new b(observableArrayList));
        observableArrayList.setOnListChangedCallback(new O(14, this));
        timeTickLog.tick();
        new VideoStripBuilder(mediaItem, observableArrayList).setAdaptive(true).build();
        timeTickLog.tick();
        Log.d("VideoCtrlView", "load {" + i2 + "} +" + timeTickLog.summary());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        Log.d("VideoCtrlView", "onClick#PlayButton");
        View.OnClickListener onClickListener = this.mPlayButtonListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$1() {
        initSeekParams();
        setProgress(this.mSeekProgress);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlayButton$3(boolean z) {
        int i2;
        ImageView imageView = this.mPlayButton;
        if (z) {
            i2 = R$drawable.gallery_ic_detail_pause;
        } else {
            i2 = R$drawable.gallery_ic_detail_play;
        }
        imageView.setImageResource(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSeekHandler$2(ViewGroup.MarginLayoutParams marginLayoutParams) {
        this.mSeekHandler.setLayoutParams(marginLayoutParams);
    }

    private void onSeekBegin() {
        initSeekParams();
        int progress = getProgress(this.mSeekPosition);
        this.mSeekProgress = (float) progress;
        Log.d("VideoCtrlView", "onSeekBegin", Integer.valueOf(progress), Integer.valueOf(this.mSeekPosition));
        BiConsumer<SeekState, Integer> biConsumer = this.mSeekStateListener;
        if (biConsumer != null) {
            biConsumer.accept(SeekState.SEEK_BEGIN, Integer.valueOf(progress));
        }
    }

    private void onSeekEnd() {
        int progress = getProgress(this.mSeekPosition);
        Log.d("VideoCtrlView", "onSeekEnd", Integer.valueOf(progress), Integer.valueOf(this.mSeekPosition));
        BiConsumer<SeekState, Integer> biConsumer = this.mSeekStateListener;
        if (biConsumer != null) {
            biConsumer.accept(SeekState.SEEK_END, Integer.valueOf(progress));
        }
    }

    /* access modifiers changed from: private */
    public boolean onSeekTouchEvent(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 2) {
                if (!(action == 5 || action == 211)) {
                    if (action != 213) {
                        if (this.mHasTouch) {
                            this.mHasTouch = false;
                            onSeekProgress(Math.max(this.mSeekMin, Math.min((int) (motionEvent.getX() - view.getX()), this.mSeekMax)) - this.mSeekMin);
                            onSeekEnd();
                        }
                        return false;
                    }
                }
            }
            if (this.mHasTouch) {
                onSeekProgress(Math.max(this.mSeekMin, Math.min((int) (motionEvent.getX() - view.getX()), this.mSeekMax)) - this.mSeekMin);
            }
            return false;
        }
        if (!this.mHasTouch) {
            this.mHasTouch = true;
            onSeekBegin();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void updateSeekHandler(float f) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mSeekHandler.getLayoutParams();
        int i2 = this.mSeekMin + ((int) (f * ((float) this.mSeekRange)));
        if (marginLayoutParams.getMarginStart() != i2) {
            marginLayoutParams.setMarginStart(i2);
            this.mSeekHandler.postOnAnimation(new d(20, this, marginLayoutParams));
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    public void initAni(int i2) {
        if (this.mSeekHandlerAnimator == null) {
            ProgressAnimator progressAnimator = new ProgressAnimator(this.mSeekHandler, (long) i2, new C0367b(12, this));
            this.mSeekHandlerAnimator = progressAnimator;
            progressAnimator.start();
        }
    }

    public void load(MediaItem mediaItem, Bitmap bitmap) {
        this.mMediaItem = mediaItem;
        ThreadUtil.postOnBgThread(new b(this, mediaItem, bitmap, 3));
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d("VideoCtrlView", "onConfigurationChanged", Integer.valueOf(configuration.orientation));
        adjustViewMargin(this.mContainer);
        post(new q(this, 0));
        load(this.mMediaItem, (Bitmap) null);
    }

    public void onSeekProgress(int i2) {
        int progress = getProgress(this.mSeekPosition);
        int progress2 = getProgress(i2);
        this.mSeekPosition = i2;
        if (progress != progress2) {
            float f = (float) progress2;
            this.mSeekProgress = f;
            ProgressAnimator progressAnimator = this.mSeekHandlerAnimator;
            if (progressAnimator != null) {
                progressAnimator.seekTo(f);
            }
            BiConsumer<SeekState, Integer> biConsumer = this.mSeekStateListener;
            if (biConsumer != null) {
                biConsumer.accept(SeekState.SEEK_PROGRESS, Integer.valueOf(progress2));
            }
        }
    }

    public void pauseAni() {
        ProgressAnimator progressAnimator = this.mSeekHandlerAnimator;
        if (progressAnimator != null) {
            progressAnimator.pause();
        }
    }

    public void resumeAni() {
        ProgressAnimator progressAnimator = this.mSeekHandlerAnimator;
        if (progressAnimator != null) {
            progressAnimator.resume();
        }
    }

    public void setPlayButton(boolean z) {
        ThreadUtil.runOnUiThread(this.mPlayButton, new c((Object) this, z, 3));
    }

    public void setPlayClickListener(View.OnClickListener onClickListener) {
        this.mPlayButtonListener = onClickListener;
    }

    public void setProgress(float f) {
        if (this.mSeekPosition != getPosition(f)) {
            this.mSeekProgress = f;
            this.mSeekPosition = getPosition(f);
            ProgressAnimator progressAnimator = this.mSeekHandlerAnimator;
            if (progressAnimator != null) {
                progressAnimator.setProgress(f);
            }
        }
    }

    public void setSeekStateListener(BiConsumer<SeekState, Integer> biConsumer) {
        this.mSeekStateListener = biConsumer;
    }

    public VideoCtrlView(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mSeekMin = 100;
        this.mSeekMax = 200;
        this.mSeekRange = 100;
        View inflate = LayoutInflater.from(context).inflate(R$layout.video_controller, this, true);
        ImageView imageView = (ImageView) inflate.findViewById(R$id.video_play_button);
        this.mPlayButton = imageView;
        imageView.setOnClickListener(new a(8, this));
        this.mSeekHandler = inflate.findViewById(R$id.video_seek_handler);
        View findViewById = inflate.findViewById(R$id.video_seekbar);
        this.mSeekbar = findViewById;
        findViewById.setOnTouchListener(new i(1, this));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R$id.video_strip);
        this.mListView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        recyclerView.setAdapter(new SeekbarAdapter());
        ViewGroup viewGroup = (ViewGroup) inflate.findViewById(R$id.video_ctrl_container);
        this.mContainer = viewGroup;
        adjustViewMargin(viewGroup);
        post(new q(this, 1));
    }
}
