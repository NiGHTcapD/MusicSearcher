package com.egl.music.musicsearcher.models;


import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TimeSignature")
@Entity
public class TimeSignature {
    @Id
    @Column
    @GeneratedValue
    int IDgnorable;

    @Column(length = 10)
    String timeSignature;

    @Column(length = 100)
    String songName;

    @Column
    int songId;

    public TimeSignature(String timeSignature, String songName, int id) {
        this.timeSignature=timeSignature;
        this.songName=songName;
        this.songId=id;
    }
}
