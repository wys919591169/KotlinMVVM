package com.itcunkou.kotlinmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itcunkou.kotlinmvvm.R
import com.itcunkou.kotlinmvvm.db.bean.User
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * @author wys
 * @date 2020/2/29/0029
 * Function:
 */
class UserAdapter : PagedListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback()) {

    private lateinit var onItemClick: (user: User) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.itemView.tv_user.text = data.name
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

    infix fun setOnItemClick(onClick: (user: User) -> Unit) {
        this.onItemClick = onClick
    }
}
/* Callback for calculating the diff between two non-null items in a list. */
private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}