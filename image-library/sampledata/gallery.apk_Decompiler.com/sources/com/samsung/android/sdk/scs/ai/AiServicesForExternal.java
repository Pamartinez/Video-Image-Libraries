package com.samsung.android.sdk.scs.ai;

import android.content.Context;
import com.samsung.android.sdk.scs.ai.asr.SpeechRecognitionListener;
import com.samsung.android.sdk.scs.ai.asr.SpeechRecognizer;
import com.samsung.android.sdk.scs.ai.language.Corrector;
import com.samsung.android.sdk.scs.ai.language.CorrectorForExternal;
import com.samsung.android.sdk.scs.ai.language.SmartReplyer;
import com.samsung.android.sdk.scs.ai.language.SmartReplyerForExternal;
import com.samsung.android.sdk.scs.ai.language.SuggesterForExternal;
import com.samsung.android.sdk.scs.ai.language.Summarizer;
import com.samsung.android.sdk.scs.ai.language.SummarizerForExternal;
import com.samsung.android.sdk.scs.ai.language.ToneConverter;
import com.samsung.android.sdk.scs.ai.language.ToneConverterForExternal;
import com.samsung.android.sdk.scs.ai.language.WritingComposer;
import com.samsung.android.sdk.scs.ai.language.WritingComposerForExternal;
import com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeech;
import com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeechForExternal;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiServicesForExternal {
    private AiServicesForExternal() {
    }

    public static Corrector getCorrector(Context context) {
        return new CorrectorForExternal(context);
    }

    public static NeuralTranslator getNeuralTranslator(Context context) {
        return new NeuralTranslator(context, true);
    }

    public static SmartReplyer getSmartReplyer(Context context) {
        return new SmartReplyerForExternal(context);
    }

    public static SpeechRecognizer getSpeechRecognizer(Context context, SpeechRecognitionListener speechRecognitionListener) {
        return new SpeechRecognizer(context, speechRecognitionListener);
    }

    public static SuggesterForExternal getSuggesterForExternal(Context context) {
        return new SuggesterForExternal(context);
    }

    public static Summarizer getSummarizer(Context context) {
        return new SummarizerForExternal(context);
    }

    public static TextToSpeech getTextToSpeechForExternal(Context context) {
        return new TextToSpeechForExternal(context);
    }

    public static ToneConverter getToneConverter(Context context) {
        return new ToneConverterForExternal(context);
    }

    public static WritingComposer getWritingComposer(Context context) {
        return new WritingComposerForExternal(context);
    }
}
