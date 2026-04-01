package com.samsung.android.gallery.widget.videoview.mediaplayer;

import B4.c;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.media.InstantSlowMoPlayLogger;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.SystemUi;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantSlowMoPlayDelegate {
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private final Comparator<String> mComparator = new Comparator<String>() {
        int MIN_EXECUTION = 250;

        public int compare(String str, String str2) {
            int compareTo;
            String[] split = str.split("_");
            String[] split2 = str2.split("_");
            long parseLong = Long.parseLong(split[2]) - Long.parseLong(split[1]);
            Long valueOf = Long.valueOf(parseLong);
            long parseLong2 = Long.parseLong(split2[2]) - Long.parseLong(split2[1]);
            Long valueOf2 = Long.valueOf(parseLong2);
            int i2 = this.MIN_EXECUTION;
            if (parseLong < ((long) i2) || parseLong2 < ((long) i2)) {
                compareTo = valueOf.compareTo(valueOf2);
            } else {
                compareTo = Integer.valueOf(Integer.parseInt(split[0])).compareTo(Integer.valueOf(Integer.parseInt(split2[0])));
            }
            return -compareTo;
        }
    };
    private final IDelegateListener mDelegateListener;
    private ArrayList<String> mExecutionSectionList = new ArrayList<>();
    private boolean mLongPressed;
    private final ArrayList<MediaPlayerListener> mMediaPlayerListener = new ArrayList<>();
    private final MpViewThread mMpViewThread = new MpViewThread();
    private InstantSlowMoPlayLogger mPlayLogger = InstantSlowMoPlayLogger.getInstance();
    private long mSlowMoEndPos;
    private long mSlowMoStartPos;
    private float mStartX = -1.0f;
    private final IMediaPlayerInnerView mView;

    public InstantSlowMoPlayDelegate(IMediaPlayerInnerView iMediaPlayerInnerView, IDelegateListener iDelegateListener) {
        this.mView = iMediaPlayerInnerView;
        this.mDelegateListener = iDelegateListener;
    }

    private void addCandidateExecutedSection() {
        int size = this.mExecutionSectionList.size();
        this.mExecutionSectionList.add(size + "_" + this.mSlowMoStartPos + "_" + this.mSlowMoEndPos);
        this.mExecutionSectionList.sort(this.mComparator);
        MediaItem mediaItem = this.mDelegateListener.getMediaPlayerDelegate().getMediaItem();
        if (mediaItem != null) {
            if (!TextUtils.isEmpty(this.mExecutionSectionList.get(0))) {
                String[] split = this.mExecutionSectionList.get(0).split("_");
                VideoPropData.of(mediaItem).instantSlowMoExecutedSection = new long[]{Long.parseLong(split[1]), Long.parseLong(split[2])};
            }
            VideoPropData.of(mediaItem).instantSlowMoLastExecutedSection = new long[]{this.mSlowMoStartPos, this.mSlowMoEndPos};
            long mediaId = mediaItem.getMediaId();
            this.mPlayLogger.setExecutedNumber(mediaId, this.mExecutionSectionList.size());
            this.mPlayLogger.setExecutedDuration(mediaId, this.mSlowMoEndPos - this.mSlowMoStartPos);
            this.mPlayLogger.postLogExecuted(mediaId);
            Log.d(this.TAG, "latest executed section = " + (this.mSlowMoEndPos - this.mSlowMoStartPos), this.mSlowMoStartPos + "-" + this.mSlowMoEndPos, "candidate:" + this.mExecutionSectionList.toString(), Integer.valueOf(mediaItem.hashCode()));
        }
    }

    private int checkInstantSlowMoPlayErrorType() {
        if (SystemUi.isInMultiWindowMode(Utils.getActivity(this.mView.getView()))) {
            return InstantSlowMoPlayLogger.ErrorType.MULTI_WINDOW.toInt();
        }
        if (FoldUtils.isFlipCoverScreen(Utils.getActivity(this.mView.getView()))) {
            return InstantSlowMoPlayLogger.ErrorType.Z_FLIP_COVER.toInt();
        }
        if (!this.mView.isPlaying()) {
            return InstantSlowMoPlayLogger.ErrorType.ETC.toInt();
        }
        if (!PreferenceFeatures.OneUi6x.SUPPORT_VIDEO_SPEED_CONTROLLER || this.mDelegateListener.getMediaPlayerDelegate().getSpeed() == 1.0f) {
            return InstantSlowMoUtils.checkInstantSlowMoPlayable(this.mDelegateListener.getMediaPlayerDelegate().getMediaItem(), true);
        }
        return InstantSlowMoPlayLogger.ErrorType.PLAYBACK_SPEED_CHANGED.toInt();
    }

    private String getFailedLog(int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        MediaItem mediaItem = this.mDelegateListener.getMediaPlayerDelegate().getMediaItem();
        if (mediaItem == null) {
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(InstantSlowMoPlayLogger.ErrorType.find(i2).toString());
        String str6 = "";
        if (mediaItem.isMotionPhoto()) {
            str = "|m";
        } else {
            str = str6;
        }
        sb2.append(str);
        if (mediaItem.isHdr10Video()) {
            str2 = "|v+";
        } else {
            str2 = str6;
        }
        sb2.append(str2);
        if (MediaItemUtil.isSuperSlowMotion(mediaItem)) {
            str3 = "|ssm";
        } else {
            str3 = str6;
        }
        sb2.append(str3);
        if (!this.mView.isPlaying()) {
            str4 = "|pause";
        } else {
            str4 = str6;
        }
        sb2.append(str4);
        if (SystemUi.isInMultiWindowMode(Utils.getActivity(this.mView.getView()))) {
            str5 = "|MW";
        } else {
            str5 = str6;
        }
        sb2.append(str5);
        if (FoldUtils.isFlipCoverScreen(Utils.getActivity(this.mView.getView()))) {
            str6 = "|FlipCover";
        }
        sb2.append(str6);
        sb2.append("|" + FileUtils.getExtension(mediaItem.getPath()));
        return sb2.toString();
    }

    private long getMediaItemId() {
        MediaItem mediaItem = this.mDelegateListener.getMediaPlayerDelegate().getMediaItem();
        if (mediaItem != null) {
            return mediaItem.getMediaId();
        }
        return -1;
    }

    private boolean isInstantSlowMoPlayableMediaItem() {
        return InstantSlowMoUtils.supportInstantSlowMoPlay(this.mDelegateListener.getMediaPlayerDelegate().getMediaItem(), true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setExecutionSection$0(boolean z) {
        if (z) {
            this.mSlowMoStartPos = (long) this.mDelegateListener.getMediaPlayerDelegate().getCurrentPosition();
            return;
        }
        this.mSlowMoEndPos = (long) this.mDelegateListener.getMediaPlayerDelegate().getCurrentPosition();
        addCandidateExecutedSection();
    }

    private void setExecutionSection(boolean z) {
        this.mMpViewThread.run(new c((Object) this, z, 23));
    }

    private void setInstantSlowMoPlayEnabled(boolean z, MotionEvent motionEvent) {
        setExecutionSection(z);
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onInstantSlowMoPlay(z, motionEvent);
        }
        StringCompat stringCompat = this.TAG;
        Log.majorEvent(stringCompat, "InstantSlowMo" + Logger.v(Boolean.valueOf(z)));
    }

    private void showErrorToast(int i2) {
        int i7;
        if (InstantSlowMoPlayLogger.ErrorType.outOfSpecError(i2)) {
            i7 = R$string.cannot_use_instant_slow_mo_on_this_type_of_video;
        } else if (i2 == InstantSlowMoPlayLogger.ErrorType.PLAYBACK_SPEED_CHANGED.toInt()) {
            i7 = R$string.cannot_use_instant_slow_mo_because_video_speed_has_been_changed;
        } else {
            i7 = -1;
        }
        if (i7 != -1) {
            Utils.showToast(this.mView.getContext(), i7);
        }
    }

    public void addMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        if (!this.mMediaPlayerListener.contains(mediaPlayerListener)) {
            this.mMediaPlayerListener.add(mediaPlayerListener);
        }
    }

    public void clearInstantSlowMoExecutedSection() {
        this.mExecutionSectionList.clear();
        long mediaItemId = getMediaItemId();
        this.mPlayLogger.postLogExecutedNumber(mediaItemId);
        this.mPlayLogger.clear(mediaItemId);
    }

    public boolean onLongPress(MotionEvent motionEvent) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO) {
            return false;
        }
        int checkInstantSlowMoPlayErrorType = checkInstantSlowMoPlayErrorType();
        if (checkInstantSlowMoPlayErrorType == InstantSlowMoPlayLogger.ErrorType.NONE.toInt()) {
            this.mLongPressed = true;
            setInstantSlowMoPlayEnabled(true, motionEvent);
            return false;
        }
        MediaItem mediaItem = this.mDelegateListener.getMediaPlayerDelegate().getMediaItem();
        if (mediaItem != null) {
            this.mPlayLogger.setErrorType(mediaItem.getMediaId(), InstantSlowMoPlayLogger.ErrorType.find(checkInstantSlowMoPlayErrorType));
            this.mPlayLogger.postLogExecutedFailed(mediaItem);
        }
        showErrorToast(checkInstantSlowMoPlayErrorType);
        StringCompat stringCompat = this.TAG;
        Log.w(stringCompat, "onLongPress : not support instant slow-mo play " + getFailedLog(checkInstantSlowMoPlayErrorType));
        return false;
    }

    public boolean onTouch(MotionEvent motionEvent) {
        int action;
        if (!PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO || SystemUi.isInMultiWindowMode(Utils.getActivity(this.mView.getView())) || (((action = motionEvent.getAction()) != 1 && action != 3) || !this.mLongPressed || !isInstantSlowMoPlayableMediaItem())) {
            return false;
        }
        PreferenceCache.InstantSlowMoGuide.setBoolean(true);
        this.mLongPressed = false;
        setInstantSlowMoPlayEnabled(false, motionEvent);
        return true;
    }

    public void removeMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        this.mMediaPlayerListener.remove(mediaPlayerListener);
        clearInstantSlowMoExecutedSection();
    }
}
