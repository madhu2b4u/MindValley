package com.demo.mindvalley

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.demo.mindvalley.main.presentation.ui.activity.HomeActivity
import junit.framework.Assert.*
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UITests {

    @get:Rule
    val homeActivity = ActivityTestRule(HomeActivity::class.java, true, true)

    lateinit var rvEpisodes: RecyclerView
    lateinit var rvChannels: RecyclerView
    lateinit var rvCategories: RecyclerView

    @Before
    fun init() {
        rvEpisodes = homeActivity.activity.findViewById(R.id.rvEpisodes)
        rvChannels = homeActivity.activity.findViewById(R.id.rvChannels)
        rvCategories = homeActivity.activity.findViewById(R.id.rvCategories)
    }

    @Test
    fun checkAllTexViews() {
        onView(withId(R.id.tvPageTitle)).check(matches(not(doesNotExist())))
        onView(withId(R.id.tvEpisodes)).check(matches(not(doesNotExist())))
        onView(withId(R.id.tvCategories)).check(matches(not(doesNotExist())))
        homeActivity.finishActivity()
    }

    @Test
    fun testRecyclerview() {
        val episodesCount = rvEpisodes.adapter!!.itemCount
        val channelsCount = rvChannels.adapter!!.itemCount
        onView(withId(R.id.rvEpisodes)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                episodesCount
            )
        )
        onView(withId(R.id.rvChannels)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                channelsCount
            )
        )
        homeActivity.finishActivity()
    }

    @Test
    fun testRecyclerViewItems() {
        onView(withId(R.id.rvEpisodes)).check(
            itemViewMatches(
                1,
                R.id.tvEpisodeTitle,
                withEffectiveVisibility(Visibility.VISIBLE)
            )
        )
        onView(withId(R.id.rvEpisodes)).check(
            itemViewMatches(
                1,
                R.id.ivEpisodeIcon,
                withEffectiveVisibility(Visibility.VISIBLE)
            )
        )
        onView(withId(R.id.rvChannels)).check(
            itemViewMatches(
                1,
                R.id.tvChannelName,
                withEffectiveVisibility(Visibility.VISIBLE)
            )
        )
        onView(withId(R.id.rvChannels)).check(
            itemViewMatches(
                1,
                R.id.ivChannelIcon,
                withEffectiveVisibility(Visibility.VISIBLE)
            )
        )
        onView(withId(R.id.rvCategories)).check(
            itemViewMatches(
                1,
                R.id.tvCategoryTitle,
                withEffectiveVisibility(Visibility.VISIBLE)
            )
        )
        homeActivity.finishActivity()
    }

    private fun itemViewMatches(
        position: Int, @IdRes resId: Int,
        viewMatcher: Matcher<View>
    ): ViewAssertion {
        assertNotNull(viewMatcher)

        return ViewAssertion { view, noViewException ->
            if (noViewException != null) {
                throw noViewException
            }

            assertTrue("View is RecyclerView", view is RecyclerView)

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            val itemType = adapter!!.getItemViewType(position)
            val viewHolder = adapter.createViewHolder(recyclerView, itemType)
            adapter.bindViewHolder(viewHolder, position)

            val targetView = if (resId == -1) {
                viewHolder.itemView
            } else {
                viewHolder.itemView.findViewById(resId)
            }

            if (viewMatcher.matches(targetView)) {
                return@ViewAssertion
            }

            fail("No match found")
        }
    }
}
