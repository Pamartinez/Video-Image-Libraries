package androidx.media3.extractor.text;

import androidx.media3.common.Format;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.dvb.DvbParser;
import androidx.media3.extractor.text.pgs.PgsParser;
import androidx.media3.extractor.text.ssa.SsaParser;
import androidx.media3.extractor.text.subrip.SubripParser;
import androidx.media3.extractor.text.ttml.TtmlParser;
import androidx.media3.extractor.text.tx3g.Tx3gParser;
import androidx.media3.extractor.text.vobsub.VobsubParser;
import androidx.media3.extractor.text.webvtt.Mp4WebvttParser;
import androidx.media3.extractor.text.webvtt.WebvttParser;
import i.C0212a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultSubtitleParserFactory implements SubtitleParser.Factory {
    public SubtitleParser create(Format format) {
        String str = format.sampleMimeType;
        if (str != null) {
            char c5 = 65535;
            switch (str.hashCode()) {
                case -1351681404:
                    if (str.equals("application/dvbsubs")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -1248334819:
                    if (str.equals("application/pgs")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case -1026075066:
                    if (str.equals("application/x-mp4-vtt")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case -1004728940:
                    if (str.equals("text/vtt")) {
                        c5 = 3;
                        break;
                    }
                    break;
                case 691401887:
                    if (str.equals("application/x-quicktime-tx3g")) {
                        c5 = 4;
                        break;
                    }
                    break;
                case 822864842:
                    if (str.equals("text/x-ssa")) {
                        c5 = 5;
                        break;
                    }
                    break;
                case 1157994102:
                    if (str.equals("application/vobsub")) {
                        c5 = 6;
                        break;
                    }
                    break;
                case 1668750253:
                    if (str.equals("application/x-subrip")) {
                        c5 = 7;
                        break;
                    }
                    break;
                case 1693976202:
                    if (str.equals("application/ttml+xml")) {
                        c5 = 8;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    return new DvbParser(format.initializationData);
                case 1:
                    return new PgsParser();
                case 2:
                    return new Mp4WebvttParser();
                case 3:
                    return new WebvttParser();
                case 4:
                    return new Tx3gParser(format.initializationData);
                case 5:
                    return new SsaParser(format.initializationData);
                case 6:
                    return new VobsubParser(format.initializationData);
                case 7:
                    return new SubripParser();
                case 8:
                    return new TtmlParser();
            }
        }
        throw new IllegalArgumentException(C0212a.l("Unsupported MIME type: ", str));
    }

    public int getCueReplacementBehavior(Format format) {
        String str = format.sampleMimeType;
        if (str != null) {
            char c5 = 65535;
            switch (str.hashCode()) {
                case -1351681404:
                    if (str.equals("application/dvbsubs")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -1248334819:
                    if (str.equals("application/pgs")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case -1026075066:
                    if (str.equals("application/x-mp4-vtt")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case -1004728940:
                    if (str.equals("text/vtt")) {
                        c5 = 3;
                        break;
                    }
                    break;
                case 691401887:
                    if (str.equals("application/x-quicktime-tx3g")) {
                        c5 = 4;
                        break;
                    }
                    break;
                case 822864842:
                    if (str.equals("text/x-ssa")) {
                        c5 = 5;
                        break;
                    }
                    break;
                case 1157994102:
                    if (str.equals("application/vobsub")) {
                        c5 = 6;
                        break;
                    }
                    break;
                case 1668750253:
                    if (str.equals("application/x-subrip")) {
                        c5 = 7;
                        break;
                    }
                    break;
                case 1693976202:
                    if (str.equals("application/ttml+xml")) {
                        c5 = 8;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                case 1:
                case 2:
                    return 2;
                case 3:
                    return 1;
                case 4:
                    return 2;
                case 5:
                    return 1;
                case 6:
                    return 2;
                case 7:
                case 8:
                    return 1;
            }
        }
        throw new IllegalArgumentException(C0212a.l("Unsupported MIME type: ", str));
    }

    public boolean supportsFormat(Format format) {
        String str = format.sampleMimeType;
        if (Objects.equals(str, "text/x-ssa") || Objects.equals(str, "text/vtt") || Objects.equals(str, "application/x-mp4-vtt") || Objects.equals(str, "application/x-subrip") || Objects.equals(str, "application/x-quicktime-tx3g") || Objects.equals(str, "application/pgs") || Objects.equals(str, "application/vobsub") || Objects.equals(str, "application/dvbsubs") || Objects.equals(str, "application/ttml+xml")) {
            return true;
        }
        return false;
    }
}
