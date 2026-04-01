package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode;

import android.app.KeyguardManager;
import android.view.MenuItem;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextActionModeCallback$onAssistMenuClicked$1", "Landroid/app/KeyguardManager$KeyguardDismissCallback;", "Lme/x;", "onDismissSucceeded", "()V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextActionModeCallback$onAssistMenuClicked$1 extends KeyguardManager.KeyguardDismissCallback {
    final /* synthetic */ MenuItem $assistMenu;
    final /* synthetic */ TextActionModeCallback this$0;

    public TextActionModeCallback$onAssistMenuClicked$1(TextActionModeCallback textActionModeCallback, MenuItem menuItem) {
        this.this$0 = textActionModeCallback;
        this.$assistMenu = menuItem;
    }

    public void onDismissSucceeded() {
        this.this$0.onAssistMenuClickedInternal(this.$assistMenu);
    }
}
