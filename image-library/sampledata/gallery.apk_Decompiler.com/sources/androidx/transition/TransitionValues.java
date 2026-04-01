package androidx.transition;

import android.view.View;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionValues {
    final ArrayList<Transition> mTargetedTransitions = new ArrayList<>();
    public final Map<String, Object> values = new HashMap();
    public View view;

    public TransitionValues(View view2) {
        this.view = view2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TransitionValues)) {
            return false;
        }
        TransitionValues transitionValues = (TransitionValues) obj;
        if (this.view != transitionValues.view || !this.values.equals(transitionValues.values)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.values.hashCode() + (this.view.hashCode() * 31);
    }

    public String toString() {
        StringBuilder t = C0212a.t("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n", "    view = ");
        t.append(this.view);
        t.append("\n");
        String A10 = C0212a.A(t.toString(), "    values:");
        for (String next : this.values.keySet()) {
            A10 = A10 + "    " + next + ": " + this.values.get(next) + "\n";
        }
        return A10;
    }
}
