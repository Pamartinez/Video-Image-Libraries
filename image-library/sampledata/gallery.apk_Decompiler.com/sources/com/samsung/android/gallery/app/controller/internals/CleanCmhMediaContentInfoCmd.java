package com.samsung.android.gallery.app.controller.internals;

import android.os.Bundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.providers.CmhUri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanCmhMediaContentInfoCmd extends BaseCommand {
    private void cleanMediaContentInfo(long j2) {
        Bundle bundle = new Bundle();
        bundle.putLongArray("sec_media_id", new long[]{j2});
        getApplicationContext().getContentResolver().call(CmhUri.getAuthority(), "clean_media_content_info", (String) null, bundle);
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        cleanMediaContentInfo(objArr[0].longValue());
    }
}
