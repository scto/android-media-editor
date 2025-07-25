package org.pixeldroid.media_editor.photoEdit.imagine.layers

import org.pixeldroid.media_editor.photoEdit.R
import org.pixeldroid.media_editor.photoEdit.imagine.core.types.ImagineLayer

class RandLayer : ImagineLayer(initialIntensity = 1f) {

    override val name: Int = R.string.filterRand

    override val source: String =
        """
        vec4 process(vec4 color, sampler2D uImage, vec2 vTexCoords) {
            vec3 bw = vec3(color.r * 0.3 + color.g * 0.59 + color.b * 0.11); 
            vec3 contrasted = (bw.rgb - vec3(0.5)) * 2.0 + vec3(0.5);
            return vec4(contrasted, color.a);
        }
    """
            .trimIndent()
}
