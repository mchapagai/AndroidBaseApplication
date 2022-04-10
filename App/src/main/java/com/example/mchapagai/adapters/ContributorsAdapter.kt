package com.example.mchapagai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mchapagai.R
import com.example.mchapagai.response.Contributors
import com.squareup.picasso.Picasso

class ContributorsAdapter(private val contributorItems: List<Contributors>) :
    RecyclerView.Adapter<ContributorsAdapter.ContributorsViewHolder>() {

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.about_contributors_items_layout, parent, false)
        return ContributorsViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getBindingAdapterPosition] which
     * will have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ContributorsViewHolder, position: Int) {
        val contributors = contributorItems[position]
        Picasso.get().load(contributors.avatar).into(holder.contributorAvatar)
        holder.contributorName.text = contributors.name
        holder.contributorBio.text = contributors.bio
        holder.contributorEmail.text = contributors.email
        holder.contributorGithubUrl.text = contributors.github_url
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return contributorItems.size
    }

    inner class ContributorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val contributorAvatar =
            itemView.findViewById<ImageView>(R.id.contributor_avatar)
        internal val contributorName = itemView.findViewById<TextView>(R.id.contributor_name)
        internal val contributorBio = itemView.findViewById<TextView>(R.id.contributor_bio)
        internal val contributorEmail = itemView.findViewById<TextView>(R.id.contributor_email)
        internal val contributorGithubUrl =
            itemView.findViewById<TextView>(R.id.contributor_github_url)
    }

}