package com.samsung.android.gallery.module.debugger;

import a9.C0581a;
import android.os.Bundle;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PerformanceTracker extends Subscriber {
    private static long sAppCreateEnd;
    private static long sAppCreateStart;
    private final long mOnConstructTime;
    private final long mOnCreateTime;
    private long mThumbLoadStartTime;

    public PerformanceTracker(Blackboard blackboard, long j2, long j3) {
        super(blackboard);
        this.mOnConstructTime = j2;
        this.mOnCreateTime = j3;
    }

    /* access modifiers changed from: private */
    public void onMediaDataFullSwapDone(Object obj, Bundle bundle) {
        long longValue = ((Long) obj).longValue();
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "MediaDataFullSwapDone {onCreate~now=" + (longValue - this.mOnCreateTime) + "}");
    }

    /* access modifiers changed from: private */
    public void onMediaDataLoadDone(Object obj, Bundle bundle) {
        long longValue = ((Long) obj).longValue();
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "MediaDataLoadDone {onCreate~now=" + (longValue - this.mOnCreateTime) + ", FakeLoadDone~now=" + (longValue - readBlackboard("debug://TimeDoneFakeLoad", 0)) + "}");
    }

    /* access modifiers changed from: private */
    public void onQuickViewDone(Object obj, Bundle bundle) {
        long longValue = ((Long) obj).longValue();
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "QuickViewDone {onCreate~now=" + (longValue - this.mOnCreateTime) + ",Construct~now=" + (longValue - this.mOnConstructTime) + "}");
        Trace.dumpLog(longValue);
    }

    /* access modifiers changed from: private */
    public void onThumbnailLoadDone(Object obj, Bundle bundle) {
        long j2;
        Object obj2 = obj;
        if (this.mBlackboard.isEmpty("debug://thumbLoadDoneTime")) {
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "ThumbnailLoadDone skip. no list {" + (this.mOnCreateTime - this.mOnConstructTime) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + obj2 + "}");
            return;
        }
        long readBlackboard = readBlackboard("debug://thumbLoadDoneTime", System.currentTimeMillis());
        try {
            StringBuilder sb2 = new StringBuilder(512);
            sb2.append("ThumbLoadingOnThread=");
            sb2.append(obj2);
            sb2.append(10);
            sb2.append("Activity#onCreate{");
            sb2.append("Construct~onCreate=");
            j2 = readBlackboard;
            try {
                sb2.append(this.mOnCreateTime - this.mOnConstructTime);
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append("~onThumbBegin=");
                sb2.append(this.mThumbLoadStartTime - this.mOnCreateTime);
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append("~onThumbEnd=");
                sb2.append(j2 - this.mOnCreateTime);
                sb2.append("(+");
                sb2.append(j2 - this.mThumbLoadStartTime);
                sb2.append("), ");
                long readBlackboard2 = readBlackboard("debug://TimeQueryStart", this.mOnCreateTime);
                long j3 = this.mOnCreateTime;
                long j8 = readBlackboard2 - j3;
                long readBlackboard3 = readBlackboard("debug://TimeDoneQuery", j3) - this.mOnCreateTime;
                String str = "debug://TimeDoneFakeLoad";
                long j10 = readBlackboard3 - j8;
                String str2 = "debug://TimeInflateQuery";
                sb2.append("~QueryBegin=");
                sb2.append(j8);
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append("~QueryEnd=");
                sb2.append(readBlackboard3);
                sb2.append("(+");
                sb2.append(j10);
                sb2.append("), ");
                if (!this.mBlackboard.isEmpty("debug://TimeInflateStart")) {
                    String str3 = str2;
                    if (!this.mBlackboard.isEmpty(str3)) {
                        sb2.append("ViewPool=");
                        sb2.append(readBlackboard(str3, 0) - readBlackboard("debug://TimeInflateStart", 0));
                        sb2.append(ArcCommonLog.TAG_COMMA);
                    }
                }
                sb2.append("~onResumeEnd=");
                sb2.append(readBlackboard("debug://TimeDoneOnResume", this.mOnCreateTime) - this.mOnCreateTime);
                sb2.append(ArcCommonLog.TAG_COMMA);
                String str4 = str;
                if (!this.mBlackboard.isEmpty(str4)) {
                    sb2.append("~onFakeLoadEnd=");
                    sb2.append(readBlackboard(str4, this.mOnCreateTime) - this.mOnCreateTime);
                    sb2.append(ArcCommonLog.TAG_COMMA);
                }
                sb2.replace(sb2.length() - 2, sb2.length(), "}");
                if (sAppCreateStart > 0) {
                    sb2.append("\nApplication{");
                    sb2.append("Construct~onCreateEnd=");
                    sb2.append(sAppCreateEnd - sAppCreateStart);
                    sb2.append(ArcCommonLog.TAG_COMMA);
                    sb2.append("onCreate~onThumbEnd=");
                    sb2.append(j2 - sAppCreateStart);
                    sb2.append("}");
                    sAppCreateStart = 0;
                }
                String sb3 = sb2.toString();
                StringCompat stringCompat2 = this.TAG;
                Log.p(stringCompat2, "ThumbnailLoadDone " + sb3);
                if (j10 >= 2000) {
                    DebugLogger.getInstance().lambda$apply$0(this.TAG.toString(), sb3);
                }
            } catch (Exception e) {
                e = e;
                StringCompat stringCompat3 = this.TAG;
                Log.e(stringCompat3, "ThumbnailLoadDone failed. e=" + e.getMessage());
                e.printStackTrace();
                Trace.dumpLog(j2);
            }
        } catch (Exception e7) {
            e = e7;
            j2 = readBlackboard;
            StringCompat stringCompat32 = this.TAG;
            Log.e(stringCompat32, "ThumbnailLoadDone failed. e=" + e.getMessage());
            e.printStackTrace();
            Trace.dumpLog(j2);
        }
        Trace.dumpLog(j2);
    }

    /* access modifiers changed from: private */
    public void onThumbnailLoadStart(Object obj, Bundle bundle) {
        this.mThumbLoadStartTime = ((Long) obj).longValue();
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "ThumbnailLoadStart {onCreate~now=" + (this.mThumbLoadStartTime - this.mOnCreateTime) + "}");
    }

    private long readBlackboard(String str, long j2) {
        Long l = (Long) this.mBlackboard.read(str);
        if (l != null) {
            return l.longValue();
        }
        return j2;
    }

    public static void setAppCreateEnd(long j2) {
        sAppCreateEnd = j2;
    }

    public static void setAppCreateStart(long j2) {
        sAppCreateStart = j2;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("lifecycle://on_thumbnail_load_start", new C0581a(this, 0)));
        arrayList.add(new SubscriberInfo("lifecycle://on_thumbnail_load_done", new C0581a(this, 1)));
        arrayList.add(new SubscriberInfo("lifecycle://onQuickViewDone", new C0581a(this, 2)));
        arrayList.add(new SubscriberInfo("debug://TimeDoneFullLoad", new C0581a(this, 3)));
        arrayList.add(new SubscriberInfo("lifecycle://onTimeLineFullSwapDone", new C0581a(this, 4)));
    }
}
