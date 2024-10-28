package com.moneygement.lyf.jarvis.route.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneygement.lyf.jarvis.route.domain.OptimalRouteRequest;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteResponse;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteUserResponse;
import com.moneygement.lyf.jarvis.route.service.RouteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
@Tag(name = "Route", description = "최단 경로 정보 제공 API")
public class RouteController {

	private final RouteService routeService;

	@PostMapping("")
	@Operation(summary = "최단 경로 정보 제공", description = "좌표와 교통수단 기준의 최단 경로 정보를 제공합니다.")
	public ResponseEntity<OptimalRouteUserResponse> getOptimalRouteRequest(@RequestBody OptimalRouteRequest optimalRouteRequest)
		throws IOException {
		OptimalRouteResponse optimalRoute = routeService.getOptimalRoute(optimalRouteRequest);
		return ResponseEntity.ok(new OptimalRouteUserResponse(optimalRoute.getPaths().get(0).getPoints().getCoordinates()));
	}
}
