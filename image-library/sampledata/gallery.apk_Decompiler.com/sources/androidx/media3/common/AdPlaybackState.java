package androidx.media3.common;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AdPlaybackState {
    private static final String FIELD_AD_GROUPS = Util.intToStringMaxRadix(1);
    private static final String FIELD_AD_RESUME_POSITION_US = Util.intToStringMaxRadix(2);
    private static final String FIELD_CONTENT_DURATION_US = Util.intToStringMaxRadix(3);
    private static final String FIELD_REMOVED_AD_GROUP_COUNT = Util.intToStringMaxRadix(4);
    public static final AdPlaybackState NONE = new AdPlaybackState((Object) null, new AdGroup[0], 0, -9223372036854775807L, 0);
    private static final AdGroup REMOVED_AD_GROUP = new AdGroup(0).withAdCount(0);
    public final int adGroupCount;
    private final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final Object adsId;
    public final long contentDurationUs;
    public final int removedAdGroupCount;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AdGroup {
        private static final String FIELD_CONTENT_RESUME_OFFSET_US = Util.intToStringMaxRadix(5);
        private static final String FIELD_COUNT = Util.intToStringMaxRadix(1);
        private static final String FIELD_DURATIONS_US = Util.intToStringMaxRadix(4);
        static final String FIELD_IDS = Util.intToStringMaxRadix(9);
        static final String FIELD_IS_PLACEHOLDER = Util.intToStringMaxRadix(10);
        private static final String FIELD_IS_SERVER_SIDE_INSERTED = Util.intToStringMaxRadix(6);
        static final String FIELD_MEDIA_ITEMS = Util.intToStringMaxRadix(8);
        private static final String FIELD_ORIGINAL_COUNT = Util.intToStringMaxRadix(7);
        private static final String FIELD_STATES = Util.intToStringMaxRadix(3);
        private static final String FIELD_TIME_US = Util.intToStringMaxRadix(0);
        private static final String FIELD_URIS = Util.intToStringMaxRadix(2);
        public final long contentResumeOffsetUs;
        public final int count;
        public final long[] durationsUs;
        public final String[] ids;
        public final boolean isPlaceholder;
        public final boolean isServerSideInserted;
        public final MediaItem[] mediaItems;
        public final int originalCount;
        public final int[] states;
        public final long timeUs;
        @Deprecated
        public final Uri[] uris;

        public AdGroup(long j2) {
            this(j2, -1, -1, new int[0], new MediaItem[0], new long[0], 0, false, new String[0], false);
        }

        private static long[] copyDurationsUsWithSpaceForAdCount(long[] jArr, int i2) {
            int length = jArr.length;
            int max = Math.max(i2, length);
            long[] copyOf = Arrays.copyOf(jArr, max);
            Arrays.fill(copyOf, length, max, -9223372036854775807L);
            return copyOf;
        }

        private static int[] copyStatesWithSpaceForAdCount(int[] iArr, int i2) {
            int length = iArr.length;
            int max = Math.max(i2, length);
            int[] copyOf = Arrays.copyOf(iArr, max);
            Arrays.fill(copyOf, length, max, 0);
            return copyOf;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && AdGroup.class == obj.getClass()) {
                AdGroup adGroup = (AdGroup) obj;
                if (this.timeUs == adGroup.timeUs && this.count == adGroup.count && this.originalCount == adGroup.originalCount && Arrays.equals(this.mediaItems, adGroup.mediaItems) && Arrays.equals(this.states, adGroup.states) && Arrays.equals(this.durationsUs, adGroup.durationsUs) && this.contentResumeOffsetUs == adGroup.contentResumeOffsetUs && this.isServerSideInserted == adGroup.isServerSideInserted && Arrays.equals(this.ids, adGroup.ids) && this.isPlaceholder == adGroup.isPlaceholder) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(int i2) {
            int i7;
            int i8 = i2 + 1;
            while (true) {
                int[] iArr = this.states;
                if (i8 >= iArr.length || this.isServerSideInserted || (i7 = iArr[i8]) == 0 || i7 == 1) {
                    return i8;
                }
                i8++;
            }
            return i8;
        }

        public boolean hasUnplayedAds() {
            if (this.count == -1) {
                return true;
            }
            for (int i2 = 0; i2 < this.count; i2++) {
                int i7 = this.states[i2];
                if (i7 == 0 || i7 == 1) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            long j2 = this.timeUs;
            int hashCode = Arrays.hashCode(this.mediaItems);
            int hashCode2 = Arrays.hashCode(this.states);
            int hashCode3 = Arrays.hashCode(this.durationsUs);
            long j3 = this.contentResumeOffsetUs;
            return ((((((((hashCode3 + ((hashCode2 + ((hashCode + (((((this.count * 31) + this.originalCount) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31)) * 31)) * 31)) * 31) + ((int) ((j3 >>> 32) ^ j3))) * 31) + (this.isServerSideInserted ? 1 : 0)) * 31) + Arrays.hashCode(this.ids)) * 31) + (this.isPlaceholder ? 1 : 0);
        }

        public boolean isLivePostrollPlaceholder() {
            if (this.isPlaceholder && this.timeUs == Long.MIN_VALUE && this.count == -1) {
                return true;
            }
            return false;
        }

        public boolean shouldPlayAdGroup() {
            if (this.count == -1 || getFirstAdIndexToPlay() < this.count) {
                return true;
            }
            return false;
        }

        public AdGroup withAdCount(int i2) {
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i2);
            long[] copyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(this.durationsUs, i2);
            return new AdGroup(this.timeUs, i2, this.originalCount, copyStatesWithSpaceForAdCount, (MediaItem[]) Arrays.copyOf(this.mediaItems, i2), copyDurationsUsWithSpaceForAdCount, this.contentResumeOffsetUs, this.isServerSideInserted, (String[]) Arrays.copyOf(this.ids, i2), this.isPlaceholder);
        }

        private AdGroup(long j2, int i2, int i7, int[] iArr, MediaItem[] mediaItemArr, long[] jArr, long j3, boolean z, String[] strArr, boolean z3) {
            int i8 = 0;
            Assertions.checkArgument(iArr.length == mediaItemArr.length);
            this.timeUs = j2;
            this.count = i2;
            this.originalCount = i7;
            this.states = iArr;
            this.mediaItems = mediaItemArr;
            this.durationsUs = jArr;
            this.contentResumeOffsetUs = j3;
            this.isServerSideInserted = z;
            this.uris = new Uri[mediaItemArr.length];
            while (true) {
                Uri[] uriArr = this.uris;
                if (i8 < uriArr.length) {
                    MediaItem mediaItem = mediaItemArr[i8];
                    uriArr[i8] = mediaItem == null ? null : ((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration)).uri;
                    i8++;
                } else {
                    this.ids = strArr;
                    this.isPlaceholder = z3;
                    return;
                }
            }
        }
    }

    private AdPlaybackState(Object obj, AdGroup[] adGroupArr, long j2, long j3, int i2) {
        this.adsId = obj;
        this.adResumePositionUs = j2;
        this.contentDurationUs = j3;
        this.adGroupCount = adGroupArr.length + i2;
        this.adGroups = adGroupArr;
        this.removedAdGroupCount = i2;
    }

    private boolean isPositionBeforeAdGroup(long j2, long j3, int i2) {
        if (j2 == Long.MIN_VALUE) {
            return false;
        }
        AdGroup adGroup = getAdGroup(i2);
        long j8 = adGroup.timeUs;
        if (j8 == Long.MIN_VALUE) {
            if (j3 == -9223372036854775807L || adGroup.isLivePostrollPlaceholder() || j2 < j3) {
                return true;
            }
            return false;
        } else if (j2 < j8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AdPlaybackState.class == obj.getClass()) {
            AdPlaybackState adPlaybackState = (AdPlaybackState) obj;
            if (Objects.equals(this.adsId, adPlaybackState.adsId) && this.adGroupCount == adPlaybackState.adGroupCount && this.adResumePositionUs == adPlaybackState.adResumePositionUs && this.contentDurationUs == adPlaybackState.contentDurationUs && this.removedAdGroupCount == adPlaybackState.removedAdGroupCount && Arrays.equals(this.adGroups, adPlaybackState.adGroups)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public AdGroup getAdGroup(int i2) {
        int i7 = this.removedAdGroupCount;
        if (i2 < i7) {
            return REMOVED_AD_GROUP;
        }
        return this.adGroups[i2 - i7];
    }

    public int getAdGroupIndexAfterPositionUs(long j2, long j3) {
        int i2;
        if (j2 != Long.MIN_VALUE && (j3 == -9223372036854775807L || j2 < j3)) {
            int i7 = this.removedAdGroupCount;
            while (i7 < this.adGroupCount && ((getAdGroup(i7).timeUs != Long.MIN_VALUE && getAdGroup(i7).timeUs <= j2) || !getAdGroup(i7).shouldPlayAdGroup())) {
                i7++;
            }
            if (i7 >= this.adGroupCount || (i2 != 0 && getAdGroup(i7).timeUs > j3)) {
                return -1;
            }
            return i7;
        }
        return -1;
    }

    public int getAdGroupIndexForPositionUs(long j2, long j3) {
        AdPlaybackState adPlaybackState;
        int i2 = this.adGroupCount - 1;
        int i7 = i2 - (isLivePostrollPlaceholder(i2) ? 1 : 0);
        while (true) {
            adPlaybackState = this;
            if (i7 < 0) {
                break;
            }
            long j8 = j2;
            long j10 = j3;
            if (!adPlaybackState.isPositionBeforeAdGroup(j8, j10, i7)) {
                break;
            }
            i7--;
            this = adPlaybackState;
            j2 = j8;
            j3 = j10;
        }
        if (i7 < 0 || !adPlaybackState.getAdGroup(i7).hasUnplayedAds()) {
            return -1;
        }
        return i7;
    }

    public int hashCode() {
        int i2;
        int i7 = this.adGroupCount * 31;
        Object obj = this.adsId;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = obj.hashCode();
        }
        return Arrays.hashCode(this.adGroups) + ((((((((i7 + i2) * 31) + ((int) this.adResumePositionUs)) * 31) + ((int) this.contentDurationUs)) * 31) + this.removedAdGroupCount) * 31);
    }

    public boolean isLivePostrollPlaceholder(int i2) {
        if (i2 != this.adGroupCount - 1 || !getAdGroup(i2).isLivePostrollPlaceholder()) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AdPlaybackState(adsId=");
        sb2.append(this.adsId);
        sb2.append(", adResumePositionUs=");
        sb2.append(this.adResumePositionUs);
        sb2.append(", adGroups=[");
        for (int i2 = 0; i2 < this.adGroups.length; i2++) {
            sb2.append("adGroup(timeUs=");
            sb2.append(this.adGroups[i2].timeUs);
            sb2.append(", ads=[");
            for (int i7 = 0; i7 < this.adGroups[i2].states.length; i7++) {
                sb2.append("ad(state=");
                int i8 = this.adGroups[i2].states[i7];
                if (i8 == 0) {
                    sb2.append('_');
                } else if (i8 == 1) {
                    sb2.append('R');
                } else if (i8 == 2) {
                    sb2.append('S');
                } else if (i8 == 3) {
                    sb2.append('P');
                } else if (i8 != 4) {
                    sb2.append('?');
                } else {
                    sb2.append('!');
                }
                sb2.append(", durationUs=");
                sb2.append(this.adGroups[i2].durationsUs[i7]);
                sb2.append(')');
                if (i7 < this.adGroups[i2].states.length - 1) {
                    sb2.append(ArcCommonLog.TAG_COMMA);
                }
            }
            sb2.append("])");
            if (i2 < this.adGroups.length - 1) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
        }
        sb2.append("])");
        return sb2.toString();
    }
}
