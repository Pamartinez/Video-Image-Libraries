package com.samsung.android.gallery.app.ui.viewholders;

import Fb.e;
import N2.j;
import a8.a;
import a8.b;
import a8.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageViewHolder extends CheckboxListViewHolder implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener, View.OnLayoutChangeListener, View.OnHoverListener {
    private static final int[] DURATION_FORMAT = {R.string.details_ms, R.string.details_hms};
    static final boolean SHOW_THUMBNAIL_RAW_ICON = PreferenceFeatures.OneUi6x.IS_ONE_UI_60;
    private static Integer sMinWidthShowDuration;
    protected final View.AccessibilityDelegate mAccessibilityDelegate;
    private final View.AccessibilityDelegate mAccessibilityDelegateCommon;
    protected int mBgColor;
    protected Bitmap mBitmap;
    protected Bitmap mBitmapUnMutable;
    private final Runnable mCallback;
    private boolean mClickEnabled;
    protected TextView mContentDuration;
    protected LinearLayout mContentTypeContainer;
    protected ImageButton mContentTypeIcon;
    protected TextView mContentTypeTextIcon;
    protected ViewStub mContentTypeViewStub;
    protected ViewGroup mDecoViewLayout;
    private float mDownX;
    private float mDownY;
    protected View mExpandView;
    protected ViewStub mExpandViewStub;
    protected Drawable mForegroundDrawable;
    private boolean mHorizontalDrag;
    /* access modifiers changed from: protected */
    public ImageView mImageView;
    private boolean mIsSelectable;
    private boolean mSecondaryButtonClicked;
    protected int mSelectedTint;
    private boolean mShowDeco;
    private volatile int mSimilarViewPosition;
    protected ImageView mStorageFavoriteSlot1;
    protected ViewStub mStorageFavoriteSlot1ViewStub;
    protected ImageView mStorageFavoriteSlot2;
    protected ViewStub mStorageFavoriteSlot2ViewStub;
    protected ThumbKind mThumbKind;
    protected FrameLayout mThumbnailPreviewLayout;
    protected float mThumbnailShapeRadius;
    protected int mThumbnailShapeType;
    private int mViewId;
    protected ImageView mVisualCueIconView;
    protected ViewStub mVisualCueViewStub;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TINT_COLOR_FILTER {
        static final ConcurrentHashMap<Integer, ColorFilter> map = new ConcurrentHashMap<>();

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, java.util.function.Function] */
        public static ColorFilter get(int i2) {
            return map.computeIfAbsent(Integer.valueOf(i2), new Object());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ColorFilter lambda$get$1(Integer num) {
            return new PorterDuffColorFilter(num.intValue(), PorterDuff.Mode.SRC_ATOP);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TINT_COLOR_SELECTABLE_FILTER {
        static final ColorFilter value = new PorterDuffColorFilter(-1711276033, PorterDuff.Mode.SRC_ATOP);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TRANSPARENT_COLOR_FILTER {
        static final ColorFilter value = new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP);
    }

    public ImageViewHolder(View view, int i2, boolean z) {
        super(view, i2);
        this.mThumbKind = ThumbKind.MEDIUM_KIND;
        this.mClickEnabled = true;
        this.mShowDeco = true;
        this.mIsSelectable = true;
        this.mThumbnailShapeType = -1;
        this.mThumbnailShapeRadius = 0.0f;
        this.mViewId = 0;
        this.mCallback = new b(this, 1);
        this.mAccessibilityDelegate = new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setClassName(ImageViewHolder.this.getClassNameForAccessibility());
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 32768) {
                    Log.v(ImageViewHolder.this.TAG, "Populating accessibility event");
                    accessibilityEvent.setClassName(ImageViewHolder.this.getClassNameForAccessibility());
                    ImageViewHolder.this.setContentDescription();
                } else if (accessibilityEvent.getEventType() == 1 || accessibilityEvent.getEventType() == 2) {
                    ImageViewHolder.this.setContentDescriptionAfterClicked(accessibilityEvent.getEventType());
                }
            }
        };
        this.mAccessibilityDelegateCommon = new View.AccessibilityDelegate() {
            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 32768) {
                    Log.v(ImageViewHolder.this.TAG, "Populating accessibility event");
                    ImageViewHolder.this.appendContentDescription(view);
                }
            }
        };
        this.mBgColor = view.getContext().getColor(R.color.thumbnail_icon_background);
        this.mClickEnabled = z;
        this.mForegroundDrawable = this.mImageView.getForeground();
    }

    /* access modifiers changed from: private */
    public void appendContentDescription(View view) {
        if (view != null && this.mMediaItem != null) {
            String contentDescription = getContentDescription();
            if (!TextUtils.isEmpty(contentDescription)) {
                String buttonTypeString = getButtonTypeString(view);
                view.setContentDescription(contentDescription + buttonTypeString);
            }
        }
    }

    private void changeDrawableIfBrokenImage(ColorFilter colorFilter) {
        Bitmap bitmap;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && mediaItem.isBroken() && (bitmap = this.mBitmap) != null && colorFilter != null) {
            this.mImageView.setImageDrawable(getBrokenDrawable(bitmap, colorFilter));
        }
    }

    private void clearContentType() {
        ImageButton imageButton = this.mContentTypeIcon;
        if (imageButton != null && this.mContentDuration != null) {
            imageButton.setBackground((Drawable) null);
            ViewUtils.setVisibility(this.mContentDuration, 8);
            ViewUtils.setVisibility(this.mContentTypeContainer, 8);
        }
    }

    private String getButtonTypeString(View view) {
        if (view != this.mExpandView) {
            return "";
        }
        return " " + view.getResources().getString(R.string.expand);
    }

    private String getContentDescriptionHeader() {
        String localizedDateTime = TimeUtil.getLocalizedDateTime(this.mMediaItem.getDateTaken());
        if (this.mMediaItem.isSimilarShot()) {
            return j.f(new StringBuilder(), getContext().getResources().getQuantityString(R.plurals.similar_photo_group_tb, this.mMediaItem.getCount(), new Object[]{Integer.valueOf(this.mMediaItem.getCount())}), ArcCommonLog.TAG_COMMA, localizedDateTime);
        }
        return localizedDateTime;
    }

    private int getMinWidthShowDuration() {
        if (sMinWidthShowDuration == null) {
            sMinWidthShowDuration = Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.content_duration_display_min_width) - (getContext().getResources().getDimensionPixelSize(R.dimen.pictures_default_side_spacing) / 2));
        }
        return sMinWidthShowDuration.intValue();
    }

    private boolean hasStorageTypeView() {
        if (this.mStorageFavoriteSlot1ViewStub != null) {
            return true;
        }
        return false;
    }

    private void inflateVisualCueIconView() {
        ViewStub viewStub = this.mVisualCueViewStub;
        if (viewStub != null && this.mVisualCueIconView == null) {
            this.mVisualCueIconView = (ImageView) inflateStub(viewStub);
        }
    }

    private boolean isAvailableAdvancedMouseDrag(MotionEvent motionEvent) {
        ListViewHolder.SelectListener selectListener;
        if (motionEvent.getToolType(0) != 3 || ((selectListener = this.mSelectListener) == null ? isSelectionMode() : selectListener.isListSelectionMode()) || !DeviceInfo.isAdvancedMouseCompat(getContext()) || !isAvailableAdvancedMouseDragThreshold(motionEvent.getX(), this.mDownX, motionEvent.getY(), this.mDownY)) {
            return false;
        }
        return true;
    }

    private boolean isAvailableAdvancedMouseDragThreshold(float f, float f5, float f8, float f10) {
        float abs = Math.abs(f - f5);
        float abs2 = Math.abs(f8 - f10);
        if (abs > 10.0f || abs2 > 10.0f) {
            return true;
        }
        return false;
    }

    private boolean isAvailableHorizontalDrag(MotionEvent motionEvent) {
        if (!this.mHorizontalDrag || motionEvent.getAction() == 3 || Math.abs(motionEvent.getY() - this.mDownY) >= Math.abs(motionEvent.getX() - this.mDownX)) {
            return false;
        }
        return true;
    }

    private boolean isContentTypeVisible(MediaItem mediaItem) {
        if (mediaItem.isGif() || mediaItem.isRawImage()) {
            return SHOW_THUMBNAIL_RAW_ICON;
        }
        if (ResourceUtil.getIconResourceId(mediaItem) > 0) {
            return true;
        }
        return false;
    }

    private boolean isFavoriteEnabled() {
        int i2 = this.mSupportDecoItemType;
        if (i2 == -1 || (i2 & 64) == 0) {
            return false;
        }
        return true;
    }

    private boolean isStorageTypeEnabled() {
        int i2 = this.mSupportDecoItemType;
        if (i2 == -1 || (i2 & 16) == 0) {
            return false;
        }
        return true;
    }

    private boolean isStorageTypeEnabledIfPossible() {
        int i2 = this.mSupportDecoItemType;
        if (i2 == -1 || (i2 & 128) == 0) {
            return false;
        }
        return true;
    }

    private boolean isValidSimilarView(int i2) {
        if (i2 < 0 || this.mSimilarViewPosition != i2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$1(Object obj) {
        View view = (View) obj;
        view.setOnClickListener(this);
        view.setOnTouchListener(this);
        view.setOnLongClickListener(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$2(Object obj) {
        View view = (View) obj;
        view.setOnClickListener(this);
        view.setOnTouchListener(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        onHorizontalDrag(this.mViewId);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTouch$3(Handler handler) {
        handler.removeCallbacks(this.mCallback);
    }

    private /* synthetic */ void lambda$onTouch$4(Handler handler) {
        if (!handler.hasCallbacks(this.mCallback)) {
            handler.postDelayed(this.mCallback, 30);
        }
    }

    private /* synthetic */ void lambda$onTouch$5() {
        onHorizontalDrag(this.mViewId);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setContentTypeVisibility$6() {
        updateDuration(this.mImageView.getWidth());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateContentDurationView$9() {
        LinearLayout linearLayout = this.mContentTypeContainer;
        if (linearLayout != null) {
            linearLayout.requestLayout();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSimilarItem$7(MediaItem mediaItem, long j2) {
        updateSimilarCount(mediaItem);
        if (mediaItem.getCount() <= 1) {
            SimilarPhotoHelper.deleteGroupContents(getContext(), 2, mediaItem.getFileId(), j2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateSimilarItem$8(MediaItem mediaItem, long j2, int i2, ThreadPool.JobContext jobContext) {
        TimeTickLog timeTickLog = new TimeTickLog("updateSimilarItem");
        int similarCount = SimilarPhotoHelper.getSimilarCount(mediaItem.getBucketID(), j2);
        if (i2 != similarCount && isValidSimilarView(getViewPosition())) {
            mediaItem.setCount(similarCount);
            this.itemView.post(new H.b(this, mediaItem, j2, 4));
        }
        timeTickLog.tock(50);
        return null;
    }

    /* access modifiers changed from: private */
    public void setContentDescriptionAfterClicked(int i2) {
        if (this.mSelectListener != null) {
            if (i2 == 1 && isCheckBoxEnabled()) {
                int selectedCount = this.mSelectListener.getSelectedCount();
                this.itemView.setContentDescription(getContext().getResources().getQuantityString(R.plurals.speak_item_selected_quantity_string, selectedCount, new Object[]{Integer.valueOf(selectedCount)}));
            } else if (i2 == 2 && !isCheckBoxEnabled()) {
                this.itemView.setContentDescription(getContext().getResources().getQuantityString(R.plurals.speak_item_selected_quantity_string, 1, new Object[]{1}));
            }
        }
    }

    private void setContentIcon(ImageButton imageButton, int i2) {
        if (imageButton != null) {
            imageButton.setBackgroundResource(i2);
            ViewGroup.LayoutParams layoutParams = imageButton.getLayoutParams();
            if (i2 == R.drawable.gallery_ic_thumbnail_video) {
                layoutParams.width = layoutParams.height;
            } else {
                layoutParams.width = (int) (((float) layoutParams.height) * (((float) imageButton.getBackground().getIntrinsicWidth()) / ((float) imageButton.getBackground().getIntrinsicHeight())));
            }
            imageButton.setVisibility(0);
            imageButton.setOnClickListener(this);
        }
    }

    private void setContentTypeVisibility(MediaItem mediaItem) {
        if (this.mContentTypeContainer != null || this.mContentTypeViewStub != null) {
            if (!this.mShowDeco || !isContentTypeVisible(mediaItem)) {
                ViewUtils.setVisibility(this.mContentTypeContainer, 8);
                return;
            }
            inflateContentTypeContainer();
            if (this.mContentTypeContainer == null) {
                return;
            }
            if (mediaItem.isGif()) {
                updateTextIcon(mediaItem);
            } else if (!mediaItem.isRawImage() || mediaItem.isSimilarShot()) {
                if (this.mImageView.getWidth() == 0) {
                    this.mImageView.post(new b(this, 0));
                } else {
                    updateDuration(this.mImageView.getWidth());
                }
                updateContentType(getIconResourceId(mediaItem));
            } else {
                ShotMode byType = ShotModeList.getInstance().getByType("raw");
                if (byType != null) {
                    updateContentType(byType.iconRes);
                }
            }
        }
    }

    private void setExpandVisibility() {
        if (isSelectionMode()) {
            inflateExpandView();
            ViewUtils.setVisibleOrGone(this.mExpandView, true);
            return;
        }
        ViewUtils.setVisibleOrGone(this.mExpandView, false);
    }

    private void setVisualCueIconVisibility() {
        if (isVisualCueIconVisible()) {
            updateVisualCueIconView(true);
        }
    }

    private void startUpdateSimilarShot() {
        if (Features.isEnabled(Features.SUPPORT_CLUSTER_TABLE) && this.mMediaItem.getGroupType() == 2 && this.mMediaItem.getCount() == DbTable.UNLOADED) {
            updateSimilarItem(this.mMediaItem);
        }
    }

    private void updateContentDurationView(int i2, int i7, boolean z) {
        TextView textView;
        if (this.mContentTypeContainer != null && (textView = this.mContentDuration) != null) {
            int visibility = textView.getVisibility();
            if (isDurationAvailable(this.mMediaItem, i2, i7)) {
                if (z) {
                    this.mContentDuration.setText(TimeUtil.formatDuration(this.mImageView.getContext(), i2, DURATION_FORMAT));
                } else {
                    this.mContentDuration.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)}));
                }
                this.mContentDuration.setVisibility(0);
                this.mContentDuration.measure(0, 0);
                if (!hasEnoughSpaceForDuration(this.mMediaItem, i2, this.itemView.getWidth())) {
                    this.mContentDuration.setVisibility(8);
                }
            } else {
                this.mContentDuration.setVisibility(8);
            }
            if (visibility != this.mContentDuration.getVisibility()) {
                this.mContentTypeContainer.post(new b(this, 2));
            }
        }
    }

    private void updateSimilarCount(MediaItem mediaItem) {
        if (!isValidSimilarView(getViewPosition())) {
            return;
        }
        if (mediaItem.getCount() > 1) {
            inflateContentTypeContainer();
            updateContentType(ShotModeList.getInstance().getByType("Similar photo").iconRes);
            updateSimilarShotCount(mediaItem.getCount(), this.mImageView.getWidth());
            return;
        }
        clearContentType();
        setContentTypeVisibility(mediaItem);
    }

    private void updateSimilarItem(MediaItem mediaItem) {
        int i2;
        if (Features.isEnabled(Features.SUPPORT_CLUSTER_TABLE)) {
            this.mSimilarViewPosition = getViewPosition();
            long groupMediaId = mediaItem.getGroupMediaId();
            if (SimilarPhotoHelper.hasCachedCount(mediaItem.getBucketID(), groupMediaId)) {
                i2 = SimilarPhotoHelper.getCachedSimilarCount(mediaItem.getBucketID(), groupMediaId).intValue();
                mediaItem.setCount(i2);
                updateSimilarCount(mediaItem);
            } else {
                i2 = 0;
            }
            ThreadPool.getInstance().submit(new c(this, mediaItem, groupMediaId, i2));
        }
    }

    private void updateSimilarShotCount(int i2, int i7) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && mediaItem.isSimilarShot()) {
            if (i2 > 1 || i2 == DbTable.UNLOADED) {
                if (this.mContentTypeContainer == null || this.mContentDuration == null) {
                    inflateContentTypeContainer();
                }
                updateContentDurationView(i2, i7, false);
            }
        }
    }

    private void updateVisualCueIconView(boolean z) {
        int i2;
        if (z) {
            inflateVisualCueIconView();
        }
        if (applyImageColorFilter() && this.mIsSelectable) {
            applyVisualCueImageFilter(z);
        }
        ImageView imageView = this.mVisualCueIconView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(imageView, i2);
    }

    public void addThumbnailBorder(Drawable drawable) {
        this.mImageView.setForeground(drawable);
    }

    public boolean applyFocusedBorderOnAdvancedMouseEvent() {
        return true;
    }

    public boolean applyImageColorFilter() {
        return true;
    }

    public void applyVisualCueImageFilter(boolean z) {
        setColorFilter(getColorFilter(z));
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mImageView.addOnLayoutChangeListener(this);
        this.itemView.setAccessibilityDelegate(this.mAccessibilityDelegate);
        if (this.mClickEnabled) {
            View viewForActionListener = getViewForActionListener();
            viewForActionListener.setOnLongClickListener(this);
            viewForActionListener.setOnClickListener(this);
            viewForActionListener.setOnTouchListener(this);
            viewForActionListener.setOnHoverListener(this);
        }
        setTransitionName(mediaItem);
    }

    public void bindImage(Bitmap bitmap) {
        Drawable drawable;
        ImageView.ScaleType scaleType;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            if (bitmap == null || bitmap != this.mBitmap) {
                if (bitmap == null) {
                    Log.w(this.TAG, "bindImage broken {" + this.mMediaItem.getFileId() + "}");
                    this.mMediaItem.setBroken(true);
                    bitmap = getBrokenBitmap(this.mMediaItem);
                    drawable = getBrokenDrawable(bitmap, getColorFilter(false));
                } else {
                    drawable = null;
                }
                Bitmap bitmap2 = this.mBitmap;
                if (bitmap2 != null) {
                    recycleBitmap(bitmap2);
                    this.mBitmapUnMutable = null;
                }
                ImageView imageView = this.mImageView;
                if (this.mMediaItem.isScenesOrCreature()) {
                    scaleType = ImageView.ScaleType.CENTER_CROP;
                } else {
                    scaleType = ImageView.ScaleType.MATRIX;
                }
                imageView.setScaleType(scaleType);
                this.mBitmap = bitmap;
                if (drawable != null) {
                    this.mImageView.setImageDrawable(drawable);
                } else {
                    this.mImageView.setImageBitmap(bitmap);
                }
                if (this.mImageView.getVisibility() != 0) {
                    Log.e(this.TAG, "bindImage wrong visibility=" + this.mImageView.getVisibility());
                }
                setViewMatrix();
                setDecoViewVisibility(this.mMediaItem);
                ListViewHolder.OnImageBindListener onImageBindListener = this.mOnImageBindListener;
                if (onImageBindListener != null) {
                    ((e) onImageBindListener).d.onBindImageInternal(getViewPosition(), this);
                }
                if (Features.isEnabled(Features.SUPPORT_CLUSTER_TABLE)) {
                    startUpdateSimilarShot();
                }
                if (!this.mMediaItem.isTransparent()) {
                    this.mImageView.setBackground((Drawable) null);
                    return;
                }
                return;
            }
            setDecoViewVisibility(mediaItem);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mImageView = (ImageView) view.findViewById(R.id.thumbnail);
        this.mCheckboxViewStub = (ViewStub) view.findViewById(R.id.checkboxViewStub);
        this.mExpandViewStub = (ViewStub) view.findViewById(R.id.expandViewStub);
        this.mContentTypeViewStub = (ViewStub) view.findViewById(R.id.content_type_container_ViewStub);
        this.mThumbnailPreviewLayout = (FrameLayout) view.findViewById(R.id.thumbnail_preview_layout);
        this.mDecoViewLayout = (ViewGroup) view.findViewById(R.id.deco_view_layout);
        this.mContentTypeIcon = (ImageButton) view.findViewById(R.id.content_type_icon);
        this.mContentTypeTextIcon = (TextView) view.findViewById(R.id.content_type_text_icon);
        this.mContentDuration = (TextView) view.findViewById(R.id.content_duration);
        this.mVisualCueViewStub = (ViewStub) view.findViewById(R.id.visualCueViewStub);
        this.mStorageFavoriteSlot1ViewStub = (ViewStub) view.findViewById(R.id.storage_favorite_slot1_ViewStub);
        this.mStorageFavoriteSlot1 = (ImageView) view.findViewById(R.id.storage_favorite_slot1);
        this.mStorageFavoriteSlot2ViewStub = (ViewStub) view.findViewById(R.id.storage_favorite_slot2_ViewStub);
        this.mStorageFavoriteSlot2 = (ImageView) view.findViewById(R.id.storage_favorite_slot2);
        this.mSelectedTint = view.getResources().getColor(R.color.selected_image_tint_color, getContext().getTheme());
        Optional.ofNullable(view.findViewById(R.id.checkbox)).ifPresent(new a(this, 0));
        Optional.ofNullable(view.findViewById(R.id.expand)).ifPresent(new a(this, 1));
    }

    public void clearReplacedView(View view) {
        super.clearReplacedView(view);
        if (this.mExpandView == view) {
            this.mExpandView = null;
        } else if (this.mContentTypeContainer == view) {
            this.mContentTypeContainer = null;
            this.mContentTypeTextIcon = null;
            this.mContentTypeIcon = null;
            this.mContentDuration = null;
        } else if (this.mVisualCueIconView == view) {
            this.mVisualCueIconView = null;
        } else if (this.mStorageFavoriteSlot1 == view) {
            this.mStorageFavoriteSlot1 = null;
        } else if (this.mStorageFavoriteSlot2 == view) {
            this.mStorageFavoriteSlot2 = null;
        }
    }

    public void drawVisualCue() {
        this.mSupportDecoItemType |= 32;
        updateVisualCueIconView(true);
    }

    public boolean durationAvailableItem(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isSingleTakenShot()) {
            return false;
        }
        return true;
    }

    public boolean equalBitmap(Bitmap bitmap) {
        if (this.mBitmap == bitmap) {
            return true;
        }
        return false;
    }

    public void eraseVisualCue() {
        this.mSupportDecoItemType &= -33;
        updateVisualCueIconView(false);
    }

    public Bitmap getBitmap() {
        return getBitmap(true);
    }

    public Bitmap getBrokenBitmap(MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(this.itemView.getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    public Drawable getBrokenDrawable(Bitmap bitmap, ColorFilter colorFilter) {
        return ResourceUtil.getBrokenDrawableWithExtraKey(getContext(), bitmap, colorFilter.toString());
    }

    public String getClassNameForAccessibility() {
        return "android.widget.Image";
    }

    public final ColorFilter getColorFilter(boolean z) {
        if (z) {
            return TINT_COLOR_FILTER.get(this.mSelectedTint);
        }
        return TRANSPARENT_COLOR_FILTER.value;
    }

    public String getContentDescription() {
        String str;
        int i2;
        String str2;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getContentDescriptionHeader());
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (this.mMediaItem.isFavourite()) {
            str = getContext().getString(R.string.favorite) + ArcCommonLog.TAG_COMMA;
        } else {
            str = "";
        }
        sb2.append(str);
        Context context = getContext();
        if (this.mMediaItem.isImage()) {
            i2 = R.string.image;
        } else {
            i2 = R.string.video;
        }
        sb2.append(context.getString(i2));
        if (this.mMediaItem.isImage()) {
            str2 = " ";
        } else {
            str2 = TimeUtil.formatDurationForAccessibility(getContext(), this.mMediaItem.getFileDuration(), DURATION_FORMAT);
        }
        sb2.append(str2);
        return sb2.toString();
    }

    public float getCornerRadius() {
        return this.mThumbnailShapeRadius;
    }

    public View getDecoView(int i2) {
        if (i2 == 11) {
            return this.mDecoViewLayout;
        }
        if (i2 == 12) {
            inflateExpandView();
            return this.mExpandView;
        } else if (i2 == 13) {
            inflateContentTypeContainer();
            return this.mContentTypeContainer;
        } else if (i2 == 14) {
            if (isVisualCueIconVisible()) {
                inflateVisualCueIconView();
            }
            return this.mVisualCueIconView;
        } else if (i2 == 15) {
            return this.mContentDuration;
        } else {
            if (i2 == 16) {
                inflateStorageFavoriteTypeSlot1();
                return this.mStorageFavoriteSlot1;
            } else if (i2 != 17) {
                return super.getDecoView(i2);
            } else {
                inflateStorageFavoriteTypeSlot2();
                return this.mStorageFavoriteSlot2;
            }
        }
    }

    public int getFocusedBorderDrawableId() {
        return R.drawable.thumbnail_focused_border_on_dex;
    }

    public int getIconResourceId(MediaItem mediaItem) {
        return ResourceUtil.getIconResourceId(mediaItem);
    }

    public ImageView getImage() {
        return this.mImageView;
    }

    public View getScalableView() {
        FrameLayout frameLayout = this.mThumbnailPreviewLayout;
        if (frameLayout != null) {
            return frameLayout;
        }
        return this.mImageView;
    }

    public int getSlot1ResId() {
        return R.drawable.gallery_ic_thumbnail_favorite;
    }

    public int getSlot2ResId() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            return ResourceUtil.getStorageResourceId(mediaItem);
        }
        return 0;
    }

    public String getString(int i2) {
        try {
            return getContext().getString(i2);
        } catch (Exception unused) {
            Log.w(this.TAG, "getString failed");
            return "";
        }
    }

    public ThumbKind getThumbKind() {
        return this.mThumbKind;
    }

    public Drawable getThumbnailBorder() {
        return this.mImageView.getForeground();
    }

    public View getViewForActionListener() {
        return this.itemView;
    }

    public int getViewItemId(View view) {
        if (view == this.mExpandView) {
            return 2;
        }
        if (view == this.mImageView) {
            return 100;
        }
        return super.getViewItemId(view);
    }

    public boolean hasBitmap() {
        if (this.mBitmap != null) {
            return true;
        }
        return false;
    }

    public boolean hasEnoughSpaceForDuration(MediaItem mediaItem, int i2, int i7) {
        int i8;
        if (!(this.mContentTypeIcon == null || this.mContentDuration == null || (mediaItem == null || !mediaItem.isSimilarShot() ? i2 < 0 : i2 <= 0))) {
            View view = this.mExpandView;
            if (view == null || view.getVisibility() != 0) {
                i8 = 0;
            } else {
                i8 = this.mExpandView.getWidth();
            }
            if (((i7 - this.mContentTypeIcon.getWidth()) - this.mContentDuration.getMeasuredWidth()) - i8 > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasImageView() {
        return true;
    }

    public void inflateContentTypeContainer() {
        ViewStub viewStub;
        if (this.mContentTypeContainer == null && (viewStub = this.mContentTypeViewStub) != null) {
            LinearLayout linearLayout = (LinearLayout) inflateStub(viewStub);
            this.mContentTypeContainer = linearLayout;
            linearLayout.setFocusable(false);
            TextView textView = (TextView) this.mContentTypeContainer.findViewById(R.id.content_type_text_icon);
            this.mContentTypeTextIcon = textView;
            textView.setFocusable(false);
            ImageButton imageButton = (ImageButton) this.mContentTypeContainer.findViewById(R.id.content_type_icon);
            this.mContentTypeIcon = imageButton;
            imageButton.setFocusable(false);
            TextView textView2 = (TextView) this.mContentTypeContainer.findViewById(R.id.content_duration);
            this.mContentDuration = textView2;
            textView2.setFocusable(false);
        }
    }

    public void inflateExpandView() {
        ViewStub viewStub;
        if (this.mExpandView == null && (viewStub = this.mExpandViewStub) != null) {
            View inflateStub = inflateStub(viewStub);
            this.mExpandView = inflateStub;
            inflateStub.setOnClickListener(this);
            this.mExpandView.setOnTouchListener(this);
            this.itemView.setNextFocusRightId(this.mExpandView.getId());
            this.itemView.setNextFocusForwardId(this.mExpandView.getId());
            this.mExpandView.setNextFocusLeftId(this.itemView.getId());
            this.mExpandView.setAccessibilityDelegate(this.mAccessibilityDelegateCommon);
        }
    }

    public ImageView inflateStorageFavoriteSlot(boolean z) {
        if (z) {
            ViewStub viewStub = this.mStorageFavoriteSlot1ViewStub;
            if (viewStub != null && this.mStorageFavoriteSlot1 == null) {
                this.mStorageFavoriteSlot1 = (ImageView) inflateStub(viewStub);
            }
        } else {
            ViewStub viewStub2 = this.mStorageFavoriteSlot2ViewStub;
            if (viewStub2 != null && this.mStorageFavoriteSlot2 == null) {
                this.mStorageFavoriteSlot2 = (ImageView) inflateStub(viewStub2);
            }
        }
        if (z) {
            return this.mStorageFavoriteSlot1;
        }
        return this.mStorageFavoriteSlot2;
    }

    public void inflateStorageFavoriteTypeSlot1() {
        ViewStub viewStub;
        if (this.mStorageFavoriteSlot1 == null && (viewStub = this.mStorageFavoriteSlot1ViewStub) != null) {
            ImageView imageView = (ImageView) inflateStub(viewStub);
            this.mStorageFavoriteSlot1 = imageView;
            imageView.setFocusable(false);
        }
    }

    public void inflateStorageFavoriteTypeSlot2() {
        ViewStub viewStub;
        if (this.mStorageFavoriteSlot2 == null && (viewStub = this.mStorageFavoriteSlot2ViewStub) != null) {
            ImageView imageView = (ImageView) inflateStub(viewStub);
            this.mStorageFavoriteSlot2 = imageView;
            imageView.setFocusable(false);
        }
    }

    public boolean isChangedViewSize(int i2, int i7, int i8, int i10) {
        if (i2 == i8 && i7 == i10) {
            return false;
        }
        return true;
    }

    public boolean isDurationAvailable(MediaItem mediaItem, int i2, int i7) {
        if (i7 < getMinWidthShowDuration() || !durationAvailableItem(mediaItem)) {
            return false;
        }
        if (mediaItem == null || !mediaItem.isSimilarShot()) {
            if (i2 >= 0) {
                return true;
            }
            return false;
        } else if (i2 > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFolder() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isFolder()) {
            return false;
        }
        return true;
    }

    public boolean isSelectionMode() {
        int i2 = this.mSupportDecoItemType;
        if (i2 == -1 || (i2 & 2) == 0) {
            return false;
        }
        return true;
    }

    public boolean isSlot1Enabled() {
        MediaItem mediaItem;
        if (!isFavoriteEnabled() || (mediaItem = this.mMediaItem) == null || !mediaItem.isFavourite()) {
            return false;
        }
        return true;
    }

    public boolean isSlot2Enabled(boolean z) {
        if (isStorageTypeEnabled()) {
            return true;
        }
        if (!isStorageTypeEnabledIfPossible() || z) {
            return false;
        }
        return true;
    }

    public boolean isVisualCueIconVisible() {
        int i2 = this.mSupportDecoItemType;
        if (i2 == -1 || (i2 & 32) == 0) {
            return false;
        }
        return true;
    }

    public void onCheckboxViewStubInflated(CheckBox checkBox) {
        checkBox.setOnClickListener(this);
        checkBox.setOnTouchListener(this);
        checkBox.setOnLongClickListener(this);
    }

    public void onClick(View view) {
        onItemClickInternal(getViewItemId(view));
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        return onHoverInternal(motionEvent);
    }

    public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        int i15 = i8 - i2;
        if (isChangedViewSize(i13 - i11, i14 - i12, i15, i10 - i7) && this.mBitmap != null) {
            setViewMatrix();
            updateDuration(i15);
        }
    }

    public boolean onLongClick(View view) {
        return onItemLongClickInternal(getViewItemId(view));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!motionEvent.isButtonPressed(32)) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mDownX = motionEvent.getX();
                this.mDownY = motionEvent.getY();
                this.mHorizontalDrag = false;
                if (motionEvent.getButtonState() == 2) {
                    this.mSecondaryButtonClicked = true;
                }
            } else if (action == 1 || action == 3) {
                Optional.ofNullable(this.itemView.getHandler()).ifPresent(new a(this, 2));
                ListViewHolder.SelectListener selectListener = this.mSelectListener;
                if (selectListener != null) {
                    selectListener.onTouchUp();
                }
                if (this.mSecondaryButtonClicked) {
                    onItemSecondaryClickInternal(getViewItemId(view), new PointF(motionEvent.getRawX(), motionEvent.getRawY()), motionEvent);
                    this.mSecondaryButtonClicked = false;
                    return true;
                } else if (motionEvent.getAction() == 3 && isAvailableAdvancedMouseDrag(motionEvent)) {
                    Log.d(this.TAG, "onAdvancedMouseDrag: from ACTION_CANCEL");
                    onAdvancedMouseDrag(getViewItemId(view));
                    return false;
                }
            } else {
                if (!this.mHorizontalDrag && Math.abs(motionEvent.getX() - this.mDownX) > 24.0f) {
                    this.mHorizontalDrag = true;
                }
                if (isAvailableAdvancedMouseDrag(motionEvent)) {
                    onAdvancedMouseDrag(getViewItemId(view));
                    return false;
                } else if (isAvailableHorizontalDrag(motionEvent)) {
                    this.mViewId = getViewItemId(view);
                    Optional ofNullable = Optional.ofNullable(this.itemView.getHandler());
                    if (ofNullable.isPresent()) {
                        lambda$onTouch$4((Handler) ofNullable.get());
                        return false;
                    }
                    lambda$onTouch$5();
                    return false;
                }
            }
        }
        return false;
    }

    public void recycle() {
        this.mSimilarViewPosition = -1;
        this.mShowDeco = true;
        this.mIsSelectable = true;
        this.mImageView.setTag((Object) null);
        this.mImageView.removeOnLayoutChangeListener(this);
        this.mImageView.setImageDrawable((Drawable) null);
        this.mImageView.setImageMatrix((Matrix) null);
        this.mImageView.setTransitionName((String) null);
        this.itemView.setAccessibilityDelegate((View.AccessibilityDelegate) null);
        if (this.mImageView.getAlpha() != 1.0f) {
            this.mImageView.setAlpha(1.0f);
        }
        this.mImageView.setVisibility(0);
        this.mImageView.setBackgroundColor(this.mBgColor);
        recycleBitmap(this.mBitmap);
        this.mBitmap = null;
        this.mBitmapUnMutable = null;
        this.mThumbKind = ThumbKind.MEDIUM_KIND;
        ViewUtils.setVisibility(this.mExpandView, 8);
        ViewUtils.setVisibility(this.mContentTypeContainer, 8);
        ViewUtils.setVisibility(this.mContentTypeIcon, 8);
        ViewUtils.setVisibility(this.mContentTypeTextIcon, 8);
        ViewUtils.setVisibility(this.mContentDuration, 8);
        ViewUtils.setVisibility(this.mVisualCueIconView, 8);
        ViewUtils.setVisibility(this.mStorageFavoriteSlot1, 8);
        ViewUtils.setVisibility(this.mStorageFavoriteSlot2, 8);
        if (applyImageColorFilter()) {
            setColorFilter(getColorFilter(false));
        }
        super.recycle();
    }

    public void recycleBitmap(Bitmap bitmap) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && bitmap != null && !mediaItem.isBroken() && !this.mTempMode) {
            ThumbnailLoader.getInstance().recycle((String) null, bitmap);
        }
    }

    public void recycleToBackup() {
        super.recycleToBackup();
        replaceWithViewStubToClear(this.mContentTypeContainer, this.mContentTypeViewStub);
        replaceWithViewStubToClear(this.mExpandView, this.mExpandViewStub);
        replaceWithViewStubToClear(this.mVisualCueIconView, this.mVisualCueViewStub);
        replaceWithViewStubToClear(this.mStorageFavoriteSlot1, this.mStorageFavoriteSlot1ViewStub);
        replaceWithViewStubToClear(this.mStorageFavoriteSlot2, this.mStorageFavoriteSlot2ViewStub);
    }

    public boolean requireThumbnail() {
        return true;
    }

    public void restoreThumbnailBorder() {
        this.mImageView.setForeground(this.mForegroundDrawable);
    }

    public void setCheckboxEnabled(boolean z) {
        CheckBox checkBox;
        super.setCheckboxEnabled(z);
        if (hasCheckbox()) {
            if (applyImageColorFilter() && (checkBox = this.mCheckboxView) != null && !checkBox.isChecked() && this.mIsSelectable) {
                setColorFilter(getColorFilter(isVisualCueIconVisible()));
            }
            if (this.mBitmap != null) {
                setDecoViewVisibilityInSelectionMode();
            }
        }
        CheckBox checkBox2 = this.mCheckboxView;
        if (checkBox2 != null) {
            checkBox2.setFocusable(false);
            this.mCheckboxView.setImportantForAccessibility(2);
        }
    }

    public void setChecked(boolean z) {
        boolean z3;
        super.setChecked(z);
        if (applyImageColorFilter() && hasCheckbox() && this.mIsSelectable) {
            if (z || isVisualCueIconVisible()) {
                z3 = true;
            } else {
                z3 = false;
            }
            setColorFilter(getColorFilter(z3));
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        changeDrawableIfBrokenImage(colorFilter);
        this.mImageView.setColorFilter(colorFilter);
    }

    public void setContentDescription() {
        if (this.mMediaItem != null) {
            String contentDescription = getContentDescription();
            if (!TextUtils.isEmpty(contentDescription)) {
                boolean isVisible = ViewUtils.isVisible(getCheckbox());
                int i2 = R.string.speak_checked;
                if (isVisible) {
                    Context context = this.itemView.getContext();
                    if (!getCheckbox().isChecked()) {
                        i2 = R.string.speak_not_checked;
                    }
                    String string = context.getString(i2);
                    View view = this.itemView;
                    view.setContentDescription(string + ArcCommonLog.TAG_COMMA + contentDescription + ArcCommonLog.TAG_COMMA + this.itemView.getContext().getString(R.string.speak_checkbox));
                } else if (isVisualCueIconVisible()) {
                    View view2 = this.itemView;
                    view2.setContentDescription(this.itemView.getContext().getString(R.string.speak_checked) + ArcCommonLog.TAG_COMMA + contentDescription);
                } else {
                    this.itemView.setContentDescription(contentDescription);
                }
            }
        }
    }

    public void setDecoViewVisibility(MediaItem mediaItem) {
        setContentTypeVisibility(mediaItem);
        setVisualCueIconVisibility();
        if (isSelectionMode()) {
            setDecoViewVisibilityInSelectionMode();
        } else {
            setStorageFavoriteVisibility();
        }
    }

    public void setDecoViewVisibilityInSelectionMode() {
        setExpandVisibility();
        setStorageFavoriteVisibility();
    }

    public void setFocusedFilterOnAdvancedMouseEvent(boolean z) {
        setFocusedFilterOnAdvancedMouseEvent(z, applyFocusedBorderOnAdvancedMouseEvent());
    }

    public void setImageUid(Object obj) {
        this.mImageView.setTag(obj);
    }

    public void setSelectable(boolean z) {
        ColorFilter colorFilter;
        this.mIsSelectable = z;
        if (this.mImageView != null) {
            if (z) {
                colorFilter = TRANSPARENT_COLOR_FILTER.value;
            } else {
                colorFilter = TINT_COLOR_SELECTABLE_FILTER.value;
            }
            setColorFilter(colorFilter);
        }
    }

    public void setShowDeco(boolean z) {
        int i2;
        boolean z3;
        int i7;
        MediaItem mediaItem;
        this.mShowDeco = z;
        if (z && this.mContentTypeContainer == null && (mediaItem = this.mMediaItem) != null) {
            setContentTypeVisibility(mediaItem);
        }
        LinearLayout linearLayout = this.mContentTypeContainer;
        int i8 = 8;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(linearLayout, i2);
        boolean isSlot1Enabled = isSlot1Enabled();
        if (!isSlot2Enabled(isSlot1Enabled) || getSlot2ResId() <= 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        ImageView imageView = this.mStorageFavoriteSlot1;
        if (!z || (!isSlot1Enabled && !z3)) {
            i7 = 8;
        } else {
            i7 = 0;
        }
        ViewUtils.setVisibility(imageView, i7);
        ImageView imageView2 = this.mStorageFavoriteSlot2;
        if (z && isSlot1Enabled && z3) {
            i8 = 0;
        }
        ViewUtils.setVisibility(imageView2, i8);
    }

    public void setStorageFavoriteVisibility() {
        boolean z;
        int slot2ResId;
        boolean z3;
        if (hasStorageTypeView()) {
            boolean z7 = true;
            boolean z9 = false;
            if (isSlot1Enabled()) {
                ImageView inflateStorageFavoriteSlot = inflateStorageFavoriteSlot(true);
                inflateStorageFavoriteSlot.setImageResource(getSlot1ResId());
                inflateStorageFavoriteSlot.setVisibility(0);
                z = true;
            } else {
                z = false;
            }
            if (isSlot2Enabled(z) && (slot2ResId = getSlot2ResId()) > 0) {
                ImageView inflateStorageFavoriteSlot2 = inflateStorageFavoriteSlot(!z);
                if (inflateStorageFavoriteSlot2 == this.mStorageFavoriteSlot2) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z && z3) {
                    z7 = false;
                }
                inflateStorageFavoriteSlot2.setImageResource(slot2ResId);
                inflateStorageFavoriteSlot2.setVisibility(0);
                z = z7;
                z9 = z3;
            }
            if (!z) {
                ViewUtils.setVisibility(this.mStorageFavoriteSlot1, 8);
            }
            if (!z9) {
                ViewUtils.setVisibility(this.mStorageFavoriteSlot2, 8);
            }
        }
    }

    public void setThumbKind(ThumbKind thumbKind) {
        this.mThumbKind = thumbKind;
    }

    public void setThumbnailBackgroundColor(int i2) {
        this.mBgColor = i2;
        this.mImageView.setBackgroundColor(i2);
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        this.mThumbnailShapeType = i2;
        this.mThumbnailShapeRadius = f;
        ViewUtils.setViewShape(this.mImageView, i2, f);
        return this;
    }

    public void setViewMatrix() {
        setViewMatrix(this.mImageView, this.mMediaItem);
    }

    public void updateContentType(int i2) {
        if (i2 > 0) {
            setContentIcon(this.mContentTypeIcon, i2);
            ViewUtils.setVisibility(this.mContentTypeTextIcon, 8);
        }
        ViewUtils.setVisibility(this.mContentTypeContainer, 0);
    }

    public void updateDecoItemViews() {
        int i2;
        setStorageFavoriteVisibility();
        View view = this.mExpandView;
        if (view != null) {
            if (isSelectionMode()) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        if ((i2 & 2048) != 0) {
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem != null) {
                inflateContentTypeContainer();
                setContentTypeVisibility(mediaItem);
                startUpdateSimilarShot();
            } else {
                Log.e(this.TAG, "updateDecoration#DECO_TYPE_SIMILAR {null}");
                clearContentType();
            }
            return true;
        } else if (!PreferenceFeatures.OneUi40.SUPPORT_NONDESTRUCTIVE_SLOW_MO || (i2 & 4096) == 0) {
            return super.updateDecoration(i2, objArr);
        } else {
            MediaItem mediaItem2 = this.mMediaItem;
            if (mediaItem2 != null && MediaItemUtil.isNoneDestructionSlowRecordingMode(mediaItem2.getRecordingMode())) {
                inflateContentTypeContainer();
                setContentTypeVisibility(mediaItem2);
            }
            return true;
        }
    }

    public void updateDuration(int i2) {
        int i7;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null) {
            return;
        }
        if (mediaItem.isSimilarShot()) {
            updateSimilarShotCount(this.mMediaItem.getCount(), i2);
            return;
        }
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || !MediaItemUtil.isHighLightClip(this.mMediaItem)) {
            i7 = this.mMediaItem.getFileDuration();
        } else {
            i7 = ((int) (MediaItemUtil.getHighLightClipEndTime(this.mMediaItem) - this.mMediaItem.getVideoThumbStartTime())) / 1000;
        }
        updateDuration(i7, i2);
    }

    public void updateMatrix() {
        setViewMatrix();
    }

    public void updateTextIcon(MediaItem mediaItem) {
        ViewUtils.setVisibility(this.mContentTypeIcon, 8);
        if (mediaItem.isGif()) {
            this.mContentTypeTextIcon.setText(R.string.gif);
        }
        ViewUtils.setVisibility(this.mContentTypeTextIcon, 0);
        ViewUtils.setVisibility(this.mContentTypeContainer, 0);
    }

    public boolean useExpandCrop() {
        if (getViewType() != 1) {
            return true;
        }
        return false;
    }

    public boolean useSelectListener() {
        return true;
    }

    public Bitmap getBitmap(boolean z) {
        Bitmap bitmap;
        if (!z) {
            return this.mBitmap;
        }
        Bitmap bitmap2 = this.mBitmapUnMutable;
        if (bitmap2 != null || (bitmap = this.mBitmap) == null) {
            return bitmap2;
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        this.mBitmapUnMutable = copy;
        return copy;
    }

    public void setFocusedFilterOnAdvancedMouseEvent(boolean z, boolean z3) {
        if (this.mIsSelectable) {
            setColorFilter(getColorFilter(z));
        }
        if (!z3) {
            return;
        }
        if (z) {
            addThumbnailBorder(getContext().getDrawable(getFocusedBorderDrawableId()));
        } else {
            restoreThumbnailBorder();
        }
    }

    public void setViewMatrix(ImageView imageView, MediaItem mediaItem) {
        ViewMatrixUtils.setViewMatrix(imageView, mediaItem, useExpandCrop());
    }

    public final void setViewMatrix(RectF rectF, int i2) {
        ViewMatrixUtils.setViewMatrix(getImage(), this.mMediaItem, rectF, i2, false);
    }

    public final void setViewMatrix(RectF rectF, int i2, boolean z) {
        ViewMatrixUtils.setViewMatrix(getImage(), this.mMediaItem, rectF, i2, z);
    }

    public final void setViewMatrix(RectF rectF, int i2, int i7, boolean z) {
        ViewMatrixUtils.setViewMatrix(getImage(), this.mMediaItem, rectF, i2, i7, z);
    }

    private void updateDuration(int i2, int i7) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && mediaItem.isVideo()) {
            if (i2 >= 0 && (this.mContentTypeContainer == null || this.mContentDuration == null)) {
                inflateContentTypeContainer();
            }
            updateContentDurationView(i2, i7, true);
        }
    }

    public void setCheckboxEnabled(int i2, boolean z) {
        super.setCheckboxEnabled(i2, z);
        setCheckboxEnabled(z);
    }

    public ImageViewHolder(View view, int i2) {
        this(view, i2, true);
    }

    public void setTransitionName(MediaItem mediaItem) {
    }
}
