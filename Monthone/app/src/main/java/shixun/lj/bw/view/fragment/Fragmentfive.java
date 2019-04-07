package shixun.lj.bw.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import shixun.lj.bw.R;
import shixun.lj.bw.view.activity.Addressactivity;
import shixun.lj.bw.view.activity.Loginactivity;

/*
  name:刘江
  data:2019
*/public class Fragmentfive extends Fragment {

    private TextView address;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentfive, null, false);
        ImageView imageView = view.findViewById(R.id.fimage);
        TextView name = view.findViewById(R.id.ftext1);
        address = view.findViewById(R.id.address);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("c", Context.MODE_PRIVATE);
        String nickName = sharedPreferences.getString("nickName", "");
        String headPic = sharedPreferences.getString("headPic", "");
        name.setText(nickName);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(getActivity()).load(headPic).apply(requestOptions).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Loginactivity.class));
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Addressactivity.class));
            }
        });
        return view;
    }
}
