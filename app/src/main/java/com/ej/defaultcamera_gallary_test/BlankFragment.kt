package com.ej.defaultcamera_gallary_test

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ej.defaultcamera_gallary_test.databinding.FragmentBlankBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import java.io.IOException


class BlankFragment : Fragment() {
    lateinit var binding : FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_blank,container,false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
        showCaptureImage()
    }

    private fun showCaptureImage() {
        try {
//            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
            val inputImage = InputImage.fromBitmap(bitmap!!, 0)
            //val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
            recognizer.process(inputImage)
                .addOnSuccessListener {
                    Log.d("EJTAG", it.text)
                }
                .addOnFailureListener {
                    Log.d("EJTAG", it.message.toString())
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            BlankFragment()
    }
}