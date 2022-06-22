package org.d3if1139.penghitungkalori.ui.saran

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import org.d3if1139.penghitungkalori.R
import org.d3if1139.penghitungkalori.databinding.FragmentSaranMakananBinding
import org.d3if1139.penghitungkalori.db.Kalori
import org.d3if1139.penghitungkalori.model.KategoriKalori
import org.d3if1139.penghitungkalori.model.SaranItem
import org.d3if1139.penghitungkalori.network.ApiStatus
import org.d3if1139.penghitungkalori.network.SaranApi
import org.d3if1139.penghitungkalori.ui.histori.HistoriViewModel
import org.d3if1139.penghitungkalori.ui.histori.HistoriViewModelFactory
import org.d3if1139.penghitungkalori.utils.Constan.IMAGE_BANYAK
import org.d3if1139.penghitungkalori.utils.Constan.IMAGE_SEDANG
import org.d3if1139.penghitungkalori.utils.Constan.IMAGE_SEDIKIT

class SaranFragment : Fragment(){

    private val viewModel : SaranViewModel by lazy {
        val api = SaranApi
        val factory = SaranViewModelFactory(api)
        ViewModelProvider(this, factory)[SaranViewModel::class.java]
    }
    private lateinit var myAdapter: SaranAdapter
    private lateinit var binding: FragmentSaranMakananBinding
    private val args: SaranFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myAdapter = SaranAdapter()
        binding = FragmentSaranMakananBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = myAdapter
        return binding.root
    }
    private fun updateUI(kategoriKalori: KategoriKalori){
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        viewModel.getData().observe(viewLifecycleOwner){data ->
            if(data != null){
                when (kategoriKalori){
                    KategoriKalori.SEDIKIT -> {
                        actionBar?.title = getString(R.string.judul_sedikit)
                        myAdapter.setData(data[0].saran, IMAGE_SEDIKIT)
                        myAdapter.updateData(data)
                    }
                    KategoriKalori.SEDANG -> {
                        actionBar?.title = getString(R.string.judul_sedang)
                        myAdapter.setData(data[1].saran, IMAGE_SEDANG)
                        myAdapter.updateData(data)
                    }
                    KategoriKalori.BANYAK -> {
                        actionBar?.title = getString(R.string.judul_banyak)
                        myAdapter.setData(data[2].saran,IMAGE_BANYAK)
                        myAdapter.updateData(data)
                    }
                }
            }else return@observe
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        updateUI(args.kategori)
        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProggres(it)
        })
    }
    private fun updateProggres(status: ApiStatus){
        when(status){
            ApiStatus.LOADING ->{
                binding.proggresBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCES->{
                binding.proggresBar.visibility = View.GONE
            }
            ApiStatus.FAILED ->{
                binding.proggresBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}