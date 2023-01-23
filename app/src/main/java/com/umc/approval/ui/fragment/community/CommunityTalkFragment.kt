package com.umc.approval.ui.fragment.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.approval.data.dto.community.get.CommunityTok
import com.umc.approval.data.dto.opengraph.OpenGraphDto
import com.umc.approval.databinding.FragmentCommunityTalkBinding
import com.umc.approval.ui.adapter.community_fragment.CommunityTalkItemRVAdapter

class CommunityTalkFragment : Fragment() {

    private var _binding : FragmentCommunityTalkBinding? = null
    private val binding get() = _binding!!

    //Community Image RV Adapter
    private lateinit var communityTalkItemRVAdapter: CommunityTalkItemRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommunityTalkBinding.inflate(inflater, container, false)
        val view = binding.root

        connect_to_community_rv()

        return view
    }

    private fun connect_to_community_rv() {
        val init_data = mutableListOf<CommunityTok>()

        var openGraphDto = OpenGraphDto(
            "https://www.naver.com/",
            "네이버",
            "네이버",
            "네이버",
            "https://s.pstatic.net/static/www/mobile/edit/2016/0705/mobile_212852414260.png"
        )
        init_data.add(
            CommunityTok
                (
                "", "", "", "", false, 0, "", mutableListOf(),
                openGraphDto, 0, 0, 0, 0, "")
        )
        init_data.add(
            CommunityTok
                (
                "", "", "", "", false, 0, "", mutableListOf(),
                openGraphDto, 0, 0, 0, 0, "")
        )

        communityTalkItemRVAdapter = CommunityTalkItemRVAdapter(init_data)

        val community_item_rv: RecyclerView = binding.communityRvItem

        community_item_rv.adapter = communityTalkItemRVAdapter
        community_item_rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    /**
     * viewBinding이 더이상 필요 없을 경우 null 처리 필요
     */
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}