package com.samsung.android.sdk.scs.ai.suggestion;

import android.content.Context;
import com.samsung.android.sdk.scs.base.connection.ProviderExecutor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionProviderExecutor extends ProviderExecutor {
    private static final String TAG = "ScsApi@SuggestionProviderExecutor";

    public SuggestionProviderExecutor(Context context) {
        super(context, 1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingDeque());
    }
}
