package com.creativeinstitute.platzistore.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creativeinstitute.platzistore.R
import com.creativeinstitute.platzistore.base.BaseFragment
import com.creativeinstitute.platzistore.data.models.login.RequestLogin
import com.creativeinstitute.platzistore.databinding.FragmentLoginBinding
import com.creativeinstitute.platzistore.utils.Keys
import com.creativeinstitute.platzistore.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var prefManager: PrefManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Observe ViewModel login response
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                // Save tokens to SharedPreferences
                prefManager.setPref(Keys.ACCESS_TOKEN, it.body()?.access_token!!)
                prefManager.setPref(Keys.REFRESH_TOKEN, it.body()?.refresh_token!!)
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            }
        }

        fun handleLogin(email: String, password: String) {
            val requestLogin = RequestLogin(email = email, password = password)
            viewModel.login(requestLogin)
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.emailId.text.toString()
            val password = binding.loginPassId.text.toString()

            handleLogin("john@mail.com", "changeme")
//            handleLogin("ali@gmail.com", "123456")
//            handleLogin("ali123@gmail.com", "123456")

        }
    }
}