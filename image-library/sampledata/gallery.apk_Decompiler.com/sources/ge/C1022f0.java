package ge;

import ee.C0982o;
import java.net.SocketAddress;
import java.util.List;

/* renamed from: ge.f0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1022f0 {

    /* renamed from: a  reason: collision with root package name */
    public List f4509a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f4510c;

    public SocketAddress a() {
        if (c()) {
            return (SocketAddress) ((C0982o) this.f4509a.get(this.b)).f4304a.get(this.f4510c);
        }
        throw new IllegalStateException("Index is past the end of the address group list");
    }

    public boolean b() {
        if (c()) {
            int i2 = this.f4510c + 1;
            this.f4510c = i2;
            if (i2 >= ((C0982o) this.f4509a.get(this.b)).f4304a.size()) {
                int i7 = this.b + 1;
                this.b = i7;
                this.f4510c = 0;
                if (i7 < this.f4509a.size()) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    public boolean c() {
        if (this.b < this.f4509a.size()) {
            return true;
        }
        return false;
    }

    public void d() {
        this.b = 0;
        this.f4510c = 0;
    }

    public boolean e(SocketAddress socketAddress) {
        int i2 = 0;
        while (i2 < this.f4509a.size()) {
            int indexOf = ((C0982o) this.f4509a.get(i2)).f4304a.indexOf(socketAddress);
            if (indexOf == -1) {
                i2++;
            } else {
                this.b = i2;
                this.f4510c = indexOf;
                return true;
            }
        }
        return false;
    }
}
