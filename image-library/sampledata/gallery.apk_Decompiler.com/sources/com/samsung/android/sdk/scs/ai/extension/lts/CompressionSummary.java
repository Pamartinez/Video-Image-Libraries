package com.samsung.android.sdk.scs.ai.extension.lts;

import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CompressionSummary {

    /* renamed from: N  reason: collision with root package name */
    private int f1657N;
    private final String TAG;
    private List<String> chunkedSummarySentence;
    private List<Integer> dividedCount;
    private Queue<Integer> dividedCountQueue;
    private Queue<Queue<Integer>> dividedNQueue;
    private Queue<String> summarySentence;

    public CompressionSummary() {
        this.TAG = "CompressionSummary";
        this.dividedCount = new ArrayList();
        this.dividedCountQueue = new LinkedList();
        this.dividedNQueue = new LinkedList();
        this.summarySentence = new LinkedList();
        this.chunkedSummarySentence = new ArrayList();
        this.f1657N = 4;
    }

    public int addSentence(String str) {
        this.summarySentence.add(str);
        return this.summarySentence.size();
    }

    public Queue<Queue<Integer>> balanceGroup() {
        int i2;
        int i7;
        this.dividedNQueue.clear();
        for (int i8 = 0; i8 < this.dividedCount.size(); i8++) {
            LinkedList linkedList = new LinkedList();
            this.dividedNQueue.add(linkedList);
            int intValue = this.dividedCount.get(i8).intValue();
            int i10 = this.f1657N;
            if (intValue > i10) {
                if (((double) (i10 - (this.dividedCount.get(i8).intValue() % this.f1657N))) > Math.ceil(((double) this.dividedCount.get(i8).intValue()) / ((double) this.f1657N))) {
                    i10 = (int) Math.ceil(((double) this.dividedCount.get(i8).intValue()) / Math.ceil(((double) this.dividedCount.get(i8).intValue()) / ((double) this.f1657N)));
                }
                int intValue2 = this.dividedCount.get(i8).intValue();
                while (intValue2 >= 0) {
                    if (intValue2 > i10) {
                        if (intValue2 % i10 == 0) {
                            i7 = i10;
                        } else {
                            i7 = i10 - 1;
                        }
                        int i11 = i7;
                        i2 = intValue2 - i7;
                        intValue2 = i11;
                    } else {
                        i2 = -intValue2;
                    }
                    linkedList.add(Integer.valueOf(intValue2));
                    intValue2 = i2;
                }
            } else {
                Integer num = this.dividedCount.get(i8);
                num.intValue();
                linkedList.add(num);
            }
        }
        Log.i("CompressionSummary", "dividedNQueue " + this.dividedNQueue);
        return this.dividedNQueue;
    }

    public List<Integer> divideAndCeil(int i2) {
        this.dividedCount.clear();
        this.dividedCountQueue.clear();
        if (i2 <= 0) {
            return this.dividedCount;
        }
        this.dividedCount.add(Integer.valueOf(i2));
        int i7 = i2;
        while (i7 > 1) {
            i7 = (int) Math.ceil(((double) i7) / ((double) this.f1657N));
            this.dividedCount.add(Integer.valueOf(i7));
        }
        if (i2 <= this.f1657N) {
            this.dividedCount.add(1);
        }
        Log.i("CompressionSummary", "dividedCount" + this.dividedCount);
        this.dividedCountQueue.addAll(this.dividedCount);
        return this.dividedCount;
    }

    public List<String> getChunkedSummarySentence() {
        return this.chunkedSummarySentence;
    }

    public List<String> getChunkedSummarySentenceList() {
        this.chunkedSummarySentence.clear();
        Queue poll = this.dividedNQueue.poll();
        Integer poll2 = this.dividedCountQueue.poll();
        if (poll2.intValue() != this.summarySentence.size()) {
            Log.e("CompressionSummary", "size error Text size " + this.summarySentence.size() + " Q size " + poll2);
        }
        if (poll == null) {
            return new ArrayList();
        }
        int i2 = 0;
        while (!poll.isEmpty()) {
            Integer num = (Integer) poll.poll();
            this.chunkedSummarySentence.add("");
            for (int i7 = 0; i7 < num.intValue(); i7++) {
                this.chunkedSummarySentence.set(i2, this.chunkedSummarySentence.get(i2) + this.summarySentence.poll() + " ");
            }
            i2++;
        }
        this.summarySentence.clear();
        return this.chunkedSummarySentence;
    }

    public List<Integer> getDividedCount() {
        return this.dividedCount;
    }

    public Queue<Queue<Integer>> getDividedNQueue() {
        return this.dividedNQueue;
    }

    public int getSeparateN() {
        return this.f1657N;
    }

    public String getSummary() {
        StringBuilder sb2 = new StringBuilder();
        for (String append : getSummarySentence()) {
            sb2.append(append);
        }
        return sb2.toString();
    }

    public Queue<String> getSummarySentence() {
        return new LinkedList(this.summarySentence);
    }

    public void setChunkedSummarySentence(List<String> list) {
        this.chunkedSummarySentence = list;
    }

    public void setDividedCount(List<Integer> list) {
        this.dividedCount = list;
        this.dividedCountQueue.addAll(list);
    }

    public void setDividedNQueue(Queue<Queue<Integer>> queue) {
        this.dividedNQueue = queue;
    }

    public CompressionSummary(int i2) {
        this.TAG = "CompressionSummary";
        this.dividedCount = new ArrayList();
        this.dividedCountQueue = new LinkedList();
        this.dividedNQueue = new LinkedList();
        this.summarySentence = new LinkedList();
        this.chunkedSummarySentence = new ArrayList();
        this.f1657N = i2;
    }
}
