package com.samsung.android.sdk.scs.ai.visual;

import android.net.Uri;
import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WallpaperResult {
    Bundle retBundle;
    int status;
    Uri uri;

    public Bundle getBundle() {
        return this.retBundle;
    }

    public int getResult() {
        return this.status;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void setBundle(Bundle bundle) {
        this.retBundle = bundle;
    }

    public void setResult(int i2) {
        this.status = i2;
    }

    public void setUri(Uri uri2) {
        this.uri = uri2;
    }
}
