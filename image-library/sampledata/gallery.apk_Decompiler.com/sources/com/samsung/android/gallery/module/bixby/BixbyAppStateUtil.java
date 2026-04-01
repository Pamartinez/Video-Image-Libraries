package com.samsung.android.gallery.module.bixby;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.bixby.BixbyKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BixbyAppStateUtil {
    public static final StateHandler.Callback EMPTY_CALLBACK = new StateHandler.Callback() {
        public List<String> getUsedPermissionsWhenAppStateRequested() {
            return BixbyAppStateUtil.getUsedPermissions();
        }

        public String onAppStateRequested() {
            return BixbyAppStateUtil.generateEmpty();
        }
    };
    public static final HashMap<String, Object> OPTIONS = new HashMap<String, Object>() {
        {
            Boolean bool = Boolean.FALSE;
            put("KEY_ADD_TAG", bool);
            put("KEY_AI_EDIT", bool);
            put("KEY_CREATE_ALBUM", bool);
            put("KEY_CREATE_GROUP", bool);
            put("KEY_CREATE_MOVIE", Integer.valueOf(BixbyAppStateUtil.getCreateMovieDefaultState()));
            put("KEY_DELETE", bool);
            put("KEY_EDIT", bool);
            put("KEY_GENERATIVE_EDIT", bool);
            put("KEY_MOVE_TO_ALBUM", 100);
            put("KEY_MOVE_TO_GROUP", bool);
            put("KEY_OBJECT_ERASER", bool);
            put("KEY_QUICK_CROP", 100);
            put("KEY_SAVE", bool);
            put("KEY_SCREEN", Integer.valueOf(BixbyKey.ScreenType.UNKNOWN.toInt()));
            put("KEY_SET_AS", bool);
            put("KEY_SHARE", bool);
            put("KEY_SIMILAR", bool);
            put("KEY_SIMILAR_STATE", 0);
            put("KEY_TRASH", bool);
            put("KEY_TRASH_IMAGE_COUNT", 0);
            put("KEY_TRASH_VIDEO_COUNT", 0);
            put("KEY_TRASH_ON", Boolean.valueOf(PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash)));
        }
    };
    private static final HashMap<Integer, String> SCREEN_CONTEXT_MAP = new HashMap<Integer, String>() {
        {
            put(Integer.valueOf(BixbyKey.ScreenType.UNKNOWN.toInt()), "GalleryUnknownScreen");
            put(Integer.valueOf(BixbyKey.ScreenType.VIEWER.toInt()), "ViewerScreen");
            put(Integer.valueOf(BixbyKey.ScreenType.PICTURES.toInt()), "PicturesTabScreen");
            put(Integer.valueOf(BixbyKey.ScreenType.ALBUMS.toInt()), "AlbumsTabScreen");
            put(Integer.valueOf(BixbyKey.ScreenType.STORIES.toInt()), "StoriesTabScreen");
            put(Integer.valueOf(BixbyKey.ScreenType.STORY_PICTURES.toInt()), "StoryPicturesScreen");
            put(Integer.valueOf(BixbyKey.ScreenType.SEARCH_RESULT.toInt()), "SearchResultScreen");
            put(Integer.valueOf(BixbyKey.ScreenType.SEARCH_NO_RESULT.toInt()), "SearchNoResultScreen");
            put(Integer.valueOf(BixbyKey.ScreenType.ALBUM_PICTURES.toInt()), "AlbumPicturesScreen");
        }
    };

    private static JSONObject appendLLMContextObject(JSONObject jSONObject, JSONObject jSONObject2, int i2) {
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("type", SCREEN_CONTEXT_MAP.get(Integer.valueOf(i2)));
            jSONObject3.put("value", jSONObject2);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject3);
            jSONObject.put(StateHandler.LLM_CONTEXT, jSONArray);
            jSONObject.put("llmCapsuleId", "samsung.galleryApp");
            Log.bx("BixbyAppStateUtil", "append llm. " + Logger.getEncodedString(jSONObject3.toString()));
            return jSONObject;
        } catch (JSONException e) {
            Log.bxe("BixbyAppStateUtil", "append llm context failed." + e.getMessage());
            return jSONObject;
        }
    }

    public static String generate(String str, HashMap<String, Object> hashMap) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry next : hashMap.entrySet()) {
                jSONObject.put((String) next.getKey(), next.getValue());
            }
            jSONObject.put("bb_name", str);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            JSONObject baseObject = getBaseObject(jSONArray);
            if (baseObject == null) {
                return null;
            }
            Log.bx("BixbyAppStateUtil", "generate. " + Logger.getEncodedString(baseObject.toString()));
            return appendLLMContextObject(baseObject, jSONObject, getScreenType(hashMap)).toString();
        } catch (JSONException e) {
            Log.bxe("BixbyAppStateUtil", "generate failed." + e.getMessage());
            return null;
        }
    }

    public static String generateEmpty() {
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry next : OPTIONS.entrySet()) {
                jSONObject.put((String) next.getKey(), next.getValue());
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            JSONObject baseObject = getBaseObject(jSONArray);
            if (baseObject == null) {
                return null;
            }
            Log.bx("BixbyAppStateUtil", "generate empty. " + Logger.getEncodedString(baseObject.toString()));
            return appendLLMContextObject(baseObject, jSONObject, BixbyKey.ScreenType.UNKNOWN.toInt()).toString();
        } catch (JSONException e) {
            Log.bxe("BixbyAppStateUtil", "generate empty failed." + e.getMessage());
            return null;
        }
    }

    private static JSONObject getBaseObject(JSONArray jSONArray) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "viv.galleryApp.AppContextResult");
            jSONObject.put(StateHandler.VALUES, jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(StateHandler.CONCEPTS, jSONArray2);
            return jSONObject2;
        } catch (JSONException e) {
            Log.bxe("BixbyAppStateUtil", "generate default failed." + e.getMessage());
            return null;
        }
    }

    public static String getContentType(FileItemInterface fileItemInterface) {
        if (fileItemInterface.isVideo()) {
            String shotMode = fileItemInterface.getShotMode();
            if (shotMode != null) {
                return BixbyKey.getVideoContentType(shotMode);
            }
            return "video";
        } else if (fileItemInterface.isGif()) {
            return "animatedgif";
        } else {
            if (fileItemInterface.isBurstShot()) {
                return "burstshot";
            }
            return BixbyKey.getImageContentType(fileItemInterface.getSefFileType());
        }
    }

    public static int getCreateMovieDefaultState() {
        if (Features.isEnabled(Features.SUPPORT_CREATE_HIGHLIGHT_REEL)) {
            return 100;
        }
        return 202;
    }

    public static String getNonNullValue(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return "";
    }

    private static int getScreenType(HashMap<String, Object> hashMap) {
        Integer num = (Integer) hashMap.get("KEY_SCREEN");
        if (num != null) {
            return num.intValue();
        }
        return BixbyKey.ScreenType.UNKNOWN.toInt();
    }

    public static int getStorageType(FileItemInterface fileItemInterface, String str) {
        if (LocationKey.containsTrash(str)) {
            return 3;
        }
        if (fileItemInterface.isCloud()) {
            return 2;
        }
        return 1;
    }

    public static List<String> getUsedPermissions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        arrayList.add("android.permission.READ_MEDIA_IMAGES");
        arrayList.add("android.permission.READ_MEDIA_VIDEO");
        return arrayList;
    }

    public static void updateEmptyState(String str) {
        Log.bx(str, "updateBixbyState empty state [" + str + "]");
        StateHandler stateHandler = Sbixby.getStateHandler();
        if (stateHandler != null) {
            stateHandler.updateStateChange(EMPTY_CALLBACK);
        }
    }
}
