//package com.boots.service;
//
//import com.boots.domain.User;
//import com.boots.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetailsImpl user = UserDetailsImpl.build(userRepository.findByUsername(username));
//                //.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//        return UserDetailsImpl.build(user);
//    }
//
//}