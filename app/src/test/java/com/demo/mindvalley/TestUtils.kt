package com.demo.mindvalley

import com.demo.mindvalley.main.data.models.categoriesmodel.CategoriesResponse
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.*
import com.demo.mindvalley.main.data.models.episodemodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.CoverAsset
import com.demo.mindvalley.main.data.models.episodemodel.EpisodesResponse
import com.demo.mindvalley.main.data.models.episodemodel.Media


class TestUtils {

    //categories

    val fakeCategories = listOf(Category("Career"), Category("Financial"), Category("Parenting"))
    val fakeCategoryResponse = CategoriesResponse(
        com.demo.mindvalley.main.data.models.categoriesmodel.Data(
            fakeCategories
        )
    )

    //Episodes

    val fakeMedias = listOf(
        Media(
            Channel("Little Humans"),
            CoverAsset("https://assets.mindvalley.com/api/v1/assets/5bdbdd0e-3bd3-432b-b8cb-3d3556c58c94.jpg?transform=w_1080"),
            "Conscious Parenting",
            "course"
        )
    )
    val fakeEpisodesResponse = EpisodesResponse(
        com.demo.mindvalley.main.data.models.episodemodel.Data(
            fakeMedias
        )
    )

    //Channels

    val fakeChannels = listOf(
        Channel(
            com.demo.mindvalley.main.data.models.channelmodel.CoverAsset(
                "https://assets.mindvalley.com/api/v1/assets/5bdbdd0e-3bd3-432b-b8cb-3d3556c58c94.jpg?transform=w_1080"
            ),
            IconAsset("dd", "dd"),
            "11",
            listOf(LatestMedia(CoverAssetX("url"), "title", "titleType")),
            98,
            listOf(
                Sery(CoverAssetXX(""), "11", "title")
            ),
            "slug",
            "title"
        )
    )

    val fakeChannelResponse = ChannelsResponse(
        Data(
            fakeChannels
        )
    )


}