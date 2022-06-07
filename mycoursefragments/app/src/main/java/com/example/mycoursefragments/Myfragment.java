package com.example.mycoursefragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.mycoursefragments.data.Course;
import com.example.mycoursefragments.data.CourseArrayAdapter;
import com.example.mycoursefragments.data.CourseData;

import java.util.List;

public class Myfragment extends ListFragment {
    List<Course> courses = new CourseData().courseList();
    public Myfragment(){


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CourseArrayAdapter adapter = new CourseArrayAdapter(getActivity(),
                R.layout.coarse_listitem, courses);
        setListAdapter(adapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.myfragment_main,container,false);
        return view;
    }
}
