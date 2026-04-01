package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import A4.O;
import Ab.a;
import B6.c;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderPeople;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHeaderPeopleViewHolder extends RecyclerView.ViewHolder {
    ImageView mImageView;
    StoryHeaderPeople.SearchRelatedPeopleListener mListener;
    MediaItem mMediaItem;
    TextView mTitleView;
    View mView;

    public StoryHeaderPeopleViewHolder(View view) {
        super(view);
        this.mView = view;
        this.mTitleView = (TextView) view.findViewById(R.id.story_pictures_face_title);
        this.mImageView = (ImageView) view.findViewById(R.id.story_pictures_face_image);
    }

    private void bindFaceImage(Bitmap bitmap, ImageView imageView) {
        ThreadUtil.postOnUiThread(new c(imageView, bitmap, 0));
    }

    private void bindFaceThumbnail() {
        Object obj;
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        MediaItem mediaItem = this.mMediaItem;
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        if (!TextUtils.isEmpty(mediaItem.getSubCategory())) {
            obj = this.mMediaItem.getSubCategory();
        } else {
            obj = this.mMediaItem;
        }
        Objects.requireNonNull(obj);
        instance.getOrLoad(mediaItem, thumbKind, new O(11, obj), new O(12, this));
    }

    private void bindPersonName(int i2, boolean z) {
        String str = CreatureData.of(this.mMediaItem).creatureName;
        if (!TextUtils.isEmpty(str)) {
            this.mTitleView.setText(str);
            this.mTitleView.setVisibility(0);
        } else {
            this.mTitleView.setText("");
            this.mTitleView.setVisibility(8);
        }
        this.mImageView.setEnabled(z);
        this.mImageView.setOnClickListener(new a(5, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFaceThumbnail$0(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        bindFaceImage(bitmap, this.mImageView);
    }

    /* access modifiers changed from: private */
    public void relatedPeopleThumbClicked(View view) {
        StoryHeaderPeople.SearchRelatedPeopleListener searchRelatedPeopleListener = this.mListener;
        if (searchRelatedPeopleListener != null) {
            ((b) searchRelatedPeopleListener).f2561a.showSearchPeopleResult(this.mMediaItem);
        }
    }

    public void bind(MediaItem mediaItem, int i2, StoryHeaderPeople.SearchRelatedPeopleListener searchRelatedPeopleListener, boolean z) {
        int i7;
        this.mMediaItem = mediaItem;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mView.getLayoutParams();
        if (i2 != 0) {
            i7 = this.mView.getResources().getDimensionPixelOffset(R.dimen.story_pictures_header_item_people_thumb_gap);
        } else {
            i7 = 0;
        }
        marginLayoutParams.setMarginStart(i7);
        this.mView.setLayoutParams(marginLayoutParams);
        this.mListener = searchRelatedPeopleListener;
        if (this.mMediaItem != null) {
            bindPersonName(i2, z);
            bindFaceThumbnail();
        }
    }

    public void recycle() {
        this.mTitleView.setText((CharSequence) null);
        this.mImageView.setOnClickListener((View.OnClickListener) null);
        this.mImageView.setImageBitmap((Bitmap) null);
        this.mListener = null;
        this.mMediaItem = null;
    }
}
