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

class EmailDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

        val rootView: View = inflater.inflate(R.layout.email_dialog,container,false)
        val btnEmail = rootView.findViewById<Button>(R.id.btn_email)
        val etEmail = rootView.findViewById<EditText>(R.id.et_email)

        btnEmail.setOnClickListener {


            var emailString = "Links to pictures of cute dogs:\n\n"
            val favArr = MyApplication.getFavList()

            for(i in favArr){
                emailString += "$i\n"
            }

            val emailArr = arrayOf<String>(etEmail.text.toString())

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL,emailArr)
                putExtra(Intent.EXTRA_SUBJECT,"Checkout these cute dogs!")
                putExtra(Intent.EXTRA_TEXT,emailString)
            }

//            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
//            }
        }

        return rootView
    }

}