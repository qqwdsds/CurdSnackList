package com.example.curdsnacklist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.curdsnacklist.CurdSnack
import com.example.curdsnacklist.R
import com.example.curdsnacklist.databinding.FragmentCurdSnackInfoBinding

class CurdSnackInfoFragment : Fragment(R.layout.fragment_curd_snack_info) {

    private lateinit var binding: FragmentCurdSnackInfoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCurdSnackInfoBinding.bind(view)

        var curdSnack =
            CurdSnackInfoFragmentArgs.fromBundle(requireArguments()).curdSnack

        if(curdSnack == null){
            curdSnack = CurdSnack(
                "Ожина",
                "26%",
                "36",
                "30",
                "поліпропілен",
                "+2/+6/-18/-22",
                "14/30/180",
                "4820004238706",
                "https://voloshkovepole.com.ua/wp-content/uploads/2021/01/surokozhina.jpg")


        }

        Glide.with(binding.root)
                .load(curdSnack.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageCurdSnack)

        with(binding) {
            textAmount.text = curdSnack.amount
            textSpecies.text = curdSnack.species
            textFats.text = curdSnack.fats
            textWeight.text = curdSnack.weight
            textTypeOfPackaging.text = curdSnack.type_of_packaging
            textStorageConditions.text = curdSnack.storage_conditions
            textExpirationDate.text = curdSnack.expiration_date
            textBarCode.text = curdSnack.bar_code
        }
    }
}