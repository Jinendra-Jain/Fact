package com.jinendra.fact;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicComponent {
    public static MediaPlayer mediaPlayer;
    public static boolean shouldPlayCategoryActivity = false;
    public static boolean shouldPlayAngerActivity = false;
    public static boolean shouldPlayAngerFactListActivity = false;
    public static boolean shouldPlayAngerFactActivity = false;
    public static boolean shouldPlaySettingsActivity = false;
    public static boolean shouldPlayCustomizeActivity = false;
    public static boolean shouldPlayWallpaperActivity = false;
    public static boolean shouldPlaySolidColorsActivity = false;
    public static boolean shouldPlayAboutActivity = false;
    public static boolean shouldPlayAnimalsFactListActivity = false;
    public static boolean shouldPlayAnimalsFactActivity = false;
    public static boolean shouldPlayAmusingFactListActivity = false;
    public static boolean shouldPlayAmusingFactActivity = false;
    public static boolean shouldPlayAppealingFactListActivity = false;
    public static boolean shouldPlayAppealingFactActivity = false;
    public static boolean shouldPlayCaptivatingFactListActivity = false;
    public static boolean shouldPlayCaptivatingFactActivity = false;
    public static boolean shouldPlayCompellingFactListActivity = false;
    public static boolean shouldPlayCompellingFactActivity = false;
    public static boolean shouldPlayEngagingFactListActivity = false;
    public static boolean shouldPlayEngagingFactActivity = false;
    public static boolean shouldPlayEngrossingFactListActivity = false;
    public static boolean shouldPlayEngrossingFactActivity = false;
    public static boolean shouldPlayEntertainingFactListActivity = false;
    public static boolean shouldPlayEntertainingFactActivity = false;
    public static boolean shouldPlayEnthrallingFactListActivity = false;
    public static boolean shouldPlayEnthrallingFactActivity = false;
    public static boolean shouldPlayFascinatingFactListActivity = false;
    public static boolean shouldPlayFascinatingFactActivity = false;
    public static boolean shouldPlaySpellbindingFactListActivity = false;
    public static boolean shouldPlaySpellbindingFactActivity = false;

    public static void onCompletion(MediaPlayer mpi) {
        mpi.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    public static void BackgroundMusic(Context context, int raw_music_id) {
        mediaPlayer = MediaPlayer.create(context, raw_music_id);
    }
}
