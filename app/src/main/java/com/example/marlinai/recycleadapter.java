package com.example.marlinai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class recycleadapter extends RecyclerView.Adapter<recycleadapter.ViewHolder> {
    Context context;

    List<ImageUploadInfo> MainImageUploadInfoList;
    public recycleadapter(Context context, List<ImageUploadInfo> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;


    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fragment, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);

        holder.tripname.setText(UploadInfo.getTripname());
        holder.date.setText(UploadInfo.getDate1());
        holder.infostuff.setText(UploadInfo.getDate2());
        holder.info.setText(findDifference(UploadInfo.getDate1(), UploadInfo.getDate2())+" days this FY");



    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tripname;
        public TextView date;
        public TextView infostuff;
        public TextView info;


        public ViewHolder(View itemView) {
            super(itemView);

            tripname = (TextView) itemView.findViewById(R.id.tripname);

            date = (TextView) itemView.findViewById(R.id.date);
            infostuff = (TextView) itemView.findViewById(R.id.infostuff);
            info = (TextView) itemView.findViewById(R.id.info);
        }
    }
    static String findDifference(String start_date,
                               String end_date)
    {
        long difference_In_Days=0;
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd-MM-yyyy");

        // Try Class
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in seconds,


            difference_In_Days
                    = TimeUnit
                    .MILLISECONDS
                    .toDays(difference_In_Time)
                    % 365;



            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds






        }

        catch (ParseException e) {
            e.printStackTrace();
        }
        String a = Integer.toString((int)difference_In_Days);

        return a;
    }

}