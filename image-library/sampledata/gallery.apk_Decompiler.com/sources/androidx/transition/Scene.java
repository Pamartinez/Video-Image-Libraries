package androidx.transition;

import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Scene {
    public static Scene getCurrentScene(ViewGroup viewGroup) {
        if (viewGroup.getTag(R$id.transition_current_scene) == null) {
            return null;
        }
        throw new ClassCastException();
    }

    public static void setCurrentScene(ViewGroup viewGroup, Scene scene) {
        viewGroup.setTag(R$id.transition_current_scene, scene);
    }
}
