package com.example.androidonetask.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.AppState
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.MusicAdapter
import com.example.androidonetask.presentation.utils.TrackMapper

class WorkFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var handlerThread = HandlerThread(HANDLER_NAME)
    private var adapter = MusicAdapter(
        listenerAlbumImage = ::onClickView,
        listenerPosition = {}
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

        binding.progressBar.isVisible = true

        initRecyclerView()
        clickViewError()

        handlerThread.start()
        val looper = handlerThread.looper
        val handler = Handler(looper)
        handler.post {
            handleApi()
        }
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun clickViewError() {
        binding.imageRepeatRequest.setOnClickListener {
            val id = findNavController().currentDestination?.id
            if (id != null) {
                findNavController().popBackStack(id, true)
            }
            if (id != null) {
                findNavController().navigate(id)
            }
        }
    }

    private fun handleApi() {
        when (val response = Repository.getTracks()) {
            is AppState.Success -> {
                val data = response.data
                val list = TrackMapper.buildFrom(data)
                binding.root.post {
                    adapter.updateList(list)
                    binding.progressBar.isGone = true
                }
            }
            is AppState.ServerError -> {
                binding.root.post {
                    showDataFetchError()
                }
            }
            else -> {
                binding.root.post {
                    binding.textViewError.isInvisible = true
                    binding.imageRepeatRequest.isInvisible = true
                }
            }
        }
    }

    private fun showDataFetchError() {
        with(binding) {
            progressBar.isGone = true
            recView.isGone = true
            textViewError.isVisible = true
            textViewError.isClickable = true
            imageRepeatRequest.isVisible = true
        }
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    override fun onDestroyView() {
        _binding = null
        handlerThread.quit()
        super.onDestroyView()
    }

    companion object {
        private const val HANDLER_NAME = "WorkHandler"
    }
}

