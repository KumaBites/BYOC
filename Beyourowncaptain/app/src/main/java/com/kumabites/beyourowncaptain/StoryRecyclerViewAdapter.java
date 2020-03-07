package com.kumabites.beyourowncaptain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kumabites.beyourowncaptain.GettingReady.Event;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class StoryRecyclerViewAdapter extends RecyclerView.Adapter<StoryRecyclerViewAdapter.StoryViewHolder> {
        private String checkStoryName;
        private List<StoryModel> storyList;
        Context context;

        public StoryRecyclerViewAdapter(List<StoryModel> storyList, Context context) {
            this.storyList = storyList;
            this.context = context;
        }

        @Override
        public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate the layout file
            View storyModelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story, parent, false);
            StoryViewHolder gvh = new StoryViewHolder(storyModelView);
            return gvh;
        }

        @Override
        public void onBindViewHolder(StoryViewHolder holder, final int position) {

            holder.txtStory_Name.setText("Lets Begin The Tale Of: :"+(storyList.get(position).getStoryName()));


            holder.txtStory_Name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String storyName = storyList.get(position).getStoryName();
                    Toast.makeText(context, storyName + " is selected", Toast.LENGTH_SHORT).show();
                    Intent newI = new Intent(context, Event.class);

                    Player.setBreakfast(false);
                    Player.setHygiene(false);
                    Player.setClothes(false);
                    Player.setHair(false);

                    context.startActivity(newI);
                }
            });
        }
        @Override
        public int getItemCount() {
            return storyList.size();
        }

        public class StoryViewHolder extends RecyclerView.ViewHolder {
            TextView txtStory_Name;


            public StoryViewHolder(View view) {
                super(view);
                txtStory_Name= view.findViewById(R.id.story_name);

            }
        }
    }

