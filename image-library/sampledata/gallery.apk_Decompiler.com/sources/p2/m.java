package P2;

import L2.a;
import P0.b;
import S2.t;
import i.C0212a;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends o {
    public final q b() {
        return q.TYPE_HEADER_ITEM;
    }

    public final int c() {
        return 112;
    }

    public final void d(C0056f fVar, b bVar) {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int b = fVar.f594c.b();
        z zVar = fVar.f593a;
        z zVar2 = fVar.f594c;
        int b5 = zVar.b();
        int b8 = zVar2.b();
        zVar2.f();
        int i12 = (b8 + zVar2.f607i) - b5;
        String m = C0212a.m("dex\n", "035", "\u0000");
        if (bVar.d()) {
            bVar.b(8, "magic: " + new t(m).f());
            bVar.b(4, "checksum");
            bVar.b(20, "signature");
            int i13 = fVar.f598o;
            if (i13 >= 0) {
                bVar.b(4, "file_size:       ".concat(a.E(i13)));
                bVar.b(4, "header_size:     ".concat(a.E(112)));
                bVar.b(4, "endian_tag:      ".concat(a.E(305419896)));
                bVar.b(4, "link_size:       0");
                bVar.b(4, "link_off:        0");
                bVar.b(4, "map_off:         ".concat(a.E(b)));
            } else {
                throw new RuntimeException("file size not yet known");
            }
        }
        int i14 = 0;
        for (int i15 = 0; i15 < 8; i15++) {
            bVar.k(m.charAt(i15));
        }
        bVar.p(24);
        int i16 = fVar.f598o;
        if (i16 >= 0) {
            bVar.l(i16);
            bVar.l(112);
            bVar.l(305419896);
            bVar.p(8);
            bVar.l(b);
            C c5 = fVar.e;
            c5.f();
            int size = ((TreeMap) c5.g).size();
            if (size == 0) {
                i2 = 0;
            } else {
                i2 = c5.b();
            }
            if (bVar.d()) {
                bVar.b(4, "string_ids_size: ".concat(a.E(size)));
                bVar.b(4, "string_ids_off:  ".concat(a.E(i2)));
            }
            bVar.l(size);
            bVar.l(i2);
            C c6 = fVar.f;
            c6.f();
            int size2 = ((TreeMap) c6.g).size();
            if (size2 == 0) {
                i7 = 0;
            } else {
                i7 = c6.b();
            }
            if (size2 <= 65536) {
                if (bVar.d()) {
                    bVar.b(4, "type_ids_size:   ".concat(a.E(size2)));
                    bVar.b(4, "type_ids_off:    ".concat(a.E(i7)));
                }
                bVar.l(size2);
                bVar.l(i7);
                C c8 = fVar.g;
                c8.f();
                int size3 = ((TreeMap) c8.g).size();
                if (size3 == 0) {
                    i8 = 0;
                } else {
                    i8 = c8.b();
                }
                if (size3 <= 65536) {
                    if (bVar.d()) {
                        bVar.b(4, "proto_ids_size:  ".concat(a.E(size3)));
                        bVar.b(4, "proto_ids_off:   ".concat(a.E(i8)));
                    }
                    bVar.l(size3);
                    bVar.l(i8);
                    l lVar = fVar.f595h;
                    lVar.f();
                    int size4 = lVar.f.size();
                    if (size4 == 0) {
                        i10 = 0;
                    } else {
                        i10 = lVar.b();
                    }
                    if (bVar.d()) {
                        bVar.b(4, "field_ids_size:  ".concat(a.E(size4)));
                        bVar.b(4, "field_ids_off:   ".concat(a.E(i10)));
                    }
                    bVar.l(size4);
                    bVar.l(i10);
                    v vVar = fVar.f596i;
                    vVar.f();
                    int size5 = vVar.f.size();
                    if (size5 == 0) {
                        i11 = 0;
                    } else {
                        i11 = vVar.b();
                    }
                    if (bVar.d()) {
                        bVar.b(4, "method_ids_size: ".concat(a.E(size5)));
                        bVar.b(4, "method_ids_off:  ".concat(a.E(i11)));
                    }
                    bVar.l(size5);
                    bVar.l(i11);
                    C0054d dVar = fVar.f597j;
                    dVar.f();
                    int size6 = dVar.f.size();
                    if (size6 != 0) {
                        i14 = dVar.b();
                    }
                    if (bVar.d()) {
                        bVar.b(4, "class_defs_size: ".concat(a.E(size6)));
                        bVar.b(4, "class_defs_off:  ".concat(a.E(i14)));
                    }
                    bVar.l(size6);
                    bVar.l(i14);
                    if (bVar.d()) {
                        bVar.b(4, "data_size:       ".concat(a.E(i12)));
                        bVar.b(4, "data_off:        ".concat(a.E(b5)));
                    }
                    bVar.l(i12);
                    bVar.l(b5);
                    return;
                }
                throw new UnsupportedOperationException("too many proto ids");
            }
            throw new UnsupportedOperationException("too many type ids");
        }
        throw new RuntimeException("file size not yet known");
    }

    public final void a(C0056f fVar) {
    }
}
