package com.egl.music.musicsearcher.repository;

import com.egl.music.musicsearcher.models.BPM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BPMRepository extends JpaRepository<BPM, Integer> {

    @Query("select b.songId from BPM b where b.beatsPerMinute = :bpm")
    List<Integer> findByBPM(@Param("bpm") int bpm);

    @Query("select b.beatsPerMinute from BPM b where b.id = :songId")
    List<Integer> findBPMBySongId(@Param("songId") int songId);

}
