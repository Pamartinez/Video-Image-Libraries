package androidx.emoji2.text;

import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.EmojiCompatInitializer;
import java.util.concurrent.ThreadPoolExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ EmojiCompatInitializer.BackgroundDefaultLoader d;
    public final /* synthetic */ EmojiCompat.MetadataRepoLoaderCallback e;
    public final /* synthetic */ ThreadPoolExecutor f;

    public /* synthetic */ b(EmojiCompatInitializer.BackgroundDefaultLoader backgroundDefaultLoader, EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback, ThreadPoolExecutor threadPoolExecutor) {
        this.d = backgroundDefaultLoader;
        this.e = metadataRepoLoaderCallback;
        this.f = threadPoolExecutor;
    }

    public final void run() {
        this.d.lambda$load$0(this.e, this.f);
    }
}
