package com.example.userprofileregistration.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofileregistration.Model.UserProfile
import com.example.userprofileregistration.databinding.ItemListBinding

class UserProfileAdapter(
    private val list: List<UserProfile>,
    private val onEdit: (UserProfile) -> Unit,
    private val onDelete: (UserProfile) -> Unit,
    private val onClick: (UserProfile) -> Unit,

    ) : RecyclerView.Adapter<UserProfileAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {


        val user = list[position]
        holder.binding.tvName.text = user.name
        holder.binding.tvEmail.text = user.email

        holder.binding.ivEdit.setOnClickListener {
            onEdit(user)
        }
        holder.binding.ivDelete.setOnClickListener {
            onDelete(user)
        }
        holder.binding.root.setOnClickListener {
            onClick(user)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class UserViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

}