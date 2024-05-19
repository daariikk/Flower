DROP TABLE IF EXISTS user_flower;
CREATE TABLE user_flower
(
    user_id BIGINT REFERENCES _user (id),
    flower_id  BIGINT REFERENCES flower (id),
    PRIMARY KEY (user_id, flower_id)
)