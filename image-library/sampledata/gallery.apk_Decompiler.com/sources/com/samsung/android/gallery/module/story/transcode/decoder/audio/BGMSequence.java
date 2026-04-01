package com.samsung.android.gallery.module.story.transcode.decoder.audio;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BGMSequence {
    private final List<SequenceInfo> mSequenceList = new Vector();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SequenceInfo {
        public long end;
        public int index;
        public long start;

        public /* synthetic */ SequenceInfo(int i2) {
            this();
        }

        private SequenceInfo() {
        }
    }

    private int addBGMBody(ArrayList<BGMData> arrayList, int i2, int i7) {
        int i8;
        int size = arrayList.size() - 2;
        int i10 = 1;
        while (i2 > 0) {
            SequenceInfo sequenceInfo = new SequenceInfo(0);
            int i11 = i10 % size;
            if (i11 > 0) {
                i8 = i11;
            } else {
                i8 = size;
            }
            sequenceInfo.index = i8;
            long j2 = this.mSequenceList.get(i10 - 1).end;
            sequenceInfo.start = j2;
            sequenceInfo.end = (((long) arrayList.get(sequenceInfo.index).getDuration()) * 1000) + j2;
            this.mSequenceList.add(sequenceInfo);
            if (i11 == 0) {
                i2--;
            }
            i10++;
        }
        for (int i12 = 1; i12 <= i7; i12++) {
            SequenceInfo sequenceInfo2 = new SequenceInfo(0);
            sequenceInfo2.index = i12;
            long j3 = this.mSequenceList.get(i10 - 1).end;
            sequenceInfo2.start = j3;
            sequenceInfo2.end = (((long) arrayList.get(sequenceInfo2.index).getDuration()) * 1000) + j3;
            this.mSequenceList.add(sequenceInfo2);
            i10++;
        }
        return i10;
    }

    private void addBGMEnding(ArrayList<BGMData> arrayList, int i2, long j2) {
        SequenceInfo sequenceInfo = new SequenceInfo(0);
        sequenceInfo.index = arrayList.size() - 1;
        long j3 = this.mSequenceList.get(i2 - 1).end;
        sequenceInfo.start = j3;
        long duration = (((long) arrayList.get(sequenceInfo.index).getDuration()) * 1000) + j3;
        sequenceInfo.end = duration;
        if (duration > j2) {
            sequenceInfo.end = j2;
        }
        this.mSequenceList.add(sequenceInfo);
    }

    private void addBGMIntro(ArrayList<BGMData> arrayList) {
        SequenceInfo sequenceInfo = new SequenceInfo(0);
        sequenceInfo.index = 0;
        sequenceInfo.start = 0;
        sequenceInfo.end = ((long) arrayList.get(0).getDuration()) * 1000;
        this.mSequenceList.add(sequenceInfo);
    }

    public long getDuration(int i2) {
        return this.mSequenceList.get(i2).end - this.mSequenceList.get(i2).start;
    }

    public int getIndex(int i2) {
        return this.mSequenceList.get(i2).index;
    }

    public void init(ArrayList<BGMData> arrayList, int i2, int i7, long j2) {
        addBGMIntro(arrayList);
        addBGMEnding(arrayList, addBGMBody(arrayList, i2, i7), j2);
    }

    public void release() {
        this.mSequenceList.clear();
    }

    public int size() {
        return this.mSequenceList.size();
    }
}
