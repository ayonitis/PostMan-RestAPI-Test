package com.creativeinstitute.platzistore.ui.upload

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import com.creativeinstitute.platzistore.base.BaseFragment
import com.creativeinstitute.platzistore.upload.UploadViewModel
import com.creativeitinstitute.platzi2401.databinding.FragmentFileUploadBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class FileUploadFragment : BaseFragment<FragmentFileUploadBinding>(FragmentFileUploadBinding::inflate) {

    private val viewModel: UploadViewModel by viewModels()

    private var fileUri : Uri? = null


    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                binding.imagePreview.setImageURI(fileUri)

                binding.uploadButton.visibility = View.VISIBLE
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                binding.uploadButton.visibility = View.INVISIBLE
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                binding.uploadButton.visibility = View.INVISIBLE
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.uploadResponse.observe(viewLifecycleOwner) {
            if(it.isSuccessful){
                it.body()?.location?.let{it1 ->
                    binding.secondImagePreview.load(it1)
                }
            }
        }



        binding.selectImageButton.setOnClickListener {
            ImagePicker.with(this)
                .compress(512)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(512, 512)  //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

        //Upload Button
        binding.uploadButton.setOnClickListener {
            fileUri?.let{ fileUri->
                uploadFile(fileUri)
            }
        }
    }

    //Upload File
    private fun uploadFile(fileUri: Uri) {

        val fileDir = requireActivity().filesDir
        val file = File(fileDir, "user_${System.currentTimeMillis()}.png")

        val inputStream = requireActivity().contentResolver.openInputStream(fileUri)

        val outputStream = FileOutputStream(file)

        inputStream?.copyTo(outputStream)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val part = MultipartBody.Part.createFormData("file", file.name, requestBody)
        viewModel.upload(part)

        inputStream?.close()
        outputStream.close()

    }
}