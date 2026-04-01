package ie;

import A0.b;
import G0.a;
import java.net.HttpURLConnection;
import java.net.URL;
import rf.C1268s;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class c implements b, C1268s {
    public static a f(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        return new a(httpURLConnection);
    }

    public boolean a(float f) {
        throw new IllegalStateException("not implemented");
    }

    public K0.a b() {
        throw new IllegalStateException("not implemented");
    }

    public boolean c(float f) {
        return false;
    }

    public float d() {
        return 0.0f;
    }

    public float e() {
        return 1.0f;
    }

    public boolean isEmpty() {
        return true;
    }
}
