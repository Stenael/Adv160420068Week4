package com.example.adv160420068week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.adv160420068week4.R
import com.example.adv160420068week4.databinding.FragmentStudentDetailBinding
import com.example.adv160420068week4.databinding.StudentListItemBinding
import com.example.adv160420068week4.viewModel.DetailViewModel
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class StudentDetailFragment : Fragment() {

    private lateinit var viewModel:DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id =""
        arguments.let{
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            id = "$studentID"
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)

        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
        })
//        val id = view?.findViewById<TextInputEditText>(R.id.txtID)
//        val name = view?.findViewById<TextInputEditText>(R.id.txtName)
//        val BOD = view?.findViewById<TextInputEditText>(R.id.txBod)
//        val phone = view?.findViewById<TextInputEditText>(R.id.txtPhone)
//        val photoUrl = view?.findViewById<ImageView>(R.id.imageView2)
//
//        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            id?.setText(viewModel.studentLD.value?.id)
//            name?.setText(viewModel.studentLD.value?.name)
//            BOD?.setText(viewModel.studentLD.value?.bod)
//            phone?.setText(viewModel.studentLD.value?.phone)
//            photoUrl?.loadImage(viewModel.studentLD.value?.photoUrl)
//        })
    }


    fun ImageView.loadImage(url: String?){
        Picasso.get()
            .load(url)
            .resize(400, 400)
            .centerCrop()
            .error(R.drawable.ic_baseline_error_24)
            .into(this)
    }

}