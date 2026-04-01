package com.samsung.android.app.sdk.deepsky.objectcapture.video;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMPanelString;", "", "InProgressString", "", "cancelString", "completedString", "closeString", "viewString", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInProgressString", "()Ljava/lang/String;", "getCancelString", "getCompletedString", "getCloseString", "getViewString", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GPPMPanelString {
    private final String InProgressString;
    private final String cancelString;
    private final String closeString;
    private final String completedString;
    private final String viewString;

    public GPPMPanelString() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, 31, (e) null);
    }

    public static /* synthetic */ GPPMPanelString copy$default(GPPMPanelString gPPMPanelString, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gPPMPanelString.InProgressString;
        }
        if ((i2 & 2) != 0) {
            str2 = gPPMPanelString.cancelString;
        }
        if ((i2 & 4) != 0) {
            str3 = gPPMPanelString.completedString;
        }
        if ((i2 & 8) != 0) {
            str4 = gPPMPanelString.closeString;
        }
        if ((i2 & 16) != 0) {
            str5 = gPPMPanelString.viewString;
        }
        String str6 = str4;
        String str7 = str5;
        return gPPMPanelString.copy(str, str2, str3, str6, str7);
    }

    public final String component1() {
        return this.InProgressString;
    }

    public final String component2() {
        return this.cancelString;
    }

    public final String component3() {
        return this.completedString;
    }

    public final String component4() {
        return this.closeString;
    }

    public final String component5() {
        return this.viewString;
    }

    public final GPPMPanelString copy(String str, String str2, String str3, String str4, String str5) {
        j.e(str, "InProgressString");
        j.e(str2, "cancelString");
        j.e(str3, "completedString");
        j.e(str4, "closeString");
        j.e(str5, "viewString");
        return new GPPMPanelString(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GPPMPanelString)) {
            return false;
        }
        GPPMPanelString gPPMPanelString = (GPPMPanelString) obj;
        if (j.a(this.InProgressString, gPPMPanelString.InProgressString) && j.a(this.cancelString, gPPMPanelString.cancelString) && j.a(this.completedString, gPPMPanelString.completedString) && j.a(this.closeString, gPPMPanelString.closeString) && j.a(this.viewString, gPPMPanelString.viewString)) {
            return true;
        }
        return false;
    }

    public final String getCancelString() {
        return this.cancelString;
    }

    public final String getCloseString() {
        return this.closeString;
    }

    public final String getCompletedString() {
        return this.completedString;
    }

    public final String getInProgressString() {
        return this.InProgressString;
    }

    public final String getViewString() {
        return this.viewString;
    }

    public int hashCode() {
        return this.viewString.hashCode() + C0212a.d(C0212a.d(C0212a.d(this.InProgressString.hashCode() * 31, 31, this.cancelString), 31, this.completedString), 31, this.closeString);
    }

    public String toString() {
        String str = this.InProgressString;
        String str2 = this.cancelString;
        String str3 = this.completedString;
        String str4 = this.closeString;
        String str5 = this.viewString;
        StringBuilder q = C0086a.q("GPPMPanelString(InProgressString=", str, ", cancelString=", str2, ", completedString=");
        C0086a.z(q, str3, ", closeString=", str4, ", viewString=");
        return C0212a.p(q, str5, ")");
    }

    public GPPMPanelString(String str, String str2, String str3, String str4, String str5) {
        j.e(str, "InProgressString");
        j.e(str2, "cancelString");
        j.e(str3, "completedString");
        j.e(str4, "closeString");
        j.e(str5, "viewString");
        this.InProgressString = str;
        this.cancelString = str2;
        this.completedString = str3;
        this.closeString = str4;
        this.viewString = str5;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GPPMPanelString(String str, String str2, String str3, String str4, String str5, int i2, e eVar) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? "" : str5);
    }
}
