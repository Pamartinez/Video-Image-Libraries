package com.samsung.android.sdk.pen.ocr;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrModelLoaderFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MODEL_LOADER {
        PROVIDER_LATEST,
        PROVIDER_ONEUI411,
        ONEUI41,
        ASSETS
    }

    public static SpenIOcrModelLoader createModelLoader(Context context, SpenIOcrModelManager spenIOcrModelManager, MODEL_LOADER model_loader) {
        if (model_loader == MODEL_LOADER.PROVIDER_LATEST) {
            return new SpenOcrModelLoaderForDataProvider_Dynamic(context, spenIOcrModelManager);
        }
        if (model_loader == MODEL_LOADER.PROVIDER_ONEUI411) {
            return new SpenOcrModelLoaderForDataProvider(context, spenIOcrModelManager);
        }
        if (model_loader == MODEL_LOADER.ONEUI41) {
            return new SpenOcrModelLoaderForDataProvider_OneUI41(context, spenIOcrModelManager);
        }
        return new SpenOcrModelLoaderForLocalAssetFile(context, spenIOcrModelManager);
    }
}
