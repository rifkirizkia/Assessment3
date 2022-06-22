package org.d3if1139.penghitungkalori.ui.saran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if1139.penghitungkalori.R
import org.d3if1139.penghitungkalori.databinding.ItemSaranBinding
import org.d3if1139.penghitungkalori.model.SaranItem

class SaranAdapter() : RecyclerView.Adapter<SaranAdapter.ViewHolder>(){
    private var text: String? = null
    private var imgUrl: String? = null
    fun setData(text: String, imgUrl: String){
        this.text = text
    this.imgUrl = imgUrl
    }
    private val data = mutableListOf<SaranItem>()
    fun updateData(newData: List<SaranItem>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ItemSaranBinding
    ) : RecyclerView.ViewHolder(binding.root)
    {

        fun bind(drawable: String?, text: String?) = with(binding) {
            saranTextView.text = text
            Glide.with(imageView.context)
                .load(drawable)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemSaranBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imgUrl,text )
    }

    override fun getItemCount(): Int {
        return 1
    }
}