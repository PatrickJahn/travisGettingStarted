/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.characterDTO;
import dto.filmDTO;
import dto.planetDTO;
import java.util.List;
import java.util.concurrent.Callable;
import utils.HttpUtils;

/**
 *
 * @author Patrick
 */

             /** ALL CALLABLE CLASSES GOES HERE **/

/* Husk ikke at sætte public foran "class" ellers kan der ikke
    være flere classes i samme fil. Dette gør dog også at man
        kun kan bruge dem i samme package - "facades". */


     class PlanetHandler implements Callable<planetDTO>{
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        String planetUrl;
        public PlanetHandler(String planetUrl){
            this.planetUrl = planetUrl.replace("http", "https");
        }
        
        @Override
        public planetDTO call() throws Exception {
     
            String data = HttpUtils.fetchData(planetUrl);
            planetDTO planetdto = GSON.fromJson(data, planetDTO.class);
                
            return planetdto;
        }
        
    }
    
      class CharacterHandler implements Callable<characterDTO>{
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        String characteUrl;
        public CharacterHandler(String characteUrl){
            this.characteUrl = characteUrl.replace("http", "https");
        }
        
        @Override
        public characterDTO call() throws Exception {
     
            String data = HttpUtils.fetchData(characteUrl);
            characterDTO charactedto = GSON.fromJson(data, characterDTO.class);
                
            return charactedto;
        }
        
      }

        class filmHandler implements Callable<filmDTO>{
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        String filmUrl;
        List<String> likedUrl;
        public filmHandler(int number, List<String> likedUrl){
          this.likedUrl = likedUrl;
            this.filmUrl = "https://swapi.dev/api/films/" + number +"/";
        }
        
        @Override
        public filmDTO call() throws Exception {
     
            String data = HttpUtils.fetchData(filmUrl);
            filmDTO filmdto = GSON.fromJson(data, filmDTO.class);
           
            if (likedUrl.contains(filmUrl)){
                filmdto.setLiked();
                System.out.println("LIKED");
            }
              
            return filmdto;
        }
        
    }