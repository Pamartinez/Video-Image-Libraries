package og;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements m {
    public final b d;
    public final a e;
    public j f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f5014h;

    /* renamed from: i  reason: collision with root package name */
    public long f5015i;

    public g(b bVar) {
        int i2;
        this.d = bVar;
        a d2 = bVar.d();
        this.e = d2;
        j jVar = d2.d;
        this.f = jVar;
        if (jVar != null) {
            i2 = jVar.b;
        } else {
            i2 = -1;
        }
        this.g = i2;
    }

    public final void close() {
        this.f5014h = true;
    }

    public final long e(a aVar, long j2) {
        j jVar;
        j jVar2;
        if (!this.f5014h) {
            j jVar3 = this.f;
            a aVar2 = this.e;
            if (jVar3 == null || (jVar3 == (jVar2 = aVar2.d) && this.g == jVar2.b)) {
                if (!this.d.k(this.f5015i + 1)) {
                    return -1;
                }
                if (this.f == null && (jVar = aVar2.d) != null) {
                    this.f = jVar;
                    this.g = jVar.b;
                }
                long min = Math.min(8192, aVar2.e - this.f5015i);
                long j3 = this.f5015i;
                n.a(aVar2.e, j3, min);
                if (min != 0) {
                    aVar.e += min;
                    j jVar4 = aVar2.d;
                    while (true) {
                        long j8 = (long) (jVar4.f5017c - jVar4.b);
                        if (j3 < j8) {
                            break;
                        }
                        j3 -= j8;
                        jVar4 = jVar4.f;
                    }
                    long j10 = min;
                    while (j10 > 0) {
                        j c5 = jVar4.c();
                        int i2 = (int) (((long) c5.b) + j3);
                        c5.b = i2;
                        c5.f5017c = Math.min(i2 + ((int) j10), c5.f5017c);
                        j jVar5 = aVar.d;
                        if (jVar5 == null) {
                            c5.g = c5;
                            c5.f = c5;
                            aVar.d = c5;
                        } else {
                            jVar5.g.b(c5);
                        }
                        j10 -= (long) (c5.f5017c - c5.b);
                        jVar4 = jVar4.f;
                        j3 = 0;
                    }
                }
                this.f5015i += min;
                return min;
            }
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        throw new IllegalStateException("closed");
    }
}
