package com.ssafy.algonote.submission.controller;

import com.ssafy.algonote.config.security.SecurityUtil;
import com.ssafy.algonote.submission.dto.request.SubmissionReqDto;
import com.ssafy.algonote.submission.dto.response.SubmissionResDto;
import com.ssafy.algonote.submission.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<Void> saveSubmission(@RequestBody List<SubmissionReqDto> dtos) {
        log.info("사용자 제출 정보: {}", dtos);
        Long memberId = SecurityUtil.getMemberId();
        dtos.stream().forEach(dto -> submissionService.saveSubmission(dto, memberId));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SubmissionResDto>> searchSubmissions(@RequestParam("memberId") Long memberId, @RequestParam("problemId") Long problemId) {
        return ResponseEntity.ok(submissionService.searchSubmissions(memberId, problemId)
                .stream()
                .map(SubmissionResDto::fromDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<SubmissionResDto> searchSubmission(@PathVariable("submissionId") Long submissionId) {
        return ResponseEntity.ok(
                SubmissionResDto.fromDto(submissionService.searchSubmissionById(submissionId))
        );
    }

}