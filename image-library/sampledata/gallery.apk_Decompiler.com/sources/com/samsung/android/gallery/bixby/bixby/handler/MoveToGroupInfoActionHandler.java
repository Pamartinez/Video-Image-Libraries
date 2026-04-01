package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.bixby.bixby.search.SearchAlbumData;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelper;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelperParams;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.scs.base.StatusCodes;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MoveToGroupInfoActionHandler extends GalleryActionHandler {
    private int mErrorCode = 100;

    private JSONObject getInformation(String str, int i2, String str2, int i7) {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            jSONObject.put("KEY_STATUS", "success");
            jSONObject.put("KEY_ALBUM_NAME", str);
            jSONObject.put("KEY_ALBUM_ID", i2);
            jSONObject.put("KEY_GROUP_NAME", str2);
            jSONObject.put("KEY_GROUP_ID", i7);
            String str3 = this.TAG;
            Log.bx(str3, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (Exception e) {
            String str4 = this.TAG;
            Log.bxe(str4, "unable to get information, e=" + e.getMessage());
            return jSONObject;
        }
    }

    private ActionHelperParams getParams(String str, boolean z) {
        int i2;
        ActionHelperParams.Builder name = new ActionHelperParams.Builder().setName(str);
        if (z) {
            i2 = 256;
        } else {
            i2 = 4096;
        }
        return name.addFlag(i2).build();
    }

    private void handleExecutable(Blackboard blackboard) {
        String value = getValue("KEY_ALBUM_NAME");
        String value2 = getValue("KEY_GROUP_NAME");
        Context context = (Context) blackboard.read("data://app_context");
        if (TextUtils.isEmpty(value) || TextUtils.isEmpty(value2)) {
            Log.bxe(this.TAG, "passed album name and group name are null, so skip.");
            return;
        }
        SearchAlbumData queryAlbumAndGroupDataFromName = new ActionHelper().queryAlbumAndGroupDataFromName(context, getParams(value, true));
        SearchAlbumData queryAlbumAndGroupDataFromName2 = new ActionHelper().queryAlbumAndGroupDataFromName(context, getParams(value2, false));
        if (!isInvalid(queryAlbumAndGroupDataFromName, queryAlbumAndGroupDataFromName2.getAlbumId())) {
            sendCallback(getResultString(getInformation(queryAlbumAndGroupDataFromName.getAlbumName(), queryAlbumAndGroupDataFromName.getAlbumId(), queryAlbumAndGroupDataFromName2.getAlbumName(), queryAlbumAndGroupDataFromName2.getAlbumId())));
        }
    }

    private boolean isAlbums(String str) {
        if (LocationKey.isAlbumsMatch(str) || LocationKey.isAllAlbumsMatch(str) || LocationKey.isFolder(str)) {
            return true;
        }
        return false;
    }

    private boolean isExecutable(String str, String str2, String str3) {
        if (isAlbums(str)) {
            return isExecutable(str2, str3);
        }
        String str4 = this.TAG;
        Log.bxe(str4, "not executable [" + str + "]");
        return false;
    }

    private boolean isInvalid(SearchAlbumData searchAlbumData, int i2) {
        if (searchAlbumData == null || searchAlbumData.getAlbumName() == null || searchAlbumData.getAlbumId() == 0 || searchAlbumData.getAlbumPath() == null) {
            Log.bxe(this.TAG, "unable to handle move to group. album is not found");
            this.mErrorCode = StatusCodes.INPUT_MISSING;
            return true;
        } else if (i2 != 0) {
            return false;
        } else {
            Log.bxe(this.TAG, "unable to handle move to group. folder id is invalid");
            this.mErrorCode = 301;
            return true;
        }
    }

    public void getResultValue(JSONObject jSONObject) {
        super.getResultValue(jSONObject);
        try {
            jSONObject.put("KEY_ERROR", this.mErrorCode);
        } catch (JSONException e) {
            Log.bxe(this.TAG, e.getMessage());
        }
    }

    public boolean isSupported() {
        return "MOVE_TO_GROUP_INFO".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        String str = this.TAG;
        Log.bx(str, "found blackboard to start ACTION_MOVE_TO_GROUP_INFO [" + blackboard.getName() + "]");
        if (isExecutable((String) blackboard.read("location://variable/currentv1"), Sbixby.getStateHandler().getAppState(context, (Bundle) null), blackboard.getName())) {
            handleExecutable(blackboard);
        }
    }
}
