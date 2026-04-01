package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.util.Pair;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StorySharedTransitionHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Info {
        Bitmap mBitmap;
        View mGradientView;
        ImageView mImageView;
        MediaItem mItem;
        View mTitleView;

        public /* synthetic */ Info(ListViewHolder listViewHolder, MediaItem mediaItem, int i2) {
            this(listViewHolder, mediaItem);
        }

        private void setValues(ListViewHolder listViewHolder, Bitmap bitmap, MediaItem mediaItem, ImageView imageView) {
            this.mBitmap = bitmap;
            this.mItem = mediaItem;
            this.mImageView = imageView;
            this.mTitleView = listViewHolder.getDecoView(57);
            this.mGradientView = listViewHolder.getDecoView(32);
            if (this.mTitleView == null) {
                this.mTitleView = listViewHolder.getTitleView();
            }
        }

        private Info(ListViewHolder listViewHolder, MediaItem mediaItem) {
            setValues(listViewHolder, listViewHolder.getBitmap(), mediaItem, listViewHolder.getImage());
        }
    }

    private static void addSharedElementWithFadeInBlur(Blackboard blackboard, Info info) {
        ImageView imageView = info.mImageView;
        if (imageView != null && info.mTitleView != null) {
            ViewUtils.setVisibility(imageView, 0);
            ViewUtils.setVisibility(info.mTitleView, 0);
            TransitionInfo transitionInfo = new TransitionInfo(info.mItem, info.mBitmap, 0);
            SharedTransition.addSharedElement(blackboard, transitionInfo, Pair.create(info.mImageView, getImageTransitionName(info.mItem)), Pair.create(info.mTitleView, getTitleTransitionName(info.mItem)));
            View view = info.mGradientView;
            if (view != null) {
                SharedTransition.addSharedElement(blackboard, view, getGradientTransitionName(info.mItem), transitionInfo);
            }
        }
    }

    public static void addStoryAlbumTransition(Blackboard blackboard, ImageView imageView, TextView textView, MediaItem mediaItem) {
        SharedTransition.addSharedElement(blackboard, new TransitionInfo(mediaItem, ((BitmapDrawable) imageView.getDrawable()).getBitmap(), 0), Pair.create(imageView, getOnDemandViImageTransitionName(mediaItem)), Pair.create(textView, getOnDemandViTitleTransitionName(mediaItem)));
    }

    public static String getGradientTransitionName(MediaItem mediaItem) {
        return "story/gradient/" + mediaItem.getAlbumID();
    }

    public static String getImageTransitionName(MediaItem mediaItem) {
        return "story/image/" + mediaItem.getAlbumID();
    }

    public static String getOnDemandViImageTransitionName(MediaItem mediaItem) {
        return "story/on_demand_vi_image/" + mediaItem.getAlbumID();
    }

    public static String getOnDemandViTitleTransitionName(MediaItem mediaItem) {
        return "story/on_demand_vi_title/" + mediaItem.getAlbumID();
    }

    public static String getTitleTransitionName(MediaItem mediaItem) {
        return "story/title/" + mediaItem.getAlbumID();
    }

    public static void resetTransitionName(ListViewHolder listViewHolder) {
        SharedTransition.setTransitionName(listViewHolder.getImage(), (String) null);
        SharedTransition.setTransitionName(listViewHolder.getDecoView(32), (String) null);
    }

    public static void setTransitionName(ListViewHolder listViewHolder) {
        SharedTransition.setTransitionName(listViewHolder.getImage(), getImageTransitionName(listViewHolder.getMediaItem()));
        if (!PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            SharedTransition.setTransitionName(listViewHolder.getDecoView(32), getGradientTransitionName(listViewHolder.getMediaItem()));
        }
    }

    public static void addStoryAlbumTransition(Blackboard blackboard, ListViewHolder listViewHolder, MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            Info info = new Info(listViewHolder, mediaItem, 0);
            SharedTransition.setTransitionName(listViewHolder.getImage(), (String) null);
            addSharedElementWithFadeInBlur(blackboard, info);
        }
    }
}
