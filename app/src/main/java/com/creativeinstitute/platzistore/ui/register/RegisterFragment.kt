package com.creativeinstitute.platzistore.ui.register

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creativeinstitute.platzistore.R
import com.creativeinstitute.platzistore.base.BaseFragment
import com.creativeinstitute.platzistore.data.models.register.RequestRegistration
import com.creativeinstitute.platzistore.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe registration response
        viewModel.registrationResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                // Log success and navigate to LoginFragment
                Log.d("RegisterFragment", "Registration successful: ${response.body()}")
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                // Log error
                Log.e("RegisterFragment", "Registration failed: ${response.errorBody()?.string()}")
            }
        }

        // Set onClickListener for the button
        binding.registerBtn.setOnClickListener {
            val name = binding.firstnameId.text.toString()
            val email = binding.emailId.text.toString()
            val password = binding.passwordId.text.toString()
            val imageAvatar = "https://media.istockphoto.com/id/637769756/photo/indian-bricks-making-worker.jpg?s=612x612&w=0&k=20&c=EyNkIge5Iqp67GbIyjGM-k3QfZYds9fQrgg5BGHrm7U="

            val registerRequest = RequestRegistration(
                avatar = imageAvatar,
                email = email,
                name = name,
                password = password
            )

            // Trigger registration API call
            viewModel.register(registerRequest)
        }
    }
}
