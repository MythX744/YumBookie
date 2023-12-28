package com.example.Project.Service;

import com.example.Project.Model.Comment;
import com.example.Project.Model.Favorite;
import com.example.Project.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService{
    private CommentDao commentDao;
    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment findById(int id) {
        Optional<Comment> result = commentDao.findById(id);

        Comment comment = null;

        if (result.isPresent()) {
            comment = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return comment;
    }

    @Override
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public void deleteById(int id) {
        commentDao.deleteById(id);
    }
}
