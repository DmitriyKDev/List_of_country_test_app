package dev.kalendula.listofcountry.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.kalendula.listofcountry.R
import dev.kalendula.listofcountry.databinding.FragmentListBinding
import dev.kalendula.listofcountry.utils.IOnItemClick
import dev.kalendula.listofcountry.utils.Resource
import dev.kalendula.listofcountry.utils.adapters.RecyclerAdapter
import dev.kalendula.listofcountry.viewmodel.SharedViewModel

@AndroidEntryPoint
class ListFragment : Fragment(), IOnItemClick {
    companion object {
        fun newInstance() = ListFragment()
    }
    private val viewModel: SharedViewModel  by activityViewModels()
    private val ra = RecyclerAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)
        val rv = binding.root.findViewById<RecyclerView>(R.id.rv_list_of_country)
        binding.viewModel = viewModel
        rv.adapter = ra
        viewModel.getLd().observe(viewLifecycleOwner, { t ->
            viewModel.isLoading.set(false)
            when(t.status){
                Resource.Status.SUCCESS -> ra.updateList(t.data!!)
                Resource.Status.ERROR -> {
                    snackMe(rv, t.message!!)
                    if (t.data != null){
                        ra.updateList(t.data)
                    }
                }
            }

        })
        return binding.root
    }

    override fun onClick(i: Int) {
        requireActivity().supportFragmentManager.commit{
            setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            replace(R.id.container, CountryFragment.newInstance(i), null)
            addToBackStack(null)
        }
    }

    private fun snackMe(v: View, error: String){
        Snackbar.make(v, "${getString(R.string.error)} $error", Snackbar.LENGTH_LONG).show()

    }
}