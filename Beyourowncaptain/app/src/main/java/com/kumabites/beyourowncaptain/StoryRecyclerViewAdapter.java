package com.kumabites.beyourowncaptain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kumabites.beyourowncaptain.Fantasy.Fantasy_Event;
import com.kumabites.beyourowncaptain.Fantasy.Fantasy_Player;
import com.kumabites.beyourowncaptain.GettingReady.Alan_Event;
import com.kumabites.beyourowncaptain.GettingReady.Alan_Player;

import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.RecyclerView;


public class StoryRecyclerViewAdapter extends RecyclerView.Adapter<StoryRecyclerViewAdapter.StoryViewHolder> {
        private String checkStoryName;
        private List<StoryModel> storyList;
        Context context;
        private Random start_health, start_attack, start_defense;


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
                    String storyTimeClass1 = "Alan's Dilemma";
                    String storyTimeClass2 = "Fantasy";
                    if(storyName.equals(storyTimeClass1)) {
                        Intent newStory = new Intent(context, Alan_Event.class);
                        Alan_Player.setCurrentEventID(1.0);
                        Story_Select.getInstance().finish();
                        context.startActivity(newStory);
                    }
                    if(storyName.equals(storyTimeClass2)) {
                        Intent newStory = new Intent(context, Fantasy_Event.class);
                        Fantasy_Player.setCurrentEventID(1.0);
                        start_health = new Random();
                        start_defense = new Random();
                        start_attack = new Random();
                        Fantasy_Player.setHeath(start_attack.nextInt(6));
                        Fantasy_Player.setDefense(start_defense.nextInt(6));
                        Fantasy_Player.setAttack(start_attack.nextInt(6));
                        Fantasy_Player.setName("Pum Pum Bear");
                        Fantasy_Player.setEnemyCheck(0);
                        Story_Select.getInstance().finish();
                        context.startActivity(newStory);
                    }
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

