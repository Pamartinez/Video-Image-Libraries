package Jd;

import Sd.o;
import U2.e;
import Xd.a;
import a.C0068a;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import com.samsung.scsp.framework.core.ScspException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.io.Writer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements a {
    public int d;
    public final Object e;
    public final Object f;
    public Comparable g;

    /* renamed from: h  reason: collision with root package name */
    public final Object f3475h;

    /* renamed from: i  reason: collision with root package name */
    public final Object f3476i;

    public d(Context context, int i2, ContentValues contentValues) {
        this.e = Uri.parse("content://com.sec.android.log.diagmonagent.sa/common");
        this.f = Uri.parse("content://com.sec.android.log.diagmonagent.sa/log");
        this.g = null;
        this.f3475h = context;
        this.d = i2;
        this.f3476i = contentValues;
    }

    public static void a(StringBuffer stringBuffer, Writer writer) {
        int length = stringBuffer.length();
        if (length != 0 && stringBuffer.charAt(length - 1) != 10) {
            writer.write(10);
        }
    }

    public void b() {
        try {
            Bundle bundle = (Bundle) this.f;
            bundle.putParcelable("DECRYPT_INPUT_PFD", (ParcelFileDescriptor) this.f3475h);
            bundle.putParcelable("DECRYPT_OUTPUT_PFD", (ParcelFileDescriptor) this.f3476i);
        } catch (Throwable th) {
            Log.i((String) this.e, "request build error: " + th.getMessage());
            this.d = ScspException.Code.BAD_IMPLEMENTATION;
            this.g = th.getMessage();
        }
    }

    public void c() {
        int indexOf;
        StringBuffer stringBuffer = (StringBuffer) this.g;
        StringBuffer stringBuffer2 = (StringBuffer) this.f;
        Writer writer = (Writer) this.e;
        while (true) {
            int indexOf2 = stringBuffer2.indexOf("\n");
            if (indexOf2 >= 0 && (indexOf = stringBuffer.indexOf("\n")) >= 0) {
                if (indexOf2 != 0) {
                    writer.write(stringBuffer2.substring(0, indexOf2));
                }
                if (indexOf != 0) {
                    for (int i2 = this.d - indexOf2; i2 > 0; i2--) {
                        writer.write(32);
                    }
                    writer.write(stringBuffer.substring(0, indexOf));
                }
                writer.write(10);
                stringBuffer2.delete(0, indexOf2 + 1);
                stringBuffer.delete(0, indexOf + 1);
            } else {
                return;
            }
        }
    }

    public int onFinish() {
        try {
            Uri uri = (Uri) this.g;
            if (uri != null) {
                int parseInt = Integer.parseInt(uri.getLastPathSegment());
                C0068a.b("SendLog Result = " + parseInt);
                boolean z = true;
                if (this.d == 1) {
                    if (parseInt != 0) {
                        z = false;
                    }
                    c.E((Context) this.f3475h).edit().putBoolean("sendCommonSuccess", z).apply();
                    C0068a.b("Save Result = " + z);
                    return 0;
                }
            }
            return 0;
        } catch (Exception e7) {
            C0068a.K("failed to get send result" + e7.getMessage());
            return 0;
        }
    }

    public void run() {
        ContentValues contentValues = (ContentValues) this.f3476i;
        Context context = (Context) this.f3475h;
        try {
            int i2 = this.d;
            if (i2 == 1) {
                this.g = context.getContentResolver().insert((Uri) this.e, contentValues);
            } else if (i2 == 2) {
                this.g = context.getContentResolver().insert((Uri) this.f, contentValues);
            }
        } catch (Exception e7) {
            C0068a.K("failed to send log" + e7.getMessage());
        }
    }

    public d(File file, FileOutputStream fileOutputStream) {
        this.e = "[SCG-SDK][0.0.0019][SCGalleryRequest][requestDecryptFile]";
        this.f = new Bundle();
        this.d = 20000000;
        this.g = "";
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
            this.f3475h = open;
            ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileOutputStream.getFD());
            this.f3476i = dup;
            if (open == null || dup == null) {
                throw new Throwable("Invalid files: source or target pfd is null");
            }
        } catch (Throwable th) {
            o.j((ParcelFileDescriptor) this.f3475h);
            o.j((ParcelFileDescriptor) this.f3476i);
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r4v5, types: [java.lang.StringBuffer, java.lang.Comparable] */
    public d(StringWriter stringWriter, int i2, int i7, String str) {
        if (stringWriter == null) {
            throw new NullPointerException("out == null");
        } else if (i2 < 1) {
            throw new IllegalArgumentException("leftWidth < 1");
        } else if (i7 >= 1) {
            StringWriter stringWriter2 = new StringWriter(1000);
            StringWriter stringWriter3 = new StringWriter(1000);
            this.e = stringWriter;
            this.d = i2;
            this.f = stringWriter2.getBuffer();
            this.g = stringWriter3.getBuffer();
            this.f3475h = new e(stringWriter2, i2, "");
            this.f3476i = new e(stringWriter3, i7, str);
        } else {
            throw new IllegalArgumentException("rightWidth < 1");
        }
    }
}
