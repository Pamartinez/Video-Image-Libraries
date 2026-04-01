package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.Subtitle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class WebvttSubtitle implements Subtitle {
    private final List<WebvttCueInfo> cueInfos;
    private final long[] cueTimesUs;
    private final long[] sortedCueTimesUs;

    public WebvttSubtitle(List<WebvttCueInfo> list) {
        this.cueInfos = Collections.unmodifiableList(new ArrayList(list));
        this.cueTimesUs = new long[(list.size() * 2)];
        for (int i2 = 0; i2 < list.size(); i2++) {
            WebvttCueInfo webvttCueInfo = list.get(i2);
            int i7 = i2 * 2;
            long[] jArr = this.cueTimesUs;
            jArr[i7] = webvttCueInfo.startTimeUs;
            jArr[i7 + 1] = webvttCueInfo.endTimeUs;
        }
        long[] jArr2 = this.cueTimesUs;
        long[] copyOf = Arrays.copyOf(jArr2, jArr2.length);
        this.sortedCueTimesUs = copyOf;
        Arrays.sort(copyOf);
    }

    public List<Cue> getCues(long j2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < this.cueInfos.size(); i2++) {
            long[] jArr = this.cueTimesUs;
            int i7 = i2 * 2;
            if (jArr[i7] <= j2 && j2 < jArr[i7 + 1]) {
                WebvttCueInfo webvttCueInfo = this.cueInfos.get(i2);
                Cue cue = webvttCueInfo.cue;
                if (cue.line == -3.4028235E38f) {
                    arrayList2.add(webvttCueInfo);
                } else {
                    arrayList.add(cue);
                }
            }
        }
        Collections.sort(arrayList2, new a(1));
        for (int i8 = 0; i8 < arrayList2.size(); i8++) {
            arrayList.add(((WebvttCueInfo) arrayList2.get(i8)).cue.buildUpon().setLine((float) (-1 - i8), 1).build());
        }
        return arrayList;
    }

    public long getEventTime(int i2) {
        boolean z;
        boolean z3 = false;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (i2 < this.sortedCueTimesUs.length) {
            z3 = true;
        }
        Assertions.checkArgument(z3);
        return this.sortedCueTimesUs[i2];
    }

    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    public int getNextEventTimeIndex(long j2) {
        int binarySearchCeil = Util.binarySearchCeil(this.sortedCueTimesUs, j2, false, false);
        if (binarySearchCeil < this.sortedCueTimesUs.length) {
            return binarySearchCeil;
        }
        return -1;
    }
}
