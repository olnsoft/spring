package com.eventostec.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRequestDTO;
import com.eventostec.api.repositories.EventRepository;

@Service 
public class EventService {

    @Autowired 
    private EventRepository repository;

    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;

        if(data.image() != null) {
            imgUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());

        repository.save(newEvent);

        return newEvent;
    }

    private String uploadImg(MultipartFile multipartFile) {
        try {
            // Obtém a raiz do projeto de forma absoluta e segura
            String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";
            Path uploadPath = Paths.get(uploadDir);

            // Cria o diretório se ele não existir
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);

            // Salva o arquivo no caminho absoluto correto
            multipartFile.transferTo(filePath.toFile());

            return "/images/" + filename;
        } catch (Exception e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
