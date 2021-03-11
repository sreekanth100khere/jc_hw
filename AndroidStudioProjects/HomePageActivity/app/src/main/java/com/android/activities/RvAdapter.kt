package com.android.activities

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class RvAdapter(context:Context, iCountryList:ArrayList<String>) :
    RecyclerView.Adapter<RvAdapter.CountryRowVh>() {
    private  var mCountryList: ArrayList<String> = iCountryList
    private var context: Context? = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRowVh {
        context = parent.context
        return CountryRowVh(LayoutInflater.from(context).inflate(R.layout.row_country, null))
    }

    override fun onBindViewHolder(holder: CountryRowVh, position: Int) {
        val country:String = mCountryList[position]
        holder.countryNameTv.text = country
        holder.mRowParentLl.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"List row clicked "+mCountryList.get(position),Toast.LENGTH_LONG).show()

            gotoDetails(mCountryList.get(position))

        })
    }

    override fun getItemCount(): Int {
        return mCountryList.size
    }



    inner class CountryRowVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var countryNameTv: TextView
        var mRowParentLl:LinearLayout

        init {
            countryNameTv    = itemView.findViewById(R.id.id_row_country_tv)
            mRowParentLl    = itemView.findViewById(R.id.id_row_parent_ll)

        }
    }

    fun gotoDetails(countryName:String){
        val myIntent = Intent(context, DetailsActivity::class.java)
        myIntent.putExtra("countryName",countryName)
        context?.startActivity(myIntent)
    }

}