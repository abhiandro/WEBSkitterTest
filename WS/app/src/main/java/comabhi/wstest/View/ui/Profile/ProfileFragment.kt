package comabhi.wstest.View.ui.Profile

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import comabhi.wstest.ViewModel.ProfileViewModel
import comabhi.wstest.databinding.FragmentDashboardBinding
import io.paperdb.Paper

class ProfileFragment : Fragment() {

    private lateinit var dashboardViewModel: ProfileViewModel
    private lateinit var  binding: FragmentDashboardBinding
    private  var locationPermissionCode = 1
    private val imageRequestCode = 100
    lateinit var fileper : String
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding = FragmentDashboardBinding.inflate(inflater, container, false)


        binding.attachment.setOnClickListener {
            choseFile()
        }


        return binding.root
    }

   

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == imageRequestCode) {
            var imageUri = data?.data
            binding.proImage  .setImageURI(imageUri)
        }
    }

    fun choseFile(){
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, imageRequestCode)
    }
}