package s1;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: s1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0270a {

    /* renamed from: c  reason: collision with root package name */
    public static final ReentrantLock f1912c = new ReentrantLock();
    public static C0270a d;

    /* renamed from: a  reason: collision with root package name */
    public final ReentrantLock f1913a = new ReentrantLock();
    public final SharedPreferences b;

    public C0270a(Context context) {
        this.b = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public final String a(String str) {
        ReentrantLock reentrantLock = this.f1913a;
        reentrantLock.lock();
        try {
            return this.b.getString(str, (String) null);
        } finally {
            reentrantLock.unlock();
        }
    }
}
