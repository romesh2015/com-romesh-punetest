package com.romesh.punetest.profile.view.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.romesh.punetest.R
import com.romesh.punetest.appdb.ProfileModel
import com.romesh.punetest.databinding.RecyclerViewProfileBinding
import com.romesh.punetest.utility.Constant.MY_ADDRESS
import com.romesh.punetest.utility.Constant.MY_EMAIL
import com.romesh.punetest.utility.Constant.MY_LOCATION
import com.romesh.punetest.utility.Constant.MY_PHONE
import com.romesh.punetest.utility.Constant.MY_USERNAME
import kotlinx.android.synthetic.main.recycler_view_profile.view.*
/*
* This adapter show the user profile from database
 */
class ProfilesOfflineAdapter : RecyclerView.Adapter<ProfilesOfflineAdapter.ProfileViewHolder>() {
    private var results: List<ProfileModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_profile,
            parent,
            false
        )
    )
    override fun getItemCount() = results?.size ?: 0

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        results?.let {
            holder.itemView.address.text= results!![position].street
            holder.itemView.text_title.text= MY_ADDRESS
            val imagePath= results!![position].picture
            if(imagePath!=null){
                Glide.with(holder.itemView.image_view_profile_pic)
                    .load(imagePath)
                    .into(holder.itemView.image_view_profile_pic);
            }
           holder.itemView.btn_globe.setOnClickListener {
               holder.itemView.text_title.text= MY_LOCATION
               holder.itemView.address.text= results!![position].street+","+results!![position].city+","+results!![position].state
           }
            holder.itemView.btn_calender.setOnClickListener {
                holder.itemView.text_title.text= MY_EMAIL
                holder.itemView.address.text= results!![position].email
            }
            holder.itemView.btn_location.setOnClickListener {
                holder.itemView.text_title.text= MY_ADDRESS
                holder.itemView.address.text= results!![position].street
            }
            holder.itemView.btn_phone.setOnClickListener {
                holder.itemView.text_title.text= MY_PHONE
                holder.itemView.address.text= results!![position].phone
            }
            holder.itemView.btn_account.setOnClickListener {
                holder.itemView.text_title.text= MY_USERNAME
                holder.itemView.address.text= results!![position].username
            }
        }
    }
    fun setProfiles(results: List<ProfileModel>) {
        this.results = results
        notifyDataSetChanged()
    }
    inner class ProfileViewHolder(val binding: RecyclerViewProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

}