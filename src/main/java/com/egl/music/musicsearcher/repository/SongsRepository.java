package com.egl.music.musicsearcher.repository;

import com.egl.music.musicsearcher.models.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongsRepository extends JpaRepository<Songs, Integer> {

    //find id by title
    @Query("select song.id from Songs song where song.songTitle LIKE '%title%'")
    List<Integer> findBySongTitle(@Param("title") String title);

    //find id by artist
    @Query("select song.id from Songs song where song.Artist LIKE '%artist%'")
    List<Integer> findBySongArtist(@Param("artist") String artist);

    //find everything by id

    //LatestHit (largest id)
    @Query("select MAX(album.id) from Songs album")
    Integer LatestHit();

}
