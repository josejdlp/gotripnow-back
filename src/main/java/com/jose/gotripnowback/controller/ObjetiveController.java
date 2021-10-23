package com.jose.gotripnowback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.repository.ObjetiveRepository;
import com.jose.gotripnowback.service.ObjetiveService;
import com.jose.gotripnowback.util.Constants;
import com.jose.gotripnowback.util.FileUploadUtil;
import com.jose.gotripnowback.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/objetives")
@CrossOrigin(origins = "*")
public class ObjetiveController {
    @Autowired
    ObjetiveRepository objetiveRepository;

    @Autowired
    ObjetiveService objetiveService;

    @GetMapping()
    ResponseEntity<List<Objetive>> getObjetives(){
        return new ResponseEntity(objetiveService.getObjetives(),HttpStatus.OK);
    }


    @PostMapping()
    ResponseEntity<Integer> createObjetive(@RequestBody Objetive objetive){
        return objetiveService.createObjetive(objetive);
    }

    @PostMapping("/{id}/image/create")
    ResponseEntity<String> create(@RequestParam(value = "file", required = true)MultipartFile multipartFile,
                                  @PathVariable("id") String idObjetive)  {
        String username= UserUtils.getUsernameLogged();
        //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "photos/objetives";

        String fileLocation = new File(uploadDir).getAbsolutePath();


        try {
            FileUploadUtil.saveFile(fileLocation, idObjetive, multipartFile);
        }catch (IOException e){
            log.error("IO exception "+e);
        }

        return new ResponseEntity(uploadDir+ idObjetive,HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    ResponseEntity<Resource> getImageWithMediaType(@PathVariable("id") String idObjetive) throws IOException {

        String fileLocation = new File("photos/objetives").getAbsolutePath();

         ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                fileLocation+"/"+idObjetive+".jpg"
        )));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);
    }
}
