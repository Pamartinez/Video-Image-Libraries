package com.samsung.android.gallery.app.ui.list.albums.viewholder;

import Bb.g;
import Qb.e;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blur.BlurImageInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.FontUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.PinchBlurImageView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumTitleCountViewHolder extends AlbumTitleViewHolder {
    private View mAlbumDeco;
    protected View mAlbumExpiryNoticeView;
    private View mAlbumSync;
    private View mAlbumTypeLayout;
    private View mBorder;
    private ViewStub mBorderStub;
    protected TextView mCountText;
    protected View mEmptyAlbumView;
    protected boolean mEnableSyncView;
    private View mFrameView;
    private final View.OnLayoutChangeListener mLayoutChangeListener;
    protected View mNewLabel;
    protected int mNewLabelAlbumId;
    private View mTitleContainer;
    private final View[] mVirtualAlbumChildViews;
    private ImageView mVirtualAlbumEmptyIcon;
    private ImageView mVirtualAlbumFirstImage;
    private View mVirtualAlbumView;

    public AlbumTitleCountViewHolder(View view, int i2) {
        super(view, i2);
        this.mVirtualAlbumChildViews = new View[3];
        this.mLayoutChangeListener = new g(5, this);
        this.mEnableSyncView = false;
    }

    private void bindAlbumTypeLayout(View view) {
        View findViewById = view.findViewById(R.id.album_type_layout);
        this.mAlbumTypeLayout = findViewById;
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR && findViewById != null) {
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                layoutParams2.gravity = 8388661;
                layoutParams2.topMargin = this.mAlbumTypeLayout.getResources().getDimensionPixelSize(R.dimen.album_type_layout_margin_top_list);
                layoutParams2.setMarginEnd(this.mAlbumTypeLayout.getResources().getDimensionPixelSize(R.dimen.album_type_layout_margin_end_list));
                this.mAlbumTypeLayout.setLayoutParams(layoutParams2);
            }
        }
    }

    private void bindEmptyAlbumView(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || mediaItem == null || !mediaItem.isEmptyAlbum()) {
            ViewUtils.setStubVisibility(this.mEmptyAlbumView, 8);
            return;
        }
        inflateEmptyAlbumView();
        ViewUtils.setStubVisibility(this.mEmptyAlbumView, 0);
    }

    private void bindVirtualAlbumChildImage(ImageView imageView, Bitmap bitmap) {
        if (imageView != null) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            imageView.setImageBitmap(bitmap);
            setViewMatrix(imageView, this.mMediaItem);
        }
    }

    private void bindVirtualAlbumChildLayout(View view, int i2) {
        if (view != null) {
            view.setBackground(new ColorDrawable(getContext().getColor(i2)));
        }
    }

    private boolean bindVirtualAlbumImage(Bitmap bitmap) {
        Bitmap blurAfterResize;
        if (this.mVirtualAlbumView == null) {
            return false;
        }
        if (MediaItemUtil.isVirtualEmptyAlbum(this.mMediaItem) || bitmap == null) {
            ViewUtils.setVisibleOrGone(this.mVirtualAlbumFirstImage, false);
            ViewUtils.setVisibleOrGone(this.mVirtualAlbumEmptyIcon, true);
            Bitmap virtualAlbumCoverImage = ThumbnailLoader.getInstance().getVirtualAlbumCoverImage();
            bindVirtualAlbumChildLayout(this.mVirtualAlbumChildViews[0], getGradientBitmap(virtualAlbumCoverImage));
            bindVirtualAlbumChildLayout(this.mVirtualAlbumChildViews[1], BitmapUtils.applyColorFilter(virtualAlbumCoverImage, getContext().getColor(R.color.virtual_album_second_layer_filter_color)));
            bindVirtualAlbumChildLayout(this.mVirtualAlbumChildViews[2], BitmapUtils.applyColorFilter(virtualAlbumCoverImage, getContext().getColor(R.color.virtual_album_third_layer_filter_color)));
            super.bindImage(bitmap);
        } else {
            ViewUtils.setVisibleOrGone(this.mVirtualAlbumFirstImage, true);
            ViewUtils.setVisibleOrGone(this.mVirtualAlbumEmptyIcon, false);
            ViewUtils.setBackground(this.mVirtualAlbumChildViews[0], (Drawable) null);
            bindVirtualAlbumChildImage(this.mVirtualAlbumFirstImage, getGradientBitmap(bitmap));
            if (!Features.isEnabled(Features.SUPPORT_REALTIME_BLUR) || (blurAfterResize = BitmapUtils.blurAfterResize(getContext(), bitmap, 32)) == null) {
                bindVirtualAlbumChildLayout(this.mVirtualAlbumChildViews[1], BitmapUtils.applyColorFilter(bitmap, getContext().getColor(R.color.virtual_album_second_layer_background_color)));
                bindVirtualAlbumChildLayout(this.mVirtualAlbumChildViews[2], (int) R.color.virtual_album_third_layer_background_color);
                super.bindImage(BitmapUtils.applyColorFilter(ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), (int) R.color.album_no_pic_background_color, ThumbKind.MINI_KIND), BitmapUtils.getAverageColor(bitmap, 0.2f, false)));
            } else {
                bindVirtualAlbumChildLayout(this.mVirtualAlbumChildViews[1], BitmapUtils.applyColorFilter(blurAfterResize, getContext().getColor(R.color.virtual_album_second_layer_filter_color)));
                bindVirtualAlbumChildLayout(this.mVirtualAlbumChildViews[2], BitmapUtils.applyColorFilter(blurAfterResize, getContext().getColor(R.color.virtual_album_third_layer_filter_color)));
                super.bindImage(BitmapUtils.applyColorFilter(blurAfterResize, getContext().getColor(R.color.virtual_album_background_layer_filter_color)));
                return true;
            }
        }
        return true;
    }

    private void bindVirtualAlbumView(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || !MediaItemUtil.isVirtualAlbum(mediaItem)) {
            ViewUtils.setStubVisibility(this.mVirtualAlbumView, 8);
            return;
        }
        inflateVirtualAlbumView();
        ViewUtils.setStubVisibility(this.mVirtualAlbumView, 0);
        ImageView imageView = this.mVirtualAlbumFirstImage;
        if (imageView != null) {
            imageView.removeOnLayoutChangeListener(this.mLayoutChangeListener);
            this.mVirtualAlbumFirstImage.addOnLayoutChangeListener(this.mLayoutChangeListener);
        }
    }

    private void clearVirtualAlbumView() {
        ViewUtils.setStubVisibility(this.mVirtualAlbumView, 8);
        ViewUtils.setVisibility(this.mVirtualAlbumEmptyIcon, 8);
        ViewUtils.setVisibility(this.mVirtualAlbumFirstImage, 8);
        ImageView imageView = this.mVirtualAlbumFirstImage;
        if (imageView != null) {
            imageView.removeOnLayoutChangeListener(this.mLayoutChangeListener);
            this.mVirtualAlbumFirstImage.setImageDrawable((Drawable) null);
            this.mVirtualAlbumFirstImage.setImageMatrix((Matrix) null);
        }
    }

    private String getAlbumCountDescription() {
        if (this.mMediaItem.isFolder()) {
            return "";
        }
        int count = this.mMediaItem.getCount();
        if (count == 1 && FileUtils.isEmptyDummyImage(this.mMediaItem.getPath())) {
            count = 0;
        }
        if (count == 1) {
            return ArcCommonLog.TAG_COMMA + getString(R.string.speak_folder_name_1_item);
        } else if (count >= 0) {
            return ArcCommonLog.TAG_COMMA.concat(String.format(Locale.getDefault(), getString(R.string.speak_folder_name_n_items), new Object[]{Integer.valueOf(count)}));
        } else {
            return "";
        }
    }

    private String getAlbumString() {
        int i2;
        if (this.mMediaItem.isFolder()) {
            i2 = R.string.group_title;
        } else {
            i2 = R.string.album;
        }
        return getString(i2);
    }

    private Bitmap getGradientBitmap(Bitmap bitmap) {
        if (getContext().getResources().getBoolean(R.bool.isNightTheme)) {
            return BitmapUtils.applyGradient(bitmap, getContext().getColor(R.color.virtual_album_first_layer_gradient_start_color), getContext().getColor(R.color.virtual_album_first_layer_gradient_end_color));
        }
        return bitmap;
    }

    private String getNewLabelDescription() {
        if (!ViewUtils.isStubVisible(this.mNewLabel)) {
            return "";
        }
        return ArcCommonLog.TAG_COMMA + getString(R.string.new_content_added);
    }

    private Drawable getStrokeDrawable(float f) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(f);
        gradientDrawable.setStroke(getContext().getResources().getDimensionPixelOffset(R.dimen.album_view_sub_album_border_thickness), getContext().getColor(R.color.album_view_sub_album_border_color));
        return gradientDrawable;
    }

    private void inflateAlbumSyncView() {
        View view = this.mAlbumSync;
        if (view instanceof ViewStub) {
            this.mAlbumSync = ((ViewStub) view).inflate();
        }
    }

    private void inflateBorderView() {
        ViewStub viewStub;
        if (this.mBorder == null && (viewStub = this.mBorderStub) != null) {
            this.mBorder = viewStub.inflate();
        }
    }

    private void inflateEmptyAlbumView() {
        View view = this.mEmptyAlbumView;
        if (view instanceof ViewStub) {
            this.mEmptyAlbumView = ((ViewStub) view).inflate();
        }
    }

    private void inflateNewLabelView() {
        View view = this.mNewLabel;
        if (view instanceof ViewStub) {
            this.mNewLabel = ((ViewStub) view).inflate();
        }
    }

    private void inflateVirtualAlbumView() {
        View view = this.mVirtualAlbumView;
        if (view instanceof ViewStub) {
            View inflate = ((ViewStub) view).inflate();
            this.mVirtualAlbumView = inflate;
            this.mVirtualAlbumChildViews[0] = inflate.findViewById(R.id.first_layer);
            this.mVirtualAlbumChildViews[1] = this.mVirtualAlbumView.findViewById(R.id.second_layer);
            this.mVirtualAlbumChildViews[2] = this.mVirtualAlbumView.findViewById(R.id.third_layer);
            this.mVirtualAlbumEmptyIcon = (ImageView) this.mVirtualAlbumView.findViewById(R.id.empty_icon);
            this.mVirtualAlbumFirstImage = (ImageView) this.mVirtualAlbumView.findViewById(R.id.first_layer_image);
        }
    }

    private boolean isEmpty() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isEmptyAlbum()) {
            return true;
        }
        if (!isFolder() || this.mMediaItem.getItemsInFolder().length != 0) {
            return false;
        }
        return true;
    }

    private boolean isSkipBlur() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isVirtualAlbum()) {
            return true;
        }
        if (!isFolder() || this.mMediaItem.getItemsInFolder().length <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (this.mMediaItem != null && this.mBitmap != null && isChangedViewSize(i13 - i11, i14 - i12, i8 - i2, i10 - i7)) {
            setViewMatrix((ImageView) view, this.mMediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateNewLabelVisibility$1() {
        if (this.mTitleText.getMeasuredWidth() == 0) {
            this.mTitleText.requestLayout();
        }
    }

    private void setTitleColor() {
        boolean z;
        int i2;
        int i7;
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR && getViewType() == 2) {
            if (this.mAlbumTypeLayout != null) {
                ViewUtils.setTextColor(this.mTitleText, R.color.one_ui_album_title_text_color);
                return;
            }
            if (!MediaItemUtil.isVirtualAlbum(this.mMediaItem) || ResourceCompat.isNightTheme(this.itemView.getContext())) {
                z = false;
            } else {
                z = true;
            }
            TextView textView = this.mTitleText;
            if (z) {
                i2 = R.color.one_ui_blur_album_title_text_color_dark;
            } else {
                i2 = R.color.one_ui_blur_album_title_text_color;
            }
            ViewUtils.setTextColor(textView, i2);
            TextView textView2 = this.mCountText;
            if (z) {
                i7 = R.color.one_ui_blur_album_sub_title_text_color_dark;
            } else {
                i7 = R.color.one_ui_blur_album_sub_title_text_color;
            }
            ViewUtils.setTextColor(textView2, i7);
        }
    }

    public void addThumbnailBorder(Drawable drawable) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || this.mFrameView == null) {
            super.addThumbnailBorder(drawable);
            return;
        }
        this.mImageView.setForeground((Drawable) null);
        this.mFrameView.setForeground(drawable);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        bindCountView(mediaItem);
        bindEmptyAlbumView(mediaItem);
        bindVirtualAlbumView(mediaItem);
        setTitleColor();
        setNewLabel(this.mNewLabelAlbumId);
    }

    public void bindCountView(MediaItem mediaItem) {
        int i2;
        if (!mediaItem.isFolder()) {
            int count = mediaItem.getCount();
            if (count == 1 && FileUtils.isEmptyDummyImage(mediaItem.getPath())) {
                count = 0;
            }
            this.mCountText.setText(StringCompat.toReadableNumber((long) count));
            this.mCountText.setVisibility(0);
            return;
        }
        TextView textView = this.mCountText;
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || !isGridView()) {
            i2 = 8;
        } else {
            i2 = 4;
        }
        textView.setVisibility(i2);
    }

    public void bindImage(Bitmap bitmap) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || !MediaItemUtil.isVirtualAlbum(this.mMediaItem) || !bindVirtualAlbumImage(bitmap)) {
            super.bindImage(bitmap);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCountText = (TextView) view.findViewById(R.id.count);
        this.mNewLabel = view.findViewById(R.id.newLabel);
        this.mBorderStub = (ViewStub) view.findViewById(R.id.border_stub);
        this.mFrameView = view.findViewById(R.id.frame_view);
        this.mAlbumSync = view.findViewById(R.id.album_sync);
        this.mAlbumDeco = view.findViewById(R.id.album_deco);
        this.mTitleContainer = view.findViewById(R.id.title_container);
        this.mEmptyAlbumView = view.findViewById(R.id.emptyAlbumViewStub);
        this.mVirtualAlbumView = view.findViewById(R.id.virtualAlbumViewStub);
        bindAlbumTypeLayout(view);
        resizeUpFontToLarge(view);
    }

    public void enableBlur(boolean z) {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            ImageView imageView = this.mImageView;
            if (imageView instanceof PinchBlurImageView) {
                ((PinchBlurImageView) imageView).enableBlur(z);
            }
        }
    }

    public void enableBorderView(boolean z, Drawable drawable) {
        int i2;
        int i7;
        super.enableBorderView(z, drawable);
        inflateBorderView();
        View view = this.mBorder;
        int i8 = 0;
        if (!(view == null || this.mFrameView == null)) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
            View view2 = this.mFrameView;
            if (z) {
                i7 = 8;
            } else {
                i7 = 0;
            }
            view2.setVisibility(i7);
            if (z) {
                this.mBorder.setForeground(drawable);
            }
        }
        if (this.mMediaItem == null) {
            return;
        }
        if (z) {
            ViewUtils.setStubVisibility(this.mNewLabel, 8);
            this.mCountText.setVisibility(8);
            ViewUtils.setStubVisibility(this.mAlbumSync, 8);
            return;
        }
        setNewLabel(this.mNewLabelAlbumId);
        TextView textView = this.mCountText;
        if (this.mMediaItem.isFolder()) {
            if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || !isGridView()) {
                i8 = 8;
            } else {
                i8 = 4;
            }
        }
        textView.setVisibility(i8);
        setAlbumSyncView();
    }

    public String getContentDescription() {
        return this.mMediaItem.getDisplayName() + " " + getAlbumString() + getAlbumCountDescription() + getNewLabelDescription();
    }

    public TextView getCountView() {
        return this.mCountText;
    }

    public View getDecoView(int i2) {
        if (i2 == 1) {
            inflateNewLabelView();
            return this.mNewLabel;
        } else if (i2 == 26 && this.mEnableSyncView) {
            inflateAlbumSyncView();
            return this.mAlbumSync;
        } else if (i2 == 45) {
            return this.mAlbumExpiryNoticeView;
        } else {
            if (i2 == 28) {
                return this.mAlbumDeco;
            }
            if (i2 == 29) {
                return this.mTitleContainer;
            }
            if (i2 == 61) {
                inflateEmptyAlbumView();
                return this.mEmptyAlbumView;
            } else if (i2 == 64) {
                inflateVirtualAlbumView();
                return this.mVirtualAlbumView;
            } else if (i2 != 65) {
                return super.getDecoView(i2);
            } else {
                inflateVirtualAlbumView();
                return this.mVirtualAlbumEmptyIcon;
            }
        }
    }

    public boolean isGridView() {
        return true;
    }

    public void recycle() {
        this.mCountText.setText((CharSequence) null);
        ViewUtils.setStubVisibility(this.mAlbumSync, 8);
        ViewUtils.setVisibility(this.mAlbumExpiryNoticeView, 8);
        ViewUtils.setStubVisibility(this.mEmptyAlbumView, 8);
        clearVirtualAlbumView();
        this.itemView.setEnabled(true);
        super.recycle();
    }

    public void resizeUpFontToLarge(View view) {
        if (getViewType() == 2) {
            FontUtils.resizeUpToLarge(view.getContext(), this.mTitleText, (float) view.getResources().getDimensionPixelSize(R.dimen.album_grid_title_text_size));
            FontUtils.resizeUpToLarge(view.getContext(), this.mCountText, (float) view.getResources().getDimensionPixelSize(R.dimen.album_grid_count_text_size));
        } else if (getViewType() == 1) {
            FontUtils.resizeUpToLarge(view.getContext(), this.mTitleText, (float) view.getResources().getDimensionPixelSize(R.dimen.album_list_title_text_size));
            FontUtils.resizeUpToLarge(view.getContext(), this.mCountText, (float) view.getResources().getDimensionPixelSize(R.dimen.album_list_count_text_size));
        }
    }

    public void restoreThumbnailBorder() {
        View view;
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || (view = this.mFrameView) == null) {
            super.restoreThumbnailBorder();
        } else {
            view.setForeground(this.mForegroundDrawable);
        }
    }

    public void setAlbumSyncView() {
        int i2;
        if (!this.mEnableSyncView) {
            return;
        }
        if (CloudData.isAlbumCloudSync(this.mMediaItem)) {
            inflateAlbumSyncView();
            View view = this.mAlbumSync;
            if (CloudStateCompat.isNewGalleryAvailable()) {
                i2 = R.drawable.gallery_ic_samsung_cloud_album;
            } else {
                i2 = R.drawable.gallery_ic_cloud_album;
            }
            ViewUtils.setBackgroundResource(view, i2);
            ViewUtils.setStubVisibility(this.mAlbumSync, 0);
            return;
        }
        ViewUtils.setStubVisibility(this.mAlbumSync, 8);
    }

    public void setFolderBorder(Drawable drawable) {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            addThumbnailBorder(drawable);
        } else {
            super.setFolderBorder(drawable);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001f, code lost:
        if (r6.mMediaItem.getAlbumID() == r6.mNewLabelAlbumId) goto L_0x003c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNewLabel(int r7) {
        /*
            r6 = this;
            r6.mNewLabelAlbumId = r7
            com.samsung.android.gallery.module.data.MediaItem r7 = r6.mMediaItem
            if (r7 == 0) goto L_0x003f
            boolean r7 = r7.isFolder()
            r0 = 1
            r1 = 0
            if (r7 != 0) goto L_0x0024
            com.samsung.android.gallery.module.data.MediaItem r7 = r6.mMediaItem
            boolean r7 = r7.isMergedAlbum()
            if (r7 == 0) goto L_0x0017
            goto L_0x0024
        L_0x0017:
            com.samsung.android.gallery.module.data.MediaItem r7 = r6.mMediaItem
            int r7 = r7.getAlbumID()
            int r2 = r6.mNewLabelAlbumId
            if (r7 != r2) goto L_0x0022
            goto L_0x003c
        L_0x0022:
            r0 = r1
            goto L_0x003c
        L_0x0024:
            com.samsung.android.gallery.module.data.MediaItem r7 = r6.mMediaItem
            com.samsung.android.gallery.module.data.MediaItem[] r7 = r7.getAlbumsInFolder(r1)
            int r2 = r7.length
            r3 = r1
        L_0x002c:
            if (r3 >= r2) goto L_0x0022
            r4 = r7[r3]
            int r4 = r4.getAlbumID()
            int r5 = r6.mNewLabelAlbumId
            if (r4 != r5) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            int r3 = r3 + 1
            goto L_0x002c
        L_0x003c:
            r6.updateNewLabelVisibility(r0)
        L_0x003f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountViewHolder.setNewLabel(int):void");
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        int i7;
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR && MediaItemUtil.isVirtualAlbum(this.mMediaItem)) {
            Resources resources = this.itemView.getResources();
            if (isGridView()) {
                i7 = R.dimen.album_virtual_view_corner_radius_grid;
            } else {
                i7 = R.dimen.album_virtual_view_corner_radius_list;
            }
            float dimensionPixelSize = (float) resources.getDimensionPixelSize(i7);
            for (View view : this.mVirtualAlbumChildViews) {
                ViewUtils.setForeground(view, getStrokeDrawable(dimensionPixelSize));
                ViewUtils.setViewShape(view, i2, dimensionPixelSize);
            }
        }
        return super.setThumbnailShape(i2, f);
    }

    public void updateBlurInfo(int i2, int i7) {
        boolean z;
        View view;
        super.updateBlurInfo(i2, i7);
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            ImageView imageView = this.mImageView;
            if (imageView instanceof PinchBlurImageView) {
                PinchBlurImageView pinchBlurImageView = (PinchBlurImageView) imageView;
                boolean z3 = false;
                if (getViewType() == 2) {
                    z = true;
                } else {
                    z = false;
                }
                BlurImageInfo.Builder gridType = new BlurImageInfo.Builder().setFolder(isFolder()).setGridType(z);
                if (i2 == i7) {
                    z3 = true;
                }
                BlurImageInfo.Builder skip = gridType.setMaxDepth(z3).setSkip(isSkipBlur());
                if (z) {
                    view = this.mTitleContainer;
                } else {
                    view = this.mImageView;
                }
                pinchBlurImageView.setBlurInfo(skip.setTargetView(view).setUseEmptyCoverBlur(isEmpty()).build());
                pinchBlurImageView.enableBlur(true);
            }
        }
    }

    public void updateNewLabelVisibility(boolean z) {
        int i2;
        int i7;
        if (z) {
            inflateNewLabelView();
            this.mTitleText.post(new e(2, this));
        }
        if (getViewType() == 1) {
            i2 = R.dimen.list_album_title_with_dot_end_margin;
        } else {
            i2 = R.dimen.new_badge_dot_size;
        }
        TextView textView = this.mTitleText;
        int i8 = 0;
        if (z) {
            i7 = textView.getResources().getDimensionPixelSize(i2);
        } else {
            i7 = 0;
        }
        ViewMarginUtils.setEndMargin(textView, i7);
        View view = this.mNewLabel;
        if (!z) {
            i8 = 8;
        }
        ViewUtils.setStubVisibility(view, i8);
    }

    public boolean useExpandCrop() {
        if (getMediaItem() == null || !getMediaItem().isCustomCover()) {
            return true;
        }
        return false;
    }

    private void bindVirtualAlbumChildLayout(View view, Bitmap bitmap) {
        if (view != null) {
            view.setBackground(new BitmapDrawable(getContext().getResources(), bitmap));
        }
    }

    public AlbumTitleCountViewHolder(View view, int i2, boolean z) {
        super(view, i2);
        this.mVirtualAlbumChildViews = new View[3];
        this.mLayoutChangeListener = new g(5, this);
        this.mEnableSyncView = z;
    }
}
