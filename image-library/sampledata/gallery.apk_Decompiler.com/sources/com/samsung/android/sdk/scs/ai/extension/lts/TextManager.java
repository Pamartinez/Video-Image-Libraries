package com.samsung.android.sdk.scs.ai.extension.lts;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TextManager {
    private static final int DEFAULT_CHUNK_SIZE = 2950;
    private static final String INPUT_FILE_NAME = "summarization_30min.txt";
    private static final String TAG = "TextManager";
    private static final TextChunkContext mTextChunkContext = new TextChunkContext();

    private TextManager() {
    }

    public static void getChunkSenteneList(Context context, List<String> list) {
        list.addAll(mTextChunkContext.chunkTextWithDefaultSize(readInputTextFile(context, INPUT_FILE_NAME)));
    }

    private static String readInputTextFile(Context context, String str) {
        InputStream open;
        BufferedReader bufferedReader;
        StringBuilder sb2 = new StringBuilder();
        try {
            open = context.getAssets().open(str);
            InputStreamReader inputStreamReader = new InputStreamReader(open, StandardCharsets.UTF_8);
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb2.append(readLine);
                    sb2.append(" ");
                }
                bufferedReader.close();
                inputStreamReader.close();
                if (open != null) {
                    open.close();
                }
                return sb2.toString().trim();
            } catch (Throwable th) {
                inputStreamReader.close();
                throw th;
            }
            throw th;
            throw th;
        } catch (Exception e) {
            Log.e(TAG, "Error reading input file: " + str, e);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    public static void getChunkSenteneList(String str, List<String> list, int i2, int i7) {
        list.addAll(mTextChunkContext.chunkText(str, i2, i7));
    }

    public static void getChunkSenteneList(String str, List<String> list) {
        list.addAll(mTextChunkContext.chunkTextWithDefaultSize(str));
    }
}
