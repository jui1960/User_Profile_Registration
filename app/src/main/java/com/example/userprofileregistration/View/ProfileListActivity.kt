package com.example.userprofileregistration.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userprofileregistration.Adapter.UserProfileAdapter
import com.example.userprofileregistration.ViewModel.UserProfileViewModel
import com.example.userprofileregistration.databinding.ActivityProfileListBinding
import com.example.userprofileregistration.databinding.DialogBinding

class ProfileListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileListBinding
    private lateinit var userViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fabAdduser.setOnClickListener {
            startActivity(Intent(this, AddProfileActivity::class.java))

        }
        binding.rvEmployees.layoutManager = LinearLayoutManager(this)


        userViewModel = ViewModelProvider(

            this
        )[UserProfileViewModel::class.java]

        userViewModel.liveData.observe(this) { it ->

            binding.tvEmployeeCount.text = "${it.size}"

            val adapter = UserProfileAdapter(
                it,
                onEdit = { user ->
                    val intent = Intent(this, AddProfileActivity::class.java)

                    intent.putExtra("id", user.id)
                    intent.putExtra("name", user.name)
                    intent.putExtra("email", user.email)
                    intent.putExtra("address", user.Address)
                    intent.putExtra("dob", user.dob)
                    intent.putExtra("phone", user.number)
                    startActivity(intent)
                },
                onDelete = { data ->
                    val dialogBinding = DialogBinding.inflate(layoutInflater)
                    val dialog = AlertDialog.Builder(this)
                        .setView(dialogBinding.root)
                        .create()

                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                    dialogBinding.btnConfirm.setOnClickListener {
                        userViewModel.deleteViewModel(data)
                        dialog.dismiss()

                    }
                    dialogBinding.btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                },
                onClick = { user ->
                    val intent = Intent(this, SingleProfileActivity::class.java)
                    intent.putExtra("id", user.id)
                    intent.putExtra("name", user.name)
                    intent.putExtra("email", user.email)
                    intent.putExtra("address", user.Address)
                    intent.putExtra("dob", user.dob)
                    intent.putExtra("phone", user.number)
                    startActivity(intent)
                })

            binding.rvEmployees.adapter = adapter


        }


    }

    override fun onResume() {
        super.onResume()
        userViewModel.loadViewModel()
    }
}