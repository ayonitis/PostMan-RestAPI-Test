package com.creativeinstitute.platzistore.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil3.load
import com.creativeinstitute.platzistore.base.BaseFragment
import com.creativeinstitute.platzistore.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel : ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profileResponse.observe(viewLifecycleOwner){
            if(!it.isSuccessful) return@observe

            binding.apply {
                it.body()?.let {profile->
                    usernameTextView.text = profile.name
                    emailTextView.text = profile.email
                    roleTextView.text = profile.role

                    profileImageView.load(profile.avatar)
                    }

                }
            }

        }




    }