package com.example.arasinav;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.PersonHolder> {

    private ArrayList<Person> persons;

    public PersonRecyclerAdapter(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public void setPersons(ArrayList<Person> persons)
    {
        this.persons=persons;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_layout,viewGroup,false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder personHolder, int i) {
        personHolder.tvName.setText(persons.get(i).getName());
        personHolder.tvPhone.setText(persons.get(i).getPhone());
        personHolder.ivPhoto.setImageBitmap(persons.get(i).getPhoto());

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }


    public class PersonHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvPhone;
        ImageView ivPhoto;

        public PersonHolder(@NonNull View personLayout)
        {
            super(personLayout);
            tvName=personLayout.findViewById(R.id.tv_name);
            tvPhone=personLayout.findViewById(R.id.tv_number);
            ivPhoto=personLayout.findViewById(R.id.iv_photo);


        }

    }
}
