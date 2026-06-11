package com.careeros.controller;

// TODO: BE-003 + BE-004
// Issues: github.com/career-os/careeros-api/issues/3 and /4

import com.careeros.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/upload")
    @Operation(summary = "Upload resume (PDF/DOCX, max 10MB)")
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("X-User-Id") String userId) {

        throw new UnsupportedOperationException("BE-003 not yet implemented");
    }

    @GetMapping
    @Operation(summary = "List all resumes")
    public ResponseEntity<?> list(
            @RequestHeader("X-User-Id") String userId) {

        return ResponseEntity.ok(
                resumeService.getUserResumes(userId)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a resume")
    public ResponseEntity<?> delete(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {

        resumeService.deleteResume(
                UUID.fromString(id),
                userId
        );

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/analyze")
    @Operation(summary = "Trigger ATS analysis")
    public ResponseEntity<?> analyze(@PathVariable String id) {
        throw new UnsupportedOperationException("BE-004 not yet implemented");
    }

    @GetMapping("/{id}/analysis")
    @Operation(summary = "Poll analysis result")
    public ResponseEntity<?> getAnalysis(@PathVariable String id) {
        throw new UnsupportedOperationException("BE-004 not yet implemented");
    }
}