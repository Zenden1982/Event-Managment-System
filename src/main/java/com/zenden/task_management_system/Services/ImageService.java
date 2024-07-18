package com.zenden.task_management_system.Services;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;

@Service
@Component
public class ImageService {
    
    @Value("${app.image.bucket:C:\\Users\\nikit\\VISUALCODE\\task-management-system\\images}")
    private String bucket;

    @SneakyThrows
    public void uploadImage(String imagePath, InputStream inputStream) {
        Path fullPath = Path.of(bucket, imagePath.replace("/", "\\"));
        try(inputStream) {
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, inputStream.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }

    }

    @SneakyThrows
    public Optional<byte[]> getImage(String imagePath) {
        Path fullPath = Path.of(bucket, imagePath.replace("/", "\\"));
        if (Files.exists(fullPath)) {
            return Optional.of(Files.readAllBytes(fullPath));
        }
        return Optional.empty();
    }
}
