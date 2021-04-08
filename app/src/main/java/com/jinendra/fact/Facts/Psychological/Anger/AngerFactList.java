package com.jinendra.fact.Facts.Psychological.Anger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jinendra.fact.Categories.Anger;
import com.jinendra.fact.MusicComponent;
import com.jinendra.fact.R;

import static com.jinendra.fact.MusicComponent.mediaPlayer;

public class AngerFactList extends AppCompatActivity {

    ImageView vPreviousMenuAngerFactListActivity;

    ListView vFactNumberListViewAngerFactListActivity;
    String[] vFactNumbersAngerFactListActivity = new String[49];
    int i,j,vFactNumberAngerFactListActivity,k;
    public static final String[] extraTextAngerFactListActivity = new String[49];
    public static final String EXTRA_NUMBER_ANGER_FACT_LIST_ACTIVITY = "com.jinendra.fact.Facts.Anger.EXTRA_NUMBER";
    String[] angerFacts = {"#1 \"Wrap rage\" is the anger and frustration felt when you are unable to open packages.",
            "#2 Anger increases the desire of possession in people. People make more efforts to obtain the object that is associated with angry faces.",
            "#3 Pain alters your personality. Everybody has their own story of struggles",
            "#4 It is impossible to remain angry at someone you truly love. Anger lasting for more than three days indicates that you are not in love.",
            "#5 Kangaroos hop because they can't move their legs independently.",
            "#6 Angry people produce more unique ideas faster than people in any other type of emotional state, according to a study.",
            "#7 If you repeatedly criticize someone for liking something you don't, they won't stop liking it. They will stop liking you.",
            "#8 Owning a cat can reduce the risk of heart attacks and strokes by more than a third.",
            "#9 Anger triggers the region of the brain associated with honesty, that’s when the truth comes out.",
            "#10 Someone who becomes angry easily, over silly things, subconsciously desires to be loved.",
            "#11 If someone is always angry at you just stay calm. It will help them to be ashamed later.",
            "#12 While a very heavy pen pressure can suggest tension and anger; a moderately heavy pressure is a sign of commitment. A soft pressure means you're empathetic and sensitive.",
            "#13 Expressing anger is actually helpful, it releases tension and help people to not have grudges to anyone because they express themselves every time they get angry.",
            "#14 Pretending you don't have feelings like anger,sadness,or loneliness can mentally destroy you.",
            "#15 When two people have a fight, the angrier one tends to be wrong. Anger can cloud judgement.",
            "#16 Arguing over text is the worst way to argue because the lack of tone decreases the meaning of the words.Talk it out in person.",
            "#17 Controlling Facial Muscles Can Help Control your Anger.Studies have shown that if you don’t frown when you’re angry, you won’t feel the emotion in much intensity.",
            "#18 Anger is more than just an emotion, it actually has physiological effects that occur alongside of it. These range from racing heartbeats, sweating, and increase in blood pressure.",
            "#19 It’s not as simple as just being either angry or not – it’s a bit more complicated than that. Think of it as if on a scale. There are varying degrees of anger ranging from annoyed to rage.",
            "#20 It’s usually some outside factor that you feel leads to you becoming angry. However, there are many factors that can make you more susceptible to feeling that anger. These are things such as hunger, heat, exhaustion, dehydration, or other circumstances of annoyance.",
            "#21 One thing that has been shown to consistently combat anger is humor. Not only do most people enjoy humor, but it break the attention and stress caused by feeling angry and refocuses it on something less physiologically taxing.",
            "#22 Anger, at least in Western culture, is largely thought of as a more masculine emotion. Because of this, girls and boys are taught differing stances when it comes to handling their anger. Men tend to express their anger physically and impulsively, where women tend to be resentful and emotional.",
            "#23 We mentioned early that anger is linked to other physiological reactions. In the same bucket, uncontrolled anger and outbursts that become out of hand have been linked to side effects as severe as stroke or heart attacks.",
            "#24 Pain alone is not enough to cause anger. Anger occurs when pain is combined with some anger-triggering thought.",
            "#25 Thoughts that can trigger anger include personal assessments, assumptions, evaluations, or interpretations of situations that makes people think that someone else is attempting (consciously or not) to hurt them.",
            "#26 Anger produces more muscle tension, higher blood pressure, and a lower heart rate.",
            "#27 It is more satisfying to feel angry than to acknowledge the painful feelings associated with vulnerability.",
            "#28 You can use anger to convert feelings of vulnerability and helplessness into feelings of control and power.",
            "#29 Some people develop an unconscious habit of transforming almost all of their vulnerable feelings into anger so they can avoid having to deal with them.",
            "#30 Anger cannot make pain disappear - it only distracts you from it.",
            "#31 Anger generally does not resolve or address the problems that made you feel fearful or vulnerable in the first place, and it can create new problems, including social and health issues.",
            "#32 Whether justified or unjustified, the seductive feeling of righteousness associated with anger offers a powerful temporary boost to self-esteem.",
            "#33 Anger can also be a substitute emotion. By this we mean that sometimes people make themselves angry so that they don't have to feel pain.",
            "#34 People change their feelings of pain into anger because it feels better to be angry than it does to be in pain. This changing of pain into anger may be done consciously or unconsciously.",
            "#35 Being angry rather than simply in pain has a number of advantages, primarily among them distraction. People in pain generally think about their pain.",
            "#36 However, angry people think about harming those who have caused pain. Part of the transmutation of pain into anger involves an attention shift - from self-focus to other-focus.",
            "#37 Anger temporarily protects people from having to recognize and deal with their painful real feelings; you get to worry about getting back at the people you're angry with instead.",
            "#38 Making yourself angry can help you to hide the reality that you find a situation frightening or that you feel vulnerable.",
            "#39 Anger is a natural and mostly automatic response to pain of one form or another (physical or emotional). Anger can occur when people don't feel well, feel rejected, feel threatened, or experience some loss.",
            "#40 The type of pain does not matter; the important thing is that the pain experienced is unpleasant. Because anger never occurs in isolation but rather is necessarily preceded by pain feelings, it is often characterized as a ''secondhand'' emotion.",
            "#41 Angry people most always feel that their anger is justified. However, other people don't always agree. The social judgment of anger creates real consequences for the angry person.",
            "#42 Anger is an emotion characterized by antagonism toward someone or something you feel has deliberately done you wrong",
            "#43 Anger can be a good thing. It can give you a way to express negative feelings, for example, or motivate you to find solutions to problems.",
            "#44 Excessive anger can cause problems. Increased blood pressure and other physical changes associated with anger make it difficult to think straight and harm your physical and mental health.",
            "#45 Anger is a social emotion.You always have a target that your anger is directed against (even if that target is yourself). Feelings of pain, combined with anger-triggering thoughts motivate you to take action, face threats and defend yourself by striking out against the target you think is causing you pain.",
            "#46 The best way to deal with anger is through choiceless awareness. Basically being aware of your anger, and not judging it, but not exerting it in any way either. However if you are to express it, probably better through writing.",
            "#47 When masculinity was challenged, men reacted with more anger and with an increased endorsement of social dominance over women.",
            "#48 Men who have low levels of testosterone served as a threat to masculinity and led to engagement in more “gender stereotypical behaviors,” like getting into physical fights and anger.",
            "#49 Meanwhile, men who were told they had high testosterone levels were more likely to support gender equality and more likely to engage in stereotypically feminine behaviors, like caretaking or doing housework and  don't show agression easily."
    };

    AdView vBannerAdViewAngerFactListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anger_fact_list);

        vPreviousMenuAngerFactListActivity = findViewById(R.id.previousMenuImageViewAngerFactListActivity);
        vPreviousMenuAngerFactListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent angerIntentAngerFactListActivity = new Intent(AngerFactList.this, Anger.class);
                startActivity(angerIntentAngerFactListActivity);
                MusicComponent.shouldPlayAngerActivity = false;
                MusicComponent.shouldPlayAngerFactListActivity = true;

            }
        });

        for(i = 0; i <= 48; i++){
            vFactNumbersAngerFactListActivity[i] = "Fact #" + (1 + i);
        }

        for (j = 0; j <= 48; j++) {
            extraTextAngerFactListActivity[j] = "EXTRA_TEXT_ANGER_FACT_LIST_ACTIVITY" + j;
        }

        vFactNumberListViewAngerFactListActivity = findViewById(R.id.factNumberListViewAngerFactListActivity);

        AngerFactsAdapter angerFactsAdapterAngerFactListActivity = new AngerFactsAdapter();
        vFactNumberListViewAngerFactListActivity.setAdapter(angerFactsAdapterAngerFactListActivity);


        vFactNumberListViewAngerFactListActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AngerFactList.this,vFactNumbersAngerFactListActivity[position],Toast.LENGTH_SHORT).show();
                vFactNumberAngerFactListActivity = position;

                    Intent angerFactIntentAngerFactListActivity = new Intent(AngerFactList.this, AngerFact.class);

                    for (k = 0; k <= 48; k++) {
                        angerFactIntentAngerFactListActivity.putExtra(extraTextAngerFactListActivity[k], angerFacts[k]);
                    }

                    angerFactIntentAngerFactListActivity.putExtra(EXTRA_NUMBER_ANGER_FACT_LIST_ACTIVITY, vFactNumberAngerFactListActivity);
                    startActivity(angerFactIntentAngerFactListActivity);
                    MusicComponent.shouldPlayAngerFactListActivity = true;
                    MusicComponent.shouldPlayAngerFactActivity = false;

            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewAngerFactListActivity = findViewById(R.id.bannerAdViewAngerFactListActivity);
        AdRequest vBannerAdRequestAngerFactListActivity = new AdRequest.Builder().build();
        vBannerAdViewAngerFactListActivity.loadAd(vBannerAdRequestAngerFactListActivity);



    }



    class AngerFactsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return vFactNumbersAngerFactListActivity.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            view = View.inflate(AngerFactList.this, R.layout.anger_fact_list_layout, null);
            if (view == null) {
                view = View.inflate(AngerFactList.this,R.layout.anger_fact_list_layout, null);
            }

            TextView mTextView = view.findViewById(R.id.angerFactListTextView);

            mTextView.setText(vFactNumbersAngerFactListActivity[position]);

            return view;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferencesAngerFactListActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorAngerFactListActivity = sharedPreferencesAngerFactListActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutAngerFactListActivity = findViewById(R.id.constraintLayoutAngerFactListActivity);
        if (sharedPreferencesAngerFactListActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorAngerFactListActivity)
        {vConstraintLayoutAngerFactListActivity.setBackgroundColor(vSelectedColorAngerFactListActivity);}
        else
        {vConstraintLayoutAngerFactListActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(AngerFactList.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!MusicComponent.shouldPlayAngerFactListActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayAngerActivity = false;
            MusicComponent.shouldPlayAngerFactListActivity = true;

        }

    }
}