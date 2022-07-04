package com.jns.codelink.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R


public class SignupFragment : Fragment() {


    lateinit var etRegUsername: EditText
    lateinit var etRegEmail: EditText
    lateinit var etRegPassword: EditText
    lateinit var etRegConfPassword: EditText
    private lateinit var auth: FirebaseAuth;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_signup, container, false)

        etRegUsername=view.findViewById(R.id.etRegUsername)
        etRegEmail=view.findViewById(R.id.etRegEmail)
        etRegPassword=view.findViewById(R.id.etRegPassword)
        etRegConfPassword=view.findViewById(R.id.etRegConfPassword)

        auth = Firebase.auth

        etRegEmail.setOnFocusChangeListener { view, b ->
            if (!b && !etRegEmail.text.isEmpty() && etRegEmail.text.toString().contains('@'))
            {
                auth.fetchSignInMethodsForEmail(etRegEmail.text.toString())
                    .addOnCompleteListener(OnCompleteListener<SignInMethodQueryResult?> { task ->
                        val isNewUser = task.result.signInMethods!!.isEmpty()
                        if (!isNewUser) {
                            etRegEmail.error = "Email already exists"
                        }
                    })
            }
        }

        return view
    }

    public fun getDetails():MutableMap<String,Any>
    {

        val details: MutableMap<String, Any> = HashMap()
        details["username"] = etRegUsername.text.toString()
        details["email"]=etRegEmail.text.toString()
        details["password"]=etRegPassword.text.toString()
        return details
    }

    public fun validate():Boolean
    {
        var flag=0
        if(etRegUsername.text.isEmpty())
        {
            etRegUsername.error="Field empty"
            flag =1
        }
        else if(etRegEmail.text.isEmpty())
        {
            etRegEmail.error="Field empty"
            flag =1
        }
        else if(etRegPassword.text.isEmpty())
        {
            etRegPassword.error="Field empty"
            flag =1
        }
        else if(etRegPassword.text.length<6)
        {
            etRegPassword.error="Password Should be 6 Characters Long"
            flag =1
        }
        else if(!etRegConfPassword.text.toString().equals(etRegPassword.text.toString()))
        {
            etRegConfPassword.error="Doesnot Match with password"
            flag =1
        }

        return flag==0
    }



}