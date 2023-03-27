package com.example.instagram.Advice;

import com.example.instagram.Exception.Posts.NotFoundFeedException;
import com.example.instagram.Exception.Posts.NotFoundPostsException;
import com.example.instagram.Exception.ChatRoom.NotFoundChatRoomException;
import com.example.instagram.Exception.Comment.NotFoundCommentException;
import com.example.instagram.Exception.DirectMessage.NotFoundDirectMessageException;
import com.example.instagram.Exception.Follow.AlreadyFollowException;
import com.example.instagram.Exception.Replies.NotFoundRepliesException;
import com.example.instagram.Exception.Token.NotMatchRefreshTokenException;
import com.example.instagram.Exception.User.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NotFoundPostsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundBoardException() {
        return "게시글이 존재하지 않습니다.";
    }

    @ExceptionHandler(NotFoundCommentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundCommentException() {
        return "댓글이 존재하지 않습니다.";
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundUserException() {
        return "유저가 존재하지 않습니다.";
    }

    @ExceptionHandler(NotFoundChatRoomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundChatRoomException() {
        return "DM이 존재하지 않습니다.";
    }

    @ExceptionHandler(NotFoundDirectMessageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundDirectMessageException() {
        return "DM이 내용이 존재하지 않습니다.";
    }

    @ExceptionHandler(NotFoundRepliesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundRepliesException() {
        return "답글이 존재하지 않습니다.";
    }

    @ExceptionHandler(AlreadyFollowException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String alreadyFollowException() {
        return "이미 팔로우 한 유저입니다.";
    }

    @ExceptionHandler(NotFoundFeedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundFeedException() {
        return "피드가 존재하지 않습니다.";
    }

    @ExceptionHandler(NotMatchRefreshTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notMatchRefreshTokenException() {
        return "토큰정보가 일치하지 않습니다.";
    }
}
