package com.samsung.android.sdk.mobileservice;

import android.content.Context;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeMobileServiceWatchSessionImpl extends SeMobileServiceSessionImpl {
    public SeMobileServiceWatchSessionImpl(Context context, HashSet<String> hashSet, String str, SeMobileServiceSession.ConnectionResultCallback connectionResultCallback) {
        super(context, hashSet, str, connectionResultCallback);
    }

    public int getAuthorized() {
        return 3;
    }
}
