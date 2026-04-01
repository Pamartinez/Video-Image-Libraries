package com.samsung.android.sdk.scs.base.utils;

import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BaseConstants {
    public static final long SERVICE_CONNECT_WAIT_TIME = TimeUnit.SECONDS.toMillis(5);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PACKAGE_INFO {
        public static final String PACKAGE_AICORE_SERVICES = "com.samsung.android.aicore";
        public static final String PACKAGE_CORE_SERVICES = "com.samsung.android.scs";
        public static final String PACKAGE_NATURALLANGUAGE_SERVICE = "com.samsung.android.scs.ai.text";
        public static final String PACKAGE_SIVS_SERVICES = "com.samsung.android.intellivoiceservice";
        public static final String PACKAGE_SUGGESTION_SERVICE = "com.samsung.android.scs.ai.suggestion";
        public static final String PACKAGE_TEXT_SERVICE = "com.samsung.android.scs.ai.text";
        public static final String PACKAGE_TRANSLATION = "com.samsung.android.intellivoiceservice.aitranslator";
        public static final String PACKAGE_VISION_SERVICE = "com.samsung.android.scs.ai.image";
        public static final String PACKAGE_VISUAL_CLOUD_CORE_SERVICES = "com.samsung.android.visual.cloudcore";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SERVICE_ACTION {
        public static final String ACTION_CONTENT_AUTHENTICATION_SERVICE = "visual.intent.action.BIND_C2PA_SERVICE";
        public static final String ACTION_CORRECTION_SERVICE = "android.intellivoiceservice.CorrectionService";
        public static final String ACTION_CORRECTION_SERVICE_FOR_EXTERNAL = "android.intellivoiceservice.CorrectionServiceForExternal";
        public static final String ACTION_DOWNLOAD_SERVICE = "visual.intent.action.BIND_DOWNLOAD_SERVICE";
        public static final String ACTION_IMAGE_EDITOR_SERVICE = "visual.intent.action.BIND_IMAGE_EDITOR_SERVICE";
        public static final String ACTION_ON_DEVICE_SERVICE = "visual.intent.action.BIND_ON_DEVICE_SERVICE";
        public static final String ACTION_PORTRAIT_SERVICE = "visual.intent.action.BIND_PORTRAIT_SERVICE";
        public static final String ACTION_PREREQUISITE_SERVICE = "visual.intent.action.BIND_PREREQUISITE_SERVICE";
        public static final String ACTION_SMART_REPLY_SERVICE = "android.intellivoiceservice.SmartReplyService";
        public static final String ACTION_SMART_REPLY_SERVICE_FOR_EXTERNAL = "android.intellivoiceservice.SmartReplyServiceForExternal";
        public static final String ACTION_SUGGESTION_SERVICE = "scs.ai.intent.action.BIND_SUGGESTION_SERVICE";
        public static final String ACTION_SUMMARIZATION_SERVICE = "android.intellivoiceservice.SummarizationService";
        public static final String ACTION_SUMMARIZATION_SERVICE_FOR_EXTERNAL = "android.intellivoiceservice.SummarizationServiceForExternal";
        public static final String ACTION_TEXT_SERVICE = "scs.ai.intent.action.BIND_TEXT_SERVICE";
        public static final String ACTION_TEXT_TO_SPEECH_SERVICE = "intellivoiceservice.intent.action.BIND_TEXT_TO_SPEECH";
        public static final String ACTION_TEXT_TO_SPEECH_SERVICE_FOR_EXTERNAL = "intellivoiceservice.intent.action.BIND_TEXT_TO_SPEECH_FOR_EXTERNAL";
        public static final String ACTION_TONE_CONVERT_SERVICE = "android.intellivoiceservice.ToneConvertService";
        public static final String ACTION_TONE_CONVERT_SERVICE_FOR_EXTERNAL = "android.intellivoiceservice.ToneConvertServiceForExternal";
        public static final String ACTION_TRANSLATION_SERVICE = "intellivoiceservice.intent.action.BIND_TRANSLATION";
        public static final String ACTION_TRANSLATION_SERVICE_FOR_EXTERNAL = "intellivoiceservice.intent.action.BIND_TRANSLATION_FOR_EXTERNAL";
        public static final String ACTION_VISION_SERVICE = "scs.ai.intent.action.BIND_IMAGE_SERVICE";
        public static final String ACTION_WALLPAPER_SERVICE = "visual.intent.action.BIND_WALLPAPER_SERVICE";
        public static final String ACTION_WRITING_COMPOSER_SERVICE = "android.intellivoiceservice.WritingComposerService";
        public static final String ACTION_WRITING_COMPOSER_SERVICE_FOR_EXTERNAL = "android.intellivoiceservice.WritingComposerServiceForExternal";
    }
}
