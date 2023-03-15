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

    public List<Integer> getSongsByMusicKeys(String key) {
        return musicKeyRepository.findByMusicKey(key);
    }

    public List<Integer> getSongsWithMultipleMusicKeys() {
        return musicKeyRepository.findByMusicKey("-1");
    }

    public List<Integer> getSongsWithMultipleMusicKeys(int atLeastThisMany) {
        return musicKeyRepository.findByMusicKey(String.valueOf(-atLeastThisMany));
    }

    public List<String> getMusicKeysForSong(int songId) {
        return musicKeyRepository.findMusicKeyBySongId(songId);
    }
}
