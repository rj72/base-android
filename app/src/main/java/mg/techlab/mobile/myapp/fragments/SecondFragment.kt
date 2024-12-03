package mg.techlab.mobile.myapp.fragments

import android.Manifest
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import mg.techlab.mobile.myapp.MainActivity
import mg.techlab.mobile.myapp.R
import mg.techlab.mobile.myapp.datamanager.PersonManager
import java.io.File


class SecondFragment : Fragment() {
    private lateinit var nameView: TextView
    private lateinit var imageView: ImageView
    private lateinit var photoUri: Uri
    private var takePictureLauncher: ActivityResultLauncher<Uri>? = null
    private var selectGalleryLauncher: ActivityResultLauncher<PickVisualMediaRequest>? = null

    private val cameraPermissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (!it) (context as MainActivity).showToast("The camera permission is necessary")
        }

    private val galleryPermissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) openGallery() else (context as MainActivity).showToast("read gallery permission is necessary")
        }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_second, container, false)

        nameView = view.findViewById(R.id.txt_view_data)
        imageView = view.findViewById(R.id.img_captured)
        //nameView.text = arguments?.getString("person")
        val name = "Pers1"


        nameView.text = PersonManager.findByName("Pers1").toString()

        cameraPermissionResult.launch(Manifest.permission.CAMERA)
        galleryPermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE)


        view.findViewById<Button>(R.id.btn_camera).setOnClickListener {
            launchCamera()
        }
        view.findViewById<Button>(R.id.btn_gallery).setOnClickListener {
            openGallery()
        }

        takePictureLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) {
                if (it) {
                    Picasso.get().load(photoUri).resize(100, 100).
                    centerCrop().into(imageView);
                    //imageView.setImageURI(photoUri)
                }
            }

        selectGalleryLauncher =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
                imageView.setImageURI(it)
            }

        view.findViewById<Button>(R.id.btn_api).setOnClickListener {
            pushFragment(ThirdFragment())
        }

        return view
    }

    private fun launchCamera() {
        // Create a temporary file for the photo
        val photoFile = File.createTempFile(
            "photo_",
            ".jpg",
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        )


        // Get the URI for the file using FileProvider
        photoUri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )
        takePictureLauncher?.launch(photoUri)
    }

    private fun openGallery() {
        selectGalleryLauncher?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun pushFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }


}