package com.samsung.scsp.framework.core.identity;

import com.samsung.android.scs.ai.sdkcommon.feature.FeatureConfig;
import com.samsung.scsp.common.PreferenceItem;
import com.samsung.scsp.common.Preferences;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScspCorePreferences extends Preferences {
    private static final String PREFERENCES_NAME = "samsungcloudsdk.preferences";
    public final PreferenceItem<String> appVersion;
    public final PreferenceItem<String> cdid;
    public final PreferenceItem<String> cloudToken;
    public final PreferenceItem<Long> cloudTokenExpiredOn;
    public final PreferenceItem<String> deviceAlias;
    public final PreferenceItem<String> deviceToken;
    public final PreferenceItem<Long> deviceTokenExpiredOn;
    public final PreferenceItem<String> dvcId;
    public final PreferenceItem<String> e2eeType;
    public final PreferenceItem<String> hashedUid;
    public final PreferenceItem<Boolean> isAccountRegistered;
    public final PreferenceItem<Boolean> isDeviceRegistered;
    public final PreferenceItem<String> osVersion;
    public final PreferenceItem<String> pdid;
    public final PreferenceItem<String> platformVersion;
    public final PreferenceItem<String> pushInfos;
    public final PreferenceItem<String> registrationId;
    public final PreferenceItem<String> sakUid;
    public final PreferenceItem<String> simMcc;
    public final PreferenceItem<String> simMnc;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LazyHolder {
        /* access modifiers changed from: private */
        public static final ScspCorePreferences preferences = new ScspCorePreferences();

        private LazyHolder() {
        }
    }

    public static ScspCorePreferences get() {
        return LazyHolder.preferences;
    }

    private ScspCorePreferences() {
        super(PREFERENCES_NAME);
        this.cloudToken = new PreferenceItem<>(this, "cloud_token", "");
        this.cloudTokenExpiredOn = new PreferenceItem<>(this, "cloud_token_expire_time", -1L);
        this.deviceToken = new PreferenceItem<>(this, "device_cloud_token", "");
        this.deviceTokenExpiredOn = new PreferenceItem<>(this, "device_cloud_token_expire_time", -1L);
        Boolean bool = Boolean.FALSE;
        this.isDeviceRegistered = new PreferenceItem<>(this, "is_device_registered", bool);
        this.isAccountRegistered = new PreferenceItem<>(this, "is_account_registered", bool);
        this.hashedUid = new PreferenceItem<>(this, "hashed_uid", "");
        this.pdid = new PreferenceItem<>(this, "physical_device_id", "");
        this.cdid = new PreferenceItem<>(this, "client_device_id", "");
        this.pushInfos = new PreferenceItem<>(this, "push_infos", "");
        this.e2eeType = new PreferenceItem<>(this, IdentityApiContract.Parameter.E2EE_TYPE, "");
        this.appVersion = new PreferenceItem<>(this, FeatureConfig.JSON_KEY_APP_VERSION, "");
        this.simMcc = new PreferenceItem<>(this, "sim_mcc", "");
        this.simMnc = new PreferenceItem<>(this, "sim_mnc", "");
        this.osVersion = new PreferenceItem<>(this, "os_version", "");
        this.deviceAlias = new PreferenceItem<>(this, "device_name", "");
        this.platformVersion = new PreferenceItem<>(this, "platform_version", "");
        this.registrationId = new PreferenceItem<>(this, "registrationId", "");
        this.dvcId = new PreferenceItem<>(this, "dvcId", "");
        this.sakUid = new PreferenceItem<>(this, "sak_uid", "");
    }
}
