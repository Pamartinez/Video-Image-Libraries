package com.samsung.android.sdk.scs.base.utils;

import android.content.Intent;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConnectionHelper {
    public static Intent getC2PAServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_CONTENT_AUTHENTICATION_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_VISUAL_CLOUD_CORE_SERVICES);
    }

    public static Intent getDownloadCoreServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_DOWNLOAD_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_AICORE_SERVICES);
    }

    public static Intent getDownloadServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_DOWNLOAD_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_VISUAL_CLOUD_CORE_SERVICES);
    }

    public static Intent getImageEditorServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_IMAGE_EDITOR_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_VISUAL_CLOUD_CORE_SERVICES);
    }

    public static Intent getNaturalLanguageServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_TEXT_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES);
    }

    public static Intent getPortraitServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_PORTRAIT_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_VISUAL_CLOUD_CORE_SERVICES);
    }

    public static Intent getPrerequisiteServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_PREREQUISITE_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_VISUAL_CLOUD_CORE_SERVICES);
    }

    public static Intent getSuggestionServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_SUGGESTION_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES);
    }

    public static Intent getTextToSpeechServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_TEXT_TO_SPEECH_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
    }

    public static Intent getTextToSpeechServiceIntentForExternal() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_TEXT_TO_SPEECH_SERVICE_FOR_EXTERNAL, BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
    }

    public static Intent getTranslationServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_TRANSLATION_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
    }

    public static Intent getTranslationServiceIntentForExternal() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_TRANSLATION_SERVICE_FOR_EXTERNAL, BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
    }

    public static Intent getVisionServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_VISION_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES);
    }

    public static Intent getWallpaperServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_WALLPAPER_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_AICORE_SERVICES);
    }
}
