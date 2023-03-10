package com.egl.music.musicsearcher.controllers;


import com.egl.music.musicsearcher.models.SongsReturnable;
import com.egl.music.musicsearcher.services.BPMService;
import com.egl.music.musicsearcher.services.MusicKeyService;
import com.egl.music.musicsearcher.services.SongsService;
import com.egl.music.musicsearcher.services.TimeSignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SongSearchController {

    SongsService songsService;
    BPMService bpmService;
    MusicKeyService musicKeyService;
    TimeSignatureService timeSignatureService;

    List<Integer> beats;
    List<String> keys;
    List<String> sigs;

    @Autowired
    public SongSearchController(SongsService songsService, BPMService bpmService, MusicKeyService musicKeyService, TimeSignatureService timeSignatureService) {
        this.songsService = songsService;
        this.bpmService = bpmService;
        this.musicKeyService = musicKeyService;
        this.timeSignatureService = timeSignatureService;
    }

    //@GetMapping(value = "/index", produces = MediaType.TEXT_HTML_VALUE)
    //@ResponseBody
    //public String index(){
    //    return "forward:index.html";
    //}

    @GetMapping("new-index")
    public String indexNewSong(@ModelAttribute FrontPageRequest requestData) {
        //do logic to all incoming data and strings...mostly strings
        //take song-title at its word as the title of the song
        //take artist at its word as the artist of the song

        convertToLists(requestData);

        //and send them into
        songsService.createSong(requestData.songTitle, requestData.artist, beats, keys, sigs);

        return "index.html";
    }

    protected void convertToLists(FrontPageRequest requestData) {
        beats = convertStringToIntegerList(requestData.beatsPer);

        //look for split characters in music-keys
        keys = Arrays.asList(requestData.musicKeys.split("\\s*,\\s*"));

        //look for split characters in time-signs
        sigs = Arrays.asList(requestData.timeSigns.split("\\s*,\\s*"));
    }

    protected List<Integer> convertStringToIntegerList(String string) {
        return Arrays.stream(string.split("\\s*,\\s*"))
                .mapToInt(Integer::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @GetMapping("search")
    public String SearchForSongs(@ModelAttribute FrontPageRequest requestData, Model model) {
        //an array of Int arrays
        List<List<Integer>> identifiers = new ArrayList<>();


        //do logic to all incoming data and strings...mostly strings
        //take song-title at its word as the title of the song
        identifiers.add(songsService.getSongByName(requestData.songTitle));
        //take artist at its word as the artist of the song
        identifiers.add(songsService.getSongByArtist(requestData.artist));

        //if beats-per-many is not 1 or 0, send it in. Otherwise, go right ahead and search.
        if (requestData.beatsPerMany > 1) {
            identifiers.add(bpmService.getSongsWithMultipleBPM(requestData.beatsPerMany));
        } else {
            if (!"".equals(requestData.beatsPer)) {
                identifiers.add(bpmService.getSongsByBPM(Integer.parseInt(requestData.beatsPer)));
            }
            else{
                identifiers.add(null);
            }
        }

        //if music-keys-many is not 1 or 0, send it in. Otherwise, go right ahead and search.
        if (requestData.musicKeysMany > 1) {
            identifiers.add(musicKeyService.getSongsWithMultipleMusicKeys(requestData.musicKeysMany));
        } else {
            identifiers.add(musicKeyService.getSongsByMusicKeys(requestData.musicKeys));
        }

        //if time-signs-many is not 1 or 0, send it in. Otherwise, go right ahead and search.
        if (requestData.timeSignsMany > 1) {
            identifiers.add(timeSignatureService.getSongsWithMultipleTimeSignatures(requestData.timeSignsMany));
        } else {
            identifiers.add(timeSignatureService.getSongsByTimeSignatures(requestData.timeSigns));
        }

        //Consider adding logic to find multiple of an aspect, and then AND it separately.

        //Add logic to detect any NULL lists, and add them to a list of aspects that returned no results, so they can be ignored

        for (int index= identifiers.size()-1; index>=0;index--) {
            if (identifiers.get(index) == null) {
                //Nothing found for that aspect.
                //Excise element.
                identifiers.remove(index);
            }
        }

        //AND the other lists together
        //Loop ANDTwoLists(identifiers.get(0), identifiers.get(1))
        // and excising element 1
        // until identifiers is 1 long.
        while (identifiers.size() > 1) {
            ANDTwoLists(identifiers.get(0), identifiers.get(1));
            //Excise element 1.
            identifiers.remove(1);
        }

        List<SongsReturnable> returnables = songsService.returnSearchedSongs(identifiers.get(0));

        //What to do with all that data is up to javascript...

        model.addAttribute("songsReturnable", returnables);
        System.out.print(returnables);

        return "index.html";
    }

    protected List<Integer> ANDTwoLists(List<Integer> baseList, List<Integer> otherList) {
        return baseList.stream()
                .distinct()
                .filter(otherList::contains)
                .collect(Collectors.toList());
    }


}
