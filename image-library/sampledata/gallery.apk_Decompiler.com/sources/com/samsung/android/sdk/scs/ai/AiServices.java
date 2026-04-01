package com.samsung.android.sdk.scs.ai;

import android.content.Context;
import com.samsung.android.sdk.scs.ai.asr.SpeechRecognitionListener;
import com.samsung.android.sdk.scs.ai.asr.SpeechRecognizer;
import com.samsung.android.sdk.scs.ai.core.ActionParamExtraction;
import com.samsung.android.sdk.scs.ai.core.GenericInference;
import com.samsung.android.sdk.scs.ai.core.IntentQueryGeneration;
import com.samsung.android.sdk.scs.ai.downloader.ModelDownloader;
import com.samsung.android.sdk.scs.ai.image.BoundaryDetector;
import com.samsung.android.sdk.scs.ai.image.ImageUpscaler;
import com.samsung.android.sdk.scs.ai.language.Classifier;
import com.samsung.android.sdk.scs.ai.language.Configuration;
import com.samsung.android.sdk.scs.ai.language.Corrector;
import com.samsung.android.sdk.scs.ai.language.EmojiAugmentor;
import com.samsung.android.sdk.scs.ai.language.Extractor;
import com.samsung.android.sdk.scs.ai.language.FormatConverter;
import com.samsung.android.sdk.scs.ai.language.Generic;
import com.samsung.android.sdk.scs.ai.language.LlmCloudUsageRequest;
import com.samsung.android.sdk.scs.ai.language.NotesOrganizer;
import com.samsung.android.sdk.scs.ai.language.SmartCoverer;
import com.samsung.android.sdk.scs.ai.language.SmartReplyer;
import com.samsung.android.sdk.scs.ai.language.Suggester;
import com.samsung.android.sdk.scs.ai.language.SuggesterForExternal;
import com.samsung.android.sdk.scs.ai.language.Summarizer;
import com.samsung.android.sdk.scs.ai.language.ToneConverter;
import com.samsung.android.sdk.scs.ai.language.Translator;
import com.samsung.android.sdk.scs.ai.language.WritingComposer;
import com.samsung.android.sdk.scs.ai.suggestion.AppCategorizer;
import com.samsung.android.sdk.scs.ai.suggestion.FolderNameSuggester;
import com.samsung.android.sdk.scs.ai.suggestion.KeywordSuggester;
import com.samsung.android.sdk.scs.ai.text.bnlp.BasicNlpAnalyzer;
import com.samsung.android.sdk.scs.ai.text.category.DocumentCategoryClassifier;
import com.samsung.android.sdk.scs.ai.text.entity.BasicEntityExtractor;
import com.samsung.android.sdk.scs.ai.text.event.EventCategoryClassifier;
import com.samsung.android.sdk.scs.ai.text.event.EventExtractor;
import com.samsung.android.sdk.scs.ai.text.language.LanguageDetector;
import com.samsung.android.sdk.scs.ai.text.phrase.KeyPhraseExtractor;
import com.samsung.android.sdk.scs.ai.text.reminder.ReminderEntityExtractor;
import com.samsung.android.sdk.scs.ai.text.unit.UnitConverter;
import com.samsung.android.sdk.scs.ai.texttospeech.CustomTextToSpeech;
import com.samsung.android.sdk.scs.ai.texttospeech.CustomTextToSpeechForExternal;
import com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeech;
import com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeechForExternal;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslator;
import com.samsung.android.sdk.scs.ai.visual.ImageEditorGenerator;
import com.samsung.android.sdk.scs.ai.visual.RequestType;
import com.samsung.android.sdk.scs.ai.visual.WallpaperGenerator;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paClient;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiServices {
    private AiServices() {
    }

    public static ActionParamExtraction getActionParamExtraction(Context context) {
        return new ActionParamExtraction(context);
    }

    public static AppCategorizer getAppCategorizer(Context context) {
        return new AppCategorizer(context);
    }

    public static BasicEntityExtractor getBasicEntityExtractor(Context context) {
        return new BasicEntityExtractor(context);
    }

    public static BasicNlpAnalyzer getBasicNlpAnalyzer(Context context) {
        return new BasicNlpAnalyzer(context);
    }

    public static BoundaryDetector getBoundaryDetector(Context context) {
        return new BoundaryDetector(context);
    }

    public static C2paClient getC2PAClient(Context context) {
        return new C2paClient(context);
    }

    public static Classifier getClassifier(Context context) {
        return new Classifier(context);
    }

    public static Configuration getConfiguration(Context context) {
        return new Configuration(context);
    }

    public static Corrector getCorrector(Context context) {
        return new Corrector(context);
    }

    public static CustomTextToSpeech getCustomTextToSpeech(Context context) {
        return new CustomTextToSpeech(context);
    }

    public static CustomTextToSpeechForExternal getCustomTextToSpeechForExternal(Context context) {
        return new CustomTextToSpeechForExternal(context);
    }

    public static DocumentCategoryClassifier getDocumentCategoryClassifier(Context context) {
        return new DocumentCategoryClassifier(context);
    }

    public static EmojiAugmentor getEmojiAugmentor(Context context) {
        return new EmojiAugmentor(context);
    }

    public static EventCategoryClassifier getEventCategoryClassifier(Context context) {
        return new EventCategoryClassifier(context);
    }

    public static EventExtractor getEventExtractor(Context context) {
        return new EventExtractor(context);
    }

    public static Extractor getExtractor(Context context) {
        return new Extractor(context);
    }

    public static FolderNameSuggester getFolderNameSuggester(Context context) {
        return new FolderNameSuggester(context);
    }

    public static FormatConverter getFormatConverter(Context context) {
        return new FormatConverter(context);
    }

    @Deprecated
    public static Generic getGeneric(Context context) {
        return new Generic(context);
    }

    public static GenericInference getGenericInference(Context context) {
        return new GenericInference(context);
    }

    public static ImageEditorGenerator getImageEditorGenerator(Context context, int i2) {
        return getImageEditorGenerator(context, i2, RequestType.CLOUD);
    }

    public static ImageUpscaler getImageUpscaler(Context context) {
        return new ImageUpscaler(context);
    }

    public static IntentQueryGeneration getIntentQueryGeneration(Context context) {
        return new IntentQueryGeneration(context);
    }

    public static KeyPhraseExtractor getKeyPhraseExtractor(Context context) {
        return new KeyPhraseExtractor(context);
    }

    public static KeywordSuggester getKeywordSuggester(Context context) {
        return new KeywordSuggester(context);
    }

    public static LanguageDetector getLanguageDetector(Context context) {
        return new LanguageDetector(context);
    }

    public static LlmCloudUsageRequest getLlmCloudUsage(Context context) {
        return new LlmCloudUsageRequest(context);
    }

    public static ModelDownloader getModelDownloader(Context context) {
        return new ModelDownloader(context);
    }

    public static NeuralTranslator getNeuralTranslator(Context context) {
        return new NeuralTranslator(context);
    }

    public static NotesOrganizer getNotesOrganizer(Context context) {
        return new NotesOrganizer(context);
    }

    public static ReminderEntityExtractor getReminderEntityExtractor(Context context) {
        return new ReminderEntityExtractor(context);
    }

    public static SmartCoverer getSmartCoverer(Context context) {
        return new SmartCoverer(context);
    }

    public static SmartReplyer getSmartReplyer(Context context) {
        return new SmartReplyer(context);
    }

    public static SpeechRecognizer getSpeechRecognizer(Context context, SpeechRecognitionListener speechRecognitionListener) {
        return new SpeechRecognizer(context, speechRecognitionListener);
    }

    public static Suggester getSuggester(Context context) {
        return new Suggester(context);
    }

    public static SuggesterForExternal getSuggesterForExternal(Context context) {
        return new SuggesterForExternal(context);
    }

    public static Summarizer getSummarizer(Context context) {
        return new Summarizer(context);
    }

    public static TextToSpeech getTextToSpeech(Context context) {
        return new TextToSpeech(context);
    }

    public static TextToSpeech getTextToSpeechForExternal(Context context) {
        return new TextToSpeechForExternal(context);
    }

    public static ToneConverter getToneConverter(Context context) {
        return new ToneConverter(context);
    }

    public static Translator getTranslator(Context context) {
        return new Translator(context);
    }

    public static UnitConverter getUnitConverter(Context context) {
        return new UnitConverter(context);
    }

    public static WallpaperGenerator getWallpaperGenerator(Context context) {
        return new WallpaperGenerator(context);
    }

    public static WritingComposer getWritingComposer(Context context) {
        return new WritingComposer(context);
    }

    public static ImageEditorGenerator getImageEditorGenerator(Context context, int i2, RequestType requestType) {
        return new ImageEditorGenerator(context, i2, requestType);
    }
}
