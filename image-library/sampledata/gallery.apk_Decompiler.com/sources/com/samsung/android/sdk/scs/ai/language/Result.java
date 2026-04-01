package com.samsung.android.sdk.scs.ai.language;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Result {
    private final String content;
    private final String modelAlias;
    private final String safetyAttribute;

    public Result(Bundle bundle) {
        this.content = bundle.getString("content");
        this.safetyAttribute = bundle.getString("safety");
        this.modelAlias = bundle.getString("model_alias");
    }

    public String getContent() {
        String str = this.content;
        if (str == null) {
            return "";
        }
        return str;
    }

    public String getModelAlias() {
        String str = this.modelAlias;
        if (str == null) {
            return "";
        }
        return str;
    }

    public String getSafetyAttribute() {
        String str = this.safetyAttribute;
        if (str == null) {
            return "";
        }
        return str;
    }

    public String toString() {
        return "content: " + getContent() + ", safety attribute: " + getSafetyAttribute() + ", model alias: " + getModelAlias();
    }
}
