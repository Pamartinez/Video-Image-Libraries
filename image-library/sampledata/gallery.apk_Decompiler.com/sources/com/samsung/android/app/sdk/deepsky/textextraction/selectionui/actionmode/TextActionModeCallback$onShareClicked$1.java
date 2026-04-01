package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextActionModeCallback$onShareClicked$1", "Landroid/app/KeyguardManager$KeyguardDismissCallback;", "Lme/x;", "onDismissSucceeded", "()V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextActionModeCallback$onShareClicked$1 extends KeyguardManager.KeyguardDismissCallback {
    final /* synthetic */ TextActionModeCallback this$0;

    public TextActionModeCallback$onShareClicked$1(TextActionModeCallback textActionModeCallback) {
        this.this$0 = textActionModeCallback;
    }

    public void onDismissSucceeded() {
        Intent access$makeChooserIntent = this.this$0.makeChooserIntent(true);
        if (access$makeChooserIntent != null) {
            TextActionModeCallback textActionModeCallback = this.this$0;
            access$makeChooserIntent.setFlags(268435456);
            ((Activity) textActionModeCallback.context).startActivity(access$makeChooserIntent);
            TextExtractionDrawHelper.OnToolbarMenuClickListener unused = textActionModeCallback.getClass();
            textActionModeCallback.listener.onShareClicked();
        }
        this.this$0.lastSelectedText = "";
    }
}
