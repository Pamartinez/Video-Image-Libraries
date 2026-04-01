package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.DurationHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class StoriesCatItemBaseViewHolder extends StoriesViewHolder {
    protected final String mDataKey;
    Drawable[] mFavoriteDrawable;
    ImageView mFavoriteView;
    protected FrameLayout mThumbnailContainer;

    public StoriesCatItemBaseViewHolder(View view, int i2, String str) {
        super(view, i2);
        this.mDataKey = str;
    }

    private void applyGradientBlur() {
        if (supportGradientBlur()) {
            CoverGradientBlur.apply(this.mThumbnailContainer);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onItemClickInternal(6);
    }

    private void setTextSize(TextView textView, int i2) {
        if (textView != null) {
            textView.setTextSize(0, (float) i2);
        }
    }

    public void bind(MediaItem mediaItem) {
        boolean z;
        super.bind(mediaItem);
        if (MediaItemStory.getStoryFavorite(getMediaItem()) > 0) {
            z = true;
        } else {
            z = false;
        }
        setFavoriteInfo(z);
        setTextSize(this.mTitleText, ItemProperty.getTitleSize(getContext(), this.mDataKey));
        setTextSize(this.mDurationText, ItemProperty.getSubTitleSize(getContext(), this.mDataKey));
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        applyGradientBlur();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mThumbnailContainer = (FrameLayout) view.findViewById(R.id.thumbnail_container);
        this.mFavoriteDrawable = new Drawable[]{getContext().getDrawable(R.drawable.story_favorite_off), getContext().getDrawable(R.drawable.story_favorite_on)};
        ImageView imageView = (ImageView) view.findViewById(R.id.icon_favorite);
        this.mFavoriteView = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(new a(this, 1));
        }
    }

    public int getPreviewDuration() {
        int i2;
        if (MediaItemStory.isLiveEffectItem(getMediaItem())) {
            i2 = 7000;
        } else {
            i2 = super.getPreviewDuration();
        }
        return DurationHelper.getItemDuration(getMediaItem(), new int[]{i2, i2});
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [boolean] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFavoriteInfo(boolean r3) {
        /*
            r2 = this;
            android.widget.ImageView r0 = r2.mFavoriteView
            if (r0 == 0) goto L_0x0024
            android.graphics.drawable.Drawable[] r1 = r2.mFavoriteDrawable
            if (r1 == 0) goto L_0x0024
            r1 = r1[r3]
            r0.setImageDrawable(r1)
            if (r3 == 0) goto L_0x0013
            r3 = 2131888161(0x7f120821, float:1.941095E38)
            goto L_0x0016
        L_0x0013:
            r3 = 2131886164(0x7f120054, float:1.94069E38)
        L_0x0016:
            java.lang.String r3 = com.samsung.android.gallery.support.utils.AppResources.getString(r3)
            android.widget.ImageView r0 = r2.mFavoriteView
            r0.setContentDescription(r3)
            android.widget.ImageView r2 = r2.mFavoriteView
            r2.setTooltipText(r3)
        L_0x0024:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemBaseViewHolder.setFavoriteInfo(boolean):void");
    }

    public boolean supportGradientBlur() {
        return PreferenceFeatures.OneUi8x.STORY_COVER_BLUR;
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        if ((i2 & 64) == 0) {
            return super.updateDecoration(i2, objArr);
        }
        if (objArr == null || objArr.length <= 0) {
            return true;
        }
        setFavoriteInfo(objArr[0].booleanValue());
        return true;
    }
}
