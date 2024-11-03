package org.example.diary.api;

import org.example.diary.api.dto.request.MemberCreateRequest;
import org.example.diary.api.dto.request.SignInRequest;
import org.example.diary.api.dto.response.MemberCreateResponse;
import org.example.diary.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MembersController {

    private final MemberService memberService;

    public MembersController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    ResponseEntity<Void> createUser(@RequestBody MemberCreateRequest request){
        memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    ResponseEntity<MemberCreateResponse> signIn(@RequestBody SignInRequest request){
         final MemberCreateResponse response =  new MemberCreateResponse(memberService.signIn(request));
        return ResponseEntity.ok(response);
    }




}
