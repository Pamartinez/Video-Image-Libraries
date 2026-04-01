package com.samsung.android.app.sdk.deepsky.contract;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/DeepSkyMethod;", "", "<init>", "()V", "VERSION_CODE", "", "KEY_VERSION", "", "URI", "REQUEST_SEARCH", "RESPONSE_SEARCH", "COMMAND_SEARCH", "GET_CAPABILITIES", "REQUEST_SUGGESTION", "SUBSCRIBE_SUGGESTION", "UNSUBSCRIBE_SUGGESTION", "IS_SUBSCRIBED", "GET_SUBSCRIPTION_ID_LIST", "GET_SMART_SUGGESTIONS_ENABLED", "SET_SMART_SUGGESTIONS_ENABLED", "GET_APP_RECOMMENDATION_AVAILABILITY", "SET_APP_RECOMMENDATION_AVAILABILITY", "GET_APP_SETTING_VALUE", "RESET_APP_RECOMMENDATION_AVAILABILITY", "ENABLE_GLANCE", "DISABLE_GLANCE", "SMART_SUGGESTIONS_ENABLED_PROPERTY", "APP_PACKAGE_NAME_PROPERTY", "APP_PREFERENCE_KEY_PROPERTY", "APP_SETTING_VALUE_PROPERTY", "APP_RECOMMENDATION_AVAILABILITY_PROPERTY", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DeepSkyMethod {
    public static final String APP_PACKAGE_NAME_PROPERTY = "app_package_name";
    public static final String APP_PREFERENCE_KEY_PROPERTY = "app_preference_key";
    public static final String APP_RECOMMENDATION_AVAILABILITY_PROPERTY = "app_recommendation_availability_result";
    public static final String APP_SETTING_VALUE_PROPERTY = "app_setting_value_key";
    public static final String COMMAND_SEARCH = "command_search";
    public static final String DISABLE_GLANCE = "disable_glance";
    public static final String ENABLE_GLANCE = "enable_glance";
    public static final String GET_APP_RECOMMENDATION_AVAILABILITY = "get_app_recommendation_availability";
    public static final String GET_APP_SETTING_VALUE = "get_app_setting_value";
    public static final String GET_CAPABILITIES = "get_capabilities";
    public static final String GET_SMART_SUGGESTIONS_ENABLED = "get_smart_suggestions_enabled";
    public static final String GET_SUBSCRIPTION_ID_LIST = "get_subscription_id_list";
    public static final DeepSkyMethod INSTANCE = new DeepSkyMethod();
    public static final String IS_SUBSCRIBED = "is_subscribed";
    public static final String KEY_VERSION = "version";
    public static final String REQUEST_SEARCH = "request_search";
    public static final String REQUEST_SUGGESTION = "request_suggestion";
    public static final String RESET_APP_RECOMMENDATION_AVAILABILITY = "reset_app_recommendation_availability";
    public static final String RESPONSE_SEARCH = "response_search";
    public static final String SET_APP_RECOMMENDATION_AVAILABILITY = "set_app_recommendation_availability";
    public static final String SET_SMART_SUGGESTIONS_ENABLED = "set_smart_suggestions_enabled";
    public static final String SMART_SUGGESTIONS_ENABLED_PROPERTY = "smart_suggestions_enabled";
    public static final String SUBSCRIBE_SUGGESTION = "subscribe_suggestion";
    public static final String UNSUBSCRIBE_SUGGESTION = "unsubscribe_suggestion";
    public static final String URI = "content://com.samsung.android.app.deepsky.DeepSkyQuery.provider";
    public static final int VERSION_CODE = 1;

    private DeepSkyMethod() {
    }
}
