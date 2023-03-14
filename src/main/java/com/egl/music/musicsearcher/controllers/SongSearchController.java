package com.egl.music.musicsearcher.controllers;


import com.egl.music.musicsearcher.models.SongsReturnable;
import com.egl.music.musicsearcher.services.SearchService;
import com.egl.music.musicsearcher.services.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class SongSearchController {

    SongsService songsService;

    SearchService searchService;

    List<Integer> beats;
    List<String> keys;
    List<String> sigs;

    @Autowired
    public SongSearchController(SongsService songsService, SearchService searchService) {
        this.songsService = songsService;
        this.searchService = searchService;
    }


    @PostMapping("new-index")
    public String indexNewSong(@ModelAttribute FrontPageRequest requestData, Model model) {

        convertToLists(requestData);

        try{
            songsService.createSong(requestData.songTitle, requestData.artist, beats, keys, sigs);
            model.addAttribute("message", "Save successful!");
        }
        catch(Exception e) {
            model.addAttribute("message", "That didn't work for whatever reason. Please try again.");
        }


        return "index";
    }

    protected void convertToLists(FrontPageRequest requestData) {
        beats = convertStringToIntegerList(requestData.beatsPer);

        //look for split characters in music-keys
        keys = Arrays.asList(requestData.musicKeys.split("\\s*,\\s*"));

        //look for split characters in time-signs
        sigs = Arrays.asList(requestData.timeSigns.split("\\s*,\\s*"));
    }

    protected List<Integer> convertStringToIntegerList(String string) {
        if (string.isEmpty()){
            return Collections.emptyList();
        }
        return Arrays.stream(string.split("\\s*,\\s*"))
                .mapToInt(Integer::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @PostMapping("search")
    public String SearchForSongs(@ModelAttribute FrontPageRequest requestData, Model model) {
        List<SongsReturnable> returnables = searchService.getSongsReturnables(requestData);


        if (returnables.size()==0){
            model.addAttribute("message", "No results.");
        }
        else{
            model.addAttribute("songsReturnable", returnables);
            model.addAttribute("message", "Search successful. If results don't match an aspect, there were no matches for that part.");
        }

        return "index";
    }



}
