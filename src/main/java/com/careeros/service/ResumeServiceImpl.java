package com.careeros.service;

import com.careeros.dto.response.ResumeResponse;
import com.careeros.dto.response.ResumeUploadResponse;
import com.careeros.repository.ResumeRepository;
import com.careeros.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.careeros.entity.User;
import com.careeros.entity.Resume;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    @Override
    public ResumeUploadResponse uploadResume(
            MultipartFile file,
            String userId) {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<ResumeResponse> getUserResumes(String userId) {

        UUID userUuid = UUID.fromString(userId);

        User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return resumeRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(resume -> new ResumeResponse(
                        resume.getId(),
                        resume.getFileName(),
                        resume.getVersion(),
                        resume.getIsActive(),
                        resume.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public void deleteResume(UUID resumeId, String userId) {

        UUID userUuid = UUID.fromString(userId);

        User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resume resume = resumeRepository
                .findByIdAndUser(resumeId, user)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resumeRepository.delete(resume);
    }
}