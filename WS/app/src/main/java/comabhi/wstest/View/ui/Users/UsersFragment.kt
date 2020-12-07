package comabhi.wstest.View.ui.Users

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import comabhi.wstest.Adapter.UserListAdapter
import comabhi.wstest.Model.ResponseModel.UsersListResponse
import comabhi.wstest.View.DetailsActivity
import comabhi.wstest.ViewModel.UsersViewModel
import comabhi.wstest.databinding.FragmentHomeBinding


class
UsersFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var  binding: FragmentHomeBinding
    lateinit var  userListAdapter: UserListAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        usersViewModel =
                ViewModelProvider(this).get(UsersViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun callApi(context: Context){
        usersViewModel.UserListVmCall(2).observe(requireActivity(), Observer {

            if(it!=null){

                if(it.data!!.size>0){
                    initRecycleView(it.data!!,context)
                }

            }
        })
    }


    @SuppressLint("WrongConstant")
    fun initRecycleView(list : ArrayList<UsersListResponse>, context: Context){

        binding.userListing.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        userListAdapter = UserListAdapter(context, list)
        binding.userListing.adapter = userListAdapter

    }


    override fun onResume() {
        callApi(requireActivity())
        super.onResume()
    }
}