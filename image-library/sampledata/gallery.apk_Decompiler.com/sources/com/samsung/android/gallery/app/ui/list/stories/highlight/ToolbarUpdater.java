package com.samsung.android.gallery.app.ui.list.stories.highlight;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.CenteredImageSpan;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ToolbarUpdater {
    private CenteredImageSpan mBgmIcon;
    private View mToolbarParent;
    public final IStoryHighlightView mView;

    public ToolbarUpdater(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
    }

    private CenteredImageSpan getBgmIcon() {
        Context context = this.mView.getContext();
        if (this.mBgmIcon == null && context != null) {
            Drawable drawable = context.getDrawable(R.drawable.ic_gallery_ic_detail_bgm);
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.story_highlight_bgm_icon_size);
            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            this.mBgmIcon = new CenteredImageSpan(drawable);
        }
        return this.mBgmIcon;
    }

    private void updateWindowInset() {
        int i2;
        View view = this.mToolbarParent;
        if (view != null) {
            if (!this.mView.isInMultiWindowMode()) {
                i2 = WindowUtils.getSystemInsetsTop(this.mToolbarParent.getRootWindowInsets());
            } else {
                i2 = 0;
            }
            ViewMarginUtils.setTopPadding(view, i2);
        }
    }

    public void handleEvent(Event event, Object... objArr) {
        if (event == Event.VIEW_PAGER_SELECTED) {
            updateTitle(objArr);
        } else if (event == Event.UPDATE_BGM_NAME) {
            updateSubTitle(objArr[0]);
        }
    }

    public void handleResolutionChange() {
        updateWindowInset();
    }

    public void initView() {
        this.mToolbarParent = (View) this.mView.getToolbar().getParent();
        updateWindowInset();
    }

    public void onMultiWindowModeChanged() {
        updateWindowInset();
    }

    public void updateSubTitle(BgmExtraInfo bgmExtraInfo) {
        String str;
        CharSequence charSequence = null;
        if (bgmExtraInfo != null) {
            str = bgmExtraInfo.bgmName;
        } else {
            str = null;
        }
        if (!this.mView.isDestroyed() && this.mView.getToolbar() != null) {
            if (TextUtils.isEmpty(str) || !this.mView.getOptions().supportBgm()) {
                if (this.mView.getOptions().supportBgm()) {
                    charSequence = " ";
                }
                this.mView.getToolbar().setSubtitle(charSequence);
                return;
            }
            SpannableString spannableString = new SpannableString(C0212a.l(" ", str));
            spannableString.setSpan(getBgmIcon(), 0, 1, 33);
            this.mView.getToolbar().setSubtitle((CharSequence) spannableString);
        }
    }

    public void updateTitle(Object... objArr) {
        int i2;
        MediaItem mediaItem;
        long j2;
        if (objArr != null) {
            i2 = objArr[0].intValue();
        } else {
            i2 = -1;
        }
        if (i2 != -1 && !this.mView.isDestroyed() && this.mView.getToolbar() != null) {
            MediaData mediaData = this.mView.getMediaData();
            if (mediaData != null) {
                mediaItem = mediaData.readCache(i2);
            } else {
                mediaItem = null;
            }
            if (mediaItem == null) {
                PreviewViewHolder currentViewHolder = this.mView.getEventHandler().getCurrentViewHolder();
                if (currentViewHolder != null) {
                    mediaItem = currentViewHolder.getMediaItem();
                } else {
                    mediaItem = this.mView.getHeaderItem();
                }
            }
            if (mediaItem != null) {
                if (mediaItem.getDateLocal() <= 0) {
                    j2 = mediaItem.getDateTaken();
                } else if (Features.isEnabled(Features.SUPPORT_LOCAL_TIME)) {
                    j2 = TimeUtil.toUtcTimeInMillis(mediaItem.getDateLocal());
                } else {
                    j2 = mediaItem.getDateLocal();
                }
                if (!PreferenceFeatures.OneUi8x.SUPPORT_STORY_POLYGON_POI || !SettingPreference.LocationAuth.isEnabled()) {
                    this.mView.getToolbar().setTitle(TimeUtil.toLocalDate(j2, "YYMD"));
                    return;
                }
                String polygonPoi = MediaItemStory.getPolygonPoi(mediaItem);
                GalleryToolbar toolbar = this.mView.getToolbar();
                if (polygonPoi == null) {
                    polygonPoi = TimeUtil.toLocalDate(j2, "YYMD");
                }
                toolbar.setTitle(polygonPoi);
            }
        }
    }
}
