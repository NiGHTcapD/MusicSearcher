package com.egl.music.musicsearcher.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
//@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
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

    public static Integer latestHit(String songTitle) {//helper function
        return null;
    }//Song Title only here for verification. Otherwise, return highest value.


    /*@Column
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name="student_courses",
            joinColumns = @JoinColumn(name="student_email"),
            inverseJoinColumns = @JoinColumn(name="courses_id"))
    List<Course> courses = new ArrayList<Course>();*/
}
