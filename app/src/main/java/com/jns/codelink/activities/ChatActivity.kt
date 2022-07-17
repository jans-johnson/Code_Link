package com.jns.codelink.activities

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.jns.codelink.R
import com.jns.codelink.adapters.ChatMessageAdapter
import com.jns.codelink.adapters.GroupMessageAdapter
import com.jns.codelink.models.GroupChat
import com.jns.codelink.models.Message

class ChatActivity(var isGroup:Int=0) : AppCompatActivity() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var chatHeader: TextView
    private lateinit var chatMessageAdapter: ChatMessageAdapter
    private lateinit var groupMessageAdapter: GroupMessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference
    private lateinit var exit_icon:ImageView

    var receiverRoom: String? = null
    var senderRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sentButton)
        exit_icon = findViewById(R.id.ivChatBack)
        mDbRef = FirebaseDatabase.getInstance().getReference()

        exit_icon.setOnClickListener {
            finish()
        }

        chatHeader = findViewById(R.id.chat_header)
        messageList = ArrayList()
        val senderUid =
            FirebaseAuth.getInstance().currentUser?.uid

        isGroup=intent.getIntExtra("isGroup",0)


        if (isGroup == 0) {

            val name = intent.getStringExtra("name")
            val receiverUid = intent.getStringExtra("uid")
             //get the uid of the current user

            senderRoom = receiverUid + senderUid
            receiverRoom = senderUid + receiverUid

            chatHeader.text = name

            chatMessageAdapter = ChatMessageAdapter(this, messageList)


            val layoutManager = LinearLayoutManager(this)
            layoutManager.stackFromEnd = true
            chatRecyclerView.layoutManager = layoutManager
            chatRecyclerView.adapter = chatMessageAdapter

            // logic for adding data to recyclerView
            mDbRef.child("chats").child(senderRoom!!).child("messages")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        messageList.clear()
                        for (postSnapshot in snapshot.children) {

                            val message = postSnapshot.getValue(Message::class.java)
                            messageList.add(message!!)

                        }
                        chatMessageAdapter.notifyDataSetChanged()

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })


            //adding message to database
            sendButton.setOnClickListener {

                val message = messageBox.text.toString()
                val messageObject = Message(message, senderUid)

                mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                    .setValue(messageObject).addOnSuccessListener {
                        mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                            .setValue(messageObject)
                    }
                messageBox.setText("")
            }

        }
        else if(isGroup==1)
        {
            val id=intent.getStringExtra("id")

            mDbRef.child("GroupChat").child(id.toString()).get().addOnSuccessListener {

                var groupId=""
                var groupName=""
                var owner=""
                val users=HashMap<String,String>()
                it.children.forEach {
                    if(it.key.toString().equals("groupId"))
                        groupId=it.value.toString()
                    else if(it.key.toString().equals("groupName"))
                        groupName=it.value.toString()
                    else if(it.key.toString().equals("owner"))
                        owner=it.value.toString()
                    else if(it.key.toString().equals("users"))
                    {
                        it.children.forEach {
                            users[it.key.toString()]=it.value.toString()
                        }
                    }

                }
                val group=GroupChat(groupId,groupName,owner,users)

                chatHeader.text=group.groupName

                groupMessageAdapter = GroupMessageAdapter(this, messageList)


                val layoutManager = LinearLayoutManager(this)
                layoutManager.stackFromEnd = true
                chatRecyclerView.layoutManager = layoutManager
                chatRecyclerView.adapter = groupMessageAdapter

                // logic for adding data to recyclerView
                mDbRef.child("GroupChat").child(group.groupId).child("messages")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            messageList.clear()
                            for (postSnapshot in snapshot.children) {

                                val message = postSnapshot.getValue(Message::class.java)
                                messageList.add(message!!)

                            }
                            groupMessageAdapter.notifyDataSetChanged()

                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })

                sendButton.setOnClickListener {

                    val message = messageBox.text.toString()
                    val messageObject = Message(message, senderUid)

                    mDbRef.child("GroupChat").child(group.groupId).child("messages").push()
                        .setValue(messageObject)
                    messageBox.setText("")
                }

            }


        }
    }
}