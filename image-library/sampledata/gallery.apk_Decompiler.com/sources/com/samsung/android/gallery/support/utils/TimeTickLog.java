package com.samsung.android.gallery.support.utils;

import L5.b;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimeTickLog {
    private long mCurrTime;
    private long mDiff;
    private final ArrayList<String> mLogs;
    private final long mStartTime;
    private final ArrayList<Long> mTicks;
    private String mTitle;

    public TimeTickLog() {
        this((String) null);
    }

    public void appendExtra(String str) {
        this.mTitle = C0212a.p(new StringBuilder(), this.mTitle, str);
    }

    public long getElapsedTime() {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = currentTimeMillis - this.mCurrTime;
        this.mCurrTime = currentTimeMillis;
        return j2;
    }

    public String summary() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(String.valueOf(this.mCurrTime - this.mStartTime));
        if (this.mTicks.size() > 1) {
            str = C0086a.m(new StringBuilder("("), (String) this.mTicks.stream().map(new b(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), ')');
        } else {
            str = "";
        }
        sb2.append(str);
        return sb2.toString();
    }

    public void tick() {
        long currentTimeMillis = System.currentTimeMillis();
        this.mTicks.add(Long.valueOf(currentTimeMillis - this.mCurrTime));
        this.mCurrTime = currentTimeMillis;
    }

    public long tock(long j2) {
        return tock(j2, false);
    }

    public long tockWithLog(long j2) {
        return tock(j2, PerformanceLog.isEnabled());
    }

    public TimeTickLog(String str) {
        this.mLogs = new ArrayList<>();
        this.mTicks = new ArrayList<>();
        this.mTitle = str;
        long currentTimeMillis = System.currentTimeMillis();
        this.mCurrTime = currentTimeMillis;
        this.mStartTime = currentTimeMillis;
    }

    private synchronized long tock(long j2, boolean z) {
        try {
            long currentTimeMillis = System.currentTimeMillis() - this.mStartTime;
            this.mDiff = currentTimeMillis;
            if (currentTimeMillis > j2) {
                Iterator<String> it = this.mLogs.iterator();
                while (it.hasNext()) {
                    Log.p("TimeTickLog", it.next());
                }
                String str = "[" + this.mTitle + "] +" + this.mDiff;
                Log.p("TimeTickLog", str);
                if (z) {
                    FileLogger.add(str);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mDiff;
    }

    public void tick(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<String> arrayList = this.mLogs;
        StringBuilder sb2 = new StringBuilder("[");
        C0086a.z(sb2, this.mTitle, "] [", str, "] *");
        sb2.append(currentTimeMillis - this.mCurrTime);
        arrayList.add(sb2.toString());
        this.mCurrTime = currentTimeMillis;
    }
}
