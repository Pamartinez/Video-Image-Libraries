package com.samsung.android.gallery.widget.smartalbum;

import B8.e;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import c0.C0086a;
import com.samsung.android.gallery.module.crop.SmartCropUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryAppBarManager;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SmartAlbumStoryItem extends SmartAlbumItem {
    /* access modifiers changed from: private */
    public MediaItem mMediaItem;
    private int mPosition = -1;

    public SmartAlbumStoryItem(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    /* renamed from: bindBitmap */
    public void lambda$updateDetails$0(MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap != null) {
            setVisibility(0);
            setImageWithMatrix(bitmap, mediaItem.getOrientation());
            setTitle(StoryAppBarManager.getInstance().getTitle(getContext(), this.mPosition));
            return;
        }
        Log.w("SmartAlbumStoryItem", "bindBitmap {" + this.mPosition + "} null bitmap");
        setVisibility(8);
    }

    /* access modifiers changed from: private */
    public Rect getCropRect(RectF rectF, Drawable drawable, ImageView imageView, int i2) {
        if (SmartCropUtils.isValidRect(rectF)) {
            return SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(rectF).setDrawableSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()).setViewSize((float) imageView.getWidth(), (float) imageView.getHeight()).setRotation(i2).build());
        }
        return getFaceCropRect();
    }

    private Rect getFaceCropRect() {
        RectF rectF;
        ArrayList cropRectRatioList = this.mMediaItem.getCropRectRatioList();
        if (cropRectRatioList == null || cropRectRatioList.size() != 6) {
            return null;
        }
        ImageView imageView = this.mImageView;
        if (((float) imageView.getHeight()) / ((float) imageView.getWidth()) >= 0.75f) {
            rectF = (RectF) cropRectRatioList.get(3);
        } else {
            rectF = (RectF) cropRectRatioList.get(5);
        }
        if (!SmartCropUtils.isValidRect(rectF)) {
            return null;
        }
        return SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(rectF).setDrawableSize(imageView.getDrawable().getIntrinsicWidth(), imageView.getDrawable().getIntrinsicHeight()).setViewSize((float) imageView.getWidth(), (float) imageView.getHeight()).setRotation(this.mMediaItem.getOrientation()).build());
    }

    private boolean isLandscapeLastItem() {
        if (this.mPosition + 1 == 4) {
            return true;
        }
        return false;
    }

    private boolean isUpdatedStoryAppBarItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null && mediaItem2 != null) {
            return true;
        }
        if (mediaItem != null && mediaItem2 == null) {
            return true;
        }
        if (mediaItem == null || mediaItem2 == null || mediaItem.getAlbumID() == mediaItem2.getAlbumID()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDetails$1(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new b(this, mediaItem, bitmap));
    }

    private void setImageWithMatrix(final Bitmap bitmap, final int i2) {
        ViewTreeObserver viewTreeObserver = this.mImageView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    try {
                        SmartAlbumStoryItem.this.mImageView.setImageBitmap(bitmap);
                        RectF stringToRectF = RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(SmartAlbumStoryItem.this.mMediaItem));
                        Drawable drawable = SmartAlbumStoryItem.this.mImageView.getDrawable();
                        if (drawable == null) {
                            return false;
                        }
                        SmartAlbumStoryItem smartAlbumStoryItem = SmartAlbumStoryItem.this;
                        SmartAlbumStoryItem.this.mImageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(SmartAlbumStoryItem.this.mImageView, false).withCropRect(RectUtils.getRotatedRect(smartAlbumStoryItem.getCropRect(stringToRectF, drawable, smartAlbumStoryItem.mImageView, i2), drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), i2)).withOrientation(i2)));
                        SmartAlbumStoryItem.this.mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    } finally {
                        SmartAlbumStoryItem.this.mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                    }
                }
            });
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mTitleView.getLayoutParams();
        layoutParams.topMargin = getContext().getResources().getDimensionPixelOffset(R$dimen.story_appbar_item_title_marginTop);
        this.mTitleView.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.mImageViewFrame.getLayoutParams();
        Resources resources = getContext().getResources();
        int i2 = R$dimen.story_appbar_item_circle_size;
        layoutParams2.width = resources.getDimensionPixelSize(i2);
        layoutParams2.height = getContext().getResources().getDimensionPixelSize(i2);
        this.mImageViewFrame.setLayoutParams(layoutParams2);
        this.mImageView.setScaleType(ImageView.ScaleType.MATRIX);
        ViewUtils.setViewShape(this.mImageView, 0, 0.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2);
        layoutParams3.weight = 1.0f;
        setLayoutParams(layoutParams3);
        setVisibility(8);
    }

    public String getAnalyticsId() {
        return null;
    }

    public int getDrawableId() {
        return -1;
    }

    public int getTitleStringId() {
        return -1;
    }

    public int getType() {
        return 4;
    }

    public void handleOnClick(boolean z) {
        int storyId = StoryAppBarManager.getInstance().getStoryId(this.mPosition);
        if (storyId >= 0) {
            moveTo(new UriBuilder(C0086a.i(storyId, "location://story/albums/fileList/")).appendArg("id", storyId).build());
        }
    }

    public boolean isVisibleInPickMode() {
        return false;
    }

    public void setPosition(int i2) {
        this.mPosition = i2;
    }

    public void updateDetails() {
        boolean z;
        if (ResourceCompat.isLandscape(getRootView()) || !isLandscapeLastItem()) {
            MediaItem mediaItem = (MediaItem) StoryAppBarManager.getInstance().getMediaItem(this.mPosition);
            if (!isUpdatedStoryAppBarItem(this.mMediaItem, mediaItem)) {
                StringBuilder sb2 = new StringBuilder("updateDetails {");
                sb2.append(this.mPosition);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (mediaItem == null) {
                    z = true;
                } else {
                    z = false;
                }
                sb2.append(z);
                sb2.append("} skip");
                Log.d("SmartAlbumStoryItem", sb2.toString());
                return;
            }
            this.mMediaItem = mediaItem;
            if (mediaItem != null) {
                ThumbnailLoader.getInstance().getOrLoad(mediaItem, ThumbKind.MEDIUM_KIND, new e(mediaItem, 1), new a(this, mediaItem));
                return;
            }
            Log.w("SmartAlbumStoryItem", "bindBitmap {" + this.mPosition + "} no item");
            setVisibility(8);
            return;
        }
        setVisibility(8);
    }

    public boolean usingFixedImageAndTitle() {
        return false;
    }
}
