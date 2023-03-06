package com.egl.music.musicsearcher.services;

import com.egl.music.musicsearcher.repository.MusicKeyRepository;
import com.egl.music.musicsearcher.models.MusicKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicKeyService{
    private final MusicKeyRepository musicKeyRepository;

    public MusicKeyService(MusicKeyRepository musicKeyRepository){
        this.musicKeyRepository = musicKeyRepository;
    }

    public MusicKey createMusicKeyPair(String key, String songName, int id) {
        MusicKey newMusicKey = new MusicKey(key, songName, id);
        return musicKeyRepository.saveAndFlush(newMusicKey);
    }


    //GetMapping("/getSongsByBPM")
    public List<Integer> getSongsByMusicKeys(String key) {
        //log.warn("Searching BPM table for entries...");
        List<Integer> songIdList = musicKeyRepository.findByMusicKey(key);
        return songIdList;
    }

    public List<Integer> getSongsWithMultipleMusicKeys() {
        //log.warn("Searching BPM table for entries...");
        List<Integer> songIdList = musicKeyRepository.findByMusicKey("-1");
        return songIdList;
    }

    public List<Integer> getSongsWithMultipleMusicKeys(int atLeastThisMany) {
        //log.warn("Searching BPM table for entries...");
        List<Integer> songIdList = musicKeyRepository.findByMusicKey(String.valueOf(-atLeastThisMany));
        return songIdList;
    }

    public List<String> getMusicKeysForSong(int songId) {
        List<String> songIdList = musicKeyRepository.findMusicKeyBySongId(songId);
        return songIdList;
    }
}
