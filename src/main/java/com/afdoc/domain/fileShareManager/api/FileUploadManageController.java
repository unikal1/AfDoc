package com.afdoc.domain.fileShareManager.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/upload")
public class FileUploadManageController {

    @PostMapping("/file")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        return ResponseEntity.ok("test");
    }
}
