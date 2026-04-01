package com.samsung.scsp.error;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Result {
    private static final int FAIL = 2;
    private static final String RCODE = "rcode";
    private static final String RESULT = "result";
    private static final String RMSG = "rmsg";
    private static final int SUCCESS = 1;
    public final int rcode;
    public final int result;
    public final String rmsg;
    public final boolean success;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder {
        public Result result = new Result();
    }

    public Result(int i2, String str) {
        this.success = false;
        this.result = 2;
        this.rcode = i2;
        this.rmsg = str;
    }

    public static boolean isSuccess(Bundle bundle) {
        if (bundle.getInt("result") == 1) {
            return true;
        }
        return false;
    }

    public Bundle bundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("result", this.result);
        bundle.putInt("rcode", this.rcode);
        bundle.putString("rmsg", this.rmsg);
        return bundle;
    }

    public Result() {
        this.success = true;
        this.result = 1;
        this.rcode = 20000000;
        this.rmsg = "";
    }
}
