package androidx.media3.common;

import android.net.Uri;
import android.util.Pair;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Timeline {
    public static final Timeline EMPTY = new Timeline() {
        public int getIndexOfPeriod(Object obj) {
            return -1;
        }

        public Period getPeriod(int i2, Period period, boolean z) {
            throw new IndexOutOfBoundsException();
        }

        public int getPeriodCount() {
            return 0;
        }

        public Object getUidOfPeriod(int i2) {
            throw new IndexOutOfBoundsException();
        }

        public Window getWindow(int i2, Window window, long j2) {
            throw new IndexOutOfBoundsException();
        }

        public int getWindowCount() {
            return 0;
        }
    };
    private static final String FIELD_PERIODS = Util.intToStringMaxRadix(1);
    private static final String FIELD_SHUFFLED_WINDOW_INDICES = Util.intToStringMaxRadix(2);
    private static final String FIELD_WINDOWS = Util.intToStringMaxRadix(0);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Period {
        private static final String FIELD_AD_PLAYBACK_STATE = Util.intToStringMaxRadix(4);
        private static final String FIELD_DURATION_US = Util.intToStringMaxRadix(1);
        private static final String FIELD_PLACEHOLDER = Util.intToStringMaxRadix(3);
        private static final String FIELD_POSITION_IN_WINDOW_US = Util.intToStringMaxRadix(2);
        private static final String FIELD_WINDOW_INDEX = Util.intToStringMaxRadix(0);
        public AdPlaybackState adPlaybackState = AdPlaybackState.NONE;
        public long durationUs;
        public Object id;
        public boolean isPlaceholder;
        public long positionInWindowUs;
        public Object uid;
        public int windowIndex;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Period.class.equals(obj.getClass())) {
                Period period = (Period) obj;
                if (Objects.equals(this.id, period.id) && Objects.equals(this.uid, period.uid) && this.windowIndex == period.windowIndex && this.durationUs == period.durationUs && this.positionInWindowUs == period.positionInWindowUs && this.isPlaceholder == period.isPlaceholder && Objects.equals(this.adPlaybackState, period.adPlaybackState)) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int getAdCountInAdGroup(int i2) {
            return this.adPlaybackState.getAdGroup(i2).count;
        }

        public long getAdDurationUs(int i2, int i7) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i2);
            if (adGroup.count != -1) {
                return adGroup.durationsUs[i7];
            }
            return -9223372036854775807L;
        }

        public int getAdGroupCount() {
            return this.adPlaybackState.adGroupCount;
        }

        public int getAdGroupIndexAfterPositionUs(long j2) {
            return this.adPlaybackState.getAdGroupIndexAfterPositionUs(j2, this.durationUs);
        }

        public int getAdGroupIndexForPositionUs(long j2) {
            return this.adPlaybackState.getAdGroupIndexForPositionUs(j2, this.durationUs);
        }

        public long getAdGroupTimeUs(int i2) {
            return this.adPlaybackState.getAdGroup(i2).timeUs;
        }

        public long getAdResumePositionUs() {
            return this.adPlaybackState.adResumePositionUs;
        }

        public int getAdState(int i2, int i7) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i2);
            if (adGroup.count != -1) {
                return adGroup.states[i7];
            }
            return 0;
        }

        public long getContentResumeOffsetUs(int i2) {
            return this.adPlaybackState.getAdGroup(i2).contentResumeOffsetUs;
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public int getFirstAdIndexToPlay(int i2) {
            return this.adPlaybackState.getAdGroup(i2).getFirstAdIndexToPlay();
        }

        public int getNextAdIndexToPlay(int i2, int i7) {
            return this.adPlaybackState.getAdGroup(i2).getNextAdIndexToPlay(i7);
        }

        public long getPositionInWindowMs() {
            return Util.usToMs(this.positionInWindowUs);
        }

        public long getPositionInWindowUs() {
            return this.positionInWindowUs;
        }

        public int getRemovedAdGroupCount() {
            return this.adPlaybackState.removedAdGroupCount;
        }

        public boolean hasPlayedAdGroup(int i2) {
            return !this.adPlaybackState.getAdGroup(i2).hasUnplayedAds();
        }

        public int hashCode() {
            int i2;
            Object obj = this.id;
            int i7 = 0;
            if (obj == null) {
                i2 = 0;
            } else {
                i2 = obj.hashCode();
            }
            int i8 = (217 + i2) * 31;
            Object obj2 = this.uid;
            if (obj2 != null) {
                i7 = obj2.hashCode();
            }
            long j2 = this.durationUs;
            long j3 = this.positionInWindowUs;
            return this.adPlaybackState.hashCode() + ((((((((((i8 + i7) * 31) + this.windowIndex) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31);
        }

        public boolean isLivePostrollPlaceholder(int i2) {
            if (i2 != getAdGroupCount() - 1 || !this.adPlaybackState.isLivePostrollPlaceholder(i2)) {
                return false;
            }
            return true;
        }

        public boolean isServerSideInsertedAdGroup(int i2) {
            return this.adPlaybackState.getAdGroup(i2).isServerSideInserted;
        }

        public Period set(Object obj, Object obj2, int i2, long j2, long j3) {
            return set(obj, obj2, i2, j2, j3, AdPlaybackState.NONE, false);
        }

        public Period set(Object obj, Object obj2, int i2, long j2, long j3, AdPlaybackState adPlaybackState2, boolean z) {
            this.id = obj;
            this.uid = obj2;
            this.windowIndex = i2;
            this.durationUs = j2;
            this.positionInWindowUs = j3;
            this.adPlaybackState = adPlaybackState2;
            this.isPlaceholder = z;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Window {
        private static final Object FAKE_WINDOW_UID = new Object();
        private static final String FIELD_DEFAULT_POSITION_US = Util.intToStringMaxRadix(9);
        private static final String FIELD_DURATION_US = Util.intToStringMaxRadix(10);
        private static final String FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS = Util.intToStringMaxRadix(4);
        private static final String FIELD_FIRST_PERIOD_INDEX = Util.intToStringMaxRadix(11);
        private static final String FIELD_IS_DYNAMIC = Util.intToStringMaxRadix(6);
        private static final String FIELD_IS_PLACEHOLDER = Util.intToStringMaxRadix(8);
        private static final String FIELD_IS_SEEKABLE = Util.intToStringMaxRadix(5);
        private static final String FIELD_LAST_PERIOD_INDEX = Util.intToStringMaxRadix(12);
        private static final String FIELD_LIVE_CONFIGURATION = Util.intToStringMaxRadix(7);
        private static final String FIELD_MEDIA_ITEM = Util.intToStringMaxRadix(1);
        private static final String FIELD_POSITION_IN_FIRST_PERIOD_US = Util.intToStringMaxRadix(13);
        private static final String FIELD_PRESENTATION_START_TIME_MS = Util.intToStringMaxRadix(2);
        private static final String FIELD_WINDOW_START_TIME_MS = Util.intToStringMaxRadix(3);
        private static final MediaItem PLACEHOLDER_MEDIA_ITEM = new MediaItem.Builder().setMediaId("androidx.media3.common.Timeline").setUri(Uri.EMPTY).build();
        public static final Object SINGLE_WINDOW_UID = new Object();
        public long defaultPositionUs;
        public long durationUs;
        public long elapsedRealtimeEpochOffsetMs;
        public int firstPeriodIndex;
        public boolean isDynamic;
        public boolean isPlaceholder;
        public boolean isSeekable;
        public int lastPeriodIndex;
        public MediaItem.LiveConfiguration liveConfiguration;
        public Object manifest;
        public MediaItem mediaItem = PLACEHOLDER_MEDIA_ITEM;
        public long positionInFirstPeriodUs;
        public long presentationStartTimeMs;
        @Deprecated
        public Object tag;
        public Object uid = SINGLE_WINDOW_UID;
        public long windowStartTimeMs;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Window.class.equals(obj.getClass())) {
                Window window = (Window) obj;
                if (Objects.equals(this.uid, window.uid) && Objects.equals(this.mediaItem, window.mediaItem) && Objects.equals(this.manifest, window.manifest) && Objects.equals(this.liveConfiguration, window.liveConfiguration) && this.presentationStartTimeMs == window.presentationStartTimeMs && this.windowStartTimeMs == window.windowStartTimeMs && this.elapsedRealtimeEpochOffsetMs == window.elapsedRealtimeEpochOffsetMs && this.isSeekable == window.isSeekable && this.isDynamic == window.isDynamic && this.isPlaceholder == window.isPlaceholder && this.defaultPositionUs == window.defaultPositionUs && this.durationUs == window.durationUs && this.firstPeriodIndex == window.firstPeriodIndex && this.lastPeriodIndex == window.lastPeriodIndex && this.positionInFirstPeriodUs == window.positionInFirstPeriodUs) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public long getCurrentUnixTimeMs() {
            return Util.getNowUnixTimeMs(this.elapsedRealtimeEpochOffsetMs);
        }

        public long getDefaultPositionMs() {
            return Util.usToMs(this.defaultPositionUs);
        }

        public long getDefaultPositionUs() {
            return this.defaultPositionUs;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getPositionInFirstPeriodUs() {
            return this.positionInFirstPeriodUs;
        }

        public int hashCode() {
            int i2;
            int hashCode = (this.mediaItem.hashCode() + ((this.uid.hashCode() + 217) * 31)) * 31;
            Object obj = this.manifest;
            int i7 = 0;
            if (obj == null) {
                i2 = 0;
            } else {
                i2 = obj.hashCode();
            }
            int i8 = (hashCode + i2) * 31;
            MediaItem.LiveConfiguration liveConfiguration2 = this.liveConfiguration;
            if (liveConfiguration2 != null) {
                i7 = liveConfiguration2.hashCode();
            }
            long j2 = this.presentationStartTimeMs;
            long j3 = this.windowStartTimeMs;
            long j8 = this.elapsedRealtimeEpochOffsetMs;
            long j10 = this.defaultPositionUs;
            long j11 = this.durationUs;
            long j12 = this.positionInFirstPeriodUs;
            return ((((((((((((((((((((((i8 + i7) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j8 ^ (j8 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31) + (this.isPlaceholder ? 1 : 0)) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31) + ((int) (j11 ^ (j11 >>> 32)))) * 31) + this.firstPeriodIndex) * 31) + this.lastPeriodIndex) * 31) + ((int) (j12 ^ (j12 >>> 32)));
        }

        public boolean isLive() {
            if (this.liveConfiguration != null) {
                return true;
            }
            return false;
        }

        public Window set(Object obj, MediaItem mediaItem2, Object obj2, long j2, long j3, long j8, boolean z, boolean z3, MediaItem.LiveConfiguration liveConfiguration2, long j10, long j11, int i2, int i7, long j12) {
            MediaItem mediaItem3;
            Object obj3;
            MediaItem.LocalConfiguration localConfiguration;
            this.uid = obj;
            if (mediaItem2 != null) {
                mediaItem3 = mediaItem2;
            } else {
                mediaItem3 = PLACEHOLDER_MEDIA_ITEM;
            }
            this.mediaItem = mediaItem3;
            if (mediaItem2 == null || (localConfiguration = mediaItem2.localConfiguration) == null) {
                obj3 = null;
            } else {
                obj3 = localConfiguration.tag;
            }
            this.tag = obj3;
            this.manifest = obj2;
            this.presentationStartTimeMs = j2;
            this.windowStartTimeMs = j3;
            this.elapsedRealtimeEpochOffsetMs = j8;
            this.isSeekable = z;
            this.isDynamic = z3;
            this.liveConfiguration = liveConfiguration2;
            this.defaultPositionUs = j10;
            this.durationUs = j11;
            this.firstPeriodIndex = i2;
            this.lastPeriodIndex = i7;
            this.positionInFirstPeriodUs = j12;
            this.isPlaceholder = false;
            return this;
        }
    }

    public boolean equals(Object obj) {
        int lastWindowIndex;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Timeline)) {
            return false;
        }
        Timeline timeline = (Timeline) obj;
        if (timeline.getWindowCount() != getWindowCount() || timeline.getPeriodCount() != getPeriodCount()) {
            return false;
        }
        Window window = new Window();
        Period period = new Period();
        Window window2 = new Window();
        Period period2 = new Period();
        for (int i2 = 0; i2 < getWindowCount(); i2++) {
            if (!getWindow(i2, window).equals(timeline.getWindow(i2, window2))) {
                return false;
            }
        }
        for (int i7 = 0; i7 < getPeriodCount(); i7++) {
            if (!getPeriod(i7, period, true).equals(timeline.getPeriod(i7, period2, true))) {
                return false;
            }
        }
        int firstWindowIndex = getFirstWindowIndex(true);
        if (firstWindowIndex != timeline.getFirstWindowIndex(true) || (lastWindowIndex = getLastWindowIndex(true)) != timeline.getLastWindowIndex(true)) {
            return false;
        }
        while (firstWindowIndex != lastWindowIndex) {
            int nextWindowIndex = getNextWindowIndex(firstWindowIndex, 0, true);
            if (nextWindowIndex != timeline.getNextWindowIndex(firstWindowIndex, 0, true)) {
                return false;
            }
            firstWindowIndex = nextWindowIndex;
        }
        return true;
    }

    public int getFirstWindowIndex(boolean z) {
        if (isEmpty()) {
            return -1;
        }
        return 0;
    }

    public abstract int getIndexOfPeriod(Object obj);

    public int getLastWindowIndex(boolean z) {
        if (isEmpty()) {
            return -1;
        }
        return getWindowCount() - 1;
    }

    public final int getNextPeriodIndex(int i2, Period period, Window window, int i7, boolean z) {
        int i8 = getPeriod(i2, period).windowIndex;
        if (getWindow(i8, window).lastPeriodIndex != i2) {
            return i2 + 1;
        }
        int nextWindowIndex = getNextWindowIndex(i8, i7, z);
        if (nextWindowIndex == -1) {
            return -1;
        }
        return getWindow(nextWindowIndex, window).firstPeriodIndex;
    }

    public int getNextWindowIndex(int i2, int i7, boolean z) {
        if (i7 != 0) {
            if (i7 == 1) {
                return i2;
            }
            if (i7 != 2) {
                throw new IllegalStateException();
            } else if (i2 == getLastWindowIndex(z)) {
                return getFirstWindowIndex(z);
            } else {
                return i2 + 1;
            }
        } else if (i2 == getLastWindowIndex(z)) {
            return -1;
        } else {
            return i2 + 1;
        }
    }

    public final Period getPeriod(int i2, Period period) {
        return getPeriod(i2, period, false);
    }

    public abstract Period getPeriod(int i2, Period period, boolean z);

    public Period getPeriodByUid(Object obj, Period period) {
        return getPeriod(getIndexOfPeriod(obj), period, true);
    }

    public abstract int getPeriodCount();

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int i2, long j2) {
        return (Pair) Assertions.checkNotNull(getPeriodPositionUs(window, period, i2, j2, 0));
    }

    public int getPreviousWindowIndex(int i2, int i7, boolean z) {
        if (i7 != 0) {
            if (i7 == 1) {
                return i2;
            }
            if (i7 != 2) {
                throw new IllegalStateException();
            } else if (i2 == getFirstWindowIndex(z)) {
                return getLastWindowIndex(z);
            } else {
                return i2 - 1;
            }
        } else if (i2 == getFirstWindowIndex(z)) {
            return -1;
        } else {
            return i2 - 1;
        }
    }

    public abstract Object getUidOfPeriod(int i2);

    public final Window getWindow(int i2, Window window) {
        return getWindow(i2, window, 0);
    }

    public abstract Window getWindow(int i2, Window window, long j2);

    public abstract int getWindowCount();

    public int hashCode() {
        Window window = new Window();
        Period period = new Period();
        int windowCount = getWindowCount() + 217;
        for (int i2 = 0; i2 < getWindowCount(); i2++) {
            windowCount = (windowCount * 31) + getWindow(i2, window).hashCode();
        }
        int periodCount = getPeriodCount() + (windowCount * 31);
        for (int i7 = 0; i7 < getPeriodCount(); i7++) {
            periodCount = (periodCount * 31) + getPeriod(i7, period, true).hashCode();
        }
        int firstWindowIndex = getFirstWindowIndex(true);
        while (firstWindowIndex != -1) {
            periodCount = (periodCount * 31) + firstWindowIndex;
            firstWindowIndex = getNextWindowIndex(firstWindowIndex, 0, true);
        }
        return periodCount;
    }

    public final boolean isEmpty() {
        if (getWindowCount() == 0) {
            return true;
        }
        return false;
    }

    public final boolean isLastPeriod(int i2, Period period, Window window, int i7, boolean z) {
        if (getNextPeriodIndex(i2, period, window, i7, z) == -1) {
            return true;
        }
        return false;
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int i2, long j2, long j3) {
        Assertions.checkIndex(i2, 0, getWindowCount());
        getWindow(i2, window, j3);
        if (j2 == -9223372036854775807L) {
            j2 = window.getDefaultPositionUs();
            if (j2 == -9223372036854775807L) {
                return null;
            }
        }
        int i7 = window.firstPeriodIndex;
        getPeriod(i7, period);
        while (i7 < window.lastPeriodIndex && period.positionInWindowUs != j2) {
            int i8 = i7 + 1;
            if (getPeriod(i8, period).positionInWindowUs > j2) {
                break;
            }
            i7 = i8;
        }
        getPeriod(i7, period, true);
        long j8 = j2 - period.positionInWindowUs;
        long j10 = period.durationUs;
        if (j10 != -9223372036854775807L) {
            j8 = Math.min(j8, j10 - 1);
        }
        return Pair.create(Assertions.checkNotNull(period.uid), Long.valueOf(Math.max(0, j8)));
    }
}
