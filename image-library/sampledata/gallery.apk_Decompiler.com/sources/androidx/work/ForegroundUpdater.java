package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ForegroundUpdater {
    ListenableFuture setForegroundAsync(Context context, UUID uuid, ForegroundInfo foregroundInfo);
}
