package com.samsung.android.gallery.widget.abstraction;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionInfo {
    public Bitmap background;
    public Bitmap bitmap;
    public Bitmap bottom;
    public int dataPosition;
    public RectF displayRect;
    public MediaItem item;
    public String name;
    public boolean original;
    public int paddingStart;
    public PointF point;
    public Matrix presetMatrix;
    public float scale;
    public Bitmap top;

    public TransitionInfo() {
        this.scale = -1.0f;
    }

    public boolean hasValidData() {
        if (this.item == null && this.bitmap == null && this.name == null) {
            return false;
        }
        return true;
    }

    public void publish(Blackboard blackboard) {
        if (blackboard != null && this.bitmap != null) {
            blackboard.publish("data://shared_original_bitmap", this);
        }
    }

    public void recycle() {
        this.item = null;
        this.bitmap = null;
        this.name = null;
        this.scale = -1.0f;
        this.point = null;
        this.original = false;
        this.presetMatrix = null;
    }

    public TransitionInfo setBackground(Bitmap bitmap2) {
        this.background = bitmap2;
        return this;
    }

    public TransitionInfo setDisplayRect(RectF rectF) {
        this.displayRect = rectF;
        return this;
    }

    public TransitionInfo setOriginal(boolean z) {
        this.original = z;
        return this;
    }

    public TransitionInfo setPresetMatrix(Matrix matrix) {
        this.presetMatrix = matrix;
        return this;
    }

    public TransitionInfo setScale(float f, PointF pointF) {
        this.scale = f;
        this.point = pointF;
        return this;
    }

    public String toString() {
        return "TransitionInfo{" + this.name + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.scale + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.point + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.original + "}, " + MediaItemUtil.getSimpleLog(this.item) + ArcCommonLog.TAG_COMMA + Logger.toSimpleString(this.bitmap) + ArcCommonLog.TAG_COMMA + this.displayRect;
    }

    public TransitionInfo(MediaItem mediaItem, Bitmap bitmap2) {
        this(mediaItem, bitmap2, (String) null);
    }

    public TransitionInfo(MediaItem mediaItem, Bitmap bitmap2, int i2) {
        this(mediaItem, bitmap2, (String) null);
        this.dataPosition = i2;
    }

    public TransitionInfo(MediaItem mediaItem, Bitmap bitmap2, String str) {
        this.scale = -1.0f;
        this.item = mediaItem;
        this.bitmap = bitmap2;
        this.name = str;
    }
}
