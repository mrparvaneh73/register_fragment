package com.example.registerfragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class SecondFragment:Fragment(R.layout.second_fragment) {
    private lateinit var showfullname:TextView
    private lateinit var showusername:TextView
    private lateinit var showgender:TextView
    private lateinit var showpassword:TextView
    private lateinit var showemail:TextView
    private lateinit var btnsave:MaterialButton
    private lateinit var sharedPreferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showfullname=view.findViewById(R.id.showfullname)
        showusername=view.findViewById(R.id.showusername)
        showgender=view.findViewById(R.id.showgender)
        showemail=view.findViewById(R.id.showemail)
        showpassword=view.findViewById(R.id.showpassword)
        btnsave=view.findViewById(R.id.btnsave)
        showfullname.text=arguments?.getString(FirstFragment.FULLNAME)
        showusername.text=arguments?.getString(FirstFragment.USERNAME)
        showgender.text=arguments?.getString(FirstFragment.GENDER)
        showpassword.text=arguments?.getString(FirstFragment.PASSWORD)
        showemail.text=arguments?.getString(FirstFragment.EMAIL)
        btnsave.setOnClickListener {
            sharedPreferences = this.activity!!.getSharedPreferences("info",Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("fullname",showfullname.text.toString()).apply()
            sharedPreferences.edit().putString("username",showusername.text.toString()).apply()
            sharedPreferences.edit().putString("email",showemail.text.toString()).apply()
            sharedPreferences.edit().putString("password",showpassword.text.toString()).apply()
            sharedPreferences.edit().putString("gender",showgender.text.toString()).apply()
            Toast.makeText(this.activity, "SAVED", Toast.LENGTH_SHORT).show()
            fragmentManager?.beginTransaction()?.replace(R.id.maincontainer, FirstFragment())?.commit()
        }


    }

}