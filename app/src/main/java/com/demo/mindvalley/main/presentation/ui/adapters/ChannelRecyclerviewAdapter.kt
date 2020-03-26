package com.demo.mindvalley.main.presentation.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.bumptech.glide.request.target.Target
import com.demo.mindvalley.R
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.facebook.shimmer.ShimmerFrameLayout

class ChannelRecyclerviewAdapter(private val channels: List<Channel>) :
    RecyclerView.Adapter<ChannelsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_channels, parent, false)
        return ChannelsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return channels.size
    }

    override fun onBindViewHolder(holder: ChannelsViewHolder, position: Int) {
        holder.bind(channels[position])
    }
}

class ChannelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val icon: ImageView = itemView.findViewById(R.id.ivChannelIcon)
    private val name: TextView = itemView.findViewById(R.id.tvChannelName)
    private val count: TextView = itemView.findViewById(R.id.tvChannelCount)
    private val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.sfChannel)
    private val channelsRecycler: RecyclerView = itemView.findViewById(R.id.rvSeries)

    fun bind(channel: Channel) {

        val context = name.context
        
        if (channel.title.isNotEmpty())
            name.text = channel.title
        else
            name.text = context.getString(R.string.name_not_available)


        if (channel.series.isEmpty()) {
            count.text = "${channel.mediaCount}" + " " + context.getString(R.string.episode)
            channelsRecycler.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = CourseRecyclerviewAdapter(channel.latestMedia)
            }

        } else {
            count.text = "${channel.mediaCount}" + " " + context.getString(R.string.series)
            channelsRecycler.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = SeriesRecyclerviewAdapter(channel.latestMedia)
            }
        }

        if (channel.iconAsset == null || channel.iconAsset.thumbnailUrl.isNullOrEmpty()) {
            Glide.with(itemView.context).load(R.drawable.ic_image_error).centerCrop()
                .apply(circleCropTransform())
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
                .into(icon)
        } else {
            Glide.with(itemView.context)
                .load(channel.iconAsset.thumbnailUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_image_error)
                .centerCrop()
                .apply(circleCropTransform())
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
                .into(icon)

        }
    }

}