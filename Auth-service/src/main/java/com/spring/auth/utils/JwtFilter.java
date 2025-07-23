//package com.spring.auth.utils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.spring.auth.entity.User;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public  class JwtFilter extends OncePerRequestFilter{
//	
//	@Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserRepo userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//        String email = null;
//        String jwt = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwt = authHeader.substring(7);
//            email = jwtUtil.extractUsername(jwt);
//        }
//
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            Optional<User> userOpt = userRepository.findByEmail(email);
//            if (userOpt.isPresent() && jwtUtil.validateToken(jwt, email)) {
//                org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
//                        userOpt.get().getEmail(), userOpt.get().getPassword(), new ArrayList<>());
//
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//	
//
//}
