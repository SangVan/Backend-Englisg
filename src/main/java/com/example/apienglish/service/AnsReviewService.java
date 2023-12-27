package com.example.apienglish.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apienglish.entity.AnsReview;
import com.example.apienglish.entity.Lesson;
import com.example.apienglish.repository.AnsReviewRepository;
import com.example.apienglish.repository.LessonRepository;

@Service
public class AnsReviewService {
	@Autowired
	private AnsReviewRepository ansReviewRepository;

	@Autowired
	private LessonRepository lessonRepository;

	public AnsReview addAnsReview(String name, String explainRight, String explainWrong, Long lessonId)
			throws IOException {

		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
		AnsReview ansReview = new AnsReview();

		ansReview.setName(name);
		ansReview.setExplainRight(explainRight);
		ansReview.setExplainWrong(explainWrong);
		ansReview.setLesson(lesson);

		return ansReviewRepository.save(ansReview);

	}

	public List<AnsReview> getAllAnsReview() {
		return ansReviewRepository.findAll();
	}

	public AnsReview getAnsReviewById(Long id) {
		return ansReviewRepository.findById(id).orElse(null);
	}
}
