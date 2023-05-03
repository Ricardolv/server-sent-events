package com.richard.infrastructure.persistence.repositories;

import com.richard.infrastructure.persistence.entities.Comment;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Repository
public class CommentRepository {
    public  Flux<Comment> findAll() {
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(this::generateComment)
                .flatMapIterable(x -> x);
    }

    private List<Comment> generateComment(long interval) {

        Comment obj = new Comment(
                CommentGenerator.randomAuthor(),
                CommentGenerator.randomMessage(),
                CommentGenerator.getCurrentTimeStamp());
        return List.of(obj);

    }

}
