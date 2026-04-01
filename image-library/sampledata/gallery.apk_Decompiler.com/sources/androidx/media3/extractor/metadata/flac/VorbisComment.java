package androidx.media3.extractor.metadata.flac;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import i.C0212a;
import o1.C0246a;
import og.k;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VorbisComment implements Metadata.Entry {
    public final String key;
    public final String value;

    public VorbisComment(String str, String str2) {
        this.key = k.T(str);
        this.value = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            VorbisComment vorbisComment = (VorbisComment) obj;
            if (!this.key.equals(vorbisComment.key) || !this.value.equals(vorbisComment.value)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode() + C0212a.d(527, 31, this.key);
    }

    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        String str = this.key;
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1935137620:
                if (str.equals("TOTALTRACKS")) {
                    c5 = 0;
                    break;
                }
                break;
            case -215998278:
                if (str.equals("TOTALDISCS")) {
                    c5 = 1;
                    break;
                }
                break;
            case -113312716:
                if (str.equals("TRACKNUMBER")) {
                    c5 = 2;
                    break;
                }
                break;
            case 62359119:
                if (str.equals("ALBUM")) {
                    c5 = 3;
                    break;
                }
                break;
            case 67703139:
                if (str.equals("GENRE")) {
                    c5 = 4;
                    break;
                }
                break;
            case 79833656:
                if (str.equals("TITLE")) {
                    c5 = 5;
                    break;
                }
                break;
            case 428414940:
                if (str.equals("DESCRIPTION")) {
                    c5 = 6;
                    break;
                }
                break;
            case 993300766:
                if (str.equals("DISCNUMBER")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1746739798:
                if (str.equals("ALBUMARTIST")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1939198791:
                if (str.equals("ARTIST")) {
                    c5 = 9;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                Integer n02 = C0246a.n0(this.value);
                if (n02 != null) {
                    builder.setTotalTrackCount(n02);
                    return;
                }
                return;
            case 1:
                Integer n03 = C0246a.n0(this.value);
                if (n03 != null) {
                    builder.setTotalDiscCount(n03);
                    return;
                }
                return;
            case 2:
                Integer n04 = C0246a.n0(this.value);
                if (n04 != null) {
                    builder.setTrackNumber(n04);
                    return;
                }
                return;
            case 3:
                builder.setAlbumTitle(this.value);
                return;
            case 4:
                builder.setGenre(this.value);
                return;
            case 5:
                builder.setTitle(this.value);
                return;
            case 6:
                builder.setDescription(this.value);
                return;
            case 7:
                Integer n05 = C0246a.n0(this.value);
                if (n05 != null) {
                    builder.setDiscNumber(n05);
                    return;
                }
                return;
            case 8:
                builder.setAlbumArtist(this.value);
                return;
            case 9:
                builder.setArtist(this.value);
                return;
            default:
                return;
        }
    }

    public String toString() {
        return "VC: " + this.key + "=" + this.value;
    }
}
