package com.capstone.rasain.ui.activity.upload

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ActivityUploadBinding
import com.capstone.rasain.ui.activity.scan.ScanActivity
import com.capstone.rasain.ui.activity.scan.reduceFileImage
import com.capstone.rasain.ui.activity.search.UploadResultActivity
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

        setupView()

        uploadViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[UploadViewModel::class.java]

        getFile = intent.getSerializableExtra("picture") as File
        val result = BitmapFactory.decodeFile(getFile!!.path)
        binding.prevCam.setImageBitmap(result)

        binding.btnUpload.setOnClickListener {
            upload()
        }

        binding.btnRetake.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun upload(){
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

            uploadViewModel.getToken().observe(this) { user ->
                uploadViewModel.upload(imageMultipart, user.token).observe(this) {
                    getFood = it[0].label
                    val intent = Intent(this, UploadResultActivity::class.java)
                    intent.putExtra(FOOD, getFood)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object {
        const val CAMERA_X_RESULT = 200
        const val FOOD = "FOOD"
    }
}