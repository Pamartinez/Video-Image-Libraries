package com.samsung.android.gallery.module.dynamicview;

import B8.d;
import B8.f;
import N2.j;
import android.media.PlaybackParams;
import c0.C0086a;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DynamicViewPlayInfo {
    public static final boolean SUPPORT = Features.isEnabled(Features.SUPPORT_DYNAMIC_VIEW);
    private static final String TAG = "DynamicViewPlayInfo";
    private final String HASH_TAG = ("DV@" + Integer.toHexString(hashCode()));
    private final int mDuration;
    private final PlayInfo mPlayInfo;
    private final ArrayList<PlaybackOption> mPlayList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CallBack {
        boolean loop() {
            return true;
        }

        void onPlayComplete();
    }

    public DynamicViewPlayInfo(PlayInfo playInfo) {
        ArrayList<PlaybackOption> arrayList = new ArrayList<>();
        this.mPlayList = arrayList;
        this.mPlayInfo = playInfo;
        arrayList.addAll(playInfo.getPlayList());
        this.mDuration = calculateDuration();
        Log.v(TAG, toDebugString());
    }

    private int calculateDuration() {
        Iterator<PlaybackOption> it = this.mPlayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().mRelativeDurationMs;
        }
        return i2;
    }

    private int findBgmSeekPos(int i2) {
        Iterator<PlaybackOption> it = this.mPlayList.iterator();
        float f = 0.0f;
        while (true) {
            if (it.hasNext()) {
                PlaybackOption next = it.next();
                int i7 = next.mStartMs;
                if (i7 <= i2 && next.mEndMs >= i2) {
                    f += ((float) (i2 - i7)) / next.mSpeed;
                    break;
                }
                f += ((float) (next.mEndMs - i7)) / next.mSpeed;
            } else {
                break;
            }
        }
        String str = TAG;
        Log.d(str, "findBgmSeekPos=" + f + ",startMs=" + i2);
        return (int) f;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPlayBack$0(AtomicInteger atomicInteger, MediaPlayerCompat mediaPlayerCompat, CallBack callBack, MediaPlayerCompat mediaPlayerCompat2) {
        if (nextPlayback(atomicInteger.incrementAndGet())) {
            setPlaybackInfo(mediaPlayerCompat, atomicInteger, -1);
        } else if (playbackCompleted(atomicInteger.get()) && callBack != null) {
            if (callBack.loop()) {
                Log.d(TAG, "loop");
                atomicInteger.set(0);
                mediaPlayerCompat.setBgmPlaybackRange(0, getDuration());
                setPlaybackInfo(mediaPlayerCompat, atomicInteger, this.mPlayList.get(0).mStartMs);
                return;
            }
            callBack.onPlayComplete();
            String str = TAG;
            Log.d(str, "completed=" + this.mPlayList.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + atomicInteger.get());
        }
    }

    private boolean nextPlayback(int i2) {
        if (i2 < this.mPlayList.size()) {
            return true;
        }
        return false;
    }

    private boolean playbackCompleted(int i2) {
        if (i2 >= this.mPlayList.size()) {
            return true;
        }
        return false;
    }

    private void setPlaybackInfo(MediaPlayerCompat mediaPlayerCompat, AtomicInteger atomicInteger, int i2) {
        int i7;
        try {
            int i8 = atomicInteger.get();
            PlaybackOption playbackOption = this.mPlayList.get(i8);
            int max = Math.max(i2, playbackOption.mStartMs);
            if (i8 > 0) {
                i7 = this.mPlayList.get(i8 - 1).mEndMs;
            } else {
                i7 = 0;
            }
            if (i7 == 0 || i7 != playbackOption.mStartMs) {
                mediaPlayerCompat.seekToAdaptively(max);
            }
            mediaPlayerCompat.setPlaybackRange(max, playbackOption.mEndMs);
            PlaybackParams playbackParam = mediaPlayerCompat.getPlaybackParam();
            playbackParam.setSpeed(playbackOption.mSpeed);
            mediaPlayerCompat.setPlaybackParam(playbackParam);
            if (i2 >= 0) {
                mediaPlayerCompat.seekToBgm(findBgmSeekPos(max));
            }
            mediaPlayerCompat.start();
            String str = TAG;
            Log.d(str, "play : " + playbackOption + ",type=" + this.mPlayInfo.getType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i8);
        } catch (Exception e) {
            j.s(e, new StringBuilder("fail to play e="), TAG);
        }
    }

    public int findCurrentPosition(int i2) {
        for (int i7 = 0; i7 < this.mPlayList.size(); i7++) {
            PlaybackOption playbackOption = this.mPlayList.get(i7);
            if (i2 >= playbackOption.mStartMs && i2 < playbackOption.mEndMs) {
                return i7;
            }
        }
        return 0;
    }

    public AnalyticsDetail getAnalyticsDetail() {
        return this.mPlayInfo.getAnalyticsDetail();
    }

    public int getConvertedRecordingMode() {
        int type = this.mPlayInfo.getType();
        if (type == 0) {
            return 93;
        }
        if (type == 1) {
            return 94;
        }
        if (type == 2) {
            return 96;
        }
        if (type != 3) {
            return -1;
        }
        return 95;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public ArrayList<MediaPlayback> getDynamicViewConfig() {
        ArrayList<MediaPlayback> arrayList = new ArrayList<>();
        getPlayList().forEach(new d(arrayList, 29));
        return arrayList;
    }

    public ArrayList<PlaybackOption> getPlayList() {
        return this.mPlayList;
    }

    public int getShortClipIndex() {
        return this.mPlayInfo.getShortClipIndex();
    }

    public int getSize() {
        return this.mPlayList.size();
    }

    public int getType() {
        return this.mPlayInfo.getType();
    }

    public String getTypeString() {
        return this.mPlayInfo.getTypeString();
    }

    public long getVideoThumbStartTime() {
        int i2;
        if (this.mPlayList.isEmpty()) {
            i2 = 1;
        } else {
            i2 = this.mPlayList.get(0).mStartMs;
        }
        return ((long) Math.max(i2, 1)) * 1000;
    }

    public void initPlayBack(MediaPlayerCompat mediaPlayerCompat, CallBack callBack, int i2) {
        int i7;
        if (this.mPlayList.size() == 0) {
            Log.e(TAG, "play fail due to no play list");
            return;
        }
        if (i2 != -1) {
            i7 = findCurrentPosition(i2);
        } else {
            i7 = 0;
        }
        AtomicInteger atomicInteger = new AtomicInteger(i7);
        String str = TAG;
        Log.d(str, "initPlayBack index=" + i7 + ",pos=" + i2);
        setPlaybackInfo(mediaPlayerCompat, atomicInteger, i2);
        MediaPlayerCompat mediaPlayerCompat2 = mediaPlayerCompat;
        mediaPlayerCompat2.setPlaybackCompleteListener(new f((Object) this, (Object) atomicInteger, (Object) mediaPlayerCompat2, (Object) callBack, 5));
    }

    public boolean supportBgm() {
        return this.mPlayInfo.supportBgm();
    }

    public String toDebugString() {
        return this + " " + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mPlayList.iterator());
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.HASH_TAG);
        sb2.append("{T=");
        sb2.append(this.mPlayInfo.getType());
        sb2.append(",P=");
        C0086a.A(sb2, this.mPlayList, ",D=");
        return C0086a.l(sb2, this.mDuration, "}");
    }
}
