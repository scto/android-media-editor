package org.pixeldroid.media_editor.photoEdit.imagine.core.objects

import android.opengl.GLES20
import org.pixeldroid.media_editor.photoEdit.imagine.core.types.ImagineBlendMode
import org.pixeldroid.media_editor.photoEdit.imagine.core.types.ImagineMatrix

/**
 * A wrapper for an [ImagineShader.Program]. This follows the Decorator
 * pattern to provide some Imagine layer processing specific functionality
 * using the contained shader program.
 *
 * @property program The underlying [ImagineShader.Program]
 */
internal class ImagineLayerShader(
    val program: ImagineShader.Program
) {
    /**
     * Aspect ratio matrix uniform
     */
    private val aspectRatio: ImagineShaderBinding.Uniform =
        ImagineShaderBinding.Uniform(GLES20.glGetUniformLocation(program.program, "uAspectRatio"))

    /**
     * Invert matrix uniform
     */
    private val invert: ImagineShaderBinding.Uniform =
        ImagineShaderBinding.Uniform(GLES20.glGetUniformLocation(program.program, "uInvert"))

    /**
     * Image texture uniform
     */
    private val image: ImagineShaderBinding.Uniform =
        ImagineShaderBinding.Uniform(GLES20.glGetUniformLocation(program.program, "sampler2D"))

    /**
     * Intensity value uniform
     */
    private val intensity: ImagineShaderBinding.Uniform =
        ImagineShaderBinding.Uniform(GLES20.glGetUniformLocation(program.program, "uIntensity"))

    /**
     * Blend mode raw value uniform
     */
    private val blendMode: ImagineShaderBinding.Uniform =
        ImagineShaderBinding.Uniform(GLES20.glGetUniformLocation(program.program, "uBlendMode"))


    /**
     * Release the underlying shader program
     */
    fun release() {
        program.release()
    }

    /**
     * Bind the underlying shader for drawing
     */
    fun bind() {
        program.use()
    }

    /**
     * Bind an aspect ratio matrix to be used to correctly shape the
     * quad inside the viewport
     *
     * @param matrix An instance of [ImagineMatrix]
     */
    fun bindAspectRatioMatrix(matrix: ImagineMatrix) {
        aspectRatio.bindMatrix(matrix)
    }


    /**
     * Bind an image inversion matrix to be used to invert an
     * upside down bitmap
     *
     * @param matrix An instance of [ImagineMatrix]
     */
    fun bindInvertMatrix(matrix: ImagineMatrix) {
        invert.bindMatrix(matrix)
    }


    /**
     * Bind a Texture Object to the correct uniform in the shader program
     *
     * @param texture The [ImagineTexture] to bind
     * @param index Texture index to bind it at
     */
    fun bindImage(texture: ImagineTexture, index: Int = 0) {
        image.bindTexture(texture, index)
    }

    /**
     * Bind the intensity value to the shader program to control
     * the interpolation between the original and the processed layer
     *
     * @param value A Float value between 0.0f and 1.0f
     */
    fun bindIntensity(value: Float) {
        intensity.bindFloat(value)
    }

    /**
     * Bind the blendMode value to the shader program to control
     * the blending between the original and the processed layer
     *
     * @param value A Float value between 0.0f and 1.0f
     */
    fun bindBlendMode(value: ImagineBlendMode) {
        blendMode.bindInt(value.ordinal)
    }

    companion object {

        /**
         * Vertex streaming attribute
         */
        val position = ImagineShaderBinding.Attribute(0)

        /**
         * Texture coordinates streaming attribute
         */
        val texCoords = ImagineShaderBinding.Attribute(1)
    }
}