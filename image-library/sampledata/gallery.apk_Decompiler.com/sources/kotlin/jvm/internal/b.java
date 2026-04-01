package kotlin.jvm.internal;

import He.C0747c;
import He.C0750f;
import He.m;
import He.u;
import He.v;
import He.z;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b implements C0747c, Serializable {
    public static final Object NO_RECEIVER = a.d;
    private final boolean isTopLevel;
    private final String name;
    private final Class owner;
    protected final Object receiver;
    private transient C0747c reflected;
    private final String signature;

    public b(Object obj, Class cls, String str, String str2, boolean z) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z;
    }

    public Object call(Object... objArr) {
        return getReflected().call(objArr);
    }

    public Object callBy(Map map) {
        return getReflected().callBy(map);
    }

    public C0747c compute() {
        C0747c cVar = this.reflected;
        if (cVar != null) {
            return cVar;
        }
        C0747c computeReflected = computeReflected();
        this.reflected = computeReflected;
        return computeReflected;
    }

    public abstract C0747c computeReflected();

    public List<Annotation> getAnnotations() {
        return getReflected().getAnnotations();
    }

    public Object getBoundReceiver() {
        return this.receiver;
    }

    public String getName() {
        return this.name;
    }

    public C0750f getOwner() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        if (this.isTopLevel) {
            return v.f4727a.c(cls, "");
        }
        return v.f4727a.b(cls);
    }

    public List<m> getParameters() {
        return getReflected().getParameters();
    }

    public abstract C0747c getReflected();

    public u getReturnType() {
        return getReflected().getReturnType();
    }

    public String getSignature() {
        return this.signature;
    }

    public List<v> getTypeParameters() {
        return getReflected().getTypeParameters();
    }

    public z getVisibility() {
        return getReflected().getVisibility();
    }

    public boolean isAbstract() {
        return getReflected().isAbstract();
    }

    public boolean isFinal() {
        return getReflected().isFinal();
    }

    public boolean isOpen() {
        return getReflected().isOpen();
    }
}
