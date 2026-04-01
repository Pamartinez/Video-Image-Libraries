package androidx.media3.extractor.text.ttml;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.Subtitle;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TtmlSubtitle implements Subtitle {
    private final long[] eventTimesUs;
    private final Map<String, TtmlStyle> globalStyles;
    private final Map<String, String> imageMap;
    private final Map<String, TtmlRegion> regionMap;
    private final TtmlNode root;

    public TtmlSubtitle(TtmlNode ttmlNode, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2, Map<String, String> map3) {
        Map<String, TtmlStyle> map4;
        this.root = ttmlNode;
        this.regionMap = map2;
        this.imageMap = map3;
        if (map != null) {
            map4 = Collections.unmodifiableMap(map);
        } else {
            map4 = Collections.EMPTY_MAP;
        }
        this.globalStyles = map4;
        this.eventTimesUs = ttmlNode.getEventTimesUs();
    }

    public List<Cue> getCues(long j2) {
        return this.root.getCues(j2, this.globalStyles, this.regionMap, this.imageMap);
    }

    public long getEventTime(int i2) {
        return this.eventTimesUs[i2];
    }

    public int getEventTimeCount() {
        return this.eventTimesUs.length;
    }

    public int getNextEventTimeIndex(long j2) {
        int binarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j2, false, false);
        if (binarySearchCeil < this.eventTimesUs.length) {
            return binarySearchCeil;
        }
        return -1;
    }
}
