package com.example.doggyapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.room.Room
import com.example.doggyapp.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EmailDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.email_dialog,container,false)
        val btnEmail = rootView.findViewById<Button>(R.id.btn_email)
        val etEmail = rootView.findViewById<EditText>(R.id.et_email)

        btnEmail.setOnClickListener {
            var emailString = "Links to pictures of cute dogs:\n\n"

            val db = Room.databaseBuilder(
                this.requireContext().applicationContext,
                AppDatabase::class.java, "db"
            ).build()

            CoroutineScope(IO).launch {
                val favArr = db.favoriteDao().getAllFavorites()

                for(i in favArr){
                    emailString += "${i.url}\n"
                }
                emailString += "\nSent from DoggyApp"

                val emailArr = arrayOf<String>(etEmail.text.toString())

                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL,emailArr)
                    putExtra(Intent.EXTRA_SUBJECT,"Checkout these cute dogs!")
                    putExtra(Intent.EXTRA_TEXT,emailString)
                }

                startActivity(intent)
            }
        }
        return rootView
    }
}