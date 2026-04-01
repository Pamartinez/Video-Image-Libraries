package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.bixby.bixby.search.SearchAlbumData;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelper;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelperParams;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.bixby2.Sbixby;
import i.C0212a;
import java.io.File;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MoveToAlbumInfoActionHandler extends GalleryActionHandler {
    private JSONObject getInformation(int i2, String str, String str2, boolean z, boolean z3) {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            jSONObject.put("KEY_STATUS", "success");
            jSONObject.put("KEY_ALBUM_ID", i2);
            jSONObject.put("KEY_PATH", str);
            jSONObject.put("KEY_ALBUM_NAME", str2);
            jSONObject.put("KEY_EMPTY_ALBUM", z);
            jSONObject.put("KEY_NEW_ALBUM", z3);
            String str3 = this.TAG;
            Log.bx(str3, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (Exception e) {
            String str4 = this.TAG;
            Log.bxe(str4, "unable to get information, e=" + e.getMessage());
            return jSONObject;
        }
    }

    private ActionHelperParams getParams(String str) {
        return new ActionHelperParams.Builder().setName(str).addFlag(256).addFlag(16).build();
    }

    private void handleExecutable(Context context, String str) {
        boolean z;
        String str2;
        boolean z3;
        SearchAlbumData queryAlbumAndGroupDataFromName = new ActionHelper().queryAlbumAndGroupDataFromName(context, getParams(str));
        boolean isEmptyAlbum = queryAlbumAndGroupDataFromName.isEmptyAlbum();
        int albumId = queryAlbumAndGroupDataFromName.getAlbumId();
        String albumPath = queryAlbumAndGroupDataFromName.getAlbumPath();
        String albumName = queryAlbumAndGroupDataFromName.getAlbumName();
        if (albumId == 0 || TextUtils.isEmpty(albumPath)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(StorageInfo.getDefault().album);
            albumPath = C0212a.p(sb2, File.separator, str);
            albumId = -1;
            z = true;
            str2 = str;
            z3 = false;
        } else {
            str2 = albumName;
            z3 = isEmptyAlbum;
            z = false;
        }
        sendCallback(getResultString(getInformation(albumId, albumPath, str2, z3, z)));
    }

    public boolean isSupported() {
        return "MOVE_TO_ALBUM_INFO".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        String str = this.TAG;
        Log.bx(str, "found blackboard to start ACTION_MOVE_TO_ALBUM_INFO [" + blackboard.getName() + "]");
        String value = getValue("KEY_ALBUM_NAME");
        if (TextUtils.isEmpty(value)) {
            Log.bxe(this.TAG, "passed album name is null, so skip.");
        } else if (isExecutable(Sbixby.getStateHandler().getAppState(context, (Bundle) null), blackboard.getName())) {
            handleExecutable(context, value);
        }
    }
}
