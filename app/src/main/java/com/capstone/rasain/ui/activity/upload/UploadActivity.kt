package com.capstone.rasain.ui.activity.upload

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ActivityScanBinding
import com.capstone.rasain.databinding.ActivityUploadBinding
import com.capstone.rasain.ui.activity.SearchResultActivity
import com.capstone.rasain.ui.activity.register.RegisterViewModel
import com.capstone.rasain.ui.activity.scan.reduceFileImage
import com.capstone.rasain.ui.activity.scan.rotateBitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var uploadViewModel: UploadViewModel
    private var getFile: File? = null
    private var getFood: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        uploadViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[UploadViewModel::class.java]

        getFile = intent.getSerializableExtra("picture") as File
        val result = BitmapFactory.decodeFile(getFile!!.path)
        binding.prevCam.setImageBitmap(result)

        binding.btnUpload.setOnClickListener {
            upload()
            Toast.makeText(this,"kepencet",Toast.LENGTH_SHORT).show()
        }
    }


    fun upload(){
        uploadViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[UploadViewModel::class.java]

        if (getFile != null){
            val file = reduceFileImage(getFile as File)
            val reqImg = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                file.name,
                reqImg
            )

            uploadViewModel.getToken().observe(this,{
                uploadViewModel.upload(imageMultipart, it.token).observe(this,{
                    getFood = it[0].label
                    val intent = Intent(this, SearchResultActivity::class.java)
                    intent.putExtra(FOOD, getFood)
                    startActivity(intent)
                })
            })

        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200
        const val FOOD = "FOOD"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}