package com.samsung.android.app.sdk.deepsky.suggestion;

import android.content.Context;
import com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionItem;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponseImpl;", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponse;", "context", "Landroid/content/Context;", "suggestionItem", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;)V", "getSuggestionItem", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SuggestionResponseImpl implements SuggestionResponse {
    private final Context context;
    private final SuggestionItem suggestionItem;

    public SuggestionResponseImpl(Context context2, SuggestionItem suggestionItem2) {
        j.e(context2, "context");
        j.e(suggestionItem2, "suggestionItem");
        this.context = context2;
        this.suggestionItem = suggestionItem2;
    }

    public SuggestionItem getSuggestionItem() {
        return this.suggestionItem;
    }
}
