package com.sandiarta.hobbyapp_160421110.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sandiarta.hobbyapp_160421110.databinding.BeritaListItemBinding
import com.sandiarta.hobbyapp_160421110.model.Berita
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class BeritaListAdapter(val beritaList:ArrayList<Berita>)
    : RecyclerView.Adapter<BeritaListAdapter.BeritaViewHolder>() {

    class BeritaViewHolder(var binding: BeritaListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeritaListAdapter.BeritaViewHolder {
        val binding = BeritaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaListAdapter.BeritaViewHolder, position: Int) {
        holder.binding.textViewTitle.text = beritaList[position].title
        holder.binding.textViewUser.text = "@"+beritaList[position].name
        holder.binding.textViewDesc.text = beritaList[position].descriptions[0]


        holder.binding.buttonRead.setOnClickListener {
            val action = HomeFragmentDirections.actionDetailBeritaFragment(beritaList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(beritaList[position].image).into(holder.binding.imageViewBerita, object:Callback {
            override fun onSuccess() {
//                holder.binding.progressBarImage.visibility = View.INVISIBLE;
                holder.binding.imageViewBerita.visibility = View.VISIBLE;
            }

            override fun onError(e: Exception?) {
                Log.d("Cek","Error")
            }

        })
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }
    fun updateBeritaList(newStudentList: ArrayList<Berita>) {
        beritaList.clear()
        beritaList.addAll(newStudentList)
        notifyDataSetChanged()
    }


}