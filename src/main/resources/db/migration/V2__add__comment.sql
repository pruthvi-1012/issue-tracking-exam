CREATE TABLE ISSUE_TRACKER.COMMENT (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  user BIGINT not null,
  issue BIGINT not null,
  description VARCHAR(255) not null,
  created DATE NOT NULL
);

ALTER TABLE ISSUE_TRACKER.COMMENT
ADD FOREIGN KEY (user)
REFERENCES ISSUE_TRACKER.USER(id);

ALTER TABLE ISSUE_TRACKER.COMMENT
ADD FOREIGN KEY (issue)
REFERENCES ISSUE_TRACKER.ISSUE(id);