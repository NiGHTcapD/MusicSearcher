package com.egl.music.musicsearcher.models;

import jakarta.persistence.*;
import lombok.*;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MusicKey")
@Entity
public class MusicKey {
    @Id
    @Column
    @GeneratedValue
    int IDgnorable;

    @Column(length = 10)
    String musicKey;

    @Column(length = 100)
    String songName;

    @Column
    int songId;

    public MusicKey(String musicKey, String songName, int id) {
        this.musicKey=musicKey;
        this.songName=songName;
        this.songId=id;
    }
}
