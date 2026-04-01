package com.samsung.android.scs.ai.sdkcommon.feature;

import Ac.a;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001bB#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001c\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ0\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000f\u0010\nJ\u0010\u0010\u0010\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\nR#\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0019\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/scs/ai/sdkcommon/feature/FeatureConfig;", "", "", "appVersion", "", "", "features", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/Map;", "copy", "(Ljava/lang/String;Ljava/util/Map;)Lcom/samsung/android/scs/ai/sdkcommon/feature/FeatureConfig;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getAppVersion", "Ljava/util/Map;", "getFeatures", "Companion", "Ac/a", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FeatureConfig {
    public static final a Companion = new Object();
    public static final String JSON_KEY_APP_VERSION = "app_version";
    public static final String JSON_KEY_FEATURES = "features";
    private final String appVersion;
    private final Map<String, Integer> features;

    public FeatureConfig(String str, Map<String, Integer> map) {
        j.e(str, "appVersion");
        j.e(map, "features");
        this.appVersion = str;
        this.features = map;
    }

    public static /* synthetic */ FeatureConfig copy$default(FeatureConfig featureConfig, String str, Map<String, Integer> map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = featureConfig.appVersion;
        }
        if ((i2 & 2) != 0) {
            map = featureConfig.features;
        }
        return featureConfig.copy(str, map);
    }

    public final String component1() {
        return this.appVersion;
    }

    public final Map<String, Integer> component2() {
        return this.features;
    }

    public final FeatureConfig copy(String str, Map<String, Integer> map) {
        j.e(str, "appVersion");
        j.e(map, "features");
        return new FeatureConfig(str, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeatureConfig)) {
            return false;
        }
        FeatureConfig featureConfig = (FeatureConfig) obj;
        if (j.a(this.appVersion, featureConfig.appVersion) && j.a(this.features, featureConfig.features)) {
            return true;
        }
        return false;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final Map<String, Integer> getFeatures() {
        return this.features;
    }

    public int hashCode() {
        return this.features.hashCode() + (this.appVersion.hashCode() * 31);
    }

    public String toString() {
        String str = this.appVersion;
        Map<String, Integer> map = this.features;
        return "FeatureConfig(appVersion=" + str + ", features=" + map + ")";
    }
}
