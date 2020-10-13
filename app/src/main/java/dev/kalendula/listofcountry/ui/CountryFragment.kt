package dev.kalendula.listofcountry.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.kalendula.listofcountry.databinding.FragmentCountryBinding
import dev.kalendula.listofcountry.utils.IToolbarButtonClick
import dev.kalendula.listofcountry.viewmodel.SharedViewModel

private const val ARG_PARAM1 = "countryId"

@AndroidEntryPoint
class CountryFragment : Fragment(), IToolbarButtonClick {
    companion object {
        @JvmStatic
        fun newInstance(countryId: Int) =
            CountryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, countryId)
                }
            }
    }

    private var countryId: Int? = null
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCountryBinding.inflate(inflater, container, false)
        binding.backPressed = this
        viewModel.getLd().observe(viewLifecycleOwner,{t ->
            if(countryId != null){
               binding.country = t.data!![countryId!!]
            }
        })
        return binding.root
    }

    override fun backPressed() {
        requireActivity().onBackPressed()
    }
}