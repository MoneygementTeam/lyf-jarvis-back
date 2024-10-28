package com.moneygement.lyf.jarvis.history.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moneygement.lyf.jarvis.history.domain.History;
import com.moneygement.lyf.jarvis.history.domain.HistoryCreateRequest;
import com.moneygement.lyf.jarvis.history.domain.HistoryResponse;
import com.moneygement.lyf.jarvis.history.persistence.HistoryRepository;
import com.moneygement.lyf.jarvis.user.domain.User;
import com.moneygement.lyf.jarvis.user.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

	private final HistoryRepository historyRepository;
	private final UserService userService;

	@Override
	public History save(HistoryCreateRequest request) {
		validateUserId(request.userId());

		return historyRepository.save(
			new History(request.userId(),
				request.groupId(),
				request.request(),
				request.response()));
	}

	@Override
	public List<HistoryResponse> findByUserId(String userId) {
		validateUserId(userId);
		return historyRepository.findByUserId(userId).stream().map(
			history -> new HistoryResponse(history.getUserId(),
				history.getGroupId(),
				history.getResponse(),
				history.getRequest(),
				history.getCreatedDate()
				)
		).toList();
	}

	/**
	 * UserId가 실제 존재하는지 확인
	 * @param userId
	 */
	private void validateUserId(String userId) {
		Optional<User> user = userService.findById(userId);

		if(user.isEmpty()) {
			throw new EntityNotFoundException("user not found!");
		}
	}
}
