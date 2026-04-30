package com.example.userprofileregistration.View

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.userprofileregistration.Model.UserProfile
import com.example.userprofileregistration.ViewModel.UserProfileViewModel
import com.example.userprofileregistration.databinding.ActivityAddProfileBinding

class AddProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProfileBinding
    private lateinit var userViewModel: UserProfileViewModel
    private var noteId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.back.setOnClickListener {
            finish()
        }


        userViewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]
        noteId = intent.getIntExtra("id", -1)

        if (noteId != -1) {
            val name = intent.getStringExtra("name")
            val email = intent.getStringExtra("email")
            val dob = intent.getStringExtra("dob")
            val address = intent.getStringExtra("address")
            val phone = intent.getStringExtra("phone")

            binding.apply {
                etName.setText(name)
                etEmail.setText(email)
                etDob.setText(dob)
                etAddress.setText(address)
                etPhone.setText(phone)
            }

        }


        binding.btnSave.setOnClickListener {

            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val dob = binding.etDob.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                binding.apply {
                    etName.error = "Name Required"
                    etEmail.error = "Email Required"
                    etDob.error = "Date Of Birth Required"
                    etAddress.error = "Address Required"
                    etPhone.error = "Phone Number Required"
                    return@setOnClickListener


                }

            } else {
                binding.apply {
                    etName.error = null
                    etEmail.error = null
                    etDob.error = null
                    etAddress.error = null
                    etPhone.error = null

                }
            }

            if (noteId == -1) {
                val user = UserProfile(
                    name = name, email = email, dob = dob, Address = address, number = phone
                )
                userViewModel.insertViewModel(user)

            } else {
                val user = UserProfile(
                    id = noteId,
                    name = name,
                    email = email,
                    dob = dob,
                    Address = address,
                    number = phone
                )
                userViewModel.updateViewModel(user)


            }
            Toast.makeText(
                this, "Data save successfully", Toast.LENGTH_SHORT
            ).show()
            finish()

        }


    }
}