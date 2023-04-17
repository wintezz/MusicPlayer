package com.example.androidonetask.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.presentation.adapter.MusicAdapter
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.fragment.PostFragment.Companion.ADAPTER_POSITION
import com.example.androidonetask.presentation.utils.fillList

class NewsFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter =
        MusicAdapter(
            listenerPosition = ::onClickItemPosition,
            listenerAlbumImage = ::onClickImageView,
            listenerArtistName = ::onClickArtist
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding
            .inflate(
                inflater, container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        initRecyclerView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
        adapter.updateList(fillList())
    }

    private fun onClickItemPosition(position: Int) {
        findNavController().navigate(
            R.id.action_newsFragment_to_postFragment,
            bundleOf(ADAPTER_POSITION to position)
        )
    }

    private fun onClickImageView() {
        findNavController().navigate(R.id.action_newsFragment_to_imageViewActivity)
    }

    private fun onClickArtist() {
        findNavController().navigate(R.id.action_newsFragment_to_artistFragment)
    }
}