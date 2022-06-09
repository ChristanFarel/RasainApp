package com.capstone.rasain.ui.fragment.profile

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        profileViewModel.getUser().observe(viewLifecycleOwner){
            userId = it.userId
            userName = it.fullName
            pass = "********"

            binding.txtNameInProfile.text = userName
            binding.txtPassInProfile.text = pass

            binding.btnEdit.setOnClickListener { view->

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
                        profileViewModel.editUser(it.userId, it.token, null,name = m_Text,null).observe(viewLifecycleOwner){
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