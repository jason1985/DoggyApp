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
import com.example.doggyapp.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class EmailDialog: DialogFragment() {
    @Inject
    @Named("provideAppDatabase")
    lateinit var db: AppDatabase

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