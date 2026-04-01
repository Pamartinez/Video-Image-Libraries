package androidx.media3.common;

import androidx.media3.common.FlagSet;
import androidx.media3.common.util.Util;
import i.C0212a;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Player {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Commands {
        public static final Commands EMPTY = new Builder().build();
        private static final String FIELD_COMMANDS = Util.intToStringMaxRadix(0);
        /* access modifiers changed from: private */
        public final FlagSet flags;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            private static final int[] SUPPORTED_COMMANDS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 31, 20, 21, 22, 23, 24, 25, 33, 26, 34, 35, 27, 28, 29, 30, 32};
            private final FlagSet.Builder flagsBuilder = new FlagSet.Builder();

            public Builder add(int i2) {
                this.flagsBuilder.add(i2);
                return this;
            }

            public Builder addAll(int... iArr) {
                this.flagsBuilder.addAll(iArr);
                return this;
            }

            public Builder addIf(int i2, boolean z) {
                this.flagsBuilder.addIf(i2, z);
                return this;
            }

            public Commands build() {
                return new Commands(this.flagsBuilder.build());
            }

            public Builder addAll(Commands commands) {
                this.flagsBuilder.addAll(commands.flags);
                return this;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Commands)) {
                return false;
            }
            return this.flags.equals(((Commands) obj).flags);
        }

        public int hashCode() {
            return this.flags.hashCode();
        }

        private Commands(FlagSet flagSet) {
            this.flags = flagSet;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Events {
        private final FlagSet flags;

        public Events(FlagSet flagSet) {
            this.flags = flagSet;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Events)) {
                return false;
            }
            return this.flags.equals(((Events) obj).flags);
        }

        public int hashCode() {
            return this.flags.hashCode();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onPlayerError(PlaybackException playbackException);

        @Deprecated
        void onPositionDiscontinuity(int i2) {
        }

        void onTimelineChanged(Timeline timeline, int i2);

        void onTracksChanged(Tracks tracks);

        void onPositionDiscontinuity(PositionInfo positionInfo, PositionInfo positionInfo2, int i2) {
        }

        void onAudioSessionIdChanged(int i2) {
        }

        void onAvailableCommandsChanged(Commands commands) {
        }

        void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        }

        void onIsLoadingChanged(boolean z) {
        }

        void onIsPlayingChanged(boolean z) {
        }

        @Deprecated
        void onLoadingChanged(boolean z) {
        }

        void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        }

        void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        void onPlaybackStateChanged(int i2) {
        }

        void onPlaybackSuppressionReasonChanged(int i2) {
        }

        void onPlayerErrorChanged(PlaybackException playbackException) {
        }

        void onDeviceVolumeChanged(int i2, boolean z) {
        }

        void onEvents(Player player, Events events) {
        }

        void onMediaItemTransition(MediaItem mediaItem, int i2) {
        }

        void onPlayWhenReadyChanged(boolean z, int i2) {
        }

        @Deprecated
        void onPlayerStateChanged(boolean z, int i2) {
        }

        void onSurfaceSizeChanged(int i2, int i7) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PositionInfo {
        private static final String FIELD_AD_GROUP_INDEX = Util.intToStringMaxRadix(5);
        private static final String FIELD_AD_INDEX_IN_AD_GROUP = Util.intToStringMaxRadix(6);
        static final String FIELD_CONTENT_POSITION_MS = Util.intToStringMaxRadix(4);
        private static final String FIELD_MEDIA_ITEM = Util.intToStringMaxRadix(1);
        static final String FIELD_MEDIA_ITEM_INDEX = Util.intToStringMaxRadix(0);
        static final String FIELD_PERIOD_INDEX = Util.intToStringMaxRadix(2);
        static final String FIELD_POSITION_MS = Util.intToStringMaxRadix(3);
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final long contentPositionMs;
        public final MediaItem mediaItem;
        public final int mediaItemIndex;
        public final int periodIndex;
        public final Object periodUid;
        public final long positionMs;
        @Deprecated
        public final int windowIndex;
        public final Object windowUid;

        public PositionInfo(Object obj, int i2, MediaItem mediaItem2, Object obj2, int i7, long j2, long j3, int i8, int i10) {
            this.windowUid = obj;
            this.windowIndex = i2;
            this.mediaItemIndex = i2;
            this.mediaItem = mediaItem2;
            this.periodUid = obj2;
            this.periodIndex = i7;
            this.positionMs = j2;
            this.contentPositionMs = j3;
            this.adGroupIndex = i8;
            this.adIndexInAdGroup = i10;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && PositionInfo.class == obj.getClass()) {
                PositionInfo positionInfo = (PositionInfo) obj;
                if (!equalsForBundling(positionInfo) || !Objects.equals(this.windowUid, positionInfo.windowUid) || !Objects.equals(this.periodUid, positionInfo.periodUid)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public boolean equalsForBundling(PositionInfo positionInfo) {
            if (this.mediaItemIndex == positionInfo.mediaItemIndex && this.periodIndex == positionInfo.periodIndex && this.positionMs == positionInfo.positionMs && this.contentPositionMs == positionInfo.contentPositionMs && this.adGroupIndex == positionInfo.adGroupIndex && this.adIndexInAdGroup == positionInfo.adIndexInAdGroup && Objects.equals(this.mediaItem, positionInfo.mediaItem)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.windowUid, Integer.valueOf(this.mediaItemIndex), this.mediaItem, this.periodUid, Integer.valueOf(this.periodIndex), Long.valueOf(this.positionMs), Long.valueOf(this.contentPositionMs), Integer.valueOf(this.adGroupIndex), Integer.valueOf(this.adIndexInAdGroup)});
        }

        public String toString() {
            String str = "mediaItem=" + this.mediaItemIndex + ", period=" + this.periodIndex + ", pos=" + this.positionMs;
            if (this.adGroupIndex == -1) {
                return str;
            }
            StringBuilder t = C0212a.t(str, ", contentPos=");
            t.append(this.contentPositionMs);
            t.append(", adGroup=");
            t.append(this.adGroupIndex);
            t.append(", ad=");
            t.append(this.adIndexInAdGroup);
            return t.toString();
        }
    }

    void addListener(Listener listener);

    long getContentPosition();

    int getCurrentAdGroupIndex();

    int getCurrentAdIndexInAdGroup();

    int getCurrentMediaItemIndex();

    int getCurrentPeriodIndex();

    long getCurrentPosition();

    Timeline getCurrentTimeline();

    Tracks getCurrentTracks();

    long getDuration();

    boolean getPlayWhenReady();

    int getPlaybackState();

    int getPlaybackSuppressionReason();

    PlaybackException getPlayerError();

    int getRepeatMode();

    boolean getShuffleModeEnabled();

    long getTotalBufferedDuration();

    boolean hasNextMediaItem();

    boolean hasPreviousMediaItem();

    boolean isCurrentMediaItemDynamic();

    boolean isCurrentMediaItemLive();

    boolean isCurrentMediaItemSeekable();

    boolean isPlayingAd();

    void play();

    void prepare();

    void setMediaItem(MediaItem mediaItem);

    void setMediaItems(List<MediaItem> list, boolean z);

    void setPlayWhenReady(boolean z);
}
