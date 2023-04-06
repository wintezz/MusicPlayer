package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.ArtistFragmentAdapter
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.utils.RankElement

class ArtistFragment : Fragment() {

    private lateinit var binding: FragmentArtistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtistBinding
            .inflate(
                inflater, container,
                false
            )

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName
    }

    private fun initRecyclerView() {
        binding.recViewArtist.layoutManager = LinearLayoutManager(context)
        binding.recViewArtist.adapter = ArtistFragmentAdapter(RankElement.fillList())
    }
}