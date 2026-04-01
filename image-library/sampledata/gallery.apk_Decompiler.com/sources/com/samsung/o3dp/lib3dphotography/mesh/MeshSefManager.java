package com.samsung.o3dp.lib3dphotography.mesh;

import android.os.Build;
import android.util.Base64;
import android.util.Log;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.utils.SefUtil;
import com.samsung.o3dp.nativelib.BuildConfig;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MeshSefManager {
    private static final String JSON_KEY_CAMERA_ANIMATION = "cameraAnimation";
    private static final String JSON_KEY_LIVE_EFFECT_VERSION = "liveEffectVersion";
    private static final String JSON_KEY_MESH = "mesh";
    private static final String JSON_KEY_ONE_UI_VERSION = "oneUiVersion";
    private static final String TAG = "MeshSefManager";

    private static String createAnimatedMeshSefJson(byte[] bArr, String str) {
        String encodeToString = Base64.encodeToString(bArr, 2);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_MESH, encodeToString);
            jSONObject.put(JSON_KEY_CAMERA_ANIMATION, str);
            jSONObject.put(JSON_KEY_ONE_UI_VERSION, Build.VERSION.SEM_PLATFORM_INT);
            jSONObject.put(JSON_KEY_LIVE_EFFECT_VERSION, BuildConfig.SDK_VERSION);
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException("Failed to create mesh sef json", e);
        }
    }

    private static String createVersionSefJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ONE_UI_VERSION, Build.VERSION.SEM_PLATFORM_INT);
            jSONObject.put(JSON_KEY_LIVE_EFFECT_VERSION, BuildConfig.SDK_VERSION);
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException("Failed to create version sef json", e);
        }
    }

    public static AnimatedMesh extract(String str) {
        byte[] sefData = SefUtil.getSefData(str, SefUtil.SefMetadata.PHOTO_3D);
        if (sefData == null) {
            Log.w(TAG, "Failed to extract sef data from given file");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(sefData, StandardCharsets.UTF_8));
            byte[] decode = Base64.decode((String) jSONObject.get(JSON_KEY_MESH), 2);
            if (decode != null) {
                return new AnimatedMesh(decode, new Animation(jSONObject.getString(JSON_KEY_CAMERA_ANIMATION)));
            }
            Log.e(TAG, "Failed to extract mesh from file. Given file has no mesh");
            return null;
        } catch (JSONException e) {
            Log.e(TAG, "Failed to extract mesh from file, Please check file has valid sef", e);
            return null;
        }
    }

    public static void mux(String str, byte[] bArr, String str2) {
        SefUtil.addSefData(str, createAnimatedMeshSefJson(bArr, str2).getBytes(StandardCharsets.UTF_8), SefUtil.SefMetadata.PHOTO_3D);
    }

    public static void mux(String str) {
        SefUtil.addSefData(str, createVersionSefJson().getBytes(StandardCharsets.UTF_8), SefUtil.SefMetadata.PHOTO_3D);
    }
}
