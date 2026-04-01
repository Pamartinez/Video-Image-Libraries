package com.samsung.android.gallery.crossapp.function;

import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.Logger;
import java.time.LocalDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001b\b\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0010\"\u0004\b\u0019\u0010\u001aR$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR$\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\"\u0010\u0010\"\u0004\b#\u0010\u001aR$\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0017\u001a\u0004\b$\u0010\u0010\"\u0004\b%\u0010\u001aR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0017\u001a\u0004\b&\u0010\u0010\"\u0004\b'\u0010\u001aR$\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0017\u001a\u0004\b(\u0010\u0010\"\u0004\b)\u0010\u001aR$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\f\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006/"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/FindContentsParams;", "", "", "queryString", "Ljava/time/LocalDateTime;", "startTime", "endTime", "city", "country", "countryCode", "orderType", "", "maxCount", "<init>", "(Ljava/lang/String;Ljava/time/LocalDateTime;Ljava/time/LocalDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getQueryString", "setQueryString", "(Ljava/lang/String;)V", "Ljava/time/LocalDateTime;", "getStartTime", "()Ljava/time/LocalDateTime;", "setStartTime", "(Ljava/time/LocalDateTime;)V", "getEndTime", "setEndTime", "getCity", "setCity", "getCountry", "setCountry", "getCountryCode", "setCountryCode", "getOrderType", "setOrderType", "Ljava/lang/Integer;", "getMaxCount", "()Ljava/lang/Integer;", "setMaxCount", "(Ljava/lang/Integer;)V", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindContentsParams {
    private String city;
    private String country;
    private String countryCode;
    private LocalDateTime endTime;
    private Integer maxCount;
    private String orderType;
    private String queryString;
    private LocalDateTime startTime;

    public FindContentsParams(String str, LocalDateTime localDateTime, LocalDateTime localDateTime2, String str2, String str3, String str4, String str5, Integer num) {
        j.e(str, "queryString");
        this.queryString = str;
        this.startTime = localDateTime;
        this.endTime = localDateTime2;
        this.city = str2;
        this.country = str3;
        this.countryCode = str4;
        this.orderType = str5;
        this.maxCount = num;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FindContentsParams)) {
            return false;
        }
        FindContentsParams findContentsParams = (FindContentsParams) obj;
        if (j.a(this.queryString, findContentsParams.queryString) && j.a(this.startTime, findContentsParams.startTime) && j.a(this.endTime, findContentsParams.endTime) && j.a(this.city, findContentsParams.city) && j.a(this.country, findContentsParams.country) && j.a(this.countryCode, findContentsParams.countryCode) && j.a(this.orderType, findContentsParams.orderType) && j.a(this.maxCount, findContentsParams.maxCount)) {
            return true;
        }
        return false;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final LocalDateTime getEndTime() {
        return this.endTime;
    }

    public final Integer getMaxCount() {
        return this.maxCount;
    }

    public final String getOrderType() {
        return this.orderType;
    }

    public final String getQueryString() {
        return this.queryString;
    }

    public final LocalDateTime getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int hashCode = this.queryString.hashCode() * 31;
        LocalDateTime localDateTime = this.startTime;
        int i13 = 0;
        if (localDateTime == null) {
            i2 = 0;
        } else {
            i2 = localDateTime.hashCode();
        }
        int i14 = (hashCode + i2) * 31;
        LocalDateTime localDateTime2 = this.endTime;
        if (localDateTime2 == null) {
            i7 = 0;
        } else {
            i7 = localDateTime2.hashCode();
        }
        int i15 = (i14 + i7) * 31;
        String str = this.city;
        if (str == null) {
            i8 = 0;
        } else {
            i8 = str.hashCode();
        }
        int i16 = (i15 + i8) * 31;
        String str2 = this.country;
        if (str2 == null) {
            i10 = 0;
        } else {
            i10 = str2.hashCode();
        }
        int i17 = (i16 + i10) * 31;
        String str3 = this.countryCode;
        if (str3 == null) {
            i11 = 0;
        } else {
            i11 = str3.hashCode();
        }
        int i18 = (i17 + i11) * 31;
        String str4 = this.orderType;
        if (str4 == null) {
            i12 = 0;
        } else {
            i12 = str4.hashCode();
        }
        int i19 = (i18 + i12) * 31;
        Integer num = this.maxCount;
        if (num != null) {
            i13 = num.hashCode();
        }
        return i19 + i13;
    }

    public String toString() {
        String encodedString = Logger.getEncodedString(this.queryString);
        LocalDateTime localDateTime = this.startTime;
        LocalDateTime localDateTime2 = this.endTime;
        String encodedString2 = Logger.getEncodedString(this.city);
        String encodedString3 = Logger.getEncodedString(this.country);
        String str = this.orderType;
        Integer num = this.maxCount;
        StringBuilder sb2 = new StringBuilder("#FindContentsParams : ");
        sb2.append(encodedString);
        sb2.append(", [");
        sb2.append(localDateTime);
        sb2.append(" - ");
        sb2.append(localDateTime2);
        sb2.append("], ");
        sb2.append(encodedString2);
        sb2.append(ArcCommonLog.TAG_COMMA);
        C0086a.z(sb2, encodedString3, ArcCommonLog.TAG_COMMA, str, ArcCommonLog.TAG_COMMA);
        sb2.append(num);
        return sb2.toString();
    }
}
