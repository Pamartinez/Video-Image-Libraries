package com.samsung.android.gallery.bixby.bixby.handler;

import G7.e;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiEditActionHandler extends GalleryActionHandler {
    private String mErrorCode = "unsupported";

    private String getResult(Object[] objArr) {
        String str = objArr[0];
        if (Objects.equals(str, "no_error")) {
            JSONObject success = getSuccess();
            try {
                if (objArr.length > 3) {
                    String str2 = objArr[1];
                    String str3 = objArr[2];
                    Uri uri = objArr[3];
                    if (!TextUtils.isEmpty(str3) && uri != null) {
                        if (TextUtils.isEmpty(str2)) {
                            str2 = FileType.getMimeType(str3);
                        }
                        success.put("KEY_MIME_TYPE", str2);
                        success.put("KEY_URI", uri);
                        success.put("KEY_PATH", str3);
                    }
                }
            } catch (Exception e) {
                String str4 = this.TAG;
                Log.bxe(str4, "unable to generate json object for success message, e=" + e.getMessage());
            }
            return getResultString(success);
        }
        this.mErrorCode = str;
        return getFailResultString();
    }

    private void handleExecutable(Blackboard blackboard) {
        blackboard.subscribe("data://bixby_command_done", new e(5, this));
        String value = getValue("KEY_EDIT_MODE");
        if (TextUtils.isEmpty(value)) {
            Log.bxe(this.TAG, "passed argument are empty, so skip.");
            return;
        }
        blackboard.postEvent(EventMessage.obtain(3008, Uri.parse("applink://com.sec.android.gallery3d/DETAIL_VIEW_AI_EDIT").buildUpon().appendQueryParameter("KEY_INTERNAL_REQUEST", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).appendQueryParameter("KEY_EDIT_MODE", value).appendQueryParameter("KEY_AUTO_SAVE", String.valueOf(UnsafeCast.toBoolean(getValue("KEY_AUTO_SAVE"), false))).build()));
        setWait();
    }

    private boolean isExecutable(FileItemInterface fileItemInterface, String str, String str2, Blackboard blackboard) {
        if (!LocationKey.isMtp(str) && !LocationKey.isSuggestionViewList(str) && LocationKey.supportDetails(str) && !isFlipCover(blackboard)) {
            return isExecutableInViewer(fileItemInterface, str, str2, blackboard.getName());
        }
        String str3 = this.TAG;
        Log.bxe(str3, "not executable [" + str + "]");
        return false;
    }

    private boolean isFlipCover(Blackboard blackboard) {
        if (!FoldUtils.SUPPORT_FLIP_COVER_GALLERY || !FoldUtils.isFlipCoverScreen(BlackboardUtils.readActivity(blackboard))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleExecutable$0(Object obj, Bundle bundle) {
        try {
            sendCallback(getResult((Object[]) obj));
        } catch (Exception e) {
            String str = this.TAG;
            Log.bxe(str, "can not execute bixby command done. " + e.getMessage());
            sendCallback(getFailResultString());
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
        return "DETAIL_VIEW_AI_EDIT".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        if (!isActivityResumed(BlackboardUtils.readActivity(blackboard))) {
            Log.bxe(this.TAG, "activity is not resumed. so skip.");
        } else if (isExecutable((FileItemInterface) blackboard.pop("data://bixby_mediaItem"), (String) blackboard.read("location://variable/currentv1"), Sbixby.getStateHandler().getAppState(context, (Bundle) null), blackboard)) {
            handleExecutable(blackboard);
        }
    }
}
