package com.egl.music.musicsearcher.repository;

import com.egl.music.musicsearcher.models.MusicKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicKeyRepository extends JpaRepository<MusicKey, Integer> {

    @Query("select mk.songId from MusicKey mk where mk.musicKey = :key")
    List<Integer> findByMusicKey(@Param("key") String key);

    @Query("select mk.musicKey from MusicKey mk where mk.id = :songId")
    List<String> findMusicKeyBySongId(@Param("songId") int songId);

}