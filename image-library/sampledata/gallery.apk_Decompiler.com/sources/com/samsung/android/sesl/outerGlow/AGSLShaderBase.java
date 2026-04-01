package com.samsung.android.sesl.outerGlow;

import android.os.Handler;
import android.view.View;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b&\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H$¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0011\u0010\u0010J\r\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0012H$¢\u0006\u0004\b\u0015\u0010\u0014J\r\u0010\u000f\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u000bJ\r\u0010\u0011\u001a\u00020\u0007¢\u0006\u0004\b\u0011\u0010\u000bR\"\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\tR\"\u0010\u001b\u001a\u00020\u001a8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\u00020\u001a8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u0016\u0010$\u001a\u00020\u001a8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b$\u0010\u001cR\"\u0010%\u001a\u00020\u001a8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010\u001e\"\u0004\b'\u0010 R\"\u0010(\u001a\u00020\u00128\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010\u0014\"\u0004\b+\u0010,R\"\u0010-\u001a\u00020\u00128\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b-\u0010)\u001a\u0004\b.\u0010\u0014\"\u0004\b/\u0010,R\"\u00100\u001a\u00020\u00128\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b0\u0010)\u001a\u0004\b1\u0010\u0014\"\u0004\b2\u0010,R(\u00105\u001a\b\u0012\u0004\u0012\u000204038\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010<\u001a\u00020;8\u0004X\u0004¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R$\u0010A\u001a\u0004\u0018\u00010@8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\"\u0010G\u001a\u00020\u00128\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bG\u0010)\u001a\u0004\bG\u0010\u0014\"\u0004\bH\u0010,R\"\u0010I\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bI\u0010\u0016\u001a\u0004\bJ\u0010\u0018\"\u0004\bK\u0010\t¨\u0006L"}, d2 = {"Lcom/samsung/android/sesl/outerGlow/AGSLShaderBase;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroid/view/View;", "getView", "()Landroid/view/View;", "", "iTime", "Lme/x;", "updateITime", "(F)V", "startUpdateRunnable", "()V", "stopUpdateRunnable", "Landroidx/lifecycle/LifecycleOwner;", "owner", "onResume", "(Landroidx/lifecycle/LifecycleOwner;)V", "onPause", "", "requestInvalidate", "()Z", "canDrawCheck", "F", "getITime", "()F", "setITime", "", "initFrameTimeNanos", "J", "getInitFrameTimeNanos", "()J", "setInitFrameTimeNanos", "(J)V", "lastFrameTimeNanos", "getLastFrameTimeNanos", "setLastFrameTimeNanos", "frameRate", "frameIntervalNanos", "getFrameIntervalNanos", "setFrameIntervalNanos", "stopAnimation", "Z", "getStopAnimation", "setStopAnimation", "(Z)V", "needInitDraw", "getNeedInitDraw", "setNeedInitDraw", "readyDraw", "getReadyDraw", "setReadyDraw", "", "", "shaderList", "Ljava/util/List;", "getShaderList", "()Ljava/util/List;", "setShaderList", "(Ljava/util/List;)V", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "Ljava/lang/Runnable;", "updateRunnable", "Ljava/lang/Runnable;", "getUpdateRunnable", "()Ljava/lang/Runnable;", "setUpdateRunnable", "(Ljava/lang/Runnable;)V", "isUpdateRunnableWorking", "setUpdateRunnableWorking", "lastStopTime", "getLastStopTime", "setLastStopTime", "graphic-solution_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AGSLShaderBase implements DefaultLifecycleObserver {
    private long frameIntervalNanos;
    private long frameRate;
    private final Handler handler;
    private float iTime;
    private long initFrameTimeNanos;
    private boolean isUpdateRunnableWorking;
    private long lastFrameTimeNanos;
    private float lastStopTime;
    private boolean needInitDraw;
    private boolean readyDraw;
    private List<Object> shaderList;
    private boolean stopAnimation;
    private Runnable updateRunnable;

    public abstract boolean canDrawCheck();

    public final List<Object> getShaderList() {
        return this.shaderList;
    }

    public abstract View getView();

    public final void onPause() {
    }

    public void onResume(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
        super.onResume(lifecycleOwner);
        startUpdateRunnable();
        onResume();
    }

    public final boolean requestInvalidate() {
        if (this.stopAnimation || !canDrawCheck()) {
            return false;
        }
        if (this.frameRate == 0 && !this.needInitDraw) {
            return false;
        }
        long nanoTime = System.nanoTime();
        long j2 = nanoTime - this.lastFrameTimeNanos;
        if (this.needInitDraw || j2 >= this.frameIntervalNanos) {
            if (canDrawCheck()) {
                this.needInitDraw = false;
            }
            updateITime(((float) (nanoTime - this.initFrameTimeNanos)) / 1.0E9f);
            this.lastFrameTimeNanos = nanoTime;
            this.readyDraw = true;
            getView().invalidate();
        }
        return true;
    }

    public final void startUpdateRunnable() {
        this.initFrameTimeNanos = System.nanoTime() - ((long) (this.lastStopTime * 1.0E9f));
        Runnable runnable = this.updateRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.updateRunnable;
        if (runnable2 != null) {
            this.isUpdateRunnableWorking = true;
            this.handler.post(runnable2);
        }
    }

    public final void stopUpdateRunnable() {
        this.lastStopTime = this.iTime;
        this.lastFrameTimeNanos = System.nanoTime();
        this.isUpdateRunnableWorking = false;
        Runnable runnable = this.updateRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
    }

    public final void updateITime(float f) {
        this.iTime = f;
    }

    public void onPause(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
        super.onPause(lifecycleOwner);
        stopUpdateRunnable();
        onPause();
    }

    public final void onResume() {
        this.stopAnimation = false;
        this.needInitDraw = true;
        requestInvalidate();
    }
}
