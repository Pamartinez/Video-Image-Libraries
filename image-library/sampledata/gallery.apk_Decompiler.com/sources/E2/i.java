package E2;

import android.content.Intent;
import android.os.Parcel;
import android.util.Base64;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public final String f167a;

    public i(Intent intent) {
        String str;
        if (intent != null) {
            Parcel obtain = Parcel.obtain();
            j.d(obtain, "obtain()");
            intent.writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            j.d(marshall, "value");
            str = Base64.encodeToString(marshall, 0);
            j.d(str, "encodeToString(bytes, Base64.DEFAULT)");
        } else {
            str = null;
        }
        this.f167a = str;
    }

    public void a(StringBuilder sb2, Iterator it) {
        CharSequence charSequence;
        CharSequence charSequence2;
        try {
            if (it.hasNext()) {
                Object next = it.next();
                Objects.requireNonNull(next);
                if (next instanceof CharSequence) {
                    charSequence = (CharSequence) next;
                } else {
                    charSequence = next.toString();
                }
                sb2.append(charSequence);
                while (it.hasNext()) {
                    sb2.append(this.f167a);
                    Object next2 = it.next();
                    Objects.requireNonNull(next2);
                    if (next2 instanceof CharSequence) {
                        charSequence2 = (CharSequence) next2;
                    } else {
                        charSequence2 = next2.toString();
                    }
                    sb2.append(charSequence2);
                }
            }
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public i(String str) {
        str.getClass();
        this.f167a = str;
    }
}
