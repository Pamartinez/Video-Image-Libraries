package com.samsung.o3dp.lib3dphotography.animation;

import android.util.Range;
import com.samsung.android.sdk.spage.card.event.Event;
import com.samsung.android.sum.core.filter.n;
import com.samsung.o3dp.lib3dphotography.animation.dynamics.DampedOscillationDynamicOffset;
import com.samsung.o3dp.lib3dphotography.animation.dynamics.DynamicOffset;
import com.samsung.o3dp.lib3dphotography.animation.dynamics.NoneDynamicOffset;
import com.samsung.o3dp.lib3dphotography.animation.dynamics.SimpleHarmonicDynamicOffset;
import com.samsung.o3dp.lib3dphotography.animation.movement.BezierMovement;
import com.samsung.o3dp.lib3dphotography.animation.movement.CircularMovement;
import com.samsung.o3dp.lib3dphotography.animation.movement.LinearMovement;
import com.samsung.o3dp.lib3dphotography.animation.movement.Movement;
import com.samsung.o3dp.lib3dphotography.animation.movement.SplineMovement;
import com.samsung.o3dp.lib3dphotography.animation.time.AnimationTime;
import com.samsung.o3dp.lib3dphotography.animation.time.BounceAnimationTime;
import com.samsung.o3dp.lib3dphotography.animation.time.LoopAnimationTime;
import com.samsung.o3dp.lib3dphotography.animation.time.SingleAnimationTime;
import com.samsung.o3dp.lib3dphotography.animation.transition.EaseTransition;
import com.samsung.o3dp.lib3dphotography.animation.transition.LinearTransition;
import com.samsung.o3dp.lib3dphotography.animation.transition.PathTransition;
import com.samsung.o3dp.lib3dphotography.animation.transition.Transition;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.interaction.InputEvent;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.scsp.media.file.FileApiContract;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Animation implements Comparable<Animation> {
    private static final String DEFAULT_ANIMATION_TIME_MODE = "bounce";
    private static final String DEFAULT_DYNAMIC_OFFSET_MODE = "damped_oscillation";
    private static final String DEFAULT_MOVEMENT = "linear";
    private static final String DEFAULT_TRANSITION = "linear";
    private static final String TAG = "Animation";
    private AnimationParams[] mAnimationParams;
    private String mAnimationScript;
    private AnimationTime mAnimationTime;
    private Boolean mBokehEnabled;
    private float mCameraDirPivotZ;
    private Vector3 mCameraLookAt;
    private DynamicOffset mDynamicOffset;
    private boolean mEmbossing;
    private Movement mMovement;
    private String mName;
    private int mSortOrder;
    private float mSpeedFactor;
    public boolean mStaticAnimation;
    private Transition mTransition;
    private final HashMap<String, AnimationTime> mValidAnimationTimeModes;
    private final HashMap<String, DynamicOffset> mValidDynamicOffset;
    private final HashMap<String, Movement> mValidMovements;
    private final HashMap<String, Transition> mValidTransitions;

    public Animation() {
        AnonymousClass1 r0 = new HashMap<String, Transition>() {
            {
                put("ease", new EaseTransition());
                put("linear", new LinearTransition());
                put(FileApiContract.Parameter.PATH, new PathTransition());
            }
        };
        this.mValidTransitions = r0;
        AnonymousClass2 r1 = new HashMap<String, AnimationTime>() {
            {
                put("loop", new LoopAnimationTime());
                put(Animation.DEFAULT_ANIMATION_TIME_MODE, new BounceAnimationTime());
                put("single", new SingleAnimationTime());
            }
        };
        this.mValidAnimationTimeModes = r1;
        AnonymousClass3 r22 = new HashMap<String, Movement>() {
            {
                put("linear", new LinearMovement());
                put("spline", new SplineMovement());
                put("bezier", new BezierMovement());
                put("circle", new CircularMovement());
            }
        };
        this.mValidMovements = r22;
        AnonymousClass4 r32 = new HashMap<String, DynamicOffset>() {
            {
                put("none", new NoneDynamicOffset());
                put("simple_harmonic", new SimpleHarmonicDynamicOffset());
                put(Animation.DEFAULT_DYNAMIC_OFFSET_MODE, new DampedOscillationDynamicOffset());
            }
        };
        this.mValidDynamicOffset = r32;
        this.mCameraLookAt = new Vector3(0.0f, 0.0f, 0.0f);
        this.mStaticAnimation = false;
        this.mCameraDirPivotZ = 0.5f;
        this.mName = Event.DEFAULT_EVENT_TYPE;
        this.mSortOrder = 100;
        this.mAnimationTime = (AnimationTime) r1.get(DEFAULT_ANIMATION_TIME_MODE);
        this.mSpeedFactor = 1.0f;
        this.mTransition = (Transition) r0.get("linear");
        this.mMovement = (Movement) r22.get("linear");
        this.mDynamicOffset = (DynamicOffset) r32.get(DEFAULT_DYNAMIC_OFFSET_MODE);
        this.mBokehEnabled = Boolean.FALSE;
    }

    private void addJSONParam(String str, Object obj) {
        if (this.mAnimationScript != null) {
            try {
                JSONObject jSONObject = new JSONObject(new JSONTokener(this.mAnimationScript));
                jSONObject.put(str, obj);
                this.mAnimationScript = jSONObject.toString();
            } catch (JSONException e) {
                LogUtil.e(TAG, "JSONException: " + e);
            }
        }
    }

    private static String componentName(String str, String str2) {
        if (!str.matches(".*\\[\\d+]")) {
            return C0212a.B(str, "_", str2);
        }
        return str.replaceAll("\\[", "_" + str2 + "[");
    }

    private static Vector3 parseVector(JSONObject jSONObject, String str, Vector3 vector3) {
        Vector3 vector32 = new Vector3(vector3);
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null && optJSONArray.length() == 3) {
            vector32.f4210x = (float) optJSONArray.getDouble(0);
            vector32.y = (float) optJSONArray.getDouble(1);
            vector32.z = (float) optJSONArray.getDouble(2);
        }
        vector32.f4210x = (float) jSONObject.optDouble(componentName(str, "x"), (double) vector32.f4210x);
        vector32.y = (float) jSONObject.optDouble(componentName(str, "y"), (double) vector32.y);
        vector32.z = (float) jSONObject.optDouble(componentName(str, "z"), (double) vector32.z);
        return vector32;
    }

    public void compute(int i2, int i7, int i8, int i10) {
        float f = ((float) i2) / ((float) i7);
        if (i2 == 0 || i7 == 0) {
            f = 1.0f;
        }
        this.mDynamicOffset.setAmplitude(f, 1.0f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Animation animation = (Animation) obj;
            if (this.mSortOrder != animation.mSortOrder || !this.mName.equals(animation.mName)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void generateTime(AnimationParams[] animationParamsArr) {
        if (animationParamsArr.length == 0) {
            LogUtil.e(TAG, "empty params");
            return;
        }
        int i2 = 0;
        Float f = animationParamsArr[0].time;
        if (f == null || f.floatValue() > 0.0f) {
            LogUtil.w(TAG, "Modifying missing keyframe at time 0.0");
            animationParamsArr[0].time = Float.valueOf(0.0f);
        }
        if (animationParamsArr[animationParamsArr.length - 1].time == null || animationParamsArr[animationParamsArr.length - 1].time.floatValue() < 1.0f) {
            LogUtil.w(TAG, "Modifying missing keyframe at time 1.0");
            animationParamsArr[animationParamsArr.length - 1].time = Float.valueOf(1.0f);
        }
        for (int i7 = 1; i7 < animationParamsArr.length - 1; i7++) {
            Float f5 = animationParamsArr[i7].time;
            if (f5 != null && (f5.floatValue() == 0.0f || animationParamsArr[i7].time.floatValue() == 1.0f)) {
                LogUtil.w(TAG, animationParamsArr[i7].time + " cannot exist inside the time array.");
                animationParamsArr[i7].time = null;
            }
        }
        float[] fArr = new float[animationParamsArr.length];
        fArr[0] = 0.0f;
        for (int i8 = 1; i8 < animationParamsArr.length; i8++) {
            int i10 = i8 - 1;
            float distanceToNextParam = this.mMovement.getDistanceToNextParam(i10, animationParamsArr, new n(21));
            fArr[i8] = distanceToNextParam;
            fArr[i8] = fArr[i10] + distanceToNextParam;
        }
        for (int i11 = 1; i11 < animationParamsArr.length; i11++) {
            Float f8 = animationParamsArr[i11].time;
            if (f8 != null) {
                if (i11 - i2 > 1) {
                    float floatValue = f8.floatValue() - animationParamsArr[i2].time.floatValue();
                    float f10 = fArr[i11] - fArr[i2];
                    while (true) {
                        i2++;
                        if (i2 >= i11) {
                            break;
                        }
                        int i12 = i2 - 1;
                        animationParamsArr[i2].time = Float.valueOf(animationParamsArr[i12].time.floatValue() + ((floatValue / f10) * (fArr[i2] - fArr[i12])));
                    }
                }
                i2 = i11;
            }
        }
    }

    public AnimationParams[] getAnimationParams() {
        return this.mAnimationParams;
    }

    public String getAnimationScript() {
        return this.mAnimationScript;
    }

    public AnimationTime getAnimationTime() {
        return this.mAnimationTime;
    }

    public float getCameraDirPivotZ() {
        return this.mCameraDirPivotZ;
    }

    public Vector3 getCameraLookAt() {
        return this.mCameraLookAt;
    }

    public DynamicOffset getDynamicOffset() {
        return this.mDynamicOffset;
    }

    public Movement getMovement() {
        return this.mMovement;
    }

    public String getName() {
        return this.mName;
    }

    public int getSensorType() {
        return this.mDynamicOffset.getSensorType();
    }

    public int getSortOrder() {
        return this.mSortOrder;
    }

    public float getSpeedFactor() {
        return this.mSpeedFactor;
    }

    public Transition getTransition() {
        return this.mTransition;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mSortOrder), this.mName});
    }

    public boolean isBokehEnabled() {
        return this.mBokehEnabled.booleanValue();
    }

    public boolean isEmbossing() {
        return this.mEmbossing;
    }

    public boolean isSensorInteraction() {
        if (getSensorType() != -1) {
            return true;
        }
        return false;
    }

    public boolean isStaticAnimation() {
        return this.mStaticAnimation;
    }

    public boolean isTouchInteraction() {
        return this.mDynamicOffset.isTouchInteraction();
    }

    public boolean onMotionSensorEvent(InputEvent.MotionSensor motionSensor) {
        this.mDynamicOffset.onMotionSensorEvent(motionSensor);
        return true;
    }

    public boolean onTouchEvent(InputEvent.Touch touch) {
        return this.mDynamicOffset.onTouchEvent(touch);
    }

    public void reset() {
        this.mAnimationTime.reset();
    }

    public void setAnimationParams(AnimationParams[] animationParamsArr) {
        this.mAnimationParams = animationParamsArr;
    }

    public void setAnimationTimeMode(AnimationTime animationTime) {
        this.mAnimationTime = animationTime;
    }

    public void setBokehEnabled(Boolean bool) {
        this.mBokehEnabled = bool;
    }

    public void setCameraDirPivotZ(float f) {
        this.mCameraDirPivotZ = f;
        addJSONParam("camera_dir_pivot_z", Float.valueOf(f));
    }

    public void setCameraLookAt(Vector3 vector3) {
        this.mCameraLookAt = vector3;
        addJSONParam("camera_look_at", Arrays.toString(new float[]{vector3.f4210x, vector3.y, vector3.z}));
    }

    public void setDynamicOffset(String str) {
        if (!this.mValidDynamicOffset.containsKey(str)) {
            LogUtil.e(TAG, "DynamicOffset is not valid: " + str);
            return;
        }
        this.mDynamicOffset = this.mValidDynamicOffset.get(str);
    }

    public void setDynamicsIntensity(int i2) {
        this.mDynamicOffset.setDynamicsIntensity(i2);
        addJSONParam("dynamic_offset_options", "dynamics_intensity", Integer.valueOf(i2));
    }

    public void setMovement(String str) {
        if (!this.mValidMovements.containsKey(str)) {
            LogUtil.e(TAG, "Movement is not valid: " + str);
            return;
        }
        this.mMovement = this.mValidMovements.get(str);
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setSortOrder(int i2) {
        this.mSortOrder = i2;
    }

    public void setSpeedFactor(float f) {
        this.mSpeedFactor = f;
    }

    public void setStaticAnimation(boolean z) {
        this.mStaticAnimation = z;
        addJSONParam("static_animation", Boolean.valueOf(z));
    }

    public void setStaticDynamicsDirection(int i2) {
        this.mDynamicOffset.setStaticDynamicsDirection(i2);
        addJSONParam("dynamic_offset_options", "static_dynamics_direction", Integer.valueOf(i2));
    }

    public void setTransition(String str) {
        if (!this.mValidTransitions.containsKey(str)) {
            LogUtil.e(TAG, "Transition is not valid: " + str);
            return;
        }
        this.mTransition = this.mValidTransitions.get(str);
    }

    public int compareTo(Animation animation) {
        if (this.mSortOrder < animation.getSortOrder()) {
            return -1;
        }
        if (this.mSortOrder > animation.getSortOrder()) {
            return 1;
        }
        return this.mName.compareTo(animation.mName);
    }

    public void setAnimationTimeMode(String str) {
        if (!this.mValidAnimationTimeModes.containsKey(str)) {
            LogUtil.e(TAG, "Mode is not valid: " + str);
            return;
        }
        this.mAnimationTime = this.mValidAnimationTimeModes.get(str);
    }

    private void addJSONParam(String str, String str2, Object obj) {
        JSONObject jSONObject;
        if (this.mAnimationScript != null) {
            try {
                JSONObject jSONObject2 = new JSONObject(new JSONTokener(this.mAnimationScript));
                if (jSONObject2.has(str)) {
                    jSONObject = jSONObject2.getJSONObject(str);
                } else {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject2.put(str, jSONObject3);
                    jSONObject = jSONObject3;
                }
                jSONObject.put(str2, obj);
                this.mAnimationScript = jSONObject2.toString();
            } catch (JSONException e) {
                LogUtil.e(TAG, "JSONException: " + e);
            }
        }
    }

    public Animation(String str) {
        String str2;
        String str3;
        JSONArray jSONArray;
        Range range;
        String str4;
        String str5;
        char c5;
        String str6;
        String str7 = str;
        String str8 = DEFAULT_ANIMATION_TIME_MODE;
        String str9 = "linear";
        String str10 = "bokeh_focus_depth";
        String str11 = "]";
        String str12 = "auto";
        String str13 = "camera_dir";
        String str14 = "time";
        String str15 = ", using damped_oscillation";
        String str16 = "Invalid dynamicOffset specification: ";
        AnonymousClass1 r15 = new HashMap<String, Transition>() {
            {
                put("ease", new EaseTransition());
                put("linear", new LinearTransition());
                put(FileApiContract.Parameter.PATH, new PathTransition());
            }
        };
        this.mValidTransitions = r15;
        String str17 = DEFAULT_DYNAMIC_OFFSET_MODE;
        AnonymousClass2 r62 = new HashMap<String, AnimationTime>() {
            {
                put("loop", new LoopAnimationTime());
                put(Animation.DEFAULT_ANIMATION_TIME_MODE, new BounceAnimationTime());
                put("single", new SingleAnimationTime());
            }
        };
        this.mValidAnimationTimeModes = r62;
        String str18 = "transition_value";
        AnonymousClass3 r72 = new HashMap<String, Movement>() {
            {
                put("linear", new LinearMovement());
                put("spline", new SplineMovement());
                put("bezier", new BezierMovement());
                put("circle", new CircularMovement());
            }
        };
        this.mValidMovements = r72;
        String str19 = " - using linear";
        AnonymousClass4 r22 = new HashMap<String, DynamicOffset>() {
            {
                put("none", new NoneDynamicOffset());
                put("simple_harmonic", new SimpleHarmonicDynamicOffset());
                put(Animation.DEFAULT_DYNAMIC_OFFSET_MODE, new DampedOscillationDynamicOffset());
            }
        };
        this.mValidDynamicOffset = r22;
        AnonymousClass4 r26 = r22;
        String str20 = "Invalid transition specifier: ";
        this.mCameraLookAt = new Vector3(0.0f, 0.0f, 0.0f);
        this.mStaticAnimation = false;
        float f = 0.0f;
        this.mCameraDirPivotZ = 0.5f;
        this.mAnimationScript = str7;
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(new JSONTokener(str7));
            if (jSONObject.has("keyframes")) {
                if (jSONObject.has("name")) {
                    this.mName = jSONObject.getString("name");
                    this.mSortOrder = jSONObject.optInt("sort_order", 100);
                    String str21 = "keyframes";
                    this.mSpeedFactor = (float) jSONObject.optDouble("speed_factor", 1.0d);
                    this.mBokehEnabled = Boolean.valueOf(jSONObject.optBoolean("bokeh", false));
                    this.mEmbossing = jSONObject.optBoolean("embossing", false);
                    this.mCameraLookAt = parseVector(jSONObject, "camera_look_at", this.mCameraLookAt);
                    this.mStaticAnimation = jSONObject.optBoolean("static_animation", false);
                    this.mCameraDirPivotZ = (float) jSONObject.optDouble("camera_dir_pivot_z", 0.5d);
                    String optString = jSONObject.optString("mode", str8);
                    if (!r62.containsKey(optString)) {
                        LogUtil.w(TAG, "Invalid mode specification: " + optString + ", using bounce");
                    } else {
                        str8 = optString;
                    }
                    this.mAnimationTime = (AnimationTime) r62.get(str8);
                    String optString2 = jSONObject.optString("movement", str9);
                    if (!r72.containsKey(optString2)) {
                        LogUtil.w(TAG, "Invalid movement specification: " + optString2 + ", using linear");
                        optString2 = str9;
                    }
                    this.mMovement = (Movement) r72.get(optString2);
                    String optString3 = jSONObject.optString("transition", str9);
                    if (!r15.containsKey(optString3)) {
                        LogUtil.w(TAG, str20 + optString3 + str19);
                    } else {
                        str9 = optString3;
                    }
                    this.mTransition = (Transition) r15.get(str9);
                    String str22 = str18;
                    if (jSONObject.has(str22)) {
                        this.mTransition.setValue(jSONObject.get(str22));
                    }
                    String str23 = str17;
                    String optString4 = jSONObject.optString("dynamic_offset", str23);
                    AnonymousClass4 r5 = r26;
                    if (!r5.containsKey(optString4)) {
                        LogUtil.w(TAG, str16 + optString4 + str15);
                        str2 = str23;
                    } else {
                        str2 = optString4;
                    }
                    this.mDynamicOffset = (DynamicOffset) r5.get(str2);
                    JSONObject optJSONObject = jSONObject.optJSONObject("dynamic_offset_options");
                    if (optJSONObject != null) {
                        this.mDynamicOffset.parseConfig(optJSONObject);
                    }
                    char c6 = 0;
                    Range range2 = new Range(Float.valueOf(0.0f), Float.valueOf(1.0f));
                    JSONArray jSONArray2 = jSONObject.getJSONArray(str21);
                    int i2 = 0;
                    while (i2 < jSONArray2.length()) {
                        AnimationParams animationParams = new AnimationParams();
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                        String str24 = str14;
                        if (jSONObject2.has(str24)) {
                            animationParams.time = Float.valueOf((float) jSONObject2.getDouble(str24));
                            if (arrayList.isEmpty() || (range2.contains(animationParams.time) && animationParams.time.floatValue() > f)) {
                                f = animationParams.time.floatValue();
                            } else {
                                LogUtil.w(TAG, "Keyframe time is not monotonically increasing - ignoring keyframe at time " + animationParams.time);
                                jSONArray = jSONArray2;
                                range = range2;
                                c5 = c6;
                                str14 = str24;
                                str6 = str10;
                                str3 = str11;
                                str4 = str12;
                                str5 = str13;
                                i2++;
                                str10 = str6;
                                c6 = c5;
                                str13 = str5;
                                str12 = str4;
                                range2 = range;
                                jSONArray2 = jSONArray;
                                str11 = str3;
                            }
                        }
                        animationParams.cameraEye = parseVector(jSONObject2, "camera_eye", animationParams.cameraEye);
                        str5 = str13;
                        str4 = str12;
                        if (jSONObject2.optString(str5, "").compareTo(str4) == 0) {
                            animationParams.autoCameraDir = Boolean.TRUE;
                        } else {
                            animationParams.cameraDir = parseVector(jSONObject2, str5, animationParams.cameraDir);
                        }
                        animationParams.cameraUp = parseVector(jSONObject2, "camera_up", animationParams.cameraUp);
                        animationParams.cameraDir.normalize();
                        animationParams.cameraUp.normalize();
                        int i7 = 0;
                        while (i7 < 2) {
                            Vector3[] vector3Arr = animationParams.layerPosition;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("layer_position[");
                            sb2.append(i7);
                            String str25 = str11;
                            sb2.append(str25);
                            vector3Arr[i7] = parseVector(jSONObject2, sb2.toString(), animationParams.layerPosition[i7]);
                            animationParams.layerDirection[i7] = parseVector(jSONObject2, "layer_direction[" + i7 + str25, animationParams.layerDirection[i7]);
                            animationParams.layerDirection[i7].normalize();
                            i7++;
                            str11 = str25;
                        }
                        str3 = str11;
                        jSONArray = jSONArray2;
                        range = range2;
                        animationParams.fov = (float) jSONObject2.optDouble("fov", (double) animationParams.fov);
                        str14 = str24;
                        animationParams.depthIntensity = (float) jSONObject2.optDouble("depth_intensity", (double) animationParams.depthIntensity);
                        if (this.mBokehEnabled.booleanValue()) {
                            animationParams.bokehIntensity = (float) jSONObject2.optDouble("bokeh_intensity", (double) animationParams.bokehIntensity);
                            str6 = str10;
                            if (jSONObject2.optString(str6, str4).compareTo(str4) == 0) {
                                animationParams.autoBokehFocusDepth = Boolean.TRUE;
                            } else {
                                animationParams.bokehFocusDepth = (float) jSONObject2.optDouble(str6, (double) animationParams.bokehFocusDepth);
                            }
                            animationParams.bokehFocusRange = (float) jSONObject2.optDouble("bokeh_focus_range", (double) animationParams.bokehFocusRange);
                            c5 = 0;
                        } else {
                            str6 = str10;
                            c5 = 0;
                            animationParams.bokehIntensity = 1.0f;
                            animationParams.bokehFocusDepth = 0.5f;
                            animationParams.bokehFocusRange = 1.0f;
                        }
                        arrayList.add(animationParams);
                        i2++;
                        str10 = str6;
                        c6 = c5;
                        str13 = str5;
                        str12 = str4;
                        range2 = range;
                        jSONArray2 = jSONArray;
                        str11 = str3;
                    }
                    if (!arrayList.isEmpty()) {
                        AnimationParams[] animationParamsArr = new AnimationParams[arrayList.size()];
                        this.mAnimationParams = animationParamsArr;
                        arrayList.toArray(animationParamsArr);
                        generateTime(this.mAnimationParams);
                        return;
                    }
                    LogUtil.w(TAG, "Keyframes not specified");
                    throw new RuntimeException("Undefined keyframes");
                }
            }
            LogUtil.e(TAG, "JSON file is not valid");
        } catch (JSONException e) {
            LogUtil.e(TAG, "Invalid animation definition: " + e);
        }
    }
}
