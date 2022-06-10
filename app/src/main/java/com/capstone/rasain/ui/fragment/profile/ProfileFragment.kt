package com.capstone.rasain.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var profileViewModel: ProfileViewModel
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
        profileViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        )[ProfileViewModel::class.java]

        profileViewModel.getToken().observe(viewLifecycleOwner){
           profileViewModel.getUser(it.userId).observe(viewLifecycleOwner){
               userId = it.data.user.userId
               userName = it.data.user.fullName
               pass = "********"

               binding.txtNameInProfile.text = userName
               binding.txtPassInProfile.text = pass
           }

            binding.btnEditName.setOnClickListener { view->

                var newName: String = ""
                val alertLogout = android.app.AlertDialog.Builder(requireContext())
                val input = EditText(requireContext())
                with(alertLogout) {
                    setTitle("Edit Your Name")
                    setMessage("Fill your name")
                    input.setHint("name")
                    input.inputType = InputType.TYPE_CLASS_TEXT
                    setView(input)

                    setPositiveButton("Yes") { dialog, which ->
                        newName += input.text.toString()
                        Log.d("input 1", newName)
                        profileViewModel.editUser(it.userId, it.token, null,name = newName,null).observe(viewLifecycleOwner){
                            binding.txtNameInProfile.text = it.data.user.fullName
                        }
                    }
                    setNegativeButton("No") { dialog, _ -> dialog.cancel() }
                }
                val alertDialog = alertLogout.create()
                alertDialog.show()

            }

            binding.btnEditPass.setOnClickListener { view ->
                var newPass: String = ""
                val alertLogout = android.app.AlertDialog.Builder(requireContext())
                val input = EditText(requireContext())
                with(alertLogout) {
                    setTitle("Edit Your Name")
                    setMessage("Fill your name")
                    input.setHint("name")
                    input.inputType = InputType.TYPE_CLASS_TEXT
                    setView(input)

                    setPositiveButton("Yes") { dialog, which ->
                        newPass += input.text.toString()
                        Log.d("input 1", newPass)
                        profileViewModel.editUser(it.userId, it.token, newPass,null,null).observe(viewLifecycleOwner){
                            binding.txtNameInProfile.text = it.data.user.fullName
                        }
                    }
                    setNegativeButton("No") { dialog, _ -> dialog.cancel() }
                }
                val alertDialog = alertLogout.create()
                alertDialog.show()
            }

        }

    }

    private fun editName(){
        var m_Text: String = ""
        val alertLogout = android.app.AlertDialog.Builder(requireContext())
        val input = EditText(requireContext())
        with(alertLogout) {
            setTitle("Edit Your Name")
            setMessage("Fill your name")
            input.setHint("name")
            input.inputType = InputType.TYPE_CLASS_TEXT
            setView(input)

            setPositiveButton("Yes") { dialog, which ->
                m_Text += input.text.toString()
                Log.d("input 1", m_Text)
                return@setPositiveButton
            }
            setNegativeButton("No") { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertLogout.create()
        alertDialog.show()

    }

}