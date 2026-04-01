package com.samsung.android.gallery.app.ui.viewholders;

import U5.c;
import a6.C0419b;
import a8.d;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dbtype.StoryCategoryType;
import com.samsung.android.gallery.module.data.EffectItemBuilder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryImageViewHolder extends PreviewViewHolder {
    protected FrameLayout mItemContainer;
    private View mOptionSaveView;
    private View mOptionShareView;
    private View mOptionViewContainer;
    private View mOptionViewCover;
    private ViewGroup mOptionViewRoot;
    protected ViewStub mOptionViewStub;
    /* access modifiers changed from: private */
    public final ArrayList<PlaybackOption> mPlaybackOptions = new ArrayList<>();
    protected TextView mTagView;
    protected ViewGroup mTagViewContainer;
    protected ViewStub mTagViewStub;
    protected TextView mTimeView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PlaybackPreviewListener extends PreviewViewHolder.PreviewListener {
        public /* synthetic */ PlaybackPreviewListener(StoryImageViewHolder storyImageViewHolder, int i2) {
            this();
        }

        public PlaybackOption getNextPlaybackOption(int i2) {
            return StoryImageViewHolder.this.getPlaybackOption(i2);
        }

        public String getPlaybackOptionsDebug() {
            if (StoryImageViewHolder.this.mPlaybackOptions.size() > 1) {
                return "#" + StoryImageViewHolder.this.mPlaybackOptions.size() + NumericEnum.SEP + StoryImageViewHolder.this.mPlaybackOptions.get(0) + "..." + StoryImageViewHolder.this.mPlaybackOptions.get(StoryImageViewHolder.this.mPlaybackOptions.size() - 1);
            } else if (StoryImageViewHolder.this.mPlaybackOptions.size() != 1) {
                return "#0";
            } else {
                return "#1:" + StoryImageViewHolder.this.mPlaybackOptions.get(0);
            }
        }

        public boolean isPlaybackPreview() {
            return StoryImageViewHolder.this.hasPlaybackRanges();
        }

        private PlaybackPreviewListener() {
            super();
        }
    }

    public StoryImageViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void bindChunkStreetView(MediaItem mediaItem, TextView textView) {
        setTagView(textView, getLimitedMaxTagText(MediaItemStory.getStoryChunkData(mediaItem)), 3);
    }

    private void bindChunkTagView(MediaItem mediaItem, TextView textView) {
        if (textView != null) {
            ThreadUtil.runOnUiThread(new d((Object) this, (Object) mediaItem, (Object) textView, (Object) getTranslatedTagString(mediaItem), 0));
        }
    }

    private void bindTag(MediaItem mediaItem) {
        int storyChunkType = MediaItemStory.getStoryChunkType(mediaItem);
        if (isTagDisplayable(mediaItem, storyChunkType)) {
            inflateTagView();
            if (storyChunkType == 2) {
                SimpleThreadPool.getInstance().execute(new c(28, this, mediaItem));
            } else if (storyChunkType == 3) {
                bindChunkStreetView(mediaItem, this.mTagView);
            } else {
                ViewUtils.setVisibility(this.mTagView, 8);
            }
        } else {
            ViewUtils.setVisibility(this.mTagView, 8);
        }
    }

    private void bindTimeView(MediaItem mediaItem) {
        String str;
        if (this.mTimeView == null || !MediaItemStory.getStoryTimeVisible(mediaItem)) {
            ViewUtils.setVisibility(this.mTimeView, 8);
            return;
        }
        if (MediaItemStory.getStoryCategoryType(mediaItem) == StoryCategoryType.TRIP.ordinal()) {
            str = MediaItemStory.getStoryStreetName(mediaItem);
        } else {
            str = null;
        }
        TextView textView = this.mTimeView;
        if (str == null) {
            str = TimeUtil.getLocalizedTime(mediaItem.getDateTaken());
        }
        ViewUtils.setText(textView, str);
        ViewUtils.setVisibility(this.mTimeView, 0);
    }

    private String getLimitedMaxTagText(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 35) {
            return str;
        }
        return str.substring(0, 32) + "...";
    }

    /* access modifiers changed from: private */
    public PlaybackOption getPlaybackOption(int i2) {
        if (i2 < 0 || i2 >= this.mPlaybackOptions.size()) {
            return null;
        }
        return this.mPlaybackOptions.get(i2);
    }

    private float getSpeed(float f) {
        return 1.0f;
    }

    private String getTranslatedTagString(MediaItem mediaItem) {
        return getLimitedMaxTagText(TranslationManager.getInstance().getTranslatedString("location://search/fileList/Category/Scene", MediaItemStory.getStoryChunkData(mediaItem)));
    }

    /* access modifiers changed from: private */
    public boolean hasPlaybackRanges() {
        if (this.mPlaybackOptions.size() > 0) {
            return true;
        }
        return false;
    }

    private void inflateOptionView() {
        ViewStub viewStub = this.mOptionViewStub;
        if (viewStub != null && this.mOptionViewRoot == null) {
            ViewGroup viewGroup = (ViewGroup) viewStub.inflate();
            this.mOptionViewRoot = viewGroup;
            this.mOptionViewCover = viewGroup.findViewById(R.id.option_view_cover);
            this.mOptionViewContainer = this.mOptionViewRoot.findViewById(R.id.option_view_container);
            this.mOptionShareView = this.mOptionViewRoot.findViewById(R.id.story_card_option_share);
            this.mOptionSaveView = this.mOptionViewRoot.findViewById(R.id.story_card_option_save);
            this.mOptionShareView.setOnClickListener(this);
            this.mOptionSaveView.setOnClickListener(this);
        }
    }

    private void inflateTagView() {
        ViewStub viewStub = this.mTagViewStub;
        if (viewStub != null && this.mTagView == null) {
            ViewGroup viewGroup = (ViewGroup) viewStub.inflate();
            this.mTagViewContainer = viewGroup;
            this.mTagView = (TextView) viewGroup.findViewById(R.id.tag_view);
            this.mTagViewContainer.setOnClickListener(new C0419b(2, this));
        }
    }

    private void initDynamicViewInfo(MediaItem mediaItem) {
        DynamicViewPlayInfo build;
        DynamicViewInfo dynamicViewInfo = MediaItemUtil.getDynamicViewInfo(mediaItem);
        if (dynamicViewInfo != null && (build = new DynamicViewInfo.PlayInfoBuilder(dynamicViewInfo).setFileDuration(mediaItem.getFileDuration()).setPlayType(1).setForList(true).build()) != null && build.getSize() > 0) {
            this.mPlaybackOptions.clear();
            Iterator<PlaybackOption> it = build.getPlayList().iterator();
            while (it.hasNext()) {
                PlaybackOption next = it.next();
                this.mPlaybackOptions.add(new PlaybackOption(next.mStartMs, next.mEndMs, getSpeed(next.mSpeed)));
            }
        }
    }

    private boolean isTagDisplayable(MediaItem mediaItem, int i2) {
        if (!MediaItemStory.getStoryChunkDisplayable(mediaItem)) {
            return false;
        }
        if (i2 == 2 || i2 == 3) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindChunkTagView$2(MediaItem mediaItem, TextView textView, String str) {
        if (MediaItemUtil.equals(mediaItem, this.mMediaItem)) {
            setTagView(textView, str, 2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindTag$0(MediaItem mediaItem) {
        if (!TranslationManager.getInstance().isTranslationMapLoaded()) {
            TranslationManager.getInstance().loadTranslationMap(AppResources.getAppContext());
        }
        bindChunkTagView(mediaItem, this.mTagView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateTagView$1(View view) {
        onItemClickInternal(1001);
    }

    private void printDebugEffectInfo() {
        String effectType = EffectItemBuilder.getEffectType(this.mMediaItem);
        if (this.mMediaItem != null && !TextUtils.isEmpty(effectType)) {
            String str = this.TAG;
            Log.d(str, "effectItem {" + getViewPosition() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + effectType + "}");
        }
    }

    private void setTagView(TextView textView, String str, int i2) {
        int i7;
        int i8;
        if (i2 == 2) {
            i7 = R.drawable.gallery_ic_memery_normal_tag;
        } else {
            i7 = R.drawable.gallery_ic_memory_location_tag;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(getContext().getDrawable(i7), (Drawable) null, (Drawable) null, (Drawable) null);
        textView.setText(str);
        if (!TextUtils.isEmpty(str)) {
            i8 = 0;
        } else {
            i8 = 8;
        }
        ViewUtils.setVisibility(textView, i8);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        bindTag(mediaItem);
        bindTimeView(mediaItem);
        initDynamicViewInfo(mediaItem);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SuggestedEffectOnStory)) {
            printDebugEffectInfo();
        }
    }

    public void bindPreviewView(View view) {
        ((ViewGroup) this.mImageView.getParent()).addView(view);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mItemContainer = (FrameLayout) view.findViewById(R.id.item_container);
        this.mTagViewStub = (ViewStub) view.findViewById(R.id.tag_view_stub);
        this.mTimeView = (TextView) view.findViewById(R.id.time_view);
        this.mOptionViewStub = (ViewStub) view.findViewById(R.id.story_card_option_view_stub);
    }

    public boolean canLooping(int i2) {
        return false;
    }

    public View getDecoView(int i2) {
        if (i2 == 53) {
            inflateOptionView();
            return this.mOptionViewRoot;
        } else if (i2 == 55) {
            inflateOptionView();
            return this.mOptionViewContainer;
        } else if (i2 != 54) {
            return super.getDecoView(i2);
        } else {
            inflateOptionView();
            return this.mOptionViewCover;
        }
    }

    public int getPreviewDuration() {
        if (!hasPlaybackRanges()) {
            return 10000;
        }
        Iterator<PlaybackOption> it = this.mPlaybackOptions.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            PlaybackOption next = it.next();
            i2 = (int) ((((float) (next.mEndMs - next.mStartMs)) / next.mSpeed) + ((float) i2));
        }
        return i2;
    }

    public PreviewViewHolder.PreviewListener getPreviewListener() {
        return new PlaybackPreviewListener(this, 0);
    }

    public int getPreviewThumbnailOffsetMs() {
        return 0;
    }

    public int getSeekTime() {
        if (!hasPlaybackRanges()) {
            return super.getSeekTime();
        }
        PlaybackOption playbackOption = getPlaybackOption(0);
        if (playbackOption != null) {
            return playbackOption.mStartMs;
        }
        return super.getSeekTime();
    }

    public View getViewForActionListener() {
        FrameLayout frameLayout = this.mItemContainer;
        if (frameLayout != null) {
            return frameLayout;
        }
        return this.itemView;
    }

    public void onClick(View view) {
        if (view == this.mOptionShareView) {
            onItemClickInternal(1002);
        } else if (view == this.mOptionSaveView) {
            onItemClickInternal(1003);
        } else {
            super.onClick(view);
        }
    }

    public void recycle() {
        super.recycle();
        ViewUtils.setVisibility(this.mTagView, 8);
        ViewUtils.setVisibility(this.mTimeView, 8);
        this.mPlaybackOptions.clear();
        ViewUtils.setVisibility(this.mOptionViewRoot, 8);
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        if ((i2 & 1024) == 0) {
            return super.updateDecoration(i2, objArr);
        }
        if (objArr != null && objArr.length > 0 && MediaItemStory.getStoryChunkType(this.mMediaItem) == 1) {
            int i7 = 0;
            boolean booleanValue = objArr[0].booleanValue();
            TextView textView = this.mTagView;
            if (!booleanValue) {
                i7 = 8;
            }
            ViewUtils.setVisibility(textView, i7);
        }
        return true;
    }
}
