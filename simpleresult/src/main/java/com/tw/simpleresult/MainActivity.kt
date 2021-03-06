package com.tw.simpleresult

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.tw.simpleresult.constract.CustomContract
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
        val NAME: String = "name"

    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initContracts()
    }

    @SuppressLint("SetTextI18n")
    private fun initContracts() {
        //自定义contract
        val myActivityLauncher = registerForActivityResult(CustomContract()) { result ->
            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            tv_center.text = "result is ：$result"
        }
        tv_jump.setOnClickListener { myActivityLauncher.launch("hello，second") }

        //jump activity by normal way
        val normalLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                if (activityResult.resultCode == Activity.RESULT_OK) {
                    val result = activityResult.data?.getStringExtra("result")
                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                    tv_center.text = "result is ：$result"
                }
            }
        tv_activity.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java).apply {
                putExtra(NAME, "hello，second")
            }
            normalLauncher.launch(intent)
        }

        // request one permission
        val requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) toast("read sdcard is granted")
                else toast("read sdcard is denied")
            }
        tv_permission.setOnClickListener {
            requestPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        //request  permissions
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val requestPermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
                permissions.forEach {
                    dealPermission(it.key, it.value)
                }
            }

        tv_permissions.setOnClickListener { requestPermissions.launch(permissions) }

        //take picture preview
        val picturePreview =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap ->
                iv_result.setImageBitmap(bitmap)
            }
        tv_camera_preview.setOnClickListener { picturePreview.launch(null) }
        //take picture uri
        val picPath = ApplicationUtils.getApp().filesDir.path + File.separator
        val picUri = Uri.parse(createPath(this))
        val pictureTake = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                iv_result.setImageURI(picUri)
            }
        }

//        tv_camera_preview.setOnClickListener { pictureTake.launch(picUri) }
    }

    private fun toast(content: String) {
        Toast.makeText(applicationContext, content, Toast.LENGTH_SHORT).show()
    }

    private fun dealPermission(permission: String, boolean: Boolean) {
        if (boolean) {
            toast("$permission is granted")
        } else {
            toast("have no access to $permission")
        }
    }

    /**
     * 制作图片的路径地址
     * @param context
     * @return
     */
    fun createPath(context: Context): String? {
        var path: String? = null
        var file: File? = null
        val tag = System.currentTimeMillis()
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) { //SDCard是否可用
            //最好把images替换成你的项目名称，避免有重复文件夹
            path = Environment.getExternalStorageDirectory().toString() + File.separator + "images/"
            file = File(path)
            if (!file.exists()) {
                file.mkdirs()
            }
            path = Environment.getExternalStorageDirectory()
                .toString() + File.separator + "images/" + tag + ".png"
        } else {
            path = context.filesDir.toString() + File.separator + "images/"
            file = File(path)
            if (!file.exists()) {
                file.mkdirs()
            }
            path = context.filesDir.toString() + File.separator + "images/" + tag + ".png"
        }
        return path
    }
}



