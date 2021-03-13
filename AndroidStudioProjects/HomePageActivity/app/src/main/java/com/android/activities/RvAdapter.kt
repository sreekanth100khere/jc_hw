package com.android.activities

import android.app.Activity
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


class RvAdapter(context:Context, iCountryList:ArrayList<RetroResponse>) :
    RecyclerView.Adapter<RvAdapter.CountryRowVh>() {
    private  var mCountryList: ArrayList<RetroResponse> = iCountryList
    private var context: Context? = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRowVh {
        context = parent.context
        return CountryRowVh(LayoutInflater.from(context).inflate(R.layout.row_country, null))
    }

    override fun onBindViewHolder(holder: CountryRowVh, position: Int) {
        val country:String = mCountryList.get(position).name!!
        val population:Int               = mCountryList.get(position).population
        val capital: String?             =  mCountryList.get(position).capital
        val region:String?               = mCountryList.get(position).region
        val flag:String?                            =  mCountryList.get(position).flag
        holder.countryNameTv.text = country
        holder.mRowParentLl.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"List row clicked "+mCountryList.get(position).name,Toast.LENGTH_LONG).show()

            gotoDetails(country,capital,region,population,flag)
            var activity:Activity =  context as Activity
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

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

    fun gotoDetails(iCountryName:String?,iCapital:String?,iRegion:String?,iPopulation:Int?,iFlag:String?){
        val myIntent = Intent(context, DetailsActivity::class.java)
        myIntent.putExtra("countryName",iCountryName)
        myIntent.putExtra("capital",iCapital)
        myIntent.putExtra("region",iRegion)
        myIntent.putExtra("population",iPopulation)
        myIntent.putExtra("flag",iFlag)
        context?.startActivity(myIntent)
    }

}