package N2;

import R2.e;
import R2.h;
import R2.i;
import R2.l;
import R2.q;
import R2.r;
import S2.j;
import U2.c;
import android.os.Bundle;
import com.samsung.scsp.framework.core.api.Response;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class t implements e {
    public int d;
    public int e;
    public Object f;

    public t(int i2, int i7, String str) {
        this.d = i2;
        this.e = i7;
        this.f = str;
    }

    public static t b(Bundle bundle) {
        String str;
        int i2 = 2;
        int i7 = 90000000;
        if (bundle != null) {
            i2 = bundle.getInt("result", 2);
            i7 = bundle.getInt("rcode", 90000000);
            str = bundle.getString(Response.RMSG, (String) null);
        } else {
            str = "The returned value from SCPM is not correct(null or empty).";
        }
        return new t(i2, i7, str);
    }

    public void a(g gVar) {
        ((ArrayList) this.f).add(gVar);
        gVar.f427c.getClass();
        if (gVar instanceof r) {
            throw null;
        }
    }

    public void c(h hVar) {
        boolean z;
        if (hVar.d.f652a == 3) {
            int i2 = ((j) hVar.f647h).d;
            boolean[] zArr = (boolean[]) this.f;
            if (!zArr[0] || (this.d - this.e) + i2 != hVar.f.d) {
                z = false;
            } else {
                z = true;
            }
            zArr[0] = z;
        }
    }

    public i e(g gVar) {
        l lVar = gVar.d;
        i iVar = gVar.b;
        i f5 = f(gVar.i(lVar.j(iVar.e, (BitSet) null)), iVar);
        if (f5 != null) {
            return f5;
        }
        throw new c("No expanded opcode for " + gVar, (Exception) null);
    }

    public i f(g gVar, i iVar) {
        loop0:
        while (iVar != null && !iVar.d.D(gVar)) {
            i[] iVarArr = k.f492a;
            while (true) {
                int i2 = iVar.f429c;
                if (i2 == -1) {
                    iVar = null;
                    break;
                }
                try {
                    iVar = k.f492a[i2 + 1];
                    if (iVar != null) {
                        if (i2 < 255) {
                            break;
                        }
                    } else {
                        break loop0;
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            }
        }
        return iVar;
        throw new IllegalArgumentException("bogus opcode");
    }

    public byte g() {
        ByteBuffer byteBuffer = (ByteBuffer) this.f;
        int i2 = this.d;
        if (i2 < 0 || i2 + 1 > this.e) {
            throw new IndexOutOfBoundsException("Index out of buffer bounds");
        }
        byte[] bArr = new byte[1];
        byteBuffer.position(i2);
        byteBuffer.get(bArr);
        this.d++;
        return bArr[0];
    }

    public byte[] h(int i2) {
        ByteBuffer byteBuffer = (ByteBuffer) this.f;
        if (i2 >= 0) {
            int i7 = this.d;
            if (i7 + i2 <= this.e) {
                byte[] bArr = new byte[i2];
                byteBuffer.position(i7);
                byteBuffer.get(bArr);
                this.d += i2;
                return bArr;
            }
        }
        throw new IndexOutOfBoundsException("Index out of buffer bounds");
    }

    public void d(q qVar) {
    }

    public void i(r rVar) {
    }

    public void l(i iVar) {
    }
}
