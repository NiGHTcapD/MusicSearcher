package com.egl.music.musicsearcher.repository;

import com.egl.music.musicsearcher.models.TimeSignature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSignatureRepository extends JpaRepository<TimeSignature, Integer> {

    @Query("select ts.songId from TimeSignature ts where ts.timeSignature = :signature")
    List<Integer> findByTimeSignature(@Param("signature") String signature);

    @Query("select ts.timeSignature from TimeSignature ts where ts.id = :songId")
    List<String> findTimeSignatureBySongId(@Param("songId") int songId);

}
