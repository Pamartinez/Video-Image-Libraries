package com.samsung.android.sdk.scs.ai.asr.tasks;

import android.graphics.RuntimeShader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class d {
    public static /* synthetic */ RuntimeShader h() {
        return new RuntimeShader("\nconst int MaxSpots = 5;\n    \nuniform shader inputShader;\nuniform shader spotLightMapShader;\nuniform half2 uLightMapSize;\n\nuniform half2 uSize;\nuniform half uTime;\n\nuniform half4 uBaseColor;\nuniform int uSpotCount;\n\nuniform half uSpotEnabled[MaxSpots];\nuniform half4 uSpotColors[MaxSpots];\nuniform half2 uSpotPositions[MaxSpots];\nuniform half uSpotScales[MaxSpots];\n\nconst half QPI = 3.141592 * 0.25;\n\nhalf4 spotData(half2 uv, half4 color, half2 pos, half scale) {\n    half asp = uSize.x / uSize.y;\n    \n    pos.x *= asp;\n    pos /= scale;\n    pos -= half2(0.5, 0.5);\n    uv.x *= asp;\n    uv /= scale; // scale by radius\n    uv -= pos; // translate\n    half4 spot = spotLightMapShader.eval(uv * uLightMapSize);\n    half alpha = color.a * length(spot.rgb) / sqrt(3); // TODO use actual alpha channel in future.\n    return half4(color.a * spot.rgb * color.rgb, alpha);\n}\n\nhalf4 main(vec2 fragCoord) {\n    vec2 uv = fragCoord / uSize;\n    half4 spots = uBaseColor;\n    for (int i = 0; i < MaxSpots; i++) { // AGSL not support conditional loop with uniform at least now\n        if (uSpotCount == i) {\n            break;\n        }\n        if (uSpotEnabled[i] > 0) {\n            half4 s = spotData(uv, uSpotColors[i], uSpotPositions[i], uSpotScales[i]);\n            spots.rgb = s.rgb + spots.rgb * (1 - s.a); // using premult\n            spots.a += s.a * (1 - spots.a);\n        }\n    }\n    half4 view = inputShader.eval(fragCoord);\n    half useView = step(0.01, view.a);\n    \n    return mix(spots, view * spots, useView); // tint if there is alpha on the view \n}\n        ");
    }
}
