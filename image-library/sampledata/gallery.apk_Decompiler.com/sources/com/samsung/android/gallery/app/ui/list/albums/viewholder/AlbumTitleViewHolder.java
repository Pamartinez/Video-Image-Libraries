package com.samsung.android.gallery.app.ui.list.albums.viewholder;

import A4.L;
import Qb.c;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumTitleViewHolder extends ImageTitleViewHolder {
    private ImageView mAlbumType;
    private Drawable mAlbumTypeIcon;
    private ViewStub mAlbumTypeStub;
    private BorderListener mBorderListener;
    private ViewStub mChildViewContainerStub;
    private ImageView mFolderBackground;
    private View mFolderEmptyView;
    private View mFolderEmptyViewIcon;
    private AlbumFolderImageViewHolder[] mFolderImageViewHolders;
    private ViewStub[] mFolderImageViewStubs;
    private ViewGroup mFolderView;
    private ViewStub mFolderViewStub;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BorderListener {
        Drawable getBorder(boolean z);

        int getDepth();

        boolean isDexMode();
    }

    public AlbumTitleViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void clearFolderView() {
        ViewGroup viewGroup = this.mFolderView;
        if (viewGroup != null) {
            viewGroup.setVisibility(8);
            setFolderBackGroundVisibility(false);
            setFolderEmptyViewVisibility(false);
            for (AlbumFolderImageViewHolder albumFolderImageViewHolder : this.mFolderImageViewHolders) {
                if (albumFolderImageViewHolder != null) {
                    albumFolderImageViewHolder.recycle();
                }
            }
        }
    }

    private View getChildView(int i2) {
        ViewStub[] viewStubArr = this.mFolderImageViewStubs;
        if (viewStubArr != null) {
            return viewStubArr[i2].inflate();
        }
        return this.mFolderView.getChildAt(i2);
    }

    private int getSubFolderPosition() {
        return (getViewPosition() + 1) * 4;
    }

    private void inflateAlbumTypeView() {
        if (ViewUtils.isViewStubInflatable(this.mAlbumTypeStub)) {
            this.mAlbumType = (ImageView) this.mAlbumTypeStub.inflate();
        }
    }

    private void inflateFolderChildViewContainer() {
        if (ViewUtils.isViewStubInflatable(this.mChildViewContainerStub)) {
            this.mFolderView = (ViewGroup) this.mChildViewContainerStub.inflate();
        }
    }

    private void initFolderView() {
        boolean z = false;
        inflateFolderView(false);
        inflateFolderChildViewContainer();
        ViewGroup viewGroup = this.mFolderView;
        if (viewGroup != null) {
            viewGroup.setVisibility(0);
            setFolderBackGroundVisibility(true);
            MediaItem[] itemsInFolder = this.mMediaItem.getItemsInFolder();
            for (int i2 = 0; i2 < 4; i2++) {
                AlbumFolderImageViewHolder[] albumFolderImageViewHolderArr = this.mFolderImageViewHolders;
                if (albumFolderImageViewHolderArr[i2] == null) {
                    albumFolderImageViewHolderArr[i2] = new AlbumFolderImageViewHolder(getChildView(i2), 1, false);
                }
                updateSubViewHolder(itemsInFolder, i2);
            }
            if (itemsInFolder.length == 0) {
                z = true;
            }
            setFolderEmptyViewVisibility(z);
        }
    }

    private boolean needAlbumListFocusedBorderOnDex() {
        BorderListener borderListener = this.mBorderListener;
        if (borderListener == null || borderListener.isDexMode() || this.mBorderListener.getDepth() != 0) {
            return false;
        }
        return true;
    }

    private void setFolderBackGroundImage(MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || mediaItem == null) {
            setImageBitmap(this.mFolderBackground, (Bitmap) null);
        } else if (PreferenceFeatures.OneUi5x.MX_ALBUMS && mediaItem.isEmpty()) {
            setImageBitmap(this.mFolderBackground, ThumbnailLoader.getInstance().getEmptyFolderImage(getContext()));
        }
    }

    private void setFolderBackGroundVisibility(boolean z) {
        boolean z3;
        ImageView imageView = this.mFolderBackground;
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || !z) {
            z3 = false;
        } else {
            z3 = true;
        }
        ViewUtils.setVisibleOrGone(imageView, z3);
    }

    private void setFolderEmptyViewVisibility(boolean z) {
        boolean z3;
        View view = this.mFolderEmptyView;
        boolean z7 = PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR;
        boolean z9 = false;
        if (!z7 || !z) {
            z3 = false;
        } else {
            z3 = true;
        }
        ViewUtils.setVisibleOrGone(view, z3);
        View view2 = this.mFolderEmptyViewIcon;
        if (z7 && z) {
            z9 = true;
        }
        ViewUtils.setVisibleOrGone(view2, z9);
    }

    private void updateAlbumTypeIcon(MediaItem mediaItem) {
        Drawable albumStorageIcon = ResourceUtil.getAlbumStorageIcon(getContext(), mediaItem);
        this.mAlbumTypeIcon = albumStorageIcon;
        setImageDrawable(this.mAlbumType, albumStorageIcon);
        ViewUtils.setVisibleOrGone(this.mAlbumType, hasAlbumTypeIcon());
    }

    private void updateBorderCornerRadius(Drawable drawable) {
        float f;
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            if (needAlbumListFocusedBorderOnDex()) {
                f = getContext().getResources().getDimension(R.dimen.album_view_corner_radius_list_blur);
            } else {
                f = getContext().getResources().getDimension(R.dimen.album_view_corner_radius_grid_blur);
            }
            ViewUtils.setDrawableCornerRadius(drawable, f);
        }
    }

    private void updateSubViewHolder(MediaItem[] mediaItemArr, int i2) {
        AlbumFolderImageViewHolder albumFolderImageViewHolder = this.mFolderImageViewHolders[i2];
        if (i2 < mediaItemArr.length) {
            albumFolderImageViewHolder.getRootView().setVisibility(0);
            albumFolderImageViewHolder.setFakePosition((-getSubFolderPosition()) - i2);
            MediaItem mediaItem = mediaItemArr[i2];
            if (mediaItem.isFolder() && mediaItem.getAlbumsInFolder().length != 0) {
                mediaItem = mediaItem.getAlbumsInFolder()[0];
            }
            albumFolderImageViewHolder.bind(mediaItem);
            albumFolderImageViewHolder.setImageUid(mediaItem.getPath());
            return;
        }
        albumFolderImageViewHolder.getRootView().setVisibility(8);
    }

    public void addThumbnailBorder(Drawable drawable) {
        if (isFolder()) {
            setFolderBorder(drawable);
        } else {
            super.addThumbnailBorder(drawable);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (mediaItem.isFolder()) {
            initFolderView();
            if (applyImageColorFilter() && this.mCheckboxView != null) {
                setColorFilter(getColorFilter(getCheckbox().isChecked()));
            }
            setFolderBackGroundImage(mediaItem);
            return;
        }
        clearFolderView();
        inflateAlbumTypeView();
        ViewUtils.setText(this.mTitleText, mediaItem.getDisplayName());
        updateAlbumTypeIcon(mediaItem);
        setAlbumSyncView();
    }

    public void bindImage(Bitmap bitmap) {
        if (this.mMediaItem == null) {
            return;
        }
        if (bitmap != null && equalBitmap(bitmap)) {
            return;
        }
        if (!PocFeatures.SUPPORT_LOCKED_ALBUM || !MediaItemUtil.containsLockedAlbum(this.mMediaItem)) {
            if (bitmap == null && this.mMediaItem.isEmptyAlbum()) {
                bitmap = ThumbnailLoader.getInstance().getDefaultAlbumImage(false);
            }
            super.bindImage(bitmap);
            return;
        }
        this.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.mImageView.setImageBitmap(ThumbnailLoader.getInstance().getLockedAlbumImage());
        this.mImageView.setBackground((Drawable) null);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mAlbumTypeStub = (ViewStub) view.findViewById(R.id.album_type_stub);
        this.mFolderViewStub = (ViewStub) view.findViewById(R.id.folderViewStub);
    }

    public void enableBorderView(boolean z, Drawable drawable) {
        int i2;
        TextView textView = this.mTitleText;
        if (z) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        textView.setVisibility(i2);
    }

    public View getDecoView(int i2) {
        if (i2 == 21) {
            inflateAlbumTypeView();
            return this.mAlbumType;
        } else if (i2 == 23) {
            return this.mFolderView;
        } else {
            if (i2 == 24) {
                return this.mFolderBackground;
            }
            if (i2 == 62) {
                return this.mFolderEmptyView;
            }
            if (i2 == 63) {
                return this.mFolderEmptyViewIcon;
            }
            return super.getDecoView(i2);
        }
    }

    public int getFocusedBorderDrawableId() {
        if (needAlbumListFocusedBorderOnDex()) {
            return R.drawable.album_list_focused_border_on_dex;
        }
        return R.drawable.album_grid_focused_border_on_dex;
    }

    public ListViewHolder[] getFolderImageHolders() {
        return this.mFolderImageViewHolders;
    }

    public boolean hasAlbumTypeIcon() {
        if (this.mAlbumTypeIcon != null) {
            return true;
        }
        return false;
    }

    public boolean hasImageView() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isFolder()) {
            return true;
        }
        return false;
    }

    public void inflateFolderView(boolean z) {
        ViewStub viewStub = this.mFolderViewStub;
        if (viewStub != null && this.mFolderView == null) {
            View inflate = viewStub.inflate();
            this.mFolderBackground = (ImageView) inflate.findViewById(R.id.folder_background);
            this.mFolderImageViewHolders = new AlbumFolderImageViewHolder[4];
            View findViewById = inflate.findViewById(R.id.folderView);
            if (ViewUtils.isViewStub(findViewById)) {
                this.mChildViewContainerStub = (ViewStub) findViewById;
            } else {
                this.mFolderView = (ViewGroup) findViewById;
                this.mFolderImageViewStubs = new ViewStub[4];
                for (int i2 = 0; i2 < 4; i2++) {
                    this.mFolderImageViewStubs[i2] = (ViewStub) this.mFolderView.getChildAt(i2);
                }
            }
            if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
                ViewStub viewStub2 = (ViewStub) inflate.findViewById(R.id.folder_empty_background);
                if (ViewUtils.isViewStubInflatable(viewStub2)) {
                    View inflate2 = viewStub2.inflate();
                    this.mFolderEmptyView = inflate2;
                    this.mFolderEmptyViewIcon = inflate2.findViewById(R.id.empty_folder_icon);
                    setFolderEmptyViewVisibility(false);
                }
            }
        }
        if (z) {
            inflateFolderChildViewContainer();
            setFolderBackGroundVisibility(true);
            setFolderEmptyViewVisibility(true);
            ViewUtils.setVisibility(this.mFolderView, 0);
            for (int i7 = 0; i7 < 4; i7++) {
                AlbumFolderImageViewHolder[] albumFolderImageViewHolderArr = this.mFolderImageViewHolders;
                AlbumFolderImageViewHolder albumFolderImageViewHolder = albumFolderImageViewHolderArr[i7];
                if (albumFolderImageViewHolder == null) {
                    albumFolderImageViewHolderArr[i7] = new AlbumFolderImageViewHolder(getChildView(i7), 1, false);
                } else {
                    albumFolderImageViewHolder.getRootView().setVisibility(0);
                }
            }
        }
    }

    public boolean isAlbumSyncEnable() {
        return true;
    }

    public void recycle() {
        super.recycle();
        clearFolderView();
        this.mImageView.setForeground((Drawable) null);
        setFolderBackGroundImage((MediaItem) null);
        setImageDrawable(this.mAlbumType, (Drawable) null);
        ViewUtils.setVisibility(this.mAlbumType, 8);
        ViewUtils.setWidth(this.mAlbumType, -2);
        this.mAlbumTypeIcon = null;
    }

    public void restoreThumbnailBorder() {
        Drawable drawable;
        if (isFolder()) {
            BorderListener borderListener = this.mBorderListener;
            if (borderListener != null) {
                drawable = borderListener.getBorder(true);
            } else {
                drawable = null;
            }
            setFolderBorder(drawable);
            return;
        }
        super.restoreThumbnailBorder();
    }

    public void setBorderListener(BorderListener borderListener) {
        this.mBorderListener = borderListener;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        AlbumFolderImageViewHolder[] albumFolderImageViewHolderArr;
        MediaItem mediaItem = this.mMediaItem;
        if (!(mediaItem == null || !mediaItem.isFolder() || this.mFolderView == null || (albumFolderImageViewHolderArr = this.mFolderImageViewHolders) == null)) {
            for (AlbumFolderImageViewHolder ofNullable : albumFolderImageViewHolderArr) {
                Optional.ofNullable(ofNullable).ifPresent(new c(2, colorFilter));
            }
        }
        super.setColorFilter(colorFilter);
    }

    public void setFocusedFilterOnAdvancedMouseEvent(boolean z) {
        BorderListener borderListener;
        AlbumFolderImageViewHolder[] albumFolderImageViewHolderArr;
        if (!isFolder()) {
            super.setFocusedFilterOnAdvancedMouseEvent(z);
            if (!z && (borderListener = this.mBorderListener) != null) {
                addThumbnailBorder(borderListener.getBorder(false));
            }
        } else if (this.mFolderView != null && (albumFolderImageViewHolderArr = this.mFolderImageViewHolders) != null) {
            for (AlbumFolderImageViewHolder ofNullable : albumFolderImageViewHolderArr) {
                Optional.ofNullable(ofNullable).ifPresent(new L(z, 18));
            }
            Drawable drawable = getContext().getDrawable(getFocusedBorderDrawableId());
            updateBorderCornerRadius(drawable);
            if (!z) {
                BorderListener borderListener2 = this.mBorderListener;
                if (borderListener2 != null) {
                    drawable = borderListener2.getBorder(true);
                } else {
                    drawable = null;
                }
            }
            setFolderBorder(drawable);
        }
    }

    public void setFolderBorder(Drawable drawable) {
        ViewUtils.setForeground(this.mFolderBackground, drawable);
    }

    public void setImageUid(Object obj) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && !mediaItem.isFolder()) {
            super.setImageUid(obj);
        }
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR && PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            ViewUtils.setViewShape(this.mFolderBackground, i2, f);
        }
        return super.setThumbnailShape(i2, f);
    }

    public void updateBlurInfo(int i2, int i7) {
        int length;
        boolean z;
        boolean z3;
        boolean z7;
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR && isFolder() && (length = this.mMediaItem.getItemsInFolder().length) != 0) {
            if (getViewType() == 2) {
                z = true;
            } else {
                z = false;
            }
            int min = Math.min(length, 4);
            for (int i8 = 0; i8 < min; i8++) {
                AlbumFolderImageViewHolder albumFolderImageViewHolder = this.mFolderImageViewHolders[i8];
                if (i8 >= min - 2) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (i2 == i7) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                albumFolderImageViewHolder.enableSubAlbumBlur(z3, z, z7, (View) this.mTitleText.getParent());
            }
        }
    }

    public void setTransitionName(MediaItem mediaItem) {
    }
}
