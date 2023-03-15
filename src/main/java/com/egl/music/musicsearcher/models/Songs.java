package com.egl.music.musicsearcher.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "songs")
@Entity
public class Songs {
    @Id
    @Column
    @GeneratedValue
    int id;

    @Column(length = 100)
    String songTitle;

    @Column(length = 100)
    String Artist;


    public Songs(String newSongTitle, String newArtist) {
        songTitle=newSongTitle;
        Artist=newArtist;
    }
}
