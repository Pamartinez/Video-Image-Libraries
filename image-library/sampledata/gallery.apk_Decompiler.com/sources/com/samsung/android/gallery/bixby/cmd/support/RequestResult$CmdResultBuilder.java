package com.samsung.android.gallery.bixby.cmd.support;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestResult$CmdResultBuilder {
    private final Bundle mExtra = new Bundle();
    private RequestResult$CmdState mState;

    public RequestResult$CmdResultBuilder setExtra(String str, String str2) {
        this.mExtra.putString(str, str2);
        return this;
    }

    public RequestResult$CmdResultBuilder setState(RequestResult$CmdState requestResult$CmdState) {
        this.mState = requestResult$CmdState;
        return this;
    }
}
