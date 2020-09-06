package com.romesh.punetest.profile.view.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.romesh.punetest.R
import com.romesh.punetest.databinding.RecyclerViewProfileBinding
import kotlinx.android.synthetic.main.recycler_view_profile.view.*
import com.romesh.punetest.profile.model.Results
import com.romesh.punetest.profile.model.User
import com.romesh.punetest.utility.Constant.MY_ADDRESS
import com.romesh.punetest.utility.Constant.MY_EMAIL
import com.romesh.punetest.utility.Constant.MY_LOCATION
import com.romesh.punetest.utility.Constant.MY_PHONE
import com.romesh.punetest.utility.Constant.MY_USERNAME
/*
* This adapter show the user profile from api response
 */
class ProfilesAdapter : RecyclerView.Adapter<ProfilesAdapter.ProfileViewHolder>() {
    private var results: List<User>? = null
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
            holder.itemView.address.text= results!![0].location.street
            holder.itemView.text_title.text= MY_ADDRESS
            val imagePath= results!![0].picture
            if(imagePath!=null){
                Glide.with(holder.itemView.image_view_profile_pic)
                    .load(imagePath)
                    .into(holder.itemView.image_view_profile_pic);
            }
           holder.itemView.btn_globe.setOnClickListener {
               holder.itemView.text_title.text= MY_LOCATION
               holder.itemView.address.text= results!![0].location.street+","+results!![0].location.city+","+results!![0].location.state
           }
            holder.itemView.btn_calender.setOnClickListener {
                holder.itemView.text_title.text= MY_EMAIL
                holder.itemView.address.text= results!![0].email
            }
            holder.itemView.btn_location.setOnClickListener {
                holder.itemView.text_title.text= MY_ADDRESS
                holder.itemView.address.text= results!![0].location.street
            }
            holder.itemView.btn_phone.setOnClickListener {
                holder.itemView.text_title.text= MY_PHONE
                holder.itemView.address.text= results!![0].phone
            }
            holder.itemView.btn_account.setOnClickListener {
                holder.itemView.text_title.text= MY_USERNAME
                holder.itemView.address.text= results!![0].username
            }
        }
    }
    fun setProfiles(results: List<Results>) {
        this.results = listOf(results[0].user)
        notifyDataSetChanged()
    }

    inner class ProfileViewHolder(val binding: RecyclerViewProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

}