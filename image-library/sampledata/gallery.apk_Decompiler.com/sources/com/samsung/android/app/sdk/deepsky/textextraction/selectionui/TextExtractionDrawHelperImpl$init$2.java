package com.samsung.android.app.sdk.deepsky.textextraction.selectionui;

import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode.ActionModeListener;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0004¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelperImpl$init$2", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/ActionModeListener;", "Lme/x;", "onSelectAllClicked", "()V", "onCopyClicked", "onTranslateClicked", "onShareClicked", "onAssistMenuClicked", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextExtractionDrawHelperImpl$init$2 implements ActionModeListener {
    final /* synthetic */ TextExtractionDrawHelperImpl this$0;

    public TextExtractionDrawHelperImpl$init$2(TextExtractionDrawHelperImpl textExtractionDrawHelperImpl) {
        this.this$0 = textExtractionDrawHelperImpl;
    }

    public void onAssistMenuClicked() {
        this.this$0.clearAllSelection();
    }

    public void onCopyClicked() {
        this.this$0.clearAllSelection();
    }

    public void onSelectAllClicked() {
        this.this$0.selectAll();
    }

    public void onShareClicked() {
        this.this$0.clearAllSelection();
    }

    public void onTranslateClicked() {
        this.this$0.clearAllSelection();
    }
}
