package androidx.emoji2.text;

import androidx.emoji2.text.FontRequestEmojiCompatConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ FontRequestEmojiCompatConfig.FontRequestMetadataLoader d;

    public /* synthetic */ c(FontRequestEmojiCompatConfig.FontRequestMetadataLoader fontRequestMetadataLoader) {
        this.d = fontRequestMetadataLoader;
    }

    public final void run() {
        this.d.createMetadata();
    }
}
