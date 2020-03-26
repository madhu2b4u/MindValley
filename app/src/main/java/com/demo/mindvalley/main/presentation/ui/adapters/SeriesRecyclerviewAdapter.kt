package com.demo.mindvalley.main.presentation.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.demo.mindvalley.R
import com.demo.mindvalley.main.data.models.channelmodel.LatestMedia
import com.facebook.shimmer.ShimmerFrameLayout

class SeriesRecyclerviewAdapter(private val series: List<LatestMedia>) :
    RecyclerView.Adapter<SeriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false)
        return SeriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        val limit = 6
        return if (series.size > 6)
            limit
        else
            series.size
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        return holder.bind(series[position])
    }
}

class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cover: ImageView = itemView.findViewById(R.id.ivSeriesImage)
    private val title: TextView = itemView.findViewById(R.id.tvSeriesTitle)
    private val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.sfSeries)

    fun bind(sery: LatestMedia) {
        if (sery.title.isEmpty())
            title.text = title.context.getString(R.string.series_not_available)
        else
            title.text = sery.title

        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(16))

        if (sery.coverAsset.url.isEmpty()) {
            Glide.with(itemView.context).load(R.drawable.ic_image_error_wide).centerCrop()
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmer.hideShimmer()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmer.hideShimmer()
                        return false
                    }

                })
                .into(cover)
        } else {
            Glide.with(itemView.context)
                .load(sery.coverAsset.url)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_image_error_wide)
                .centerCrop()
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmer.hideShimmer()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmer.hideShimmer()
                        return false
                    }

                })
                .into(cover)
        }
    }

}