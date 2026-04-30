package com.example.userprofileregistration.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.userprofileregistration.Model.UserProfile
import com.example.userprofileregistration.ViewModel.UserProfileViewModel
import com.example.userprofileregistration.databinding.ActivitySingleProfileBinding
import com.example.userprofileregistration.databinding.DialogBinding

class SingleProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingleProfileBinding
    private lateinit var userViewModel: UserProfileViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySingleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getIntExtra("id", -1)
        val name = intent.getStringExtra("name") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val dob = intent.getStringExtra("dob") ?: ""
        val address = intent.getStringExtra("address") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""


        binding.apply {
            tvName.text = name
            tvEmail.text = email
            tvDate.text = dob
            tvAddress.text = address
            tvPhone.text = phone

            btndlt.setOnClickListener {
                showDeleteDialog(id, name, email, dob, address, phone)
            }



            btnEdit.setOnClickListener {
                val intent = Intent(this@SingleProfileActivity, AddProfileActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", name)
                intent.putExtra("email", email)
                intent.putExtra("dob", dob)
                intent.putExtra("address", address)
                intent.putExtra("phone", phone)
                startActivity(intent)
                finish()
            }

            back.setOnClickListener {
                finish()
            }

        }





    }

    private fun showDeleteDialog(id: Int, name: String, email: String, dob: String, address: String, phone: String) {
        val dialogBinding = DialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogBinding.btnConfirm.setOnClickListener {
            val userToDelete = UserProfile(id, name, email, dob, address, phone)

            userViewModel.deleteViewModel(userToDelete)

            dialog.dismiss()
            finish()
        }

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}