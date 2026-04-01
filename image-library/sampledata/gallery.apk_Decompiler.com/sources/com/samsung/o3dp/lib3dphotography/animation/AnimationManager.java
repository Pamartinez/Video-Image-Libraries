package com.samsung.o3dp.lib3dphotography.animation;

import android.content.Context;
import android.os.Looper;
import com.samsung.o3dp.lib3dphotography.animation.animations.DynamicGyroAnimation;
import com.samsung.o3dp.lib3dphotography.animation.animations.GyroAnimation;
import com.samsung.o3dp.lib3dphotography.animation.animations.PanoramaAnimation;
import com.samsung.o3dp.lib3dphotography.animation.animations.TouchGestureAnimation;
import com.samsung.o3dp.lib3dphotography.graphics.ImageRenderer;
import com.samsung.o3dp.lib3dphotography.graphics.Texture;
import com.samsung.o3dp.lib3dphotography.interaction.InputEvent;
import com.samsung.o3dp.lib3dphotography.utils.FileUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnimationManager {
    private static final String TAG = "AnimationManager";
    private TreeMap<String, Animation> mAnimations = null;
    private final Animator mAnimator = new Animator();

    public void addAnimation(Animation animation) {
        this.mAnimations.put(animation.getName(), animation);
    }

    public TreeMap<String, Animation> getAnimations() {
        return this.mAnimations;
    }

    public Animator getAnimator() {
        return this.mAnimator;
    }

    public void loadAnimations(Context context) {
        if (this.mAnimations == null) {
            this.mAnimations = new TreeMap<>();
            try {
                String[] list = context.getAssets().list("animation");
                if (list != null) {
                    for (String str : list) {
                        if (str.endsWith(".json")) {
                            String p6 = C0212a.p(new StringBuilder("animation"), File.separator, str);
                            LogUtil.d(TAG, "Parsing animation: ".concat(str));
                            Animation animation = new Animation(FileUtil.getStringFromAsset(context, p6));
                            this.mAnimations.put(animation.getName(), animation);
                        }
                    }
                }
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    addAnimation(new PanoramaAnimation(context));
                    addAnimation(new GyroAnimation());
                    addAnimation(new DynamicGyroAnimation());
                    addAnimation(new TouchGestureAnimation(context));
                }
            } catch (IOException e) {
                LogUtil.e(TAG, "Unable to process animation file list " + e);
                throw new RuntimeException(e);
            }
        }
    }

    public boolean onInputEvent(InputEvent inputEvent) {
        Animation animation = this.mAnimator.getAnimation();
        if (inputEvent instanceof InputEvent.Touch) {
            return animation.onTouchEvent((InputEvent.Touch) inputEvent);
        }
        if (inputEvent instanceof InputEvent.MotionSensor) {
            return animation.onMotionSensorEvent((InputEvent.MotionSensor) inputEvent);
        }
        return false;
    }

    public void resetAnimationParams(ImageRenderer imageRenderer, Texture texture, Texture texture2, Texture texture3) {
        this.mAnimator.resetAnimationTime();
        if (imageRenderer != null) {
            this.mAnimator.setForegroundLayer(Math.min(1, imageRenderer.getNumLayers() - 1));
        } else {
            this.mAnimator.setForegroundLayer(1);
        }
        if (texture2 != null) {
            this.mAnimator.computeAnimation(texture.width(), texture.height(), texture2.width(), texture2.height());
        } else {
            this.mAnimator.computeAnimation(texture.width(), texture.height(), texture3.width(), texture3.height());
        }
    }
}
