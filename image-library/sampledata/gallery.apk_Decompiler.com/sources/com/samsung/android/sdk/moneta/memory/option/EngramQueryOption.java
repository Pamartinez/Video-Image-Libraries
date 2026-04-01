package com.samsung.android.sdk.moneta.memory.option;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.moneta.memory.entity.activity.ActivityType;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b*\b\u0007\u0018\u00002\u00020\u0001:\u0007DEFGHIJBÙ\u0001\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0013\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\"\u001a\u00020!2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u000f¢\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\u000f¢\u0006\u0004\b$\u0010%R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010&\u001a\u0004\b)\u0010(R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010*\u001a\u0004\b+\u0010\u001dR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010,\u001a\u0004\b-\u0010.R\u001f\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010/\u001a\u0004\b0\u00101R\u001f\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010/\u001a\u0004\b2\u00101R\u001f\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\r\u0010/\u001a\u0004\b3\u00101R\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010/\u001a\u0004\b4\u00101R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u00105\u001a\u0004\b6\u0010%R\u0017\u0010\u0011\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u00105\u001a\u0004\b7\u0010%R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0012\u0010*\u001a\u0004\b8\u0010\u001dR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u00109\u001a\u0004\b:\u0010;R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0015\u00109\u001a\u0004\b<\u0010;R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0016\u00109\u001a\u0004\b=\u0010;R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010>\u001a\u0004\b?\u0010@R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0019\u0010A\u001a\u0004\bB\u0010C¨\u0006K"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "", "contentId", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryType;", "queryType", "", "includeTags", "excludeTags", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "includeActivityTypes", "excludeActivityTypes", "", "limit", "offset", "engramId", "", "latitude", "longitude", "radius", "", "contentFill", "resolution", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryType;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;IILjava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;ZLjava/lang/Integer;)V", "toString", "()Ljava/lang/String;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Ljava/lang/String;", "getContentId", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryType;", "getQueryType", "()Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryType;", "Ljava/util/List;", "getIncludeTags", "()Ljava/util/List;", "getExcludeTags", "getIncludeActivityTypes", "getExcludeActivityTypes", "I", "getLimit", "getOffset", "getEngramId", "Ljava/lang/Double;", "getLatitude", "()Ljava/lang/Double;", "getLongitude", "getRadius", "Z", "getContentFill", "()Z", "Ljava/lang/Integer;", "getResolution", "()Ljava/lang/Integer;", "Builder", "ConditionalBuilder", "ByContentIdBuilder", "ByEngramIdBuilder", "ByActivityIdBuilder", "ByLocationBuilder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramQueryOption implements Parcelable {
    public static final Parcelable.Creator<EngramQueryOption> CREATOR = new Creator();
    private final boolean contentFill;
    private final String contentId;
    private final Long endTimestamp;
    private final String engramId;
    private final List<ActivityType> excludeActivityTypes;
    private final List<String> excludeTags;
    private final List<ActivityType> includeActivityTypes;
    private final List<String> includeTags;
    private final Double latitude;
    private final int limit;
    private final Double longitude;
    private final int offset;
    private final EngramQueryType queryType;
    private final Double radius;
    private final Integer resolution;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000f\u0010\u0010J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006J\u0006\u0010\u0016\u001a\u00020\u0017R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\f\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\r\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption$Builder;", "", "startTimestamp", "", "endTimestamp", "limit", "", "offset", "contentFill", "", "latitude", "", "longitude", "radius", "resolution", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;IIZLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;)V", "Ljava/lang/Long;", "Ljava/lang/Double;", "Ljava/lang/Integer;", "startTimeStamp", "endTimeStamp", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private boolean contentFill;
        private Long endTimestamp;
        private Double latitude;
        private int limit;
        private Double longitude;
        private int offset;
        private Double radius;
        private Integer resolution;
        private Long startTimestamp;

        public Builder() {
            this((Long) null, (Long) null, 0, 0, false, (Double) null, (Double) null, (Double) null, (Integer) null, 511, (e) null);
        }

        public final EngramQueryOption build() {
            return new EngramQueryOption(this.startTimestamp, this.endTimestamp, (String) null, EngramQueryType.BETWEEN_TIMESTAMP, (List) null, (List) null, (List) null, (List) null, this.limit, this.offset, (String) null, this.latitude, this.longitude, this.radius, this.contentFill, this.resolution, 1024, (e) null);
        }

        public final Builder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final Builder endTimeStamp(long j2) {
            this.endTimestamp = Long.valueOf(j2);
            return this;
        }

        public final Builder latitude(double d) {
            this.latitude = Double.valueOf(d);
            return this;
        }

        public final Builder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final Builder longitude(double d) {
            this.longitude = Double.valueOf(d);
            return this;
        }

        public final Builder offset(int i2) {
            this.offset = i2;
            return this;
        }

        public final Builder radius(double d) {
            this.radius = Double.valueOf(d);
            return this;
        }

        public final Builder resolution(int i2) {
            this.resolution = Integer.valueOf(i2);
            return this;
        }

        public final Builder startTimeStamp(long j2) {
            this.startTimestamp = Long.valueOf(j2);
            return this;
        }

        public Builder(Long l, Long l8, int i2, int i7, boolean z, Double d, Double d2, Double d3, Integer num) {
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.limit = i2;
            this.offset = i7;
            this.contentFill = z;
            this.latitude = d;
            this.longitude = d2;
            this.radius = d3;
            this.resolution = num;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(Long l, Long l8, int i2, int i7, boolean z, Double d, Double d2, Double d3, Integer num, int i8, e eVar) {
            this((i8 & 1) != 0 ? null : l, (i8 & 2) != 0 ? null : l8, (i8 & 4) != 0 ? 20 : i2, (i8 & 8) != 0 ? 0 : i7, (i8 & 16) != 0 ? false : z, (i8 & 32) != 0 ? null : d, (i8 & 64) != 0 ? null : d2, (i8 & 128) != 0 ? null : d3, (i8 & 256) != 0 ? null : num);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption$ByContentIdBuilder;", "", "contentId", "", "<init>", "(Ljava/lang/String;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByContentIdBuilder {
        private String contentId;

        public ByContentIdBuilder(String str) {
            j.e(str, EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID);
            this.contentId = str;
        }

        public final EngramQueryOption build() {
            return new EngramQueryOption((Long) null, (Long) null, this.contentId, EngramQueryType.BY_CONTENT_ID, (List) null, (List) null, (List) null, (List) null, 0, 0, (String) null, (Double) null, (Double) null, (Double) null, true, (Integer) null, ErrorCodeConvertor.CLOUD_PARAM_CA_DEVICE_ID_INVALID, (e) null);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bq\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u0014\u0010\u0005\u001a\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0014\u0010\b\u001a\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0014\u0010\t\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006J\u0014\u0010\u000b\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0006J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u0013\u001a\u00020\u0014R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption$ConditionalBuilder;", "", "startTimestamp", "", "endTimestamp", "includeTags", "", "", "excludeTags", "includeActivityTypes", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "excludeActivityTypes", "contentFill", "", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Z)V", "Ljava/lang/Long;", "startTimeStamp", "endTimeStamp", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ConditionalBuilder {
        private boolean contentFill;
        private Long endTimestamp;
        private List<? extends ActivityType> excludeActivityTypes;
        private List<String> excludeTags;
        private List<? extends ActivityType> includeActivityTypes;
        private List<String> includeTags;
        private Long startTimestamp;

        public ConditionalBuilder() {
            this((Long) null, (Long) null, (List) null, (List) null, (List) null, (List) null, false, 127, (e) null);
        }

        public final EngramQueryOption build() {
            return new EngramQueryOption(this.startTimestamp, this.endTimestamp, (String) null, EngramQueryType.BETWEEN_TIMESTAMP, this.includeTags, this.excludeTags, this.includeActivityTypes, this.excludeActivityTypes, 2147483646, 0, (String) null, (Double) null, (Double) null, (Double) null, this.contentFill, (Integer) null, 48128, (e) null);
        }

        public final ConditionalBuilder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final ConditionalBuilder endTimeStamp(long j2) {
            this.endTimestamp = Long.valueOf(j2);
            return this;
        }

        public final ConditionalBuilder excludeActivityTypes(List<? extends ActivityType> list) {
            j.e(list, EngramQueryOptionBundleWrapper.BUNDLE_KEY_EXCLUDE_ACTIVITY_TYPES);
            this.excludeActivityTypes = list;
            return this;
        }

        public final ConditionalBuilder excludeTags(List<String> list) {
            j.e(list, EngramQueryOptionBundleWrapper.BUNDLE_KEY_EXCLUDE_TAGS);
            this.excludeTags = list;
            return this;
        }

        public final ConditionalBuilder includeActivityTypes(List<? extends ActivityType> list) {
            j.e(list, EngramQueryOptionBundleWrapper.BUNDLE_KEY_INCLUDE_ACTIVITY_TYPES);
            this.includeActivityTypes = list;
            return this;
        }

        public final ConditionalBuilder includeTags(List<String> list) {
            j.e(list, EngramQueryOptionBundleWrapper.BUNDLE_KEY_INCLUDE_TAGS);
            this.includeTags = list;
            return this;
        }

        public final ConditionalBuilder startTimeStamp(long j2) {
            this.startTimestamp = Long.valueOf(j2);
            return this;
        }

        public ConditionalBuilder(Long l, Long l8, List<String> list, List<String> list2, List<? extends ActivityType> list3, List<? extends ActivityType> list4, boolean z) {
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.includeTags = list;
            this.excludeTags = list2;
            this.includeActivityTypes = list3;
            this.excludeActivityTypes = list4;
            this.contentFill = z;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ConditionalBuilder(Long l, Long l8, List list, List list2, List list3, List list4, boolean z, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : l, (i2 & 2) != 0 ? null : l8, (i2 & 4) != 0 ? null : list, (i2 & 8) != 0 ? null : list2, (i2 & 16) != 0 ? null : list3, (i2 & 32) != 0 ? null : list4, (i2 & 64) != 0 ? false : z);
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramQueryOption> {
        public final EngramQueryOption createFromParcel(Parcel parcel) {
            ArrayList arrayList;
            ArrayList arrayList2;
            Parcel parcel2 = parcel;
            j.e(parcel2, "parcel");
            Integer num = null;
            Long valueOf = parcel2.readInt() == 0 ? null : Long.valueOf(parcel2.readLong());
            Long valueOf2 = parcel2.readInt() == 0 ? null : Long.valueOf(parcel2.readLong());
            String readString = parcel2.readString();
            EngramQueryType valueOf3 = EngramQueryType.valueOf(parcel2.readString());
            ArrayList<String> createStringArrayList = parcel2.createStringArrayList();
            ArrayList<String> createStringArrayList2 = parcel2.createStringArrayList();
            boolean z = false;
            if (parcel2.readInt() == 0) {
                arrayList = null;
            } else {
                int readInt = parcel2.readInt();
                arrayList = new ArrayList(readInt);
                for (int i2 = 0; i2 != readInt; i2++) {
                    arrayList.add(ActivityType.valueOf(parcel2.readString()));
                }
            }
            if (parcel2.readInt() == 0) {
                arrayList2 = null;
            } else {
                int readInt2 = parcel2.readInt();
                arrayList2 = new ArrayList(readInt2);
                for (int i7 = 0; i7 != readInt2; i7++) {
                    arrayList2.add(ActivityType.valueOf(parcel2.readString()));
                }
            }
            int readInt3 = parcel2.readInt();
            int readInt4 = parcel2.readInt();
            String readString2 = parcel2.readString();
            Double valueOf4 = parcel2.readInt() == 0 ? null : Double.valueOf(parcel2.readDouble());
            Double valueOf5 = parcel2.readInt() == 0 ? null : Double.valueOf(parcel2.readDouble());
            Double valueOf6 = parcel2.readInt() == 0 ? null : Double.valueOf(parcel2.readDouble());
            if (parcel2.readInt() != 0) {
                z = true;
            }
            boolean z3 = z;
            if (parcel2.readInt() != 0) {
                num = Integer.valueOf(parcel2.readInt());
            }
            return new EngramQueryOption(valueOf, valueOf2, readString, valueOf3, createStringArrayList, createStringArrayList2, arrayList, arrayList2, readInt3, readInt4, readString2, valueOf4, valueOf5, valueOf6, z3, num, (e) null);
        }

        public final EngramQueryOption[] newArray(int i2) {
            return new EngramQueryOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B×\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0014\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u001a\u0010\u001bJ\u0006\u0010\u001f\u001a\u00020 R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0014X\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0004\n\u0002\u0010\u001e¨\u0006!"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption$WrapBuilder;", "", "startTimestamp", "", "endTimestamp", "contentId", "", "queryType", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryType;", "includeTags", "", "excludeTags", "includeActivityTypes", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "excludeActivityTypes", "limit", "", "offset", "engramId", "latitude", "", "longitude", "radius", "contentFill", "", "resolution", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryType;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;IILjava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;ZLjava/lang/Integer;)V", "Ljava/lang/Long;", "Ljava/lang/Double;", "Ljava/lang/Integer;", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private boolean contentFill;
        private String contentId;
        private Long endTimestamp;
        private String engramId;
        private List<? extends ActivityType> excludeActivityTypes;
        private List<String> excludeTags;
        private List<? extends ActivityType> includeActivityTypes;
        private List<String> includeTags;
        private final Double latitude;
        private int limit;
        private final Double longitude;
        private int offset;
        private EngramQueryType queryType;
        private final Double radius;
        private Integer resolution;
        private Long startTimestamp;

        public WrapBuilder() {
            this((Long) null, (Long) null, (String) null, (EngramQueryType) null, (List) null, (List) null, (List) null, (List) null, 0, 0, (String) null, (Double) null, (Double) null, (Double) null, false, (Integer) null, 65535, (e) null);
        }

        public final /* synthetic */ EngramQueryOption build() {
            return new EngramQueryOption(this.startTimestamp, this.endTimestamp, this.contentId, this.queryType, this.includeTags, this.excludeTags, this.includeActivityTypes, this.excludeActivityTypes, this.limit, this.offset, this.engramId, this.latitude, this.longitude, this.radius, this.contentFill, this.resolution, (e) null);
        }

        public WrapBuilder(Long l, Long l8, String str, EngramQueryType engramQueryType, List<String> list, List<String> list2, List<? extends ActivityType> list3, List<? extends ActivityType> list4, int i2, int i7, String str2, Double d, Double d2, Double d3, boolean z, Integer num) {
            j.e(engramQueryType, "queryType");
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.contentId = str;
            this.queryType = engramQueryType;
            this.includeTags = list;
            this.excludeTags = list2;
            this.includeActivityTypes = list3;
            this.excludeActivityTypes = list4;
            this.limit = i2;
            this.offset = i7;
            this.engramId = str2;
            this.latitude = d;
            this.longitude = d2;
            this.radius = d3;
            this.contentFill = z;
            this.resolution = num;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ WrapBuilder(java.lang.Long r18, java.lang.Long r19, java.lang.String r20, com.samsung.android.sdk.moneta.memory.option.EngramQueryType r21, java.util.List r22, java.util.List r23, java.util.List r24, java.util.List r25, int r26, int r27, java.lang.String r28, java.lang.Double r29, java.lang.Double r30, java.lang.Double r31, boolean r32, java.lang.Integer r33, int r34, kotlin.jvm.internal.e r35) {
            /*
                r17 = this;
                r0 = r34
                r1 = r0 & 1
                if (r1 == 0) goto L_0x0008
                r1 = 0
                goto L_0x000a
            L_0x0008:
                r1 = r18
            L_0x000a:
                r3 = r0 & 2
                if (r3 == 0) goto L_0x0010
                r3 = 0
                goto L_0x0012
            L_0x0010:
                r3 = r19
            L_0x0012:
                r4 = r0 & 4
                if (r4 == 0) goto L_0x0018
                r4 = 0
                goto L_0x001a
            L_0x0018:
                r4 = r20
            L_0x001a:
                r5 = r0 & 8
                if (r5 == 0) goto L_0x0021
                com.samsung.android.sdk.moneta.memory.option.EngramQueryType r5 = com.samsung.android.sdk.moneta.memory.option.EngramQueryType.BETWEEN_TIMESTAMP
                goto L_0x0023
            L_0x0021:
                r5 = r21
            L_0x0023:
                r6 = r0 & 16
                if (r6 == 0) goto L_0x0029
                r6 = 0
                goto L_0x002b
            L_0x0029:
                r6 = r22
            L_0x002b:
                r7 = r0 & 32
                if (r7 == 0) goto L_0x0031
                r7 = 0
                goto L_0x0033
            L_0x0031:
                r7 = r23
            L_0x0033:
                r8 = r0 & 64
                if (r8 == 0) goto L_0x0039
                r8 = 0
                goto L_0x003b
            L_0x0039:
                r8 = r24
            L_0x003b:
                r9 = r0 & 128(0x80, float:1.794E-43)
                if (r9 == 0) goto L_0x0041
                r9 = 0
                goto L_0x0043
            L_0x0041:
                r9 = r25
            L_0x0043:
                r10 = r0 & 256(0x100, float:3.59E-43)
                if (r10 == 0) goto L_0x004a
                r10 = 20
                goto L_0x004c
            L_0x004a:
                r10 = r26
            L_0x004c:
                r11 = r0 & 512(0x200, float:7.175E-43)
                if (r11 == 0) goto L_0x0052
                r11 = 0
                goto L_0x0054
            L_0x0052:
                r11 = r27
            L_0x0054:
                r13 = r0 & 1024(0x400, float:1.435E-42)
                if (r13 == 0) goto L_0x005a
                r13 = 0
                goto L_0x005c
            L_0x005a:
                r13 = r28
            L_0x005c:
                r14 = r0 & 2048(0x800, float:2.87E-42)
                if (r14 == 0) goto L_0x0062
                r14 = 0
                goto L_0x0064
            L_0x0062:
                r14 = r29
            L_0x0064:
                r15 = r0 & 4096(0x1000, float:5.74E-42)
                if (r15 == 0) goto L_0x006a
                r15 = 0
                goto L_0x006c
            L_0x006a:
                r15 = r30
            L_0x006c:
                r2 = r0 & 8192(0x2000, float:1.14794E-41)
                if (r2 == 0) goto L_0x0072
                r2 = 0
                goto L_0x0074
            L_0x0072:
                r2 = r31
            L_0x0074:
                r12 = r0 & 16384(0x4000, float:2.2959E-41)
                if (r12 == 0) goto L_0x007a
                r12 = 0
                goto L_0x007c
            L_0x007a:
                r12 = r32
            L_0x007c:
                r16 = 32768(0x8000, float:4.5918E-41)
                r0 = r0 & r16
                if (r0 == 0) goto L_0x00a6
                r34 = 0
            L_0x0085:
                r18 = r17
                r19 = r1
                r32 = r2
                r20 = r3
                r21 = r4
                r22 = r5
                r23 = r6
                r24 = r7
                r25 = r8
                r26 = r9
                r27 = r10
                r28 = r11
                r33 = r12
                r29 = r13
                r30 = r14
                r31 = r15
                goto L_0x00a9
            L_0x00a6:
                r34 = r33
                goto L_0x0085
            L_0x00a9:
                r18.<init>(r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.option.EngramQueryOption.WrapBuilder.<init>(java.lang.Long, java.lang.Long, java.lang.String, com.samsung.android.sdk.moneta.memory.option.EngramQueryType, java.util.List, java.util.List, java.util.List, java.util.List, int, int, java.lang.String, java.lang.Double, java.lang.Double, java.lang.Double, boolean, java.lang.Integer, int, kotlin.jvm.internal.e):void");
        }
    }

    public /* synthetic */ EngramQueryOption(Long l, Long l8, String str, EngramQueryType engramQueryType, List list, List list2, List list3, List list4, int i2, int i7, String str2, Double d, Double d2, Double d3, boolean z, Integer num, e eVar) {
        this(l, l8, str, engramQueryType, list, list2, list3, list4, i2, i7, str2, d, d2, d3, z, num);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean getContentFill() {
        return this.contentFill;
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getEngramId() {
        return this.engramId;
    }

    public final List<ActivityType> getExcludeActivityTypes() {
        return this.excludeActivityTypes;
    }

    public final List<String> getExcludeTags() {
        return this.excludeTags;
    }

    public final List<ActivityType> getIncludeActivityTypes() {
        return this.includeActivityTypes;
    }

    public final List<String> getIncludeTags() {
        return this.includeTags;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final Double getLongitude() {
        return this.longitude;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final EngramQueryType getQueryType() {
        return this.queryType;
    }

    public final Double getRadius() {
        return this.radius;
    }

    public final Integer getResolution() {
        return this.resolution;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public String toString() {
        return "EngramQueryOption(startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ", contentId=" + this.contentId + ", queryType=" + this.queryType + ", includeTags=" + this.includeTags + ", excludeTags=" + this.excludeTags + ", includeActivityTypes=" + this.includeActivityTypes + ", excludeActivityTypes=" + this.excludeActivityTypes + ", limit=" + this.limit + ", offset=" + this.offset + ", engramId=" + this.engramId + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", radius=" + this.radius + ", contentFill=" + this.contentFill + ", resolution=" + this.resolution + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Long l = this.startTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        Long l8 = this.endTimestamp;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
        parcel.writeString(this.contentId);
        parcel.writeString(this.queryType.name());
        parcel.writeStringList(this.includeTags);
        parcel.writeStringList(this.excludeTags);
        List<ActivityType> list = this.includeActivityTypes;
        if (list == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(list.size());
            for (ActivityType name : list) {
                parcel.writeString(name.name());
            }
        }
        List<ActivityType> list2 = this.excludeActivityTypes;
        if (list2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(list2.size());
            for (ActivityType name2 : list2) {
                parcel.writeString(name2.name());
            }
        }
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeString(this.engramId);
        Double d = this.latitude;
        if (d == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d);
        }
        Double d2 = this.longitude;
        if (d2 == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d2);
        }
        Double d3 = this.radius;
        if (d3 == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d3);
        }
        parcel.writeInt(this.contentFill ? 1 : 0);
        Integer num = this.resolution;
        if (num == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(num.intValue());
    }

    private EngramQueryOption(Long l, Long l8, String str, EngramQueryType engramQueryType, List<String> list, List<String> list2, List<? extends ActivityType> list3, List<? extends ActivityType> list4, int i2, int i7, String str2, Double d, Double d2, Double d3, boolean z, Integer num) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.contentId = str;
        this.queryType = engramQueryType;
        this.includeTags = list;
        this.excludeTags = list2;
        this.includeActivityTypes = list3;
        this.excludeActivityTypes = list4;
        this.limit = i2;
        this.offset = i7;
        this.engramId = str2;
        this.latitude = d;
        this.longitude = d2;
        this.radius = d3;
        this.contentFill = z;
        this.resolution = num;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption$ByActivityIdBuilder;", "", "activityId", "", "limit", "", "offset", "<init>", "(Ljava/lang/String;II)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByActivityIdBuilder {
        private String activityId;
        private int limit;
        private int offset;

        public ByActivityIdBuilder(String str, int i2, int i7) {
            j.e(str, "activityId");
            this.activityId = str;
            this.limit = i2;
            this.offset = i7;
        }

        public final EngramQueryOption build() {
            return new EngramQueryOption((Long) null, (Long) null, (String) null, EngramQueryType.BY_ENGRAM_ID, (List) null, (List) null, (List) null, (List) null, this.limit, this.offset, this.activityId, (Double) null, (Double) null, (Double) null, true, (Integer) null, 47344, (e) null);
        }

        public final ByActivityIdBuilder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final ByActivityIdBuilder offset(int i2) {
            this.offset = i2;
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ByActivityIdBuilder(String str, int i2, int i7, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 20 : i2, (i8 & 4) != 0 ? 0 : i7);
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption$ByEngramIdBuilder;", "", "engramId", "", "limit", "", "offset", "<init>", "(Ljava/lang/String;II)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByEngramIdBuilder {
        private String engramId;
        private int limit;
        private int offset;

        public ByEngramIdBuilder(String str, int i2, int i7) {
            j.e(str, "engramId");
            this.engramId = str;
            this.limit = i2;
            this.offset = i7;
        }

        public final EngramQueryOption build() {
            return new EngramQueryOption((Long) null, (Long) null, (String) null, EngramQueryType.BY_ENGRAM_ID, (List) null, (List) null, (List) null, (List) null, this.limit, this.offset, this.engramId, (Double) null, (Double) null, (Double) null, true, (Integer) null, 47344, (e) null);
        }

        public final ByEngramIdBuilder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final ByEngramIdBuilder offset(int i2) {
            this.offset = i2;
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ByEngramIdBuilder(String str, int i2, int i7, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 20 : i2, (i8 & 4) != 0 ? 0 : i7);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption$ByLocationBuilder;", "", "limit", "", "offset", "latitude", "", "longitude", "radius", "contentFill", "", "<init>", "(IIDDDZ)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByLocationBuilder {
        private boolean contentFill;
        private double latitude;
        private int limit;
        private double longitude;
        private int offset;
        private double radius;

        public ByLocationBuilder(int i2, int i7, double d, double d2, double d3, boolean z) {
            this.limit = i2;
            this.offset = i7;
            this.latitude = d;
            this.longitude = d2;
            this.radius = d3;
            this.contentFill = z;
        }

        public final EngramQueryOption build() {
            return new EngramQueryOption((Long) null, (Long) null, (String) null, EngramQueryType.BY_LOCATION, (List) null, (List) null, (List) null, (List) null, this.limit, this.offset, (String) null, Double.valueOf(this.latitude), Double.valueOf(this.longitude), Double.valueOf(this.radius), this.contentFill, (Integer) null, 34039, (e) null);
        }

        public final ByLocationBuilder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final ByLocationBuilder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final ByLocationBuilder offset(int i2) {
            this.offset = i2;
            return this;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ ByLocationBuilder(int r2, int r3, double r4, double r6, double r8, boolean r10, int r11, kotlin.jvm.internal.e r12) {
            /*
                r1 = this;
                r12 = r11 & 1
                if (r12 == 0) goto L_0x0006
                r2 = 20
            L_0x0006:
                r12 = r11 & 2
                r0 = 0
                if (r12 == 0) goto L_0x000c
                r3 = r0
            L_0x000c:
                r11 = r11 & 32
                if (r11 == 0) goto L_0x0018
                r12 = r0
            L_0x0011:
                r10 = r8
                r8 = r6
                r6 = r4
                r4 = r2
                r5 = r3
                r3 = r1
                goto L_0x001a
            L_0x0018:
                r12 = r10
                goto L_0x0011
            L_0x001a:
                r3.<init>(r4, r5, r6, r8, r10, r12)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.option.EngramQueryOption.ByLocationBuilder.<init>(int, int, double, double, double, boolean, int, kotlin.jvm.internal.e):void");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ EngramQueryOption(java.lang.Long r18, java.lang.Long r19, java.lang.String r20, com.samsung.android.sdk.moneta.memory.option.EngramQueryType r21, java.util.List r22, java.util.List r23, java.util.List r24, java.util.List r25, int r26, int r27, java.lang.String r28, java.lang.Double r29, java.lang.Double r30, java.lang.Double r31, boolean r32, java.lang.Integer r33, int r34, kotlin.jvm.internal.e r35) {
        /*
            r17 = this;
            r0 = r34
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x000a
        L_0x0008:
            r1 = r18
        L_0x000a:
            r3 = r0 & 2
            if (r3 == 0) goto L_0x0010
            r3 = 0
            goto L_0x0012
        L_0x0010:
            r3 = r19
        L_0x0012:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x0018
            r4 = 0
            goto L_0x001a
        L_0x0018:
            r4 = r20
        L_0x001a:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x0021
            com.samsung.android.sdk.moneta.memory.option.EngramQueryType r5 = com.samsung.android.sdk.moneta.memory.option.EngramQueryType.BETWEEN_TIMESTAMP
            goto L_0x0023
        L_0x0021:
            r5 = r21
        L_0x0023:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x0029
            r6 = 0
            goto L_0x002b
        L_0x0029:
            r6 = r22
        L_0x002b:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x0031
            r7 = 0
            goto L_0x0033
        L_0x0031:
            r7 = r23
        L_0x0033:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0039
            r8 = 0
            goto L_0x003b
        L_0x0039:
            r8 = r24
        L_0x003b:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0041
            r9 = 0
            goto L_0x0043
        L_0x0041:
            r9 = r25
        L_0x0043:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x004a
            r10 = 20
            goto L_0x004c
        L_0x004a:
            r10 = r26
        L_0x004c:
            r11 = r0 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x0052
            r11 = 0
            goto L_0x0054
        L_0x0052:
            r11 = r27
        L_0x0054:
            r13 = r0 & 1024(0x400, float:1.435E-42)
            if (r13 == 0) goto L_0x005a
            r13 = 0
            goto L_0x005c
        L_0x005a:
            r13 = r28
        L_0x005c:
            r14 = r0 & 2048(0x800, float:2.87E-42)
            if (r14 == 0) goto L_0x0062
            r14 = 0
            goto L_0x0064
        L_0x0062:
            r14 = r29
        L_0x0064:
            r15 = r0 & 4096(0x1000, float:5.74E-42)
            if (r15 == 0) goto L_0x006a
            r15 = 0
            goto L_0x006c
        L_0x006a:
            r15 = r30
        L_0x006c:
            r2 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r2 == 0) goto L_0x0072
            r2 = 0
            goto L_0x0074
        L_0x0072:
            r2 = r31
        L_0x0074:
            r12 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r12 == 0) goto L_0x007a
            r12 = 0
            goto L_0x007c
        L_0x007a:
            r12 = r32
        L_0x007c:
            r16 = 32768(0x8000, float:4.5918E-41)
            r0 = r0 & r16
            if (r0 == 0) goto L_0x00a6
            r34 = 0
        L_0x0085:
            r18 = r17
            r19 = r1
            r32 = r2
            r20 = r3
            r21 = r4
            r22 = r5
            r23 = r6
            r24 = r7
            r25 = r8
            r26 = r9
            r27 = r10
            r28 = r11
            r33 = r12
            r29 = r13
            r30 = r14
            r31 = r15
            goto L_0x00a9
        L_0x00a6:
            r34 = r33
            goto L_0x0085
        L_0x00a9:
            r18.<init>(r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.option.EngramQueryOption.<init>(java.lang.Long, java.lang.Long, java.lang.String, com.samsung.android.sdk.moneta.memory.option.EngramQueryType, java.util.List, java.util.List, java.util.List, java.util.List, int, int, java.lang.String, java.lang.Double, java.lang.Double, java.lang.Double, boolean, java.lang.Integer, int, kotlin.jvm.internal.e):void");
    }
}
