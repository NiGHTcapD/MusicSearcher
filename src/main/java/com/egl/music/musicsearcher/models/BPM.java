package com.egl.music.musicsearcher.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BPM")
@Entity
public class BPM {
    @Id
    @Column
    @GeneratedValue
    int IDgnorable;

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
}
