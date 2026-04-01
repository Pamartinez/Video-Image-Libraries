package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.types.ImgpType;
import java.nio.ByteBuffer;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimgpPlugin implements Plugin<ImgpPlugin> {
    private static final String TAG = Def.tagOf((Class<?>) SimgpPlugin.class);

    static {
        String property = System.getProperty(Def.JUNIT_TEST_EXECUTION_MODE);
        if (property == null || !Boolean.parseBoolean(property)) {
            System.loadLibrary("sum_core_jni.media.samsung");
        }
    }

    /* access modifiers changed from: private */
    public MutableMediaBuffer cvtColor(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        String str = TAG;
        SLog.d(str, "try to simgp cvtColor: " + mediaBuffer + " => " + mutableMediaBuffer.getFormat());
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dup();
            mutableMediaFormat.setColorFormat(mutableMediaBuffer.getFormat().getColorFormat());
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cols", mutableMediaBuffer.getFormat().getCols());
            jSONObject.put("rows", mutableMediaBuffer.getFormat().getRows());
            jSONObject.put("src-color-format", mediaBuffer.getFormat().getColorFormat().stringfy());
            jSONObject.put("dst-color-format", mutableMediaBuffer.getFormat().getColorFormat().stringfy());
            Class cls = ByteBuffer.class;
            nativeCvtColor((ByteBuffer) mediaBuffer.getTypedData(cls), (ByteBuffer) mutableMediaBuffer.getTypedData(cls), jSONObject.toString());
            return mutableMediaBuffer;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("");
        }
    }

    private static native int nativeCvtColor(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, String str);

    private static native int nativeResize(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, String str);

    private static native int nativeRotate(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, String str);

    /* access modifiers changed from: private */
    public MutableMediaBuffer resize(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        String str = TAG;
        SLog.d(str, "try to simgp resize: " + mediaBuffer);
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dup();
            if (mutableMediaBuffer.getFormat().contains("scale")) {
                float floatValue = ((Float) mutableMediaBuffer.getFormat().get("scale")).floatValue();
                mutableMediaFormat.setCols((int) (((float) mediaBuffer.getCols()) * floatValue));
                mutableMediaFormat.setRows((int) (((float) mediaBuffer.getRows()) * floatValue));
            } else {
                mutableMediaFormat.setShape(mutableMediaBuffer.getFormat().getShape());
            }
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("src-cols", mediaBuffer.getCols());
            jSONObject.put("src-rows", mediaBuffer.getRows());
            jSONObject.put("dst-cols", mutableMediaBuffer.getCols());
            jSONObject.put("dst-rows", mutableMediaBuffer.getRows());
            jSONObject.put("color-format", mediaBuffer.getFormat().getColorFormat().stringfy());
            Class cls = ByteBuffer.class;
            nativeResize((ByteBuffer) mediaBuffer.getTypedData(cls), (ByteBuffer) mutableMediaBuffer.getTypedData(cls), jSONObject.toString());
            return mutableMediaBuffer;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("");
        }
    }

    /* access modifiers changed from: private */
    public MutableMediaBuffer rotate(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        String str = TAG;
        SLog.d(str, "try to simgp rotate: " + mediaBuffer);
        throw new UnsupportedOperationException("not yet implemented");
    }

    public void bindToFixture(ImgpPlugin imgpPlugin) {
        imgpPlugin.setDescriptorLoader(new f(1));
        imgpPlugin.setImgProcessor(ImgpType.RESIZE, new l(this, 0));
        imgpPlugin.setImgProcessor(ImgpType.ROTATE, new l(this, 1));
        imgpPlugin.setImgProcessor(ImgpType.CVT_COLOR, new l(this, 2));
    }
}
