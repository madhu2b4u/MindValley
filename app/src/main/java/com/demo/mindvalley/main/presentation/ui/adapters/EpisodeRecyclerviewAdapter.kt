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
import com.demo.mindvalley.assemblyrecyclerview.NotifyableAdapter
import com.demo.mindvalley.assemblyrecyclerview.OnNotifyDatasetChangeListener
import com.demo.mindvalley.assemblyrecyclerview.RecyclerViewAssemblerElement
import com.demo.mindvalley.main.data.models.episodemodel.Media
import com.facebook.shimmer.ShimmerFrameLayout


class EpisodeRecyclerviewAdapter(private val episodes: List<Media>) :
    RecyclerView.Adapter<EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
        return EpisodesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        return holder.bind(episodes[position])
    }
}

class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.tvEpisodeTitle)
    private val channel: TextView = itemView.findViewById(R.id.tvEpsisodeChannel)
    private val cover: ImageView = itemView.findViewById(R.id.ivEpisodeIcon)
    private val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.sfEpisode)

    fun bind(media: Media) {

        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(16))

        Glide.with(itemView.context)
            .load(media.coverAsset.url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .error(R.drawable.ic_image_error)
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

        if (media.title.isNotEmpty())
            title.text = media.title
        else
            title.text = title.context.getString(R.string.title_not_available)
        if (media.channel.title.isNotEmpty())
            channel.text = media.channel.title
        else
            channel.text = title.context.getString(R.string.channel_not_available)
    }
}
