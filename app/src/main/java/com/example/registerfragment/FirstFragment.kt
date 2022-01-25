package com.example.registerfragment
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout


class FirstFragment:Fragment(R.layout.first_fragment) {
    companion object{
        const val FULLNAME="FULLNAME"
        const val USERNAME="USERNAME"
        const val PASSWORD="PASSWORD"
        const val EMAIL="EMAIL"
        const val GENDER="GENDER"
    }
    private lateinit var fullname:TextInputLayout
    private lateinit var username:TextInputLayout
    private lateinit var email:TextInputLayout
    private lateinit var password:TextInputLayout
    private lateinit var gender:RadioGroup
    private lateinit var kindofgender:RadioButton
    private lateinit var btnregister:MaterialButton
    private lateinit var repassword:TextInputLayout
    private lateinit var male:RadioButton
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnregister=view.findViewById(R.id.btnregister)
            fullname=view.findViewById(R.id.fullname)
            username=view.findViewById(R.id.username)
            email=view.findViewById(R.id.email)
            password=view.findViewById(R.id.password)
            repassword=view.findViewById(R.id.repassword)
            gender=view.findViewById(R.id.gender)
            male=view.findViewById(R.id.male)

            fun validateFullName():Boolean{
                return  if (fullname.editText?.text?.length==0) {
                    fullname.error = "field can't be empty!!"
                    false
                }else true

            }
            fun validateUserName():Boolean{
                return if (username.editText?.text?.length==0) {
                    username.error = "field can't be empty!!"
                    false
                }else true
            }
            fun validateEmail():Boolean{
                return    if (email.editText?.text?.length==0){
                    email.error = "field can't be empty!!"
                    false
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email.editText?.text.toString()).matches()) {
                    email.error = "unvalid email address!!"
                    false
                }else true
            }
            fun validatePassword():Boolean{
                return if (password.editText?.text?.length==0) {
                    password.error = "field can't be empty!!"
                    false
                }else true
            }
            fun validateRetypePassword():Boolean{
                return if(repassword.editText?.text?.length==0){
                    repassword.error = "field can't be empty!!"
                    false
                }else if(repassword.editText?.text.toString() != password.editText?.text.toString()){
                    repassword.error = "mismatch!!"
                    false
                }else true
            }
            fun validateGender():Boolean{
                return if ( gender.checkedRadioButtonId == -1) {
                   male.error="Select Item"
                    false
                }else true
            }
        btnregister.setOnClickListener {
            if (!validateFullName() || !validateUserName() ||  !validateEmail() || !validatePassword() || !validateRetypePassword() || !validateGender()){
                return@setOnClickListener
            }else {
                val selectedOption: Int = gender.checkedRadioButtonId
                kindofgender= view.findViewById(selectedOption)

                val bundle = bundleOf(
                    FULLNAME to fullname.editText?.text.toString(),
                    USERNAME to username.editText?.text.toString(),
                    GENDER to kindofgender.text.toString(),
                    EMAIL to email.editText?.text.toString(),
                    PASSWORD to password.editText?.text.toString()
                )
                val secondfragment = SecondFragment()
                secondfragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.maincontainer, secondfragment)
                    ?.commit()
            }

        }
    }

}