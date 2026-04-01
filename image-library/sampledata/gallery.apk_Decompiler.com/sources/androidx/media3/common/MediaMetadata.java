package androidx.media3.common;

import F2.G;
import F2.U;
import F2.y0;
import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaMetadata {
    public static final MediaMetadata EMPTY = new Builder().build();
    private static final String FIELD_ALBUM_ARTIST = Util.intToStringMaxRadix(3);
    private static final String FIELD_ALBUM_TITLE = Util.intToStringMaxRadix(2);
    private static final String FIELD_ARTIST = Util.intToStringMaxRadix(1);
    private static final String FIELD_ARTWORK_DATA = Util.intToStringMaxRadix(10);
    private static final String FIELD_ARTWORK_DATA_TYPE = Util.intToStringMaxRadix(29);
    private static final String FIELD_ARTWORK_URI = Util.intToStringMaxRadix(11);
    private static final String FIELD_COMPILATION = Util.intToStringMaxRadix(28);
    private static final String FIELD_COMPOSER = Util.intToStringMaxRadix(23);
    private static final String FIELD_CONDUCTOR = Util.intToStringMaxRadix(24);
    private static final String FIELD_DESCRIPTION = Util.intToStringMaxRadix(6);
    private static final String FIELD_DISC_NUMBER = Util.intToStringMaxRadix(25);
    private static final String FIELD_DISPLAY_TITLE = Util.intToStringMaxRadix(4);
    private static final String FIELD_DURATION_MS = Util.intToStringMaxRadix(33);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(1000);
    private static final String FIELD_FOLDER_TYPE = Util.intToStringMaxRadix(14);
    private static final String FIELD_GENRE = Util.intToStringMaxRadix(27);
    private static final String FIELD_IS_BROWSABLE = Util.intToStringMaxRadix(32);
    private static final String FIELD_IS_PLAYABLE = Util.intToStringMaxRadix(15);
    private static final String FIELD_MEDIA_TYPE = Util.intToStringMaxRadix(31);
    private static final String FIELD_OVERALL_RATING = Util.intToStringMaxRadix(9);
    private static final String FIELD_RECORDING_DAY = Util.intToStringMaxRadix(18);
    private static final String FIELD_RECORDING_MONTH = Util.intToStringMaxRadix(17);
    private static final String FIELD_RECORDING_YEAR = Util.intToStringMaxRadix(16);
    private static final String FIELD_RELEASE_DAY = Util.intToStringMaxRadix(21);
    private static final String FIELD_RELEASE_MONTH = Util.intToStringMaxRadix(20);
    private static final String FIELD_RELEASE_YEAR = Util.intToStringMaxRadix(19);
    private static final String FIELD_STATION = Util.intToStringMaxRadix(30);
    private static final String FIELD_SUBTITLE = Util.intToStringMaxRadix(5);
    private static final String FIELD_SUPPORTED_COMMANDS = Util.intToStringMaxRadix(34);
    private static final String FIELD_TITLE = Util.intToStringMaxRadix(0);
    private static final String FIELD_TOTAL_DISC_COUNT = Util.intToStringMaxRadix(26);
    private static final String FIELD_TOTAL_TRACK_COUNT = Util.intToStringMaxRadix(13);
    private static final String FIELD_TRACK_NUMBER = Util.intToStringMaxRadix(12);
    private static final String FIELD_USER_RATING = Util.intToStringMaxRadix(8);
    private static final String FIELD_WRITER = Util.intToStringMaxRadix(22);
    public final CharSequence albumArtist;
    public final CharSequence albumTitle;
    public final CharSequence artist;
    public final byte[] artworkData;
    public final Integer artworkDataType;
    public final Uri artworkUri;
    public final CharSequence compilation;
    public final CharSequence composer;
    public final CharSequence conductor;
    public final CharSequence description;
    public final Integer discNumber;
    public final CharSequence displayTitle;
    public final Long durationMs;
    public final Bundle extras;
    @Deprecated
    public final Integer folderType;
    public final CharSequence genre;
    public final Boolean isBrowsable;
    public final Boolean isPlayable;
    public final Integer mediaType;
    public final Integer recordingDay;
    public final Integer recordingMonth;
    public final Integer recordingYear;
    public final Integer releaseDay;
    public final Integer releaseMonth;
    public final Integer releaseYear;
    public final CharSequence station;
    public final CharSequence subtitle;
    public final U supportedCommands;
    public final CharSequence title;
    public final Integer totalDiscCount;
    public final Integer totalTrackCount;
    public final Integer trackNumber;
    public final CharSequence writer;
    @Deprecated
    public final Integer year;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public CharSequence albumArtist;
        /* access modifiers changed from: private */
        public CharSequence albumTitle;
        /* access modifiers changed from: private */
        public CharSequence artist;
        /* access modifiers changed from: private */
        public byte[] artworkData;
        /* access modifiers changed from: private */
        public Integer artworkDataType;
        /* access modifiers changed from: private */
        public Uri artworkUri;
        /* access modifiers changed from: private */
        public CharSequence compilation;
        /* access modifiers changed from: private */
        public CharSequence composer;
        /* access modifiers changed from: private */
        public CharSequence conductor;
        /* access modifiers changed from: private */
        public CharSequence description;
        /* access modifiers changed from: private */
        public Integer discNumber;
        /* access modifiers changed from: private */
        public CharSequence displayTitle;
        /* access modifiers changed from: private */
        public Long durationMs;
        /* access modifiers changed from: private */
        public Bundle extras;
        /* access modifiers changed from: private */
        public Integer folderType;
        /* access modifiers changed from: private */
        public CharSequence genre;
        /* access modifiers changed from: private */
        public Boolean isBrowsable;
        /* access modifiers changed from: private */
        public Boolean isPlayable;
        /* access modifiers changed from: private */
        public Integer mediaType;
        /* access modifiers changed from: private */
        public Integer recordingDay;
        /* access modifiers changed from: private */
        public Integer recordingMonth;
        /* access modifiers changed from: private */
        public Integer recordingYear;
        /* access modifiers changed from: private */
        public Integer releaseDay;
        /* access modifiers changed from: private */
        public Integer releaseMonth;
        /* access modifiers changed from: private */
        public Integer releaseYear;
        /* access modifiers changed from: private */
        public CharSequence station;
        /* access modifiers changed from: private */
        public CharSequence subtitle;
        /* access modifiers changed from: private */
        public U supportedCommands;
        /* access modifiers changed from: private */
        public CharSequence title;
        /* access modifiers changed from: private */
        public Integer totalDiscCount;
        /* access modifiers changed from: private */
        public Integer totalTrackCount;
        /* access modifiers changed from: private */
        public Integer trackNumber;
        /* access modifiers changed from: private */
        public CharSequence writer;

        public MediaMetadata build() {
            return new MediaMetadata(this);
        }

        public Builder maybeSetArtworkData(byte[] bArr, int i2) {
            if (this.artworkData != null && i2 != 3 && Objects.equals(this.artworkDataType, 3)) {
                return this;
            }
            this.artworkData = (byte[]) bArr.clone();
            this.artworkDataType = Integer.valueOf(i2);
            return this;
        }

        public Builder populate(MediaMetadata mediaMetadata) {
            if (mediaMetadata != null) {
                CharSequence charSequence = mediaMetadata.title;
                if (charSequence != null) {
                    setTitle(charSequence);
                }
                CharSequence charSequence2 = mediaMetadata.artist;
                if (charSequence2 != null) {
                    setArtist(charSequence2);
                }
                CharSequence charSequence3 = mediaMetadata.albumTitle;
                if (charSequence3 != null) {
                    setAlbumTitle(charSequence3);
                }
                CharSequence charSequence4 = mediaMetadata.albumArtist;
                if (charSequence4 != null) {
                    setAlbumArtist(charSequence4);
                }
                CharSequence charSequence5 = mediaMetadata.displayTitle;
                if (charSequence5 != null) {
                    setDisplayTitle(charSequence5);
                }
                CharSequence charSequence6 = mediaMetadata.subtitle;
                if (charSequence6 != null) {
                    setSubtitle(charSequence6);
                }
                CharSequence charSequence7 = mediaMetadata.description;
                if (charSequence7 != null) {
                    setDescription(charSequence7);
                }
                Long l = mediaMetadata.durationMs;
                if (l != null) {
                    setDurationMs(l);
                }
                Uri uri = mediaMetadata.artworkUri;
                if (!(uri == null && mediaMetadata.artworkData == null)) {
                    setArtworkUri(uri);
                    setArtworkData(mediaMetadata.artworkData, mediaMetadata.artworkDataType);
                }
                Integer num = mediaMetadata.trackNumber;
                if (num != null) {
                    setTrackNumber(num);
                }
                Integer num2 = mediaMetadata.totalTrackCount;
                if (num2 != null) {
                    setTotalTrackCount(num2);
                }
                Integer num3 = mediaMetadata.folderType;
                if (num3 != null) {
                    setFolderType(num3);
                }
                Boolean bool = mediaMetadata.isBrowsable;
                if (bool != null) {
                    setIsBrowsable(bool);
                }
                Boolean bool2 = mediaMetadata.isPlayable;
                if (bool2 != null) {
                    setIsPlayable(bool2);
                }
                Integer num4 = mediaMetadata.year;
                if (num4 != null) {
                    setRecordingYear(num4);
                }
                Integer num5 = mediaMetadata.recordingYear;
                if (num5 != null) {
                    setRecordingYear(num5);
                }
                Integer num6 = mediaMetadata.recordingMonth;
                if (num6 != null) {
                    setRecordingMonth(num6);
                }
                Integer num7 = mediaMetadata.recordingDay;
                if (num7 != null) {
                    setRecordingDay(num7);
                }
                Integer num8 = mediaMetadata.releaseYear;
                if (num8 != null) {
                    setReleaseYear(num8);
                }
                Integer num9 = mediaMetadata.releaseMonth;
                if (num9 != null) {
                    setReleaseMonth(num9);
                }
                Integer num10 = mediaMetadata.releaseDay;
                if (num10 != null) {
                    setReleaseDay(num10);
                }
                CharSequence charSequence8 = mediaMetadata.writer;
                if (charSequence8 != null) {
                    setWriter(charSequence8);
                }
                CharSequence charSequence9 = mediaMetadata.composer;
                if (charSequence9 != null) {
                    setComposer(charSequence9);
                }
                CharSequence charSequence10 = mediaMetadata.conductor;
                if (charSequence10 != null) {
                    setConductor(charSequence10);
                }
                Integer num11 = mediaMetadata.discNumber;
                if (num11 != null) {
                    setDiscNumber(num11);
                }
                Integer num12 = mediaMetadata.totalDiscCount;
                if (num12 != null) {
                    setTotalDiscCount(num12);
                }
                CharSequence charSequence11 = mediaMetadata.genre;
                if (charSequence11 != null) {
                    setGenre(charSequence11);
                }
                CharSequence charSequence12 = mediaMetadata.compilation;
                if (charSequence12 != null) {
                    setCompilation(charSequence12);
                }
                CharSequence charSequence13 = mediaMetadata.station;
                if (charSequence13 != null) {
                    setStation(charSequence13);
                }
                Integer num13 = mediaMetadata.mediaType;
                if (num13 != null) {
                    setMediaType(num13);
                }
                Bundle bundle = mediaMetadata.extras;
                if (bundle != null) {
                    setExtras(bundle);
                }
                if (!mediaMetadata.supportedCommands.isEmpty()) {
                    setSupportedCommands(mediaMetadata.supportedCommands);
                }
            }
            return this;
        }

        public Builder populateFromMetadata(List<Metadata> list) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                Metadata metadata = list.get(i2);
                for (int i7 = 0; i7 < metadata.length(); i7++) {
                    metadata.get(i7).populateMediaMetadata(this);
                }
            }
            return this;
        }

        public Builder setAlbumArtist(CharSequence charSequence) {
            this.albumArtist = charSequence;
            return this;
        }

        public Builder setAlbumTitle(CharSequence charSequence) {
            this.albumTitle = charSequence;
            return this;
        }

        public Builder setArtist(CharSequence charSequence) {
            this.artist = charSequence;
            return this;
        }

        public Builder setArtworkData(byte[] bArr, Integer num) {
            byte[] bArr2;
            if (bArr == null) {
                bArr2 = null;
            } else {
                bArr2 = (byte[]) bArr.clone();
            }
            this.artworkData = bArr2;
            this.artworkDataType = num;
            return this;
        }

        public Builder setArtworkUri(Uri uri) {
            this.artworkUri = uri;
            return this;
        }

        public Builder setCompilation(CharSequence charSequence) {
            this.compilation = charSequence;
            return this;
        }

        public Builder setComposer(CharSequence charSequence) {
            this.composer = charSequence;
            return this;
        }

        public Builder setConductor(CharSequence charSequence) {
            this.conductor = charSequence;
            return this;
        }

        public Builder setDescription(CharSequence charSequence) {
            this.description = charSequence;
            return this;
        }

        public Builder setDiscNumber(Integer num) {
            this.discNumber = num;
            return this;
        }

        public Builder setDisplayTitle(CharSequence charSequence) {
            this.displayTitle = charSequence;
            return this;
        }

        public Builder setDurationMs(Long l) {
            boolean z;
            if (l == null || l.longValue() >= 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.durationMs = l;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.extras = bundle;
            return this;
        }

        @Deprecated
        public Builder setFolderType(Integer num) {
            this.folderType = num;
            return this;
        }

        public Builder setGenre(CharSequence charSequence) {
            this.genre = charSequence;
            return this;
        }

        public Builder setIsBrowsable(Boolean bool) {
            this.isBrowsable = bool;
            return this;
        }

        public Builder setIsPlayable(Boolean bool) {
            this.isPlayable = bool;
            return this;
        }

        public Builder setMediaType(Integer num) {
            this.mediaType = num;
            return this;
        }

        public Builder setRecordingDay(Integer num) {
            this.recordingDay = num;
            return this;
        }

        public Builder setRecordingMonth(Integer num) {
            this.recordingMonth = num;
            return this;
        }

        public Builder setRecordingYear(Integer num) {
            this.recordingYear = num;
            return this;
        }

        public Builder setReleaseDay(Integer num) {
            this.releaseDay = num;
            return this;
        }

        public Builder setReleaseMonth(Integer num) {
            this.releaseMonth = num;
            return this;
        }

        public Builder setReleaseYear(Integer num) {
            this.releaseYear = num;
            return this;
        }

        public Builder setStation(CharSequence charSequence) {
            this.station = charSequence;
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.subtitle = charSequence;
            return this;
        }

        public Builder setSupportedCommands(List<String> list) {
            this.supportedCommands = U.y(list);
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }

        public Builder setTotalDiscCount(Integer num) {
            this.totalDiscCount = num;
            return this;
        }

        public Builder setTotalTrackCount(Integer num) {
            this.totalTrackCount = num;
            return this;
        }

        public Builder setTrackNumber(Integer num) {
            this.trackNumber = num;
            return this;
        }

        public Builder setWriter(CharSequence charSequence) {
            this.writer = charSequence;
            return this;
        }

        public Builder() {
            G g = U.e;
            this.supportedCommands = y0.f278h;
        }

        private Builder(MediaMetadata mediaMetadata) {
            this.title = mediaMetadata.title;
            this.artist = mediaMetadata.artist;
            this.albumTitle = mediaMetadata.albumTitle;
            this.albumArtist = mediaMetadata.albumArtist;
            this.displayTitle = mediaMetadata.displayTitle;
            this.subtitle = mediaMetadata.subtitle;
            this.description = mediaMetadata.description;
            this.durationMs = mediaMetadata.durationMs;
            this.artworkData = mediaMetadata.artworkData;
            this.artworkDataType = mediaMetadata.artworkDataType;
            this.artworkUri = mediaMetadata.artworkUri;
            this.trackNumber = mediaMetadata.trackNumber;
            this.totalTrackCount = mediaMetadata.totalTrackCount;
            this.folderType = mediaMetadata.folderType;
            this.isBrowsable = mediaMetadata.isBrowsable;
            this.isPlayable = mediaMetadata.isPlayable;
            this.recordingYear = mediaMetadata.recordingYear;
            this.recordingMonth = mediaMetadata.recordingMonth;
            this.recordingDay = mediaMetadata.recordingDay;
            this.releaseYear = mediaMetadata.releaseYear;
            this.releaseMonth = mediaMetadata.releaseMonth;
            this.releaseDay = mediaMetadata.releaseDay;
            this.writer = mediaMetadata.writer;
            this.composer = mediaMetadata.composer;
            this.conductor = mediaMetadata.conductor;
            this.discNumber = mediaMetadata.discNumber;
            this.totalDiscCount = mediaMetadata.totalDiscCount;
            this.genre = mediaMetadata.genre;
            this.compilation = mediaMetadata.compilation;
            this.station = mediaMetadata.station;
            this.mediaType = mediaMetadata.mediaType;
            this.supportedCommands = mediaMetadata.supportedCommands;
            this.extras = mediaMetadata.extras;
        }
    }

    private static int getFolderTypeFromMediaType(int i2) {
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
                return 1;
            case 21:
                return 2;
            case 22:
                return 3;
            case 23:
                return 4;
            case 24:
                return 5;
            case 25:
                return 6;
            default:
                return 0;
        }
    }

    private static int getMediaTypeFromFolderType(int i2) {
        switch (i2) {
            case 1:
                return 0;
            case 2:
                return 21;
            case 3:
                return 22;
            case 4:
                return 23;
            case 5:
                return 24;
            case 6:
                return 25;
            default:
                return 20;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        boolean z;
        boolean z3;
        if (this == obj) {
            return true;
        }
        if (obj != null && MediaMetadata.class == obj.getClass()) {
            MediaMetadata mediaMetadata = (MediaMetadata) obj;
            if (Objects.equals(this.title, mediaMetadata.title) && Objects.equals(this.artist, mediaMetadata.artist) && Objects.equals(this.albumTitle, mediaMetadata.albumTitle) && Objects.equals(this.albumArtist, mediaMetadata.albumArtist) && Objects.equals(this.displayTitle, mediaMetadata.displayTitle) && Objects.equals(this.subtitle, mediaMetadata.subtitle) && Objects.equals(this.description, mediaMetadata.description) && Objects.equals(this.durationMs, mediaMetadata.durationMs) && Arrays.equals(this.artworkData, mediaMetadata.artworkData) && Objects.equals(this.artworkDataType, mediaMetadata.artworkDataType) && Objects.equals(this.artworkUri, mediaMetadata.artworkUri) && Objects.equals(this.trackNumber, mediaMetadata.trackNumber) && Objects.equals(this.totalTrackCount, mediaMetadata.totalTrackCount) && Objects.equals(this.folderType, mediaMetadata.folderType) && Objects.equals(this.isBrowsable, mediaMetadata.isBrowsable) && Objects.equals(this.isPlayable, mediaMetadata.isPlayable) && Objects.equals(this.recordingYear, mediaMetadata.recordingYear) && Objects.equals(this.recordingMonth, mediaMetadata.recordingMonth) && Objects.equals(this.recordingDay, mediaMetadata.recordingDay) && Objects.equals(this.releaseYear, mediaMetadata.releaseYear) && Objects.equals(this.releaseMonth, mediaMetadata.releaseMonth) && Objects.equals(this.releaseDay, mediaMetadata.releaseDay) && Objects.equals(this.writer, mediaMetadata.writer) && Objects.equals(this.composer, mediaMetadata.composer) && Objects.equals(this.conductor, mediaMetadata.conductor) && Objects.equals(this.discNumber, mediaMetadata.discNumber) && Objects.equals(this.totalDiscCount, mediaMetadata.totalDiscCount) && Objects.equals(this.genre, mediaMetadata.genre) && Objects.equals(this.compilation, mediaMetadata.compilation) && Objects.equals(this.station, mediaMetadata.station) && Objects.equals(this.mediaType, mediaMetadata.mediaType) && Objects.equals(this.supportedCommands, mediaMetadata.supportedCommands)) {
                if (this.extras == null) {
                    z = true;
                } else {
                    z = false;
                }
                if (mediaMetadata.extras == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z == z3) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        boolean z;
        CharSequence charSequence = this.title;
        CharSequence charSequence2 = this.artist;
        CharSequence charSequence3 = this.albumTitle;
        CharSequence charSequence4 = this.albumArtist;
        CharSequence charSequence5 = this.displayTitle;
        CharSequence charSequence6 = this.subtitle;
        CharSequence charSequence7 = this.description;
        Long l = this.durationMs;
        Integer valueOf = Integer.valueOf(Arrays.hashCode(this.artworkData));
        Integer num = this.artworkDataType;
        Uri uri = this.artworkUri;
        Integer num2 = this.trackNumber;
        Integer num3 = this.totalTrackCount;
        Integer num4 = this.folderType;
        Boolean bool = this.isBrowsable;
        CharSequence charSequence8 = charSequence;
        Boolean bool2 = this.isPlayable;
        Integer num5 = this.recordingYear;
        Integer num6 = this.recordingMonth;
        Integer num7 = this.recordingDay;
        Integer num8 = this.releaseYear;
        Integer num9 = this.releaseMonth;
        Integer num10 = this.releaseDay;
        CharSequence charSequence9 = this.writer;
        CharSequence charSequence10 = this.composer;
        CharSequence charSequence11 = this.conductor;
        Integer num11 = this.discNumber;
        Integer num12 = this.totalDiscCount;
        CharSequence charSequence12 = this.genre;
        CharSequence charSequence13 = this.compilation;
        CharSequence charSequence14 = this.station;
        Integer num13 = this.mediaType;
        if (this.extras == null) {
            z = true;
        } else {
            z = false;
        }
        return Objects.hash(new Object[]{charSequence8, charSequence2, charSequence3, charSequence4, charSequence5, charSequence6, charSequence7, l, null, null, valueOf, num, uri, num2, num3, num4, bool, bool2, num5, num6, num7, num8, num9, num10, charSequence9, charSequence10, charSequence11, num11, num12, charSequence12, charSequence13, charSequence14, num13, Boolean.valueOf(z), this.supportedCommands});
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private MediaMetadata(androidx.media3.common.MediaMetadata.Builder r7) {
        /*
            r6 = this;
            r6.<init>()
            java.lang.Boolean r0 = r7.isBrowsable
            java.lang.Integer r1 = r7.folderType
            java.lang.Integer r2 = r7.mediaType
            r3 = 0
            r4 = -1
            if (r0 == 0) goto L_0x0035
            boolean r5 = r0.booleanValue()
            if (r5 != 0) goto L_0x001e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            goto L_0x0052
        L_0x001e:
            if (r1 == 0) goto L_0x0026
            int r5 = r1.intValue()
            if (r5 != r4) goto L_0x0052
        L_0x0026:
            if (r2 == 0) goto L_0x0030
            int r1 = r2.intValue()
            int r3 = getFolderTypeFromMediaType(r1)
        L_0x0030:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            goto L_0x0052
        L_0x0035:
            if (r1 == 0) goto L_0x0052
            int r0 = r1.intValue()
            if (r0 == r4) goto L_0x003e
            r3 = 1
        L_0x003e:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            if (r3 == 0) goto L_0x0052
            if (r2 != 0) goto L_0x0052
            int r2 = r1.intValue()
            int r2 = getMediaTypeFromFolderType(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L_0x0052:
            java.lang.CharSequence r3 = r7.title
            r6.title = r3
            java.lang.CharSequence r3 = r7.artist
            r6.artist = r3
            java.lang.CharSequence r3 = r7.albumTitle
            r6.albumTitle = r3
            java.lang.CharSequence r3 = r7.albumArtist
            r6.albumArtist = r3
            java.lang.CharSequence r3 = r7.displayTitle
            r6.displayTitle = r3
            java.lang.CharSequence r3 = r7.subtitle
            r6.subtitle = r3
            java.lang.CharSequence r3 = r7.description
            r6.description = r3
            java.lang.Long r3 = r7.durationMs
            r6.durationMs = r3
            androidx.media3.common.Rating unused = r7.getClass()
            androidx.media3.common.Rating unused = r7.getClass()
            byte[] r3 = r7.artworkData
            r6.artworkData = r3
            java.lang.Integer r3 = r7.artworkDataType
            r6.artworkDataType = r3
            android.net.Uri r3 = r7.artworkUri
            r6.artworkUri = r3
            java.lang.Integer r3 = r7.trackNumber
            r6.trackNumber = r3
            java.lang.Integer r3 = r7.totalTrackCount
            r6.totalTrackCount = r3
            r6.folderType = r1
            r6.isBrowsable = r0
            java.lang.Boolean r0 = r7.isPlayable
            r6.isPlayable = r0
            java.lang.Integer r0 = r7.recordingYear
            r6.year = r0
            java.lang.Integer r0 = r7.recordingYear
            r6.recordingYear = r0
            java.lang.Integer r0 = r7.recordingMonth
            r6.recordingMonth = r0
            java.lang.Integer r0 = r7.recordingDay
            r6.recordingDay = r0
            java.lang.Integer r0 = r7.releaseYear
            r6.releaseYear = r0
            java.lang.Integer r0 = r7.releaseMonth
            r6.releaseMonth = r0
            java.lang.Integer r0 = r7.releaseDay
            r6.releaseDay = r0
            java.lang.CharSequence r0 = r7.writer
            r6.writer = r0
            java.lang.CharSequence r0 = r7.composer
            r6.composer = r0
            java.lang.CharSequence r0 = r7.conductor
            r6.conductor = r0
            java.lang.Integer r0 = r7.discNumber
            r6.discNumber = r0
            java.lang.Integer r0 = r7.totalDiscCount
            r6.totalDiscCount = r0
            java.lang.CharSequence r0 = r7.genre
            r6.genre = r0
            java.lang.CharSequence r0 = r7.compilation
            r6.compilation = r0
            java.lang.CharSequence r0 = r7.station
            r6.station = r0
            r6.mediaType = r2
            F2.U r0 = r7.supportedCommands
            r6.supportedCommands = r0
            android.os.Bundle r7 = r7.extras
            r6.extras = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.MediaMetadata.<init>(androidx.media3.common.MediaMetadata$Builder):void");
    }
}
