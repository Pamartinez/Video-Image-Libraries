package com.samsung.android.sdk.scs.ai.asr;

import android.os.Bundle;
import java.io.PrintStream;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SpeechRecognitionListener {
    void onError(int i2, String str, Bundle bundle);

    void onLocaleChanged(Locale locale, Bundle bundle) {
        PrintStream printStream = System.out;
        printStream.println("onLocaleUpdate : " + locale);
    }

    void onPartialResults(String str, Bundle bundle);

    void onProgressUpdate(int i2, Bundle bundle) {
        PrintStream printStream = System.out;
        printStream.println("onProgressUpdate : " + i2);
    }

    void onResults(String str, Bundle bundle);
}
