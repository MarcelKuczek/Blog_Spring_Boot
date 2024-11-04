CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author VARCHAR(400) NOT NULL,
    title VARCHAR(400) NOT NULL,
    content VARCHAR(2000) NULL,
    created timestamp
);

CREATE TABLE comment(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    content VARCHAR(2000) NULL,
    created timestamp
);

ALTER TABLE comment
    ADD CONSTRAINT comment_post_id
    FOREIGN KEY (post_id) REFERENCES post(id)