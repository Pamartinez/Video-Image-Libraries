package com.samsung.android.gallery.widget.hoverview;

import V4.a;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;
import com.samsung.android.gallery.widget.hoverview.IHoverPreviewData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HoverPreviewListView<D extends IHoverPreviewData> extends RecyclerView implements HoverPreviewViewHolder.OnShareClickListener, HoverPreviewViewHolder.OnDeleteClickListener {
    private int mColumnCount;
    private HoverPreviewViewHolder.OnDeleteClickListener mDeleteClickListener;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 0 && HoverPreviewListView.this.mRemoveListener != null) {
                ((a) HoverPreviewListView.this.mRemoveListener).d.onRemoveHoverPreview();
            }
        }
    };
    private HoverPreviewListAdapter<D> mHoverPreviewListAdapter;
    private boolean mIsAvailableClick;
    private boolean mIsScaled;
    private OnItemClickListener mItemClickListener;
    private OnLoadCompleteListener mLoadCompleteListener;
    /* access modifiers changed from: private */
    public OnRemoveListener mRemoveListener;
    private int mRowCount;
    private HoverPreviewViewHolder.OnShareClickListener mShareClickListener;
    private float mTouchDownX;
    private float mTouchDownY;
    private int mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemClickListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemTouchListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnLoadCompleteListener {
        void onLoadCompleted();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnRemoveListener {
    }

    public HoverPreviewListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void endScale(View view) {
        view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
    }

    private void init(D d, boolean z, boolean z3, boolean z7, int i2, int i7) {
        int min = Math.min(d.getCount(), 7);
        this.mRowCount = new int[]{0, 1, 1, 2, 2, 2, 2, 3}[min];
        this.mColumnCount = new int[]{0, 1, 2, 2, 2, 3, 3, 3}[min];
        setLayoutManager(new GridLayoutManager(getContext(), this.mColumnCount));
        setHoverPreviewListAdapter(d, z, z3, z7, i2, i7);
        setAdapter(this.mHoverPreviewListAdapter);
        this.mHoverPreviewListAdapter.setOnLoadCompleteListener(new Eb.a(this));
        this.mHoverPreviewListAdapter.setOnItemTouchListener(new Eb.a(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), R$anim.hover_preview_fade_in));
        OnLoadCompleteListener onLoadCompleteListener = this.mLoadCompleteListener;
        if (onLoadCompleteListener != null) {
            onLoadCompleteListener.onLoadCompleted();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$init$1(View view, MotionEvent motionEvent, int i2, MediaItem mediaItem) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                Log.d("HoverPreviewListView", "onItemTouch UP mIsAvailableClick : " + this.mIsAvailableClick);
                if (this.mIsScaled) {
                    endScale(view);
                    this.mIsScaled = false;
                }
                if (this.mIsAvailableClick) {
                    ((a) this.mItemClickListener).a(view.getContext(), i2, mediaItem, this.mIsAvailableClick, false);
                    this.mIsAvailableClick = false;
                    return true;
                }
            } else if (action != 2) {
                if (action == 3) {
                    if (this.mIsScaled) {
                        endScale(view);
                        this.mIsScaled = false;
                    }
                    this.mIsAvailableClick = false;
                    return false;
                }
            } else if (!this.mIsAvailableClick && this.mIsScaled) {
                endScale(view);
                this.mIsScaled = false;
                return false;
            }
            return false;
        }
        if (this.mHandler.hasMessages(0)) {
            this.mHandler.removeMessages(0);
        }
        startScale(view);
        this.mIsScaled = true;
        this.mIsAvailableClick = true;
        return false;
    }

    private void setHoverPreviewListAdapter(D d, boolean z, boolean z3, boolean z7, int i2, int i7) {
        HoverPreviewListAdapter<D> hoverPreviewListAdapter = new HoverPreviewListAdapter<>();
        this.mHoverPreviewListAdapter = hoverPreviewListAdapter;
        hoverPreviewListAdapter.setIsAlbum(z);
        this.mHoverPreviewListAdapter.setIsSuggestion(z3);
        this.mHoverPreviewListAdapter.setHideOption(z7);
        this.mHoverPreviewListAdapter.setSpanSize(this.mRowCount, this.mColumnCount);
        this.mHoverPreviewListAdapter.setListViewSize(getContext(), i2, (i7 - getPaddingTop()) - getPaddingBottom());
        this.mHoverPreviewListAdapter.setData(d);
    }

    private void startScale(View view) {
        view.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start();
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 9) {
            if (action == 10 && this.mRemoveListener != null) {
                Handler handler = this.mHandler;
                handler.sendMessageDelayed(handler.obtainMessage(0), 200);
            }
        } else if (this.mHandler.hasMessages(0)) {
            this.mHandler.removeMessages(0);
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.mHandler.hasMessages(0)) {
                this.mHandler.removeMessages(0);
            }
            this.mTouchDownX = motionEvent.getX();
            this.mTouchDownY = motionEvent.getY();
        } else if (action == 1) {
            float x9 = motionEvent.getX();
            float y = motionEvent.getY();
            if (x9 < 0.0f || x9 > ((float) getWidth()) || y < 0.0f || y > ((float) getHeight())) {
                Log.d("HoverPreviewListView", "dispatchTouchEvent - touch is out of range!");
                this.mHandler.sendEmptyMessage(0);
                return true;
            }
            if (this.mHandler.hasMessages(0)) {
                this.mHandler.removeMessages(0);
            }
            if (((double) Math.abs(motionEvent.getX() - this.mTouchDownX)) > ((double) (this.mTouchSlop * 3))) {
                if (this.mTouchDownX > x9) {
                    z = true;
                } else {
                    z = false;
                }
                ((a) this.mItemClickListener).a(getContext(), -1, (MediaItem) null, false, z);
            } else {
                Log.d("HoverPreviewListView", "dispatchTouchEvent - skip moving to next page");
            }
        } else if (action == 2) {
            if (Math.sqrt(Math.pow((double) (motionEvent.getY() - this.mTouchDownY), 2.0d) + Math.pow((double) (motionEvent.getX() - this.mTouchDownX), 2.0d)) > ((double) this.mTouchSlop)) {
                this.mIsAvailableClick = false;
            }
        } else if (action == 3) {
            Log.d("HoverPreviewListView", "dispatchTouchEvent ACTION_CANCEL");
            this.mHandler.sendEmptyMessage(0);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onDeleteClick(int i2) {
        HoverPreviewViewHolder.OnDeleteClickListener onDeleteClickListener = this.mDeleteClickListener;
        if (onDeleteClickListener != null) {
            onDeleteClickListener.onDeleteClick(i2);
        }
    }

    public void onShareClick(int i2) {
        HoverPreviewViewHolder.OnShareClickListener onShareClickListener = this.mShareClickListener;
        if (onShareClickListener != null) {
            onShareClickListener.onShareClick(i2);
        }
    }

    public void recycle() {
        this.mHoverPreviewListAdapter.recycle();
        setAdapter((RecyclerView.Adapter) null);
    }

    public void setDataAndSize(D d, boolean z, boolean z3, boolean z7, int i2, int i7) {
        init(d, z, z3, z7, i2, i7);
    }

    public void setOnDeleteClickListener(HoverPreviewViewHolder.OnDeleteClickListener onDeleteClickListener) {
        this.mHoverPreviewListAdapter.setOnDeleteClickListener(onDeleteClickListener);
        this.mDeleteClickListener = onDeleteClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnLoadCompleteListener(OnLoadCompleteListener onLoadCompleteListener) {
        this.mLoadCompleteListener = onLoadCompleteListener;
    }

    public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.mRemoveListener = onRemoveListener;
    }

    public void setOnShareClickListener(HoverPreviewViewHolder.OnShareClickListener onShareClickListener) {
        this.mHoverPreviewListAdapter.setOnShareClickListener(onShareClickListener);
        this.mShareClickListener = onShareClickListener;
    }

    public void updateData(D d, int i2, int i7) {
        this.mHoverPreviewListAdapter.setData(d);
        setItemAnimator((RecyclerView.ItemAnimator) null);
        int childCount = getChildCount();
        if (i7 < childCount) {
            removeViews(i2 + i7, childCount - i7);
        }
        this.mHoverPreviewListAdapter.notifyItemRangeChanged(i2, i7);
    }
}
