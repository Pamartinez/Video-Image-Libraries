package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import B4.c;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.view.animation.LinearInterpolator;
import com.samsung.android.gallery.app.controller.story.SaveStoryLiveEffectCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;
import o6.t;
import o6.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryLiveEffectDelegate extends Delegate {
    private View mLiveEffectDownloadButton;
    private View mLiveEffectDownloadView;

    public StoryLiveEffectDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    private MediaItem getMediaItem() {
        PreviewViewHolder currentViewHolder = this.mEventHandler.getCurrentViewHolder();
        if (currentViewHolder != null) {
            return currentViewHolder.getMediaItem();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVisible$0() {
        lambda$setVisible$1(true);
    }

    /* access modifiers changed from: private */
    public void onDownloadClicked(View view) {
        MediaItem mediaItem = getMediaItem();
        if (mediaItem != null) {
            new SaveStoryLiveEffectCmd().execute(this.mView.getEventContext(), mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public void setEnable(Object... objArr) {
        int i2 = 0;
        boolean booleanValue = objArr[0].booleanValue();
        View view = this.mLiveEffectDownloadButton;
        if (!booleanValue) {
            i2 = 4;
        }
        ViewUtils.setVisibility(view, i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: setVisibility */
    public void lambda$setVisible$1(boolean z) {
        int i2;
        View view = this.mLiveEffectDownloadView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    public void addListenEvent() {
        addEvent(Event.ENABLE_BOTTOM_DECO, new x(this, 0));
        addEvent(Event.ENABLE_LIVE_EFFECT_DOWNLOAD, new x(this, 1));
    }

    public void initView(View view) {
        View inflate = ((ViewStub) view.findViewById(R.id.story_live_effect_download_stub)).inflate();
        this.mLiveEffectDownloadView = inflate;
        View findViewById = inflate.findViewById(R.id.story_live_effect_download_icon);
        this.mLiveEffectDownloadButton = findViewById;
        findViewById.setOnClickListener(new C0496a(7, this));
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        ViewMarginUtils.setTopMargin(this.mLiveEffectDownloadView, -WindowUtils.getSystemInsetsBottom(windowInsets));
    }

    public void setVisible(Object... objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        float f = 1.0f;
        if (!objArr[1].booleanValue() || !booleanValue) {
            View view = this.mLiveEffectDownloadView;
            if (!booleanValue) {
                f = 0.0f;
            }
            view.setAlpha(f);
            lambda$setVisible$1(booleanValue);
            return;
        }
        this.mLiveEffectDownloadView.animate().alpha(1.0f).setDuration(90).setInterpolator(new LinearInterpolator()).withStartAction(new t(1, this)).withEndAction(new c((Object) this, booleanValue, 25)).start();
    }
}
