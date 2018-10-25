DROP TABLE IF EXISTS executions;
CREATE TABLE executions(
  id uuid PRIMARY KEY,
  timestamp VARCHAR(100),
  commands integer,
  result integer,
  steps integer,
  duration DOUBLE PRECISION
);