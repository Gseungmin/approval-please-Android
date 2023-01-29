package com.umc.approval.ui.fragment.search

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.approval.R
import com.umc.approval.data.dto.approval.get.ApprovalPaper
import com.umc.approval.databinding.FragmentSearchApprovalPaperTabBinding
import com.umc.approval.ui.activity.DocumentActivity
import com.umc.approval.ui.adapter.approval_fragment.ApprovalPaperListRVAdapter
import com.umc.approval.ui.fragment.approval.ApprovalBottomSheetDialogSortFragment
import com.umc.approval.ui.fragment.approval.ApprovalBottomSheetDialogStatusFragment

class ApprovalPaperTabFragment: Fragment() {

    private var _binding : FragmentSearchApprovalPaperTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchApprovalPaperTabBinding.inflate(inflater, container, false)
        val view = binding.root

        setApprovalPaperList()  // 리사이클러뷰 데이터 & 어댑터 설정

        binding.categorySelect.setOnClickListener {
            val bottomSheetDialog = SearchBottomSheetDialogCategoryFragment()
            bottomSheetDialog.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.RoundCornerBottomSheetDialogTheme
            )
            bottomSheetDialog.show(childFragmentManager, bottomSheetDialog.tag)
        }

        binding.stateSelect.setOnClickListener {
            val bottomSheetDialog = ApprovalBottomSheetDialogStatusFragment()
            bottomSheetDialog.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.RoundCornerBottomSheetDialogTheme
            )
            bottomSheetDialog.show(childFragmentManager, bottomSheetDialog.tag)
        }

        binding.sortSelect.setOnClickListener {
            val bottomSheetDialog = ApprovalBottomSheetDialogSortFragment()
            bottomSheetDialog.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.RoundCornerBottomSheetDialogTheme
            )
            bottomSheetDialog.show(childFragmentManager, bottomSheetDialog.tag)
        }

        childFragmentManager
            .setFragmentResultListener("category", this) { requestKey, bundle ->
                val result = bundle.getString("result")
                binding.categoryText.text = result
            }

        childFragmentManager
            .setFragmentResultListener("categoryList", this) { requestKey, bundle ->
                val category = bundle.getIntegerArrayList("category")
                Log.d("로그", "카테고리 인덱스: $category")

                // 체크한 카테고리 정보(category)에 따라 리사이클러뷰 아이템 갱신
            }

        childFragmentManager
            .setFragmentResultListener("status", this) { requestKey, bundle ->
                val result = bundle.getString("result")
                binding.stateText.text = result

                // 리사이클러뷰 아이템 갱신
            }

        childFragmentManager
            .setFragmentResultListener("sort", this) { requestKey, bundle ->
                val result = bundle.getString("result")
                binding.sortText.text = result

                // 리사이클러뷰 아이템 갱신
            }

        return view
    }

    /**
     * viewBinding이 더이상 필요 없을 경우 null 처리 필요
     */
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setApprovalPaperList() {
        val approvalPaperList: ArrayList<ApprovalPaper> = arrayListOf()  // 샘플 데이터

        approvalPaperList.apply{
            add(
                ApprovalPaper(0, 0, "30분전",
                mutableListOf("https://www.backmarket.co.kr/used-refurbished/iPhone-13-Pro-128GB-Gold-Unlocked/2"),
                "아이폰 14 Pro", "새로 출시된 아이폰 골드입니다", mutableListOf("가전", "환경"),
                1000, 32, 12)
            )

            add(
                ApprovalPaper(1, 0, "30분전",
                mutableListOf(),
                "아이폰 14 Pro", "새로 출시된 아이폰 골드입니다", mutableListOf("기계", "가구"),
                1000, 32, 12)
            )

            add(
                ApprovalPaper(0, 0, "30분전",
                mutableListOf("https://www.backmarket.co.kr/used-refurbished/iPhone-13-Pro-128GB-Gold-Unlocked/2"),
                "아이폰 14 Pro", "새로 출시된 아이폰 골드입니다", mutableListOf("환경"),
                1000, 32, 12)
            )

            add(
                ApprovalPaper(1, 0, "30분전",
                mutableListOf(),
                "아이폰 14 Pro", "새로 출시된 아이폰 골드입니다", mutableListOf("기계"),
                1000, 32, 12)
            )

            add(
                ApprovalPaper(2, 0, "30분전",
                mutableListOf("https://www.backmarket.co.kr/used-refurbished/iPhone-13-Pro-128GB-Gold-Unlocked/2"),
                "아이폰 14 Pro", "새로 출시된 아이폰 골드입니다", mutableListOf("기계", "환경"),
                1000, 32, 12)
            )
        }

        val dataRVAdapter = ApprovalPaperListRVAdapter(approvalPaperList)
        val spaceDecoration = VerticalSpaceItemDecoration(40)
        binding.rvSearchResultApprovalPaper.addItemDecoration(spaceDecoration)
        binding.rvSearchResultApprovalPaper.adapter = dataRVAdapter
        binding.rvSearchResultApprovalPaper.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        // 클릭 이벤트 처리
        dataRVAdapter.setOnItemClickListener(object: ApprovalPaperListRVAdapter.OnItemClickListner {
            override fun onItemClick(v: View, data: ApprovalPaper, pos: Int) {
                startActivity(Intent(requireContext(), DocumentActivity::class.java))
            }
        })
    }

    // 아이템 간 간격 조절 기능
    inner class VerticalSpaceItemDecoration(private val height: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.bottom = height
        }
    }
}