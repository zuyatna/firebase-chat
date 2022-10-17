package com.zuyatna.firebasechat

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.zuyatna.firebasechat.databinding.ItemMessageBinding

class FirebaseMessageAdapter(
    options: FirebaseRecyclerOptions<Message>,
    private val currentUserName: String?
    ) : FirebaseRecyclerAdapter<Message, FirebaseMessageAdapter.MessageViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_message, parent, false)
        val binding = ItemMessageBinding.bind(view)

        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int, model: Message) {
        holder.bind(model)
    }

    inner class MessageViewHolder(private val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Message) {
            binding.tvMessage.text = model.text
            setTextColor(model.name, binding.tvMessage)
            binding.tvMessenger.text = model.name

            Glide.with(itemView.context)
                .load(model.photoUrl)
                .circleCrop()
                .into(binding.ivMessenger)

            if (model.timeStamp != null) {
                binding.tvTimestamp.text = DateUtils.getRelativeTimeSpanString(model.timeStamp)
            }
        }

    }

    private fun setTextColor(name: String?, tvMessage: TextView) {
        if (currentUserName == name && name != null) {
            tvMessage.setBackgroundResource(R.drawable.rounded_message_blue)
        } else {
            tvMessage.setBackgroundResource(R.drawable.rounded_message_yellow)
        }
    }
}