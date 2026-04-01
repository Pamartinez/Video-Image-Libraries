package x0;

import com.airbnb.lottie.LottieAnimationView;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class J {
    private final LottieAnimationView animationView;
    private boolean cacheText = true;
    private final w drawable;
    private final Map<String, String> stringMap = new HashMap();

    public J(w wVar) {
        this.drawable = wVar;
        this.animationView = null;
    }

    public abstract String getText(String str, String str2);

    public final String getTextInternal(String str, String str2) {
        if (this.cacheText && this.stringMap.containsKey(str2)) {
            return this.stringMap.get(str2);
        }
        String text = getText(str, str2);
        if (this.cacheText) {
            this.stringMap.put(str2, text);
        }
        return text;
    }

    public void setCacheText(boolean z) {
        this.cacheText = z;
    }
}
