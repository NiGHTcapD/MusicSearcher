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
//@RequiredArgsConstructor
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
