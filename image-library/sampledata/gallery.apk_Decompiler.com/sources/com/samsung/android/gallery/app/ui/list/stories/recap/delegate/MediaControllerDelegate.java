package com.samsung.android.gallery.app.ui.list.stories.recap.delegate;

import H6.d;
import H6.e;
import H6.f;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaControllerDelegate extends Delegate {
    private static final int[] DURATION_FORMAT = {R.string.details_ms, R.string.details_hms};
    private ImageView mAudioIcon;
    private View mController;
    private int mCurrentTime = -1;
    private TextView mDurationText;
    private boolean mIsAudioMute = false;
    private boolean mIsPlaying;
    private ImageView mPlayIcon;
    private int mTotalTime = -1;

    public MediaControllerDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    private int getPlayAudioIconResId(boolean z) {
        if (z) {
            return R.drawable.btn_sound_off_t;
        }
        return R.drawable.btn_sound_on;
    }

    /* access modifiers changed from: private */
    public void initializeAudioIcon(Object... objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        boolean booleanValue2 = objArr[1].booleanValue();
        this.mIsAudioMute = this.mView.getOptions().isAudioDefaultMuted();
        if (booleanValue) {
            updateAudioIcon(booleanValue2);
        } else if (this.mAudioIcon != null) {
            Log.d(this.TAG, "no audio");
            this.mAudioIcon.setImageResource(getPlayAudioIconResId(true));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPause$1() {
        updatePlayPauseIcon(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPlay$0() {
        updatePlayPauseIcon(true);
    }

    /* access modifiers changed from: private */
    public void onAudioButtonClicked(View view) {
        if (this.mAudioIcon != null) {
            IStoryHighlightView iStoryHighlightView = this.mView;
            if (iStoryHighlightView.setInputBlock(this.TAG + "_onAudioMuteClicked", 500)) {
                this.mIsAudioMute = !this.mIsAudioMute;
                String str = this.TAG;
                Log.d(str, "audio update: " + this.mIsAudioMute);
                GalleryPreference.getInstance().saveState(PreferenceName.STORY_HIGHLIGHT_AUDIO_MUTED, this.mIsAudioMute);
                updateAudioIcon(this.mIsAudioMute);
                this.mEventHandler.postEvent(Event.USER_AUDIO_MUTE, Boolean.valueOf(this.mIsAudioMute));
            }
        }
    }

    /* access modifiers changed from: private */
    public void onControllerClicked(View view) {
        String str;
        String str2 = this.TAG;
        if (this.mIsPlaying) {
            str = "pause";
        } else {
            str = "play";
        }
        Log.d(str2, str);
        this.mEventHandler.postEvent(Event.PLAYER_KEEP_PAUSE, Boolean.valueOf(this.mIsPlaying));
    }

    /* access modifiers changed from: private */
    public void onPlayTimeUpdated(Object... objArr) {
        this.mCurrentTime = Math.max(objArr[0].intValue(), 0);
        int intValue = objArr[1].intValue();
        this.mTotalTime = intValue;
        updateDurationText(this.mCurrentTime, intValue);
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeek(Object... objArr) {
        updateDurationText(objArr[0].intValue(), this.mTotalTime);
    }

    /* access modifiers changed from: private */
    public void onVideoPause(Object... objArr) {
        ThreadUtil.postOnUiThread(new e(this, 1));
    }

    /* access modifiers changed from: private */
    public void onVideoPlay(Object... objArr) {
        ThreadUtil.postOnUiThread(new e(this, 0));
    }

    private void updateAudioIcon(boolean z) {
        int i2;
        ImageView imageView = this.mAudioIcon;
        if (imageView != null) {
            imageView.setImageResource(getPlayAudioIconResId(z));
            ImageView imageView2 = this.mAudioIcon;
            if (z) {
                i2 = R.string.unmute;
            } else {
                i2 = R.string.mute;
            }
            imageView2.setContentDescription(AppResources.getString(i2));
        }
    }

    private void updateDurationText(int i2, int i7) {
        TextView textView = this.mDurationText;
        if (textView != null) {
            Context context = textView.getContext();
            TextView textView2 = this.mDurationText;
            int[] iArr = DURATION_FORMAT;
            String formatDuration = TimeUtil.formatDuration(context, i2, iArr);
            String formatDuration2 = TimeUtil.formatDuration(context, i7, iArr);
            textView2.setText(formatDuration + " / " + formatDuration2);
            ViewGroup.LayoutParams layoutParams = this.mDurationText.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = ViewUtils.getMeasureTextViewWidth(this.mDurationText) + 3;
                this.mDurationText.setLayoutParams(layoutParams);
            }
        }
    }

    private void updatePlayPauseIcon(boolean z) {
        int i2;
        int i7;
        ImageView imageView = this.mPlayIcon;
        if (imageView != null && this.mController != null) {
            this.mIsPlaying = z;
            if (z) {
                i2 = R.drawable.gallery_ic_detail_pause;
            } else {
                i2 = R.drawable.gallery_ic_detail_play;
            }
            imageView.setImageResource(i2);
            View view = this.mController;
            if (z) {
                i7 = R.string.pause;
            } else {
                i7 = R.string.play;
            }
            view.setContentDescription(AppResources.getString(i7));
        }
    }

    public void addListenEvent() {
        addEvent(Event.VIDEO_PLAY, new d(this, 0));
        addEvent(Event.VIDEO_PAUSE, new d(this, 1));
        addEvent(Event.VIDEO_PLAY_TIME_UPDATED, new d(this, 2));
        addEvent(Event.REQUEST_VIDEO_SEEK, new d(this, 3));
        addEvent(Event.HAS_AUDIO, new d(this, 4));
    }

    public void initView(View view) {
        this.mController = view.findViewById(R.id.video_controller);
        this.mPlayIcon = (ImageView) view.findViewById(R.id.play_switch_icon);
        this.mAudioIcon = (ImageView) view.findViewById(R.id.play_audio_icon);
        this.mDurationText = (TextView) view.findViewById(R.id.play_duration);
        this.mController.setOnClickListener(new f(this, 0));
        this.mAudioIcon.setOnClickListener(new f(this, 1));
    }
}
