package com.samsung.android.sdk.scs.ai.core;

import B3.g;
import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GenericInferenceParam {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class task {
        public static final String TASK_GET_TOKEN_COUNT = "genericInferenceMethodTokenCount";
        public static final String TASK_INFERENCE = "genericInferenceMethodInference";
        public static final String TASK_PREWARM_MODEL = "genericInferenceMethodPrewarmModel";
    }

    public static g getOutputTensor(Bundle bundle) {
        return (g) bundle.getParcelable("genericInferenceOutputTensor");
    }

    public static int getTokenCountResult(Bundle bundle) {
        return bundle.getInt("genericInferenceTokenCountResult");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        protected Bundle params;

        public Builder() {
            this.params = new Bundle();
        }

        public Bundle build() {
            return this.params;
        }

        public Builder setInputTensor(g gVar) {
            this.params.putParcelable("genericInferenceInputTensor", gVar);
            return this;
        }

        public Builder setInputTextPrompt(String str) {
            this.params.putString("genericInferenceInputTextPrompt", str);
            return this;
        }

        public Builder setTask(String str) {
            this.params.putString("genericInferenceMethod", str);
            return this;
        }

        public Builder setTimeout(int i2) {
            this.params.putInt("timeout", i2);
            return this;
        }

        public Builder(Bundle bundle) {
            new Bundle();
            this.params = bundle;
        }
    }
}
