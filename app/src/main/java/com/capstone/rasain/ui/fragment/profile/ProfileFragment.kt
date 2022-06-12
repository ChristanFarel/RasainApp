package com.capstone.rasain.ui.fragment.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ProfileFragmentBinding
import com.facebook.shimmer.ShimmerFrameLayout

@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var scroll: ScrollView
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var userId: String
    private lateinit var userName: String
    private lateinit var pass: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shimmer = binding.shimmerLayout
        shimmer.startShimmer()
        scroll = binding.scrollView2

        profileViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        )[ProfileViewModel::class.java]

        profileViewModel.getToken().observe(viewLifecycleOwner) { user ->
            profileViewModel.getUser(user.userId).first.observe(viewLifecycleOwner) {
                when(it){
                    is Result.Loading -> {
                        shimmer.visibility = View.VISIBLE
                        scroll.visibility = View.GONE
                    }
                    is Result.Success -> {
                        shimmer.stopShimmer()
                        shimmer.visibility = View.GONE
                        scroll.visibility = View.VISIBLE
                    }
                    is Result.Error -> Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
            }

            profileViewModel.getUser(user.userId).second.observe(viewLifecycleOwner){
                userId = it.data.user.userId
                userName = it.data.user.fullName
                pass = "********"

                binding.txtNameInProfile.text = userName
                binding.txtPassInProfile.text = pass
           }

            binding.btnEditName.setOnClickListener {
                editName(user.userId, user.token)
            }

            binding.btnEditPass.setOnClickListener {
                editPass(user.userId, user.token)
            }

        }

    }

    @SuppressLint("InflateParams")
    private fun editName(userId: String, token: String){
        var newName = ""
        val alertEditName = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.edit_name, null)
        with(alertEditName) {
            setTitle("Edit Your Name")
            setMessage("Fill your name")
            val input = view.findViewById<EditText>(R.id.edtName)
            input.inputType = InputType.TYPE_CLASS_TEXT
            setView(view)

            setPositiveButton("Yes") { _, _ ->
                newName += input.text.toString()
                Log.d("input 1", newName)
                profileViewModel.editUser(userId = userId, token = token, pass = null, name = newName, email = null).observe(viewLifecycleOwner){
                    binding.txtNameInProfile.text = it.data.user.fullName
                }
            }
            setNegativeButton("No") { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertEditName.create()
        val window = alertDialog.window
        val wlp = window?.attributes
        wlp?.gravity = Gravity.BOTTOM
        alertDialog.show()
    }

    @SuppressLint("InflateParams")
    private fun editPass(userId: String, token: String) {
        var newPass = ""
        val alertEditPass = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.edit_password, null)
        with(alertEditPass) {
            setTitle("Edit Your Password")
            setMessage("Fill your password")
            val input = view.findViewById<EditText>(R.id.edtPassword)
            input.transformationMethod = PasswordTransformationMethod.getInstance()
            setView(view)

            setPositiveButton("Yes") { _, _ ->
                newPass += input.text.toString()
                Log.d("PASSWORD BARU", newPass)
                profileViewModel.editUser(userId, token, newPass,null,null).observe(viewLifecycleOwner){
                    binding.txtNameInProfile.text = it.data.user.fullName
                }
            }
            setNegativeButton("No") { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertEditPass.create()
        val window = alertDialog.window
        val wlp = window?.attributes
        wlp?.gravity = Gravity.BOTTOM
        alertDialog.show()
    }
}