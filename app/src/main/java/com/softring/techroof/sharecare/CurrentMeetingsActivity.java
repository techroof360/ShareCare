package com.softring.techroof.sharecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.softring.techroof.sharecare.Model.MeetingForm;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurrentMeetingsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RecyclerView postList;
    private FirebaseDatabase user_db;
    private DatabaseReference post_ref, admin_watch_post_ref, userdb_ref;
    private DatabaseReference likesRef;
    private String checker="", myUrl, userid;
    String currentUserId;
    Boolean likesChecker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_meetings);

        Toolbar toolbar =  findViewById(R.id.meeting_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Current Meetings Board");

        mAuth = FirebaseAuth.getInstance();
        user_db = FirebaseDatabase.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        userdb_ref = user_db.getReference("users");
        post_ref = user_db.getReference().child("meetings");
        admin_watch_post_ref = user_db.getReference().child("Blog Post Images");
        likesRef = user_db.getReference().child("Likes");

        postList = findViewById(R.id.all_user_posts);
        postList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        postList.setLayoutManager(linearLayoutManager);

        DisplayAllUsersPosts();
    }


    private void DisplayAllUsersPosts() {

        Query postInOrder = post_ref.orderByChild("counter");

        FirebaseRecyclerOptions<MeetingForm> options=new FirebaseRecyclerOptions.Builder<MeetingForm>().setQuery(postInOrder,MeetingForm.class).build();
        final FirebaseRecyclerAdapter<MeetingForm, PostsViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<MeetingForm, PostsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PostsViewHolder holder, int position, @NonNull MeetingForm model) {

                final String PostKey = getRef(position).getKey();

                holder.username.setText(model.getName());
                holder.time.setText(" at " +model.getTime());
                holder.date.setText(" "+model.getDate());
                //holder.p_age.setText(model.getP_age());
                holder.p_careAddress.setText(model.getP_careAddress());
                holder.p_date.setText(model.getP_date());
                holder.p_disease.setText(model.getP_disease());
                //holder.p_mobile.setText(model.getP_mobile());
                holder.p_name.setText(model.getP_name());
                holder.p_time.setText(model.getP_time());
                holder.description.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder.user_profile_image);

                holder.setLikeButtonStatus(PostKey);

                /*holder.likePost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        likesChecker = true;

                        likesRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                if (likesChecker.equals(true))
                                {
                                    if (dataSnapshot.child(PostKey).hasChild(currentUserId))
                                    {
                                        likesRef.child(PostKey).child(currentUserId).removeValue();
                                        likesChecker = false;
                                    }
                                    else
                                    {
                                        likesRef.child(PostKey).child(currentUserId).setValue(true);
                                        likesChecker = false;
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
                */
            }
            @NonNull
            @Override
            public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_form,parent,false);
                PostsViewHolder viewHolder=new PostsViewHolder(view);
                return viewHolder;
            }
        };
        firebaseRecyclerAdapter.startListening();
        postList.setAdapter(firebaseRecyclerAdapter);
    }
    public static class PostsViewHolder extends RecyclerView.ViewHolder{
        TextView username,date,time,description, p_age, p_careAddress, p_date, p_disease, p_mobile, p_name, p_time,  noOfLikes;
        CircleImageView user_profile_image;
        ImageButton likePost, commentPost;
        int countsLikes;
        String currentUserID;
        DatabaseReference LikesRef;

        public PostsViewHolder(View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.post_user_name);
            date=itemView.findViewById(R.id.post_date);
            time=itemView.findViewById(R.id.post_time);
            description=itemView.findViewById(R.id.post_description);
            p_name=itemView.findViewById(R.id.meeting_pName);
            p_disease=itemView.findViewById(R.id.meeting_pDisease);
            p_date=itemView.findViewById(R.id.meeting_pDate);
            p_time=itemView.findViewById(R.id.meeting_pTime);
            p_careAddress=itemView.findViewById(R.id.meeting_pCareAddress);
            description=itemView.findViewById(R.id.post_description);
            user_profile_image=itemView.findViewById(R.id.user_profile_image);
            //likePost = itemView.findViewById(R.id.like_btn);
            //noOfLikes = itemView.findViewById(R.id.display_no_of_likes);

            LikesRef = FirebaseDatabase.getInstance().getReference().child("Likes");
            currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        public void setLikeButtonStatus(final String PostKey)
        {
           /* LikesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (dataSnapshot.child(PostKey).hasChild(currentUserID))
                    {
                        countsLikes = (int) dataSnapshot.child(PostKey).getChildrenCount();
                        likePost.setImageResource(R.drawable.like);
                        noOfLikes.setText(Integer.toString(countsLikes)+(" Likes"));
                    }
                    else
                    {
                        countsLikes = (int) dataSnapshot.child(PostKey).getChildrenCount();
                        likePost.setImageResource(R.drawable.dislike);
                        noOfLikes.setText(Integer.toString(countsLikes)+(" Likes"));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/
        }

    }
}
