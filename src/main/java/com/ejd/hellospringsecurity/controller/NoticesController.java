package com.ejd.hellospringsecurity.controller;

import com.ejd.hellospringsecurity.model.Notice;
import com.ejd.hellospringsecurity.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticesController {

  private final NoticeRepository noticeRepository;

  @GetMapping
  public ResponseEntity<List<Notice>> getNotices() {
    List<Notice> notices = noticeRepository.findAllActiveNotices();
    if (notices.isEmpty()) {
      return null;
    }
    return ResponseEntity.ok()
        .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
        .body(notices);
  }
}
