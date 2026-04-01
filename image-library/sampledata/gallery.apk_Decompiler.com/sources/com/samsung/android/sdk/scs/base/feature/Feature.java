package com.samsung.android.sdk.scs.base.feature;

import N2.j;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import com.samsung.android.sdk.scs.ai.BuildConfig;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import com.samsung.android.sdk.scs.base.utils.FeatureHelper;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst;
import i.C0212a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Feature {
    public static final String FEATURE_AI_CLOUD_LLM_INTERPRETATION = "FEATURE_AI_CLOUD_LLM_INTERPRETATION";
    public static final String FEATURE_AI_CUSTOM_TEXT_TO_SPEECH = "FEATURE_AI_CUSTOM_TEXT_TO_SPEECH";
    public static final String FEATURE_AI_GEN_B2B_ACCOUNT = "FEATURE_AI_GEN_B2B_ACCOUNT";
    public static final String FEATURE_AI_GEN_CORRECTION = "FEATURE_AI_GEN_CORRECTION";
    public static final String FEATURE_AI_GEN_CORRECTION_FOR_EXTERNAL = "FEATURE_AI_GEN_CORRECTION_FOR_EXTERNAL";
    public static final String FEATURE_AI_GEN_EMOJI_AUGMENTATION = "FEATURE_AI_GEN_EMOJI_AUGMENTATION";
    public static final String FEATURE_AI_GEN_GENERIC = "FEATURE_AI_GEN_GENERIC";
    public static final String FEATURE_AI_GEN_NOTES_ORGANIZATION = "FEATURE_AI_GEN_NOTES_ORGANIZATION";
    public static final String FEATURE_AI_GEN_SMART_CAPTURE = "FEATURE_AI_GEN_SMART_CAPTURE";
    public static final String FEATURE_AI_GEN_SMART_COVER = "FEATURE_AI_GEN_SMART_COVER";
    public static final String FEATURE_AI_GEN_SMART_REPLY = "FEATURE_AI_GEN_SMART_REPLY";
    public static final String FEATURE_AI_GEN_SMART_REPLY_FOR_EXTERNAL = "FEATURE_AI_GEN_SMART_REPLY_FOR_EXTERNAL";
    public static final String FEATURE_AI_GEN_SUGGESTION = "FEATURE_AI_GEN_SUGGESTION";
    public static final String FEATURE_AI_GEN_SUGGESTION_ONDEVICE = "FEATURE_AI_GEN_SUGGESTION_ONDEVICE";
    public static final String FEATURE_AI_GEN_SUMMARY = "FEATURE_AI_GEN_SUMMARY";
    public static final String FEATURE_AI_GEN_SUMMARY_FOR_EXTERNAL = "FEATURE_AI_GEN_SUMMARY_FOR_EXTERNAL";
    public static final String FEATURE_AI_GEN_TONE = "FEATURE_AI_GEN_TONE";
    public static final String FEATURE_AI_GEN_TONE_FOR_EXTERNAL = "FEATURE_AI_GEN_TONE_FOR_EXTERNAL";
    public static final String FEATURE_AI_GEN_TRANSLATION = "FEATURE_AI_GEN_TRANSLATION";
    public static final String FEATURE_AI_GEN_TRANSLATION_ONDEVICE = "FEATURE_AI_GEN_TRANSLATION_ONDEVICE";
    public static final String FEATURE_AI_GEN_USAGE = "FEATURE_AI_GEN_USAGE";
    public static final String FEATURE_AI_LANGUAGE_PACK_CONFIGURATION_PROVIDER = "FEATURE_AI_LANGUAGE_PACK_CONFIGURATION_PROVIDER";
    public static final String FEATURE_AI_ONDEVICE_LLM_PREWARM = "FEATURE_AI_ONDEVICE_LLM_PREWARM";
    public static final String FEATURE_AI_ONDEVICE_SUMMARY_EXTRA_PROMPT = "FEATURE_AI_ONDEVICE_SUMMARY_EXTRA_PROMPT";
    public static final String FEATURE_AI_TEXT_TO_SPEECH = "FEATURE_AI_TEXT_TO_SPEECH";
    public static final String FEATURE_AUDIO_TO_TRANSLATION = "FEATURE_AUDIO_TO_TRANSLATION";
    public static final String FEATURE_CORE_EXT_ACTION_PARAM_EXTRACTION = "FEATURE_ACTION_PARAM_EXTRACTION";
    public static final String FEATURE_CORE_EXT_GENERIC_INFERENCE = "FEATURE_GENERIC_INFERENCE";
    public static final String FEATURE_CORE_EXT_INTENT_QUERY = "FEATURE_INTENT_QUERY";
    public static final String FEATURE_FAST_SPEAKER_DIARISATION = "FEATURE_FAST_SPEAKER_DIARISATION";
    public static final String FEATURE_IMAGE_GET_BOUNDARIES = "FEATURE_IMAGE_GET_BOUNDARIES";
    public static final String FEATURE_IMAGE_GET_LARGEST_BOUNDARY = "FEATURE_IMAGE_GET_LARGEST_BOUNDARY";
    public static final String FEATURE_IMAGE_UPSCALE_IMAGE = "FEATURE_IMAGE_UPSCALE";
    public static final String FEATURE_LANGUAGE_IDENTIFICATION_AND_GET_CANDIDATE = "FEATURE_LANGUAGE_IDENTIFICATION_AND_GET_CANDIDATE";
    public static final String FEATURE_LANGUAGE_LIST_IDENTIFICATION = "FEATURE_LANGUAGE_LIST_IDENTIFICATION";
    public static final String FEATURE_NATURAL_LANGUAGE_QUERY = "FEATURE_NATURAL_LANGUAGE_QUERY";
    public static final String FEATURE_NEURAL_TRANSLATION = "FEATURE_NEURAL_TRANSLATION";
    public static final String FEATURE_NEURAL_TRANSLATION_BY_CHUNK = "FEATURE_NEURAL_TRANSLATION_BY_CHUNK";
    public static final String FEATURE_NEURAL_TRANSLATION_CLEAR_WITH_SOURCE_ID = "FEATURE_NEURAL_TRANSLATION_CLEAR_WITH_SOURCE_ID";
    public static final String FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL = "FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL";
    public static final String FEATURE_NEURAL_TRANSLATION_ONDEVICE_LLM = "FEATURE_NEURAL_TRANSLATION_ONDEVICE_LLM";
    public static final String FEATURE_NEURAL_TRANSLATION_SPEECH_LLM = "FEATURE_NEURAL_TRANSLATION_SPEECH_LLM";
    public static final String FEATURE_NEURAL_TRANSLATION_TAG_SUPPORTED = "FEATURE_NEURAL_TRANSLATION_TAG_SUPPORTED";
    public static final String FEATURE_SIVS_CLASSIFICATION = "FEATURE_SIVS_CLASSIFICATION";
    public static final String FEATURE_SIVS_CONFIGURATION = "FEATURE_SIVS_CONFIGURATION";
    public static final String FEATURE_SIVS_EXTRACTION = "FEATURE_SIVS_EXTRACTION";
    public static final String FEATURE_SIVS_EXTRACTION_ONDEVICE = "FEATURE_SIVS_EXTRACTION_ONDEVICE";
    public static final String FEATURE_SIVS_FORMAT_CONVERSION = "FEATURE_SIVS_FORMAT_CONVERSION";
    public static final String FEATURE_SIVS_WRITING_COMPOSER = "FEATURE_SIVS_WRITING_COMPOSER";
    public static final String FEATURE_SIVS_WRITING_COMPOSER_FOR_EXTERNAL = "FEATURE_SIVS_WRITING_COMPOSER_FOR_EXTERNAL";
    public static final String FEATURE_SIVS_WRITING_COMPOSER_ONDEVICE = "FEATURE_SIVS_WRITING_COMPOSER_ONDEVICE";
    public static final String FEATURE_SKETCH_GUIDED_EDITING_CROPPING_RECT = "FEATURE_SKETCH_GUIDE_EDITING_CROPPING_RECT";
    public static final String FEATURE_SPEAKER_DIARISATION = "FEATURE_SPEAKER_DIARISATION";
    public static final String FEATURE_SPEECH_LOCALE_RECOGNITION = "FEATURE_SPEECH_LOCALE_RECOGNITION";
    public static final String FEATURE_SPEECH_RECOGNITION = "FEATURE_SPEECH_RECOGNITION";
    public static final String FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY = "FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY";
    public static final String FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY_DETAILS = "FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY_DETAILS";
    public static final String FEATURE_SUGGESTION_SUGGEST_FOLDER_NAME = "FEATURE_SUGGESTION_SUGGEST_FOLDER_NAME";
    public static final String FEATURE_SUGGESTION_SUGGEST_KEYWORD = "FEATURE_SUGGESTION_SUGGEST_KEYWORD";
    public static final String FEATURE_TEXT_CONVERT_UNIT = "FEATURE_TEXT_CONVERT_UNIT";
    public static final String FEATURE_TEXT_DETECT_LANGUAGE = "FEATURE_TEXT_DETECT_LANGUAGE";
    public static final String FEATURE_TEXT_GET_BNLP = "FEATURE_TEXT_GET_BNLP";
    public static final String FEATURE_TEXT_GET_BNLP_LIGHT_MODEL = "FEATURE_TEXT_GET_BNLP_LIGHT_MODEL";
    public static final String FEATURE_TEXT_GET_BNLP_TOKEN = "FEATURE_TEXT_GET_BNLP_TOKEN";
    public static final String FEATURE_TEXT_GET_DOCUMENT_CATEGORY = "FEATURE_TEXT_GET_DOCUMENT_CATEGORY";
    public static final String FEATURE_TEXT_GET_ENTITY = "FEATURE_TEXT_GET_ENTITY";
    public static final String FEATURE_TEXT_GET_ENTITY_BANK = "FEATURE_TEXT_GET_ENTITY_BANK";
    public static final String FEATURE_TEXT_GET_ENTITY_BULK = "FEATURE_TEXT_GET_ENTITY_BULK";
    public static final String FEATURE_TEXT_GET_ENTITY_DATETIME_NUMERAL = "FEATURE_TEXT_GET_ENTITY_DATETIME_NUMERAL";
    public static final String FEATURE_TEXT_GET_ENTITY_DATETIME_SEARCH = "FEATURE_TEXT_GET_ENTITY_DATETIME_SEARCH";
    public static final String FEATURE_TEXT_GET_ENTITY_HAS_YEAR_MONTH_DAY = "FEATURE_TEXT_GET_ENTITY_HAS_YEAR_MONTH_DAY";
    public static final String FEATURE_TEXT_GET_ENTITY_IS_MAPPABLE = "FEATURE_TEXT_GET_ENTITY_IS_MAPPABLE";
    public static final String FEATURE_TEXT_GET_ENTITY_IS_RELATIVE = "FEATURE_TEXT_GET_ENTITY_IS_RELATIVE";
    public static final String FEATURE_TEXT_GET_ENTITY_IS_SPECIAL_DAY = "FEATURE_TEXT_GET_ENTITY_IS_SPECIAL_DAY";
    public static final String FEATURE_TEXT_GET_ENTITY_LOCAL_DATE_TIME = "FEATURE_TEXT_GET_ENTITY_LOCAL_DATE_TIME";
    public static final String FEATURE_TEXT_GET_ENTITY_PHONE_NUMBER = "FEATURE_TEXT_GET_ENTITY_PHONE_NUMBER";
    public static final String FEATURE_TEXT_GET_ENTITY_POI = "FEATURE_TEXT_GET_ENTITY_POI";
    public static final String FEATURE_TEXT_GET_ENTITY_START_OF_WEEK = "FEATURE_TEXT_GET_ENTITY_START_OF_WEEK";
    public static final String FEATURE_TEXT_GET_ENTITY_UNIT = "FEATURE_TEXT_GET_ENTITY_UNIT";
    public static final String FEATURE_TEXT_GET_ENTITY_UPI_ID = "FEATURE_TEXT_GET_ENTITY_UPI_ID";
    public static final String FEATURE_TEXT_GET_EVENT = "FEATURE_TEXT_GET_EVENT";
    public static final String FEATURE_TEXT_GET_EVENT_CATEGORY = "FEATURE_TEXT_GET_EVENT_CATEGORY";
    public static final String FEATURE_TEXT_GET_EVENT_HAS_YEAR_MONTH_DAY = "FEATURE_TEXT_GET_EVENT_HAS_YEAR_MONTH_DAY";
    public static final String FEATURE_TEXT_GET_EVENT_INDEX = "FEATURE_TEXT_GET_EVENT_INDEX";
    public static final String FEATURE_TEXT_GET_KEY_PHRASE = "FEATURE_TEXT_GET_KEY_PHRASE";
    public static final String FEATURE_TEXT_GET_KEY_PHRASE_EVENT_TITLE = "FEATURE_TEXT_GET_KEY_PHRASE_EVENT_TITLE";
    public static final String FEATURE_TEXT_GET_REMINDER_ENTITY = "FEATURE_TEXT_GET_REMINDER_ENTITY";
    public static final String FEATURE_VISUAL_AI_ERASER_ON_DEVICE = "FEATURE_AI_ERASER_ON_DEVICE";
    public static final String FEATURE_VISUAL_B2B_ACCOUNT = "FEATURE_B2B_ACCOUNT";
    public static final String FEATURE_VISUAL_C2PA = "FEATURE_C2PA";
    public static final String FEATURE_VISUAL_DESIGN_IMAGE = "FEATURE_DESIGN_IMAGE";
    public static final String FEATURE_VISUAL_DESIGN_STICKER = "FEATURE_DESIGN_STICKER";
    public static final String FEATURE_VISUAL_DOWNLOAD = "FEATURE_DOWNLOAD";
    public static final String FEATURE_VISUAL_GEN_EDIT_ON_DEVICE = "FEATURE_GEN_EDIT_ON_DEVICE";
    public static final String FEATURE_VISUAL_GEN_STICKER = "FEATURE_GEN_STICKER";
    public static final String FEATURE_VISUAL_HARMONIZATION_ON_DEVICE = "FEATURE_HARMONIZATION_ON_DEVICE";
    public static final String FEATURE_VISUAL_IMAGE_CONVERSION = "FEATURE_IMAGE_CONVERSION";
    public static final String FEATURE_VISUAL_IMAGE_CONVERSION_ON_DEVICE = "FEATURE_IMAGE_CONVERSION_ON_DEVICE";
    public static final String FEATURE_VISUAL_PET_PORTRAIT = "FEATURE_PET_PORTRAIT";
    public static final String FEATURE_VISUAL_PET_PORTRAIT_ON_DEVICE = "FEATURE_PET_PORTRAIT_ON_DEVICE";
    public static final String FEATURE_VISUAL_PORTRAIT = "FEATURE_PORTRAIT";
    public static final String FEATURE_VISUAL_PORTRAIT_ON_DEVICE = "FEATURE_PORTRAIT_ON_DEVICE";
    public static final String FEATURE_VISUAL_PORTRAIT_RELIGHT_ON_DEVICE = "FEATURE_PORTRAIT_RELIGHT_ON_DEVICE";
    public static final String FEATURE_VISUAL_PREREQUISITE = "FEATURE_PREREQUISITE";
    public static final String FEATURE_VISUAL_RESTYLING = "FEATURE_RESTYLING";
    public static final String FEATURE_VISUAL_SKETCH_GUIDED_EDITING = "FEATURE_SKETCH_GUIDE_EDITING";
    public static final String FEATURE_VISUAL_SKETCH_GUIDED_EDITING_ON_DEVICE = "FEATURE_SKETCH_GUIDE_EDITING_ON_DEVICE";
    public static final String FEATURE_VISUAL_SKETCH_TO_IMAGE = "FEATURE_SKETCH_TO_IMAGE";
    public static final String FEATURE_VISUAL_SKETCH_TO_IMAGE_ON_DEVICE = "FEATURE_SKETCH_TO_IMAGE_ON_DEVICE";
    public static final String FEATURE_VISUAL_WALLPAPER = "FEATURE_WALLPAPER";
    private static final List<String> SUPPORTED_AI_CORE_EXT_FEATURES = Arrays.asList(new String[]{FEATURE_CORE_EXT_INTENT_QUERY, FEATURE_CORE_EXT_ACTION_PARAM_EXTRACTION, FEATURE_CORE_EXT_GENERIC_INFERENCE});
    private static final List<String> SUPPORTED_AI_CORE_FEATURES = Arrays.asList(new String[]{FEATURE_VISUAL_DOWNLOAD, FEATURE_VISUAL_WALLPAPER, FEATURE_VISUAL_GEN_EDIT_ON_DEVICE, FEATURE_VISUAL_PORTRAIT_ON_DEVICE, FEATURE_VISUAL_SKETCH_TO_IMAGE_ON_DEVICE, FEATURE_VISUAL_SKETCH_GUIDED_EDITING_ON_DEVICE, FEATURE_VISUAL_PORTRAIT_RELIGHT_ON_DEVICE, FEATURE_VISUAL_IMAGE_CONVERSION_ON_DEVICE, FEATURE_VISUAL_AI_ERASER_ON_DEVICE, FEATURE_VISUAL_HARMONIZATION_ON_DEVICE, FEATURE_VISUAL_PET_PORTRAIT_ON_DEVICE});
    private static final List<String> SUPPORTED_SIVS_FEATURES = Arrays.asList(new String[]{FEATURE_SPEECH_RECOGNITION, FEATURE_SPEECH_LOCALE_RECOGNITION, FEATURE_SPEAKER_DIARISATION, FEATURE_AUDIO_TO_TRANSLATION, FEATURE_FAST_SPEAKER_DIARISATION, FEATURE_AI_GEN_SUMMARY, FEATURE_AI_GEN_TRANSLATION, FEATURE_AI_GEN_TONE, FEATURE_AI_GEN_CORRECTION, FEATURE_AI_GEN_SUGGESTION, FEATURE_AI_GEN_SUGGESTION_ONDEVICE, FEATURE_AI_GEN_SMART_COVER, FEATURE_AI_GEN_SMART_REPLY, FEATURE_AI_GEN_EMOJI_AUGMENTATION, FEATURE_AI_GEN_NOTES_ORGANIZATION, FEATURE_AI_GEN_SMART_CAPTURE, FEATURE_AI_GEN_GENERIC, FEATURE_AI_GEN_USAGE, FEATURE_NEURAL_TRANSLATION, FEATURE_LANGUAGE_LIST_IDENTIFICATION, FEATURE_LANGUAGE_IDENTIFICATION_AND_GET_CANDIDATE, FEATURE_NEURAL_TRANSLATION_BY_CHUNK, FEATURE_NEURAL_TRANSLATION_CLEAR_WITH_SOURCE_ID, FEATURE_NEURAL_TRANSLATION_TAG_SUPPORTED, FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL, FEATURE_NEURAL_TRANSLATION_SPEECH_LLM, FEATURE_SIVS_CLASSIFICATION, FEATURE_SIVS_CONFIGURATION, FEATURE_SIVS_EXTRACTION, FEATURE_SIVS_EXTRACTION_ONDEVICE, FEATURE_SIVS_WRITING_COMPOSER, FEATURE_SIVS_WRITING_COMPOSER_ONDEVICE, FEATURE_SIVS_FORMAT_CONVERSION, FEATURE_AI_LANGUAGE_PACK_CONFIGURATION_PROVIDER, FEATURE_AI_GEN_B2B_ACCOUNT, FEATURE_AI_GEN_TRANSLATION_ONDEVICE, FEATURE_NEURAL_TRANSLATION_ONDEVICE_LLM, FEATURE_AI_TEXT_TO_SPEECH, FEATURE_AI_GEN_SUMMARY_FOR_EXTERNAL, FEATURE_AI_GEN_TONE_FOR_EXTERNAL, FEATURE_AI_GEN_CORRECTION_FOR_EXTERNAL, FEATURE_AI_GEN_SMART_REPLY_FOR_EXTERNAL, FEATURE_SIVS_WRITING_COMPOSER_FOR_EXTERNAL, FEATURE_AI_CUSTOM_TEXT_TO_SPEECH, FEATURE_AI_ONDEVICE_SUMMARY_EXTRA_PROMPT, FEATURE_AI_CLOUD_LLM_INTERPRETATION, FEATURE_AI_ONDEVICE_LLM_PREWARM});
    private static final List<String> SUPPORTED_VISUAL_CLOUD_FEATURES = Arrays.asList(new String[]{FEATURE_VISUAL_PORTRAIT, FEATURE_VISUAL_SKETCH_TO_IMAGE, FEATURE_VISUAL_SKETCH_GUIDED_EDITING, FEATURE_SKETCH_GUIDED_EDITING_CROPPING_RECT, FEATURE_VISUAL_C2PA, FEATURE_VISUAL_PREREQUISITE, FEATURE_VISUAL_GEN_STICKER, FEATURE_VISUAL_IMAGE_CONVERSION, FEATURE_VISUAL_PET_PORTRAIT, FEATURE_VISUAL_RESTYLING, FEATURE_VISUAL_DESIGN_IMAGE, FEATURE_VISUAL_DESIGN_STICKER});
    private static final String TAG = "ScsApi@Feature";
    private static Boolean sIsVst;
    private static Boolean sIsWatch;
    private static final Map<String, Integer> sinceVersionMap;

    @Retention(RetentionPolicy.CLASS)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface featureName {
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(FEATURE_IMAGE_GET_BOUNDARIES, 1);
        hashMap.put(FEATURE_IMAGE_GET_LARGEST_BOUNDARY, 1);
        hashMap.put(FEATURE_IMAGE_UPSCALE_IMAGE, 2);
        hashMap.put(FEATURE_SUGGESTION_SUGGEST_KEYWORD, 1);
        hashMap.put(FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY, 2);
        hashMap.put(FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY_DETAILS, 3);
        hashMap.put(FEATURE_SUGGESTION_SUGGEST_FOLDER_NAME, 2);
        hashMap.put(FEATURE_TEXT_GET_ENTITY, 2);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_BULK, 24);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_DATETIME_NUMERAL, 9);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_DATETIME_SEARCH, 22);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_PHONE_NUMBER, 9);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_POI, 10);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_BANK, 9);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_IS_MAPPABLE, 15);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_IS_RELATIVE, 15);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_IS_SPECIAL_DAY, 17);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_HAS_YEAR_MONTH_DAY, 18);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_UPI_ID, 16);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_UNIT, 20);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_START_OF_WEEK, 26);
        hashMap.put(FEATURE_TEXT_GET_ENTITY_LOCAL_DATE_TIME, 27);
        hashMap.put(FEATURE_TEXT_GET_EVENT, 13);
        hashMap.put(FEATURE_TEXT_GET_EVENT_HAS_YEAR_MONTH_DAY, 18);
        hashMap.put(FEATURE_TEXT_GET_EVENT_INDEX, 21);
        hashMap.put(FEATURE_TEXT_GET_KEY_PHRASE, 3);
        hashMap.put(FEATURE_TEXT_GET_KEY_PHRASE_EVENT_TITLE, 11);
        hashMap.put(FEATURE_TEXT_GET_DOCUMENT_CATEGORY, 5);
        hashMap.put(FEATURE_TEXT_GET_BNLP, 2);
        hashMap.put(FEATURE_TEXT_GET_BNLP_TOKEN, 12);
        hashMap.put(FEATURE_TEXT_GET_BNLP_LIGHT_MODEL, 26);
        hashMap.put(FEATURE_TEXT_DETECT_LANGUAGE, 9);
        hashMap.put(FEATURE_TEXT_CONVERT_UNIT, 19);
        Object obj = FEATURE_CORE_EXT_GENERIC_INFERENCE;
        Object obj2 = FEATURE_CORE_EXT_ACTION_PARAM_EXTRACTION;
        j.l(23, hashMap, FEATURE_TEXT_GET_REMINDER_ENTITY, 25, FEATURE_TEXT_GET_EVENT_CATEGORY);
        hashMap.put(FEATURE_NATURAL_LANGUAGE_QUERY, 1);
        hashMap.put(FEATURE_SPEECH_RECOGNITION, SpeechRecognitionConst.SINCE_SPEECH_RECOGNITION);
        hashMap.put(FEATURE_SPEECH_LOCALE_RECOGNITION, SpeechRecognitionConst.SINCE_SPEECH_LOCALE_RECOGNITION);
        hashMap.put(FEATURE_SPEAKER_DIARISATION, SpeechRecognitionConst.SINCE_SPEAKER_DIARISATION);
        hashMap.put(FEATURE_AUDIO_TO_TRANSLATION, SpeechRecognitionConst.SINCE_AUDIO_TO_TRANSLATION);
        hashMap.put(FEATURE_FAST_SPEAKER_DIARISATION, SpeechRecognitionConst.SINCE_FEATURE_FAST_SOUND_RECOGNITION);
        hashMap.put(FEATURE_AI_GEN_SUMMARY, 6);
        hashMap.put(FEATURE_AI_GEN_TRANSLATION, 6);
        hashMap.put(FEATURE_AI_GEN_TONE, 6);
        hashMap.put(FEATURE_AI_GEN_CORRECTION, 6);
        hashMap.put(FEATURE_AI_GEN_SUGGESTION, 7);
        hashMap.put(FEATURE_AI_GEN_SUGGESTION_ONDEVICE, 10);
        hashMap.put(FEATURE_AI_GEN_SMART_COVER, 6);
        hashMap.put(FEATURE_AI_GEN_SMART_REPLY, 6);
        hashMap.put(FEATURE_AI_GEN_EMOJI_AUGMENTATION, 6);
        hashMap.put(FEATURE_AI_GEN_NOTES_ORGANIZATION, 6);
        hashMap.put(FEATURE_AI_GEN_SMART_CAPTURE, 6);
        hashMap.put(FEATURE_AI_GEN_GENERIC, 6);
        hashMap.put(FEATURE_NEURAL_TRANSLATION, 1);
        hashMap.put(FEATURE_LANGUAGE_LIST_IDENTIFICATION, 7);
        hashMap.put(FEATURE_LANGUAGE_IDENTIFICATION_AND_GET_CANDIDATE, 8);
        hashMap.put(FEATURE_NEURAL_TRANSLATION_BY_CHUNK, 11);
        hashMap.put(FEATURE_NEURAL_TRANSLATION_TAG_SUPPORTED, 10);
        hashMap.put(FEATURE_NEURAL_TRANSLATION_CLEAR_WITH_SOURCE_ID, 10);
        hashMap.put(FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL, 12);
        hashMap.put(FEATURE_SIVS_CLASSIFICATION, 6);
        hashMap.put(FEATURE_SIVS_EXTRACTION, 6);
        hashMap.put(FEATURE_SIVS_EXTRACTION_ONDEVICE, 10);
        hashMap.put(FEATURE_SIVS_WRITING_COMPOSER, 8);
        hashMap.put(FEATURE_SIVS_WRITING_COMPOSER_ONDEVICE, 10);
        hashMap.put(FEATURE_SIVS_FORMAT_CONVERSION, 10);
        hashMap.put(FEATURE_SIVS_CONFIGURATION, 6);
        hashMap.put(FEATURE_AI_GEN_USAGE, 6);
        hashMap.put(FEATURE_AI_LANGUAGE_PACK_CONFIGURATION_PROVIDER, 9);
        hashMap.put(FEATURE_AI_GEN_B2B_ACCOUNT, 12);
        hashMap.put(FEATURE_AI_GEN_TRANSLATION_ONDEVICE, 13);
        hashMap.put(FEATURE_NEURAL_TRANSLATION_ONDEVICE_LLM, 12);
        hashMap.put(FEATURE_NEURAL_TRANSLATION_SPEECH_LLM, 13);
        hashMap.put(FEATURE_AI_ONDEVICE_SUMMARY_EXTRA_PROMPT, 14);
        hashMap.put(FEATURE_AI_CLOUD_LLM_INTERPRETATION, 15);
        hashMap.put(FEATURE_AI_ONDEVICE_LLM_PREWARM, 16);
        hashMap.put(FEATURE_AI_GEN_SUMMARY_FOR_EXTERNAL, 11);
        hashMap.put(FEATURE_AI_GEN_TONE_FOR_EXTERNAL, 11);
        hashMap.put(FEATURE_AI_GEN_CORRECTION_FOR_EXTERNAL, 11);
        hashMap.put(FEATURE_AI_GEN_SMART_REPLY_FOR_EXTERNAL, 11);
        hashMap.put(FEATURE_SIVS_WRITING_COMPOSER_FOR_EXTERNAL, 11);
        hashMap.put(FEATURE_AI_TEXT_TO_SPEECH, 2);
        hashMap.put(FEATURE_AI_CUSTOM_TEXT_TO_SPEECH, 3);
        hashMap.put(FEATURE_VISUAL_WALLPAPER, 1);
        hashMap.put(FEATURE_VISUAL_DOWNLOAD, 1);
        hashMap.put(FEATURE_VISUAL_PORTRAIT, 1);
        hashMap.put(FEATURE_VISUAL_SKETCH_TO_IMAGE, 1);
        hashMap.put(FEATURE_VISUAL_SKETCH_GUIDED_EDITING, 1);
        hashMap.put(FEATURE_SKETCH_GUIDED_EDITING_CROPPING_RECT, 2);
        hashMap.put(FEATURE_VISUAL_C2PA, 3);
        hashMap.put(FEATURE_VISUAL_GEN_STICKER, 6);
        hashMap.put(FEATURE_VISUAL_IMAGE_CONVERSION, 6);
        hashMap.put(FEATURE_VISUAL_B2B_ACCOUNT, 7);
        hashMap.put(FEATURE_VISUAL_PET_PORTRAIT, 8);
        hashMap.put(FEATURE_VISUAL_RESTYLING, 9);
        hashMap.put(FEATURE_VISUAL_DESIGN_IMAGE, 10);
        hashMap.put(FEATURE_VISUAL_DESIGN_STICKER, 10);
        hashMap.put(FEATURE_VISUAL_IMAGE_CONVERSION_ON_DEVICE, 8);
        hashMap.put(FEATURE_VISUAL_AI_ERASER_ON_DEVICE, 9);
        hashMap.put(FEATURE_VISUAL_HARMONIZATION_ON_DEVICE, 9);
        hashMap.put(FEATURE_VISUAL_GEN_EDIT_ON_DEVICE, 4);
        hashMap.put(FEATURE_VISUAL_SKETCH_TO_IMAGE_ON_DEVICE, 5);
        hashMap.put(FEATURE_VISUAL_PORTRAIT_ON_DEVICE, 10);
        hashMap.put(FEATURE_VISUAL_PET_PORTRAIT_ON_DEVICE, 10);
        hashMap.put(FEATURE_VISUAL_PREREQUISITE, 2);
        hashMap.put(FEATURE_CORE_EXT_INTENT_QUERY, 1);
        hashMap.put(obj2, 1);
        hashMap.put(obj, 2);
        sinceVersionMap = Collections.unmodifiableMap(hashMap);
    }

    public static int checkFeature(Context context, String str) {
        Log.i(TAG, "checkFeature() : " + str + ", sdk : 4.0.53");
        if (context == null || str == null) {
            Log.e(TAG, "checkFeature(). input is null. context: " + context + ", feature: " + str);
            return StatusCodes.INPUT_MISSING;
        }
        String serviceAppName = getServiceAppName(context, str);
        try {
            if (!context.getPackageManager().getApplicationInfo(serviceAppName, 128).enabled) {
                Log.w(TAG, "checkFeature(). " + serviceAppName + " has disabled.");
                FeatureStatusCache.setStatus(str, 2);
                return 2;
            }
            int checkScsFeature = checkScsFeature(context, serviceAppName, str);
            FeatureStatusCache.setStatus(str, checkScsFeature);
            return checkScsFeature;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "dump(), " + serviceAppName + " does not exist");
            FeatureStatusCache.setStatus(str, 1);
            return 1;
        }
    }

    private static int checkScsFeature(Context context, String str, String str2) {
        int i2;
        int featureVersionFromSettings = FeatureHelper.getFeatureVersionFromSettings(context, str, str2, getGlobalSettingsKeyName(context, str2));
        if (featureVersionFromSettings == -2) {
            featureVersionFromSettings = FeatureHelper.getFeatureVersionFromProvider(context, Uri.parse(getUriString(context, str2)), str2);
        }
        if (featureVersionFromSettings == -2) {
            Log.e(TAG, "checkScsFeature(). retBundle == null!!!");
            return 2000;
        } else if (featureVersionFromSettings == 0) {
            Log.w(TAG, "checkScsFeature(). " + str2 + " is not available!!");
            return 5;
        } else if (featureVersionFromSettings == -1) {
            Log.w(TAG, "checkScsFeature(). SCS doesn't know " + str2 + ". SCS update might be required.");
            return 3;
        } else {
            Map<String, Integer> map = sinceVersionMap;
            if (map.containsKey(str2)) {
                i2 = map.get(str2).intValue();
            } else {
                i2 = Integer.MAX_VALUE;
            }
            if (featureVersionFromSettings >= i2) {
                return 0;
            }
            StringBuilder u = C0212a.u("checkScsFeature(). ", str2, ", scsVersion: ", featureVersionFromSettings, ", sinceVersion: ");
            u.append(i2);
            Log.e(TAG, u.toString());
            return 3;
        }
    }

    public static void clear() {
        FeatureStatusCache.clear();
    }

    public static int getApiVersionCode() {
        return Integer.parseInt(BuildConfig.VERSION_CODE);
    }

    public static String getApiVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    private static String getGlobalSettingsKeyName(Context context, String str) {
        if (isSIVSAvailableOSVersion(context) && SUPPORTED_SIVS_FEATURES.contains(str)) {
            return "scs_sivs_supported_feature_info";
        }
        if (SUPPORTED_AI_CORE_FEATURES.contains(str) || SUPPORTED_VISUAL_CLOUD_FEATURES.contains(str)) {
            return "scs_visual_supported_feature_info";
        }
        return "scs_core_supported_feature_info";
    }

    private static PackageInfo getPackageInfo(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getQueryService(Context context, Intent intent, String str) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices.isEmpty()) {
            Log.d(TAG, "getQueryService is null. use default " + str);
            return str;
        }
        Log.d(TAG, "getQueryService : " + queryIntentServices.get(0).serviceInfo.packageName);
        return queryIntentServices.get(0).serviceInfo.packageName;
    }

    public static int getScsVersionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context, BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES);
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return -1;
    }

    public static String getScsVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context, BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES);
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    private static String getServiceAppName(Context context, String str) {
        if (isSIVSAvailableOSVersion(context) && SUPPORTED_SIVS_FEATURES.contains(str)) {
            return BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES;
        }
        if (SUPPORTED_AI_CORE_FEATURES.contains(str)) {
            return BaseConstants.PACKAGE_INFO.PACKAGE_AICORE_SERVICES;
        }
        if (SUPPORTED_VISUAL_CLOUD_FEATURES.contains(str)) {
            return BaseConstants.PACKAGE_INFO.PACKAGE_VISUAL_CLOUD_CORE_SERVICES;
        }
        if (SUPPORTED_AI_CORE_EXT_FEATURES.contains(str)) {
            return getQueryService(context, new Intent(BaseConstants.SERVICE_ACTION.ACTION_ON_DEVICE_SERVICE), BaseConstants.PACKAGE_INFO.PACKAGE_AICORE_SERVICES);
        }
        return BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES;
    }

    public static int getSivsVersionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context, BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return -1;
    }

    public static String getSivsVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context, BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    private static String getUriString(Context context, String str) {
        if (isSIVSAvailableOSVersion(context) && SUPPORTED_SIVS_FEATURES.contains(str)) {
            return "content://com.samsung.android.intellivoiceservice.feature";
        }
        if (SUPPORTED_AI_CORE_FEATURES.contains(str) || SUPPORTED_AI_CORE_EXT_FEATURES.contains(str)) {
            return "content://com.samsung.android.aicore.feature";
        }
        if (SUPPORTED_VISUAL_CLOUD_FEATURES.contains(str)) {
            return "content://com.samsung.android.visual.cloudcore.feature";
        }
        return "content://com.samsung.android.scs.feature";
    }

    public static boolean isSIVSAvailableOSVersion(Context context) {
        try {
            if (!isWatch(context)) {
                if (!isVst(context)) {
                    if (Build.VERSION.SDK_INT < 34 || Build.VERSION.SEM_PLATFORM_INT < 150100) {
                        return false;
                    }
                    return true;
                }
            }
            if (Build.VERSION.SDK_INT < 34 || Build.VERSION.SEM_PLATFORM_INT < 150000) {
                return false;
            }
            return true;
        } catch (Exception | NoSuchFieldError unused) {
        }
    }

    private static boolean isVst(Context context) {
        boolean z;
        Boolean bool = sIsVst;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            if (!context.getPackageManager().hasSystemFeature("android.software.xr.api.spatial")) {
                if (!context.getPackageManager().hasSystemFeature("android.software.xr.api.openxr")) {
                    z = false;
                    Boolean valueOf = Boolean.valueOf(z);
                    sIsVst = valueOf;
                    return valueOf.booleanValue();
                }
            }
            z = true;
            Boolean valueOf2 = Boolean.valueOf(z);
            sIsVst = valueOf2;
            return valueOf2.booleanValue();
        } catch (Error | Exception unused) {
            return false;
        }
    }

    private static boolean isWatch(Context context) {
        Boolean bool = sIsWatch;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
            sIsWatch = valueOf;
            return valueOf.booleanValue();
        } catch (Error | Exception unused) {
            return false;
        }
    }
}
