package com.example.afinal

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.hardware.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mCamera: Camera? = null
    private lateinit var mPreview: CameraPreview
    private lateinit var capture: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var image1 = findViewById<ImageView>(R.id.photo1)
        var image2 = findViewById<ImageView>(R.id.photo2)


        var queue : Queue<Bitmap> = LinkedList<Bitmap>().apply {
            add((image1.drawable as BitmapDrawable).bitmap)
            add((image2.drawable as BitmapDrawable).bitmap)
        }

        mCamera = getCameraInstance()
        mPreview = mCamera?.let { CameraPreview(this, it) }!!

        mPreview.also {
            val preview: FrameLayout = findViewById(R.id.preview)
            preview.addView(it)
        }

        capture = findViewById(R.id.captureButton)
        capture.setOnClickListener {
            mCamera!!.takePicture(null, null) { data, _ ->
                val originalBitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                val matrix = Matrix()
                matrix.setRotate(90f)

                val rotatedImage = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.width,
                originalBitmap.height, matrix, true)

                val pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE)
                val fos = FileOutputStream(pictureFile)

                rotatedImage.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.close()

                if (pictureFile != null) {
                    Toast.makeText(this, "Image saved: ${pictureFile.path}", Toast.LENGTH_SHORT).show()
                }
                updateImages(queue, image1, image2, pictureFile)
                mCamera!!.startPreview()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mCamera?.apply {
            stopPreview()
            setPreviewCallback(null)
            release()
            mCamera = null
        }
    }

    override fun onResume() {
        super.onResume()
        mCamera = getCameraInstance()
        mPreview = mCamera?.let { CameraPreview(this, it) }!!

        mPreview.also {
            val preview: FrameLayout = findViewById(R.id.preview)
            preview.addView(it)
        }
    }

    private fun updateImages(
        queue: Queue<Bitmap>,
        photo1: ImageView,
        photo2: ImageView,
        pictureFile: File?
    ) {
        val len : Int = queue.size
        Log.i("DEV", queue.size.toString())
        if(len == 2) {
            queue.poll()
            if (pictureFile != null) {
                queue.add(BitmapFactory.decodeFile(pictureFile.absolutePath))
            }
        } else {
            if (pictureFile != null) {
                queue.add(BitmapFactory.decodeFile(pictureFile.absolutePath))
            }
        }

        var tempQueue = LinkedList(queue)
        photo2.setImageBitmap(tempQueue.poll())
        photo1.setImageBitmap(tempQueue.poll())
    }

    private fun getOutputMediaFile(type: Int): File? {
        val mediaStorageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "MyCameraApp"
        )
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val mediaFile: File = when (type) {
            MEDIA_TYPE_IMAGE -> {
                File("${mediaStorageDir.path}${File.separator}IMG_$timeStamp.jpg")
            }
            else -> {
                return null
            }
        }

        return mediaFile
    }

    private fun getCameraInstance(): Camera? {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 1)
            return Camera.open()
        }
        return Camera.open()
    }
}
