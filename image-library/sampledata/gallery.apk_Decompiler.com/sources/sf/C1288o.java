package sf;

import He.j;
import He.t;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;

/* renamed from: sf.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1288o implements C1286m {
    public static final /* synthetic */ t[] Y;

    /* renamed from: A  reason: collision with root package name */
    public final C1287n f5085A;
    public final C1287n B;

    /* renamed from: C  reason: collision with root package name */
    public final C1287n f5086C;
    public final C1287n D;
    public final C1287n E;

    /* renamed from: F  reason: collision with root package name */
    public final C1287n f5087F;

    /* renamed from: G  reason: collision with root package name */
    public final C1287n f5088G;

    /* renamed from: H  reason: collision with root package name */
    public final C1287n f5089H;

    /* renamed from: I  reason: collision with root package name */
    public final C1287n f5090I;

    /* renamed from: J  reason: collision with root package name */
    public final C1287n f5091J;

    /* renamed from: K  reason: collision with root package name */
    public final C1287n f5092K;
    public final C1287n L;

    /* renamed from: M  reason: collision with root package name */
    public final C1287n f5093M;

    /* renamed from: N  reason: collision with root package name */
    public final C1287n f5094N;

    /* renamed from: O  reason: collision with root package name */
    public final C1287n f5095O;

    /* renamed from: P  reason: collision with root package name */
    public final C1287n f5096P;
    public final C1287n Q;
    public final C1287n R;
    public final C1287n S;
    public final C1287n T;
    public final C1287n U;
    public final C1287n V;

    /* renamed from: W  reason: collision with root package name */
    public final C1287n f5097W;

    /* renamed from: X  reason: collision with root package name */
    public final C1287n f5098X;

    /* renamed from: a  reason: collision with root package name */
    public boolean f5099a;
    public final C1287n b = new C1287n(C1275b.d, this);

    /* renamed from: c  reason: collision with root package name */
    public final C1287n f5100c;
    public final C1287n d;
    public final C1287n e;
    public final C1287n f;
    public final C1287n g;

    /* renamed from: h  reason: collision with root package name */
    public final C1287n f5101h;

    /* renamed from: i  reason: collision with root package name */
    public final C1287n f5102i;

    /* renamed from: j  reason: collision with root package name */
    public final C1287n f5103j;
    public final C1287n k;
    public final C1287n l;
    public final C1287n m;
    public final C1287n n;

    /* renamed from: o  reason: collision with root package name */
    public final C1287n f5104o;

    /* renamed from: p  reason: collision with root package name */
    public final C1287n f5105p;
    public final C1287n q;
    public final C1287n r;
    public final C1287n s;
    public final C1287n t;
    public final C1287n u;
    public final C1287n v;
    public final C1287n w;

    /* renamed from: x  reason: collision with root package name */
    public final C1287n f5106x;
    public final C1287n y;
    public final C1287n z;

    static {
        w wVar = v.f4727a;
        Class<C1288o> cls = C1288o.class;
        j d2 = wVar.d(new m(wVar.b(cls), "classifierNamePolicy", "getClassifierNamePolicy()Lorg/jetbrains/kotlin/renderer/ClassifierNamePolicy;"));
        j jVar = d2;
        Y = new t[]{jVar, wVar.d(new m(wVar.b(cls), "withDefinedIn", "getWithDefinedIn()Z")), wVar.d(new m(wVar.b(cls), "withSourceFileForTopLevel", "getWithSourceFileForTopLevel()Z")), wVar.d(new m(wVar.b(cls), "modifiers", "getModifiers()Ljava/util/Set;")), wVar.d(new m(wVar.b(cls), "startFromName", "getStartFromName()Z")), wVar.d(new m(wVar.b(cls), "startFromDeclarationKeyword", "getStartFromDeclarationKeyword()Z")), wVar.d(new m(wVar.b(cls), "debugMode", "getDebugMode()Z")), wVar.d(new m(wVar.b(cls), "classWithPrimaryConstructor", "getClassWithPrimaryConstructor()Z")), wVar.d(new m(wVar.b(cls), "verbose", "getVerbose()Z")), wVar.d(new m(wVar.b(cls), "unitReturnType", "getUnitReturnType()Z")), wVar.d(new m(wVar.b(cls), "withoutReturnType", "getWithoutReturnType()Z")), wVar.d(new m(wVar.b(cls), "enhancedTypes", "getEnhancedTypes()Z")), wVar.d(new m(wVar.b(cls), "normalizedVisibilities", "getNormalizedVisibilities()Z")), wVar.d(new m(wVar.b(cls), "renderDefaultVisibility", "getRenderDefaultVisibility()Z")), wVar.d(new m(wVar.b(cls), "renderDefaultModality", "getRenderDefaultModality()Z")), wVar.d(new m(wVar.b(cls), "renderConstructorDelegation", "getRenderConstructorDelegation()Z")), wVar.d(new m(wVar.b(cls), "renderPrimaryConstructorParametersAsProperties", "getRenderPrimaryConstructorParametersAsProperties()Z")), wVar.d(new m(wVar.b(cls), "actualPropertiesInPrimaryConstructor", "getActualPropertiesInPrimaryConstructor()Z")), wVar.d(new m(wVar.b(cls), "uninferredTypeParameterAsName", "getUninferredTypeParameterAsName()Z")), wVar.d(new m(wVar.b(cls), "includePropertyConstant", "getIncludePropertyConstant()Z")), wVar.d(new m(wVar.b(cls), "propertyConstantRenderer", "getPropertyConstantRenderer()Lkotlin/jvm/functions/Function1;")), wVar.d(new m(wVar.b(cls), "withoutTypeParameters", "getWithoutTypeParameters()Z")), wVar.d(new m(wVar.b(cls), "withoutSuperTypes", "getWithoutSuperTypes()Z")), wVar.d(new m(wVar.b(cls), "typeNormalizer", "getTypeNormalizer()Lkotlin/jvm/functions/Function1;")), wVar.d(new m(wVar.b(cls), "defaultParameterValueRenderer", "getDefaultParameterValueRenderer()Lkotlin/jvm/functions/Function1;")), wVar.d(new m(wVar.b(cls), "secondaryConstructorsAsPrimary", "getSecondaryConstructorsAsPrimary()Z")), wVar.d(new m(wVar.b(cls), "overrideRenderingPolicy", "getOverrideRenderingPolicy()Lorg/jetbrains/kotlin/renderer/OverrideRenderingPolicy;")), wVar.d(new m(wVar.b(cls), "valueParametersHandler", "getValueParametersHandler()Lorg/jetbrains/kotlin/renderer/DescriptorRenderer$ValueParametersHandler;")), wVar.d(new m(wVar.b(cls), "textFormat", "getTextFormat()Lorg/jetbrains/kotlin/renderer/RenderingFormat;")), wVar.d(new m(wVar.b(cls), "parameterNameRenderingPolicy", "getParameterNameRenderingPolicy()Lorg/jetbrains/kotlin/renderer/ParameterNameRenderingPolicy;")), wVar.d(new m(wVar.b(cls), "receiverAfterName", "getReceiverAfterName()Z")), wVar.d(new m(wVar.b(cls), "renderCompanionObjectName", "getRenderCompanionObjectName()Z")), wVar.d(new m(wVar.b(cls), "propertyAccessorRenderingPolicy", "getPropertyAccessorRenderingPolicy()Lorg/jetbrains/kotlin/renderer/PropertyAccessorRenderingPolicy;")), wVar.d(new m(wVar.b(cls), "renderDefaultAnnotationArguments", "getRenderDefaultAnnotationArguments()Z")), wVar.d(new m(wVar.b(cls), "eachAnnotationOnNewLine", "getEachAnnotationOnNewLine()Z")), wVar.d(new m(wVar.b(cls), "excludedAnnotationClasses", "getExcludedAnnotationClasses()Ljava/util/Set;")), wVar.d(new m(wVar.b(cls), "excludedTypeAnnotationClasses", "getExcludedTypeAnnotationClasses()Ljava/util/Set;")), wVar.d(new m(wVar.b(cls), "annotationFilter", "getAnnotationFilter()Lkotlin/jvm/functions/Function1;")), wVar.d(new m(wVar.b(cls), "annotationArgumentsRenderingPolicy", "getAnnotationArgumentsRenderingPolicy()Lorg/jetbrains/kotlin/renderer/AnnotationArgumentsRenderingPolicy;")), wVar.d(new m(wVar.b(cls), "alwaysRenderModifiers", "getAlwaysRenderModifiers()Z")), wVar.d(new m(wVar.b(cls), "renderConstructorKeyword", "getRenderConstructorKeyword()Z")), wVar.d(new m(wVar.b(cls), "renderUnabbreviatedType", "getRenderUnabbreviatedType()Z")), wVar.d(new m(wVar.b(cls), "renderTypeExpansions", "getRenderTypeExpansions()Z")), wVar.d(new m(wVar.b(cls), "renderAbbreviatedTypeComments", "getRenderAbbreviatedTypeComments()Z")), wVar.d(new m(wVar.b(cls), "includeAdditionalModifiers", "getIncludeAdditionalModifiers()Z")), wVar.d(new m(wVar.b(cls), "parameterNamesInFunctionalTypes", "getParameterNamesInFunctionalTypes()Z")), wVar.d(new m(wVar.b(cls), "renderFunctionContracts", "getRenderFunctionContracts()Z")), wVar.d(new m(wVar.b(cls), "presentableUnresolvedTypes", "getPresentableUnresolvedTypes()Z")), wVar.d(new m(wVar.b(cls), "boldOnlyForNamesInHtml", "getBoldOnlyForNamesInHtml()Z")), wVar.d(new m(wVar.b(cls), "informativeErrorType", "getInformativeErrorType()Z"))};
    }

    public C1288o() {
        Boolean bool = Boolean.TRUE;
        this.f5100c = new C1287n(bool, this);
        this.d = new C1287n(bool, this);
        this.e = new C1287n(C1285l.ALL_EXCEPT_ANNOTATIONS, this);
        Boolean bool2 = Boolean.FALSE;
        this.f = new C1287n(bool2, this);
        this.g = new C1287n(bool2, this);
        this.f5101h = new C1287n(bool2, this);
        this.f5102i = new C1287n(bool2, this);
        this.f5103j = new C1287n(bool2, this);
        this.k = new C1287n(bool, this);
        this.l = new C1287n(bool2, this);
        this.m = new C1287n(bool2, this);
        this.n = new C1287n(bool2, this);
        this.f5104o = new C1287n(bool, this);
        this.f5105p = new C1287n(bool, this);
        this.q = new C1287n(bool2, this);
        this.r = new C1287n(bool2, this);
        this.s = new C1287n(bool2, this);
        this.t = new C1287n(bool2, this);
        this.u = new C1287n(bool2, this);
        this.v = new C1287n((Object) null, this);
        this.w = new C1287n(bool2, this);
        this.f5106x = new C1287n(bool2, this);
        this.y = new C1287n(C1277d.r, this);
        this.z = new C1287n(C1277d.s, this);
        this.f5085A = new C1287n(bool, this);
        this.B = new C1287n(C1291r.RENDER_OPEN, this);
        this.f5086C = new C1287n(C1279f.f5080a, this);
        this.D = new C1287n(w.PLAIN, this);
        this.E = new C1287n(C1292s.ALL, this);
        this.f5087F = new C1287n(bool2, this);
        this.f5088G = new C1287n(bool2, this);
        this.f5089H = new C1287n(C1293t.DEBUG, this);
        this.f5090I = new C1287n(bool2, this);
        this.f5091J = new C1287n(bool2, this);
        this.f5092K = new C1287n(ne.v.d, this);
        this.L = new C1287n(C1289p.f5107a, this);
        this.f5093M = new C1287n((Object) null, this);
        this.f5094N = new C1287n(C1274a.NO_ARGUMENTS, this);
        this.f5095O = new C1287n(bool2, this);
        this.f5096P = new C1287n(bool, this);
        this.Q = new C1287n(bool, this);
        this.R = new C1287n(bool2, this);
        this.S = new C1287n(bool2, this);
        this.T = new C1287n(bool, this);
        this.U = new C1287n(bool, this);
        this.V = new C1287n(bool2, this);
        this.f5097W = new C1287n(bool2, this);
        this.f5098X = new C1287n(bool, this);
    }

    public final void a() {
        t tVar = Y[30];
        this.f5087F.e(Boolean.TRUE, tVar);
    }

    public final void b() {
        t tVar = Y[31];
        this.f5088G.e(Boolean.TRUE, tVar);
    }

    public final void c(C1276c cVar) {
        this.b.e(cVar, Y[0]);
    }

    public final void d() {
        t tVar = Y[21];
        this.w.e(Boolean.TRUE, tVar);
    }

    public final void e(C1292s sVar) {
        kotlin.jvm.internal.j.e(sVar, "<set-?>");
        this.E.e(sVar, Y[29]);
    }

    public final void f(w wVar) {
        kotlin.jvm.internal.j.e(wVar, "<set-?>");
        this.D.e(wVar, Y[28]);
    }

    public final void g() {
        t tVar = Y[4];
        this.f.e(Boolean.TRUE, tVar);
    }

    public final Set h() {
        return (Set) this.L.f(this, Y[36]);
    }

    public final void i() {
        t tVar = Y[22];
        this.f5106x.e(Boolean.TRUE, tVar);
    }

    public final void j() {
        t tVar = Y[6];
        this.f5101h.e(Boolean.TRUE, tVar);
    }

    public final void k(Set set) {
        kotlin.jvm.internal.j.e(set, "<set-?>");
        this.e.e(set, Y[3]);
    }

    public final void l(LinkedHashSet linkedHashSet) {
        this.L.e(linkedHashSet, Y[36]);
    }

    public final void m() {
        t tVar = Y[1];
        this.f5100c.e(Boolean.FALSE, tVar);
    }

    public final boolean n() {
        return ((Boolean) this.f5101h.f(this, Y[6])).booleanValue();
    }
}
