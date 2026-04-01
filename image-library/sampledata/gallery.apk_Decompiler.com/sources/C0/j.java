package C0;

import D1.f;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import androidx.core.util.Pair;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f101a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f102c;

    public /* synthetic */ j(int i2) {
        this.f101a = i2;
    }

    public static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            jSONObject.put("TOTAL", (statFs.getBlockCountLong() * statFs.getBlockSizeLong()) >> 20);
            StatFs statFs2 = new StatFs(Environment.getDataDirectory().getPath());
            jSONObject.put("FREE", (statFs2.getAvailableBlocksLong() * statFs2.getBlockSizeLong()) >> 20);
            return jSONObject;
        } catch (JSONException e) {
            f.o(e.getMessage());
            return jSONObject;
        }
    }

    public static JSONObject b() {
        long nativeHeapFreeSize = Debug.getNativeHeapFreeSize() >> 20;
        long nativeHeapSize = Debug.getNativeHeapSize() >> 20;
        long nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize() >> 20;
        StringBuilder j2 = N2.j.j(nativeHeapSize, "[NativeHeap] nativeHeapSize : ", " nativeHeapFree : ");
        j2.append(nativeHeapFreeSize);
        j2.append(" nativeHeapAllocatedSize : ");
        j2.append(nativeHeapAllocatedSize);
        f.n(j2.toString());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("HEAP_SIZE", nativeHeapSize);
            jSONObject.put("HEAP_FREE", nativeHeapFreeSize);
            jSONObject.put("HEAD_ALLOC", nativeHeapAllocatedSize);
            return jSONObject;
        } catch (JSONException e) {
            f.o(e.getMessage());
            return jSONObject;
        }
    }

    public static JSONObject c() {
        Runtime runtime = Runtime.getRuntime();
        long j2 = runtime.totalMemory() >> 20;
        long freeMemory = runtime.freeMemory() >> 20;
        long maxMemory = runtime.maxMemory() >> 20;
        StringBuilder j3 = N2.j.j(j2, "[VM] TotalMemory : ", " FreeMemory : ");
        j3.append(freeMemory);
        j3.append(" maxMemory : ");
        j3.append(maxMemory);
        f.n(j3.toString());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("TOTAL", j2);
            jSONObject.put("FREE", freeMemory);
            jSONObject.put("MAX", maxMemory);
            return jSONObject;
        } catch (JSONException e) {
            f.o(e.getMessage());
            return jSONObject;
        }
    }

    public boolean equals(Object obj) {
        switch (this.f101a) {
            case 0:
                if (!(obj instanceof Pair)) {
                    return false;
                }
                Pair pair = (Pair) obj;
                F f = pair.first;
                F f5 = this.b;
                if (f != f5 && (f == null || !f.equals(f5))) {
                    return false;
                }
                S s = pair.second;
                S s5 = this.f102c;
                if (s == s5 || (s != null && s.equals(s5))) {
                    return true;
                }
                return false;
            default:
                return super.equals(obj);
        }
    }

    public int hashCode() {
        int i2;
        switch (this.f101a) {
            case 0:
                String str = this.b;
                int i7 = 0;
                if (str == null) {
                    i2 = 0;
                } else {
                    i2 = str.hashCode();
                }
                String str2 = this.f102c;
                if (str2 != null) {
                    i7 = str2.hashCode();
                }
                return i2 ^ i7;
            default:
                return super.hashCode();
        }
    }

    public String toString() {
        switch (this.f101a) {
            case 0:
                return "Pair{" + this.b + " " + this.f102c + "}";
            default:
                return super.toString();
        }
    }
}
