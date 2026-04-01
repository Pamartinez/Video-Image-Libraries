package com.samsung.android.gallery.module.deepsky;

import A.a;
import android.os.Bundle;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionData;
import com.samsung.android.app.sdk.deepsky.suggestion.CapabilityEnum;
import com.samsung.android.app.sdk.deepsky.suggestion.SuggestionResponse;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrResult;
import com.samsung.android.app.sdk.deepsky.textextraction.result.TextData;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DeepSkySuggest {
    private static boolean isSuggestionAvailableItem(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.isImage() || fileItemInterface.isCloudOnly()) {
            return false;
        }
        return true;
    }

    public static JsonObject loadScreenShotAction(FileItemInterface fileItemInterface) {
        List<SuggestionData> list;
        String str;
        if (isSuggestionAvailableItem(fileItemInterface)) {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle bundle = new Bundle();
            bundle.putParcelable("image_uri", ContentUri.getUri(fileItemInterface));
            SuggestionResponse requestSuggestion = DeepSkyHelper.getSuggestionRequest(AppResources.getAppContext()).requestSuggestion(CapabilityEnum.SCREEN_CATEGORY_INFORMATION, bundle);
            if (requestSuggestion == null) {
                list = null;
            } else {
                list = requestSuggestion.getSuggestionItem().getItems();
            }
            if (list == null || list.isEmpty()) {
                Log.w("DeepSkySuggest", "loadScreenShotAction. skip empty");
            } else {
                SuggestionData suggestionData = list.get(0);
                Log.d("DeepSkySuggest", "loadScreenShotAction" + Logger.vt(suggestionData.getTitle(), suggestionData.getDescription(), suggestionData.getIcon(), suggestionData.getUrl(), Long.valueOf(currentTimeMillis)));
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("Classification", suggestionData.getDescription());
                jsonObject.addProperty("Title", suggestionData.getTitle());
                if (suggestionData.getIcon() != null) {
                    str = suggestionData.getIcon().toString();
                } else {
                    str = "";
                }
                jsonObject.addProperty("IconUri", str);
                jsonObject.addProperty("ActionUrl", suggestionData.getUrl());
                return jsonObject;
            }
        }
        return null;
    }

    public static JsonObject loadTextCapsule(FileItemInterface fileItemInterface, TextData textData) {
        OcrResult ocrResult;
        if (isSuggestionAvailableItem(fileItemInterface)) {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle bundle = new Bundle();
            bundle.putParcelable("image_uri", ContentUri.getUri(fileItemInterface));
            if (textData != null) {
                ocrResult = textData.toOcrResult();
            } else {
                ocrResult = null;
            }
            bundle.putParcelable("ocr_result", ocrResult);
            bundle.putInt(BundleKey.REQUEST_TYPE, 2);
            SuggestionResponse requestSuggestion = DeepSkyHelper.getSuggestionRequest(AppResources.getAppContext()).requestSuggestion(CapabilityEnum.SCREEN_CATEGORY_INFORMATION, bundle);
            a.A(new Object[]{Long.valueOf(fileItemInterface.getMediaId()), Long.valueOf(fileItemInterface.getFileId()), bundle, requestSuggestion, Long.valueOf(currentTimeMillis)}, new StringBuilder("loadTextCapsule"), "DeepSkySuggest");
            if (requestSuggestion != null) {
                return GsonTool.toJsonObject(requestSuggestion.getSuggestionItem().getTitle());
            }
        }
        return null;
    }
}
