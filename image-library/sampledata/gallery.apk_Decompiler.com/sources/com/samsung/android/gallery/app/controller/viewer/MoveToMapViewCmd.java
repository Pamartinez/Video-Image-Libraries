package com.samsung.android.gallery.app.controller.viewer;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.scsp.media.file.FileApiContract;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MoveToMapViewCmd extends BaseCommand {
    public void onExecute(EventContext eventContext, Object... objArr) {
        JSONObject jSONObject = new JSONObject();
        MediaItem mediaItem = objArr[0];
        if (mediaItem == null) {
            Log.e(this.TAG, "MoveToMapViewCmd failed, item is null");
            return;
        }
        double latitude = mediaItem.getLatitude();
        double longitude = mediaItem.getLongitude();
        try {
            jSONObject.put("address", DetailsData.of(mediaItem).getAddress());
            jSONObject.put("latitude", latitude);
            jSONObject.put("longitude", longitude);
            jSONObject.put("entryItem", mediaItem.getFileId());
            jSONObject.put(FileApiContract.Parameter.PATH, mediaItem.getPath());
            getBlackboard().publish("data://user/map/InitialLocation", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getBlackboard().post("command://MoveURL", ArgumentsUtil.appendArgs("location://map", "no_locality", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE));
    }
}
