package com.hb.collegeprojectdemo.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hb.collegeprojectdemo.R
import com.hb.collegeprojectdemo.database.RoleType
import com.hb.collegeprojectdemo.database.model.User
import com.hb.collegeprojectdemo.databinding.FragmentLoginBinding
import com.hb.collegeprojectdemo.utils.Preference
import com.hb.collegeprojectdemo.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : Fragment() {

//    private var _binding: FragmentHomeBinding? = null
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val homeViewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Preference.init(requireContext())

        val buttonView: Button = binding.submitButton
        if(Preference.getUserLoggedIn()){
            goToHome()
        } else{
            homeViewModel.addAdmin(User(userName = "sa", password = "password",role = RoleType.ADMIN))
            setUpSignUpButton()


        }
        setUpObservers()
        setUpListeners()
        return root
    }

    private fun setUpSignUpButton() {
        binding.userNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.count() == 0) {
                    binding.nameErrorTV.text = "username cannot be empty"
                    binding.nameErrorTV.visibility = View.VISIBLE
                } else {
                    binding.nameErrorTV.text = ""
                    binding.nameErrorTV.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.count() == 0) {
                    binding.passwordErrorText.text = "password cannot be empty"
                    binding.passwordErrorText.visibility = View.VISIBLE
                } else {
                    binding.passwordErrorText.text = ""
                    binding.passwordErrorText.visibility = View.GONE
                    binding.submitButton.isEnabled = true


                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

    private fun goToHome(){
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

    }

    private fun setUpObservers() {
        homeViewModel.text.observe(viewLifecycleOwner) {
        }

        homeViewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is SignInState.Success -> {
                    binding.loginProgressBar.visibility = View.GONE
                    Preference.setUserLoggedIn(true)
                    goToHome()

                }

                is SignInState.Error -> {
                    binding.loginProgressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                        .show()


                }
                is SignInState.IsLoading -> {
                    binding.loginProgressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setUpListeners() {
      binding.submitButton.setOnClickListener{
          Utils.hideKeyboard(requireActivity())

          val userName = binding.userNameEditText.text.toString()
          val password = binding.passwordEditText.text.toString()
          val user = User(userName =  userName, password = password, role = RoleType.ADMIN)

          homeViewModel.signUp(user)

      }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}