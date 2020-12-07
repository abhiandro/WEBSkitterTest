package comabhi.wstest.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import comabhi.wstest.Model.ResponseModel.UsersListResponse
import comabhi.wstest.R
import comabhi.wstest.View.DetailsActivity
import io.paperdb.Paper
import kotlin.collections.ArrayList

class UserListAdapter (val mContext : Context, var arrayListUser : ArrayList<UsersListResponse>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>(){

    var userAdpterList =  ArrayList<UsersListResponse>()
    init {

        userAdpterList = arrayListUser
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindItem(usersListResponse: UsersListResponse, context: Context){

            var tvFirstName : TextView = itemView.findViewById(R.id.tvFirstName)
            var tvEmail : TextView = itemView.findViewById(R.id.tvEmail)
            var ivUser : ImageView = itemView.findViewById(R.id.ivUser)
            var constrainLayout : ConstraintLayout = itemView.findViewById(R.id.constrainLayout)


            Picasso.get().load(usersListResponse.avatar).into(ivUser);
            tvFirstName.setText(usersListResponse.first_name+" "+ usersListResponse.last_name)
            tvEmail.setText(usersListResponse.email)

            constrainLayout.setOnClickListener {
                Paper.book().write("Name",""+usersListResponse.first_name+" "+ usersListResponse.last_name)
                Paper.book().write("Email",""+usersListResponse.email)
                Paper.book().write("ImageUrl",""+usersListResponse.avatar)
                var intent  = Intent(context, DetailsActivity::class.java)
                context.startActivity(intent)

            }




        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        val item_view = LayoutInflater.from(mContext).inflate(R.layout.row_users,parent,false)
        return ViewHolder(item_view)
    }

    override fun getItemCount(): Int {
        return userAdpterList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(userAdpterList[position],mContext)
    }




}