package com.samsung.android.gallery.app.ui.container.clipboard;

import A4.Q;
import B2.i;
import B8.e;
import D5.g;
import a8.d;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.HoverViewCompat;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import i4.C0469b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipboardViewAdapter extends RecyclerView.Adapter<ClipboardViewHolder> {
    private final Blackboard mBlackboard;
    private final LinkedHashMap<Long, MediaItem> mClipMap = new LinkedHashMap<>();
    private final HoverViewCompat mHoverPopupWindow = SeApiCompat.getHoverViewCompat();
    private View mPressedView;

    public ClipboardViewAdapter(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private Matrix getImageMatrix(ImageView imageView, MediaItem mediaItem) {
        int i2;
        Rect rect;
        RectF cropRectRatio = mediaItem.getCropRectRatio();
        Drawable drawable = imageView.getDrawable();
        if (mediaItem.isVideo() || mediaItem.isBroken()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        if (drawable == null || !isValidRect(cropRectRatio)) {
            rect = null;
        } else {
            rect = RectUtils.getSmartCropRect(drawable, cropRectRatio, i2);
        }
        return ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, false).withCropRect(rect).withOrientation(i2).withOrientationTag(mediaItem.getOrientationTag()));
    }

    private int getLayoutId() {
        if (PickerUtil.supportSearch()) {
            return R.layout.clipboard_list_item_v2;
        }
        return R.layout.clipboard_list_item;
    }

    private boolean isChangedViewSize(int i2, int i7, int i8, int i10) {
        if (i2 == i8 && i7 == i10) {
            return false;
        }
        return true;
    }

    private boolean isValidRect(RectF rectF) {
        if (rectF == null || rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBitmap$4(ImageView imageView, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2 = bitmap;
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.postOnUiThread(new d((Object) this, (Object) imageView, mediaItem2, (Object) bitmap2, 15));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setHoverListener$2(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            this.mHoverPopupWindow.showPopup(view);
            return true;
        } else if (action != 10) {
            return true;
        } else {
            this.mHoverPopupWindow.dismissPopup(view);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnLayoutChangeListener$5(ImageView imageView, MediaItem mediaItem, View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (isChangedViewSize(i13 - i11, i14 - i12, i8 - i2, i10 - i7)) {
            setViewMatrix(imageView, mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setOnTouchListener$1(View view, MotionEvent motionEvent) {
        if (!(view == null || view.getParent() == null)) {
            this.mPressedView = (ViewGroup) view.getParent();
            if (motionEvent.getAction() == 0) {
                setTouchEffectSwitch(true);
            } else if (motionEvent.getAction() == 1) {
                setTouchEffectSwitch(false);
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: onRemoveIconClicked */
    public void lambda$setItemLayoutMargin$0(View view, MediaItem mediaItem) {
        if (removeItems(List.of(mediaItem), false)) {
            Clipboard.getInstance(this.mBlackboard).remove(Long.valueOf(mediaItem.getFileId()));
            this.mBlackboard.postEvent(EventMessage.obtain(1023, mediaItem));
            SeApiCompat.announceVoiceAssistant(view.getContext(), view.getContext().getString(R.string.remove));
        }
    }

    private void setBitmap(ImageView imageView, MediaItem mediaItem) {
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        Bitmap memCache = instance.getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$setBitmap$3(imageView, mediaItem, memCache);
            return;
        }
        ThumbnailLoader instance2 = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance2.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new Q((Object) this, (Object) imageView, (Object) mediaItem, 19));
    }

    private void setHoverListener(View view) {
        view.setOnHoverListener(new g(1, this));
    }

    private void setImageView(ClipboardViewHolder clipboardViewHolder, int i2) {
        MediaItem item = getItem(i2);
        if (item != null) {
            ImageView thumbnailView = clipboardViewHolder.getThumbnailView();
            thumbnailView.setContentDescription(item.getTitle());
            clipboardViewHolder.setContentType(item);
            setBitmap(thumbnailView, item);
            setOnLayoutChangeListener(thumbnailView, item);
        }
    }

    private void setItemLayoutMargin(ClipboardViewHolder clipboardViewHolder, int i2) {
        int i7;
        MediaItem item = getItem(i2);
        Resources resources = clipboardViewHolder.getLayout().getResources();
        if (i2 == 0) {
            i7 = R.dimen.clipboard_list_item_first_start_margin;
        } else {
            i7 = R.dimen.clipboard_list_item_start_margin;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(i7);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) clipboardViewHolder.getLayout().getLayoutParams();
        layoutParams.setMarginStart(dimensionPixelSize);
        clipboardViewHolder.getLayout().setLayoutParams(layoutParams);
        clipboardViewHolder.getLayout().setOnClickListener(new Ba.g(21, this, item));
    }

    private void setOnLayoutChangeListener(ImageView imageView, MediaItem mediaItem) {
        imageView.addOnLayoutChangeListener(new C0469b(this, imageView, mediaItem));
    }

    private void setOnTouchListener(ClipboardViewHolder clipboardViewHolder) {
        clipboardViewHolder.getLayout().setOnTouchListener(new i(22, this));
    }

    private void setRemoveIconView(ClipboardViewHolder clipboardViewHolder) {
        setHoverListener(clipboardViewHolder.getRemoveIconView());
    }

    private void setSelectedViewPadding(int i2) {
        this.mPressedView.setPaddingRelative(i2, i2, i2, i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: setThumbnailView */
    public void lambda$setBitmap$3(ImageView imageView, MediaItem mediaItem, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        setViewMatrix(imageView, mediaItem);
    }

    private void setViewMatrix(ImageView imageView, MediaItem mediaItem) {
        imageView.setImageMatrix(getImageMatrix(imageView, mediaItem));
    }

    public boolean add(MediaItem mediaItem) {
        Object obj;
        if (mediaItem == null || this.mClipMap.containsKey(Long.valueOf(mediaItem.getFileId()))) {
            StringBuilder sb2 = new StringBuilder("add skip ");
            if (mediaItem == null) {
                obj = "null";
            } else {
                obj = Long.valueOf(mediaItem.getFileId());
            }
            sb2.append(obj);
            Log.e("ClipboardViewAdapter", sb2.toString());
            return false;
        }
        this.mClipMap.put(Long.valueOf(mediaItem.getFileId()), mediaItem);
        return true;
    }

    public void addAll(MediaItem[] mediaItemArr) {
        for (MediaItem mediaItem : mediaItemArr) {
            this.mClipMap.put(Long.valueOf(mediaItem.getFileId()), mediaItem);
        }
        StringBuilder sb2 = new StringBuilder("addAll");
        sb2.append(Logger.v(Integer.valueOf(getItemCount()), "+" + mediaItemArr.length));
        Log.d("ClipboardViewAdapter", sb2.toString());
    }

    public void clear() {
        Log.d("ClipboardViewAdapter", "clear {" + getItemCount() + "}");
        this.mClipMap.clear();
    }

    public MediaItem[] getAllItems() {
        int itemCount = getItemCount();
        MediaItem[] mediaItemArr = new MediaItem[itemCount];
        for (int i2 = 0; i2 < itemCount; i2++) {
            mediaItemArr[i2] = getItem(i2);
        }
        return mediaItemArr;
    }

    public MediaItem getItem(int i2) {
        return (MediaItem) new ArrayList(this.mClipMap.values()).get(i2);
    }

    public int getItemCount() {
        return this.mClipMap.size();
    }

    public int remove(MediaItem mediaItem) {
        Object obj;
        if (mediaItem == null || !this.mClipMap.containsKey(Long.valueOf(mediaItem.getFileId()))) {
            StringBuilder sb2 = new StringBuilder("remove skip ");
            if (mediaItem == null) {
                obj = "null";
            } else {
                obj = Long.valueOf(mediaItem.getFileId());
            }
            sb2.append(obj);
            Log.e("ClipboardViewAdapter", sb2.toString());
            return -1;
        }
        int indexOf = new ArrayList(this.mClipMap.keySet()).indexOf(Long.valueOf(mediaItem.getFileId()));
        this.mClipMap.remove(Long.valueOf(mediaItem.getFileId()));
        return indexOf;
    }

    public boolean removeItems(Collection<MediaItem> collection, boolean z) {
        Iterator<MediaItem> it = collection.iterator();
        int i2 = -1;
        int i7 = 0;
        while (true) {
            int i8 = 1;
            if (!it.hasNext()) {
                break;
            }
            i2 = remove(it.next());
            if (i2 < 0) {
                i8 = 0;
            }
            i7 += i8;
        }
        if (i7 > 0) {
            if (z || collection.size() != 1 || getItemCount() <= 0) {
                notifyDataSetChanged();
            } else {
                notifyItemRemoved(i2);
            }
        }
        Log.d("ClipboardViewAdapter", "removeItems=" + getItemCount(), Integer.valueOf(collection.size()), Integer.valueOf(i7));
        if (i7 > 0) {
            return true;
        }
        return false;
    }

    public void setTouchEffectSwitch(boolean z) {
        int i2;
        View view = this.mPressedView;
        if (view != null) {
            if (z) {
                i2 = (int) (((float) view.getWidth()) * 0.02f);
            } else {
                i2 = 0;
            }
            setSelectedViewPadding(i2);
        }
    }

    public void onBindViewHolder(ClipboardViewHolder clipboardViewHolder, int i2) {
        setImageView(clipboardViewHolder, i2);
        setRemoveIconView(clipboardViewHolder);
        setItemLayoutMargin(clipboardViewHolder, i2);
        setOnTouchListener(clipboardViewHolder);
    }

    public ClipboardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ClipboardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false));
    }

    public void addAll(Collection<MediaItem> collection) {
        for (MediaItem next : collection) {
            this.mClipMap.put(Long.valueOf(next.getFileId()), next);
        }
        StringBuilder sb2 = new StringBuilder("addAll");
        Integer valueOf = Integer.valueOf(getItemCount());
        sb2.append(Logger.v(valueOf, "+" + collection.size()));
        Log.d("ClipboardViewAdapter", sb2.toString());
    }
}
