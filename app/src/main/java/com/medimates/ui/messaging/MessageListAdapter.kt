package com.medimates.ui.messaging

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.medimates.R
import com.medimates.extensions.formatDate
import com.medimates.model.Message
import java.util.Calendar

class MessageListAdapter(activity: Activity, messageList: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mActivity: Activity
    private val mMessageList: List<Message>

    init {
        mActivity = activity
        mMessageList = messageList
    }

    override fun getItemCount(): Int {
        return mMessageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val message: Message = mMessageList[position]
        return if (message.sender?.userId.equals("me"/*getCurrentUser().getUserId()*/)) {
            VIEW_TYPE_MESSAGE_SENT
        } else {
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.context).inflate(
                R.layout.current_user_chat_bubble,
                parent,
                false
            )
            SentMessageViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(
                R.layout.other_user_chat_bubble,
                parent,
                false
            )
            ReceivedMessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message: Message = mMessageList[position]
        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> {
                (holder as SentMessageViewHolder?)?.bind(message)
                groupByDate(position, holder)
            }
            VIEW_TYPE_MESSAGE_RECEIVED -> {
                (holder as ReceivedMessageViewHolder?)?.bind(message)
                groupByDate(position, holder)
            }
        }
    }

    private inner class SentMessageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageText: TextView
        var timeText: TextView
        var dateLayout: LinearLayout
        fun bind(message: Message) {
            messageText.text = message.messageText
        }

        init {
            messageText = itemView.findViewById<View>(R.id.chat_message_current_user) as TextView
            timeText = itemView.findViewById<View>(R.id.chat_timestamp_current_user) as TextView
            dateLayout = itemView.findViewById(R.id.dateLayout) as LinearLayout
        }
    }

    private inner class ReceivedMessageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageText: TextView
        var timeText: TextView
        var nameText: TextView
        var profileImage: ImageView
        var dateLayout: LinearLayout

        fun bind(message: Message) {
            messageText.text = message.messageText
            nameText.text = message.sender?.nickname

         /*   Utils.displayRoundImageFromUrl(
                mContext,
                message.sender?.profileImageUrl,
                profileImage
            )*/
        }

        init {
            messageText = itemView.findViewById<View>(R.id.chat_message_other_user) as TextView
            timeText = itemView.findViewById<View>(R.id.chat_timestamp_other_user) as TextView
            nameText = itemView.findViewById<View>(R.id.chat_user_name_other) as TextView
            profileImage = itemView.findViewById<View>(R.id.chat_profile_image_other_user) as ImageView
            dateLayout = itemView.findViewById(R.id.dateLayout) as LinearLayout
        }
    }

    /**
     * ASK BE FOR SORTED LIST BY DATE TO RESOLVE SORTING ISSUE - paging
     */
    private fun groupByDate(position: Int, holder: RecyclerView.ViewHolder) {
        var previousTs: Long = 0
        if (position >= 1) {
            val previousMessage: Message = mMessageList[position - 1]
            previousTs = previousMessage.createdAt
        }
        val cal1: Calendar = Calendar.getInstance()
        val cal2: Calendar = Calendar.getInstance()
        cal1.timeInMillis = mMessageList[position].createdAt * 1000
        cal2.timeInMillis = previousTs * 1000
        val sameDay = cal1.get(Calendar.YEAR) === cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) === cal2.get(Calendar.DAY_OF_YEAR)
        if (!sameDay) {
            val dateView = TextView(mActivity)
            dateView.text = mMessageList[position].createdAt.formatDate(mActivity)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.CENTER_HORIZONTAL
            dateView.layoutParams = params

            when (holder.itemViewType) {
                VIEW_TYPE_MESSAGE_SENT -> {
                    (holder as SentMessageViewHolder?)?.dateLayout?.addView(dateView)
                }
                VIEW_TYPE_MESSAGE_RECEIVED -> {
                    (holder as ReceivedMessageViewHolder?)?.dateLayout?.addView(dateView)
                }
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }
}
