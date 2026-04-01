package Tf;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import ne.C1180A;
import ne.C1187e;
import ne.C1194l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends C1187e {
    public final /* synthetic */ int d;
    public final Object e;

    public /* synthetic */ i(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public /* bridge */ boolean contains(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof String)) {
                    return false;
                }
                return super.contains((String) obj);
            default:
                return super.contains(obj);
        }
    }

    public final Object get(int i2) {
        switch (this.d) {
            case 0:
                String group = ((j) this.e).f3823a.group(i2);
                if (group == null) {
                    return "";
                }
                return group;
            default:
                return ((List) this.e).get(C1194l.D0(i2, this));
        }
    }

    public /* bridge */ int indexOf(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof String)) {
                    return -1;
                }
                return super.indexOf((String) obj);
            default:
                return super.indexOf(obj);
        }
    }

    public Iterator iterator() {
        switch (this.d) {
            case 1:
                return new C1180A(this, 0);
            default:
                return super.iterator();
        }
    }

    public /* bridge */ int lastIndexOf(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof String)) {
                    return -1;
                }
                return super.lastIndexOf((String) obj);
            default:
                return super.lastIndexOf(obj);
        }
    }

    public ListIterator listIterator() {
        switch (this.d) {
            case 1:
                return new C1180A(this, 0);
            default:
                return super.listIterator();
        }
    }

    public final int p() {
        switch (this.d) {
            case 0:
                return ((j) this.e).f3823a.groupCount() + 1;
            default:
                return ((List) this.e).size();
        }
    }

    public ListIterator listIterator(int i2) {
        switch (this.d) {
            case 1:
                return new C1180A(this, i2);
            default:
                return super.listIterator(i2);
        }
    }
}
