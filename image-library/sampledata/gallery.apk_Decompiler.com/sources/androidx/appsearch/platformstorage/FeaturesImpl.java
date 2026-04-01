package androidx.appsearch.platformstorage;

import android.content.Context;
import androidx.appsearch.app.Features;
import androidx.core.util.Preconditions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FeaturesImpl implements Features {
    private final Context mContext;

    public FeaturesImpl(Context context) {
        this.mContext = (Context) Preconditions.checkNotNull(context);
    }
}
