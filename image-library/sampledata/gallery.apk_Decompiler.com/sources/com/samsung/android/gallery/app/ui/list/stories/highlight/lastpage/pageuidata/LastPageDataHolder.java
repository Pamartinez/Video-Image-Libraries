package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata;

import com.samsung.android.gallery.support.utils.Log;
import i.C0212a;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import r6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LastPageDataHolder {
    AtomicReference<Float> mCountDownProgress = new AtomicReference<>(Float.valueOf(0.0f));
    private int mFocusedPosition;
    private ImeState mImeState;
    ArrayList<UpdateListener> mUpdateListeners = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ImeState {
        public int cursorPosition;
        public CharSequence editedTitle = "";
        public boolean hasFocus;
        public boolean imeShown;

        public String toString() {
            return "ImeState t=" + this.editedTitle + " ,p=" + this.cursorPosition + " ,is=" + this.imeShown + " ,f" + this.hasFocus;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface UpdateListener {
        void focusPositionChanged();

        void resetCountDown();
    }

    public void addUpdateListener(UpdateListener updateListener) {
        if (!this.mUpdateListeners.contains(updateListener)) {
            this.mUpdateListeners.add(updateListener);
        }
    }

    public void destroy() {
        reset();
        this.mUpdateListeners.clear();
    }

    public float getCountDownProgress() {
        Float f = this.mCountDownProgress.get();
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    public int getFocusPosition() {
        return this.mFocusedPosition;
    }

    public ImeState getImeState() {
        return this.mImeState;
    }

    public void removeUpdateListener(UpdateListener updateListener) {
        this.mUpdateListeners.remove(updateListener);
    }

    public void reset() {
        this.mFocusedPosition = 0;
        this.mCountDownProgress.set(Float.valueOf(0.0f));
        this.mImeState = null;
    }

    public void resetCountDown() {
        this.mCountDownProgress.set(Float.valueOf(0.0f));
        this.mUpdateListeners.forEach(new e(7));
    }

    public void saveFocusPosition(int i2, boolean z, String str) {
        String l = C0212a.l("setFocusPosition from ", str);
        Log.d("LastPageDataHolder", l, this.mFocusedPosition + "->" + i2, Boolean.valueOf(z));
        this.mFocusedPosition = i2;
        if (z) {
            this.mUpdateListeners.forEach(new e(8));
        }
    }

    public void setCountDownProgress(float f) {
        this.mCountDownProgress.set(Float.valueOf(f));
    }

    public void setImeState(ImeState imeState) {
        this.mImeState = imeState;
    }
}
