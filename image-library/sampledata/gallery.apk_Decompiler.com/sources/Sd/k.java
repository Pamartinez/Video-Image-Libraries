package Sd;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public final Context f3706a;
    public final m b;

    /* renamed from: c  reason: collision with root package name */
    public final FileOutputStream f3707c;
    public final long d = System.currentTimeMillis();
    public final int e;

    public k(Context context, m mVar, FileOutputStream fileOutputStream) {
        this.f3706a = context;
        this.b = mVar;
        this.f3707c = fileOutputStream;
        if (mVar.e) {
            this.e = j.b.incrementAndGet();
        } else {
            this.e = 0;
        }
    }

    public final void a() {
        try {
            File file = new File(b());
            if (file.exists()) {
                file.delete();
            }
        } catch (Throwable unused) {
        }
    }

    public final String b() {
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.f3706a.getFilesDir());
        String str = File.separator;
        sb3.append(str);
        sb3.append("cloud_file_encrypted");
        sb2.append(sb3.toString());
        sb2.append(str);
        sb2.append(this.e + "_" + this.d);
        return sb2.toString();
    }
}
