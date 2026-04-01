package com.samsung.android.gallery.module.analyticsquery;

import c0.C0086a;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAnalysisStatus {
    static final SearchAnalysisStatus EMPTY = new SearchAnalysisStatus();
    long apt;
    int n;
    int np;
    long npt;
    int op;
    int sp;
    int t;

    public static SearchAnalysisStatus of(String str) {
        return (SearchAnalysisStatus) GsonTool.fromJsonString(SearchAnalysisStatus.class, str);
    }

    public int getAnalyzedRatio() {
        return this.np;
    }

    public String toHtmlTableRow() {
        StringBuilder sb2 = new StringBuilder("<td>");
        sb2.append(this.t);
        sb2.append("</td> <td>");
        sb2.append(this.n);
        sb2.append("</td> <td>");
        return C0086a.l(sb2, this.np, "%</td>");
    }

    public String toSimpleString() {
        StringBuilder sb2 = new StringBuilder("[");
        sb2.append(this.n);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.np);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.npt);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.sp);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, this.op, "]");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("{");
        sb2.append(this.t);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.n);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.np);
        sb2.append("%,");
        sb2.append(this.npt);
        sb2.append("ms,");
        sb2.append(this.sp);
        sb2.append("%,");
        sb2.append(this.op);
        sb2.append("%,");
        return C0212a.o(sb2, this.apt, "s}");
    }
}
