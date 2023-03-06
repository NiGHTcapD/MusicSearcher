package com.egl.music.musicsearcher.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "BPM")
@Entity
public class BPM {
    @Id
    @Column
    @GeneratedValue
    int IDgnorable;

    //@Id
    @Column
    int beatsPerMinute;

    @Column(length = 100)
    String songName;

    @Column
    int songId;

    public BPM(int beats, String songName, int id) {
        this.beatsPerMinute=beats;
        this.songName=songName;
        this.songId=id;
    }


    public static List<Integer> findByBPM(int bpm) {//helper function
        return null;
    }

    public static List<Integer> findBPMBySongId(int songId) {//helper function
        return null;
    }

    /*public BPM(int newBeats, String newSong, int newID) {
        beatsPerMinute=newBeats;
        songName=newSong;
        songId=newID;
    }*/
}
