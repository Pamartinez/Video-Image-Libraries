package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.os.Handler;
import android.os.Looper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgm.BgmPlayerCallback;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgm.BgmPlayerV2;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.Objects;
import k6.b;
import o6.c;
import o6.d;
import o6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmPlayerDelegate extends Delegate implements BgmPlayerCallback {
    private BgmExtraInfo mBgmExtraInfo;
    private final BgmUriService mBgmUriService;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsTransitionEnd;
    private boolean mKeepPause;
    private Runnable mPendingCheckAudioPermission;
    private final BgmPlayerV2 mPlayer;
    private boolean mThemeInitialize;
    private boolean mUserMute;
    private int mUserPausedPlayTime = -1;

    public BgmPlayerDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        BgmUriService bgmUriService = new BgmUriService();
        this.mBgmUriService = bgmUriService;
        BgmPlayerV2 bgmPlayerV2 = new BgmPlayerV2(bgmUriService);
        this.mPlayer = bgmPlayerV2;
        bgmPlayerV2.setCallback(this);
        if (iStoryHighlightView.getOptions().isAudioDefaultMuted()) {
            this.mUserMute = true;
            changeVolume(0.0f);
        }
    }

    private void changeVolume(float f) {
        this.mPlayer.changeVolume(f);
    }

    /* access modifiers changed from: private */
    /* renamed from: checkAudioPermission */
    public void lambda$handleEvent$7(BgmExtraInfo bgmExtraInfo) {
        this.mEventHandler.postEvent(Event.CHECK_AUDIO_PERMISSION, new d(this, bgmExtraInfo, 1), 0);
    }

    private int getFadeVolumeDuration() {
        return ((int) Math.abs(this.mPlayer.getCurrentVolume() * 10.0f)) * 50;
    }

    private int getSeekPos() {
        int i2;
        Integer num = (Integer) this.mEventHandler.requestData(DataRequest.CURRENT_PLAY_TIME);
        if (num == null || num.intValue() == this.mUserPausedPlayTime) {
            i2 = -1;
        } else {
            i2 = num.intValue();
        }
        Log.d(this.TAG, "getSeekPos {p=" + this.mUserPausedPlayTime + ",c=" + num + ",seek=" + i2 + "}");
        this.mUserPausedPlayTime = -1;
        return i2;
    }

    private float getTargetVolume() {
        float f;
        float f5 = 1.0f;
        if (StoryHelper.isVideoItem(this.mEventHandler.getCurrentMediaItem())) {
            f = 0.5f;
        } else {
            f = 1.0f;
        }
        if (this.mKeepPause || this.mUserMute) {
            f5 = 0.0f;
        } else if (this.mEventHandler.isFilterViewVisible() || this.mEventHandler.isShowingRelatedView()) {
            f5 = 0.7f;
        } else if (!this.mEventHandler.isBottomSheetHidden()) {
            f5 = 0.5f;
        }
        return Math.min(f5, f);
    }

    private int getTotalDuration() {
        return ((Integer) this.mEventHandler.requestData(DataRequest.TOTAL_PLAY_TIME, 10000)).intValue();
    }

    private boolean isDataReady() {
        if (this.mView.getMediaData() == null || this.mView.getMediaData().getRealCount() <= 0) {
            return false;
        }
        return true;
    }

    private boolean isPlayableState() {
        if (!isDataReady() || this.mKeepPause || !isResumed() || this.mUserMute || !this.mThemeInitialize || this.mBgmExtraInfo == null || ((Boolean) this.mView.requestData(DataRequest.FRAGMENT_REQ_ONGOING_ENTRY_ANIM, new Object[0])).booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$0(Object[] objArr) {
        changeVolume(getTargetVolume());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        changeVolume(getTargetVolume());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$2(Object[] objArr) {
        changeVolume(1.0f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$3(Object[] objArr) {
        changeVolume(getTargetVolume());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$4(DataRequest dataRequest, Object[] objArr) {
        return this.mBgmUriService;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$5(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(isPlayableState());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$6(DataRequest dataRequest, Object[] objArr) {
        boolean z;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryBgmWithSEAContentProvider) || !this.mBgmUriService.isAllDownloaded()) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAudioPermission$8(BgmExtraInfo bgmExtraInfo) {
        onBgmChanged(bgmExtraInfo, true);
    }

    private void onBgmChanged(BgmExtraInfo bgmExtraInfo, boolean z) {
        if (bgmExtraInfo == null) {
            Log.w(this.TAG, "onBgmChanged - no bgm info");
        } else if (bgmExtraInfo.equals(this.mBgmExtraInfo)) {
            Log.w((CharSequence) this.TAG, "onBgmChanged - same bgm", bgmExtraInfo);
        } else {
            this.mBgmExtraInfo = bgmExtraInfo;
            Log.d(this.TAG, "onBgmChanged", bgmExtraInfo);
            setBgm(bgmExtraInfo.bgmName, bgmExtraInfo.path, !bgmExtraInfo.isMyMusic);
            this.mEventHandler.lambda$postEvent$0(Event.UPDATE_BGM_NAME, bgmExtraInfo);
            if (z) {
                requestPlay();
                this.mEventHandler.postEvent(Event.UPDATE_LAST_USED_BGM, bgmExtraInfo.bgmName);
            }
        }
    }

    private void printPlayableDebugString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8 = this.TAG;
        if (isDataReady()) {
            str = "D";
        } else {
            str = "d";
        }
        String str9 = str;
        if (isResumed()) {
            str2 = "R";
        } else {
            str2 = "r";
        }
        String str10 = str2;
        if (this.mBgmExtraInfo != null) {
            str3 = "B";
        } else {
            str3 = "b";
        }
        String str11 = str3;
        if (this.mKeepPause) {
            str4 = "P";
        } else {
            str4 = "p";
        }
        String str12 = str4;
        if (this.mUserMute) {
            str5 = "M";
        } else {
            str5 = "m";
        }
        String str13 = str5;
        if (this.mThemeInitialize) {
            str6 = "T";
        } else {
            str6 = "t";
        }
        String str14 = str6;
        if (((Boolean) this.mView.requestData(DataRequest.FRAGMENT_REQ_ONGOING_ENTRY_ANIM, new Object[0])).booleanValue()) {
            str7 = "A";
        } else {
            str7 = "a";
        }
        Log.d(str8, "ignore play", str9, str10, str11, str12, str13, str14, str7);
    }

    private void requestPlay() {
        if (!isPlayableState()) {
            printPlayableDebugString();
        } else if (this.mPlayer.isPaused()) {
            this.mPlayer.resume(getSeekPos());
        } else if (!this.mPlayer.isPlaying()) {
            int totalDuration = getTotalDuration();
            if (totalDuration > 0) {
                startPlay(totalDuration);
                Log.d(this.TAG, "requestPlay#start", Integer.valueOf(totalDuration));
                return;
            }
            Log.d(this.TAG, "requestPlay#ignore", Integer.valueOf(totalDuration));
        } else {
            Log.d(this.TAG, "requestPlay#playing");
        }
    }

    private boolean requiredCheckingAudioPermission(BgmExtraInfo bgmExtraInfo) {
        if (!SdkConfig.atLeast(SdkConfig.GED.T) || bgmExtraInfo == null || !bgmExtraInfo.isMyMusic) {
            return false;
        }
        return true;
    }

    private void setBgm(String str, String str2, boolean z) {
        this.mPlayer.setBgm(str, str2, z);
    }

    private void startPlay(int i2) {
        this.mPlayer.setDuration(i2);
        this.mPlayer.start();
    }

    public void addListenEvent() {
        addEvent(Event.PLAYER_KEEP_PAUSE);
        addEvent(Event.BGM_RESUME);
        addEvent(Event.BGM_PAUSE);
        addEvent(Event.USER_AUDIO_MUTE);
        addEvent(Event.ON_THEME_CHANGED);
        addEvent(Event.ON_BGM_CHANGED);
        addEvent(Event.ENTER_TRANSITION_END);
        addEvent(Event.ON_THEME_INITIALIZED);
        addEvent(Event.BOTTOM_SHEET_STATE_CHANGED, new c(this, 0));
        addEvent(Event.RELATED_VIEW_STATE_CHANGED, new c(this, 1));
        addEvent(Event.CHANGE_STORY, new c(this, 2));
        addEvent(Event.VIEW_PAGER_SELECTED, new c(this, 3));
        addEvent(Event.DEFAULT_ENTRY_ANIM_END);
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.REQ_BGM_URI_SERVICE, new e(this, 0));
        addRequestProvider(DataRequest.IS_BGM_PLAYABLE, new e(this, 1));
        addRequestProvider(DataRequest.IS_ALL_BGM_DOWNLOADED, new e(this, 2));
    }

    public void handleEvent(Event event, Object... objArr) {
        Event event2;
        if (event == Event.PLAYER_KEEP_PAUSE) {
            boolean booleanValue = objArr[0].booleanValue();
            this.mKeepPause = booleanValue;
            if (booleanValue) {
                event2 = Event.BGM_PAUSE;
            } else {
                event2 = Event.BGM_RESUME;
            }
            handleEvent(event2, new Object[0]);
            Log.v(this.TAG, "handleEvent#PLAYER_KEEP_PAUSE", Boolean.valueOf(this.mKeepPause));
            return;
        }
        Event event3 = Event.BGM_RESUME;
        if (event == event3 || event == Event.DEFAULT_ENTRY_ANIM_END) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
            changeVolume(getTargetVolume());
            requestPlay();
            return;
        }
        Event event4 = Event.BGM_PAUSE;
        if (event == event4) {
            this.mUserPausedPlayTime = ((Integer) this.mEventHandler.requestData(DataRequest.CURRENT_PLAY_TIME, 0)).intValue();
            changeVolume(0.0f);
            Handler handler = this.mHandler;
            BgmPlayerV2 bgmPlayerV2 = this.mPlayer;
            Objects.requireNonNull(bgmPlayerV2);
            handler.postDelayed(new b(26, bgmPlayerV2), (long) getFadeVolumeDuration());
        } else if (event == Event.USER_AUDIO_MUTE) {
            boolean booleanValue2 = objArr[0].booleanValue();
            this.mUserMute = booleanValue2;
            if (booleanValue2) {
                event3 = event4;
            }
            handleEvent(event3, new Object[0]);
            Log.d(this.TAG, "handleEvent#AUDIO_MUTE", Boolean.valueOf(this.mUserMute));
        } else if (event == Event.ON_THEME_CHANGED) {
            Log.d(this.TAG, "handleEvent#CHANGE_STORY_THEME");
            onBgmChanged(this.mEventHandler.getBgmExtraInfo(), true);
        } else if (event == Event.ON_BGM_CHANGED) {
            Log.d(this.TAG, "handleEvent#REPLACE_BGM");
            BgmExtraInfo bgmExtraInfo = objArr[0];
            onBgmChanged(bgmExtraInfo, true);
            if (!bgmExtraInfo.isMyMusic) {
                this.mBgmUriService.addBgmInfo(bgmExtraInfo.bgmName, bgmExtraInfo.uris, "replaceBgm");
            }
        } else if (event == Event.ENTER_TRANSITION_END) {
            Runnable runnable = this.mPendingCheckAudioPermission;
            if (runnable != null) {
                runnable.run();
                this.mPendingCheckAudioPermission = null;
            }
            this.mIsTransitionEnd = true;
        } else if (event == Event.ON_THEME_INITIALIZED) {
            this.mThemeInitialize = true;
            BgmExtraInfo bgmExtraInfo2 = this.mEventHandler.getBgmExtraInfo();
            if (!requiredCheckingAudioPermission(bgmExtraInfo2)) {
                onBgmChanged(this.mEventHandler.getBgmExtraInfo(), true);
            } else if (this.mIsTransitionEnd) {
                lambda$handleEvent$7(bgmExtraInfo2);
            } else {
                this.mPendingCheckAudioPermission = new d(this, bgmExtraInfo2, 0);
            }
        }
    }

    public void onAudioFocus(boolean z) {
        this.mEventHandler.postEvent(Event.AUDIO_FOCUS, Boolean.valueOf(z));
        if (z) {
            requestPlay();
        } else {
            this.mPlayer.pause();
        }
    }

    public void onDataChangedOnUi() {
        requestPlay();
    }

    public void onDestroy() {
        this.mPlayer.destroy();
        this.mBgmUriService.destroy();
    }

    public void onDestroyView() {
        this.mPlayer.stop();
    }

    public void onFail(int i2) {
        Log.d(this.TAG, "onFail", Integer.valueOf(i2));
        if (i2 == -2000) {
            this.mEventHandler.postEvent(Event.RECOVER_STORY_BGM, new Object[0]);
        }
    }

    public void onPause() {
        super.onPause();
        this.mPlayer.pause();
    }

    public void onResume() {
        super.onResume();
        requestPlay();
    }
}
