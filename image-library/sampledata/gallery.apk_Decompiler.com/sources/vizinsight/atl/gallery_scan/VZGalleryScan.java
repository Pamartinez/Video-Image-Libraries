package vizinsight.atl.gallery_scan;

import android.graphics.Bitmap;
import android.util.Log;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VZGalleryScan {
    public static final int IMAGEFORMAT_BGR = 1;
    public static final int IMAGEFORMAT_NV21 = 0;
    public static final int IMAGEFORMAT_RGBA = 2;
    public static final int MODE_DOC = 3;
    public static final int MODE_LIVE_FOCUS = 2;
    public static final int MODE_PET = 1;
    public static final int MODE_SALIENCY = 0;
    public static final int MODE_SCENE = 4;
    private final String TAG = "VZGalleryScan";
    private long gallery_scan_reference;
    private ByteBuffer image_byte_buffer;
    public boolean isValid = false;
    private int mode;
    public float progress = 100.0f;
    private boolean soft_parsing;

    static {
        System.loadLibrary("SmartScan.camera.samsung");
    }

    public VZGalleryScan(int i2, String str) {
        if (i2 == 3) {
            this.soft_parsing = false;
        } else {
            this.soft_parsing = false;
        }
        boolean initializeJNI = initializeJNI(i2, str);
        this.isValid = initializeJNI;
        this.mode = i2;
        if (!initializeJNI) {
            Log.e("VZ Debug", "Initialization of SmartScanSegmenter failed");
        }
    }

    private native boolean executeJNI(ByteBuffer byteBuffer, int i2, int i7, int i8, int i10, float[] fArr);

    private native boolean initializeJNI(int i2, String str);

    private native void releaseJNI();

    public boolean execute(byte[] bArr, int i2, int i7, int i8, int i10, float[] fArr) {
        if (!this.isValid) {
            return false;
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bArr.length);
        this.image_byte_buffer = allocateDirect;
        allocateDirect.put(bArr);
        boolean executeJNI = executeJNI(this.image_byte_buffer, i2, i7, i8, i10, fArr);
        this.image_byte_buffer.clear();
        return executeJNI;
    }

    public int getMode() {
        return this.mode;
    }

    public void release() {
        releaseJNI();
    }

    public boolean execute(String str, float[] fArr) {
        Bitmap bitmap = Utils.get_exif_corrected_bitmap(str);
        return execute(VZImageDecoder.decodeImage(bitmap, 2), bitmap.getWidth(), bitmap.getHeight(), 0, 2, fArr);
    }
}
