package com.example.nycschools.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.databinding.SchoolsListItemBinding
import com.example.nycschools.network.model.SchoolsDirectoryResponse

class SchoolsListAdapter(private val adapterOnClick: (String) -> Unit) :
    ListAdapter<SchoolsDirectoryResponse, SchoolsListAdapter.ViewHolder>(StoreFeedDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SchoolsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), adapterOnClick)
    }

    class ViewHolder constructor(private val schoolsListItemBinding: SchoolsListItemBinding) :
        RecyclerView.ViewHolder(schoolsListItemBinding.root) {

        fun bind(
            schoolsDirectoryResponse: SchoolsDirectoryResponse,
            adapterOnClick: (String) -> Unit
        ) {
            schoolsListItemBinding.root.setOnClickListener {
                adapterOnClick(
                    schoolsDirectoryResponse.dbn
                )
            }
            schoolsListItemBinding.run {
                schoolResponse = schoolsDirectoryResponse
            }
        }
    }

}

class StoreFeedDiffUtil : DiffUtil.ItemCallback<SchoolsDirectoryResponse>() {

    override fun areItemsTheSame(
        oldItem: SchoolsDirectoryResponse,
        newItem: SchoolsDirectoryResponse
    ): Boolean {
        return oldItem.dbn == newItem.dbn
    }

    override fun areContentsTheSame(
        oldItem: SchoolsDirectoryResponse,
        newItem: SchoolsDirectoryResponse
    ): Boolean {
        return oldItem == newItem
    }

}