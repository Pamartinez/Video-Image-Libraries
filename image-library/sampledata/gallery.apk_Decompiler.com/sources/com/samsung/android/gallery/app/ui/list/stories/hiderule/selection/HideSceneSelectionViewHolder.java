package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionViewHolder extends ImageTitleViewHolder {
    boolean STORY_ONE_UI_85 = PreferenceFeatures.OneUi8x.STORY_ONE_UI_85;
    private View mTitleArea;

    public HideSceneSelectionViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void applyRenderEffect() {
        if (Build.VERSION.SDK_INT < 31) {
            return;
        }
        if (!PreferenceFeatures.OneUi8x.STORY_ONE_UI_85 || !ViewUtils.isVisible(this.mTitleText)) {
            getImage().setRenderEffect((RenderEffect) null);
        } else {
            CoverGradientBlur.apply(getImage());
        }
    }

    private void setThumbnailShape() {
        if (this.STORY_ONE_UI_85) {
            setThumbnailShape(1, (float) this.itemView.getResources().getDimensionPixelOffset(R.dimen.search_item_card_people_thumbnail_spacing_expansion_85));
            return;
        }
        setThumbnailShape(0, 0.0f);
        addThumbnailBorder(getContext().getDrawable(R.drawable.search_people_thumbnail_border));
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        setThumbnailShape();
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        applyRenderEffect();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTitleArea = view.findViewById(R.id.title_area);
    }

    public String getTitleText(MediaItem mediaItem) {
        String str;
        if (mediaItem != null) {
            str = mediaItem.getTitle();
        } else {
            str = null;
        }
        if (!this.STORY_ONE_UI_85 && TextUtils.isEmpty(str)) {
            return " ";
        }
        return str;
    }
}
