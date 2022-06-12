package com.capstone.rasain.ui.fragment.profile

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.ScrollView
import androidx.constraintlayout.widget.Constraints
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ProfileFragmentBinding
import com.facebook.shimmer.ShimmerFrameLayout


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

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
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

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
                }
            }

            profileViewModel.getUser(user.userId).second.observe(viewLifecycleOwner){
                userId = it.data.user.userId
                userName = it.data.user.fullName
                pass = "********"

                binding.txtNameInProfile.text = userName
                binding.txtPassInProfile.text = pass
           }

            binding.btnEditName.setOnClickListener { view ->
                editName(user.userId, user.token)
            }

            binding.btnEditPass.setOnClickListener { view ->
                editPass(user.userId, user.token)
            }

        }

    }

    private fun editName(userId: String, token: String){
        var newName: String = ""
        val alertEditName = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.edit_name, null)
        with(alertEditName) {
            setTitle("Edit Your Name")
            setMessage("Fill your name")
            val input = view.findViewById<EditText>(R.id.edtName)
            input.inputType = InputType.TYPE_CLASS_TEXT
            setView(view)

            setPositiveButton("Yes") { dialog, which ->
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

    private fun editPass(userId: String, token: String) {
        var newPass: String = ""
        val alertEditPass = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.edit_password, null)
        with(alertEditPass) {
            setTitle("Edit Your Password")
            setMessage("Fill your password")
            val input = view.findViewById<EditText>(R.id.edtPassword)
            input.transformationMethod = PasswordTransformationMethod.getInstance()
            setView(view)

            setPositiveButton("Yes") { dialog, which ->
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