package com.egl.music.musicsearcher.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SongsReturnable {
    String songTitle;


    String Artist;


    String beats;

    String keys;

    String times;


    public SongsReturnable(String newSongTitle, String newArtist, String allBeats, String allKeys, String allTimes) {
        songTitle=newSongTitle;
        Artist=newArtist;
        beats=allBeats;
        keys=allKeys;
        times=allTimes;
    }
}
