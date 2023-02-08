package com.example.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.login.dto.User;
import com.example.login.repository.UserRepository;
import com.example.security.service.PrincipalDetails;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private UserRepository userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	// 인가가 필요한 주소 요청이 있을 때 해당 필터를 반드시 실행함
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("인가가 필요한 주소 요청이 실행되는 메소드 : doFilterInternal()");

		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader :" + jwtHeader);

		// JWT 토큰 검증을 해서 정상적인 사용자인지 확인=> 정상적인 요청이 아닌경우
		if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}

		// JWT 토큰 검증을 해서 정상적인 사용자인지 확인=> 정상적인 요청인 경우
		String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
		String username = JWT.require(Algorithm.HMAC512("mySecurityCos")).build().verify(jwtToken).getClaim("username")
				.asString();

		// 서명이 정상적으로 처리되었으면
		if (username != null) {
			// spring security가 수행해주는 권한 처리를 위해 아래와 같이 토큰을 만들어 Authentication객체를 강제로 만들고 세션에
			// 넣어준다.
			User user = userRepository.getUserAccount(username);
			PrincipalDetails principalDetails = new PrincipalDetails(user);
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,
					principalDetails.getAuthorities());

			// 강제로 시큐리티의 세션에 접근하여 값 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}
}
