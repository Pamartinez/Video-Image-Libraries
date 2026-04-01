package Ke;

import Ae.a;
import D1.f;
import a.C0068a;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import kotlin.jvm.internal.j;

public final class Y implements a {
    public final /* synthetic */ int d;
    public final C0778a0 e;

    public /* synthetic */ Y(C0778a0 a0Var, int i2) {
        this.d = i2;
        this.e = a0Var;
    }

    public final Object invoke() {
        Object obj;
        AccessibleObject accessibleObject;
        switch (this.d) {
            case 0:
                return new Z(this.e);
            default:
                C0778a0 a0Var = this.e;
                Member r = a0Var.r();
                try {
                    Object obj2 = n0.f3505p;
                    if (a0Var.q()) {
                        obj = C0068a.k(a0Var.m, a0Var.j());
                    } else {
                        obj = null;
                    }
                    if (obj == obj2) {
                        obj = null;
                    }
                    a0Var.q();
                    if (r != null) {
                        accessibleObject = (AccessibleObject) r;
                    } else {
                        accessibleObject = null;
                    }
                    if (accessibleObject != null) {
                        accessibleObject.setAccessible(f.C(a0Var));
                    }
                    if (r == null) {
                        return null;
                    }
                    if (r instanceof Field) {
                        return ((Field) r).get(obj);
                    }
                    if (r instanceof Method) {
                        int length = ((Method) r).getParameterTypes().length;
                        if (length == 0) {
                            return ((Method) r).invoke((Object) null, (Object[]) null);
                        }
                        if (length == 1) {
                            Method method = (Method) r;
                            if (obj == null) {
                                Class cls = ((Method) r).getParameterTypes()[0];
                                j.d(cls, "get(...)");
                                obj = E0.e(cls);
                            }
                            return method.invoke((Object) null, new Object[]{obj});
                        } else if (length == 2) {
                            Class cls2 = ((Method) r).getParameterTypes()[1];
                            j.d(cls2, "get(...)");
                            return ((Method) r).invoke((Object) null, new Object[]{obj, E0.e(cls2)});
                        } else {
                            throw new AssertionError("delegate method " + r + " should take 0, 1, or 2 parameters");
                        }
                    } else {
                        throw new AssertionError("delegate field/method " + r + " neither field nor method");
                    }
                } catch (IllegalAccessException e7) {
                    throw new Exception("Cannot obtain the delegate of a non-accessible property. Use \"isAccessible = true\" to make the property accessible", e7);
                }
        }
    }
}
