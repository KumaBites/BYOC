package com.kumabites.beyourowncaptain.GettingReady;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kumabites.beyourowncaptain.R;
import com.kumabites.beyourowncaptain.StoryModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder> {

    private List<EventModel> eventList;
    Context context;

    public EventRecyclerViewAdapter(List<EventModel> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public EventRecyclerViewAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View EventModelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_event, parent, false);
        EventRecyclerViewAdapter.EventViewHolder gvh = new EventRecyclerViewAdapter.EventViewHolder(EventModelView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(EventRecyclerViewAdapter.EventViewHolder holder, final int position) {

        holder.eventChoice1.setText("Choice 1:"+(eventList.get(position).getEventChoice1()));
        holder.eventChoice2.setText("Choice 2 :"+(eventList.get(position).getEventChoice2()));
        holder.eventChoice3.setText("Choice 3 :"+(eventList.get(position).getEventChoice3()));
        holder.eventChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName1 = eventList.get(position).getEventChoice1();
                Toast.makeText(context, eventName1 + " is selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Event.class);
                context.startActivity(intent);

            }
        });
        holder.eventChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName2 = eventList.get(position).getEventChoice2();
                Toast.makeText(context, eventName2 + " is selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Event.class);
                context.startActivity(intent);
            }
        });
        holder.eventChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName3 = eventList.get(position).getEventChoice3();
                Toast.makeText(context, eventName3 + " is selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Event.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventChoice1;
        TextView eventChoice2;
        TextView eventChoice3;



        public EventViewHolder(View view) {
            super(view);
            eventChoice1= view.findViewById(R.id.choice_name1);
            eventChoice2 = view.findViewById(R.id.choice_name2);
            eventChoice3 = view.findViewById(R.id.choice_name3);
        }
    }
}

