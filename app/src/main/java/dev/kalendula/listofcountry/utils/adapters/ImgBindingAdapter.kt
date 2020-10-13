package dev.kalendula.listofcountry.utils.adapters

import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.kalendula.listofcountry.utils.imageloading.GlideApp
import dev.kalendula.listofcountry.utils.imageloading.SvgSoftwareLayerSetter

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    if (!url.isBlank()) {
        val uri = Uri.parse(url)
        val requestBuilder : RequestBuilder<PictureDrawable> = GlideApp.with(view.context)
            .`as`(PictureDrawable::class.java)
            .error(error)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(SvgSoftwareLayerSetter())
        requestBuilder.load(uri).into(view)
    }
}





