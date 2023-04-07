package com.example.androidonetask.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.activity.ImageViewActivity
import com.example.androidonetask.adapter.NewsAdapter
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.utils.ClickListener
import com.example.androidonetask.utils.RankElement

class NewsFragment : Fragment(), ClickListener {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NewsAdapter

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

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        adapter = NewsAdapter(
            RankElement.fillList(),
            this@NewsFragment
        )
        binding.recView.adapter = adapter
    }

    override fun onClickItem(position: Int) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.hostFragment, PostFragment.newInstance(ID))
            addToBackStack(null)
            commit()
        }
    }

    override fun onClickView(view: View) {
        val intent = Intent(requireContext(), ImageViewActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        private const val ID = "id_value"
    }
}
